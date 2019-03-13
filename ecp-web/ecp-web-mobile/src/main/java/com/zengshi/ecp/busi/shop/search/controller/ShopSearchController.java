package com.zengshi.ecp.busi.shop.search.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.shop.ShopSearchUtil;
import com.zengshi.ecp.busi.shop.vo.GoodSearchPromVO;
import com.zengshi.ecp.busi.shop.vo.GoodsPromInfoJsonBean;
import com.zengshi.ecp.busi.shop.vo.ShopScrollResult;
import com.zengshi.ecp.busi.shop.vo.ShopSearchPageReqVO;
import com.zengshi.ecp.busi.shop.vo.ShopSearchResult;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.LogUtil;

@Controller
@RequestMapping(value = "/shopsearch")
public class ShopSearchController extends EcpBaseController{
    private static final String MODUAL = ShopSearchController.class.getName();
    private static final String URL = "/shop/search";
    @Resource
    private IPromRSV promRSV;
    @RequestMapping(value="")
    public String init(Model model,ShopSearchPageReqVO vo){
        model.addAttribute("keyword",vo.getKeyword());
        model.addAttribute("searchType", "2");
        return URL + "/shop-search";
    }
    /**
     * 
     * scroller:(滚动分页). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/scroller")
    @ResponseBody
    public ShopScrollResult scroller(Model model,ShopSearchPageReqVO vo){
        ShopScrollResult result = new ShopScrollResult();
        // 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);
        if(vo.getPage() >= 2){
            vo.setPageNumber(vo.getPage());
        }
        try {
            SearchResult<ShopSearchResult> shopResult = ShopSearchUtil.searchShop(vo);
            if(shopResult.isSuccess()){
                if(shopResult.getResultList() != null && shopResult.getResultList().size() >= 1){
                    for(ShopSearchResult shopSearchResult : shopResult.getResultList()){
                        shopSearchResult.render();       
                    }
                }
            }
            result.setDatas(shopResult.getResultList());
            result.setTotal(shopResult.getTotallyPage());
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "获取店铺列表失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODUAL, "获取店铺列表失败！", e);
        }
        return result;
    }
    
    @RequestMapping(value="/queryshopgds")
    @ResponseBody
    public Model queryShopGds(Model model,GoodSearchPageReqVO vo){
        // 初始分页长度设置
        vo.setPageSize(SearchUtil.DEFAULT_PAGESIZE);
        SearchResult<Map<String,Object>> result = SearchUtil.searchGood(vo);
        if (result.isSuccess()) {
            EcpBasePageRespVO<Map<String,Object>> pageRespVO = SearchUtil.renderRespVO(vo, result);
            super.addPageToModel(model, pageRespVO);
        }
        return model;
    }
    /**
     * 
     * queryPromInfo:(异步加载获取促销信息列表). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param goodSearchPromVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryPromInfo")
    @ResponseBody
    public GoodsPromInfoJsonBean queryPromInfo(GoodSearchPromVO goodSearchPromVO) {
        GoodsPromInfoJsonBean bean = new GoodsPromInfoJsonBean();
        // 获取促销信息
        PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
        promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
        promRuleCheckDTO.setGdsId(goodSearchPromVO.getGdsId());
        promRuleCheckDTO.setChannelValue("2");
        promRuleCheckDTO.setShopId(goodSearchPromVO.getShopId());
        promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
        promRuleCheckDTO.setSkuId(goodSearchPromVO.getSkuId());
        promRuleCheckDTO.setBasePrice(goodSearchPromVO.getRealPrice());
        promRuleCheckDTO.setBuyPrice(goodSearchPromVO.getDiscountPrice());
        promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
        
        
        List<PromListRespDTO> promInfoDTOList = promRSV.listPromForSolr(promRuleCheckDTO);

        String[] promTypes = null;

        if (CollectionUtils.isNotEmpty(promInfoDTOList)) {
            promTypes = new String[promInfoDTOList.size()];
            for (int i = 0; i < promInfoDTOList.size(); i++) {
                //取第一条价格
                if(i==0){
                    if(promInfoDTOList.get(i).getPromSkuPriceRespDTO() != null){
                        bean.setPrice(promInfoDTOList.get(i).getPromSkuPriceRespDTO().getDiscountFinalPrice());
                        bean.setGuidePrice(promInfoDTOList.get(i).getPromSkuPriceRespDTO().getDiscountCaclPrice());
                    }
                }
                if(promInfoDTOList.get(i).getPromInfoDTO() != null){
                    promTypes[i] = promInfoDTOList.get(i).getPromInfoDTO().getPromTypeShow();
                }
            }
        }

        List<String> typeList = new ArrayList<String>();
        if(org.apache.commons.lang.StringUtils.equals(goodSearchPromVO.getIfFree(),SearchConstants.STATUS_VALID)){
            typeList.add("免邮");
        }
        if (promTypes != null) {
            for (String type : promTypes) {
                if (!typeList.contains(type)) {
                    typeList.add(type);
                }
            }
            // 取前三的促销类型
            if (typeList.size() > 3) {
                List<String> greatThan3Type = new ArrayList<String>();
                for (int i = 3; i < typeList.size(); i++) {
                    greatThan3Type.add(typeList.get(i));
                }
                typeList.removeAll(greatThan3Type);
            }

        }
        bean.setPromTypes(typeList);
        return bean;
    }
}


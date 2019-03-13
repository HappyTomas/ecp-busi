package com.zengshi.ecp.busi.staff.buyer.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.search.SearchUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchPageReqVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.busi.staff.buyer.vo.FavShopsVO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 买家：店铺收藏<br>
 * Date:2016-4-25上午10:59:34  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/favshops")
public class FavShopsController extends EcpBaseController {

	private static String URL = "/staff/buyer";

	@Resource
	private IShopCollectRSV shopCollectRSV;
	
	private static int shopPageSize = 2;//店铺收藏的默认分页数
	
	/**
	 * 
	 * init:(我的店铺收藏). <br/> 
	 * 
	 * @author huangxl5
	 * @param model
	 * @param reqVo
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping("/index")
	public String init(Model model, FavShopsVO reqVo) {
	    /*初始化查询出两条店铺记录*/
	    ShopCollectReqDTO collect = new ShopCollectReqDTO();
	    collect.setPageNo(reqVo.getPageNumber());
	    collect.setPageSize(2);
	    collect.setStaffId(collect.getStaff().getId());//当前登录用户id
	    String sName = "";
	    if (StringUtil.isNotBlank(reqVo.getShopName())) {
	        try {
                sName = URLDecoder.decode(reqVo.getShopName(),"UTF-8");
                collect.setShopName(sName);
            } catch (Exception e) {
                e.printStackTrace();
            }
	        
	    }
	    PageResponseDTO<ShopInfoResDTO> shopPage = shopCollectRSV.listShopCollectForFav(collect);
	    
	    /*查询店铺的收藏人气*/
	    this.fullToFavShops(shopPage);
	    
	    model.addAttribute("shopPage", shopPage);
	    model.addAttribute("shopName", sName);
		
		return URL + "/favShops/member-favShops";
	}

	/**
	 * 
	 * fullToFavShops:(查询店铺的收藏人气). <br/> 
	 * 
	 * @author huangxl5
	 * @param shopPage 
	 * @since JDK 1.6
	 */
	private void fullToFavShops(PageResponseDTO<ShopInfoResDTO> shopPage) {
	    if (shopPage != null && !CollectionUtils.isEmpty(shopPage.getResult())) {
	        for (ShopInfoResDTO res : shopPage.getResult()) {
	            ShopCollectReqDTO coll = new ShopCollectReqDTO();
	            coll.setShopId(res.getId() + "");
	            //根据店铺id，查询店铺的收藏人气
	            long collCount = shopCollectRSV.countByShopId(coll);
	            res.setCollectCount(collCount);
	        }
	    }
	}
	
	
	/**
	 * 
	 * shoplist:(店铺列表). <br/> 
	 * 
	 * @author huangxl5
	 * @param model
	 * @param reqVo
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
    @RequestMapping(value="/shoplist")
    public String shoplist(Model model,FavShopsVO reqVo) throws BusinessException{
        
        /*初始化查询出两条店铺记录*/
        ShopCollectReqDTO collect = new ShopCollectReqDTO();
        collect.setPageNo(reqVo.getPageNumber());
        collect.setPageSize(reqVo.getPageSize());
        collect.setStaffId(collect.getStaff().getId());//当前登录用户id
        PageResponseDTO<ShopInfoResDTO> shopPage = shopCollectRSV.listShopCollectForFav(collect);
        
        /*查询店铺的收藏人气*/
        this.fullToFavShops(shopPage);
        
        model.addAttribute("shopPage", shopPage);
        
        return URL + "/favShops/div/favShopsDiv";
    }
	
	
	/**
	 * 
	 * cancel:(取消关注). <br/> 
	 * 
	 * @author huangxl5
	 * @param model
	 * @param shopId
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/cancel")
    @ResponseBody
    public EcpBaseResponseVO cancel(Model model,@RequestParam("shopId")long shopId)throws BusinessException{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        ShopCollectReqDTO req = new ShopCollectReqDTO();
        req.setShopId(shopId + "");//店铺id
        req.setStaffId(req.getStaff().getId());//当前用户
        //调用业务逻辑，取消关注店铺
        shopCollectRSV.deleteShopBySel(req);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
	
	/**
	 * 
	 * hotGoodsList:(店铺热销商品). <br/> 
	 * 
	 * @author huangxl5
	 * @param model
	 * @param shopId
	 * @return 
	 * @since JDK 1.6
	 */
    @RequestMapping("/hotgoods")
    public String hotGoodsList(Model model,FavShopsVO vo) {
        vo.setPageSize(4);//大小固定为4
        EcpBasePageRespVO<Map> res = SearchUtil.shopHotSales(vo, vo.getShopId());
        model.addAttribute("goodsPage", res);
        model.addAttribute("shopId", vo.getShopId());
        model.addAttribute("selType", "hot");
        return URL + "/favShops/div/shopGoodsDiv";
    }
    
    /**
     * 
     * newGoodsList:(店铺新上架商品). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/newgoods")
    public String newGoodsList(Model model,FavShopsVO vo) {
    	vo.setPageSize(4);//大小固定为4
        EcpBasePageRespVO<Map> goodsRes = SearchUtil.shopNewSales(vo, vo.getShopId());
        model.addAttribute("shopId", vo.getShopId());
        model.addAttribute("goodsPage", goodsRes);
        model.addAttribute("selType", "new");
        return URL + "/favShops/div/shopGoodsDiv";
    }
}


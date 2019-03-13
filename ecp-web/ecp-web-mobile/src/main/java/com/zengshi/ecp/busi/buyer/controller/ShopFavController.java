package com.zengshi.ecp.busi.buyer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.buyer.vo.CollectionShopInfoVO;
import com.zengshi.ecp.busi.buyer.vo.CollectionShopResult;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: 店铺收藏<br>
 * Date:2016年8月2日上午9:35:27  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/shopfav")
public class ShopFavController  extends EcpBaseController{
    
	@Resource
	private IShopCollectRSV shopCollectRSV;
	
	@Resource(name = "reportGoodPayedRSV")
    private IReportGoodPayedRSV reportGoodPayedRSV;
	
	@Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @RequestMapping(value="/index")
    public String index(Model model) {
    	ShopCollectReqDTO collect = new ShopCollectReqDTO();
        collect.setStaffId(collect.getStaff().getId());
        collect.setPageNo(1);
        collect.setPageSize(1);
    	/*1、调用业务查询接口，查询我收藏的店铺*/
        PageResponseDTO<ShopInfoResDTO> shopPage = shopCollectRSV.listShopCollectForFav(collect);
        if (shopPage == null || CollectionUtils.isEmpty(shopPage.getResult())) {
        	model.addAttribute("shopnodata", "true");
        }
        model.addAttribute("showWhat", "shop");
    	return "/buyer/shopfav/shop-fav";
    }
    
    @RequestMapping(value="/listdata")
    @ResponseBody
    public CollectionShopResult listdata(Model model,@RequestParam(value="page",required=false)int pageNo) {
    	ShopCollectReqDTO collect = new ShopCollectReqDTO();
        collect.setStaffId(collect.getStaff().getId());
        if(pageNo >=2){
            collect.setPageNo(pageNo);
        }else{
            collect.setPageNo(1);
        }
       
        collect.setPageSize(5);
    	/*1、调用业务查询接口，查询我收藏的店铺*/
        PageResponseDTO<ShopInfoResDTO> shopPage = shopCollectRSV.listShopCollectForFav(collect);
        CollectionShopResult result = new CollectionShopResult();
        List<CollectionShopInfoVO> resList = new ArrayList<CollectionShopInfoVO>();
        if (shopPage != null && CollectionUtils.isNotEmpty(shopPage.getResult())) {
        	result.setTotal(shopPage.getPageCount());
        	for (ShopInfoResDTO shop : shopPage.getResult()) {
        		CollectionShopInfoVO collectonShop = new CollectionShopInfoVO();
        		ObjectCopyUtil.copyObjValue(shop, collectonShop, null, false);
        		//查询店铺销售的商品
        		RGoodSaleRequest rgoodSaleRequest = new RGoodSaleRequest();
                rgoodSaleRequest.setShopId(shop.getId());
                Long saleGdsCnt = this.reportGoodPayedRSV.querySumBuyNumByShopId(rgoodSaleRequest);
                if (saleGdsCnt != null) {
                	collectonShop.setSaleGdsCnt(saleGdsCnt);
                }
                //查询店铺的商品数量
                GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
                gdsInfoReqDTO.setShopId(shop.getId());
                List<String> list=new ArrayList<String>();
                list.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
                list.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
                list.add(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
                gdsInfoReqDTO.setGdsStatusArr(list);
                Long gdsCnt = gdsInfoQueryRSV.countGdsInfoByShopIDAndStatusArr(gdsInfoReqDTO);
                if (gdsCnt != null) {
                	collectonShop.setGdsCnt(gdsCnt);
                }
                resList.add(collectonShop);
        	}
        }
        result.setDatas(resList);
    	return result;
    }
    
    /**
     * 
     * cancel:(取消店铺收藏). <br/> 
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
    public  EcpBaseResponseVO cancel(Model model,@RequestParam("shopId") String shopId)throws BusinessException{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        
        /*2、调用业务查询接口，取消关注店铺*/
        ShopCollectReqDTO req = new ShopCollectReqDTO();
        req.setShopId(shopId);//店铺id
        req.setStaffId(req.getStaff().getId());//当前用户
        //调用业务逻辑，取消关注店铺
        shopCollectRSV.deleteShopBySel(req);
        res.setResultFlag("ok");
        return res;
    }
    
    /**
     * 
     * collect:(收藏我的店铺). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @param shopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @RequestMapping(value="/collect")
    @ResponseBody
    public  EcpBaseResponseVO collect(Model model,@RequestParam("shopId") String shopId)throws BusinessException{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        
        /*2、调用业务查询接口，收藏店铺*/
        ShopCollectReqDTO req = new ShopCollectReqDTO();
        req.setShopId(shopId);//店铺id
        req.setStaffId(req.getStaff().getId());//当前用户
        //调用业务逻辑，收藏店铺
        shopCollectRSV.insertShopCollect(req);
        res.setResultFlag("ok");
        return res;
    }
}


package com.zengshi.ecp.busi.seller.coup.shop.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.seller.prom.createprom.vo.QueryShopVO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-coup <br>
 * Description: <br>
 * Date:2016-4-28下午5:41:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/seller/promshop")
public class PromShopController extends EcpBaseController {

    @Resource
    private IPromAuthRSV promAuthRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;
    /**
     * 平台促销类型授权   店铺查询功能
     */
    private static String MODULE = PromShopController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author lisp
     * @return
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model,Long shopId) {
    	 model.addAttribute("shopId", shopId);
        HashMap<String,Object> shop = (HashMap<String, Object>)CacheUtil.getItem("STAFF_SHOP_CACHE_DATA");
        
        return "/seller/coupon/shop/query-shop-grid";
    }

    /**
     * 授权列表查询 初始化列表和查询按钮功能调用
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/shoplist")
    public String shopList(Model model, QueryShopVO queryShopVO) throws Exception {

        // 组织参数
        ShopSelectReqDTO  conds=new ShopSelectReqDTO();
        conds=queryShopVO.toBaseInfo(ShopSelectReqDTO.class);
        ObjectCopyUtil.copyObjValue(queryShopVO, conds, "", false);
      
        if(!StringUtil.isEmpty(queryShopVO.getStatus())){
             //待实现
        }
        SellerResDTO sellerResDTO = SellerLocaleUtil.getSeller();
        //店铺管理人员查询 companyid
        conds.setCompanyId(sellerResDTO.getCompanyId());
        //店铺普通人员 使用shopId查询
        if(StaffConstants.custInfo.CUST_TYPE_C.equals(sellerResDTO.getCustType())){
        	if(!CollectionUtils.isEmpty(sellerResDTO.getShoplist())){
        		 conds.setShopId(sellerResDTO.getShoplist().get(0).getId());
        	}
        }
        PageResponseDTO<ShopInfoResDTO> page=shopInfoRSV.listShopInfoByCond(conds);

        model.addAttribute("page",page );
        return "/seller/coupon/shop/query-shop-list";
    }

}

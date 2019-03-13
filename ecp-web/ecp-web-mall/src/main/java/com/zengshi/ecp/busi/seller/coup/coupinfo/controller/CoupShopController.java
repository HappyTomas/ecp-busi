package com.zengshi.ecp.busi.seller.coup.coupinfo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.seller.coup.shop.vo.ShopVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupShopLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupShopLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-26下午5:13:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/seller/coupshop")
public class CoupShopController extends EcpBaseController {
    
    private static String MODULE = CoupShopController.class.getName();
    
    @Resource
    private ICoupLimitRSV coupLimitRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;
     
    /**
     *  已选择店铺列表
     * @param model
     * @param coupId
     * @param shopIds
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/shopList")
    public String shopList(Model model, @RequestParam("coupId") String coupId ,@RequestParam("shopIds") String[] shopIds) throws Exception {
        
        List<ShopVO> shopList=new ArrayList<ShopVO>();
        model.addAttribute("shopList", shopList);
        
        //为空 表示新增
        if(!StringUtil.isEmpty(coupId) && shopIds.length==0){
          
            CoupShopLimitReqDTO reqdto=new CoupShopLimitReqDTO();
            reqdto.setCoupId(new Long(coupId));
            reqdto.setStatus(CouponConstants.CoupSys.status_1);
            List<CoupShopLimitRespDTO> list= coupLimitRSV.queryCoupShop(reqdto);
            
            if(!CollectionUtils.isEmpty(list)){
           
                ShopInfoResDTO shopDTO=null;
                 for(CoupShopLimitRespDTO dto:list){
                     
                     ShopVO vo=new ShopVO();
                     vo.setShopId(dto.getShopId().toString());
                     shopDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                     if(shopDTO!=null){
                         vo.setShopName(shopDTO.getShopName()); 
                         vo.setStatus(shopDTO.getShopStatus());
                     }
                     shopList.add(vo);
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(shopIds) && shopIds.length>0){
                
                //页面传入参数 组织
                List<String> shops = java.util.Arrays.asList(shopIds);
                //去重 过滤
                Set set = new HashSet(shops);
                shops=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(shops)){
                    //设置列表值
                    for(String shopId:shops){
                        ShopInfoResDTO shopDTO=null;
                        if(!StringUtil.isEmpty(shopId)){
                            ShopVO vo=new ShopVO();
                            vo.setShopId(shopId);
                            shopDTO=shopInfoRSV.findShopInfoByShopID(new Long(shopId));
                            if(shopDTO!=null){
                                vo.setShopName(shopDTO.getShopName()); 
                                vo.setStatus(shopDTO.getShopStatus());
                            }
                            shopList.add(vo);
                        }
                    }
                }
            }
        }
        return "/seller/coupon/userule/shoptable/shop-table";
    } 
}



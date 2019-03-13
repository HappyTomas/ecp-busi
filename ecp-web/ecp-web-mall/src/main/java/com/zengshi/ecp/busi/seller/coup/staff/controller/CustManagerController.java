
package com.zengshi.ecp.busi.seller.coup.staff.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.busi.seller.coup.staff.vo.CustInfoListVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopVipRealResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-5下午5:41:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/seller/cust")
public class CustManagerController extends EcpBaseController {

    private static String MODULE = CustManagerController.class.getName();

    @Resource
    private ICustManageRSV custManageRSV;
    @Resource
    private IShopManageRSV shopManageRSV;

    
    /**
     * 
     * gridList:(会员列表). <br/> 
     * 
     * @author lisp
     * @param model
     * @param vo
     * @param custInfoList
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    
    @RequestMapping("/gridlist")
    public String  gridList(Model model, EcpBasePageReqVO vo, @ModelAttribute CustInfoListVO custInfoList) throws Exception {
        /*
        CustInfoReqDTO info = vo.toBaseInfo(CustInfoReqDTO.class);
        if (StringUtil.isNotBlank(custInfoList.getCompanyId())) {
            info.setCompanyId(Long.parseLong(custInfoList.getCompanyId()));
        }
        if (StringUtil.isNotBlank(custInfoList.getShopId())) {
            info.setShopId(Long.valueOf(custInfoList.getShopId()));
        }
        info.setStaffCode(custInfoList.getStaffCode());
        info.setSerialNumber(custInfoList.getSerialNumber());
        
        */
        ShopVipRealReqDTO reqDTO = vo.toBaseInfo(ShopVipRealReqDTO.class);
        if (StringUtil.isNotBlank(custInfoList.getShopId())) {
            reqDTO.setShopId(Long.valueOf(custInfoList.getShopId()));
        }
        if (StringUtil.isNotBlank(custInfoList.getStaffCode())) {
            reqDTO.setStaffCode(custInfoList.getStaffCode());
        }
        PageResponseDTO<ShopVipRealResDTO> page = shopManageRSV.listShopVipReal(reqDTO);
        //PageResponseDTO<CustInfoResDTO> page = custManageRSV.listCustInfo(info);
        model.addAttribute("page", page);
        
       return "/seller/coupon/send/cust/cust-list";

    }
   
}

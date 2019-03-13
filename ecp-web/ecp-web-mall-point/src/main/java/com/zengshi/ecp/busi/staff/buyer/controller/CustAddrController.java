/** 
 * Project Name:ecp-web-mall 
 * File Name:CustAddrController.java 
 * Package Name:com.zengshi.ecp.busi.staff.seller.controller 
 * Date:2015年9月18日下午7:10:09 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.buyer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.buyer.vo.CustAddrVO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fasterxml.jackson.databind.Module;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月18日下午7:10:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 * 
 * 用户收货地址Controller管理
 */
@Controller
@RequestMapping(value="/custaddr")
public class CustAddrController  extends EcpBaseController{
    
    private static final String Module = CustAddrController.class.getName();
    @Resource
    ICustAddrRSV custaddrRSV;
    
    @RequestMapping(value="/index")
    public String custaddr(Model model)
    {
        //获取用户收货地址信息
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(dto.getStaff().getId());
        
        List<CustAddrResDTO> listaddr = custaddrRSV.listCustAddr(dto);
        int count = 0;
        if(!CollectionUtils.isEmpty(listaddr))
        {
            count = listaddr.size();
        }
        model.addAttribute("listaddr", listaddr);
        model.addAttribute("count", count);

        
        return "/staff/buyer/address/buyer-address";
    }
    @RequestMapping(value="/newaddr")
    public String newaddr(Model model)
    {   
        return "/staff/buyer/address/buyer-addressnew";
    }
    @RequestMapping(value="/saveaddr")
    @ResponseBody
    public EcpBaseResponseVO saveaddr(Model model, @ModelAttribute CustAddrVO custaddr)
    {   
        LogUtil.info(Module, "============== 保存店铺收货地址    开始  =============");
        
        CustAddrReqDTO cusraddrDTO = new CustAddrReqDTO();
        ObjectCopyUtil.copyObjValue(custaddr, cusraddrDTO, null, false);
        
//        System.out.println("====== custaddr:"+custaddr.toString());
//        cusraddrDTO.setStaffId(cusraddrDTO.getStaff().getId());
        cusraddrDTO.setStaffId(cusraddrDTO.getStaff().getId());

        if(cusraddrDTO.getId() == null)
        {
            custaddrRSV.saveCustAddr(cusraddrDTO);
        }
        else {
            custaddrRSV.updateCustAddr(cusraddrDTO);
        }
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        
        LogUtil.info(Module, "============== 保存店铺收货地址    结束  ============="); 
        return resultVo;
    }
    @RequestMapping(value="/setaddr")
    @ResponseBody
    public EcpBaseResponseVO setaddr(Model model, @RequestParam String addrid, @RequestParam String staffid)
    {                   
        LogUtil.info(Module, "============== 设置地址为默认收货地址    开始  =============");
//        System.out.println("====== addrid："+addrid+" ;staffid:"+staffid);
        
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(Long.valueOf(staffid));
        dto.setId(Long.valueOf(addrid));
        
        custaddrRSV.installDefauleAddr(dto);
        
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
              
        LogUtil.info(Module, "============== 设置地址为默认收货地址    结束  =============");

        return resultVo;
    }
    @RequestMapping(value="/editaddr")
    public String editaddr(Model model, @RequestParam(value="addrid") String addrid, @RequestParam(value="staffid") String staffid)
    {            
        LogUtil.info(Module, "============== 编辑店铺收货地址    开始  =============");

//        System.out.println("====== addrid："+addrid+" ;staffid:"+staffid);
        //1.填补用户收货地址信息     
        //根据店铺id，查找地址信息
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(Long.valueOf(staffid));
        dto.setId(Long.valueOf(addrid));
        
        CustAddrResDTO custaddr = custaddrRSV.findAddr(dto);
               
        model.addAttribute("custaddr", custaddr);
      
        LogUtil.info(Module, "============== 编辑店铺收货地址    结束  =============");

        return "/staff/buyer/address/buyer-addressnew";
    }
    @RequestMapping(value="/deladdr")
    @ResponseBody
    public EcpBaseResponseVO deladdr(Model model, @RequestParam String addrid, @RequestParam String staffid)
    {                   
        LogUtil.info(Module, "============== 删除收货地址    开始  =============");
//        System.out.println("====== addrid："+addrid+" ;staffid:"+staffid);
        
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(Long.valueOf(staffid));
        dto.setId(Long.valueOf(addrid));
        
        custaddrRSV.deleteCustAddr(dto);
        
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
              
        LogUtil.info(Module, "============== 删除收货地址    结束  =============");

        return resultVo;
    }
}


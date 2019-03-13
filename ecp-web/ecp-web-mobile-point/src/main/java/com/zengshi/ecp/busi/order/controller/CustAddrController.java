package com.zengshi.ecp.busi.order.controller;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.vo.CustAddrVO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAddrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustAddrRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
public class CustAddrController extends EcpBaseController{
    
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
        
        return "/buyer/address/buyer-address";
    }
    /**
     * 
     * saveaddr:(收货地址新增和编辑公用一个接口). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param custAddrVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/saveaddr")
    @ResponseBody
    public EcpBaseResponseVO saveaddr(Model model,CustAddrVO custAddrVO)
    {   
        CustAddrReqDTO cusraddrDTO = new CustAddrReqDTO();
        ObjectCopyUtil.copyObjValue(custAddrVO, cusraddrDTO, "", false);
        cusraddrDTO.setStaffId(cusraddrDTO.getStaff().getId());
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        try {
            if(cusraddrDTO.getId() == null){
                custaddrRSV.saveCustAddr(cusraddrDTO);
            }else {
                custaddrRSV.updateCustAddr(cusraddrDTO);
            }
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(Module, "新增或编辑地址失败！", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        } catch (Exception e) {
            LogUtil.error(Module, "新增或编辑地址失败！", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
       
        return resultVo;
    }
    /**
     * 
     * setaddr:(设置为默认地址). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param custAddrVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/setaddr")
    @ResponseBody
    public EcpBaseResponseVO setaddr(Model model,CustAddrVO custAddrVO)
    {                   
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(custAddrVO.getStaffId());
        dto.setId(custAddrVO.getId());
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        try {
            custaddrRSV.installDefauleAddr(dto);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
        } catch (BusinessException e) {
            LogUtil.error(Module, "设置为默认地址报错！", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);  
        } catch (Exception e) {
            LogUtil.error(Module, "设置为默认地址报错！", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);  
        }
        return resultVo;
    }
    /**
     * 
     * editaddr:(收货地址编辑). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param addrid
     * @param staffid
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/editaddr")
    public String editaddr(Model model, CustAddrVO custAddrVO)
    {            
        //根据用户staffId 和用户地址记录id 获取编辑的收货地址信息
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setId(custAddrVO.getId());
        dto.setStaffId(custAddrVO.getStaffId());
        CustAddrResDTO custaddr = null;
        try {
            custaddr = custaddrRSV.findAddr(dto);
        } catch (BusinessException e) {
            LogUtil.error(Module, "编辑获取收货地址失败！", e);
        } catch (Exception e) {
            LogUtil.error(Module, "编辑获取收货地址失败！", e);
        }
        model.addAttribute("custaddr", custaddr);
        return "/buyer/address/buyer-addressedit";
    }
    
    /**
     * 
     * deladdr:(删除收货地址). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param addrid
     * @param staffid
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/deladdr")
    @ResponseBody
    public EcpBaseResponseVO deladdr(Model model, CustAddrVO custAddrVO)
    {                   
        CustAddrReqDTO dto = new CustAddrReqDTO();
        dto.setStaffId(custAddrVO.getStaffId());
        dto.setId(custAddrVO.getId());
        EcpBaseResponseVO resultVo = new EcpBaseResponseVO();
        try {
            custaddrRSV.deleteCustAddr(dto);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
        } catch (BusinessException e) {
            LogUtil.error(Module, "删除获取收货地址失败！", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        } catch (Exception e) {
            LogUtil.error(Module, "删除获取收货地址失败！", e);
            resultVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        return resultVo;
    }
    
    /**
     * 
     * buyerAddrNew:(新增收货地址跳转页面). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/buyeraddrnew")
    public String buyerAddrNew(){
        return "/buyer/address/buyer-addressnew";
    }
}


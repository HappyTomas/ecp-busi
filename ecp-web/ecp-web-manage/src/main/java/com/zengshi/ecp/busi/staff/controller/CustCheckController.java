/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.CustCheckVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustCheckRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/custcheck")
public class CustCheckController extends EcpBaseController {
    
    private static String MODULE = CustCheckController.class.getName();
    
    
    @Resource
    private ICustCheckRSV custCheckRSV;
    
    
    /**
     * 
     * grid:(待审核页). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */

    @RequestMapping(value="/grid")
    public String grid(){
    	return "/staff/custcheck/custcheck-grid";
    }
    

    @RequestMapping(value="/checkremark")
    public String checkremark(){
        
        return "/staff/custcheck/remark/check-remark";
    }
    
    
    @RequestMapping(value="/check")
    @ResponseBody
    public EcpBaseResponseVO check(@RequestParam("id")String id,@RequestParam("staffId")String staffId) throws Exception{
        LogUtil.info(this.getClass().getName(), "========check");
        CustAuthstrReqDTO info = new CustAuthstrReqDTO();
        info.setId(Long.parseLong(id));
        info.setStaffId(Long.parseLong(staffId));
        custCheckRSV.checkCustToCompanyCust(info);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    @RequestMapping(value="/oncheck")
    @ResponseBody
    public EcpBaseResponseVO nocheck(@RequestParam("id")String id,@RequestParam("staffId")String staffId,@RequestParam("remark")String remark) throws Exception{
        LogUtil.info(this.getClass().getName(), "========nocheck");
        CustAuthstrReqDTO info = new CustAuthstrReqDTO();
        info.setId(Long.parseLong(id));
        info.setStaffId(Long.parseLong(staffId));
        if(StringUtil.isBlank(remark)){
            throw new BusinessException("不通过审核理由不能为空");
        }
        info.setCheckRemark(remark);
       
        custCheckRSV.checkNoCustToCompanyCust(info);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    @RequestMapping(value="/remove")
    @ResponseBody
    public EcpBaseResponseVO remove(@RequestParam("id")String id)throws Exception{
        
        CustAuthstrReqDTO info = new CustAuthstrReqDTO();
        info.setId(Long.parseLong(id));
        custCheckRSV.removeCustAuthstr(info);
        LogUtil.info(this.getClass().getName(), "========remove over");
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo,@Valid @ModelAttribute("custCheck") CustCheckVO custCheck) throws Exception{
        
        CustAuthstrReqDTO info = vo.toBaseInfo(CustAuthstrReqDTO.class);
        info.setStaffCode(custCheck.getStaffCode());
        info.setSerialNumber(custCheck.getSerialNumber());
        info.setCheckStatus(custCheck.getCheckStatus());
        PageResponseDTO<CustAuthstrResDTO> t = custCheckRSV.queryCustCheckList(info); 
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    

}



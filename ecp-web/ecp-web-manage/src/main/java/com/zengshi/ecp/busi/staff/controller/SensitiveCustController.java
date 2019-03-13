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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.CustInfoListVO;
import com.zengshi.ecp.busi.staff.vo.CustInfoVO;
import com.zengshi.ecp.busi.staff.vo.CustSensitiveLogVO;
import com.zengshi.ecp.busi.staff.vo.CustThirdCodeVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustThirdCodeRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/sensitive")
public class SensitiveCustController extends EcpBaseController {

    private static String MODULE = SensitiveCustController.class.getName();

    @Resource
    private ICustManageRSV custManageRSV;
    
    @Resource
    private ICustThirdCodeRSV custThirdCodeRSV;
    

    @RequestMapping(value = "/grid")
    public String grid(Model model) {
        return "/staff/custservice/sensitive/sensitive-grid";
    }
    
    
    @RequestMapping(value = "/change")
    public String edit(Model model,@RequestParam(value="staffId",required=false) String staffId) {
        model.addAttribute("staffId", staffId);
        return "/staff/custservice/sensitive/open/add";
    }
    
    @RequestMapping(value="/saveSensitive")
    @ResponseBody
    public EcpBaseResponseVO saveSensitive(CustSensitiveLogVO custSensitiveLogVO)throws BusinessException{
    	EcpBaseResponseVO baseResponseVO = new EcpBaseResponseVO();
    
    		CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        	custInfoReqDTO.setId(custSensitiveLogVO.getStaffId());
        	custInfoReqDTO.setActionType("02");
        	custInfoReqDTO.setSensitiveDesc(custSensitiveLogVO.getSensitiveDesc());
        	custInfoReqDTO.setSensitiveType(custSensitiveLogVO.getSensitiveType());
        	custManageRSV.updateScust(custInfoReqDTO);
		
    	
    	baseResponseVO.setResultFlag(baseResponseVO.RESULT_FLAG_SUCCESS);
    	return baseResponseVO;
    }
    
    @RequestMapping(value="/delSensitive")
    @ResponseBody
    public EcpBaseResponseVO delSensitive(CustSensitiveLogVO custSensitiveLogVO)throws BusinessException{
    	EcpBaseResponseVO baseResponseVO = new EcpBaseResponseVO();
    	String[] staffIds = custSensitiveLogVO.getListStaff().split(",");
    	for (String string : staffIds) {
    		CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        	custInfoReqDTO.setId(Long.parseLong(string));
        	custInfoReqDTO.setActionType("03");
        	custManageRSV.delScust(custInfoReqDTO);
		}
    	
    	baseResponseVO.setResultFlag(baseResponseVO.RESULT_FLAG_SUCCESS);
    	return baseResponseVO;
    }
    
    
    /**
     * 
     * gridList:(会员列表). <br/> 
     * 
     * @author wangbh
     * @param model
     * @param vo
     * @param custInfoList
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo, @ModelAttribute CustInfoListVO custInfoList) throws Exception {
        CustInfoReqDTO info = vo.toBaseInfo(CustInfoReqDTO.class);
        if (StringUtil.isBlank(custInfoList.getCompanyId())) {
            info.setCompanyId(0L);
        } else {
            info.setCompanyId(Long.parseLong(custInfoList.getCompanyId()));
        }
        info.setStaffCode(custInfoList.getStaffCode());
        info.setSerialNumber(custInfoList.getSerialNumber());
        info.setCustLevelCode(custInfoList.getCustLevelCode());
        if(StringUtil.isBlank(custInfoList.getSensitiveType())){
        	info.setSensitiveType("0");
        }else{
        info.setSensitiveType(custInfoList.getSensitiveType());
        }
        PageResponseDTO<CustInfoResDTO> t = custManageRSV.listCustInfo(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        
       return super.addPageToModel(model, respVO);

    }
 

 
    
    
}

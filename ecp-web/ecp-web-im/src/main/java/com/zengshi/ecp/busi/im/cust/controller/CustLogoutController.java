package com.zengshi.ecp.busi.im.cust.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.cust.vo.StaffHotlineVO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: 商城用户登出<br>
 * Date:2016年10月27日下午5:41:39  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/cust/logout")
public class CustLogoutController extends EcpBaseController {
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	/**
	 * 
	 * updateMsgStatus:(断开与客服的连接). <br/> 
	 * 
	 * @author huangxl5 
	 * @param staffHotlineVO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	@RequestMapping("/disconn")
	@ResponseBody
	public EcpBaseResponseVO updateMsgStatus(StaffHotlineVO staffHotlineVO)throws BusinessException{
		
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		ImStaffHotlineReqDTO req = new ImStaffHotlineReqDTO();
		ObjectCopyUtil.copyObjValue(staffHotlineVO, req, null, false);
		//req.setStaffId(req.getStaff().getId());
		req.setOfStaffCode(staffHotlineVO.getOfStaffCode());
		//调用后场服务，结果客户端的会话
		custServiceMgrRSV.finishChat(req);
		vo.setResultFlag("ok");
		return vo;
	}
	
}

package com.zengshi.ecp.busi.im.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.cust.vo.CustInfoReqVO;
import com.zengshi.ecp.im.dubbo.dto.CustInfoImResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IOfuserRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdSpecialCountResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: 会话过程服务 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2016年9月9日下午4:46:05  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/chat")
public class ChatController extends EcpBaseController {
	private static String MODULE = ChatController.class.getName();
	
	@Resource
	private ICustManageRSV custManageRSV;
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	/**
	 * openfire用户服务
	 */
	@Resource
	private IOfuserRSV iOfuserRSV;
	
	/**
	 * 订单用户服务
	 */
	@Resource
	private IOrdMainRSV iOrdMainRSV;
	
	/**
	 * id:(获取id). <br/> 
	 * 
	 * @author linby
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/id")
	@ResponseBody
	public String id() throws Exception{
		//TODO 
		return RandomStringUtils.randomNumeric(10);
	}
	
	@RequestMapping(value = "/getcustinfo")
	@ResponseBody
	public CustInfoResDTO  getCustInfo(CustInfoReqVO custInfoReqVO) throws Exception{
		CustInfoReqDTO arg0 = new CustInfoReqDTO();
		arg0.setStaffCode(custInfoReqVO.getStaffCode());
		CustInfoResDTO custInfoResDTO = custManageRSV.findCustInfo(arg0);
		if(null!=custInfoResDTO){
			if(StringUtil.isNotBlank(custInfoResDTO.getCustPic())){
				String imageUrl = ImageUtil.getImageUrl(custInfoResDTO.getCustPic()+"_50x50!");
				custInfoResDTO.setCustPic(imageUrl);
			}else{
				BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_USER_PIC");
				String imageUrl = ImageUtil.getImageUrl(baseSysCfgRespDTO.getParaValue()+"_50x50!");
				custInfoResDTO.setCustPic(imageUrl);
			}
			
		}
		return custInfoResDTO;
	}
	
	/**
	 * 
	 * getCustInfoByOpenfireUser:(通过openfire用户查询用户信息). <br/> 
	 * 
	 * @author linby
	 * @param custInfoReqVO
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/getcustinfo/of")
	@ResponseBody
	public CustInfoImResDTO  getCustInfoByOpenfireUser(CustInfoReqVO custInfoReqVO) throws Exception{
		CustInfoReqDTO arg0 = new CustInfoReqDTO();
		arg0.setStaffCode(custInfoReqVO.getStaffCode());
		CustInfoImResDTO custInfoImResDTO = iOfuserRSV.findCustByOfuser(custInfoReqVO.getOfStaffCode());
		
		return custInfoImResDTO;
	}
	
	
	/**
	 * 
	 * loadSensInfo:(加载用户的敏感信息). <br/> 
	 * 
	 * @author zhuqr
	 * @param custInfoReqVO
	 * @return
	 * @throws  
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/loadSensInfo")
	@ResponseBody
	public Map<String,Object> loadSensInfo(CustInfoReqVO custInfoReqVO) throws Exception{
		Map<String,Object> map = new HashMap<>();
		
		CustInfoReqDTO custInfo = new CustInfoReqDTO();
		custInfo.setStaffCode(custInfoReqVO.getStaffCode());
		CustInfoResDTO custInfoResDTO = custManageRSV.findCustInfo(custInfo);

		RQueryOrderRequest ordReq= new RQueryOrderRequest();
		ordReq.setStaffId(custInfoResDTO.getId());
		ROrdSpecialCountResponse sensInfo = iOrdMainRSV.findOrdSpecialCount(ordReq);

		
		BaseSysCfgRespDTO ordCancelsysCfg = SysCfgUtil.fetchSysCfg("ORDER_CANCEL_NUMBER");
		BaseSysCfgRespDTO ordRefundsysCfg = SysCfgUtil.fetchSysCfg("ORDER_REFUND_NUMBER");
		map.put("sensitiveType", custInfoResDTO.getSensitiveType());
		BaseParamDTO sensParam = BaseParamUtil.fetchParamDTO("CUST_SENSITIVE_TYPE",custInfoResDTO.getSensitiveType());
		if(sensParam !=null){
			map.put("sensitiveTypeTxt",sensParam.getSpValue());
		}
		map.put("sensitiveDesc", custInfoResDTO.getSensitiveDesc());
		map.put("monthLimit",sensInfo.getMonthLimit());
		map.put("backOrdCount",sensInfo.getBackOrdCount());
		map.put("cancleOrdCount", sensInfo.getCancleOrdCount());
		map.put("ordCancelNumber", Integer.parseInt(ordCancelsysCfg.getParaValue()));
		map.put("ordRefundNumber", Integer.parseInt(ordRefundsysCfg.getParaValue()));

		return map;
	}
	
	
	/**
	 * 
	 * setSensUser:(设置为敏感用户). <br/> 
	 * 
	 * @author zhuqr
	 * @param 
	 * @return
	 * @throws Exception 
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/setSensUser")
	@ResponseBody
	public EcpBaseResponseVO setSensUser(CustInfoReqDTO custInfoReqVO) throws Exception{
		EcpBaseResponseVO resp = new EcpBaseResponseVO();  
		CustInfoReqDTO custInfo = new CustInfoReqDTO();
		custInfo.setStaffCode(custInfoReqVO.getStaffCode());
		  try { 
			    CustInfoResDTO custInfoResDTO = custManageRSV.findCustInfo(custInfo);
				ObjectCopyUtil.copyObjValue(custInfoResDTO, custInfo, "", false);
				resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
				resp.setResultMsg(custInfoReqVO.getSensitiveDesc());
				custInfo.setActionType("02");
				custInfo.setSensitiveType(custInfoReqVO.getSensitiveType());;
				custInfo.setSensitiveDesc(custInfoReqVO.getSensitiveDesc());
				custManageRSV.updateScust(custInfo);
    
		  }catch(Exception e){	
			
          resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
          resp.setResultMsg(e.getMessage());
         }
		  
		return resp;
	}
	
	
	@RequestMapping(value = "/getWaitCount")
	@ResponseBody
	public Integer getWaitCount(Long shopId){
		ImStaffHotlineReqDTO imStaffHotlineReq=new ImStaffHotlineReqDTO();
		imStaffHotlineReq.setShopId(shopId);
		ImStaffHotlineResDTO imStaffHotlineRes=custServiceMgrRSV.getWaitCount(imStaffHotlineReq);
		return imStaffHotlineRes.getWaitCount();
	}
}

package com.zengshi.ecp.app.action;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM001Req;
import com.zengshi.ecp.app.resp.IM001Resp;
import com.zengshi.ecp.busi.im.history.vo.SessionReqVO;
import com.zengshi.ecp.busi.im.history.vo.SessionRespVO;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IOfuserRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.util.ConstantTool;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

/**
 * 
 * Title: 会话接入 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im001")
@Action(bizcode="im001", userCheck=true)
@Scope("prototype")
public class IM001Action extends AppBaseAction<IM001Req, IM001Resp> {
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	@Resource
	private IOfuserRSV ofuserRSV;
	
	@Resource
	private IStaffHotlineRSV staffHotlineRSV;
	
	@Resource
    private ICustInfoRSV custInfoRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest();//校验入参
		
		Long shopId = this.request.getShopId();//店铺id
		Long businessType = this.request.getBusinessType();//业务类型：商品咨询/2，订单咨询/1
		String businessId = this.request.getBusinessId(); //商品id或订单id
		
		BaseStaff staff = StaffLocaleUtil.getStaff();
		String staffCode = staff.getStaffCode();
		String custLevel = staff.getStaffLevelCode();
		
		//获取用户OF信息
		ImOfuserRelReqDTO imOfuserRelReq = new ImOfuserRelReqDTO();
		imOfuserRelReq.setStaffCode(staffCode);
		ImOfuserRelResDTO imOfuserRelResDTO = ofuserRSV.findofuserByCust(imOfuserRelReq);
		
		//获取用户头像
		CustInfoReqDTO custInfo = new CustInfoReqDTO();
		custInfo.setId(staff.getId());
		CustInfoResDTO custInfoRes = custInfoRSV.getCustInfoById(custInfo);
		if(custInfoRes!=null){
			String custPic = StringUtil.isBlank(custInfoRes.getCustPic())
					?"":ImageUtil.getImageUrl(custInfoRes.getCustPic());
			this.response.setCustPic(custPic);
		}
		
		//获取客服
		ImStaffHotlineResDTO imStaffHotlineResp = new ImStaffHotlineResDTO();
		ImCustReqDTO imCustReq = new ImCustReqDTO();
		imCustReq.setOfStaffCode(imOfuserRelResDTO.getOfStaffCode());
		imCustReq.setBusinessType(businessType);
		imCustReq.setBusinessId(businessId);
		imCustReq.setShopId(shopId);
		imCustReq.setCustLevel(custLevel);
		imStaffHotlineResp = custServiceMgrRSV.getStaffHotline(imCustReq);
		String hotlinePerson = "";
		String hotlinePhoto = "";
		SessionReqVO reqVO = null;//获取会话id
		if(imStaffHotlineResp.getWaitCount()==0){
			//客服名称
			ImStaffHotlineReqDTO imStaffHotlineReq = new ImStaffHotlineReqDTO();
			imStaffHotlineReq.setOfStaffCode(imStaffHotlineResp.getCsaCode());
			ImStaffHotlineResDTO hotlineResDTO = staffHotlineRSV.getStaffHotline(imStaffHotlineReq);
			hotlinePerson = hotlineResDTO.getHotlinePerson();
			
			//客服头像
			BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_CSA_PIC");
			hotlinePhoto = baseSysCfgRespDTO==null?"":ImageUtil.getImageUrl(baseSysCfgRespDTO.getParaValue());
			
			//获取会话id
			reqVO = new SessionReqVO();
			reqVO.setCsaCode(imStaffHotlineResp.getCsaCode());
			reqVO.setIssueType(businessType.toString());
			reqVO.setStatus(BizUtil.status_1);
			reqVO.setUserCode(imOfuserRelResDTO.getOfStaffCode());
			reqVO.setSessionTime(DateUtil.getSysDate());
			reqVO.setShopId(shopId);
			reqVO.setSource("APP");
			if(businessType.toString().equals(BizUtil.issueType_1)){
				reqVO.setOrdId(businessId);
			}else if(businessType.toString().equals(BizUtil.issueType_2)){
				reqVO.setGdsId(businessId);
			}
			
			//获取前失效已有，与web保持一致
			SessionRespVO respVO = this.getSession(reqVO);
			if(StringUtil.isNotBlank(respVO.getId())){
				this.updateSession(respVO);
			}
		}
		
		//响应
		this.response.setResultFlag("");
		this.response.setResultMsg("");
		this.response.setUserCode(imOfuserRelResDTO.getOfStaffCode()); //用户OF账号
		this.response.setCsaCode(imStaffHotlineResp.getCsaCode());//供于客户端发起会话
		this.response.setHotlinePerson(hotlinePerson);
		this.response.setHotlinePhoto(hotlinePhoto);
		this.response.setSessionId(this.obtainSessionId(reqVO));
		this.response.setWaitCount(imStaffHotlineResp.getWaitCount());
	}
	
	/**
	 * 
	 * checkRequest:(校验入参). <br/> 
	 * 
	 * @author linby
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private void checkRequest() throws BusinessException{
		if(this.request.getShopId()==null){//店铺id
			throw new BusinessException(new DefaultExceptionCode("1001","店铺id不能为空"));
		}
		if(this.request.getBusinessType()==null){//业务类型：商品咨询，订单咨询
			throw new BusinessException(new DefaultExceptionCode("1002","咨询业务类型不能为空"));
		}
		if(StringUtil.isBlank(this.request.getBusinessId())){//商品id或订单id
			throw new BusinessException(new DefaultExceptionCode("1003","咨询业务对象id不能为空"));
		}
	}
	
	/**
	 * 
	 * obtainSessionId:(获取sessionId). <br/> 
	 * 
	 * @author linby
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private String obtainSessionId(SessionReqVO reqVO) throws BusinessException{
		if(reqVO == null) return null;//会话不存在，返回空
		
		String uuid = UUID.randomUUID().toString();
		reqVO.setId(uuid);
		JSONObject doc = new JSONObject();
		doc = (JSONObject) JSON.toJSON(reqVO);
		MongoUtil.insert("T_IM_SESSION_HISTORY", doc);
		return uuid;
	}

	/*
	 * 获取session
	 */
	private SessionRespVO getSession(SessionReqVO reqVO){
		SessionRespVO respVO = new SessionRespVO();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder doc = new QueryBuilder();  
		
		doc.and("csaCode").is(reqVO.getCsaCode());
		doc.and("userCode").is(reqVO.getUserCode());
		doc.and("status").is(reqVO.getStatus());
		doc.and("source").is(reqVO.getSource());
		
		DBCursor cursor= collection.find(doc.get());
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, respVO);
			}
		} finally {
			cursor.close();
		}
		return respVO;
	}
	/*
	 * 更新session
	 */
	private int updateSession(SessionRespVO reqVO) throws BusinessException{
		try {
			BasicDBObject query = new BasicDBObject();
			query.put("id", reqVO.getId());
			DBObject stuFound = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").findOne(query);
			stuFound.put("status", ImConstants.STATE_0);
			WriteResult result = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").update(query, stuFound);
			return result.getN();
		} catch (Exception e) {
			throw new BusinessException(new DefaultExceptionCode("2001","会话结束失败"));
		}
	}
}

package com.zengshi.ecp.busi.im.history.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * Title: 历史会话记录<br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2016年9月9日下午4:46:05  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.history.vo.MessageHistoryReqVO;
import com.zengshi.ecp.busi.im.history.vo.MessageHistoryRespVO;
import com.zengshi.ecp.busi.im.history.vo.SessionReqVO;
import com.zengshi.ecp.busi.im.history.vo.SessionRespVO;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.busi.im.util.EmoticonUtil;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.util.ConstantTool;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;
@Controller
@RequestMapping(value = "/history")
public class HistoryController  extends EcpBaseController {
	private static String MODULE = HistoryController.class.getName();
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	/**
	 * 保存会话
	 * @param reqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("saveSession")
	@ResponseBody
	public String saveSession(SessionReqVO reqVO) throws BusinessException{
		String uuid = UUID.randomUUID().toString();
		reqVO.setId(uuid);
		reqVO.setSessionTime(DateUtil.getSysDate());
		JSONObject doc = new JSONObject();
		doc = (JSONObject) JSON.toJSON(reqVO);
		MongoUtil.insert("T_IM_SESSION_HISTORY", doc);
		return uuid;
	}
	
	
	
	/**
	 * 修改会话
	 * @param reqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateSession")
	@ResponseBody
	public EcpBaseResponseVO updateSession(SessionReqVO reqVO)throws BusinessException{
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		try {
			BasicDBObject query = new BasicDBObject();
			query.put("id", reqVO.getId());
			DBObject stuFound = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").findOne(query);
			stuFound.put("status", ImConstants.STATE_0);
			WriteResult result = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").update(query, stuFound);
			if(result.getN()>0){
				ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
				dto.setOfStaffCode(reqVO.getUserCode());
				dto.setCsaCode(reqVO.getCsaCode());
				dto.setReqTime(DateUtil.getSysDate().getTime());
				custServiceMgrRSV.staffFinishChat(dto);
			}
			vo.setResultFlag("ok");
		} catch (Exception e) {
			throw new BusinessException("会话结束失败");
		}
		
		return vo;
	}
	
	
	/**
	 * 获取会话次数
	 * @param reqVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getSessionCount")
	@ResponseBody
	public Map<String,Object> getSessionCount(SessionReqVO reqVO)throws Exception{
		Map<String,Object> ap = new HashMap<>();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder queryBuilder = new QueryBuilder();
		
		if(StringUtil.isNotBlank(reqVO.getUserCode())){
			queryBuilder.and("userCode").is(reqVO.getUserCode());
		}
		
		 long count = collection.count(queryBuilder.get());
		 ap.put("count", count);
		 queryBuilder.and("status").is(ImConstants.STATE_0);
		 DBCursor cursor = collection.find(queryBuilder.get()).sort(new BasicDBObject("sessionTime",-1));
			try {
				while (cursor.hasNext()) {
					
					DBObject obj = cursor.next();
					ap.put("csaCode", obj.get("csaCode")+BizUtil.getOfServer());
					ap.put("sessionTime", obj.get("sessionTime"));
					return ap;
				}
			} finally {
				cursor.close();
			}
			return ap;
	}
	
	
	/**
	 * 获取会话列表
	 * @param reqVO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getSessionByone")
	@ResponseBody
	public SessionRespVO getSessionByone(SessionReqVO reqVO)throws Exception{
	
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder queryBuilder = new QueryBuilder();
		
		if(StringUtil.isNotBlank(reqVO.getId())){
			queryBuilder.and("id").is(reqVO.getId());
		}
		
		//queryBuilder.and("status").is(ImConstants.STATE_1);
		SessionRespVO respVO=null;
		DBCursor cursor = collection.find(queryBuilder.get());
		try {
			while (cursor.hasNext()) {
				respVO = new SessionRespVO();
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, respVO);
				respVO.setUserServer(respVO.getUserCode()+BizUtil.getOfServer());
				return respVO;
			}
		} finally {
			cursor.close();
		}
			return respVO;	
	}
	
	/**
	 * 获取会话列表
	 * @param reqVO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getSessionList")
	@ResponseBody
	public List<SessionRespVO> getSessionList(SessionReqVO reqVO)throws Exception{
		List<SessionRespVO> list = new ArrayList<>();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder queryBuilder = new QueryBuilder();
		
		if(StringUtil.isNotBlank(reqVO.getCsaCode())){
			queryBuilder.and("csaCode").is(reqVO.getCsaCode());
		}
		
		queryBuilder.and("status").is(BizUtil.status_1);
		
		DBCursor cursor = collection.find(queryBuilder.get());
		try {
			while (cursor.hasNext()) {
				SessionRespVO respVO = new SessionRespVO();
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, respVO);
				respVO.setUserServer(respVO.getUserCode()+BizUtil.getOfServer());
				list.add(respVO);
			}
		} finally {
			cursor.close();
		}
			return list;	
	}
	
	
	/**
	 * 获取最近一次会话
	 * @param reqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getSession")
	@ResponseBody
	public SessionRespVO getSession(SessionReqVO reqVO)throws BusinessException{
		
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		BasicDBObject query = new BasicDBObject();
		query.put("userCode", reqVO.getUserCode());
		query.put("issueType", reqVO.getIssueType());
		if(reqVO.getIssueType().equals(ImConstants.issueType_1)){
			query.put("ordId", reqVO.getOrdId());
		}else if(reqVO.getIssueType().equals(ImConstants.issueType_2)){
			query.put("gdsId", reqVO.getGdsId());
		}
		DBCursor cursor =collection.find(query).sort(new BasicDBObject("sessionTime",-1));
		try {
			SessionRespVO resDTO = new SessionRespVO();
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, resDTO);
				MessageHistoryReqVO historyReqVO = new MessageHistoryReqVO();
				historyReqVO.setSessionId(resDTO.getId());
				resDTO.setList(getMessageHistory(historyReqVO));
				return resDTO;
			}
		} finally {
			cursor.close();
		}
		return null;
		
		
	}
	
	
	/**
	 * 保存历史消息
	 * @param historyReqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("saveMsgHistory")
	@ResponseBody
	public MessageHistoryRespVO saveMsgHistory(MessageHistoryReqVO historyReqVO)throws BusinessException{
		//过滤下http超链接
		//当发送消息为订单、商品消息时，不对内容作处理
		if(StringUtil.isNotBlank(historyReqVO.getBody())
				&&!historyReqVO.getBody().contains("gdsImage")
				&&!historyReqVO.getBody().contains("ordImage")){
			String body = http(historyReqVO.getBody());
			historyReqVO.setBody(body);
		}
		if(StringUtil.isBlank(historyReqVO.getSessionId())){
			SessionReqVO reqVO = new SessionReqVO();
			ObjectCopyUtil.copyObjValue(historyReqVO, reqVO, null, false);
			String uuid = saveSession(reqVO);
			historyReqVO.setSessionId(uuid);
		}
		MessageHistoryRespVO messageHistoryRespVO =new MessageHistoryRespVO();
		String uuid = UUID.randomUUID().toString();
		historyReqVO.setId(uuid);
		historyReqVO.setStatus(BizUtil.msg_status_10);
		historyReqVO.setBeginDate(DateUtil.getSysDate());
		JSONObject doc = new JSONObject();
		doc = (JSONObject) JSON.toJSON(historyReqVO);
		MongoUtil.insert("T_IM_MESSAGE_HISTORY", doc);
		ObjectCopyUtil.copyObjValue(historyReqVO, messageHistoryRespVO, null, false);
	
		return messageHistoryRespVO;
	}
	
	public String http(String body){
	    if(body.indexOf("</a>")>-1){
	    	body = body.replaceAll("<a href=", "<a target='_blank' href=");
	    	return body;
	    }
	    if(body.indexOf("<img")>-1){
	    	//String regxpForImgTag = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	        String regexp  
	        = "(((http|ftp|https|file)://)|((?<!((http|ftp|https|file)://))www\\.))"  // 以http...或www开头  
	        + ".*?"                                                                   // 中间为任意内容，惰性匹配  
	        + "(?=(&nbsp;|\\s|　|<br />|$|[<>]))";  // 结束条件
	    	Pattern pattern = Pattern.compile(regexp);
	    	Matcher matcher = pattern.matcher(body);
	    	StringBuffer sb = new StringBuffer();
	    	while (matcher.find()) {
	    	    String temp = matcher.group();
	    	    String hz = temp.substring(temp.length()-4, temp.length()-1);
	    	    String hz1 = temp.substring(temp.length()-5, temp.length()-2);
	    	    if(hz.equals("gif")||hz.equals("jpg")||hz.equals("png")||hz.equals("jpeg")||hz.equals("bmp")){

	    	    }else if(hz1.equals("gif")||hz1.equals("jpg")||hz1.equals("png")||hz1.equals("jpeg")||hz1.equals("bmp")){

	    	    }
	    	    else{
	    	    	// body = body.replaceAll(temp,"<a target='_blank' href='"+temp+"'>"+temp+"</a>");
	    	    	matcher.appendReplacement(sb, "<a target='_blank' href='"+temp+"'>"+temp+"</a>");
	    	    }
	    	}
	    	matcher.appendTail(sb);
	    	return sb.toString();
	    }
	    body = body.replaceAll("(?is)(?<!')(http://[/\\.\\w.-]+)","<a target='_blank' href='$1'>$1</a>");
	    return body;
	}

	/**
	 * 通过session获取会话下的历史聊天记录
	 * @param historyReqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getMessageHistory")
	@ResponseBody
	public List<MessageHistoryRespVO> getMessageHistory(MessageHistoryReqVO historyReqVO)throws BusinessException{
		List<MessageHistoryRespVO> list = new ArrayList<>();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY");
		QueryBuilder  query = new QueryBuilder();
		if(StringUtil.isNotBlank(historyReqVO.getSessionId())){
			query.and("sessionId").is(historyReqVO.getSessionId());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getCsaCode())){
			query.and("csaCode").is(historyReqVO.getCsaCode());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getUserCode())){
			query.and("userCode").is(historyReqVO.getUserCode());
		}
		if(historyReqVO.getShopId() != null && historyReqVO.getShopId() != 0L){
			query.and("shopId").is(historyReqVO.getShopId());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getFrom())){
			query.and("from").is(historyReqVO.getFrom());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getMessageType())){
			query.and("messageType").is(historyReqVO.getMessageType());
		}else{
			query.and("messageType").notIn(Arrays.asList("welcome", "inform"));
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getBody())){
			Pattern pattern = Pattern.compile("^.*" + historyReqVO.getBody()+ ".*$", Pattern.CASE_INSENSITIVE); 
			query.and("body").regex(pattern);
		}
		query.and("beginDate").lessThanEquals(DateUtil.getSysDate());
		query.and("beginDate").greaterThan(DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -7));
		String status = historyReqVO.getStatus();
		if(StringUtil.isNotBlank(status)){
			query.and("status").is(status);
		}
		DBCursor cursor =collection.find(query.get()).sort(new BasicDBObject("beginDate",-1)).skip(historyReqVO.getStartRowIndex()).limit(historyReqVO.getEndRowIndex());
		try {
			
			while (cursor.hasNext()) {
				MessageHistoryRespVO resDTO = new MessageHistoryRespVO();
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, resDTO);
				transMessage(resDTO);
				list.add(resDTO);
			}
		} finally {
			cursor.close();
		}
		return list;
	}
	
	/**
	 * 
	 * transMessage:(转化成web可展示的消息). <br/> 
	 * 
	 * @author linby
	 * @param vo
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private static void transMessage(MessageHistoryRespVO vo) throws BusinessException{
		if("APP".equals(vo.getFrom().split("/")[1]) && "msg".equals(vo.getMessageType()) 
				&& StringUtil.isNotBlank(vo.getContentType())){
			
			String msgBody = vo.getBody();
			String contentType = vo.getContentType();
			//消息内容处理
			if("1".equals(contentType)){//处理接收的图片类型消息
				String imageUrl = ImageUtil.getImageUrl(msgBody);//转义图片
				String htmlBody = "<img src=\""+imageUrl+"\" alt=\"\">";
				vo.setBody(htmlBody);
			}else if("0".equals(contentType)){//处理接收的文字类型消息，如果包含表情
				Pattern r = Pattern.compile("\\[\\S+?\\]");
				Matcher m = r.matcher(msgBody);
				while(m.find()){
					String matchWord = m.group(0);
					msgBody = msgBody.replace(matchWord, EmoticonUtil.getHtmlTag(matchWord));
				}
				vo.setBody(msgBody);
			} 
		}
	}
	
	/**
	 * 获取聊天记录总记录数
	 * @param historyReqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getMessageCount")
	@ResponseBody
	public Long getMessageCount(MessageHistoryReqVO historyReqVO)throws BusinessException{
		
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY");
		QueryBuilder  query = new QueryBuilder();
		
		if(StringUtil.isNotBlank(historyReqVO.getCsaCode())){
			query.and("csaCode").is(historyReqVO.getCsaCode());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getUserCode())){
			query.and("userCode").is(historyReqVO.getUserCode());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getFrom())){
			query.and("from").is(historyReqVO.getFrom());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getMessageType())){
			query.and("messageType").is(historyReqVO.getMessageType());
		}
		query.and("status").is("20");
		if(StringUtil.isNotBlank(historyReqVO.getBody())){
			Pattern pattern = Pattern.compile("^.*" + historyReqVO.getBody()+ ".*$", Pattern.CASE_INSENSITIVE); 
			query.and("body").regex(pattern);
		}
		query.and("beginDate").lessThanEquals(DateUtil.getSysDate());
		query.and("beginDate").greaterThan(DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -7));
		//BasicDBObject dbObject = (BasicDBObject) query.get().put("$gte", BasicDBObjectBuilder.start("beginDate",DateUtil.getOffsetDaysDate(DateUtil.getSysDate(), 7)).add("$lt", DateUtil.getSysDate()));
		long count  =collection.count(query.get());
		return count;
	}
	
	
	/**
	 *  查找聊天记录
	 * @param historyReqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("getMessageHistoryDate")
	@ResponseBody
	public List<MessageHistoryRespVO> getMessageHistoryByDate(MessageHistoryReqVO historyReqVO)throws BusinessException{
		List<MessageHistoryRespVO> list = new ArrayList<>();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY");
		QueryBuilder  query = new QueryBuilder();
		
		if(StringUtil.isNotBlank(historyReqVO.getCsaCode())){
			query.and("csaCode").is(historyReqVO.getCsaCode());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getUserCode())){
			query.and("userCode").is(historyReqVO.getUserCode());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getFrom())){
			query.and("from").is(historyReqVO.getFrom());
		}
		
		if(StringUtil.isNotBlank(historyReqVO.getMessageType())){
			query.and("messageType").is(historyReqVO.getMessageType());
		}else{
			query.and("messageType").notIn(Arrays.asList("welcome", "inform"));
		}
	//	query.and("status").is("10");
		if(StringUtil.isNotBlank(historyReqVO.getBody())){
			Pattern pattern = Pattern.compile("^.*" + historyReqVO.getBody()+ ".*$", Pattern.CASE_INSENSITIVE); 
			query.and("body").regex(pattern);
		}
		query.and("beginDate").lessThanEquals(DateUtil.getSysDate());
		query.and("beginDate").greaterThan(DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -7));
		DBCursor cursor =collection.find(query.get()).sort(new BasicDBObject("beginDate",-1)).skip(historyReqVO.getStartRowIndex()).limit(historyReqVO.getEndRowIndex());
		try {
			
			while (cursor.hasNext()) {
				MessageHistoryRespVO resDTO = new MessageHistoryRespVO();
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, resDTO);
				transMessage(resDTO);
				list.add(resDTO);
			}
		} finally {
			cursor.close();
		}
		return list;
	}
	
	
	/**
	 * 消息到达
	 * @param historyReqVO
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("updateMsgStatus")
	@ResponseBody
	public EcpBaseResponseVO updateMsgStatus(MessageHistoryReqVO historyReqVO)throws BusinessException{
		
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		try {
			BasicDBObject query = new BasicDBObject();
			query.put("id", historyReqVO.getId());
			DBObject stuFound = MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY").findOne(query);
			stuFound.put("status", "20");
			stuFound.put("arriveDate", DateUtil.getSysDate());
			WriteResult result = MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY").update(query, stuFound);
			/*if(result.getN()>0){
				//消息未到达时，如果session失效
				BasicDBObject basicDBObject = new BasicDBObject();
				Map map = stuFound.toMap();
				basicDBObject.put("id", map.get("sessionId"));
				DBObject dbObject = MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY").findOne(query);
				dbObject.put("status", ImConstants.STATE_1);
				WriteResult result2 = MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY").update(dbObject, stuFound);
				
			}*/
			vo.setResultFlag("ok");
		} catch (Exception e) {
			LogUtil.debug(MODULE, e.getMessage());
			throw new BusinessException("网络异常",e);
		}
	
		return vo;
	}
	
}

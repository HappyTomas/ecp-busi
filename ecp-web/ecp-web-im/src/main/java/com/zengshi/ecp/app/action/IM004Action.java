package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM004Req;
import com.zengshi.ecp.app.resp.IM004Resp;
import com.zengshi.ecp.busi.im.history.vo.MessageHistoryReqVO;
import com.zengshi.ecp.busi.im.history.vo.MessageHistoryRespVO;
import com.zengshi.ecp.im.util.ConstantTool;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

/**
 * 
 * Title: 消息历史查询 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im004")
@Action(bizcode="im004", userCheck=true)
@Scope("prototype")
public class IM004Action extends AppBaseAction<IM004Req, IM004Resp> {

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest();//校验入参
		
		String from = this.request.getFrom();
		String userCode = this.request.getUserCode();
		String csaCode = this.request.getCsaCode(); 	
		String sessionId = this.request.getSessionId();
		Long shopId = this.request.getShopId();
		String messageType = this.request.getMessageType();
		Date beginDate = this.request.getBeginDate();
		Date endDate = this.request.getBeginDate();
		Integer pageNo = this.request.getPageNo();
		Integer pageSize = this.request.getPageSize();
		
		MessageHistoryReqVO historyReqVO = new MessageHistoryReqVO();
		historyReqVO.setPageNumber(pageNo);
		historyReqVO.setPageSize(pageSize);
		
		List<MessageHistoryRespVO> list = new ArrayList<>();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_MESSAGE_HISTORY");
		QueryBuilder  query = new QueryBuilder();
		if(StringUtil.isNotBlank(sessionId)){
			query.and("sessionId").is(sessionId);
		}

		if(shopId != null){
			query.and("shopId").is(shopId);
		}
		
		if(StringUtil.isNotBlank(csaCode)){
			query.and("csaCode").is(csaCode);
		}
		
		if(StringUtil.isNotBlank(userCode)){
			query.and("userCode").is(userCode);
		}
		
		if(StringUtil.isNotBlank(from)){
			query.and("from").is(from);
		}
		
		if(StringUtil.isNotBlank(messageType)){
			query.and("messageType").is(messageType);
		}else{
			query.and("messageType").notIn(Arrays.asList("welcome", "inform"));
		}
		
//		if(StringUtil.isNotBlank(body)){
//			Pattern pattern = Pattern.compile("^.*" + body+ ".*$", Pattern.CASE_INSENSITIVE); 
//			query.and("body").regex(pattern);
//		}
		query.and("beginDate").lessThanEquals(beginDate==null?DateUtil.getSysDate():beginDate);
		query.and("beginDate").greaterThan(endDate==null?DateUtil.getOffsetDaysTime(DateUtil.getSysDate(), -7):endDate);
		Long count  =collection.count(query.get());//记录总数
		Long pageCount = (count + historyReqVO.getPageSize() - 1) / pageSize; //当前页
		query.and("status").is("20");
		DBCursor cursor =collection.find(query.get()).sort(new BasicDBObject("beginDate",-1)).skip(historyReqVO.getStartRowIndex()).limit(historyReqVO.getEndRowIndex());
		try {
			
			while (cursor.hasNext()) {
				MessageHistoryRespVO resDTO = new MessageHistoryRespVO();
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, resDTO);
				List<MessageHistoryRespVO> breaks = breakMessage(resDTO);
				for (MessageHistoryRespVO messageHistoryRespVO : breaks) {
					list.add(messageHistoryRespVO);
				}
			}
		} finally {
			cursor.close();
		}
		
		//响应
		this.response.setPageResp(list);
		this.response.setPageCount(pageCount);
		this.response.setCount(count);
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
		if(this.request.getUserCode() == null){
			throw new BusinessException(new DefaultExceptionCode("1002","userCode不能为空"));
		}
	}
	
	private List<MessageHistoryRespVO> breakMessage(MessageHistoryRespVO vo) throws BusinessException{
		List<MessageHistoryRespVO> listvo = new ArrayList<>();
		//对web的普通消息进行处理
		if("WEB".equals(vo.getFrom().split("/")[1]) && "msg".equals(vo.getMessageType())){
			String msgBody = vo.getBody();
			//处理p标签
			msgBody = this.delPTag(msgBody);
			//1. 处理表情消息
			Pattern rEmoticon = Pattern.compile(this.regexpEmoticon);
			Matcher mEmoticon = rEmoticon.matcher(msgBody);
			while(mEmoticon.find()){
				String matchWord = mEmoticon.group(0);
				String matchnum = mEmoticon.group(0).replaceAll(this.regexpEmoticon, "$1");
				msgBody = msgBody.replace(matchWord, this.getEmoticonName(matchnum));
			}
			//2. 处理图片消息
			// 当包含图片内容时需要拆发消息
		    Pattern r = Pattern.compile(this.regexpImage);
			Matcher m = r.matcher(msgBody);
			List<String> list = new ArrayList<>();
			while (m.find()) {
				int start = msgBody.indexOf(m.group(0));
				int end = msgBody.indexOf(m.group(0)) + m.group(0).length();
				list.add(msgBody.substring(0, start).length() > 0 ? msgBody.substring(0, start) : null);
				list.add(m.group(0));
				msgBody = msgBody.substring(end);
			}
			if (msgBody.length() > 0) {
				list.add(msgBody);
			}
			list.removeAll(Collections.singleton(null)); //去除null值
			boolean isMulti = list.size()>1;
			int seq = 0;
			for (String string : list) {
				MessageHistoryRespVO msg = new MessageHistoryRespVO();
				ObjectCopyUtil.copyObjValue(vo, msg, null, true);
				Matcher mc = r.matcher(string);
				if(mc.matches()){//图片消息
					msg.setContentType("1");
					//取得文件url
					string = string.replaceAll(this.regexpImage, "$1");
				}else{//文字表情
					msg.setContentType("0");
				}
				string = StringEscapeUtils.unescapeHtml(string);//转义html字符
				string = this.delHTMLTag(string);
				msg.setBody(string);
				if(isMulti){
					msg.setId(msg.getId().concat("-"+seq));
					seq++;
				}
				listvo.add(msg);
			}
			
		}else if("APP".equals(vo.getFrom().split("/")[1]) && "msg".equals(vo.getMessageType()) && "1".equals(vo.getContentType())){
			String fileId = vo.getBody();
			if(StringUtil.isNotBlank(fileId)){
				vo.setBody(ImageUtil.getImageUrl(fileId));
			}
			listvo.add(vo);
		}else{
			listvo.add(vo);
		}
		
		return listvo;
	}
	
	//表情集
	private static String[] emoticonArray = new String[]{"微笑","大笑","调皮","惊慌",
			"耍酷","发火","害羞","汗水","大哭","得志","鄙视","困乏","夸奖","晕倒",
			"疑问","媒婆","狂吐","青蛙","发愁","亲吻","斗鸡眼","爱心","心碎","玫瑰",
			"礼物","哭","奸笑","可爱","得意","呲牙","暴汗","楚楚可怜","困","流泪","生气",
			"惊讶","口水","彩虹","夜空","太阳","钱钱","灯泡","咖啡","蛋糕","音乐","爱",
			"胜利","赞","差","OK"};
	
	private String regexpEmoticon = "<img src=\""+BaseParamUtil.fetchParamValue("IM_PROJECT_URL", "IM_PROJECT_URL")+"/images/face/i_f(\\d{2}).gif\"/>";
	private String regexpImage = "<img src=\"(http:\\S*)\" title=\"\\S*\" alt=\"\\S*\"/>";
	
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
//    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	
	private String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
//        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
//        Matcher m_space = p_space.matcher(htmlStr);  
//        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }  
      
	private String delPTag(String htmlStr){
    	return htmlStr.replace("<p>", "").replace("</p>", "");
    }
	
	private String getEmoticonName(String pos){
		Integer p = Integer.valueOf(pos);
		return "["+emoticonArray[--p]+"]";
	}
	
}

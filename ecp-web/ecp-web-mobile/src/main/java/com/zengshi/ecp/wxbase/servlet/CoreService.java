package com.zengshi.ecp.wxbase.servlet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.wxbase.message.resp.ArticleRespVO;
import com.zengshi.ecp.wxbase.message.resp.NewsMessageRespVO;
import com.zengshi.ecp.wxbase.message.resp.TextMessageRespVO;
import com.zengshi.ecp.wxbase.util.WxConstants;
import com.zengshi.ecp.wxbase.util.MessageUtil;

/**
 * 核心请求处理类
 * 
 * @author wangbh
 * @date 2015-12-18
 */
public class CoreService {

	/* private static ApplicationContext ctx = null; */

	/*private static Map<String, IWeixSendOperateSV> sendOperateMap;*/

	public static String openKey = "WEIX_USER_OPEN_";

	/*public static Map<String, IWeixSendOperateSV> getSendOperateMap() {
		return sendOperateMap;
	}

	public static void setSendOperateMap(Map<String, IWeixSendOperateSV> sendOperateMap) {
		CoreService.sendOperateMap = sendOperateMap;
	}*/

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;

		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessageRespVO textMessage = new TextMessageRespVO();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String Content = requestMap.get("Content");
				NewsMessageRespVO newsMessage = new NewsMessageRespVO();
				newsMessage.setFromUserName(toUserName);
				newsMessage.setToUserName(fromUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(1);

				List<ArticleRespVO> articleList = new ArrayList<ArticleRespVO>();
				if ("a".equals(Content)) {
					ArticleRespVO article = new ArticleRespVO();
					article.setTitle("ecp微信商城");
					article.setDescription("你好");
					article.setPicUrl("http://wangbaihuai.vicp.net/pmph-web-wx/images/banner2.jpg");
					article.setUrl("http://sso.ipmph.com/Login.jsp?ServiceID=mallshop");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					return respMessage;
				}

				respContent = BaseParamUtil.fetchParamValue(MessageUtil.RESP_MESSAGE_TYPE,MessageUtil.RESP_MESSAGE_TYPE_DEFAULT);//"您好，欢迎光临亚信数据电子商务平台";
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = BaseParamUtil.fetchParamValue(MessageUtil.RESP_MESSAGE_TYPE,MessageUtil.RESP_MESSAGE_TYPE_DEFAULT);//"您好，欢迎光临亚信数据电子商务平台";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = BaseParamUtil.fetchParamValue(MessageUtil.RESP_MESSAGE_TYPE,MessageUtil.RESP_MESSAGE_TYPE_DEFAULT);//"您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = BaseParamUtil.fetchParamValue(MessageUtil.RESP_MESSAGE_TYPE,MessageUtil.RESP_MESSAGE_TYPE_DEFAULT);//"您好，欢迎光临亚信数据电子商务平台";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = BaseParamUtil.fetchParamValue(MessageUtil.RESP_MESSAGE_TYPE,MessageUtil.RESP_MESSAGE_TYPE_DEFAULT);//"您好，欢迎光临亚信数据电子商务平台";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = BaseParamUtil.fetchParamValue(MessageUtil.RESP_MESSAGE_TYPE,MessageUtil.RESP_MESSAGE_TYPE_DEFAULT);//"谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey"); 
					if(eventKey.equals("1")){
					String Content = requestMap.get("Content");
					NewsMessageRespVO newsMessage = new NewsMessageRespVO();
					newsMessage.setFromUserName(toUserName);
					newsMessage.setToUserName(fromUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(1);

					List<ArticleRespVO> articleList = new ArrayList<ArticleRespVO>();

					ArticleRespVO article = new ArticleRespVO();
					article.setTitle("91蕾丝微官网");
					article.setDescription("点击进入");
					article.setPicUrl("http://lace-web-server.img-cn-hangzhou.aliyuncs.com/1ad4fe23-7c4b-4572-8880-b042439ef393@4e_0o_0l_362h_450w.jpg");
					/*String tocken = UUID.randomUUID().toString().replaceAll("-", "");
					tocken = openKey+tocken;
					CacheUtil.put(tocken, fromUserName, true);*/
					article.setUrl(WxConstants.LACE_WX_URL+"?wxTokcken="+fromUserName);
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					return respMessage;
					}
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				} else if (eventType.equals("VIEW")) {

				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}

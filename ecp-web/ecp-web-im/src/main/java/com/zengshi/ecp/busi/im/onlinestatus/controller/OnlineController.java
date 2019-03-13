package com.zengshi.ecp.busi.im.onlinestatus.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.onlinestatus.vo.OnlineReqVO;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@Controller
@RequestMapping(value = "/online")
public class OnlineController extends EcpBaseController {
	private static String MODULE = OnlineController.class.getName();

	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;

	@Resource
	private IStaffHotlineRSV iStaffHotlineRSV;

	@RequestMapping("updateOnlineStatus")
	@ResponseBody
	public EcpBaseResponseVO updateOnlineStatus(OnlineReqVO onlineReqVO) throws BusinessException {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		BasicDBObject query = new BasicDBObject();
		query.put("csaCode", onlineReqVO.getCsaCode());
		DBObject stuFound = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").findOne(query);
		stuFound.put("onlineStatus", onlineReqVO.getOnlineStatus());
		stuFound.put("updateTime", DateUtil.getSysDate());
		WriteResult result = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").update(query, stuFound);

		if (result.getN() > 0) {
			JSONObject doc = new JSONObject();
			onlineReqVO.setCreateTime(DateUtil.getSysDate());
			onlineReqVO.setUpdateTime(DateUtil.getSysDate());
			Map map = stuFound.toMap();
			onlineReqVO.setHotlineId((Long) map.get("hotlineId"));
			doc = (JSONObject) JSON.toJSON(onlineReqVO);
			MongoUtil.insert("T_IM_HOTLINE_ONLINE_LOG", doc);

			ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
			dto.setCsaCode(onlineReqVO.getCsaCode());
			dto.setShopId(onlineReqVO.getShopId());
			dto.setLineStatus(Integer.parseInt(onlineReqVO.getOnlineStatus()));
			custServiceMgrRSV.alterStaffLineStatus(dto);

			vo.setResultFlag("ok");
			vo.setResultMsg(onlineReqVO.getOnlineStatus());
		}

		return vo;

	}
	
	/**
	 * 
	 * loginAndupdateOnlineStatus:(登录并调整到非在线的可选状态). <br/> 
	 * 
	 * @author linby
	 * @param onlineReqVO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	@RequestMapping("loginAndupdateOnlineStatus")
	@ResponseBody
	public EcpBaseResponseVO loginAndupdateOnlineStatus(OnlineReqVO onlineReqVO) throws BusinessException {
		//登录客服
		ImStaffHotlineReqDTO staffHotline = new ImStaffHotlineReqDTO();
		staffHotline.setCsaCode(onlineReqVO.getCsaCode());
		custServiceMgrRSV.staffLogin(staffHotline);
		//修改状态
		return this.updateOnlineStatus(onlineReqVO);
	}

	
	/**
	 * 获取用户在线状态
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getOnlineInfo")
	@ResponseBody
	public Map<String, Object> getOnlineInfo(HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<>();
		if (StringUtil.isNotBlank(request.getParameter("JID"))) {
			String[] JID = request.getParameter("JID").split(",");
			for (String jid : JID) {
				String Jid = jid + BizUtil.getOfServer();
				String strUrl = BizUtil.getServer()+"/plugins/presence/status?jid="+Jid+"&type=text";
				try {
					URL oUrl = new URL(strUrl);
					URLConnection oConn = oUrl.openConnection();
					if (oConn != null) {
						BufferedReader oIn = new BufferedReader(new InputStreamReader(oConn.getInputStream()));
						if (null != oIn) {
							String strFlag = oIn.readLine();
							oIn.close();
							/*if(StringUtil.isNotBlank(strFlag)&&strFlag.equals("Unavailable")){
								ImStaffHotlineReqDTO req = new ImStaffHotlineReqDTO();
								req.setOfStaffCode(Jid);
								//调用后场服务，结果客户端的会话
								custServiceMgrRSV.finishChat(req);
							}*/
						    map.put(jid, strFlag);
						}
					}
				} catch (Exception e) {
					LogUtil.error(MODULE, "===调用用户在线状态插件失败", e);
				}

			}

		}
		return map;
	}


}

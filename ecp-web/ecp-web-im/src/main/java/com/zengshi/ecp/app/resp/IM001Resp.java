package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:31  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class IM001Resp extends IBody {
	
	private String resultFlag; //成功状态码
	private String resultMsg; //原因短语
	private String userCode; //用户OF账号
	private String csaCode; //客服OF账号
	private String hotlinePerson; //客服账号名
	private String hotlinePhoto; //客服头像url 
	private String custPic; //默认用户头像
	private String sessionId; //发起聊天记录ID
	private int waitCount; //等待人数
	
	public String getResultFlag() {
		return resultFlag;
	}
	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getCsaCode() {
		return csaCode;
	}
	public void setCsaCode(String csaCode) {
		this.csaCode = csaCode;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getWaitCount() {
		return waitCount;
	}
	public void setWaitCount(int waitCount) {
		this.waitCount = waitCount;
	}
	public String getHotlinePerson() {
		return hotlinePerson;
	}
	public void setHotlinePerson(String hotlinePerson) {
		this.hotlinePerson = hotlinePerson;
	}
	public String getHotlinePhoto() {
		return hotlinePhoto;
	}
	public void setHotlinePhoto(String hotlinePhoto) {
		this.hotlinePhoto = hotlinePhoto;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getCustPic() {
		return custPic;
	}
	public void setCustPic(String custPic) {
		this.custPic = custPic;
	}


}

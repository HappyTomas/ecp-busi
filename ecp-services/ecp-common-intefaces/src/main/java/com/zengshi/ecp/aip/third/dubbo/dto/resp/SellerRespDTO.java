package com.zengshi.ecp.aip.third.dubbo.dto.resp;


import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class SellerRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	
	//用户数字ID
	private String  userId;
	//用户昵称
	private String nick;
	//性别。可选值:m(男),f(女)
	private String sex;
   // 用户类型。可选值:B(B商家),C(C商家)  B表示天猫  C表示淘宝
	private String type;
	//可选值:normal(正常),inactive(未激活),delete(删除),reeze(冻结),supervise(监管)
	private String status;
	public String getUserId() {
		return userId;
	}
	public String getNick() {
		return nick;
	}
	public String getSex() {
		return sex;
	}
	public String getType() {
		return type;
	}
	public String getStatus() {
		return status;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}


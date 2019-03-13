package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Gds017Req extends IBody {
	//版本系统类型
	private String verOs;
		
    //版本归属项目
	private String verProgram;
	//当前版本编号
	private Long verNo; 
	
	public String getVerOs() {
		return verOs;
	}

	public void setVerOs(String verOs) {
		this.verOs = verOs;
	}

	public String getVerProgram() {
		return verProgram;
	}

	public void setVerProgram(String verProgram) {
		this.verProgram = verProgram;
	}

	public Long getVerNo() {
		return verNo;
	}

	public void setVerNo(Long verNo) {
		this.verNo = verNo;
	}
	
}


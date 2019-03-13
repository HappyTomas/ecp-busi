/** 
 * Project Name:ecp-web-mall 
 * File Name:DemoResp.java 
 * Package Name:com.zengshi.ecp.app.resp 
 * Date:2016-2-22下午6:53:17 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.resp;



import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 查询签到送积分出参<br>
 * Date:2016年9月21日下午3:41:54  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class Staff122Resp extends IBody {
    
	private Long score;
	
	private Long signCnt;//连续签到次数

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getSignCnt() {
		return signCnt;
	}

	public void setSignCnt(Long signCnt) {
		this.signCnt = signCnt;
	}

}


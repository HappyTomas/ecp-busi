/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoInfoVO.java 
 * Package Name:com.zengshi.ecp.busi.demo.vo 
 * Date:2015-8-5下午3:02:29 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.buyer.vo;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-8-5下午3:02:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7 
 */
public class ModifyPwdVO implements Serializable{
    
    /** 
     * serialVersionUID:
     * @since JDK 1.7 
     */ 
    private static final long serialVersionUID = -1808106181284676714L;

    private String newPassword;
    
    private String oldPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}


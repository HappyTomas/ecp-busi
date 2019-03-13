package com.zengshi.model;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * @author huangjx
 *
 */
public class MsgTaskReq  extends BaseInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean ifSuccess=true;//是否成功
    
    private Exception exception;//错误信息
    
    public boolean isIfSuccess() {
		return ifSuccess;
	}

	public Exception getException() {
		return exception;
	}

	public void setIfSuccess(boolean ifSuccess) {
		this.ifSuccess = ifSuccess;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	
}

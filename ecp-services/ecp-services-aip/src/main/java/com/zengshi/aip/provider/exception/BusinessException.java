package com.zengshi.aip.provider.exception;

/**
 * AIP业务级别异常类
 * @author liangdl5
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 7560621105713460541L;

	public BusinessException() {
		super(); 
	} 
	
	public BusinessException(String msg) {
		super(msg); 
	} 
	
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause); 
	}
	
	public BusinessException(Throwable cause) {
		super(cause); 
	}
	
}

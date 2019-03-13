package com.zengshi.aip.provider.mail;

import java.util.List;

/**
 * 
 * @Title: MailService.java
 * @Package: com.zengshi.aip.provider.mail
 * @Description: 邮件发送服务
 * @Comment:
 * @author: luocan
 * @CreateDate: 2014年9月29日 上午11:05:50
 */

public interface MailService {
	
	public void sendMail(String to, String title, String content) throws Exception;
	
	public void sendMail(String from, String to, String title, String content) throws Exception;
	
	public void sendMail(List<String> toList, String title, String content) throws Exception;
	
	public void sendMail(String from, List<String> toList, String title, String content)  throws Exception;
	
	public void sendDelayMail(String to, String title, String content) throws Exception;
	
	public void sendDelayMail(String from, String to, String title, String content) throws Exception;
	
	public void sendDelayMail(List<String> toList, String title, String content) throws Exception;
	
	public void sendDelayMail(String from, List<String> toList, String title, String content) throws Exception;
	
}

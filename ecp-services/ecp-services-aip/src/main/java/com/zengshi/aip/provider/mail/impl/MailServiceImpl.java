package com.zengshi.aip.provider.mail.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.paas.utils.MailUtil;
import com.zengshi.aip.provider.mail.MailService;

public class MailServiceImpl implements MailService {
	private final static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	public void sendMail(String to, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("收件人  ：%s ，主题  ：%s ，内容  ：%s  。",  to, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendMail(to, title, content);
		} catch (Exception e) {
         logger.error("邮件发送出现异常",e);
         throw e;
         
		}
	}

	public void sendMail(String from, String to, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("发件人  ：  %s ，收件人  ：%s ，主题  ：%s ，内容  ：%s  。", from, to, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendMail(from, to, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;
	         
		}
	}

	public void sendMail(List<String> toList, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("收件人  ：%s ，主题  ：%s ，内容  ：%s  。",  toList, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendMail(toList, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;
	     
		}
	}

	public void sendMail(String from, List<String> toList, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("发件人  ：  %s ，收件人  ：%s ，主题  ：%s ，内容  ：%s  。", from, toList, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendMail(from, toList, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;
	     
		}
	}

	public void sendDelayMail(String to, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("收件人  ：%s ，主题  ：%s ，内容  ：%s  。",  to, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendDelayMail(to, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;
	         
		}
	}

	public void sendDelayMail(String from, String to, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("发件人  ：  %s ，收件人  ：%s ，主题  ：%s ，内容  ：%s  。", from, to, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendDelayMail(from, to, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;
	     
		}
	}

	public void sendDelayMail(List<String> toList, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("收件人  ：%s ，主题  ：%s ，内容  ：%s  。",  toList, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendDelayMail(toList, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;
	     
		}
	}

	public void sendDelayMail(String from, List<String> toList, String title, String content) throws Exception {
		//记录日志
		String sendMsg=String.format("发件人  ：  %s ，收件人  ：%s ，主题  ：%s ，内容  ：%s  。", from, toList, title, content);
		logger.info(sendMsg);
		try {
			MailUtil.sendDelayMail(from, toList, title, content);
		} catch (Exception e) {
	         logger.error("邮件发送出现异常",e);
	         throw e;

		}
		
	}
	
	public static void main(String[] args) {
		try {
			List<String> mailadd=new ArrayList<String>();
			mailadd.add("luocan@izengshi.com");
			mailadd.add("xuanjian@izengshi.com");
			MailServiceImpl tMailServiceImpl=new MailServiceImpl();
//			tMailServiceImpl.sendMail("luocan@izengshi.com","hello","成功!");
			tMailServiceImpl.sendMail(mailadd, "list测试！", "成功");
			System.out.println("发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

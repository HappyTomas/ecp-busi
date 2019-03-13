package com.zengshi.ecp.im.dubbo.util;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: 是否允许用户退出控制 <br>
 * logout
 * 
 * Project Name:ecp-services-im-server <br>
 * Description: <br>
 * Date:2017年2月21日下午7:03:04  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class OpenfireLogoutable {

	private static String MODULE = OpenfireLogoutable.class.getName();
	
	/**
	 * 用户是否可退出标识MAP
	 */
	private static final String ECP_IM_USER_LOG_MAP = "ECP.IM.USER.LOG.MAP.OPENFIRE";
	
	/**
	 * 超时阈值：10分钟
	 */
	public static final long TIMEOUT_THRESHOLD = 10*60*1000;
	
	private static  ICustServiceMgrRSV custServiceMgrRSV;
    
    static{
    	custServiceMgrRSV = new ClassPathXmlApplicationContext("spring-context.xml").getBean("custServiceMgrRSV", ICustServiceMgrRSV.class);
    }
    
    public static ICustServiceMgrRSV getCustServiceMgrRSV(){
        return custServiceMgrRSV;
    }
	
	/**
	 * 
	 * allowLogout:(允许用户退出). <br/> 
	 * 
	 * @author linby
	 * @param usercode 
	 * @since JDK 1.7
	 */
	public static void allowLogout(String usercode){
		CacheUtil.hsetItem(ECP_IM_USER_LOG_MAP, usercode, Boolean.TRUE);
	}
	
	/**
	 * 
	 * forbidLogout:(禁止用户退出). <br/> 
	 * 
	 * @author linby
	 * @param usercode 
	 * @since JDK 1.7
	 */
	public static void forbidLogout(String usercode){
		CacheUtil.hsetItem(ECP_IM_USER_LOG_MAP, usercode, Boolean.FALSE);
	}
	
	/**
	 * 
	 * prepareLogout:(用户退出准备). <br/> 
	 * 
	 * @author linby
	 * @param usercode 
	 * @since JDK 1.7
	 */
	public static void prepareLogout(String usercode){
		CacheUtil.hsetItem(ECP_IM_USER_LOG_MAP, usercode, new Date());
	}
	
	/**
	 * 
	 * logoutable:(是否允许退出). <br/>
	 * 
	 * @author linby
	 * @return 
	 * @since JDK 1.7
	 */
	public static Boolean logoutable(String usercode){
		Object data = CacheUtil.hgetItem(ECP_IM_USER_LOG_MAP, usercode);
		//根据显式要求返回
		if(Boolean.class.isInstance(data)){
			Boolean tf = (Boolean) data;
			if(tf){
				LogUtil.info(MODULE, "用户["+usercode+"]允许退出！");
			}else{
				LogUtil.info(MODULE, "用户["+usercode+"]不允许退出！");
			}
			return tf;
		}
		//若预期超时，则允许退出
		if(Date.class.isInstance(data)){
			Date pre = (Date) data;
			if((System.currentTimeMillis() - pre.getTime()) > TIMEOUT_THRESHOLD){
				LogUtil.info(MODULE, "用户["+usercode+"]超时("+TIMEOUT_THRESHOLD+")退出！");
				allowLogout(usercode);
				return true;
			}
		}
		
		return false;
	} 
	
	/**
	 * 
	 * clear:(清理未正常退出的客服). <br/> 
	 * 
	 * @author linby
	 * @param usercode 
	 * @since JDK 1.7
	 */
	public void clear(){
		Set<String> ables = CacheUtil.hkeys(ECP_IM_USER_LOG_MAP);
		Iterator<String> it = ables.iterator();
		while (it.hasNext()) {
			String usercode = it.next();
			if(logoutable(usercode)){
				ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
				dto.setCsaCode(usercode);
				custServiceMgrRSV.staffLogout(dto);
			}
		}
	}
	
}

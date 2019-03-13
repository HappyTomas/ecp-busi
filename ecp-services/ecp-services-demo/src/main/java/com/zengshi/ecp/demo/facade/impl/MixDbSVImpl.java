/**
 * 
 */
package com.zengshi.ecp.demo.facade.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dao.model.User;
import com.zengshi.ecp.demo.facade.IMixDbSV;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoInfoSV;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoLogSV;
import com.zengshi.ecp.demo.service.common.interfaces.IUserSV;


/**
 * @author yugn
 *
 */
//@Service("mixDbSv")
public class MixDbSVImpl implements IMixDbSV {
	
	@Autowired
	private IUserSV userSv;
	
	@Autowired
	private IDemoLogSV demoLogSv;
	
	@Resource(name="demoInfoSv")
	private IDemoInfoSV demoInfoSv;

	/* (non-Javadoc)
	 * @see com.zengshi.ecp.busi.demo.log.service.interfaces.IMixDbSV#saveInfo(com.zengshi.ecp.busi.demo.log.dao.model.DemoLog, com.zengshi.ecp.busi.demo.common.dao.model.User)
	 */
	@Override
	public void saveInfo(DemoLog log, User user) throws Exception {
		
		this.demoLogSv.saveLog(log);
		
		this.userSv.saveUser(user);
		
		throw new RuntimeException("异常回滚！！！");
	}

	@Override
	public void saveDemo(DemoLog log, DemoInfo info) throws Exception {
		this.demoInfoSv.saveInfo(info);
		this.demoLogSv.saveLog(log);
	}

}

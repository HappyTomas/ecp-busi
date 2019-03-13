/**
 * 
 */
package com.zengshi.ecp.demo.service.common.impl;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.demo.dao.mapper.common.PasswordMapper;
import com.zengshi.ecp.demo.dao.mapper.common.UserMapper;
import com.zengshi.ecp.demo.dao.model.Password;
import com.zengshi.ecp.demo.dao.model.User;
import com.zengshi.ecp.demo.service.common.interfaces.IUserSV;
import com.db.sequence.Sequence;

/**
 * @author yugn
 *
 */
public class UserSVImpl implements IUserSV {
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private PasswordMapper passwordMapper;
	
//	@Resource
//	private IUserDAO userDAO;
//	@Resource
//	private IPasswordDAO passwordDAO;
	
	@Resource(name="seq_user_id")
//	@Resource(name="seq_demo_info")
	private Sequence seqUserId;

	/* (non-Javadoc)
	 * @see com.zengshi.ecp.busi.demo.common.service.interfaces.IUserSV#saveUser(com.zengshi.ecp.busi.demo.common.dao.model.User)
	 */
	@Override
	public String saveUser(User user){
		
		long id = seqUserId.nextValue();
		String userId = StringUtils.leftPad(id+"", 5, "0");
		user.setUserId(userId);
		user.setUserName(user.getUserName()+userId);
		this.userMapper.insert(user);
		
		Password pass = new Password();
		pass.setUserId(user.getUserId());
		pass.setPassword("1");
		
		Calendar t = Calendar.getInstance();
		t.add(Calendar.MONTH, 3);
		pass.setValidDate(new Timestamp(t.getTime().getTime()));
		
		this.passwordMapper.insert(pass);
		
		return userId;
	}

}

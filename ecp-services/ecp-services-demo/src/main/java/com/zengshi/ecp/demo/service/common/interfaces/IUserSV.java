/**
 * 
 */
package com.zengshi.ecp.demo.service.common.interfaces;

import com.zengshi.ecp.demo.dao.model.User;

/**
 * @author yugn
 *
 */
public interface IUserSV {
	
	/**
	 * 保存用户信息
	 * @param user
	 * @throws Exception
	 */
	public String saveUser(User user);

}

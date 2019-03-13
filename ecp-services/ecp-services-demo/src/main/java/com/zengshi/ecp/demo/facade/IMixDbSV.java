/**
 * 
 */
package com.zengshi.ecp.demo.facade;

import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dao.model.User;

/**
 * @author yugn
 *
 */

public interface IMixDbSV {
    
	/**
	 * 混合日志保存
	 * @param log
	 * @param user
	 */
	public void saveInfo(DemoLog log ,User user) throws Exception;
	
	/**
	 * 混合日志信息保存，域内；
	 * @param log
	 * @param info
	 * @throws Exception
	 */
	public void saveDemo(DemoLog log, DemoInfo info) throws Exception;
}

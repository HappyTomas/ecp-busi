/**
 * 
 */
package com.zengshi.ecp.demo.service.busi.interfaces;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.demo.dao.model.DemoInfo;

/**
 * @author yugn
 *
 */
public interface IDemoInfoSV {
	
	public long saveInfo(DemoInfo info) throws DataAccessException;
	
	public DemoInfo fetchDemoById(long id ) throws DataAccessException;

}

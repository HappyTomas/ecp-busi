/**
 * 
 */
package com.zengshi.ecp.demo.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.demo.dao.model.DemoLog;
import com.zengshi.ecp.demo.dao.model.DemoLogCriteria;
import com.zengshi.ecp.demo.dubbo.dto.DemoLogDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * @author yugn
 *
 */
public interface IDemoLogSV extends IGeneralSQLSV{
	
	/**
	 * 
	 * @param log
	 * @throws Exception
	 */
	public boolean saveLog(DemoLog log);
	
	
	DemoLog find(long id);
	
	DemoLog select(long id);
	
	DemoLog findByCodeAndId(long id,String dbCode) throws Exception;
	
	List<DemoLog> findAll();
	
	List<DemoLog> findAll(DemoLogCriteria criteria);
	
	PageResponseDTO<DemoLogDTO> findByPage(BaseInfo baseInfo);

}

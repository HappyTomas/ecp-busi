/**
 * 
 */
package com.zengshi.ecp.demo.service.busi.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.demo.dao.mapper.busi.DemoInfoMapper;
import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoInfoSV;
import com.db.sequence.Sequence;

/**
 * @author yugn
 *
 */

public class DemoInfoSVImpl implements IDemoInfoSV {
	
	@Autowired
	private DemoInfoMapper demoInfoMapper;
	
	@Resource(name="seq_demo_info_id")
//	@Resource(name="seq_demo_info")
	private Sequence seqDemoInfoId;

	@Override
	public long saveInfo(DemoInfo info) throws DataAccessException{
		
		int id = seqDemoInfoId.nextValue().intValue();
		info.setId(Long.valueOf(id));
		demoInfoMapper.insert(info);
		return id;
	}

    @Override
    public DemoInfo fetchDemoById(long id) throws DataAccessException {
        
        return demoInfoMapper.selectByPrimaryKey(id);
    }

	
	
}

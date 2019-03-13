package com.zengshi.ecp.unpf.service.busi.order.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdLogMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdLog;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdLogSV;
import com.db.sequence.Sequence;

public class UnpfOrdLogSVImpl implements IUnpfOrdLogSV{

	@Resource(name = "seq_unpf_ord_log_id")
	private Sequence seqUnpfOrdLog;
	 
	@Resource
	private UnpfOrdLogMapper unpfOrdLogMapper;
	
	@Override
	public void saveUnpfOrdLog(OrderRespDTO orderDetial) throws BusinessException {
		// TODO Auto-generated method stub
		UnpfOrdLog log = new UnpfOrdLog();
		log.setId(seqUnpfOrdLog.nextValue());
		log.setOrderId(orderDetial.getOrderId());
		log.setOrdParams(orderDetial.getRespParam());
		log.setPlatType(orderDetial.getPlatType());
		log.setShopId(orderDetial.getShopId());
		log.setCreateStaff(1000L);
		log.setCreateTime(new Timestamp(System.currentTimeMillis()));
		unpfOrdLogMapper.insert(log);
	}

}

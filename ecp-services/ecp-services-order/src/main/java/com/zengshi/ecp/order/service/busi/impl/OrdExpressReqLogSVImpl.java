package com.zengshi.ecp.order.service.busi.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.order.dao.mapper.busi.OrdExpressReqLogMapper;
import com.zengshi.ecp.order.dao.model.OrdDeliveryBatch;
import com.zengshi.ecp.order.dao.model.OrdExpressReqLogWithBLOBs;
import com.zengshi.ecp.order.dubbo.dto.OrdExpressLogReq;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryBatchSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdExpressReqLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class OrdExpressReqLogSVImpl implements IOrdExpressReqLogSV {

	@Resource
	private OrdExpressReqLogMapper ordExpressReqLogMapper;
	
	@Resource
	private IOrdDeliveryBatchSV ordDeliveryBatchSV;
	
	@Resource(name = "seq_ord_express_req_log")
	private Sequence seqOrdRxpressReqLog;
	
	@Override
	public void saveOrdExpressLog(OrdExpressLogReq log) throws BusinessException {
		// TODO Auto-generated method stub
		OrdExpressReqLogWithBLOBs record = new OrdExpressReqLogWithBLOBs(); 
		ObjectCopyUtil.copyObjValue(log, record, null, false);
		if(StringUtil.isNotBlank(log.getExpressNo())){
			if(StringUtil.isBlank(log.getOrderId())){
				//根据运单号查询发货时对应的订单信息
				List<OrdDeliveryBatch> list = ordDeliveryBatchSV.queryExpressInfoByexpressNo(log.getExpressNo());
				if(CollectionUtils.isNotEmpty(list)){
					OrdDeliveryBatch delivery = list.get(0);
					record.setOrderId(delivery.getOrderId());
					record.setShopId(delivery.getShopId());
				}
			}else{
					record.setOrderId(log.getOrderId());
					record.setShopId(log.getShopId());				
			}
		}else{
			if(StringUtil.isNotBlank(log.getOrderId())){
				record.setOrderId(log.getOrderId());
				record.setShopId(log.getShopId());
			}
		}
		
		record.setId(seqOrdRxpressReqLog.nextValue());
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		
		ordExpressReqLogMapper.insert(record);
	}

}

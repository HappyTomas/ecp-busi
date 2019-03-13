package com.zengshi.ecp.order.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.order.dao.mapper.busi.OrdExpressDetailMapper;
import com.zengshi.ecp.order.dao.model.OrdDeliveryBatch;
import com.zengshi.ecp.order.dao.model.OrdExpressDetail;
import com.zengshi.ecp.order.dao.model.OrdExpressDetailCriteria;
import com.zengshi.ecp.order.dubbo.dto.OrdExpressDetailResp;
import com.zengshi.ecp.order.dubbo.dto.OrderExpressReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryBatchSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrderExpressSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;


public class OrderExpressSVImpl implements IOrderExpressSV {
	
	@Resource
	private IOrdDeliveryBatchSV ordDeliveryBatchSV;
	
	@Resource
	private OrdExpressDetailMapper ordExpressDetailMapper;
	
	@Resource
	private IBaseExpressRSV baseExpressRSV;
	
	@Resource(name = "seq_ord_express_detail")
	private Sequence seqOrdExpress;
	
	@Override
	public void saveOrderExpress(String expressNo,String expressCode, List<OrderExpressReq> expresses) throws BusinessException {
		// TODO Auto-generated method stub
		if(StringUtil.isBlank(expressNo)){
			throw new BusinessException("ORD_INPUT_311014");
		}
		//根据运单号查询发货时对应的订单信息
		List<OrdDeliveryBatch> list = ordDeliveryBatchSV.queryExpressInfoByexpressNo(expressNo);
		if(CollectionUtils.isNotEmpty(list)){
			OrdDeliveryBatch delivery = list.get(0);
			
			//由于接口信息是全量信息，因而需要删除原有信息（耗序列和服务器资源，但是没办法啊。。。。。，没办法判断那条信息是否已经保存）
			OrdExpressDetailCriteria criteria = new OrdExpressDetailCriteria();
			criteria.createCriteria().andExpressIdEqualTo(expressNo);
			ordExpressDetailMapper.deleteByExample(criteria);
			if(expresses!=null){
			//保存物流信息
				for(OrderExpressReq expresse:expresses){
					OrdExpressDetail detail = new OrdExpressDetail();
					ObjectCopyUtil.copyObjValue(expresse, detail, null, false);
					detail.setId(seqOrdExpress.nextValue());
					detail.setOrderId(delivery.getOrderId());//订单号
					detail.setExpressId(expressNo);//物流单号
					detail.setExpressCode(expressCode);//物流公司号
					detail.setCreateTime(new Timestamp(System.currentTimeMillis()));
					Timestamp expressTime =  Timestamp.valueOf(expresse.getFtime());
					detail.setExpressTime(expressTime);
					try{
						ordExpressDetailMapper.insert(detail);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public List<ROrdExpressDetailsResp> queryOrderExpressDetailList(String orderId) throws BusinessException {
		// TODO Auto-generated method stub
		List<ROrdExpressDetailsResp> resps = new ArrayList<ROrdExpressDetailsResp>();
		List<SOrderDetailsDelivery> list = ordDeliveryBatchSV.queryExpressInfoByOrderId(orderId);
		if(list!=null){
			for(SOrderDetailsDelivery delivery:list){
				
				ROrdExpressDetailsResp resp = new ROrdExpressDetailsResp();
				ObjectCopyUtil.copyObjValue(delivery, resp, null, false);
				
				if(delivery.getDeliveryType().equals(OrdConstants.Order.ORDER_DELIVER_FLAG_TRUE)){
					resp.setStatus("暂无状态信息");
					BaseExpressReqDTO reqDto = new BaseExpressReqDTO();
					reqDto.setId(delivery.getExpressId());
					BaseExpressRespDTO  expressResp = baseExpressRSV.fetchExpressInfo(reqDto);
					resp.setExpressName(expressResp.getExpressName());
					
					OrdExpressDetailCriteria criteria = new OrdExpressDetailCriteria();
					criteria.setOrderByClause(" express_time desc");
					criteria.createCriteria().andOrderIdEqualTo(orderId).andExpressIdEqualTo(delivery.getExpressNo());
					List<OrdExpressDetail> details = ordExpressDetailMapper.selectByExample(criteria);
					
					List<OrdExpressDetailResp> detailResps = new ArrayList<OrdExpressDetailResp>();
					
					if(details!=null){
						int i=0;
						for(OrdExpressDetail ordExpressDetail:details){
							OrdExpressDetailResp detailResp = new OrdExpressDetailResp();
							ObjectCopyUtil.copyObjValue(ordExpressDetail, detailResp, null, false);
							detailResps.add(detailResp);
							if(i==0){
								resp.setStatus(ordExpressDetail.getStatus());
							}
							i++;
						}
					}
					resp.setOrdExpressDetailResps(detailResps);
				}
				resps.add(resp);
			}
		}
		return resps;
	}

	@Override
	public List<OrdExpressDetailResp> queryOrderExpressDetail(String orderId) throws BusinessException {
		// TODO Auto-generated method stub
		OrdExpressDetailCriteria criteria = new OrdExpressDetailCriteria();
		//criteria.setOrderByClause("");
		criteria.createCriteria().andOrderIdEqualTo(orderId);
		List<OrdExpressDetailResp> resps = new ArrayList<OrdExpressDetailResp>();
		List<OrdExpressDetail> list = ordExpressDetailMapper.selectByExample(criteria);
		if(list!=null){
			for(OrdExpressDetail ordExpressDetail:list){
				OrdExpressDetailResp resp = new OrdExpressDetailResp();
				ObjectCopyUtil.copyObjValue(ordExpressDetail, resp, null, false);
				resps.add(resp);
			}
		}
		return resps;
	}

	

}

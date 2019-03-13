package com.zengshi.ecp.unpf.service.busi.order.impl;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdDeliveryDetailsMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryBatch;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryDetails;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdDeliveryDetailsCriteria;
import com.zengshi.ecp.unpf.dubbo.dto.order.*;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdDeliverDetailsSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdSubSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public class UnpfOrdDeliverDetailsSVImpl implements IUnpfOrdDeliverDetailsSV {
	
	private static final String MODULE = UnpfOrdDeliverDetailsSVImpl.class.getName();
    @Resource
    private IUnpfOrdSubSV unpfOrdSubSV;

    @Resource(name = "seq_unpf_ord_delivery_details")
    private Sequence seqUnpfOrdDeliveryDetails;

    @Resource
    private UnpfOrdDeliveryDetailsMapper unpfOrdDeliveryDetailsMapper;

    @Override
    public void saveUnpfOrdDeliverDetails(RUnpfSendMainReq rUnpfSendMainReq, UnpfOrdDeliveryBatch unpfOrdDeliveryBatch) throws BusinessException {


        for (RUnpfSendSubReq rUnpfSendSubReq: rUnpfSendMainReq.getUnpfSendSubReqList()){
            RUnpfOrdSubReq rUnpfOrdSubReq = new RUnpfOrdSubReq();
//            rUnpfOrdSubReq.setOuterSubId(rUnpfSendSubReq.getOuterSubId());
            rUnpfOrdSubReq.setId(rUnpfSendSubReq.getId());
            rUnpfOrdSubReq.setPlatType(unpfOrdDeliveryBatch.getPlatType());
            RUnpfOrdSubResp rUnpfOrdSubResp = unpfOrdSubSV.queryUnpfOrdSubIn(rUnpfOrdSubReq);
            UnpfOrdDeliveryDetails unpfOrdDeliveryDetails = new UnpfOrdDeliveryDetails();
            unpfOrdDeliveryDetails.setId(this.seqUnpfOrdDeliveryDetails.nextValue());
            unpfOrdDeliveryDetails.setBatchId(unpfOrdDeliveryBatch.getId());
            unpfOrdDeliveryDetails.setOrderId(unpfOrdDeliveryBatch.getOrderId());
            unpfOrdDeliveryDetails.setOrderSubId(rUnpfSendSubReq.getId());
            unpfOrdDeliveryDetails.setExpressNo(unpfOrdDeliveryBatch.getExpressNo());
//            unpfOrdDeliveryDetails.setCategoryCode(rUnpfOrdSubResp.getCategoryCode());
            try{
            if(StringUtil.isNotBlank(rUnpfOrdSubResp.getGdsId())){
                unpfOrdDeliveryDetails.setGdsId(Long.parseLong(rUnpfOrdSubResp.getGdsId()) );
            }
            }catch(Exception ex){
            	LogUtil.info(MODULE, ex.toString());
            }
            try{
            if(StringUtil.isNotBlank(rUnpfOrdSubResp.getSkuId())){
                unpfOrdDeliveryDetails.setSkuId(Long.parseLong(rUnpfOrdSubResp.getSkuId()));
            }
	        }catch(Exception ex){
	        	LogUtil.info(MODULE, ex.toString());
	        }
            unpfOrdDeliveryDetails.setSkuInfo(rUnpfOrdSubResp.getSkuInfo());
            unpfOrdDeliveryDetails.setGdsName(rUnpfOrdSubResp.getGdsName());
            unpfOrdDeliveryDetails.setDeliveryAmount(rUnpfSendSubReq.getDeliveryAmount());
            unpfOrdDeliveryDetails.setSendDate(unpfOrdDeliveryBatch.getSendDate());
            unpfOrdDeliveryDetails.setStaffId(unpfOrdDeliveryBatch.getStaffId());
            unpfOrdDeliveryDetails.setShopId(unpfOrdDeliveryBatch.getShopId());
            unpfOrdDeliveryDetails.setCreateTime(unpfOrdDeliveryBatch.getSendDate());
            unpfOrdDeliveryDetails.setCreateStaff(unpfOrdDeliveryBatch.getCreateStaff());
            unpfOrdDeliveryDetails.setUpdateTime(unpfOrdDeliveryBatch.getSendDate());
            unpfOrdDeliveryDetails.setUpdateStaff(unpfOrdDeliveryBatch.getUpdateStaff());
            unpfOrdDeliveryDetails.setPlatType(unpfOrdDeliveryBatch.getPlatType());
            unpfOrdDeliveryDetails.setOuterId(unpfOrdDeliveryBatch.getOuterId());
            unpfOrdDeliveryDetails.setOuterSubId(rUnpfSendSubReq.getOuterSubId());
            this.unpfOrdDeliveryDetailsMapper.insert(unpfOrdDeliveryDetails);
        }


    }

    @Override
    public List<RUnpfOrdDeliverDetailsResp> queryUnpfOrdDeliverDetails(RUnpfOrdDeliveryBatchReq rUnpfOrdDeliveryBatchReq) throws BusinessException {

        UnpfOrdDeliveryDetailsCriteria uodc = new UnpfOrdDeliveryDetailsCriteria();
        UnpfOrdDeliveryDetailsCriteria.Criteria ca = uodc.createCriteria();
        ca.andBatchIdEqualTo(rUnpfOrdDeliveryBatchReq.getId());
        List<UnpfOrdDeliveryDetails> unpfOrdDeliveryDetailsList = this.unpfOrdDeliveryDetailsMapper.selectByExample(uodc);
        if(CollectionUtils.isEmpty(unpfOrdDeliveryDetailsList)){
            return  null;
        }
        List<RUnpfOrdDeliverDetailsResp> rUnpfOrdDeliverDetailsResps = new ArrayList<>();
        for(UnpfOrdDeliveryDetails unpfOrdDeliveryDetails:unpfOrdDeliveryDetailsList){
            RUnpfOrdDeliverDetailsResp rUnpfOrdDeliverDetailsResp = new RUnpfOrdDeliverDetailsResp();
            ObjectCopyUtil.copyObjValue(unpfOrdDeliveryDetails,rUnpfOrdDeliverDetailsResp,null,false);
            rUnpfOrdDeliverDetailsResps.add(rUnpfOrdDeliverDetailsResp);
        }
        return rUnpfOrdDeliverDetailsResps;
    }
}

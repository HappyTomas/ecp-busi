package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdInvoiceCommMapper;
import com.zengshi.ecp.order.dao.model.OrdInvoiceComm;
import com.zengshi.ecp.order.dao.model.OrdInvoiceCommCriteria;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdInvoiceCommSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:27:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version  
 * @since JDK 1.6 
 */  
public class OrdInvoiceCommSVImpl  implements IOrdInvoiceCommSV {
    
    @Resource
    private OrdInvoiceCommMapper ordInvoiceCommMapper;
    
    @Resource(name="seq_ord_invoice_comm")
    private Sequence seqOrdInvoiceComm;
    
    private static final String MODULE = OrdInvoiceCommSVImpl.class.getName();

    @Override
    public void saveOrdInvoiceComm(OrdInvoiceComm info) {
        info.setId(this.seqOrdInvoiceComm.nextValue());
        this.ordInvoiceCommMapper.insert(info);
    }

    @Override
    public SOrderDetailsComm queryOrderDetailsComm(String orderId) {
        OrdInvoiceCommCriteria occ = new OrdInvoiceCommCriteria();
        occ.createCriteria().andOrderIdEqualTo(orderId);
        List<OrdInvoiceComm> oics = this.ordInvoiceCommMapper.selectByExample(occ);
        if(CollectionUtils.isEmpty(oics)){
            return null;
        }
        SOrderDetailsComm sod = new SOrderDetailsComm();
        ObjectCopyUtil.copyObjValue(oics.get(0), sod, null, false);
        LogUtil.info(MODULE, sod.toString());
        return sod;
    }
    
    @Override
    public void updateOrdInvoiceComm(OrdInvoiceComm info){
    	OrdInvoiceCommCriteria occ = new OrdInvoiceCommCriteria();
        occ.createCriteria().andOrderIdEqualTo(info.getOrderId());
    	this.ordInvoiceCommMapper.updateByExampleSelective(info, occ);
    }

	@Override
	public Long countOrdInvoiceCommByOrderId(String orderId) {
		// TODO Auto-generated method stub
		  OrdInvoiceCommCriteria occ = new OrdInvoiceCommCriteria();
	      occ.createCriteria().andOrderIdEqualTo(orderId);
	      return ordInvoiceCommMapper.countByExample(occ);
	}

	@Override
	public void deleteInvoiceCommByOrderId(String orderId) {
		// TODO Auto-generated method stub
		OrdInvoiceCommCriteria occ = new OrdInvoiceCommCriteria();
	    occ.createCriteria().andOrderIdEqualTo(orderId);
	    ordInvoiceCommMapper.deleteByExample(occ);
	}
    
    



}


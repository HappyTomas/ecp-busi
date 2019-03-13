package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdInvoiceTaxMapper;
import com.zengshi.ecp.order.dao.model.OrdInvoiceTax;
import com.zengshi.ecp.order.dao.model.OrdInvoiceTaxCriteria;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTax;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdInvoiceTaxSV;
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
public class OrdInvoiceTaxSVImpl  implements IOrdInvoiceTaxSV{
    
    @Resource
    private OrdInvoiceTaxMapper ordInvoiceTaxMapper;
    
    @Resource(name="seq_ord_invoice_tax")
    private Sequence seqOrdInvoiceTax;
    
    private static final String MODULE = OrdInvoiceTaxSVImpl.class.getName();

    @Override
    public void saveOrdInvoiceTax(OrdInvoiceTax info) {
        info.setId(this.seqOrdInvoiceTax.nextValue());
        this.ordInvoiceTaxMapper.insert(info);
    }

    @Override
    public SOrderDetailsTax queryOrderDetailsTax(String orderId) {
        OrdInvoiceTaxCriteria oitc = new OrdInvoiceTaxCriteria();
        oitc.createCriteria().andOrderIdEqualTo(orderId);
        List<OrdInvoiceTax> oits = this.ordInvoiceTaxMapper.selectByExample(oitc);
        if(CollectionUtils.isEmpty(oits)){
            return null;
        }
        SOrderDetailsTax sod = new SOrderDetailsTax();
        ObjectCopyUtil.copyObjValue(oits.get(0), sod, null, false);
        LogUtil.info(MODULE, sod.toString());
        return sod;
    }

	@Override
	public void updateOrdInvoiceTax(OrdInvoiceTax info) {
		// TODO Auto-generated method stub
		OrdInvoiceTaxCriteria oitc = new OrdInvoiceTaxCriteria();
        oitc.createCriteria().andOrderIdEqualTo(info.getOrderId());
        this.ordInvoiceTaxMapper.updateByExampleSelective(info, oitc);
	}



}


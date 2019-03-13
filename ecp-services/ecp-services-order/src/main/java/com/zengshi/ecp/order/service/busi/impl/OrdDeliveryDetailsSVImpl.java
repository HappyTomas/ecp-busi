package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdDeliveryDetailsMapper;
import com.zengshi.ecp.order.dao.model.OrdDeliveryDetails;
import com.zengshi.ecp.order.dao.model.OrdDeliveryDetailsCriteria;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryDetailsSV;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月7日下午2:47:39 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdDeliveryDetailsSVImpl implements IOrdDeliveryDetailsSV {

    @Resource
    private OrdDeliveryDetailsMapper ordDeliveryDetailsMapper;

    @Resource(name = "seq_ord_delivery_details")
    private Sequence seqOrdDeliveryDetails;

    @Override
    public void saveOrdDeliveryDetailsList(List<OrdDeliveryDetails> ordDeliveryDetails) {
        for(OrdDeliveryDetails odd:ordDeliveryDetails){
            odd.setId(this.seqOrdDeliveryDetails.nextValue());
            this.ordDeliveryDetailsMapper.insert(odd);
        }
    }

    @Override
    public List<OrdDeliveryDetails> findByOrderSubId(String orderId, String orderSubId) {
        final OrdDeliveryDetailsCriteria oddc = new OrdDeliveryDetailsCriteria();
        oddc.createCriteria().andOrderIdEqualTo(orderId).andOrderSubIdEqualTo(orderSubId);
        return this.ordDeliveryDetailsMapper.selectByExample(oddc);
    }

    @Override
    public List<OrdDeliveryDetails> findByOrderId(String orderId) {
        final OrdDeliveryDetailsCriteria oddc = new OrdDeliveryDetailsCriteria();
        oddc.createCriteria().andOrderIdEqualTo(orderId);
        return this.ordDeliveryDetailsMapper.selectByExample(oddc);
    }

}

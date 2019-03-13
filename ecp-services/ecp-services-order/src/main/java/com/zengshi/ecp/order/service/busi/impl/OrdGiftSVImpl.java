package com.zengshi.ecp.order.service.busi.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.order.dao.mapper.busi.OrdGiftMapper;
import com.zengshi.ecp.order.dao.model.OrdGift;
import com.zengshi.ecp.order.dao.model.OrdGiftCriteria;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsGift;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdGiftSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:27:03 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version
 * @since JDK 1.6
 */
public class OrdGiftSVImpl implements IOrdGiftSV{

    @Resource
    private OrdGiftMapper ordGiftMapper;

    @Resource(name = "seq_ord_gift")
    private Sequence seqOrdGift;

    @Override
    public void saveOrdGift(OrdGift info) {
        info.setId(this.seqOrdGift.nextValue());
        this.ordGiftMapper.insert(info);
    }
    @Override
    public List<OrdGift> queryOrdGift(ROrderIdRequest info) {
        String orderId=info.getOrderId();
        OrdGiftCriteria orderGiftCriteria = new OrdGiftCriteria();
        orderGiftCriteria.createCriteria().andOrderIdEqualTo(orderId);                
        List<OrdGift> ordgifts = ordGiftMapper.selectByExample(orderGiftCriteria);
        if(CollectionUtils.isEmpty(ordgifts)){
            return null;
        }
        return ordgifts;
    }   
    @Override
    public List<SOrderDetailsGift> queryOrdGiftForDetail(ROrderIdRequest info) {
        String orderId=info.getOrderId();
        OrdGiftCriteria orderGiftCriteria = new OrdGiftCriteria();
        orderGiftCriteria.createCriteria().andOrderIdEqualTo(orderId);                
        List<OrdGift> ordgifts = ordGiftMapper.selectByExample(orderGiftCriteria);
        if(CollectionUtils.isEmpty(ordgifts)){
            return null;
        }
        List<SOrderDetailsGift> sOrderDetailsGifts=new ArrayList<SOrderDetailsGift>();
        for(OrdGift ordGift:ordgifts){
        	SOrderDetailsGift sOrderDetailsGift=new SOrderDetailsGift();
        	ObjectCopyUtil.copyObjValue(ordGift, sOrderDetailsGift, null, false);
        	sOrderDetailsGifts.add(sOrderDetailsGift);
        }
        return sOrderDetailsGifts;
    } 
}

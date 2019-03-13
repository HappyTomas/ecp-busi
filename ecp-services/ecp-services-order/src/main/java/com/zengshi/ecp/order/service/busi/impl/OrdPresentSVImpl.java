package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdPresentMapper;
import com.zengshi.ecp.order.dao.model.OrdPresent;
import com.zengshi.ecp.order.dao.model.OrdPresentCriteria;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPresentSV;
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
public class OrdPresentSVImpl implements IOrdPresentSV {
    

    @Resource
    private OrdPresentMapper ordPresentMapper;

    @Resource(name = "seq_ord_present")
    private Sequence seqOrdPresent;

    @Override
    public void saveOrdPresent(OrdPresent info) {
        info.setId(this.seqOrdPresent.nextValue());
        this.ordPresentMapper.insert(info);
    }

    @Override
    public List<OrdPresent> queryOrdPresentByOrderId(String orderId) {
        OrdPresentCriteria example = new OrdPresentCriteria();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return ordPresentMapper.selectByExample(example);
    }

    @Override
	public List<OrdPresent> queryCoupByOrderId(String orderId) {
		OrdPresentCriteria example = new OrdPresentCriteria();
        example.createCriteria().andOrderIdEqualTo(orderId).andCouponCntIsNotNull().andCouponTypeIdIsNotNull();
        return ordPresentMapper.selectByExample(example);
	}
}

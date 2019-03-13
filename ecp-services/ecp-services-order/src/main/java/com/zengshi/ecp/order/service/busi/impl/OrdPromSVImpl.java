package com.zengshi.ecp.order.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdPromMapper;
import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dao.model.OrdPromCriteria;
import com.zengshi.ecp.order.dao.model.OrdPromCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPromSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;
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
public class OrdPromSVImpl implements IOrdPromSV {

    @Resource
    private OrdPromMapper ordPromMapper;

    @Resource(name = "seq_ord_prom")
    private Sequence seqOrdProm;

    @Override
    public void saveOrdProm(OrdProm info) {
        info.setId(this.seqOrdProm.nextValue());
        this.ordPromMapper.insert(info);
    }

    @Override
    public OrdProm queryOrdProm(SBaseAndSubInfo sBaseAndSubInfo) {
        OrdPromCriteria op = new OrdPromCriteria();
        Criteria  ca = op.createCriteria();
        if(sBaseAndSubInfo.getOrderId() != null){
            ca.andOrderIdEqualTo(sBaseAndSubInfo.getOrderId());
        }
        if(sBaseAndSubInfo.getOrderSubId() != null){
            ca.andOrderSubIdEqualTo(sBaseAndSubInfo.getOrderSubId());
        }
        
        List<OrdProm> OrdProms = this.ordPromMapper.selectByExample(op);
        if (CollectionUtils.isEmpty(OrdProms)) {
            return null;
        }
        return OrdProms.get(0);
    }

}

package com.zengshi.ecp.order.service.busi.impl;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.order.dao.mapper.busi.ThingLockMapper;
import com.zengshi.ecp.order.dao.model.ThingLock;
import com.zengshi.ecp.order.dao.model.ThingLockCriteria;
import com.zengshi.ecp.order.service.busi.interfaces.IThingLockSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;

public class ThingLockSVImpl implements IThingLockSV {
    
    
    @Resource
    private ThingLockMapper thingLockMapper;
    
    private static final String MODULE = ThingLockSVImpl.class.getName();

    @Override
    public void addThingLock(ThingLock thingLock) {
        LogUtil.debug(MODULE, "加锁："+JSON.toJSONString(thingLock));
        try {
            this.thingLockMapper.insert(thingLock);
        } catch (DataAccessException e) {
            e.printStackTrace();
            LogUtil.debug(MODULE, "加锁："+thingLock.getId() +":已有类型为:"+thingLock.getType()+"在执行");
        }
        ThingLockCriteria criteria = new ThingLockCriteria();
        criteria.setSuffix("for update");
        criteria.createCriteria().andIdEqualTo(thingLock.getId())
                                 .andTypeEqualTo(thingLock.getType());
        this.thingLockMapper.selectByExample(criteria);
    }

    @Override
    public void removeThingLock(ThingLock thingLock) {
        ThingLockCriteria example = new ThingLockCriteria();
        example.createCriteria().andIdEqualTo(thingLock.getId())
                                 .andTypeEqualTo(thingLock.getType());
        this.thingLockMapper.deleteByExample(example);
        LogUtil.debug(MODULE, "解锁："+JSON.toJSONString(thingLock));
    }

}


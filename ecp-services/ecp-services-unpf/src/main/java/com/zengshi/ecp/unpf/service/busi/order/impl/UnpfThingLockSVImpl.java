package com.zengshi.ecp.unpf.service.busi.order.impl;


import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfThingLockMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfThingLock;
import com.zengshi.ecp.unpf.dao.model.UnpfThingLockCriteria;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfThingLockSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;

public class UnpfThingLockSVImpl implements IUnpfThingLockSV {
    
    
    @Resource
    private UnpfThingLockMapper unpfThingLockMapper;
    
    private static final String MODULE = UnpfThingLockSVImpl.class.getName();

    @Override
    public void addThingLock(UnpfThingLock thingLock) {
        LogUtil.debug(MODULE, "加锁："+JSON.toJSONString(thingLock));
        try {
            this.unpfThingLockMapper.insert(thingLock);
        } catch (DataAccessException e) {
            e.printStackTrace();
            LogUtil.debug(MODULE, "加锁："+thingLock.getId() +":已有类型为:"+thingLock.getType()+"在执行");
        }
        UnpfThingLockCriteria criteria = new UnpfThingLockCriteria();
        criteria.setSuffix("for update");
        criteria.createCriteria().andIdEqualTo(thingLock.getId())
                                 .andTypeEqualTo(thingLock.getType());
        this.unpfThingLockMapper.selectByExample(criteria);
    }

    @Override
    public void removeThingLock(UnpfThingLock thingLock) {
        UnpfThingLockCriteria example = new UnpfThingLockCriteria();
        example.createCriteria().andIdEqualTo(thingLock.getId())
                                 .andTypeEqualTo(thingLock.getType());
        this.unpfThingLockMapper.deleteByExample(example);
        LogUtil.debug(MODULE, "解锁："+JSON.toJSONString(thingLock));
    }

}


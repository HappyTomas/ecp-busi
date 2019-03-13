package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.ThingLock;

public interface IThingLockSV {

    /** 
     * addThingLock:添加业务操作锁. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void addThingLock(ThingLock thingLock);
    
    /** 
     * removeThingLock:删除业务操作锁. <br/> 
     * @author cbl 
     * @param rhingLock 
     * @since JDK 1.6 
     */ 
    public void removeThingLock(ThingLock thingLock);
}


package com.zengshi.ecp.unpf.service.busi.order.interfaces;


import com.zengshi.ecp.unpf.dao.model.UnpfThingLock;

public interface IUnpfThingLockSV {

    /** 
     * addThingLock:添加业务操作锁. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void addThingLock(UnpfThingLock thingLock);
    
    /** 
     * removeThingLock:删除业务操作锁. <br/> 
     * @author cbl 
     * @param thingLock
     * @since JDK 1.6 
     */ 
    public void removeThingLock(UnpfThingLock thingLock);
}


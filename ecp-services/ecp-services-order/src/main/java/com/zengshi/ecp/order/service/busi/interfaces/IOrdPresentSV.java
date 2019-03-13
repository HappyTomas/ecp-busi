package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdPresent;



/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:25:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdPresentSV {
    /**
     * 
     * saveOrdPresent:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void saveOrdPresent(OrdPresent info);
    
    /** 
     * queryOrdPresentByOrderId:根据订单号查询数据. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdPresent> queryOrdPresentByOrderId(String orderId);
    
    /** 
     * queryOrdPresentByOrderId:根据订单号优惠券数据. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdPresent> queryCoupByOrderId(String orderId);
    

}


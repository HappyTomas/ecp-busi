package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdProm;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;



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
public interface IOrdPromSV {
    /**
     * 
     * saveOrdProm:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void saveOrdProm(OrdProm info);
    
    /** 
     * queryOrdProm:根据订单号或子订单号查询活动信息. <br/> 
     * @author cbl 
     * @return 
     * @since JDK 1.6 
     */ 
    public OrdProm queryOrdProm(SBaseAndSubInfo sBaseAndSubInfo);

}


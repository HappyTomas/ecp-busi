package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdTrack;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTrack;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月17日下午4:25:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdTrackSV {
    /** 
     * saveOrdTrack:保存对象信息. <br/> 
     * @author cbl 
     * @param ordTrack 
     * @since JDK 1.6 
     */ 
    public void saveOrdTrack(OrdTrack ordTrack);
    
    /** 
     * queryOrderDetailsTrack:订单详情查询订单跟踪相关信息. <br/> 
     * @author cbl 
     * @param rOrderDetailsRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public List<SOrderDetailsTrack> queryOrderDetailsTrack(String orderId);
}


package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdGift;
import com.zengshi.ecp.order.dubbo.dto.ROrderGiftResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrderIdRequest;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsGift;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;



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
public interface IOrdGiftSV {
    /**
     * 
     * saveOrdGift:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void saveOrdGift(OrdGift info);
    /**
     * 
     * queryOrdGift:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author linwei 
     * @param info
     * @return 
     * @since JDK 1.6
     */
    public List<OrdGift> queryOrdGift(ROrderIdRequest info);
    
    public List<SOrderDetailsGift> queryOrdGiftForDetail(ROrderIdRequest info);
}


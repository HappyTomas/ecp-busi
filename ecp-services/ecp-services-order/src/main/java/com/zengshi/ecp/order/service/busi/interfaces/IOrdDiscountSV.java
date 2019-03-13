package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdDiscount;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDiscount;



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
public interface IOrdDiscountSV {
    /**
     * 
     * saveOrdDiscount:(这里用一句话描述这个方法的作用). <br/> 
     * @author linwei 
     * @param info 
     * @since JDK 1.6
     */
    public void saveOrdDiscount(OrdDiscount info);
    
    /** 
     * queryOrderDetailsDiscount:订单详情查询优惠相关信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public SOrderDetailsDiscount queryOrderDetailsDiscount(String orderId);

    /**
     * queryOrderDetailsDiscount:订单详情查询优惠相关信息. <br/>
     * @author cbl
     * @param orderId, orderSubId
     * @return
     * @since JDK 1.6
     */
    public boolean queryOrdSubIsDiscount(String orderId, String orderSubId);

    /** 
     * queryOrdDiscountByOrderId:查询订单优惠信息. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdDiscount> queryOrdDiscountByOrderId(String orderId);
    /**
     * 查询某个优惠类型的减免信息
     * @param orderId
     * @param discountType
     * @return
     */
    public List<OrdDiscount> queryOrdDiscount(String orderId,List<String> discountTypes);
    /**
     * 查询某个优惠类型的减免信息
     * @param orderId
     * @param discountType
     * @return
     */
    public Long queryOrdDiscountMoney(String orderId,List<String> discountTypes);    

}


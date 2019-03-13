package com.zengshi.ecp.order.service.busi.interfaces.pay;
import java.util.List;

import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午2:58:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */
public interface IPayWaySV {
    /**
     * 
     * getPayWay:获取支付通道信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payWay
     * @return 
     * @since JDK 1.6
     */
    public List<PayWay> getPayWay(PayWayRequest payWay);
    
    /**
     * 
     * getPayWayById:根据支付通道编码获取支付通道实体. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payWay
     * @return 
     * @since JDK 1.6
     */
    public PayWay getPayWayById(String payWay);
    
    /**
     * 
     * getPayWayByShopId:根据店铺编码获取店铺支付通道配信息. <br/> 
     * TODO shopId 不能为空.<br/> 
     * TODO 根据店铺编码获取T_PAY_SHOP_CFG表的信息，得到支付通道字段组装list作为条件去查询开启的支付通道信息.<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payWay
     * @return 
     * @since JDK 1.6
     */
    public List<PayWay> getPayWayByShopId(PayWayRequest payWay);
    
    /**
     * 
     * addPayWay:添加支付通道信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payWay 
     * @since JDK 1.6
     */
    public void addPayWay(PayWayRequest payWay);
    
    /**
     * 
     * editPayWay:更新支付通道配置信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payWay 
     * @since JDK 1.6
     */
    public void editPayWay(PayWayRequest payWay);
    
    /**
     * 
     * handlePaySucc:支付回调出来方法. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param paySuccInfo 
     * @since JDK 1.6
     */
    public void savehandlePaySucc(PaySuccInfo paySuccInfo);
    
    /**
     * 
     * queryOrderByShopIdPage:(分页查询支付方式). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author lwy 
     * @param payWayRequest
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<PayWayResponse> queryPayWayPage(PayWayRequest payWayRequest);

}
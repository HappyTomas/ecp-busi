package com.zengshi.ecp.order.service.busi.interfaces.pay;
import java.util.List;

import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dubbo.dto.pay.PayShopCfgRequest;
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
public interface IPayShopCfgSV {
    
    /**
     * 
     * getCfgByPayWay:通过支付通道编码获取支付通道信息. <br/> 
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
    public List<PayShopCfg> getCfgByPayWay(String payWay);
    
    /**
     * 
     * getCfgByShopId:根据店铺编码获取店铺支付通道信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    public List<PayShopCfg> getCfgByShopId(long shopId);
    
    /**
     * 
     * getCfgByShopIdAndPayWay:根据店铺编码和支付通道编码获取店铺配置信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param shopId
     * @param payWay
     * @return 
     * @since JDK 1.6
     */
    public PayShopCfg getCfgByShopIdAndPayWay(Long shopId,String payWay);
    
    /**
     * 
     * editCfg:编辑店铺支付通道配置信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param shopId 
     * @since JDK 1.6
     */
    public void editCfg(PayShopCfgRequest cfg);
    
    /**
     * 
     * addCfg:添加店铺支付通道配置信息. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param shopId 
     * @since JDK 1.6
     */
    public void addCfg(PayShopCfgRequest cfg);
    
}
package com.zengshi.ecp.order.service.busi.interfaces.pay.audit;

import java.sql.Timestamp;

import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.HongpayAuditResponse;

public interface IAuditHongpaySV {
    /**
     * 
     * saveAuditInfo:保存对账总表，明细表，已处理对账文件表，控制事物. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payShopCfg
     * @param totleAuditResponse
     * @param qsDate 
     * @since JDK 1.6
     */
    public void saveAuditInfo(PayShopCfg payShopCfg,HongpayAuditResponse auditResponse,Timestamp qsDate);
}


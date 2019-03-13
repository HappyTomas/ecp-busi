package com.zengshi.ecp.order.service.busi.interfaces.pay.audit;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.order.dao.model.AuditHongpayDetail;



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
public interface IAuditHongpayDetailSV {
    /**
     * saveAuditDailySum:保存鸿支付对账明细. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param log 
     * @since JDK 1.6
     */
    public void saveAuditHongpayDetail(AuditHongpayDetail auditHongpayDetail);
    
    /**
     * 
     * getAuditAlipayDetailByCheckDate:根据对账日期获取对账明细. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param qsDate
     * @return 
     * @since JDK 1.6
     */
    public List<AuditHongpayDetail> getAuditHongpayDetailByCheckDate(Timestamp qsDate);
    
    /**
     * 
     * deleteAuditAlipayDetail:删除鸿支付对账明细表，并插入对账明细历史表. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param id 
     * @since JDK 1.6
     */
    public void deleteAuditHongpayDetail(long id);
}
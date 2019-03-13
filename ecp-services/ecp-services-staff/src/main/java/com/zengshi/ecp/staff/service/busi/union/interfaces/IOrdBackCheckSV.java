package com.zengshi.ecp.staff.service.busi.union.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackCheckReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 订单退款退货查重服务<br>
 * Date:2016年5月24日下午2:01:58  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IOrdBackCheckSV {

    /**
     * 
     * checkOrdBack:(查询该次订单退款退货操作是否已经处理过。). <br/> 
     * 
     * @author huangxl5 
     * @param backId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean checkOrdBack(long backId) throws BusinessException;
    
    /**
     * 
     * saveOraBackCheck:(保存订单退款退货处理信息). <br/> 
     * 
     * @author huangxl5 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveOraBackCheck(OrderBackCheckReqDTO req) throws BusinessException;
}


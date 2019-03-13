package com.zengshi.ecp.staff.service.busi.acct.interfaces;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;

/**
 * 
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 提供于订单对资金帐户使用情况的公共接口<br>
 * Date:2015年10月20日下午2:42:34  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAcctToOrderSV {
    
    /**
     * 
     * checkOrdCart:(校验订单购物车所使用的相关资金帐户余额是否够). <br/> 
     * 
     * @author linby 
     * @param ordCarts
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean checkOrdCart(ROrdCartsCommRequest ordCarts) throws BusinessException;
    
    /**
     * 
     * saveAcctUseRollBack:(资金帐户使用的回退机制/订单取消的回退机制). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveAcctUseRollBack(ROrdCartsCommRequest ordCarts) throws BusinessException;
    
    /**
     * 
     * saveAcctUseForOrderRefund:(资金账户使用：订单退款). <br/> 
     * 
     * @author huangxl5
     * @param ordCarts
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveAcctUseForOrderRefund(OrderBackSubReqDTO orderReq) throws BusinessException;
    
    /**
     * 
     * saveAcctUseForOrderBack:(资金账户使用：订单退货同意退款). <br/> 
     * 当为最后一笔退货时，不能直接按比例计算退款额，需要用总的额度减去已退的额度
     * @author huangxl5
     * @param orderReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveAcctUseForOrderBack(OrderBackSubReqDTO orderReq) throws BusinessException;

    /**
     * 
     * selAcctByBackOrder:(通过主订单id查询此次退货需要返还的资金). <br/> 
     * 入参：orderId、退货金额比例scale、是否最后一笔退货lastFlag
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public OrderAcctMainResDTO<OrderAcctSubResDTO> selAcctByBackOrder(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException;
}


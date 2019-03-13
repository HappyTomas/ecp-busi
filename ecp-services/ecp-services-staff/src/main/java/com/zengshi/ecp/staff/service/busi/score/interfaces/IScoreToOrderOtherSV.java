/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IScoreToOrderSV.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.score.interfaces 
 * Date:2015年9月30日下午3:04:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.score.interfaces;

import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFrozenBackReqDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分配合订单退货、退款改造
 * 主要是支持了子订单可部分退货<br>
 * Date:2015年9月30日下午3:04:01  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public interface IScoreToOrderOtherSV {
    public int saveScoreRollBack(CustInfoReqDTO custinfo, OrderInfoReqDTO orderinfo);
    
    /**
     * 
     * checkOrdCart:(校验订单购物车所使用的积分是否够). <br/> 
     * 
     * @author huangxl 
     * @param ordCarts
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public boolean checkOrdCart(ROrdCartsCommRequest ordCarts) throws BusinessException;
    
    /**
     * 
     * saveScoreUseRollBack:(积分使用的回退机制/订单取消的回退机制). <br/> 
     * 
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveScoreUseRollBack(ScoreExchangeReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveScoreUseForOrderRefund:(积分使用-->订单退款). <br/> 
     * 订单退款时，把使用的积分返回：根据主订单，从消费明细中查出所有使用的积分，返还。
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreUseForOrderRefund(OrderBackSubReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveScoreAddForOrderRefund:(积分获得-->订单退款). <br/> 
     * 订单退款时，把获得的积分，扣减：根据主订单查得所有获得的积分，相应扣减。
     * 当帐户积分不足时，根据传入的标识，决定是否把账户扣为负的。
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreAddForOrderRefund(OrderBackSubReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveScoreFrozenForOrderBack:(同意退货：冻结子订单赠送的积分). <br/> 
     * 包括：1、子订单积分；
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long saveScoreFrozenForOrderBackSub(OrderBackSubReqDTO req) throws BusinessException;
    
    /**
     * 
     * saveScoreFrozenForOrderBack:(同意退货：冻结子订单平摊主订单赠送的积分). <br/> 
     * 包括：1、主订单获得的积分按比例平摊到子订单的积分部分。
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long saveScoreFrozenForOrderBackMain(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException;
    
    /**
     * 
     * saveScoreAddForOrderBack:(退货同意退款，解冻当时冻结的积分，并扣除). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreAddForOrderBackMain(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException;
    
    /**
     * 
     * saveScoreAddForOrderBack:(退货同意退款，解冻当时冻结的积分，并扣除). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreAddForOrderBackSub(OrderBackSubReqDTO req) throws BusinessException;
    
    /**
     * 
     * selTotalScoreByOrderId:(通过主订单id获取一共获得了多少积分). <br/> 
     * 
     * @author huangxl5
     * @param orderId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long selTotalScoreByOrderId(String orderId) throws BusinessException;
    
    /**
     * 
     * selTotalScoreByBackSubOrder:(统计此次退货的所有子订单，一共需要扣除多少积分，包含主订单获得的平摊积分). <br/> 
     * 
     * @author huangxl5
     * @param orderReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long selTotalScoreByBackOrderSub(OrderBackSubReqDTO req) throws BusinessException;
    
    /**
     * 
     * selTotalScoreByBackSubOrder:(统计此次退货的所有子订单，一共需要扣除多少积分，包含主订单获得的平摊积分). <br/> 
     * 
     * @author huangxl5
     * @param orderReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long selTotalScoreByBackOrderMain(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException;
    
    /**
     * 
     * saveScoreFrozenBack:(订单冻结积分回退操作). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreFrozenBack(ScoreFrozenBackReqDTO req) throws BusinessException;
}


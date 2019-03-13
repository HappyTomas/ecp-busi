package com.zengshi.ecp.staff.service.busi.union.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFrozenBackReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 用户域对外统一服务接口<br>
 * Date:2015-9-30下午7:13:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface IStaffUnionSV {

    /**
     * 
     * saveStaffRelInfoForOrder:(用户域统一接口：下订单). <br/> 
     * 资金账户使用sv及积分使用sv组合接口
     * @author huangxl 
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public boolean saveStaffRelInfoForOrder(ListReqDTO<ScoreExchangeReqDTO> scoreReq,ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException;
    
    /**
     * 
     * saveStaffRelInfoForOrderRollBack:(用户域统一接口：下订单，异常回调). <br/> 
     * 资金账户使用sv及积分使用sv组合接口异常时回调
     * @author huangxl 
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public boolean saveStaffRelInfoForOrderRollBack(ListReqDTO<ScoreExchangeReqDTO> scoreReq,ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException;
    
    /**
     * 
     * saveStaffRelInfoForPay:(用户域统一接口：支付回调). <br/> 
     * 会员成长值增加sv、积分增加sv
     * @author huangxl 
     * @param scoreReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public boolean saveStaffRelInfoForPay(PayQuartzInfoRequest dto) throws BusinessException;
    
    /**
     * 
     * findCustOrAdminByStaffId:(通过staffId获取用户的staffCode). <br/> 
     * 先判断是否为会员表custInfo，如果查到结果，则取出staffCode返回
     * 如果不是会员表，则查询管理员authStaffAdmin，如果查到结果，则staffCode统一叫：管理员
     * @author huangxl 
     * @param StaffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustInfoResDTO findCustOrAdminByStaffId(Long staffId) throws BusinessException;
    
    /**
     * 
     * findCustInfo:(通过唯一条件，获取会员信息). <br/> 
     * 查询条件只能是：
     * staffCode、email、nickname、serialNumber
     * @author huangxl 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustInfoResDTO findCustInfo(CustInfoReqDTO req) throws BusinessException;
    
    /**
     * 
     * findScoreInfoByStaffId:(根据会员id，获取会员的积分账户信息). <br/> 
     * 
     * @author huangxl5
     * @param staffId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreInfoResDTO findScoreInfoByStaffId(Long staffId) throws BusinessException;
    
    /**
     * 
     * saveScoreRelForOrderRefund:(订单退款，积分，资金账户回退操作). <br/> 
     * 订单退款后，已使用的积分返回，已获得的积分扣减掉，使用的资金账户返还
     * @author huangxl5
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreAcctForOrderRefund(OrderBackSubReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveScoreFrozenForOrderBack:(同意退货：冻结子订单赠送的积分). <br/> 
     * 包括：1、子订单积分；2、主订单获得的积分按比较平摊到子订单的积分部分。
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long saveScoreFrozenForOrderBack(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException;
    
    /**
     * 
     * saveScoreAddForOrderBack:(退货同意退款). <br/> 
     * 1、返还已使用的积分
     * 2、退货同意退款时，解冻之前冻结的积分，并把积分扣除
     * 3、返还已使用的资金账户
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean saveScoreAcctForOrderBack(OrderBackMainReqDTO<OrderBackSubReqDTO> req,OrderBackSubReqDTO orderReq) throws BusinessException;
    
    
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
     * selTotalScoreByBackSubOrder:(统计此次退货的所有子订单，一共需要扣除多少积分). <br/> 
     * 
     * @author huangxl5
     * @param orderReq
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long selTotalScoreByBackSubOrder(OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException;
    
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


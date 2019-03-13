package com.zengshi.ecp.staff.service.busi.acct.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AcctTrade;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月13日下午3:07:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAcctTradeSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveAcctTrade:(新增资金账户交易记录). <br/> 
     * 
     * @author linby 
     * @param acctTrade AcctTrade
     * @since JDK 1.6
     */
    public long saveAcctTrade(AcctTrade acctTrade) throws BusinessException;
    
    /**
     * 
     * useShopCaptialTraded:(使用店铺资金进行交易). <br/> 
     * 
     * @author linby 
     * @param transactionContentDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void useShopCaptialTraded(TransactionContentReqDTO transactionContentDto) throws BusinessException;
    
    /**
     * 
     * decreaseAcctBalance:(减少账户余额). <br/> 
     * 
     * @author linby 
     * @param transactionContentDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void decreaseAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException;

    /**
     * 
     * increaseAcctBalance:(增加账户余额). <br/> 
     * 
     * @author linby 
     * @param transactionContentDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void increaseAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAcctBalance:(资金变动服务). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAcctBalance:(资金变动服务,批量方法). <br/> 
     * 
     * @author linby 
     * @param reqList
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctBalance(ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException;
    
    /**
     * 
     * listAcctTradeByAcct:(通过账户信息查询交易信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctTradeResDTO> listAcctTradeByAcct(AcctTradeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listAcctTrade:(通过既定条件查询交易信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctTradeResDTO> listAcctTrade(AcctTradeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * executeOrderRefund:(资金账户中的订单退款). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeOrderRefund(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * executeOrderRefund:(资金账户中的订单退货). <br/> 
     * 
     * @author huangxl 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeOrderBack(OrderBackSubReqDTO orderReq) throws BusinessException;
    
    /**
     * 
     * sumAcctTrade:(统计交易金额之和). <br/> 
     * 
     * @author huangxl5
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long sumAcctTrade(AcctTradeReqDTO reqDto) throws BusinessException;
    
}


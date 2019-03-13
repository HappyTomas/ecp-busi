package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;

public class AcctTradeSVImplTest extends EcpServicesTest {

    @Resource
    private IAcctTradeSV acctTradeSV;
    
    @Test
    public void testUpdateAcctBalance(){
        TransactionContentReqDTO reqDto = new TransactionContentReqDTO();
        reqDto.setStaffId(1L);
        reqDto.setShopId(1234L);
        reqDto.setOrderId("3");
        reqDto.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);
        reqDto.setAcctType(StaffConstants.Acct.ACCT_TYPE_SHOPRETURN);
        reqDto.setDebitCredit(StaffConstants.Trade.ACCT_DC_SPEND);
        reqDto.setTradeMoney(100L);
        acctTradeSV.updateAcctBalance(reqDto);
    }
    
    @Test
    public void testExecuteOrderRefund(){
        TransactionContentReqDTO reqDto = new TransactionContentReqDTO();
        reqDto.setStaffId(1L);
        reqDto.setOrderId("3");
        acctTradeSV.executeOrderRefund(reqDto);
    }
    
}


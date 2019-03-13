package com.zengshi.ecp.staff.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;

public class AcctManageRSVImplTest extends EcpServicesTest {
    
    @Resource
    private IAcctManageRSV acctManageRSV;
    
    @Test
    public void testListAccountByShop() {
        ShopRelatedAcctsReqDTO reqDto = new ShopRelatedAcctsReqDTO();
        
        PageResponseDTO<AcctInfoResDTO> res = acctManageRSV.listAccountByShop(reqDto);
        System.out.println(res!=null?res.getCount():"??");
    } 
    
    @Test
    public void testQueryAcctWithOrderByStaff(){
        TransactionContentReqDTO reqDto = new TransactionContentReqDTO();
        reqDto.setStaffId(1L);
        reqDto.setShopId(1234L);
        reqDto.setOrderMoney(100L);
        List<AcctInfoResDTO> ls = acctManageRSV.queryAcctWithOrderByStaff(reqDto);
        System.out.println(ls.toString());
    }
}


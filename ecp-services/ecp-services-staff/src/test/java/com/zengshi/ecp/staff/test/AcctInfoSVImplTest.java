package com.zengshi.ecp.staff.test;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dao.model.AcctInfo;
import com.zengshi.ecp.staff.dao.model.AcctType;
import com.zengshi.ecp.staff.dao.model.AcctTypeKey;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.paas.utils.ObjectCopyUtil;

public class AcctInfoSVImplTest extends EcpServicesTest {

    @Resource
    private IAcctInfoSV acctInfoSV;
    
    @Resource
    private IShopMgrSV shopMgrService; //店铺管理服务
    
    @Test
    public void testSaveAcctInfo() {
        /*
        Date sDate = new Date();
        long start = System.currentTimeMillis();
        long i = 1;
        String output = "";
        while(i<30){
            AcctInfo acctInfo = new AcctInfo();
            acctInfo.setStaffId(i);
            acctInfo.setAcctType("001");
            acctInfo.setAdaptType("03");
            acctInfo.setShopId(i);
            acctInfo.setTotalMoney(10000L+i+i);
            acctInfo.setStatus("1");
            acctInfo.setFreezeMoney(500L+i);
            acctInfo.setBalance(9500L+i);
            acctInfo.setCreateStaff(0L);
            acctInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            long id = acctInfoSV.saveAcctInfo(acctInfo);
            output += String.valueOf(i);
            System.out.println("打印：" + id); 
            i++;
            
        }
        long end = System.currentTimeMillis();
        System.out.println("-----------------\n\n"+output);
        System.out.println("开始" + sDate);
        System.out.println("结束" + new Date() + "\n" + (end-start));
        
//        AcctInfo info = acctInfoSV.findAcctInfoById(10L);
//        System.out.println("输出");
//        System.out.println(info.toString());
        */
        AcctInfo acctInfo = new AcctInfo();
        acctInfo.setStaffId(808L);
        acctInfo.setAcctType("002");
        acctInfo.setAdaptType("03");
        acctInfo.setShopId(35L);
        acctInfo.setTotalMoney(1000000L);
        acctInfo.setStatus("1");
        acctInfo.setFreezeMoney(50000L);
        acctInfo.setBalance(950000L);
        acctInfo.setCreateStaff(0L);
        acctInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        long id = acctInfoSV.saveAcctInfo(acctInfo);
    }
    
    @Test
    public void testShopSV(){
        ShopInfo si = shopMgrService.findShopByShopID(10L);
        System.out.println(si==null?si:si.toString());
    }
    
    @Test
    public void testAcctType(){
        
        AcctTypeKey key = new AcctTypeKey();
        key.setAcctType("001");
        key.setAdaptType("03");
        key.setShopId(10L);
        AcctTypeReqDTO atDTO = new AcctTypeReqDTO();
        ObjectCopyUtil.copyObjValue(key, atDTO, null, false);
        AcctTypeResDTO at = acctInfoSV.findAcctTypeByKey(atDTO);
        System.out.println(at==null?at:at.toString());
        
    }
    
    @Test
    public void testAcctTypeSave(){
        /*
        long start = System.currentTimeMillis();
        long i = 1;
        while(i<100){
            AcctType acctType = new AcctType();
            acctType.setAcctType("001");
            acctType.setAdaptType("03");
            acctType.setShopId(i);
            acctType.setDeductOrderRatio(new BigDecimal(70l));
            acctInfoSV.saveAcctType(acctType);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("-----------------\n\n");
        System.out.println("开始" + new Date(start));
        System.out.println("结束" + new Date(end) + "\n" + (end-start));
        */
        AcctType acctType = new AcctType();
        acctType.setAcctType("001");
        acctType.setAdaptType("03");
        acctType.setShopId(35L);
        acctType.setDeductOrderRatio(new BigDecimal(70l));
        AcctTypeReqDTO atDTO = new AcctTypeReqDTO();
        ObjectCopyUtil.copyObjValue(acctType, atDTO, null, false);
        acctInfoSV.saveAcctType(atDTO);
    }
    

}


package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemReqDTO;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthManageSV;

public class DataAuthManageImplTest extends EcpServicesTest{

    @Resource
    private IDataAuthManageSV dataAuthManageSV;
    
    @Test
    public void saveDataRuleItemTest() {
        
        long start = System.currentTimeMillis();
        long i = 20041;
        while(i<20046){
            DataRuleItemReqDTO reqDto = new DataRuleItemReqDTO();
            reqDto.setName("数据属性_"+i);
            reqDto.setFuncId(i);
            dataAuthManageSV.saveDataRuleItem(reqDto);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(i+";执行时间：" + (end-start));
    }
    
}


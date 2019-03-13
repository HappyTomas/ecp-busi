package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dao.model.ShopStaff2Group;
import com.zengshi.ecp.staff.dao.model.ShopStaffGroup;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopStaffGroupSV;

public class ShopStaffGroupSVImplTest extends EcpServicesTest {
    
    @Resource
    private IShopStaffGroupSV sGroupSV;
    
    @Test
    public void testSaveShopStaffGroup(){
        
        long start = System.currentTimeMillis();
        long i = 1L;
        while (i<5) {
            ShopStaffGroup save = new ShopStaffGroup();
            save.setGroupName("1234店铺会员分组"+i);
            save.setRemark("1234店铺会员分组"+i+"的描述说明");
            save.setShopId(1234L);
            save.setCreateStaff(0L);
            sGroupSV.saveShopStaffGroup(save);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("\n\n"+(end-start));
    }
    
    @Test
    public void testSaveShopStaff2Group(){
        
        long i = 32L;
        
        while (i<40) {
            ShopStaff2Group rel = new ShopStaff2Group();
            rel.setCreateStaff(0L);
            rel.setStaffId(1L);
            rel.setGroupId(i);
            sGroupSV.saveShopStaff2Group(rel);
            i++;
        }
    }
    
    @Test
    public void testListShopStaffGroup(){
        ShopStaffGroupReqDTO  reqDto = new ShopStaffGroupReqDTO();
        reqDto.setShopId(1234L);
        sGroupSV.listShopStaffGroup(reqDto);
    }
    
    @Test
    public void testQueryShopStaffGroup(){
        ShopStaffGroupReqDTO dto = new ShopStaffGroupReqDTO();
        dto.setShopId(1234L);
        dto.setStaffId(1L);
        String str = sGroupSV.queryShopStaffGroup(dto);
        System.out.println(str);
    }
}


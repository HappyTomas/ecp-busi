package com.zengshi.ecp.staff.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;

public class ShopInfoRSVImplTest extends EcpServicesTest{

    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopRSV;
    
    @Resource
    private IShopMgrSV shopsv;
    
    @Test
    public void listShopInfoTest()
    {
        try {
            long start = System.currentTimeMillis();
//            shopRSV.listShopInfo();
            long end = System.currentTimeMillis();
            System.out.println("=======This func excute :"+(end-start)/1000.00+"'s=======");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @Test
    public void listShopInfoTest2()
    {
        try {
            
            ShopSelectReqDTO sCond = new ShopSelectReqDTO();
//            sCond.setCompanyID(13115924273L);
               // sCond.setShopID(11L);
//            sCond.setStaffID(234234L);
//            sCond.setStaffPhone(223235L);
            sCond.setShopName("wo");
            long start = System.currentTimeMillis();
            shopRSV.listShopInfoByCond(sCond);
            long end = System.currentTimeMillis();
            System.out.println("=======This func excute :"+(end-start)/1000.00+"'s=======");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @Test
    public void updateShopInfoTest()
    {
        try {
            long start = System.currentTimeMillis();
            ShopInfoReqDTO sReqDTO = new ShopInfoReqDTO();
            sReqDTO.setId(5L);
            sReqDTO.setShopFullName("彭杰测试更新用例");
            shopRSV.updateShopInfo(sReqDTO);
            long end = System.currentTimeMillis();
            System.out.println("=======This func excute :"+(end-start)/1000.00+"'s=======");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Test
    public void findShopInfoByShopId() {
        ShopInfoResDTO dto = shopRSV.findShopInfoByShopID(6488888L);
        if(dto != null)
            System.out.println("====="+dto.toString());
        
    }
    @Test
    public void listShopInfoByCond()
    {
        
        try {
            
            ShopSelectReqDTO sCond = new ShopSelectReqDTO();
//            sCond.setCompanyID(13115924273L);
            sCond.setShopName("wo");
            sCond.setPageNo(2);
            sCond.setPageSize(2);
            long start = System.currentTimeMillis();
            PageResponseDTO<ShopInfoResDTO> page = shopRSV.listShopInfoByCond(sCond);
            long end = System.currentTimeMillis();
            System.out.println("=======This func excute :"+(end-start)/1000.00+"'s=======");
            System.out.println("=======Get Count : "+page.getCount());
            for(ShopInfoResDTO dto: page.getResult())
            {
                System.out.println("======="+dto.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void ShopExpressTest()
    {
        Long shopId = 4L;
        Map<String, ShopExpressResDTO> resultDtos = shopRSV.listShopExpress(shopId);
        
        Map<Long, String> map= shopRSV.listExpress(48L);
        
        Iterator<Entry<Long, String>> it = map.entrySet().iterator();
//        if(!CollectionUtils.isEmpty(map))
//        {
//            System.out.println("=======物流公司ID："+map);
//        }
//        
        while(it.hasNext())         
        {
            Entry<Long, String> mEntry = it.next();
            System.out.println("=======物流公司ID："+mEntry.getKey()+"=======物流公司名称："+mEntry.getValue());
        }
    }
}


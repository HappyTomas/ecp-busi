/** 
 * Project Name:ecp-services-staff-server 
 * File Name:ShopShipperRSVImplTest.java 
 * Package Name:com.zengshi.ecp.staff.test 
 * Date:2015年9月17日上午9:52:08 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月17日上午9:52:08  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public class ShopShipperRSVImplTest extends EcpServicesTest {
    
    @Resource
    private IShopShipperAddrRSV shopShipperAddrRSV;
    
    @Test
    public void savaTest()
    {
        ShopShipperReqDTO record = new ShopShipperReqDTO();
        record.setShopId(808L);
        record.setShipperName("林必耀");
        record.setShipperPhone("13115924273");
        record.setShipperAddress("福建省福州市湖东路亚信");
        record.setShipperMobile("0591-99999999");
        
        
        shopShipperAddrRSV.saveShipperAddr(record);
    }
    @Test
    public void selectTest()
    {
        //设置默认发货地址
        ShopShipperReqDTO dto = new ShopShipperReqDTO();
        dto.setShopId(808L);
        dto.setId(4L);
        shopShipperAddrRSV.installShipperAddr(dto);
        
        shopShipperAddrRSV.installReturnBack(dto);

    }

    @Test
    public void selectShipperAddrTest()
    {
        ShopShipperReqDTO dto = new ShopShipperReqDTO();
        dto.setShopId(808L);
        ShopShipperResDTO shipperAddr = shopShipperAddrRSV.selectShipperAddr(dto);
        if(shipperAddr != null)
        {
            System.out.println("=====获取到店铺["+dto.getShopId()+"]的默认发货地址："+shipperAddr.toString());
        }
        else {
            System.out.println("=======没有获取到店铺默认发货地址========");
        }
        ShopShipperResDTO returnAddrDto = shopShipperAddrRSV.selectReturnAddr(dto);
        
        if(returnAddrDto != null)
        {
            System.out.println("=====获取到店铺["+dto.getShopId()+"]的默认退货地址："+returnAddrDto.toString());
        }
        else {
            System.out.println("=======没有获取到店铺默认退货地址========");
        }
    }
}


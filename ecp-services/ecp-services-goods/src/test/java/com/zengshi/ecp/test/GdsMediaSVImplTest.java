package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class GdsMediaSVImplTest extends EcpServicesTest {

    @Resource
    private IGdsMediaRSV gdsMediaRSV;

    @Test
    public void testQueryGdsCollect() {

//        GdsMediaLibReqDTO gdsMediaLibReqDTO=new GdsMediaLibReqDTO();
//        gdsMediaLibReqDTO.setShopId(116l);
//        gdsMediaLibReqDTO.setShopName("116店铺");
//        gdsMediaRSV.saveGdsMediaLib(gdsMediaLibReqDTO);
        
        GdsMediaReqDTO gdsMediaReqDTO=new GdsMediaReqDTO();
        gdsMediaReqDTO.setMediaUuid("002");
        gdsMediaReqDTO.setShopId(1l);
        gdsMediaReqDTO.setMediaLibId(2l);
        gdsMediaReqDTO.setMediaType("2");
        gdsMediaReqDTO.setMediaSize(104857600);
        gdsMediaRSV.saveGdsMedia(gdsMediaReqDTO);

    }
}

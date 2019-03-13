package com.zengshi.ecp.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidx;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsInterfaceGdsSV;
import com.zengshi.ecp.server.test.EcpServicesTest;

public class GdsInterfaceGdsRSVImplTest extends EcpServicesTest {

    @Resource
    private IGdsInterfaceGdsSV gdsInterfaceGdsSV;
    
    @Test
    public void save(){
        GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO=new GdsInterfaceGdsReqDTO();
        gdsInterfaceGdsReqDTO.setGdsId(8l);
        gdsInterfaceGdsReqDTO.setSkuId(8l);
        gdsInterfaceGdsReqDTO.setShopId(8l);
        gdsInterfaceGdsReqDTO.setOriginGdsId("8");
        gdsInterfaceGdsReqDTO.setOriginSkuId("8");
        gdsInterfaceGdsReqDTO.setOrigin("01");//人卫
        gdsInterfaceGdsReqDTO.setCreateStaff(10l);
        
        this.gdsInterfaceGdsSV.saveGdsInterfaceGds(gdsInterfaceGdsReqDTO);
    }
    
    @Test
    public void update(){
        GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO=new GdsInterfaceGdsReqDTO();
        gdsInterfaceGdsReqDTO.setId(88l);
        gdsInterfaceGdsReqDTO.setGdsId(88l);
        gdsInterfaceGdsReqDTO.setSkuId(88l);
        gdsInterfaceGdsReqDTO.setShopId(88l);
        gdsInterfaceGdsReqDTO.setOriginGdsId("8");
        gdsInterfaceGdsReqDTO.setOriginSkuId("8");
        gdsInterfaceGdsReqDTO.setOrigin("01");//人卫
        gdsInterfaceGdsReqDTO.setCreateStaff(10l);
        
        this.gdsInterfaceGdsSV.updateGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);
    }
    
    @Test
    public void delete(){
        GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO=new GdsInterfaceGdsReqDTO();
        gdsInterfaceGdsReqDTO.setOriginGdsId("8");
        this.gdsInterfaceGdsSV.deleteGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);
    }
    
    @Test
    public void query(){
//        GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO=new GdsInterfaceGdsReqDTO();
//        gdsInterfaceGdsReqDTO.setOrigin("08");
//        gdsInterfaceGdsReqDTO.setOriginGdsId("0000Z28220");
//        GdsInterfaceGds gdsInterfaceGds=this.gdsInterfaceGdsSV.queryGdsInterfaceGdsByOriginGdsId(gdsInterfaceGdsReqDTO);
//        System.out.println(gdsInterfaceGds);
        
        GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO=new GdsInterfaceGdsGidxReqDTO();
        gdsInterfaceGdsGidxReqDTO.setOrigin("08");
        gdsInterfaceGdsGidxReqDTO.setGdsId(11366l);
        GdsInterfaceGdsGidx gdsInterfaceGdsGidx=this.gdsInterfaceGdsSV.queryGdsInterfaceGdsGidxByEcpGdsId(gdsInterfaceGdsGidxReqDTO);
        System.out.println(gdsInterfaceGdsGidx);
    }

}

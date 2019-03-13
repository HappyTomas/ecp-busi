package com.zengshi.ecp.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.goods.dubbo.dto.AreaCodeDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsInfoShipmentReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShipmentCalInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsShipTempSV;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class GdsShipTempSVImplTest extends EcpServicesTest{
    @Autowired(required = false)
    IGdsShipTempSV iGdsShipTempSV;
    
    @Test
    public void testSaveTemp(){
        GdsShiptempReqDTO gdsShipTempReqDTO = new GdsShiptempReqDTO();
        gdsShipTempReqDTO.setId(1l);
        gdsShipTempReqDTO.setCreateStaff(11l);
        gdsShipTempReqDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsShipTempReqDTO.setIfFree("1");
        gdsShipTempReqDTO.setShipInstruction("测试");
        gdsShipTempReqDTO.setShipTemplateName("模板测试");
        gdsShipTempReqDTO.setShipTemplateType("1");
        gdsShipTempReqDTO.setStatus("1");
        gdsShipTempReqDTO.setShopId(123L);
        List<GdsShiptempPriceReqDTO> gdsShipTempPriceReqDTOList = new ArrayList<GdsShiptempPriceReqDTO>();
        GdsShiptempPriceReqDTO gdsShipTempPriceReqDTO = new GdsShiptempPriceReqDTO();
        List<AreaCodeDTO> areaCode = new ArrayList<AreaCodeDTO>();
        AreaCodeDTO areaCodeDTO1 = new AreaCodeDTO();
        AreaCodeDTO areaCodeDTO2 = new AreaCodeDTO();
        areaCodeDTO1.setAreaCode("59");
        areaCodeDTO1.setAreaLevel(1l);
        areaCodeDTO2.setAreaCode("38");
        areaCodeDTO2.setAreaLevel(2l);
        areaCode.add(areaCodeDTO2);
        areaCode.add(areaCodeDTO1);
        gdsShipTempPriceReqDTO.setAreaCodeList(areaCode);
        gdsShipTempPriceReqDTO.setContinueAmount(10l);
        gdsShipTempPriceReqDTO.setContinuePrice(100l);
        gdsShipTempPriceReqDTO.setFirstAmount(1l);
        gdsShipTempPriceReqDTO.setFirstPrice(5l);
        gdsShipTempPriceReqDTO.setFreeAmount(50l);
        gdsShipTempPriceReqDTO.setPricingListId(1l);
        gdsShipTempPriceReqDTO.setShipTemplateId(1l);
        gdsShipTempPriceReqDTO.setPricingMode("0");
        gdsShipTempPriceReqDTO.setCreateStaff(11l);
        gdsShipTempPriceReqDTO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        gdsShipTempPriceReqDTO.setStatus("1");
        gdsShipTempReqDTO.setGdsShiptempPriceReqDTOList(gdsShipTempPriceReqDTOList);
        try {
            iGdsShipTempSV.saveGdsShipTemp(gdsShipTempReqDTO);
        } catch (Exception e) {
            LogUtil.info("","",e);
        }
        
    }
    
    @Test
    public void testCalShipTemp(){
    	
    	GdsShipmentCalInfoReqDTO gdsShipmentCalInfoReqDTO = new GdsShipmentCalInfoReqDTO();
    	gdsShipmentCalInfoReqDTO.setShopId(35l);
    	List<GdsInfoShipmentReqDTO> gdsInfos = new ArrayList<GdsInfoShipmentReqDTO>();
    	gdsShipmentCalInfoReqDTO.setGdsInfos(gdsInfos);
    	GdsInfoShipmentReqDTO dto1 = new GdsInfoShipmentReqDTO();
    	dto1.setGdsId(1008609472L);
    	dto1.setSkuId(1008617912l);
    	dto1.setCatgCode("111");
    	dto1.setTypeId(1l);
    	dto1.setCount(1l);
    	GdsInfoShipmentReqDTO dto2 = new GdsInfoShipmentReqDTO();
    	dto2.setGdsId(1008609472L);
    	dto2.setSkuId(1008617911l);
    	dto2.setCatgCode("111");
    	dto2.setTypeId(1l);
    	dto2.setCount(1l);
    	
    	GdsInfoShipmentReqDTO dto3 = new GdsInfoShipmentReqDTO();
    	dto3.setGdsId(1008609564L);
    	dto3.setSkuId(1008618055l);
    	dto3.setCatgCode("111");
    	dto3.setTypeId(1l);
    	dto3.setCount(1l);
    	
    	GdsInfoShipmentReqDTO dto4 = new GdsInfoShipmentReqDTO();
    	dto4.setGdsId(1008609564L);
    	dto4.setSkuId(1008618054l);
    	dto4.setCatgCode("111");
    	dto4.setTypeId(1l);
    	dto4.setCount(1l);
    	
    	gdsInfos.add(dto1);
    	gdsInfos.add(dto2);
    	gdsInfos.add(dto3);
    	gdsInfos.add(dto4);
    	gdsShipmentCalInfoReqDTO.setGdsInfos(gdsInfos);
    	long price;
        try {
            price = iGdsShipTempSV.calcShipExpense(gdsShipmentCalInfoReqDTO);
            System.out.println(price);
        } catch (Exception e) {
            LogUtil.info("","",e);
        }
    }
    
    @Test 
    public void testqueryShopDefaultShipMent()throws Exception{
        
        GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO = new GdsShop2ShipmentReqDTO();
//        gdsShop2ShipmentReqDTO.setShipmentId(11l);
        gdsShop2ShipmentReqDTO.setShopId(11L);
        GdsShiptempRespDTO gdsShiptempRespDTO =  iGdsShipTempSV.queryShopDefaultShipMent(gdsShop2ShipmentReqDTO); 
        
        System.out.println("================================" + gdsShiptempRespDTO.getShipTemplateName());
        
    }
    
    @Test 
    public void testEditGdsShop2Shiptemp()throws Exception{
        
        GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO = new GdsShop2ShipmentReqDTO();
        gdsShop2ShipmentReqDTO.setShipmentId(11l);
        gdsShop2ShipmentReqDTO.setShopId(11L);
        iGdsShipTempSV.editGdsShop2Shiptemp(gdsShop2ShipmentReqDTO); 
        
        
        
    }
    
    @Test 
    public void testAddShop2ShopTemp()throws Exception{
        
        GdsShop2ShipmentReqDTO gdsShop2ShipmentReqDTO = new GdsShop2ShipmentReqDTO();
        gdsShop2ShipmentReqDTO.setShipmentId(11l);
        gdsShop2ShipmentReqDTO.setShopId(11L);
        iGdsShipTempSV.addGdsShop2Shiptemp(gdsShop2ShipmentReqDTO); 
        
        
        
    }
}




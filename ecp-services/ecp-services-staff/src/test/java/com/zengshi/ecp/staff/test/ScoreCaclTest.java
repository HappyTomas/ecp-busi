package com.zengshi.ecp.staff.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreCaclSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreToOrderSV;

public class ScoreCaclTest extends EcpServicesTest{
    @Resource
    private IScoreCaclSV scoreCaclSV;
    
    @Resource
    private IScoreToOrderSV scoreToOrderSV;
    
    @Test
    public void caclTest()
    {
        String sourceType = "01";
        CustInfoReqDTO info = new CustInfoReqDTO();
        info.setId(84L);
        info.setStaffCode("pengjie2");
        ScoreResultResDTO result = scoreCaclSV.updateScore(sourceType, info, null);
        if(result != null)
            System.out.println("=====This result score is : "+result.getScore());
    }
    @Test
    public void caclTest2()
    {
        System.out.println("====== 积分计算     开始 ======");
        long start = System.currentTimeMillis();
        String sourceType = "02";
        CustInfoReqDTO info = new CustInfoReqDTO();
        info.setId(84L);
        info.setStaffCode("pengjie2");
        PayQuartzInfoRequest orderinfo = new PayQuartzInfoRequest();
        orderinfo.setOrderId("order-131111111");
        
        List<ROrdCartItemCommRequest> ordCartItemCommList = new ArrayList<ROrdCartItemCommRequest>();
        
        ROrdCartItemCommRequest item1 = new ROrdCartItemCommRequest();
        item1.setCategoryCode("211");
        item1.setBuyPrice(5500L);
        item1.setDiscountMoney(5500L);
        item1.setGdsId(120110004L);
        item1.setOrderId("order-131111111");
        item1.setOrderSubId("order-sub-13111111");
        item1.setOrderAmount(5L);
        ROrdCartItemCommRequest item2 = new ROrdCartItemCommRequest();
        item2.setCategoryCode("211");
        item2.setBuyPrice(5500L);
        item2.setGdsId(120110002L);   
        item2.setOrderId("order-131111111");
        item2.setOrderSubId("order-sub-13111111");
        item2.setOrderAmount(5L);
        item2.setDiscountMoney(5500L);

        ordCartItemCommList.add(item1);
        ordCartItemCommList.add(item2);

        orderinfo.setOrdCartItemCommList(ordCartItemCommList);

//        OrderInfoReqDTO infoDTO = new OrderInfoReqDTO();
//        List<ProductInfoReqDto> list = new ArrayList<ProductInfoReqDto>();
//        ProductInfoReqDto dto = new ProductInfoReqDto();
//        //商品类型211 21 2
//        dto.setProductPrice(5500);
//        dto.setProductType("211");
//        dto.setProductID(120110004L);
//        ProductInfoReqDto dto2 = new ProductInfoReqDto();
//        //商品类型211 21 2
//        dto2.setProductPrice(5500);
//        dto2.setProductType("211");
//        dto2.setProductID(120110002L);
//        list.add(dto);
//        list.add(dto2);
//        
//        infoDTO.setProductList(list);
        ScoreResultResDTO result = scoreCaclSV.updateScore(sourceType, info, orderinfo);
        if(result != null)
            System.out.println("=====This result score is : "+result.getScore());
        long end = System.currentTimeMillis();
        System.out.println("====== 积分计算     结束 ======");
        System.out.println("=======This func excute :"+(end-start)/1000.00+"'s=======");
    }
    
    @Test
    public void scoreRollBackTest()
    {
        CustInfoReqDTO custinfo = new CustInfoReqDTO();
        OrderInfoReqDTO orderinfo = new OrderInfoReqDTO();
        
        custinfo.setId(84L);
        orderinfo.setOrderInfoID("13115924273");
        orderinfo.setOrderSonInfoID("13115924273");
        
//        int flag = scoreToOrderSV.saveScoreUseRollBack(custinfo, orderinfo);
//        
//        if(flag != 0)
//            System.out.println("==========没有记录存在=============");
    }
}


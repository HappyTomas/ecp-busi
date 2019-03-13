/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.test.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.SecondKillPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dubbo/client/dubbo-test-prom.xml")
public class PromQueryRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromQueryRSV promQueryRSV;
    
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
 
   @Test
    public void testQueryShop() {
       PageResponseDTO<PromInfoResponseDTO> p= promQueryRSV.queryPromInfoList(new PromInfoDTO());
       System.out.println(p);
          
    } 
   @Test
   public void testQueryPlat() {
      PageResponseDTO<PromInfoResponseDTO> p= promQueryRSV.queryPromInfoListByPlat(new PromInfoDTO());
      System.out.println(p);
         
   }   
   @Test
   public void testQueryPromType() {
       PromTypeDTO promTypeDTO=new PromTypeDTO();
       promTypeDTO.setPromTypeCode("111");
       PromTypeResponseDTO p= promQueryRSV.queryPromType(promTypeDTO);
      System.out.println(p);
         
   }  
   @Test
   public void testQueryPromGdsNUll() {
      List<PromSkuDTO> l= promQueryRSV.listPromotionSku(null);
   } 
   @Test
   public void testQueryPromGds1() {
       PromSkuDTO PromSkuDTO=new PromSkuDTO();
       PromSkuDTO.setPromId(new Long(28));
      List<PromSkuDTO> l= promQueryRSV.listPromotionSku(PromSkuDTO);
      System.out.println(l.size());
   }  
   @Test
   public void testQueryPresent() {
      
      PromPresentDTO dto= promQueryRSV.promPresent(new Long(49), null);
       
   }  
   
   //订单传入  查询赠送信息（无任何促销情况 ）
   @Test
   public void testQueryPresentOrdInfo() {
       
       ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
       rOrdCartsCommRequest.setCurrentSiteId(new Long(1));
       rOrdCartsCommRequest.setSource("1");//渠道来源
       rOrdCartsCommRequest.setStaffId(new Long(1));
       
       List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
       
       ROrdCartCommRequest r=new ROrdCartCommRequest();
       ordCartsCommList.add(r);
       r.setCartType("1");
       r.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
       r.setId(new Long(1));
       r.setOrderId("orderId1");
     //r.setPromId(new Long(-1));
       r.setShopId(new Long(35));
       r.setShopName("shopName");
       r.setSource("1");
       r.setStaffId(new Long(0));
       
       r.setAreaValue("1");
       r.setChannelValue("1");
       r.setCustGroupValue("1");
       r.setCustLevelValue("1");
       
       
       List<ROrdCartItemCommRequest> ordCartItemCommList =new ArrayList<ROrdCartItemCommRequest>();
       ROrdCartItemCommRequest rd=new ROrdCartItemCommRequest();
       
       r.setOrdCartItemCommList(ordCartItemCommList);
       
       rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
       
       
       ROrdCartItemCommRequest  item=new ROrdCartItemCommRequest();
       
       ordCartItemCommList.add(item);
       
       item.setAddTime(DateUtil.getSysDate());
       item.setAppName("1");
       item.setBasePrice(new Long(10023));
       item.setBuyPrice(new Long(10000));
       item.setCartId(new Long(1));
       item.setCartType("1");
       item.setCategoryCode("111");
       item.setCreateStaff(new Long(1));
       item.setCreateTime(DateUtil.getSysDate());
       item.setCurrentSiteId(r.getCurrentSiteId());
       item.setGdsId(new Long(37305));
       item.setGdsName("gdsName");
       item.setGdsType(new Long(1));
       item.setGroupDetail("setGroupDetail");
       item.setGroupType("setGroupType");
       item.setId(new Long(1));
       item.setOrderAmount(new Long(100));
       item.setOrderId(null);
       item.setOrderSubId(null);
      // item.setOrdPromId(new Long(2088));
       item.setPriceType("1");
      // item.setPromId(new Long(-1));
       item.setShopId(new Long(69));
       item.setShopName("shopName");
       item.setSkuId(new Long(74540));
       item.setSkuInfo("sadfsdf");
       item.setStaffId(r.getStaffId());
       
       PromPresentRespDTO d= promQueryRSV.promPresent(rOrdCartsCommRequest);
       
       System.out.println(d);
   }  
   //订单传入  查询赠送信息（明细赠送 gift ）
   @Test
   public void testQueryPresentDetail() {
       
       ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
       rOrdCartsCommRequest.setCurrentSiteId(new Long(1));
       rOrdCartsCommRequest.setSource("1");//渠道来源
       rOrdCartsCommRequest.setStaffId(new Long(1));
       
       List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
       
       ROrdCartCommRequest r=new ROrdCartCommRequest();
       ordCartsCommList.add(r);
       r.setCartType("1");
       r.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
       r.setId(new Long(1));
       r.setOrderId("orderId1");
     //r.setPromId(new Long(-1));
       r.setShopId(new Long(35));
       r.setShopName("shopName");
       r.setSource("1");
       r.setStaffId(new Long(0));
       
       r.setAreaValue("1");
       r.setChannelValue("1");
       r.setCustGroupValue("1");
       r.setCustLevelValue("1");
       
       
       List<ROrdCartItemCommRequest> ordCartItemCommList =new ArrayList<ROrdCartItemCommRequest>();
       ROrdCartItemCommRequest rd=new ROrdCartItemCommRequest();
       
       r.setOrdCartItemCommList(ordCartItemCommList);
       
       rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
       
       
       ROrdCartItemCommRequest  item=new ROrdCartItemCommRequest();
       
       ordCartItemCommList.add(item);
       
       item.setAddTime(DateUtil.getSysDate());
       item.setAppName("1");
       item.setBasePrice(new Long(10023));
       item.setBuyPrice(new Long(10000));
       item.setCartId(new Long(1));
       item.setCartType("1");
       item.setCategoryCode("111");
       item.setCreateStaff(new Long(1));
       item.setCreateTime(DateUtil.getSysDate());
       item.setCurrentSiteId(r.getCurrentSiteId());
       item.setGdsId(new Long(37305));
       item.setGdsName("gdsName");
       item.setGdsType(new Long(1));
       item.setGroupDetail("setGroupDetail");
       item.setGroupType("setGroupType");
       item.setId(new Long(1));
       item.setOrderAmount(new Long(100));
       item.setOrderId(null);
       item.setOrderSubId(null);
      // item.setOrdPromId(new Long(2088));
       item.setPriceType("1");
       item.setIfFulfillProm(true);
       item.setPromId(new Long(3036));
       item.setShopId(new Long(69));
       item.setShopName("shopName");
       item.setSkuId(new Long(74540));
       item.setSkuInfo("sadfsdf");
       item.setStaffId(r.getStaffId());
       
       PromPresentRespDTO d= promQueryRSV.promPresent(rOrdCartsCommRequest);
       
       System.out.println(d);
   }  
   
   //订单传入  查询赠送信息（明细赠送 积分 ）
   @Test
   public void testQueryPresentPoints() {
       
       ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
       rOrdCartsCommRequest.setCurrentSiteId(new Long(1));
       rOrdCartsCommRequest.setSource("1");//渠道来源
       rOrdCartsCommRequest.setStaffId(new Long(808));
       
       List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
       
       ROrdCartCommRequest r=new ROrdCartCommRequest();
       ordCartsCommList.add(r);
       r.setCartType("1");
       r.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
       r.setId(new Long(1));
       r.setOrderId("orderId1");
       r.setIfFulfillProm(true);
       r.setPromId(new Long(3033));
       
       r.setShopId(new Long(69));
       r.setShopName("shopName");
       r.setSource("1");
       r.setStaffId(rOrdCartsCommRequest.getStaffId());
       
       r.setAreaValue("1");
       r.setChannelValue("1");
       r.setCustGroupValue("1");
       r.setCustLevelValue("1");
       
       
       List<ROrdCartItemCommRequest> ordCartItemCommList =new ArrayList<ROrdCartItemCommRequest>();
       ROrdCartItemCommRequest rd=new ROrdCartItemCommRequest();
       
       r.setOrdCartItemCommList(ordCartItemCommList);
       
       rOrdCartsCommRequest.setOrdCartsCommList(ordCartsCommList);
       
       
       ROrdCartItemCommRequest  item=new ROrdCartItemCommRequest();
       
       ordCartItemCommList.add(item);
       
       item.setAddTime(DateUtil.getSysDate());
       item.setAppName("1");
       item.setBasePrice(new Long(10023));
       item.setBuyPrice(new Long(10000));
       item.setCartId(new Long(1));
       item.setCartType("1");
       item.setCategoryCode("111");
       item.setCreateStaff(new Long(1));
       item.setCreateTime(DateUtil.getSysDate());
       item.setCurrentSiteId(r.getCurrentSiteId());
       item.setGdsId(new Long(37305));
       item.setGdsName("gdsName");
       item.setGdsType(new Long(1));
       item.setGroupDetail("setGroupDetail");
       item.setGroupType("setGroupType");
       item.setId(new Long(1));
       item.setOrderAmount(new Long(100));
       item.setOrderId(null);
       item.setOrderSubId(null);
      // item.setOrdPromId(new Long(2088));
       item.setPriceType("1");
       //item.setIfFulfillProm(true);
       //item.setPromId(new Long(3033));
       item.setShopId(new Long(69));
       item.setShopName("shopName");
       item.setSkuId(new Long(74540));
       item.setSkuInfo("sadfsdf");
       item.setStaffId(r.getStaffId());
       
       PromPresentRespDTO d= promQueryRSV.promPresent(rOrdCartsCommRequest);
       
       System.out.println(d);
   }  
   
   @Test
   public void testHH(){

       PromSkuDTO ipromSkuDTO=new PromSkuDTO();
       ipromSkuDTO.setPromId(new Long("3220"));
       ipromSkuDTO.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);
       long t=System.currentTimeMillis();
       List<PromSkuDTO> skuList=promQueryRSV.listPromotionSku(ipromSkuDTO);
       System.out.println((System.currentTimeMillis()-t)/1000+"秒");
       if(!CollectionUtils.isEmpty(skuList)){
           
           GdsSkuInfoReqDTO dto=new GdsSkuInfoReqDTO();
           GdsSkuInfoRespDTO  respDTO=new GdsSkuInfoRespDTO();
           long t1=System.currentTimeMillis();
            for(PromSkuDTO promSkuDTO:skuList){
                
                dto.setId(promSkuDTO.getSkuId().longValue());
                //库存查询 设置条件
                SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.STOCK,SkuQueryOption.SHOWPRICE};
                dto.setSkuQuery(skuQuery);
                //调用商品接口
                respDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
                
           }
            System.out.println((System.currentTimeMillis()-t1)/1000+"秒");
       }
   
   }
   
   @Test
   public void listProm() {
       
       PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
       promRuleCheckDTO.setCalDate(DateUtil.getSysDate());//必填
     /*  promRuleCheckDTO.setGdsId(new Long(37867));//必填
       promRuleCheckDTO.setGdsName("gdsName");//可填  如果有尽量传
       promRuleCheckDTO.setSkuId(new Long(75185));//必填
       promRuleCheckDTO.setShopId(new Long(35));//必填
       promRuleCheckDTO.setShopName("shopna,me");//可填  如果有尽量传
       promRuleCheckDTO.setSiteId(new Long(1));//必填
       promRuleCheckDTO.setStaffId("1");//可填
       promRuleCheckDTO.setBasePrice(Long.valueOf(100));//必填
       promRuleCheckDTO.setBuyPrice(Long.valueOf(85)); //必填
       promRuleCheckDTO.setBuyCnt(String.valueOf(1));//可填
*/      
       promRuleCheckDTO.setGdsId(new Long(37863));//必填
       promRuleCheckDTO.setGdsName("gdsName");//可填  如果有尽量传
       promRuleCheckDTO.setSkuId(new Long(75181));//必填
       promRuleCheckDTO.setShopId(new Long(35));//必填
       promRuleCheckDTO.setShopName("shopna,me");//可填  如果有尽量传
       promRuleCheckDTO.setSiteId(new Long(1));//必填
       promRuleCheckDTO.setStaffId("1");//可填
       promRuleCheckDTO.setBasePrice(Long.valueOf(3));//必填
       promRuleCheckDTO.setBuyPrice(Long.valueOf(3)); //必填
       promRuleCheckDTO.setBuyCnt(String.valueOf(1));//可填
       long t=System.currentTimeMillis();
       List<PromListRespDTO>  list=promQueryRSV.listProm(promRuleCheckDTO);
       System.out.println(list.toString());
       long t1=System.currentTimeMillis();
       System.out.println((t1-t)+"毫秒");
       //System.out.println(list);
   }
   @Test
   public void listPromGds() {
       
       PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
       promRuleCheckDTO.setCalDate(DateUtil.getSysDate());//必填
     /*  promRuleCheckDTO.setGdsId(new Long(37867));//必填
       promRuleCheckDTO.setGdsName("gdsName");//可填  如果有尽量传
       promRuleCheckDTO.setSkuId(new Long(75185));//必填
       promRuleCheckDTO.setShopId(new Long(35));//必填
       promRuleCheckDTO.setShopName("shopna,me");//可填  如果有尽量传
       promRuleCheckDTO.setSiteId(new Long(1));//必填
       promRuleCheckDTO.setStaffId("1");//可填
       promRuleCheckDTO.setBasePrice(Long.valueOf(100));//必填
       promRuleCheckDTO.setBuyPrice(Long.valueOf(85)); //必填
       promRuleCheckDTO.setBuyCnt(String.valueOf(1));//可填
*/      
       promRuleCheckDTO.setGdsId(new Long(37863));//必填
       promRuleCheckDTO.setGdsName("gdsName");//可填  如果有尽量传
      // promRuleCheckDTO.setSkuId(new Long(75181));//必填
       promRuleCheckDTO.setShopId(new Long(35));//必填
       promRuleCheckDTO.setShopName("shopna,me");//可填  如果有尽量传
       promRuleCheckDTO.setSiteId(new Long(1));//必填
       promRuleCheckDTO.setStaffId("1");//可填
       promRuleCheckDTO.setBasePrice(Long.valueOf(3));//必填
       promRuleCheckDTO.setBuyPrice(Long.valueOf(3)); //必填
       promRuleCheckDTO.setBuyCnt(String.valueOf(1));//可填
       long t=System.currentTimeMillis();
       List<PromListRespDTO>  list=promQueryRSV.listPromByGds(promRuleCheckDTO);
       System.out.println(list.toString());
       long t1=System.currentTimeMillis();
       System.out.println((t1-t)+"毫秒");
       //System.out.println(list);
   }
   
   @Test
   public void listSecondPromInfoForPage() {
	  PromInfoDTO promInfoDTO = new PromInfoDTO();
	  promInfoDTO.setSiteId(1l);
	  promInfoDTO.setPageSize(8);
	  promInfoDTO.setStatus("10");
	  PageResponseDTO<PromInfoResponseDTO> page = promQueryRSV.listSecondPromInfoForPage(promInfoDTO);
	  List<PromInfoResponseDTO> list = page.getResult();
  	for (PromInfoResponseDTO promInfoResponseDTO : list) {
  		System.out.println(promInfoResponseDTO.getIfStart());
  		System.out.println(promInfoResponseDTO.getPromTheme());
  	}
   }
   
   @Test
   public void listSkuOfSecondKillProm() {
	   PromInfoDTO promInfoDTO = new PromInfoDTO ();
	   promInfoDTO.setId(17126l);
	   promInfoDTO.setPageSize(8);
	   promInfoDTO.setPageNo(Integer.valueOf(3));
	   SecondKillPromRespDTO secondKillPromRespDTO = promQueryRSV.listSkuOfSecondKillProm(promInfoDTO);
	   if(!StringUtil.isEmpty(secondKillPromRespDTO)){
		   PageResponseDTO<KillGdsInfoDTO> page = secondKillPromRespDTO.getPage();
		   if(page!=null){
			   for (KillGdsInfoDTO killGdsInfoDTO : page.getResult()) {
				   System.out.println( killGdsInfoDTO.getSkuId());
				  /* System.out.println( killGdsInfoDTO.getBuyCnt());
				   System.out.println( killGdsInfoDTO.getBuyPrice());
				   System.out.println( killGdsInfoDTO.getDiscountPrice());
				   System.out.println( killGdsInfoDTO.getGdsName());
				   System.out.println( killGdsInfoDTO.getPercent());
				   System.out.println( killGdsInfoDTO.getIfStart());*/
			   }
		   }
	   }
   }
   
   @Test
   public void KillPromGdsinfoList() {
	   PromDTO promDTO =  new  PromDTO();
	   promDTO.setSiteId(1l);
	   List<KillGdsInfoDTO> list = promQueryRSV.killPromGdsinfoList(promDTO);
	   if(!CollectionUtils.isEmpty(list)){
		   for (KillGdsInfoDTO killGdsInfoDTO : list) {
			   System.out.println( killGdsInfoDTO.getBasePrice());
			   System.out.println( killGdsInfoDTO.getBuyCnt());
			   System.out.println( killGdsInfoDTO.getBuyPrice());
			   System.out.println( killGdsInfoDTO.getGdsName());
			   System.out.println( killGdsInfoDTO.getPercent());
		   }
   }
   }
}

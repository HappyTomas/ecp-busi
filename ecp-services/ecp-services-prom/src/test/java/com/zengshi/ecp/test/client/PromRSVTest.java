/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoClientTest.java 
 * Package Name:com.zengshi.ecp.test.client 
 * Date:2015-8-3下午6:55:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.test.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.prom.dubbo.dto.CartPromBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.DateUtil;
import com.alibaba.fastjson.JSON;

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
public class PromRSVTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IPromRSV promRSV;
    
    @Test
    public void testInitCartsJson() {
       // String str="{\"appName\":\"ai-ecp\",\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"ordCartsCommList\":[{\"appName\":\"ai-ecp\",\"areaValue\":\"110000\",\"cartType\":\"01\",\"channelValue\":\"1\",\"currentSiteId\":1,\"custGroupValue\":\"\",\"custLevelValue\":\"02\",\"endRowIndex\":1,\"id\":1310,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordCartItemCommList\":[{\"addTime\":1446952081000,\"appName\":\"ai-ecp\",\"basePrice\":88000,\"buyPrice\":88000,\"cartId\":1310,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":72,\"createTime\":1446952081000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37782,\"gdsName\":\"学谦的娃娃6\",\"gdsType\":1,\"groupDetail\":\"75075:75074\",\"groupType\":\"1\",\"id\":490,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":1,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3119,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":75075,\"skuInfo\":\"优秀/大众\",\"staff\":{\"id\":72,\"staffClass\":\"20\",\"staffCode\":\"linby3\",\"staffLevelCode\":\"02\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\",\"updateStaff\":72,\"updateTime\":1446952081000},{\"addTime\":1446952081000,\"appName\":\"ai-ecp\",\"basePrice\":80000,\"buyPrice\":80000,\"cartId\":1310,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":72,\"createTime\":1446952081000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37782,\"gdsName\":\"学谦的娃娃6\",\"gdsType\":1,\"groupDetail\":\"75075:75074\",\"groupType\":\"1\",\"id\":489,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":1,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3119,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":75074,\"skuInfo\":\"普通/大众\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\",\"updateStaff\":72,\"updateTime\":1446952081000}],\"pageNo\":1,\"pageSize\":1,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\"}],\"pageNo\":1,\"pageSize\":1,\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\"}";
        //String str="{\"appName\":\"ai-ecp\",\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"ordCartsCommList\":[{\"appName\":\"ai-ecp\",\"areaValue\":\"350000\",\"cartType\":\"01\",\"channelValue\":\"1\",\"currentSiteId\":1,\"custGroupValue\":\"\",\"custLevelValue\":\"04\",\"endRowIndex\":1,\"id\":1403,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordCartItemCommList\":[{\"addTime\":1447296167000,\"appName\":\"ai-ecp\",\"basePrice\":20000,\"buyPrice\":20000,\"cartId\":1403,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":182,\"createTime\":1447296167000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37537,\"gdsName\":\"学谦的娃娃3\",\"gdsType\":1,\"groupDetail\":\"74827\",\"groupType\":\"0\",\"id\":652,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":3,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":74827,\"skuInfo\":\"优秀/精品\",\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6295588149310817871\",\"updateStaff\":182,\"updateTime\":1447296167000}],\"pageNo\":1,\"pageSize\":1,\"promId\":3054,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6295588149310817871\"}],\"pageNo\":1,\"pageSize\":1,\"source\":\"1\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6295588149310817871\"}";
        String str="{\"appName\":\"ai-ecp\",\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"ordCartsCommList\":[{\"appName\":\"ai-ecp\",\"areaValue\":\"110000\",\"cartType\":\"01\",\"channelValue\":\"1\",\"currentSiteId\":1,\"custGroupValue\":\"\",\"custLevelValue\":\"01\",\"endRowIndex\":1,\"id\":1654,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordCartItemCommList\":[{\"addTime\":1448004220000,\"appName\":\"ai-ecp\",\"basePrice\":88000,\"buyPrice\":52800,\"cartId\":1654,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":72,\"createTime\":1448004220000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37782,\"gdsName\":\"学谦的娃娃6\",\"gdsType\":1,\"groupDetail\":\":75096:75075\",\"groupType\":\"1\",\"id\":1008,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":1,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3129,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":75075,\"skuInfo\":\"优秀/大众\",\"staff\":{\"id\":72,\"staffClass\":\"20\",\"staffCode\":\"linby3\",\"staffLevelCode\":\"01\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6539879758333273088\",\"updateStaff\":72,\"updateTime\":1448004220000},{\"addTime\":1448004220000,\"appName\":\"ai-ecp\",\"basePrice\":40000,\"buyPrice\":24000,\"cartId\":1654,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":72,\"createTime\":1448004220000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37802,\"gdsName\":\"学谦的娃娃10\",\"gdsType\":1,\"groupDetail\":\":75096:75075\",\"groupType\":\"1\",\"id\":1009,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":1,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3129,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":75096,\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6539879758333273088\",\"updateStaff\":72,\"updateTime\":1448004220000}],\"pageNo\":1,\"pageSize\":1,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6539879758333273088\"}],\"pageNo\":1,\"pageSize\":1,\"source\":\"1\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6539879758333273088\"}";
        ROrdCartsCommRequest rOrdCartsCommRequest=new ROrdCartsCommRequest();
        rOrdCartsCommRequest=JSON.parseObject(str, ROrdCartsCommRequest.class);
        long t= System.currentTimeMillis();
        System.out.println(t);
        CartPromRespDTO d= promRSV.initCartPromMap(rOrdCartsCommRequest);
        System.out.println("哈哈啥地方哈市的飞洒地方"+((System.currentTimeMillis()-t)/1000));
    }
    @Test
    public void change() {
       // String str="{\"appName\":\"ai-ecp\",\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"ordCartsCommList\":[{\"appName\":\"ai-ecp\",\"areaValue\":\"110000\",\"cartType\":\"01\",\"channelValue\":\"1\",\"currentSiteId\":1,\"custGroupValue\":\"\",\"custLevelValue\":\"02\",\"endRowIndex\":1,\"id\":1310,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordCartItemCommList\":[{\"addTime\":1446952081000,\"appName\":\"ai-ecp\",\"basePrice\":88000,\"buyPrice\":88000,\"cartId\":1310,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":72,\"createTime\":1446952081000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37782,\"gdsName\":\"学谦的娃娃6\",\"gdsType\":1,\"groupDetail\":\"75075:75074\",\"groupType\":\"1\",\"id\":490,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":1,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3119,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":75075,\"skuInfo\":\"优秀/大众\",\"staff\":{\"id\":72,\"staffClass\":\"20\",\"staffCode\":\"linby3\",\"staffLevelCode\":\"02\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\",\"updateStaff\":72,\"updateTime\":1446952081000},{\"addTime\":1446952081000,\"appName\":\"ai-ecp\",\"basePrice\":80000,\"buyPrice\":80000,\"cartId\":1310,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":72,\"createTime\":1446952081000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37782,\"gdsName\":\"学谦的娃娃6\",\"gdsType\":1,\"groupDetail\":\"75075:75074\",\"groupType\":\"1\",\"id\":489,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":1,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3119,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":75074,\"skuInfo\":\"普通/大众\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\",\"updateStaff\":72,\"updateTime\":1446952081000}],\"pageNo\":1,\"pageSize\":1,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\"}],\"pageNo\":1,\"pageSize\":1,\"staff\":{\"$ref\":\"$.ordCartsCommList[0].ordCartItemCommList[0].staff\"},\"staffId\":72,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-6881908945841745101\"}";
        String str="{\"appName\":\"ai-ecp\",\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"rOrdCartCommRequest\":{\"appName\":\"ai-ecp\",\"areaValue\":\"350000\",\"cartType\":\"01\",\"currentSiteId\":1,\"custGroupValue\":\"\",\"custLevelValue\":\"04\",\"endRowIndex\":1,\"id\":1403,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordCartItemCommList\":[{\"addTime\":1447296167000,\"appName\":\"ai-ecp\",\"basePrice\":20000,\"buyPrice\":20000,\"cartId\":1403,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":182,\"createTime\":1447296167000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37537,\"gdsName\":\"学谦的娃娃3\",\"gdsType\":1,\"groupDetail\":\"74827\",\"groupType\":\"0\",\"id\":652,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":3,\"orderSubScore\":0,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3134,\"score\":0,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":74827,\"skuInfo\":\"优秀/精品\",\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-8886683646487105967\",\"updateStaff\":182,\"updateTime\":1447296167000}],\"pageNo\":1,\"pageSize\":1,\"promId\":3054,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"staff\":{\"$ref\":\"$.rOrdCartChangeRequest.rOrdCartCommRequest.ordCartItemCommList[0].staff\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-8886683646487105967\"},\"staff\":{\"$ref\":\"$.rOrdCartChangeRequest.rOrdCartCommRequest.ordCartItemCommList[0].staff\"},\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-8886683646487105967\"}";
        ROrdCartChangeRequest rOrdCartChangeRequest=new ROrdCartChangeRequest();
        rOrdCartChangeRequest=JSON.parseObject(str, ROrdCartChangeRequest.class);
        
        String ss="{\"rOrdCartCommRequest\":{\"appName\":\"ai-ecp\",\"areaValue\":\"350000\",\"cartType\":\"01\",\"currentSiteId\":1,\"custGroupValue\":\"\",\"custLevelValue\":\"04\",\"endRowIndex\":1,\"id\":1403,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordCartItemCommList\":[{\"addTime\":1447296167000,\"appName\":\"ai-ecp\",\"basePrice\":20000,\"buyPrice\":20000,\"cartId\":1403,\"cartType\":\"01\",\"categoryCode\":\"2359\",\"createStaff\":182,\"createTime\":1447296167000,\"currentSiteId\":1,\"endRowIndex\":1,\"gdsId\":37537,\"gdsName\":\"学谦的娃娃3\",\"gdsType\":1,\"groupDetail\":\"74827\",\"groupType\":\"0\",\"id\":652,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"orderAmount\":3,\"orderSubScore\":0,\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"promId\":3134,\"score\":0,\"sendOrderSubScoreTimes\":0,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"siteId\":\"1\",\"skuId\":74827,\"skuInfo\":\"优秀/精品\",\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-8886683646487105967\",\"updateStaff\":182,\"updateTime\":1447296167000}],\"pageNo\":1,\"pageSize\":1,\"promId\":3054,\"shopId\":69,\"shopName\":\"测试店铺0011\",\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"0:0:0:0:0:0:0:1:-8886683646487105967\"}}";
        
        ROrdCartCommRequest rOrdCartCommRequest=new ROrdCartCommRequest();
        rOrdCartCommRequest=JSON.parseObject(ss, ROrdCartCommRequest.class);
        
        rOrdCartChangeRequest.setrOrdCartCommRequest(rOrdCartCommRequest);
        
        long t= System.currentTimeMillis();
        System.out.println(t);
        CartPromBeanRespDTO d= promRSV.selectProm(rOrdCartChangeRequest);
        System.out.println("哈哈啥地方哈市的飞洒地方11"+((System.currentTimeMillis()-t)/1000));
    }
   /* @Test
    public void testSaveInfo() {

        PromDTO promDTO = new PromDTO();
        promDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        try {
            QueryPromDTO queryPromDTO=new QueryPromDTO();
            Long shopId=new Long(1);
            BigDecimal gdsId=new BigDecimal(1);
            BigDecimal skuId=new BigDecimal(1);
            queryPromDTO.setShopId(shopId);
            queryPromDTO.setGdsId(gdsId);
            queryPromDTO.setSkuId(skuId);
            queryPromDTO.setDate(new Date());
            List<PromMatchSkuResponseDTO> l=promRSV.queryMatchList(queryPromDTO,null);
            System.out.println("test");
        } catch (BusinessException err) {
            System.out.println(err.getErrorCode() + err.getErrorMessage());
        }

    }*/
  @Test
    public void testInitOrd() {
            OrdPromDTO ordPromDTO = new OrdPromDTO();
            ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            ordPromDTO.setCalDate(new Date());
            ordPromDTO.setCartType("1");
            ordPromDTO.setCreateStaff(new Long(1));
            ordPromDTO.setId(new Long(1));
           // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
           // ordPromDTO.setPromId(null);
            ordPromDTO.setPromInfoDTOList(null);
            ordPromDTO.setPromTypeMsgResponseDTO(null);
            ordPromDTO.setShopId(new Long(1234));
            ordPromDTO.setStaffId("808");
            
            OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
            ordPromDetailDTO.setAddTime(new Date());
            ordPromDetailDTO.setCartId(new Long(1));
            ordPromDetailDTO.setCartType("1");
            ordPromDetailDTO.setCreateStaff("1");
            ordPromDetailDTO.setCreateTime(new Date());
            ordPromDetailDTO.setGdsId(new Long(1008609472));
            ordPromDetailDTO.setGdsName("hahah");
            ordPromDetailDTO.setId(new Long(1));
            ordPromDetailDTO.setOrderAmount(new Long(4));
            ordPromDetailDTO.setBasePrice(new Long(100));
            ordPromDetailDTO.setBuyPrice(new Long(80));
            //ordPromDetailDTO.setPromId(null);
            ordPromDetailDTO.setPromInfoDTOList(null);
            ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
            ordPromDetailDTO.setShopId(new Long(1234));
            ordPromDetailDTO.setSkuId(new Long(1008617911));
            ordPromDetailDTO.setStaffId("808");
            PromInfoDTO promInfoDTO=new PromInfoDTO();
            promInfoDTO.setId(new Long(2070));
            ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
            List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
            ordPromDetailDTOList.add(ordPromDetailDTO);
            
            PromInfoDTO promInfoDTO1=new PromInfoDTO();
            promInfoDTO1.setId(new Long(2090));
            ordPromDTO.setPromInfoDTO(promInfoDTO1);
            
            ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
     /*       PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
           // promRuleCheckDTO.setBuyCnt("1");
            promRuleCheckDTO.setAreaValue("1");
            promRuleCheckDTO.setCalDate(new Date());
            promRuleCheckDTO.setChannelValue("2");
            promRuleCheckDTO.setCustGroupValue("1");
            promRuleCheckDTO.setCustLevelValue("1");
            ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);*/
            ordPromDTO=promRSV.initOrdProm(ordPromDTO);
            System.out.println("test");

    }
    
  /*  @Test
    public void testupdateDeduce() throws BusinessException {
        PromStockLimitDTO promStockLimitDTO=new PromStockLimitDTO();
        promStockLimitDTO.setBuyCnt(new Long(1));
        promStockLimitDTO.setGdsId(new BigDecimal(10000));
        promStockLimitDTO.setPromId(new Long(81));
        promStockLimitDTO.setSkuId(new BigDecimal(11122));
        int k=promRSV.updatePromStockLimitDeduce(promStockLimitDTO);
        assertEquals(1, k);
    }
    
    @Test
    public void testupdateAdd() throws BusinessException {
        PromStockLimitDTO promStockLimitDTO=new PromStockLimitDTO();
        promStockLimitDTO.setBuyCnt(new Long(1));
        promStockLimitDTO.setGdsId(new BigDecimal(10000));
        promStockLimitDTO.setPromId(new Long(81));
        promStockLimitDTO.setSkuId(new BigDecimal(111));
        int k=promRSV.updatePromStockLimitAdd(promStockLimitDTO);
        assertEquals(1, k);
    }*/
 /* @Test
    public void testListPromGds() throws BusinessException {
        PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
        promRuleCheckDTO.setAreaValue("1");
        promRuleCheckDTO.setChannelValue("2");
        promRuleCheckDTO.setCustGroupValue("1");
        promRuleCheckDTO.setCustLevelValue("1");
        promRuleCheckDTO.setCalDate(new Date());
        promRuleCheckDTO.setGdsId(new BigDecimal(1));
         promRuleCheckDTO.setShopId(new Long(1) );
         promRuleCheckDTO.setCustId("1");
         List<PromInfoDTO> l=promRSV.listPromByGds(promRuleCheckDTO);
         System.out.println(l.size());
    }
*/    
    @Test
    public void testlistPromByGdsForSolrGds() {
            QueryPromDTO queryPromDTO=new QueryPromDTO();
            queryPromDTO.setShopId(new Long(35));
            queryPromDTO.setGdsId(new Long(37821));
            queryPromDTO.setSkuId(Long.valueOf(75119));
            queryPromDTO.setCurrentSiteId(Long.valueOf(1));
            queryPromDTO.setSiteId(Long.valueOf(1));
           // List<PromInfoDTO> l=promRSV.listPromByGdsForSolr(queryPromDTO );
            System.out.println("test");
    }

  @Test
  public void ao() {
          PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
          promRuleCheckDTO.setAreaValue("1");
          promRuleCheckDTO.setChannelValue("2");
          promRuleCheckDTO.setCustGroupValue("1");
          promRuleCheckDTO.setCustLevelValue("1");
          promRuleCheckDTO.setCalDate(new Date());
          promRuleCheckDTO.setGdsId(new Long(1));
          promRuleCheckDTO.setSkuId(new Long(1));
          promRuleCheckDTO.setShopId(new Long(1) );
        //  promRuleCheckDTO.setCustId("1");
          promRSV.queryMatchList(promRuleCheckDTO);
          System.out.println("test");
  }
  @Test
  public void testSave() {
      OrdPromDTO ordPromDTO = new OrdPromDTO();
      ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
      ordPromDTO.setCalDate(new Date());
      ordPromDTO.setCartType("1");
      ordPromDTO.setCreateStaff(new Long(1));
      ordPromDTO.setId(new Long(1));
     // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
      //ordPromDTO.setPromId(null);//是否传入促销编码
      ordPromDTO.setPromInfoDTOList(null);
      ordPromDTO.setPromTypeMsgResponseDTO(null);
      ordPromDTO.setShopId(new Long(1234));
      ordPromDTO.setStaffId("1");
      
      OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
      ordPromDetailDTO.setAddTime(new Date());
      ordPromDetailDTO.setCartId(new Long(1));
      ordPromDetailDTO.setCartType("1");
      ordPromDetailDTO.setCreateStaff("1");
      ordPromDetailDTO.setCreateTime(new Date());
      ordPromDetailDTO.setGdsId(new Long(1));
      ordPromDetailDTO.setGdsName("hahah");
      ordPromDetailDTO.setId(new Long(1));
      ordPromDetailDTO.setOrderAmount(new Long(4));
      ordPromDetailDTO.setBasePrice(new Long(100));
      ordPromDetailDTO.setBuyPrice(new Long(80));
      //ordPromDetailDTO.setPromId(null);
      ordPromDetailDTO.setPromInfoDTOList(null);
      ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
      ordPromDetailDTO.setShopId(new Long(1234));
      ordPromDetailDTO.setSkuId(new Long(4));
      ordPromDetailDTO.setStaffId("1");
      //ordPromDetailDTO.setPromId(new Long(79));
      PromInfoDTO promInfoDTO =new PromInfoDTO();
      promInfoDTO.setId(new Long(2002));
      ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
      ordPromDetailDTO.setOrdPromId(new Long(2002));
      List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
      ordPromDetailDTOList.add(ordPromDetailDTO);
      
      ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
      PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
     // promRuleCheckDTO.setBuyCnt("1");
      promRuleCheckDTO.setAreaValue("1");
      promRuleCheckDTO.setCalDate(new Date());
      promRuleCheckDTO.setChannelValue("2");
      promRuleCheckDTO.setCustGroupValue("1");
      promRuleCheckDTO.setCustLevelValue("1");
      ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
      //ordPromDTO.setPromId(new Long(90));
      
      PromInfoDTO promInfoDTO3=new PromInfoDTO();
      promInfoDTO3.setId(new Long(90));
      ordPromDTO.setPromInfoDTO(promInfoDTO3);
      
      //赠品
      PromGiftDTO dto=new PromGiftDTO();
      dto.setPromId(new Long(2002));
      dto.setGiftId(new Long(1001));
      dto.setPresentCnt(new Long(1));
      List<PromGiftDTO> promGiftDTOList=new ArrayList<PromGiftDTO>();
      promGiftDTOList.add(dto);
      ordPromDetailDTO.setPromGiftDTOList(promGiftDTOList);
      List<OrdPromDTO> l=new ArrayList<OrdPromDTO>();
      l.add(ordPromDTO);
      OrdPromListDTO ordPromListDTO=new OrdPromListDTO();
      ordPromListDTO.setOrdPromDTOList(l);
      //promRSV.promOrdSave(ordPromListDTO);
       promRSV.promOrdSaveRollBack(ordPromListDTO);
      System.out.println("test");

} 
  @Test
  public void testInitOrdMap() {
          OrdPromDTO ordPromDTO = new OrdPromDTO();
          ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
          ordPromDTO.setCalDate(new Date());
          ordPromDTO.setCartType("1");
          ordPromDTO.setCreateStaff(new Long(1));
          ordPromDTO.setId(new Long(1));
         // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
         // ordPromDTO.setPromId(null);
          ordPromDTO.setPromInfoDTOList(null);
          ordPromDTO.setPromTypeMsgResponseDTO(null);
          ordPromDTO.setShopId(new Long(1234));
          ordPromDTO.setStaffId("1");
          
          OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
          ordPromDetailDTO.setAddTime(new Date());
          ordPromDetailDTO.setCartId(new Long(1));
          ordPromDetailDTO.setCartType("1");
          ordPromDetailDTO.setCreateStaff("1");
          ordPromDetailDTO.setCreateTime(new Date());
          ordPromDetailDTO.setGdsId(new Long(36981));
          ordPromDetailDTO.setGdsName("hahah");
          ordPromDetailDTO.setId(new Long(1));
          ordPromDetailDTO.setOrderAmount(new Long(40));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO.setPromInfoDTOList(null);
          ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO.setShopId(new Long(1234));
          ordPromDetailDTO.setSkuId(new Long(73901));
          ordPromDetailDTO.setStaffId("1");
          
          
          OrdPromDetailDTO ordPromDetailDTO1=new OrdPromDetailDTO();
          ordPromDetailDTO1.setAddTime(new Date());
          ordPromDetailDTO1.setCartId(new Long(1));
          ordPromDetailDTO1.setCartType("1");
          ordPromDetailDTO1.setCreateStaff("1");
          ordPromDetailDTO1.setCreateTime(new Date());
          ordPromDetailDTO1.setGdsId(new Long(1));
          ordPromDetailDTO1.setGdsName("hahah");
          ordPromDetailDTO1.setId(new Long(2));
          ordPromDetailDTO1.setOrderAmount(new Long(1));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO1.setPromInfoDTOList(null);
          ordPromDetailDTO1.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO1.setShopId(new Long(1234));
          ordPromDetailDTO1.setSkuId(new Long(1));
          ordPromDetailDTO1.setStaffId("1");
          
          
          List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
          ordPromDetailDTOList.add(ordPromDetailDTO);
          ordPromDetailDTOList.add(ordPromDetailDTO1);
          
          ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
          PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
         // promRuleCheckDTO.setBuyCnt("1");
          /*promRuleCheckDTO.setAreaValue("1");
          promRuleCheckDTO.setCalDate(new Date());
          promRuleCheckDTO.setChannelValue("2");
          promRuleCheckDTO.setCustGroupValue("1");
          promRuleCheckDTO.setCustLevelValue("1");*/
          //promRuleCheckDTO.setIfThrows("1");
          ordPromDTO.setSiteId(new Long(1));
          ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
          OrdPromRespDTO map=promRSV.initOrdPromMap(ordPromDTO);
          OrdPromDTO o= map.getOrdPromDTO();
          Map<Long,OrdPromDetailDTO> detailMap=map.getDetailMap();
          OrdPromDetailDTO d=detailMap.get(new Long(1));
          System.out.println("test");

  }
  //捆绑套餐
  @Test
  public void testInitOrdMapBind() {
          OrdPromDTO ordPromDTO = new OrdPromDTO();
          ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
          ordPromDTO.setCalDate(new Date());
          ordPromDTO.setCartType("1");
          ordPromDTO.setCreateStaff(new Long(1));
          ordPromDTO.setId(new Long(1));
         // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
         // ordPromDTO.setPromId(null);
          ordPromDTO.setPromInfoDTOList(null);
          ordPromDTO.setPromTypeMsgResponseDTO(null);
          ordPromDTO.setShopId(new Long(1234));
          ordPromDTO.setStaffId("1");
          
          OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
          ordPromDetailDTO.setAddTime(new Date());
          ordPromDetailDTO.setCartId(new Long(1));
          ordPromDetailDTO.setCartType("1");
          ordPromDetailDTO.setCreateStaff("1");
          ordPromDetailDTO.setCreateTime(new Date());
          ordPromDetailDTO.setGdsId(new Long(36953));
          ordPromDetailDTO.setGdsName("hahah");
          ordPromDetailDTO.setId(new Long(1));
          ordPromDetailDTO.setOrderAmount(new Long(40));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO.setPromInfoDTOList(null);
          ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO.setShopId(new Long(1234));
          ordPromDetailDTO.setSkuId(new Long(73872));
          ordPromDetailDTO.setStaffId("1");
          
          
          OrdPromDetailDTO ordPromDetailDTO1=new OrdPromDetailDTO();
          ordPromDetailDTO1.setAddTime(new Date());
          ordPromDetailDTO1.setCartId(new Long(1));
          ordPromDetailDTO1.setCartType("1");
          ordPromDetailDTO1.setCreateStaff("1");
          ordPromDetailDTO1.setCreateTime(new Date());
          ordPromDetailDTO1.setGdsId(new Long(1));
          ordPromDetailDTO1.setGdsName("hahah");
          ordPromDetailDTO1.setId(new Long(2));
          ordPromDetailDTO1.setOrderAmount(new Long(1));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO1.setPromInfoDTOList(null);
          ordPromDetailDTO1.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO1.setShopId(new Long(1234));
          ordPromDetailDTO1.setSkuId(new Long(1));
          ordPromDetailDTO1.setStaffId("1");
          
          
          List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
          ordPromDetailDTOList.add(ordPromDetailDTO);
          ordPromDetailDTOList.add(ordPromDetailDTO1);
          
          ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
          PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
         // promRuleCheckDTO.setBuyCnt("1");
          /*promRuleCheckDTO.setAreaValue("1");
          promRuleCheckDTO.setCalDate(new Date());
          promRuleCheckDTO.setChannelValue("2");
          promRuleCheckDTO.setCustGroupValue("1");
          promRuleCheckDTO.setCustLevelValue("1");*/
          //promRuleCheckDTO.setIfThrows("1");
          ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
          OrdPromRespDTO map=promRSV.initOrdPromMap(ordPromDTO);
          OrdPromDTO o= map.getOrdPromDTO();
          Map<Long,OrdPromDetailDTO> detailMap=map.getDetailMap();
          OrdPromDetailDTO d=detailMap.get(new Long(1));
          System.out.println("test");

  }
//促销选择
  @Test
  public void testInitOrdMapgdsSelect() {
          OrdPromDTO ordPromDTO = new OrdPromDTO();
          ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
          ordPromDTO.setCalDate(new Date());
          ordPromDTO.setCartType("1");
          ordPromDTO.setCreateStaff(new Long(1));
          ordPromDTO.setId(new Long(1));
         // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
         // ordPromDTO.setPromId(null);
          ordPromDTO.setPromInfoDTOList(null);
          ordPromDTO.setPromTypeMsgResponseDTO(null);
          ordPromDTO.setShopId(new Long(1234));
          ordPromDTO.setStaffId("1");
          
          OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
          ordPromDetailDTO.setAddTime(new Date());
          ordPromDetailDTO.setCartId(new Long(1));
          ordPromDetailDTO.setCartType("1");
          ordPromDetailDTO.setCreateStaff("1");
          ordPromDetailDTO.setCreateTime(new Date());
          ordPromDetailDTO.setGdsId(new Long(36953));
          ordPromDetailDTO.setGdsName("hahah");
          ordPromDetailDTO.setId(new Long(1));
          ordPromDetailDTO.setOrderAmount(new Long(40));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO.setPromInfoDTOList(null);
          ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO.setShopId(new Long(1234));
          ordPromDetailDTO.setSkuId(new Long(73872));
          ordPromDetailDTO.setStaffId("1");
          
          
          OrdPromDetailDTO ordPromDetailDTO1=new OrdPromDetailDTO();
          ordPromDetailDTO1.setAddTime(new Date());
          ordPromDetailDTO1.setCartId(new Long(1));
          ordPromDetailDTO1.setCartType("1");
          ordPromDetailDTO1.setCreateStaff("1");
          ordPromDetailDTO1.setCreateTime(new Date());
          ordPromDetailDTO1.setGdsId(new Long(1));
          ordPromDetailDTO1.setGdsName("hahah");
          ordPromDetailDTO1.setId(new Long(2));
          ordPromDetailDTO1.setOrderAmount(new Long(1));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO1.setPromInfoDTOList(null);
          ordPromDetailDTO1.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO1.setShopId(new Long(1234));
          ordPromDetailDTO1.setSkuId(new Long(1));
          ordPromDetailDTO1.setStaffId("1");
          
          
          List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
          ordPromDetailDTOList.add(ordPromDetailDTO);
          ordPromDetailDTOList.add(ordPromDetailDTO1);
          
          ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
          PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
         // promRuleCheckDTO.setBuyCnt("1");
          /*promRuleCheckDTO.setAreaValue("1");
          promRuleCheckDTO.setCalDate(new Date());
          promRuleCheckDTO.setChannelValue("2");
          promRuleCheckDTO.setCustGroupValue("1");
          promRuleCheckDTO.setCustLevelValue("1");*/
          //promRuleCheckDTO.setIfThrows("1");
          ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
          OrdPromRespDTO map=promRSV.initOrdPromMap(ordPromDTO);
          
          OrdPromDTO ordPromDTO1= map.getOrdPromDTO();
          PromInfoDTO promInfoDTO=new PromInfoDTO();
          promInfoDTO.setId(new Long(2058));
        // promInfoDTO.setId(null);
          ordPromDTO1.getOrdPromDetailDTOList().get(0).setPromInfoDTO(promInfoDTO);
         // ordPromDTO1.setPromInfoDTO(promInfoDTO);
          OrdPromDTO  aa =promRSV.selectPromByGds(ordPromDTO1.getOrdPromDetailDTOList().get(0),ordPromDTO1);
          
          System.out.println("test");

  }
  //订单促销选择
  @Test
  public void testInitOrdMapOrdSelect() {
          OrdPromDTO ordPromDTO = new OrdPromDTO();
          ordPromDTO.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
          ordPromDTO.setCalDate(new Date());
          ordPromDTO.setCartType("1");
          ordPromDTO.setCreateStaff(new Long(1));
          ordPromDTO.setId(new Long(1));
         // ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
         // ordPromDTO.setPromId(null);
          ordPromDTO.setPromInfoDTOList(null);
          ordPromDTO.setPromTypeMsgResponseDTO(null);
          ordPromDTO.setShopId(new Long(1234));
          ordPromDTO.setStaffId("1");
          
          OrdPromDetailDTO ordPromDetailDTO=new OrdPromDetailDTO();
          ordPromDetailDTO.setAddTime(new Date());
          ordPromDetailDTO.setCartId(new Long(1));
          ordPromDetailDTO.setCartType("1");
          ordPromDetailDTO.setCreateStaff("1");
          ordPromDetailDTO.setCreateTime(new Date());
          ordPromDetailDTO.setGdsId(new Long(36953));
          ordPromDetailDTO.setGdsName("hahah");
          ordPromDetailDTO.setId(new Long(1));
          ordPromDetailDTO.setOrderAmount(new Long(40));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO.setPromInfoDTOList(null);
          ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO.setShopId(new Long(1234));
          ordPromDetailDTO.setSkuId(new Long(73872));
          ordPromDetailDTO.setStaffId("1");
          
          
          OrdPromDetailDTO ordPromDetailDTO1=new OrdPromDetailDTO();
          ordPromDetailDTO1.setAddTime(new Date());
          ordPromDetailDTO1.setCartId(new Long(1));
          ordPromDetailDTO1.setCartType("1");
          ordPromDetailDTO1.setCreateStaff("1");
          ordPromDetailDTO1.setCreateTime(new Date());
          ordPromDetailDTO1.setGdsId(new Long(1));
          ordPromDetailDTO1.setGdsName("hahah");
          ordPromDetailDTO1.setId(new Long(2));
          ordPromDetailDTO1.setOrderAmount(new Long(1));
          ordPromDetailDTO.setBasePrice(new Long(100));
          ordPromDetailDTO.setBuyPrice(new Long(80));
          //ordPromDetailDTO.setPromId(null);
          ordPromDetailDTO1.setPromInfoDTOList(null);
          ordPromDetailDTO1.setPromTypeMsgResponseDTO(null);
          ordPromDetailDTO1.setShopId(new Long(1234));
          ordPromDetailDTO1.setSkuId(new Long(1));
          ordPromDetailDTO1.setStaffId("1");
          
          
          List<OrdPromDetailDTO> ordPromDetailDTOList=new ArrayList<OrdPromDetailDTO>();
          ordPromDetailDTOList.add(ordPromDetailDTO);
          ordPromDetailDTOList.add(ordPromDetailDTO1);
          
          ordPromDTO.setOrdPromDetailDTOList(ordPromDetailDTOList);
          PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
         // promRuleCheckDTO.setBuyCnt("1");
          /*promRuleCheckDTO.setAreaValue("1");
          promRuleCheckDTO.setCalDate(new Date());
          promRuleCheckDTO.setChannelValue("2");
          promRuleCheckDTO.setCustGroupValue("1");
          promRuleCheckDTO.setCustLevelValue("1");*/
          //promRuleCheckDTO.setIfThrows("1");
          ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
          OrdPromRespDTO map=promRSV.initOrdPromMap(ordPromDTO);
          
          OrdPromDTO ordPromDTO1= map.getOrdPromDTO();
          PromInfoDTO promInfoDTO=new PromInfoDTO();
          // promInfoDTO.setId(new Long(119));
          promInfoDTO.setId(/*new Long(140*/null);
          //ordPromDTO1.getOrdPromDetailDTOList().get(0).setPromInfoDTO(promInfoDTO);
         ordPromDTO1.setPromInfoDTO(promInfoDTO);
          OrdPromDTO  aa =promRSV.selectPromByOrd(/*new Long(140)*/null,ordPromDTO1);
          
          System.out.println("test");

  }
  
  @Test
  public void listPromByGds() {
      
      PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
      promRuleCheckDTO.setCalDate(new Date());
      promRuleCheckDTO.setGdsId(new Long(1008609460));
      promRuleCheckDTO.setGdsName("gdsName");
      //promRuleCheckDTO.setIfComposit(ifComposit)
      promRuleCheckDTO.setShopId(new Long(1234));
      promRuleCheckDTO.setShopName("shopna,me");
      promRuleCheckDTO.setSiteId(new Long(2));
      promRuleCheckDTO.setStaffId("1");
      List<PromInfoDTO>  list=promRSV.listPromByGds(promRuleCheckDTO);
      System.out.println(list);
  }
  @Test
  public void listPromBySku() {
      
      PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
      promRuleCheckDTO.setCalDate(new Date());
      promRuleCheckDTO.setGdsId(new Long(1008609460));
      promRuleCheckDTO.setGdsName("gdsName");
      promRuleCheckDTO.setSkuId(new Long(1008617883));
      //promRuleCheckDTO.setIfComposit(ifComposit)
      promRuleCheckDTO.setShopId(new Long(1234));
      promRuleCheckDTO.setShopName("shopna,me");
      promRuleCheckDTO.setSiteId(new Long(2));
      promRuleCheckDTO.setStaffId("1");
      List<PromInfoDTO>  list=promRSV.listPromBySku(promRuleCheckDTO);
      System.out.println(list);
  }
  
  
  @Test
  public void testnewInit() {
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
     r.setPromId(new Long(3036));
      r.setShopId(new Long(69));
      r.setShopName("shopName");
      r.setSource("1");
      r.setStaffId(new Long(104));
      
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
      item.setCreateStaff(new Long(104));
      item.setCreateTime(DateUtil.getSysDate());
      item.setCurrentSiteId(r.getCurrentSiteId());
      item.setGdsId(new Long(37312));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(1));
      item.setOrderId(null);
      item.setOrderSubId(null);
     // item.setOrdPromId(new Long(2088));
      item.setPriceType("1");
      item.setPromId(new Long(3038));
      item.setShopId(new Long(69));
      item.setShopName("shopName");
      item.setSkuId(new Long(74560));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      
      CartPromRespDTO d= promRSV.initCartPromMap(rOrdCartsCommRequest);
      System.out.println(d);
      
  }

  @Test
  public void testInitZhuHe() {
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
      r.setShopId(new Long(1234));
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
      item.setGdsId(new Long(1008609407));
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
      item.setShopId(new Long(1234));
      item.setShopName("shopName");
      item.setSkuId(new Long(1008617802));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      
      
      
 ROrdCartItemCommRequest  item1=new ROrdCartItemCommRequest();
      
      ordCartItemCommList.add(item1);
      
      item1.setAddTime(DateUtil.getSysDate());
      item1.setAppName("1");
      item1.setBasePrice(new Long(10023));
      item1.setBuyPrice(new Long(10000));
      item1.setCartId(new Long(2));
      item1.setCartType("1");
      item1.setCategoryCode("111");
      item1.setCreateStaff(new Long(1));
      item1.setCreateTime(DateUtil.getSysDate());
      item1.setCurrentSiteId(r.getCurrentSiteId());
      item1.setGdsId(new Long(1008609407));
      item1.setGdsName("gdsName");
      item1.setGdsType(new Long(1));
      item1.setGroupDetail("setGroupDetail");
      item1.setGroupType("setGroupType");
      item1.setId(new Long(2));
      item1.setOrderAmount(new Long(100));
      item1.setOrderId(null);
      item1.setOrderSubId(null);
     // item1.setOrdPromId(new Long(2088));
      item1.setPriceType("1");
     item1.setPromId(new Long(2033));
      item1.setShopId(new Long(1234));
      item1.setShopName("shopName");
      item1.setSkuId(new Long(1008617802));
      item1.setSkuInfo("sadfsdf");
      item1.setStaffId(r.getStaffId());
      
      CartPromRespDTO d= promRSV.initCartPromMap(rOrdCartsCommRequest);
      System.out.println(d);
      
  }
  
  //测试单品数量变化
  //测试单品 促销id变更
  //测试单品 删除
  //测试单品 取消
  //测试单品 新增
  
  //订单和明细都选择不参与 promId=-1
  @Test
  public void testSkuChange1() {
    
      List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
      ROrdCartCommRequest r=new ROrdCartCommRequest();
      ordCartsCommList.add(r);
      r.setCartType("1");
      r.setCurrentSiteId(1000l);
      r.setId(new Long(1));
      r.setOrderId("orderId1");
      r.setPromId(new Long(-1));
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
      item.setGdsId(new Long(1));
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
      
      item.setPromId(new Long(-1));
      item.setShopId(new Long(35));
      item.setShopName("shopName");
      item.setSkuId(new Long(1));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      
      ROrdCartChangeRequest rOrdCartChangeRequest=new ROrdCartChangeRequest();
      rOrdCartChangeRequest.setrOrdCartCommRequest(r);
      rOrdCartChangeRequest.setrOrdCartItemCommRequest(item);
      CartPromBeanRespDTO d= promRSV.selectProm(rOrdCartChangeRequest);
      System.out.println(d);
      
  }
  
  //订单和明细都选择不参与 promId=3011
  @Test
  public void testSkuChange30112088() {
    
      List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
      ROrdCartCommRequest r=new ROrdCartCommRequest();
      ordCartsCommList.add(r);
      r.setCartType("1");
      r.setCurrentSiteId(1000l);
      r.setId(new Long(1));
      r.setOrderId("orderId1");
      r.setPromId(new Long(2088));
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
      item.setGdsId(new Long(1));
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
      
      item.setPromId(new Long(2108));
      item.setShopId(new Long(35));
      item.setShopName("shopName");
      item.setSkuId(new Long(1));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      
      ROrdCartChangeRequest rOrdCartChangeRequest=new ROrdCartChangeRequest();
      rOrdCartChangeRequest.setrOrdCartCommRequest(r);
      rOrdCartChangeRequest.setrOrdCartItemCommRequest(item);
      CartPromBeanRespDTO d= promRSV.selectProm(rOrdCartChangeRequest);
      System.out.println(d);
      
  }
  
  //选择购物车明细为空
  @Test
  public void testOrdChange() {
    
      List<ROrdCartCommRequest> ordCartsCommList=new ArrayList<ROrdCartCommRequest> ();
      ROrdCartCommRequest r=new ROrdCartCommRequest();
      ordCartsCommList.add(r);
      r.setCartType("1");
      r.setCurrentSiteId(1000l);
      r.setId(new Long(1));
      r.setOrderId("orderId1");
      r.setPromId(new Long(-1));
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
      item.setGdsId(new Long(1));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(1));
      item.setOrderId(null);
      item.setOrderSubId(null);
     // item.setOrdPromId(new Long(2088));
      item.setPriceType("1");
      
      item.setPromId(new Long(-1));
      item.setShopId(new Long(35));
      item.setShopName("shopName");
      item.setSkuId(new Long(1));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      
      ROrdCartChangeRequest rOrdCartChangeRequest=new ROrdCartChangeRequest();
      rOrdCartChangeRequest.setrOrdCartCommRequest(r);
      rOrdCartChangeRequest.setrOrdCartItemCommRequest(null);
      CartPromBeanRespDTO d= promRSV.selectProm(rOrdCartChangeRequest);
      System.out.println(d);
      
  }
  /*
   * 测试 促销库存信息
   */
  @Test
  public void testPromSave(){
      
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
     r.setPromId(new Long(3036));
      r.setShopId(new Long(69));
      r.setShopName("shopName");
      r.setSource("1");
      r.setStaffId(new Long(104));
      
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
      item.setCreateStaff(new Long(104));
      item.setCreateTime(DateUtil.getSysDate());
      item.setCurrentSiteId(r.getCurrentSiteId());
      item.setGdsId(new Long(37312));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(1));
      item.setOrderId(null);
      item.setOrderSubId(null);
     // item.setOrdPromId(new Long(2088));
      item.setPriceType("1");
      item.setPromId(new Long(3038));
      item.setShopId(new Long(69));
      item.setShopName("shopName");
      item.setSkuId(new Long(74560));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      promRSV.promOrdSave(rOrdCartsCommRequest);
  
  }
  /*
   * 测试 促销库存信息 回滚
   */
  @Test
  public void testpromOrdSaveRollBack(){
      
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
     r.setPromId(new Long(3036));
      r.setShopId(new Long(69));
      r.setShopName("shopName");
      r.setSource("1");
      r.setStaffId(new Long(104));
      
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
      item.setCreateStaff(new Long(104));
      item.setCreateTime(DateUtil.getSysDate());
      item.setCurrentSiteId(r.getCurrentSiteId());
      item.setGdsId(new Long(37312));
      item.setGdsName("gdsName");
      item.setGdsType(new Long(1));
      item.setGroupDetail("setGroupDetail");
      item.setGroupType("setGroupType");
      item.setId(new Long(1));
      item.setOrderAmount(new Long(1));
      item.setOrderId(null);
      item.setOrderSubId(null);
     // item.setOrdPromId(new Long(2088));
      item.setPriceType("1");
      item.setPromId(new Long(3038));
      item.setShopId(new Long(69));
      item.setShopName("shopName");
      item.setSkuId(new Long(74560));
      item.setSkuInfo("sadfsdf");
      item.setStaffId(r.getStaffId());
      promRSV.promOrdSaveRollBack(rOrdCartsCommRequest);
  
  }
  
  @Test
  public void testListPromgds() throws BusinessException {
      PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
      promRuleCheckDTO.setAreaValue("140000");
      promRuleCheckDTO.setChannelValue("2");
      promRuleCheckDTO.setCustGroupValue("");
      promRuleCheckDTO.setCustLevelValue("01");
      promRuleCheckDTO.setCalDate(new Date());
      promRuleCheckDTO.setGdsId(new Long(1));
       promRuleCheckDTO.setShopId(new Long(69) );
       promRuleCheckDTO.setSiteId(new Long(1));
       promRuleCheckDTO.setStaffId("123");
       List<PromInfoDTO> l=promRSV.listPromByGds(promRuleCheckDTO);
       System.out.println(l.size());
  }
  
  @Test
  public void testListPromSolr() {
 
		   // 获取促销信息
	       PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
	       CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
	       promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
	       promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
	       promRuleCheckDTO.setGdsId(46709L);
	       promRuleCheckDTO.setChannelValue("1");
	       promRuleCheckDTO.setShopId(100L);
	       promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
	       promRuleCheckDTO.setSkuId(87108L);
	       promRuleCheckDTO.setBasePrice(800L);
	       promRuleCheckDTO.setBuyPrice(100L);
	       promRuleCheckDTO.setShopName("test");
	       
	       List<PromListRespDTO> promInfoDTOList = promRSV.listPromForSolr(promRuleCheckDTO);
           System.out.println("asdf");
           
  }
       
}

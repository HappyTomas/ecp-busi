package com.zengshi.ecp.aip.third.test.service.busi.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.SkuStockThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.aip.third.service.busi.stock.interfaces.IStockSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class StockSVImplTest extends EcpServicesTest {

    @Resource
    private IStockSV defaultStockSV;
    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
        GdsStockThirdReqDTO gdsStockThirdReqDTO=new GdsStockThirdReqDTO();
        gdsStockThirdReqDTO.setAppkey("test");
        gdsStockThirdReqDTO.setAppscret("test");
        gdsStockThirdReqDTO.setServerUrl("https://gw.api.360buy.com/routerjson");
    	
        gdsStockThirdReqDTO.setPlatType(AipThirdConstants.Commons.JD);
    	HashMap value=defaultStockSV.updateStock(gdsStockThirdReqDTO);
    	System.out.println(value);
    }
    //淘宝调用测试
    
    /*{"taobao_user_nick":"sandbox_b_16","re_expires_in":15552000,"expires_in":12960000,"expire_time":1491546898910,"r1_expires_in":12960000,"w2_valid":1478588698910,"w2_expires_in":1800,"taobao_user_id":"2076226623","w1_expires_in":12960000,"r1_valid":1491546898910,"r2_valid":1478846098910,"w1_valid":1491546898910,"r2_expires_in":259200,"token_type":"Bearer","refresh_token":"6200b23261b868173d7da5baf1d8eccegaa579f325c80d22076226623","refresh_token_valid_time":1494138898910,"access_token":"6200623c3399a35d58e68225533178bdfc31708275c7f772076226623"}*/
    @Test
    public void testTaobao() throws BusinessException {
    	 GdsStockThirdReqDTO gdsStockThirdReqDTO=new GdsStockThirdReqDTO();
    	 gdsStockThirdReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	 gdsStockThirdReqDTO.setAppkey("1023521935");
    	 gdsStockThirdReqDTO.setAppscret("sandbox75de5da394607a610715e572a");
    	 gdsStockThirdReqDTO.setServerUrl("https://gw.api.tbsandbox.com/router/rest");
    	 gdsStockThirdReqDTO.setAccessToken("620270160ZZ6bf164137cee6a2290ff49550fdcc0becb092074082786");
    	 
    	 
    	 gdsStockThirdReqDTO.setGdsId(1L);
    	 gdsStockThirdReqDTO.setOutGdsId(2100663786375L);
    	 
    	 List<SkuStockThirdReqDTO> skuInfos=new ArrayList<SkuStockThirdReqDTO>();
    	 
    	 gdsStockThirdReqDTO.setSkuInfos(skuInfos);
    	 
    	 SkuStockThirdReqDTO s1=new SkuStockThirdReqDTO();
    	 s1.setSkuId(123L);
    	 s1.setQuanties(1L);
    	 
    	 SkuStockThirdReqDTO s2=new SkuStockThirdReqDTO();
    	 s2.setSkuId(234L);
    	 s2.setQuanties(2L);
    	 
    	 
    	 skuInfos.add(s1);
    	 skuInfos.add(s2);
    	 
    	 gdsStockThirdReqDTO.setShopId(100L);
    	 HashMap value=defaultStockSV.updateStock(gdsStockThirdReqDTO);
         System.out.println(value);
    }
    //现网测试
    @Test
    public void testTaobaoPrd() throws BusinessException {
    	 GdsStockThirdReqDTO gdsStockThirdReqDTO=new GdsStockThirdReqDTO();
    	 gdsStockThirdReqDTO.setPlatType(AipThirdConstants.Commons.TAOBAO);
    	 gdsStockThirdReqDTO.setAppkey("23521935");
    	 gdsStockThirdReqDTO.setAppscret("bedf75375de5da394607a610715e572a");
    	 gdsStockThirdReqDTO.setServerUrl("https://eco.taobao.com/router/rest");
    	 gdsStockThirdReqDTO.setGdsId(21082L);
    	 gdsStockThirdReqDTO.setOutGdsId(543505844573L);
    	 
    	 List<SkuStockThirdReqDTO> skuInfos=new ArrayList<SkuStockThirdReqDTO>();
    	 
    	 gdsStockThirdReqDTO.setSkuInfos(skuInfos);
    	 gdsStockThirdReqDTO.setQuanties(99L);
    	 SkuStockThirdReqDTO s1=new SkuStockThirdReqDTO();
    	// s1.setSkuId(30110L);
    	 s1.setSkuId(30111L);
    	 s1.setQuanties(100L);
    	 skuInfos.add(s1);
    	 gdsStockThirdReqDTO.setShopId(35L);
    	 HashMap value=defaultStockSV.updateStock(gdsStockThirdReqDTO);
         System.out.println(value);
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	 GdsStockThirdReqDTO gdsStockThirdReqDTO=new GdsStockThirdReqDTO();
         gdsStockThirdReqDTO.setAppkey("8a4602a057fc8f8485");
         gdsStockThirdReqDTO.setAppscret("5b56c136787e54e077f5d780fd0ff076");
         gdsStockThirdReqDTO.setServerUrl("https://open.koudaitong.com/api/entry?");
     	
         gdsStockThirdReqDTO.setPlatType(AipThirdConstants.Commons.YOUZAN);

		gdsStockThirdReqDTO.setOutGdsId(327039524L);
		gdsStockThirdReqDTO.setQuanties(100L);
     	HashMap value=defaultStockSV.updateStock(gdsStockThirdReqDTO);
		LogUtil.info(this.getClass().getName(), JSON.toJSONString(value));
    }
}

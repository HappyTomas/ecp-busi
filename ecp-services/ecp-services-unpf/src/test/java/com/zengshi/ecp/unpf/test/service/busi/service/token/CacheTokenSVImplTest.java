package com.zengshi.ecp.unpf.test.service.busi.service.token;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.SkuInfoThirdReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-6 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CacheTokenSVImplTest  {

    
    //京东调用测试
    @Test
    public void testJd() throws BusinessException {
    	
    	StringBuffer skuIds=new StringBuffer();
    	for(int i=1;i<5;i++){
        	
        	skuIds.append(i+",");
        
    	}
    	if(skuIds!=null && skuIds.length()>0){
    		String aa=skuIds.substring(0, skuIds.toString().lastIndexOf(",")).toString();
        	System.out.println(skuIds.toString());
        	System.out.println(aa);
    	}
    	
    	
    }
    //淘宝调用测试
    
    @Test
    public void testTaobao() throws BusinessException { 
    	
/*    	Object tokenJson = CacheUtil
				.getItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
						+ 100
						+ "taobao");
    	System.out.println(tokenJson.toString());
    	
       	
    	Object tokenJson1 = CacheUtil
				.getItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
						+ 100
						+ "taobao7");
    	System.out.println(tokenJson1.toString());*/
    	
/*    	Object tokenJson = CacheUtil
				.getItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
						+ 100
						+ "taobao");
    	System.out.println(tokenJson.toString());
    	
    	CacheUtil
		.addItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
				+ 100
				+ "taobao"+7,tokenJson.toString(),12222222);*/
   	
 
    	
/*     	String aa="{\"taobao_user_nick\":\"人民卫生出版社旗舰店\",\"re_expires_in\":15552000,\"expires_in\":12960000,\"expire_time\":1492843418713,\"r1_expires_in\":12960000,\"w2_valid\":1479885218713,\"w2_expires_in\":1800,\"taobao_user_id\":\"2074082786\",\"w1_expires_in\":12960000,\"r1_valid\":1492843418713,\"r2_valid\":1480142618713,\"w1_valid\":1492843418713,\"r2_expires_in\":259200,\"token_type\":\"Bearer\",\"refresh_token\":\"6201029e3ea0e8f180775e26b46e93c2d85b3ZZ073f8acb1125014918\",\"refresh_token_valid_time\":1495435418713,\"access_token\":\"6200a295f2b4f71c36689147d16f1cc290c21ZZ7b1a9fbe1125014918\"}";
    	
    	CacheUtil
		.addItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
				+ 100
				+ "taobao8",aa,1222222222);*/
    
    	
     	String aa="{\"taobao_user_nick\":\"sandbox_c_10\",\"re_expires_in\":15552000,\"expires_in\":12960000,\"expire_time\":1492843418713,\"r1_expires_in\":12960000,\"w2_valid\":1479885218713,\"w2_expires_in\":1800,\"taobao_user_id\":\"2074082786\",\"w1_expires_in\":12960000,\"r1_valid\":1492843418713,\"r2_valid\":1480142618713,\"w1_valid\":1492843418713,\"r2_expires_in\":259200,\"token_type\":\"Bearer\",\"refresh_token\":\"620012720a598f7302aca91740967368d36ZZb2d7a841db2054631247\",\"refresh_token_valid_time\":1495435418713,\"access_token\":\"62027276dd107560c4e8d424b3d34ca70eceg202ce157e22054631247\"}";
    	
    	CacheUtil
		.addItem(AipThirdConstants.Commons.THIRD_TOKEN_KEY
				+ 96
				+ "taobao44",aa,1222222222);
    
    	
    	
    	
    	
    }
    //有赞调用测试
    @Test
    public void testYouzan() throws BusinessException {
    	String name="福州市";
    	name=StringUtils.removeEnd(name, "市");
    	
    	String name1="福州";
    	name1=StringUtils.removeEnd(name1, "市");
    	
    	String name2="市福市哈哈州";
    	name2=StringUtils.removeEnd(name2, "市");
    	
    	System.out.println(name+name1+name2);
    }
}

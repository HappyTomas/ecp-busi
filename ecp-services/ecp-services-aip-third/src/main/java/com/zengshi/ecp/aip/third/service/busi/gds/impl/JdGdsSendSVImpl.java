package com.zengshi.ecp.aip.third.service.busi.gds.impl;

import java.util.HashMap;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
/*import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.request.ware.WareAddRequest;
import com.jd.open.api.sdk.response.ware.WareAddResponse;*/

public class JdGdsSendSVImpl implements IGdsSendSV{
    
    public static final String MODULE = JdGdsSendSVImpl.class.getName();

    @Override
    public HashMap send(GdsSendThirdReqDTO gdsSendReqDTO) throws BusinessException {/*
    	
    	String accessToken=gdsSendReqDTO.getAccessToken();//??????//每个接口不一样还是 都一样
    	
    	JdClient client=new DefaultJdClient(gdsSendReqDTO.getServerUrl(),accessToken,gdsSendReqDTO.getAppKey(),gdsSendReqDTO.getAppScret());

    	WareAddRequest request=new WareAddRequest();

    	request.setTradeNo( "jingdong" );
    	request.setWareLocation( "jingdong" );
    	request.setCid( "jingdong" );
    	request.setShopCategory( "jingdong" );
    	request.setTitle( "jingdong" );
    	request.setUpcCode( "jingdong" );
    	request.setOptionType( "jingdong" );
    	request.setItemNum( "jingdong" );
    	request.setStockNum( "jingdong" );
    	request.setProducter( "jingdong" );
    	request.setWrap( "jingdong" );
    	request.setLength( "jingdong" );
    	request.setWide( "jingdong" );
    	request.setHigh( "jingdong" );
    	request.setWeight( "jingdong" );
    	request.setCostPrice( "jingdong" );
    	request.setMarketPrice( "jingdong" );
    	request.setJdPrice( "jingdong" );
    	request.setNotes( "jingdong" );
    	request.setPackListing( "jingdong" );
    	request.setService( "jingdong" );
    	request.setSkuProperties( "jingdong" );
    	request.setAttributes( "jingdong" );
    	request.setSkuPrices( "jingdong" );
    	request.setSkuStocks( "jingdong" );
    	request.setPropertyAlias( "jingdong" );
    	request.setOuterId( "jingdong" );
    	request.setShelfLifeDays( "jingdong" );
    	request.setWareBigSmallModel( "jingdong" );
    	request.setWarePackType( "jingdong" );
    	request.setInputPids( "jingdong" );
    	request.setInputStrs( "jingdong" );
    	request.setHasCheckCode( "jingdong" );
    	request.setAdContent( "jingdong" );
    	request.setListTime( "jingdong" );

    	try {
			WareAddResponse response=client.execute(request);
		} catch (Exception e) {
            LogUtil.error(MODULE, e.toString());
			throw new BusinessException(e.toString());
		}
    	return "这个是京东同步接口";
    */
    	
    	return null;
    }
}


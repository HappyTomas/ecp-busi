package com.zengshi.ecp.aip.third.service.busi.stock.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.SkuStockThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.stock.interfaces.IStockSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TmallItemQuantityUpdateRequest;
import com.taobao.api.request.TmallItemQuantityUpdateRequest.UpdateSkuQuantity;
import com.taobao.api.response.TmallItemQuantityUpdateResponse;

public class TaobaoStockSVImpl implements IStockSV{
    
    public static final String MODULE = TaobaoStockSVImpl.class.getName();

    @Resource
	private ITokenSV defaultTokenSV;
    
    @Override
    public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException{
    	
    	
    	TaobaoClient client = new DefaultTaobaoClient(gdsStockThirdReqDTO.getServerUrl(), gdsStockThirdReqDTO.getAppkey(), gdsStockThirdReqDTO.getAppscret());
    	 
    	TmallItemQuantityUpdateRequest req = new TmallItemQuantityUpdateRequest();
    	req.setItemId(gdsStockThirdReqDTO.getOutGdsId());
    	if(gdsStockThirdReqDTO.getQuanties()!=null){
    		req.setItemQuantity(gdsStockThirdReqDTO.getQuanties());
    	}
    	if(CollectionUtils.isNotEmpty(gdsStockThirdReqDTO.getSkuInfos())){
    		List<UpdateSkuQuantity> usList=new ArrayList<UpdateSkuQuantity>();
    		for(SkuStockThirdReqDTO s:gdsStockThirdReqDTO.getSkuInfos()){
    			UpdateSkuQuantity u=new UpdateSkuQuantity();
    			u.setOuterId(s.getSkuId().toString());
    			u.setQuantity(s.getQuanties());
    			u.setSkuId(s.getOutSkuId());
    			usList.add(u);
    		}
    		req.setSkuQuantities(usList);
    	}
    	HashMap map=new HashMap();
    	TmallItemQuantityUpdateResponse rsp;
		try {
			//验证参数是否正确
			req.check();
			//获得token
			String token=defaultTokenSV.fetchShopToken(gdsStockThirdReqDTO.findBaseShopAuth());
			
			rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) { 
				map.put("body", rsp.getBody());
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}

    	return map;
    }
   /* 
    @Override
    public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException{
    	
    	
    	TaobaoClient client = new DefaultTaobaoClient(gdsStockThirdReqDTO.getServerUrl(), gdsStockThirdReqDTO.getAppkey(), gdsStockThirdReqDTO.getAppscret());
    	 
    	SkusQuantityUpdateRequest req = new SkusQuantityUpdateRequest();
    	
    	req.setNumIid(gdsStockThirdReqDTO.getOutGdsId());
    	req.setType(gdsStockThirdReqDTO.getType());
    	 //默认使用该方式 如果为空 使用outeridQuantities方式
    	if(CollectionUtils.isNotEmpty(gdsStockThirdReqDTO.getSkuInfos())){
    		StringBuffer outeridQuantities=new StringBuffer();
    		for(SkuStockThirdReqDTO s:gdsStockThirdReqDTO.getSkuInfos()){
    			outeridQuantities.append(s.getSkuId()).append(":").append(s.getQuanties()).append(";");
    		}
    		outeridQuantities.deleteCharAt(outeridQuantities.lastIndexOf(";"));
    		req.setOuteridQuantities(outeridQuantities.toString());
    		//req.setSkuidQuantities(outeridQuantities.toString());
    	}
    	HashMap map=new HashMap();
    	SkusQuantityUpdateResponse rsp;
		try {
			//验证参数是否正确
			req.check();
			//获得token
			String token=defaultTokenSV.fetchShopToken(gdsStockThirdReqDTO.findBaseShopAuth());
			
			//有些方法不需要传入token 具体查看每个api的头部说明  一般和当前店铺有关系的都需要token
			rsp = client.execute(req, token);
			// 返回结果解析
			if (rsp.isSuccess()) { 
				map.put("body", rsp.getBody());
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}

    	return map;
    }*/
}


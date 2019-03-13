package com.zengshi.ecp.aip.third.service.busi.stock.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.aip.third.dubbo.dto.resp.YZGdsResp;
import com.zengshi.ecp.aip.third.service.busi.utils.SchemaUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;

import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsSendThirdReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.GdsStockThirdReqDTO;
import com.zengshi.ecp.aip.third.service.busi.client.youzan.KdtApiClient;
import com.zengshi.ecp.aip.third.service.busi.gds.interfaces.IGdsSendSV;
import com.zengshi.ecp.aip.third.service.busi.stock.interfaces.IStockSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

public class YouzanStockSVImpl implements IStockSV{
    
    public static final String MODULE = YouzanStockSVImpl.class.getName();

    private static final String method="kdt.item.update";
    
    @Override
    public HashMap updateStock(GdsStockThirdReqDTO gdsStockThirdReqDTO)throws BusinessException{

		String value="这个是有赞修改库存接口";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("num_iid",""+gdsStockThirdReqDTO.getOutGdsId());
		params.put("quantity",""+gdsStockThirdReqDTO.getQuanties());

		KdtApiClient kdtApiClient;
		HttpResponse response;

		try {
			LogUtil.error(MODULE, "====-----===="+ JSON.toJSONString(gdsStockThirdReqDTO));
			kdtApiClient = new KdtApiClient(gdsStockThirdReqDTO.getAppkey(), gdsStockThirdReqDTO.getAppscret());
			response = kdtApiClient.post(method, params, null, null);
			LogUtil.error(MODULE,"Response Code : " + response.getStatusLine().getStatusCode());
			if(200 == response.getStatusLine().getStatusCode()){
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					result.append(line);
				}
				YZGdsResp resp = JSON.parseObject(SchemaUtil.decodeUnicode(result.toString()), YZGdsResp.class);
				LogUtil.error(MODULE,"result:"+ JSON.toJSONString(resp));
				if(resp.getError_response() != null) {
					LogUtil.error(MODULE,"error_response: ");
					throw  new BusinessException("AIPTHIRD.100026");
				} else {
					LogUtil.error(MODULE,"response: " + result.toString());
					if(resp.getResponse() != null){
						HashMap  resultMap=new HashMap();
						resultMap.put("gdsId",resp.getResponse().getItem().getNum_iid());
						return  resultMap;
					} else {
						throw  new BusinessException("AIPTHIRD.100026");
					}
				}
			}

		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.100026");
		}
		return null;
    }
}


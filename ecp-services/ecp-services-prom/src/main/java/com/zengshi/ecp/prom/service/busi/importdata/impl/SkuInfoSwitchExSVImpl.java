package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zengshi.ecp.prom.service.busi.importdata.interfaces.ISkuInfoSwitchExSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

public class SkuInfoSwitchExSVImpl implements ISkuInfoSwitchExSV {

	private String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";

	@Override
	public String switchSkuId(List<Object> row) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String skuId = "";
		// 单品编码
		boolean item0 = row.get(0).toString().matches(regex);
		if (!(item0)) {
			throw new BusinessException("prom.400188", new String[] { row.get(0).toString() });
		}
		skuId = row.get(0).toString();
		if (skuId.indexOf(".") > 0) {
			skuId = skuId.replaceAll("0+?$", "");// 去掉多余的0
			skuId = skuId.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return skuId;
	}

	@Override
	public  Map<String,Object> filterDatas(List<List<Object>> datas) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String, Object>();
		 List<List<Object>> resultDatas = new ArrayList<List<Object>>();
	        resultDatas.clear();
	        //方案2：根据单品id进行过滤
	        Set<String> hashSet = new HashSet<String>();
	        for(Iterator<List<Object>> iter = datas.iterator();iter.hasNext();)
	        {
	            List<Object> row = iter.next();
	            if(hashSet.add(String.valueOf(row.get(0))))
	            {
	                resultDatas.add(row);
	            }
	        }
	        map.put("totalCount", datas.size());
	        map.put("repeatCount", datas.size() - resultDatas.size());
	        map.put("resultDatas", resultDatas);
	        return map;
	}

	@Override
	public void checkedRow(List<Object> row) {
		// TODO Auto-generated method stub
		
	}

}

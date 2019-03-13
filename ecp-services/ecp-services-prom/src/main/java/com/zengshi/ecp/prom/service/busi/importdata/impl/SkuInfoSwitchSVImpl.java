package com.zengshi.ecp.prom.service.busi.importdata.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.prom.service.busi.importdata.interfaces.ISkuInfoSwitchExSV;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.ISkuInfoSwitchSV;

public class SkuInfoSwitchSVImpl implements ISkuInfoSwitchSV{

	@Autowired
	private ISkuInfoSwitchExSV skuInfoSwitchExSV;
	
	@Override
	public String switchSkuId(List<Object> row) {
		// TODO Auto-generated method stub		
		return skuInfoSwitchExSV.switchSkuId(row);
	}

	@Override
	public Map<String,Object> filterDatas(List<List<Object>> datas) {
		// TODO Auto-generated method stub
		return skuInfoSwitchExSV.filterDatas(datas);
	}
	
	@Override
	public void checkedRow(List<Object> row){
		skuInfoSwitchExSV.checkedRow(row);
	}
	
	

}

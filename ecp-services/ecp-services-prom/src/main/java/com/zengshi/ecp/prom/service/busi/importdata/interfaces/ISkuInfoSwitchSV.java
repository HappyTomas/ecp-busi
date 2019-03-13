package com.zengshi.ecp.prom.service.busi.importdata.interfaces;

import java.util.List;
import java.util.Map;

public interface ISkuInfoSwitchSV {
	
	/**
	 * 单品编号转换
	 * @param row
	 * @return
	 */
	public String switchSkuId(List<Object> row);
	
	public Map<String,Object> filterDatas(List<List<Object>> datas);
	
	public void checkedRow(List<Object> row);
}

package com.zengshi.ecp.busi.search.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by HDF on 2016/7/4.
 */
public class ScrollResult {

    private long total;

    private List<Map<String,Object>> datas;

    private String shopId="";

    private String category;
    
    private String adid;

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}
}

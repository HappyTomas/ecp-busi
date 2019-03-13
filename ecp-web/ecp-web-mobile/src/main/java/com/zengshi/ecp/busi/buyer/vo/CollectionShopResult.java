package com.zengshi.ecp.busi.buyer.vo;

import java.util.List;

/**
 * Created by HDF on 2016/7/4.
 */
public class CollectionShopResult {

    private long total;

    private List<CollectionShopInfoVO> datas;

	public List<CollectionShopInfoVO> getDatas() {
		return datas;
	}

	public void setDatas(List<CollectionShopInfoVO> datas) {
		this.datas = datas;
	}

	public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

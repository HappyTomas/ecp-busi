package com.zengshi.ecp.busi.buyer.vo;

import java.util.List;


/**
 * Created by HDF on 2016/7/4.
 */
public class MsgScrollResult {

    private long total;

    private List<MsgInsiteVO> datas;

	public List<MsgInsiteVO> getDatas() {
		return datas;
	}

	public void setDatas(List<MsgInsiteVO> datas) {
		this.datas = datas;
	}

	public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

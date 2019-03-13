package com.zengshi.ecp.busi.buyer.vo;

import java.util.List;

/**
 * Created by HDF on 2016/7/4.
 */
public class ScoreScrollResult {

    private long total;

    private List<ScoreDetailVO> datas;

   

    public List<ScoreDetailVO> getDatas() {
		return datas;
	}

	public void setDatas(List<ScoreDetailVO> datas) {
		this.datas = datas;
	}

	public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

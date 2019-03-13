package com.zengshi.ecp.busi.buyer.vo;

import java.util.List;

public class FavGdsResult {

    private long total;

    private List<GdsCollectRespVO> datas;

    public List<GdsCollectRespVO> getDatas() {
        return datas;
    }

    public void setDatas(List<GdsCollectRespVO> datas) {
        this.datas = datas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}


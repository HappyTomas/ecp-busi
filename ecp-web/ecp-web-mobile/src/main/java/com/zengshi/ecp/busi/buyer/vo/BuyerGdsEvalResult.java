package com.zengshi.ecp.busi.buyer.vo;

import java.util.List;

public class BuyerGdsEvalResult {
    private long total;

    private List<EvalutionVO> datas;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<EvalutionVO> getDatas() {
        return datas;
    }

    public void setDatas(List<EvalutionVO> datas) {
        this.datas = datas;
    }
    
}


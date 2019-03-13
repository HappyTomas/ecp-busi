package com.zengshi.ecp.busi.shop.vo;

import java.io.Serializable;
import java.util.List;

public class PromScollerResult implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3413326184639015596L;
    
    private long total;

    private List<PromSkuRespVO> datas;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<PromSkuRespVO> getDatas() {
        return datas;
    }

    public void setDatas(List<PromSkuRespVO> datas) {
        this.datas = datas;
    }

}


package com.zengshi.ecp.busi.shop.vo;

import java.io.Serializable;
import java.util.List;

public class ShopScrollResult implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6047585426048650106L;

    private long total;

    private List<ShopSearchResult> datas;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ShopSearchResult> getDatas() {
        return datas;
    }

    public void setDatas(List<ShopSearchResult> datas) {
        this.datas = datas;
    }
    
    
}


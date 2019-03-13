package com.zengshi.ecp.search.test.search;

public class GDSSearchResult {
    
    private String gdsName;
    private Long gdsNameBoost;

    //商城页面的三个排序字段
    private Long sales;
    private Long discountPrice;
    private String newProductTime;
    
    private String discount;
    
    private String score;

    @Override
    public String toString() {
        return "GDSSearchResult [gdsName=" + gdsName + ", gdsNameBoost=" + gdsNameBoost
                + ", sales=" + sales + ", discountPrice=" + discountPrice + ", newProductTime="
                + newProductTime + ", discount=" + discount + ", score=" + score + "]";
    }
    
}


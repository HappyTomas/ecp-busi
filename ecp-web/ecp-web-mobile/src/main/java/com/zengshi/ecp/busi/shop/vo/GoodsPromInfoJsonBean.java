package com.zengshi.ecp.busi.shop.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GoodsPromInfoJsonBean implements Serializable {
    /**
     * 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -4437507618919287377L;
    //促销列表信息
    private List<String> promTypes = new ArrayList<String>();
    //第一条促销信息对应的价格
    private BigDecimal price;
    //是否已经收藏
    private String ifHavFav ;
    //经过促销那边计算出来的指导价（划横线的价格）
    private BigDecimal guidePrice;
    
    public List<String> getPromTypes() {
        return promTypes;
    }

    public void setPromTypes(List<String> promTypes) {
        this.promTypes = promTypes;
    }

    public String getIfHavFav() {
        return ifHavFav;
    }

    public void setIfHavFav(String ifHavFav) {
        this.ifHavFav = ifHavFav;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal bigDecimal) {
        this.price = bigDecimal;
    }
    
    public BigDecimal getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(BigDecimal guidePrice) {
        this.guidePrice = guidePrice;
    }
}

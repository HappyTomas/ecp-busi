package com.zengshi.ecp.busi.goods.vo;

import java.io.Serializable;

public class GdsSkuMediaVO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6093863200620810188L;
    /**
     * 经过压缩后的小图片
     */
    private String url;
    /**
     * 经过压缩后的展示一张的图片url
     */
    private String middleUrl;
    /**
     * 经过压缩后的展示一张图片中放大后的图片
     */
    private String bigUrl;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMiddleUrl() {
        return middleUrl;
    }
    public void setMiddleUrl(String middleUrl) {
        this.middleUrl = middleUrl;
    }
    public String getBigUrl() {
        return bigUrl;
    }
    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }
    
}


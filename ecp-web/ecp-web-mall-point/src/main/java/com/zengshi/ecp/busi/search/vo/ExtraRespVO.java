package com.zengshi.ecp.busi.search.vo;

import java.io.Serializable;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月14日下午5:05:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version @param <T>
 * @since JDK 1.6
 */
public class ExtraRespVO implements Serializable {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -2004460624500515441L;
    
    private String keyword;
    
    private String searchCategory;
    
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }
    
}

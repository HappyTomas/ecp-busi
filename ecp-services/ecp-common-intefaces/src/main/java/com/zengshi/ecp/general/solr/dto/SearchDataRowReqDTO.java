package com.zengshi.ecp.general.solr.dto;


public class SearchDataRowReqDTO extends SearchDataReqDTO {

    private static final long serialVersionUID = -1748700280889061697L;
    
    //联合主键使用英文逗号间隔
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}


package com.zengshi.ecp.general.solr.dto;


public class SearchDataDupPagerRowRowReqDTO extends SearchDataRowReqDTO {

    private static final long serialVersionUID = -1748700280889061697L;
    
    //联合主键使用英文逗号间隔
    private String insideId;

    public String getInsideId() {
        return insideId;
    }

    public void setInsideId(String insideId) {
        this.insideId = insideId;
    }

}


package com.zengshi.ecp.general.solr.dto;


public class SearchDataDupPagerPageReqDTO extends SearchDataPageReqDTO {

    private static final long serialVersionUID = -1748700280889061697L;
    
    private int insideDbIndex=1;
    
    private int insidePageSize;
    
    private int insidePageNo=1;
    
    public int getInsidePageSize() {
        return insidePageSize;
    }

    public void setInsidePageSize(int insidePageSize) {
        this.insidePageSize = insidePageSize;
    }

    public int getInsidePageNo() {
        return insidePageNo;
    }

    public void setInsidePageNo(int insidePageNo) {
        this.insidePageNo = insidePageNo;
    }

    public int getInsideDbIndex() {
        return insideDbIndex;
    }

    public void setInsideDbIndex(int insideDbIndex) {
        this.insideDbIndex = insideDbIndex;
    }

}


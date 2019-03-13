package com.zengshi.ecp.general.solr.dto;


public class SearchDataPageReqDTO extends SearchDataReqDTO {

    private static final long serialVersionUID = 1L;
    
    /**
     * 数据库索引，从1开始
     */
    private int dbIndex=1;
    
    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }
}


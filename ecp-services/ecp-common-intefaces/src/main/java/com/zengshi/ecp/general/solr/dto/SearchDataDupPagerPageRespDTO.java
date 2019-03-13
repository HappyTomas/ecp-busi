package com.zengshi.ecp.general.solr.dto;


public class SearchDataDupPagerPageRespDTO extends SearchDataPageRespDTO {

    private static final long serialVersionUID = -2536806329404454691L;
    
    /**
     * 内部分页是否完成//业务处理设置字段
     */
    private boolean isInsidePagerOver=false;
    
    /**
     * 内部分库分表扫是否完成//业务处理设置字段
     */
    private boolean isInsideDbIndexOver=false;
    
    public boolean isInsidePagerOver() {
        return isInsidePagerOver;
    }

    public void setInsidePagerOver(boolean isInsidePagerOver) {
        this.isInsidePagerOver = isInsidePagerOver;
    }

    public boolean isInsideDbIndexOver() {
        return isInsideDbIndexOver;
    }

    public void setInsideDbIndexOver(boolean isInsideDbIndexOver) {
        this.isInsideDbIndexOver = isInsideDbIndexOver;
    }

}


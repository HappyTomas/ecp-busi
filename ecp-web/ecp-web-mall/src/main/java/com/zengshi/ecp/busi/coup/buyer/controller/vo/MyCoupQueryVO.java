package com.zengshi.ecp.busi.coup.buyer.controller.vo;

import java.util.Map;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class MyCoupQueryVO extends EcpBasePageReqVO {

    private static final long serialVersionUID = 1L;
    
    private String queryType;//1已使用 2未使用  0过期
    
    private Map<String,String> mapSort;//排序字段

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public Map<String,String> getMapSort() {
        return mapSort;
    }

    public void setMapSort(Map<String,String> mapSort) {
        this.mapSort = mapSort;
    }
     
}


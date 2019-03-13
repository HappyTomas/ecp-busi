package com.zengshi.ecp.general.solr.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class SearchDataReqDTO extends BaseInfo{

    private static final long serialVersionUID = 1L;
    
    /**
     * 配置参数
     */
    private String jsonParams;
    
    /**
     * 扩展参数
     */
    private Map<String,Object> xParams;

    public String getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(String jsonParams) {
        this.jsonParams = jsonParams;
    }

    public Map<String, Object> getxParams() {
        return xParams;
    }

    public void setxParams(Map<String, Object> xParams) {
        this.xParams = xParams;
    }
    
}


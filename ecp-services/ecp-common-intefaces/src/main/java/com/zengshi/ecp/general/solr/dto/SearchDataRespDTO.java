package com.zengshi.ecp.general.solr.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class SearchDataRespDTO extends BaseResponseDTO{

    private static final long serialVersionUID = 1L;
    
    /**
     * 扩展参数
     */
    private Map<String,Object> xParams;

    public Map<String, Object> getxParams() {
        return xParams;
    }

    public void setxParams(Map<String, Object> xParams) {
        this.xParams = xParams;
    }
    
}


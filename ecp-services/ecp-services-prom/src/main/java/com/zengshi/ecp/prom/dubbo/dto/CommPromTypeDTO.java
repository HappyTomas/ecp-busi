package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 * @param <T>
 * @param <T>
 */
public class CommPromTypeDTO extends BaseInfo{

    private static final long serialVersionUID = 1L;
    
    private Map<String,CommPromTypeDTO> promTypeMap;

    public Map<String,CommPromTypeDTO> getPromTypeMap() {
        return promTypeMap;
    }

    public void setPromTypeMap(Map<String,CommPromTypeDTO> promTypeMap) {
        this.promTypeMap = promTypeMap;
    }
    //获得促销类
    public CommPromTypeDTO translate(String key){
        return  promTypeMap.get(key);
    }

}

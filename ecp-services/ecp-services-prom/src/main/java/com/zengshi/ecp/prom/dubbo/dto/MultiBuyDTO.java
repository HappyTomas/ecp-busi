package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Map;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-8 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 * @param <T>
 */
public class MultiBuyDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;
    /*
     * key:购买数量 value :价格
     * 4个30元  5个35元 10个80元
     */
    private Map<String,String> multiMap;

    public Map<String,String> getMultiMap() {
        return multiMap;
    }

    public void setMultiMap(Map<String,String> multiMap) {
        this.multiMap = multiMap;
    }

}

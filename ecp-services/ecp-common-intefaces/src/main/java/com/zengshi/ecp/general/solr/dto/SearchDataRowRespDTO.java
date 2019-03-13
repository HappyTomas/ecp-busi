package com.zengshi.ecp.general.solr.dto;

import java.util.Map;

public class SearchDataRowRespDTO extends SearchDataRespDTO {

    private static final long serialVersionUID = -2536806329404454691L;
    
    //联合主键使用英文逗号间隔存在搜索引擎定义的唯一索引字段
    private Map<String,Object> data;
    
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}


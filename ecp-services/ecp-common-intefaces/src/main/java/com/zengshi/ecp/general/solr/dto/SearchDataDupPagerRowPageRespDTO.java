package com.zengshi.ecp.general.solr.dto;

import java.util.List;
import java.util.Map;

public class SearchDataDupPagerRowPageRespDTO extends SearchDataRespDTO {

    private static final long serialVersionUID = -2536806329404454691L;
    
    /**
     * 内部分页是否完成//业务处理设置字段
     */
    private boolean isInsidePagerOver=false;
    
    /**
     * 内部分库分表扫是否完成//业务处理设置字段
     */
    private boolean isInsideDbIndexOver=false;
    
    //联合主键使用英文逗号间隔存在搜索引擎定义的唯一索引字段
    private List<Map<String,Object>> dataList;

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

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


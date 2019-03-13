package com.zengshi.ecp.general.solr.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchDataPageRespDTO extends SearchDataRespDTO {

    private static final long serialVersionUID = -2536806329404454691L;
    
    /**
     * map key为搜索数据对象字段中配置的属性名
     * 联合主键使用英文逗号间隔存在搜索引擎定义的唯一索引字段
     */
    private List<Map<String,Object>> dataList;
    
    /**
     * 外部分页是否完成//业务处理设置字段
     */
    private boolean isPagerOver=false;
    
    /**
     * 外部分库分表扫是否完成//业务处理设置字段
     */
    private boolean isDbIndexOver=false;
    
    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }
    
    public void add(Map<String,Object> map){
        if(this.dataList==null){
            this.dataList=new ArrayList<Map<String,Object>>(); 
        }
        dataList.add(map);
    }

    public boolean isPagerOver() {
        return isPagerOver;
    }

    public void setPagerOver(boolean isPagerOver) {
        this.isPagerOver = isPagerOver;
    }

    public boolean isDbIndexOver() {
        return isDbIndexOver;
    }

    public void setDbIndexOver(boolean isDbIndexOver) {
        this.isDbIndexOver = isDbIndexOver;
    }

}


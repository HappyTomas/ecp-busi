package com.zengshi.ecp.search.index;

import java.util.ArrayList;
import java.util.List;

public class IndexResult {

    private long dataListCount=0l;
    private long failCount=0l;
    /**
     * 索引录入失败记录详细信息（不保留全部，字段空间受限）
     */
    /*private List<String> failRecordList= new ArrayList<String>();*/
    /**
     * id列表（不保留全部，字段空间受限）
     */
    private List<String> failIdList=new ArrayList<String>();;
    
    public void increaseFailCountBy(long step){
        this.failCount+=step;
    }
    
    /*public void addFailRecord(String failRecord){
        this.failRecordList.add(failRecord);
    }*/
    
    public void addFailId(String failId){
        this.failIdList.add(failId);
    }
    
    public long getDataListCount() {
        return dataListCount;
    }
    public void setDataListCount(long dataListCount) {
        this.dataListCount = dataListCount;
    }
    public long getFailCount() {
        return failCount;
    }
    public void setFailCount(long failCount) {
        this.failCount = failCount;
    }
    /*public List<String> getFailRecordList() {
        return failRecordList;
    }
    public void setFailRecordList(List<String> failRecordList) {
        this.failRecordList = failRecordList;
    }*/
    public List<String> getFailIdList() {
        return failIdList;
    }
    public void setFailIdList(List<String> failIdList) {
        this.failIdList = failIdList;
    }
    
}


package com.zengshi.ecp.general.solr.message;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.solr.util.DeltaUtils.EDeltaType;
import com.zengshi.ecp.general.solr.util.DeltaUtils.EOperType;

public class DeltaMessage implements Serializable{
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6407833427830311324L;
    /**
     * 增量消息ID，用于保障幂等性。
     */
    private String deltaMsgId;

    private String deltaType;
    
    private String operType;
    
    private String objectNameEn;
    
    private List<String> ids;//用于批量增量
    
    private List<String> insideIds;//用于批量增量
    
    private List<Map<String,Object>> dataList;//用于批量增量
    /**
     * 是否需要重试
     */
    private boolean needRetry = false;
    /**
     * 重试次数    
     */
    private int maxRetry = 3;
    /**
     * 重试间隔单位秒。
     */
    private long retryIntervalSec = 3;
    /**
     * 重试次数。
     */
    private int retryCnt = 0;
    

    public String getDeltaType() {
        return deltaType;
    }

    public void setDeltaType_(EDeltaType deltaType) {
        this.deltaType = deltaType.getType();
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType_(EOperType operType) {
        this.operType = operType.getType();
    }

    public String getObjectNameEn() {
        return objectNameEn;
    }

    public void setObjectNameEn(String objectNameEn) {
        this.objectNameEn = objectNameEn;
    }

    public void setDeltaType(String deltaType) {
        this.deltaType = deltaType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getInsideIds() {
        return insideIds;
    }

    public void setInsideIds(List<String> insideIds) {
        this.insideIds = insideIds;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

    public boolean isNeedRetry() {
        return needRetry;
    }

    public void setNeedRetry(boolean needRetry) {
        this.needRetry = needRetry;
    }

    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    

    public long getRetryIntervalSec() {
        return retryIntervalSec;
    }

    public void setRetryIntervalSec(long retryIntervalSec) {
        this.retryIntervalSec = retryIntervalSec;
    }

    public String getDeltaMsgId() {
        return deltaMsgId;
    }

    public void setDeltaMsgId(String deltaMsgId) {
        this.deltaMsgId = deltaMsgId;
    }

    public int getRetryCnt() {
        return retryCnt;
    }

    public void setRetryCnt(int retryCnt) {
        this.retryCnt = retryCnt;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeltaMessage [deltaMsgId=");
        builder.append(deltaMsgId);
        builder.append(", deltaType=");
        builder.append(deltaType);
        builder.append(", operType=");
        builder.append(operType);
        builder.append(", objectNameEn=");
        builder.append(objectNameEn);
        builder.append(", ids=");
        builder.append(ids);
        builder.append(", insideIds=");
        builder.append(insideIds);
        builder.append(", dataList=");
        builder.append(dataList);
        builder.append(", needRetry=");
        builder.append(needRetry);
        builder.append(", maxRetry=");
        builder.append(maxRetry);
        builder.append(", retryIntervalSec=");
        builder.append(retryIntervalSec);
        builder.append(", retryCnt=");
        builder.append(retryCnt);
        builder.append("]");
        return builder.toString();
    }
    
    
    

}


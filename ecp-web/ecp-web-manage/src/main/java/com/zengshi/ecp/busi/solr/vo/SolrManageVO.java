package com.zengshi.ecp.busi.solr.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SolrManageVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 2303134656330013455L;
    
    private Long id;

    private String configName;

    private String configDesc;

    private String configCollectionName;

    private String configQueryOp;
    
    private String configQueryIfHl;

    private String configQueryHlPre;

    private String configQueryHlPost;

    private String configQueryIfSpellcheck;

    private Short configQuerySpellcheckCount;

    private String collectionStatus;

    private String indexStatus;

    private String configIfActive;

    private String status;
    /**
     * 操作类型。1：启用；0：禁用
     */
    private String operateType;
    /**
     * 重建索引前是否进行清除数据。1：清除；0：不清楚
     */
    private String clear;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getConfigCollectionName() {
        return configCollectionName;
    }

    public void setConfigCollectionName(String configCollectionName) {
        this.configCollectionName = configCollectionName;
    }

    public String getConfigQueryOp() {
        return configQueryOp;
    }

    public void setConfigQueryOp(String configQueryOp) {
        this.configQueryOp = configQueryOp;
    }

    public String getConfigQueryIfHl() {
        return configQueryIfHl;
    }

    public void setConfigQueryIfHl(String configQueryIfHl) {
        this.configQueryIfHl = configQueryIfHl;
    }

    public String getConfigQueryHlPre() {
        return configQueryHlPre;
    }

    public void setConfigQueryHlPre(String configQueryHlPre) {
        this.configQueryHlPre = configQueryHlPre;
    }

    public String getConfigQueryHlPost() {
        return configQueryHlPost;
    }

    public void setConfigQueryHlPost(String configQueryHlPost) {
        this.configQueryHlPost = configQueryHlPost;
    }

    public String getConfigQueryIfSpellcheck() {
        return configQueryIfSpellcheck;
    }

    public void setConfigQueryIfSpellcheck(String configQueryIfSpellcheck) {
        this.configQueryIfSpellcheck = configQueryIfSpellcheck;
    }

    public Short getConfigQuerySpellcheckCount() {
        return configQuerySpellcheckCount;
    }

    public void setConfigQuerySpellcheckCount(Short configQuerySpellcheckCount) {
        this.configQuerySpellcheckCount = configQuerySpellcheckCount;
    }

    public String getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(String collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

    public String getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(String indexStatus) {
        this.indexStatus = indexStatus;
    }

    public String getConfigIfActive() {
        return configIfActive;
    }

    public void setConfigIfActive(String configIfActive) {
        this.configIfActive = configIfActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }
    
}


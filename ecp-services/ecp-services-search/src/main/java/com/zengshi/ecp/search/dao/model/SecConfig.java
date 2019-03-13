package com.zengshi.ecp.search.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SecConfig implements Serializable {
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

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String configIfMultilan;

    private String lans;

    private String configParams;

    private String lanField;

    private String lanFieldFieldType;

    private static final long serialVersionUID = 1L;

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
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc == null ? null : configDesc.trim();
    }

    public String getConfigCollectionName() {
        return configCollectionName;
    }

    public void setConfigCollectionName(String configCollectionName) {
        this.configCollectionName = configCollectionName == null ? null : configCollectionName.trim();
    }

    public String getConfigQueryOp() {
        return configQueryOp;
    }

    public void setConfigQueryOp(String configQueryOp) {
        this.configQueryOp = configQueryOp == null ? null : configQueryOp.trim();
    }

    public String getConfigQueryIfHl() {
        return configQueryIfHl;
    }

    public void setConfigQueryIfHl(String configQueryIfHl) {
        this.configQueryIfHl = configQueryIfHl == null ? null : configQueryIfHl.trim();
    }

    public String getConfigQueryHlPre() {
        return configQueryHlPre;
    }

    public void setConfigQueryHlPre(String configQueryHlPre) {
        this.configQueryHlPre = configQueryHlPre == null ? null : configQueryHlPre.trim();
    }

    public String getConfigQueryHlPost() {
        return configQueryHlPost;
    }

    public void setConfigQueryHlPost(String configQueryHlPost) {
        this.configQueryHlPost = configQueryHlPost == null ? null : configQueryHlPost.trim();
    }

    public String getConfigQueryIfSpellcheck() {
        return configQueryIfSpellcheck;
    }

    public void setConfigQueryIfSpellcheck(String configQueryIfSpellcheck) {
        this.configQueryIfSpellcheck = configQueryIfSpellcheck == null ? null : configQueryIfSpellcheck.trim();
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
        this.collectionStatus = collectionStatus == null ? null : collectionStatus.trim();
    }

    public String getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(String indexStatus) {
        this.indexStatus = indexStatus == null ? null : indexStatus.trim();
    }

    public String getConfigIfActive() {
        return configIfActive;
    }

    public void setConfigIfActive(String configIfActive) {
        this.configIfActive = configIfActive == null ? null : configIfActive.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getConfigIfMultilan() {
        return configIfMultilan;
    }

    public void setConfigIfMultilan(String configIfMultilan) {
        this.configIfMultilan = configIfMultilan == null ? null : configIfMultilan.trim();
    }

    public String getLans() {
        return lans;
    }

    public void setLans(String lans) {
        this.lans = lans == null ? null : lans.trim();
    }

    public String getConfigParams() {
        return configParams;
    }

    public void setConfigParams(String configParams) {
        this.configParams = configParams == null ? null : configParams.trim();
    }

    public String getLanField() {
        return lanField;
    }

    public void setLanField(String lanField) {
        this.lanField = lanField == null ? null : lanField.trim();
    }

    public String getLanFieldFieldType() {
        return lanFieldFieldType;
    }

    public void setLanFieldFieldType(String lanFieldFieldType) {
        this.lanFieldFieldType = lanFieldFieldType == null ? null : lanFieldFieldType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", configName=").append(configName);
        sb.append(", configDesc=").append(configDesc);
        sb.append(", configCollectionName=").append(configCollectionName);
        sb.append(", configQueryOp=").append(configQueryOp);
        sb.append(", configQueryIfHl=").append(configQueryIfHl);
        sb.append(", configQueryHlPre=").append(configQueryHlPre);
        sb.append(", configQueryHlPost=").append(configQueryHlPost);
        sb.append(", configQueryIfSpellcheck=").append(configQueryIfSpellcheck);
        sb.append(", configQuerySpellcheckCount=").append(configQuerySpellcheckCount);
        sb.append(", collectionStatus=").append(collectionStatus);
        sb.append(", indexStatus=").append(indexStatus);
        sb.append(", configIfActive=").append(configIfActive);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", configIfMultilan=").append(configIfMultilan);
        sb.append(", lans=").append(lans);
        sb.append(", configParams=").append(configParams);
        sb.append(", lanField=").append(lanField);
        sb.append(", lanFieldFieldType=").append(lanFieldFieldType);
        sb.append("]");
        return sb.toString();
    }
}
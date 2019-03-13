package com.zengshi.ecp.search.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SecSolrServer implements Serializable {
    private Long id;

    private String solrserverIp;

    private Integer solrserverPort;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private String ifMaster;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolrserverIp() {
        return solrserverIp;
    }

    public void setSolrserverIp(String solrserverIp) {
        this.solrserverIp = solrserverIp == null ? null : solrserverIp.trim();
    }

    public Integer getSolrserverPort() {
        return solrserverPort;
    }

    public void setSolrserverPort(Integer solrserverPort) {
        this.solrserverPort = solrserverPort;
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

    public String getIfMaster() {
        return ifMaster;
    }

    public void setIfMaster(String ifMaster) {
        this.ifMaster = ifMaster == null ? null : ifMaster.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", solrserverIp=").append(solrserverIp);
        sb.append(", solrserverPort=").append(solrserverPort);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ifMaster=").append(ifMaster);
        sb.append("]");
        return sb.toString();
    }
}
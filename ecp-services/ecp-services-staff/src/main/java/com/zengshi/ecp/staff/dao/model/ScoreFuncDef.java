package com.zengshi.ecp.staff.dao.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ScoreFuncDef implements Serializable,Comparable<ScoreFuncDef> {
    private Long funcId;

    private String funcName;

    private String actionType;

    private String scoreType;

    private Short calPri;

    private String status;

    private String remark;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String classBeanId;

    private static final long serialVersionUID = 1L;
    /** 
     * TODO 判断优先级大小. 
     * @see java.lang.Comparable#compareTo(java.lang.Object) 
     */
    @Override
    public int compareTo(ScoreFuncDef fun) {
        return this.calPri - fun.getCalPri();
    }
    
    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType == null ? null : scoreType.trim();
    }

    public Short getCalPri() {
        return calPri;
    }

    public void setCalPri(Short calPri) {
        this.calPri = calPri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getClassBeanId() {
        return classBeanId;
    }

    public void setClassBeanId(String classBeanId) {
        this.classBeanId = classBeanId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", funcId=").append(funcId);
        sb.append(", funcName=").append(funcName);
        sb.append(", actionType=").append(actionType);
        sb.append(", scoreType=").append(scoreType);
        sb.append(", calPri=").append(calPri);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", classBeanId=").append(classBeanId);
        sb.append("]");
        return sb.toString();
    }


}
package com.zengshi.ecp.search.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class SecField implements Serializable {
    private Long id;

    private Long objectId;

    private String fieldBeanFieldName;

    private String fieldTypeName;

    private String fieldIndexName;

    private String fieldNamecn;

    private String fieldComment;

    private String fieldIfBelongtoDf;

    private String fieldProcessorName;

    private String fieldIfMutlivalue;

    private String fieldIfFacet;

    private String fieldIfSpellcheck;

    private String fieldIfHlfield;

    private String fieldSort;

    private String fieldIfSortcust;

    private BigDecimal fieldBoost;

    private String fieldIfMultilan;

    private String status;

    private Long createStaff;

    private Timestamp createTime;

    private Long updateStaff;

    private Timestamp updateTime;

    private Short fieldSortNum;

    private String fieldParams;

    private Short fieldInitSort;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getFieldBeanFieldName() {
        return fieldBeanFieldName;
    }

    public void setFieldBeanFieldName(String fieldBeanFieldName) {
        this.fieldBeanFieldName = fieldBeanFieldName == null ? null : fieldBeanFieldName.trim();
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName == null ? null : fieldTypeName.trim();
    }

    public String getFieldIndexName() {
        return fieldIndexName;
    }

    public void setFieldIndexName(String fieldIndexName) {
        this.fieldIndexName = fieldIndexName == null ? null : fieldIndexName.trim();
    }

    public String getFieldNamecn() {
        return fieldNamecn;
    }

    public void setFieldNamecn(String fieldNamecn) {
        this.fieldNamecn = fieldNamecn == null ? null : fieldNamecn.trim();
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment == null ? null : fieldComment.trim();
    }

    public String getFieldIfBelongtoDf() {
        return fieldIfBelongtoDf;
    }

    public void setFieldIfBelongtoDf(String fieldIfBelongtoDf) {
        this.fieldIfBelongtoDf = fieldIfBelongtoDf == null ? null : fieldIfBelongtoDf.trim();
    }

    public String getFieldProcessorName() {
        return fieldProcessorName;
    }

    public void setFieldProcessorName(String fieldProcessorName) {
        this.fieldProcessorName = fieldProcessorName == null ? null : fieldProcessorName.trim();
    }

    public String getFieldIfMutlivalue() {
        return fieldIfMutlivalue;
    }

    public void setFieldIfMutlivalue(String fieldIfMutlivalue) {
        this.fieldIfMutlivalue = fieldIfMutlivalue == null ? null : fieldIfMutlivalue.trim();
    }

    public String getFieldIfFacet() {
        return fieldIfFacet;
    }

    public void setFieldIfFacet(String fieldIfFacet) {
        this.fieldIfFacet = fieldIfFacet == null ? null : fieldIfFacet.trim();
    }

    public String getFieldIfSpellcheck() {
        return fieldIfSpellcheck;
    }

    public void setFieldIfSpellcheck(String fieldIfSpellcheck) {
        this.fieldIfSpellcheck = fieldIfSpellcheck == null ? null : fieldIfSpellcheck.trim();
    }

    public String getFieldIfHlfield() {
        return fieldIfHlfield;
    }

    public void setFieldIfHlfield(String fieldIfHlfield) {
        this.fieldIfHlfield = fieldIfHlfield == null ? null : fieldIfHlfield.trim();
    }

    public String getFieldSort() {
        return fieldSort;
    }

    public void setFieldSort(String fieldSort) {
        this.fieldSort = fieldSort == null ? null : fieldSort.trim();
    }

    public String getFieldIfSortcust() {
        return fieldIfSortcust;
    }

    public void setFieldIfSortcust(String fieldIfSortcust) {
        this.fieldIfSortcust = fieldIfSortcust == null ? null : fieldIfSortcust.trim();
    }

    public BigDecimal getFieldBoost() {
        return fieldBoost;
    }

    public void setFieldBoost(BigDecimal fieldBoost) {
        this.fieldBoost = fieldBoost;
    }

    public String getFieldIfMultilan() {
        return fieldIfMultilan;
    }

    public void setFieldIfMultilan(String fieldIfMultilan) {
        this.fieldIfMultilan = fieldIfMultilan == null ? null : fieldIfMultilan.trim();
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

    public Short getFieldSortNum() {
        return fieldSortNum;
    }

    public void setFieldSortNum(Short fieldSortNum) {
        this.fieldSortNum = fieldSortNum;
    }

    public String getFieldParams() {
        return fieldParams;
    }

    public void setFieldParams(String fieldParams) {
        this.fieldParams = fieldParams == null ? null : fieldParams.trim();
    }

    public Short getFieldInitSort() {
        return fieldInitSort;
    }

    public void setFieldInitSort(Short fieldInitSort) {
        this.fieldInitSort = fieldInitSort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", objectId=").append(objectId);
        sb.append(", fieldBeanFieldName=").append(fieldBeanFieldName);
        sb.append(", fieldTypeName=").append(fieldTypeName);
        sb.append(", fieldIndexName=").append(fieldIndexName);
        sb.append(", fieldNamecn=").append(fieldNamecn);
        sb.append(", fieldComment=").append(fieldComment);
        sb.append(", fieldIfBelongtoDf=").append(fieldIfBelongtoDf);
        sb.append(", fieldProcessorName=").append(fieldProcessorName);
        sb.append(", fieldIfMutlivalue=").append(fieldIfMutlivalue);
        sb.append(", fieldIfFacet=").append(fieldIfFacet);
        sb.append(", fieldIfSpellcheck=").append(fieldIfSpellcheck);
        sb.append(", fieldIfHlfield=").append(fieldIfHlfield);
        sb.append(", fieldSort=").append(fieldSort);
        sb.append(", fieldIfSortcust=").append(fieldIfSortcust);
        sb.append(", fieldBoost=").append(fieldBoost);
        sb.append(", fieldIfMultilan=").append(fieldIfMultilan);
        sb.append(", status=").append(status);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", fieldSortNum=").append(fieldSortNum);
        sb.append(", fieldParams=").append(fieldParams);
        sb.append(", fieldInitSort=").append(fieldInitSort);
        sb.append("]");
        return sb.toString();
    }
}
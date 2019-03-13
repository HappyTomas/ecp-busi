package com.zengshi.ecp.busi.solr.vo;

import java.math.BigDecimal;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class SolrFieldVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -441720222911369998L;
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
    
    private String fieldSortNum;

    private String fieldIfSortcust;

    private BigDecimal fieldBoost;

    private String fieldIfMultilan;

    private String status;

    private Long createStaff;

    private Long updateStaff;

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

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

	public String getFieldSortNum() {
		return fieldSortNum;
	}

	public void setFieldSortNum(String fieldSortNum) {
		this.fieldSortNum = fieldSortNum;
	}
    
}


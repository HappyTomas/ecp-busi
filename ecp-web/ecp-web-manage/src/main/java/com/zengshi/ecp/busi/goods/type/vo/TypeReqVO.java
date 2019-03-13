package com.zengshi.ecp.busi.goods.type.vo;

import java.sql.Timestamp;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class TypeReqVO extends EcpBasePageReqVO{

    /** 
     * serialVersionUID:
     */ 
    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String typeName;
    
    private String typeCode;

    private String typeDesc;
    
    private Integer sortNo;

    private Long typeOrd;

    private String status;
    
    private String ifEntitycode;
    
    private String ifNeedstock;

    private String ifFree;

    private String ifBuyonce;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc == null ? null : typeDesc.trim();
    }
    
    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getTypeOrd() {
        return typeOrd;
    }

    public void setTypeOrd(Long typeOrd) {
        this.typeOrd = typeOrd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    
    public String getIfEntitycode() {
        return ifEntitycode;
    }

    public void setIfEntitycode(String ifEntitycode) {
        this.ifEntitycode = ifEntitycode == null ? null : ifEntitycode.trim();
    }
    
    public String getIfNeedstock() {
        return ifNeedstock;
    }

    public void setIfNeedstock(String ifNeedstock) {
        this.ifNeedstock = ifNeedstock == null ? null : ifNeedstock.trim();
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree == null ? null : ifFree.trim();
    }

    public String getIfBuyonce() {
        return ifBuyonce;
    }

    public void setIfBuyonce(String ifBuyonce) {
        this.ifBuyonce = ifBuyonce == null ? null : ifBuyonce.trim();
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

}


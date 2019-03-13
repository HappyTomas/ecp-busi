package com.zengshi.ecp.unpf.dubbo.dto;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class UnpfCatgRelaRespDTO extends BaseResponseDTO{
    private Long id;

    private Long shopAuthId;

    private String platType;

    private Long shopId;

    private String catgCodeName;
    
    private String catgCode;

    private String outCatgCode;
    
    private String outCatgCodeName;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopAuthId() {
        return shopAuthId;
    }

    public void setShopAuthId(Long shopAuthId) {
        this.shopAuthId = shopAuthId;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType == null ? null : platType.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getOutCatgCode() {
        return outCatgCode;
    }

    public void setOutCatgCode(String outCatgCode) {
        this.outCatgCode = outCatgCode == null ? null : outCatgCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getCatgCodeName() {
        return catgCodeName;
    }

    public void setCatgCodeName(String catgCodeName) {
        this.catgCodeName = catgCodeName;
    }

    public String getOutCatgCodeName() {
        return outCatgCodeName;
    }

    public void setOutCatgCodeName(String outCatgCodeName) {
        this.outCatgCodeName = outCatgCodeName;
    }


}


package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月17日 下午2:49:47 
* @version 1.0 
**/
public class GdsSendBaseRespDTO extends BaseResponseDTO{

    private Long id;

    private String platType;

    private Long shopId;

    private String outCatgCode;

    private Long gdsId;

    private String action;

    private String status;

    private BigDecimal sendCnt;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private Long shopAuthId;

    private String outGdsId;

    private static final long serialVersionUID = -1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOutCatgCode() {
        return outCatgCode;
    }

    public void setOutCatgCode(String outCatgCode) {
        this.outCatgCode = outCatgCode == null ? null : outCatgCode.trim();
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public BigDecimal getSendCnt() {
        return sendCnt;
    }

    public void setSendCnt(BigDecimal sendCnt) {
        this.sendCnt = sendCnt;
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

    public Long getShopAuthId() {
        return shopAuthId;
    }

    public void setShopAuthId(Long shopAuthId) {
        this.shopAuthId = shopAuthId;
    }

    public String getOutGdsId() {
        return outGdsId;
    }

    public void setOutGdsId(String outGdsId) {
        this.outGdsId = outGdsId == null ? null : outGdsId.trim();
    }
}



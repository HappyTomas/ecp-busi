package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
* @author  lisp: 
* @date 创建时间：2016年11月19日 上午10:25:49 
* @version 1.0 
**/
public class UnpfSendLogReqDTO extends BaseInfo{

    private Long id;

    private Long shopAuthId;

    private Long sendId;

    private String platType;

    private Long shopId;

    private Long gdsId;

    private Long skuId;

    private String action;

    private Timestamp createTime;

    private Long createStaff;

    private String outGdsId;

    private String errors;

    private static final long serialVersionUID = -1L;

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

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
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

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
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

    public String getOutGdsId() {
        return outGdsId;
    }

    public void setOutGdsId(String outGdsId) {
        this.outGdsId = outGdsId == null ? null : outGdsId.trim();
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors == null ? null : errors.trim();
    }
}



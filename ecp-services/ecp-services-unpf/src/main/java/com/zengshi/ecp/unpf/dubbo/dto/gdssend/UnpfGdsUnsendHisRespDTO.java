package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月21日 下午7:49:34 
* @version 1.0 
**/
public class UnpfGdsUnsendHisRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = -1L;

    private Long id;

    private Long shopId;

    private Long gdsId;

    private String status;

    private Timestamp createTimeOld;

    private Long createStaffOld;

    private Timestamp createTime;

    private Long createStaff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Timestamp getCreateTimeOld() {
        return createTimeOld;
    }

    public void setCreateTimeOld(Timestamp createTimeOld) {
        this.createTimeOld = createTimeOld;
    }

    public Long getCreateStaffOld() {
        return createStaffOld;
    }

    public void setCreateStaffOld(Long createStaffOld) {
        this.createStaffOld = createStaffOld;
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


}



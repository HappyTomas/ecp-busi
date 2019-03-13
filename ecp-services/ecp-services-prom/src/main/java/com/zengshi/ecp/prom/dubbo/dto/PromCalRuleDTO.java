package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromCalRuleDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long promId;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    private String calStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public String getCalStr() {
        return calStr;
    }

    public void setCalStr(String calStr) {
        this.calStr = calStr;
    }

}
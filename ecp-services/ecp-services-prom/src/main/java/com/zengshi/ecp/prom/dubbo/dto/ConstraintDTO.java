package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class ConstraintDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long promId;

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }
 
}
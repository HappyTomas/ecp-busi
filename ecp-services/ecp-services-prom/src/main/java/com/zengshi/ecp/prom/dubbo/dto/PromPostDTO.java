package com.zengshi.ecp.prom.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-08-30 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromPostDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private List<Long> promIds;

	public List<Long> getPromIds() {
		return promIds;
	}

	public void setPromIds(List<Long> promIds) {
		this.promIds = promIds;
	}
}

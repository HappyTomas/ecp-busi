package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-08-30 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromPostRespDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private String ifFreePost;

	public String getIfFreePost() {
		return ifFreePost;
	}

	public void setIfFreePost(String ifFreePost) {
		this.ifFreePost = ifFreePost;
	}
 
}

package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsSendListReqDTO extends BaseInfo{
	
	 private static final long serialVersionUID = -1L;
	 
	 private List<GdsSendReqDTO> sends;

	public List<GdsSendReqDTO> getSends() {
		return sends;
	}

	public void setSends(List<GdsSendReqDTO> sends) {
		this.sends = sends;
	}
}

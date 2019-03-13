package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsSendListRespDTO  extends BaseResponseDTO {
	
	 private static final long serialVersionUID = -1L;
	 
	 private List<GdsSendRespDTO> sendResult;

	public List<GdsSendRespDTO> getSendResult() {
		return sendResult;
	}

	public void setSendResult(List<GdsSendRespDTO> sendResult) {
		this.sendResult = sendResult;
	}
 
	  
}

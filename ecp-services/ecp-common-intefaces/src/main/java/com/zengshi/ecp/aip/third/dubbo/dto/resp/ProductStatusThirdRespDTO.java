package com.zengshi.ecp.aip.third.dubbo.dto.resp;


import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class ProductStatusThirdRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
	//状态1 表示可以发布商品  状态 其他有状态的情况 不能发布商品。 
	private String status="0";
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}


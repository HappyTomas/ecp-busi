package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class CatgsRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
    
	private List<CatgRespDTO> catgs;

	public List<CatgRespDTO> getCatgs() {
		return catgs;
	}

	public void setCatgs(List<CatgRespDTO> catgs) {
		this.catgs = catgs;
	}
 
}


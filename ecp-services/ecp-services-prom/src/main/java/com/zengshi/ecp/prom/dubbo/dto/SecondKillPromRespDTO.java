package com.zengshi.ecp.prom.dubbo.dto;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年10月27日 上午11:08:04 
* @version 1.0 
* @parameter  
* @since  
* @return  */
public class SecondKillPromRespDTO extends BaseInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PromInfoResponseDTO  promInfoResponseDTO;
	
	private  PageResponseDTO<KillGdsInfoDTO> page;


	public PageResponseDTO<KillGdsInfoDTO> getPage() {
		return page;
	}

	public void setPage(PageResponseDTO<KillGdsInfoDTO> page) {
		this.page = page;
	}

	public PromInfoResponseDTO getPromInfoResponseDTO() {
		return promInfoResponseDTO;
	}

	public void setPromInfoResponseDTO(PromInfoResponseDTO promInfoResponseDTO) {
		this.promInfoResponseDTO = promInfoResponseDTO;
	}

}



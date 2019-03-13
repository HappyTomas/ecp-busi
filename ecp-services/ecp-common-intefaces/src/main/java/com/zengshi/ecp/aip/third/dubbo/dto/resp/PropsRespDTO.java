package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * 
 * @version  
 * @since JDK 1.7
 */
public class PropsRespDTO extends BaseResponseDTO {

	private static final long serialVersionUID = 1L;
    
	private List<PropRespDTO> props;

	public List<PropRespDTO> getProps() {
		return props;
	}

	public void setProps(List<PropRespDTO> props) {
		this.props = props;
	}
 
 
}


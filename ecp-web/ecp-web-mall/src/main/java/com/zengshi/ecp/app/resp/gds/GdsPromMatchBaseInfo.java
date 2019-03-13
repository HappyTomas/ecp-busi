package com.zengshi.ecp.app.resp.gds;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class GdsPromMatchBaseInfo extends IBody{
    
    private PromBaseInfo promInfoDTO;
    
    private List<GdsPromMatchSkuBaseInfo> gdsPromMatchSkuVOList;

	public PromBaseInfo getPromInfoDTO() {
		return promInfoDTO;
	}

	public void setPromInfoDTO(PromBaseInfo promInfoDTO) {
		this.promInfoDTO = promInfoDTO;
	}

	public List<GdsPromMatchSkuBaseInfo> getGdsPromMatchSkuVOList() {
		return gdsPromMatchSkuVOList;
	}

	public void setGdsPromMatchSkuVOList(List<GdsPromMatchSkuBaseInfo> gdsPromMatchSkuVOList) {
		this.gdsPromMatchSkuVOList = gdsPromMatchSkuVOList;
	}
}


package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Prom005Resp extends IBody{
	List<PromSkuRespVO> resList;

	public List<PromSkuRespVO> getResList() {
		return resList;
	}

	public void setResList(List<PromSkuRespVO> resList) {
		this.resList = resList;
	}

}

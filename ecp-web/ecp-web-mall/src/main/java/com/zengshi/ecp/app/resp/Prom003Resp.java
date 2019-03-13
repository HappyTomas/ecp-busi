package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Prom003Resp extends IBody{
	List<PromListRespVO> resList;

	public List<PromListRespVO> getResList() {
		return resList;
	}

	public void setResList(List<PromListRespVO> resList) {
		this.resList = resList;
	}

}

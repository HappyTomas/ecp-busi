package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Prom001Resp extends IBody{

	List<PromInfoRespVO> resList;

	public List<PromInfoRespVO> getResList() {
		return resList;
	}

	public void setResList(List<PromInfoRespVO> resList) {
		this.resList = resList;
	}
}

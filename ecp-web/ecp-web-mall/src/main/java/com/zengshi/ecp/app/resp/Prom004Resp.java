package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Prom004Resp extends IBody{
	List<PromTypeRespVO> resList;

	public List<PromTypeRespVO> getResList() {
		return resList;
	}

	public void setResList(List<PromTypeRespVO> resList) {
		this.resList = resList;
	}

}

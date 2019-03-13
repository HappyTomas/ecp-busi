package com.zengshi.ecp.app.resp;


import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Prom006Resp extends IBody{

	List<KillPromInfoRespVO> resList;

	public List<KillPromInfoRespVO> getResList() {
		return resList;
	}

	public void setResList(List<KillPromInfoRespVO> resList) {
		this.resList = resList;
	}
	
}

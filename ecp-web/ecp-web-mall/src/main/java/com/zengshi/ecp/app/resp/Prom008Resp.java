package com.zengshi.ecp.app.resp;


import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.butterfly.app.model.IBody;

public class Prom008Resp extends IBody{

	List<KillGdsInfoRespVO> killGdsList;
	
	
	public List<KillGdsInfoRespVO> getKillGdsList() {
		return killGdsList;
	}

	public void setKillGdsList(List<KillGdsInfoRespVO> killGdsList) {
		this.killGdsList = killGdsList;
	}
	
}

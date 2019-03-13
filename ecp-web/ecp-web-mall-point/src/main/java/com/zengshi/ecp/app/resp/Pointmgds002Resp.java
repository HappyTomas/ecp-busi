package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.PointGdsDetailBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Pointmgds002Resp extends IBody {
    
    private List<PointGdsDetailBaseInfo> rankGdsList;

	public List<PointGdsDetailBaseInfo> getRankGdsList() {
		return rankGdsList;
	}

	public void setRankGdsList(List<PointGdsDetailBaseInfo> rankGdsList) {
		this.rankGdsList = rankGdsList;
	} 
    


    
}


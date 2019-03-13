package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.PointGdsPropBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Pointmgds014Resp extends IBody {

    private List<PointGdsPropBaseInfo> propList;

	public List<PointGdsPropBaseInfo> getPropList() {
		return propList;
	}

	public void setPropList(List<PointGdsPropBaseInfo> propList) {
		this.propList = propList;
	}


	
	
	
}


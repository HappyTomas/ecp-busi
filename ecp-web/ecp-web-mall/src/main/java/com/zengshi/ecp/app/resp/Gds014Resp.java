package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsPropBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds014Resp extends IBody {

    private List<GdsPropBaseInfo> propList;

	public List<GdsPropBaseInfo> getPropList() {
		return propList;
	}

	public void setPropList(List<GdsPropBaseInfo> propList) {
		this.propList = propList;
	}


	
	
	
}


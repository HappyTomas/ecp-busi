package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.butterfly.app.model.IBody;

public class Gds011Resp extends IBody {
	public List<CollationReuslt> getCollationReuslts() {
		return collationReuslts;
	}

	public void setCollationReuslts(List<CollationReuslt> collationReuslts) {
		this.collationReuslts = collationReuslts;
	}

	private List<CollationReuslt> collationReuslts ;
	
	
}


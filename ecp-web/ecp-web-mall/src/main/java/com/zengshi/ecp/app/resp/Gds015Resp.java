package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsEvalReplyRespBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds015Resp extends IBody {

    private List<GdsEvalReplyRespBaseInfo> evalReplyInfoList;

	public List<GdsEvalReplyRespBaseInfo> getEvalReplyInfoList() {
		return evalReplyInfoList;
	}

	public void setEvalReplyInfoList(List<GdsEvalReplyRespBaseInfo> evalReplyInfoList) {
		this.evalReplyInfoList = evalReplyInfoList;
	}

	
}


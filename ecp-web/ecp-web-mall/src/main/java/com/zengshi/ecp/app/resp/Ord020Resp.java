package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.ROrdExpressDetailsResp;
import com.zengshi.butterfly.app.model.IBody;

public class Ord020Resp extends IBody{
	
	private List<ROrdExpressDetailsResp> expressDetailsResp;

	public List<ROrdExpressDetailsResp> getExpressDetailsResp() {
		return expressDetailsResp;
	}

	public void setExpressDetailsResp(List<ROrdExpressDetailsResp> expressDetailsResp) {
		this.expressDetailsResp = expressDetailsResp;
	}
}

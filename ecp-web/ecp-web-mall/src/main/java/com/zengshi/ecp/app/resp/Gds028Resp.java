package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.butterfly.app.model.IBody;

public class Gds028Resp extends IBody {
	private List<GdsPropRespDTO> gdsPropList;

	public List<GdsPropRespDTO> getGdsPropList() {
		return gdsPropList;
	}

	public void setGdsPropList(List<GdsPropRespDTO> gdsPropList) {
		this.gdsPropList = gdsPropList;
	}

	
}


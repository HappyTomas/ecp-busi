package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsEvalRespBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds005Resp extends IBody {
    
    private List<GdsEvalRespBaseInfo> gdsEvalRespList;    

	private long count = 0;// 总条数

	private long pageCount;

	public List<GdsEvalRespBaseInfo> getGdsEvalRespList() {
		return gdsEvalRespList;
	}

	public void setGdsEvalRespList(List<GdsEvalRespBaseInfo> gdsEvalRespList) {
		this.gdsEvalRespList = gdsEvalRespList;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}





    
}


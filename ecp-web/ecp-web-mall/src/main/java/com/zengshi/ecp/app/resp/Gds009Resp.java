package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsCollectBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds009Resp extends IBody {
 
  
    
    private List<GdsCollectBaseInfo> gdsCollectBaseInfos;    

	private long count = 0;// 总条数

	private long pageCount;

	public List<GdsCollectBaseInfo> getGdsCollectBaseInfos() {
		return gdsCollectBaseInfos;
	}

	public void setGdsCollectBaseInfos(List<GdsCollectBaseInfo> gdsCollectBaseInfos) {
		this.gdsCollectBaseInfos = gdsCollectBaseInfos;
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


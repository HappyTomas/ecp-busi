package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.PointGoodSearchResultVO;
import com.zengshi.butterfly.app.model.IBody;

public class Pointmgds006Resp extends IBody {
  @SuppressWarnings("rawtypes")
private  List<PointGoodSearchResultVO>pageRespVO ;

	private long count = 0;// 总条数

	private long pageCount;

	public List<PointGoodSearchResultVO> getPageRespVO() {
		return pageRespVO;
	}

	public void setPageRespVO(List<PointGoodSearchResultVO> pageRespVO) {
		this.pageRespVO = pageRespVO;
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


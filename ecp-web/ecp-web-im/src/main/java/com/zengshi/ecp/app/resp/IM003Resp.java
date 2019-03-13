package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.vo.IM00301VO;
import com.zengshi.butterfly.app.model.IBody;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:31  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class IM003Resp extends IBody {
	private List<IM00301VO> pageResp;
	private Long count = 0l;// 总条数
    private Long pageCount; // 分页总数
    
	public List<IM00301VO> getPageResp() {
		return pageResp;
	}
	public void setPageResp(List<IM00301VO> pageResp) {
		this.pageResp = pageResp;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
}

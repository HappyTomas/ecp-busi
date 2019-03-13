package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.busi.im.history.vo.MessageHistoryRespVO;
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
public class IM004Resp extends IBody {
	private List<MessageHistoryRespVO> pageResp;
	private Long count = 0l;// 总条数
    private Long pageCount; // 分页总数
    
	public List<MessageHistoryRespVO> getPageResp() {
		return pageResp;
	}
	public void setPageResp(List<MessageHistoryRespVO> pageResp) {
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

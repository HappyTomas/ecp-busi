package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Gds016Req extends IBody {
	public Long getEvalId() {
		return evalId;
	}

	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private Long evalId;
	
	private Long replyId;
	
	private String content;
	


	
}


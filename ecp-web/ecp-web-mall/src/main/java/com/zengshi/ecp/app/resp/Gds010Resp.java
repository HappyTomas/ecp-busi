package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class Gds010Resp extends IBody {
 
  
    
    private String flag;
    
    private String msg;
    
    public Long getCollectId() {
		return collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	private Long collectId;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
     
}


package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.app.resp.gds.PointGdsDetailBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Pointmgds001Resp extends IBody {
    
    private PointGdsDetailBaseInfo gdsDetailBaseInfo; 
    //剩余积分
    private Long remainScore;
	 
    public PointGdsDetailBaseInfo getGdsDetailBaseInfo() {
		return gdsDetailBaseInfo;
	}

	public void setGdsDetailBaseInfo(PointGdsDetailBaseInfo gdsDetailBaseInfo) {
		this.gdsDetailBaseInfo = gdsDetailBaseInfo;
	}

	public Long getRemainScore() {
		return remainScore;
	}

	public void setRemainScore(Long remainScore) {
		this.remainScore = remainScore;
	}  
    
	
    
}


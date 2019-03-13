package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.app.resp.gds.GdsDetailBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds001Resp extends IBody {
    
    private GdsDetailBaseInfo gdsDetailBaseInfo; 
    

	public GdsDetailBaseInfo getGdsDetailBaseInfo() {
		return gdsDetailBaseInfo;
	}

	public void setGdsDetailBaseInfo(GdsDetailBaseInfo gdsDetailBaseInfo) {
		this.gdsDetailBaseInfo = gdsDetailBaseInfo;
	}  
    
    
}


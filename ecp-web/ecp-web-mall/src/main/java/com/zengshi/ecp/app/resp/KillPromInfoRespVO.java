package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

public class KillPromInfoRespVO extends IBody{
	
	/** 
     * id:促销编码. 
     */ 
    private Long id;
	
	/** 
     * promTheme:促销名称. 
     */ 
    private String promTheme;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPromTheme() {
		return promTheme;
	}

	public void setPromTheme(String promTheme) {
		this.promTheme = promTheme;
	}

    

}

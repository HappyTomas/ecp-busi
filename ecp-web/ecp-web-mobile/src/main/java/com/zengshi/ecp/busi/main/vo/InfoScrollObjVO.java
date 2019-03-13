package com.zengshi.ecp.busi.main.vo;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Created by HDF on 2016/7/4.
 */
public class InfoScrollObjVO extends BaseResponseDTO{

    private static final long serialVersionUID = -7262550402838886595L;

    private long id;

    private String infoTitle;
    
    private String pubTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

    
   

    
}

package com.zengshi.ecp.app.req;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Gds028Req extends IBody {
	private String catgCode;
	
	private String catgName;

    private Short catgLevel;
    
    private String catgType;

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public String getCatgName() {
		return catgName;
	}

	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}

	public Short getCatgLevel() {
		return catgLevel;
	}

	public void setCatgLevel(Short catgLevel) {
		this.catgLevel = catgLevel;
	}

	public String getCatgType() {
		return catgType;
	}

	public void setCatgType(String catgType) {
		this.catgType = catgType;
	}

}


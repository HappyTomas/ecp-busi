package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.CategoryTree;
import com.zengshi.butterfly.app.model.IBody;

public class Gds027Resp extends IBody {
	private List<CategoryTree> gdsCategoryList;

	public List<CategoryTree> getGdsCategoryList() {
		return gdsCategoryList;
	}

	public void setGdsCategoryList(List<CategoryTree> gdsCategoryList) {
		this.gdsCategoryList = gdsCategoryList;
	}
}


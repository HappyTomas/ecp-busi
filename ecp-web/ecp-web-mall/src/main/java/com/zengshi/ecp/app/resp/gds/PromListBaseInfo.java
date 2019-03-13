package com.zengshi.ecp.app.resp.gds;

import com.zengshi.butterfly.app.model.IBody;

public class PromListBaseInfo extends IBody{
    private PromBaseInfo promBaseInfo;
    
    private PromSkuPriceBaseInfo promSkuPriceBaseInfo;

	public PromBaseInfo getPromBaseInfo() {
		return promBaseInfo;
	}

	public void setPromBaseInfo(PromBaseInfo promBaseInfo) {
		this.promBaseInfo = promBaseInfo;
	}

	public PromSkuPriceBaseInfo getPromSkuPriceBaseInfo() {
		return promSkuPriceBaseInfo;
	}

	public void setPromSkuPriceBaseInfo(PromSkuPriceBaseInfo promSkuPriceBaseInfo) {
		this.promSkuPriceBaseInfo = promSkuPriceBaseInfo;
	}
}


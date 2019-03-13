package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.app.resp.gds.AppVersionInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds017Resp extends IBody {

private AppVersionInfo appVersionInfo;

public AppVersionInfo getAppVersionInfo() {
	return appVersionInfo;
}

public void setAppVersionInfo(AppVersionInfo appVersionInfo) {
	this.appVersionInfo = appVersionInfo;
}  
	
}


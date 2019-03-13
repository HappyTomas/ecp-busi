package com.zengshi.ecp.sys.dubbo.util;

public class AppVersionConstants {
	// 未发布
	public static final String VERSION_STATUS_UNDEAL = "00";

	// 发布中
	public static final String VERSION_STATUS_DEAL = "01";
	// 已过时
	public static final String VERSION_STATUS_OUTTIME = "02";
	
	//异常信息
	//添加app版本失败
	public static final String SYS_APP_VERSION_ADD_ERROR = "sys.app.version.add.fail";
	//更新app版本失败
	public static final String SYS_APP_VERSION_UPDATE_ERROR = "sys.app.version.update.fail";
	
	//查询app版本列表失败
	public static final String SYS_APP_VERSION_FETCHLIST_ERROR = "sys.app.version.fetchlist.fail";
	
	//查询app版本列表失败
		public static final String SYS_APP_VERSION_FETCH_ERROR = "sys.app.version.fetch.fail";
	
	//校验app版本更新信息失败
	public static final String SYS_APP_VERSION_CHECKUPDATE_ERROR = "sys.app.version.checkupdate.fail";
	
	
	//app的verNo
	public static final String SYS_APP_VERSION_PARAM_NULL_VERNO = "sys.app.version.param.null.verno";
	//app的verNo
		public static final String SYS_APP_VERSION_PARAM_NULL_ID = "sys.app.version.param.null.id";
		
		//app的verNo
		public static final String SYS_APP_VERSION_VERNO_EXISTS = "sys.app.version.verno.exists";	
}

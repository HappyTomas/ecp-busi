package com.zengshi.aip.provider.bss;

import com.zengshi.aip.provider.bss.model.appinfo.AipAppInfoResult;

/**
 * 应用信息服务
 * @author liangdl
 * 
 */
public interface AppinfoService {
	
	/**
	 * @Description:根据appkey获取AppInfo
	 * @author: liangdl
	 * @Create: 2015年03月07日 下午3:17:34
	 * @param: appkey
	 * @return: AipAppInfoResult
	 * @throws Exception
	 */
	public AipAppInfoResult getAppinfoByAppkey(String appkey) throws Exception;
}

package com.zengshi.aip.provider.appkey;

public interface VerifyAppkeyService {

	/**
	 * @Title: isPermission
	 * @Description:根据appkey验证appkey是否合法
	 * @author: luocan
	 * @Create: 2014年10月27日 下午3:17:34
	 * @Modify: 2014年10月27日 下午3:17:34
	 * @param: appkey
	 * @return: boolean
	 */
	public boolean isPermission(String appkey) throws Exception;
	
	/**
	 * @Title: verifyAppSecret
	 * @Description:根据appKey和appSeceret验证合法性
	 * @author: luocan
	 * @Create: 2014年11月12日 下午3:16:20
	 * @Modify: 2014年11月12日 下午3:16:20
	 * @param:  appKey,appSecret
	 * @return:  boolean
	 */
	public boolean verifyAppSecret(String appKey,String appSecret) throws Exception;
	
}

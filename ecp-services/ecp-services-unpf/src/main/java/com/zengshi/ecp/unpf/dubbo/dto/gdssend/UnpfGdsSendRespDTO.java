package com.zengshi.ecp.unpf.dubbo.dto.gdssend;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
/**
* @author  lisp: 
* @date 创建时间：2016年11月16日 下午2:14:25 
* @version 1.0 
**/
public class UnpfGdsSendRespDTO extends GdsInfoRespDTO{
	
	private static final long serialVersionUID = -1L;
	
	/**
	 * 授权品台类型
	 */
	private Map<String,String> platTypes;
	/**
	 * 授权品台推送状态
	 */
	private Map<String,String> platStatus;

	public Map<String, String> getPlatTypes() {
		return platTypes;
	}

	public void setPlatTypes(Map<String, String> platTypes) {
		this.platTypes = platTypes;
	}

	public Map<String, String> getPlatStatus() {
		return platStatus;
	}

	public void setPlatStatus(Map<String, String> platStatus) {
		this.platStatus = platStatus;
	}
}



package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.Map;

import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;

public class CoupCodeRespDTO extends CoupInfoRespDTO {
	
	/**
	 * 全部的coupId 包含平台级的和店铺级总和
	 * key:coupId (优惠券小类ID)
	 * value:skuID的集合  此List表示此优惠券能在哪些单品上使用
	 * PS:满减规则可能是多个单品单价总和计算的
	 * 如果是没有任何限制规则的抵用券则 List<CoupSkuRespDTO> coupSkuRespDTO不赋值
	 */
	private Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap;
	
	//优惠码
	private String coupCode;
	
	//处理结果信息；
	private String resultMsg;
	
	//是否可用标志位 1：可用；
	private String ifCanUse ="0";
	
	//redisKey
	private String hashKey;
		
	private static final long serialVersionUID = 1L;

	public String getCoupCode() {
		return coupCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getIfCanUse() {
		return ifCanUse;
	}

	public void setIfCanUse(String ifCanUse) {
		this.ifCanUse = ifCanUse;
	}

	public Map<Long, CoupCheckInfoRespDTO> getCoupIdskuIdMap() {
		return coupIdskuIdMap;
	}

	public void setCoupIdskuIdMap(Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap) {
		this.coupIdskuIdMap = coupIdskuIdMap;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}


}

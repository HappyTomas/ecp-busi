package com.zengshi.ecp.coupon.dubbo.dto.resp;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupLimitCheckRespDTO extends BaseResponseDTO{

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.7
	 
	 */ 
	private static final long serialVersionUID = 1L;
	
	//1:通过 0:不通过  
	private String judge;
	//skuGroup : 2:多个单品组合
	private String skuGroup;
	//1:与部分优惠券可以共同使用
	//2:与所有优惠互斥
	//默认为0 就是可与所有优惠券共同使用
	private String varLimit="0";
	//此优惠券能和部分优惠券的集合
	private List<Long> coupVarLong;
	// 单个订单里,此类优惠券可使用的张数
	private int ordUseNum;
	// 优惠券ID
	private Long coupId;
	// 单品ID
	private Long skuId;
	// 此为单品ID的上一级商品ID,属于冗余字段,方便使用
	private Long gdsId;
	// 1:该优惠券为免邮优惠券
	private String noExpress;
	//0:平台级别,1:店铺级别 
	private String typeLimit;
	//满减扣除的金额(只有带满减金额限制的时候才有)
	private Long sumLimit;
	
	
	public Long getSumLimit() {
		return sumLimit;
	}
	public void setSumLimit(Long sumLimit) {
		this.sumLimit = sumLimit;
	}
	public String getTypeLimit() {
		return typeLimit;
	}
	public void setTypeLimit(String typeLimit) {
		this.typeLimit = typeLimit;
	}
	public String getNoExpress() {
		return noExpress;
	}
	public void setNoExpress(String noExpress) {
		this.noExpress = noExpress;
	}
	public String getSkuGroup() {
		return skuGroup;
	}
	public void setSkuGroup(String skuGroup) {
		this.skuGroup = skuGroup;
	}
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getGdsId() {
		return gdsId;
	}
	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public List<Long> getCoupVarLong() {
		return coupVarLong;
	}
	public void setCoupVarLong(List<Long> coupVarLong) {
		this.coupVarLong = coupVarLong;
	}
	public int getOrdUseNum() {
		return ordUseNum;
	}
	public void setOrdUseNum(int ordUseNum) {
		this.ordUseNum = ordUseNum;
	}
	
	public String getVarLimit() {
		return varLimit;
	}
	public void setVarLimit(String varLimit) {
		this.varLimit = varLimit;
	}
	@Override
	public String toString() {
		return "CoupLimitCheckRespDTO [judge=" + judge + ", skuGroup="
				+ skuGroup + ", coupVarLong=" + coupVarLong + ", ordUseNum="
				+ ordUseNum + ", coupId=" + coupId + ", skuId=" + skuId
				+ ", gdsId=" + gdsId + "]";
	}
	
	
}


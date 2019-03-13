package com.zengshi.ecp.general.order.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class CoupCheckBeanRespDTO extends BaseResponseDTO{

	/** 
	 * @since JDK 1.7
	 
	 */ 
	private static final long serialVersionUID = 1L;
	//优惠券信息ID
	private Long coupId;
	//退货申请ID
	private Long applyId;
	//优惠券面额
    private Long coupValue;
	//具体优惠券信息 包含:优惠券编码,具体生失效时间,面额等等
	private List<CoupDetailRespDTO> coupDetails;
	//优惠券数量
	private int coupSize;
	//优惠券名称
	private String coupName;
	// 1:该优惠券为免邮优惠券
	private String noExpress;
	
	//1:与部分优惠券可以共同使用
	//2:与所有优惠互斥
	//默认为0 就是可与所有优惠券共同使用
	private String varLimit="0";
	//如果varLimit==1时,该list存放可与此优惠券共同使用的其他优惠券ID
	private List<Long> coupVarBeans;
	// 单个订单里,此类优惠券可使用的张数
	private int ordUseNum;
	
	
	public Long getCoupValue() {
		return coupValue;
	}
	public void setCoupValue(Long coupValue) {
		this.coupValue = coupValue;
	}
	public int getOrdUseNum() {
		return ordUseNum;
	}
	public void setOrdUseNum(int ordUseNum) {
		this.ordUseNum = ordUseNum;
	}
	public int getCoupSize() {
		return coupSize;
	}
	public void setCoupSize(int coupSize) {
		this.coupSize = coupSize;
	}
	public String getNoExpress() {
		return noExpress;
	}
	public void setNoExpress(String noExpress) {
		this.noExpress = noExpress;
	}
	public List<CoupDetailRespDTO> getCoupDetails() {
		return coupDetails;
	}
	public void setCoupDetails(List<CoupDetailRespDTO> coupDetails) {
		this.coupDetails = coupDetails;
	}
	public String getCoupName() {
		return coupName;
	}
	public void setCoupName(String coupName) {
		this.coupName = coupName;
	}
	public Long getCoupId() {
		return coupId;
	}
	public void setCoupId(Long coupId) {
		this.coupId = coupId;
	}
	public String getVarLimit() {
		return varLimit;
	}
	public void setVarLimit(String varLimit) {
		this.varLimit = varLimit;
	}
	public List<Long> getCoupVarBeans() {
		return coupVarBeans;
	}
	public void setCoupVarBeans(List<Long> coupVarBeans) {
		this.coupVarBeans = coupVarBeans;
	}
	public Long getApplyId() {
		return applyId;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	
	
}


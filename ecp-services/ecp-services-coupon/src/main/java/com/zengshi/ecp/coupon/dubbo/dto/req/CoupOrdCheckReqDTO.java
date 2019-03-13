package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class CoupOrdCheckReqDTO extends BaseInfo{

	//退货申请ID
	private Long applyId;
	//买家id
    private Long staffId;
    //来源
    private String source;
	//优惠码 PS:此属性用于优惠码校验使用,其他使用则不需赋值
    private String coupCode;
    
    //用户购买的商品以及店铺信息
  	private List<ROrdCartCommRequest> ordCartsCommList;
    //存放用户选择的优惠券信息
  	private List<CoupCheckBeanRespDTO> coupCheckBean;
  	
	/**
	 * 这个是之前查询可用的优惠券信息时传过去的
	 * key:CoupCheckBeanRespDTO value:skuID的集合  此List表示此优惠券能在哪些单品上使用
	 * PS:满减规则可能是多个单品单价总和计算的
	 */
	private Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap;
	
    
	
	public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		this.coupCode = coupCode;
	}

	public List<CoupCheckBeanRespDTO> getCoupCheckBean() {
		return coupCheckBean;
	}

	public void setCoupCheckBean(List<CoupCheckBeanRespDTO> coupCheckBean) {
		this.coupCheckBean = coupCheckBean;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Map<Long, CoupCheckInfoRespDTO> getCoupIdskuIdMap() {
		return coupIdskuIdMap;
	}

	public void setCoupIdskuIdMap(Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap) {
		this.coupIdskuIdMap = coupIdskuIdMap;
	}

	public List<ROrdCartCommRequest> getOrdCartsCommList() {
		return ordCartsCommList;
	}

	public void setOrdCartsCommList(List<ROrdCartCommRequest> ordCartsCommList) {
		this.ordCartsCommList = ordCartsCommList;
	}

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
    
    
}


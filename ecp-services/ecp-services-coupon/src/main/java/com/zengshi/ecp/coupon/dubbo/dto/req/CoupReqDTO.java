package com.zengshi.ecp.coupon.dubbo.dto.req;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-16下午7:38:13  <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public class CoupReqDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private CoupInfoReqDTO coupInfoReqDTO;
    //0:新增 1:编辑 (或者0 失效 1 生效)
    private String edit;
    
    private List<CoupInfoReqDTO> coupInfoReqDTOs;
    //优惠券领取限制规则
    private List<CoupGetLimitReqDTO> coupGetLimitReqDTOs;
    //店铺限制规则
    private List<CoupShopLimitReqDTO> coupShopLimitReqDTOs;
    //优惠券黑名单限制规则
    private List<CoupBlackLimitReqDTO> coupBlackLimitReqDTOs;
    //优惠券满减限制规则
    private List<CoupFullLimitReqDTO> coupFullLimitReqDTOs;
    //优惠券品类限制规则
    private List<CoupCatgLimitReqDTO> coupCatgLimitReqDTOs;
    //优惠券其他使用限制规则
    private List<CoupUseLimitReqDTO> coupUseLimitReqDTOs;
    //优惠券共同使用限制规则
    private List<CoupVarLimitReqDTO> coupVarLimitReqDTOs;
    
    //优惠券领取限制规则
    private CoupGetLimitReqDTO coupGetLimitReqDTO;
    //店铺限制规则
    private CoupShopLimitReqDTO coupShopLimitReqDTO;
    //优惠券黑名单限制规则
    private CoupBlackLimitReqDTO coupBlackLimitReqDTO;
    //优惠券满减限制规则
    private CoupFullLimitReqDTO coupFullLimitReqDTO;
    //优惠券品类限制规则
    private CoupCatgLimitReqDTO coupCatgLimitReqDTO;
    //优惠券其他使用限制规则
    private CoupUseLimitReqDTO coupUseLimitReqDTO;
    //优惠券共同使用限制规则
    private CoupVarLimitReqDTO coupVarLimitReqDTO;
    
    
    
	public List<CoupInfoReqDTO> getCoupInfoReqDTOs() {
		return coupInfoReqDTOs;
	}
	public void setCoupInfoReqDTOs(List<CoupInfoReqDTO> coupInfoReqDTOs) {
		this.coupInfoReqDTOs = coupInfoReqDTOs;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public CoupInfoReqDTO getCoupInfoReqDTO() {
		return coupInfoReqDTO;
	}
	public void setCoupInfoReqDTO(CoupInfoReqDTO coupInfoReqDTO) {
		this.coupInfoReqDTO = coupInfoReqDTO;
	}
	public List<CoupGetLimitReqDTO> getCoupGetLimitReqDTOs() {
		return coupGetLimitReqDTOs;
	}
	public void setCoupGetLimitReqDTOs(List<CoupGetLimitReqDTO> coupGetLimitReqDTOs) {
		this.coupGetLimitReqDTOs = coupGetLimitReqDTOs;
	}
	public List<CoupShopLimitReqDTO> getCoupShopLimitReqDTOs() {
		return coupShopLimitReqDTOs;
	}
	public void setCoupShopLimitReqDTOs(
			List<CoupShopLimitReqDTO> coupShopLimitReqDTOs) {
		this.coupShopLimitReqDTOs = coupShopLimitReqDTOs;
	}
	public List<CoupBlackLimitReqDTO> getCoupBlackLimitReqDTOs() {
		return coupBlackLimitReqDTOs;
	}
	public void setCoupBlackLimitReqDTOs(
			List<CoupBlackLimitReqDTO> coupBlackLimitReqDTOs) {
		this.coupBlackLimitReqDTOs = coupBlackLimitReqDTOs;
	}
	public List<CoupFullLimitReqDTO> getCoupFullLimitReqDTOs() {
		return coupFullLimitReqDTOs;
	}
	public void setCoupFullLimitReqDTOs(
			List<CoupFullLimitReqDTO> coupFullLimitReqDTOs) {
		this.coupFullLimitReqDTOs = coupFullLimitReqDTOs;
	}
	
	public List<CoupCatgLimitReqDTO> getCoupCatgLimitReqDTOs() {
		return coupCatgLimitReqDTOs;
	}
	public void setCoupCatgLimitReqDTOs(
			List<CoupCatgLimitReqDTO> coupCatgLimitReqDTOs) {
		this.coupCatgLimitReqDTOs = coupCatgLimitReqDTOs;
	}
	public List<CoupUseLimitReqDTO> getCoupUseLimitReqDTOs() {
		return coupUseLimitReqDTOs;
	}
	public void setCoupUseLimitReqDTOs(List<CoupUseLimitReqDTO> coupUseLimitReqDTOs) {
		this.coupUseLimitReqDTOs = coupUseLimitReqDTOs;
	}
	public List<CoupVarLimitReqDTO> getCoupVarLimitReqDTOs() {
		return coupVarLimitReqDTOs;
	}
	public void setCoupVarLimitReqDTOs(List<CoupVarLimitReqDTO> coupVarLimitReqDTOs) {
		this.coupVarLimitReqDTOs = coupVarLimitReqDTOs;
	}
	public CoupGetLimitReqDTO getCoupGetLimitReqDTO() {
		return coupGetLimitReqDTO;
	}
	public void setCoupGetLimitReqDTO(CoupGetLimitReqDTO coupGetLimitReqDTO) {
		this.coupGetLimitReqDTO = coupGetLimitReqDTO;
	}
	public CoupShopLimitReqDTO getCoupShopLimitReqDTO() {
		return coupShopLimitReqDTO;
	}
	public void setCoupShopLimitReqDTO(CoupShopLimitReqDTO coupShopLimitReqDTO) {
		this.coupShopLimitReqDTO = coupShopLimitReqDTO;
	}
	public CoupBlackLimitReqDTO getCoupBlackLimitReqDTO() {
		return coupBlackLimitReqDTO;
	}
	public void setCoupBlackLimitReqDTO(CoupBlackLimitReqDTO coupBlackLimitReqDTO) {
		this.coupBlackLimitReqDTO = coupBlackLimitReqDTO;
	}
	public CoupFullLimitReqDTO getCoupFullLimitReqDTO() {
		return coupFullLimitReqDTO;
	}
	public void setCoupFullLimitReqDTO(CoupFullLimitReqDTO coupFullLimitReqDTO) {
		this.coupFullLimitReqDTO = coupFullLimitReqDTO;
	}
	public CoupCatgLimitReqDTO getCoupCatgLimitReqDTO() {
		return coupCatgLimitReqDTO;
	}
	public void setCoupCatgLimitReqDTO(CoupCatgLimitReqDTO coupCatgLimitReqDTO) {
		this.coupCatgLimitReqDTO = coupCatgLimitReqDTO;
	}
	public CoupUseLimitReqDTO getCoupUseLimitReqDTO() {
		return coupUseLimitReqDTO;
	}
	public void setCoupUseLimitReqDTO(CoupUseLimitReqDTO coupUseLimitReqDTO) {
		this.coupUseLimitReqDTO = coupUseLimitReqDTO;
	}
	public CoupVarLimitReqDTO getCoupVarLimitReqDTO() {
		return coupVarLimitReqDTO;
	}
	public void setCoupVarLimitReqDTO(CoupVarLimitReqDTO coupVarLimitReqDTO) {
		this.coupVarLimitReqDTO = coupVarLimitReqDTO;
	}
	
}

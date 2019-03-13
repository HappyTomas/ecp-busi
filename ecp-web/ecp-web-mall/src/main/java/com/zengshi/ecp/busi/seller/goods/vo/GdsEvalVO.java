package com.zengshi.ecp.busi.seller.goods.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.alibaba.fastjson.annotation.JSONField;

public class GdsEvalVO extends EcpBasePageReqVO {

	/**
	 * 评价管理VO
	 */
	private static final long serialVersionUID = 1785653923638354473L;
	
	private Long id;//评价id

    private Long gdsId;//商品编码

    private Long skuId;//单品编码

    private Long  shopId;//店铺编码

    private String orderId;//订单编码

    private String orderSubId;//子订单编码

    private Short correspondencyScore;//产品与描述相符

    private Short serviceattitudeScore;//卖家的服务态度

    private Short deliveryspeedScore;//卖家的发货速度

    private String content;//评价内容

    private String isAnonymous;//是否匿名评论

    private Short isReply;//是否已回复  0表示未回复   1表示已回复

    private Long labelNames;//标签名

    private String status;//状态
    
    private String evalScore;//评分--好中差
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;// 起始时间
    
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;//结束时间
    // 是否删除回复,true-表示删除该评价时,则时也删除与之前联的回复.
    private Boolean delReply;
    // 评价评情.
    private String detail;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGdsId() {
		return gdsId;
	}
	public void setGdsId(Long gdsId) {
		this.gdsId = gdsId;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderSubId() {
		return orderSubId;
	}
	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}
	public Short getCorrespondencyScore() {
		return correspondencyScore;
	}
	public void setCorrespondencyScore(Short correspondencyScore) {
		this.correspondencyScore = correspondencyScore;
	}
	public Short getServiceattitudeScore() {
		return serviceattitudeScore;
	}
	public void setServiceattitudeScore(Short serviceattitudeScore) {
		this.serviceattitudeScore = serviceattitudeScore;
	}
	public Short getDeliveryspeedScore() {
		return deliveryspeedScore;
	}
	public void setDeliveryspeedScore(Short deliveryspeedScore) {
		this.deliveryspeedScore = deliveryspeedScore;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsAnonymous() {
		return isAnonymous;
	}
	public void setIsAnonymous(String isAnonymous) {
		this.isAnonymous = isAnonymous;
	}
	public Short getIsReply() {
		return isReply;
	}
	public void setIsReply(Short isReply) {
		this.isReply = isReply;
	}
	public Long getLabelNames() {
		return labelNames;
	}
	public void setLabelNames(Long labelNames) {
		this.labelNames = labelNames;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Boolean getDelReply() {
		return delReply;
	}
	public void setDelReply(Boolean delReply) {
		this.delReply = delReply;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEvalScore() {
		return evalScore;
	}
	public void setEvalScore(String evalScore) {
		this.evalScore = evalScore;
	}


}

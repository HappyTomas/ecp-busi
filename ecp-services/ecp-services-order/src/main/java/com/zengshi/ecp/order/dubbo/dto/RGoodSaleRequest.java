package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by wang on 16/1/12.
 */
public class RGoodSaleRequest extends BaseInfo{
    /**
     * 平台分类
     */
    private List<String> categoryCodes;
    /**
     * 订单状态
     */
    private String payFlag;
    /**
     * 商品分类
     */
    private Long gdsType;
    /**
     * 商品名称
     */
    private String gdsName;
    /**
     * ISBN号
     */
    private List<Long> gdsIds;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 会员
     */
    private Long staffId;
    /**
     * 起始日期
     */
    private Timestamp begDate;
    /**
     * 截止日期
     */
    private Timestamp endDate;
    /**
     * 站点
     */
    private Long siteId;
    /**
     * 店铺Id
     */
    private Long shopId;

    private Long key;
    
    /**
     * 支付方式
     */
    private String payType;

    public List<String> getCategoryCodes() {
        return categoryCodes;
    }

    public void setCategoryCodes(List<String> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }

    public List<Long> getGdsIds() {
        return gdsIds;
    }

    public void setGdsIds(List<Long> gdsIds) {
        this.gdsIds = gdsIds;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Timestamp getBegDate() {
        return begDate;
    }

    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getGdsType() {
        return gdsType;
    }

    public void setGdsType(Long gdsType) {
        this.gdsType = gdsType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}

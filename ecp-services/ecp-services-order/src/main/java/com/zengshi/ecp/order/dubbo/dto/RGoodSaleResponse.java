package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

import java.sql.Timestamp;

/**
 * Created by wang on 16/1/12.
 */
public class RGoodSaleResponse extends BaseResponseDTO {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 、商品名称
     */
    private String gdsName;
    /**
     * 、ISBN号、
     */
    private String isbn;
    /**
     * 书号、
     */
    private String zsCode;
    /**
     * 产品一级分类
     */
    private String category01;
    /**
     * 产品二级分类、
     */
    private String category02;
    /**
     * 产品三级分类、
     */
    private String category03;
    /**
     * 产品四级分类、
     */
    private String category04;
    /**
     * 购买单价、
     */
    private Long discountPrice;
    /**
     * 购买数量
     */
    private Long orderAmount;
    /**
     * 、购买总金额(未优惠)
     */
    private Long orderMoney;
    /**
     * 购买总金额(实际)
     */
    private Long realMoney;
    /**
     * 、是否活动商品
     */
    private Boolean isProm;
    /**
     * 、购买日期
     */
    private Timestamp orderTime;
    /**
     * 、会员名、
     */
    private String staffCode;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 、联系电话
     */
    private String contactPhone;
    /**
     * 、联系地址。
     */
    private String chnlAddress;
    /**
     * 站点id
     */
    private Long siteId;
    
    /**
     * 子订单号
     */
    private String orderSubId;
    
    /**
     * 已退数量
     */
    private Long hasBackNum;
    
    /**
     * 支付方式
     */
    private String payType;
    
    /**
     * 索引表主键
     */
    private Long indexId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getZsCode() {
        return zsCode;
    }

    public void setZsCode(String zsCode) {
        this.zsCode = zsCode;
    }

    public String getCategory01() {
        return category01;
    }

    public void setCategory01(String category01) {
        this.category01 = category01;
    }

    public String getCategory02() {
        return category02;
    }

    public void setCategory02(String category02) {
        this.category02 = category02;
    }

    public String getCategory03() {
        return category03;
    }

    public void setCategory03(String category03) {
        this.category03 = category03;
    }

    public String getCategory04() {
        return category04;
    }

    public void setCategory04(String category04) {
        this.category04 = category04;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Long orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Boolean getIsProm() {
        return isProm;
    }

    public void setIsProm(Boolean isProm) {
        this.isProm = isProm;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getChnlAddress() {
        return chnlAddress;
    }

    public void setChnlAddress(String chnlAddress) {
        this.chnlAddress = chnlAddress;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

	public String getOrderSubId() {
		return orderSubId;
	}

	public void setOrderSubId(String orderSubId) {
		this.orderSubId = orderSubId;
	}

	public Long getHasBackNum() {
		return hasBackNum;
	}

	public void setHasBackNum(Long hasBackNum) {
		this.hasBackNum = hasBackNum;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}
}

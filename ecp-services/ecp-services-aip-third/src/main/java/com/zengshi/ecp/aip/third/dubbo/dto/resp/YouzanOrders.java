package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by guojingman on 2017/2/22.
 * 有赞订单数据对象，包括订单和订单商品
 */
public class YouzanOrders implements Serializable {
    private String id;

    private String outerId;

    private String platType;

    private Long shopId;

    private String shopName;

    private String paramid;

    private String orderCode;

    private String staffCode;

    private Long realExpressFee;

    private Long realMoney;

    private String status;

    private String buyerMsg;

    private String buyerNick;

    private String contractProvince;

    private String contractCity;

    private String contractDistrict;

    private String contractZipcode;

    private String contractName;

    private String contractAddr;

    private String dispatchType;

    private String contractTel;

    private String contractNum;

    private Timestamp orderTime;

    private Timestamp payTime;

    private Timestamp dispatchTime;

    private String expressNo;

    private String expressCompany;

    private String remark;

    private String orderAmount;

    private String closeReason;

    private String invoiceTitle;

    private String appFlag;

    private String ecpStaffCode;

    private Long ecpStaffId;

    private String ecpScoreFlag;

    private Long ecpScore;

    private Timestamp importTime;

    private Timestamp createTime;

    private Long createStaff;

    private Long updateStaff;

    private Timestamp updateTime;

    private String sysFlag;

    private List<OrderGoods> orderGoods;

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getParamid() {
        return paramid;
    }

    public void setParamid(String paramid) {
        this.paramid = paramid;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Long getRealExpressFee() {
        return realExpressFee;
    }

    public void setRealExpressFee(Long realExpressFee) {
        this.realExpressFee = realExpressFee;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyerMsg() {
        return buyerMsg;
    }

    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }

    public String getContractProvince() {
        return contractProvince;
    }

    public void setContractProvince(String contractProvince) {
        this.contractProvince = contractProvince;
    }

    public String getContractCity() {
        return contractCity;
    }

    public void setContractCity(String contractCity) {
        this.contractCity = contractCity;
    }

    public String getContractDistrict() {
        return contractDistrict;
    }

    public void setContractDistrict(String contractDistrict) {
        this.contractDistrict = contractDistrict;
    }

    public String getContractZipcode() {
        return contractZipcode;
    }

    public void setContractZipcode(String contractZipcode) {
        this.contractZipcode = contractZipcode;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractAddr() {
        return contractAddr;
    }

    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getContractTel() {
        return contractTel;
    }

    public void setContractTel(String contractTel) {
        this.contractTel = contractTel;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public Timestamp getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Timestamp dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getAppFlag() {
        return appFlag;
    }

    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag;
    }

    public String getEcpStaffCode() {
        return ecpStaffCode;
    }

    public void setEcpStaffCode(String ecpStaffCode) {
        this.ecpStaffCode = ecpStaffCode;
    }

    public Long getEcpStaffId() {
        return ecpStaffId;
    }

    public void setEcpStaffId(Long ecpStaffId) {
        this.ecpStaffId = ecpStaffId;
    }

    public String getEcpScoreFlag() {
        return ecpScoreFlag;
    }

    public void setEcpScoreFlag(String ecpScoreFlag) {
        this.ecpScoreFlag = ecpScoreFlag;
    }

    public Long getEcpScore() {
        return ecpScore;
    }

    public void setEcpScore(Long ecpScore) {
        this.ecpScore = ecpScore;
    }

    public Timestamp getImportTime() {
        return importTime;
    }

    public void setImportTime(Timestamp importTime) {
        this.importTime = importTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public static class OrderGoods implements Serializable{
        private String id;

        private String orderId;

        private String platType;

        private String outerSubId;

        private String outerId;

        private String gdsId;

        private String gdsName;

        private String picUrl;

        private String skuId;

        private String skuInfo;

        private String orderAmount;

        private Long orderPrice;

        private Long deliveryAmount;

        private String remark;

        private Timestamp orderTime;

        private String status;

        private Long shopId;

        private Long staffId;

        private String deliveryStatus;

        private Timestamp createTime;

        private Long createStaff;

        private Timestamp updateTime;

        private Long updateStaff;

        private String sysFlag;
        //1 已发货，0待发货
        private int isSend;
        
        private String discountFee;//优惠金额  DISCOUNT_MONEY
        
        private String realMoney;//实付金额  REAL_MONEY	
        
        private String orderMoney;//子订单总额  ORDER_MONEY	

        public int getIsSend() {
            return isSend;
        }

        public void setIsSend(int isSend) {
            this.isSend = isSend;
        }

        public Long getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(Long orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPlatType() {
            return platType;
        }

        public void setPlatType(String platType) {
            this.platType = platType;
        }

        public String getOuterSubId() {
            return outerSubId;
        }

        public void setOuterSubId(String outerSubId) {
            this.outerSubId = outerSubId;
        }

        public String getOuterId() {
            return outerId;
        }

        public void setOuterId(String outerId) {
            this.outerId = outerId;
        }

        public String getGdsId() {
            return gdsId;
        }

        public void setGdsId(String gdsId) {
            this.gdsId = gdsId;
        }

        public String getGdsName() {
            return gdsName;
        }

        public void setGdsName(String gdsName) {
            this.gdsName = gdsName;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuInfo() {
            return skuInfo;
        }

        public void setSkuInfo(String skuInfo) {
            this.skuInfo = skuInfo;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public Long getDeliveryAmount() {
            return deliveryAmount;
        }

        public void setDeliveryAmount(Long deliveryAmount) {
            this.deliveryAmount = deliveryAmount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Timestamp getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(Timestamp orderTime) {
            this.orderTime = orderTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Long getShopId() {
            return shopId;
        }

        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        public Long getStaffId() {
            return staffId;
        }

        public void setStaffId(Long staffId) {
            this.staffId = staffId;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public Timestamp getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
        }

        public Long getCreateStaff() {
            return createStaff;
        }

        public void setCreateStaff(Long createStaff) {
            this.createStaff = createStaff;
        }

        public Timestamp getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Timestamp updateTime) {
            this.updateTime = updateTime;
        }

        public Long getUpdateStaff() {
            return updateStaff;
        }

        public void setUpdateStaff(Long updateStaff) {
            this.updateStaff = updateStaff;
        }

        public String getSysFlag() {
            return sysFlag;
        }

        public void setSysFlag(String sysFlag) {
            this.sysFlag = sysFlag;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

		public String getRealMoney() {
			return realMoney;
		}

		public void setRealMoney(String realMoney) {
			this.realMoney = realMoney;
		}

		public String getOrderMoney() {
			return orderMoney;
		}

		public void setOrderMoney(String orderMoney) {
			this.orderMoney = orderMoney;
		}

		public String getDiscountFee() {
			return discountFee;
		}

		public void setDiscountFee(String discountFee) {
			this.discountFee = discountFee;
		}
    }
}

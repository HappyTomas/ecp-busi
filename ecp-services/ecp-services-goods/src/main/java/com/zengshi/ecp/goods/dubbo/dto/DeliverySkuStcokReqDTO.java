package com.zengshi.ecp.goods.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class DeliverySkuStcokReqDTO extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -2412095318684040045L;

    // 订单编码
    private String orderId;

    // 子订单编码
    private String subOrder;

    // 库存id
    private Long stockId;

    // 是否全部发货
    private Boolean isDelivAll;

    // 发货数量
    private Long deliveryCount;

    // 单品Id
    private Long skuId;

    // 是否串号发货
    private Boolean isSerial;

    // 串号列表
    private List<String> serialNoList;

    // 操作人id
    private Long staffId;

    // 库存操作id
    private Long stockOptId;
    //是否数字印刷版
    private String prnFlag;
    
    /**
     * 商品类型编码
     */
	private Long gdsTypeId;
	
	/**
	 * 发货批次号
	 */
    private String deliverySno;
    
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSubOrder() {
        return subOrder;
    }

    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Boolean getIsDelivAll() {
        return isDelivAll;
    }

    public void setIsDelivAll(Boolean isDelivAll) {
        this.isDelivAll = isDelivAll;
    }

    public Long getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(Long deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getIsSerial() {
        return isSerial;
    }

    public void setIsSerial(Boolean isSerial) {
        this.isSerial = isSerial;
    }

    public List<String> getSerialNoList() {
        return serialNoList;
    }

    public void setSerialNoList(List<String> serialNoList) {
        this.serialNoList = serialNoList;
    }



    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getStockOptId() {
        return stockOptId;
    }

    public void setStockOptId(Long stockOptId) {
        this.stockOptId = stockOptId;
    }

	public Long getGdsTypeId() {
		return gdsTypeId;
	}

	public void setGdsTypeId(Long gdsTypeId) {
		this.gdsTypeId = gdsTypeId;
	}

    public String getPrnFlag() {
        return prnFlag;
    }

    public void setPrnFlag(String prnFlag) {
        this.prnFlag = prnFlag;
    }

    public String getDeliverySno() {
        return deliverySno;
    }

    public void setDeliverySno(String deliverySno) {
        this.deliverySno = deliverySno;
    }

}

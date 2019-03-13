package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class DeliverySkuStcokMainReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -8186240652009425095L;

    private List<DeliverySkuStcokReqDTO> deliverySkuStcokDTOs = new ArrayList<DeliverySkuStcokReqDTO>();
    /**
     * 发货批次号
     */
    private Long deliverySno;
    private Long staffId;

    public List<DeliverySkuStcokReqDTO> getDeliverySkuStcokDTOs() {
        return deliverySkuStcokDTOs;
    }

    public void setDeliverySkuStcokDTOs(List<DeliverySkuStcokReqDTO> deliverySkuStcokDTOs) {
        this.deliverySkuStcokDTOs = deliverySkuStcokDTOs;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getDeliverySno() {
        return deliverySno;
    }

    public void setDeliverySno(Long deliverySno) {
        this.deliverySno = deliverySno;
    }
}


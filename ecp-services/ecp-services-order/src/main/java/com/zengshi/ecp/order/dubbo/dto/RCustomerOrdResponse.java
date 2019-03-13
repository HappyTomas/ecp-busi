package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RCustomerOrdResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -3972139324593024891L;
    
    //主订单相关信息
    private SCustomerOrdMain sCustomerOrdMain;
    
    //子订单相关信息
    private List<SOrderDetailsSub> sOrderDetailsSubs;

    public SCustomerOrdMain getsCustomerOrdMain() {
        return sCustomerOrdMain;
    }

    public void setsCustomerOrdMain(SCustomerOrdMain sCustomerOrdMain) {
        this.sCustomerOrdMain = sCustomerOrdMain;
    }

    public List<SOrderDetailsSub> getsOrderDetailsSubs() {
        return sOrderDetailsSubs;
    }

    public void setsOrderDetailsSubs(List<SOrderDetailsSub> sOrderDetailsSubs) {
        this.sOrderDetailsSubs = sOrderDetailsSubs;
    }


}


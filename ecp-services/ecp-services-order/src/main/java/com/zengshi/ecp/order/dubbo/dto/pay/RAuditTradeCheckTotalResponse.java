package com.zengshi.ecp.order.dubbo.dto.pay;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RAuditTradeCheckTotalResponse extends BaseResponseDTO {

    /**
     * 交易总金额
     */
    private Long transAmounts;

    /**
     * 对账成功笔数
     */
    private Long successOrderCounts;

    /**
     * 订单总笔数
     */
    private Long transOrderCounts;

    /**
     * 对账成功总金额
     */
    private Long successOrderAmounts;

    private static final long serialVersionUID = 1L;

    public Long getTransAmounts() {
        return transAmounts;
    }

    public void setTransAmounts(Long transAmounts) {
        this.transAmounts = transAmounts;
    }

    public Long getSuccessOrderCounts() {
        return successOrderCounts;
    }

    public void setSuccessOrderCounts(Long successOrderCounts) {
        this.successOrderCounts = successOrderCounts;
    }

    public Long getTransOrderCounts() {
        return transOrderCounts;
    }

    public void setTransOrderCounts(Long transOrderCounts) {
        this.transOrderCounts = transOrderCounts;
    }

    public Long getSuccessOrderAmounts() {
        return successOrderAmounts;
    }

    public void setSuccessOrderAmounts(Long successOrderAmounts) {
        this.successOrderAmounts = successOrderAmounts;
    }
}
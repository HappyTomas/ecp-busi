package com.zengshi.ecp.busi.seller.order.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

import java.io.Serializable;

/**
 * Created by wang on 16/1/13.
 */
public class RGoodSaleSumRespVO extends EcpBaseResponseVO implements Serializable {
    private static final long serialVersionUID = 7292541991904587741L;

    private Long orderNum;

    private Long saleNum;

    /**
     * 累计订单码洋
     */
    private Long basicMoney;
    /**
     * 累计订单实洋
     */
    private Long realMoney;

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum==null?0l:orderNum;
    }

    public Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum==null?0l:saleNum;
    }

    public Long getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Long realMoney) {
        this.realMoney = realMoney==null?0l:realMoney;
    }

    public Long getBasicMoney() {
        return basicMoney;
    }

    public void setBasicMoney(Long basicMoney) {
        this.basicMoney = basicMoney==null?0l:basicMoney;
    }
}

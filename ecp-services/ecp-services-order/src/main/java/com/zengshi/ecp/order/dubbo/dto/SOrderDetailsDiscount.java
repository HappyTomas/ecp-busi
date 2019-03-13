package com.zengshi.ecp.order.dubbo.dto;

import java.io.Serializable;

public class SOrderDetailsDiscount implements Serializable{
    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7026074757153522984L;
    /*订单详情订单优惠相关字段*/
    /** 
     * discountPriceSum:优惠总金额. 
     * @since JDK 1.6 
     */ 
    private Long discountPriceSum;
    
    /** 
     * discountPromSum:促销优惠. 
     * @since JDK 1.6 废弃
     */ 
    private Long discountPromSum;

    /**
     * 店铺优惠
     */
    private Long discountOrderSum;

    /**
     * 商品分类折扣（无promId）优惠
     */
    private Long discountGdsProm;

    /**
     * 商品（有promId）优惠
     */
    private Long discountSubProm;
    
    /** 
     * discountAcctSum:账户优惠. 
     * @since JDK 1.6 
     */ 
    private Long discountAcctSum;

    /**
     * 优惠券优惠
     */
    private Long discountCoupSum;
    
    /**
     * 优惠码优惠
     */
    private Long discountCoupCodeSum;


    public Long getDiscountPromSum() {
        return discountPromSum;
    }

    public void setDiscountPromSum(Long discountPromSum) {
        this.discountPromSum = discountPromSum;
    }

    public Long getDiscountAcctSum() {
        return discountAcctSum;
    }

    public void setDiscountAcctSum(Long discountAcctSum) {
        this.discountAcctSum = discountAcctSum;
    }

    public Long getDiscountPriceSum() {
        return discountPriceSum;
    }

    public void setDiscountPriceSum(Long discountPriceSum) {
        this.discountPriceSum = discountPriceSum;
    }

    public Long getDiscountOrderSum() {
        return discountOrderSum;
    }

    public void setDiscountOrderSum(Long discountOrderSum) {
        this.discountOrderSum = discountOrderSum;
    }

    public Long getDiscountCoupSum() {
        return discountCoupSum;
    }

    public void setDiscountCoupSum(Long discountCoupSum) {
        this.discountCoupSum = discountCoupSum;
    }

    public Long getDiscountGdsProm() {
        return discountGdsProm;
    }

    public void setDiscountGdsProm(Long discountGdsProm) {
        this.discountGdsProm = discountGdsProm;
    }

    public Long getDiscountSubProm() {
        return discountSubProm;
    }

    public void setDiscountSubProm(Long discountSubProm) {
        this.discountSubProm = discountSubProm;
    }

    //特意提取分类折扣（无promID）和有promID优惠综合价格
    public Long getDiscountGdsSum() {
        return discountGdsProm + discountSubProm;
    }

    public Long getDiscountCoupCodeSum() {
        return discountCoupCodeSum;
    }

    public void setDiscountCoupCodeSum(Long discountCoupCodeSum) {
        this.discountCoupCodeSum = discountCoupCodeSum;
    }

}


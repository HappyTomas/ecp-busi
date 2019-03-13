package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.resp.OrdNumRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrderBackDetailResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7292331053103624621L;
    
    /** 
     * rBackApplyResp:申请表信息. 
     * @since JDK 1.6 
     */ 
    private RBackApplyResp rBackApplyResp;
    
    /** 
     * rBackGdsResps:明细表信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackGdsResp> rBackGdsResps;
    
    /** 
     * rBackDiscountResps:金额、资金账户、积分相关信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackDiscountResp> rBackDiscountResps;
    
    /** 
     * rBackCouponResps:优惠卷相关信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackCouponResp> rBackCouponResps;
    
    /** 
     * rBackGiftResps:赠品相关信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackGiftResp> rBackGiftResps;
    
    /** 
     * rBackPicResps:图片作证相关信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackPicResp> rBackPicResps;
    
    /** 
     * rBackTrackResps:日志相关信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackTrackResp> rBackTrackResps;
    
    /**
     * backApllyCoupList:封装后优惠券信息
     */
    private List<OrdNumRespDTO> backApllyCoupList;

    public RBackApplyResp getrBackApplyResp() {
        return rBackApplyResp;
    }

    public void setrBackApplyResp(RBackApplyResp rBackApplyResp) {
        this.rBackApplyResp = rBackApplyResp;
    }

    public List<RBackGdsResp> getrBackGdsResps() {
        return rBackGdsResps;
    }

    public void setrBackGdsResps(List<RBackGdsResp> rBackGdsResps) {
        this.rBackGdsResps = rBackGdsResps;
    }

    public List<RBackDiscountResp> getrBackDiscountResps() {
        return rBackDiscountResps;
    }

    public void setrBackDiscountResps(List<RBackDiscountResp> rBackDiscountResps) {
        this.rBackDiscountResps = rBackDiscountResps;
    }

    public List<RBackCouponResp> getrBackCouponResps() {
        return rBackCouponResps;
    }

    public void setrBackCouponResps(List<RBackCouponResp> rBackCouponResps) {
        this.rBackCouponResps = rBackCouponResps;
    }

    public List<RBackGiftResp> getrBackGiftResps() {
        return rBackGiftResps;
    }

    public void setrBackGiftResps(List<RBackGiftResp> rBackGiftResps) {
        this.rBackGiftResps = rBackGiftResps;
    }

    public List<RBackPicResp> getrBackPicResps() {
        return rBackPicResps;
    }

    public void setrBackPicResps(List<RBackPicResp> rBackPicResps) {
        this.rBackPicResps = rBackPicResps;
    }

    public List<RBackTrackResp> getrBackTrackResps() {
        return rBackTrackResps;
    }

    public void setrBackTrackResps(List<RBackTrackResp> rBackTrackResps) {
        this.rBackTrackResps = rBackTrackResps;
    }

	public List<OrdNumRespDTO> getBackApllyCoupList() {
		return backApllyCoupList;
	}

	public void setBackApllyCoupList(List<OrdNumRespDTO> backApllyCoupList) {
		this.backApllyCoupList = backApllyCoupList;
	}
    
    

}


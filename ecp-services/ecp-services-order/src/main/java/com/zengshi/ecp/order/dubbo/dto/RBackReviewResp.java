package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackReviewResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7473020956984436664L;
    
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
     * rBackApplyInfoResp:分摊相关信息. 
     * @since JDK 1.6 
     */ 
    private RBackApplyInfoResp rBackApplyInfoResp;
    
    /** 
     * rBackPicResps:凭证信息. 
     * @since JDK 1.6 
     */ 
    private List<RBackPicResp> rBackPicResps;
    /**
     * rBackTrackResps:多级审核信息.
     * @since JDK 1.6
     */
    private List<RBackTrackResp> rBackTrackResps;
    
    /**
     * orderDetailsMain：主订单详情
     */
    private SOrderDetailsMain orderDetailsMain;


    public List<RBackTrackResp> getrBackTrackResps() {
        return rBackTrackResps;
    }

    public void setrBackTrackResps(List<RBackTrackResp> rBackTrackResps) {
        this.rBackTrackResps = rBackTrackResps;
    }

    public List<RBackPicResp> getrBackPicResps() {
        return rBackPicResps;
    }

    public void setrBackPicResps(List<RBackPicResp> rBackPicResps) {
        this.rBackPicResps = rBackPicResps;
    }

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

    public RBackApplyInfoResp getrBackApplyInfoResp() {
        return rBackApplyInfoResp;
    }

    public void setrBackApplyInfoResp(RBackApplyInfoResp rBackApplyInfoResp) {
        this.rBackApplyInfoResp = rBackApplyInfoResp;
    }

    public SOrderDetailsMain getOrderDetailsMain() {
        return orderDetailsMain;
    }

    public void setOrderDetailsMain(SOrderDetailsMain orderDetailsMain) {
        this.orderDetailsMain = orderDetailsMain;
    }
    
    
    

}


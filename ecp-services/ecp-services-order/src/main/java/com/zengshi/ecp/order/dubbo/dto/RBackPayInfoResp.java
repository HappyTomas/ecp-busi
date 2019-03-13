package com.zengshi.ecp.order.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class RBackPayInfoResp extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -7208951801986076791L;
    
    /** 
     * rBackApplyResp:申请表信息. 
     * @since JDK 1.6 
     */ 
    private RBackApplyResp rBackApplyResp;
    
    /** 
     * sOrderDetailsMain:订单主表相关信息. 
     * @since JDK 1.6 
     */ 
    private SOrderDetailsMain sOrderDetailsMain;

    public RBackApplyResp getrBackApplyResp() {
        return rBackApplyResp;
    }

    public void setrBackApplyResp(RBackApplyResp rBackApplyResp) {
        this.rBackApplyResp = rBackApplyResp;
    }

    public SOrderDetailsMain getsOrderDetailsMain() {
        return sOrderDetailsMain;
    }

    public void setsOrderDetailsMain(SOrderDetailsMain sOrderDetailsMain) {
        this.sOrderDetailsMain = sOrderDetailsMain;
    }
    
    

}


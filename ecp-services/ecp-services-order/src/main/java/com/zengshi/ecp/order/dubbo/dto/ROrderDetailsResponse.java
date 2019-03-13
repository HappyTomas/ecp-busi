package com.zengshi.ecp.order.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class ROrderDetailsResponse extends BaseResponseDTO {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 6623027357534686998L;
    //主订单相关字段
    private SOrderDetailsMain sOrderDetailsMain;
    //子订单相关字段
    private List<SOrderDetailsSub>  sOrderDetailsSubs;
    //订单优惠相关字段
    private SOrderDetailsDiscount sOrderDetailsDiscount;
    //订单跟踪相关字段
    private List<SOrderDetailsTrack> sOrderDetailsTracks;
    //订单收货地址相关字段
    private SOrderDetailsAddr sOrderDetailsAddr;
    //订单普通发票相关字段
    private SOrderDetailsComm sOrderDetailsComm;
    //订单增值税发票相关字段 
    private SOrderDetailsTax sOrderDetailsTax;
    //付款信息表相关字段
    private String  tmp; //待补充
    //物流信息相关字段
    private List<SOrderDetailsDelivery> sOrderDetailsDeliverys;
    //赠品信息
    private List<SOrderDetailsGift> sOrderDetailsGifts;
    //物流信息
    private List<ROrdExpressDetailsResp> ordExpressDetailsResps;
    
    public List<SOrderDetailsDelivery> getsOrderDetailsDeliverys() {
        return sOrderDetailsDeliverys;
    }
    public void setsOrderDetailsDeliverys(List<SOrderDetailsDelivery> sOrderDetailsDeliverys) {
        this.sOrderDetailsDeliverys = sOrderDetailsDeliverys;
    }
    public List<SOrderDetailsSub> getsOrderDetailsSubs() {
        return sOrderDetailsSubs;
    }
    public void setsOrderDetailsSubs(List<SOrderDetailsSub> sOrderDetailsSubs) {
        this.sOrderDetailsSubs = sOrderDetailsSubs;
    }
    public List<SOrderDetailsTrack> getsOrderDetailsTracks() {
        return sOrderDetailsTracks;
    }
    public void setsOrderDetailsTracks(List<SOrderDetailsTrack> sOrderDetailsTracks) {
        this.sOrderDetailsTracks = sOrderDetailsTracks;
    }
    public SOrderDetailsMain getsOrderDetailsMain() {
        return sOrderDetailsMain;
    }
    public void setsOrderDetailsMain(SOrderDetailsMain sOrderDetailsMain) {
        this.sOrderDetailsMain = sOrderDetailsMain;
    }
    public SOrderDetailsDiscount getsOrderDetailsDiscount() {
        return sOrderDetailsDiscount;
    }
    public void setsOrderDetailsDiscount(SOrderDetailsDiscount sOrderDetailsDiscount) {
        this.sOrderDetailsDiscount = sOrderDetailsDiscount;
    }
    public SOrderDetailsAddr getsOrderDetailsAddr() {
        return sOrderDetailsAddr;
    }
    public void setsOrderDetailsAddr(SOrderDetailsAddr sOrderDetailsAddr) {
        this.sOrderDetailsAddr = sOrderDetailsAddr;
    }
    public SOrderDetailsComm getsOrderDetailsComm() {
        return sOrderDetailsComm;
    }
    public void setsOrderDetailsComm(SOrderDetailsComm sOrderDetailsComm) {
        this.sOrderDetailsComm = sOrderDetailsComm;
    }
    public SOrderDetailsTax getsOrderDetailsTax() {
        return sOrderDetailsTax;
    }
    public void setsOrderDetailsTax(SOrderDetailsTax sOrderDetailsTax) {
        this.sOrderDetailsTax = sOrderDetailsTax;
    }
    public String getTmp() {
        return tmp;
    }
    public void setTmp(String tmp) {
        this.tmp = tmp;
    }
	public List<SOrderDetailsGift> getsOrderDetailsGifts() {
		return sOrderDetailsGifts;
	}
	public void setsOrderDetailsGifts(List<SOrderDetailsGift> sOrderDetailsGifts) {
		this.sOrderDetailsGifts = sOrderDetailsGifts;
	}
	public List<ROrdExpressDetailsResp> getOrdExpressDetailsResps() {
		return ordExpressDetailsResps;
	}
	public void setOrdExpressDetailsResps(List<ROrdExpressDetailsResp> ordExpressDetailsResps) {
		this.ordExpressDetailsResps = ordExpressDetailsResps;
	}

}


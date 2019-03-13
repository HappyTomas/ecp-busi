package com.zengshi.ecp.order.dubbo.dto;


import java.util.List;
import java.util.Map;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupCheckInfoRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class RSumbitMainsRequest extends BaseInfo {

    /** 
     * serialVersionUID:. 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7752916158780295106L;

    private Long staffId;
    
    private String source;

    //为立即购买单独设置的变量
    private String sourceKey;
        
    private List<RSumbitMainRequest> sumbitMainList;
    
    private CartPromRespDTO  cartPromRespDTO;
    
    private PromPresentRespDTO  promPresentRespDTO;
    //订单收货地址信息----提交订单时候用
    private ROrdDeliveAddrRequest rOrdDeliveAddrRequest;
    
    //支付方式---提交订单时候使用、
    private String payType;
    
    //买家留言
    private String buyerMsg;


    /**
     * 全部的coupId 包含平台级的和店铺级总和
     * key:coupId (优惠券小类ID)
     * value:skuID的集合  此List表示此优惠券能在哪些单品上使用
     * PS:满减规则可能是多个单品单价总和计算的
     * 如果是没有任何限制规则的抵用券则 List<CoupSkuRespDTO> coupSkuRespDTO不赋值
     */
    private Map<Long,CoupCheckInfoRespDTO> coupIdskuIdMap;

    //可使用的优惠券信息(平台)
    private List<CoupCheckBeanRespDTO> coupPlatfBean;
    
    //分享送积分封装参数
    private Map<Long,Long> shareMap;
    

    public CartPromRespDTO getCartPromRespDTO() {
        return cartPromRespDTO;
    }


    public void setCartPromRespDTO(CartPromRespDTO cartPromRespDTO) {
        this.cartPromRespDTO = cartPromRespDTO;
    }


    public PromPresentRespDTO getPromPresentRespDTO() {
        return promPresentRespDTO;
    }


    public void setPromPresentRespDTO(PromPresentRespDTO promPresentRespDTO) {
        this.promPresentRespDTO = promPresentRespDTO;
    }


    public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public ROrdDeliveAddrRequest getrOrdDeliveAddrRequest() {
        return rOrdDeliveAddrRequest;
    }


    public void setrOrdDeliveAddrRequest(ROrdDeliveAddrRequest rOrdDeliveAddrRequest) {
        this.rOrdDeliveAddrRequest = rOrdDeliveAddrRequest;
    }


    public String getPayType() {
        return payType;
    }


    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    public String getBuyerMsg() {
        return buyerMsg;
    }


    public void setBuyerMsg(String buyerMsg) {
        this.buyerMsg = buyerMsg;
    }


    public List<RSumbitMainRequest> getSumbitMainList() {
        return sumbitMainList;
    }


    public void setSumbitMainList(List<RSumbitMainRequest> sumbitMainList) {
        this.sumbitMainList = sumbitMainList;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public Map<Long, CoupCheckInfoRespDTO> getCoupIdskuIdMap() {
        return coupIdskuIdMap;
    }

    public void setCoupIdskuIdMap(Map<Long, CoupCheckInfoRespDTO> coupIdskuIdMap) {
        this.coupIdskuIdMap = coupIdskuIdMap;
    }

    public List<CoupCheckBeanRespDTO> getCoupPlatfBean() {
        return coupPlatfBean;
    }

    public void setCoupPlatfBean(List<CoupCheckBeanRespDTO> coupPlatfBean) {
        this.coupPlatfBean = coupPlatfBean;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }


	public Map<Long,Long> getShareMap() {
		return shareMap;
	}


	public void setShareMap(Map<Long,Long> shareMap) {
		this.shareMap = shareMap;
	}
}


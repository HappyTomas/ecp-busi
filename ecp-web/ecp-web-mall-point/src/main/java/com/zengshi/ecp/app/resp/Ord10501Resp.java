package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;

public class Ord10501Resp {
    /** 
     * shopId:店铺ID. 
     * @since JDK 1.6 
     */ 
    private Long shopId;
    /** 
     * coupOrdSkuList:优惠券相关信息. 
     * @since JDK 1.6 
     */ 
    private List<CoupCheckBeanRespDTO> coupOrdSkuList;
    /** 
     * acctInfoList:现金账户相关信息. 
     * @since JDK 1.6 
     */ 
    private List<AcctInfoResDTO> acctInfoList;
    public Long getShopId() {
        return shopId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
    public List<CoupCheckBeanRespDTO> getCoupOrdSkuList() {
        return coupOrdSkuList;
    }
    public void setCoupOrdSkuList(List<CoupCheckBeanRespDTO> coupOrdSkuList) {
        this.coupOrdSkuList = coupOrdSkuList;
    }
    public List<AcctInfoResDTO> getAcctInfoList() {
        return acctInfoList;
    }
    public void setAcctInfoList(List<AcctInfoResDTO> acctInfoList) {
        this.acctInfoList = acctInfoList;
    }

}


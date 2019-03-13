package com.zengshi.ecp.app.req;

import java.util.List;

import com.zengshi.ecp.busi.order.vo.RSumbitMainReqVO;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.butterfly.app.model.IBody;

public class Ord009Req extends IBody {

    /** 
     * redisKey:数据缓存中的Key. 
     * @since JDK 1.6 
     */ 
    private String redisKey;
    
    
    /** 
     * addrId:订单收货地址信息ID. 
     * @since JDK 1.6 
     */ 
    private Long addrId;
    
    /** 
     * payType:支付方式. 
     * @since JDK 1.6 
     */ 
    private String payType;
    
    /** 
     * gdsType:. 
     * @since JDK 1.6 
     */ 
    private String gdsType;
    
    /** 
     * sumbitMainList:发票运费资金账户等信息. 
     * @since JDK 1.6 
     */ 
    List<RSumbitMainReqVO> sumbitMainList;

    /** 
     * coupPlatfBean:可使用的优惠券信息(平台). 
     * @since JDK 1.6 
     */ 
    private List<CoupCheckBeanRespDTO> coupPlatfBean;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getGdsType() {
        return gdsType;
    }

    public void setGdsType(String gdsType) {
        this.gdsType = gdsType;
    }

    public List<RSumbitMainReqVO> getSumbitMainList() {
        return sumbitMainList;
    }

    public void setSumbitMainList(List<RSumbitMainReqVO> sumbitMainList) {
        this.sumbitMainList = sumbitMainList;
    }

    public List<CoupCheckBeanRespDTO> getCoupPlatfBean() {
        return coupPlatfBean;
    }

    public void setCoupPlatfBean(List<CoupCheckBeanRespDTO> coupPlatfBean) {
        this.coupPlatfBean = coupPlatfBean;
    }
    
    

}


/**
 * RSumbitMainsReqVO.java	  V1.0   2015-10-9 下午8:00:14
 *
 * Copyright 2015 AsiaInfo Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.order.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;

public class RSumbitMainsReqVO extends EcpBasePageReqVO {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
    
    //订单收货地址信息----提交订单时候用
	@NotNull
    private Long addrId;
    
    //支付方式---提交订单时候使用、
	@NotNull
    private String payType;
	
	//收货地址
	private String gdsType;
    
    List<RSumbitMainReqVO> sumbitMainList;
    
    /**
     * redis keyName
     */
    private String mainHashKey;

    //可使用的优惠券信息(平台)
    private List<CoupCheckBeanRespDTO> coupPlatfBean;
    
    public List<RSumbitMainReqVO> getSumbitMainList() {
        return sumbitMainList;
    }

    public void setSumbitMainList(List<RSumbitMainReqVO> sumbitMainList) {
        this.sumbitMainList = sumbitMainList;
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

    public List<CoupCheckBeanRespDTO> getCoupPlatfBean() {
        return coupPlatfBean;
    }

    public void setCoupPlatfBean(List<CoupCheckBeanRespDTO> coupPlatfBean) {
        this.coupPlatfBean = coupPlatfBean;
    }

    public String getMainHashKey() {
        return mainHashKey;
    }

    public void setMainHashKey(String mainHashKey) {
        this.mainHashKey = mainHashKey;
    }
}

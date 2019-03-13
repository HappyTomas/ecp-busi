package com.zengshi.ecp.busi.goods.vo;

import java.math.BigDecimal;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class CommissionVO extends  EcpBasePageReqVO{
	
    private static final long serialVersionUID = -6620744971867241470L;
    
    private String catgCode;
    
    private String catgName;

    private Long shopId;

    private BigDecimal commission;
    
    private String operateId;

	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public String getCatgName() {
		return catgName;
	}

	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	
    
    



	
    
    

}

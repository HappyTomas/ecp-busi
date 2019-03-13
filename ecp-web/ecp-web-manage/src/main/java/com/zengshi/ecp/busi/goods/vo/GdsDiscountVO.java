package com.zengshi.ecp.busi.goods.vo;

import java.math.BigDecimal;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GdsDiscountVO extends  EcpBasePageReqVO{
	
    private static final long serialVersionUID = -6620744971867241470L;
    
    private Long id01;
    private Long id02;
    private Long id03;
    private Long id04;
    
    private String custLevelCode;

    private String catgCode;
    
    private String catgName;

    private Long shopId;

    private String status;
    
    private String operateId;

    private BigDecimal discount01;
    
    private BigDecimal discount02;

    private BigDecimal discount03;

    private BigDecimal discount04;



	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public String getCatgName() {
		return catgName;
	}

	public void setCatgName(String catgName) {
		this.catgName = catgName;
	}


	public String getCatgCode() {
		return catgCode;
	}

	public void setCatgCode(String catgCode) {
		this.catgCode = catgCode;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

   
    public BigDecimal getDiscount01() {
        return discount01;
    }

    public void setDiscount01(BigDecimal discount01) {
        this.discount01 = discount01;
    }

    public BigDecimal getDiscount02() {
        return discount02;
    }

    public void setDiscount02(BigDecimal discount02) {
        this.discount02 = discount02;
    }

    public BigDecimal getDiscount03() {
        return discount03;
    }

    public void setDiscount03(BigDecimal discount03) {
        this.discount03 = discount03;
    }

    public BigDecimal getDiscount04() {
        return discount04;
    }

    public void setDiscount04(BigDecimal discount04) {
        this.discount04 = discount04;
    }

    public Long getId01() {
        return id01;
    }

    public void setId01(Long id01) {
        this.id01 = id01;
    }

    public Long getId02() {
        return id02;
    }

    public void setId02(Long id02) {
        this.id02 = id02;
    }

    public Long getId03() {
        return id03;
    }

    public void setId03(Long id03) {
        this.id03 = id03;
    }

    public Long getId04() {
        return id04;
    }

    public void setId04(Long id04) {
        this.id04 = id04;
    }

    public String getCustLevelCode() {
        return custLevelCode;
    }

    public void setCustLevelCode(String custLevelCode) {
        this.custLevelCode = custLevelCode;
    }
    
    

}

package com.zengshi.ecp.busi.seller.prom.createprom.vo;

import java.math.BigDecimal;

import com.zengshi.ecp.busi.seller.prom.goods.vo.GdsVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class MatchGdsVO extends GdsVO{

    private static final long serialVersionUID = 1L;
  
    private Long id;

    private Long promId;
    
    private Long price;

    private String matchType;
    
    private String mainCatgName;
    
    private BigDecimal sortNum;//排序
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getMainCatgName() {
        return mainCatgName;
    }

    public void setMainCatgName(String mainCatgName) {
        this.mainCatgName = mainCatgName;
    }

	public BigDecimal getSortNum() {
		return sortNum;
	}

	public void setSortNum(BigDecimal sortNum) {
		this.sortNum = sortNum;
	}

}

package com.zengshi.ecp.busi.seller.prom.goods.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-09 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class StockVO implements Serializable{

    private static final long serialVersionUID = 1L;

    // 仓库id
    private Long id;

    // 仓库名称
    private String repName;

    // 企业编码
    private Long companyId;

    // 店铺编码
    private Long shopId;

    // 仓库类型
    private String repType;

    // 库存类型
    private String stockType;

    // 仓库下库存列表
    private List<StockInfoVO> stockInfoVOList;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public List<StockInfoVO> getStockInfoVOList() {
        return stockInfoVOList;
    }

    public void setStockInfoVOList(List<StockInfoVO> stockInfoVOList) {
        this.stockInfoVOList = stockInfoVOList;
    }
  
}

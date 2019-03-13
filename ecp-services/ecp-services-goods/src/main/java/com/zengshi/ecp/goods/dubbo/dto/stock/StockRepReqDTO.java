package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockRepReqDTO extends BaseInfo {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 8037141286008919261L;

    private Long id;

    private String repName;

    private Long companyId;

    private Long shopId;

    private String codeType;

    private String repType;

    private String remark;

    private String status;
    
    private Long staffId;
    //判断商品是否区域化库存
    private Boolean ifRegionalStock;
    //仓库下有效仓库适用范围
    private List<StockRepAdaptReqDTO>  stockRepAdaptDTOs = new ArrayList<StockRepAdaptReqDTO>();

  
    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

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
        this.repName = repName == null ? null : repName.trim();
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

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType == null ? null : codeType.trim();
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType == null ? null : repType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Boolean getIfRegionalStock() {
        return ifRegionalStock;
    }

    public void setIfRegionalStock(Boolean ifRegionalStock) {
        this.ifRegionalStock = ifRegionalStock;
    }

    public List<StockRepAdaptReqDTO> getStockRepAdaptDTOs() {
        return stockRepAdaptDTOs;
    }

    public void setStockRepAdaptDTOs(List<StockRepAdaptReqDTO> stockRepAdaptDTOs) {
        this.stockRepAdaptDTOs = stockRepAdaptDTOs;
    }


   
  

}


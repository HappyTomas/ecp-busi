package com.zengshi.ecp.busi.seller.goods.vo.rep;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockRepEditInfo extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 7436975727702905569L;
    @NotNull(message="{goods.StockRepEditInfo.id.null.error}")
    private Long id;
    
    @NotNull(message="{goods.StockRepEditInfo.shopId.null.error}")
    private Long shopId;
    
    
    @NotBlank(message="{goods.StockRepAddInitInfo.repName.null.error}")
    @Length(min=1,max=32,message="{goods.StockRepInfo.repName.length.error}")
    private String repName;
    
    private String newStockRepAdaptVOsStr ;
    
    private String delStockRepAdaptVOsStr;

  

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getNewStockRepAdaptVOsStr() {
        return newStockRepAdaptVOsStr;
    }

    public void setNewStockRepAdaptVOsStr(String newStockRepAdaptVOsStr) {
        this.newStockRepAdaptVOsStr = newStockRepAdaptVOsStr;
    }

    public String getDelStockRepAdaptVOsStr() {
        return delStockRepAdaptVOsStr;
    }

    public void setDelStockRepAdaptVOsStr(String delStockRepAdaptVOsStr) {
        this.delStockRepAdaptVOsStr = delStockRepAdaptVOsStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "StockRepEditInfo [id=" + id + ", shopId=" + shopId + ", repName=" + repName
                + ", newStockRepAdaptVOsStr=" + newStockRepAdaptVOsStr
                + ", delStockRepAdaptVOsStr=" + delStockRepAdaptVOsStr + "]";
    }
    
    
}


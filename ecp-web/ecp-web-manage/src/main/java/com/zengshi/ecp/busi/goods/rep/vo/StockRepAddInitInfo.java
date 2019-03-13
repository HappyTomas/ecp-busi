package com.zengshi.ecp.busi.goods.rep.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockRepAddInitInfo extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -6025716099302121334L;
    @NotBlank(message="{goods.StockRepAddInitInfo.repName.null.error}")
    private String repName;
    @NotNull(message="{goods.StockRepAddInitInfo.shopId.null.error}")
    private Long shopId;
    private List<AreaInfo> areaInfos = new ArrayList<AreaInfo>();

    private String newStockRepAdaptVOsStr ;

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public List<AreaInfo> getAreaInfos() {
        return areaInfos;
    }

    public void setAreaInfos(List<AreaInfo> areaInfos) {
        this.areaInfos = areaInfos;
    }

    public String getNewStockRepAdaptVOsStr() {
        return newStockRepAdaptVOsStr;
    }

    public void setNewStockRepAdaptVOsStr(String newStockRepAdaptVOsStr) {
        this.newStockRepAdaptVOsStr = newStockRepAdaptVOsStr;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "StockRepAddInitInfo [repName=" + repName + ", shopId=" + shopId + ", areaInfos="
                + areaInfos + ", newStockRepAdaptVOsStr=" + newStockRepAdaptVOsStr + "]";
    }

 

}

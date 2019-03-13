package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class AffirmStockMainDTO extends BaseInfo{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -848475762026396130L;

    // 确认收货，子订单级别参数信息
    private List<AffirmStockDTO> affirmStockDTOs = new ArrayList<AffirmStockDTO>();

    // 操作人
    private Long staffId;

    // 店铺id
    private Long shopId;

    // 企业id
    private Long companyId;

    public List<AffirmStockDTO> getAffirmStockDTOs() {
        return affirmStockDTOs;
    }

    public void setAffirmStockDTOs(List<AffirmStockDTO> affirmStockDTOs) {
        this.affirmStockDTOs = affirmStockDTOs;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

}

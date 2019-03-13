package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-9-2下午5:29:27 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
public class StockInfoReqDTO extends BaseInfo {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 4224629260683399290L;

    private String repType;

    private Long id;

    private Long repCode;

    private String catgCode;

    private Long typeId;

    private Long gdsId;

    private Long skuId;

    private Long shopId;

    private Long companyId;

    private Long lackCount;

    private Long warningCount;

    private String isUsewarning;

    private String isOver;

    private String stockType;

    private String status;

    private Long staffId;

    // 库存更新类型
    private String turnType;

    // 库存变更数量
    private Long turnCount;

    // 库存操作id
    private Long optId;

    private String gdsName;
    
    /**
     * 工厂库存
     */
    private Long facStock;

    // 仓库适用范围列表
    private List<StockRepAdaptReqDTO> stockRepAdapts = new ArrayList<StockRepAdaptReqDTO>();

    private Long realCount;

    private Long preOccupyCount;

    private Long availCount;

    private Long sendCount;

    // 库存状态（00：缺货 01：紧俏 02：充裕）
    private String stockStatus;

    // isbn号
    private String isbn;

    // 订单域操作流水号
    private String ordOptNo;

    private String productNo;

    private String catgPath;
    
    /**
     * 扩展字段1
     */
    private String ext1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRepCode() {
        return repCode;
    }

    public void setRepCode(Long repCode) {
        this.repCode = repCode;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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

    public Long getLackCount() {
        return lackCount;
    }

    public void setLackCount(Long lackCount) {
        this.lackCount = lackCount;
    }

    public Long getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(Long warningCount) {
        this.warningCount = warningCount;
    }

    public String getIsUsewarning() {
        return isUsewarning;
    }

    public void setIsUsewarning(String isUsewarning) {
        this.isUsewarning = isUsewarning == null ? null : isUsewarning.trim();
    }

    public String getIsOver() {
        return isOver;
    }

    public void setIsOver(String isOver) {
        this.isOver = isOver == null ? null : isOver.trim();
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType == null ? null : stockType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRepType() {
        return repType;
    }

    public void setRepType(String repType) {
        this.repType = repType;
    }

    public String getTurnType() {
        return turnType;
    }

    public void setTurnType(String turnType) {
        this.turnType = turnType;
    }

    public Long getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(Long turnCount) {
        this.turnCount = turnCount;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
    }

    public List<StockRepAdaptReqDTO> getStockRepAdapts() {
        return stockRepAdapts;
    }

    public void setStockRepAdapts(List<StockRepAdaptReqDTO> stockRepAdapts) {
        this.stockRepAdapts = stockRepAdapts;
    }

    public Long getRealCount() {
        return realCount;
    }

    public void setRealCount(Long realCount) {
        this.realCount = realCount;
    }

    public Long getPreOccupyCount() {
        return preOccupyCount;
    }

    public void setPreOccupyCount(Long preOccupyCount) {
        this.preOccupyCount = preOccupyCount;
    }

    public Long getAvailCount() {
        return availCount;
    }

    public void setAvailCount(Long availCount) {
        this.availCount = availCount;
    }

    public Long getSendCount() {
        return sendCount;
    }

    public void setSendCount(Long sendCount) {
        this.sendCount = sendCount;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getOrdOptNo() {
        return ordOptNo;
    }

    public void setOrdOptNo(String ordOptNo) {
        this.ordOptNo = ordOptNo;
    }

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCatgPath() {
		return catgPath;
	}

	public void setCatgPath(String catgPath) {
		this.catgPath = catgPath;
	}

    public Long getFacStock() {
        return facStock;
    }

    public void setFacStock(Long facStock) {
        this.facStock = facStock;
    }

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}


}

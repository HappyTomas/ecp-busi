package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015-9-2下午5:29:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
public class StockInfoRespDTO extends BaseResponseDTO{
    

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

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
    //库存更新类型
    private String turnType;
    //库存变更数量
    private Long turnCount;
    //库存操作id
    private Long optId;
    //商品Id列表（库存管理列表模糊查询）
    private List<Long> gdsIds;
    //仓库适用范围列表
    private List<StockRepAdaptReqDTO> stockRepAdapts = new ArrayList<StockRepAdaptReqDTO>();


    private Long realCount;

    private Long preOccupyCount;

    private Long availCount;

    private Long sendCount;
    //库存状态（00：缺货 01：紧俏 02：充裕）
    private String stockStatus;
    
    private String gdsName;

    private String productNo;

    private String catgPath;

    private Timestamp createTime;
    private Long createStaff;
    private Timestamp updateTime;
    private Long updateStaff;
    
    /**
     * 工厂库存
     */
    private Long facStock;
    
    
    /**
     * 零库存持续标识0-表示非持续零库存 1-表示持续零库存
     */
    private String zeroStockFlag;

    /**
     * 零库存持续开始时间,如果是零库存则该字段有值反之为空
     */
    private Timestamp zeroStockStarttime;

    
    
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

    public List<Long> getGdsIds() {
        return gdsIds;
    }

    public void setGdsIds(List<Long> gdsIds) {
        this.gdsIds = gdsIds;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

	public String getGdsName() {
		return gdsName;
	}

	public void setGdsName(String gdsName) {
		this.gdsName = gdsName;
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

    public String getZeroStockFlag() {
        return zeroStockFlag;
    }

    public void setZeroStockFlag(String zeroStockFlag) {
        this.zeroStockFlag = zeroStockFlag;
    }

    public Timestamp getZeroStockStarttime() {
        return zeroStockStarttime;
    }

    public void setZeroStockStarttime(Timestamp zeroStockStarttime) {
        this.zeroStockStarttime = zeroStockStarttime;
    }

   
}


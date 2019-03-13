package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Date;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromImportRespDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String fileId;

    private String fileName;

    private String importType;

    private Long promId;

    private String catgCode;

    private Long siteId;

    private Long shopId;

    private String gdsName;

    private Long gdsId;

    private Long skuId;

    private Long price;

    private Long promCnt;

    private Long giftId;

    private Long everyTimeCnt;

    private String importDesc;

    private Date createTime;

    private Long createStaff;
    
    private String mainCatgs;//分类代码
    
    private String mainCatgName;//分类名称
    
    private String siteName;//站点名称
    
    private String shopName;//店铺名称
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public Long getPromId() {
        return promId;
    }

    public void setPromId(Long promId) {
        this.promId = promId;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getPromCnt() {
        return promCnt;
    }

    public void setPromCnt(Long promCnt) {
        this.promCnt = promCnt;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Long getEveryTimeCnt() {
        return everyTimeCnt;
    }

    public void setEveryTimeCnt(Long everyTimeCnt) {
        this.everyTimeCnt = everyTimeCnt;
    }

    public String getImportDesc() {
        return importDesc;
    }

    public void setImportDesc(String importDesc) {
        this.importDesc = importDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public String getMainCatgs() {
        return mainCatgs;
    }

    public void setMainCatgs(String mainCatgs) {
        this.mainCatgs = mainCatgs;
    }

    public String getMainCatgName() {
        return mainCatgName;
    }

    public void setMainCatgName(String mainCatgName) {
        this.mainCatgName = mainCatgName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
 


}

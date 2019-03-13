package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class PromSolrRespDTO extends BaseResponseDTO {

    private static final long serialVersionUID = 1L;
    
    private String gdsStatus;//商品状态
    private Long gdsId;//商品编码
    //private Long catlogId;//目录编码
    private Long catgCode;//分类编码
    private Long promId;//促销编码
    private Long staffId;//操作人
    private Long shopId;//店铺编码
    public String getGdsStatus() {
        return gdsStatus;
    }
    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus;
    }
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getCatgCode() {
        return catgCode;
    }
    public void setCatgCode(Long catgCode) {
        this.catgCode = catgCode;
    }
/*    public Long getCatlogId() {
        return catlogId;
    }
    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }*/
    public Long getPromId() {
        return promId;
    }
    public void setPromId(Long promId) {
        this.promId = promId;
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
}
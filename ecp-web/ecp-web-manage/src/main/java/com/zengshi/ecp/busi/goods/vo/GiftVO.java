package com.zengshi.ecp.busi.goods.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class GiftVO extends EcpBasePageReqVO{
    /** 
     * serialVersionUID:TODO(赠品新增、编辑保存参数). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 3464039206382633089L;
    
    private Long giftId;//赠品编码
    
    @NotNull(message="{goods.giftVO.giftName.null.error}")
    @Length(min=0,max=32, message="{goods.giftVO.giftName.length.error}")
    private String giftName;//赠品名称

    @NotNull(message="{goods.giftVO.giftValue.null.error}")
    private String giftValue;//赠品价值
    
    @NotNull(message="{goods.giftVO.giftAmount.null.error}")
    private Long giftAmount;//赠品总量
    
    private Long gdsId;//商品编码
    
    @NotNull(message="{goods.giftVO.skuId.null.error}")
    private Long skuId;//单品编码

    private Long shopId;//店铺编码

    @NotNull(message="{goods.giftVO.giftPic.null.error}")
    private String giftPic;//赠品图片ID
    
    private String pictrueName;//赠品图片名称
    
    @NotNull(message="{goods.giftVO.giftDesc.null.error}")
    @Length(min=0,max=64, message="{goods.giftVO.giftDesc.length.error}")
    private String giftDesc;//赠品描述

    private Long createStaff;//创建工号

    private Long updateStaff;//更新工号
    
    private Long giftValid;//赠品可赠量
    
    private String giftType;//赠品类型
    
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


    public String getGiftPic() {
        return giftPic;
    }


    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic;
    }


    public String getGiftName() {
        return giftName;
    }


    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }


    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public String getPictrueName() {
        return pictrueName;
    }

    public void setPictrueName(String pictrueName) {
        this.pictrueName = pictrueName;
    }


    public Long getGiftAmount() {
        return giftAmount;
    }


    public void setGiftAmount(Long giftAmount) {
        this.giftAmount = giftAmount;
    }

    public Long getCreateStaff() {
        return createStaff;
    }


    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }


    public Long getUpdateStaff() {
        return updateStaff;
    }


    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }


    public Long getGiftId() {
        return giftId;
    }


    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }


    public Long getGiftValid() {
        return giftValid;
    }


    public void setGiftValid(Long giftValid) {
        this.giftValid = giftValid;
    }


    public String getGiftValue() {
        return giftValue;
    }


    public void setGiftValue(String giftValue) {
        this.giftValue = giftValue;
    }


    public String getGiftType() {
        return giftType;
    }


    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    
}


package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsGiftReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    private Long id;//赠品编码

    private Long gdsId;

    private Long skuId;

    private Long shopId;

    private String giftPic;

    private String giftName;

    private String giftDesc;

    private Long giftValue;

    private String giftStatus;

    private Long giftAmount;

    private Long giftSend;

    private Long giftValid;

    private Timestamp startTime;

    private Timestamp endTime;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String idUpDown;//赠品是否调增调减  1：调增，0：调减
    
    private Long changeAmount;//调增或者调减的数量。即变化量
    
    private List<GdsGiftReqDTO> list;//批量赠品数量变更。
    
    private String giftType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getGiftValue() {
        return giftValue;
    }

    public void setGiftValue(Long giftValue) {
        this.giftValue = giftValue;
    }

    public String getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(String giftStatus) {
        this.giftStatus = giftStatus;
    }

    public Long getGiftAmount() {
        return giftAmount;
    }

    public void setGiftAmount(Long giftAmount) {
        this.giftAmount = giftAmount;
    }

    public Long getGiftSend() {
        return giftSend;
    }

    public void setGiftSend(Long giftSend) {
        this.giftSend = giftSend;
    }

    public Long getGiftValid() {
        return giftValid;
    }

    public void setGiftValid(Long giftValid) {
        this.giftValid = giftValid;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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

    public String getIdUpDown() {
        return idUpDown;
    }

    public void setIdUpDown(String idUpDown) {
        this.idUpDown = idUpDown;
    }

    public Long getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Long changeAmount) {
        this.changeAmount = changeAmount;
    }

    public List<GdsGiftReqDTO> getList() {
        return list;
    }

    public void setList(List<GdsGiftReqDTO> list) {
        this.list = list;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }
    
    
}


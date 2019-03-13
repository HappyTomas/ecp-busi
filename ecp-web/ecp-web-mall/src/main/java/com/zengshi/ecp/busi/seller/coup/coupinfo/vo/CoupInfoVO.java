package com.zengshi.ecp.busi.seller.coup.coupinfo.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-09 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupInfoVO implements Serializable{

    /**
     * 优惠券基本信息vo
     */
    private static final long serialVersionUID = 1L;
    
    //优惠券id
    private Long id;
    
    //站点id
    @NotNull(message="{coup.coupInfoVO.siteId.null.error}")
    private Long siteId;

    //优惠券类型id
    private Long coupTypeId;
    
    //优惠券名称
    //@NotNull(message="{coup.coupInfoVO.coupName.null.error}")
    @NotEmpty(message="{coup.coupInfoVO.coupName.null.error}")
    private String coupName;

    //优惠券面额
    @NotEmpty(message="{coup.coupInfoVO.coupValue.null.error}")
    private String coupValue;
    
    //使用规则
    private String useRuleCode;
    
    //领取规则
    private String gainRuleCode;
    
    //店铺id
    @NotNull(message="{coup.coupInfoVO.shopId.null.error}")
    private Long shopId;

    //是否优惠码
    private String ifCode;

    //是否支持退货
    private String ifBack;

    //优惠券数量
    @NotNull(message="{coup.coupInfoVO.coupNum.null.error}")
    private Long coupNum;
    
    //无发行量限制  1 无发行量 coupNum==null  0有发行量 coupNum 必需大于0
    private String checkPublish;
    
    //领取数量
    private Long getNum;

    //优惠券期限类型
    private String effType;

    //天数
    private Integer fixedTime;

    //生效时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activeTime;

    //截止时间
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inactiveTime;

    //状态
    private String status;

    //备注
    private String remark;

    private Date createTime;

    private Long createStaff;
    //与所有优惠券共同使用
    //不与其他优惠券共同使用
    //不与特定的优惠券共同使用
    private String coupExclude;
    
    //优惠券范围   0平台券 1店铺券
    private String typeLimit;

    private String coupCode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getCoupTypeId() {
        return coupTypeId;
    }

    public void setCoupTypeId(Long coupTypeId) {
        this.coupTypeId = coupTypeId;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public String getUseRuleCode() {
        return useRuleCode;
    }

    public void setUseRuleCode(String useRuleCode) {
        this.useRuleCode = useRuleCode;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getIfCode() {
        return ifCode;
    }

    public void setIfCode(String ifCode) {
        this.ifCode = ifCode;
    }

    public String getIfBack() {
        return ifBack;
    }

    public void setIfBack(String ifBack) {
        this.ifBack = ifBack;
    }

    public Long getCoupNum() {
        return coupNum;
    }

    public void setCoupNum(Long coupNum) {
        this.coupNum = coupNum;
    }

    public Long getGetNum() {
        return getNum;
    }

    public void setGetNum(Long getNum) {
        this.getNum = getNum;
    }

    public String getEffType() {
        return effType;
    }

    public void setEffType(String effType) {
        this.effType = effType;
    }

    public Integer getFixedTime() {
        return fixedTime;
    }

    public void setFixedTime(Integer fixedTime) {
        this.fixedTime = fixedTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(Date inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCheckPublish() {
        return checkPublish;
    }

    public void setCheckPublish(String checkPublish) {
        this.checkPublish = checkPublish;
    }

    public String getGainRuleCode() {
        return gainRuleCode;
    }

    public void setGainRuleCode(String gainRuleCode) {
        this.gainRuleCode = gainRuleCode;
    }

    public String getCoupExclude() {
        return coupExclude;
    }

    public void setCoupExclude(String coupExclude) {
        this.coupExclude = coupExclude;
    }

    public String getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(String typeLimit) {
        this.typeLimit = typeLimit;
    }

    public String getCoupCode() {
        return coupCode;
    }

    public void setCoupCode(String coupCode) {
        this.coupCode = coupCode;
    }

    public String getCoupValue() {
        return coupValue;
    }

    public void setCoupValue(String coupValue) {
        this.coupValue = coupValue;
    }

}

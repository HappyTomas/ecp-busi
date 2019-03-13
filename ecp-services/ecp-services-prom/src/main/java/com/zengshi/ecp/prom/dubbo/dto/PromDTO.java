package com.zengshi.ecp.prom.dubbo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long groupId;

    private String promTypeCode;

    private String ifShow;

    private String promClass;

    private String promTheme;

    private String promContent;

    private String promTypeShow;

    private Long priority;

    private String status;

    private Date startTime;

    private Date endTime;

    private Date showStartTime;

    private Date showEndTime;

    private Long shopId;

    private String promUrl;

    private String promImg;

    private String joinRange;

    private String ifBlacklist;

    private Date createTime;

    private Long createStaff;

    private Date updateTime;

    private Long updateStaff;

    private PromConstraintDTO promConstraintDTO;//促销规则

    private ArrayList<?> blackSkuList;//黑名单列表

    private ArrayList<?> skuList;//单品列表
    
    private ArrayList<?> catgList;//分类列表
    
    private ArrayList<?> catgLimitList;//分类黑名单列表

    private String discountRule;// 优惠规则 json格式字符串

    private String ifMatch;
    
    private List<PromGiftDTO> giftList;//赠品列表
    
    private List<PromMatchSkuDTO> matchSkuDTOList;//搭配商品列表
    
    private Long siteId;
    
    private String ifComposit;
    
    private String keyWord;//搜索关键字
    
    private String ifFreePost;//是否免邮

    // 根据 id 判断两个 PromVo 是否相等
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o.getClass() == PromDTO.class) {
            PromDTO n = (PromDTO) o;
            return n.id.equals(id);
        }
        return Boolean.FALSE;
    }

    // 根据 id 计算 PromVo 对象的 hashCode() 返回值
    public int hashCode() {
        return id.hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPromTypeCode() {
        return promTypeCode;
    }

    public void setPromTypeCode(String promTypeCode) {
        this.promTypeCode = promTypeCode;
    }

    public String getIfShow() {
        return ifShow;
    }

    public void setIfShow(String ifShow) {
        this.ifShow = ifShow;
    }

    public String getPromClass() {
        return promClass;
    }

    public void setPromClass(String promClass) {
        this.promClass = promClass;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
    }

    public String getPromContent() {
        return promContent;
    }

    public void setPromContent(String promContent) {
        this.promContent = promContent;
    }

    public String getPromTypeShow() {
        return promTypeShow;
    }

    public void setPromTypeShow(String promTypeShow) {
        this.promTypeShow = promTypeShow;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(Date showStartTime) {
        this.showStartTime = showStartTime;
    }

    public Date getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(Date showEndTime) {
        this.showEndTime = showEndTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPromUrl() {
        return promUrl;
    }

    public void setPromUrl(String promUrl) {
        this.promUrl = promUrl;
    }

    public String getPromImg() {
        return promImg;
    }

    public void setPromImg(String promImg) {
        this.promImg = promImg;
    }

    public String getJoinRange() {
        return joinRange;
    }

    public void setJoinRange(String joinRange) {
        this.joinRange = joinRange;
    }

    public String getIfBlacklist() {
        return ifBlacklist;
    }

    public void setIfBlacklist(String ifBlacklist) {
        this.ifBlacklist = ifBlacklist;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public PromConstraintDTO getPromConstraintDTO() {
        return promConstraintDTO;
    }

    public void setPromConstaintDTO(PromConstraintDTO promConstraintDTO) {
        this.promConstraintDTO = promConstraintDTO;
    }

    public ArrayList<?> getBlackSkuList() {
        return blackSkuList;
    }

    public void setBlackSkuList(ArrayList<?> blackSkuList) {
        this.blackSkuList = blackSkuList;
    }

    public ArrayList<?> getSkuList() {
        return skuList;
    }

    public void setSkuList(ArrayList<?> skuList) {
        this.skuList = skuList;
    }

    public String getDiscountRule() {
        return discountRule;
    }

    public void setDiscountRule(String discountRule) {
        this.discountRule = discountRule;
    }

    public String getIfMatch() {
        return ifMatch;
    }

    public void setIfMatch(String ifMatch) {
        this.ifMatch = ifMatch;
    }

    public List<PromGiftDTO> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<PromGiftDTO> giftList) {
        this.giftList = giftList;
    }

    public List<PromMatchSkuDTO> getMatchSkuDTOList() {
        return matchSkuDTOList;
    }

    public void setMatchSkuDTOList(List<PromMatchSkuDTO> matchSkuDTOList) {
        this.matchSkuDTOList = matchSkuDTOList;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getIfComposit() {
        return ifComposit;
    }

    public void setIfComposit(String ifComposit) {
        this.ifComposit = ifComposit;
    }

    public ArrayList<?> getCatgList() {
        return catgList;
    }

    public void setCatgList(ArrayList<?> catgList) {
        this.catgList = catgList;
    }

    public ArrayList<?> getCatgLimitList() {
        return catgLimitList;
    }

    public void setCatgLimitList(ArrayList<?> catgLimitList) {
        this.catgLimitList = catgLimitList;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

	public String getIfFreePost() {
		return ifFreePost;
	}

	public void setIfFreePost(String ifFreePost) {
		this.ifFreePost = ifFreePost;
	}

}

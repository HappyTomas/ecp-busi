package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsArrmsgReqDTO extends BaseInfo{

    
    /** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = -5513833849870001629L;

	private Long id;

    private String noticeType;

    private Long noticePrice;

    private Long staffId;

    private Long gdsId;

    private Long skuId;

    private Long  shopId;

    private String mobile;

    private String email;

    private String dealStaff;

    private Timestamp dealTime;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String gdsName;//商品名称
    
    private List<GdsArrmsgReqDTO> gdsArrmsgReqDTOList;
    
    private List<Long> msgIds;
    
    private String skuName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public Long getNoticePrice() {
        return noticePrice;
    }

    public void setNoticePrice(Long noticePrice) {
        this.noticePrice = noticePrice;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDealStaff() {
        return dealStaff;
    }

    public void setDealStaff(String dealStaff) {
        this.dealStaff = dealStaff;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<GdsArrmsgReqDTO> getGdsArrmsgReqDTOList() {
        return gdsArrmsgReqDTOList;
    }

    public void setGdsArrmsgReqDTOList(List<GdsArrmsgReqDTO> gdsArrmsgReqDTOList) {
        this.gdsArrmsgReqDTOList = gdsArrmsgReqDTOList;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

	public List<Long> getMsgIds() {
		return msgIds;
	}

	public void setMsgIds(List<Long> msgIds) {
		this.msgIds = msgIds;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
    
    
    
}


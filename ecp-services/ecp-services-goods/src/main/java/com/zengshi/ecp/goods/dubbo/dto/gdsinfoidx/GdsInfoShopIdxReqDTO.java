package com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseInfo;
public class GdsInfoShopIdxReqDTO extends BaseInfo    {

	/** 
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
	 * @since JDK 1.6 
	 */ 
	private static final long serialVersionUID = 726746524044687190L;

	private Long shopId;

    private Long gdsId;

    private Long snapId;

    private String gdsName;

    private String gdsSubHead;

    private Long guidePrice;

    private String gdsTypeCode;

    private String gdsStatus;

    private String gdsApprove;

    private String platCatgs;

    private String shopCatgs;

    private Integer sortNo;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String isbn;
    
    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 扩展字段4
     */
    private String ext4;

    /**
     * 扩展字段5
     */
    private String ext5;
    
    public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSnapId() {
        return snapId;
    }

    public void setSnapId(Long snapId) {
        this.snapId = snapId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getGdsSubHead() {
        return gdsSubHead;
    }

    public void setGdsSubHead(String gdsSubHead) {
        this.gdsSubHead = gdsSubHead == null ? null : gdsSubHead.trim();
    }

    public Long getGuidePrice() {
        return guidePrice;
    }

    public void setGuidePrice(Long guidePrice) {
        this.guidePrice = guidePrice;
    }

    public String getGdsTypeCode() {
        return gdsTypeCode;
    }

    public void setGdsTypeCode(String gdsTypeCode) {
        this.gdsTypeCode = gdsTypeCode == null ? null : gdsTypeCode.trim();
    }

    public String getGdsStatus() {
        return gdsStatus;
    }

    public void setGdsStatus(String gdsStatus) {
        this.gdsStatus = gdsStatus == null ? null : gdsStatus.trim();
    }

    public String getGdsApprove() {
        return gdsApprove;
    }

    public void setGdsApprove(String gdsApprove) {
        this.gdsApprove = gdsApprove == null ? null : gdsApprove.trim();
    }

    public String getPlatCatgs() {
        return platCatgs;
    }

    public void setPlatCatgs(String platCatgs) {
        this.platCatgs = platCatgs == null ? null : platCatgs.trim();
    }

    public String getShopCatgs() {
        return shopCatgs;
    }

    public void setShopCatgs(String shopCatgs) {
        this.shopCatgs = shopCatgs == null ? null : shopCatgs.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }
    
    
}

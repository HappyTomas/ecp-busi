package com.zengshi.ecp.goods.dubbo.dto.gdsinfores;

import java.sql.Timestamp;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
public class GdsGds2CatgRespDTO extends BaseResponseDTO    {


    /** 
     * serialVersionUID:TODO(序列Id). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;

    private Long gdsId;

    private String gdsName;

    private String catgCode;
    
    private String catgType;

    private String categoryRange;

    private String gds2catgType;

    private String catgPath;

    private Long  shopId;

    private String status;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;
    
    private String isbn;
    
    private String gdsStatus;
    
    private Long catlogId;
    
    public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName == null ? null : gdsName.trim();
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getCategoryRange() {
        return categoryRange;
    }

    public void setCategoryRange(String categoryRange) {
        this.categoryRange = categoryRange == null ? null : categoryRange.trim();
    }

    public String getGds2catgType() {
        return gds2catgType;
    }

    public void setGds2catgType(String gds2catgType) {
        this.gds2catgType = gds2catgType == null ? null : gds2catgType.trim();
    }

    public String getCatgPath() {
        return catgPath;
    }

    public void setCatgPath(String catgPath) {
        this.catgPath = catgPath == null ? null : catgPath.trim();
    }

    public Long  getShopId() {
        return shopId;
    }

    public void setShopId(Long  shopId) {
        this.shopId = shopId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getGdsStatus() {
		return gdsStatus;
	}

	public void setGdsStatus(String gdsStatus) {
		this.gdsStatus = gdsStatus;
	}

	public String getCatgType() {
        return catgType;
    }

    public void setCatgType(String catgType) {
        this.catgType = catgType;
    }
    

    public Long getCatlogId() {
        return catlogId;
    }

    public void setCatlogId(Long catlogId) {
        this.catlogId = catlogId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GdsGds2CatgRespDTO [gdsId=");
        builder.append(gdsId);
        builder.append(", gdsName=");
        builder.append(gdsName);
        builder.append(", catgCode=");
        builder.append(catgCode);
        builder.append(", catgType=");
        builder.append(catgType);
        builder.append(", categoryRange=");
        builder.append(categoryRange);
        builder.append(", gds2catgType=");
        builder.append(gds2catgType);
        builder.append(", catgPath=");
        builder.append(catgPath);
        builder.append(", shopId=");
        builder.append(shopId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", createStaff=");
        builder.append(createStaff);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append(", updateStaff=");
        builder.append(updateStaff);
        builder.append(", isbn=");
        builder.append(isbn);
        builder.append(", gdsStatus=");
        builder.append(gdsStatus);
        builder.append(", catlogId=");
        builder.append(catlogId);
        builder.append("]");
        return builder.toString();
    }

    
}

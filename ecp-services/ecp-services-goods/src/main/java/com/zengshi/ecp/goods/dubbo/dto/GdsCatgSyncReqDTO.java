package com.zengshi.ecp.goods.dubbo.dto;

import java.sql.Timestamp;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatgSyncReqDTO extends BaseInfo{
    
    private Long id;

    private String catgCode;

    private String catgName;

    private Integer sortNo;

    private String catgParent;

    private String catgParentName;

    private String srcSystem;

    private String status;

    private Timestamp operTime;

    private Timestamp createTime;

    private Long createStaff;

    private Timestamp updateTime;

    private Long updateStaff;

    private String mapCatgCode;
    //需要删除的记录id列表
    private List<Long> ids;
    //是否映射数据 1已经映射0不映射，不传则所有
    private String ifMap;
    
    private Long shopId;
    private Long shopAuthId;
    
    /**
     * 操作类型. 0-增加 1－删除 2－修改
     */
    private Integer actionType;
    
    private String catgType;
    
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode == null ? null : catgCode.trim();
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName == null ? null : catgName.trim();
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCatgParent() {
        return catgParent;
    }

    public void setCatgParent(String catgParent) {
        this.catgParent = catgParent == null ? null : catgParent.trim();
    }

    public String getCatgParentName() {
        return catgParentName;
    }

    public void setCatgParentName(String catgParentName) {
        this.catgParentName = catgParentName == null ? null : catgParentName.trim();
    }

    public String getSrcSystem() {
        return srcSystem;
    }

    public void setSrcSystem(String srcSystem) {
        this.srcSystem = srcSystem == null ? null : srcSystem.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

   

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
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

    public String getMapCatgCode() {
        return mapCatgCode;
    }

    public void setMapCatgCode(String mapCatgCode) {
        this.mapCatgCode = mapCatgCode == null ? null : mapCatgCode.trim();
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catgCode=").append(catgCode);
        sb.append(", catgName=").append(catgName);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", catgParent=").append(catgParent);
        sb.append(", catgParentName=").append(catgParentName);
        sb.append(", srcSystem=").append(srcSystem);
        sb.append(", status=").append(status);
        sb.append(", operTime=").append(operTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", mapCatgCode=").append(mapCatgCode);
        sb.append("]");
        return sb.toString();
    }

    public String getIfMap() {
        return ifMap;
    }

    public void setIfMap(String ifMap) {
        this.ifMap = ifMap;
    }

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getCatgType() {
		return catgType;
	}

	public void setCatgType(String catgType) {
		this.catgType = catgType;
	}

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopAuthId() {
        return shopAuthId;
    }

    public void setShopAuthId(Long shopAuthId) {
        this.shopAuthId = shopAuthId;
    }
 
    
}


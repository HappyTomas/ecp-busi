package com.zengshi.ecp.busi.goods.gdscategory.vo;

import com.zengshi.ecp.busi.goods.common.vo.BaseTreeVO;


public class CategorySyncTreeVO extends BaseTreeVO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -152057391172966313L;

    private Long delId;

    private String delIds;
    
    private String srcSystem;

    private String mapCatgCode;

    private String catgName;
    
    private Boolean isRoot;

    public String getSrcSystem() {
        return srcSystem;
    }

    public void setSrcSystem(String srcSystem) {
        this.srcSystem = srcSystem;
    }

    public String getMapCatgCode() {
        return mapCatgCode;
    }

    public void setMapCatgCode(String mapCatgCode) {
        this.mapCatgCode = mapCatgCode;
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    public Long getDelId() {
        return delId;
    }

    public void setDelId(Long delId) {
        this.delId = delId;
    }

    public String getDelIds() {
        return delIds;
    }

    public void setDelIds(String delIds) {
        this.delIds = delIds;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }
}


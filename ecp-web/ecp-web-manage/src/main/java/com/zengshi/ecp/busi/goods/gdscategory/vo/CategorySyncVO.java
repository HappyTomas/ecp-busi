package com.zengshi.ecp.busi.goods.gdscategory.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;


public class CategorySyncVO extends EcpBasePageReqVO{

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
    
    private String catgCode;
    
    private String id;
    //是否已已经映射1：是0否
    private String ifMap;
    //条件查询的来源,用于来源展示
    private String catgSrcSystem;
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

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIfMap() {
        return ifMap;
    }

    public void setIfMap(String ifMap) {
        this.ifMap = ifMap;
    }

    public String getCatgSrcSystem() {
        return catgSrcSystem;
    }

    public void setCatgSrcSystem(String catgSrcSystem) {
        this.catgSrcSystem = catgSrcSystem;
    }
}


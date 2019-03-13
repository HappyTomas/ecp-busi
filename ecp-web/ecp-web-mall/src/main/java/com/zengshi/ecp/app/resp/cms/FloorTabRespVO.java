package com.zengshi.ecp.app.resp.cms;

import com.zengshi.butterfly.app.model.IBody;

public class FloorTabRespVO extends IBody {
    
    private Long id;
    
    private String tabName;
    /**
     * 数据来源  行为分析  或者配置
     */
    private String dataSource  = "";

    /**
     * 行为分析统计类型
     */
    private String countType  = "";
    /**
     * 行为分析 统计对应商品分类
     */
    private String catgCode  = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getCatgCode() {
        return catgCode;
    }

    public void setCatgCode(String catgCode) {
        this.catgCode = catgCode;
    }
}


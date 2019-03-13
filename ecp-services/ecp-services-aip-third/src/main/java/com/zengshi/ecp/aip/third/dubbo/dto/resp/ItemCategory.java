package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

/**
 * Created by cbl on 2017/2/15.
 */
public class ItemCategory {
    private Long cid;
    private Long parent_cid;
    private String name;
    private Boolean is_parent;
    private List<ItemCategory> sub_categories;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getParent_cid() {
        return parent_cid;
    }

    public void setParent_cid(Long parent_cid) {
        this.parent_cid = parent_cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_parent() {
        return is_parent;
    }

    public void setIs_parent(Boolean is_parent) {
        this.is_parent = is_parent;
    }

    public List<ItemCategory> getSub_categories() {
        return sub_categories;
    }

    public void setSub_categories(List<ItemCategory> sub_categories) {
        this.sub_categories = sub_categories;
    }
}

package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import java.util.List;

/**
 * Created by cbl on 2017/2/15.
 */
public class SuccResponse {
    private List<Categories> categories;

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}

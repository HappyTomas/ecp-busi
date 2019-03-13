package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Created by HDF on 2016/6/1.
 */
public class Gds023Req extends IBody {

    /**
     * 店铺id
     */
    private String id;

    /**
     * 分页大小
     */
    private int pageSize;

    /**
     * 页码
     */
    private int pageNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

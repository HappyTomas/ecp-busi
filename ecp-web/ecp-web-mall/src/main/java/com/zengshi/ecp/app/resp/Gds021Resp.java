package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.app.resp.gds.ShopSearchResultVO;
import com.zengshi.butterfly.app.model.IBody;

import java.util.List;

/**
 * Created by HDF on 2016/6/1.
 */
public class Gds021Resp extends IBody {

    /**
     * 店铺列表
     */
    private  List<ShopSearchResultVO> pageRespVO ;

    /**
     * 总条数
     */
    private long count = 0;

    /**
     * 页数
     */
    private long pageCount;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<ShopSearchResultVO> getPageRespVO() {
        return pageRespVO;
    }

    public void setPageRespVO(List<ShopSearchResultVO> pageRespVO) {
        this.pageRespVO = pageRespVO;
    }
}

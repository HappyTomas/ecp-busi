package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.app.resp.gds.GdsCategoryVO;
import com.zengshi.butterfly.app.model.IBody;

import java.util.List;

/**
 * Created by HDF on 2016/6/1.
 */
public class Gds025Resp extends IBody {

    /**
     * 一级分类列表
     */
    private List<GdsCategoryVO> catgList;

    public List<GdsCategoryVO> getCatgList() {
        return catgList;
    }

    public void setCatgList(List<GdsCategoryVO> catgList) {
        this.catgList = catgList;
    }
}

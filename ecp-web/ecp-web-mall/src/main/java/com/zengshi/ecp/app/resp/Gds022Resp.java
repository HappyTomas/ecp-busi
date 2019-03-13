package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.butterfly.app.model.IBody;

import java.util.List;

/**
 * Created by HDF on 2016/6/1.
 */
public class Gds022Resp extends IBody {

    /**
     * 搜索建议列表
     */
    private List<CollationReuslt> collationReuslts ;

    public List<CollationReuslt> getCollationReuslts() {
        return collationReuslts;
    }

    public void setCollationReuslts(List<CollationReuslt> collationReuslts) {
        this.collationReuslts = collationReuslts;
    }
}

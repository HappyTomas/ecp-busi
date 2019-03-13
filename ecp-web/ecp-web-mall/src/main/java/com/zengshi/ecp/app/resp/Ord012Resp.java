package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Ord012Resp extends IBody {
    /** 
     * pageNo:请求查询的页码. 
     * @since JDK 1.6 
     */ 
    private int pageNo = 1;

    /** 
     * pageSize:每页显示条数. 
     * @since JDK 1.6 
     */ 
    private int pageSize;

    /** 
     * count:总条数. 
     * @since JDK 1.6 
     */ 
    private long count = 0;
    /** 
     * Ord01201Resps:订单信息列表. 
     * @since JDK 1.6 
     */ 
    private List<Ord01201Resp> Ord01201Resps;
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public List<Ord01201Resp> getOrd01201Resps() {
        return Ord01201Resps;
    }
    public void setOrd01201Resps(List<Ord01201Resp> ord01201Resps) {
        Ord01201Resps = ord01201Resps;
    }
    
    
}


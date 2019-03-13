package com.zengshi.ecp.busi.order.vo.myorder;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

import java.util.List;

/**
 * Created by wang on 16/8/2.
 */
public class ROrdersRespVO extends EcpBaseResponseVO{

    private List<ROrderRespVO> datas;

    private Integer pageNo = Integer.valueOf(1);
    private Integer pageSize;
    private long total = 0L;
    private long pageCount;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<ROrderRespVO> getDatas() {
        return datas;
    }

    public void setDatas(List<ROrderRespVO> datas) {
        this.datas = datas;
    }
}

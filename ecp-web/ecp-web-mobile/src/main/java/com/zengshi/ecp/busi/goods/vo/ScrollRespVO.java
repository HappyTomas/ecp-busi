package com.zengshi.ecp.busi.goods.vo;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;

public class ScrollRespVO {
    public Long total;
    
    public List<GdsEvalRespDTO> datas;
    
    public Long page;
    
    public Long count;
    
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<GdsEvalRespDTO> getDatas() {
        return datas;
    }

    public void setDatas(List<GdsEvalRespDTO> datas) {
        this.datas = datas;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
    
}


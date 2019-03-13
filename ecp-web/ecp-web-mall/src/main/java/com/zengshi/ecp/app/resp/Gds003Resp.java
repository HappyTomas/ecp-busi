package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsSeckillDiscountDTO;
import com.zengshi.ecp.app.resp.gds.PromListBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds003Resp extends IBody {

    
    private List<PromListBaseInfo> saleList;
    
    private GdsSeckillDiscountDTO seckill;

    

    public List<PromListBaseInfo> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<PromListBaseInfo> saleList) {
        this.saleList = saleList;
    }

    public GdsSeckillDiscountDTO getSeckill() {
        return seckill;
    }

    public void setSeckill(GdsSeckillDiscountDTO seckill) {
        this.seckill = seckill;
    }

    
    
    
    
    
}


package com.zengshi.ecp.app.req;

import com.zengshi.butterfly.app.model.IBody;

public class Pointmgds001Req extends IBody {

    private Long gdsId;
    
    private Long skuId;
    
    private Long staffId;

    public Long getGdsId() {
        return gdsId;
    }

    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}


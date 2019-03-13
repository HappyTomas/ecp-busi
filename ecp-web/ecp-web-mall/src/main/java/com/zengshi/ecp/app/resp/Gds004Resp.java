package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsPromMatchBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds004Resp extends IBody {


    private List<GdsPromMatchBaseInfo> autoCombineList;

    private List<GdsPromMatchBaseInfo> fixedCombineList;

    private Long skuId;


    public List<GdsPromMatchBaseInfo> getAutoCombineList() {
        return autoCombineList;
    }

    public void setAutoCombineList(List<GdsPromMatchBaseInfo> autoCombineList) {
        this.autoCombineList = autoCombineList;
    }

    public List<GdsPromMatchBaseInfo> getFixedCombineList() {
        return fixedCombineList;
    }

    public void setFixedCombineList(List<GdsPromMatchBaseInfo> fixedCombineList) {
        this.fixedCombineList = fixedCombineList;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

}

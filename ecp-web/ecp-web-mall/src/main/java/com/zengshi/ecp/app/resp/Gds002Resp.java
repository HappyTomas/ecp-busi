package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.ecp.app.resp.gds.GdsDetailBaseInfo;
import com.zengshi.butterfly.app.model.IBody;

public class Gds002Resp extends IBody {

    
    
    private List<GdsDetailBaseInfo> commondCatGds;



    public List<GdsDetailBaseInfo> getCommondCatGds() {
        return commondCatGds;
    }

    public void setCommondCatGds(List<GdsDetailBaseInfo> commondCatGds) {
        this.commondCatGds = commondCatGds;
    }  
    
    
}


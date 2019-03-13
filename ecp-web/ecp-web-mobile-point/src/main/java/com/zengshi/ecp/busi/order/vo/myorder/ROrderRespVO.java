package com.zengshi.ecp.busi.order.vo.myorder;

import java.util.List;

/**
 * Created by wang on 16/8/2.
 */
public class ROrderRespVO {

    private ROrdMainRespVO ordMain;
    private List<ROrdSubRespVO> ordSubs;

    public ROrdMainRespVO getOrdMain() {
        return ordMain;
    }

    public void setOrdMain(ROrdMainRespVO ordMain) {
        this.ordMain = ordMain;
    }

    public List<ROrdSubRespVO> getOrdSubs() {
        return ordSubs;
    }

    public void setOrdSubs(List<ROrdSubRespVO> ordSubs) {
        this.ordSubs = ordSubs;
    }
}

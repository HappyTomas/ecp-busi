package com.zengshi.ecp.unpf.dubbo.dto.order;

import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.util.Date;

/**
 * Created by guojingman on 2017/2/23.
 */
public class RYouzanOrderReq extends BaseInfo {
    //交易状态更新开始时间
    private Date startUpdate;
    //交易状态更新结束时间
    private Date endUpdate;

    public Date getStartUpdate() {
        return startUpdate;
    }

    public void setStartUpdate(Date startUpdate) {
        this.startUpdate = startUpdate;
    }

    public Date getEndUpdate() {
        return endUpdate;
    }

    public void setEndUpdate(Date endUpdate) {
        this.endUpdate = endUpdate;
    }
}

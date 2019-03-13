package com.zengshi.ecp.aip.third.dubbo.dto.resp;

import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.util.List;

/**
 * Created by guojingman on 2017/2/22.
 * 有赞订单数据对象，包括订单和订单商品
 */
public class YouzanOrdersResp extends BaseInfo {
    //查询到数据总数，分页的时候需要判断是否有下一页
    private int totalCount;
    private List<YouzanOrders> youzanOrders;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<YouzanOrders> getYouzanOrders() {
        return youzanOrders;
    }

    public void setYouzanOrders(List<YouzanOrders> youzanOrders) {
        this.youzanOrders = youzanOrders;
    }
}

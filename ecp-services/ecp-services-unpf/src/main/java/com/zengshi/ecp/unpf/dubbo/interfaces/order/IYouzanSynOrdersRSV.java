package com.zengshi.ecp.unpf.dubbo.interfaces.order;

import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.YouzanPushOrdersEntityReq;

/**
 * Created by guojingman on 2017/2/23.
 */
public interface IYouzanSynOrdersRSV {

    /**
     * 批量获取有赞订单数据,同步保存到统一平台中
     *
     * @param orderReq
     */
    void saveOrdersFromYouzan(RYouzanOrderReq orderReq);

    /**
     * 接收有赞推送过来的订单数据
     *
     * @param orderReq 订单数据对象，包含订单和订单商品信息
     */
    void saveOrUpdateOrdersFromYouzanPush(YouzanPushOrdersEntityReq orderReq);

}

package com.zengshi.ecp.unpf.service.busi.order.interfaces;

import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.YouzanPushOrdersEntityReq;

/**
 * Created by guojingman on 2017/2/23.
 * 从有赞同步订单数据
 */
public interface IYouzanSynOrdersSV {
    /**
     * 有赞订单数据同步保存到统一平台
     *
     * @param orderReq
     */
    void saveOrdersFromYouzan(RYouzanOrderReq orderReq);

    /**
     * 接收保存第三方系统推送过来的订单数据，已存在更新，否则保存
     *
     * @param orderReq 订单数据对象，包含订单和订单商品信息
     */
    void saveOrUpdateOrdersFromYouzanPush(YouzanPushOrdersEntityReq orderReq);

}

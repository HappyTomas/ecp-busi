package com.zengshi.ecp.aip.third.service.busi.order.youzan;

import com.zengshi.ecp.aip.third.dubbo.dto.req.YouzanOrderReq;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrdersResp;

/**
 * Created by guojingman on 2017/2/24.
 */
public interface IYouzanOrderSV {

    /**
     * 批量获取有赞订单数据
     *
     * @param orderReq
     * @return
     */
    YouzanOrdersResp fetchOrdersFromYouzan(YouzanOrderReq orderReq);

}

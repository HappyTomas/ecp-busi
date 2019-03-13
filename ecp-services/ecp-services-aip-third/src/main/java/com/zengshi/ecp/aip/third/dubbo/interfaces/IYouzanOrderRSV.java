package com.zengshi.ecp.aip.third.dubbo.interfaces;


import com.zengshi.ecp.aip.third.dubbo.dto.req.YouzanOrderReq;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrdersResp;

/**
 * Created by guojingman on 2017/2/21.
 * 有赞订单同步接口
 */
public interface IYouzanOrderRSV {

    /**
     * 批量获取有赞订单数据
     *
     * @param orderReq
     * @return
     */
    YouzanOrdersResp fetchOrdersFromYouzan(YouzanOrderReq orderReq);

}

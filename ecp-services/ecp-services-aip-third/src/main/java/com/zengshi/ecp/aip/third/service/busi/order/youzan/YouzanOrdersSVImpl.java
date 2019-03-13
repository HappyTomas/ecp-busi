package com.zengshi.ecp.aip.third.service.busi.order.youzan;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.YouzanOrderReq;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrders;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrdersResp;
import com.zengshi.ecp.aip.third.model.youzan.Orders;
import com.zengshi.ecp.aip.third.model.youzan.Trades;
import com.zengshi.ecp.aip.third.model.youzan.TradesRootBean;
import com.zengshi.ecp.aip.third.service.busi.client.youzan.KdtApiClient;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by guojingman on 2017/2/24.
 */
public class YouzanOrdersSVImpl implements IYouzanOrderSV {
    /**
     * 批量获取有赞订单数据接口方法
     * 接口文档：http://open.youzan.com/apilist/detail/trade?name=kdt.trades.sold.get
     */
    private static final String BAT_FETCH_ORDERS = "kdt.trades.sold.get";
    /**
     * 如果没有设置分页参数，默认获取30条数据
     */
    private static final int DEFAULT_PAGE_SIZE = 30;


    @Override
    public YouzanOrdersResp fetchOrdersFromYouzan(YouzanOrderReq orderReq) {
        TradesRootBean rootBean = getTrades(orderReq);

        return fillYouzanOrdersResp(rootBean);
    }

    /**
     * 把值设置到YouzanOrdersResp对象中
     *
     * @param rootBean
     * @return
     */
    private YouzanOrdersResp fillYouzanOrdersResp(TradesRootBean rootBean) {
        int totalCount = Integer.parseInt(rootBean.getResponse().getTotalResults());
        YouzanOrdersResp ordersResp = new YouzanOrdersResp();
        ordersResp.setTotalCount(totalCount);
        if (totalCount == 0) {
            ordersResp.setYouzanOrders(Collections.EMPTY_LIST);
            return ordersResp;
        }
        List<YouzanOrders> ordersList = new ArrayList<>();
        List<Trades> tradeList = rootBean.getResponse().getTrades();
        //获取店铺名称
        String shopName = getShopName(rootBean);
        for (Trades trade : tradeList) {
            YouzanOrders orders = new YouzanOrders();
            orders.setOuterId(trade.getTid());
            orders.setStatus(trade.getStatus());
            orders.setPlatType(AipThirdConstants.Commons.YOUZAN);
            orders.setBuyerMsg(trade.getBuyerMessage());
            orders.setContractAddr(trade.getReceiverAddress());
            orders.setContractTel(trade.getReceiverMobile());
            orders.setContractNum(trade.getReceiverMobile());
            orders.setContractZipcode(trade.getReceiverZip());
            orders.setContractProvince(trade.getReceiverState());
            orders.setContractDistrict(trade.getReceiverDistrict());
            orders.setContractCity(trade.getReceiverCity());
            orders.setContractName(trade.getReceiverName());
            orders.setStaffCode(trade.getFansInfo().getFansNickname());
            orders.setShopName(shopName);
            orders.setBuyerNick(trade.getBuyerNick());
            //邮费
            long postFee = (long) (Double.parseDouble(trade.getPostFee()) * 100);
            orders.setRealExpressFee(postFee);
            //实付金额
            long payment = (long) (Double.parseDouble(trade.getPayment()) * 100);
            orders.setRealMoney(payment);
            //配送方式
            orders.setDispatchType(trade.getShippingType());
            //下单时间
            Timestamp orderTime = DateUtil.getTimestamp(trade.getCreated(),"yyyy-MM-dd HH:mm:ss");
            orders.setOrderTime(orderTime);
            //付款时间
            if (StringUtil.isNotBlank(trade.getPayTime())) {
                Timestamp payTime = DateUtil.getTimestamp(trade.getPayTime(),"yyyy-MM-dd HH:mm:ss");
                orders.setPayTime(payTime);
            }
            if (StringUtil.isNotBlank(trade.getConsignTime())) {
                //发货时间
                Timestamp dispatchTime = DateUtil.getTimestamp(trade.getConsignTime(),"yyyy-MM-dd HH:mm:ss");
                orders.setDispatchTime(dispatchTime);
            }

            //订单商品总数量
            orders.setOrderAmount(trade.getNum() + "");
            //订单商品
            setOrderGoods(orders, trade);
            ordersList.add(orders);
        }
        ordersResp.setYouzanOrders(ordersList);
        return ordersResp;
    }

    private String getShopName(TradesRootBean rootBean) {
        String shopName = "";
        try {
            shopName = rootBean.getResponse().getTrades().get(0).getOrders().get(0).getSellerNick();
        } catch (Exception e) {
            LogUtil.error(this.getClass().getName(), "获取店铺名称失败", e);
        }
        return shopName;
    }

    /**
     * 设置订单商品
     *
     * @param youzanOrders
     * @param trade
     */
    private void setOrderGoods(YouzanOrders youzanOrders, Trades trade) {
        List<YouzanOrders.OrderGoods> orderGoodsList = new ArrayList<>();
        List<Orders> ordersList = trade.getOrders();
        if (ordersList != null && ordersList.size() > 0) {
            for (Orders order : ordersList) {
                YouzanOrders.OrderGoods orderGoods = new YouzanOrders.OrderGoods();
                orderGoods.setPlatType(AipThirdConstants.Commons.YOUZAN);
                //有赞主订单id
                orderGoods.setOuterId(trade.getTid());
                //有赞子订单id（即交易商品id）
                orderGoods.setOuterSubId(order.getOid() + "");
                orderGoods.setGdsId(order.getNumIid());
                orderGoods.setGdsName(order.getTitle());
                orderGoods.setSkuId(order.getSkuUniqueCode());
                orderGoods.setSkuInfo(order.getSkuPropertiesName());
                //订购数量
                orderGoods.setOrderAmount(order.getNum());
                //商品价格
                long price = (long) (Double.parseDouble(order.getPrice()) * 100);
                orderGoods.setOrderPrice(price);
                //商品状态 已发货,待发货,退款中(买家申请退款),退款关闭,退款成功
                orderGoods.setStatus(order.getStateStr());
                //买家id
                orderGoods.setStaffId(Long.parseLong(trade.getFansInfo().getBuyerId()));
                orderGoods.setPicUrl(order.getPicThumbPath());
                orderGoods.setIsSend(order.getIsSend());
                orderGoods.setDiscountFee(order.getDiscountFee());
                orderGoods.setOrderMoney(order.getTotalFee());
                orderGoods.setRealMoney(order.getPayment());
                orderGoodsList.add(orderGoods);
            }
        }
        youzanOrders.setOrderGoods(orderGoodsList);
    }

    /**
     * 获取有赞订单数据，转换为java bean对象
     *
     * @param orderReq
     * @return
     * @throws IOException
     */
    private TradesRootBean getTrades(YouzanOrderReq orderReq) {
        Map<String, Object> params = new HashMap<>();
        //设置查询参数数，包含分页信息
        setParams(orderReq, params);
        String content = null;
        try {
            KdtApiClient client = new KdtApiClient(orderReq.getAppId(), orderReq.getAppSecret());
            HttpResponse response = client.get(BAT_FETCH_ORDERS, params);
            if (200 == response.getStatusLine().getStatusCode()) {
                content = getResponseContent(response);
                LogUtil.info("youzanOrdersSVImpl","从有赞拉取订单数据："+content);
            } else {
                throw new IllegalStateException("接口数据返回失败");
            }
        } catch (Exception e) {
            LogUtil.error(this.getClass().getName(), "请求有赞服务接口异常", e);
        }
        try {
            TradesRootBean bean = JSON.parseObject(content, TradesRootBean.class);
            return bean;
        } catch (Exception e) {
            LogUtil.error(this.getClass().getName(), "json格式转换异常", e);
        }
        return null;
    }

    /**
     * 设置请求参数
     *
     * @param orderReq
     * @param params
     */
    private void setParams(YouzanOrderReq orderReq, Map<String, Object> params) {
        Date startTime = orderReq.getStartUpdate();
        Date endTime = orderReq.getEndUpdate();
        int pageNo = orderReq.getPageNo();
        int pageSize = orderReq.getPageSize();
        if (startTime != null && endTime != null) {
            params.put("start_update", DateFormatUtils.format(startTime,"yyyy-MM-dd HH:mm:ss"));
            params.put("end_update", DateFormatUtils.format(endTime,"yyyy-MM-dd HH:mm:ss"));
        }
        pageNo = pageNo == 0 ? 1 : pageNo;
        pageSize = pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize;
        params.put("page_no", pageNo);
        params.put("page_size", pageSize);
        if (StringUtil.isNotBlank(orderReq.getStatus())) {
            params.put("status", orderReq.getStatus());
        }
    }

    private String getResponseContent(HttpResponse response) {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = response.getEntity().getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (Exception e) {
            LogUtil.error(this.getClass().getName(), "获取接口数据异常", e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                LogUtil.error(this.getClass().getName(), "IO异常", e);
            }
        }

        return null;
    }


}

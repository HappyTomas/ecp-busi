package com.zengshi.controller.third.msg;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.msgSyc.UnpfMsgSycReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.YouzanPushOrdersEntityReq;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.msgSyc.IUnpfMsgSycRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IYouzanSynOrdersRSV;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.model.youzan.Msg;
import com.zengshi.model.youzan.Orders;
import com.zengshi.model.youzan.Trade;
import com.zengshi.model.youzan.YouzanPushEntity;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.Md5Util;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by guojingman on 2017/2/13.
 * 有赞官方接口文档：http://open.youzan.com/docs/server#424
 */
@Controller
@RequestMapping("/youzan")
public class YouzanOrderController {

    private final static Logger logger = LoggerFactory.getLogger(YouzanOrderController.class);

    @Resource
    private IUnpfShopAuthRSV unpfShopAuthRSV;
    @Resource
    private IUnpfMsgSycRSV unpfMsgSycRSV;
    @Resource
    private IYouzanSynOrdersRSV youzanSynOrdersRSV;

    /**
     * 0：商家自有消息推送 1：服务商消息推送
     */
    private static final int MODE = 0;
    /**
     * 对接平台 有赞
     */
    private static final String PLAT_TYPE = "youzan";
    /**
     * 业务类型
     */
    private static final String BUSINESS_TYPE = "TRADE";
    /**
     * 没有配置授权信息
     */
    private static final String MSG_STATUS_NOT_AUTH = "2";
    /**
     * 签名不正确
     */
    private static final String MSG_STATUS_SIGN_ERROR = "3";
    /**
     * 报文解析
     */
    private static final String MSG_STATUS_DECODE_ERROR = "4";

    /**
     * 接收有赞推送过来的订单数据
     */
    @ResponseBody
    @RequestMapping(value = "/push", method = {RequestMethod.POST})
    public Object receiveOrder(@RequestBody YouzanPushEntity entity) {
        logger.info("youzan push orders data:" + entity.toString());
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");

        /**
         *  是心跳检查
         *  或非当前选择的消息推送方式
         *  或非TRADE业务类型则直接返回，不做业务处理
         */
        if (entity.isTest() || MODE != entity.getMode() || !BUSINESS_TYPE.equals(entity.getType())) {
            return res;
        }

        //平台授权检查
        UnpfShopAuthRespDTO shopAuthInfo = getShopAuthInfo(entity.getApp_id());
        if (shopAuthInfo == null) {
            logger.error("没有配置授权,店铺id：" + entity.getKdt_id());
            saveMsgSyc(MSG_STATUS_NOT_AUTH, "有赞没有配置授权,店铺名称：" + entity.getKdt_name(), entity.getKdt_id());
            res.put("code", 1);
            res.put("msg", "没有授权");
            return res;
        }

        String msg = entity.getMsg();
        /**
         * 数据签名验证
         */
        String sign = Md5Util.encode(shopAuthInfo.getAppkey() + msg + shopAuthInfo.getAppscret());
        if (!sign.equals(entity.getSign())) {
            logger.error("接收到的消息签名校验失败");
            saveMsgSyc(MSG_STATUS_SIGN_ERROR, "有赞订单数据签名校验错误,店铺名:" + entity.getKdt_name(), entity.getKdt_id());
            res.put("code", 1);
            res.put("msg", "数字签名校验错误");
            return res;
        }

        //转码
        try {
            msg = URLDecoder.decode(msg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("转码错误");
            res.put("code", 1);
            res.put("msg", "转码错误");
            return res;
        }
        logger.info("youzan push msg:" + msg);
        Msg msgEntity = null;
        try {
            msgEntity = JSON.parseObject(msg, Msg.class);
        } catch (Exception e) {
            LogUtil.error("YouzanOrderController", "有赞数据解析错误", e);
            saveMsgSyc(MSG_STATUS_DECODE_ERROR, "有赞数据解析错误,店铺名称：" + entity.getKdt_name(), entity.getKdt_id());
            res.put("code", 1);
            res.put("msg", "数据格式错误");
            return res;
        }

        //处理业务
        YouzanPushOrdersEntityReq pushOrdersEntityReq = packageMainOrder(entity, msgEntity);
        pushOrdersEntityReq.setShopId(shopAuthInfo.getShopId());
        youzanSynOrdersRSV.saveOrUpdateOrdersFromYouzanPush(pushOrdersEntityReq);

        return res;
    }

    /**
     * 记录错误日志
     *
     * @param status 错误码
     * @param errMsg 错误描述
     */
    private void saveMsgSyc(String status, String errMsg, int kdtId) {
        UnpfMsgSycReqDTO unpfMsgSycReqDTO = new UnpfMsgSycReqDTO();
        Long id = unpfMsgSycRSV.getMsgSycId();
        unpfMsgSycReqDTO.setPlatType(PLAT_TYPE);
        unpfMsgSycReqDTO.setTradeType(BUSINESS_TYPE);
        unpfMsgSycReqDTO.setId(id);
        unpfMsgSycReqDTO.setUpdateStaff(1L);
        unpfMsgSycReqDTO.setErrors(errMsg);
        unpfMsgSycReqDTO.setStatus(status);
        unpfMsgSycReqDTO.setShopId((long) kdtId);
        unpfMsgSycReqDTO.setMsg(errMsg);
        unpfMsgSycRSV.saveUnpfMsgSyc(unpfMsgSycReqDTO);
    }

    /**
     * 封装主订单对象
     *
     * @param entity
     * @return
     */
    private YouzanPushOrdersEntityReq packageMainOrder(YouzanPushEntity entity, Msg msgEntity) {
        YouzanPushOrdersEntityReq ordMainReq = new YouzanPushOrdersEntityReq();
        Trade trade = msgEntity.getTrade();
        //第三方系统订单号，唯一
        ordMainReq.setOuterId(entity.getId());
        ordMainReq.setPlatType(PLAT_TYPE);
        ordMainReq.setStaffCode(trade.getFansInfo().getFansNickname());
        //邮费
        long postFee = (long) (Double.parseDouble(trade.getPostFee()) * 100);
        ordMainReq.setRealExpressFee(postFee);
        //实付金额
        long payment = (long) (Double.parseDouble(trade.getPayment()) * 100);
        ordMainReq.setRealMoney(payment);
        //订单状态
        ordMainReq.setStatus(trade.getStatus());
        //买家留言
        ordMainReq.setBuyerMsg(trade.getBuyerMessage());
        //买家收货省份
        ordMainReq.setContractProvince(trade.getReceiverState());
        //买家收货城市
        ordMainReq.setContractCity(trade.getReceiverCity());
        //收货地区
        ordMainReq.setContractDistrict(trade.getReceiverDistrict());
        //收货详细地址
        ordMainReq.setContractAddr(trade.getReceiverAddress());
        //收货邮编
        ordMainReq.setContractZipcode(trade.getReceiverZip());
        //收货联系电话
        ordMainReq.setContractNum(trade.getReceiverMobile());
        //收货联系手机
        ordMainReq.setContractTel(trade.getReceiverMobile());
        //收货人姓名
        ordMainReq.setContractName(trade.getReceiverName());
        //配送方式
        ordMainReq.setDispatchType(trade.getShippingType());
        //下单时间
        if (StringUtil.isNotBlank(trade.getCreated())) {
            Timestamp orderTime = DateUtil.getTimestamp(trade.getCreated(), "yyyy-MM-dd HH:mm:ss");
            ordMainReq.setOrderTime(orderTime);
        }
        //付款时间
        if (StringUtil.isNotBlank(trade.getPayTime())) {
            Timestamp payTime = DateUtil.getTimestamp(trade.getPayTime(), "yyyy-MM-dd HH:mm:ss");
            ordMainReq.setPayTime(payTime);
        }
        //发货时间
        if (StringUtil.isNotBlank(trade.getConsignTime())) {
            Timestamp dispatchTime = DateUtil.getTimestamp(trade.getConsignTime(), "yyyy-MM-dd HH:mm:ss");
            ordMainReq.setDispatchTime(dispatchTime);
        }
        ordMainReq.setShopName(entity.getKdt_name());
        //订单商品总数量
        ordMainReq.setOrderAmount(trade.getNum() + "");
        List<YouzanPushOrdersEntityReq.PushOrdersGoods> ordersGoodsList = getSubOrders(msgEntity.getTrade());
        ordMainReq.setOrderGoods(ordersGoodsList);

        return ordMainReq;
    }

    /**
     * 封装订单商品信息
     *
     * @param trade
     * @return
     */
    private List<YouzanPushOrdersEntityReq.PushOrdersGoods> getSubOrders(Trade trade) {
        List<Orders> orders = trade.getOrders();
        if (orders != null && orders.size() > 0) {
            List<YouzanPushOrdersEntityReq.PushOrdersGoods> ordSubReqs = new ArrayList<>();
            for (Orders order : orders) {
                YouzanPushOrdersEntityReq.PushOrdersGoods subReq = new YouzanPushOrdersEntityReq.PushOrdersGoods();
                subReq.setPlatType(PLAT_TYPE);
                //有赞主订单id
                subReq.setOuterId(trade.getTid());
                //有赞子订单id（即交易商品id）
                subReq.setOuterSubId(order.getOid() + "");
                subReq.setGdsId(order.getNumIid());
                subReq.setGdsName(order.getTitle());
                subReq.setSkuId(order.getSkuUniqueCode());
                subReq.setSkuInfo(order.getSkuPropertiesName());
                //订购数量
                subReq.setOrderAmount(order.getNum());
                //商品价格
                long price = (long) (Double.parseDouble(order.getPrice()) * 100);
                subReq.setOrderPrice(price);
                //商品状态 已发货,待发货,退款中(买家申请退款),退款关闭,退款成功
                subReq.setStatus(order.getStateStr());
                //买家id
                subReq.setStaffId(Long.parseLong(trade.getFansInfo().getBuyerId()));
                subReq.setPicUrl(order.getPicThumbPath());
                subReq.setRealMoney(order.getPayment());
                subReq.setDiscountFee(order.getDiscountFee());
                subReq.setOrderMoney(order.getTotalFee());
                ordSubReqs.add(subReq);
            }
            return ordSubReqs;
        }
        return Collections.emptyList();
    }

    private UnpfShopAuthRespDTO getShopAuthInfo(String appKey) {
        UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
        unpfShopAuthReqDTO.setPlatType(PLAT_TYPE);
        unpfShopAuthReqDTO.setStatus(UnpfConstants.PlatType4Shop.STATUS_1);
        unpfShopAuthReqDTO.setAppkey(appKey);
        PageResponseDTO<UnpfShopAuthRespDTO> results = unpfShopAuthRSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
        if (results == null || results.getResult().get(0) == null) {
            LogUtil.error(this.getClass().getName(), "没有授权信息");
            return null;
        }
        return results.getResult().get(0);
    }
}

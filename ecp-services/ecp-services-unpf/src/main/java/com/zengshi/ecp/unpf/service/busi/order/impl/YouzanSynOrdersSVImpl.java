package com.zengshi.ecp.unpf.service.busi.order.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.YouzanOrderReq;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrders;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrdersResp;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IYouzanOrderRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSub;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuth;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RYouzanOrderReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.YouzanPushOrdersEntityReq;
import com.zengshi.ecp.unpf.dubbo.util.UnpfOrdConstants;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfErpOrderSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IYouzanSynOrdersSV;
import com.zengshi.paas.lock.DistributeLock;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guojingman on 2017/2/23.
 */
public class YouzanSynOrdersSVImpl implements IYouzanSynOrdersSV {
    @Resource
    private IYouzanOrderRSV youzanOrderRSV;
    @Resource
    private YouzanSynOrderUtil orderUtil;
    @Autowired(required = false)
    private IUnpfErpOrderSV unpfErpOrderSV;

    @Override
    public void saveOrdersFromYouzan(RYouzanOrderReq orderReq) {
        List<UnpfShopAuth> authList = orderUtil.getShopAuthInfo();
        if (CollectionUtils.isNotEmpty(authList)) {
            YouzanOrderReq targetOrderReq = new YouzanOrderReq();
            ObjectCopyUtil.copyObjValue(orderReq, targetOrderReq, null, false);
            for (UnpfShopAuth auth : authList) {
                LogUtil.info("YouzanSynOrdersSVImpl", "shopId:" + auth.getShopId());
                targetOrderReq.setAppId(auth.getAppkey());
                targetOrderReq.setAppSecret(auth.getAppscret());
                YouzanOrdersResp ordersResp = youzanOrderRSV.fetchOrdersFromYouzan(targetOrderReq);
                int totalCount = ordersResp.getTotalCount();
                LogUtil.info("YouzanSynOrdersSVImpl", "获取到的有赞订单总数据量(shopId:"+auth.getShopId()+")：" + totalCount);
                if (totalCount > 0) {
                    saveOrderList(ordersResp, auth.getShopId());
                    if (totalCount > orderReq.getPageSize()) {
                        int count = totalCount / orderReq.getPageSize();
                        //总页数
                        int pageCount = totalCount % orderReq.getPageSize() > 0 ? (count + 1) : count;
                        LogUtil.info("YouzanSynOrdersSVImpl", "获取到的有赞订单数据总页数(shopId:"+auth.getShopId()+")：" + pageCount);
                        for (int i = 1; i < pageCount; i++) {
                            targetOrderReq.setPageNo(i + 1);
                            YouzanOrdersResp resp = youzanOrderRSV.fetchOrdersFromYouzan(targetOrderReq);
                            saveOrderList(resp, auth.getShopId());
                        }
                    }
                }
            }
        }
    }

    private void saveOrderList(YouzanOrdersResp ordersResp, long shopId) {
        if (ordersResp != null && CollectionUtils.isNotEmpty(ordersResp.getYouzanOrders())) {
            for (YouzanOrders youzanOrders : ordersResp.getYouzanOrders()) {
                youzanOrders.setShopId(shopId);
                synOrderMain(youzanOrders);
            }
        }
    }

    private void synOrderMain(YouzanOrders youzanOrders) {
        String outId = youzanOrders.getOuterId();
        //加分布式锁
        String lockKey = YouzanSynOrderUtil.PLAT_TYPE + outId;
        DistributeLock lock = getLock(lockKey);
        UnpfOrdMain ordMain = null;
        boolean isNew = false;
        try {
            ordMain = orderUtil.findOrderMainByOutId(outId);
            if (ordMain == null) {//保存操作
                isNew = true;
                ordMain = new UnpfOrdMain();
                String mainId = orderUtil.generatePrimaryId(ordMain);
                ObjectCopyUtil.copyObjValue(youzanOrders, ordMain, null, false);
                ordMain.setId(mainId);
                ordMain.setShopName(youzanOrders.getShopName());
                ordMain.setShopId(youzanOrders.getShopId());
                if(youzanOrders.getPayTime()!=null){
                	ordMain.setPayTime(youzanOrders.getPayTime());
                }
                orderUtil.saveOrderMain(ordMain);
                //保存商品表
                List<YouzanOrders.OrderGoods> orderGoodsList = youzanOrders.getOrderGoods();
                if (orderGoodsList != null && orderGoodsList.size() > 0) {
                    UnpfOrdSub unpfOrdSub = new UnpfOrdSub();
                    for (YouzanOrders.OrderGoods goods : orderGoodsList) {
                        ObjectCopyUtil.copyObjValue(goods, unpfOrdSub, null, false);
                        if(StringUtils.isNotEmpty(goods.getDiscountFee())){
                 		   Long discountMoney = new Double(Double.parseDouble(goods.getDiscountFee())*100).longValue();
                 		   unpfOrdSub.setDiscountMoney(discountMoney);
                 	    }
                 	    if(StringUtils.isNotEmpty(goods.getRealMoney())){
                 		   Long realMoney = new Double(Double.parseDouble(goods.getRealMoney())*100).longValue();
                 		   unpfOrdSub.setRealMoney(realMoney);
                 	    }
                 	    if(StringUtils.isNotEmpty(goods.getOrderMoney())){
                 		   Long orderMoney = new Double(Double.parseDouble(goods.getOrderMoney())*100).longValue();
                 		   unpfOrdSub.setOrderMoney(orderMoney);
                 	    }
                        orderUtil.saveOrderGoods(ordMain, unpfOrdSub);
                    }
                }
                //更新订单是否系统商品
                orderUtil.updateOrderMainSysFlag(mainId);
            }
        } catch (Exception e) {
            LogUtil.error("YouzanSynOrdersSVImpl", "同步保存有赞订单数据失败", e);
            throw new BusinessException("同步保存有赞订单数据失败");
        } finally {
            lock.clearLock();
        }
        String youzanStatus = orderUtil.mappingOrderStatus(youzanOrders.getStatus());
        LogUtil.info("YouzanSynOrdersSVImpl","原订单状态+"+outId+"，status="+ordMain.getStatus());
        LogUtil.info("YouzanSynOrdersSVImpl","从有赞获取状态，status="+youzanStatus);
        //有赞待发货状态 或 有赞同步过来的数据状态与统一平台数据状态不一致时进行更新操作
        if(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(youzanStatus) || !ordMain.getStatus().equals(youzanStatus)){
            orderUtil.updateOrderAndOrderGoodsStatus(youzanOrders);
        }

        //已支付状态，同步到erp
        if (UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(youzanStatus)) {
            List<RUnpfOrdSubReq> subOrders = new ArrayList<>();
            List<YouzanOrders.OrderGoods> orderGoodsList = youzanOrders.getOrderGoods();
            if (CollectionUtils.isNotEmpty(orderGoodsList)) {
                for (YouzanOrders.OrderGoods goods : orderGoodsList) {
                    RUnpfOrdSubReq unpfOrdSub = new RUnpfOrdSubReq();
                    String gdsId = orderUtil.mappingUnpfGdsIdFromYouzanNumIid(goods.getGdsId());
                    if (gdsId != null) {
                        goods.setGdsId(gdsId);
                    }
                    ObjectCopyUtil.copyObjValue(goods, unpfOrdSub, null, false);
                    subOrders.add(unpfOrdSub);
                }
                if(isNew) {
                    ordMain = orderUtil.findOrderMainByOutId(outId);
                }
                ordMain.setStatus(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID);
                unpfErpOrderSV.saveErpOrder(ordMain, subOrders, youzanOrders.getBuyerNick());
            }
        }
    }

    @Override
    public void saveOrUpdateOrdersFromYouzanPush(YouzanPushOrdersEntityReq orderReq) {
        //加分布式锁
        String lockKey = YouzanSynOrderUtil.PLAT_TYPE + orderReq.getOuterId();
        DistributeLock lock = getLock(lockKey);
        UnpfOrdMain ordMain = null;
        boolean isNew = false;
        try {
            ordMain = orderUtil.findOrderMainByOutId(orderReq.getOuterId());
            if (ordMain == null) {
                isNew = true;
                ordMain = new UnpfOrdMain();
                String mainId = orderUtil.generatePrimaryId(ordMain);
                ObjectCopyUtil.copyObjValue(orderReq, ordMain, null, false);
                ordMain.setId(mainId);
                ordMain.setShopId(orderReq.getShopId());
                if(orderReq.getPayTime()!=null){
                	ordMain.setPayTime(orderReq.getPayTime());
                }
                LogUtil.info("youzanSynOrdersSVImpl", "有赞推送过来的数据状态：" + ordMain.getStatus());
                orderUtil.saveOrderMain(ordMain);
                List<YouzanPushOrdersEntityReq.PushOrdersGoods> ordSubReqList = orderReq.getOrderGoods();
                if (ordSubReqList != null && ordSubReqList.size() > 0) {
                    UnpfOrdSub unpfOrdSub = new UnpfOrdSub();
                    for (YouzanPushOrdersEntityReq.PushOrdersGoods ordersGoods : ordSubReqList) {
                        ObjectCopyUtil.copyObjValue(ordersGoods, unpfOrdSub, null, false);
                        if(StringUtils.isNotEmpty(ordersGoods.getDiscountFee())){
                  		   Long discountMoney = new Double(Double.parseDouble(ordersGoods.getDiscountFee())*100).longValue();
                  		   unpfOrdSub.setDiscountMoney(discountMoney);
                  	    }
                  	    if(StringUtils.isNotEmpty(ordersGoods.getRealMoney())){
                  		   Long realMoney = new Double(Double.parseDouble(ordersGoods.getRealMoney())*100).longValue();
                  		   unpfOrdSub.setRealMoney(realMoney);
                  	    }
                  	    if(StringUtils.isNotEmpty(ordersGoods.getOrderMoney())){
                  		   Long orderMoney = new Double(Double.parseDouble(ordersGoods.getOrderMoney())*100).longValue();
                  		   unpfOrdSub.setOrderMoney(orderMoney);
                  	    }
                        orderUtil.saveOrderGoods(ordMain, unpfOrdSub);
                    }
                }
                //更新订单是否系统商品
                orderUtil.updateOrderMainSysFlag(mainId);
            }
        } catch (Exception e) {
            LogUtil.error("YouzanSynOrdersSVImpl.saveOrUpdateOrdersFromYouzanPush", "同步保存有赞订单数据失败", e);
            throw new BusinessException("同步保存有赞订单数据失败");
        } finally {
            lock.clearLock();
        }
        //有赞同步过来的数据状态与统一平台数据状态不一致时进行更新操作
        String youzanStatus = orderUtil.mappingOrderStatus(orderReq.getStatus());
        LogUtil.info("youzanSynOrdersSVImpl", "有赞推送过来的数据状态：" + orderReq.getStatus());
        LogUtil.info("youzanSynOrdersSVImpl", "有赞推送过来的数据状态对应统一平台状态：" + youzanStatus);
        //有赞推送顺序有可能是先推送已支付，再推送未支付，需要进行状态判断，如果当前状态是已支付，推送过来的是未支付，则不进行更新操作
        if (UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(ordMain.getStatus())
                && UnpfOrdConstants.OrderStatus.ORDER_STATUS_SUBMIT.equals(youzanStatus)) {
            return;
        }

        //有赞待发货状态 或 有赞同步过来的数据状态与统一平台数据状态不一致时进行更新操作
        if(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(youzanStatus) || !ordMain.getStatus().equals(youzanStatus)){
            orderUtil.updateOrderAndOrderGoodsStatusFromPush(orderReq);
        }
        //已支付状态，同步保存到erp
        if (UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(youzanStatus)) {
            List<RUnpfOrdSubReq> subOrders = new ArrayList<>();
            List<YouzanPushOrdersEntityReq.PushOrdersGoods> ordSubReqList = orderReq.getOrderGoods();
            if (CollectionUtils.isNotEmpty(ordSubReqList)) {
                for (YouzanPushOrdersEntityReq.PushOrdersGoods ordersGoods : ordSubReqList) {
                    RUnpfOrdSubReq unpfOrdSub = new RUnpfOrdSubReq();
                    String gdsId = orderUtil.mappingUnpfGdsIdFromYouzanNumIid(ordersGoods.getGdsId());
                    ordersGoods.setGdsId(gdsId == null ? ordersGoods.getGdsId() : gdsId);
                    ObjectCopyUtil.copyObjValue(ordersGoods, unpfOrdSub, null, false);
                    subOrders.add(unpfOrdSub);
                }
                if(isNew){
                    ordMain = orderUtil.findOrderMainByOutId(orderReq.getOuterId());
                }
                ordMain.setStatus(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID);
                LogUtil.info("YouzanSynOrdersSVImpl from youzan push","有赞订单数据同步保存到erp，子订单数量："+subOrders.size());
                unpfErpOrderSV.saveErpOrder(ordMain, subOrders, orderReq.getStaffCode());
            }
        }
    }

    /**
     * 获取分布式锁
     *
     * @param key
     * @return
     */
    private DistributeLock getLock(String key) {
        DistributeLock lock = null;
        try {
            int tryTimes = 0;
            while ((lock = DistributeLock.lock(key, 10)) == null) {
                if (++tryTimes > 30) {
                    LogUtil.error("YouzanSynOrderSVImpl", "尝试获取锁超出限制次数（30次），lockKey=" + key);
                    throw new BusinessException("尝试获取锁次数超时");
                }
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            LogUtil.error("YouzanSynOrderSVImpl", "等待获取锁异常，lockKey=" + key, e);
            throw new BusinessException("等待获取锁异常，lockKey=" + key);
        }
        return lock;
    }
}

package com.zengshi.ecp.unpf.service.busi.order.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.resp.YouzanOrders;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdMainMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdSubMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfShopAuthMapper;
import com.zengshi.ecp.unpf.dao.model.*;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.YouzanPushOrdersEntityReq;
import com.zengshi.ecp.unpf.dubbo.util.UnpfConstants;
import com.zengshi.ecp.unpf.dubbo.util.UnpfOrdConstants;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGoodSendSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by guojingman on 2017/2/23.
 */
@Component
public class YouzanSynOrderUtil {

    @Resource(name = "seq_unpf_ord_main")
    private Sequence seqUnpfOrdMain;
    @Resource(name = "seq_unpf_ord_sub")
    private Sequence seqUnpfOrdSub;
    @Resource
    private UnpfOrdMainMapper unpfOrdMainMapper;
    @Resource
    private UnpfOrdSubMapper unpfOrdSubMapper;
    public static final String PLAT_TYPE = "youzan";
    @Resource
    private UnpfShopAuthMapper unpfShopAuthMapper;
    @Resource
    private IUnpfGoodSendSV unpfGoodSendSV;


    /**
     * 保存订单
     *
     * @param ordMain 包括主键id
     */
    public void saveOrderMain(UnpfOrdMain ordMain) {
        Timestamp nowTime = DateUtil.getSysDate();
        ordMain.setCreateTime(nowTime);
        ordMain.setImportTime(nowTime);
        ordMain.setUpdateTime(nowTime);
        ordMain.setStatus(mappingOrderStatus(ordMain.getStatus()));
        ordMain.setDispatchType(mappingShipingType(ordMain.getDispatchType()));
        //FIXME paramid 不知道做什么用，又不能为空，先固定设值
        ordMain.setParamid("1");
        ordMain.setCreateStaff(1000L);
        unpfOrdMainMapper.insert(ordMain);
    }

    /**
     * 保存订单物品数据
     *
     * @param ordMain
     * @param unpfOrdSub
     */
    public void saveOrderGoods(UnpfOrdMain ordMain, UnpfOrdSub unpfOrdSub) {
        unpfOrdSub.setOrderId(ordMain.getId());
        String subId = generatePrimaryId(unpfOrdSub);
        unpfOrdSub.setId(subId);
        unpfOrdSub.setStatus(ordMain.getStatus());
        Timestamp nowTime = DateUtil.getSysDate();
        unpfOrdSub.setCreateTime(nowTime);
        unpfOrdSub.setUpdateTime(nowTime);
        unpfOrdSub.setOrderTime(ordMain.getOrderTime());
        String gdsId = mappingUnpfGdsIdFromYouzanNumIid(unpfOrdSub.getGdsId());
        unpfOrdSub.setSysFlag(gdsId == null ? UnpfOrdConstants.IsSysFlag.NO_SYSORDER : UnpfOrdConstants.IsSysFlag.SYSORDER);
        if (gdsId != null) {
            unpfOrdSub.setGdsId(gdsId);
        }
        unpfOrdSubMapper.insert(unpfOrdSub);
    }


    /**
     * 更新订单中的sysFlag
     *
     * @param orderMainId
     */
    public void updateOrderMainSysFlag(String orderMainId) {
        UnpfOrdMain ordMain = new UnpfOrdMain();
        ordMain.setId(orderMainId);
        UnpfOrdSubCriteria criteria = new UnpfOrdSubCriteria();
        criteria.createCriteria().andOrderIdEqualTo(orderMainId).andPlatTypeEqualTo(PLAT_TYPE);
        List<UnpfOrdSub> subOrdList = unpfOrdSubMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(subOrdList)) {
            int sysGoodsCount = 0;
            for (UnpfOrdSub sub : subOrdList) {
                if (UnpfOrdConstants.IsSysFlag.SYSORDER.equals(sub.getSysFlag())) {
                    sysGoodsCount++;
                }
            }
            if (sysGoodsCount == 0) {
                ordMain.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
            } else if (sysGoodsCount < subOrdList.size()) {
                ordMain.setSysFlag(UnpfOrdConstants.IsSysFlag.PART_SYSORDER);
            } else {
                ordMain.setSysFlag(UnpfOrdConstants.IsSysFlag.SYSORDER);
            }
            ordMain.setUpdateTime(DateUtil.getSysDate());
            unpfOrdMainMapper.updateByPrimaryKeySelective(ordMain);
        }
    }

    /**
     * 根据有赞订单id（outId）获取统一平台对应数据，
     * 用于与同步过来的数据进行比对，如果状态不一致，说明要更新，否则忽略
     *
     * @param outId
     * @return 返回null说明统一平台不存在此数据，需要进行保存
     */
    public UnpfOrdMain findOrderMainByOutId(String outId) {
        UnpfOrdMainCriteria criteria = new UnpfOrdMainCriteria();
        criteria.createCriteria().andOuterIdEqualTo(outId).andPlatTypeEqualTo(PLAT_TYPE);
        List<UnpfOrdMain> list = unpfOrdMainMapper.selectByExample(criteria);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 生成订单数据主键
     *
     * @param obj
     * @return
     */
    public String generatePrimaryId(Object obj) {
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        String id = "";
        if (obj instanceof RUnpfOrdMainReq || obj instanceof UnpfOrdMain) {
            id = seqUnpfOrdMain.nextValue().toString();
        } else if (obj instanceof RUnpfOrdSubReq || obj instanceof UnpfOrdSub) {
            id = seqUnpfOrdSub.nextValue().toString();
        } else {
            throw new IllegalArgumentException("参数错误");
        }

        return "UN" + nowDate + StringUtil.lPad(id, "0", 8);
    }

    /**
     * 获取授权信息
     *
     * @return
     */
    public List<UnpfShopAuth> getShopAuthInfo() {
        UnpfShopAuthCriteria criteria = new UnpfShopAuthCriteria();
        criteria.createCriteria().andPlatTypeEqualTo(PLAT_TYPE).andStatusEqualTo(UnpfConstants.PlatType4Shop.STATUS_1);
        List<UnpfShopAuth> list = unpfShopAuthMapper.selectByExample(criteria);
        if (CollectionUtils.isEmpty(list)) {
            LogUtil.error(this.getClass().getName(), "有赞没有授权信息");
            return Collections.emptyList();
        }
        return list;
    }

    /**
     * 有赞订单状态映射成统一平台订单状态
     *
     * @param sourceStatus
     * @return
     */
    public String mappingOrderStatus(String sourceStatus) {
        if (StringUtil.isBlank(sourceStatus)) {
            return UnpfOrdConstants.OrderStatus.ORDER_STATUS_OTHER;
        }

        switch (sourceStatus) {
            case UnpfOrdConstants.YouZanOrderStatus.WAIT_BUYER_PAY: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_SUBMIT;
            }
            case UnpfOrdConstants.YouZanOrderStatus.WAIT_SELLER_SEND_GOODS: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID;
            }
            case UnpfOrdConstants.YouZanOrderStatus.WAIT_BUYER_CONFIRM_GOODS: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL;
            }
            case UnpfOrdConstants.YouZanOrderStatus.TRADE_BUYER_SIGNED: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_RECEPT;
            }
            case UnpfOrdConstants.YouZanOrderStatus.TRADE_CLOSED: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_CLOSE;
            }
            case UnpfOrdConstants.YouZanOrderStatus.TRADE_CLOSED_BY_USER: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_CANCLE;
            }
            case UnpfOrdConstants.YouZanOrderStatus.ALL_WAIT_PAY: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_SUBMIT;
            }
            case UnpfOrdConstants.YouZanOrderStatus.TRADE_NO_CREATE_PAY: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_SUBMIT;
            }
            case UnpfOrdConstants.YouZanOrderStatus.WAIT_PAY_RETURN: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID;
            }
            default: {
                return UnpfOrdConstants.OrderStatus.ORDER_STATUS_OTHER;
            }
        }
    }

    /**
     * 有赞配送方式映射到统一平台配送方式标识
     *
     * @param shipingType 取值范围：express（快递），fetch（到店自提），local（同城配送）
     * @return 0 平邮，1快递，2自提
     * FIXME 这里要设置成枚举或常量
     */
    private String mappingShipingType(String shipingType) {
        if ("express".equals(shipingType) || "local".equals(shipingType)) {
            return "1";
        } else if ("fetch".equals(shipingType)) {
            return "2";
        } else {
            return "0";
        }
    }

    /**
     * 把有赞的商品编号（num_iid）映射为统一平台商品id（gds_id）
     *
     * @param numIid
     * @return null 说明不是统一平台商品，直接使用有赞商品id
     */
    public String mappingUnpfGdsIdFromYouzanNumIid(String numIid) {
        UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
        unpfGdsSendReqDTO.setPlatType(YouzanSynOrderUtil.PLAT_TYPE);
        unpfGdsSendReqDTO.setOutGdsId(numIid);
        List<UnpfGdsSend> unpfGdsSendList = unpfGoodSendSV.queryGdsSendByOuterId(unpfGdsSendReqDTO);
        return CollectionUtils.isEmpty(unpfGdsSendList) ? null : String.valueOf(unpfGdsSendList.get(0).getGdsId());
    }

    /**
     * 更新订单状态及订单商品状态
     *
     * @param youzanOrders
     */
    public void updateOrderAndOrderGoodsStatus(YouzanOrders youzanOrders) {
        String youzanStatus = mappingOrderStatus(youzanOrders.getStatus());
        LogUtil.info("YouzanSynOrderUtil", "开始更新订单状态（fetch）,native status=" + youzanOrders.getStatus() + " mapper=" + youzanStatus);
        //未发货商品数
        int unSendCount = 0;
        List<YouzanOrders.OrderGoods> goodsList = youzanOrders.getOrderGoods();
        for (YouzanOrders.OrderGoods goods : goodsList) {
            //isSend 1表示已发货，0未发货
            if (goods.getIsSend() == 0) {
                unSendCount++;
                updateOrdSubStatusByOuterSubId(youzanOrders.getOuterId(), goods.getOuterSubId(), UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID);
            } else {
                updateOrdSubStatusByOuterSubId(youzanOrders.getOuterId(), goods.getOuterSubId(), UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
            }
        }
        LogUtil.info("YouzanSynOrderUtil", "[" + youzanOrders.getOuterId() + "]未发货商品数量(fetch)：" + unSendCount);
        updateOrdMainStatus(youzanOrders.getOuterId(), unSendCount, goodsList.size(), youzanStatus,youzanOrders.getPayTime());
    }

    private void updateOrdSubStatusByOuterSubId(String outerId, String outerSubId, String targetStatus) {
        UnpfOrdSubCriteria subCriteria = new UnpfOrdSubCriteria();
        subCriteria.createCriteria().andOuterSubIdEqualTo(outerSubId).andOuterIdEqualTo(outerId).andPlatTypeEqualTo(PLAT_TYPE);
        List<UnpfOrdSub> subList = unpfOrdSubMapper.selectByExample(subCriteria);
        if (CollectionUtils.isNotEmpty(subList)) {
            for (UnpfOrdSub sub : subList) {
                sub.setId(sub.getId());
                sub.setStatus(targetStatus);
                unpfOrdSubMapper.updateByPrimaryKeySelective(sub);
            }
        }
    }

    /**
     * 根据outerId更新订单状态
     *
     * @param outerId
     * @param targetStatus
     */
    public void updateOrdMainStatusByOuterId(String outerId, String targetStatus,Timestamp payTime) {
        UnpfOrdMainCriteria criteria = new UnpfOrdMainCriteria();
        criteria.createCriteria().andOuterIdEqualTo(outerId).andPlatTypeEqualTo(PLAT_TYPE);
        List<UnpfOrdMain> list = unpfOrdMainMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(list)) {
            UnpfOrdMain ordMain = list.get(0);
            UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
            unpfOrdMain.setId(ordMain.getId());
            unpfOrdMain.setStatus(targetStatus);
            if (UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(targetStatus)&&payTime!=null) {
                unpfOrdMain.setPayTime(payTime);
            } else if (UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL.equals(targetStatus)
                    || UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION.equals(targetStatus)) {
                unpfOrdMain.setDispatchTime(DateUtil.getSysDate());
            }
            unpfOrdMain.setUpdateTime(DateUtil.getSysDate());
            unpfOrdMainMapper.updateByPrimaryKeySelective(unpfOrdMain);
        }
    }

    public void updateOrdSubStatusByOuterId(String outerId, String targetStatus) {
        UnpfOrdSubCriteria subCriteria = new UnpfOrdSubCriteria();
        subCriteria.createCriteria().andOuterIdEqualTo(outerId).andPlatTypeEqualTo(PLAT_TYPE);
        List<UnpfOrdSub> subList = unpfOrdSubMapper.selectByExample(subCriteria);
        if (CollectionUtils.isNotEmpty(subList)) {
            for (UnpfOrdSub sub : subList) {
                sub.setId(sub.getId());
                sub.setStatus(targetStatus);
                sub.setUpdateTime(DateUtil.getSysDate());
                unpfOrdSubMapper.updateByPrimaryKeySelective(sub);
            }
        }
    }

    public void updateOrderAndOrderGoodsStatusFromPush(YouzanPushOrdersEntityReq orderReq) {
        String youzanStatus = mappingOrderStatus(orderReq.getStatus());
        LogUtil.info("YouzanSynOrderUtil", "开始更新订单状态（push）,native status=" + orderReq.getStatus() + " mapper=" + youzanStatus);
        //未发货商品数
        int unSendCount = 0;
        List<YouzanPushOrdersEntityReq.PushOrdersGoods> goodsList = orderReq.getOrderGoods();
        for (YouzanPushOrdersEntityReq.PushOrdersGoods goods : goodsList) {
            //isSend 1表示已发货，0未发货
            if (goods.getIsSend() == 0) {
                unSendCount++;
                updateOrdSubStatusByOuterSubId(orderReq.getOuterId(), goods.getOuterSubId(), UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID);
            } else {
                updateOrdSubStatusByOuterSubId(orderReq.getOuterId(), goods.getOuterSubId(), UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
            }
        }
        LogUtil.info("YouzanSynOrderUtil", "[" + orderReq.getOuterId() + "]未发货商品数量(push)：" + unSendCount);
        updateOrdMainStatus(orderReq.getOuterId(), unSendCount, goodsList.size(), youzanStatus,orderReq.getPayTime());
    }

    private void updateOrdMainStatus(String outerId, int unSendCount, int goodsNum, String youzanStatus,Timestamp payTime) {
        //有赞"待发货"状态，统一平台存在"部分发货"状态，需要额外做判断
        if (UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID.equals(youzanStatus)) {
            if (unSendCount == 0) {
                //全部"已发货"状态
                updateOrdMainStatusByOuterId(outerId, UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL,payTime);
                return;
            }
            if (unSendCount == goodsNum) {
                //全部"未发货"
                updateOrdMainStatusByOuterId(outerId, UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID,payTime);
                return;
            }
            //部分发货
            if (unSendCount < goodsNum) {
                updateOrdMainStatusByOuterId(outerId, UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION,payTime);
            }
        } else {
            updateOrdMainStatusByOuterId(outerId, youzanStatus,payTime);
            updateOrdSubStatusByOuterId(outerId, youzanStatus);
        }
    }
}

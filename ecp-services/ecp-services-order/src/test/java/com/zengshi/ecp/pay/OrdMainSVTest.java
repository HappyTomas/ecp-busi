package com.zengshi.ecp.pay;

import com.zengshi.ecp.general.order.dto.CoupCheckBeanRespDTO;
import com.zengshi.ecp.general.order.dto.CoupDetailRespDTO;
import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdOfflinePaySV;
import com.zengshi.ecp.order.service.busi.interfaces.*;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.db.sequence.Sequence;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.stringtemplate.v4.ST;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: cbl
 * @date: 2016/10/25.
 */
public class OrdMainSVTest extends EcpServicesTest{

    private static final String MODULE = OrdMainSVTest.class.getName();

    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private IOrdSubSV ordSubSV;

    @Resource
    private IOrdInvoiceCommSV ordInvoiceCommSV;

    @Resource
    private IOrdInvoiceTaxSV ordInvoiceTaxSV;

    @Resource
    private IOrdGiftSV ordGiftSV;

    @Resource
    private IOrdDiscountSV ordDiscountSV;

    @Resource
    private IOrdPromSV ordPromSV;

    @Resource
    private IOrdPresentSV ordPresentSV;

    @Resource
    private IOrdOfflineSV ordOfflineSV;

    @Resource
    private IOrdOfflinePaySV ordOfflinePaySV;

    @Resource
    private IOrdTrackSV ordTrackSV;

    @Resource(name = "seq_ord_main")
    private Sequence seqOrdMain;

    @Resource(name = "seq_ord_sub")
    private Sequence seqOrdSub;

    public String createOrdMainId() {
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        return OrdConstants.Common.RW_CODE + nowDate
                + StringUtil.lPad(seqOrdMain.nextValue().toString(), "0", 8);

    }

    public Long sumOrderScore(RSumbitMainRequest rSumbitMainRequest) {
        Long score = 0l;
        for (RSumbitSubRequest rSumbitSubRequest : rSumbitMainRequest.getPreOrdSubList()) {
            score += rSumbitSubRequest.getOrderScore();
        }
        return score;
    }

    private ROrdMainResponse saveOrdMainInfo(RSumbitMainRequest rSumbitMainRequest,RSumbitMainsRequest info) {

        OrdMain ordMain = new OrdMain();
        // String ordMainId = seqOrdMain.nextValue().toString();
        ObjectCopyUtil.copyObjValue(rSumbitMainRequest, ordMain, null, false);
        ordMain.setOrderTime(DateUtil.getSysDate());
        ordMain.setOrderCode(rSumbitMainRequest.getOrderId());
        ordMain.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);
        ordMain.setSource(info.getSource());
        ordMain.setOrderType(OrdConstants.Order.ORDER_TYPE_01);
        ordMain.setPayFlag(OrdConstants.Order.ORDER_PAY_FLAG_0);
        ordMain.setDispatchType(rSumbitMainRequest.getDeliverType());
        ordMain.setSiteId(rSumbitMainRequest.getCurrentSiteId());
        ordMain.setSysType(OrdConstants.SysType.SYS_TYPE_BASE);
        ordMain.setInvoiceType(StringUtil.isBlank(ordMain.getInvoiceType()) ? "2" :ordMain.getInvoiceType());
        ordMain.setIsInAuditFile(OrdConstants.PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_0);
        // 订单总积分
        ordMain.setOrderScore(sumOrderScore(rSumbitMainRequest));

        ordMain.setBuyerMsg(rSumbitMainRequest.getBuyerMsg());

        ordMain.setBillingFlag(OrdConstants.Common.COMMON_INVALID);

        ROrdDeliveAddrRequest rOrdDeliveAddrRequest = info.getrOrdDeliveAddrRequest();
        ObjectCopyUtil.copyObjValue(rOrdDeliveAddrRequest, ordMain , null, false);
        ordMain.setId(rSumbitMainRequest.getOrderId());
        ordMain.setCreateTime(DateUtil.getSysDate());
        ordMain.setCreateStaff(rSumbitMainRequest.getStaff().getId());
        ordMain.setUpdateStaff(rSumbitMainRequest.getStaff().getId());
        ordMain.setUpdateTime(DateUtil.getSysDate());
        LogUtil.info(MODULE, "保存订单主表" + JSON.toJSONString(ordMain));
        ordMainSV.saveOrdMain(ordMain);
        ROrdMainResponse rOrdMainResponse=new ROrdMainResponse();
        ObjectCopyUtil.copyObjValue(ordMain, rOrdMainResponse, null, false);

        return rOrdMainResponse;
    }

    private void saveOrdInvoiceInfo(String ordMainId, RSumbitMainRequest rSumbitMainRequest) {
        String invoiceType = rSumbitMainRequest.getInvoiceType();
        if ("0".equals(invoiceType)) {
            OrdInvoiceComm ordInvoiceComm = new OrdInvoiceComm();
            ROrdInvoiceCommRequest rOrdInvoiceCommRequest = rSumbitMainRequest
                    .getrOrdInvoiceCommRequest();
            if (rOrdInvoiceCommRequest != null) {
                ObjectCopyUtil.copyObjValue(rOrdInvoiceCommRequest, ordInvoiceComm, null, false);
                ordInvoiceComm.setOrderId(ordMainId);
                ordInvoiceComm.setCreateStaff(rSumbitMainRequest.getStaff().getId());
                ordInvoiceComm.setCreateTime(DateUtil.getSysDate());
                ordInvoiceComm.setUpdateStaff(rSumbitMainRequest.getStaff().getId());
                ordInvoiceComm.setUpdateTime(DateUtil.getSysDate());
                ordInvoiceCommSV.saveOrdInvoiceComm(ordInvoiceComm);
            }
        } else if("1".equals(invoiceType)){
            OrdInvoiceTax ordInvoiceTax = new OrdInvoiceTax();
            ROrdInvoiceTaxRequest rOrdInvoiceTaxRequest = rSumbitMainRequest
                    .getrOrdInvoiceTaxRequest();
            if (rOrdInvoiceTaxRequest != null) {
                ObjectCopyUtil.copyObjValue(rOrdInvoiceTaxRequest, ordInvoiceTax, null, false);
                ordInvoiceTax.setOrderId(ordMainId);
                ordInvoiceTax.setShopId(rSumbitMainRequest.getShopId());
                ordInvoiceTax.setCreateStaff(rSumbitMainRequest.getStaffId());
                ordInvoiceTax.setCreateTime(DateUtil.getSysDate());
                ordInvoiceTax.setUpdateStaff(rSumbitMainRequest.getStaffId());
                ordInvoiceTax.setUpdateTime(DateUtil.getSysDate());
                ordInvoiceTaxSV.saveOrdInvoiceTax(ordInvoiceTax);
            }
        }else{

        }
    }

    /**
     *
     * saveOrdTrack:保存订单跟踪表. <br/>
     *
     * @author linwei
     * @param ordMainId
     * @param sumbitMainRequest
     * @since JDK 1.6
     */
    private void saveOrdTrack(String ordMainId, RSumbitMainRequest sumbitMainRequest) {
        OrdTrack ordTrack = new OrdTrack();
        ordTrack.setOrderId(ordMainId);
        ordTrack.setNode(OrdConstants.OrderTwoStatus.STATUS_SUBMIT);
        ordTrack.setNodeDesc(OrdConstants.NodeDesc.STATUS_SUBMIT);
        ordTrack.setCreateStaff(sumbitMainRequest.getStaff().getId());
        ordTrack.setUpdateStaff(sumbitMainRequest.getStaff().getId());
        ordTrack.setCreateTime(DateUtil.getSysDate());
        ordTrack.setUpdateTime(DateUtil.getSysDate());
        ordTrackSV.saveOrdTrack(ordTrack);
    }

    /**
     *
     * saveOrdDiscount:保存订单优惠信息. <br/>
     *
     * @author linwei
     * @param rSumbitMainsRequest
     * @since JDK 1.6
     */
    private void saveOrdDiscount(RSumbitMainsRequest rSumbitMainsRequest,RSumbitMainRequest rs) {

        //优惠券
        if(rs.getCoupCheckBean()!=null && rs.getCoupCheckBean().size()>0){
            List<CoupCheckBeanRespDTO> coups = rs.getCoupCheckBean();
            //已优惠券小类做记录区分，一种优惠券记录一条记录
            for(CoupCheckBeanRespDTO coupCheckBean: coups){
                Long discountPrice = 0l;
                if(coupCheckBean.getCoupDetails()!=null && coupCheckBean.getCoupDetails().size()>0){
                    for(CoupDetailRespDTO coup : coupCheckBean.getCoupDetails()){
                        discountPrice += coup.getCoupValue();//不做判断，正确状态无异常？
                    }
                }
                OrdDiscount ordDiscount = new OrdDiscount();
                ordDiscount.setRemark(coups.get(0).getCoupId()+"");//由于没有设计优惠券字段，需要添加在remark字段当中
                ordDiscount.setOrderId(rs.getOrderId());
                ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_COUP_CODE);
                ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_COUP_NAME);
                ordDiscount.setDiscountPrice(discountPrice);
                ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
                ordDiscount.setCreateTime(DateUtil.getSysDate());
                ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
                ordDiscount.setUpdateTime(DateUtil.getSysDate());
                ordDiscountSV.saveOrdDiscount(ordDiscount);
            }
        }
        // 资金帐户
        if(CollectionUtils.isNotEmpty(rs.getOrdAcctInfoList())){
            for (AcctInfoResDTO ar : rs.getOrdAcctInfoList()) {
                if (ar.getDeductOrderMoney() > 0l) {
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_CAPITAL_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_CAPITAL_NAME);
                    ordDiscount.setDiscountPrice(ar.getDeductOrderMoney());
                    ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }
            }
        }

        // 积分帐户
        if(CollectionUtils.isNotEmpty(rs.getPreOrdSubList())){
            for (RSumbitSubRequest rSumbitSubRequest : rs.getPreOrdSubList()) {
                if (rSumbitSubRequest.getOrderScore() > 0l) {
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setOrderSubId(rSumbitSubRequest.getOrderSubId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_SCORE_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_SCORE_NAME);
                    ordDiscount.setUseScore(rSumbitSubRequest.getOrderScore());
                    ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }
            }
        }

        //优惠码
        if(StringUtils.isNotBlank(rs.getCoupCode())){
            OrdDiscount ordDiscount = new OrdDiscount();
            ordDiscount.setRemark("优惠码："+rs.getCoupCode()+"已优惠"+rs.getCoupCodeMoney());//由于没有设计优惠券字段，需要添加在remark字段当中
            ordDiscount.setOrderId(rs.getOrderId());
            ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_COUPCODE_CODE);
            ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_COUPCODE_NAME);
            ordDiscount.setDiscountPrice(rs.getCoupCodeMoney());
            ordDiscount.setCreateStaff(rSumbitMainsRequest.getStaff().getId());
            ordDiscount.setCreateTime(DateUtil.getSysDate());
            ordDiscount.setUpdateStaff(rSumbitMainsRequest.getStaff().getId());
            ordDiscount.setUpdateTime(DateUtil.getSysDate());
            ordDiscountSV.saveOrdDiscount(ordDiscount);
        }

    }

    /**
     *
     * saveOrdProm:保存订单促销信息. <br/>
     *
     * @author linwei
     * @since JDK 1.6
     */

    private void saveOrdProm(String ordMainId, RSumbitMainsRequest sumbitMainsRequest,RSumbitMainRequest rs) {

        // 保存T_ORD_PROM--订单活动表

        // 订单级别优惠
        Map<Long, CartPromDTO> ordMap = null;

        // 子订单级别优惠
        Map<Long, CartPromItemDTO> ordSubMap = null;

        // 订单级别赠送
        Map<Long, PromPresentDTO> ordPreMap = null;
        // 子订单级别赠送
        Map<Long, PromPresentDTO> ordSubPreMap = null;

        if(sumbitMainsRequest.getCartPromRespDTO() != null){
            ordMap = sumbitMainsRequest.getCartPromRespDTO().getCartPromDTOMap();
            ordSubMap = sumbitMainsRequest.getCartPromRespDTO().getCartPromItemDTOMap();
        }
        if(sumbitMainsRequest.getPromPresentRespDTO() != null){
            ordPreMap = sumbitMainsRequest.getPromPresentRespDTO().getCartPromDTOMap();
            ordSubPreMap = sumbitMainsRequest.getPromPresentRespDTO().getCartPromItemDTOMap();
        }

        // 如果该活动为赠品相关 则保存T_ORD_GIFT--订单赠品表
        // 如果该活动为优惠相关 刚保存T_ORD_DISCOUNT-订单优惠表
        // 如果该活动为积分或优惠券相关 则保存T_ORD_PRESENT--订单赠送积分和优惠券

        if (ordMap != null) {
            CartPromDTO cpd = ordMap.get(rs.getCartId());
            if (cpd != null && cpd.isIfFulfillProm()) {
                // 主订单活动
                if (cpd.getPromInfoDTO() != null) {
                    OrdProm ordProm = new OrdProm();
                    ordProm.setOrderId(rs.getOrderId());
                    ordProm.setPromId(cpd.getPromInfoDTO().getId());
                    ordProm.setPromTypeCode(cpd.getPromInfoDTO().getPromTypeCode());
                    ordProm.setPromTypeName(cpd.getPromInfoDTO().getPromTypeName());
                    ordProm.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setCreateTime(DateUtil.getSysDate());
                    ordProm.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setUpdateTime(DateUtil.getSysDate());
                    ordPromSV.saveOrdProm(ordProm);
                }
                // 主订单优惠价格等
                if (cpd.getDiscountMoney() != null && (cpd.getDiscountMoney().longValue()
                        + cpd.getDiscountPriceMoney().longValue()) != 0l) {
                    OrdDiscount ordDiscount = new OrdDiscount();
                    ordDiscount.setOrderId(rs.getOrderId());
                    ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_ORDER_CODE);
                    ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_ORDER_NAME);
                    ordDiscount.setDiscountPrice(cpd.getDiscountMoney().longValue()
                            + cpd.getDiscountPriceMoney().longValue());
                    ordDiscount.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                    ordDiscount.setCreateTime(DateUtil.getSysDate());
                    ordDiscount.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                    ordDiscount.setUpdateTime(DateUtil.getSysDate());
                    ordDiscountSV.saveOrdDiscount(ordDiscount);
                }

                if (ordPreMap != null) {
                    PromPresentDTO pp = ordPreMap.get(rs.getCartId());
                    if (pp != null) {
                        // 主订单赠送积分
                        if (pp.getPoints() != null && pp.getSendType() != null
                                && (pp.getPoints().doubleValue() > 0.0001
                                || pp.getPoints().doubleValue() < -0.0001)) {

                            OrdPresent ordPresent = new OrdPresent();
                            ordPresent.setOrderId(rs.getOrderId());
                            ordPresent.setPromId(pp.getPromId());
                            if (PromConstants.PromDiscountRule.SEND_POINTS_0
                                    .equals(pp.getSendType())) {
                                ordPresent.setCredits(pp.getPoints().longValue());
                            } else {
                                ordPresent.setCreditTimes(pp.getPoints());
                            }

                            ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setCreateTime(DateUtil.getSysDate());
                            ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setUpdateTime(DateUtil.getSysDate());
                            ordPresentSV.saveOrdPresent(ordPresent);
                        }
                        // 主订单赠送优惠券
                        if (pp.getSendAmount() != null && pp.getSendAmount().longValue() != 0l) {
                            OrdPresent ordPresent = new OrdPresent();
                            ordPresent.setOrderId(rs.getOrderId());
                            ordPresent.setPromId(pp.getPromId());
                            ordPresent.setCouponTypeId(pp.getCoupId());
                            ordPresent.setCouponCnt(pp.getSendAmount().longValue());
                            ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setCreateTime(DateUtil.getSysDate());
                            ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                            ordPresent.setUpdateTime(DateUtil.getSysDate());
                            ordPresentSV.saveOrdPresent(ordPresent);
                        }
                        // 主订单赠品
                        if (CollectionUtils.isNotEmpty(pp.getPromGiftDTOList())) {
                            for (PromGiftDTO pg : pp.getPromGiftDTOList()) {
                                OrdGift ordGift = new OrdGift();
                                ordGift.setOrderId(rs.getOrderId());
                                ordGift.setFromType(OrdConstants.GiftFromType.TYPE_PROM);
                                ordGift.setGiftId(pg.getGiftId());
                                ordGift.setGiftName(pg.getGiftName());
                                ordGift.setGiftCount(pg.getEveryTimeCnt());
                                ordGift.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                ordGift.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                ordGift.setCreateTime(DateUtil.getSysDate());
                                ordGift.setUpdateTime(DateUtil.getSysDate());
                                ordGiftSV.saveOrdGift(ordGift);
                            }
                        }
                    }

                }

            }
        }

        // 子订单 --start
        if (ordSubMap != null) {
            for (RSumbitSubRequest ssb : rs.getPreOrdSubList()) {
                CartPromItemDTO cpid = ordSubMap.get(ssb.getCartItemId());
                if (cpid != null && !cpid.isIfFulfillProm() && cpid.getOrdPromId() != null
                        && cpid.getOrdPromId() != 0l) {
                    OrdProm ordProm = new OrdProm();
                    ordProm.setOrderId(rs.getOrderId());
                    ordProm.setOrderSubId(ssb.getOrderSubId());
                    ordProm.setPromId(-2l);
                    ordProm.setPromTypeCode("-2");
                    ordProm.setPromTypeName("-2");
                    ordProm.setRelaMainPromId(cpid.getOrdPromId());
                    ordProm.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setCreateTime(DateUtil.getSysDate());
                    ordProm.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                    ordProm.setUpdateTime(DateUtil.getSysDate());
                    ordPromSV.saveOrdProm(ordProm);
                }
                if (cpid != null && cpid.isIfFulfillProm()) {
                    // 子订单活动
                    if (cpid.getPromInfoDTO() != null) {
                        OrdProm ordProm = new OrdProm();
                        ordProm.setOrderId(rs.getOrderId());
                        ordProm.setOrderSubId(ssb.getOrderSubId());
                        ordProm.setPromId(cpid.getPromInfoDTO().getId());
                        ordProm.setPromTypeCode(cpid.getPromInfoDTO().getPromTypeCode());
                        ordProm.setPromTypeName(cpid.getPromInfoDTO().getPromTypeName());
                        ordProm.setRelaMainPromId(cpid.getOrdPromId());
                        ordProm.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                        ordProm.setCreateTime(DateUtil.getSysDate());
                        ordProm.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                        ordProm.setUpdateTime(DateUtil.getSysDate());
                        ordPromSV.saveOrdProm(ordProm);
                    }
                    // 子订单优惠价格等
                    if (cpid.getDiscountMoney() != null
                            && (cpid.getDiscountMoney().longValue() != 0l
                            || cpid.getDiscountPriceMoney().longValue() != 0l)) {
                        OrdDiscount ordDiscount = new OrdDiscount();
                        ordDiscount.setOrderId(rs.getOrderId());
                        ordDiscount.setOrderSubId(ssb.getOrderSubId());
                        ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_ORDER_CODE);
                        ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_ORDER_NAME);
                        ordDiscount.setDiscountPrice(cpid.getDiscountMoney().longValue()
                                + cpid.getDiscountPriceMoney().longValue());
                        ordDiscount.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                        ordDiscount.setCreateTime(DateUtil.getSysDate());
                        ordDiscount.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                        ordDiscount.setUpdateTime(DateUtil.getSysDate());
                        ordDiscountSV.saveOrdDiscount(ordDiscount);
                    }

                    if (ordSubPreMap != null) {
                        PromPresentDTO pp = ordSubPreMap.get(ssb.getCartItemId());
                        if (pp != null) {
                            // 子订单赠送积分
                            if (pp.getPoints() != null && pp.getSendType() != null
                                    && (pp.getPoints().doubleValue() > 0.0001
                                    || pp.getPoints().doubleValue() < -0.0001)) {
                                OrdPresent ordPresent = new OrdPresent();
                                ordPresent.setOrderId(rs.getOrderId());
                                ordPresent.setSubOrder(ssb.getOrderSubId());
                                ordPresent.setPromId(pp.getPromId());
                                if (PromConstants.PromDiscountRule.SEND_POINTS_0
                                        .equals(pp.getSendType())) {
                                    ordPresent.setCredits(pp.getPoints().longValue());
                                } else {
                                    ordPresent.setCreditTimes(pp.getPoints());
                                }
                                ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setCreateTime(DateUtil.getSysDate());
                                ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setUpdateTime(DateUtil.getSysDate());
                                ordPresentSV.saveOrdPresent(ordPresent);
                            }
                            // 子订单赠送优惠券
                            if (pp.getSendAmount() != null
                                    && pp.getSendAmount().longValue() != 0l) {
                                OrdPresent ordPresent = new OrdPresent();
                                ordPresent.setOrderId(rs.getOrderId());
                                ordPresent.setSubOrder(ssb.getOrderSubId());
                                ordPresent.setPromId(pp.getPromId());
                                // ordPresent.setCouponTypeId(pp.getCoupId());
                                // ordPresent.setCouponCnt(pp.getSendAmount());
                                ordPresent.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setCreateTime(DateUtil.getSysDate());
                                ordPresent.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                ordPresent.setUpdateTime(DateUtil.getSysDate());
                                ordPresentSV.saveOrdPresent(ordPresent);
                            }
                            // 子订单赠品
                            if (CollectionUtils.isNotEmpty(pp.getPromGiftDTOList())) {
                                for (PromGiftDTO pg : pp.getPromGiftDTOList()) {
                                    OrdGift ordGift = new OrdGift();
                                    ordGift.setOrderId(rs.getOrderId());
                                    ordGift.setOrdSubId(ssb.getOrderSubId());
                                    ordGift.setFromType(OrdConstants.GiftFromType.TYPE_PROM);
                                    ordGift.setGiftId(pg.getGiftId());
                                    ordGift.setGiftName(pg.getGiftName());
                                    ordGift.setGiftCount(pg.getEveryTimeCnt());
                                    ordGift.setCreateStaff(sumbitMainsRequest.getStaff().getId());
                                    ordGift.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
                                    ordGift.setCreateTime(DateUtil.getSysDate());
                                    ordGift.setUpdateTime(DateUtil.getSysDate());
                                    ordGiftSV.saveOrdGift(ordGift);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 子订单 --end
        //保存商品域优惠金额（分类折扣优惠）
        for (RSumbitSubRequest ssb : rs.getPreOrdSubList()) {
            if (ordSubMap != null){
                CartPromItemDTO cpid = ordSubMap.get(ssb.getCartItemId());
                if (cpid != null && cpid.isIfFulfillProm()){
                    //有促销活动
                    continue;
                }
            }
            OrdDiscount ordDiscount = new OrdDiscount();
            ordDiscount.setOrderId(rs.getOrderId());
            ordDiscount.setOrderSubId(ssb.getOrderSubId());
            ordDiscount.setDiscountType(OrdConstants.DiscountType.TYPE_GDS_CODE);
            ordDiscount.setDiscountTitle(OrdConstants.DiscountType.TYPE_GDS_NAME);
            ordDiscount.setDiscountPrice((ssb.getBasePrice()-ssb.getBuyPrice())*ssb.getOrderAmount());
            ordDiscount.setCreateStaff(sumbitMainsRequest.getStaff().getId());
            ordDiscount.setCreateTime(DateUtil.getSysDate());
            ordDiscount.setUpdateStaff(sumbitMainsRequest.getStaff().getId());
            ordDiscount.setUpdateTime(DateUtil.getSysDate());
            ordDiscountSV.saveOrdDiscount(ordDiscount);
        }
    }
    private void saveOrdOffline(String ordMainId, RSumbitMainRequest sumbitMainRequest) {
        ROfflineApplyRequest rOfflineApplyRequest = new ROfflineApplyRequest();
        rOfflineApplyRequest.setStaffId(sumbitMainRequest.getStaffId());
        rOfflineApplyRequest.setShopId(sumbitMainRequest.getShopId());
        rOfflineApplyRequest.setOrderId(ordMainId);
        rOfflineApplyRequest.setApplyType(sumbitMainRequest.getPayType());
        rOfflineApplyRequest.getStaff().setId(sumbitMainRequest.getStaff().getId());
        ordOfflineSV.saveOfflineApply(rOfflineApplyRequest);
    }

    public String createOrdSubId(){
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        return OrdConstants.Common.SRW_CODE+nowDate+StringUtil.lPad(seqOrdSub.nextValue().toString(), "0", 8);
    }

    public void submitOrder() {

        String data = "{\"appName\":\"ai-ecp\",\"cartPromRespDTO\":{\"appName\":\"ai-ecp\",\"cartPromDTOMap\":{15244:{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"discountAmount\":0,\"discountCaclPrice\":0,\"discountFinalPrice\":0,\"discountMoney\":0,\"discountPrice\":0,\"discountPriceMoney\":0,\"endRowIndex\":1,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"promInfoDTO\":{\"appName\":\"ai-ecp\",\"createStaff\":178,\"createTime\":1470795092000,\"currentSiteId\":0,\"endRowIndex\":1,\"endTime\":1483098710000,\"id\":12039,\"ifBlacklist\":\"0\",\"ifComposit\":\"1\",\"ifFreePost\":\"0\",\"ifMatch\":\"0\",\"ifShow\":\"1\",\"joinRange\":\"1\",\"keyWord\":\"满减\",\"locale\":\"zh_CN\",\"nameShort\":\"满减\",\"pageNo\":1,\"pageSize\":1,\"priority\":1,\"promClass\":\"10\",\"promClassName\":\"订单\",\"promContent\":\"满减100减30\",\"promTheme\":\"满减100减30-人卫商城店铺\",\"promTypeCode\":\"1000000007\",\"promTypeName\":\"满减\",\"promTypeShow\":\"满减\",\"shopId\":100,\"showEndTime\":1483185120000,\"showStartTime\":1462708315000,\"siteId\":1,\"siteName\":\"商城-开发环境\",\"staff\":{\"id\":0},\"startRowIndex\":0,\"startTime\":1462794708000,\"status\":\"10\",\"statusName\":\"生效\",\"threadId\":\"\"},\"promInfoDTOList\":[{\"appName\":\"ai-ecp\",\"createStaff\":178,\"createTime\":1470795092000,\"currentSiteId\":0,\"endRowIndex\":1,\"endTime\":1483098710000,\"id\":12039,\"ifBlacklist\":\"0\",\"ifComposit\":\"1\",\"ifFreePost\":\"0\",\"ifMatch\":\"0\",\"ifShow\":\"1\",\"joinRange\":\"1\",\"keyWord\":\"满减\",\"locale\":\"zh_CN\",\"nameShort\":\"满减\",\"pageNo\":1,\"pageSize\":1,\"priority\":1,\"promClass\":\"10\",\"promClassName\":\"订单\",\"promContent\":\"满减100减30\",\"promTheme\":\"满减100减30-人卫商城店铺\n" +
                "\",\"promTypeCode\":\"1000000007\",\"promTypeName\":\"满减\",\"promTypeShow\":\"满减\",\"shopId\":100,\"showEndTime\":1483185120000,\"showStartTime\":1462708315000,\"siteId\":1,\"siteName\":\"商城-开发环境\",\"staff\":{\"id\":0},\"startRowIndex\":0,\"startTime\":1462794708000,\"status\":\"10\",\"statusName\":\"生效\",\"threadId\":\"\"},{\"appName\":\"ai-ecp\",\"createStaff\":178,\"createTime\":1464162185000,\"currentSiteId\":0,\"endRowIndex\":1,\"endTime\":1589442281000,\"id\":3870,\"ifBlacklist\":\"0\",\"ifComposit\":\"1\",\"ifFreePost\":\"0\",\"ifMatch\":\"0\",\"ifShow\":\"1\",\"joinRange\":\"0\",\"keyWord\":\"满送\",\"locale\":\"zh_CN\",\"nameShort\":\"满送\",\"pageNo\":1,\"pageSize\":1,\"priority\":1,\"promClass\":\"10\",\"promClassName\":\"订单\",\"promContent\":\"111\",\"promTheme\":\"满100送优惠券\",\"promTypeCode\":\"1000000002\",\"promTypeName\":\"满X送优惠券\",\"promTypeShow\":\"满送\",\"shopId\":100,\"showEndTime\":1589528687000,\"showStartTime\":1462088685000,\"siteId\":1,\"siteName\":\"商城-开发环境\",\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.promInfoDTOList[0].staff\"},\"startRowIndex\":0,\"startTime\":1462175078000,\"status\":\"10\",\"statusName\":\"生效\",\"threadId\":\"\"}],\"promTypeMsgResponseDTO\":{\"createStaff\":1,\"createTime\":1442937600000,\"fulfilMsg\":\"亲，订单小计达到%s时，可减掉%s。\",\"id\":10,\"noFulfilMsg\":\"亲，订单小计达到100.00时，可减掉30.00。\",\"position\":\"10\",\"positionName\":\"\",\"promTypeCode\":\"1000000007\",\"status\":\"1\",\"statusName\":\"\"},\"staff\":{\"id\":0},\"startRowIndex\":0,\"threadId\":\"\"},15243:{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"discountAmount\":0,\"discountCaclPrice\":0,\"discountFinalPrice\":0,\"discountMoney\":0,\"discountPrice\":0,\"discountPriceMoney\":0,\"endRowIndex\":1,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"promInfoDTO\":{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"endRowIndex\":1,\"id\":-1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"},\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"}},\"cartPromItemDTOMap\":{19439:{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"discountAmount\":0,\"discountCaclPrice\":8000,\"discountFinalPrice\":8000,\"discountMoney\":0,\"discountPrice\":0,\"discountPriceMoney\":0,\"endRowIndex\":1,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"promInfoDTO\":{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"endRowIndex\":1,\"id\":-1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"},\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"},19438:{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"discountAmount\":0,\"discountCaclPrice\":99,\"discountFinalPrice\":99,\"discountMoney\":0,\"discountPrice\":0,\"discountPriceMoney\":0,\"endRowIndex\":1,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"promInfoDTO\":{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"endRowIndex\":1,\"id\":-1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"},\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"},19440:{\"appName\":\"ai-ecp\",\"currentSiteId\":0,\"discountAmount\":0,\"discountCaclPrice\":99,\"discountFinalPrice\":69,\"discountMoney\":0,\"discountPrice\":0,\"discountPriceMoney\":0,\"endRowIndex\":1,\"ifFulfillProm\":false,\"locale\":\"zh_CN\",\"ordPromId\":12039,\"pageNo\":1,\"pageSize\":1,\"promInfoDTO\":{\"appName\":\"ai-ecp\",\"currentSiteId\":1,\"endRowIndex\":1,\"id\":-1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"startRowIndex\":0,\"threadId\":\"192.168.1.158:-8032688472782484335\"},\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromDTOMap.15244.staff\"},\"startRowIndex\":0,\"threadId\":\"\"}},\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"staff\":{\"$ref\":\"$.cartPromRespDTO.cartPromItemDTOMap.19440.promInfoDTO.staff\"},\"startRowIndex\":0,\"threadId\":\"192.168.1.158:-8032688472782484335\"},\"coupIdskuIdMap\":{},\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"payType\":\"3\",\"promPresentRespDTO\":{\"appName\":\"ai-ecp\",\"cartPromDTOMap\":{},\"cartPromItemDTOMap\":{},\"currentSiteId\":1,\"endRowIndex\":1,\"locale\":\"zh_CN\",\"pageNo\":1,\"pageSize\":1,\"staff\":{\"id\":182,\"staffClass\":\"20\",\"staffCode\":\"chengbl\",\"staffLevelCode\":\"04\"},\"startRowIndex\":0,\"threadId\":\"192.168.1.158:-7579113733062996415\"},\"rOrdDeliveAddrRequest\":{\"chnlAddress\":\"河北石家庄市市辖区ddddd\",\"cityCode\":\"130100\",\"contactName\":\"hhh\",\"contactPhone\":\"13222222222\",\"countyCode\":\"130101\",\"createStaff\":182,\"createTime\":1463538886000,\"id\":1625,\"postCode\":\"555555\",\"province\":\"130000\",\"updateStaff\":182,\"updateTime\":1475133167000},\"source\":\"1\",\"sourceKey\":\"\",\"staff\":{\"$ref\":\"$.promPresentRespDTO.staff\"},\"staffId\":182,\"startRowIndex\":0,\"sumbitMainList\":[{\"appName\":\"ai-ecp\",\"basicMoney\":99,\"cartId\":15244,\"coupCode\":\"\",\"currentSiteId\":1,\"deliverType\":\"2\",\"endRowIndex\":1,\"invoiceType\":\"2\",\"locale\":\"zh_CN\",\"ordAcctInfoList\":[],\"orderAmount\":1,\"orderId\":\"RW16102500023429\",\"orderMoney\":99,\"pageNo\":1,\"pageSize\":1,\"preOrdSubList\":[{\"appName\":\"ai-ecp\",\"basePrice\":99,\"buyPrice\":69,\"cartItemId\":19440,\"categoryCode\":\"2333\",\"currentSiteId\":1,\"endRowIndex\":1,\"gdsCateName\":\"纸质书-图书\",\"gdsId\":46284,\"gdsName\":\"自由搭配A-1\",\"gdsType\":1,\"groupDetail\":\"86672\",\"groupType\":\"0\",\"locale\":\"zh_CN\",\"orderAmount\":1,\"orderId\":\"RW16102500023429\",\"orderMoney\":69,\"orderScore\":0,\"orderSubId\":\"SRW16102500025425\",\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"repCode\":4090,\"score\":0,\"shopId\":100,\"shopName\":\"0\",\"skuId\":86672,\"skuInfo\":\"精品/普通\",\"staff\":{\"$ref\":\"$.promPresentRespDTO.staff\"},\"staffId\":182,\"startRowIndex\":0,\"stockId\":12408,\"threadId\":\"192.168.1.158:-7579113733062996415\"}],\"rOrdInvoiceCommRequest\":{\"detailFlag\":\"0\",\"invoiceContent\":\"\",\"invoiceTitle\":\"\",\"invoiceType\":\"2\"},\"rOrdInvoiceTaxRequest\":{\"acctInfo\":\"\",\"bankName\":\"\",\"contactInfo\":\"\",\"invoiceContent\":\"\",\"invoiceTitle\":\"\",\"phone\":\"\",\"takerAddress\":\"\",\"takerCity\":\"\",\"takerCounty\":\"\",\"takerName\":\"\",\"takerPhone\":\"\",\"takerProvince\":\"\",\"taxpayerNo\":\"\",\"vfsId2\":\"\"},\"realExpressFee\":0,\"realMoney\":69,\"shopId\":100,\"shopName\":\"人卫商城店铺100\",\"staff\":{\"$ref\":\"$.promPresentRespDTO.staff\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"192.168.1.158:-7579113733062996415\"},{\"appName\":\"ai-ecp\",\"basicMoney\":8099,\"cartId\":15243,\"coupCode\":\"\",\"currentSiteId\":1,\"deliverType\":\"2\",\"endRowIndex\":1,\"invoiceType\":\"2\",\"locale\":\"zh_CN\",\"ordAcctInfoList\":[{\"acctName\":\"店铺返现\",\"acctType\":\"001\",\"adaptType\":\"03\",\"balance\":335995,\"createStaff\":178,\"createTime\":1446285362000,\"deductOrderMoney\":0,\"freezeMoney\":0,\"shopId\":69,\"staffId\":182,\"status\":\"1\",\"totalMoney\":335995,\"updateStaff\":182,\"updateTime\":1470367316000}],\"orderAmount\":2,\"orderId\":\"RW16102500023430\",\"orderMoney\":8099,\"pageNo\":1,\"pageSize\":1,\"preOrdSubList\":[{\"appName\":\"ai-ecp\",\"basePrice\":8000,\"buyPrice\":8000,\"cartItemId\":19439,\"categoryCode\":\"2341\",\"currentSiteId\":1,\"endRowIndex\":1,\"gdsCateName\":\"纸质书-图书\",\"gdsId\":46514,\"gdsName\":\"0426测试商品006-jxk\",\"gdsType\":1,\"groupDetail\":\"86909\",\"groupType\":\"0\",\"locale\":\"zh_CN\",\"orderAmount\":1,\"orderId\":\"RW16102500023430\",\"orderMoney\":8000,\"orderScore\":0,\"orderSubId\":\"SRW16102500025426\",\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"repCode\":4084,\"score\":0,\"shopId\":69,\"shopName\":\"0\",\"skuId\":86909,\"staff\":{\"$ref\":\"$.promPresentRespDTO.staff\"},\"staffId\":182,\"startRowIndex\":0,\"stockId\":12644,\"threadId\":\"192.168.1.158:-7579113733062996415\"},{\"appName\":\"ai-ecp\",\"basePrice\":99,\"buyPrice\":99,\"cartItemId\":19438,\"categoryCode\":\"2333\",\"currentSiteId\":1,\"endRowIndex\":1,\"gdsCateName\":\"纸质书-图书\",\"gdsId\":37915,\"gdsName\":\"0112纸质书001\",\"gdsType\":1,\"groupDetail\":\"75242\",\"groupType\":\"0\",\"locale\":\"zh_CN\",\"orderAmount\":1,\"orderId\":\"RW16102500023430\",\"orderMoney\":99,\"orderScore\":0,\"orderSubId\":\"SRW16102500025427\",\"pageNo\":1,\"pageSize\":1,\"prnFlag\":\"0\",\"repCode\":4084,\"score\":0,\"shopId\":69,\"shopName\":\"0\",\"skuId\":75242,\"staff\":{\"$ref\":\"$.promPresentRespDTO.staff\"},\"staffId\":182,\"startRowIndex\":0,\"stockId\":963,\"threadId\":\"192.168.1.158:-7579113733062996415\"}],\"rOrdInvoiceCommRequest\":{\"detailFlag\":\"0\",\"invoiceContent\":\"\",\"invoiceTitle\":\"\",\"invoiceType\":\"2\"},\"rOrdInvoiceTaxRequest\":{\"acctInfo\":\"\",\"bankName\":\"\",\"contactInfo\":\"\",\"invoiceContent\":\"\",\"invoiceTitle\":\"\",\"phone\":\"\",\"takerAddress\":\"\",\"takerCity\":\"\",\"takerCounty\":\"\",\"takerName\":\"\",\"takerPhone\":\"\",\"takerProvince\":\"\",\"taxpayerNo\":\"\",\"vfsId2\":\"\"},\"realExpressFee\":0,\"realMoney\":8099,\"shopId\":69,\"shopName\":\"测试店铺321\",\"staff\":{\"$ref\":\"$.promPresentRespDTO.staff\"},\"staffId\":182,\"startRowIndex\":0,\"threadId\":\"192.168.1.158:-7579113733062996415\"}],\"threadId\":\"192.168.1.158:-7579113733062996415\"}";
        RSumbitMainsRequest info = JSON.parseObject(data,RSumbitMainsRequest.class);
        List<RSumbitMainRequest> sumbitMainList = info.getSumbitMainList();
        List<ROrdMainResponse> rOrdMainResponseList=new ArrayList<ROrdMainResponse>();
        // 1 保存订单主表
        for (RSumbitMainRequest sumbitMainRequest : sumbitMainList) {
            String ordMainId = createOrdMainId();
            sumbitMainRequest.setOrderId(ordMainId);
            LogUtil.info(MODULE, "保存订单主表开始:"+ordMainId);
//            String ordMainId = sumbitMainRequest.getOrderId();

            sumbitMainRequest.setPayType(info.getPayType());
            ROrdMainResponse rOrdMainResponse= saveOrdMainInfo(sumbitMainRequest,info);
            rOrdMainResponseList.add(rOrdMainResponse);
            LogUtil.info(MODULE, "保存订单主表结束");
            if (CollectionUtils.isEmpty(sumbitMainRequest.getPreOrdSubList())) {
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_352000);
            }
            for (RSumbitSubRequest rSumbitSubRequest : sumbitMainRequest.getPreOrdSubList()) {
                // 2 保存订单子表
                LogUtil.info(MODULE, "保存订单子表开始");
                rSumbitSubRequest.setOrderId(ordMainId);
                rSumbitSubRequest.setOrderSubId(createOrdSubId());
                ordSubSV.saveOrdSubInfo(rSumbitSubRequest,info,rOrdMainResponse);
                LogUtil.info(MODULE, "保存订单子表结束");
                // 5 保存订单赠品信息
                /*
                 * LogUtil.info(MODULE, "保存订单赠品开始"); saveOrdGiftInfo(ordMainId,
                 * rOrdCartItemRequest);
                 */
                LogUtil.info(MODULE, "保存用户商品报表信息");
            }
            // 3 保存订单收货地址
//            LogUtil.info(MODULE, "保存订单收货地址开始");
//            saveOrdDeliveAddrInfo(ordMainId, info);
            // 4 保存订单发票信息(普票，电子票，增票)
            LogUtil.info(MODULE, "保存订单发票开始");
            saveOrdInvoiceInfo(ordMainId, sumbitMainRequest);
            // 6 保存订单跟踪
            LogUtil.info(MODULE, "保存订单跟踪开始");
            saveOrdTrack(ordMainId, sumbitMainRequest);
            // 7 保存订单优惠信息  --处理资金账户、积分帐户的信息
            LogUtil.info(MODULE, "保存订单优惠开始");
            saveOrdDiscount(info,sumbitMainRequest);
            // 8 保存订单促销信息
            LogUtil.info(MODULE, "保存订单促销开始");
            saveOrdProm(ordMainId, info,sumbitMainRequest);
            // 9 保存订单预赠信息  --在第8点中实现
//            LogUtil.info(MODULE, "保存订单预赠开始");
//            saveOrdPresent(ordMainId, sumbitMainRequest);
            // 10 保存线下支付信息
            LogUtil.info(MODULE, "保存线下支付开始");
            if (!sumbitMainRequest.getPayType().equals("0")) {
                saveOrdOffline(ordMainId, sumbitMainRequest);
            }

        }
    }

    public class DealThread extends Thread {

        @Override
        public void run() {

            submitOrder();

        }

    }

    @Test
    public void commitOrder() {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,16,1, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        int group = 10000;
        List<Future> fus = new ArrayList<Future>();
        for (int i = 0; i < group; i++) {
            Future f = executor.submit(new DealThread());
            fus.add(f);
        }
        for (Future f : fus) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();


    }
    @Test
    public void offlinePay(){
        ROfflineQueryRequest rOfflineQueryRequest = new ROfflineQueryRequest();
        rOfflineQueryRequest.setShopId(69l);
        rOfflineQueryRequest.setPageNo(1);
        rOfflineQueryRequest.setPageSize(10000);

        PageResponseDTO<ROfflineQueryResponse> rdors = this.ordOfflineSV.queryOfflineOrder(rOfflineQueryRequest);
        LogUtil.info(MODULE, "线下支付审核："+ JSON.toJSONString(rdors));
        for(ROfflineQueryResponse roff : rdors.getResult()){
            ROfflineReviewRequest rOfflineReviewRequest = new ROfflineReviewRequest();
            rOfflineReviewRequest.setCheckCont("pass");
            rOfflineReviewRequest.setOrderId(roff.getOrderId());
            rOfflineReviewRequest.setOfflineNo(roff.getOfflineNo());
            rOfflineReviewRequest.setStaffId(roff.getStaffId());
            rOfflineReviewRequest.setStatus("1");
            ordOfflinePaySV.saveOfflineReviewPass(rOfflineReviewRequest);
        }
    }
    @Test
    public void getSpecCount(){
    	RQueryOrderRequest req = new RQueryOrderRequest();
        req.setStaffId(182l);
        ROrdSpecialCountResponse rdors = this.ordMainSV.findOrdSpecialCount(req);
        LogUtil.info(MODULE, "获取敏感客户信息："+ JSONObject.toJSONString(rdors));

    }
}

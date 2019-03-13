package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.ABCPayWay;
import com.zengshi.ecp.order.service.busi.impl.pay.PayWayEnum;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.ABCPayAuditResponse.ABCPayAuditVO;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.*;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

public class AuditABCPaySVImpl implements IAuditABCPaySV {

    public final static String payWay = PayWayEnum.getPayWayByImplClass(ABCPayWay.class);

    @Resource
    private IAuditDailySumSV auditDailySumSV;

    @Resource
    private IAuditFileDealLogSV auditFileDealLogSV;

    @Resource
    private IAuditTradeCheckSV auditTradeCheckSV;

    @Resource
    private IAuditABCPayDetailSV auditABCPayDetailSV;

    @Resource
    private IOrdMainSV ordMainSV;

    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

    @Resource
    private IOrdPayRelRSV iOrdPayRelSV;

    /**
     * 
     * TODO 加@Transactional这个注解是为了控制事物，一定不要去掉,现在人卫一个账号配置多个店铺，对账时就对账总表的店铺取默认账号
     * 
     * @see com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAlipaySV#saveAuditInfo(com.zengshi.ecp.order.dao.model.PayShopCfg,
     *      com.zengshi.ecp.order.service.busi.impl.pay.audit.AlipayAuditResponse, java.sql.Timestamp)
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void saveAuditInfo(PayShopCfg payShopCfg, ABCPayAuditResponse auditResponse,
            Timestamp qsDate) {
        // 保存到对账总表
        AuditDailySum auditDailySum = new AuditDailySum();
        auditDailySum.setCheckDate(qsDate);
        auditDailySum.setShopId(Common.DEFAULT_STAFFID);
        auditDailySum.setPayWay(payWay);
        auditDailySumSV.saveAuditDailySum(auditDailySum);

        // 保存到明细表
        for (ABCPayAuditVO abcPayAuditVO : auditResponse.getABCPayAuditList()) {
            AuditABCPayDetail auditABCPayDetail = new AuditABCPayDetail();
            auditABCPayDetail.setCheckDate(qsDate);
            auditABCPayDetail.setMercCode(abcPayAuditVO.getMerc_code());
            auditABCPayDetail.setTransType(abcPayAuditVO.getTransType());
            auditABCPayDetail.setOrderId(abcPayAuditVO.getOrderId());
            auditABCPayDetail.setTransTime(DateUtil.getTimestamp(abcPayAuditVO.getTransTime(),
                    "yyyyMMddHHmmss"));
            auditABCPayDetail.setTransAmount(abcPayAuditVO.getPayment());
            auditABCPayDetail.setMerchAcctId(abcPayAuditVO.getMerchAcctId());
            auditABCPayDetail.setMerchAcctMoney(abcPayAuditVO.getMerchAcctMoney());
            auditABCPayDetail.setPayeeAcct(abcPayAuditVO.getPayeeAcct());
            auditABCPayDetail.setAcctType(abcPayAuditVO.getAcctType());
            auditABCPayDetail.setFee(abcPayAuditVO.getFee());
            auditABCPayDetail.setStagingFee(abcPayAuditVO.getStagingFee());
            auditABCPayDetail.setAccountingDate(DateUtil.getTimestamp(
                    abcPayAuditVO.getAccountingDate(), "yyyyMMdd"));
            auditABCPayDetail.setTransNo(abcPayAuditVO.getTransNo());
            auditABCPayDetail.setTransno9014(abcPayAuditVO.getTransNo9014());
            auditABCPayDetail.setOldOrderId(abcPayAuditVO.getOldOrderId());
            auditABCPayDetailSV.saveAuditABCPayDetail(auditABCPayDetail);
        }
        // 迁移到清算表,更新订单主表IS_IN_AUDIT_FILE字段
        List<AuditABCPayDetail> list = auditABCPayDetailSV.getAuditABCPayDetailByCheckDate(qsDate);
        Long totalNum = 0L;
        Long totalTransAmount = 0L;
        Long refundTotalNum = 0L;
        Long refundTotalTransAmount = 0L;
        for (AuditABCPayDetail auditAlipayDetail : list) {
            AuditTradeCheck auditTradeCheck = fillTradeQsVo(auditAlipayDetail);
            // 迁移到历史表
            auditABCPayDetailSV.deleteAuditABCPayDetail(auditAlipayDetail.getId());
            if (auditTradeCheck == null) {
                continue;
            }
            // 支付
            if (PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_01.equals(auditTradeCheck.getAuditType())) {
                if (!StringUtil.isBlank(auditTradeCheck.getOrderId())) {
                    ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
                    rOrdPayRelReq.setJoinOrderid(auditTradeCheck.getOrderId());
                    List<ROrdPayRelResp> mergeOrdList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
                    if(mergeOrdList != null && mergeOrdList.size() >= 1){
                        for(ROrdPayRelResp rOrdPayRelResp : mergeOrdList){
                            ordMainSV.updateIsInAuditFile(rOrdPayRelResp.getOrderId(), PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_1,payWay);
                        }
                    }
                }
                totalNum++;
                totalTransAmount += auditTradeCheck.getTransAmount();
            } else if (PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_02.equals(auditTradeCheck
                    .getAuditType())) {
                // 退款
                if (!StringUtil.isBlank(auditTradeCheck.getOrderId())) {
                    ordBackApplySV.updateIsInAuditFile(auditTradeCheck.getBackId(),
                            PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_1);
                }
                refundTotalNum++;
                refundTotalTransAmount += auditTradeCheck.getTransAmount();
            }
            auditTradeCheckSV.saveAuditTradeCheck(auditTradeCheck);
        }

        // 更新对账总表
        auditDailySum.setTotalNum(totalNum);
        auditDailySum.setTotalTransAmount(totalTransAmount);
        auditDailySum.setRefundTotalNum(refundTotalNum);
        auditDailySum.setRefundTotalTransAmount(refundTotalTransAmount);
        auditDailySumSV.updateAuditDailySum(auditDailySum);

        // 保存到已处理对账表
        AuditFileDealLog auditFileDealLog = new AuditFileDealLog();
        auditFileDealLog.setPayWay(payWay);
        auditFileDealLog.setCheckDate(qsDate);
        auditFileDealLog.setShopId(Common.DEFAULT_STAFFID);
        auditFileDealLog.setTotalNum(totalNum);
        auditFileDealLog.setTotalTransAmount(totalTransAmount);
        auditFileDealLog.setRefundTotalNum(refundTotalNum);
        auditFileDealLog.setRefundTotalTransAmount(refundTotalTransAmount);
        auditFileDealLog.setFileContent(auditResponse.getFileContent());
        auditFileDealLogSV.saveAuditFileDealLog(auditFileDealLog);

    }

    /**
     * 
     * fillTradeQsVo:查询订单，填充对账表的数据. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author weijq
     * @param value
     * @return
     * @since JDK 1.6
     */
    private AuditTradeCheck fillTradeQsVo(AuditABCPayDetail value) {
        if ("Sale".equals(value.getTransType())) {
            AuditTradeCheck qsVo = new AuditTradeCheck();
            qsVo.setAuditType(PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_01);
            qsVo.setPayWay(payWay);
            qsVo.setTransNo(value.getTransNo());
            qsVo.setCheckDate(value.getCheckDate());
            qsVo.setTransAmount(PayHelper.yuan2Fen(value.getTransAmount()));
            qsVo.setOrderId(value.getOrderId());
            qsVo.setCardNo(value.getPayeeAcct());
            // qsVo.setCardName();
            qsVo.setPayDescription("支付成功");
            qsVo.setCheckStatus(PayStatus.CHECK_STATUS_YES);
            // qsVo.setBankName(value.getBankName());
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            rOrdPayRelReq.setJoinOrderid(value.getOrderId());
            List<ROrdPayRelResp> mergeOrd = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
            long mergeRealMoney = 0;
            if(mergeOrd != null && mergeOrd.size() >= 1){
                qsVo.setStaffId(mergeOrd.get(0).getStaffId());
                //合并支付，就不算这个店铺id 了。不要咯
//                qsVo.setShopId(ordMain.getShopId());
                for(ROrdPayRelResp rOrdPayRelResp : mergeOrd){
                    if(rOrdPayRelResp.getRealMoney() != null){
                        mergeRealMoney += rOrdPayRelResp.getRealMoney();
                    }
                }
                //使用合并订单中的一个拆分订单来进行支付判断
                OrdMain ordMain = ordMainSV.queryOrderByOrderId(mergeOrd.get(0).getOrderId());
                if(ordMain==null){
                    qsVo.setPayTime(mergeOrd.get(0).getUpdateTime());
                }else{
                    qsVo.setPayTime(ordMain.getPayTime());
                    qsVo.setSiteId(ordMain.getSiteId());
                }
                qsVo.setOrderAmount(mergeRealMoney);
                String auditStatus;
                if(ordMain!=null&&Order.ORDER_PAY_FLAG_1.equals(ordMain.getPayFlag())){
                    if(qsVo.getTransAmount()>mergeRealMoney){
                        auditStatus=PayStatus.AUDIT_STATUS_LONG;
                    }else if(qsVo.getTransAmount()<mergeRealMoney){
                        auditStatus=PayStatus.AUDIT_STATUS_SHORT;
                    }else{
                        auditStatus=PayStatus.AUDIT_STATUS_SUCC;
                    }
                }else{
                    auditStatus=PayStatus.AUDIT_STATUS_LONG;
                }
                qsVo.setAuditStatus(auditStatus);
            }else{
                qsVo.setPayTime(value.getTransTime());
            }
            return qsVo;
        } else if ("Refund".equals(value.getTransType())) {
            AuditTradeCheck qsVo = new AuditTradeCheck();
            qsVo.setAuditType(PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_02);
            qsVo.setPayWay(payWay);
            qsVo.setTransNo(value.getTransNo());
            qsVo.setCheckDate(value.getCheckDate());
            qsVo.setTransAmount(PayHelper.yuan2Fen(value.getTransAmount()));
            qsVo.setRefundTransAmount(PayHelper.yuan2Fen(value.getTransAmount()));
            qsVo.setOrderId(value.getOldOrderId());
            qsVo.setCardNo(value.getPayeeAcct());
            // qsVo.setCardName();
            qsVo.setPayDescription("退款成功");
            qsVo.setCheckStatus(PayStatus.CHECK_STATUS_YES);
            // qsVo.setBankName(value.getBankName());
            Long backId = Long.parseLong(value.getOrderId().substring(
                    value.getOldOrderId().length()));
            qsVo.setBackId(backId);
            OrdBackApply ordBackApply = ordBackApplySV.queryOrdBackApplyByBackId(backId);
            qsVo.setPayTime(value.getTransTime());
            String auditStatus;
            if (ordBackApply != null) {
                qsVo.setStaffId(ordBackApply.getStaffId());
                qsVo.setShopId(ordBackApply.getShopId());
                qsVo.setRefundOrderAmount(ordBackApply.getBackMoney());
                qsVo.setSiteId(ordBackApply.getSiteId());
            }
            if (ordBackApply != null
                    && BackConstants.Status.REFUNDED.equals(ordBackApply.getStatus())) {
                if (qsVo.getRefundTransAmount() > ordBackApply.getBackMoney()) {
                    auditStatus = PayStatus.AUDIT_STATUS_LONG;
                } else if (qsVo.getTransAmount() < ordBackApply.getBackMoney()) {
                    auditStatus = PayStatus.AUDIT_STATUS_SHORT;
                } else {
                    auditStatus = PayStatus.AUDIT_STATUS_SUCC;
                }
            } else {
                auditStatus = PayStatus.AUDIT_STATUS_LONG;
            }
            qsVo.setAuditStatus(auditStatus);
            return qsVo;
        } else {
            return null;
        }

    }
}

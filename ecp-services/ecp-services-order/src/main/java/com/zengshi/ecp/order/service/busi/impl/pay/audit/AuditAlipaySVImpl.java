package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.util.BackConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.Alipay;
import com.zengshi.ecp.order.service.busi.impl.pay.PayWayEnum;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.AlipayAuditResponse.AccountQueryAccountLogVO;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayRefundResultSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.*;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

public class AuditAlipaySVImpl implements IAuditAlipaySV {
    
    public final static String payWay = PayWayEnum.getPayWayByImplClass(Alipay.class);

    @Resource
    private IAuditDailySumSV auditDailySumSV;
    
    @Resource
    private IAuditFileDealLogSV auditFileDealLogSV;
    
    @Resource
    private IAuditTradeCheckSV auditTradeCheckSV;
    
    @Resource
    private IAuditAlipayDetailSV auditAlipayDetailSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;
    
    @Resource
    private IPayRefundResultSV payRefundResultSV;

    @Resource
    private IOrdPayRelSV iOrdPayRelSV;
    
    /**
     * 
     * TODO 加@Transactional这个注解是为了控制事物，一定不要去掉,现在人卫一个账号配置多个店铺，对账时就对账总表的店铺取默认账号
     * @see com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAlipaySV#saveAuditInfo(com.zengshi.ecp.order.dao.model.PayShopCfg, com.zengshi.ecp.order.service.busi.impl.pay.audit.AlipayAuditResponse, java.sql.Timestamp)
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void saveAuditInfo(PayShopCfg payShopCfg, AlipayAuditResponse auditResponse,
            Timestamp qsDate) {
        //保存到对账总表
        AuditDailySum auditDailySum = new AuditDailySum();
        auditDailySum.setCheckDate(qsDate);
        auditDailySum.setShopId(Common.DEFAULT_STAFFID);
        auditDailySum.setPayWay(payWay);
        auditDailySumSV.saveAuditDailySum(auditDailySum);
        
        //保存到明细表
        for(AccountQueryAccountLogVO vo:auditResponse.getResponse().getAccount_log_list()){
            AuditAlipayDetail auditAlipayDetail = new AuditAlipayDetail();
            auditAlipayDetail.setCheckDate(qsDate);
            auditAlipayDetail.setBalance(vo.getBalance());
            auditAlipayDetail.setBankAccountName(vo.getBank_account_name());
            auditAlipayDetail.setBankAccountNo(vo.getBank_account_no());
            auditAlipayDetail.setBankName(vo.getBank_name());
            auditAlipayDetail.setBuyerAccount(vo.getBuyer_account());
            auditAlipayDetail.setCurrency(vo.getCurrency());
            auditAlipayDetail.setDepositBankNo(vo.getDeposit_bank_no());
            auditAlipayDetail.setGoodsTitle(vo.getGoods_title());
            auditAlipayDetail.setIncome(vo.getIncome());
            auditAlipayDetail.setIwAccountLogId(vo.getIw_account_log_id());
            auditAlipayDetail.setMemo(vo.getMemo());
            auditAlipayDetail.setMerchantOutOrderNo(vo.getMerchant_out_order_no());
            auditAlipayDetail.setOtherAccountEmail(vo.getOther_account_email());
            auditAlipayDetail.setOtherAccountFullname(vo.getOther_account_fullname());
            auditAlipayDetail.setOtherUserId(vo.getOther_user_id());
            auditAlipayDetail.setOutcome(vo.getOutcome());
            auditAlipayDetail.setPartnerId(vo.getPartner_id());
            auditAlipayDetail.setRate(vo.getRate());
            auditAlipayDetail.setSellerAccount(vo.getSeller_account());
            auditAlipayDetail.setSellerFullname(vo.getSeller_fullname());
            auditAlipayDetail.setServiceFee(vo.getService_fee());
            auditAlipayDetail.setServiceFeeRatio(vo.getService_fee_ratio());
            auditAlipayDetail.setSignProductName(vo.getSign_product_name());
            auditAlipayDetail.setSubTransCodeMsg(vo.getSub_trans_code_msg());
            auditAlipayDetail.setTotalFee(vo.getTotal_fee());
            auditAlipayDetail.setTradeNo(vo.getTrade_no());
            auditAlipayDetail.setTradeRefundAmount(vo.getTrade_refund_amount());
            auditAlipayDetail.setTransAccount(vo.getTrans_account());
            auditAlipayDetail.setTransCodeMsg(vo.getTrans_code_msg());
            auditAlipayDetail.setTransDate(DateUtil.getTimestamp(vo.getTrans_date(), "yyyy-MM-dd HH:mm:ss"));
            auditAlipayDetail.setTransOutOrderNo(vo.getTrans_out_order_no());
            auditAlipayDetailSV.saveAuditAlipayDetail(auditAlipayDetail);
        }
        //迁移到清算表,更新订单主表和退款退款货主表IS_IN_AUDIT_FILE字段
        List<AuditAlipayDetail> list= auditAlipayDetailSV.getAuditAlipayDetailByCheckDate(qsDate);
        Long totalNum = 0L;
        Long totalTransAmount = 0L;
        Long refundTotalNum = 0L;
        Long refundTotalTransAmount = 0L;
//        for(AuditAlipayDetail auditAlipayDetail:list){
    	for(int i=0;i<list.size();i++){
    		AuditAlipayDetail auditAlipayDetail = list.get(i);
            AuditTradeCheck auditTradeCheck = fillTradeQsVo(auditAlipayDetail,i);
            //迁移到历史表
            auditAlipayDetailSV.deleteAuditAlipayDetail(auditAlipayDetail.getId());
            if(auditTradeCheck==null){
                continue;
            }
            auditTradeCheckSV.saveAuditTradeCheck(auditTradeCheck);
            if(PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_01.equals(auditTradeCheck.getAuditType())){
                //支付
                if(!StringUtil.isBlank(auditTradeCheck.getOrderId())) {
                    //因为这个orderId 是银行对账文件的，因此，改成合并支付后，先去合并订单获取对应的拆分订单。根据拆分订单遍历去更新拆分订单的订单主表。
                    ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
                    rOrdPayRelReq.setJoinOrderid(auditTradeCheck.getOrderId());
                    List<ROrdPayRelResp> mergeOrdList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
                    if (mergeOrdList != null && mergeOrdList.size() >= 1) {
                        for (ROrdPayRelResp rOrdPayRelResp : mergeOrdList) {
                            ordMainSV.updateIsInAuditFile(rOrdPayRelResp.getOrderId(), PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_1, payWay);
                        }
                    }
                }
                totalNum++;
                totalTransAmount+=auditTradeCheck.getTransAmount();
            }else if(PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_02.equals(auditTradeCheck.getAuditType())){
                // 退款
                if (!StringUtil.isBlank(auditTradeCheck.getOrderId())) {
                    ordBackApplySV.updateIsInAuditFile(auditTradeCheck.getBackId(),
                            PayStatus.ORD_MAIN_IS_IN_AUDIT_FILE_1);
                }
                refundTotalNum++;
                refundTotalTransAmount+=auditTradeCheck.getTransAmount();
            }
            
        }
        //更新对账总表
        auditDailySum.setTotalNum(totalNum);
        auditDailySum.setTotalTransAmount(totalTransAmount);
        auditDailySum.setRefundTotalNum(refundTotalNum);
        auditDailySum.setRefundTotalTransAmount(refundTotalTransAmount);
        auditDailySumSV.updateAuditDailySum(auditDailySum);
        
        //保存到已处理对账表
        AuditFileDealLog auditFileDealLog = new AuditFileDealLog();
        auditFileDealLog.setPayWay(payWay);
        auditFileDealLog.setCheckDate(qsDate);
        auditFileDealLog.setShopId(Common.DEFAULT_STAFFID);
        auditFileDealLog.setTotalNum(totalNum);
        auditFileDealLog.setTotalTransAmount(totalTransAmount);
        auditFileDealLog.setRefundTotalNum(refundTotalNum);
        auditFileDealLog.setRefundTotalTransAmount(refundTotalTransAmount);
        auditFileDealLog.setFileContent(auditResponse.getXmlStr());
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
    private AuditTradeCheck   fillTradeQsVo(AuditAlipayDetail value,int i){
        String auditType ="";
        String payDescription = "";
        //区分退款和支付类型，并去掉其他类型的对账
        //坑爹的支付宝居然用中文来区分，有新标准过来就把这个改了
        if("在线支付".equals(value.getTransCodeMsg())){
            auditType = PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_01;
            payDescription = "支付成功";
            AuditTradeCheck qsVo =  new AuditTradeCheck();
            qsVo.setAuditType(auditType);
            qsVo.setPayWay(payWay);
            qsVo.setTransNo(value.getTradeNo());
            qsVo.setCheckDate(value.getCheckDate());
            qsVo.setTransAmount(PayHelper.yuan2Fen(value.getTotalFee()));
            qsVo.setOrderId(value.getMerchantOutOrderNo());
            qsVo.setCardNo(value.getBankAccountNo());
            qsVo.setCardName(value.getBankAccountName());
            qsVo.setPayDescription(payDescription);
            qsVo.setCheckStatus(PayStatus.CHECK_STATUS_YES);
            qsVo.setBankName(value.getBankName());
            //因为搞合并支付了。因此从银行获取的队长文件的订单编码都是合并后的订单。因此，需要先去合并订单表（t_ord_pay_rel）获取订单信息
            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
            rOrdPayRelReq.setJoinOrderid(value.getMerchantOutOrderNo());
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
                qsVo.setPayTime(value.getTransDate());
            }
            return qsVo;


        }else if("交易退款".equals(value.getSubTransCodeMsg())){
            auditType = PayStatus.AUDIT_TRADE_CHECK_AUDIT_TYPE_02;
            payDescription = "退款成功";
            AuditTradeCheck qsVo =  new AuditTradeCheck();
            qsVo.setAuditType(auditType);
            qsVo.setPayWay(payWay);
            qsVo.setTransNo(value.getTradeNo()+DateUtil.getDateString("yyyyMMdd")+i);
            qsVo.setCheckDate(value.getCheckDate());
            qsVo.setTransAmount(PayHelper.yuan2Fen(value.getTotalFee()));
            qsVo.setRefundTransAmount(PayHelper.yuan2Fen(value.getTradeRefundAmount()));
            qsVo.setOrderId(value.getMerchantOutOrderNo());
            qsVo.setCardNo(value.getBankAccountNo());
            qsVo.setCardName(value.getBankAccountName());
            qsVo.setPayDescription(payDescription);
            qsVo.setCheckStatus(PayStatus.CHECK_STATUS_YES);
            qsVo.setBankName(value.getBankName());
            qsVo.setPayTime(value.getTransDate());
            OrdBackApply ordBackApply = null;
            List<PayRefundResult> payRefundResultlist = payRefundResultSV.getPayRefundResultByOrderId(value.getMerchantOutOrderNo());
            if(!CollectionUtils.isEmpty(payRefundResultlist)){
                for(PayRefundResult payRefundResult:payRefundResultlist){
                    if(PayWayEnum.Alipay.getPayWay().equals(payRefundResult.getPayWay())){
                        qsVo.setBackId(payRefundResult.getBackId());
                        ordBackApply = ordBackApplySV.queryOrdBackApplyByBackId(payRefundResult.getBackId());
                        if(ordBackApply!=null){
                        	qsVo.setStaffId(ordBackApply.getStaffId());
                        	qsVo.setShopId(ordBackApply.getShopId());
                        	qsVo.setRefundOrderAmount(payRefundResult.getRefundAmount());
                        }
                        break;
                    }
                }
            }
            String auditStatus;
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
        }else{
            return null;
        }
//        AuditTradeCheck qsVo =  new AuditTradeCheck();
//        qsVo.setAuditStatus(auditType);
//        qsVo.setPayWay(payWay);
//        qsVo.setTransNo(value.getTradeNo());
//        qsVo.setCheckDate(value.getCheckDate());
//        qsVo.setTransAmount(PayHelper.yuan2Fen(value.getTotalFee()));
//        qsVo.setOrderId(value.getMerchantOutOrderNo());
//        qsVo.setCardNo(value.getBankAccountNo());
//        qsVo.setCardName(value.getBankAccountName());
//        qsVo.setPayDescription(payDescription);
//        qsVo.setCheckStatus(PayStatus.CHECK_STATUS_YES);
//        qsVo.setBankName(value.getBankName());
//        OrdMain ordMain = ordMainSV.queryOrderByOrderId(value.getMerchantOutOrderNo());
//        if(ordMain==null){
//            qsVo.setPayTime(value.getTransDate());
//        }else{
//            qsVo.setStaffId(ordMain.getStaffId());
//            qsVo.setShopId(ordMain.getShopId());
//            qsVo.setPayTime(ordMain.getPayTime());
//            qsVo.setOrderAmount(ordMain.getRealMoney());
//        }
//        String auditStatus;
//        if(ordMain!=null&&Order.ORDER_PAY_FLAG_1.equals(ordMain.getPayFlag())){
//            if(qsVo.getTransAmount()>ordMain.getRealMoney()){
//                auditStatus=PayStatus.AUDIT_STATUS_LONG;
//            }else if(qsVo.getTransAmount()<ordMain.getRealMoney()){
//                auditStatus=PayStatus.AUDIT_STATUS_SHORT;
//            }else{
//                auditStatus=PayStatus.AUDIT_STATUS_SUCC;
//            }
//        }else{
//            auditStatus=PayStatus.AUDIT_STATUS_LONG;
//        }
//        qsVo.setAuditStatus(auditStatus);
//        return qsVo;
    }
}


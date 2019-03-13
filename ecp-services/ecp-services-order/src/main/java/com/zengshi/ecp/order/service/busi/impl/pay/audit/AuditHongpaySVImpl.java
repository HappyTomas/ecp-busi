package com.zengshi.ecp.order.service.busi.impl.pay.audit;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zengshi.ecp.order.dao.model.AuditDailySum;
import com.zengshi.ecp.order.dao.model.AuditFileDealLog;
import com.zengshi.ecp.order.dao.model.AuditHongpayDetail;
import com.zengshi.ecp.order.dao.model.AuditTradeCheck;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.service.busi.impl.pay.HongpayPlatform;
import com.zengshi.ecp.order.service.busi.impl.pay.PayWayEnum;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.HongpayAuditResponse.HongpayAuditVO;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditDailySumSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditFileDealLogSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditHongpayDetailSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditHongpaySV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditTradeCheckSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

public class AuditHongpaySVImpl implements IAuditHongpaySV {
    
    public final static String payWay = PayWayEnum.getPayWayByImplClass(HongpayPlatform.class);
    public static final String PAY_RESULT_0 = "0";//交易结果：0（成功）
    public static final String PAY_RESULT_1 = "1";//交易结果：1（失败）

    @Resource
    private IAuditDailySumSV auditDailySumSV;
    
    @Resource
    private IAuditFileDealLogSV auditFileDealLogSV;
    
    @Resource
    private IAuditTradeCheckSV auditTradeCheckSV;
    
    @Resource
    private IAuditHongpayDetailSV auditHongpayDetailSV;
    
    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private IOrdPayRelSV iOrdPayRelSV;
    
    /**
     * 
     * TODO 加@Transactional这个注解是为了控制事物，一定不要去掉,现在人卫一个账号配置多个店铺，对账时就对账总表的店铺取默认账号
     * @see com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAlipaySV#saveAuditInfo(com.zengshi.ecp.order.dao.model.PayShopCfg, com.zengshi.ecp.order.service.busi.impl.pay.audit.AlipayAuditResponse, java.sql.Timestamp)
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void saveAuditInfo(PayShopCfg payShopCfg, HongpayAuditResponse auditResponse,
            Timestamp qsDate) {
        //保存到对账总表
        AuditDailySum auditDailySum = new AuditDailySum();
        auditDailySum.setCheckDate(qsDate);
        auditDailySum.setShopId(Common.DEFAULT_STAFFID);
        auditDailySum.setPayWay(payWay);
        auditDailySumSV.saveAuditDailySum(auditDailySum);
        
        //保存到明细表
        for(HongpayAuditVO vo:auditResponse.getHongpayAuditList()){
            AuditHongpayDetail auditHongpayDetail = new AuditHongpayDetail();
            auditHongpayDetail.setCheckDate(qsDate);
            auditHongpayDetail.setTransDate(DateUtil.getTimestamp(vo.getTransDate(), "yyyyMMdd"));
            auditHongpayDetail.setPayType(vo.getPayType());
            auditHongpayDetail.setMercCode(vo.getMercCode());
            auditHongpayDetail.setOrderId(vo.getOrderId());
            auditHongpayDetail.setTransNo(vo.getTransNo());
            auditHongpayDetail.setBankCardId(vo.getBankCardId());
            auditHongpayDetail.setBankCardName(vo.getBankCardName());
            auditHongpayDetail.setPayeerAccountId(vo.getPayeerAccountId());
            auditHongpayDetail.setTransType(vo.getTransType());
            auditHongpayDetail.setTransAmount(vo.getTransAmount());
            auditHongpayDetail.setPayResult(vo.getPayResult());
            auditHongpayDetail.setTransTime(DateUtil.getTimestamp(vo.getTransTime(), "yyyyMMddHHmmss"));
            auditHongpayDetailSV.saveAuditHongpayDetail(auditHongpayDetail);
        }
        //迁移到清算表,更新订单主表IS_IN_AUDIT_FILE字段
        Long totalNum = 0L;
        Long totalTransAmount = 0L;
        List<AuditHongpayDetail> list= auditHongpayDetailSV.getAuditHongpayDetailByCheckDate(qsDate);
        for(AuditHongpayDetail auditHongpayDetail:list){
            //去掉除交易成功之外的其他对账信息
            if("5".equals(auditHongpayDetail.getPayType())&&PAY_RESULT_0.equals(auditHongpayDetail.getPayResult())){
                AuditTradeCheck auditTradeCheck = fillTradeQsVo(auditHongpayDetail);
                auditTradeCheckSV.saveAuditTradeCheck(auditTradeCheck);
                auditHongpayDetailSV.deleteAuditHongpayDetail(auditHongpayDetail.getId());
                if(!StringUtil.isBlank(auditTradeCheck.getOrderId())){
                    //因为这个orderId 是银行对账文件的，因此，改成合并支付后，先去合并订单获取对应的拆分订单。根据拆分订单遍历去更新拆分订单的订单主表。
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
                totalTransAmount+=auditTradeCheck.getTransAmount();
            }else{
                auditHongpayDetailSV.deleteAuditHongpayDetail(auditHongpayDetail.getId());
            }
        }
        
        //更新对账总表
        auditDailySum.setTotalNum(totalNum);
        auditDailySum.setTotalTransAmount(totalTransAmount);
        auditDailySumSV.updateAuditDailySum(auditDailySum);
        //保存到已处理对账表
        AuditFileDealLog auditFileDealLog = new AuditFileDealLog();
        auditFileDealLog.setPayWay(payWay);
        auditFileDealLog.setCheckDate(qsDate);
        auditFileDealLog.setShopId(Common.DEFAULT_STAFFID);
        auditFileDealLog.setTotalNum(auditResponse.getTotalNum());
        auditFileDealLog.setTotalTransAmount(auditResponse.getTotalTransAmount());
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
    private AuditTradeCheck   fillTradeQsVo(AuditHongpayDetail value){
        AuditTradeCheck qsVo =  new AuditTradeCheck();
        
        qsVo.setPayWay(payWay);
        qsVo.setTransNo(value.getTransNo());
        qsVo.setCheckDate(value.getCheckDate());
        qsVo.setTransAmount(Long.valueOf(value.getTransAmount()));
        qsVo.setOrderId(value.getOrderId());
        qsVo.setCardNo(value.getBankCardId());
        qsVo.setCardName(value.getBankCardName());
        qsVo.setPayDescription("支付成功");
        qsVo.setCheckStatus(PayStatus.CHECK_STATUS_YES);
//        qsVo.setBankName(value.get);

        //因为搞合并支付了。因此从银行获取的队长文件的订单编码都是合并后的订单。因此，需要先去合并订单表（t_ord_pay_rel）获取订单信息
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setJoinOrderid(value.getOrderId());
        List<ROrdPayRelResp> mergeOrd = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        long mergeRealMoney = 0;
        if(mergeOrd != null && mergeOrd.size() >= 1){
            qsVo.setStaffId(mergeOrd.get(0).getStaffId());
            //合并支付，就不算这个店铺id 了。不要咯
//            qsVo.setShopId(ordMain.getShopId());
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

    }
}


package com.zengshi.ecp.order.service.busi.impl.pay;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;

import com.abc.pay.client.Constants;
import com.abc.pay.client.MerchantConfig;
import com.abc.pay.client.MerchantPara;
import com.abc.pay.client.TrxException;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayRefundRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayRefundResponse;
import com.zengshi.ecp.aip.dubbo.dto.ABCPaySettleRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPaySettleResponse;
import com.zengshi.ecp.aip.dubbo.interfaces.IABCPayRSV;
import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.ecp.order.dao.model.AuditAutoLog;
import com.zengshi.ecp.order.dao.model.AuditDailySum;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.PayRefundResult;
import com.zengshi.ecp.order.dao.model.PayShopCfg;
import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dubbo.dto.pay.ABCPayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.ABCPayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfNotifyLogDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayIntfReqLogDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayParamVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRefundSuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.ABCPayAuditResponse;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditABCPaySV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAutoLogSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;

public class ABCPayWay extends DefaultPayWay {
    public static final String module = ABCPayWay.class.getName();

    public static final String SUCC = "1";
    
    private final static String PAYRETURNURL = "payReturnUrl";

    public final static String payWay = PayWayEnum.getPayWayByImplClass(ABCPayWay.class);

    @Resource
    private IOrdPayRelRSV iOrdPayRelSV;

    @Resource
    private IPayShopCfgSV payShopCfgSV;
    
    @Resource
    private IOrderQueryRSV orderQueryRSV;
    
    @Resource
    private IAuditAutoLogSV auditAutoLogSV;
    
    @Resource
    private IAuditABCPaySV auditABCPaySV;
    
    @Resource
    private IABCPayRSV ABCPayRSV;

    @SuppressWarnings("unchecked")
    @Override
    public PayRequestData requestPayment(PaymentRequest request, Map<String, String> extendProps)
            throws Exception {
        LogUtil.info(module, "----------*ABCPayWay requestPayment*----------");

        PayParamVO vo = buildPayParamVO(request.getOrderId(), payWay);
        PayHelper.validatePayParams(vo);
        Date date = DateUtil.getDate();
        // 1、生成订单对象
        com.abc.pay.client.ebus.PaymentRequest tPaymentRequest = new com.abc.pay.client.ebus.PaymentRequest();
        tPaymentRequest.dicOrder.put("PayTypeID", "ImmediatePay"); // 设定交易类型 直接支付:ImmediatePay
                                                                   // 分期支付:DividedPay
                                                                   // 预授权支付:PreAuthPay
        tPaymentRequest.dicOrder.put("OrderDate", DateUtil.getDateString(date, "yyyy/MM/dd")); // 设定订单日期
                                                                                               // （必要信息
                                                                                               // -
                                                                                               // YYYY/MM/DD）
        tPaymentRequest.dicOrder.put("OrderTime", DateUtil.getDateString(date, "HH:mm:ss")); // 设定订单时间
                                                                                             // （必要信息
                                                                                             // -
                                                                                             // HH:MM:SS）
        // tPaymentRequest.dicOrder.put("orderTimeoutDate", "orderTimeoutDate"); // 设定订单有效期
        tPaymentRequest.dicOrder.put("OrderNo", vo.getOrderId()); // 设定订单编号 （必要信息）
        tPaymentRequest.dicOrder.put("CurrencyCode", "156"); // 设定交易币种
        tPaymentRequest.dicOrder.put("OrderAmount",
                PayHelper.fen2Yuan(Long.toString(vo.getPayment()))); // 设定交易金额 单位是元
        // tPaymentRequest.dicOrder.put("Fee", ""); // 设定手续费金额
        // tPaymentRequest.dicOrder.put("OrderDesc", ""); // 设定订单说明
        // tPaymentRequest.dicOrder.put("OrderURL", ""); // 设定订单地址
        // tPaymentRequest.dicOrder.put("ReceiverAddress", ""); // 收货地址
        tPaymentRequest.dicOrder.put("InstallmentMark", "0"); // 分期标识
        // if ("InstallmentMark" == "1" && "PayTypeID" == "DividedPay") {
        // tPaymentRequest.dicOrder.put("InstallmentCode", "InstallmentCode"); // 设定分期代码
        // tPaymentRequest.dicOrder.put("InstallmentNum", "InstallmentNum"); // 设定分期期数
        // }
        tPaymentRequest.dicOrder.put("CommodityType", "0201"); // 设置商品种类
        // tPaymentRequest.dicOrder.put("BuyIP", ""); // IP
        // tPaymentRequest.dicOrder.put("ExpiredDate", ""); // 设定订单保存时间

        // 2、订单明细
//        List<OrdSub> OrdSubList = getordSubByOrderId(request.getOrderId());
        //因为改造成合并支付，因此传入的订单是新生成的合并订单。
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setJoinOrderid(request.getOrderId());
        List<ROrdPayRelResp> list = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310005);
        }
        for (int i = 0; i < list.size(); i++) {
            List<OrdSub> OrdSubList = getordSubByOrderId(list.get(i).getOrderId());
            if (OrdSubList!=null&&OrdSubList.size()>0) {
                for (OrdSub ordSub : OrdSubList) {
                    LinkedHashMap<String, String> orderitem = new LinkedHashMap<String, String>();
                    // orderitem.put("SubMerName", "测试二级商户1"); // 设定二级商户名称
                    // orderitem.put("SubMerId", "12345"); // 设定二级商户代码
                    // orderitem.put("SubMerMCC", "0000"); // 设定二级商户MCC码
                    // orderitem.put("SubMerchantRemarks", "测试"); // 二级商户备注项
                    // orderitem.put("ProductID", "IP000001");// 商品代码，预留字段
                    orderitem.put("ProductName", ordSub.getGdsName());// 商品名称
                    // orderitem.put("UnitPrice", "1.00");// 商品总价
                    // orderitem.put("Qty", "1");// 商品数量
                    // orderitem.put("ProductRemarks", "测试商品"); // 商品备注项
                    // orderitem.put("ProductType", "充值类");// 商品类型
                    // orderitem.put("ProductDiscount", "0.9");// 商品折扣
                    // orderitem.put("ProductExpiredDate", "10");// 商品有效期
                    tPaymentRequest.orderitems.put(i, orderitem);
                }
            }
        }


        // 3、生成支付请求对象
        String paymentType = "A";
        tPaymentRequest.dicRequest.put("PaymentType", paymentType); // 设定支付类型 1：农行卡支付 2：国际卡支付
                                                                    // 3：农行贷记卡支付 5:基于第三方的跨行支付
                                                                    // A:支付方式合并 6：银联跨行支付，7:对公户
        String paymentLinkType = "1";
        if("1".equals(extendProps.get("appflag"))){
        	paymentLinkType = "2";// 
        }
        tPaymentRequest.dicRequest.put("PaymentLinkType", paymentLinkType); // 设定支付接入方式
                                                                            // 1：internet网络接入
                                                                            // 2：手机网络接入 3:数字电视网络接入
                                                                            // 4:智能客户端
        if (paymentType.equals(Constants.PAY_TYPE_UCBP)
                && paymentLinkType.equals(Constants.PAY_LINK_TYPE_MOBILE)) {
            tPaymentRequest.dicRequest.put("UnionPayLinkType", "0"); // 当支付类型为6，支付接入方式为2的条件满足时，需要设置银联跨行移动支付接入方式
                                                                     // 0：页面接入
                                                                     // 1：客户端接入（仅支持方式一，方式二不支持）
        }
        // tPaymentRequest.dicRequest.put("ReceiveAccount", "ReceiveAccount"); // 设定收款方账号
        // tPaymentRequest.dicRequest.put("ReceiveAccName", "ReceiveAccName"); // 设定收款方户名
        String notifyType ="1";
        tPaymentRequest.dicRequest.put("NotifyType", notifyType); // 设定通知方式
        tPaymentRequest.dicRequest.put("ResultNotifyURL", vo.getPayWay().getPayNotifyUrl()); // 设定通知URL地址
        tPaymentRequest.dicRequest.put("MerchantRemarks", request.getStaff().getId()); // 设定附言
        tPaymentRequest.dicRequest.put("IsBreakAccount", "0"); // 设定交易是否分账
        // tPaymentRequest.dicRequest.put("SplitAccTemplate", "SplitAccTemplate"); // 分账模版编号

        MerchantPara para = MerchantConfig.getUniqueInstance().getPara();
        String sTrustPayIETrxURL = para.getTrustPayTrxIEURL();
        String sErrorUrl = para.getMerchantErrorURL();

//        List<PayShopCfg> payShopCfgList = payShopCfgSV.getCfgByPayWay(payWay);
//        int i = 0;
//        for (PayShopCfg payShopCfg : payShopCfgList) {
//            i++;
//            if (vo.getShopId() == payShopCfg.getShopId()) {
//                break;
//            }
//            para.getMerchantIDList();
//        }
        List<String> MercCodeList = para.getMerchantIDList();
        
        int i = 0;
        int num = 0;
        for(String MercCode:MercCodeList){
            i++;
            if(MercCode.equals(vo.getPayShopCfg().getMercCode())){
                num = i;
                break;
            }
        }
        String tSignature = "";
        try {
            tSignature = tPaymentRequest.genSignature(num);
        } catch (TrxException e) {
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310002, new String[] {
                    e.getCode(), e.getMessage() });
        }
        ABCPayWayRequest ABCrequest = new ABCPayWayRequest();
        ABCrequest.setMSG(tSignature);
        ABCrequest.setErrorPage(sErrorUrl);
        PayRequestData pr = new PayRequestData();
        pr.setFormData(ABCrequest.toMap());

        pr.setActionUrl(sTrustPayIETrxURL);// 发送地址
        String charset = vo.getPayWay().getCharSet();// 编码 gb2312
        pr.setCharset(charset);

        String requestStr = formatPayRequest(ABCrequest);

        PayRequestDTO payRequestDTO = new PayRequestDTO();

        payRequestDTO.setPayWay(payWay);
        payRequestDTO.setOrderId(request.getOrderId());
        long staffId = 0L;
        if (request.getStaff() != null) {
            staffId = request.getStaff().getId();
        }
        payRequestDTO.setStaffId(staffId);
        payRequestDTO.setShopId(vo.getShopId());
        payRequestDTO.setPayment(vo.getPayment());
        payRequestDTO.setCreateStaff(staffId);
        addPayRequest(payRequestDTO);
        PayIntfReqLogDTO log = new PayIntfReqLogDTO();
        log.setPayWay(payWay);
        log.setOrderId(request.getOrderId());
        log.setStaffId(staffId);
        // log.setRlRequestNo("");
        log.setRequestTime(DateUtil.getSysDate());
        // log.setResponseTime(responseTime);
        log.setRequestMsg(requestStr);
        // log.setResponseMsg(responseMsg);
        addPayLog(log);

        return pr;
    }

    @Override
    public Map<String, String> paymentCallback(Map<String, String> responseResultMap)
            throws Exception {
        LogUtil.info(module, "农行支付回调开始" + PayHelper.toJsonStr(responseResultMap));

        Map<String, String> result = new HashMap<String, String>();
        PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
        Timestamp requestTime = DateUtil.getSysDate();
        String payNotifyMSG = "";
        String orderId = "";
        String tradeNo = "";
        long staffId = Common.DEFAULT_STAFFID;
        Timestamp responseTime = requestTime;
        PayWay payWayBean = getPayWayBean(payWay);
        String payreturnurl = payWayBean.getPayReturnUrl();
        try {

            payNotifyMSG = responseResultMap.get("MSG");
            com.abc.pay.client.ebus.PaymentResult tResult = new com.abc.pay.client.ebus.PaymentResult(
                    payNotifyMSG);

            ABCPayWayResponse response = new ABCPayWayResponse(tResult);
            response.buildSelf(responseResultMap);
            try {
                staffId = Long.parseLong(response.getMerchantRemarks());
            } catch (Exception e) {

            }
            if(StringUtil.isEmpty(staffId)){
            	staffId = Common.DEFAULT_STAFFID;
            }
            orderId = response.getOrderNo();
            tradeNo = response.getIRspRef();
            if (tResult.isSuccess()) {
                PaySuccInfo paySuccInfo = new PaySuccInfo();
                paySuccInfo.setOrderId(response.getOrderNo());
                paySuccInfo.setPayWay(payWay);
                paySuccInfo.setStaffId(staffId);
                paySuccInfo.setPayTransNo(response.getIRspRef());
                paySuccInfo.setPayment(PayHelper.yuan2Fen(response.getAmount()));
                // paySuccInfo.setPayeeAcct(response.get);
                // paySuccInfo.setPayeeName(payeeName);
                // paySuccInfo.setPayWayName(payWayName);
                paySuccInfo.setMerchAcct(response.getMerchantID());
                // paySuccInfo.setShopId(shopId);
                paySuccInfo.setPayType(PayStatus.PAY_TYPE_01);
                saveHandlePaySucc(paySuccInfo);
                
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.SUCCESS);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "success");
            } else {
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "农行返回支付失败失败！");
            }
        } catch (Exception e) {
            LogUtil.error(module, "农行支付回调异常" + PayHelper.toJsonStr(result),e);
            result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
            result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "农行支付回调异常！");
        }
        payreturnurl = payreturnurl + "?orderId=" + orderId;
        result.put(PAYRETURNURL, payreturnurl);
        responseTime = DateUtil.getSysDate();
        log.setPayWay(payWay);
        log.setOrderId(orderId);
        log.setStaffId(staffId);
        log.setPaywayRequestNo(tradeNo);
        log.setRequestTime(requestTime);
        log.setRequestMsg(payNotifyMSG);
        log.setResponseTime(responseTime);
        log.setResponseMsg(PayHelper.toJsonStr(result));
        addPayResponseLog(log);
        LogUtil.info(module, "订单"+orderId+"农行支付回调结束" + PayHelper.toJsonStr(result));
        return result;
    }
    
    /**
     * 
     * TODO 农行的退款是实时的. 
     * @see com.zengshi.ecp.order.service.busi.impl.pay.DefaultPayWay#refund(com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest, java.util.Map)
     */
    @Override
    public RPayRefundResponse refund(RPayRefundRequest request, Map<String, String> extendProps)
            throws Exception {
        LogUtil.info(module, "----------*ABCPayWay refund request*----------"+JSON.toJSONString(request)+"----------extendProps----------"+JSON.toJSONString(extendProps));
        OrdMain ordMainBean = getOrderMainBean(request.getOrderId());
        if(ordMainBean == null){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
        }
        long shopId = 0l;
        // 获取合并支付的开关 1-表示开启  0-表示关闭
        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
        	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
        }else{
        	shopId = ordMainBean.getShopId();
        }
        PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
        if(payShopCfg==null){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310009);
        }
        if(ordMainBean.getRealMoney().longValue()<request.getRefundAmount().longValue()){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310016);
        }
        //初始化返回参数
        RPayRefundResponse response = new RPayRefundResponse();
        response.setRefundMethod(PayStatus.PAY_REFUND_METHOD_01);
        //封装请求参数开始
        ABCPayRefundRequest abcPayRefundRequest = new ABCPayRefundRequest();
        String orderDate = DateUtil.getDateString("YYYY/MM/dd");
        String orderTime = DateUtil.getDateString("HH:mm:ss");
        String merRefundAccountNo = "";
        String merRefundAccountName = "";
        String orderNo = request.getOrderId();
        String newOrderNo = request.getOrderId()+request.getBackId();
        String currencyCode = "156";
        String trxAmount = PayHelper.fen2Yuan(Long.toString(request.getRefundAmount()))+"";


        //获取支付流水
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setOrderId(request.getOrderId());
        rOrdPayRelReq.setPayFlag("1");
        List<ROrdPayRelResp> rOrdPayRelResp = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if(com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty(rOrdPayRelResp)){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310017);
        }
        String payId = rOrdPayRelResp.get(0).getJoinOrderid();


//        String merchantRemarks ="";
        boolean connectionFlag = false;
        String merchantID = payShopCfg.getMercCode();
        abcPayRefundRequest.setOrderDate(orderDate);
        abcPayRefundRequest.setOrderTime(orderTime);
//        abcPayRefundRequest.setOrderNo(orderNo);
        abcPayRefundRequest.setOrderNo(payId);
        abcPayRefundRequest.setNewOrderNo(newOrderNo);
        abcPayRefundRequest.setCurrencyCode(currencyCode);
        abcPayRefundRequest.setTrxAmount(trxAmount);
        abcPayRefundRequest.setMerRefundAccountNo(merRefundAccountNo);
        abcPayRefundRequest.setMerRefundAccountName(merRefundAccountName);
        abcPayRefundRequest.setMerchantID(merchantID);
        abcPayRefundRequest.setConnectionFlag(connectionFlag);
        //封装请求参数结束
        
        //插入退款结果表，如果已有就更新即可，状态为正在退款中
        PayRefundResult payRefundResult = payRefundResultSV.getPayRefundResultByPaywayBackId(payWay,request.getBackId());
        if(payRefundResult==null){
            payRefundResult = new PayRefundResult();
            payRefundResult.setBackId(request.getBackId());
            payRefundResult.setOrderId(request.getOrderId());
            payRefundResult.setPayWay(payWay);
            payRefundResult.setRefundAmount(request.getRefundAmount());
            payRefundResult.setRefundStatus(PayStatus.PAY_REFUND_RESULT_STATUS_1);
            payRefundResult.setRefundDesc("线上退款");
            payRefundResult.setRefundTime(DateUtil.getSysDate());
            payRefundResultSV.addPayRefundResult(payRefundResult);
        }else{
            payRefundResult.setRefundTime(DateUtil.getSysDate());
            payRefundResultSV.updateRefund(payRefundResult);
        }
        ABCPayRefundResponse abcPayRefundResponse = null;
        String erroMsg ="";
        //是否请求成功，只要接口不报异常就是请求成功
        boolean requestFlag = false;
        Timestamp requestTime = DateUtil.getSysDate();
        try{
            abcPayRefundResponse = ABCPayRSV.refund(abcPayRefundRequest);
            requestFlag= true;
        }catch(Exception e){
            LogUtil.error(module, "农行退款请求异常",e);
            erroMsg = PayHelper.getExceptionStackTrace(e);
        }
        //记录退款日志表
        Timestamp responseTime = DateUtil.getSysDate();
        String requestMsg = JSON.toJSONString(abcPayRefundRequest);
        PayIntfReqLogDTO log = new PayIntfReqLogDTO();
        log.setPayWay(payWay);
        log.setOrderId(request.getOrderId());
        log.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_02);
        log.setStaffId(request.getStaff().getId());
        log.setRequestTime(requestTime);
        log.setRequestMsg(requestMsg);
        log.setResponseTime(responseTime);
        if(abcPayRefundResponse!=null){
            log.setResponseMsg(JSON.toJSONString(abcPayRefundResponse));
        }else{
            log.setResponseMsg(erroMsg);
        }
        payIntfReqLogSV.addPayIntfReqLog(log);
        //请求成功要记录报文并处理退款逻辑
        if(requestFlag){
            String flag = PayStatus.PAY_REFUND_RESULT_STATUS_2;
            if("0000".equals(abcPayRefundResponse.getReturnCode())){
                flag = PayStatus.PAY_REFUND_RESULT_STATUS_2;
            }else{
                flag = PayStatus.PAY_REFUND_RESULT_STATUS_3;
            }
            try{
                PayRefundSuccInfo payRefundSuccInfo = new PayRefundSuccInfo();
                payRefundSuccInfo.setBackId(request.getBackId());
                payRefundSuccInfo.setOrderId(request.getOrderId());
                payRefundSuccInfo.setPayWay(payWay);
                payRefundSuccInfo.setStaffId(request.getStaff().getId());
                payRefundSuccInfo.setRefundTransNo(abcPayRefundResponse.getBatchNo());
                payRefundSuccInfo.setPayment(PayHelper.yuan2Fen(abcPayRefundResponse.getTrxAmount()));
//                payRefundSuccInfo.setPayeeName(payeeName);
//                payRefundSuccInfo.setPayeeAcct(payeeAcct);
                payRefundSuccInfo.setFlag(flag);
                saveRefundCallback(payRefundSuccInfo);
                response.setFlag(true);
                response.setMessage("退款成功");
            }catch(Exception e){
                if(PayStatus.PAY_REFUND_RESULT_STATUS_2.equals(flag)){
                    response.setFlag(false);
                    response.setMessage("农行已经退款成功但退款逻辑处理失败");
                }else{
                    response.setFlag(false);
                    response.setMessage(abcPayRefundResponse.getErrorMessage());
                }
            }
            
        }else{
            response.setFlag(false);
            response.setMessage(erroMsg);
        }
        return response;
    }

    @Override
    public OrderPayStatusVO parsePayStatus(Map<String, String> params) throws Exception {
        
        LogUtil.info(module, "ABCPayWay parsePayStatus params=" + PayHelper.toJsonStr(params));
        OrderPayStatusVO vo = new OrderPayStatusVO();
        vo.setOrderId(params.get("orderId"));
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setJoinOrderid(params.get("orderId"));
        List<ROrdPayRelResp> ordList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if(ordList != null && ordList.size() >= 1){
            OrdMain ordMain = getOrderMainBean(ordList.get(0).getOrderId());
            if(ordMain!=null){
                vo.setFlag(OrderPayStatusVO.SUCCESS);
                vo.setPayTime(ordMain.getPayTime());
            }else{
                vo.setFlag(OrderPayStatusVO.FAILURE);
            }
        }else{
            vo.setFlag(OrderPayStatusVO.FAILURE);
        }
        LogUtil.info(module, "ABCPayWay parsePayStatus vo=" + vo.toString());
        return vo;
    }

    @Override
    public Map<String, String> check(Map<String, String> resultData) throws Exception {
        LogUtil.info(module, "----------*ABCPayWay check start*----------"+PayHelper.toJsonStr(resultData));
        List<Timestamp> qsDates = PayHelper.parseQsdateAsTimestamp(resultData);
        if(qsDates.size()==0){//如果没有传就只处理今天的(昨天的清算文件)，如果传了，就按照传参处理
            qsDates.add(PayHelper.getCurDate_1AsTimestamp_yyyyMMdd());
        }
        List<PayShopCfg> payShopCfgList = payShopCfgSV.getCfgByPayWay(payWay);
        //循环处理每一天
        for(Timestamp qsDate:qsDates){
          //循环处理每个商户
            for (PayShopCfg payShopCfg : payShopCfgList) {
                dealCheck(payShopCfg,qsDate);
            }
        }
        LogUtil.info(module, "----------*ABCPayWay check end*----------"+PayHelper.toJsonStr(resultData));
        return null;
    }
    
    public void dealCheck(PayShopCfg payShopCfg, Timestamp qsDate){
        try{
            LogUtil.info(module, "----------*HongpayPlatform dealCheck start*----------qsDate:"+qsDate);
            //已经对账了的不再进行处理
            AuditDailySum auditDailySumReq = new AuditDailySum();
            auditDailySumReq.setCheckDate(qsDate);
            auditDailySumReq.setPayWay(payWay);
            auditDailySumReq.setShopId(Common.DEFAULT_STAFFID);
            AuditDailySum auditDailySumResp = auditDailySumSV.getAuditDailySum(auditDailySumReq);
            if(auditDailySumResp!=null){
                LogUtil.error(module, "农行对账");
                //记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(payWay);
                auditAutoLog.setExecMethods("dealCheck");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark("对账已经处理，不再进行");
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
                return;
            }
            // 1、取得商户对账单下载所需要的信息
            ABCPaySettleResponse settleResponse = getCheckInfo(payShopCfg, qsDate);
    
            //4、判断商户对账单下载结果状态，进行后续操作
            String returnCode = settleResponse.getReturnCode();
            String errorMessage = settleResponse.getErrorMessage();
            if ("0000".equals(returnCode)) {
                ABCPayAuditResponse abcPayAuditResponse = new ABCPayAuditResponse();
                Map<String,String> responseMap = new HashMap<String, String>();
                responseMap.put("trxType", settleResponse.getTrxType());
                responseMap.put("settleDate", settleResponse.getSettleDate());
                responseMap.put("detailRecords", settleResponse.getDetailRecords());
                responseMap.put("fileContent", JSON.toJSONString(settleResponse));
                abcPayAuditResponse.buildSelf(responseMap);
                auditABCPaySV.saveAuditInfo(payShopCfg, abcPayAuditResponse, qsDate);
            }else{
              //记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(payWay);
                auditAutoLog.setExecMethods("dealCheck");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark("获取对账文件失败："+errorMessage);
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
            }
        }catch(Exception e){
            LogUtil.error(module, "农行对账失败", e);
            //记录日志
            AuditAutoLog auditAutoLog = new AuditAutoLog();
            auditAutoLog.setPayWay(payWay);
            auditAutoLog.setExecMethods("dealCheck");
            auditAutoLog.setExecuteTime(DateUtil.getSysDate());
            auditAutoLog.setRemark(PayHelper.getExceptionStackTrace(e));
            auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
        }
        LogUtil.info(module, "----------*HongpayPlatform dealCheck end*----------qsDate:"+qsDate);
    }
    
    /**
     * 
     * getCheckInfo:通过接口获取对账信息，保存请求报文. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payShopCfg
     * @param qsDate
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private ABCPaySettleResponse getCheckInfo(PayShopCfg payShopCfg ,Timestamp qsDate) throws Exception{
        LogUtil.info(module, "----------*ABCPayWay getCheckInfo *----------qsDate:"+qsDate);
        String tSettleDate = DateUtil.getDateString(qsDate, "yyyy/MM/dd");
        String tZIP = "0";

        ABCPaySettleRequest settleRequest = new ABCPaySettleRequest();
        settleRequest.setSettleDate(tSettleDate);
        settleRequest.setZIP(tZIP);
        settleRequest.setMerchantID(payShopCfg.getMercCode());
        ABCPaySettleResponse settleResponse;
        Timestamp logRequestTime = DateUtil.getSysDate();
        Timestamp responseTime = logRequestTime;
        String requestMsg = JSONObject.fromObject(settleRequest).toString();
        settleResponse = ABCPayRSV.querySettle(settleRequest);
        responseTime = DateUtil.getSysDate();
        String responseMsg = JSONObject.fromObject(settleResponse).toString();
        PayIntfReqLogDTO log = new PayIntfReqLogDTO();
        log.setPayWay(payWay);
        log.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_08);
        log.setStaffId(Common.DEFAULT_STAFFID);
        log.setRequestTime(logRequestTime);
        log.setRequestMsg(requestMsg);
        log.setResponseTime(responseTime);
        log.setResponseMsg(responseMsg);
        payIntfReqLogSV.addPayIntfReqLog(log);
        LogUtil.info(module, "----------*ABCPayWay getCheckInfo settleResponse:*----------"+JSON.toJSONString(settleResponse).toString());
        return settleResponse;
    }
}

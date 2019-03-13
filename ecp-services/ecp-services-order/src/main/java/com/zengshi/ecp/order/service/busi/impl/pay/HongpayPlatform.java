package com.zengshi.ecp.order.service.busi.impl.pay;

import cn.com.hongpay.client.anal.ResMsgAnalyze;
import cn.com.hongpay.client.gen.ReqMsgGenerate;
import cn.com.hongpay.client.utils.SignatureTools;
import cn.com.hongpay.client.vo.pay.SinglePayNotifyResVo;
import cn.com.hongpay.client.vo.pay.SinglePayResVo;
import cn.com.hongpay.client.vo.pay.SinglePayVo;
import cn.com.hongpay.client.vo.refund.RefundResVo;
import cn.com.hongpay.client.vo.refund.RefundVo;
import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.pay.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Order;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.HongpayAuditResponse;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAutoLogSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditHongpaySV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.Md5Util;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 鸿支付<br>
 * Date:2015年10月28日下午8:57:19 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public class HongpayPlatform extends DefaultPayPlatform {
    public static final String module = HongpayPlatform.class.getName();

     private static final String SUCC = "00";// 亚信 成功标识
     
     private final static String REFUNDRETURNURL = "refundReturnUrl";
     
     @Resource
     private IPayShopCfgSV payShopCfgSV;
     
     @Resource
     private IOrderQueryRSV orderQueryRSV;
     
     @Resource
     private IAuditAutoLogSV auditAutoLogSV;
     
     @Resource
     private IAuditHongpaySV auditHongpaySV;

    @Resource
    private IOrdPayRelRSV iOrdPayRelSV;

    @Autowired(required = false)
    private IOrdBackApplySV  ordBackApplySV;


    public final static String payWay = PayWayEnum.getPayWayByImplClass(HongpayPlatform.class);

    @Override
    public PayRequestData requestPayment(PaymentRequest request, Map<String, String> extendProps)
            throws Exception {
        LogUtil.info(module, "----------*HongpayPlatform requestPayment request*----------"+request.toString()+"----------extendProps----------"+extendProps.toString());
        PayParamVO vo = buildPayParamVO(request.getOrderId(), payWay);
        PayHelper.validatePayParams(vo);
        CustInfoResDTO staffInfo = getStaffInfo(vo.getStaffId());
        if (staffInfo == null) {
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310010);
        }
        vo.setStaffInfo(staffInfo);

        String pfxFileId = vo.getPayShopCfg().getKeyName();
        String pfxFile = PayHelper.downloadFileAndGetLocalPath(pfxFileId);// 私钥文件路径
        LogUtil.info(module, "----------*HongpayPlatform requestPayment pfxFile*----------"+pfxFile);
        String pfxPwd = vo.getPayShopCfg().getCerPassword();// 私钥库密码,请修改成自己的密码
        String privateKeyPwd = vo.getPayShopCfg().getCerName();// 私钥密码,请修改成自己的密码

        String serviceType = "12";// 服务类型 非空
        if("1".equals(extendProps.get("appflag"))){
            serviceType = "11";// 服务类型 非空
        }
        String clientIp = extendProps.get("clientIp");// 客户端IP地址 非空
//        String clientIp = "120.132.0.66";// 客户端IP地址 非空
        String partnerId = vo.getPayWay().getPayMercCode();// 合作方标识 非空//TODO
        String partnerVerifyCode = vo.getPayWay().getPayPrivatePasswd();// 身份校验码 非空//TODO
        Date date = DateUtil.getSysDate();
        Date requestTime = date;// 请求时间
        String payerPartnerAcctId = vo.getOrdMain().getStaffId() + "";// 付款方账户标识 非空
        String payerPartnerAcctName = vo.getStaffInfo().getCustName();// 付款方账户名称 非空
        if(StringUtil.isBlank(payerPartnerAcctName)){
            payerPartnerAcctName =vo.getStaffId()+"";
        }
        Date tradeTime = date;// 交易时间 非空
        long totalAmount = vo.getPayment();// 交易总金额，单位分，必填 非空
        int totalRecord = 1;// 交易记录数，请填1，必填 非空
        String returnUrl = vo.getPayWay().getPayReturnUrl()+"?orderId="+vo.getOrderId();// 支付结果返回链接地址 非空
        String notifyUrl = vo.getPayWay().getPayNotifyUrl();// 支付结果通知链接地址 非空
        String acctReservedField1 = vo.getOrdMain().getShopId() + "";// 账户预留字段1
        String acctReservedField2 = request.getStaff().getId()+"";//账户预留字段2
        // String acctReservedField3 = "";//账户预留字段3
        // String tradeRemark = "";//交易备注

        String partnerTradeNo = vo.getOrderId();// 订单号，必填 合作方交易流水号 非空
        long payAmount = vo.getPayment();// 订单金额，单位分，必填 非空
        String payeePartnerAcctId = vo.getPayShopCfg().getPayeeAccount();// 收款方账户标识，必填 非空
        String payeePartnerAcctName = vo.getPayShopCfg().getPayeeName();// 收款方账户名称，必填 非空
        // String payeeCardType = "";//收款方卡类型
        // String payeeCardNo = "";//收款方卡号
        // String payeeBankCode = "";//收款方银行卡归属银行编码
        // String payeeCardName = "";//收款方开户名
        // String payeeCertType = "";//收款方证件类型
        // String payeeCertCode = "";//收款方证件号码
        // String tradeReservedField1 = "";//预留字段1
        // String tradeReservedField2 = "";//预留字段2
        // String tradeReservedField3 = "";//预留字段3
        // String tradeReservedField4 = "";//预留字段4
        // String tradeReservedField5 = "";//预留字段5
        // String detailRemark = "";//明细备注

        // 设置支付请求信息
        SinglePayVo payVo = new SinglePayVo();
        payVo.setServiceType(serviceType);// 服务类型，请根据接口文档按需使用
        payVo.setClientIp(clientIp);
        payVo.setPartnerId(partnerId);// 合作方ID ，由鸿支付统一分配，必填
        payVo.setPartnerVerifyCode(partnerVerifyCode);// 合作方身份校验码，由鸿支付统一分配，必填
        payVo.setRequestTime(requestTime);// 当前日期，必填

        // TRANS_SUM
        payVo.setPayerPartnerAcctId(payerPartnerAcctId);// 付款方账户标识，必填
        payVo.setPayerPartnerAcctName(payerPartnerAcctName);// 付款方账户名称，必填
        payVo.setTradeTime(tradeTime);
        payVo.setTotalAmount(totalAmount);
        payVo.setTotalRecord(totalRecord);
        payVo.setReturnUrl(returnUrl);
        payVo.setNotifyUrl(notifyUrl);
        payVo.setAcctReservedField1(acctReservedField1);
        payVo.setAcctReservedField2(acctReservedField2);
        // payVo.setAcctReservedField3(acctReservedField3);
        // payVo.setTradeRemark(tradeRemark);
        payVo.setPartnerTradeNo(partnerTradeNo);
        payVo.setPayAmount(payAmount);
        payVo.setPayeePartnerAcctId(payeePartnerAcctId);
        payVo.setPayeePartnerAcctName(payeePartnerAcctName);
        // payVo.setPayeeCardType(payeeCardType);
        // payVo.setPayeeCardNo(payeeCardNo);
        // payVo.setPayeeBankCode(payeeBankCode);
        // payVo.setPayeeCardName(payeeCardName);
        // payVo.setPayeeCertType(payeeCertType);
        // payVo.setPayeeCertCode(payeeCertCode);
        // payVo.setTradeReservedField1(tradeReservedField1);
        // payVo.setTradeReservedField2(tradeReservedField2);
        // payVo.setTradeReservedField3(tradeReservedField3);
        // payVo.setTradeReservedField4(tradeReservedField4);
        // payVo.setTradeReservedField5(tradeReservedField5);
        // payVo.setDetailRemark(detailRemark);

        // 实例化请求报文构造类
        ReqMsgGenerate reqMsgGenerate = new ReqMsgGenerate(pfxFile, pfxPwd, privateKeyPwd);
        // 生成支付请求报文
        String requestPacket = reqMsgGenerate.generatePayMsg(payVo);
        requestPacket = requestPacket.replace("\"", "&quot;");

        HongpayRequest hongpayRequest = new HongpayRequest();
        hongpayRequest.setRequestPacket(requestPacket);
        PayRequestData pr = new PayRequestData();
        pr.setFormData(hongpayRequest.toMap());

        pr.setActionUrl(vo.getPayWay().getPayActionUrl());// 发送地址
        pr.setAppActionUrl(vo.getPayWay().getPayAppActionUrl());
        String charset = vo.getPayWay().getCharSet();// 编码 GBK
        pr.setCharset(charset);

        String requestStr = formatPayRequest(hongpayRequest);

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
        LogUtil.info(module, "----------*HongpayPlatform requestPayment pr*----------"+pr.toString());
        return pr;
    }

    @Override
    public Map<String, String> paymentCallback(Map<String, String> responseResultMap)
            throws Exception {
        LogUtil.info(module, "鸿支付回调开始" + PayHelper.toJsonStr(responseResultMap));
        /*******************************
         * 1.获取通知报文，验证，解析
         *******************************/
        Map<String, String> result = new HashMap<String, String>();
        PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
        Timestamp requestTime = DateUtil.getSysDate();
        String payNotifyXml = "";
        String orderId = "";
        String tradeNo = "";
        long staffId = Common.DEFAULT_STAFFID;
        Timestamp responseTime = requestTime;
        long shopId = 0l;
        try {
            PayWay payWayBean = getPayWayBean(payWay);

            // 初始化解析报文实例
            String cerFileId = payWayBean.getPayVerifyCert();// "E:/hongpay/aipay_public.cer";//
            // 公钥文件，请以自己的路径为准
            String cerFile = PayHelper.downloadFileAndGetLocalPath(cerFileId);
            ResMsgAnalyze analyze = new ResMsgAnalyze(cerFile);

            // 鸿支付台通知报文
            payNotifyXml = responseResultMap.get(PayStatus.FROM_AIP_HONGPAY_NOTIFY_KEY);
            // 验证并解析通知报文
            SinglePayResVo singlePayResVo = analyze.verifyPayNotifyMsg(payNotifyXml);
            try {
                staffId = Long.parseLong(singlePayResVo.getAcctReservedField2());
            } catch (Exception e) {

            }
            if(StringUtil.isEmpty(staffId)){
            	staffId = Common.DEFAULT_STAFFID;
            }
            if (singlePayResVo != null) {
                // 成功接收到支付结果通知
                if (SUCC.equals(singlePayResVo.getResultCode())) {
                    // 支付成功
                    PaySuccInfo paySuccInfo = new PaySuccInfo();
                    paySuccInfo.setOrderId(singlePayResVo.getPartnerTradeNo());
                    paySuccInfo.setPayWay(payWay);
                    paySuccInfo.setStaffId(staffId);
                    paySuccInfo.setPayTransNo(singlePayResVo.getTradeNo());
                    paySuccInfo.setPayment(singlePayResVo.getAmount());
//                    paySuccInfo.setPayeeAcct(response.get);
//                    paySuccInfo.setPayeeName(payeeName);
//                    paySuccInfo.setPayWayName(payWayName);
                    paySuccInfo.setMerchAcct(singlePayResVo.getPartnerId());
//                    paySuccInfo.setShopId(shopId);o
                    paySuccInfo.setPayType(PayStatus.PAY_TYPE_01);
                    saveHandlePaySucc(paySuccInfo);
                }
                //OrdMain ordMain = getOrderMainBean(singlePayResVo.getPartnerTradeNo());
                //PayShopCfg payShopCfg = getPayShopCfgBean(ordMain.getShopId(), payWay);
                //合并支付shopId要取配置
                // 获取合并支付的开关 1-表示开启  0-表示关闭
                BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
                if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
                	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
                }else{
                	orderId = singlePayResVo.getPartnerTradeNo();
                	// 查询订单对应的店铺
    	            ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
    	    		rOrdPayRelReq.setJoinOrderid(orderId);
    	            List<ROrdPayRelResp> orderList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
    	            if(orderList == null || orderList.size() == 0){
    	    			throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
    	    		}
    	            ROrdPayRelResp ordPayRelResp = orderList.get(0);
    				shopId = ordPayRelResp.getShopId();
                }
                PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
                LogUtil.info(module, "店铺配置"+payShopCfg.toString());
                // ** 返回信息给鸿支付，告知已收到支付结果通知
                SinglePayNotifyResVo payNotifyResVo = new SinglePayNotifyResVo();
                
                String partnerId = payWayBean.getPayMercCode();// 合作方标识 非空//TODO
                String partnerVerifyCode = payWayBean.getPayPrivatePasswd();// 身份校验码 非空//TODO
                payNotifyResVo.setPartnerId(partnerId);// 合作方标识
                                                                         // ，由鸿支付统一分配，必填。请填写鸿支付提供的合作方ID
                payNotifyResVo.setPartnerVerifyCode(partnerVerifyCode);// 合作方身份校验码，由鸿支付统一分配，必填。请填写鸿支付提供的身份校验码
                payNotifyResVo.setRequestTime(DateUtil.getSysDate());// 当前日期，必填
                payNotifyResVo.setTradeNo(singlePayResVo.getTradeNo());
                payNotifyResVo.setPartnerTradeNo(singlePayResVo.getPartnerTradeNo());
                payNotifyResVo.setResultCode("00");// 00：成功收到通知

                // 初始化报文构造器
                String pfxFileId = payShopCfg.getKeyName();
                String pfxFile = PayHelper.downloadFileAndGetLocalPath(pfxFileId);// 私钥文件路径
                String pfxPwd = payShopCfg.getCerPassword();// 私钥库密码,请修改成自己的密码
                String privateKeyPwd = payShopCfg.getCerName();// 私钥密码,请修改成自己的密码
                
                ReqMsgGenerate reqMsgGenerate = new ReqMsgGenerate(pfxFile, pfxPwd, privateKeyPwd);
                // 生成签名的支付通知反馈报文
                String signPayNotifyMsg = reqMsgGenerate.generatePayNotifyResMsg(payNotifyResVo);
                LogUtil.info(module, "鸿支付支付通知报文"+payNotifyResVo.toString());
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.SUCCESS);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, signPayNotifyMsg);

            } else {
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "验证签名失败");
            }
            responseTime = DateUtil.getSysDate();
            if(singlePayResVo != null){
                orderId=singlePayResVo.getPartnerTradeNo();
                try{
                    staffId=Long.valueOf(singlePayResVo.getAcctReservedField2());
                }catch(Exception e){
                    
                }
                tradeNo=singlePayResVo.getTradeNo();
            }
        } catch (Exception e) {
            LogUtil.error(module, "鸿支付回调异常" + PayHelper.toJsonStr(result),e);
            result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
            result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "鸿支付回调异常");
        }
        log.setPayWay(payWay);
        log.setOrderId(orderId);
        log.setStaffId(staffId);
        log.setPaywayRequestNo(tradeNo);
        log.setRequestTime(requestTime);
        log.setRequestMsg(payNotifyXml);
        log.setResponseTime(responseTime);
        log.setResponseMsg(JSON.json(result));
        addPayResponseLog(log);
        LogUtil.info(module, "订单"+orderId+"鸿支付回调结束" + PayHelper.toJsonStr(result));
        return result;
    }

    @Override
    public OrderPayStatusVO parsePayStatus(Map<String, String> params) throws Exception {
        OrderPayStatusVO vo = new OrderPayStatusVO();
        vo.setOrderId(params.get("orderId"));
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setJoinOrderid(params.get("orderId"));
        List<ROrdPayRelResp> ordList = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if(ordList != null && ordList.size() >= 1){
            OrdMain ordMain = getOrderMainBean(ordList.get(0).getOrderId());
            if(ordMain!=null&&Order.ORDER_PAY_FLAG_1.equals(ordMain.getPayFlag())){
                vo.setFlag(OrderPayStatusVO.SUCCESS);
                vo.setPayTime(ordMain.getPayTime());
            }else{
                vo.setFlag(OrderPayStatusVO.FAILURE);
            }
        }else{
            vo.setFlag(OrderPayStatusVO.FAILURE);
        }

        return vo;

    }

    @Override
    public RPayRefundResponse refund(RPayRefundRequest request, Map<String, String> extendProps)
            throws Exception {
        LogUtil.info(module, "----------*HongpayPlatform refund orderId*----------"+request.getOrderId()+"----------refundAmount----------"+request.getRefundAmount()+"----------extendProps----------"+extendProps.toString());
        PayWay payWayBean = getPayWayBean(payWay);
        OrdMain ordMainBean = getOrderMainBean(request.getOrderId());
        if(ordMainBean==null){
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

        //获取支付流水
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setOrderId(request.getOrderId());
        rOrdPayRelReq.setPayFlag("1");
        List<ROrdPayRelResp> rOrdPayRelResp = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if(com.alibaba.dubbo.common.utils.CollectionUtils.isEmpty(rOrdPayRelResp)){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310017);
        }
        String payId = rOrdPayRelResp.get(0).getJoinOrderid();

        // 初始化解析报文实例
        String cerFileId = payWayBean.getPayVerifyCert();
        // 公钥文件，请以自己的路径为准
        String cerFile = PayHelper.downloadFileAndGetLocalPath(cerFileId);
        ResMsgAnalyze analyze = new ResMsgAnalyze(cerFile);

        //初始化报文构造器
        String pfxFileId = payShopCfg.getKeyName();
        String pfxFile = PayHelper.downloadFileAndGetLocalPath(pfxFileId);// 私钥文件路径
        String pfxPwd = payShopCfg.getCerPassword();// 私钥库密码,请修改成自己的密码
        String privateKeyPwd = payShopCfg.getCerName();// 私钥密码,请修改成自己的密码
        ReqMsgGenerate reqMsgGenerate = new ReqMsgGenerate(pfxFile,pfxPwd,privateKeyPwd);

        String partnerId = payWayBean.getPayMercCode();// 合作方标识 非空
        String partnerVerifyCode = payWayBean.getPayPrivatePasswd();// 身份校验码 非空
        //根据鸿支付网上支付成功的订单构造退款请求报文
        RefundVo refundVo = new RefundVo();
        //INFO
        refundVo.setServiceType("80");//服务类型 必填
//        refundVo.setClientIp(extendProps.get("clientIp"));//客户端IP,request为HttpServletRequest
        BaseSysCfgRespDTO bsc = SysCfgUtil.fetchSysCfg("REFUND_CLIENTIP");
        refundVo.setClientIp(bsc.getParaValue());//客户端IP,request为HttpServletRequest
        refundVo.setPartnerId(partnerId);//合作方ID ，由鸿支付统一分配，必填。请填写鸿支付提供的合作方ID
        refundVo.setPartnerVerifyCode(partnerVerifyCode);//合作方身份校验码，由鸿支付统一分配，必填。请填写鸿支付提供的身份校验码
        refundVo.setRequestTime(DateUtil.getSysDate());//请求时间，必填
        //BODY
//        refundVo.setPartnerTradeNo(request.getOrderId());//退款订单号，必填
        refundVo.setPartnerTradeNo(payId);//退款订单号，必填
        refundVo.setRefundAmount(request.getRefundAmount());//退款金额，单位分
        refundVo.setRefundNotifyUrl(payWayBean.getPayRefundNotifyUrl());//退款结果通知URL地址,必填
        refundVo.setRefundTime(DateUtil.getSysDate());//退款时间,必填
//        refundVo.setRefundRemark("退款");//备注，选填
        refundVo.setRefundRemark(String.valueOf(request.getBackId()));//备注，选填   //部分退款ID编号，成功时会原样返回
        //生成签名的退款请求报文
        String refundMsg = reqMsgGenerate.generateRefundMsg(refundVo);

//
//        HttpClient httpClient = new HttpClient();
//        httpClient.getParams().setParameter("http.protocol.content-charset",HTTP.UTF_8);
//        httpClient.getParams().setParameter("Content-Encoding",HTTP.UTF_8);
//        httpClient.getParams().setParameter("; charset=", HTTP.UTF_8);
//        httpClient.getParams().setParameter("US-ASCII", HTTP.UTF_8);
//        String url = payWayBean.getPayRefundUrl();
//        //MySecureProtocolSocketFactory 忽略无信任证书
//        Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory (), 443);
////MySecureProtocolSocketFactory请参考附录A
//        Protocol.registerProtocol("https", myhttps);
//
//        //设置请求url及请求报文
//        PostMethod postMethod = new PostMethod(url);
//        postMethod.setParameter("requestPacket", refundMsg);
//
//        //发起请求
//        int statusCode = httpClient.executeMethod(postMethod);
//        if(statusCode == HttpStatus.SC_OK){
//            byte[] responseBody = postMethod.getResponseBody();
//            String refundResXml = new String(responseBody, HTTP.UTF_8);
//            LogUtil.info(module,com.alibaba.fastjson.JSON.toJSONString(refundResXml));
//        } else {
//            LogUtil.info(module,"鸿支付请求异常");
//            throw new Exception("鸿支付请求异常");
//        }

        Map<String,String> requestMap = new HashMap<String, String>();
        requestMap.put("requestPacket", refundMsg);
        Map<String,String> resultMap;
        Timestamp requestTime = DateUtil.getSysDate();
        Timestamp responseTime = requestTime;
        String requestMsg = PayHelper.toJsonStr(requestMap);
        String responseMsg;
        try{
            resultMap = orderQueryRSV.getHongResultData(payWay, payWayBean.getCharSet(), payWayBean.getPayRefundUrl(), requestMap);
        }catch (Exception e) {
            LogUtil.error(module, "鸿支付退款请求失败", e);
            resultMap =  new HashMap<String,String>();
            resultMap.put(PayStatus.FROM_AIP_PARAM_MESSAGE,"调用AIP失败:"+e.getMessage());
        }
        //验证签名并解析报文
        RefundResVo refundResVo = analyze.verifyRefundResMsg(resultMap.get("respResult"));
        if(refundResVo == null){
            LogUtil.error(module, "报文验证签名失败");
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310018);
        }
        responseTime = DateUtil.getSysDate();
        LogUtil.info(module,JSON.json(refundResVo) );
        PayIntfReqLogDTO log = new PayIntfReqLogDTO();
        log.setPayWay(payWay);
        log.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_02);
        log.setStaffId(Common.DEFAULT_STAFFID);
        log.setRequestTime(requestTime);
        log.setRequestMsg(requestMsg);
        log.setResponseTime(responseTime);
        log.setResponseMsg(JSON.json(refundResVo));
        payIntfReqLogSV.addPayIntfReqLog(log);
        LogUtil.error(module, "----------*HongpayPlatform refund end*----------resultMap:"+PayHelper.toJsonStr(resultMap));


        //插入退款结果表，如果已有就更新即可
        PayRefundResult payRefundResult = payRefundResultSV.getPayRefundResultByPaywayBackId(payWay,request.getBackId());
        if(payRefundResult==null){
            payRefundResult = new PayRefundResult();
            payRefundResult.setBackId(request.getBackId());
            payRefundResult.setOrderId(request.getOrderId());
            payRefundResult.setPayWay(payWay);
            payRefundResult.setRefundTransNo(payId);
            payRefundResult.setRefundAmount(request.getRefundAmount());
            payRefundResult.setRefundStatus(PayStatus.PAY_REFUND_RESULT_STATUS_1);
            payRefundResult.setRefundDesc("线上退款");
            payRefundResult.setRefundTime(DateUtil.getSysDate());
            payRefundResultSV.addPayRefundResult(payRefundResult);
        }else{
            payRefundResult.setRefundTime(DateUtil.getSysDate());
            payRefundResultSV.updateRefund(payRefundResult);
        }


        RPayRefundResponse rPayRefundResponse = new RPayRefundResponse();
        rPayRefundResponse.setRefundMethod("01");  //不跳页面
        rPayRefundResponse.setMessage(refundResVo.getRefundMsg());
        return rPayRefundResponse;
    }
    
    /**
     * 
     * TODO 根据定时任务web传入的参数QsDates判断要对账的时间，如果没有传入，则处理前一天的对账信息，如果有，就循环处理每一天的对账信息. 
     * 逻辑是循环获取店铺支付通道，每个店铺有一个对账文件，分别处理之
     * @see com.zengshi.ecp.order.service.busi.impl.pay.DefaultPayWay#check(java.util.Map)
     */
    @Override
    public Map<String, String> check(Map<String, String> resultData) throws Exception {
        LogUtil.info(module, "----------*HongpayPlatform check resultData*----------"+PayHelper.toJsonStr(resultData));
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
        LogUtil.info(module, "----------*HongpayPlatform check end*----------"+PayHelper.toJsonStr(resultData));
        return null;
    }
    
    /**
     * 
     * dealCheck:根据日期处理对账. <br/> 
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
    private Map<String, String> dealCheck(PayShopCfg payShopCfg ,Timestamp qsDate) throws Exception {
        try{
            LogUtil.info(module, "----------*HongpayPlatform dealCheck *----------qsDate:"+qsDate);
            //已经对账了的不再进行处理
            AuditDailySum auditDailySumReq = new AuditDailySum();
            auditDailySumReq.setCheckDate(qsDate);
            auditDailySumReq.setPayWay(payWay);
            auditDailySumReq.setShopId(Common.DEFAULT_STAFFID);
            AuditDailySum auditDailySumResp = auditDailySumSV.getAuditDailySum(auditDailySumReq);
            if(auditDailySumResp!=null){
                LogUtil.error(module, "鸿支付对账");
                //记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(payWay);
                auditAutoLog.setExecMethods("dealCheck");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark("对账已经处理，不再进行");
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
                return null;
            }
            Map<String,String> resultMap = getCheckInfo(payShopCfg, qsDate);
            String flag = resultMap.get(PayStatus.FROM_AIP_PARAM_FLAG);
            String remark = resultMap.get(PayStatus.FROM_AIP_PARAM_MESSAGE);
            if(PayStatus.FROM_AIP_SUCCESS.equals(flag)){
                HongpayAuditResponse auditResponse = new HongpayAuditResponse();
                resultMap.put("checkDate", DateUtil.getDateString(qsDate, "yyyyMMdd"));
                auditResponse.buildSelf(resultMap);
                auditHongpaySV.saveAuditInfo(payShopCfg, auditResponse, qsDate);
            }else{
                //记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(payWay);
                auditAutoLog.setExecMethods("dealCheck");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark("aip获取对账文件失败："+remark);
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
            }
        }catch(Exception e){
            LogUtil.error(module, "鸿支付对账失败", e);
            //记录日志
            AuditAutoLog auditAutoLog = new AuditAutoLog();
            auditAutoLog.setPayWay(payWay);
            auditAutoLog.setExecMethods("dealCheck");
            auditAutoLog.setExecuteTime(DateUtil.getSysDate());
            auditAutoLog.setRemark(PayHelper.getExceptionStackTrace(e));
            auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
        }
        LogUtil.info(module, "----------*HongpayPlatform dealCheck end*----------qsDate:"+qsDate);
        return null;
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
    private Map<String, String> getCheckInfo(PayShopCfg payShopCfg ,Timestamp qsDate) throws Exception{
        LogUtil.info(module, "----------*HongpayPlatform getCheckInfo *----------");
        PayWay payWaybean = getPayWayBean(payWay);
        Map<String,String> requestMap = new HashMap<String, String>();
        String auditDate = DateUtil.getDateString(qsDate, "yyyyMMdd");//yyyyMMdd
        String requestTime = DateUtil.getDateString("yyyy-MM-dd HH:mm:ss");
        String partnerId = payWaybean.getPayMercCode();// 合作方标识 非空
        String partnerVerifyCode = payWaybean.getPayPrivatePasswd();// 身份校验码 非空
        
        String pfxFileId = payShopCfg.getKeyName();
        String pfxFile = PayHelper.downloadFileAndGetLocalPath(pfxFileId);// 私钥文件路径
        String pfxPwd = payShopCfg.getCerPassword();// 私钥库密码,请修改成自己的密码
        String privateKeyPwd = payShopCfg.getCerName();// 私钥密码,请修改成自己的密码
        
        String auditURL = payWaybean.getPayAuditUrl();
        partnerVerifyCode =Md5Util.encode(partnerVerifyCode + requestTime);
        String signData = auditDate+"|"+requestTime+"|"+partnerId+"|"+partnerVerifyCode;
        String sign = SignatureTools.sign(signData, "UTF-8", pfxFile, pfxPwd, privateKeyPwd);
        requestMap.put("AUDIT_DATE", auditDate);
        requestMap.put("REQUEST_TIME", requestTime);
        requestMap.put("PARTNER_ID", partnerId);
        requestMap.put("PARTNER_VERIFY_CODE", partnerVerifyCode);
        requestMap.put("SIGNED_MSG", sign);
        Map<String,String> resultMap;
        Timestamp logRequestTime = DateUtil.getSysDate();
        Timestamp responseTime = logRequestTime;
        String requestMsg = PayHelper.toJsonStr(requestMap);
        String responseMsg;
        try{
            resultMap = orderQueryRSV.getqueryPaymentResult(payWay, payWaybean.getCharSet(), auditURL, requestMap);
        }catch (Exception e) {
            LogUtil.error(module, "鸿支付对账aip请求失败", e);
            resultMap =  new HashMap<String,String>();
            resultMap.put(PayStatus.FROM_AIP_PARAM_MESSAGE,"调用AIP失败:"+e.getMessage());
        }
        responseTime = DateUtil.getSysDate();
        String flag = resultMap.get(PayStatus.FROM_AIP_PARAM_FLAG);
        
        if(PayStatus.FROM_AIP_SUCCESS.equals(flag)){
            responseMsg = resultMap.get(PayStatus.FROM_AIP_PaymentResult);
        }else{
            responseMsg = resultMap.get(PayStatus.FROM_AIP_PARAM_MESSAGE);
        }
        PayIntfReqLogDTO log = new PayIntfReqLogDTO();
        log.setPayWay(payWay);
        log.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_08);
        log.setStaffId(Common.DEFAULT_STAFFID);
        log.setRequestTime(logRequestTime);
        log.setRequestMsg(requestMsg);
        log.setResponseTime(responseTime);
        log.setResponseMsg(responseMsg);
        payIntfReqLogSV.addPayIntfReqLog(log);
        LogUtil.info(module, "----------*HongpayPlatform getCheckInfo resultMap:*----------"+PayHelper.toJsonStr(resultMap));
        return resultMap;
    }
    @Override
    public Map<String, String> refundCallback(Map<String, String> resultData) throws Exception {
        LogUtil.info(module, "鸿支付退款回调开始" + PayHelper.toJsonStr(resultData));
        /*******************************
         * 1.获取通知报文，验证，解析
         *******************************/
        Map<String, String> result = new HashMap<String, String>();
        PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
        Timestamp requestTime = DateUtil.getSysDate();
        String orderId = "";
        String payNotifyXml = "";
        String tradeNo = "";
        long staffId = Common.DEFAULT_STAFFID;
        Timestamp responseTime = requestTime;
        long shopId = 0l;
        try {
            PayWay payWayBean = getPayWayBean(payWay);

            // 初始化解析报文实例
            String cerFileId = payWayBean.getPayVerifyCert();// "E:/hongpay/aipay_public.cer";//
            // 公钥文件，请以自己的路径为准
            String cerFile = PayHelper.downloadFileAndGetLocalPath(cerFileId);
            ResMsgAnalyze analyze = new ResMsgAnalyze(cerFile);
            // 鸿支付台通知报文
            payNotifyXml = resultData.get(PayStatus.FROM_AIP_HONGPAY_NOTIFY_KEY);
            // 验证并解析通知报文
            RefundResVo refundResVo = analyze.verifyRefundNotifyResMsg(payNotifyXml);
//            try {
            staffId =  Common.DEFAULT_STAFFID;
//            } catch (Exception e) {
//
//            }
            if(StringUtil.isEmpty(staffId)){
                staffId = Common.DEFAULT_STAFFID;
            }
            if (refundResVo != null) {
                // 成功接收到支付结果通知
                if (SUCC.equals(refundResVo.getRefundCode())) {
                    // 支付成功
                    PayRefundSuccInfo payRefundSuccInfo = new PayRefundSuccInfo();
                    payRefundSuccInfo.setBackId(Long.parseLong(refundResVo.getRefundRemark()));
                    //singlePayResVo.getPartnerId() 有可能是合并支付的订单号，所以要根据backId查出实际的订单号
                    payRefundSuccInfo.setOrderId(refundResVo.getPartnerId());
                    if(payRefundSuccInfo.getBackId() != null){
                        OrdBackApply ordBackApply = this.ordBackApplySV.queryOrdBackApplyByBackId(payRefundSuccInfo.getBackId());
                        if(ordBackApply != null){
                            payRefundSuccInfo.setOrderId(ordBackApply.getOrderId());
                        }
                    }

                    payRefundSuccInfo.setPayWay(payWay);
                    payRefundSuccInfo.setStaffId(staffId);
                    payRefundSuccInfo.setRefundTransNo(refundResVo.getPartnerTradeNo());
                    payRefundSuccInfo.setPayment(refundResVo.getRefundAmount());
//                payRefundSuccInfo.setPayeeName(payeeName);
//                payRefundSuccInfo.setPayeeAcct(payeeAcct);
                    if("00".equals(refundResVo.getRefundCode())){
                        payRefundSuccInfo.setFlag(PayStatus.PAY_REFUND_RESULT_STATUS_2);
                        String refundReturnUrl = payWayBean.getRefundReturnUrl();
                        if(StringUtil.isNotBlank(refundReturnUrl)){
                            refundReturnUrl += "?backId=" + payRefundSuccInfo.getBackId(); 
                        }
                        result.put(REFUNDRETURNURL, refundReturnUrl);
                    }else{
                        payRefundSuccInfo.setFlag(PayStatus.PAY_REFUND_RESULT_STATUS_3);
                    }
                    saveRefundCallback(payRefundSuccInfo);
                    result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.SUCCESS);
                    result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "success");
                }

            } else {
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "验证签名失败");
            }
            responseTime = DateUtil.getSysDate();
            if(refundResVo != null){
                orderId=refundResVo.getPartnerTradeNo();
                try{
                    staffId= Common.DEFAULT_STAFFID;
                }catch(Exception e){

                }
                tradeNo=refundResVo.getPartnerTradeNo();
            }
        } catch (Exception e) {
            LogUtil.error(module, "鸿支付回调异常" + PayHelper.toJsonStr(result),e);
            result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
            result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "鸿支付回调异常");
        }
        log.setPayWay(payWay);
        log.setOrderId(orderId);
        log.setStaffId(staffId);
        log.setPaywayRequestNo(tradeNo);
        log.setRequestTime(requestTime);
        log.setRequestMsg("AIPAYTRADE");
        log.setResponseTime(responseTime);
        log.setResponseMsg(JSON.json(result));
        addPayResponseLog(log);
        LogUtil.info(module, "订单"+orderId+"鸿支付回调结束" + PayHelper.toJsonStr(result));
        return result;
    }
}
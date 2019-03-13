package com.zengshi.ecp.order.service.busi.impl.pay;

import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.dto.pay.*;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.AlipayAuditRequest;
import com.zengshi.ecp.order.service.busi.impl.pay.audit.AlipayAuditResponse;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayResultSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAlipaySV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditAutoLogSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alipay extends DefaultPayWay {
    
    public static final String module = Alipay.class.getName();

    public final static String SUCC_FINISHED = "TRADE_FINISHED";
    
    public final static String SUCC_SUCCESS = "TRADE_SUCCESS";
    
    public final static String AUDITPAGESIZE = "5000";
    
    public final static String REFUND_SUCCESS = "SUCCESS";
    
    private final static String REFUNDRETURNURL = "refundReturnUrl";

    public final static String payWay = PayWayEnum.getPayWayByImplClass(Alipay.class);
    
    @Resource
    private IPayShopCfgSV payShopCfgSV;
    
    @Resource
    private IOrderQueryRSV orderQueryRSV;
    
    @Resource
    private IAuditAutoLogSV auditAutoLogSV;
    
    @Resource
    private IAuditAlipaySV auditAlipaySV;
    
    @Resource
    private IPayResultSV payResultSV;

    @Resource
    private IOrdPayRelRSV iOrdPayRelSV;

    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

    @Override
    public PayRequestData requestPayment(PaymentRequest request, Map<String, String> extendProps)
            throws Exception {
        LogUtil.info(module, "----------*Alipay requestPayment*---没有乱码-------");
        PayParamVO vo = buildPayParamVO(request.getOrderId(), payWay);
        PayHelper.validatePayParams(vo);
        String partner = vo.getPayShopCfg().getMercCode();
        String _input_charset = "UTF-8";
        String sign_type = "MD5";
        String sign = "";
        String notify_url = vo.getPayWay().getPayNotifyUrl();
        String return_url = vo.getPayWay().getPayReturnUrl();
        String out_trade_no = vo.getOrderId();
        String subject = "";// 商品名称
        String payment_type = "1";// 支付类型 ,1 商品购买, 4 捐赠, 47 电子卡券
        String total_fee = PayHelper.fen2Yuan(Long.toString(vo.getPayment())) + "";
        String seller_id = vo.getPayShopCfg().getMercCode();// 卖家支付宝账号对应的支付宝唯 一用户号。 以 2088 开头的纯
        String extra_common_param = Long.toString(vo.getStaffId());                                             // 16 位数字。
        // 2、订单明细
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setJoinOrderid(request.getOrderId());
        List<ROrdPayRelResp> list = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        StringBuffer sb = new StringBuffer();
        if(list != null && list.size() >= 1){
            for(ROrdPayRelResp resp : list){
                List<OrdSub> OrdSubList = getordSubByOrderId(resp.getOrderId());
                if (OrdSubList!=null&&OrdSubList.size()>0) {
                    for (OrdSub ordSub : OrdSubList) {
                        if (sb.length()>128) {
                            break;
                        } else {
                            sb.append(ordSub.getGdsName()).append("*").append(ordSub.getOrderAmount()).append(",");
                        }
                    }
                    subject = sb.toString().substring(0, sb.length()-1);
                }
            }
        }
        if(subject!=null&&subject.length()>100){
            subject = subject.substring(0, 100);
        }
        
        AlipayRequest r = new AlipayRequest();
        r.setPartner(partner);
        r.set_input_charset(_input_charset);
        r.setSign_type(sign_type);
        r.setNotify_url(notify_url);
        r.setReturn_url(return_url);
        r.setOut_trade_no(out_trade_no);
        r.setSubject(subject);
        r.setPayment_type(payment_type);
        r.setTotal_fee(total_fee);
        r.setSeller_id(seller_id);
        r.setExtra_common_param(extra_common_param);
        Map<String, String> map = AlipayRequest.paraFilter(r.toMap());
        String data = AlipayRequest.createLinkString(map);
        sign = r.buildSign(new String[] { data, vo.getPayShopCfg().getKeyName() });
        r.setSign(sign);
        PayRequestData pr = new PayRequestData();
        pr.setFormData(r.toMap());
        String actionUrl = vo.getPayWay().getPayActionUrl();
        pr.setActionUrl(actionUrl);
        String appActionUrl=vo.getPayWay().getPayAppActionUrl();
        pr.setAppActionUrl(appActionUrl);
        String charset = vo.getPayWay().getCharSet();
        pr.setCharset(charset);
        pr.setMethod(PayRequestData.METHOD_GET);
        pr.setCerPassword(vo.getPayShopCfg().getCerPassword());
        String requestStr = formatPayRequest(r);

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
        log.setRequestTime(DateUtil.getSysDate());
        log.setRequestMsg(requestStr);
        addPayLog(log);
        return pr;
    }

    @Override
    public Map<String, String> paymentCallback(Map<String, String> responseResultMap)
            throws Exception {
        LogUtil.info(module, "支付宝支付回调开始" + PayHelper.toJsonStr(responseResultMap));

        Map<String, String> result = new HashMap<String, String>();
        PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
        Timestamp requestTime = DateUtil.getSysDate();
        String payNotifyMSG = PayHelper.toJsonStr(responseResultMap);
        String orderId = "";
        String tradeNo = "";
        long staffId = Common.DEFAULT_STAFFID;
        Timestamp responseTime = requestTime;
        // 店铺ID
        long shopId = 0l;
        try {
            //转换结果格式
            AlipayResponse response=new AlipayResponse();
            response.buildSelf(responseResultMap);
            orderId = response.getOut_trade_no();
			// 获取合并支付的开关 1-表示开启  0-表示关闭
	        BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
	        if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
	        	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
	        }else{
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
            PayWay payWayBean = getPayWayBean(payWay);
            /* OrdMain ordMain = getOrderMainBean(orderId);
             * PayShopCfg payShopCfg = getPayShopCfgBean(ordMain.getShopId(), payWay);*/
            PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
            //过滤空值、sign与sign_type参数
            Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
            //获取待签名字符串
            String preSignStr = AlipayRequest.createLinkString(sParaNew);
            String[] signParams = new String[]{preSignStr,payShopCfg.getKeyName(),payWayBean.getPayVerifyCert()};
            try {
                staffId = Long.parseLong(response.getExtra_common_param());
            } catch (Exception e) {

            }
            if(StringUtil.isEmpty(staffId)){
            	staffId = Common.DEFAULT_STAFFID;
            }
            orderId = response.getOut_trade_no();
            tradeNo = response.getTrade_no();
            String responseTxt = "true";
            //访问支付宝网站查询签名来源是否正确
            try{
                Map<String, String> paraMap = new HashMap<String, String>();
                paraMap.put("service", "notify_verify");
                paraMap.put("partner", response.getSeller_id());
                paraMap.put("notify_id", response.getNotify_id());
                Map<String,String> map = orderQueryRSV.queryPaymentResult(payWay, payWayBean.getCharSet(), payWayBean.getPayActionUrl(), paraMap);
                responseTxt = map.get(PayStatus.FROM_AIP_PaymentResult);
            }catch(Exception e){
                LogUtil.error(module, "支付宝验签查询aip接口异常", e);
            }
            if ("true".equals(responseTxt)&&response.verifySign(signParams, response.getSign(),response.getSign_type())) {
              //请在这里加上商户的业务逻辑程序代码

                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                
                if("TRADE_FINISHED".equals(response.getTrade_status())){
                    //注意：
                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    //怎么处理看需要
                    result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.SUCCESS);
                    result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "success");
                } else if ("TRADE_SUCCESS".equals(response.getTrade_status())){
                    //注意：
                    //付款完成后，支付宝系统发送该交易状态通知
                    PaySuccInfo paySuccInfo = new PaySuccInfo();
                    paySuccInfo.setOrderId(response.getOut_trade_no());
                    paySuccInfo.setPayWay(payWay);
                    paySuccInfo.setStaffId(staffId);
                    paySuccInfo.setPayTransNo(response.getTrade_no());
                    paySuccInfo.setPayment(PayHelper.yuan2Fen(response.getTotal_fee()));
                     paySuccInfo.setPayeeAcct(response.getBuyer_id());
                     paySuccInfo.setPayeeName(response.getBuyer_email());
                    // paySuccInfo.setPayWayName(payWayName);
                    paySuccInfo.setMerchAcct(response.getSeller_id());
//                     paySuccInfo.setShopId(shopId);
                    paySuccInfo.setPayType(PayStatus.PAY_TYPE_01);
                    saveHandlePaySucc(paySuccInfo);
                    
                    result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.SUCCESS);
                    result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "success");
                }else if ("WAIT_BUYER_PAY".equals(response.getTrade_status())){
                    result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                    result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "交易状态不正确！");               	
                }
                	
                
            } else {
            	if(!"true".equals(responseTxt)){
                    result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                    result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "支付宝Notify响应超时");	           		
            	}else{
                    result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                    result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "验签失败！");	
            	}
            }
        } catch (Exception e) {
            LogUtil.error(module, "支付宝支付回调异常" + PayHelper.toJsonStr(result),e);
            result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
            result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "支付宝支付回调异常！");
        }
        responseTime = DateUtil.getSysDate();
        log.setPayWay(payWay);
        log.setOrderId(orderId);
        log.setStaffId(staffId);
        log.setPaywayRequestNo(tradeNo);
        log.setRequestTime(requestTime);
        log.setRequestMsg(payNotifyMSG);
        log.setResponseTime(responseTime);
        log.setResponseMsg(JSON.json(result));
        addPayResponseLog(log);
        LogUtil.info(module, "订单"+orderId+"支付宝支付回调结束" + PayHelper.toJsonStr(result));
        return result;
    }
    
    @Override
    public RPayRefundResponse refund(RPayRefundRequest request, Map<String, String> extendProps)
            throws Exception {
        LogUtil.info(module, "----------*Alipay refund orderId*----------"+request.getOrderId()+"----------refundAmount----------"+request.getRefundAmount()+"----------extendProps----------"+extendProps.toString());
        PayWay payWayBean = getPayWayBean(payWay);
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
        if(payShopCfg == null){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310009);
        }
        if(ordMainBean.getRealMoney().longValue()<request.getRefundAmount().longValue()){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310016);
        }
        //获取支付流水
        ROrdPayRelReq rOrdPayRelReq = new ROrdPayRelReq();
        rOrdPayRelReq.setOrderId(request.getOrderId());
        rOrdPayRelReq.setPayFlag("1");
        List<ROrdPayRelResp> rOrdPayRelResp = iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
        if(CollectionUtils.isEmpty(rOrdPayRelResp)){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310017);
        }
        String payId = rOrdPayRelResp.get(0).getJoinOrderid();

        List<PayResult> payResultList = payResultSV.getPayResultByOrderId(payId);
        if(payResultList.size()<1){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310017);
        }

        String partner = payShopCfg.getMercCode();
        String _input_charset = "UTF-8";
        String sign_type = "MD5";
        String sign;
        String notify_url = payWayBean.getPayRefundNotifyUrl()+"?ecp_staffId="+request.getStaff().getId();
        String return_url = payWayBean.getPayReturnUrl();
        String seller_email = payShopCfg.getPayeeAccount();
        String seller_user_id = payShopCfg.getMercCode();
        String refund_date = DateUtil.getDateString("yyyy-MM-dd HH:mm:ss");
        String batch_no =DateUtil.getDateString("yyyyMMdd")+request.getBackId().longValue();//不能超过8+24位
        String batch_num = "1";
//        String detail_data = payId+"^"+PayHelper.fen2Yuan(Long.toString(request.getRefundAmount())) + "^"+"协商退款";//原付款支付宝交易号^退款总金额^退款理由
        String detail_data = payResultList.get(0).getPayTransNo()+"^"+PayHelper.fen2Yuan(Long.toString(request.getRefundAmount())) + "^"+"协商退款";//原付款支付宝交易号^退款总金额^退款理由

        AlipayRefundRequest alipayRefundRequest = new AlipayRefundRequest();
        alipayRefundRequest.setPartner(partner);
        alipayRefundRequest.set_input_charset(_input_charset);
        alipayRefundRequest.setSign_type(sign_type);
        alipayRefundRequest.setNotify_url(notify_url);
        alipayRefundRequest.setSeller_email(seller_email);
        alipayRefundRequest.setSeller_user_id(seller_user_id);
        alipayRefundRequest.setRefund_date(refund_date);
        alipayRefundRequest.setBatch_no(batch_no);
        alipayRefundRequest.setBatch_num(batch_num);
        alipayRefundRequest.setDetail_data(detail_data);

        Map<String, String> signMap = AlipayRequest.paraFilter(alipayRefundRequest.toMap());
        String data = AlipayRequest.createLinkString(signMap);
        sign = alipayRefundRequest.buildSign(new String[] { data, payShopCfg.getKeyName() });
        alipayRefundRequest.setSign(sign);
        Timestamp requestTime = DateUtil.getSysDate();
        String requestMsg = PayHelper.toJsonStr(alipayRefundRequest.toMap());
        PayIntfReqLogDTO log = new PayIntfReqLogDTO();
        log.setPayWay(payWay);
        log.setOrderId(request.getOrderId());
        log.setTypeCode(PayStatus.PAY_LOG_TYPE_CODE_02);
        log.setStaffId(request.getStaff().getId());
        log.setRequestTime(requestTime);
        log.setRequestMsg(requestMsg);
        payIntfReqLogSV.addPayIntfReqLog(log);
        
        //插入退款结果表，如果已有就更新即可
        PayRefundResult payRefundResult = payRefundResultSV.getPayRefundResultByPaywayBackId(payWay,request.getBackId());
        if(payRefundResult==null){
            payRefundResult = new PayRefundResult();
            payRefundResult.setBackId(request.getBackId());
            payRefundResult.setOrderId(request.getOrderId());
            payRefundResult.setPayWay(payWay);
            payRefundResult.setRefundTransNo(batch_no);
            payRefundResult.setRefundAmount(request.getRefundAmount());
            payRefundResult.setRefundStatus(PayStatus.PAY_REFUND_RESULT_STATUS_1);
            payRefundResult.setRefundDesc("线上退款");
            payRefundResult.setRefundTime(DateUtil.getSysDate());
            payRefundResultSV.addPayRefundResult(payRefundResult);
        }else{
            payRefundResult.setRefundTime(DateUtil.getSysDate());
            payRefundResultSV.updateRefund(payRefundResult);
        }
        
        PayRequestData pr = new PayRequestData();
        pr.setFormData(alipayRefundRequest.toMap());
        String actionUrl = payWayBean.getPayRefundUrl();
        pr.setActionUrl(actionUrl);
        String charset = payWayBean.getCharSet();
        pr.setCharset(charset);
        RPayRefundResponse response = new RPayRefundResponse();
        response.setPayRequestData(pr);
        response.setRefundMethod(PayStatus.PAY_REFUND_METHOD_02);
        pr.setMethod(PayRequestData.METHOD_GET);
//        LogUtil.error(module, "----------*Alipay refund end*----------resultMap:"+PayHelper.toJsonStr(resultMap));
        return response;
    }
    
    @Override
    public Map<String, String> refundCallback(Map<String, String> responseResultMap) throws Exception {
        LogUtil.info(module, "支付宝支付回调开始" + PayHelper.toJsonStr(responseResultMap));

        Map<String, String> result = new HashMap<String, String>();
        PayIntfNotifyLogDTO log = new PayIntfNotifyLogDTO();
        Timestamp requestTime = DateUtil.getSysDate();
        String payNotifyMSG = PayHelper.toJsonStr(responseResultMap);
        String orderId = "";
        String refundTradeNo = "";
        Long staffId = Common.DEFAULT_STAFFID;
        Timestamp responseTime = requestTime;
        try {
            //转换结果格式
            AlipayRefundResponse response = new AlipayRefundResponse();
            response.buildSelf(responseResultMap);
            refundTradeNo = response.getBatch_no();
            if(response.getEcp_staffId()!=null){
                staffId = response.getEcp_staffId();
            }
            String backIdStr = refundTradeNo.substring(8);
            Long backId = Long.valueOf(backIdStr);
            PayRefundResult payRefundResult = payRefundResultSV.getPayRefundResultByPaywayBackId(payWay,backId);
            if(payRefundResult==null){
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310019);
            }

            OrdBackApply ordBackApply = this.ordBackApplySV.queryOrdBackApplyByBackId(backId);
//            orderId = payRefundResult.getOrderId();
            orderId = ordBackApply.getOrderId();

            OrdMain ordMain = getOrderMainBean(orderId);
            PayWay payWayBean = getPayWayBean(payWay);
            //过滤自定义的ecp_staffId参数
            responseResultMap.put("ecp_staffId", "");
            //过滤空值、sign与sign_type参数
            Map<String, String> sParaNew = AlipayRequest.paraFilter(responseResultMap);
            //获取待签名字符串
            String preSignStr = AlipayRequest.createLinkString(sParaNew);
            long shopId = 0l;
            // 获取合并支付的开关 1-表示开启  0-表示关闭
            BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(OrdConstants.OrdPayRel.SWITCH_PAY_MERGE);
            if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
            	shopId = OrdConstants.OrdPayRel.PAY_WAY_MERGE_CFG_ID;
            }else{
            	shopId = ordMain.getShopId();
            }
            PayShopCfg payShopCfg = getPayShopCfgBean(shopId, payWay);
            String[] signParams=new String[]{preSignStr,payShopCfg.getKeyName()};
            String responseTxt = "true";
            //访问支付宝网站查询签名来源是否正确
            try{
                Map<String, String> paraMap = new HashMap<String, String>();
                paraMap.put("service", "notify_verify");
                paraMap.put("partner", payShopCfg.getMercCode());
                paraMap.put("notify_id", response.getNotify_id());
                Map<String,String> map = orderQueryRSV.queryPaymentResult(payWay, payWayBean.getCharSet(), payWayBean.getPayActionUrl(), paraMap);
                responseTxt = map.get(PayStatus.FROM_AIP_PaymentResult);
            }catch(Exception e){
                LogUtil.error(module, "支付宝验签查询aip接口异常", e);
            }
            if ("true".equals(responseTxt)&&response.verifySign(signParams, response.getSign())) {
                //请在这里加上商户的业务逻辑程序代码
                PayRefundSuccInfo payRefundSuccInfo = new PayRefundSuccInfo();
                payRefundSuccInfo.setBackId(payRefundResult.getBackId());
                payRefundSuccInfo.setOrderId(orderId);
                payRefundSuccInfo.setPayWay(payWay);
                payRefundSuccInfo.setStaffId(staffId);
                payRefundSuccInfo.setRefundTransNo(refundTradeNo);
                payRefundSuccInfo.setPayment(PayHelper.yuan2Fen(response.getResult_details_refudnAmount()));
//                payRefundSuccInfo.setPayeeName(payeeName);
//                payRefundSuccInfo.setPayeeAcct(payeeAcct);
                if(REFUND_SUCCESS.equals(response.getResult_details_refundSatuts())){
                    payRefundSuccInfo.setFlag(PayStatus.PAY_REFUND_RESULT_STATUS_2);
                    String refundReturnUrl = payWayBean.getRefundReturnUrl();
                    if(StringUtil.isNotBlank(refundReturnUrl)){
                        refundReturnUrl += "?backId=" + payRefundResult.getBackId(); 
                    }
                    result.put(REFUNDRETURNURL, refundReturnUrl);
                }else{
                    payRefundSuccInfo.setFlag(PayStatus.PAY_REFUND_RESULT_STATUS_3);
                }
                saveRefundCallback(payRefundSuccInfo);
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.SUCCESS);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "success");
            } else {
                result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
                result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "验签失败！");
            }
        } catch (Exception e) {
            LogUtil.error(module, "支付宝退款回调异常" + PayHelper.toJsonStr(result),e);
            result.put(PayStatus.TO_AIP_PARAM_FLAG, PayStatus.FAILURE);
            result.put(PayStatus.TO_AIP_PARAM_MESSAGE, "支付宝退款回调异常！");
        }
        responseTime = DateUtil.getSysDate();
        log.setPayWay(payWay);
        log.setOrderId(orderId);
        log.setStaffId(staffId);
        log.setPaywayRequestNo(refundTradeNo);
        log.setTypeCode(PayStatus.PAY_NOTIFYLOG_TYPE_CODE_02);
        log.setRequestTime(requestTime);
        log.setRequestMsg(payNotifyMSG);
        log.setResponseTime(responseTime);
        log.setResponseMsg(JSON.json(result));
        payIntfNotifyLogSV.addPayNotifyLog(log);
        LogUtil.info(module, "订单"+orderId+"支付宝退款回调结束" + PayHelper.toJsonStr(result));
        return result;
    }

    @Override
    public OrderPayStatusVO parsePayStatus(Map<String, String> params) throws Exception {
        LogUtil.error(module, "req=" + params);
        OrderPayStatusVO vo = new OrderPayStatusVO();
        vo.setOrderId(params.get("out_trade_no"));
        vo.setFlag(SUCC_FINISHED.equals(params.get("trade_status"))||SUCC_SUCCESS.equals(params.get("trade_status")) ? OrderPayStatusVO.SUCCESS
                : OrderPayStatusVO.FAILURE);
        vo.setPayTime(new Timestamp(System.currentTimeMillis()));
        return vo;
    }
    
    /**
     * 
     * TODO 根据定时任务web传入的参数QsDates判断要对账的时间，如果没有传入，则处理前一天的对账信息，如果有，就循环处理每一天的对账信息. 
     * 逻辑是循环获取店铺支付通道，每个店铺有一个对账文件，分别处理之
     * @see com.zengshi.ecp.order.service.busi.impl.pay.DefaultPayWay#check(java.util.Map)
     */
    @Override
    public Map<String, String> check(Map<String, String> resultData) throws Exception {
        LogUtil.info(module, "----------*Alipay check start*----------"+PayHelper.toJsonStr(resultData));
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
        LogUtil.info(module, "----------*Alipay check end*----------"+PayHelper.toJsonStr(resultData));
        return null;
    }
    /**
     * 
     * dealCheck:根据日期处理对账，如果对账文件有多页，则循环获取当天所有的对账文件都成功时才进行保存操作. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payShopCfg
     * @param qsDate
     * @throws Exception 
     * @since JDK 1.6
     */
    public void dealCheck(PayShopCfg payShopCfg, Timestamp qsDate) throws Exception{
        try{
            LogUtil.info(module, "----------*Alipay dealCheck start*----------qsDate:"+qsDate);
            //已经对账了的不再进行处理
            AuditDailySum auditDailySumReq = new AuditDailySum();
            auditDailySumReq.setCheckDate(qsDate);
            auditDailySumReq.setPayWay(payWay);
            auditDailySumReq.setShopId(Common.DEFAULT_STAFFID);
            AuditDailySum auditDailySumResp = auditDailySumSV.getAuditDailySum(auditDailySumReq);
            if(auditDailySumResp!=null){
                LogUtil.error(module, "支付宝对账");
                //记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(payWay);
                auditAutoLog.setExecMethods("dealCheck");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark("对账已经处理，不再进行");
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
                return;
            }
            
            AlipayAuditResponse totleAuditResponse = new AlipayAuditResponse();
            int page = 1;
            //是否还有下一页
            boolean hasNextPage = true;
            //是否多次请求都没有异常
            boolean isNotError = true;
            //分页查询当天所以对账文件
            String remark ="";
            while(hasNextPage){
                Map<String,String> resultMap = checkbyPage(payShopCfg,qsDate,page);
                String flag = resultMap.get(PayStatus.FROM_AIP_PARAM_FLAG);
                if(PayStatus.FROM_AIP_SUCCESS.equals(flag)){
                    AlipayAuditResponse auditResponse = new AlipayAuditResponse();
                    auditResponse.buildSelf(resultMap);
                    //解析成功后报文显示获取对账文件成功
                    if(auditResponse.getIs_succes()){
                        String[] signParams=new String[]{payShopCfg.getKeyName()};
                        //验证签名
                        if(auditResponse.verifySign(signParams, auditResponse.getSign())){
                            //记录对账文件
                            String responseStr = resultMap.get(PayStatus.FROM_AIP_PaymentResult);
                            if(totleAuditResponse.getXmlStr()==null){
                                totleAuditResponse.setXmlStr(responseStr);
                            }else{
                                responseStr = totleAuditResponse.getXmlStr()+responseStr;
                                totleAuditResponse.setXmlStr(responseStr);
                            }
                            //记录对账明细，第一次请求时直接赋值
                            if(totleAuditResponse.getResponse().getAccount_log_list()==null){
                                totleAuditResponse.getResponse().setAccount_log_list(auditResponse.getResponse().getAccount_log_list());
                            }else{
                                totleAuditResponse.getResponse().getAccount_log_list().addAll(auditResponse.getResponse().getAccount_log_list());
                            }
                            if(auditResponse.getResponse().getHas_next_page()){
                                page++;
                            }else{
                                hasNextPage = false;
                            }
                        }else{
                            //签名失败异常
                            hasNextPage = false;
                            isNotError = false;
                            remark = "第"+page+"页签名失败";
                        }
                    }else{
                        //报文返回失败状态异常
                        hasNextPage = false;
                        isNotError = false;
                        remark = "第"+page+"页报文返回失败状态";
                    }
                    
                }else{
                    //aip请求失败异常
                    hasNextPage = false;
                    isNotError = false;
                    remark = "第"+page+"页aip请求失败";
                }
                
            }
            //多次请求都没有异常才进行下一步
            if(isNotError){
                auditAlipaySV.saveAuditInfo(payShopCfg, totleAuditResponse, qsDate);
            }else{
                //记录日志
                AuditAutoLog auditAutoLog = new AuditAutoLog();
                auditAutoLog.setPayWay(payWay);
                auditAutoLog.setExecMethods("dealCheck");
                auditAutoLog.setExecuteTime(DateUtil.getSysDate());
                auditAutoLog.setRemark("分页查询当天所以对账文件没有全部成功："+remark);
                auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
            }
            
        }catch(Exception e){
            LogUtil.error(module, "支付宝对账失败", e);
            //记录日志
            AuditAutoLog auditAutoLog = new AuditAutoLog();
            auditAutoLog.setPayWay(payWay);
            auditAutoLog.setExecMethods("dealCheck");
            auditAutoLog.setExecuteTime(DateUtil.getSysDate());
            auditAutoLog.setRemark(PayHelper.getExceptionStackTrace(e));
            auditAutoLogSV.saveAuditAutoLog(auditAutoLog);
        }
        
        LogUtil.info(module, "----------*Alipay dealCheck end*----------qsDate:"+qsDate);
    }
    
    /**
     * 
     * checkbyPage:支付宝特有的分页对账查询. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author weijq
     * @param payShopCfg
     * @param qsDate
     * @param page
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private Map<String,String> checkbyPage(PayShopCfg payShopCfg, Timestamp qsDate, int page) throws Exception{
        LogUtil.error(module, "----------*Alipay checkbyPage start*----------page:"+page);
        String partner = payShopCfg.getMercCode();
        String _input_charset = "UTF-8";
        String sign_type = "MD5";
        String sign = "";
        String page_no = String.valueOf(page);//页号 必须是正整数
        String gmt_start_time = DateUtil.getDateString(DateUtil.getTheDayFirstSecond(qsDate), "yyyy-MM-dd HH:mm:ss");//账务查询开始时间  格式为：yyyy-MM-dd HH:mm:ss
        String gmt_end_time = DateUtil.getDateString(DateUtil.getTheDayLastSecond(qsDate), "yyyy-MM-dd HH:mm:ss");//账务查询结束时间   格式为：yyyy-MM-dd HH:mm:ss
        DateUtil.getTheDayFirstSecond(qsDate);
        String logon_id = payShopCfg.getPayeeAccount();
//        String iw_account_log_id ;
//        String trade_no;
//        String merchant_out_order_no;
//        String deposit_bank_no;
        String page_size = AUDITPAGESIZE;
//        String trans_code ="6001,5103";//3011 转账（含红包、集分宝等）, 3012 收费, 4003 充值 ,5004 提现, 5103 退票  ,6001 在线支付  
        
        AlipayAuditRequest request = new AlipayAuditRequest();
        request.setPartner(partner);
        request.set_input_charset(_input_charset);
        request.setSign_type(sign_type);
        request.setPage_no(page_no);
        request.setGmt_start_time(gmt_start_time);
        request.setGmt_end_time(gmt_end_time);
        request.setLogon_id(logon_id);
        request.setPage_size(page_size);
//        request.setTrans_code(trans_code);
        Map<String, String> map = AlipayRequest.paraFilter(request.toMap());
        String data = AlipayRequest.createLinkString(map);
        sign = request.buildSign(new String[] { data, payShopCfg.getKeyName() });
        request.setSign(sign);
        Map<String,String> requestMap = request.toMap();
        Map<String,String> resultMap;
        PayWay payWaybean = getPayWayBean(payWay);
        Timestamp requestTime = DateUtil.getSysDate();
        Timestamp responseTime = requestTime;
        String requestMsg = PayHelper.toJsonStr(requestMap);
        String responseMsg;
        String url = payWaybean.getPayAuditUrl()+"?_input_charset="+_input_charset;
        try{
            resultMap = orderQueryRSV.queryPaymentResult(payWay, payWaybean.getCharSet(), url, requestMap);
        }catch (Exception e) {
            LogUtil.error(module, "支付宝对账aip请求失败", e);
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
        log.setRequestTime(requestTime);
        log.setRequestMsg(requestMsg);
        log.setResponseTime(responseTime);
        log.setResponseMsg(responseMsg);
        payIntfReqLogSV.addPayIntfReqLog(log);
        LogUtil.error(module, "----------*Alipay checkbyPage end*----------resultMap:"+PayHelper.toJsonStr(resultMap));
        return resultMap;
    }
}

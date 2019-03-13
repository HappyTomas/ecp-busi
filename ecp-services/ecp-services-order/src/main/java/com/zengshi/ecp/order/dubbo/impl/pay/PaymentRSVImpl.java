package com.zengshi.ecp.order.dubbo.impl.pay;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.order.dubbo.dto.pay.BindRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayServiceMsgCode;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.service.busi.impl.pay.PayWayEnum;
import com.zengshi.ecp.order.service.busi.impl.pay.PayWayFactory;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWay;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.exception.GenericException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日上午10:44:55 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public class PaymentRSVImpl implements IPaymentRSV {
    public static final String MODULE = PaymentRSVImpl.class.getName();

    @Override
    public PayRequestData requestPayment(PaymentRequest request, Map<String, String> extendProps)
            throws BusinessException {
        LogUtil.error(MODULE, "----------*PaymentSIDSVImpl requestPayment*----------");
        PayRequestData p = null;
        try {
            if (request == null) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
            }
            if (StringUtil.isBlank(request.getOrderId())) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
            }
            if (StringUtil.isBlank(request.getPayWay())) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
            }
            IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(request.getPayWay()));
            if (extendProps == null) {
                extendProps = new HashMap<String, String>();
            }
            p = pw.requestPayment(request, extendProps);
            if (p == null) {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310004);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "----------*PaymentSIDSVImpl requestPayment error*----------", e);
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                new BusinessException(PayServiceMsgCode.PAY_SERVER_310006);
            }
        }
        LogUtil.error(MODULE, "ret=" + p);
        return p;
    }

    @Override
    public Map<String, String> paymentCallback(String payWay, Map<String, String> responseResultMap)
            throws BusinessException {
        Map<String, String> result = null;
        try {
            LogUtil.error(MODULE, "req={payWay=" + payWay + ",responseResultMap="
                    + responseResultMap + "}");
            if (StringUtil.isBlank(payWay)) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
            }
            IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
            result = pw.paymentCallback(responseResultMap);
        } catch (Exception e) {
            LogUtil.error(MODULE, "----------*PaymentSIDSVImpl paymentCallback error*----------", e);
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310007);
            }
        }
        LogUtil.info(MODULE, "ret=" + result);
        return result;
    }

    //
    // @Override
    // public Map<String,String> queryPaymentResult(String payWay, String orderId,String
    // provinceCode) throws BusinessException,GenericException{
    // Map<String, String> result = null;
    // try {
    // Debug.logError("req={payWay="+payWay+",orderId="+orderId+",provinceCode="+provinceCode+"}",module);
    // if(StringUtil.isBlank(orderId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "订单ID");
    // }
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // if(StringUtil.isEmpty(provinceCode)){
    // provinceCode = PayHelper.getProvCodeFromOrderId(orderId);
    // }
    // result = pw.queryPaymentResult(orderId,provinceCode);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl queryPaymentResult error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // Debug.log("ret="+result,module);
    // return result;
    //
    // }
    //
    // @Override
    // public Map<String, String> refundCallback(String payWay,
    // Map<String, String> resultData)throws BusinessException,GenericException {
    // Map<String, String> result = null;
    // try {
    // Debug.logError("req={payWay="+payWay+",responseResultMap="+resultData+"}",module);
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // result = pw.refundCallback(resultData);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl refundCallback error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // Debug.log("ret="+result,module);
    // return result;
    // }
    //
    // @Override
    // public BindRequestData bindBankCard(String payWay, String chnlId,
    // String provinceCode,Map<String,String> extendProps) throws BusinessException,
    // GenericException {
    // BindRequestData p = null;
    // try {
    // if(StringUtil.isBlank(chnlId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "渠道编码");
    // }
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // if(extendProps == null){
    // extendProps = new HashMap<String, String>();
    // }
    // IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // p = ((IPayPlatform) pw).bindBankCard(chnlId, provinceCode,extendProps);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl bindBankCard error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // Debug.log("ret=" + p, module);
    // return p;
    //
    // }
    //
    // @Override
    // public Map<String,String> bindBankCardCallback(String payWay, Map<String ,String>
    // responseMap)throws BusinessException,GenericException {
    // Map<String, String> result = null;
    // try {
    // Debug.logError("req={payWay="+payWay+",responseMap="+responseMap+"}",module);
    // IPayPlatform pw = (IPayPlatform)PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // result = pw.bindBankCardCallback(responseMap);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl bindBankCardCallback error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // Debug.log("ret="+result,module);
    // return result;
    // }
    //
    // @Override
    // public void shiftBindBankCard(String payWay, String oldChnlId,
    // String newChnlId,String provinceCode) throws BusinessException,GenericException{
    // try {
    // Debug.logError("req={payWay="+payWay+",oldChnlId="+oldChnlId+",newChnlId="+newChnlId+",provinceCode="+provinceCode+"}",module);
    // if(StringUtil.isBlank(oldChnlId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "渠道编码不能为空");
    // }
    // if(StringUtil.isBlank(newChnlId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "渠道编码不能为空");
    // }
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码不能为空");
    // }
    // if(StringUtil.isBlank(provinceCode)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "省份编码不能为空");
    // }
    // IPayPlatform pw;
    // if(PayInfo.PAY_WOCASHIER_OFF.equals(ParamCacheUtil.queryParaCfgValue("SYS_WOCASHIER_ON","00"))){
    // IBOTPayWayValue[] payWayBeans =
    // ServiceUtil.getService(ITPayWaySV.class,SysConstants.CenterDBProvCode).getPayPlatformBeans();
    // for(IBOTPayWayValue payWayBean:payWayBeans){
    // payWay=payWayBean.getPayWay();
    // try{
    //
    // pw = (IPayPlatform)PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // }catch(Exception e){
    // throw new BusinessException("",payWay+"支付通道不是支付平台，没有绑卡转移功能");
    // }
    // pw.shiftBindBankCard(oldChnlId, newChnlId, provinceCode);
    // }
    // }else{
    // pw = (IPayPlatform)PayWayFactory.getPayWay(PayWayEnum.getByPayWay("9998"));
    // pw.shiftBindBankCard(oldChnlId, newChnlId, provinceCode);
    // }
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl shiftBindBankCard error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // }
    //
    // @Override
    // public Map<String, String> pagePaymentCallback(String payWay,
    // Map<String, String> pageResultMap) throws BusinessException,
    // GenericException {
    // Map<String, String> result = null;
    // try {
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // result = pw.pagePaymentCallback(pageResultMap);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl pagePaymentCallback error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // Debug.log("ret="+result,module);
    // return result;
    // }
    //
    // @Override
    // public OrderPayStatusVO parsePayStatus(String payWay,
    // Map<String, String> params) throws BusinessException,
    // GenericException {
    // OrderPayStatusVO result = null;
    // try {
    // Debug.logError("req={"+"payWay="+payWay+",params="+params+"}",module);
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // if(params==null || params.size() ==0 ){
    // throw new BusinessException(Special.PARAM_IS_NULL, "需要解析的参数集合不能为空");
    // }
    // IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // result = pw.parsePayStatus(params);
    // if(result == null){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付结果解析异常");
    // }
    // if(StringUtil.isEmpty(result.getOrderId())){
    // throw new BusinessException(Special.PARAM_IS_NULL, "请求中没有订单编码");
    // }
    // //统一记录日志
    // String provinceCode = PayHelper.getProvCodeFromOrderId(result.getOrderId());
    // LocaleCodeUtil.setChnlLocaleCode(provinceCode);
    // TPayResultLogVO tPayResultLogVO =new TPayResultLogVO.Builder(
    // result.getOrderId(), payWay, result.getFlag(),
    // provinceCode, PayHelper.toJsonStr(params)).build();
    // ServiceUtil.getService(ITPayResultLogSV.class, provinceCode).addBean(tPayResultLogVO);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl parsePayStatus error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }finally{
    // if(result == null){
    // result = new OrderPayStatusVO();
    // }
    // }
    // Debug.log("ret="+result,module);
    // return result;
    // }
    //
    // @Override
    // public List<PayBindInfo> getBindCardInfo(String chnlId,String provinceCode)
    // throws BusinessException, GenericException {
    // if(StringUtil.isBlank(chnlId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "渠道编码不能为空");
    // }
    // if(StringUtil.isBlank(provinceCode)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "省份编码不能为空");
    // }
    // try {
    // LocaleCodeUtil.setChnlLocaleCode(provinceCode);
    // PayBindInfo payBindInfo = new PayBindInfo();
    // payBindInfo.setChnlId(chnlId);
    // payBindInfo.setProvinceCode(provinceCode);
    // ITPayBindSV payBindSV =ServiceUtil.getService(ITPayBindSV.class, provinceCode);
    // List<PayBindInfo> bean = payBindSV.getPayBindInfoList(payBindInfo);
    // return bean;
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl getBindCardInfo error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // }
    //
    // @Override
    // public Map<String, String> delivery(String payWay, String orderId,
    // String provinceCode,Map<String,String> extendProps) throws BusinessException,
    // GenericException {
    // Map<String, String> result = null;
    // try {
    // Debug.logError("req={payWay="+payWay+",orderId="+orderId+",provinceCode="+provinceCode+"}",module);
    // if(StringUtil.isBlank(orderId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "订单ID");
    // }
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // IPayPlatform pw = (IPayPlatform) PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // if(StringUtil.isEmpty(provinceCode)){
    // provinceCode = PayHelper.getProvCodeFromOrderId(orderId);
    // }
    // result = pw.delivery(orderId, provinceCode, extendProps);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl refund error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // return result;
    // }
    //
    // @Override
    // public Map<String, String> shipments(String payWay, String orderId,
    // String provinceCode,Map<String,String> extendProps) throws BusinessException,
    // GenericException {
    // Map<String, String> result = null;
    // try {
    // Debug.logError("req={payWay="+payWay+",orderId="+orderId+",provinceCode="+provinceCode+"}",module);
    // if(StringUtil.isBlank(orderId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "订单ID");
    // }
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道编码");
    // }
    // IPayPlatform pw = (IPayPlatform) PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
    // if(StringUtil.isEmpty(provinceCode)){
    // provinceCode = PayHelper.getProvCodeFromOrderId(orderId);
    // }
    // result = pw.shipments(orderId, provinceCode, extendProps);
    // } catch (Exception e) {
    // Debug.logError(e,"----------*PaymentSIDSVImpl refund error*----------",module);
    // if (e instanceof BusinessException)
    // throw (BusinessException) e;
    // else {
    // throw new GenericException(e);
    // }
    // }
    // return result;
    // }

    @Override
    public Map<String, String> queryPaymentResult(String payWay, String orderId)
            throws BusinessException, GenericException {
        return null;
    }

    @Override
    public RPayRefundResponse refund(RPayRefundRequest request, Map<String, String> extendProps)
            throws BusinessException, GenericException {
        try {
            RPayRefundResponse result = null;
            LogUtil.info(MODULE,
                    "req={request=" + request + "extendProps" + PayHelper.toJsonStr(extendProps)
                            + "}");
            if (request == null) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
            }
            if (StringUtil.isBlank(request.getOrderId())) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
            }
            if (StringUtil.isBlank(request.getPayWay())) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
            }
            if (request.getRefundAmount() == null || request.getRefundAmount() <= 0) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300014);
            }
            LogUtil.info(MODULE, "退款请求入参1：" + JSON.toJSONString(request));
            LogUtil.info(MODULE, "退款请求入参2：" + JSON.toJSONString(extendProps));
            IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(request.getPayWay()));
            result = pw.refund(request, extendProps);

            LogUtil.info(MODULE, "退款请求出参：" + JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            LogUtil.error(MODULE, "----------*PaymentSIDSVImpl refund error*----------", e);
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310015);
            }
        }
    }

    @Override
    public Map<String, String> refundCallback(String payWay, Map<String, String> responseResultMap)
            throws BusinessException, GenericException {
        Map<String, String> result = null;
        try {
            LogUtil.error(MODULE, "req={payWay=" + payWay + ",responseResultMap="
                    + responseResultMap + "}");
            if (StringUtil.isBlank(payWay)) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
            }
            IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
            result = pw.refundCallback(responseResultMap);
        } catch (Exception e) {
            LogUtil.error(MODULE, "----------*PaymentSIDSVImpl refundCallback error*----------", e);
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310018);
            }
        }
        LogUtil.info(MODULE, "ret=" + result);
        return result;
    }

    @Override
    public BindRequestData bindBankCard(String payWay, String chnlId,
            Map<String, String> extendProps) throws BusinessException, GenericException {
        return null;
    }

    @Override
    public Map<String, String> bindBankCardCallback(String payWay, Map<String, String> responseMap)
            throws BusinessException, GenericException {
        return null;
    }

    @Override
    public void shiftBindBankCard(String payWay, String oldChnlId, String newChnlId)
            throws BusinessException, GenericException {
    }

    @Override
    public OrderPayStatusVO parsePayStatus(String payWay, Map<String, String> params)
            throws BusinessException {
        OrderPayStatusVO result = null;
        try {
            LogUtil.error(MODULE, "req={" + "payWay=" + payWay + ",params=" + params + "}");
            if (StringUtils.isBlank(payWay)) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
            }
            if (params == null || params.size() == 0) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
            }
            IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
            result = pw.parsePayStatus(params);
            if (result == null) {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310001);
            }
            if (StringUtils.isEmpty(result.getOrderId())) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300001);
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "----------*PaymentSIDSVImpl parsePayStatus error*----------", e);
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310000);
            }
        } finally {
            if (result == null) {
                result = new OrderPayStatusVO();
            }
        }
        LogUtil.info(MODULE, "ret=" + result);
        return result;
    }

    @Override
    public Map<String, String> delivery(String payWay, String orderId, String provinceCode,
            Map<String, String> extendProps) throws BusinessException, GenericException {
        return null;
    }

    @Override
    public Map<String, String> shipments(String payWay, String orderId,
            Map<String, String> extendProps) throws BusinessException, GenericException {
        return null;
    }

    @Override
    public Map<String, String> check(String payWay, Map<String, String> extendProps)
            throws BusinessException {
        Map<String, String> result = null;
        try {
            LogUtil.error(MODULE, "req={payWay=" + payWay + ",extendProps=" + extendProps + "}");
            if (StringUtil.isBlank(payWay)) {
                throw new BusinessException(PayInputMsgCode.PAY_INPUT_300002);
            }
            IPayWay pw = PayWayFactory.getPayWay(PayWayEnum.getByPayWay(payWay));
            result = pw.check(extendProps);
        } catch (Exception e) {
            LogUtil.error(MODULE, "----------*PaymentSIDSVImpl check error*----------", e);
            if (e instanceof BusinessException)
                throw (BusinessException) e;
            else {
                throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310022);
            }
        }
        LogUtil.info(MODULE, "ret=" + result);
        return result;
    }
}

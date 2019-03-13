/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImplTest.java 
 * Package Name:com.zengshi.ecp.test 
 * Date:2015-8-3下午6:51:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.pay;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.aip.dubbo.interfaces.IOrderQueryRSV;
import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.general.order.interfaces.IPayQuartzInfoRSV;
import com.zengshi.ecp.order.dao.model.AuditDailySum;
import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dubbo.dto.ROfflineReviewRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PaySuccInfo;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.PaymentRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RAuditTradeCheckTotalResponse;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.RPayRefundResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdOfflineRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IAuditTradeCheckRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.order.dubbo.util.OfflineConstants;
import com.zengshi.ecp.order.dubbo.util.PayHelper;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.Common;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayAudit;
import com.zengshi.ecp.order.dubbo.util.OrdConstants.PayStatus;
import com.zengshi.ecp.order.facade.interfaces.eventual.IPayScoreSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPresentSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayQuartzInfoSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayShopCfgSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWay;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.audit.IAuditDailySumSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.DateUtil;

import cn.com.hongpay.client.anal.ResMsgAnalyze;
import cn.com.hongpay.client.vo.refund.RefundResVo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: <br>
 * Date:2015年10月8日下午3:43:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version  
 * @since JDK 1.6
 */

public class PayWaySVImplTest extends EcpServicesTest {
    
    @Resource
    private IPayWaySV payWaySV;
    
    @Resource
    private IPayWayRSV payWayRSV;
    
    @Resource
    private IOrdOfflineRSV ordOfflineRSV;
    
    @Resource
    private IStaffUnionRSV staffUnionRSV;
    
    @Resource
    private IPayQuartzInfoSV payQuartzInfoSV;
    
    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdSubSV ordSubSV;

    @Resource
    private IPayQuartzInfoRSV payQuartzInfoRSV;
    
    @Resource
    private IPaymentRSV paymentRSV;
    
    @Resource
    private IOrderQueryRSV orderQueryRSV;
    
    @Resource
    private IPayShopCfgSV payShopCfgSV;
    
    @Resource
    private IOrdPresentSV ordPresentSV;
    
    @Resource
    private IPayScoreSV payScoreSV;
    
    @Resource
    private IAuditDailySumSV auditDailySumSV;
    
    @Resource
    private IAuditTradeCheckRSV auditTradeCheckRSV;
    
    @Resource
    private IOrdMainRSV ordMainRSV;
    
    @Test
    public void testGetPayWay() {
        PayWayRequest payWay = new PayWayRequest();
        payWay.setUseFlag("1");
        List<PayWay> list= payWaySV.getPayWay(payWay);
        for(PayWay p:list){
            System.out.println(p);
        }
    }
    
    @Test
    public void testGetPayWayByShopId() {
        PayWayRequest payWay = new PayWayRequest();
        Long shopId = 100L;
        payWay.setShopId(shopId);
        List<PayWay> list= payWaySV.getPayWayByShopId(payWay);
        for(PayWay p:list){
            System.out.println(p);
        }
    }
    
    @Test
    public void testAddPayWay() {
        PayWayRequest payWay = new PayWayRequest();
        payWay.setId("9012");
        payWay.setPayLogo("1111111111");
        payWaySV.addPayWay(payWay);
    }
    
    @Test
    public void testEditPayWay() {
        PayWayRequest payWay = new PayWayRequest();
        payWay.setId("9011");
        payWay.setPayAcctType("01");
        payWaySV.editPayWay(payWay);
    }
    
    @Test
    public void testHandlePaySucc(){
        PaySuccInfo paySuccInfo = new PaySuccInfo();
        paySuccInfo.setOrderId("RW15102300001158");
        paySuccInfo.setPayWay("9999");
        paySuccInfo.setPayWayName("线下支付");
        paySuccInfo.setPayTransNo("RW15102300001158");
        paySuccInfo.setPayment(94400);
        paySuccInfo.setStaffId(104);
        paySuccInfo.setMerchAcct("888888888");
        paySuccInfo.setPayeeAcct("8888888888");
        paySuccInfo.setPayeeName("weijq");
        paySuccInfo.setShopId(69);
        payWaySV.savehandlePaySucc(paySuccInfo);
    }
    
    @Test
    public void testOfflineReview() {
        ROfflineReviewRequest ror = new ROfflineReviewRequest();
        ror.setOrderId("RW15110600001475");
        ror.setOfflineNo(108l);
        ror.setCheckCont("1111");
        ror.setStatus(OfflineConstants.Status.STATUS_SHOP_PASS);
        ror.setStaffId(104L);
        this.ordOfflineRSV.OfflineReview(ror);
    }
    
    
    @Test
    public void testSaveStaffRelInfoForPay() {
//        DubboServiceStart.init();
        PaySuccInfo paySuccInfo =  new PaySuccInfo();
        
        paySuccInfo.setOrderId("RW15110600001475");
        paySuccInfo.setPayType(PayStatus.PAY_TYPE_02); 
        paySuccInfo.setPayWay(PayStatus.PAY_WAY_9999);
        paySuccInfo.setPayTransNo("RW15110600001475");
        paySuccInfo.setStaffId(241);
        payScoreSV.dealScore(paySuccInfo);
    }
    
    
    @Test
    public void testGetPayWayRSV() {
        PayWayRequest payWay = new PayWayRequest();
        payWay.setShopId(100L);
        List<PayWayResponse> list = payWayRSV.getPayWay(payWay);
        System.out.println(list);
    }
    
    @Test
    public void testUpdateScoreDealFlagToDone() {
        PayQuartzInfoRequest request = new PayQuartzInfoRequest();
        request.setPayQuartzInfoId(1034L);
        payQuartzInfoRSV.updateScoreDealFlagToDone(request);
    }
    
    @Test
    public void testFileUtil() {
//        String aipay_public = FileUtil.saveFile("G:\\任务\\工作\\支付\\亚信鸿支付\\java版接入\\aipay_public.cer", "cer");
//        String test_private_key = FileUtil.saveFile("G:\\任务\\工作\\支付\\亚信鸿支付\\java版接入\\test_private_key.pfx", "pfx");
//        String test_public_key = FileUtil.saveFile("G:\\任务\\工作\\支付\\亚信鸿支付\\java版接入\\test_public_key.cer", "cer");
        
//        System.out.println("aipay_public:"+aipay_public);
//        System.out.println("test_private_key:"+test_private_key);
//        System.out.println("test_public_key:"+test_public_key);
        
//        String nonghangkey = FileUtil.saveFile("G:\\任务\\工作\\支付\\农行\\103881120990014.pfx", "pfx");
//        System.out.println("test_public_key:"+nonghangkey);
//        String abc_truststore = FileUtil.saveFile("G:\\任务\\工作\\支付\\农行\\TrustPayClient-Java-V3.1.0\\cert\\abc.truststore", "truststore");
//        String TrustPay_cer = FileUtil.saveFile("G:\\任务\\工作\\支付\\农行\\TrustPayClient-Java-V3.1.0\\cert\\TrustPay.cer", "cer");
//        System.out.println("abc_truststore:"+abc_truststore);
//        System.out.println("TrustPay_cer:"+TrustPay_cer);
        
    }
    
    @Test
    public void testRequestPayment() {
//        DubboServiceStart.init();
        PayRequestData dto = new PayRequestData();
        PaymentRequest paymentdto = new PaymentRequest();
        paymentdto.setOrderId("RW15111600001821");
        paymentdto.setPayWay("9003");
        Map<String, String> extendProps = new HashMap<String, String>();
//        extendProps.put("clientIp", "127.0.0.1");
        dto = paymentRSV.requestPayment(paymentdto, extendProps);
        System.out.println(dto);
    }
    
    @Test
    public void testOrderQueryRSV() throws Exception {
//        String payWay = "9002";
//        String charset = "utf-8";
//        String payQueryUrl = "http://books123456789.ipmph.com/ZExam2x/YaxinPower.zaction";
//        
//        Map<String, String> params = new HashMap<String,String>();
//        params.put("UserName", "xxx");//非空
//        params.put("GoodsID", "62617");//非空
//        params.put("GoodsType", "PAPER");//非空
//        params.put("Time", "2");//可空
//        params.put("OrderSN", "212122");//可空
//        
//        Map<String, String> json = new HashMap<String,String>();
//        String jsonStr = JSON.json(params);
//        String a=CryptoUtil.encrypt(CryptoUtil.DEFAULT_KEY, jsonStr);
//        json.put("json", a);//非空
//        Map<String, String> map = orderQueryRSV.queryPaymentResult(payWay, charset, payQueryUrl, json);
//        System.out.println(map);
        
    }
    @Test
    public void testpaymentCallback() throws Exception {
        IPayWay aPayWay = EcpFrameContextHolder.getBean("hongpayPlatform", IPayWay.class);
        Map<String,String> responseResultMap = new HashMap<String, String>();
        responseResultMap.put("packet", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AIPAYTRADE><INFO><PARTNER_ID>100001</PARTNER_ID><SIGNED_MSG>26c87dc1074b92e69f1d9ea0b91c13c7a6e22e813521374d92528ba8abf0cb7b9040b2109d94cf1a7157e86f0bc6e751fb211abd63463572e4420534a10341e27d1220ff23caeb0be1524fdb8b2df349f01371cdf3fdf00db69d121445b3cbc1117686f6472805f1dd4303a7736a1c419bbc574880926a453c21e50c5fb762e8cd28450c3ef91c506bb4f79ba744a33cfaf40e3e89538330df6b44485dd70ae5d0d0b41f3e53f0cb6a5213a7d33a88500b603dcca80eb41aaf61c4191122f1230d0b72596644bdb9c8fc367ba7a4e2dd81704c6b6bcccf2e330693ab6ab9d24da1aaa39b02d5ba500871e1b09b10f4c305a85c4dac7b635e823fb3ef37e22d9e</SIGNED_MSG></INFO><BODY><TRADE_DETAILS><TRANS_DETAIL><TRADE_NO>665016</TRADE_NO><PARTNER_TRADE_NO>RW15110500001436</PARTNER_TRADE_NO><RESULT_CODE>00</RESULT_CODE><DETAIL_REMARK>支付成功</DETAIL_REMARK><AMOUNT>14000</AMOUNT><ACCT_RESERVED_FIELD1></ACCT_RESERVED_FIELD1><ACCT_RESERVED_FIELD2></ACCT_RESERVED_FIELD2><ACCT_RESERVED_FIELD3></ACCT_RESERVED_FIELD3></TRANS_DETAIL></TRADE_DETAILS></BODY></AIPAYTRADE>");
        aPayWay.paymentCallback(responseResultMap);
        
    }
    
    @Test
    public void paymentCallback() throws Exception {
        Map<String, String> responseResultMap = new HashMap<String, String>();
        responseResultMap.put("buyer_id", "2088902742862444");
        responseResultMap.put("trade_no", "2015111721001004440099363354");
        responseResultMap.put("use_coupon", "N");
        responseResultMap.put("notify_time", "2015-11-17 17:17:36");
        responseResultMap.put("subject", "学谦的娃娃3*1");
        responseResultMap.put("sign_type", "MD5");
        responseResultMap.put("is_total_fee_adjust", "N");
        responseResultMap.put("notify_type", "trade_status_sync");
        responseResultMap.put("out_trade_no", "RW15111600001821");
        responseResultMap.put("gmt_payment", "2015-11-17 17:13:58");
        responseResultMap.put("trade_status", "TRADE_SUCCESS");
        responseResultMap.put("discount", "0.00");
        responseResultMap.put("sign", "01954cc11dd8e4b9b0149098526c3dfa");
        responseResultMap.put("buyer_email", "124661333@qq.com");
        responseResultMap.put("gmt_create", "2015-11-17 17:13:46");
        responseResultMap.put("price", "0.01");
        responseResultMap.put("total_fee", "0.01");
        responseResultMap.put("quantity", "1");
        responseResultMap.put("seller_id", "2088121127353472");
        responseResultMap.put("notify_id", "bf2b983366600b9ee4e1d23b6316a8eje8");
        responseResultMap.put("seller_email", "pmphmc@163.com");
        responseResultMap.put("payment_type", "1");
//        responseResultMap.put("MSG", "PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48RXJyb3JNZXNzYWdlPr270tezybmmPC9FcnJvck1lc3NhZ2U+PEVDTWVyY2hhbnRUeXBlPkVCVVM8L0VDTWVyY2hhbnRUeXBlPjxNZXJjaGFudElEPjEwMzg4MTEyMDk5MDAxNDwvTWVyY2hhbnRJRD48VHJ4VHlwZT5BQkNDYXJkUGF5PC9UcnhUeXBlPjxPcmRlck5vPlJXMTUxMTEzMDAwMDE3MDY8L09yZGVyTm8+PEFtb3VudD4wLjAxPC9BbW91bnQ+PEJhdGNoTm8+MDAwMDAxPC9CYXRjaE5vPjxWb3VjaGVyTm8+MDAwMDIyPC9Wb3VjaGVyTm8+PEhvc3REYXRlPjIwMTUvMTEvMTM8L0hvc3REYXRlPjxIb3N0VGltZT4xNzoxODo1NTwvSG9zdFRpbWU+PE1lcmNoYW50UmVtYXJrcz4yNjg8L01lcmNoYW50UmVtYXJrcz48UGF5VHlwZT5FUDAwNDwvUGF5VHlwZT48Tm90aWZ5VHlwZT4xPC9Ob3RpZnlUeXBlPjxpUnNwUmVmPkJERUNFUDAxMTY1MTI5OTY2OTIxPC9pUnNwUmVmPjwvVHJ4UmVzcG9uc2U+PC9NZXNzYWdlPjxTaWduYXR1cmUtQWxnb3JpdGhtPlNIQTF3aXRoUlNBPC9TaWduYXR1cmUtQWxnb3JpdGhtPjxTaWduYXR1cmU+RjlTRklwRkpCVWR6SUE4WUx1OXo1eStHd2UxVUtPS2ZMdEpkYjZvakg2bGhtQ3NYZGhmMzhWbTQ1OTZUb0R2WGRhMDE0QzJObExxYUZjK25ONWxvOElXeFFydUdTaEc5Q0lxMUNKU29TajdoQzVMNEs1S2dUVlREdUl6bW1qTWVVZmF6SHpwYlBXMko0bFF1cU5NM29wWmJKL3M4Ri9DbWJUREg0RDFNUk9FPTwvU2lnbmF0dXJlPjwvTVNHPg==");
//        responseResultMap.put("MSG", "PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48RXJyb3JNZXNzYWdlPr270tezybmmPC9FcnJvck1lc3NhZ2U+PEVDTWVyY2hhbnRUeXBlPkVCVVM8L0VDTWVyY2hhbnRUeXBlPjxNZXJjaGFudElEPjEwMzg4MTEyMDk5MDAxNDwvTWVyY2hhbnRJRD48VHJ4VHlwZT5BQkNDYXJkUGF5PC9UcnhUeXBlPjxPcmRlck5vPlJXMTUxMTEzMDAwMDE3MDQ8L09yZGVyTm8+PEFtb3VudD4wLjEwPC9BbW91bnQ+PEJhdGNoTm8+MDAwMDAxPC9CYXRjaE5vPjxWb3VjaGVyTm8+MDAwMDAyPC9Wb3VjaGVyTm8+PEhvc3REYXRlPjIwMTUvMTEvMTM8L0hvc3REYXRlPjxIb3N0VGltZT4xNzowNTo1MDwvSG9zdFRpbWU+PE1lcmNoYW50UmVtYXJrcz4yNjg8L01lcmNoYW50UmVtYXJrcz48UGF5VHlwZT5FUDAwNDwvUGF5VHlwZT48Tm90aWZ5VHlwZT4xPC9Ob3RpZnlUeXBlPjxpUnNwUmVmPkJERUNFUDAxMTYzODE3NTY1MDk4PC9pUnNwUmVmPjwvVHJ4UmVzcG9uc2U+PC9NZXNzYWdlPjxTaWduYXR1cmUtQWxnb3JpdGhtPlNIQTF3aXRoUlNBPC9TaWduYXR1cmUtQWxnb3JpdGhtPjxTaWduYXR1cmU+MEIvWEFkdmZTcUwwUzJUWWpIeWpXcmdpemVwK09OTkpPenZlc2luUzE4VjM0Q1FXQzIzMnNYdGF4dURHT1VmT3NyalBLS1pGU1BzK1pXQkx6TWdrSi9DKzRrZDNkOHNqRWVDSHd1aHJYaUNtcTZlejBaUXJuWUw2RWVnUXdOYzc1Q0NXTnBDSzRobTYvMXgrbGFDMUVvbVdMb2hMeGZvakphMythaW56TVc4PTwvU2lnbmF0dXJlPjwvTVNHPg==");
//        responseResultMap.put("MSG", "MSG=PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48RXJyb3JNZXNzYWdlPr270tezybmmPC9FcnJvck1lc3NhZ2U+PEVDTWVyY2hhbnRUeXBlPkVCVVM8L0VDTWVyY2hhbnRUeXBlPjxNZXJjaGFudElEPjEwMzg4MTEyMDk5MDAxNDwvTWVyY2hhbnRJRD48VHJ4VHlwZT5BQkNDYXJkUGF5PC9UcnhUeXBlPjxPcmRlck5vPlJXMTUxMTEzMDAwMDE3MDQ8L09yZGVyTm8+PEFtb3VudD4wLjEwPC9BbW91bnQ+PEJhdGNoTm8+MDAwMDAxPC9CYXRjaE5vPjxWb3VjaGVyTm8+MDAwMDAyPC9Wb3VjaGVyTm8+PEhvc3REYXRlPjIwMTUvMTEvMTM8L0hvc3REYXRlPjxIb3N0VGltZT4xNzowNTo1MDwvSG9zdFRpbWU+PE1lcmNoYW50UmVtYXJrcz4yNjg8L01lcmNoYW50UmVtYXJrcz48UGF5VHlwZT5FUDAwNDwvUGF5VHlwZT48Tm90aWZ5VHlwZT4xPC9Ob3RpZnlUeXBlPjxpUnNwUmVmPkJERUNFUDAxMTYzODE3NTY1MDk4PC9pUnNwUmVmPjwvVHJ4UmVzcG9uc2U+PC9NZXNzYWdlPjxTaWduYXR1cmUtQWxnb3JpdGhtPlNIQTF3aXRoUlNBPC9TaWduYXR1cmUtQWxnb3JpdGhtPjxTaWduYXR1cmU+MEIvWEFkdmZTcUwwUzJUWWpIeWpXcmdpemVwK09OTkpPenZlc2luUzE4VjM0Q1FXQzIzMnNYdGF4dURHT1VmT3NyalBLS1pGU1BzK1pXQkx6TWdrSi9DKzRrZDNkOHNqRWVDSHd1aHJYaUNtcTZlejBaUXJuWUw2RWVnUXdOYzc1Q0NXTnBDSzRobTYvMXgrbGFDMUVvbVdMb2hMeGZvakphMythaW56TVc4PTwvU2lnbmF0dXJlPjwvTVNHPg==");
        paymentRSV.paymentCallback("9003", responseResultMap);
        
    }
    
    @Test
    public void check() throws Exception {
        Map<String, String> extendProps = new HashMap<String, String>();
//        extendProps.put(PayAudit.QsDates, "2015-11-30,2015-11-29,2015-11-28,2015-11-27,2015-11-26,2015-11-25,2015-11-24,2015-11-23,2015-11-22,2015-11-21,2015-11-20");
//        extendProps.put(PayAudit.QsDates, "2015-11-19,2015-11-18,2015-11-17,2015-11-16,2015-11-15,2015-11-14,2015-11-13,2015-11-12,2015-11-11,2015-11-10");
//        extendProps.put(PayAudit.QsDates, "2015-12-01,2015-12-02,2015-12-03,2015-12-04,2015-12-05,2015-12-06,2015-12-07,2015-12-08,2015-12-09,2015-12-10");
//        extendProps.put(PayAudit.QsDates, "2015-12-10,2015-12-19,2015-12-18,2015-12-17,2015-12-16,2015-12-15,2015-12-14,2015-12-13,2015-12-12,2015-12-11");
//        extendProps.put(PayAudit.QsDates, "2015-12-31,2015-12-30,2015-12-29,2015-12-28,2015-12-27,2015-12-26,2015-12-25,2015-12-24,2015-12-23,2015-12-22,2015-12-21,2015-12-20");
        extendProps.put(PayAudit.QsDates, "2016-01-01,2016-01-02,2016-01-03,2016-01-04,2016-01-05,2016-01-06,2016-01-07,2016-01-08,2016-01-09,2016-01-10");
//        extendProps.put(PayAudit.QsDates, "2015-12-28");
        paymentRSV.check("9003", extendProps);
    }
    
    @Test
    public void queryNotReceiptOrder(){
        ROrdReceiptRequest rOrdReceiptRequest = new ROrdReceiptRequest();
        rOrdReceiptRequest.setCount(20);
        rOrdReceiptRequest.setTableIndex(1);
        rOrdReceiptRequest.setShopId(69L);
        List<ROrdMainResponse> list = ordMainSV.queryNotReceiptOrder(rOrdReceiptRequest);
        System.out.println(list);
    }
    
    @Test
    public void testRefund() {
        Map<String, String> extendProps = new HashMap<String, String>();
        extendProps.put("clientIp", "127.0.0.1");
        RPayRefundRequest request = new RPayRefundRequest();
        request.setPayWay("9004");
        request.setOrderId("RW15111300001704");
        request.setBackId(100L);
        request.setRefundAmount(1L);
        RPayRefundResponse result = paymentRSV.refund(request, extendProps);
        System.out.println(result);
    }
    
    @Test
    public void testRefundCallback() {
        Map<String, String> resultData = new HashMap<String, String>();
        resultData.put("sign", "b74c4456ef791479b1d19aa796d8fca7");
        resultData.put("result_details", "2015112121001004260079644334^0.01^SUCCESS");
        resultData.put("notify_time", "2015-12-24 16:09:03");
        resultData.put("ecp_staffId", "0");
        resultData.put("sign_type", "MD5");
        resultData.put("notify_type", "batch_refund_notify");
        resultData.put("notify_id", "8a7e46f7d4ec5f840d4f54d74aa0234mmw");
        resultData.put("batch_no", "20151224100");
        resultData.put("success_num", "1");
        Map<String, String> result = paymentRSV.refundCallback("9003", resultData);
        System.out.println(result);
    }
    
    @Test
    public void testGetAuditDailySum() {
        AuditDailySum auditDailySumReq = new AuditDailySum();
        auditDailySumReq.setPayWay("9002");
        auditDailySumReq.setCheckDate(DateUtil.getTimestamp("20151130", "yyyyMMdd"));
        auditDailySumReq.setShopId(Common.DEFAULT_STAFFID);
        AuditDailySum auditDailySum = auditDailySumSV.getAuditDailySum(auditDailySumReq);
        if(auditDailySum!=null){
            System.out.println(auditDailySum);
        }
    }
    
    @Test
    public void testGetTAuditTradeChecks(){
        RAuditTradeCheckRequest auditTradeCheckRequest = new RAuditTradeCheckRequest();
        auditTradeCheckRequest.setStartTime(DateUtil.getTimestamp("20151113000000", "yyyyMMddHHmmss"));
        auditTradeCheckRequest.setEndTime(DateUtil.getTimestamp("20151219000000", "yyyyMMddHHmmss"));
        auditTradeCheckRequest.setPageNo(1);
        auditTradeCheckRequest.setPageSize(1000);
        PageResponseDTO<RAuditTradeCheckResponse> respose = auditTradeCheckRSV.getTAuditTradeChecks(auditTradeCheckRequest);
        System.out.println(respose);
    }
    
    @Test
    public void testGetTAuditTradeChecksTotal(){
        RAuditTradeCheckRequest auditTradeCheckRequest = new RAuditTradeCheckRequest();
        auditTradeCheckRequest.setStartTime(DateUtil.getTimestamp("20151113000000", "yyyyMMddHHmmss"));
        auditTradeCheckRequest.setEndTime(DateUtil.getTimestamp("20151219000000", "yyyyMMddHHmmss"));
        auditTradeCheckRequest.setPageNo(1);
        auditTradeCheckRequest.setPageSize(1000);
        RAuditTradeCheckTotalResponse respose = auditTradeCheckRSV.getTAuditTradeChecksTotal(auditTradeCheckRequest);
        System.out.println(respose);
    }
    
    @Test
    public void testquerynotInAuditTradeCheckOrders(){
        RQueryOrderRequest rQueryOrderRequest = new RQueryOrderRequest();
        rQueryOrderRequest.setBegDate(DateUtil.getTimestamp("20141113000000", "yyyyMMddHHmmss"));
        rQueryOrderRequest.setEndDate(DateUtil.getTimestamp("20161219000000", "yyyyMMddHHmmss"));
        rQueryOrderRequest.setPageNo(1);
        rQueryOrderRequest.setPageSize(1000);
        PageResponseDTO<ROrdMainResponse> respose = ordMainRSV.querynotInAuditTradeCheckOrders(rQueryOrderRequest);
        System.out.println(respose);
    }
    
    @Test
    public void test(){
        String TransNo = "RW15111300001704100";
        String OldOrderId = "RW15111300001704";
        Long backId = Long.parseLong(TransNo.substring(
                OldOrderId.length()));
        System.out.println(backId);
//        String text = "商户号|交易类型|订单编号|交易时间|交易金额|商户账号|商户动账金额|客户账号|账户类型|商户回佣手续费|商户分期手续费|会计日期|主机流水号|9014流水号|原订单号^^103881120990014|Sale|RW15112600001525|20151126105952|0.55|11200601040012356|0.55|6228480062463201210|401|0.00|0.00|20151126|182365429|BQECEP01105259325994|^^103881120990014|Sale|RW15112600002091|20151126153909|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|289288230|BQECEP01151853250148|^^103881120990014|Sale|RW15112600002095|20151126163658|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|313750827|BQECEP01162818407263|^^103881120990014|Sale|RW15112600001546|20151126165308|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|319749589|BQECEP01163157064873|^^103881120990014|Sale|RW15112600002096|20151126171841|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|327819018|BQECEP01171452962664|^^103881120990014|Sale|RW15112600001549|20151126200001|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|360084943|BQECEP01194212283483|^^103881120990014|Sale|RW15112600004062|20151126214305|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|379658765|BQECEP01211303386939|^^103881120990014|Sale|RW15112600004063|20151126215828|0.01|11200601040012356|0.01|6228480062463201210|401|0.00|0.00|20151126|384439847|BQECEP01212752489199|";
////        text = new String(text.getBytes("gb2312"), "UTF-8");
//        String detailRecords = text;
//        String[] details = detailRecords.split("\\^\\^");
//        if(details.length>1){
//            for (int i=1;i<details.length;i++) {
//                String detail= details[i];
//                String[] items = detail.split("\\|", 15);
//                String merc_code = items[0];
//                String transType = items[1];
//                String orderId = items[2];
//                String transTime = items[3];
//                String payment = items[4];
//                String merchAcctId = items[5];
//                String merchAcctMoney = items[6];
//                String payeeAcct = items[7];
//                String acctType = items[8];
//                String fee = items[9];
//                String stagingFee = items[10];
//                String accountingDate = items[11];
//                String transNo = items[12];
//                String transNo9014 = items[13];
//                String oldOrderId = items[14]; 
//            }
//        }
    }
    @Test
    public void testVerifyRefundCallBack() throws Exception { 
        String payNotifyXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AIPAYTRADE><INFO><PARTNER_ID>100001</PARTNER_ID><SIGNED_MSG>7568fda41b20f5a2be4a6f378abaf3af88d08c14314101bbbe7f33d7446c96f0a89a5dfec259c8563a6c4bc1e0205cec55ff78a9c7d61a4eadc0211613bb634cbe89996659ccbb5ef11af48f8baa6a3c790175024c41b1d08ae3d04539e0c7f744675d75520c4c04944ca45d03ae663049b4ac38bcfc468e0f2775b80ee0642582b8075ed599d6cdd2050f078bb655954beea2a2c6eb4fca9bea09e4d4ac700f1ff1311233d4f18ee614c7a4380eb5b6ecfe052ebbbf54eba538315e88713eee2266f203591e96d65dafc720bc2bc6f240b26174a9cd49f871219cffa13d48abf1f57a2232cc189fa5c2bc2687fe3d6d6dbed9c38bb2389c0c434b18574cb474</SIGNED_MSG></INFO><BODY><PARTNER_TRADE_NO>RW16092300003028986051</PARTNER_TRADE_NO><REFUND_AMOUNT>17000</REFUND_AMOUNT><REFUND_TIME></REFUND_TIME><REFUND_CODE>00</REFUND_CODE><REFUND_MSG>退款成功</REFUND_MSG><REFUND_REMARK>1295</REFUND_REMARK></BODY></AIPAYTRADE>";
        PayWay payWayBean = payWaySV.getPayWayById("9002");
        String cerFileId = payWayBean.getPayVerifyCert();
        String cerFile = PayHelper.downloadFileAndGetLocalPath(cerFileId);
        ResMsgAnalyze analyze = new ResMsgAnalyze(cerFile);
        RefundResVo refundResVo = analyze.verifyRefundNotifyResMsg(payNotifyXml);
        System.out.println("出参是++++++++++++++++++++++++++"+refundResVo.toString());
    }     
}


package com.zengshi.ecp.aip.service.common.pay.impl;

import java.util.List;

import com.abc.pay.client.JSON;
import com.abc.pay.client.MerchantConfig;
import com.abc.pay.client.MerchantPara;
import com.abc.pay.client.TrxException;
import com.abc.pay.client.ZipUtil;
import com.abc.pay.client.ebus.QueryOrderRequest;
import com.abc.pay.client.ebus.RefundRequest;
import com.abc.pay.client.ebus.SettleRequest;
import com.zengshi.ecp.aip.dubbo.constants.AipConstants.PayServiceMsgCode;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayQueryOrderRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayQueryOrderResponse;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayRefundRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPayRefundResponse;
import com.zengshi.ecp.aip.dubbo.dto.ABCPaySettleRequest;
import com.zengshi.ecp.aip.dubbo.dto.ABCPaySettleResponse;
import com.zengshi.ecp.aip.service.common.pay.interfaces.ABCPaySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class ABCPaySVImpl implements ABCPaySV{
    
    public static final String MODULE = ABCPaySVImpl.class.getName();

    @Override
    public ABCPaySettleResponse querySettle(ABCPaySettleRequest request) {
        ABCPaySettleResponse response = new ABCPaySettleResponse();
      //1、取得商户对账单下载所需要的信息
        String tSettleDate = request.getSettleDate();
        String tZIP = request.getZIP();
        SettleRequest tRequest = new SettleRequest();
        tRequest.dicRequest.put("SettleDate",tSettleDate);  //对账日期YYYY/MM/DD （必要信息）
        tRequest.dicRequest.put("ZIP",tZIP);
        int num = 0;
        try {
            MerchantPara para = MerchantConfig.getUniqueInstance().getPara();
            List<String> mercCodeList = para.getMerchantIDList();
            int i = 0;
            for(String MercCode:mercCodeList){
                i++;
                if(MercCode.equals(request.getMerchantID())){
                    num = i;
                    break;
                }
            }
        } catch (TrxException e) {
            LogUtil.error(MODULE, "农行退款请求异常", e);
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310002, new String[] {
                    e.getCode(), e.getMessage() });
        }
        if(num==0){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
        }

        //3、传送商户对账单下载请求并取得对账单
        JSON json = tRequest.extendPostRequest(num);

        //4、判断商户对账单下载结果状态，进行后续操作
        String returnCode = json.GetKeyValue("ReturnCode");
        String errorMessage = json.GetKeyValue("ErrorMessage");
        response.setReturnCode(returnCode);
        response.setErrorMessage(errorMessage);
        if ("0000".equals(returnCode))
        {
            //5、商户对账单下载成功，生成对账单对象
            String trxType = json.GetKeyValue("TrxType");
            String settleDate = json.GetKeyValue("SettleDate");
            String numOfPayments = json.GetKeyValue("NumOfPayments");
            String sumOfPayAmount = json.GetKeyValue("SumOfPayAmount");
            String numOfRefunds = json.GetKeyValue("NumOfRefunds");
            String sumOfRefundAmount = json.GetKeyValue("SumOfRefundAmount");
            response.setTrxType(trxType);
            response.setSettleDate(settleDate);
            response.setNumOfPayments(numOfPayments);
            response.setSumOfPayAmount(sumOfPayAmount);
            response.setNumOfRefunds(numOfRefunds);
            response.setSumOfRefundAmount(sumOfRefundAmount);
            String detailRecords ="";
            if("0".equals(tZIP)){
                detailRecords = json.GetKeyValue("DetailRecords");
            }
            if("1".equals(tZIP)) {
                detailRecords = ZipUtil.gunzip(json.GetKeyValue("ZIPDetailRecords"));
            }
            response.setDetailRecords(detailRecords);
        }
        return response;
    }
    
    @Override
    public ABCPayQueryOrderResponse queryOrder(ABCPayQueryOrderRequest request) {
        String payTypeID = request.getPayTypeID();
        String queryTpye = request.getQueryTpye();
        if("0".equals(queryTpye)){
            queryTpye = "false";
        }else if ("1".equals(queryTpye)){
            queryTpye="true";
        }

        QueryOrderRequest tQueryRequest = new QueryOrderRequest();
        tQueryRequest.queryRequest.put("PayTypeID", payTypeID);    //设定交易类型
        tQueryRequest.queryRequest.put("OrderNo", request.getOrderNo());    //设定订单编号 （必要信息）
        tQueryRequest.queryRequest.put("QueryDetail", queryTpye);//设定查询方式
        //如果需要专线地址，调用此方法：
        if(request.getConnectionFlag()){
            tQueryRequest.setConnectionFlag(true);
        }
        int num = 0;
        try {
            MerchantPara para = MerchantConfig.getUniqueInstance().getPara();
            List<String> mercCodeList = para.getMerchantIDList();
            int i = 0;
            for(String MercCode:mercCodeList){
                i++;
                if(MercCode.equals(request.getMerchantID())){
                    num = i;
                    break;
                }
            }
        } catch (TrxException e) {
            LogUtil.error(MODULE, "农行退款请求异常", e);
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310002, new String[] {
                    e.getCode(), e.getMessage() });
        }
        if(num==0){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
        }
        JSON json = tQueryRequest.extendPostRequest(num);
        ABCPayQueryOrderResponse response= new ABCPayQueryOrderResponse();
        response.setJson(json);
        return response;
    }
    
    @Override
    public ABCPayRefundResponse refund(ABCPayRefundRequest request) {
        ABCPayRefundResponse response = new ABCPayRefundResponse();
        //1、生成退款请求对象
        RefundRequest tRequest = new RefundRequest();
        tRequest.dicRequest.put("OrderDate", request.getOrderDate());  //订单日期（必要信息）
        tRequest.dicRequest.put("OrderTime", request.getOrderTime()); //订单时间（必要信息）
        if(!StringUtil.isBlank(request.getMerRefundAccountNo())){
            tRequest.dicRequest.put("MerRefundAccountNo", request.getMerRefundAccountNo());  //商户退款账号
        }
        if(!StringUtil.isBlank(request.getMerRefundAccountName())){
            tRequest.dicRequest.put("MerRefundAccountName", request.getMerRefundAccountName()); //商户退款名
        }
        tRequest.dicRequest.put("OrderNo", request.getOrderNo()); //原交易编号（必要信息）
        tRequest.dicRequest.put("NewOrderNo", request.getNewOrderNo()); //交易编号（必要信息）
        tRequest.dicRequest.put("CurrencyCode", request.getCurrencyCode()); //交易币种（必要信息）
        tRequest.dicRequest.put("TrxAmount", request.getTrxAmount()); //退货金额 （必要信息）
        tRequest.dicRequest.put("MerchantRemarks", request.getMerchantRemarks());  //附言
        //如果需要专线地址，调用此方法：
        if(request.getConnectionFlag()){
            tRequest.setConnectionFlag(true);
        }
        int num = 0;
        try {
            MerchantPara para = MerchantConfig.getUniqueInstance().getPara();
            List<String> mercCodeList = para.getMerchantIDList();
            int i = 0;
            for(String MercCode:mercCodeList){
                i++;
                if(MercCode.equals(request.getMerchantID())){
                    num = i;
                    break;
                }
            }
        } catch (TrxException e) {
            LogUtil.error(MODULE, "农行退款请求异常", e);
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310002, new String[] {
                    e.getCode(), e.getMessage() });
        }
        if(num==0){
            throw new BusinessException(PayServiceMsgCode.PAY_SERVER_310003);
        }

        //2、传送退款请求并取得退货结果
        JSON json = tRequest.extendPostRequest(num);

        //3、判断退款结果状态，进行后续操作
        String ReturnCode = json.GetKeyValue("ReturnCode");
        String ErrorMessage = json.GetKeyValue("ErrorMessage");
        response.setReturnCode(ReturnCode);
        response.setErrorMessage(ErrorMessage);
        if ("0000".equals(ReturnCode))
        {
            //4、退款成功
            response.setOrderNo(json.GetKeyValue("OrderNo"));
            response.setNewOrderNo(json.GetKeyValue("NewOrderNo"));
            response.setTrxAmount(json.GetKeyValue("TrxAmount"));
            response.setBatchNo(json.GetKeyValue("BatchNo"));
            response.setVoucherNo(json.GetKeyValue("VoucherNo"));
            response.setHostDate(json.GetKeyValue("HostDate"));
            response.setHostTime(json.GetKeyValue("HostTime"));
            response.setiRspRef(json.GetKeyValue("iRspRef"));
        }
        return response;
    }

}


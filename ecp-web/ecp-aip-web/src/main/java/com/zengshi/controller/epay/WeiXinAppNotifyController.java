package com.zengshi.controller.epay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zengshi.ecp.aip.dao.model.AipSvLogWithBLOBs;
import com.zengshi.ecp.order.dubbo.dto.pay.OrderPayStatusVO;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustWechatRelRSV;
import com.zengshi.epay.utils.ConstantsForEpay;
import com.zengshi.service.AipEpayService;
import com.alibaba.fastjson.JSON;

/**
 * 
 * Title: 微信扫码支付回调 <br>
 * Project Name:ecp-aip-web <br>
 * Description: <br>
 * Date:2016年12月13日下午7:24:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author panjs
 * @version  
 * @since JDK 1.6
 */
@Controller
public class WeiXinAppNotifyController {

    private final static Logger logger = LoggerFactory.getLogger(WeChatNotifyController.class);

    @Autowired
    AipEpayService aipEpayService;//本地服务

    @Autowired
    ICustWechatRelRSV custWechatRelRSV;
    
    @Autowired
    IPaymentRSV paymentRSV; //dubbo服务

    String flag = ConstantsForEpay.NotifyInfo.FLAG;
    String message = ConstantsForEpay.NotifyInfo.RETURN_MSG;
    String success_status = ConstantsForEpay.NotifyInfo.SUCCES_STATUS;


    @RequestMapping(value = "/weixinappnotify", method = {RequestMethod.POST, RequestMethod.GET})
    public void weiXinNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("微信扫码支付异步回调处理-----start1------!");
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        logger.info("微信扫码异步回调处理-----start2------!");
        String payWay = "9008";
        Map<String, String> payInfo = getParamMapWeChat(request);
        Timestamp requestTime = new Timestamp(new Date().getTime());
        logger.info("请求数据payInfo：" + payInfo.toString());

        //日志记录aip_sv_log
        AipSvLogWithBLOBs tAipSvLogWithBLOBs = new AipSvLogWithBLOBs();
        tAipSvLogWithBLOBs.setAppKey("WeiXinAppNotifyController9008");
        tAipSvLogWithBLOBs.setServiceName("WeiXinAppNotifyController");
        tAipSvLogWithBLOBs.setRequest(payInfo.toString());
        tAipSvLogWithBLOBs.setRequestTime(requestTime);
        Map<String, String> returnMap = new HashMap<String, String>();
        try {
        	returnMap = paymentRSV.paymentCallback(payWay, payInfo);
    		String statusFlag=OrderPayStatusVO.SUCCESS;
            if ("SUCCESS".equals(payInfo
    				.get("return_code"))) {
    		} else {
    		    statusFlag=OrderPayStatusVO.FAILURE;
    		}
            if (returnMap != null && returnMap.get(flag) != null&& returnMap.get(message) != null) {
                logger.info("ReturnMap" + returnMap.toString());
                if (returnMap.get(flag).equalsIgnoreCase("0")) {
                    out.write(returnMap.get(message));
                    logger.info("WeChatNotifyController处理成功！");
                    //returnMap = paymentRSV.paymentCallback(payWay, payInfo);

                    logger.info("WeChatNotifyController  微信支付异步通知结果 "+ JSON.toJSONString(returnMap));
                    responseWechat(statusFlag, response);
                } else {
                    out.write(returnMap.get(message));
                    logger.error("WeChatNotifyController处理失败！失败信息："+ returnMap.get(message));
                    responseWechat(statusFlag, response);
                }
            } else {
                logger.error("返回的Map、或Map中的flag或message为空！");
                out.write("微信支付   Get wrong result,not having necessary parameters!Please contact us!");
                //若returnMap为空则初始化
                if(returnMap==null){
                    returnMap = new HashMap<String, String>();
                    returnMap.put("result", "返回的Map为null！");
                }
            }
        } catch (Exception e) {
            response.setStatus(500);
            out.write("Fail to call back service!Please try again later!");
            logger.error("调用后场异常：", e);
            Writer result = new StringWriter();
            PrintWriter printWriter = new PrintWriter(result);
            e.printStackTrace(printWriter);
            //将异常信息记录至日志表中
            returnMap.put("exception", result.toString());
            throw e;
        } finally {
            out.flush();
            out.close();
            try {
                Timestamp responseTime = new Timestamp(new Date().getTime());
                tAipSvLogWithBLOBs.setResponseTime(responseTime);
                tAipSvLogWithBLOBs.setResponse(returnMap.toString());
                // 插入日志
                aipEpayService.insertEpayLog(tAipSvLogWithBLOBs);
            } catch (Exception e) {
                logger.error("数据库异常！", e);
                throw new Exception("数据库异常！请稍后重试！");
            }
        }
    }

    private Map<String, String> getParamMapWeChat(HttpServletRequest request) {
        BufferedReader in = null;
        String content = "";
        Map<String, String> m = new HashMap<String, String>();
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                content += line;
            }
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
                Element e = (Element) iterator.next();
                m.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    //微信回调应答
    private void responseWechat(String flag, HttpServletResponse response) throws IOException {
        String xml = "<xml><return_code>";
        if(flag.equals("0")){
            xml+="<![CDATA[SUCCESS]]>";
        }else{
            xml+="<![CDATA[FAIL]]>";
        }
        xml+="</return_code></xml>";
        PrintWriter out = response.getWriter();
        logger.info("微信异步回调之后，本域回转给微信支付的数据: "+xml);
        out.write(xml);
        out.flush();
        out.close();
    }


}

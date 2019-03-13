/** 
 * Project Name:ecp-services-sys 
 * File Name:C123MsgSmsSendSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.sms.impl 
 * Date:2016年3月16日下午9:56:25 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.sys.sms.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.sms.gateway.C123SmsGatewayBean;
import com.zengshi.ecp.sys.sms.interfaces.IMsgSmsOperateSV;
import com.zengshi.ecp.sys.sms.operator.SmsOperatorSendBean;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月16日下午9:56:25 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version
 * @since JDK 1.6
 */
@Service("c123MsgSmsOperateSV")
public class C123MsgSmsOperateSVImpl implements IMsgSmsOperateSV<C123SmsGatewayBean> {

    private static final String MODULE = C123MsgSmsOperateSVImpl.class.getName();

    //private static final String C123HTTP = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
    
    
    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * 
     * @see com.zengshi.ecp.sys.sms.interfaces.IMsgSmsSendSV#sendSms(com.zengshi.ecp.sys.sms.dto.SmsOperateSendDTO)
     */
    @Override
    public void sendSms(SmsOperatorSendBean<C123SmsGatewayBean> sendDto) throws BusinessException {

        HttpClient client = new HttpClient();

        PostMethod method = new PostMethod(sendDto.getGateWay().getUrl());
        
        String queryString = C123SmsGatewayBean.buildSendOpearteQueryString(sendDto);
        // 设置请求的发送参数
        method.setQueryString(queryString);
        
        try {

            HttpMethodParams param = method.getParams();
            param.setContentCharset("UTF-8");

            client.executeMethod(method);
            // 打印服务器返回的状态
            LogUtil.debug(MODULE, "发送串：" + queryString + "返回状态：" + method.getStatusLine());

            // 获取返回信息；
            InputStream stream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuffer buf = new StringBuffer();
            String line;
            while (null != (line = br.readLine())) {
                buf.append(line);
            }
            
            //检查解析后的返回值；如果true表示发送成功；否则表示发送失败；如果发送失败，则抛出异常，异常描述中需要带上请求返回结果
            if(parseResponse(buf.toString())){
                ///true成功；
            } else {
                throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_ERR,new String[]{sendDto.getGateWay().getGateway() , buf.toString()});
            }
        } catch (Exception err) {
            LogUtil.error(MODULE, "短信发送测试", err);
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_EXCEPT,new String[]{sendDto.getGateWay().getGateway() , err.getMessage()});
        } finally {
            // 释放连接
            method.releaseConnection();
        }

    }

    private boolean parseResponse(String responseStr) throws Exception{
        LogUtil.debug(MODULE, "SMS 发送返回信息：" + responseStr);
        /*
         * //成功： <xml name="sendOnce" result="1"> <Item cid="xxx" sid="xxx" msgid="xxx" total="2"
         * price="0.10" remain="170.040"/> </xml>
         * 
         * //失败： <xml name="sendOnce" result="-1"/>
         */
        
        try {
            Document doc = (Document) DocumentHelper.parseText(responseStr);

            Element xml = doc.getRootElement();
            String result = xml.attribute("result").getValue();
            if (Integer.parseInt(result) < 1) {
                return false;
            } else {
                return true;
            }
        } catch (DocumentException e) {
            LogUtil.error(MODULE, "解析返回结果串失败; 返回信息："+responseStr+";错误信息："+e.getMessage(), e);
            throw e;
        }
    }

    /*
     * String ms = "{\"app\": \"standardWork\",\"type\": 0," +
     * "\"occurTime\": \"2013-11-14 11:22:02\",\"title\": \"-------流程标题-------\"," +
     * "\"loginName\": \"ST/G01008000311\",\"status\": 0,\"removed\": 0," +
     * " \"typename\": \"流程名称11\"," + "\"url\": \""+URLEncoder.encode(s,"UTF-8")+"\"," +
     * "\"pname\": \"主流程名称\",\"pincident\": 1," + "\"cname\": \"子流程实例号\",\"cincident\": 1," +
     * "\"stepName\": \"当前步骤\"," + "\"initiator\": \"ST/G01008000311\"}"; ((PostMethod)
     * method).addParameter("data", ms);
     */

}

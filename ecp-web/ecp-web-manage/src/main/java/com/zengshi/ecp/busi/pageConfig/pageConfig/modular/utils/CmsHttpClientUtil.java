package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.config.Application;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * HttpClient 封装使用httpclient的一些公用方法 <br>
 * Title: ECP <br>
 * Project Name:ecp-web-manage-core <br>
 * Description: <br>
 * Date:2016年7月13日下午8:37:00 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */
public class CmsHttpClientUtil {
    private static String MODULE = CmsHttpClientUtil.class.getName();

    /**
     * 
     * getAnalysisUrl:(获取行为分析地址). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @return
     * @since JDK 1.6
     */
    private static String getAnalysisUrl() {
        LogUtil.info(MODULE, "获取行为分析地址开始");
        BaseSysCfgRespDTO XwAnaUrlDto = null;
        try {
            XwAnaUrlDto = SysCfgUtil.fetchSysCfg("XW_ANAL_SYS_URL");
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取用户行为分析地址结束：异常", e);
            return null;
        }

        if (XwAnaUrlDto == null || StringUtil.isBlank(XwAnaUrlDto.getParaValue())) {
            LogUtil.error(MODULE, "用户行为分析地址结束：未配置：常量库字段为XW_ANAL_SYS_URL");
            return null;
        }
        String url = XwAnaUrlDto.getParaValue();
        LogUtil.info(MODULE, "行为分析地址结束：成功 地址为" + url);

        return url;
    }

    /**
     * 
     * getGuessAnalysUrl:(获取猜你喜欢行为分析地址). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @return
     * @since JDK 1.6
     */
    public static String getGuessAnalysUrl() {
        LogUtil.info(MODULE, "获取猜你喜欢行为分析地址开始");
        // 1. 获取用户行为分析地址
        String baseUrl = getAnalysisUrl();
        if (StringUtil.isBlank(baseUrl)) {
            LogUtil.info(MODULE, "获取猜你喜欢行为分析地址结束：用户行为分析基础地址为空");
            return null;
        }

        // 2. 获取猜你喜欢相对地址
        String recommendUrl = Application.getValue("xwrecommendurl");

        if (StringUtil.isBlank(recommendUrl)) {
            LogUtil.error(MODULE, "获取猜你喜欢行为分析地址结束：application-url.xml中猜你喜欢行为分析地址xwrecommendurl未配置");
            return null;
        }
        LogUtil.info(MODULE, "获取猜你喜欢行为分析相对地址结束：成功 地址为 " + recommendUrl);
        return baseUrl + recommendUrl;
    }
    /**
     * 
     * getRankAnalysUrl:(获取排行榜行为分析地址). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @return
     * @since JDK 1.6
     */
    public static String getRankAnalysUrl() {
        LogUtil.info(MODULE, "获取排行榜行为分析地址开始");
        // 1. 获取用户行为分析地址
        String baseUrl = getAnalysisUrl();
        if (StringUtil.isBlank(baseUrl)) {
            LogUtil.info(MODULE, "获取排行榜行为分析地址结束：用户行为分析基础地址为空");
            return null;
        }

        // 2. 获取排行榜相对地址
        String rankUrl = Application.getValue("xwrankurl");

        if (StringUtil.isBlank(rankUrl)) {
            LogUtil.error(MODULE, "获取排行榜行为分析地址结束：application-url.xml中排行榜行为分析地址xwrankurl未配置");
            return null;
        }

        LogUtil.info(MODULE, "获取排行榜行为分析相对地址结束：成功 地址为" + rankUrl);

        return baseUrl + rankUrl;
    }

    /**
     * 
     * doRequest:(使用httpclient执行http请求). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @param formparams
     * @return
     * @since JDK 1.6
     */
    public static String doRequest(String url, List<BasicNameValuePair> formparams) {
        LogUtil.info(MODULE, "httpclient请求开始");
        // 1 入参验证
        if (StringUtil.isBlank(url)) {
            LogUtil.error(MODULE, "httpclient请求结束：地址为空");
            return null;
        }
        if (CollectionUtils.isEmpty(formparams)) {
            formparams = new ArrayList<BasicNameValuePair>();
        }

        CloseableHttpClient httpclient = null;
        HttpPost httppost = null;
        CloseableHttpResponse response = null;
        String result = null;
        // 处理请求
        try {
            // 创建默认的httpClient实例
            httpclient = HttpClients.createDefault();
            // 创建httppost
            httppost = new HttpPost(url);
            // 设置请求实体
            httppost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
            // 执行请求
            response = httpclient.execute(httppost);
            //处理结果
            if(response!=null && response.getStatusLine() !=null){//请求成功
               LogUtil.info(MODULE, "httpclient请求返回状态码："
                       + response.getStatusLine().getStatusCode()); 
               HttpEntity entity = response.getEntity();  
               result = EntityUtils.toString(entity);
            }
           
        } catch (ClientProtocolException e) {
            LogUtil.error(MODULE, "远程服务协议异常", e);
        } catch (IOException e) {
            LogUtil.error(MODULE, "远程服务连接异常", e);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, e.getMessage(), e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "调用远程服务未知异常", e);
        } finally {
            // 关闭连接,释放资源
            try {
                if(response != null){
                    try { 
                        response.close();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httppost != null){
                    try { 
                        httppost.releaseConnection();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httppost!=null){
                    try { 
                        httppost.abort();
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }
                if(httpclient !=null){
                    try { 
                        httpclient.close(); 
                    }
                    catch (Exception e) {  
                        e.printStackTrace();
                    } 
                }  
            } catch (Exception e) {
                LogUtil.error(MODULE, "关闭httpclient连接异常", e);
            }
        }
        LogUtil.info(MODULE, "httpclient请求结束");
        return result;
    }
}

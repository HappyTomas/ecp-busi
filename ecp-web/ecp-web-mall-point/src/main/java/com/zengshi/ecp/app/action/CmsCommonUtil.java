package com.zengshi.ecp.app.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

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
@Service
public class CmsCommonUtil {
    private static String MODULE = CmsCommonUtil.class.getName();
    
    private static Long MALLSITEID = 1l;
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
    public static String getClickUrl(Long siteId ,String linkUrl,String linkType,String adid) {
        if(StringUtil.isBlank(linkUrl)){
            return linkUrl;
        }
        if(isNumeric(linkUrl)){
            linkUrl = idToUrlByType(linkUrl, linkType, siteId);
        }
        //加adid
        linkUrl = addExParam(linkUrl, "adid", adid);
        if(linkUrl.indexOf("modularcommon") > 0){//用于促销页标记来自app访问
            linkUrl = addExParam(linkUrl, "displayPlatType", "app");
        }
        
        //格式话url成符合app地址格式
        linkUrl = fomatAppUrl(linkUrl, siteId);
        return linkUrl;
    }
    /**
     * 
     * toUrlByType:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    public static String idToUrlByType(String id,String linkType,Long siteId){
        if(!isNumeric(id) || StringUtil.isBlank(linkType)){
            return id;
        }
        String linkUrl = id;
        if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(linkType)){//商品
            linkUrl="/pmph/jfGoodInfo/pageInit?gdsId="+id;
        }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(linkType)){//公告
            //公告只有商城有
            linkUrl=getMobileMallUrl(MALLSITEID)+"/info/infoDetail4App?id="+id;
        }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(linkType)){//促销
            linkUrl=getMobileMallUrl(siteId)+"/modularcommon/prom/"+id;
        }
        return linkUrl;
    }
    /**
     * 
     * addExParam:(给url加额外的参数 如果是有多个？号 即嵌套型url 则只加在第一级url). 有损拼接 <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param url
     * @param pKey  参数关键字
     * @param pValue 参数值
     * @return 
     * @since JDK 1.6
     */
    public static String addExParam(String url,String pKey,String pValue){
        if(null==url || StringUtil.isBlank(pKey) || StringUtil.isBlank(pValue)){
            return url;
        }
        String noShapUrl ="";//第一个#之前的地址
        String afterShap = "";//第一个#之后的地址 带第一个#
        int indexShap = url.indexOf("#");
        if(0 > indexShap){
            noShapUrl = url;
        }else if(0 == indexShap){
            noShapUrl = "";
            afterShap = url.substring(indexShap);
        }else{
            noShapUrl = url.substring(0,indexShap);
            afterShap = url.substring(indexShap);
        }
        
        String afterQue = "";//第一个？之后的地址  不带第一个？
        String doUrl = "";//第一个？之前的地址  加参数的地方
        int indexQue = noShapUrl.indexOf("?");
        if(0 > indexQue){
            doUrl = noShapUrl;
        }else if(0 == indexQue){
            doUrl = "";
            afterQue = noShapUrl.substring(indexQue+1);
        }else{
            doUrl = noShapUrl.substring(0,indexQue);
            afterQue = noShapUrl.substring(indexQue+1);
        }
        
        String pStr = pKey+"="+pValue;
        
        url = doUrl +"?"+pStr;
        if(StringUtil.isNotBlank(afterQue)){
            url+="&"+afterQue;
        }
        url +=afterShap;
        return url;
    }
    /**
     * 
     * fomatAppUrl:(格式话url成符合app地址格式). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param url
     * @param siteId
     * @return 
     * @since JDK 1.6
     */
    public static String fomatAppUrl(String url,Long siteId){
        url = url.replaceAll("^(/|\\\\)*pmph(/|\\\\)+","/pmph/");
        if(url.startsWith("/pmph/")){//符合条件，无需转化
            return url;
        }
        String regex = "^[a-zA-Z]+:(/|\\\\){2}[a-z0-9A-Z]+";//常见的 绝对url格式  并非全部
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        if(m.lookingAt()){//外网
            url = "/pmph/webview/pageInit?url="+url; 
        }else{//内网
            if(!url.startsWith("/")){
                url = "/" + url;  
            }
            String pcMallUrl = getPcMallSiteUrl(siteId);
            url ="/pmph/webview/pageInit?url="+pcMallUrl+url;
        }
        return url;
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    public static boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
        return false; 
     }
    /**
     * 
     * getPcMallSiteUrl:(获取pc端mall商城地址 过滤掉尾部的/). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    public static String getPcMallSiteUrl(Long siteId){
        if(StringUtil.isEmpty(siteId)){
            return "";
        }
        CmsSiteRespDTO pcMallSiteDto = null;
        try {
            pcMallSiteDto = CmsCacheUtil.getCmsSiteCache(siteId);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取pc商城地址异常");
        }
        String pcMallUrl = "";
        if(null != pcMallSiteDto){
            pcMallUrl = pcMallSiteDto.getSiteUrl();
        }
        pcMallUrl = null == pcMallUrl  ? "" : pcMallUrl.replaceAll("(?:/|\\\\)+$", "");
        return pcMallUrl;
    }
    /**
     * 
     * getMobileMallUrl:(从配置表获取微信端mall商城地址 过滤掉尾部的/). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    public static String getMobileMallUrl(Long siteId){
        if(StringUtil.isEmpty(siteId)){
            return "";
        }
        String mobileMallUrl = "";
        try {
            mobileMallUrl = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING,siteId.toString());//微商地址
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取微信商城地址异常，读取配置表CMS_SITE_MAPPING！");
        }
        mobileMallUrl = mobileMallUrl == null ? "" : mobileMallUrl.replaceAll("(?:/|\\\\)+$", "");
        return mobileMallUrl;
    }
}

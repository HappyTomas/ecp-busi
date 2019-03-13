package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.config.Application;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 通过行为分析获取猜你喜欢工具 <br>
 * Title: ECP <br>
 * Project Name:ecp-web-manage-core <br>
 * Description: <br>
 * Date:2016年7月14日上午9:36:37  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
@Service
public class CmsAnalUtil {
    
    private static String MODULE = CmsAnalUtil.class.getName();
    
    private final static String SERVICE_SUCCESS_CODE = "0000";//远程服务请求成功
    
    //private final static String IMGFORMER= "320x320!";
    
    private final static String GOOD_RANK_TRADE_CODE = "11";//排行榜远程服务请求排行榜类型：11-销量排行，12-浏览量排行 ,13-新书排行（出版一年内书的销售排行）
    
    private final static String GOOD_RANK_PAGE_VIEW_CODE = "12";//排行榜远程服务请求排行榜类型：11-销量排行，12-浏览量排行,13-新书排行（出版一年内书的销售排行）
    
    private final static String GOOD_RANK_NEW_BOOK_CODE = "13";//排行榜远程服务请求排行榜类型：11-销量排行，12-浏览量排行,13-新书排行（出版一年内书的销售排行）
    /**
     * 
     * getAnalysGuessData:(通过用户行为分析获取猜你喜欢数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsNum  查询数量  如果为分页模式   gdaNum为查询最大条数 建议取大一点如5000
     * @param propIds  商品属性id
     * @param isPaging 是否为分页模式
     * @param pageNo 分页模式下有用
     * @param pageSize 分页模式下有用
     * @param request 主要在用户未登陆时用于获取cookieId作为用户id
     * @return Map /String,Object/     "pageInfo":返回商品详情列表分页对象 类型：EcpBasePageRespVO /GdsInfoDetailRespDTO/       "resultCatgs":返回商品分类  类型 List/List/GdsCategoryRespDTO/
     * @throws Exception 
     * @since JDK 1.6
     */
    public static Map<String,Object> getAnalysGuessData(Integer gdsNum,List<Long> propIds,boolean isPaging,Integer pageNo,Integer pageSize,HttpServletRequest request) throws Exception {
        LogUtil.info(MODULE, "调用猜你喜欢行为分析开始：");
        Map<String,Object> resultMap = new HashMap<String, Object>();
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo= new EcpBasePageRespVO<GdsInfoDetailRespDTO>(); 
        List<GdsInfoDetailRespDTO> resultGds = null;
        List<List<GdsCategoryRespDTO>> resultCatgs = null;
        long totalPage = 0l;
        long totalLow = 0l;
        
        //获取行为分析站点的地址
        String url = CmsHttpClientUtil.getGuessAnalysUrl();
        if(StringUtil.isEmpty(url)){
            LogUtil.error(MODULE, "猜你喜欢行为分析地址未配置！");
            throw new Exception("远程请求异常");
        }
        // 创建参数队列
        List<BasicNameValuePair> formparams = getAnalGuessParams(gdsNum, isPaging, pageNo, pageSize, request);
        if(CollectionUtils.isEmpty(formparams)){
            LogUtil.error(MODULE, "调用猜你喜欢行为分析结束：请求参数为空！");
            throw new Exception("远程请求异常");
        }
        // 处理响应内容    
        JSONObject jsonResult = null;
        try {
            jsonResult = JSONObject.fromObject(CmsHttpClientUtil.doRequest(url, formparams));
        } catch (Exception e) {
            LogUtil.error(MODULE,"获取猜你喜欢模块转化为json对象错误：",e);
            throw new Exception("远程请求异常");
        }
        if(jsonResult !=null && jsonResult.containsKey("serviceState")  && SERVICE_SUCCESS_CODE.equalsIgnoreCase(jsonResult.getString("serviceState"))){//请求服务成功
            //转化商品
            //LogUtil.info(MODULE, "调用猜你喜欢行为分析json串："+jsonResult);
            try {
                resultGds = CmsGoodsDetailUtil.analysJsonObjToGdsDetialList(jsonResult, propIds);
                gdsPageInfo.setList(resultGds);
                if(!isPaging){
                    resultCatgs = CmsGoodsDetailUtil.analysJsonObjToGdsCatg(jsonResult);
                }
            } catch (Exception e) {
                LogUtil.error(MODULE, "猜你喜欢远程服务返回json转化数据出现异常！",e);
                throw new Exception("远程请求异常");
            }
            //获取分页商品总条数
            if(jsonResult.containsKey("pageInfo")){
                JSONObject pageInfo = jsonResult.getJSONObject("pageInfo");
                if(pageInfo.containsKey("totalCount")){
                    String totalCount = pageInfo.getString("totalCount");
                    if(CmsGoodsDetailUtil.isNumeric(totalCount)){
                        totalLow = Long.parseLong(totalCount);
                    }
                }
            }
        }else if(jsonResult ==null){
            LogUtil.error(MODULE, "猜你喜欢远程服务返回json为空！");
            throw new Exception("远程请求异常");
        } else {
            if(jsonResult.containsKey("serviceMsg")){
                LogUtil.error(MODULE, jsonResult.getString("serviceMsg"));
            }else{
                LogUtil.error(MODULE, "猜你喜欢远程服务返回json格式错误！");
            }
            throw new Exception("远程请求异常");
        }
        
        //转化分页数据
        if(null == pageNo || 0 >= pageNo){
            pageNo = 1;
        }
        if(!isPaging){
            pageSize = gdsNum; 
        }
        if(null == pageSize || 0 >= pageSize){
            pageSize = 10;
        }
        if(0 == totalLow && null != resultGds){
            totalLow = resultGds.size();
        }
        if(0 < totalLow && 0 < pageSize){
            totalPage = totalLow / pageSize;
            if(0 < totalLow % pageSize){
                totalPage += 1;
            }
        }
        gdsPageInfo.setPageNumber(pageNo);
        gdsPageInfo.setPageSize(pageSize);
        gdsPageInfo.setTotalPage(totalPage);
        gdsPageInfo.setTotalRow(totalLow);
        
        //返回数据
        resultMap.put("pageInfo", gdsPageInfo);
        resultMap.put("resultCatgs", resultCatgs);
        LogUtil.info(MODULE, "调用猜你喜欢行为分析结束：");
        return resultMap;
    }
    /**
     * 
     * getAnalysGuess:(通过用户行为分析获取猜你喜欢数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsNum  查询数量  如果为分页模式   gdaNum为查询最大条数 建议取大一点如5000
     * @param propIds  商品属性id
     * @param isPaging 是否为分页模式
     * @param pageNo 分页模式下有用
     * @param pageSize 分页模式下有用
     * @return Map /String,Object/     "pageInfo":返回商品详情列表分页对象 类型：EcpBasePageRespVO /GdsInfoDetailRespDTO/       "resultCatgs":返回商品分类  类型 List/List/GdsCategoryRespDTO/
     * @throws Exception 
     * @since JDK 1.6
     */
    public static Map<String,Object> getAnalysGuessData(Integer gdsNum,List<Long> propIds,boolean isPaging,Integer pageNo,Integer pageSize) throws Exception {
        return getAnalysGuessData(gdsNum, propIds, isPaging, pageNo, pageSize, null);
    }
    /**
     * 
     * getAnalysGuessList:(通过用户行为分析获取猜你喜欢数据 非分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsNum
     * @param propIds
     * @param request
     * @return  Map /String,Object/     "pageInfo":返回商品详情列表分页对象 类型：EcpBasePageRespVO /GdsInfoDetailRespDTO/       "resultCatgs":返回商品分类  类型 List/List/GdsCategoryRespDTO/
     * @throws Exception 
     * @since JDK 1.6
     */
    public static Map<String,Object> getAnalysGuessData(Integer gdsNum,List<Long> propIds,HttpServletRequest request) throws Exception {
        return getAnalysGuessData(gdsNum, propIds, false, null, null, request);
    }
    /**
     * 
     * getAnalysGuessList:(通过用户行为分析获取猜你喜欢数据 非分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsNum
     * @param propIds
     * @return  Map /String,Object/     "pageInfo":返回商品详情列表分页对象 类型：EcpBasePageRespVO /GdsInfoDetailRespDTO/       "resultCatgs":返回商品分类  类型 List/List/GdsCategoryRespDTO/
     * @throws Exception 
     * @since JDK 1.6
     */
    public static Map<String,Object> getAnalysGuessData(Integer gdsNum,List<Long> propIds) throws Exception {
        return getAnalysGuessData(gdsNum, propIds, false, null, null, null);
    }
    /**
     * 
     * getAnalGuessParams:(获取猜你喜欢行为分析请求参数). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param gdsNum
     * @param isPaging
     * @param pageNo
     * @param pageSize
     * @param request
     * @return 
     * @since JDK 1.6
     */
    public static List<BasicNameValuePair> getAnalGuessParams(Integer gdsNum,boolean isPaging,Integer pageNo,Integer pageSize,HttpServletRequest request) {
        LogUtil.info(MODULE, "获取猜你喜欢行为分析请求参数开始");
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        formparams.add(new BasicNameValuePair("format","JSON"));
        String staffId = getStaffId(request);
        LogUtil.info(MODULE, "猜你喜欢行为分析用户id为："+staffId);
        formparams.add(new BasicNameValuePair("userId",""+staffId));
        if(isPaging){
            formparams.add(new BasicNameValuePair("paging","true"));//启用分页
            if(StringUtil.isNotEmpty(pageNo) && 0 < pageNo){
                formparams.add(new BasicNameValuePair("pageNo", pageNo.toString()));
            }
            if(StringUtil.isNotEmpty(pageSize) && 0 < pageSize){
                formparams.add(new BasicNameValuePair("pageSize", pageSize.toString()));     
            }
        }
        if(StringUtil.isNotEmpty(gdsNum) && 0 < gdsNum){
            formparams.add(new BasicNameValuePair("gdsNum", gdsNum.toString())); 
        }
        LogUtil.info(MODULE, "获取猜你喜欢行为分析请求参数结束：参数为== "+formparams.toString());
        return formparams;
    }
    /**
     * 
     * getAnalRankDataPage:(获取排行榜 行为分析数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageNo  第几页
     * @param pageSize 每页条数
     * @param countType 统计类型
     * @param catgCode 统计对应商品分类
     * @param shopId  店铺id  商城传null
     * @param propIds 商品需要的属性id
     * @param staffId 会根据staffid返回对应会员折扣价
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public static EcpBasePageRespVO<GdsInfoDetailRespDTO> getAnalRankDataPage(Integer pageNo,Integer pageSize,String countType,String catgCode,Long shopId,List<Long> propIds,Long staffId) throws Exception {
        LogUtil.info(MODULE, "调用排行榜行为分析开始：");
        EcpBasePageRespVO<GdsInfoDetailRespDTO> gdsPageInfo= new EcpBasePageRespVO<GdsInfoDetailRespDTO>(); 
        List<GdsInfoDetailRespDTO> gdsDetailInfoList = null;
        long totalPage = 0l;
        long totalLow = 0l;
        
        //1 获取行为分析站点的地址
        String url = CmsHttpClientUtil.getRankAnalysUrl();
        if(StringUtil.isEmpty(url)){
            LogUtil.error(MODULE, "排行榜行为分析地址未配置！");
            throw new Exception("远程请求异常"); 
        }
        //2 创建参数队列
        List<BasicNameValuePair> formparams = getAnalRankParams(pageNo,pageSize, countType,catgCode,shopId);
        if(CollectionUtils.isEmpty(formparams)){
            LogUtil.error(MODULE, "排行榜请求参数为空！");
            throw new Exception("远程请求异常");
        }
        //3 请求远程
        JSONObject jsonResult = null;
        try {
            jsonResult = JSONObject.fromObject(CmsHttpClientUtil.doRequest(url, formparams));
        } catch (Exception e) {
            LogUtil.error(MODULE,"排行榜数据转化为json对象错误",e);
            throw new Exception("远程请求异常"); 
        }
        
        //4 转化商品
        if(null == jsonResult){
            LogUtil.error(MODULE, "排行榜远程服务返回json为空！");
            throw new Exception("远程请求异常"); 
        }else if(jsonResult.containsKey("serviceState")  && SERVICE_SUCCESS_CODE.equalsIgnoreCase(jsonResult.getString("serviceState"))){
            LogUtil.info(MODULE, "调用排行榜行为分析成功");
            try {
                gdsDetailInfoList = CmsGoodsDetailUtil.analysJsonObjToGdsDetialList(jsonResult, propIds,staffId);
            } catch (Exception e) {
                LogUtil.error(MODULE, "排行榜远程服务返回json转化数据出现异常！",e);
                throw new Exception("远程请求异常");
            }
            gdsPageInfo.setList(gdsDetailInfoList);
            //获取分页商品总条数
            if(jsonResult.containsKey("itemCount")){
                String itemCount = jsonResult.getString("itemCount");
                if(CmsGoodsDetailUtil.isNumeric(itemCount)){
                    totalLow = Long.parseLong(itemCount);
                }
            }
        }else if(jsonResult.containsKey("serviceMsg")){
            LogUtil.error(MODULE, jsonResult.getString("serviceMsg"));
            throw new Exception("远程请求异常"); 
        }else{
            LogUtil.error(MODULE, "排行榜调用远程服务未知错误！");
            throw new Exception("远程请求异常"); 
        }
        
        //转化分页数据
        if(null == pageNo || 0 >= pageNo){
            pageNo = 1;
        }
        if(null == pageSize || 0 >= pageSize){
            pageSize = 20;
        }
        if(0 < totalLow && 0 < pageSize){
            totalPage = totalLow / pageSize;
            if(0 < totalLow % pageSize){
                totalPage += 1;
            }
        }
        gdsPageInfo.setPageNumber(pageNo);
        gdsPageInfo.setPageSize(pageSize);
        gdsPageInfo.setTotalRow(totalLow);
        gdsPageInfo.setTotalPage(totalPage);
        
        LogUtil.info(MODULE, "调用排行榜行为分析结束：");
        return gdsPageInfo;
    }
    /**
     * 
     * getAnalRankDataPage:(获取排行榜 行为分析数据 不指定staffId). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageNo  第几页
     * @param pageSize 每页条数
     * @param countType 统计类型
     * @param catgCode 统计对应商品分类
     * @param shopId  店铺id  商城传null
     * @param propIds 商品需要的属性id
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    public static EcpBasePageRespVO<GdsInfoDetailRespDTO> getAnalRankDataPage(Integer pageNo,Integer pageSize,String countType,String catgCode,Long shopId,List<Long> propIds) throws Exception {
        return getAnalRankDataPage(pageNo, pageSize, countType, catgCode, shopId, propIds, null);
    }
    /**
     * 
     * getAnalRankParams:(获取排行榜行为分析请求参数). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    public static List<BasicNameValuePair> getAnalRankParams(Integer pageNo,Integer pageSize,String countType,String catgCode,Long shopId) {
        LogUtil.info(MODULE, "创建排行榜行为分析请求参数开始：");
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if(null != pageSize && 0 < pageSize){
            formparams.add(new BasicNameValuePair("pageSize", pageSize.toString()));
        }
        if(null != pageNo && 0 < pageNo){
            formparams.add(new BasicNameValuePair("pageNumber", pageNo.toString()));
        }
        if(StringUtil.isBlank(countType)){
            formparams.add(new BasicNameValuePair("rankType",GOOD_RANK_TRADE_CODE));//默认销售量
        }else if(CmsConstants.CountType.CMS_COUNT_TYPE_01.equalsIgnoreCase(countType)){//销售量
            formparams.add(new BasicNameValuePair("rankType",GOOD_RANK_TRADE_CODE));
        }else if(CmsConstants.CountType.CMS_COUNT_TYPE_04.equalsIgnoreCase(countType)){//访客量
            formparams.add(new BasicNameValuePair("rankType",GOOD_RANK_PAGE_VIEW_CODE));
        }else if(CmsConstants.CountType.CMS_COUNT_TYPE_05.equalsIgnoreCase(countType)){//新书
            formparams.add(new BasicNameValuePair("rankType",GOOD_RANK_NEW_BOOK_CODE));
        }else{//其他统计类型暂未实现 待后续补充
            LogUtil.info(MODULE, "创建排行榜行为分析请求参数结束：无效的统计类型");
            return null;
        }
        if(StringUtil.isNotBlank(catgCode)){
            formparams.add(new BasicNameValuePair("category",catgCode));
        }
        //获取排行榜商城编码,如果是获取整个商城的排行榜，则传100；如果为店铺，则传店铺ID。
        String mallCode = Application.getValue("xwrankmallcode");
        if(null != shopId){
            formparams.add(new BasicNameValuePair("shopId",shopId.toString()));
        }else if(StringUtil.isNotBlank(mallCode)){
            formparams.add(new BasicNameValuePair("shopId",mallCode));
        }else{
            //采用接口默认值
        }
        
        LogUtil.info(MODULE, "创建排行榜行为分析请求参数结束：：参数为== "+formparams.toString());
        return formparams;
    }
    /**
     * 
     * getStaffId:(获取用户id). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param request  
     * @return String 用户id   当用户未登陆时如果有request  则取request中的cksid  否则返回0
     * @since JDK 1.6
     */
    public static String getStaffId(HttpServletRequest request) {
        String staffId= null;
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        BaseStaff staffInfo= custInfoReqDTO.getStaff();
        if(staffInfo == null){
            staffInfo = new BaseStaff();
        }
        if(StringUtil.isNotEmpty(staffInfo.getId()) && 0 < staffInfo.getId()){
            staffId = new Long(staffInfo.getId()).toString();
        }else{//未登陆
            Cookie cksIdCks = null;
            if(null != request){
                cksIdCks = WebUtils.getCookie(request, "cksid");
            }
            if(null != cksIdCks && StringUtil.isNotBlank(cksIdCks.getValue())){
                staffId = cksIdCks.getValue();
            }else{
                staffId = "0";
            }
        }
        
        return staffId;
    }
    /**
     * 
     * getStaffId:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return String 用户id   当用户未登陆时返回0
     * @since JDK 1.6
     */
    public static String getStaffId() {
        return getStaffId(null);
    }
}


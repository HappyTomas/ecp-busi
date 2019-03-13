package com.zengshi.ecp.system.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.zengshi.ecp.order.dubbo.dto.ROrdMainResponse;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.alibaba.dubbo.common.utils.StringUtils;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.base.velocity.ParamToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.config.Application;

public class ParamsTool {

    public static final String ONLINE_ORDER_ID_FLAG = ":";

    public static final Long POINT_SITE_ID = 2L;
    
    /** 
     * getSiteId: <br/> 
     * TODO(APP端获取商城站点ID).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    public static Long getSiteId(){
        return 1L;
    }
    
    /**
     * getImageUrl:(根据上传到mongoDB的图片ID 从mongoDB上获取图片路径). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param vfsId
     *            图片ID
     * @param param
     *            图片规格
     * @return
     * @since JDK 1.6
     */
    public static String getImageUrl(String vfsId, String param) {
        StringBuilder sb = new StringBuilder();
        //入参的图片ID为空，那么使用默认图片；
        if(StringUtil.isBlank(vfsId)){
            sb.append(ImageUtil.getDefaultImageId());
        } else {
            sb.append(vfsId);
        }
        
        if (StringUtil.isNotBlank(param)) {
            sb.append("_").append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
    

    //补全分页参数
    /**
     * 
     * checkPage:(补全分页参数). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wang 
     * @param rdors
     * @return 
     * @since JDK 1.6
     */
    public static PageResponseDTO<? extends BaseResponseDTO> checkPage(PageResponseDTO<? extends BaseResponseDTO> rdors){ 
        BaseInfo baseInfo = new BaseInfo();
        PageResponseDTO<? extends BaseResponseDTO> pageResponse = PageResponseDTO.buildByBaseInfo(
                baseInfo, BaseResponseDTO.class);
        pageResponse.setResult(null);
        return pageResponse;
    }
    

    // 进度条相关 每个编码属于哪个状态
    public static Map<String, Integer> getStatusMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("01", 1);
        map.put("02", 2);
        map.put("04", 2);
        map.put("05", 3);
        map.put("06", 4);
        map.put("80", 5);
        return map;
    }

    // 进度条相关底下的展示除了完成之外依次的状态
    public static List<String> getStatusList() {
        List<String> statuslist = new ArrayList<String>();
        statuslist.add(0, "01");
        statuslist.add(1, "02");
        statuslist.add(2, "05");
        statuslist.add(3, "06");
        return statuslist;
    }
    
    /**
     * 获取客户端地址
     * @param request
     * @return
     */
    public static String getClientAddr(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * data 默认时间初始化
     * @return
     */
    public static Map<String,Timestamp> params(){
        Map<String,Timestamp> params = new HashMap<String,Timestamp>();
        params.put("begDate",new Timestamp(DateUtils.addYears(new Date(), -1).getTime()));
        params.put("endDate",new Timestamp(DateUtils.addDays(new Date(), 0).getTime()));
        return params;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static EcpBasePageRespVO<Map> ordDetailSiteUrl(EcpBasePageRespVO<Map> respVO){ 
        List<Map> respList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(respVO.getList())){
            for(Map m:respVO.getList()){
                Map respM = new HashMap();
                respM.putAll(m);
                String ordDetailUrl = "";
                if(StringUtil.isNotEmpty(m.get("siteId")) && StringUtil.isNotEmpty(m.get("orderId"))){
                    ordDetailUrl = getOrdDetailUrl(Long.valueOf(m.get("siteId").toString()),(String)m.get("orderId"));
                }else if(StringUtil.isNotEmpty(m.get("siteId")) && StringUtil.isNotEmpty(m.get("id"))){
                    ordDetailUrl = getOrdDetailUrl(Long.valueOf(m.get("siteId").toString()),(String)m.get("id"));
                }
                respM.put("ordDetailUrl", ordDetailUrl);
                respList.add(respM);
            }
        }
        respVO.setList(respList);
        return respVO;
    }
    /**
     * 加支付方式返回
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static EcpBasePageRespVO<Map> setApplyType(EcpBasePageRespVO<Map> respVO){
        List<Map> respList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(respVO.getList())){
            for(Map m:respVO.getList()){
                Map respM = new HashMap();
                respM.putAll(m);
                String applyType = "";
                if(!CollectionUtils.isEmpty(m)) applyType = (String)m.get("applyType");
                respM.put("applyType", applyConfig().get(m.get("applyType")));
                respList.add(respM);
            }
        }
        respVO.setList(respList);
        return respVO;
    }
    /**
     * 支付方式配置
     */
    public static Map<String,String> applyConfig(){
        List<BaseParamDTO> payList = new ParamToolUtil().getParamDTOList("ORD_PAY_TYPE");
        Map<String,String> m = new HashMap<>();

        for(BaseParamDTO pay: payList){
            m.put(pay.getSpCode(),pay.getSpValue());
        }

        return m;
    }

    /**页面title
     * Title: ECP <br>
     * Project Name:ecp-web-mall <br>
     * Description: <br>
     * Date:2015年10月29日下午4:27:42  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author jiangzh
     * @version ParamsTool 
     * @since JDK 1.6 
     */  
    public static class PageTitle {
    	public static final String HOMEPAGE = Application.getValue("homePageTitle");
        public static final String TESTBOOK = Application.getValue("testbook");
        public static final String SCIENCEBOOK =  Application.getValue("sciencebook");
        public static final String REFERENCEBOOK = Application.getValue("referencebook");
        public static final String PAPERBOOK =  Application.getValue("paperbook");
        public static final String AUDIOBOOK = Application.getValue("audiobook");
        public static final String DIGITALPRODUCT = Application.getValue("digitalproduct");
        public static final String DIGITALBOOK = Application.getValue("digitalbook");
        public static final String ONLINETEST =  Application.getValue("onlinetest");
        public static final String ELECTRONICBOOK = Application.getValue("electronicbook");
        
    }
    
    public static class Page {
        public static final String HOMEPAGE = "homepage";
        public static final String TESTBOOK = "testbook";
        public static final String SCIENCEBOOK = "sciencebook";
        public static final String REFERENCEBOOK = "referencebook";
        public static final String PAPERBOOK = "paperbook";
        public static final String AUDIOBOOK = "audiobook";
        public static final String DIGITALBOOK = "digitalbook";
        public static final String DIGITALPRODUCT = "digitalproduct";
        public static final String ONLINETEST = "onlinetest";
        public static final String ELECTRONICBOOK = "electronicbook";
        public static final String REGISTER_PAGE = "url.registerPage";
        public static final String LOSTPASSWORD_PAGE = "url.lostpasswordPage";
        public static final String UPDATEPASSWORD_PAGE ="url.updatepasswordPage";
        public static final String POINT_URL ="url.point";
        public static final String SELLER_ERROR="url.sellerError";
    }
    
    
    /**
     * 订单详情页面地址前缀
     */
    public static final String DETAIL_PREX="/seller/order/orderdetail/detail/";
    
    /**
     * 
     * getOrdDetailUrl:获取订单详情URL. <br/> 
     * 
     * @author liyong7
     * @param siteId
     * @param orderId
     * @return String
     * @since JDK 1.6
     */
    public static String getOrdDetailUrl(Long siteId,String orderId){
        String detailUrl = CmsCacheUtil.getCmsSiteCache(siteId).getSiteUrl()+DETAIL_PREX+orderId;
        return detailUrl;
    }
    
    /**
     * 
     * getPhoneCheckCode:(获取手机短信发送的验证码). <br/> 
     * 
     * @author huangxl5
     * @param request
     * @return 
     * @since JDK 1.6
     */
    public static String getPhoneCheckCode (HttpServletRequest request) {
        /*取出缓存中的验证码*/
        CustInfoReqDTO custInfoReq = new CustInfoReqDTO();
        String phoneKey = "";
        
        /*如果用户有登录，则取用户id加前缀作为key，取缓存值，如果未登录，则取sessionId加前缀作为key，取缓存值*/
        if (custInfoReq.getStaff().getId() != 0L) {
            phoneKey = StaffConstants.Msg.PHONE_CHECK_PRE + custInfoReq.getStaff().getId();
        } else {
            String sessionId = request.getSession().getId();
            phoneKey = StaffConstants.Msg.PHONE_CHECK_PRE + sessionId;
        }
        Object cacheCheckCode = CacheUtil.getItem(phoneKey);
        return String.valueOf(cacheCheckCode);
    }
    
    /**
     * 
     * removePhoneCheckCode:(移除缓存中的验证码). <br/> 
     * 
     * @author huangxl5
     * @param request 
     * @since JDK 1.6
     */
    public static void removePhoneCheckCode (HttpServletRequest request) {
        /*移除缓存中的验证码*/
        CustInfoReqDTO custInfoReq = new CustInfoReqDTO();
        String phoneKey = "";
        
        /*如果用户有登录，则取用户id加前缀作为key，移除缓存值，如果未登录，则取sessionId加前缀作为key，移除缓存值*/
        if (custInfoReq.getStaff().getId() != 0L) {
            phoneKey = StaffConstants.Msg.PHONE_CHECK_PRE + custInfoReq.getStaff().getId();
        } else {
            String sessionId = request.getSession().getId();
            phoneKey = StaffConstants.Msg.PHONE_CHECK_PRE + sessionId;
        }
        CacheUtil.delItem(phoneKey);
    }

    /**
     * 线上支付合并支付 session存取值针对orderIds
     */
    public static String buildOnlineOrderIds(List<ROrdMainResponse> orderList){
        String orderIds = "";
        if(!CollectionUtils.isEmpty(orderList)){
            for(ROrdMainResponse payOrd:orderList){
                orderIds += payOrd.getId()+ONLINE_ORDER_ID_FLAG;
            }
        }
        return orderIds;
    }

    //使用velocity宏的工具类方法
    public static String translate(String name, String code) {
        return !StringUtils.isEmpty(name) && !StringUtils.isEmpty(code)? BaseParamUtil.fetchParamValue(name, code):"";
    }

    //使用velocity宏展示金额的工具类
    public static String showMoneyByTwoDecimal(String account) {
        DecimalFormat doubleFormatter = new DecimalFormat("#0.00");
        if(org.apache.commons.lang.StringUtils.isEmpty(account)) {
            return "0.00";
        } else if("0".equals(account)) {
            return "0.00";
        } else {
            double number = 0.0D;

            try {
                number = Double.parseDouble(account);
            } catch (Exception var5) {
                return account;
            }

            return doubleFormatter.format(number / 100.0D);
        }
    }
    
    /**
     * 获取cookie中的sessionId
     * @param request
     * @return
     */
	  public static String getCookSessionId(HttpServletRequest request){
		  Cookie[] cookies = request.getCookies();
		  
			if ((cookies == null) || (cookies.length == 0))
				return null;
			//String value=null;
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName()+":"+cookie.getValue());
				if ("ECP_JSESSIONIDS".equals(cookie.getName())){
					System.out.println(cookie.getName()+":"+cookie.getValue());
				    return cookie.getValue();
				}
			}
			return null;
	  }

}


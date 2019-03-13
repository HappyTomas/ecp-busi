package com.zengshi.ecp.system.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;

import com.zengshi.ecp.busi.order.vo.RQueryOrderReqVO;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;

public class ParamsTool {
    
    public static final int ORDER_SESSION_TIME = 1800;
    
    public static final String ORDER_SESSION_KEY_PREFIX = "ORD_CAR_LIST";
    
    public static List<Long> getOrdSubMoneys(PageResponseDTO<RCustomerOrdResponse> rdors){
    	List<Long> ordsubmoneys = new ArrayList<Long>();
		if(rdors.getResult()!=null){
        	for(RCustomerOrdResponse ord : rdors.getResult()){
            	Long ordsubmoney = 0l;
            	for(SOrderDetailsSub ordSub : ord.getsOrderDetailsSubs()){
            		ordsubmoney += ordSub.getBuyPrice()*ordSub.getOrderAmount();
            	}
            	ordsubmoneys.add(ordsubmoney);
            }
        }
		return ordsubmoneys;
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
            sb.append("_");
            sb.append(param);
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
    
    public static Map<String,Timestamp> params(RQueryOrderReqVO vo){
        Map<String,Timestamp> params = new HashMap<String,Timestamp>();
        if(vo.getBegDate()==null||vo.getEndDate()==null){
            params.put("begDate",new Timestamp(DateUtils.addYears(new Date(), -1).getTime()));
            params.put("endDate",new Timestamp(DateUtils.addDays(new Date(), 1).getTime()));
        }else{
            params.put("begDate",new Timestamp(vo.getBegDate().getTime()));
            params.put("endDate", new Timestamp(vo.getEndDate().getTime()));
        }
        return params;
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
    
    
    public static class Page {
        public static final String REGISTER_PAGE = "url.registerPage";
        public static final String LOSTPASSWORD_PAGE = "url.lostpasswordPage";
        public static final String UPDATEPASSWORD_PAGE ="url.updatepasswordPage";
    }
}


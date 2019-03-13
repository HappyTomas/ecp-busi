package com.zengshi.aip.util;

import java.util.HashMap;
import java.util.Map;


/**
 * @category 业务上使用的常量定义
 * @author zhanglei11
 *
 */
public class ConstantsForBusiness {
	/**
	 * 
	 * @author jonrey
	 * 对购物车使用的
	 */
	public static class shopCartInfo{
		
		public final static String CARTTYPE = "cartType";//定义买家购买类型
		public final static String CARTTYPE_NOM = "01";//普通实例
		public final static String CARTTYPE_MEU = "02";//自由组合实例
		public final static String CARTTYPE_YS = "03";//预售实例
		public final static String CARTTYPE_DJ = "04";//定金实例
	}
	
	/**
	 * 
	 * @author linww
	 * 提货方式
	 */
	public static class shipDetail{
		public final static String DEVERLY_PICK = "0";//提货方式：自提
		public final static String DEVERLY_LOGISTICS="1";//提货方式：物流
	}
	/**
	 * @category 提交订单
	 * @author jonrey--zhanglei
	 *
	 */
	public static class submitOrder{
		
		public final static String PREORDERINFO = "PREORDERINFO";//生成的预订单信息List<PreOrdInfo>
		public final static String INVOICEFLAG_YES = "1";//是否维护增值税发票，1 是，0 否
		public final static String INVOICEFLAG_NO = "0";
		public final static String DELIVERYFLAG_YES = "2";//是否自提，2 自提，1物流
		public final static String DELIVERYFLAG_NO = "1";
		public final static String ORDERLIST = "ORDERLIST";//生成订单返回的订单列表List<OrderSumbitVO>
		public final static String CARTINFO = "CARTINFO";//购物车信息
	}
	
	/**
	 * 人员角色
	 * @author lixc
	 *
	 */
	public static class Role{
		/**
		 * 联通人员
		 */
		public final static String UNICOM_ROLE = "10";
		/**
		 * 供货商
		 */
		public final static String SUPPIER_ROLE = "20";
		/**
		 * 本网渠道商
		 */
		public final static String OUR_DISTRIBUTES_ROLE = "30";
		/**
		 * 体验渠道商
		 */
		public final static String EXP_DISTRIBUTES_ROLE = "40";
	}
	
	/**
	 * 工号级别
	 * @author lixc
	 *
	 */
	public static class StaffLevel{
		/**
		 * 全国
		 */
		public final static String COUNTRY_LEVEL = "01";
		/**
		 * 省
		 */
		public final static String PROVINCE_LEVEL = "02";
		/**
		 * 地市
		 */
		public final static String CITY_LEVEL= "03";
		/**
		 * 区县
		 */
		public final static String COUNTY_LEVEL= "04";
		/**
		 * 个人
		 */
		public final static String PERSONAL_LEVEL = "05";
	}
	
	/**
	 * 预售方式
	 * @author lixc
	 *
	 */
	public static class PresaleMode{
		/**
		 * 支付定金支付
		 */
		public static final String DEPOSIT_PAYMENT = "01";
		/**
		 * 全额支付
		 */
		public static final String FULL_PAYMENT = "02";
	}
	
	public static Map<String, String> ORDER_STATUS_MAP;
	
	public static Map<String, String> ROLE_MAP;
	
	public static Map<String, String> CHNL_MAP;
	
	public static Map<String, String> getOrderStatusMap(){
	    if(ORDER_STATUS_MAP == null){
	        ORDER_STATUS_MAP = new HashMap<String, String>();
	        ORDER_STATUS_MAP.put("01", "订单提交");
	        ORDER_STATUS_MAP.put("02", "订单已支付");
	        ORDER_STATUS_MAP.put("03", "产品已打包发出，待录入物流单号");
	        ORDER_STATUS_MAP.put("04", "全部发货");
	        ORDER_STATUS_MAP.put("05", "部分发货");
	        ORDER_STATUS_MAP.put("06", "已收货");
	        ORDER_STATUS_MAP.put("09", "申请退货");
	        ORDER_STATUS_MAP.put("10", "同意退货");
	        ORDER_STATUS_MAP.put("11", "拒绝退货");
	        ORDER_STATUS_MAP.put("80", "关闭");
	        ORDER_STATUS_MAP.put("99", "取消");
	    }
	    return ORDER_STATUS_MAP;
	}

	
	public static Map<String, String> getRoleMap(){
        if(ROLE_MAP == null){
            ROLE_MAP = new HashMap<String, String>();
            ROLE_MAP.put(Role.UNICOM_ROLE, "联通人员");
            ROLE_MAP.put(Role.SUPPIER_ROLE, "供货商");
            ROLE_MAP.put(Role.OUR_DISTRIBUTES_ROLE, "本网渠道商");
            ROLE_MAP.put(Role.EXP_DISTRIBUTES_ROLE, "体验渠道商");
        }
        return ROLE_MAP;
    }
	
	public static Map<String,String> getChnlMap(){
	    if(CHNL_MAP == null){
	        CHNL_MAP = new HashMap<String, String>();
	        CHNL_MAP.put("1", "本网渠道");
	        CHNL_MAP.put("2", "体验渠道");
	    }
	    return CHNL_MAP;
	}
}

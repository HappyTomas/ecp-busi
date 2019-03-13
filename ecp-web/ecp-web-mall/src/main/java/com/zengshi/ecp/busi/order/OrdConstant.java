package com.zengshi.ecp.busi.order;

import com.zengshi.ecp.order.dubbo.util.OrdConstants;

public class OrdConstant {
	
    public static final int ORDER_SESSION_TIME = 1800;
    public static final int APP_ORDER_SESSION_TIME = 1800;
    public static final int APP_PAY_SESSION_TIME = 86400;


    public static final String ORDER_SESSION_KEY_PREFIX = OrdConstants.Common.ORDER_SESSION_KEY_PREFIX;

    public static final String ORDER_FAST_KEY_PREFIX = OrdConstants.Common.ORDER_FAST_KEY_PREFIX;

    public static final String ORDER_SESSION_KEY_SUB = "ORD_SUB_LIST";
  
    public static final String ORDER_PAY_TYPE = "ORD_PAY_TYPE";
    
    public static final String ORDER_COUPCODE_PREFIX = "ORD_COUP_CODE";
    
    public static final String ORDER_ENTITY_TYPE = "1";
    
    
    public static class OnlineOrd {
    	public static String ORDER_ONLINE_KEY = "onlineOrd";
    }
    
	public static class PaytStatus
    {
 	  
        public static String PAY_ONLINE = "0";
        public static String PAY_ONLINE_TEXT = "线上支付";
       
        public static String PAY_OFFLINE = "1";
        public static String PAY_OFFLINE_TEXT = "线下支付";
       
        public static String PAY_BYOWN = "2";
        public static String PAY_BYOWN_TEXT = "上门支付";
    }
	 
	public static class DeliverType
	{
		public static String POSTAGE = "0";
		 
		public static String EXPERSS = "1";
		 
		public static String BYOWN = "2";
	}
    public static class InvoiceType
    {
        public static String COMM = "0";
        public static String TAX = "1";
        public static String NO = "2";
    }
    public static class ChkOrdStatus
    {
        public static String SEND = "02";

        public static String CHECK = "05";
    }
}

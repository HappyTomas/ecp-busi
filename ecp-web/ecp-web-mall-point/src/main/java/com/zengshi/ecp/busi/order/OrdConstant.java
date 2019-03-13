package com.zengshi.ecp.busi.order;

public class OrdConstant {
    
    public static final int ORDER_SESSION_TIME = 1800;
    
    public static final int APP_ORDER_SESSION_TIME = 1800;
    public static final int APP_PAY_SESSION_TIME = 86400;
    
    //测试rebase会不会修改index
    
    public static final String ORDER_SESSION_KEY_PREFIX = "ORD_CAR_LIST";
    
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
}

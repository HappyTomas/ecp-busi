package com.zengshi.ecp.order.dubbo.util;

public class CommonConstants {

    public static class LockType {
        //发货
        public static String LOCK_TYPE_DELY = "001";
        
        //提交订单
        public static String LOCK_TYPE_SUBMIT = "002";
        
        //退货申请
        public static String LOCK_BACK_GDS_APPLY = "003";
        
        //退款申请
        public static String LOCK_BACK_MONEY_APPLY = "004";
        
        //退货、退款确认支付
        public static String LOCK_BACK_PAYED = "005";
        
        //退货、退款审核
        public static String LOCK_BACK_REVIEW = "006";
        
        //取消订单
        public static String LOCK_TYPE_REMOVE = "007";

        //加入购物车
        public static String LOCK_TYPE_ADD_CART = "008";
    }
    
    public static class SOURCE {
        // 来源---商城
        public static String SOURCE_WEB = "1";

        // 来源---APP
        public static String SOURCE_APP = "2";
        
        // 来源---其它
        public static String SOURCE_OTH = "3";
    }
}


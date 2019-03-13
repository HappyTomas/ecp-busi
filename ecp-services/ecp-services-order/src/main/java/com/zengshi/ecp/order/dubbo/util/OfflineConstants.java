package com.zengshi.ecp.order.dubbo.util;

public class OfflineConstants {
//    0待供货商审核 1 待联通审核 2供货商审核不通过 3联通审核通过 4联通审核不通过
  //实体编码 新增和修改批次 状态
    public static class Status {
        /** 
         * IMPORT_STATUS_INPUT:待卖家审核. 
         * @since JDK 1.6 
         */ 
        public static String STATUS_SHOP_WAIT = "0";
        /** 
         * IMPORT_STATUS_PRO:卖家审核通过. 
         * @since JDK 1.6 
         */ 
        public static String STATUS_SHOP_PASS = "1";
        
        /** 
         * IMPORT_STATUS_FAIL:卖家审核不通过. 
         * @since JDK 1.6 
         */ 
        public static String STATUS_SHOP_NOT = "2";
        
        /** 
         * STATUS_SHOP_DEL:删除申请记录. 
         * @since JDK 1.6 
         */ 
        public static String STATUS_SHOP_DEL = "9";
    }
}


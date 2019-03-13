package com.zengshi.ecp.order.dubbo.util;

public class DelyConstants {
      
    
    //实体编码 新增和修改批次 状态
    public static class ImportStatus {
        /** 
         * IMPORT_STATUS_INPUT:初录或初导. 
         * @since JDK 1.6 
         */ 
        public static String IMPORT_STATUS_INPUT = "01";
        /** 
         * IMPORT_STATUS_PRO:处理中. 
         * @since JDK 1.6 
         */ 
        public static String IMPORT_STATUS_PRO = "02";
        
        /** 
         * IMPORT_STATUS_FAIL:校验失败. 
         * @since JDK 1.6 
         */ 
        public static String IMPORT_STATUS_FAIL = "03";
        
        /** 
         * IMPORT_STATUS_SUCCESS:校验通过. 
         * @since JDK 1.6 
         */ 
        public static String IMPORT_STATUS_SUCCESS = "04";
        /** 
         * IMPORT_STATUS_SUBMIT:已提交. 
         * @since JDK 1.6 
         */ 
        public static String IMPORT_STATUS_SUBMIT = "05";
        /** 
         * IMPORT_STATUS_DELETE:已删除. 
         * @since JDK 1.6 
         */ 
        public static String IMPORT_STATUS_DELETE = "06";
    }
    
    public static class FromId {
        /** 
         * FROM_ID_ADD_INPUT:录入实体编号. 
         * @since JDK 1.6 
         */ 
        public static String FROM_ID_ADD_INPUT = "0";
        /** 
         * FROM_ID_ADD_IMPORT:导入实体编号. 
         * @since JDK 1.6 
         */ 
        public static String FROM_ID_ADD_IMPORT = "1";
        /** 
         * FROM_ID_CHANGE_INPUT:录入变更实体编号. 
         * @since JDK 1.6 
         */ 
        public static String FROM_ID_CHANGE_INPUT = "2";
        /** 
         * FROM_ID_CHANGE_IMPORT:导入变更实体编号. 
         * @since JDK 1.6 
         */ 
        public static String FROM_ID_CHANGE_IMPORT = "3";
    }
    public static class FromType{
        /** 
         * FROM_TYPE_IMPORT:文件导入变更. 
         * @since JDK 1.6 
         */ 
        public static String FROM_TYPE_IMPORT = "0";
        /** 
         * FROM_TYPE_INPUT:页面录入变更. 
         * @since JDK 1.6 
         */ 
        public static String FROM_TYPE_INPUT = "1";
    }
    public static class IsDelyImportEntity {
        /** 
         * IS_DELY_IMPORT_ENTITY_FLASE:不需要发货的时候导入实体编号. 
         * @since JDK 1.6 
         */ 
        public static String IS_DELY_IMPORT_ENTITY_FLASE = "0";
        /** 
         * IS_DELY_IMPORT_ENTITY_TRUE:需要发货的时候导入实体编号. 
         * @since JDK 1.6 
         */ 
        public static String IS_DELY_IMPORT_ENTITY_TRUE = "1";
    }
    
    public static class DeliverStatus{
        /** 
         * DELIVER_STATUS_FLASE:未发货或部分发货. 
         * @since JDK 1.6 
         */ 
        public static String DELIVER_STATUS_FLASE = "0";
        /** 
         * DELIVER_STATUS_TRUE:已发货. 
         * @since JDK 1.6 
         */ 
        public static String DELIVER_STATUS_TRUE = "1";
    }
}


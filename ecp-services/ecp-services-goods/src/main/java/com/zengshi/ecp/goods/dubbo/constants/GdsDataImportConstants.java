package com.zengshi.ecp.goods.dubbo.constants;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 数据割接常量类<br>
 * Date:2015年10月26日下午8:06:18 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public final class GdsDataImportConstants {
    /**
     * 隐藏实现，防止初始化
     */
    private GdsDataImportConstants() {
    }

    /**
     *
     * Title: 通用常量 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月11日上午10:29:49 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     *
     * @author huangdf
     * @version
     * @since JDK 1.6
     */
    public static class Commons {

        /**
         * 分类映射默认未映射编码.
         */
        public final static String DEFAULT_MAP_CATG_CODE = "-1";

        public final static String ROOT_CATG_PARENT_CODE = "-1";
        
        /**
         * 是否允许更新图片键名称.
         */
        public final static String IF_ALLOW_UPDATE_IMAGE = "$IF_ALLOW_UPDATE_IMAGE$";
        
        /**
         * 是否忽略写入关系映射表.
         */
        public final static String IS_IGNORE_WRITE_REL = "$IS_IGNORE_WRITE_REL$";

    }


    public static class ActionType {

        /**
         * 新增。
         */
        public static final int ADD = 1;

        /**
         * 删除。
         */
        public static final int DELETE = 2;

        /**
         * 修改。
         */
        public static final int UPDATE = 3;
    }

    

}

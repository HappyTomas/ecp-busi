package com.zengshi.ecp.goods.dubbo.constants;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年10月30日上午9:41:28  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public final class GdsDataImportErrorConstants {
    
    /**
     * 隐藏实现，防止初始化
     */
    private GdsDataImportErrorConstants() {
    }
    public static class Commons {

        /**
         * 操作模式不合法
         */
        public static final String ERROR_GOODS_DATAIMPORT_1 = "error.goods.dataimport.1";

        /**
         * 操作模式是【删除】，但是商品编码映射表中找不到对应的原始记录
         */
        public static final String ERROR_GOODS_DATAIMPORT_2 = "error.goods.dataimport.2";

        /**
         * 操作模式是【更新】，但是商品编码映射表中找不到对应的原始记录
         */
        public static final String ERROR_GOODS_DATAIMPORT_3 = "error.goods.dataimport.3";

        /**
         * 操作模式是【下架】，但是商品编码映射表中找不到对应的原始记录
         */
        public static final String ERROR_GOODS_DATAIMPORT_4 = "error.goods.dataimport.4";

        /**
         * 主键值为空
         */
        public static final String ERROR_GOODS_DATAIMPORT_5 = "error.goods.dataimport.5";

        /**
         * 根据原始商品编码或原始分类编码映射到了多个商品编码或分类编码
         */
        public static final String ERROR_GOODS_DATAIMPORT_13 = "error.goods.dataimport.13";

        /**
         * 根据ECP商品编码或ECP分类编码映射到了多个外系统原始商品编码或外系统原始分类编码
         */
        public static final String ERROR_GOODS_DATAIMPORT_14 = "error.goods.dataimport.14";
        

    }

}


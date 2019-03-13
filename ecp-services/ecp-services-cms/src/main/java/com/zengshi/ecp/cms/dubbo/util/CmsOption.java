package com.zengshi.ecp.cms.dubbo.util;

import java.io.Serializable;

/**
 * cms查询枚举
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server-core <br>
 * Description: <br>
 * Date:2016年7月12日上午9:43:13  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsOption implements Serializable{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -358316861732284283L;

    /**
     * 查询楼层信息的选项
     * Title: ECP <br>
     * Project Name:ecp-services-cms-server-core <br>
     * Description: <br>
     * Date:2016年7月12日上午9:47:06  <br>
     * Copyright (c) 2016 ZengShi All Rights Reserved <br>
     * 
     * @author zhanbh
     * @version CmsOption 
     * @since JDK 1.6
     */
    public enum FloorQueryOption  implements Serializable{
        /**
         * 楼层广告
         */
        ADVERTISE("Advertise"),
        /**
         * 楼层优惠券
         */
        COUPON("Coupon"),
        /**
         * 楼层商品
         */
        GDS("Gds"),
        /**
         * 楼层标签
         */
        LABEL("Label"),
        /**
         * 楼层页签
         */
        TAB("Tab");
        /**
         * 枚举编码值
         */
        private final String code;
        
        /**
         * 
         * 构造函数 
         * 
         * @param code
         */
        private FloorQueryOption(final String code){
            this.code=code.intern();
        }
        
        public String getCode(){
            return this.code;
        }
    }
}


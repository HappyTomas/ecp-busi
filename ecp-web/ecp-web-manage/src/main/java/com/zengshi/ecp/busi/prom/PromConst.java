package com.zengshi.ecp.busi.prom;

import com.zengshi.ecp.prom.dubbo.util.PromConstants;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-19 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromConst extends PromConstants{

     public static final String STATUS_99="99";//全部
     public static final String SPILT_FH=",";//英文分号
     public static final String SPILT_HX="-";//英文分号
     
     public static final String IS_CHECK_STOCK="1";//1 需要验证库存 0 空 不需要验证
     
     public static final String DO_ACTION_VIEW="view";//促销查阅
     public static final String DO_ACTION_EDIT="edit";//促销编辑
     public static final String DO_ACTION_CREATE="create";//促销创建
     public static final String DO_ACTION_COPY="copy";//促销copy
     
     public static final String PG_DETAIL="detail";//促销主题判断是否是详情按钮
     public static final String PG_Status_2="2";//促销信息是草稿模式
     public static final String PG_STATUS_0="0";//失效
     
     public static final String PROM_TYPE_CODE_1000000011="1000000011";//表：prom_type_code=1000000011 自由搭配
     public static final String PROM_TYPE_CODE_1000000012="1000000012";//表：prom_type_code=1000000012 捆绑套餐 固定搭配
     public static final String PROM_TYPE_CODE_1000000013="1000000013";//表：prom_type_code=1000000013 自由搭配B
     public static final String PROM_TYPE_CODE_1000000014="1000000014";//表：prom_type_code=1000000014 固定搭配
     public static final String PROM_TYPE_CODE_1000000016="1000000016";//表：prom_type_code=1000000016 免邮费
     
     public static final String PROM_RULE_IF_HIDE="PROM_RULE_IF_HIDE";//是否促销规则免邮 展示key
     
     //客户模块相关参数
     public class StaffModel {
        public static final String STAFF_CUST_LEVEL="STAFF_CUST_LEVEL";
        public static final String STAFF_CUST_GROUP="STAFF_CUST_GROUP";
     }
     //sys模块相关参数
     public class SysModel {
        public static final String COUNTRY_DEFAULT="156";//默认中国
     }
     //cms模块相关参数
     public class CmsModel {
        public static final String SITE_KEY="SITE_KEY";//站点
     }

}

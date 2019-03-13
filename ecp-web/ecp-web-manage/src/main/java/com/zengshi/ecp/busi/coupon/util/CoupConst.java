package com.zengshi.ecp.busi.coupon.util;

import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;


/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-22 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupConst extends CouponConstants{

     public static final String STATUS_99="99";//全部
     public static final String SPILT_FH=",";//英文分号
     public static final String SPILT_HX="-";//英文分号
     
     public static final String DO_ACTION_VIEW="view";//查阅
     public static final String DO_ACTION_EDIT="edit";//编辑
     public static final String DO_ACTION_CREATE="create";//创建
     public static final String DO_ACTION_COPY="copy";//copy
     
     // 1 无发行量 coupNum==null  0有发行量 coupNum 必需大于0
     public static final String CHECK_PUBLISH_1="1";//无发行量限制
     public static final String CHECK_PUBLISH_0="0";//有发行量 限制
     
     public static final String RECEIVE_KEY_NUM="num";// key num-01
     
     //优惠券小类不展示面额字段  对应表t_base_sys_cfg key
     public static final String COUP_PARAM_NO_SHOW_VALUE="COUP_PARAM_NO_SHOW_VALUE";
     
     public static final String STAFF_CUST_LEVEL="STAFF_CUST_LEVEL";
     public static final String STAFF_CUST_GROUP="STAFF_CUST_GROUP";

}

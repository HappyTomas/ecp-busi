package com.zengshi.ecp.busi.unpf;


/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-11-9 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author lisp
 */
public class UnpfConst{

	 public static final String UNPF_PLAT_TYPE="UNPF_PLAT_TYPE";//平台类型UNPF_GDS_SEND_STATUS
	 public static final String UNPF_GDS_SEND_STATUS="UNPF_GDS_SEND_STATUS";
     public static final String STATUS_99="99";//全部
     // 状态，0失效，1生效
     public static final String STATUS_0 = "0";
     public static final String STATUS_1 = "1";
     public static final String SPILT_FH=",";//英文分号
     public static final String SPILT_HX="-";//英文分号
     
     public static final String DO_ACTION_VIEW="view";//查阅
     public static final String DO_ACTION_EDIT="edit";//编辑
     public static final String DO_ACTION_CREATE="create";//创建
     public static final String DO_ACTION_SYNC="sync";//copy
     
     public static final String PG_DETAIL="detail";//促销主题判断是否是详情按钮
     public static final String AUTH_URI="_auth_url";//促销主题判断是否是详情按钮
     public static final String REDIRECT_URI="_redirect_uri";//促销主题判断是否是详情按钮
     
     public class msg{
		 // 消息授权状态，1已开通 0未开通
        public static final String STATUS_0 = "0";
        public static final String STATUS_1 = "1";
	}
     
}

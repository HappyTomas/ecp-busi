package com.zengshi.ecp.im.dubbo.util;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-im-server <br>
 * Description: 常量类<br>
 * Date:2016年8月11日下午3:45:01  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ImConstants {
	public static String ServiceName;

	public  static String Host;

	public  static int Port;
	
	public static String passWord;
	
	
	private static final String MODULE = ImConstants.class.getName();


	/**
	 * IM用户默认密码
	 */
	public static final String IM_USER_DEFAULT_PASSWORD = "123456";
	
	/**
	 * 状态有效
	 */
	public static final String STATE_1 = "1";
	
	/**
	 * 状态无效
	 */
	public static final String STATE_0 = "0";

	/**
	 * 客服人员状态
	 * 1:在线 客服参与分流，接收系统分流客户，参与会话。
	 * 2:挂起 不参与系统分流。除了已经存在的好友信息以及最近联系人的消息
	 * 3:忙碌 不参与分流。客服接待的客服人数已达上限
	 * 4:不在线 不参与分流。客服登出系统。
	 */

	public static final Integer ONLINE = 1;
	public static final Integer HANG = 2;
	public static final Integer BUSY = 3;
	public static final Integer OFFLINE = 4;

	public static final Integer FREE_LINE = 1;
	public static final Integer NO_FREE_LINE = 2;

	/**
	 * 买家用户类型
	 * 1:钻石卡用户
	 * 2:金卡用户
	 * 3:普通
	 */

	public static final Integer NORMAL_CUST = 3;
	public static final Integer GOLD_CUST = 2;
	public static final Integer DIAMOND_CUST = 1;

    /**
     * 买家会员等级
     *
     * 01:普通用户
     * 02:银卡用户
     * 03:金卡用户
     * 04:白金卡用户
     *
     */

    public static final String NORMAL_LEVEL_CUST = "01";
    public static final String SILVER_LEVEL_CUST = "02";
    public static final String GOLD_LEVEL_CUST = "03";
    public static final String PLATINUM_LEVEL_CUST = "04";

	/**
	 *
	 */
	public static final Integer GOODS_BUSINESS_TYPE = 1;
	public static final Integer ORDER_BUSINESS_TYPE = 2;

	//每个客服人员最多支持的并发接入买家会员人数
	public static final Integer STAFF_MAX_SERVICE_CUST = 3;

	//超过这个时间,队列的优先已经失效,将被清出队列
	public static final Integer QUEUE_INVALID_SECONDS = 15;
	
	/**
	 * 提问类型 0 ：综合
	 */
	public static final String issueType_0 = "0"; 
	
	/**
	 * 提问类型 1：订单
	 */
	public static final String issueType_1 = "1"; 
	
	/**
	 * 提问类型 2：商品
	 */
	public static final String issueType_2 = "2";

    /**
     * MONGO队列表名
     */
    public static final String PQ_SVC_INFO = "pq_svc_info";

    public static final Integer SHOP_STAFF_QUEUE_NULL = -99;
    
    /**
     * 终端来源：web
     */
    public static final String RESOUREC_WEB = "WEB";
    
    
    public static final String MOUBLE_TYPE_CONFIG="IM_MODULE_TYPE";

    public static final Integer NUM_ZERO = 0;

    public static final Long BUSINESS_TYPE_0 = 0L;
    public static final Long BUSINESS_TYPE_ORDER = 1L;
    public static final Long BUSINESS_TYPE_GOODS = 2L;

    public static final String IM_STAFF_MAX_SERV_NUM = "IM_STAFF_MAX_SERV_NUM";
    
    public static String ERROR_600101 = "error.chatting.600101";
    
    /**
     * 常用语业务 
     */
    public class PhraseBook{
    	/**
    	 * 分组类型：公共
    	 */
    	public static final String GROUP_CLASS_TEAM = "10"; 
    	/**
    	 * 分组类型：个人
    	 */
    	public static final String GROUP_CLASS_PRIVATE = "20"; 
    }

}


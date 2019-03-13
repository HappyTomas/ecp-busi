package com.zengshi.ecp.staff.dubbo.util;

import java.math.BigDecimal;

public class StaffConstants {

    public static  String STAFF_STATUS_VALID = "1";
    
    public static class PublicParam{
        /**
         * 简单参数：系统编码
         */
        public static final String SYS_SUB_SYSTEM_PARAMKEY = "SYS_SUB_SYSTEM";
        /**
         * 简单参数：通用状态
         */
        public static final String STATUS_PARAMKEY = "PUBLIC_PARAM_STATUS";
        /**
         * 子系统：管理平台
         */
        public static final String SYS_CODE_MANAGE = "2000";
        /**
         * 子系统：商城
         */
        public static final String SYS_CODE_MALL = "1000";
        /**
         * 子系统：买家中心
         */
        public static final String SYS_CODE_BUYER = "1010";
        /**
         * 子系统：卖家中心
         */
        public static final String SYS_CODE_SELLER = "1020";
        /**
         * 通用： 有效
         */
        public static final String STATUS_ACTIVE = "1";
        /**
         * 通用：无效
         */
        public static final String STATUS_INVALID = "0";
        
    }
    
    public static class StaffGroup{
        /**
         * 用户分组标志：系统定义
         */
        public static final String STAFF_GROUP_FLAG_SYS = "00";
        /**
         * 用户分组标志：用户自定义
         */
        public static final String STAFF_GROUP_FLAG_CUSTOM = "01";
        /**
         * 用户分组状态：有效
         */
        public static final String STAFF_GROUP_STATUS_ACTIVE = "1";
        /**
         * 用户分组状态：失效
         */
        public static final String STAFF_GROUP_STATUS_INVALID = "0";
        
        /**
         * 用户分组，系统定义：普通会员分组
         */
        public static final Long STAFF_SYS_GENERAL = 10001L;
        /**
         * 用户分组，系统定义：企业会员分组
         */
        public static final Long STAFF_SYS_ENTERPRISE = 10002L;
        /**
         * 用户分组，系统定义：卖家会员分组
         */
        public static final Long STAFF_SYS_SELLER = 10003L;
        /**
         * 用户分组，系统定义：管理员分组
         */
        public static final Long STAFF_SYS_MANAGE = 10000L;
        
        /**
         * 用户分组，系统定义：卖家会员子账号分组
         */
        public static final Long STAFF_SYS_SELLER_SUB = 10004L;
        
    }
    
    public static class Privilege{
        /**
         * 权限状态：有效
         */
        public static final String STATUS_ACTIVE = "1";
        /**
         * 权限状态：失效
         */
        public static final String STATUS_INVALID = "0";
        /**
         * 权限类型：菜单
         */
        public static final String TYPE_MENU = "10";
        /**
         * 权限类型：规则
         */
        public static final String TYPE_RULE = "20";
        /**
         * 权限名后缀：一般
         */
        public static final String NAME_SUFFIX = "权限";
        /**
         * 权限名后缀：规则
         */
        public static final String NAME_SUFFIX_RULE = "规则权限";
    }
    
    public static class Menu{
        /**
         * 菜单状态：有效
         */
        public static final String STATUS_ACTIVE = "1";
        /**
         * 菜单状态：失效
         */
        public static final String STATUS_INVALID = "0";
        
        /**
         * 菜单类型:菜单
         */
        public static final String MENU_TYPE_MENU="1";
        
        /**
         * 菜单类型:目录
         */
        public static final String MENU_TYPE_DIRECTORY="0";
    }
    
    public static class ShopStaffGroup{
        /**
         * 店铺会员分组，状态：有效
         */
        public static final String STATUS_ACTIVE = "1";
        
        /**
         * 店铺会员分组，状态：无效
         */
        public static final String STATUS_INVALID = "0";
        
        /**
         * 店铺会员分组关系，状态：有效
         */
        public static final String REL_STATUS_ACTIVE = "1";
        
        /**
         * 店铺会员分组关系，状态：无效
         */
        public static final String REL_STATUS_INVALID = "0";
    }
    
    public static class authStaff{
        /**
         * 简单参数：用户状态
         */
        public static final String STAFF_FLAG_PARAMKEY = "STAFF_FLAG";
        /**
         * 用户表，状态1：有效
         */
        public static final String STAFF_FLAG = "1";
        
        /**
         * 用户表，状态2：锁定
         */
        public static final String STAFF_FLAG_LOCK = "2";
        /**
         * 用户表，状态3：失效
         */
        public static final String STAFF_FLAG_INVALID = "3";
        
        /**
         * 用户表，状态4：密码输入多次失败，账户被临时冻结
         */
        public static final String STAFF_FLAG_FREEZE = "4";
        
      
        /**
         * 用户表，创建方式00：注册
         */
        public static final String CREATE_FROM="00";
        
        /**
         * 用户表，创建方式10：管理员新增
         */
        public static final String CREATE_FROM_MANAGER="10";
        
        /**
         * 用户表，用户分类，10：管理员
         */
        public static final String STAFF_CLASS_M = "10";
        
        /**
         * 用户表，用户分类，20：普通会员
         */
        public static final String STAFF_CLASS_N = "20";
        
        /**
         * 用户登录密码错误过多时，冻结其登录时间为 3 小时
         */
        public static final Long LOGIN_FREEZE_TIME = 3 * 60 * 60 * 1000L;
        
        /**
         * 当天充许登录失败的次数
         */
        public static final int LOGIN_FAIL_MAX_CNT = 5;
        
        /**
         * 密码重置的密码：123456
         */
        public static final String PWD_RESET_DEFAULT = "123456";
        
        /**
         * 旧密码错误
         */
        public static final String OLD_PWD_ERROR = "staff.100024";
    }
    
    public static class custInfo{
        /**
         * 客户表，审核状态2：通过
         */
        public static final String CHECK_STATUS_PASS="2";
        
        /**
         * 客户表，审核状态3：不通过
         */
        public static final String CHECK_STATUS_NOPASS="3";
        
        
        /**
         * 客户表，客户类型：企业客户
         */
        public static final String CUST_TYPE_C="20";
        
        /**
         * 客户表，客户类型：普通客户
         */
        public static final String CUST_TYPE_P="10";
        
        /**
         * 客户表，机构用户：管理员
         */
        public static final String CUST_TYPE_ADMIN="30";
        
        /**
         * 客户表，机构用户：公共账户
         */
        public static final String CUST_TYPE_PUBLIC="40";
        
        /**
         * 客户表，机构用户：普通用户
         */
        public static final String CUST_TYPE_COMMON="50";
        
        
        /**
         * 客户表，是否卖家标识1：是
         */
        public static final String SHOP_FLAG_YES="1";
        
        /**
         * 客户表，是否卖家标识0：否
         */
        public static final String SHOP_FLAG_NO="0";
        
        /**
         * 客户状态:正常
         */
        public static final String CUST_STATUS_NORMAL="1";
        
        /**
         * 客户状态:加锁
         */
        public static final String CUST_STATUS_LOCK="2";
        /**
         * 客户状态:失效
         */
        public static final String CUST_STATUS_FAILURE="3";
        
        /**
         * 客户状态：冻结
         */
        public static final String CUST_STATUS_FROZEN = "4";
        
        /**
         * 用户行为
         */
        public static final String CUST_ACTION_TYPE="STAFF_ACTION_TYPE";
        
        /**
         * 会员等级
         */
        public static final String CUST_GROW_PAY_MONEY_ERROR="staff.100029";
        
        /**
         * 会员状态生效操作异常：所属企业为失效
         */
        public static final String CUST_STATUS_OPT_ERROR = "staff.100042";
        
        /**
         * 会员数据来源系统标识：00自身
         */
        public static final String CUST_SYS_TYPE_OWN = "00";
        
        /**
         * 会员数据来源系统标识：01泽元
         */
        public static final String CUST_SYS_TYPE_ZY = "01";
        
        /**
         * 第三方绑定类型：天猫：10
         */
        public static final String CUST_THIRD_CODE_TYPE_TMALL="10";
        
        
        public static final String CUST_THIRD_CODE_REPEAT_ERROR="staff.100054";
        
    }
    
    public static class companyInfo{
        
        /**
         * 企业表，企业类型：普通企业
         */
        public static final  String COMPANY_TYPE_G ="0";
        
        /**
         * 企业表，企业类型：入驻企业
         */
        public static final  String COMPANY_TYPE_P ="1";
        
        /**
         * 企业表，企业状态：有效
         */
        public static final String COMPANY_STATUS_VALID="1";
        
        /**
         * 企业表，企业状态：无效
         */
        public static final String COMPANY_STATUS_INVALID="0";
        
        
        /**
         * 企业表，来源类型：会员申请
         */
        public static final String SOURCE_FROM_CUST="10";
        
        
        /**
         * 企业表，来源类型：会员申请
         */
        public static final String SOURCE_FROM_MANAGE="30";
        
        
        /**
         * 企业审核通过
         */
        public static final String COMPANY_CHECK_STATUS_PASS = "2";
        
        /**
         * 企业审核不通过
         */
        public static final String COMPANY_CHECK_STATUS_NOPASS = "3";
        
        /**
         * 企业行业
         */
        public static final String COMPANY_TRADE="COMPANY_INFO_TRADE";
        
        
        /**
         * 企业人数
         */
        public static final String COMPANY_EMPLOYEE_NUM="COMPANY_INFO_EMPLOYEE";
        
        /**
         * 企业类型
         */
        public static final String COMPANY_TYPE="COMPANY_INFO_TYPE";
        
        /**
         * 企业列表，取缓存Key
         */
        public static final String COMPANY_CACHE_KEY="COMPANY_CACHE_DATA";
        
        
        
        /**
         * 企业名称重复
         */
        public static final String COMPANY_NAME_REPEAT_ERROR = "staff.100008";
        
        /**
         * 企业下还有生效的店铺或会员，禁止失效企业
         */
        public static final String COMPANY_STATUS_INVALID_FORBID = "staff.100028";
        
    }
    public static class shopInfo
    {
        /**
         * 店铺列表，取缓存Key
         */
        public static final String SHOP_CACHE_KEY="STAFF_SHOP_CACHE_DATA";
        /**
         * 店铺状态无效
         */
        public static final String SHOP_STATUS_INVALID = "0";
        /**
         * 店铺状态有效
         */
        public static final String SHOP_STATUS_NOMAL = "1";
        
        /**
         * 是否支持线上支付:支持
         */
        public static final String SHOP_ONLINE_SUPPORTED_YES="1";
        /**
         * 是否支持线上支付:不支持
         */
        public static final String SHOP_ONLINE_SUPPORTED_NO="0";
        /**
         * staff.100007
         */
        public static final String SHOP_NAME_REPEAT = "staff.100007";
        /**
         * staff.100006
         */
        public static final String SHOP_FULL_NAME_REPEAT = "staff.100006";
        
        /**
         * 店铺子账户上限个数
         */
        public static final Long SHOP_SUB_STAFF_COUNT = 5L;
        /**
         * staff.100032 = 店铺不存在
         */
        public static final String E_SHOP_CANT_FIND = "staff.100032";
    }
    public static class custAddr
    {
        /**
         * 收货地址状态无效
         */
        public static final String ADDR_STATUS_INVALID = "0";
        /**
         * 收货地址状态有效
         */
        public static final String ADDR_STATUS_NOMAL = "1";
        /**
         * 收货地址总数大于10产生异常
         */
        public static final String ADDR_NUMBER_ERROR = "staff.100002";
    }
    public static class Login
    {
        /*
                staff.100015 = 对不起，当前账号{0}不存在，请重新输入
                staff.100016 = 对不起，您当前账号已被锁定
                staff.100017 = 对不起，密码错误
                staff.100018 = 您当前密码已失效，请修改用户密码
         */
        /**
         * 不存在此用户
         */
        public static final String NOEXITS_ERROR = "staff.100015";
        /**
         * 用户被锁定
         */
        public static final String LOCK_ERROR = "staff.100016";
        /**
         * 用户密码错误
         */
        public static final String PASSWORD_ERROR = "staff.100017";
        /**
         * 用户密码失效
         */
        public static final String PASSWORD_INVALID_ERROR = "staff.100018";
    }
    
    public static class Score
    {
 
        /**
         * 积分回滚
         */
        public static final String SCORE_ROLLBACK_TYPE = "9999";//积分回滚
        
        /**
         * 订单退款返还积分
         */
        public static final String SCORE_ORDER_REFUND_TYPE = "9998";//订单退款返还积分
        
        /**
         * 订单退货返还积分
         */
        public static final String SCORE_ORDER_BACK_TYPE = "9997";//订单退货返还积分
        
        /**
         * 该条记录已回退
         */
        public static final String SCORE_BACK_FLAG_1 = "1";//该条记录已回退
        
        /**
         * 该条记录未回退
         */
        public static final String SCORE_BACK_FLAG_0 = "0";//该条记录未回退
        
        /**
         * 该条记录为回退记录
         */
        public static final String SCORE_BACK_FLAG_2 = "2";//该条记录为回退记录
        /*
         * 积分来源参数缓存中的key
         */
        public static final String SCORE_ACTION_CACHE_KEY= "SCORE_ACTION_CACHE_KEY";
        public static final String SCORE_TYPE_CACHE_KEY = "SCORE_TYPE_CACHE_KEY";
        public static final String SCORE_FUN_CACHE_KEY = "SCORE_FUN_CACHE_KEY";
        public static final String SCORE_PARA_CACHE_KEY = "SCORE_PARA_CACHE_KEY";
        public static final String SCORE_CACLFUN_CACHE_KEY = "SCORE_CACLFUN_CACHE_KEY";


        /**
         * 积分有效期时间
         */
        public static final int SCORE_ACTIVE_DAY = 365;
        
        /**
         * 积分账户正常状态
         */
        public static final String SCORE_STATUS_NORMAL = "1";
        /**
         * 积分账户无效
         */
        public static final String SCORE_STATUS_INVALID = "0";
        /**
         * 积分账户冻结
         */
        public static final String SCORE_STATUS_FROZEN = "2";
        
        /**
         * 积分函数状态有效
         */
        public static final String SCORE_FUNC_STATUS_ACTIVE = "1";
        /**
         * 积分函数状态无效
         */
        public static final String SCORE_FUNC_STATUS_INVALID = "0";
        
        /**
         * 积分操作类型：收入
         */
        public static final String SCORE_OPT_TYPE_1 = "1";
        
        /**
         * 积分操作类型：支出 
         */
        public static final String SCORE_OPT_TYPE_2 = "2";
        
        /**
         * 积分操作类型：解冻
         */
        public static final String SCORE_OPT_TYPE_3 = "3";
        /**
         * 积分操作类型：冻结
         */
        public static final String SCORE_OPT_TYPE_4 = "4";
        
        /**
         * 积分操作类型：后台调增
         */
        public static final String SCORE_OPT_TYPE_5 = "5";
        
        /**
         * 积分操作类型：后台调减
         */
        public static final String SCORE_OPT_TYPE_6 = "6";
        
        /**
         * 积分调整类型：客户维系
         */
        public static final String SCORE_ADJUST_TYPE_9001 = "9001";
        
        /**
         * 积分调整类型：系统差错
         */
        public static final String SCORE_ADJUST_TYPE_9002 = "9002";
        
        /**
         * 积分操作类型：积分补偿（订单异常回滚、取消订单、退款、退货等）
         */
        public static final String SCORE_OPT_TYPE_7 = "7";
        
        /**
         * 积分操作类型：积分扣减（取消订单、退款、退货等）
         */
        public static final String SCORE_OPT_TYPE_8 = "8";
        
        /**
         * 您的积分不足
         */
        public static final String SCORE_NOT_ENOUGH = "staff.100026";
        
        public static final String USER_SCORE_STATUS_ERROR = "staff.100030";
        
        /**
         * 购物车校验时：您的积分账户处于异常状态
         */
        public static final String SCORE_STATUS_ERROR = "staff.100043";
        
        /**
         * 积分消费扣减方式：1、普通
         */
        public static final String SCORE_EXCHANGE_MODE_1 = "1";
        
        /**
         * 积分消费扣减方式：2、退款，退货
         */
        public static final String SCORE_EXCHANGE_MODE_2 = "2";
        
        /**
         * 积分消费扣减方式：3、后台调减
         */
        public static final String SCORE_EXCHANGE_MODE_3 = "3";
        
        /**
         * 积分最多扣为0，不能扣为负
         */
        public static final String SUBTRACT_TO_ZERO = "0";
        
        /**
         * 积分可扣为负数
         */
        public static final String SUBTRACT_BELOW_ZERO = "1";
        
        /**
         * 通过ISBN号查询不到商品
         */
        public static final String ISBN_NOT_FOUNT = "staff.100057";
        

        /**
         * 书码已经赠送过积分
         */
		public static final String BOOK_CODE_EXIST_FOR_SCORE = "staff.100058";
		
		/**
		 * 通过本版编号找不到商品
		 */
		public static final String BBCODE_NOT_FOUNT = "staff.100060";
        
    }
    
    public static class Trade{
        /**
         * 账户资金借贷方向：收入
         */
        public static final String ACCT_DC_INCOME = "1";
        /**
         * 账户资金借贷方向：支出
         */
        public static final String ACCT_DC_SPEND = "2";
        
        /**
         * 交易类型：充值(+)
         */
        public static final String TRADE_TYPE_RECHARGE = "01";
        /**
         * 交易类型：购买商品付款(-)
         */
        public static final String TRADE_TYPE_PAY = "02";
        /**
         * 交易类型：取消订单(+)
         */
        public static final String TRADE_TYPE_CANCEL = "03";
        
        /**
         * 交易类型：订单退款(+)
         */
        public static final String TRADE_TYPE_REFUND = "04";
        
        /**
         * 交易类型：订单退货(+)
         */
        public static final String TRADE_TYPE_BACK = "05";
    }
    
    public static class Ztree{
    	/**
    	 * 混合树节点类型：数据功能
    	 */
    	public static final String NODE_TYPE_FUNC = "func";
    	/**
    	 * 混合树节点类型：权限规则
    	 */
    	public static final String NODE_TYPE_RULE = "rule";
    }
    
    public static class Role{
        /**
         * 角色状态：有效
         */
        public static final String ROLE_STATUS_ACTIVE = "1";
        /**
         * 角色状态：失效
         */
        public static final String ROLE_STATUS_INVALID = "0";
        
        /***error code***/
        /**
         * 角色名重复
         */
        public static final String E_ROLE_NAME_EXIST = "staff.100027";
    }
    
    public static class Acct{
        /**
         * 简单参数：资金账户类型
         */
        public static final String STAFF_ACCT_TYPE_PARAMKEY = "STAFF_ACCT_TYPE";
        /**
         * 简单参数：资金账户适用类型
         */
        public static final String STAFF_ADAPT_TYPE_PARAMKEY = "STAFF_ADAPT_TYPE";
        /**
         * 账户资金借贷方向：收入
         */
        public static final String ACCT_DC_INCOME = "1";
        /**
         * 账户资金借贷方向：支出
         */
        public static final String ACCT_DC_SPEND = "2";
        /**
         * 资金账户状态：生效
         */
        public static final String ACCT_STATUS_ENABLE = "1";
        /**
         * 资金账户状态：冻结
         */
        public static final String ACCT_STATUS_FREEZE = "0";
        /**
         * 资金适用类型：店铺资金
         */
        public static final String ADAPT_TYPE_SHOP = "03";
        /**
         * 资金适用类型：平台资金
         */
        public static final String ADAPT_TYPE_PLATFORM = "01";
        /**
         * 资金类型：店铺返现
         */
        public static final String ACCT_TYPE_SHOPRETURN = "001";
        /**
         * 资金类型：合约返利
         */
        public static final String ACCT_TYPE_CONTRACTPROFIT = "002";
        /**
         * 资金账户临时数据对象是否合法：是
         */
        public static final String ACCT_TEMP_IS_GOOD_Y = "1";
        /**
         * 资金账户临时数据对象是否合法：否
         */
        public static final String ACCT_TEMP_IS_GODD_N = "0";
        /**
         * 资金账户临时数据对象是否正式提交：是
         */
        public static final String ACCT_TEMP_IS_COMMIT_Y = "1";
        /**
         * 资金账户临时数据对象是否正式提交：否
         */
        public static final String ACCT_TEMP_IS_COMMIT_N = "0";
        
        /***error code***/
        /**
         * 资金帐户类型重复
         */
        public static final String E_ACCT_TYPE_EXIST = "staff.100031";
        /**
         * 请根据模板准确输入数值
         */
        public static final String E_IMPORT_NUMBER_ERROR = "staff.100033";
        /**
         * 请根据模板准确输入内容
         */
        public static final String E_IMPORT_NULL_CONTENT = "staff.100034";
        /**
         * 店铺不存在
         */
        public static final String E_IMPORT_SHOP_UNFINDED = "staff.100035";
        /**
         * 客户不存在
         */
        public static final String E_IMPORT_CUST_UNFINDED = "staff.100036";
        /**
         * 资金账户类型未定义
         */
        public static final String E_IMPORT_ACCTTYPE_UNFINDED = "staff.100037";
        /**
         * 数据长度超出要求
         */
        public static final String E_IMPORT_CELL_TOOLONG = "staff.100046";
        
    }
    
    public static class Cache{
        /**
         * 子系统：管理平台
         */
        public static final String SYS_SUB_SYSTEM_MANAGE = "SYS_SUB_SYSTEM_2000";
        /**
         * 子系统：商城
         */
        public static final String SYS_SUB_SYSTEM_MALL = "SYS_SUB_SYSTEM_1000";
        /**
         * 子系统：买家中心
         */
        public static final String SYS_SUB_SYSTEM_BUYER = "SYS_SUB_SYSTEM_1010";
        /**
         * 子系统：卖家中心
         */
        public static final String SYS_SUB_SYSTEM_SELLER = "SYS_SUB_SYSTEM_1020";
        
        
    }
    
    public static class Auth{
        /**
         * 角色状态：有效
         */
        public static final String ROLE_STATUS_ACTIVE = "1";
        
        /**
         * 角色状态：失效
         */
        public static final String ROLE_STATUS_INVALID = "0";
        /**
         * 权限类型：菜单
         */
        public static final String PRIVILEGE_TYPE_MENU = "10";
    }
    
    public static class ShopShipper
    {
        /**
         * 店铺发货地址总数超过20条
         */
        public static final String SHOP_SHIPPER_NUMBER_OVER = "staff.100022";
        
        /**
         * 取默认发货地址信息标志
         */
        public static final String SHOP_SHIPPER_ADDR_FLAG = "10";
        /**
         * 取默认退货地址信息标志
         */
        public static final String SHOP_RETURN_ADDR_FLAG = "11";
    }
    /**
     * 查询异常
     */
    public static final String STAFF_SELECT_ERROR="staff.100104";
    /**
     * 更新异常
     */
    public static final String STAFF_UPDATE_ERROR="staff.100101";
    /**
     * 删除异常
     */
    public static final String STAFF_DELETE_ERROR="staff.100102";
    
    /**
     * 插入异常
     */
    public static final String STAFF_INSERT_ERROR="staff.100103";
    
    /**
     * 方法执行异常
     */
    public static final String STAFF_EXECUTE_ERROR="staff.100201";
    /**
     * 输出自定义信息
     */
    public static final String STAFF_EXECUTE_ERROR_C="staff.100202";
    
    /**
     * 入参不能为空
     */
    public static final String STAFF_NULL_ERROR="staff.100100";
    
    /**
     * 账号校验
     */
    
    public static final String STAFF_CODE_REPEAT="staff.100010";
    
    /**
     * 手机校验
     */
    
    public static final String STAFF_SERIALNUMBER_REPEAT="staff.100011";
    
    /**
     * 邮箱校验
     */
    public static final String STAFF_EMAIL_REPEAT="staff.100019";
    
    /**
     * 昵称校验
     */
    public static final String STAFF_NICKNAME_REPEAT="staff.100021";
    
    /**
     * 昵称校验已存在
     */
    public static final String STAFF_NICKNAME_EXIST="staff.100020";
    
    /**
     * 店铺全称已经存在
     */
    public static final String SHOP_FULL_NAME_EXIST = "staff.100007";
    
    /**
     * 用户分组名称不能重复
     */
    public static final String AUTH_STAFF_GROUP_NAME="staff.100025";
    
    /**
     * 来源类型(登录)
     */
    public static final String LOGIN_TYPE = "01";
    
    /**
     * 来源类型(购物)
     */
    public static final String SHOPING_TYPE = "02";
    
    /**
     * 来源类型(评价)
     */
    public static final String ASSESS_TYPE = "03";
    /**
     * 天猫订单()
     */
    public static final String TMALL_ORDER_TYPE = "04"; 
    
    /**
     * 来源类型（一书一码激活）
     */
    public static final String ISBN_ACTIVE_TYPE = "06";
    /**
     * 客户等级时效
     */
    public static final String CUST_GROW_TIME="CUST_GROW_TIME";
    
    public static class Card{
        
        /**
         * 会员卡状态redis Key
         */
        public static final String CARD_STATUS_KEY="STAFF_CARD_STATUS";
        
        /**
         * 待审核
         */
        public static final String CHECK_STATUS_0 = "0";
        
        /**
         * 审核通过
         */
        public static final String CHECK_STATUS_1 = "1";
        
        /**
         * 审核不通过
         */
        public static final String CHECK_STATUS_2 = "2";
        
        /**
         * 会员卡状态:01未绑定
         */
        public static final String CUST_CARD_NO_SEND = "01";
        
        /**
         * 会员卡绑定方式:0 线下发卡
         */
        public static final String BIND_TYPE_0 = "0";
        
        /**
         * 会员卡绑定方式:1 线上申请
         */
        public static final String BIND_TYPE_1 = "1";
        
        /**
         * 会员卡状态:02已绑定
         */
        public static final String CUST_CARD_SEND = "02";
        
        /**
         * 生成会员卡号异常编码
         */
        public static final String BULID_CARD_ID_ERROR = "staff.100038";
        
        /**
         * 会员卡已经被绑定异常
         */
        public static final String CARD_IS_BINDED_EVER = "staff.100039";
        
        /**
         * 会员有存在已申请的会员卡记录
         */
        public static final String CARD_EXIST_APPLY_RECORD = "staff.100040";
        
        /**
         * 该会员卡号不存在
         */
        public static final String CARD_BIND_NOEXIST_ERROR = "staff.100041";
        
        /**
         * 审核时，用户会员等级与所申请等级不一致
         */
        public static final String CUST_LEVEL_CHANGE = "staff.100044";

        /**
         * 该会员卡等级低于会员自身的等级
         */
        public static final String CARD_LEVEL_LOW_ERROR = "staff.100045";

        /**
         * 该会员卡已有发卡记录
         */
        public static final String CARD_DISTRIBUTE_EXITS = "staff.100051";

    }
    public static class Msg{
        
        /**
         * 发送方式：站内短信
         */
        public static final String SEND_TYPE_INSITE = "10";
        
        /**
         * 发送方式：手机短信
         */
        public static final String SEND_TYPE_SMS = "20";
        
        /**
         * 发送方式：邮件
         */
        public static final String SEND_TYPE_EMAIL = "30";
        
        /**
         * 会员卡申请消息码
         */
        public static final String MC_CARD_APPLY = "card.apply";
        
        /**
         * 邮箱绑定校验
         */
        public static final String MC_EMAIL_CHECK = "email.check";
        
        /**
         * 手机校验
         */
        public static final String MC_PHONE_CHECK = "phone.check";
        
        /**
         * 邮箱校验key前缀
         */
        public static final String EMAIL_CHECK_PRE = "EMAIL_CHECK_";
        
        /**
         * 手机校验key前缀
         */
        public static final String PHONE_CHECK_PRE = "PHONE_CHECK_";
        
        /**
         * 企业入驻审核短信编码
         */
        public static final String MC_PHONE_COM_CHECK = "phone.com.check";
    }
    
    public static class DataAuth{
    	
    	/**
    	 * 规则来源：1  行规则
    	 */
    	public static final BigDecimal AUTH_SRC_ROW = new BigDecimal("1");
    	
    	/**
    	 * 规则来源：2  列规则
    	 */
    	public static final BigDecimal AUTH_SRC_COLUMN = new BigDecimal("2");
    	
    }
}


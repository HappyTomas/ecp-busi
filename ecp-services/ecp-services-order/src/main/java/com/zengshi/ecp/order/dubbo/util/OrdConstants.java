package com.zengshi.ecp.order.dubbo.util;

public class OrdConstants {

    public static class Common {

        public static final String ORDER_SESSION_KEY_PREFIX = "ORD_CAR_LIST";

        public static final String ORDER_FAST_KEY_PREFIX = "ORD_CAR_FAST";
        // 有效
        public static String COMMON_VALID = "1";

        // 无效
        public static String COMMON_INVALID = "0";

        public static String COMMON_NAME = "order";
        
        //人卫主订单标示
        public static String RW_CODE="RW";

        //合并订单标示
        public static String MER_CODE = "MER";

        //人卫子订单标示
        public static String SRW_CODE="SRW";        
        
        //天猫主订单
//        public static String TM_CODE="TM";    
        public static String TM_CODE="";    
        
        //天猫子订单
//        public static String STM_CODE="STM";  
        public static String STM_CODE="";  
        
        //泽元主订单
        public static String ZY_CODE="ZY";    
        
        //泽元子订单
        public static String SZY_CODE="SZY";       
        
        // 系统默认账号
        public static Long DEFAULT_STAFFID = 1000L;
        
        // 虚拟标示--是
        public static String IS_VIRTUAL="1";
        
        // 虚拟标示--否
        public static String IS_NOT_VIRTUAL="0";       
    }

    public static class ShopCart {

        // 购物车类型---普通
        public static String CART_TYPE_ORDINARY = "01";

        // 购物车类型---立即购买
        public static String CART_TYPE_EASYBUY = "02";

        // 购物车来源---商城
        public static String CART_SOURCE_WEB = "0";

        // 购物车来源---APP
        public static String CART_SOURCE_APP = "1";

        // 购物车---组合套餐主商品
        public static String CART_ITEM_IS_PRIME = "1";

        // 购物车---组合套餐次商品
        public static String CART_ITEM_IS_NOT_PRIME = "0";

        // 购物车明细---使用积分特惠购
        public static String CART_CREDIT_PAY = "01";

        // 购物车明细---使用积分特惠购
        public static String CART_CREDIT_NOT_PAY = "00";
        
       //数字印刷标示
        public static String CART_ITEM_PRN_FLAG="1";

    }

    public static class OrderStatus {
        // 订单状态---提交订单待支付
        public static String ORDER_STATUS_SUBMIT = "01";

        // 订单状态---订单已支付
        public static String ORDER_STATUS_PAID = "02";

        // 订单状态--- 产品已打包发出，待录入物流单
        // public static String ORDER_STATUS_PACKAGE = "03";

        // 订单状态--- 部分发货
        public static String ORDER_STATUS_SEND_SECTION = "04";

        // 订单状态--- 已发货
        public static String ORDER_STATUS_SEND_ALL = "05";

        // 订单状态--- 已收货
        public static String ORDER_STATUS_RECEPT = "06";
        
        // 订单状态--- 全部退货
        public static String ORDER_STATUS_BACKGDS = "07";
        
        // 订单状态--- 退款
        public static String ORDER_STATUS_REFUND = "08";

        // 订单状态--- 关闭
        public static String ORDER_STATUS_CLOSE = "80";

        // 订单状态--- 取消订单
        public static String ORDER_STATUS_CANCLE = "99";

    }

    public static class OrderTwoStatus {

        // 订单二级状态--- 提交订单
        public static String STATUS_SUBMIT = "0100";

        // 订单二级状态--- 线上支付完成
        public static String STATUS_PAID_ONLINE = "0200";

        // 订单二级状态--- 线下支付申请
        public static String STATUS_PAID_OFFLINE_APPLY = "0201";

        // 订单二级状态--- 线下支付审核通过
        public static String STATUS_PAID_OFFLINE_VERIFY_PASS = "0202";

        // 订单二级状态--- 线下支付审核不通过
        public static String STATUS_PAID_OFFLINE_VERIFY_NOT = "9902";

        // 订单二级状态--- 部分发货
        public static String STATUS_SEND_SECTION = "0400";

        // 订单二级状态--- 全部发货
        public static String STATUS_SEND_ALL = "0500";

        // 订单二级状态--- 买家收货
        public static String STATUS_RECEPT_BUYER = "0600";

        // 订单二级状态--- 自动收货
        public static String STATUS_RECEPT_SYSTEM = "0601";
        
        // 订单二级状态--- 退货流程结束
        public static String STATUS_BACKGDS_NO ="0700";
        
        // 订单二级状态--- 退货流程中
        public static String STATUS_BACKGDS_YES ="0701";
        
        // 订单二级状态--- 退款流程结束
        public static String STATUS_REFUND_NO ="0800";
        
        // 订单二级状态--- 退款流程中
        public static String STATUS_REFUND_YES ="0801";

        // 订单二级状态--- 关闭
        public static String STATUS_CLOSE = "8000";

        // 订单二级状态--- 买家取消
        public static String STATUS_CANCLE_BUYER = "9901";

        // 订单二级状态--- 系统取消
        public static String STATUS_CANCLE_SYSTEM = "9902";
        
        // 订单二级状态--- 全部退货取消
        public static String STATUS_CANCLE_BACKGDS = "9903";
        
        // 订单二级状态--- 退款取消
        public static String STATUS_CANCLE_REFUND = "9904";

    }
    
    // 与订单二级状态对应
    public static class NodeDesc {
        // 订单二级状态--- 提交订单
        public static String STATUS_SUBMIT = "提交订单";

        // 订单二级状态--- 线上支付完成
        public static String STATUS_PAID_ONLINE = "线上支付完成";

        // 订单二级状态--- 线下支付申请
        public static String STATUS_PAID_OFFLINE_APPLY = "线下支付申请";

        // 订单二级状态--- 线下支付审核通过
        public static String STATUS_PAID_OFFLINE_VERIFY_PASS = "支付完成";

        // 订单二级状态--- 线下支付审核不通过
        public static String STATUS_PAID_OFFLINE_VERIFY_NOT = "线下支付审核不通过";

        // 订单二级状态--- 部分发货
        public static String STATUS_SEND_SECTION = "部分发货";

        // 订单二级状态--- 全部发货
        public static String STATUS_SEND_ALL = "全部发货";

        // 订单二级状态--- 买家收货
        public static String STATUS_RECEPT_BUYER = "收货(买家收货)";

        // 订单二级状态--- 自动收货
        public static String STATUS_RECEPT_SYSTEM = "超时自动收货";

        // 订单二级状态--- 关闭
        public static String STATUS_CLOSE = "取消订单(卖家取消)";

        // 订单二级状态--- 买家取消
        public static String STATUS_CANCLE_BUYER = "取消订单";
        
        // 订单二级状态--- 超时取消
        public static String STATUS_CANCLE_AUTO = "超时取消订单";

        // 订单二级状态--- 系统取消
        public static String STATUS_CANCLE_SYSTEM = "关闭";
        
        // 订单二级状态--- 系统取消
        public static String STATUS_CANCLE_BACKGDS = "全部退货订单作废";
        
        // 订单二级状态--- 系统取消
        public static String STATUS_CANCLE_REFUND = "退款订单作废";
    }

    public static class Order {

        // 订单线下交易狀態---待供货商审核
        public static String ORDER_OFFLINE_CREATE = "00";

        // 订单线下交易默认有效性---有效
        public static String ORDER_OFFLINE_ISVALID = "1";

        // 自提
        public static String ORDER_DELIVER_FLAG_FALSE = "2";

        // 物流
        public static String ORDER_DELIVER_FLAG_TRUE = "1";

        // 支付状态
        public static String ORDER_PAY_FLAG_0 = "0";// 未支付

        public static String ORDER_PAY_FLAG_1 = "1";// 已支付

        // 订单来源
        public static String ORDER_SOURCE_0 = "0";// WEB

        // 优惠类型 01活动 02 店铺优惠券 03 手工调整 04 系统邮资调整 05积分 06交易会定金
        // 07现金账户抵扣 08联通红包 09 交易会优惠券 10手工邮资调整 11 零元订单手工调整 12 合约返利抵扣 13 团购返现抵扣 15价保返利 16阶梯价
        public static String ORDER_DISCOUNT_SERIAL_01 = "01";

        public static String ORDER_DISCOUNT_SERIAL_02 = "02";

        public static String ORDER_DISCOUNT_SERIAL_03 = "03";

        public static String ORDER_DISCOUNT_SERIAL_04 = "04";

        public static String ORDER_DISCOUNT_SERIAL_05 = "05";

        public static String ORDER_DISCOUNT_SERIAL_06 = "06";

        public static String ORDER_DISCOUNT_SERIAL_07 = "07";

        public static String ORDER_DISCOUNT_SERIAL_08 = "08";

        public static String ORDER_DISCOUNT_SERIAL_09 = "09";

        public static String ORDER_DISCOUNT_SERIAL_10 = "10";

        public static String ORDER_DISCOUNT_SERIAL_11 = "11";

        public static String ORDER_DISCOUNT_SERIAL_12 = "12";

        public static String ORDER_DISCOUNT_SERIAL_13 = "13";

        public static String ORDER_DISCOUNT_SERIAL_14 = "14";

        public static String ORDER_DISCOUNT_SERIAL_15 = "15";

        public static String ORDER_DISCOUNT_SERIAL_16 = "16";

        // 产品目录类型
        public static String ORDER_CAT_TYPE_200 = "200";// 手机配件

        public static String ORDER_CAT_TYPE_300 = "300";// 手机套包

        public static String ORDER_CAT_TYPE_900 = "900";// 移动业务卡包
        
        
        //订单类型
        public static String ORDER_TYPE_01 = "01";// 普通订单
        
        // 支付类型： 0为在线支付，1为线上支付，2为邮局汇款 3为银行转账
        public static String ORDER_PAY_TYPE_0 = "0";// 在线支付
        
    }

    // 所属系统
    public static class SysType {

        // 本系统
        public static String SYS_TYPE_BASE = "00";

        // 天猫
        public static String SYS_TYPE_TMMALL = "01";
        
        // 泽元
        public static String SYS_TYPE_ZYMALL = "02";        

    }

    public static class ShopRequestStatus {
        //查询全部
        public static String REQUEST_STATUS_ALL = "00";
        //待发货
        public static String REQUEST_STATUS_SEND = "01";
        //已发货
        public static String REQUEST_STATUS_DELY = "02";
    }

    public static class CustomerRequestStatus {
        
        // 00-全部
        public static String REQUEST_STATUS_ALL = "00";
        
        // 01-待付款
        public static String REQUEST_STATUS_PAY = "01";

        // 02-待发货
        public static String REQUEST_STATUS_SEND = "02";

        // 03-待收货
        public static String REQUEST_STATUS_RECEPT = "03";

        // 04-已收货
        public static String REQUEST_STATUS_RECEPTED = "04";

        // 05-已取消
        public static String REQUEST_STATUS_CANCLE = "05";

        
    }
    
    public static class PayStatus {
        
        //支付通道 9999 线下支付
        public static String PAY_WAY_9999="9999";
        
        //支付通道 9998 0元订单支付
        public static String PAY_WAY_9998="9998";
        
        // 支付相关开关 1 开启，0关闭
        public static String PAY_WAY_OPEN = "1";//启用
        
        public static String PAY_WAY_OFF = "0";//启用
        
        //定时任务处理状态 0：未处理，1：已处理 2：处理中
        public static String PAY_DEAL_FLAG_0 = "0";//未处理
        
        public static String PAY_DEAL_FLAG_1 = "1";//已处理
        
        public static String PAY_DEAL_FLAG_2 = "2";//处理中
        
        //定时任务类型  01：赠积分处理，02：赠优惠券处理
        public static String PAY_TASK_TYPE_01 = "01";//赠积分处理
        
        public static String PAY_TASK_TYPE_02 = "02";//赠优惠券处理
        
        public static String PAY_TASK_TYPE_03 = "03";//泽元数字教材授权
        
        public static String PAY_TASK_TYPE_04 = "04";//泽元考试网授权
        
        //支付方式，01 线上支付 ，02 非线上支付
        public static String PAY_TYPE_01 = "01";//线上支付
        
        public static String PAY_TYPE_02 = "02";//非线上支付
        
        //请求日志表类型 ：01：支付，02：退款，03：绑卡，04：绑卡转移，05：支付结果查询
        
        public static String PAY_LOG_TYPE_CODE_01 = "01";//支付
        
        public static String PAY_LOG_TYPE_CODE_02 = "02";//退款
        
        public static String PAY_LOG_TYPE_CODE_03 = "03";//绑卡
        
        public static String PAY_LOG_TYPE_CODE_04 = "04";//绑卡转移
        
        public static String PAY_LOG_TYPE_CODE_05 = "05";//支付结果查询
        
        public static String PAY_LOG_TYPE_CODE_06 = "06";//泽元数字教材授权
        
        public static String PAY_LOG_TYPE_CODE_07 = "07";//泽元考试网授权
        
        public static String PAY_LOG_TYPE_CODE_08 = "08";//对账接口日志
        
        //支付接口报文接收日志类型 ：01：支付结果通知，02：退款结果通知，03：绑卡通知
        
        public static String PAY_NOTIFYLOG_TYPE_CODE_01 = "01";//支付结果通知
        
        public static String PAY_NOTIFYLOG_TYPE_CODE_02 = "02";//退款结果通知
        
        public static String PAY_NOTIFYLOG_TYPE_CODE_03 = "03";//绑卡通知
        
        // 返回给AIP的flag
        public static String TO_AIP_PARAM_FLAG = "flag";

        // 返回给AIP的message
        public static String TO_AIP_PARAM_MESSAGE = "message";

        // AIP返回flag
        public static String FROM_AIP_PARAM_FLAG = "flag";

        // AIP返回message
        public static String FROM_AIP_PARAM_MESSAGE = "returnMsg";

        // 来自AIP的FLAG success
        public static String FROM_AIP_SUCCESS = "0";

        // 来自AIP的FLAG success
        public static String FROM_AIP_FAILURE = "1";

        public static String FROM_AIP_PaymentResult = "PaymentResult";
        
        public static String SUCCESS = "0";

        public static String FAILURE = "1";
        
        public static String FROM_AIP_HONGPAY_NOTIFY_KEY = "packet";
        
        public static String PAY_ZYDIGITAL_URL = "PAY_ZYDIGITAL_URL";//泽元数字教材授权同步地址
        
        public static String PAY_ZYEXAMINATION_URL = "PAY_ZYEXAMINATION_URL";//泽元考试网授权同步地址
        
        // 清算状态--已清算
        public final static String CHECK_STATUS_YES = "1";
        
        //退款状态
        public static String PAY_REFUND_RESULT_STATUS_0 = "0";//待退款
        public static String PAY_REFUND_RESULT_STATUS_1 = "1";//退款中
        public static String PAY_REFUND_RESULT_STATUS_2 = "2";//退款成功
        public static String PAY_REFUND_RESULT_STATUS_3 = "3";//退款失败
        
        //退款方式
        public static String PAY_REFUND_METHOD_01 = "01";//不经页面跳转
        
        //退款方式
        public static String PAY_REFUND_METHOD_02 = "02";//经页面跳转到支付公司页面进行操作
        
        //对账状态
        public static final String AUDIT_STATUS_LONG = "01";//长款
        public static final String AUDIT_STATUS_SHORT = "10";//短款
        public static final String AUDIT_STATUS_SUCC = "00";//对账成功
        
        //订单是否存在对账文件中
        public static String ORD_MAIN_IS_IN_AUDIT_FILE_1 = "1";//存在
        public static String ORD_MAIN_IS_IN_AUDIT_FILE_0 = "0";//不存在
        
        //对账类型
        public static String AUDIT_TRADE_CHECK_AUDIT_TYPE_01 = "01";//支付
        public static String AUDIT_TRADE_CHECK_AUDIT_TYPE_02 = "02";//退款
        
    }
    public static class PayAudit {
        public static String QsDates = "QsDates";
    }
    public static class SaveInfoType {
        //订单优惠表  
        public static String TYPE_DISCOUNT = "0";
        
        //订单赠品表 
        public static String TYPE_GIFT = "1";
        
        //订单赠送积分和优惠表
        public static String TYPE_PRESENT = "2";
    }
    
    public static class DiscountType {
        
        //优惠类型 01订单促销活动 02 产品促销活动  12 店铺优惠券  03 资金帐户  04积分帐户  系统邮资调整 05积分 06交易会定金 07现金账户抵扣  08联通红包  09 交易会优惠券  10手工邮资调整
        //订单促销活动
        public static String TYPE_ORDER_CODE ="01";
        public static String TYPE_ORDER_NAME = "订单促销活动";
        //产品促销活动
        public static String TYPE_SUB_CODE = "02";
        public static String TYPE_SUB_NAME = "产品促销活动";
        //12 店铺优惠券
        public static String TYPE_COUP_CODE = "12";
        public static String TYPE_COUP_NAME = "店铺优惠券";
        //资金帐户
        public static String TYPE_CAPITAL_CODE ="03";
        public static String TYPE_CAPITAL_NAME = "资金帐户";
        //积分帐户
        public static String TYPE_SCORE_CODE ="04";
        public static String TYPE_SCORE_NAME = "积分帐户";
        //分类折扣（商品域）
        public static String TYPE_GDS_CODE ="05";
        public static String TYPE_GDS_NAME = "分类折扣";
        //13 店铺优惠码
        public static String TYPE_COUPCODE_CODE = "13";
        public static String TYPE_COUPCODE_NAME = "店铺优惠码";
    }
    
    public static class GiftFromType {
        //促销活动的赠品
        public static String TYPE_PROM = "0";
    }
    
    public static class SendInvoiceType {
        //附书寄出 一起寄出
        public static String SEND_TYPE_UNION = "0";
        //快递寄出 分开寄出
        public static String SEND_TYPE_PART = "1";
    }
    public static class DealFrom {
        //买家取消
        public static String FROM_BUYER = "0";
        //卖家取消
        public static String FROM_SHOP = "1";
        //自动取消
        public static String FROM_AUTO = "2";
    }

    public static class SaleInfo {
        public static Long RW_SHOP_ID = 69l;
    }

    public static class PayWayWeChat{
        public static String IS_REDIRECT = "01";
        public static String RET_KEY = "return_code";//返回标识key
        public static String RET_VALUE = "SUCCESS";//返回标识值
        public static String RET_URL = "code_url";//返回微信URL
        public static String TRADE_STATE = "trade_state";//支付状态
        public static String APP_ID = "wx8b358f3aead72646";//APPID
        public static String REDIS_PREFIX = "PayWayWeChat_";//放入redis的键
    }

    public static class OrdPayRel{
    	// 合并支付店铺ID
        public static Long PAY_WAY_MERGE_CFG_ID = 0l;

        public static String PAYED_FLAG = "1";
        
        // 合并支付开关
        public static String SWITCH_PAY_MERGE = "SWITCH_PAY_MERGE";
        
        // 合并支付 1-开启
        public static String PAY_MERGE_ENABLE = "1";
    }

    public static class PAY_WAY {
        public static String WeChat = "9006";
        public static String WeiXin = "9007";
    }
    
    public static class ShareStatus{
    	public static String DONE = "1";//已处理
        public static String NODO = "0";//未处理
    }

}

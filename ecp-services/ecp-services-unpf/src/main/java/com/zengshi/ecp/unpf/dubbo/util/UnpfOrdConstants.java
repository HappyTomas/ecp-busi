package com.zengshi.ecp.unpf.dubbo.util;

public class UnpfOrdConstants {

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

        //其他订单状态，这类订单统一不处理
        public static String ORDER_STATUS_OTHER = "98";

        // 订单状态--- 取消订单
        public static String ORDER_STATUS_CANCLE = "99";  // 订单状态--- 取消订单


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
        public static String STATUS_BACKGDS_NO = "0700";

        // 订单二级状态--- 退货流程中
        public static String STATUS_BACKGDS_YES = "0701";

        // 订单二级状态--- 退款流程结束
        public static String STATUS_REFUND_NO = "0800";

        // 订单二级状态--- 退款流程中
        public static String STATUS_REFUND_YES = "0801";

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

    //天猫订单状态
    public static class TaoBaoOrderStatus {

        public static String TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";//(没有创建支付宝交易）                             01   提交订单待支付
        public static String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";//等待买家付款)                                         01   提交订单待支付
        public static String SELLER_CONSIGNED_PART = "SELLER_CONSIGNED_PART";//(卖家部分发货)                                  04   部分发货
        public static String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";//(等待卖家发货,即:买家已付款)                   02   订单已支付
        public static String WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";//(等待买家确认收货,即:卖家已发货)             05   已发货
        public static String TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";//(买家已签收,货到付款专用)                          06   已收货
        public static String TRADE_FINISHED = "TRADE_FINISHED";//(交易成功);//                                             06   已收货
        public static String TRADE_CLOSED = "TRADE_CLOSED";//(付款以后用户退款成功，交易自动关闭)                     80   关闭
        public static String TRADE_CLOSED_BY_TAOBAO = "TRADE_CLOSED_BY_TAOBAO";//(付款以前，卖家或买家主动关闭交易)             99   取消订单
        public static String PAY_PENDING = "PAY_PENDING";//(国际信用卡支付付款确认中)                                01   提交订单待支付
        public static String WAIT_PRE_AUTH_CONFIRM = "WAIT_PRE_AUTH_CONFIRM";//(0元购合约中)                      

    }

    /**
     * 有赞订单状态
     */
    public static class YouZanOrderStatus {
        /**
         * 没有创建支付交易
         */
        public static final String TRADE_NO_CREATE_PAY = "TRADE_NO_CREATE_PAY";
        /**
         * 等待买家付款
         */
        public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        /**
         * 等待支付确认
         */
        public static final String WAIT_PAY_RETURN = "WAIT_PAY_RETURN";
        /**
         * 等待成团，即：买家已付款，等待成团
         */
        public static final String WAIT_GROUP = "WAIT_GROUP";
        /**
         * 等待卖家发货，即：买家已付款
         */
        public static final String WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
        /**
         * 等待买家确认收货，即：卖家已发货
         */
        public static final String WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
        /**
         * 买家已签收
         */
        public static final String TRADE_BUYER_SIGNED = "TRADE_BUYER_SIGNED";
        /**
         * 付款以后用户退款成功，交易自动关闭
         */
        public static final String TRADE_CLOSED = "TRADE_CLOSED";
        /**
         * 付款以前，卖家或买家主动关闭交易
         */
        public static final String TRADE_CLOSED_BY_USER = "TRADE_CLOSED_BY_USER";
        /**
         * 包含：WAIT_BUYER_PAY、TRADE_NO_CREATE_PAY
         */
        public static final String ALL_WAIT_PAY = "ALL_WAIT_PAY";


    }

    //京东订单状态
    public static class JDOrderStatus {

    }

    //是否系统订单
    public static class IsSysFlag {
        public static String SYSORDER = "1"; //全部是
        public static String NO_SYSORDER = "0";//全部不是
        public static String PART_SYSORDER = "2";//部分是
    }

    public static class LockType {
        //天猫订单同步
        public static String LOCK_TYPE_TMALL_ORD = "801";
    }
}

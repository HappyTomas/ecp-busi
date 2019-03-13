package com.zengshi.ecp.im.dubbo.util;

/**
 * Created by zhangys on 16/10/6.
 */
public final class ImErrorConstants {

    /**
     * 隐藏实现，防止初始化
     */
    private ImErrorConstants() {
    }

    public static class Chatting {

        /**
         * 买家会员{0}不存在!
         */
        public static final String ERROR_CHATTING_600000 = "error.chatting.600000";

        /**
         * 客服人员{0}不存在!
         */
        public static final String ERROR_CHATTING_600001 = "error.chatting.600001";

        /**
         * 入参shopId{0}不存在!
         */
        public static final String ERROR_CHATTING_600002 = "error.chatting.600002";

        /**
         * 入参staffId{0}不存在!
         */
        public static final String ERROR_CHATTING_600003 = "error.chatting.600003";

        /**
         * 入参ofStaffCode{0}不存在!
         */
        public static final String ERROR_CHATTING_600004 = "error.chatting.600004";

        /**
         * 队列数据{0}不存在!
         */
        public static final String ERROR_CHATTING_600005 = "error.chatting.600005";

        /**
         * 买家会员等级{0}不存在!
         */
        public static final String ERROR_CHATTING_600006 = "error.chatting.600006";

        /**
         * 买家会员请求客服时业务类型{0}不存在!
         */
        public static final String ERROR_CHATTING_600007 = "error.chatting.600007";

        /**
         * 会话ID{0}不存在!
         */
        public static final String ERROR_CHATTING_600008 = "error.chatting.600008";

        /**
         * 已经对客服人员{0}进行满意度评价!
         */
        public static final String ERROR_CHATTING_600009 = "error.chatting.600009";

        /**
         * 必须的入参不能为空!
         */
        public static final String ERROR_CHATTING_600010 = "error.chatting.600010";

        /**
         * 客服状态错!
         */
        public static final String ERROR_CHATTING_600011 = "error.chatting.600011";

        /**
         * 请求连接绩效客服的商品ID或订单ID不能为空!
         */
        public static final String ERROR_CHATTING_600012 = "error.chatting.600012";

        /**
         * 请求连接客服的业务类型不能为空!
         */
        public static final String ERROR_CHATTING_600013 = "error.chatting.600013";
        
        /**
         * 客服人员{0}当前接入数不存在或者最大接入数不存！
         */
        public static final String ERROR_CHATTING_600014 = "error.chatting.600014";
        
        /**
         * 买家会员对应的店铺id不存在！
         */
        public static final String ERROR_CHATTING_600015 = "error.chatting.600015";

    }
}

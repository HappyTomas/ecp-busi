package com.zengshi.ecp.order.dubbo.util;

public class BackConstants {
    
    public static class IsProcess {
        //不在流程中
        public static String NO = "0";
        //处理流程中
        public static String YES = "1";
        //虚拟商品
        public static String OTH = "2";
    }
    public static class ApplyType {
        //退款
        public static String REFUND = "0";
        //退货
        public static String BACK_GDS = "1";
    }
    public static class IsEntire {
        //否
        public static String NO = "0";
        //是
        public static String YES = "1";
    }
    public static class ProcType {
        //下单抵用回退
        public static String USE_BACK_0 = "0";
        //下单赠送回退
        public static String GIVE_BAKC_1 = "1";
    }
    public static class DiscountType {
        //金额优惠
        public static String TYPE_MONEY_01 = "01";
        //积分优惠
        public static String TYPE_SCORE_02 = "02";
        //资金帐户优惠
        public static String TYPE_ACCT_03 = "03";
    }
    public static class PicType{
        //申请凭证
        public static String APPLY_GDS = "0";
        //买家发货凭证
        public static String SEND_GDS = "1";
        //退款凭证
        public static String REFUND = "2";
    }
    public static class Status{
        //待审核
        public static String APPLY = "00";
        //同意退货\退款
        public static String REVIEW_PASS = "01";
        //买家发货
        public static String SEND = "02";
        //确认收货待退款
        public static String WAIT_REFUND = "03";
        //已退款
        public static String REFUNDED = "04";
        //拒绝退货
        public static String REFUSE = "05";
    }
    //操作节点说明
    public static class NodeDesc {
        
        public static String GDS_APPLY = "提交退货申请";
        
        public static String MONEY_APPLY = "提交退款申请";

        public static String GDS_REVIEW_PASS = "同意退货";
        
        public static String MONEY_REVIEW_PASS = "同意退款";

        public static String SEND = "买家确认发货";

        public static String WAIT_REFUND = "确认收货";

        public static String REFUNDED = "已退款";

        public static String GDS_REFUSE = "拒绝退货";
        
        public static String MONEY_REFUSE = "拒绝退款";

    }
    public static class ChkStatus {
        //退货审核
        public static String CHK_BACKGDS_REVIEW = "00,06,07";
        //退货买家确认发货
        public static String CHK_BACKGDS_SEND = "01";
        //退货确认收货
        public static String CHK_BACKGDS_RECEIPT = "02";
        //退货确认付款
        public static String CHK_BACKGDS_REFUND = "03";
        //退货拒绝退货
        public static String CHK_BACKGDS_REFUSE = "00";
        //退款审核
        public static String CHK_REFUND_REVIEW = "00,06,07";
        //退款确认付款
        public static String CHK_REFUND_REFUND = "01";
        //退款拒绝退款
        public static String CHK_REFUND_REFUSE = "01";
    }
    
    public static class PayType {
    	//线下退款
    	public static String ORD_BACK_APPLY_PAY_TYPE_0 = "0";
    	//线上退款
    	public static String ORD_BACK_APPLY_PAY_TYPE_1 = "1";
    	
    }
}


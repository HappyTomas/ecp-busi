package com.zengshi.ecp.order.dubbo.util;

public class MsgConstants {
    public static class ServiceMsgCode{
        /*------服务接口-start------------*/
        
        /*---发货收货---start---*/
        //卖家订单查询
        public static String ORD_SERVER_310000 = "ORD.SERVER.310000";
        //买家订单查询
        public static String ORD_SERVER_310001 = "ORD.SERVER.310001";
        //确认发货
        public static String ORD_SERVER_310002 = "ORD.SERVER.310002";
        //确认收货
        public static String ORD_SERVER_310003 = "ORD.SERVER.310003";
        //管理平台订单查询
        public static String ORD_SERVER_310004 = "ORD.SERVER.310004";
        //订单详情
        public static String ORD_SERVER_310005 = "ORD.SERVER.310005";
        //实体编号录入
        public static String ORD_SERVER_310010 = "ORD.SERVER.310010";
        //实体编号录入删除
        public static String ORD_SERVER_310011 = "ORD.SERVER.310011";
        //实体编号录入查询
        public static String ORD_SERVER_310012 = "ORD.SERVER.310012";
        //实体编号导入
        public static String ORD_SERVER_310013 = "ORD.SERVER.310013";
        //实体编号导入状态查看
        public static String ORD_SERVER_310014 = "ORD.SERVER.310014";
        //实体编号导入失败原因查看
        public static String ORD_SERVER_310015 = "ORD.SERVER.310015";
        //实体编号导入失败批次删除
        public static String ORD_SERVER_310016 = "ORD.SERVER.310016";
        //实体编号变更查询
        public static String ORD_SERVER_310020 = "ORD.SERVER.310020";
        //实体编号单个变更
        public static String ORD_SERVER_310021 = "ORD.SERVER.310021";
        //实体编号变更文件导入
        public static String ORD_SERVER_310022 = "ORD.SERVER.310022";
        //实体编号变更文件导入状态查看
        public static String ORD_SERVER_310023 = "ORD.SERVER.310023";
        //实体编号变更文件导入失败原因查看
        public static String ORD_SERVER_310024 = "ORD.SERVER.310024";
        //实体编号变更文件导入失败批次删除
        public static String ORD_SERVER_310025 = "ORD.SERVER.310025";
        //统计各种订单状态数量
        public static String ORD_SERVER_310026 = "ORD.SERVER.310026";
        //发货清单打印信息查询
        public static String ORD_SERVER_310027 = "ORD.SERVER.310027";
        //订单收货地址查询
        public static String ORD_SERVER_310028 = "ORD.SERVER.310028";
        //订单收货地址修改
        public static String ORD_SERVER_310029 = "ORD.SERVER.310029";
        //查询订单开票信息
        public static String ORD_SERVER_310030 = "ORD.SERVER.310030";
        //更新订单开票信息
        public static String ORD_SERVER_310031 = "ORD.SERVER.310031";
        //设置超时未收货时间错误
        public static String ORD_SERVER_310032 = "ORD.SERVER.310032";
        //卖家退货订单查询
        public static String ORD_SERVER_310033 = "ORD.SERVER.310033";
        //买家退货查询
        public static String ORD_SERVER_310034 = "ORD.SERVER.310034";
        //查询订单明细
        public static String ORD_SERVER_310035 = "ORD.SERVER.310035";
        //退货详情
        public static String ORD_SERVER_310036 = "ORD.SERVER.310036";
        //退货申请
        public static String ORD_SERVER_310037 = "ORD.SERVER.310037";
        //买家确认发货
        public static String ORD_SERVER_310038 = "ORD.SERVER.310038";
        //退货审核
        public static String ORD_SERVER_310039 = "ORD.SERVER.310039";
        //确认收货
        public static String ORD_SERVER_310040 = "ORD.SERVER.310040";
        //确认付款
        public static String ORD_SERVER_310041 = "ORD.SERVER.310041";
        //退款申请
        public static String ORD_SERVER_310042 = "ORD.SERVER.310042";
        //退款审核
        public static String ORD_SERVER_310043 = "ORD.SERVER.310043";
        //买家退款查询
        public static String ORD_SERVER_310044 = "ORD.SERVER.310044";
        //卖家退款查询
        public static String ORD_SERVER_310045 = "ORD.SERVER.310045";
        //退款详情
        public static String ORD_SERVER_310046 = "ORD.SERVER.310046";
        //退货订单审核信息查询
        public static String ORD_SERVER_310047 = "ORD.SERVER.310047";
        //退款订单审核信息查询
        public static String ORD_SERVER_310048 = "ORD.SERVER.310048";
        //天猫订单导入
        public static String ORD_SERVER_310049 = "ORD.SERVER.310049";
        //天猫订单明细导入
        public static String ORD_SERVER_310050 = "ORD.SERVER.310050";
        //天猫订单赠送积分
        public static String ORD_SERVER_310051 = "ORD.SERVER.310051";
        //天猫订单绑定
        public static String ORD_SERVER_310052 = "ORD.SERVER.310052";
        //天猫主订单查询
        public static String ORD_SERVER_310053 = "ORD.SERVER.310053";
        //天猫子订单查询
        public static String ORD_SERVER_310054 = "ORD.SERVER.310054";
        //天猫订单导入不能超过2000
        public static String ORD_SERVER_310055 = "ORD.SERVER.310055";
        
        //天猫订单导入的文件格式不对
        public static String ORD_SERVER_310056 = "ORD.SERVER.310056";
        
        /*---发货收货---end---*/
        
        /*---线下支付---start---*/        
        //线下支付查询
        public static String ORD_SERVER_320000 = "ORD.SERVER.320000";
        //线下支付申请
        public static String ORD_SERVER_320001 = "ORD.SERVER.320001";
        //线下支付审核查询
        public static String ORD_SERVER_320002 = "ORD.SERVER.320002";
        //线下支付审核
        public static String ORD_SERVER_320003 = "ORD.SERVER.320003";
        //0元订单
        public static String ORD_SERVER_320004 = "ORD.SERVER.320004"; 
        /*---线下支付---end---*/      
        
        
        /*---购物车---start---*/
        //加入购物车
        public static String ORD_SERVER_350000 = "ORD.SERVER.350000";    
        //展示购物车
        public static String ORD_SERVER_350001 = "ORD.SERVER.350001";  
        //购物车转预订单
        public static String ORD_SERVER_350002 = "ORD.SERVER.350002";  
        //提交订单异常
        public static String ORD_SERVER_350003 = "ORD.SERVER.350003";
        //更新购物车促销实例
        public static String ORD_SERVER_350004 = "ORD.SERVER.350004";
        //更新购物车数量
        public static String ORD_SERVER_350005 = "ORD.SERVER.350005";
        //删除购物车明细
        public static String ORD_SERVER_350006 = "ORD.SERVER.350006";
        //更新购物车明细促销信息
        public static String ORD_SERVER_350007 = "ORD.SERVER.350007";
        //组合商品修改数量
        public static String ORD_SERVER_350008 = "ORD.SERVER.350008";
        //删除组合商品
        public static String ORD_SERVER_350009 = "ORD.SERVER.350009";
        
        //买家查询商品数量异常
        public static String ORD_SERVER_350010 = "ORD.SERVER.350010";
        
        //提交订单
        public static String ORD_SERVER_350011 = "ORD.SERVER.350011";
        //取消订单
        public static String ORD_SERVER_350012 = "ORD.SERVER.350012"; 
        //加入购物车明细数量
        public static String ORD_SERVER_350013 = "ORD.SERVER.350013";
        
        /*---购物车---end---*/
        
        /*---报表---start---*/
        //商品销售量查询
        public static String ORD_SERVER_330000 = "ORD.SERVER.330000";
        /*---报表---end-----*/
        
        /*------服务接口-end------------*/
    }
    public static class InputMsgCode{
        /*--------入参校验--start-----*/
        //入参不能为空
        public static String ORD_INPUT_300000 = "ORD.INPUT.300000";
        //所属站点不能为空
        public static String ORD_INPUT_300001 = "ORD.INPUT.300001";
        //所属系统不能为空
        public static String ORD_INPUT_300002 = "ORD.INPUT.300002";
        //买家未登陆成功
        public static String ORD_INPUT_300003 = "ORD.INPUT.300003";       
        
        //订单号不能为空
        public static String ORD_INPUT_311000 = "ORD.INPUT.311000";
        //子订单号不能为空
        public static String ORD_INPUT_311001 = "ORD.INPUT.311001";
        //店铺ID不能为空
        public static String ORD_INPUT_311002 = "ORD.INPUT.311002";
        //买家ID不能为空
        public static String ORD_INPUT_311003 = "ORD.INPUT.311003";
        //录入的时间不能为空
        public static String ORD_INPUT_311004 = "ORD.INPUT.311004";
        //店铺名称不能为空
        public static String ORD_INPUT_311005 = "ORD.INPUT.311005";
        //是否全部发货标识不能为空
        public static String ORD_INPUT_311006 = "ORD.INPUT.311006";
        //全部发货标识值不正确
        public static String ORD_INPUT_311007 = "ORD.INPUT.311007";
        
        //订单状态不能为空
        public static String ORD_INPUT_311008 = "ORD.INPUT.311008";
        //产品分类不能为空
        public static String ORD_INPUT_311009 = "ORD.INPUT.311009";
        //申请类型不能为空
        public static String ORD_INPUT_311010 = "ORD.INPUT.311010";
        //退款或退货申请单号不能为空
        public static String ORD_INPUT_311011 = "ORD.INPUT.311011";
        //退款方式不能为空
        public static String ORD_INPUT_311012 = "ORD.INPUT.311012";
        
        //店铺ID不能和订单的shopId不匹配
        public static String ORD_INPUT_311013 = "ORD.INPUT.311013";
        //订单编号数组不能为空
        public static String ORD_INPUT_311015 = "ORD_INPUT_311015";
        
        //发货数量必需大于0
        public static String ORD_INPUT_311100 = "ORD.INPUT.311100";
        //实体编号不能为空
        public static String ORD_INPUT_311101 = "ORD.INPUT.311101";
        //子订单信息不能为空
        public static String ORD_INPUT_311102 = "ORD.INPUT.311102";
        //商品是否导入实体编号不能为空
        public static String ORD_INPUT_311103 = "ORD.INPUT.311103";
        //批导文件不能为空
        public static String ORD_INPUT_311104 = "ORD.INPUT.311104";
        //批次号不能为空
        public static String ORD_INPUT_311105 = "ORD.INPUT.311105";
        //旧实体编号不能为空
        public static String ORD_INPUT_311106 = "ORD.INPUT.311106";
        //新实体编号不能为空
        public static String ORD_INPUT_311107 = "ORD.INPUT.311107";
        //备注不能为空
        public static String ORD_INPUT_311108 = "ORD.INPUT.311108";
        //凭证不能为空
        public static String ORD_INPUT_311109 = "ORD.INPUT.311109";
        //线下支付流水不能为空
        public static String ORD_INPUT_311110 = "ORD.INPUT.311110";
        //审核内容不能为空
        public static String ORD_INPUT_311111 = "ORD.INPUT.311111";
        //审核状态不能为空或输入的值不对
        public static String ORD_INPUT_311112 = "ORD.INPUT.311112";
      
      //分享状态不能为空或输入的值不对
        public static String ORD_INPUT_311200 = "ORD.INPUT.311200";
        
        
        //购物车类型不能为空
        public static String ORD_INPUT_351000 = "ORD.INPUT.351000";
        //购物车明细列表不能为空
        public static String ORD_INPUT_351001 = "ORD.INPUT.351001";
        //购物车明细对象不能为空
        public static String ORD_INPUT_351002 = "ORD_INPUT_351002";  
        //删除的购物车明细列表不能为空
        public static String ORD_INPUT_351003 = "ORD_INPUT_351003"; 
        //勾选的购物车明细列表为空
        public static String ORD_INPUT_351004 = "ORD_INPUT_351004"; 
        //勾选的店铺列表为空
        public static String ORD_INPUT_351005 = "ORD_INPUT_351005"; 
        
        
        //单品ID不能为空
        public static String ORD_INPUT_331000 = "ORD_INPUT_331000";
        
        
        
        
        /*------入参校验--end--------*/
    }
    public static class ProMsgCode{
        
        /*-------发货收货业务处理过程--start-------*/
        //数据异常
        public static String ORD_PRO_312000 = "ORD.PRO.312000";
        //未找订单数据信息
        public static String ORD_PRO_312001 = "ORD.PRO.312001";
        //未找到该订单的子订单数据信息
        public static String ORD_PRO_312002 = "ORD.PRO.312002";
        //未找到该订单的跟踪记录信息
        public static String ORD_PRO_312003 = "ORD.PRO.312003";
        
        /*-------发货收货业务处理过程业务处理过程--end-------*/
        
        
        /*-------线下支付业务处理过程业务处理过程--start-------*/
        //订单号查询待审核数据为空
        public static String ORD_PRO_312004 = "ORD.PRO.312004";
        //线下支付流水号查询凭证数据为空
        public static String ORD_PRO_312005 = "ORD.PRO.312005";
        /*-------线下支付业务处理过程业务处理过程--end-------*/
        //未找到该子订单实体数据信息
        public static String ORD_PRO_312006 = "ORD.PRO.312006";
        //未找到该订单发货地址表数据信息
        public static String ORD_PRO_312007 = "ORD.PRO.312007";
        //发货数量不能小于1
        public static String ORD_PRO_312008 = "ORD.PRO.312008";
        //输入的总发货数量小于1
        public static String ORD_PRO_312009 = "ORD.PRO.312009";
        //该订单剩余发货数量小于1
        public static String ORD_PRO_312010 = "ORD.PRO.312010";
        //输入的是否全部发货标识错误
        public static String ORD_PRO_312011 = "ORD.PRO.312011";
        //货的数量大于剩余发货数量
        public static String ORD_PRO_312012 = "ORD.PRO.312012";
        
        //购物车明细为空
        public static String ORD_PRO_352000 = "ORD.PRO.352000";
        //购物车实例为空
        public static String ORD_PRO_352001 = "ORD.PRO.352001";
        //未找到商品信息
        public static String ORD_PRO_352002 = "ORD.PRO.352002";
        //已有宝贝提交过订单请刷新购物车
        public static String ORD_PRO_352003 = "ORD.PRO.352003";
        //订单号或会员名已绑定过，如确认无误请拨打010-59787584联系我们
        public static String ORD_PRO_352004 = "ORD.PRO.352004";
        //订单已取消
        public static String ORD_PRO_352005 = "ORD.PRO.352005";
    }
    
    public static class PayInputMsgCode{
        
        //入参不能为空
        public static String PAY_INPUT_300000 = "PAY.INPUT.300000";
        //订单编码不能为空
        public static String PAY_INPUT_300001 = "PAY.INPUT.300001";
        //支付通道不能为空
        public static String PAY_INPUT_300002 = "PAY.INPUT.300002";
        //支付流水不能为空
        public static String PAY_INPUT_300003 = "PAY.INPUT.300003";
        //支付类型不能为空
        public static String PAY_INPUT_300004 = "PAY.INPUT.300004";
        //零元订单不能跳转支付
        public static String PAY_INPUT_300005 = "PAY.INPUT.300005";
        //已支付订单不能跳转支付
        public static String PAY_INPUT_300006 = "PAY.INPUT.300006";
        //原始状态不能为空
        public static String PAY_INPUT_300007 = "PAY.INPUT.300007";
        //新状态不能为空
        public static String PAY_INPUT_300008 = "PAY.INPUT.300008";
        //定时任务主键不能为空
        public static String PAY_INPUT_300009 = "PAY.INPUT.300009";
        //定时任务类型不能为空
        public static String PAY_INPUT_300010 = "PAY.INPUT.300010";
        //文件ID不能为空
        public static String PAY_INPUT_300011 = "PAY.INPUT.300011";
        //分库标识错误不能小于0
        public static String PAY_INPUT_300012 = "PAY.INPUT.300012";
        //对账日期不能为空
        public static String PAY_INPUT_300013 = "PAY.INPUT.300013";
        //退款金额不能为空
        public static String PAY_INPUT_300014 = "PAY.INPUT.300014";
        //退货申请ID不能为空
        public static String PAY_INPUT_300015 = "PAY.INPUT.300015";
        //开始时间不能为空
        public static String PAY_INPUT_300016 = "PAY.INPUT.300016";
        //结束时间不能为空
        public static String PAY_INPUT_300017 = "PAY.INPUT.300017";
        //对账类型不能为空
        public static String PAY_INPUT_300018 = "PAY.INPUT.300018";
    }
    public static class PayServiceMsgCode{
        
        //支付回调页面返回服务异常
        public static String PAY_SERVER_310000 = "PAY.SERVER.310000";
        //支付回调页面参数解析服务异常
        public static String PAY_SERVER_310001 = "PAY.SERVER.310001";
        //支付配置错误返回的异常
        public static String PAY_SERVER_310002 = "PAY.SERVER.310002";
        //找不到订单
        public static String PAY_SERVER_310003 = "PAY.SERVER.310003";
        //找不到支付通道
        public static String PAY_SERVER_310004 = "PAY.SERVER.310004";
        //找不到子订单
        public static String PAY_SERVER_310005 = "PAY.SERVER.310005";
        //支付请求服务异常
        public static String PAY_SERVER_310006 = "PAY.SERVER.310006";
        //支付回调服务异常
        public static String PAY_SERVER_310007 = "PAY.SERVER.310007";
        //订单状态不是未支付
        public static String PAY_SERVER_310008 = "PAY.SERVER.310008";
        //找不到店铺支付通道配置信息
        public static String PAY_SERVER_310009 = "PAY.SERVER.310009";
        //找不到用户信息
        public static String PAY_SERVER_310010 = "PAY.SERVER.310010";
        //支付回调积分接口处理异常
        public static String PAY_SERVER_310011 = "PAY.SERVER.310011";
        //支付回调泽元数字教材授权接口处理异常
        public static String PAY_SERVER_310012 = "PAY.SERVER.310012";
        //支付回调泽元考试网授权接口处理异常
        public static String PAY_SERVER_310013 = "PAY.SERVER.310013";
        //获取定时任务要处理的订单异常
        public static String PAY_SERVER_310014 = "PAY.SERVER.310014";
        //退款请求服务异常
        public static String PAY_SERVER_310015 = "PAY.SERVER.310015";
        //退款金额不能比订单实际金额大
        public static String PAY_SERVER_310016 = "PAY.SERVER.310016";
        //支付结果表没有支付信息
        public static String PAY_SERVER_310017 = "PAY.SERVER.310017";
        //退款回调服务异常
        public static String PAY_SERVER_310018 = "PAY.SERVER.310018";
        //退款结果表没有退款记录
        public static String PAY_SERVER_310019 = "PAY.SERVER.310019";
        //退款回调更新状态失败
        public static String PAY_SERVER_310020 = "PAY.SERVER.310020";
        //支付回调优惠券接口处理异常
        public static String PAY_SERVER_310021 = "PAY.SERVER.310021";
        //对账接口异常
        public static String PAY_SERVER_310022 = "PAY.SERVER.310022";
        //交易清算查询异常
        public static String PAY_SERVER_310023 = "PAY.SERVER.310023";
    }
    public static class OtherSysMsgCode{
        //商品域
        /*-------调用商品域相关服务--start-------*/
        //调用商品域异常
        public static String CALL_GOODS_SERVER_340000 = "CALL.GOODS.340000";
        /*-------调用商品域相关服务--end-------*/
        //促销域
        /*-------调用促销域相关服务--start-------*/
        //调用促销域异常
        public static String CALL_PROM_SERVER_341000 = "CALL.PROM.341000";
        /*-------调用促销域相关服务--end-------*/
        //客户域
        /*-------调用客户域相关服务--start-------*/
        //调用客户域异常
        public static String CALL_STAFF_SERVER_342000 = "CALL.STAFF.342000";
        /*-------调用客户域相关服务--end-------*/
        /*-------调用客户域相关服务--start-------*/
        //调用客户域异常
        public static String CALL_COUP_SERVER_343000 = "CALL.COUP.343000";
        /*-------调用客户域相关服务--end-------*/
    }
    /**
     * Title: ECP <br>
     * Project Name:ecp-services-order-server <br>
     * Description: <br>
     * Date:2015年12月31日上午10:28:37  <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author cbl
     * @version MsgConstants 
     * @since JDK 1.6 
     */  
    public static class ChkMsgCode{
        /*-------退货退款--start-------*/
        //退货申请
        public static String CHK_BACKGDS_APPLY = "CHK.BACKGDS.APPLY.350001";
        //退货审核
        public static String CHK_BACKGDS_REVIEW = "CHK.BACKGDS.REVIEW.350002";
        //退货买家确认发货
        public static String CHK_BACKGDS_SEND = "CHK.BACKGDS.SEND.350003";
        //退货确认收货
        public static String CHK_BACKGDS_RECEIPT = "CHK.BACKGDS.RECEIPT.350004";
        //退货确认付款
        public static String CHK_BACKGDS_REFUND = "CHK.BACKGDS.REFUND.350005";
        //退货拒绝退货
        public static String CHK_BACKGDS_REFUSE = "CHK.BACKGDS.REFUSE.350006";
        //退款申请
        public static String CHK_REFUND_APPLY = "CHK.REFUND.APPLY.350007";
        //退款审核
        public static String CHK_REFUND_REVIEW = "CHK.REFUND.REVIEW.350008";
        //退款确认付款
        public static String CHK_REFUND_REFUND = "CHK.REFUND.REFUND.350009";
        //退款拒绝退款
        public static String CHK_REFUND_REFUSE = "CHK.REFUND.REFUSE.350010";                      
        //订单含有虚拟产品不允许退款
        public static String CHK_REFUND_GDSTYPE = "CHK.REFUND.GDSTYPE.350011";        
        //买家未发货
        public static String CHK_BACKGDS_BUYER_SEND = "CHK.BACKGDS.BUYERSEND.350012";
        //无权限操作订单
        public static String CHK_BACKGDS_PERMISSION = "CHK_BACKGDS_PERMISSION_350013";
        /*-------退货退款--end-------*/
    }
}


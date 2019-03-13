package com.zengshi.ecp.prom.dubbo.util;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromConstants {
    
    //模块常量定义
    public class PromSys {
        public static final int COUNT = 0;//总数
        public static final int PAGE_COUNT = 0;//页面数量
        public static final String IF_PLAT_QUERY="1";//平台查询 传入1
        public static final String IF_PROM_CHECK="1";//1开启促销申请 审核机制  其他为否
        public static final String IF_THROWS="1";//1是否开启抛出异常模式
        
        public static final String doAction_SAVE="SAVE";
        public static final String doAction_ROLLBACK="ROLLBACK";
        
        public static final String LIKE_PERCENT="%";
        public static final String LIKE_MULTIPLE="倍";

        
        public static final String BUYAMOUNT_CONST="buyAmount";//促销类型 1000010对应优惠规则key
        public static final String BUYMONEY_CONST="buyMoney";//促销类型 1000010对应优惠规则key
        
        public static final String AMOUNT_KEY="amount"; //数量key
        public static final String MONEY_KEY="money"; //金额key
        
        public static final String DISCOUNT_RATE_KEY="discountRate"; //分类折扣比例 key常量
        public static final String SEND_TYPE_KEY="sendType"; //赠送常量 key常量
        public static final String LEVEL="level"; //用户等级前缀
        //排除区域标记
        public static final String AREA_EXCLUDE_1="1";
        
        //是否显示 分类、商品选择
        public static final String PROM_TYPE_SYS_CFG_KEY="PROM_TYPE";
        //是否隐藏展示开始时间 展示截止时间
        public static final String PROM_TYPE_HIDE_SHOW_TIME="PROM_TYPE_HIDE_SHOW_TIME";
        
        public static final String MSG_CODE_SUCCESS="1";
        
        public static final String MSG_CODE_ERROR="0";
        
        //匿名用户 没有登录是否能看到促销列表 0 否 1是
        public static final String PROM_RULE_ANONYMOUS_CHECK="PROM_RULE_ANONYMOUS_CHECK";
        
        public static final int cacheSeconds = 1500;//秒  1500秒=25分钟
        
        public static final String PROM_ORD_RELA="promrela";//促销订单和明细的关联KEY
        
        public static final String PROM_USE_IF_SHOW_TIME="1";//使用促销时间
        
    }


    public class PromType {

        // 10订单 20单品 30外围活动--无优惠规则，只有限制条件
        public static final String PROM_ClASS_10 = "10";

        public static final String PROM_ClASS_20 = "20";

        public static final String PROM_ClASS_30 = "30";

        // 是否页面展示 1展示 0不展示
        public static final String IF_SHOW_1 = "1";

        public static final String IF_SHOW_0 = "0";

        // 状态 1有效 0无效
        public static final String STATUS_1 = "1";

        public static final String STATUS_0 = "0";
        
        public static final String PROM_TYPE_CODE_1000000011="1000000011";//自由搭配a
        public static final String PROM_TYPE_CODE_1000000013="1000000013";//自由搭配B
        public static final String PROM_TYPE_CODE_1000000014="1000000014";//固定搭配
        
        public static final String PROM_TYPE_CODE_1000000016="1000000016";//免邮促销
        //限时秒杀编码
        public static final String PROM_TYPE_CODE_1000000019 = "1000000019";//限时秒杀

    }

    public class PromInfo {

        // 状态 促销状态，00 草稿10生效 20失效 30草稿转删除 40 待审核 50 审核不通过 60系统删除
        public static final String STATUS_00 = "00";

        public static final String STATUS_10 = "10";

        public static final String STATUS_20 = "20";

        public static final String STATUS_30 = "30";

        public static final String STATUS_40 = "40";

        public static final String STATUS_50 = "50";

        public static final String STATUS_60 = "60";

        // 参与促销商品范围 0 部分商品参加 1全部商品参加 产品促销类型 默认0
        public static final String JOIN_RANGE_0 = "0";

        public static final String JOIN_RANGE_1 = "1";

        // 是否设置黑名单 0 无 1有商品黑名单  2有分类黑名单 3  有商品黑名单和有分类黑名单
        public static final String IF_BLACKLIST_0 = "0";

        public static final String IF_BLACKLIST_1 = "1";
        
        public static final String IF_BLACKLIST_2 = "2";
        
        public static final String IF_BLACKLIST_3 = "3";

        // 是否设置匹配搭配 单品 0 无 1有
        public static final String IF_MATCH_0 = "0";

        public static final String IF_MATCH_1 = "1";
        
        // 是否展示
        public static final String IF_SHOW_0 = "0";

        public static final String IF_SHOW_1 = "1";
        
        
        // 是否叠加
        public static final String IF_COMPOSIT_0 = "0";//不可叠加

        public static final String IF_COMPOSIT_1 = "1";//可叠加
        
        // 是否免邮
        public static final String IF_FREE_POST_0 = "0";

        public static final String IF_FREE_POST_1 = "1";
        
        
        
    }

    public class PromGroup {

        // 状态 状态，0失效，1生效 2 草稿
        public static final String STATUS_0 = "0";

        public static final String STATUS_1 = "1";

        public static final String STATUS_2 = "2";
    }

    public class PromTypeMsg {

        // 状态 状态，0失效，1生效
        public static final String STATUS_0 = "0";

        public static final String STATUS_1 = "1";

        // POSITION 展示位置 10购物车 20发送邮件
        public static final String POSITION_10 = "10";

        public static final String POSITION_20 = "20";
        
        //购物车初始化
        public static final String CART_SUBMIT_TYPE = "CART-SUBMIT";
        //结算提交
        public static final String ORDER_SUBMIT_TYPE = "ORDER-SUBMIT";
    }
    public class PromSku {

        // 搭配 1主商品 2搭配商品
        public static final String MATCH_TYPE_2 = "2";

        public static final String MATCH_TYPE_1 = "1";
        
        // 参与类型 1 分类参与 2 单品参与
        public static final String JOIN_TYPE_2 = "2";
        public static final String JOIN_TYPE_1 = "1";
        
        // 是否有效 1有效 0 无效
        public static final String IF_VALID_1 = "1";
        public static final String IF_VALID_0 = "0";
        
    }
    public class PromMatchSku {

        // 状态，0失效，1生效
        public static final String STATUS_0 = "0";
        public static final String STATUS_1 = "1";
        
        // 状态，1 可选搭配  2 固定搭配
        public static final String MATCH_TYPE_1 = "1";
        public static final String MATCH_TYPE_2 = "2";
    }

    public class PromType4Shop {

        // 状态，0失效，1生效
        public static final String STATUS_0 = "0";
        public static final String STATUS_1 = "1";
        
    }
    public class PromConstraint {

        // 客户组，0未选择，1选择
        public static final String CUSTGROUP_0 = "0";

        public static final String CUSTGROUP_1 = "1";
        
        // 客户等级，0未选择，1选择
        public static final String CUSTLEVEL_0 = "0";

        public static final String CUSTLEVEL_1 = "1";
        
        // 渠道，0未选择，1选择
        public static final String CHANNEL_0 = "0";

        public static final String CHANNEL_1 = "1";
        
        // 区域，0未选择，1选择
        public static final String AREA_0 = "0";

        public static final String AREA_1 = "1";
        
        // 购买次数限制，0 没有限制  1日限制 2月限制 3年限制
        public static final String LIMITTIMESTYPE_0 = "0";
        
        public static final String LIMITTIMESTYPE_1 = "1";

        public static final String LIMITTIMESTYPE_2 = "2";

        public static final String LIMITTIMESTYPE_3 = "3";
        
        
        // 购买总量限制，0 没有限制 1日限制 2月限制 3年限制
        public static final String LIMITTOTALTYPE_0 = "0";
        
        public static final String LIMITTOTALTYPE_1 = "1";

        public static final String LIMITTOTALTYPE_2 = "2";

        public static final String LIMITTOTALTYPE_3 = "3";
    }
    public class PromGroupChk {

        // 状态，0拒绝，1通过
        public static final String STATUS_0 = "0";

        public static final String STATUS_1 = "1";
    }
    //促销配置表参数  t_base_param_config
    public class PromKey {

        //促销类型状态
        public static final String PROM_TYPE_STATUS = "PROM_TYPE_STATUS";
        //促销类型类别
        public static final String PROM_TYPE_PROM_CLASS = "PROM_TYPE_PROM_CLASS";
        //促销类型是否展示
        public static final String PROM_TYPE_IF_SHOW = "PROM_TYPE_IF_SHOW";
        //促销授权状态
        public static final String PROM_TYPE4SHOP_STATUS = "PROM_TYPE4SHOP_STATUS";
        //促销状态（有效状态）
        public static final String PROM_INFO_STATUS = "PROM_INFO_STATUS";
        
        //促销状态 全部状态
        public static final String PROM_INFO_STATUS_ALL = "PROM_INFO_STATUS_ALL";
        
        //促销参与促销商品范围
        public static final String PROM_INFO_JOIN_RANGE = "PROM_INFO_JOIN_RANGE";
        //促销是否设置搭配单品
        public static final String PROM_INFO_IF_MATCH = "PROM_INFO_IF_MATCH";
        //促销审核
        public static final String PROM_CHK_STATUS = "PROM_CHK_STATUS";
        //促销提醒信息展示位置
        public static final String PROM_TYPE_MSG_POSITION = "PROM_TYPE_MSG_POSITION";
        //促销提醒信息 状态
        public static final String PROM_TYPE_MSG_STATUS = "PROM_TYPE_MSG_STATUS";
       //促销组 状态
        public static final String PROM_GROUP_STATUS = "PROM_GROUP_STATUS";
        
        //促销类型 基础配置表key  可以取code name
        public static final String PROM_TYPE = "PROM_TYPE";

        
        //促销类型 业务内部自己定义缓存key  可以取 promTypeResponseDTO
        public static final String TYPE_PROM = "TYPE_PROM";

        //促销类型 提醒信息 可以取code name
        public static final String PROM_TYPE_MSG = "PROM_TYPE_MSG";
        
        //促销类型 提醒信息  PROM_TYPE_CODE+STATUS+POSTION 可以取code name
        public static final String PROM_TYPE_MSG_CODE = "PROM_TYPE_MSG_CODE";
        
        //促销类型 提醒信息  PROM_TYPE_CODE+STATUS+POSTION 可以取promTypeMsgResponseDTO
        public static final String CODE_PROM_TYPE_MSG = "CODE_PROM_TYPE_MSG";
        
        
        //促销规则 总量限制
        public static final String LIMIT_TOTAL_TYPE="LIMIT_TOTAL_TYPE";
        //促销规则 次数限制
        public static final String LIMIT_TIMES_TYPE="LIMIT_TIMES_TYPE";
        //促销规则 渠道来源
        public static final String CHANNEL_SOURCE="CHANNEL_SOURCE";
        
        //促销 积分赠送 页面选择类型key
        public static final String PROM_SEND_POINTS_TYPE="PROM_SEND_POINTS_TYPE";
        
        //站点key
        public static final String CMS_SITE="CMS_SITE";
        
        //T_BASE_SYS_CFG 
        //是否开启审核
        public static final String IF_PROM_CHECK_KEY="IF_PROM_CHECK";
        
        public static final String IF_PROM_POINTS_DEDUCT="IF_PROM_POINTS_DEDUCT";
        
        //购物车开启多线程
        public static final String PROM_SET_MULITTHREAD_CART="PROM_SET_MULITTHREAD_CART";
        
        

    }
    //促销优惠规则
    public class PromDiscountRule {
        
        public static final String SEND_POINTS_0="0";//按照固定值（积分）赠送
        public static final String SEND_POINTS_1="1";//按照指定值的（积分）倍数赠送
        
        public static final String SECKILL_PRICE_TYPE_0="0"; //0折扣率
        public static final String SECKILL_PRICE_TYPE_1="1"; //1固定价格

        
    }
    public class Prom2Solr {

        // 状态 状态，0失效，1生效
        public static final String SEND_TYPE_0 = "0";
        public static final String SEND_TYPE_1 = "1";
        
        // 发送状态 0待发送 1已发送
        public static final String SEND_STATUS_0 = "0";
        public static final String SEND_STATUS_1 = "1";
        
        public static final String SEND_RANGE_1 = "1";//全量发送
        public static final String SEND_RANGE_2 = "2";//分类发送
        public static final String SEND_RANGE_3 = "3";//商品发送
        
       // 发送solr类型 1发送solr关键字消息 2发送solr 促销商品关联关系
        public static final String SOLR_TYPE_1 = "1";
        public static final String SOLR_TYPE_2 = "2";
 
    }
    public class PromImportData {
        public static final String IMPORT_TYPE_0 = "0";//默认类型（商品导入默认类型）
        public static final String IMPORT_TYPE_1 = "1";
    }
}

/** 
\ * Project Name:ecp-services-goods 
 * File Name:GdsContants.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.util 
 * Date:2015年8月11日上午10:28:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.dubbo.constants;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月11日上午10:28:12 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public final class GdsConstants {
    /**
     * 
     * Title: 商品域通用常量 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月11日上午10:29:49 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsContants
     * @since JDK 1.6
     */
    public static class Commons {

        /**
         * 字符串0,无效状态常量.
         */
        public static final String STATUS_INVALID = "0";

        /**
         * 字符串1,有效状态常量.
         */
        public static final String STATUS_VALID = "1";
        
        /**
         * 字符串3,有效状态(线下)常量.
         */
        public static final String STATUS_OFFLINE = "3";

        /**
         * 待审核状态.
         */
        public static final String STATUS_PENDING_AUDIT = "2";

        /**
         * 审核不通过.
         */
        public static final String STATUS_AUDIT_NOT_THROUGH = "3";

        private Commons() {
        };

    }

    /**
     * 
     * 商品域-商品模块常量 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月14日上午10:10:32 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsContants
     * @since JDK 1.6
     */
    public static class GdsCategory {

        /**
         * 默认层级1,当存在父分类时LEVEL+1,不存在时层级为1.
         */
        public static final short TOP_LEVEL = 1;

        /**
         * 1为平台分类
         */
        public static final String CATG_TYPE_1 = "1";

        /**
         * 2为店铺分类
         */
        public static final String CATG_TYPE_2 = "2";

        /**
         * 1不需要录入串号(串号即单品的实体编码一个串号对应一个实物单品)
         */
        public static final String IF_ENTITY_CODE_1 = "1";

        /**
         * 2需要录入串号 (串号即单品的实体编码一个串号对应一个实物单品)
         */
        public static final String IF_ENTITY_CODE_2 = "2";

        /**
         * 3仅在发货是录入串号(串号即单品的实体编码一个串号对应一个实物单品)
         */
        public static final String IF_ENTITY_CODE_3 = "3";

        /**
         * 是否在首页显示 0-不在首页显示
         */
        public static final String IF_SHOW_0 = "0";

        /**
         * 是否在首页显示 1-在首页显示
         */
        public static final String IF_SHOW_1 = "1";

        /**
         * 是否叶节点 0－非叶节点，即存在子分类。
         */
        public static final String IF_LEAF_0 = "0";

        /**
         * 是否叶节点1－叶节点，表示不存在子分类。
         */
        public static final String IF_LEAF_1 = "1";

        private GdsCategory() {
        };

    }

    /**
     * 商品主体常量 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月22日下午3:06:12 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsInfo {
    	
    	
    	public static final String IF_CHECK_ONSHEVLES = "IF_CHECK_ONSHEVLES";

        /**
         * 商品状态缓存key
         */
        public static final String GDS_INFO_STATUS_KEY = "GDS_INFO_STATUS";

        /**
         * 商品默认URL
         */
        public static final String GDS_DEFAULT_URL = "GDS_DEFAULT_URL";

        /**
         * 商品类型缓存key
         */
        public static final String GDS_INFO_TYPE_KEY = "GDS_INFO_TYPE";

        /**
         * 图片限制参数缓存key
         */
        public static final String GDS_ENTRY_PIC_LIMIT_COUNT = "GDS_ENTRY_PIC_LIMIT_COUNT";

        /**
         * 高级库存开关参数缓存key
         */
        public static final String GDS_ENTRY_STOCK_SENIOR = "GDS_ENTRY_STOCK_SENIOR";

        /**
         * 高级价格开关参数缓存key
         */
        public static final String GDS_ENTRY_PRICE_SENIOR = "GDS_ENTRY_PRICE_SENIOR";

        /**
         * 运费模板为空时 即为-1
         */
        public static final Long GDS_INFO_TEMPLATEID_NULL = -1L;

        /**
         * 商品待上架状态
         */
        public static final String GDS_STATUS_WAITSHELVES = "0";

        /**
         * 商品上架状态
         */
        public static final String GDS_STATUS_ONSHELVES = "11";

        /**
         * 商品下架状态
         */
        public static final String GDS_STATUS_OFFSHELVES = "22";

        /**
         * 商品删除状态
         */
        public static final String GDS_STATUS_DELETE = "99";
        
        /**
         * 商品线下状态
         */
        public static final String GDS_STATUS_OFFLINE = "33";

        /**
         * 商品审批状态 待审批
         */
        public static final String GDS_APPROVE_WAIT = "0";

        /**
         * 商品审批状态 审批中
         */
        public static final String GDS_APPROVE_ING = "11";

        /**
         * 商品审批状态 审批通过
         */
        public static final String GDS_APPROVE_PASS = "22";

        /**
         * 商品审批状态 审批不通过
         */
        public static final String GDS_APPROVE_NOPASS = "99";

        /**
         * 实体编码策略 不需要录入实体编码
         */
        public static final String IF_ENTITY_CODE_NO = "1";

        /**
         * 实体编码策略 需要录入实体编码
         */
        public static final String IF_ENTITY_CODE_NEED = "2";

        /**
         * 实体编码策略 发货时才需要录入
         */
        public static final String IF_ENTITY_CODE_DELIVER = "3";

        /**
         * 单品属性串连接符号
         */
        public static final String SKU_PROP_STR_SPILT = "/";

        /**
         * 商品分类关系 主分类
         */
        public static final String GDS_2_CATG_RTYPE_MAIN = "1";

        /**
         * 商品分类关系 普通分类
         */
        public static final String GDS_2_CATG_RTYPE_ORDI = "2";

        /**
         * 价格类型编码 普通价格
         */
        public static final String SKU_PRICE_TYPE_ORDINARY = "gdsPriceOrdinary";

        /**
         * 价格类型编码 阶梯价
         */
        public static final String SKU_PRICE_TYPE_LADDER = "gdsPriceLadder";

        /**
         * 价格类型编码 地区定价
         */
        public static final String SKU_PRICE_TYPE_AREA = "gdsPriceArea";

        /**
         * 价格类型编码 分客户定价
         */
        public static final String SKU_PRICE_TYPE_CUSGROUP = "gdsPriceCusGroup";

        /**
         * 是否是阶梯价，1 是
         */
        public static final String GDS_LADDER_PRICE = "1";

        /**
         * 是否是阶梯价，0 否
         */
        public static final String NOT_GDS_LADDER_PRICE = "1";

        private GdsInfo() {
        };

    }

    /**
     * 商品缓存常量 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月22日下午3:06:12 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsInfoCacheKey {

        /**
         * 商品基本信息缓存前缀
         */
        public static final int GDS_CACHE_TIME = 3600 * 12;

        /**
         * 商品基本信息缓存前缀 后缀为商品编码
         */
        public static final String GDS_CACHE_KEY_PREFIX = "GDS_BASEINFO_";

        /**
         * 商品主图信息缓存前缀 后缀为商品编码
         */
        public static final String GDS_MAINPIC_CACHE_KEY_PREFIX = "GDS_MAINPIC_";

        /**
         * 商品图片列表信息缓存前缀 后缀为商品编码
         */
        public static final String GDS_PICLIST_CACHE_KEY_PREFIX = "GDS_PICLIST_";

        /**
         * 商品属性列表信息缓存前缀 后缀为商品编码
         */
        public static final String GDS_PROPLIST_CACHE_KEY_PREFIX = "GDS_PROPLIST_";

        /**
         * 单品基本信息缓存前缀
         */
        public static final int SKU_CACHE_TIME = 3600 * 12;

        /**
         * 单品基本信息缓存前缀
         */
        public static final String SKU_CACHE_KEY_PREFIX = "GDS_SKU_BASEINFO_";

        /**
         * 单品主图信息缓存前缀 后缀为单品编码
         */
        public static final String SKU_MAINPIC_CACHE_KEY_PREFIX = "GDS_SKU_MAINPIC_";

        /**
         * 单品图片列表信息缓存前缀 后缀为单品编码
         */
        public static final String SKU_PICLIST_CACHE_KEY_PREFIX = "GDS_SKU_PICLIST_";

        /**
         * 单品属性列表信息缓存前缀 后缀为单品编码
         */
        public static final String SKU_PROPLIST_CACHE_KEY_PREFIX = "GDS_SKU_PROPLIST_";

        /**
         * 单品基本信息价格缓存前缀 后缀为单品编码
         */
        public static final String SKU_PRICE_CACHE_KEY_PREFIX = "GDS_SKU_PRICE_";

        private GdsInfoCacheKey() {
        };

    }

    /**
     * 商品域-商品类型常量 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年9月17日下午5:07:37 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsType {

        /**
         * 商品类型cache前缀
         */
        public static final String GDS_TYPE_KEY_PREFIX = "GDS_TYPE_INFO_CACHE_";

        private GdsType() {
        };

    }

    /**
     * 商品域-商品价格常量 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年9月17日下午5:07:37 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsPrice {

        /**
         * 价格关系-单品价格
         */
        public static final String GDS_PRICE_RTYPE_SKU = "1";

        /**
         * 价格关系-商品价格
         */
        public static final String GDS_PRICE_RTYPE_GDS = "2";

        private GdsPrice() {
        };

    }

    /**
     * 
     * Title: 商品库存常量 <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2016年3月26日上午11:20:50 <br>
     * Copyright (c) 2016 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsStock {

        /**
         * 库存量新增
         */
        public static final String STOCK_INFO_TURN_NEW = "00";

        /**
         * 库存量变更增加类型
         */
        public static final String STOCK_INFO_TURN_UP = "01";

        /**
         * 库存量变更减少类型
         */
        public static final String STOCK_INFO_TURN_DOWN = "02";

        /**
         * 库存量发货类型
         */
        public static final String STOCK_INFO_TURN_SEND = "03";

        /**
         * 取消预占
         */
        public static final String STOCK_INFO_TURN_CANCEL_PRE = "04";

        /**
         * 新增预占
         */
        public static final String STOCK_INFO_TURN_ADD_PRE = "05";

        /**
         * 清空库存
         */
        public static final String STOCK_INFO_TURN_CLEARALL = "06";

        /**
         * 确认收货
         */
        public static final String STOCK_INFO_TURN_AFFIRM = "07";

        /**
         * 库存量退货类型
         */
        public static final String STOCK_INFO_TURN_RETURN = "08";

        /**
         * 库存量同步ERP接口
         */
        public static final String STOCK_INFO_TURN_FIXEDVAL = "09";

        /**
         * 仓库用户类型--卖家
         */
        public static final String STOCK_CODE_TYPE_SELLER = "01";

        /**
         * 仓库用户类型--买家
         */
        public static final String STOCK_CODE_TYPE_BUYER = "00";

        /**
         * 单品实体状态 空闲
         */
        public static final String STOCK_SKU_ENTITY_STATUS_FREE = "01";

        /**
         * 单品实体状态 已发货
         */
        public static final String STOCK_SKU_ENTITY_STATUS_SEND = "02";

        /**
         * 单品实体状态 确认收货
         */
        public static final String STOCK_SKU_ENTITY_STATUS_RECEIVE = "03";

        /**
         * 单品实体状态 已删除
         */
        public static final String STOCK_SKU_ENTITY_STATUS_DEL = "04";

        /**
         * 单品实体状态 已退货
         */
        public static final String STOCK_SKU_ENTITY_STATUS_RETURN = "05";

        /**
         * 仓库类型 买家仓库
         */
        public static final String STOCK_REP_TYPE_BUYER = "00";

        /**
         * 仓库类型 卖家总仓
         */
        public static final String STOCK_REP_TYPE_PUBLIC = "01";

        /**
         * 仓库类型 卖家分仓
         */
        public static final String STOCK_REP_TYPE_SEPERATE = "02";

        /**
         * 库存类型 共仓
         */
        public static final String STOCK_INFO_TYPE_PUBLIC = "01";

        /**
         * 仓库类型 分仓
         */
        public static final String STOCK_INFO_TYPE_SEPERATE = "02";

        /**
         * 缺货
         */
        public static final String STOCK_STATUS_LACK = "00";

        /**
         * 紧俏
         */
        public static final String STOCK_STATUS_HARDTOGET = "01";

        /**
         * 充裕
         */
        public static final String STOCK_STATUS_ENOUGH = "02";

        /**
         * 缺货常量阈值名称
         */
        public static final String STOCK_LACK_THRESHOLD = "STOCK_LACK_THRESHOLD";

        /**
         * 紧俏常量阈值名称
         */
        public static final String STOCK_HARDTOGET_THRESHOLD = "STOCK_HARDTOGET_THRESHOLD";

        /**
         * 积分商品紧俏常量阈值名称
         */
        public static final String STOCK_SCORE_HARDTOGET_THRESHOLD = "STOCK_SCORE_HARDTOGET_THRESHOLD";

        /**
         * 积分商品缺货常量阈值名称
         */
        public static final String STOCK_SCORE_LACK_THRESHOLD = "STOCK_SCORE_LACK_THRESHOLD";
        /**
         * 是否支持工厂库存.0-不支持 1－支持。
         */
        public static final String FLAG_SUPPORT_FACSTOCK = "FLAG_SUPPORT_FACSTOCK";

        private GdsStock() {
        };

    }

    /**
     * 
     * 标签常量.<br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月22日上午10:50:36 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsLabel {

        /**
         * 标签类型 1-产品用标签
         */
        public static final String LABEL_TYPE_1 = "1";

        /**
         * 标签类型 2-评价标签
         */
        public static final String LABEL_TYPE_2 = "2";

        /**
         * 标签类型 3-店铺标签
         */
        public static final String LABEL_TYPE_3 = "3";

        private GdsLabel() {
        };

    }

    /**
     * 
     * 商品域-商品属性相关常量. <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月14日下午3:35:48 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsContants
     * @since JDK 1.6
     */
    public static class GdsProp {

        /**
         * 1 手工输入
         */
        public static final String PROP_VALUE_TYPE_1 = "1";

        /**
         * 2 单选
         */
        public static final String PROP_VALUE_TYPE_2 = "2";

        /**
         * 3 多选
         */
        public static final String PROP_VALUE_TYPE_3 = "3";
        /**
         * 4富文本
         */
        public static final String PROP_VALUE_TYPE_4 = "4";

        /**
         * 1 规格属性
         */
        public static final String PROP_TYPE_1 = "1";

        /**
         * 2 参数属性
         */
        public static final String PROP_TYPE_2 = "2";

        /**
         * 3 普通属性
         */
        public static final String PROP_TYPE_3 = "3";

        /**
         * 字符数字
         */
        public static final String GDS_PROP_VALUE_INPUT_TYPE_ZIFU = "1";

        /**
         * 时间
         */
        public static final String GDS_PROP_VALUE_INPUT_TYPE_TIME = "2";

        /*
         * 富文本
         */
        public static final String GDS_PROP_VALUE_INPUT_TYPE_RICHTXT = "3";

        /*
         * 文件
         */
        public static final String GDS_PROP_VALUE_INPUT_TYPE_FILE = "4";

        public static final Long GDS_DIGITAL_PRODUCT_PRICE_PROP_ID = 1029L;

        private GdsProp() {
        };
    }

    /**
     * 
     * 商品类别属性关联关系常量类。 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月18日上午10:07:08 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsCatg2Prop {

        /**
         * 是否基础属性 0-表示不是基础属性。
         */
        public static final String IS_BASIC_0 = "0";

        /**
         * 是否基础属性 1-表示是基础属性。
         */
        public static final String IS_BASIC_1 = "1";

        /**
         * 是否必填 0-表示非必填。
         */
        public static final String IS_REQUIRE_0 = "0";

        /**
         * 是否必填 1-表示必填。
         */
        public static final String IS_REQUIRE_1 = "1";

        /**
         * 是否搜索属性 0-表示非搜索属性。
         */
        public static final String IS_SEARCH_0 = "0";

        /**
         * 是否搜索属性 1-表示搜索属性。
         */
        public static final String IS_SEARCH_1 = "1";

        /**
         * 是否输入 0-表示不输入。
         */
        public static final String IS_GDS_INPUT_0 = "0";

        /**
         * 是否输入 1-表示输入。
         */
        public static final String IS_GDS_INPUT_1 = "1";

        private GdsCatg2Prop() {
        };

    }

    /**
     * 
     * 媒体分类常量. Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月22日上午11:30:22 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsMediaCategory {

        /**
         * 默认层级1,当存在父分类时LEVEL+1,不存在时层级为1.
         */
        public static final short TOP_LEVEL = 1;

        private GdsMediaCategory() {
        };

    }

    /**
     * 
     * 媒体常量. Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月22日上午11:30:22 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsMedia {

        /**
         * 媒体类型 图片
         */
        public static final String MEDIA_TYPE_PIC = "1";

        /**
         * 媒体类型 视频
         */
        public static final String MEDIA_TYPE_VEDIO = "2";

        /**
         * 媒体类型 音频
         */
        public static final String MEDIA_TYPE_AUDIO = "3";

        /**
         * 媒体关系类型 直接上传
         */
        public static final String MEDIA_RTYPE_UPLOAD = "2";

        /**
         * 媒体关系类型 媒体库引用
         */
        public static final String MEDIA_RTYPE_REFRENCE = "1";

        /**
         * 媒体关系 商品主图排序值
         */
        public static final int MEDIA_MAINPIC_SORTNO = 1;

        /**
         * 商品音频默认限制大小 单位b
         */
        public static final String MEDIA_KEY_LIMIT_GDS_AUDIO = "LIMIT_GDS_AUDIO";

        /**
         * 商品图片默认限制大小 单位b
         */
        public static final String MEDIA_KEY_LIMIT_GDS_PICTURE = "LIMIT_GDS_PICTURE";

        /**
         * 商品视频默认限制大小 单位b
         */
        public static final String MEDIA_KEY_LIMIT_GDS_VEDIO = "LIMIT_GDS_VEDIO";

        private GdsMedia() {
        };

    }

    /**
     * 
     * 商品域-缓存键常量 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月14日上午10:11:05 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsContants
     * @since JDK 1.6
     */
    public static class CacheKey {

        /**
         * 顶级分类缓存（包含非首页展示）,数据结构为List<Long>
         */
        public static final String CATG01_TOP_ALL = "CATG01_TOP_ALL";

        /**
         * 顶级分类缓存（首页展示）,数据结构为List<Long>
         */
        public static final String CATG01_TOP_SHOW = "CATG01_TOP_SHOW";

        /**
         * 所有产品类型缓存,数据结构为List<String>
         */
        public static final String GDSTYPE01_ALL = "GDSTYPE01_ALL";

        /**
         * 商品目录缓存全局键值.
         */
        public static final String CACHE_KEY_GDS_CATALOG = "CACHE_GDS_CATALOG_";
        /**
         * 商品默认目录级存值.
         */
        public static final String CACHE_KEY_GDS_DEFAULT_CATALOG = "GDS_DEFAULT_CATALOG_";
        
        /**
         * 目录站点关联关系-基于站点ID作为缓存键
         */
        public static final String CACHE_KEY_CATALOG2SITE_BY_SITEID = "CACHE_KEY_CATALOG2SITE_BY_SITEID_";

        /**
         * 顶级分类缓存（包含非首页展示）,数据结构为List<Long>
         */
        public static final String GDS_CATG_CATG01_TOP_ALL = "CATG01_TOP_ALL";

        /**
         * 分类缓存键前缀.
         */
        public static final String CACHE_KEY_GDS_CATEGORY_ = "CACHE_KEY_GDS_CATEGORY_";

        /**
         * 商品分类全局级存键.
         */
        public static final String GLOBAL_CACHE_KEY_GDS_CATEGORY = "GLOBAL_CACHE_KEY_GDS_CATEGORY";

        /**
         * 评价评单缓存前缀.(用于保障不重复发短信.)
         */
        public static final String CACHE_PREFIX_EVAL_ORDER_ID = "CACHE_PREFIX_EVAL_ORDER_ID_";
        
        /**
         * 基于店铺为缓存键目录与店铺关联关系.
         */
        public static final String CACHE_KEY_CATLOG2SHOP_BY_SHOP = "CACHE_KEY_CATLOG2SHOP_BY_SHOP_";

        private CacheKey() {
        };

    }

    /**
     * 
     * Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: 商品赠品常量<br>
     * Date:2015年8月24日下午7:55:42 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author gxq
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsGift {

        /**
         * 调增
         */
        public static final String UP = "1";

        /**
         * 调减
         */
        public static final String DOWN = "0";
        
        /**
         * 实物赠品
         */
        public static final String GIFTTYPE_ORDINARY = "1";

        /**
         * 虚拟赠品
         */
        public static final String GIFTTYPE_VIRTUAL = "0";
        
        private GdsGift() {
        };
    }

    /**
     * 
     * 评价常量。 Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月25日下午4:57:55 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsEval {

        /**
         * 是否匿名 0－表示非匿名
         */
        public static final String IS_ANONYMOUS_0 = "0";

        /**
         * 是否匿名 1－表示匿名
         */
        public static final String IS_ANONYMOUS_1 = "1";

        /**
         * 调用FileUtil时传的默认文件名.
         */
        public static final String DEFAULT_FILE_NAME = "GDS_EVAL";

        /**
         * 调用FileUtil时传的文件类型.
         */
        public static final String FILE_TYPE_HTML = "html";

        private GdsEval() {
        };

    }

    /**
     * 
     * 评价回复常量。 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月25日上午11:06:41 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsEvalReply {

        /**
         * 为规避字段为NULL对查询性能引起的影响，针对回复ID为空的评价回复设置默认-1.
         */
        public static final Long REPLY_ID_NULL = -1L;

        /**
         * 是否已回复 0-未回复.
         */
        public static final Short IS_REPLY_0 = 0;

        /**
         * 是否已回复 1-已回复.
         */
        public static final Short IS_REPLY_1 = 1;

        /**
         * 调用FileUtil时传的默认文件名.
         */
        public static final String DEFAULT_FILE_NAME = "GDS_EVAL_REPLY";

        /**
         * 调用FileUtil时传的文件类型.
         */
        public static final String FILE_TYPE_HTML = "html";

        /**
         * 买家回复类型.
         */
        public static final String REPLY_TYPE_BUYER = "1";

        /**
         * 卖家回复类型.
         */
        public static final String REPLY_TYPE_SELLER = "2";

        private GdsEvalReply() {
        };

    }

    public static class GdsShiptemp {

        /**
         * 默认地区编码
         */
        public static final String DEFAULT_AREA_CODE = "00";

        /**
         * 运费模板规则编码
         */
        public static final String GDS_SHIPMENT_RULE_01 = "1";

        /**
         * 运费模板规则编码
         */
        public static final String GDS_SHIPMENT_RULE_02 = "2";

        /**
         * 按件计算运费
         */
        public static final String GDS_SHIPMENT_BY_COUNT = "1";

        /**
         * 按重量计算运费
         */
        public static final String GDS_SHIPMENT_BY_WEIGHT = "2";

        /**
         * 按体积计算运费
         */
        public static final String GDS_SHIPMENT_BY_VOLUME = "3";

        /**
         * 按金额计算运费
         */
        public static final String GDS_SHIPMENT_BY_MONEY = "4";

        private GdsShiptemp() {
        };

    }

    /**
     * 
     * Title: 目录常量 <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2015-9-21上午9:37:35 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsCatlog {

        /**
         * 默认服务类型,默认设置为1
         */
        public static final String CATLOG_TYPE_1 = "1";

        private GdsCatlog() {
        };

    }

    /**
     * 
     * Title: 商品到货通知常量 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月11日上午10:29:49 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsContants
     * @since JDK 1.6
     */
    public static class GdsArrMsg {

        /**
         * 到货通知类型 到货通知
         */
        public static final String ARRMSG_TYPE_ARR = "1";

        /**
         * 到货通知类型 降价通知
         */
        public static final String ARRMSG_TYPE_PRICE = "2";

        private GdsArrMsg() {
        };

    }

    public static class SysCfgConstants {
        /**
         * 系统配置:商品评价是否增加审核开关键值. 0-关闭审核 1-开启审核
         */
        public static final String SWITCH_GDS_EVAL_AUDIT = "SWITCH_GDS_EVAL_AUDIT";

        /**
         * 商品类别最大层级系统配置键值.
         */
        public static final String GDS_CATEGORY_MAX_LEVEL = "GDS_CATEGORY_MAX_LEVEL";

        /**
         * 系统配置:商品目录审核开关 0-关闭审核 1-开启审核
         */
        public static final String SWITCH_GDS_CATLOG_AUDIT = "SWITCH_GDS_CATLOG_AUDIT";

        /**
         * 人卫商城目录ID。
         */
        public static final String CATLOG_ID_RENWEI = "CATLOG_ID_RENWEI";

        /**
         * 积分商城目录ID。
         */
        public static final String CATLOG_ID_JIFEN = "CATLOG_ID_JIFEN";

        /**
         * 纸质书分类ID。
         */
        public static final String PAPERY_CATG_CODE = "PAPERY_CATG_CODE";

        /**
         * 系统配置：第一次评价是否调用短信服务开关 0-关闭表示不调用 1－开启表示调用
         */
        public static final String SWITCH_GDS_EVAL_INVOKE_SMS = "SWITCH_GDS_EVAL_INVOKE_SMS";
        /**
         * 商城商品数量单位
         */
        public static final String MALL_GDS_DETAIL_UNIT = "MALL_GDS_DETAIL_UNIT";

        private SysCfgConstants() {
        };
    }

    public static class BaseParamConstants {
        /**
         * 目录状态参数键值常量
         */
        public static final String GDS_CATLOG_STATUS = "GDS_CATLOG_STATUS";

        /**
         * 到货通知通知状态参数键常量。
         */
        public static final String GDS_ARRMSG_STATUS = "GDS_ARRMSG_STATUS";

        private BaseParamConstants() {
        };
    }

    /**
     * 
     * Title: ECP <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: 积分商品价格<br>
     * Date:2015年10月23日下午7:39:23 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author zjh
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsScorePrice {
        /**
         * 纯积分
         */
        public static final String SCORE_TYPE_SCORE_PURE = "1";

        /**
         * 金额和积分
         */
        public static final String SCORE_TYPE_SCORE_MIX = "2";

        /**
         * 纯金额
         */
        public static final String SCORE_TYPE_CASH_PURE = "3";

        private GdsScorePrice() {
        };
    }

    /**
     * 
     * Title: ECP <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: 商品校验常量<br>
     * Date:2015年11月2日上午11:40:40 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author zjh
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsCheckInfo {

        // 特定Public公共帐户校验商品分类
        public static final String[] CHECKCATG_ARRAY = new String[] { "2", "11" };

        private GdsCheckInfo() {
        };
    }

    public static class GdsVerify {
        /**
         * 提交待审核
         */
        public static final String WAITE_VERIFY = "00";

        /**
         * 审核通过
         */
        public static final String VERIFY_APPROVED = "01";

        /**
         * 审核拒绝
         */
        public static final String VERIFY_REFUSE = "02";

        private GdsVerify() {
        };
    }

    /**
     * 
     * Title: ECP <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: 商品域数据权限功能编码.<br>
     * Date:2016-3-5下午3:35:12 <br>
     * Copyright (c) 2016 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsDataAuthFunc {
        /**
         * 商品信息查询数据权编码.
         */
        public static final String GDS_INFO_SEARCH_DA1006 = "DA1006";

        /**
         * 商品平台分类数据权编码.
         */
        public static final String GDS_CATG_SEARCH_DA1007 = "DA1007";

        private GdsDataAuthFunc() {
        };

    }

    /**
     * 
     * Title: 商品浏览消息 <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2015年11月20日上午9:46:50 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author zjh
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsBrowseMessage {

        public static final String BROWSE_SKUID = "skuId";

        public static final String BROWSE_STAFFID = "staffId";

        public static final String BROWSE_SHOPID = "shopId";

        public static final String BROWSE_GDSID = "gdsId";

        public static final String BROWSE_BROWSETIME = "browseTime";

        public static final String BROWSE_BROWSEPRICE = "browsePrice";

        public static final String BROWSE_BROWSECOUNT = "browseCount";

        private GdsBrowseMessage() {
        };
    }

    /**
     * 
     * Title: 商品删除消息 <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2016年3月26日上午11:15:46 <br>
     * Copyright (c) 2016 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class GdsDelMessage {

        public static final String DEL_GDSID = "gdsId";

        private GdsDelMessage() {
        };

    }

    /**
     * 
     * Title: 库存分类变更消息 <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2016年3月26日上午11:15:26 <br>
     * Copyright (c) 2016 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsConstants
     * @since JDK 1.6
     */
    public static class StockCatgChangeMessage {

        public static final String SHOP_ID = "shopId";

        public static final String GDS_ID = "gdsId";

        public static final String SKU_ID = "skuId";

        public static final String CATG_CODE = "catgCode";

        public static final String COMPANY_ID = "companyId";

        private StockCatgChangeMessage() {
        };

    }

    
    public static class GdsExcelImpLog{
    	/**
    	 * 未导入
    	 */
       	public static final String GDS_EXCEL_IMP_LOG_STATUS_UNDEAL = "00";
       	
       	/**
       	 * 已导入中间表
       	 */
       	public static final String GDS_EXCEL_IMP_LOG_STATUS_TEMPDONE = "01";
       	
    	/**
    	 * 已导入
    	 */
       	public static final String GDS_EXCEL_IMP_LOG_STATUS_IMPORTED = "02";
       	
       	/**
       	 * 取消导入
       	 */
       	public static final String GDS_EXCEL_IMP_LOG_STATUS_CANCEL = "03";
       	
       	//导入商品
      	public static final String GDS_EXCEL_IMP_NEW = "0";
      	//导入编辑商品
      	public static final String GDS_EXCEL_IMP_EDIT= "1";

        private GdsExcelImpLog() {
        };
    }

    
    /**
     * 隐藏实现，防止初始化
     */
    private GdsConstants() {
    }

}

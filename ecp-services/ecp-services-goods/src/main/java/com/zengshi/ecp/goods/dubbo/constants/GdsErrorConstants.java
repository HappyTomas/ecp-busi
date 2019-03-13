/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsContants.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.util 
 * Date:2015年8月11日上午10:28:12 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.dubbo.constants;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日上午10:29:24 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public final class GdsErrorConstants {
    
    /**
     * 隐藏实现，防止初始化
     */
    private GdsErrorConstants() {
    }
    /**
     * 
     * Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月13日上午10:29:41 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsErrorContants
     * @since JDK 1.6
     */
    public static class Commons {
        /**
         * 参数为空!
         */
        public static final String ERROR_GOODS_200097 = "error.goods.200097";

        /**
         * {0}参数值无效!
         */
        public static final String ERROR_GOODS_200098 = "error.goods.200098";

        /**
         * {0}方法执行异常!
         */
        public static final String ERROR_GOODS_200099 = "error.goods.200099";

        /**
         * 必传参数{0}缺失!
         */
        public static final String ERROR_GOODS_200100 = "error.goods.200100";
    }

    /**
     * 商品异常常量 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年9月18日下午3:54:03 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsInfo {

        /**
         * 查询产品信息参数不允许为空
         */
        public static final String ERROR_GOODS_GDSINFO_210001 = "error.goods.gdsInfo.210001";

        /**
         * 参数主分类为空
         */
        public static final String ERROR_GOODS_GDSINFO_210002 = "error.goods.gdsInfo.210002";

        /**
         * 参数主分类超过1个，不允许一个主分类
         */
        public static final String ERROR_GOODS_GDSINFO_210003 = "error.goods.gdsInfo.210003";

        /**
         * 商品录入库存异常
         */
        public static final String ERROR_GOODS_GDSINFO_210004 = "error.goods.gdsInfo.210004";

        /**
         * 商品不存在
         */
        public static final String ERROR_GOODS_GDSINFO_210005 = "error.goods.gdsInfo.210005";

        /**
         * 商品不允许上架异常
         */
        public static final String ERROR_GOODS_GDSINFO_210006 = "error.goods.gdsInfo.210006";

        /**
         * 清除库存失败
         */
        public static final String ERROR_GOODS_GDSINFO_210007 = "error.goods.gdsInfo.210007";

        /**
         * 单品不存在
         */
        public static final String ERROR_GOODS_GDSINFO_210008 = "error.goods.gdsInfo.210008";

        /**
         * 商品不允许上架异常 商品已经是上架状态
         */
        public static final String ERROR_GOODS_GDSINFO_210012 = "error.goods.gdsInfo.210012";

    }

    /**
     * 商品价格异常常量 Title: ECP <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年9月18日下午3:54:27 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author linwb3
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsPrice {
        /**
         * 阶梯价录入 价格对象为空异常
         */
        public static final String ERROR_GOODS_PRICE_210704 = "error.goods.price.210704";

        /**
         * 阶梯价录入 起始数量为空异常
         */
        public static final String ERROR_GOODS_PRICE_210705 = "error.goods.price.210705";

        /**
         * 找不到对应的价格类型
         */
        public static final String ERROR_GOODS_PRICE_210706 = "error.goods.price.210706";

        /**
         * 不满足最低购买量
         */
        public static final String ERROR_GOODS_PRICE_210707 = "error.goods.price.210707";

        /**
         * 获取价格策略失败
         */
        public static final String ERROR_GOODS_PRICE_210708 = "error.goods.price.210708";

    }

    /**
     * 
     * 商品类型异常码常量<br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日上午9:56:18 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorContants
     * @since JDK 1.6
     */
    public static class GdsType {
        /**
         * 编辑商品类型失败!
         */
        public static final String ERROR_GOODS_TYPE_200101 = "error.goods.type.200101";

        /**
         * 商品类型名称不允为空!
         */
        public static final String ERROR_GOODS_TYPE_200102 = "error.goods.type.200102";

        /**
         * 商品类型不存在!
         */
        public static final String ERROR_GOODS_TYPE_200103 = "error.goods.type.200103";

        /**
         * 商品类型已失效!
         */
        public static final String ERROR_GOODS_TYPE_200104 = "error.goods.type.200104";

        /**
         * 商品类型禁用失败!
         */
        public static final String ERROR_GOODS_TYPE_200105 = "error.goods.type.200105";

        /**
         * 商品类型已存在!
         */
        public static final String ERROR_GOODS_TYPE_200106 = "error.goods.type.200106";

        /**
         * 商品类型创建失败!
         */
        public static final String ERROR_GOODS_TYPE_200107 = "error.goods.type.200107";

        /**
         * 商品类型启用失败!
         */
        public static final String ERROR_GOODS_TYPE_200108 = "error.goods.type.200108";

        /**
         * 找不到对应的商品类型
         */
        public static final String ERROR_GOODS_TYPE_200109 = "error.goods.type.200109";

        /**
         * 获取类型策略失败
         */
        public static final String ERROR_GOODS_TYPE_200110 = "error.goods.type.200110";

    }

    /**
     * 
     * 标签管理异常常量码.<br/>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月22日上午10:46:06 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsLabel {
        /**
         * 标签信息不存在!
         */
        public static final String ERROR_GOODS_LABEL_200150 = "error_goods_label_200150";

        /**
         * 标签编辑失败!
         */
        public static final String ERROR_GOODS_LABEL_200151 = "error_goods_label_200151";

        /**
         * 标签已被禁用,请勿重复禁用!
         */
        public static final String ERROR_GOODS_LABEL_200152 = "error_goods_label_200152";

        /**
         * 标签禁用失败!
         */
        public static final String ERROR_GOODS_LABEL_200153 = "error_goods_label_200153";
    }

    /**
     * 
     * 商品类别异常码常量。<br/>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日上午9:56:37 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorContants
     * @since JDK 1.6
     */
    public static class GdsCategory {
        // public static final String ERROR_GOODS_CATEGORY_200300 =
        // "error.goods.category.200300";
        /**
         * 编辑商品分类失败!
         */
        public static final String ERROR_GOODS_CATEGORY_200301 = "error.goods.category.200301";

        /**
         * 商品分类名称不允为空!
         */
        public static final String ERROR_GOODS_CATEGORY_200302 = "error.goods.category.200302";

        /**
         * 商品分类不存在!
         */
        public static final String ERROR_GOODS_CATEGORY_200303 = "error.goods.category.200303";

        /**
         * 商品分类已失效!
         */
        public static final String ERROR_GOODS_CATEGORY_200304 = "error.goods.category.200304";

        /**
         * 商品分类禁用失败!
         */
        public static final String ERROR_GOODS_CATEGORY_200305 = "error.goods.category.200305";

        /**
         * 存在子级有效分类,不允许禁用该分类!
         */
        public static final String ERROR_GOODS_CATEGORY_200306 = "error.goods.category.200306";

        /**
         * 商品分类已存在!
         */
        public static final String ERROR_GOODS_CATEGORY_200307 = "error.goods.category.200307";

        /**
         * 重复添加主键值为[{0}]的属性!
         */
        public static final String ERROR_GOODS_CATEGORY_200308 = "error.goods.category.200308";

        /**
         * 重复关联主键为[{0}]的标签!
         */
        public static final String ERROR_GOODS_CATEGORY_200309 = "error.goods.category.200309";

        /**
         * 创建商品分类失败!
         */
        public static final String ERROR_GOODS_CATEGORY_200310 = "error.goods.category.200310";

        /**
         * 上级分类不存在或者已经删除!
         */
        public static final String ERROR_GOODS_CATEGORY_200311 = "error.goods.category.200311";

        /**
         * 重复添加主键为[{0}]的属性组!
         */
        public static final String ERROR_GOODS_CATEGORY_200312 = "error.goods.category.200312";

        /**
         * 商品分类最大层级为[{0}]级,不允许新增子分类!
         */
        public static final String ERROR_GOODS_CATEGORY_200313 = "error.goods.category.200313";

        /**
         * 编码为[{0}]商品分类不存在!
         */
        public static final String ERROR_GOODS_CATEGORY_200314 = "error.goods.category.200314";

        /**
         * 目标分类catgLevel不允许大于当前分类catgLevel!
         */
        public static final String ERROR_GOODS_CATEGORY_200315 = "error.goods.category.200315";

        /**
         * 从缓存中移除分类编码为[{0}]的分类遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200316 = "error.goods.category.200316";

        /**
         * 查询分类编码为[{0}]的分类遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200317 = "error.goods.category.200317";

        /**
         * 新增分类映射关系遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200318 = "error.goods.category.200318";

        /**
         * 编辑分类映射关系遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200319 = "error.goods.category.200319";

        /**
         * 删除分类映射关系遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200320 = "error.goods.category.200320";

        /**
         * 查询分类映射关系遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200321 = "error.goods.category.200321";

        /**
         * 批量删除分类映射关系遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200322 = "error.goods.category.200322";

        /**
         * 根据原始分类查询分类映射关系遇到异常!
         */
        public static final String ERROR_GOODS_CATEGORY_200323 = "error.goods.category.200323";

        /**
         * 根据来源系统查询根分类映射列表
         */
        public static final String ERROR_GOODS_CATEGORY_200324 = "error.goods.category.200324";

        /**
         * 根据父分类查询子原始分类映射列表
         */
        public static final String ERROR_GOODS_CATEGORY_200325 = "error.goods.category.200325";

        
        /**
         * 分类映射不存在
         */
        public static final String ERROR_GOODS_CATEGORY_200326 = "error.goods.category.200326";
        
        /**
         * 查询分类映射的路径列表失败
         */
        public static final String ERROR_GOODS_CATEGORY_200327 = "error.goods.category.200327";
        /**
         * 父分类关系设置异常
         */
        public static final String ERROR_GOODS_CATEGORY_200328 = "error.goods.category.200328";
        
        

    }

    /**
     * 
     * 商品属性值异常码常量。<br/>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日上午9:56:56 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorContants
     * @since JDK 1.6
     */
    public static class GdsPropValue {
        /**
         * 属性值编辑失败!
         */
        public static final String ERROR_GOODS_PROPVALUE_200401 = "error.goods.propvalue.200401";

        /**
         * 属性值不存在!
         */
        public static final String ERROR_GOODS_PROPVALUE_200402 = "error.goods.propvalue.200402";

        /**
         * 属性值已失效!
         */
        public static final String ERROR_GOODS_PROPVALUE_200403 = "error.goods.propvalue.200403";

        /**
         * 属性值禁用失败!
         */
        public static final String ERROR_GOODS_PROPVALUE_200404 = "error.goods.propvalue.200404";

        /**
         * 属性值已存在!
         */
        public static final String ERROR_GOODS_PROPVALUE_200405 = "error.goods.propvalue.200405";
    }

    /**
     * 
     * 商品属性异常码常量。 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月17日上午9:57:45 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorContants
     * @since JDK 1.6
     */
    public static class GdsProp {
        /**
         * 属性编辑失败.
         */
        public static final String ERROR_GOODS_PROP_200450 = "error.goods.prop.200450";

        /**
         * 属性不存在.
         */
        public static final String ERROR_GOODS_PROP_200451 = "error.goods.prop.200451";

        /**
         * 属性已失效.
         */
        public static final String ERROR_GOODS_PROP_200452 = "error.goods.prop.200452";

        /**
         * 属性禁用失败.
         */
        public static final String ERROR_GOODS_PROP_200453 = "error.goods.prop.200453";

        /**
         * 属性已存在!
         */
        public static final String ERROR_GOODS_PROP_200454 = "error.goods.prop.200454";

        /**
         * 属性已经是启用状态，请不要重复启用!
         */
        public static final String ERROR_GOODS_PROP_200455 = "error.goods.prop.200455";

        /**
         * 属性已经是禁用状态，请不要重复禁用!
         */
        public static final String ERROR_GOODS_PROP_200456 = "error.goods.prop.200456";
    }

    /**
     * 
     * 属性组常量类. <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月19日下午2:29:33 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsPropGroup {
        /**
         * 属性组信息不存在!
         */
        public static final String ERROR_GOODS_PROPGROUP_200500 = "error.goods.propgroup.200500";

        /**
         * 属性组信息编辑失败!
         */
        public static final String ERROR_GOODS_PROPGROUP_200501 = "error.goods.propgroup.200501";

        /**
         * 属性组信息已禁用!
         */
        public static final String ERROR_GOODS_PROPGROUP_200502 = "error.goods.propgroup.200502";

        /**
         * 属性组信息禁用失败!
         */
        public static final String ERROR_GOODS_PROPGROUP_200503 = "error.goods.propgroup.200503";

        /**
         * 属性组信息已存在!
         */
        public static final String ERROR_GOODS_PROPGROUP_200504 = "error.goods.propgroup.200504";

        /**
         * 属性组已是启用状态!
         */
        public static final String ERROR_GOODS_PROPGROUP_200505 = "error.goods.propgroup.200505";

        /**
         * 重复添加主键值为[{0}]的属性!
         */
        public static final String ERROR_GOODS_PROPGROUP_200506 = "error.goods.propgroup.200506";

    }

    /**
     * 
     * 评价异常常量。 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月27日下午2:51:56 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsEval {
        /**
         * 评价信息编辑失败!
         */
        public static final String ERROR_GOODS_EVAL_200550 = "error.goods.eval.200550";

        /**
         * 评价信息不存在!
         */
        public static final String ERROR_GOODS_EVAL_200551 = "error.goods.eval.200551";

        /**
         * 评价回复信息不存在!
         */
        public static final String ERROR_GOODS_EVAL_200552 = "error.goods.eval.200552";

        /**
         * 评价信息删除失败!
         */
        public static final String ERROR_GOODS_EVAL_200553 = "error.goods.eval.200553";

        /**
         * 评价回复信息删除失败!
         */
        public static final String ERROR_GOODS_EVAL_200554 = "error.goods.eval.200554";

        /**
         * 评价信息保存失败!
         */
        public static final String ERROR_GOODS_EVAL_200555 = "error.goods.eval.200555";

        /**
         * 获取评价内容失败!
         */
        public static final String ERROR_GOODS_EVAL_200556 = "error.goods.eval.200556";

        /**
         * 获取评价回复内容失败!
         */
        public static final String ERROR_GOODS_EVAL_200557 = "error.goods.eval.200557";
        /**
         * 评价批量审核失败!
         */
        public static final String ERROR_GOODS_EVAL_200558 = "error.goods.eval.200558";

        
		/**
		 * 获取好评率失败
		 */
		public static final String ERROR_GOODS_EVAL_200559 = "error.goods.eval.200559";

		/**
		 * 获取待审核总数失败
		 */
		public static final String ERROR_GOODS_EVAL_200560 = "error.goods.eval.200560";
    }

    /**
     * 
     * Title: 商品收藏异常码. <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2015-9-19上午9:56:29 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsCollect {
        /**
         * 重复收藏
         */
        public static final String ERROR_GOODS_COLLECT_251000 = "error.goods.collect.251000";

        /**
         * 收藏的商品不存在
         */
        public static final String ERROR_GOODS_COLLECT_251001 = "error.goods.collect.251001";

    }

    /**
     * 
     * Title: 商品目录异常码. <br>
     * Project Name:ecp-services-goods-server <br>
     * Description: <br>
     * Date:2015-9-19上午9:56:29 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsCatlog {
        /**
         * 当前目录不能设为默认,有效状态目录才允许设置为默认目录!
         */
        public static final String ERROR_GOODS_CATLOG_200600 = "error.goods.catlog.200600";
        /**
         * 数据异常,存在多个默认目录!
         */
        public static final String ERROR_GOODS_CATLOG_200601 = "error.goods.catlog.200601";
        /**
         * 从缓存中移除ID={}的目录信息遇到异常!
         */
        public static final String ERROR_GOODS_CATLOG_200602 = "error.goods.catlog.200602";
  
		public static final String ERROR_GOODS_CATLOG_200603 = "error.goods.catlog.200603";

    }

    /**
     * 
     * 媒体异常常量类. <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月21日下午7:59:38 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsMedia {

        /**
         * 找不到媒体所属的媒体库！
         */
        public static final String ERROR_GOODS_MEDIA_200250 = "error.goods.media.200250";

        /**
         * 被编辑的媒体信息在数据库中不存在！
         */
        public static final String ERROR_GOODS_MEDIA_200251 = "error.goods.media.200251";

        /**
         * 未指定媒体的媒体类型！
         */
        public static final String ERROR_GOODS_MEDIA_200252 = "error.goods.media.200252";

        /**
         * 媒体库容量不足或超出！
         */
        public static final String ERROR_GOODS_MEDIA_200253 = "error.goods.media.200253";

    }

    /**
     * 
     * 媒体分类异常常量类. <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月21日下午7:59:38 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsErrorConstants
     * @since JDK 1.6
     */
    public static class GdsMediaCategory {
        /**
         * 媒体分类编辑失败!
         */
        public static final String ERROR_GOODS_MEDIACATG_200200 = "error.goods.mediacatg.200200";

        /**
         * 编码为{0}的媒体分类不存在或者已经删除!
         */
        public static final String ERROR_GOODS_MEDIACATG_200201 = "error.goods.mediacatg.200201";

        /**
         * 媒体分类删除失败!
         */
        public static final String ERROR_GOODS_MEDIACATG_200202 = "error.goods.mediacatg.200202";

        /**
         * {0}分类下含用有效子分类，不能执行删除操作!
         */
        public static final String ERROR_GOODS_MEDIACATG_200203 = "error.goods.mediacatg.200203";

        /**
         * 部份媒体分类因含有有效子分类不能删除，不含有效子分类媒体分类删除成功!
         */
        public static final String ERROR_GOODS_MEDIACATG_200204 = "error.goods.mediacatg.200204";

        /**
         * 存在同名媒体分类！
         */
        public static final String ERROR_GOODS_MEDIACATG_200205 = "error.goods.mediacatg.200205";

        /**
         * 媒体分类最大层级为[{0}]级,不允许新增子分类!
         */
        public static final String ERROR_GOODS_MEDIACATG_200206 = "error.goods.mediacatg.200206";
    }

    /**
     * 
     * Title: 商品库存异常编码 <br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015-8-17下午8:05:38 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author zjh
     * @version GdsErrorContants
     * @since JDK 1.6
     */
    public static class GdsStock {
        /**
         * 找不到对应的仓库信息
         */
        public static final String ERROR_GOODS_STOCK_235001 = "error.goods.stock.235001";

        /**
         * 找不到对应的库存信息
         */
        public static final String ERROR_GOODS_STOCK_235002 = "error.goods.stock.235002";

        /**
         * 库存操作类型未传
         */
        public static final String ERROR_GOODS_STOCK_235003 = "error.goods.stock.235003";

        /**
         * 发货串号为空
         */
        public static final String ERROR_GOODS_STOCK_235004 = "error.goods.stock.235004";

        /**
         * 订单预占记录不存在
         */
        public static final String ERROR_GOODS_STOCK_235005 = "error.goods.stock.235005";

        /**
         * 买家企业不存在对应的仓库
         */
        public static final String ERROR_GOODS_STOCK_235006 = "error.goods.stock.235006";

        /**
         * 订单不存在对应的出库明细
         */
        public static final String ERROR_GOODS_STOCK_235007 = "error.goods.stock.235007";

        /**
         * 非法仓库类型
         */
        public static final String ERROR_GOODS_STOCK_235008 = "error.goods.stock.235008";

        /**
         * 新增仓库失败
         */
        public static final String ERROR_GOODS_STOCK_235009 = "error.goods.stock.235009";

        /**
         * 获取店铺仓库列表失败
         */
        public static final String ERROR_GOODS_STOCK_235010 = "error.goods.stock.235010";

        /**
         * 获取店铺仓库覆盖省份列表失败
         */
        public static final String ERROR_GOODS_STOCK_235011 = "error.goods.stock.235011";

        /**
         * 获取店铺在某个省份下覆盖的地市列表失败
         */
        public static final String ERROR_GOODS_STOCK_235012 = "error.goods.stock.235012";

        /**
         * 编辑仓库失败
         */
        public static final String ERROR_GOODS_STOCK_235013 = "error.goods.stock.235013";

        /**
         * 失效仓库失败
         */
        public static final String ERROR_GOODS_STOCK_235014 = "error.goods.stock.235014";

        /**
         * 删除库存信息失败
         */
        public static final String ERROR_GOODS_STOCK_235015 = "error.goods.stock.235015";

        /**
         * 条件获取库存列表失败
         */
        public static final String ERROR_GOODS_STOCK_235016 = "error.goods.stock.235016";

        /**
         * 新增库存失败
         */
        public static final String ERROR_GOODS_STOCK_235017 = "error.goods.stock.235017";

        /**
         * 变更库存失败
         */
        public static final String ERROR_GOODS_STOCK_235018 = "error.goods.stock.235018";

        /**
         * 下单新增库存预占失败
         */
        public static final String ERROR_GOODS_STOCK_235019 = "error.goods.stock.235019";

        /**
         * 取消订单失效库存预占失败
         */
        public static final String ERROR_GOODS_STOCK_235020 = "error.goods.stock.235020";

        /**
         * 发货库存变更失败
         */
        public static final String ERROR_GOODS_STOCK_235021 = "error.goods.stock.235021";

        /**
         * 确认收货库存变更失败
         */
        public static final String ERROR_GOODS_STOCK_235022 = "error.goods.stock.235022";

        /**
         * 商品录入库存新增失败
         */
        public static final String ERROR_GOODS_STOCK_235023 = "error.goods.stock.235023";

        /**
         * 获取单品库存信息失败
         */
        public static final String ERROR_GOODS_STOCK_235024 = "error.goods.stock.235024";

        /**
         * 仓库编码为空
         */
        public static final String ERROR_GOODS_STOCK_235025 = "error.goods.stock.235025";

        /**
         * 标识仓库已选适用范围失败
         */
        public static final String ERROR_GOODS_STOCK_235026 = "error.goods.stock.235026";

        /**
         * 获取仓库信息失败
         */
        public static final String ERROR_GOODS_STOCK_235027 = "error.goods.stock.235027";

        /**
         * 库存调整失败
         */
        public static final String ERROR_GOODS_STOCK_235028 = "error.goods.stock.235028";

        /**
         * 下订单失败，库存预占回退失败
         */

        public static final String ERROR_GOODS_STOCK_235029 = "error.goods.stock.235029";

        /**
         * 下订单失败，库存不足
         */

        public static final String ERROR_GOODS_STOCK_235030 = "error.goods.stock.235030";

        /**
         * 导入库存失败
         */
        public static final String ERROR_GOODS_STOCK_235031 = "error.goods.stock.235031";

        /**
         * 退货失败
         * 
         */
        public static final String ERROR_GOODS_STOCK_235032 = "error.goods.stock.235032";
        
    	/**
		 * 获取缺货商品总数失败
		 * 
		 */
		public static final String ERROR_GOODS_STOCK_235033 = "error.goods.stock.235033";

    }

    public static class GdsGift {
        /**
         * 新增商品赠品失败
         */
        public static final String ERROR_GOODS_GIFT_200001 = "error.goods.gift.200001";

        /**
         * 删除商品赠品失败
         */
        public static final String ERROR_GOODS_GIFT_200002 = "error.goods.gift.200002";

        /**
         * 编辑商品赠品失败
         */
        public static final String ERROR_GOODS_GIFT_200003 = "error.goods.gift.200003";

        /**
         * 查询商品赠品失败
         */
        public static final String ERROR_GOODS_GIFT_200004 = "error.goods.gift.200004";

        /**
         * 根据赠品id获取不到赠品信息
         */
        public static final String ERROR_GOODS_GIFT_200005 = "error.goods.gift.200005";

        /**
         * 该赠品没有满足可扣减的赠品数量
         */
        public static final String ERROR_GOODS_GIFT_200006 = "error.goods.gift.200006";

        /**
         * 赠品变更数量异常
         */
        public static final String ERROR_GOODS_GIFT_200007 = "error.goods.gift.200007";

    }

    public static class GdsShipMent {

        public static final String ERROR_GOODS_SHIP_MENT_240000 = "error.goods.ship.240000";

        public static final String ERROR_GOODS_SHIP_MENT_240001 = "error.goods.ship.240001";

        public static final String ERROR_GOODS_SHIP_MENT_240002 = "error.goods.ship.240002";

        public static final String ERROR_GOODS_SHIP_MENT_240003 = "error.goods.ship.240003";

        public static final String ERROR_GOODS_SHIP_MENT_240004 = "error.goods.ship.240004";

        /**
         * 查询店铺默认模板失败
         */
        public static final String ERROR_GOODS_SHIP_MENT_240005 = "error.goods.ship.240005";

        /**
         * 编辑店铺默认模板
         */
        public static final String ERROR_GOODS_SHIP_MENT_240006 = "error.goods.ship.240006";

        /**
         * 新增店铺模板
         */
        public static final String ERROR_GOODS_SHIP_MENT_240007 = "error.goods.ship.240007";

        /**
         * 店铺已经存在该分类下的运费模板
         */
        public static final String ERROR_GOODS_SHIP_MENT_240008 = "error.goods.ship.240008";

    }

    // 会员分类折扣
    public static class GdsCatgCustDisc {
        /**
         * 已存在相同会员和分类的折扣记录
         */
        public static final String ERROR_GOODS_CATG_CUST_DISC_240301 = "error.goods.catgcustdisc.240301";

        /**
         * 删除会员分类折扣记录失败
         */
        public static final String ERROR_GOODS_CATG_CUST_DISC_240302 = "error.goods.catgcustdisc.240302";

        /**
         * 编辑会员分类折扣记录失败
         */
        public static final String ERROR_GOODS_CATG_CUST_DISC_240303 = "error.goods.catgcustdisc.240303";

        /**
         * 查询会员分类折扣记录失败
         */
        public static final String ERROR_GOODS_CATG_CUST_DISC_240304 = "error.goods.catgcustdisc.240304";

        /**
         * 新增会员分类折扣记录失败
         */
        public static final String ERROR_GOODS_CATG_CUST_DISC_240305 = "error.goods.catgcustdisc.240304";
    }

    // 用户浏览商品记录
    public static class GdsBrowseHis {
        // 失效用户浏览记录失败
        public static final String ERROR_GOODS_BROWSE_HIS_240401 = "error.goods.gdsbrowsehis.240401";

        // 查询用户浏览记录失败
        public static final String ERROR_GOODS_BROWSE_HIS_240402 = "error.goods.gdsbrowsehis.240402";

        public static final String ERROR_GOODS_BROWSE_HIS_240403 = "error.goods.gdsbrowsehis.240403";

    }

    // 积分商品拓展记录
    public static class GdsScoreExt {

        // 编辑积分商品价格失败
        public static final String ERROR_GOODS_SCORE_EXT_240501 = "error.goods.gdsscoreext.240501";

        // 失效积分商品价格失败
        public static final String ERROR_GOODS_SCORE_EXT_240502 = "error.goods.gdsscoreext.240502";

        // 查询积分商品价格记录失败
        public static final String ERROR_GOODS_SCORE_EXT_240503 = "error.goods.gdsscoreext.240503";

        // 保存积分商品价格记录失败
        public static final String ERROR_GOODS_SCORE_EXT_240504 = "error.goods.gdsscoreext.240504";

        // 积分商品价格类型不存在
        public static final String ERROR_GOODS_SCORE_EXT_240505 = "error.goods.gdsscoreext.240505";

        // 计算商品积分价格失败
        public static final String ERROR_GOODS_SCORE_EXT_240506 = "error.goods.gdsscoreext.240506";

    }
}

package com.zengshi.ecp.cms.dubbo.util;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-cms <br>
 * Description: <br>
 * Date:2015年8月10日下午3:01:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */  
public class CmsConstants {
    
    /*常量类定义的异常编码必须与资源文件中的定义名称一致*/
    public static class MsgCode {
        /*---------公共  开始-----------*/
        // 拷贝对象发生异常！
        public static final String CMS_COMMON_500101 = "CMS.COMMON.500101";
        // 入参{0}为空！
        public static final String CMS_COMMON_500102 = "CMS.COMMON.500102";
        /*---------公共  结束-----------*/
        
        /*---------广告  开始-----------*/
        // 新增广告出现异常
        public static final String CMS_ADVERTISE_500101 = "CMS.ADVERTISE.500101";
        // 修改广告出现异常
        public static final String CMS_ADVERTISE_500102 = "CMS.ADVERTISE.500102";
        // 查询广告出现异常
        public static final String CMS_ADVERTISE_500103 = "CMS.ADVERTISE.500103";
        // 删除广告出现异常
        public static final String CMS_ADVERTISE_500104 = "CMS.ADVERTISE.500104";
        // 批量删除广告出现异常
        public static final String CMS_ADVERTISE_500105 = "CMS.ADVERTISE.500105";
        // 更新广告状态出现异常
        public static final String CMS_ADVERTISE_500106 = "CMS.ADVERTISE.500106";
        // 批量更新广告状态出现异常！
        public static final String CMS_ADVERTISE_500107 = "CMS.ADVERTISE.500107";
        /*---------广告  结束-----------*/
        
        
        /*---------内容位置  开始-----------*/
        
        // 新增内容位置出现异常
        public static final String CMS_PLACE_500101 = "CMS.PLACE.500101";
        // 修改内容位置出现异常
        public static final String CMS_PLACE_500102 = "CMS.PLACE.500102";
        // 查询内容位置出现异常
        public static final String CMS_PLACE_500103 = "CMS.PLACE.500103";
        // 删除内容位置出现异常
        public static final String CMS_PLACE_500104 = "CMS.PLACE.500104";
        // 批量删除内容位置出现异常
        public static final String CMS_PLACE_500105 = "CMS.PLACE.500105";
        // 更新内容位置状态出现异常
        public static final String CMS_PLACE_500106 = "CMS.PLACE.500106";
        // 批量更新内容位置状态出现异常！
        public static final String CMS_PLACE_500107 = "CMS.PLACE.500107";
        
        /*---------内容位置  结束-----------*/
        
        /*---------页面信息  开始-----------*/
        //现在页面信息出现异常
        public static final String CMS_INFO_500101 = "CMS.INFO.500101";
        //修改页面信息出现异常
        public static final String CMS_INFO_500102 = "CMS.INFO.500102";
        //查询页面信息出现异常
        public static final String CMS_INFO_500103 = "CMS.INFO.500103";
        //删除页面信息出现异常
        public static final String CMS_INFO_500104 = "CMS.INFO.500104";
        //分页查询页面信息出现异常
        public static final String CMS_INFO_500105 = "CMS.INFO.500105";
        //使页面信息生效出现异常
        public static final String CMS_INFO_500106 = "CMS.INFO.500106";
        //使页面信息失效出现异常
        public static final String CMS_INFO_500107 = "CMS.INFO.500107";
        /*---------页面信息 结束-----------*/
        
        /*---------楼层  开始-----------*/
        // 新增楼层出现异常
        public static final String CMS_FLOOR_500101 = "CMS.FLOOR.500101";
        // 修改楼层出现异常
        public static final String CMS_FLOOR_500102 = "CMS.FLOOR.500102";
        // 查询楼层出现异常
        public static final String CMS_FLOOR_500103 = "CMS.FLOOR.500103";
        // 删除楼层出现异常
        public static final String CMS_FLOOR_500104 = "CMS.FLOOR.500104";
        // 批量删除楼层出现异常
        public static final String CMS_FLOOR_500105 = "CMS.FLOOR.500105";
        // 更新楼层状态出现异常
        public static final String CMS_FLOOR_500106 = "CMS.FLOOR.500106";
        // 批量更新楼层状态出现异常！
        public static final String CMS_FLOOR_500107 = "CMS.FLOOR.500107";
        /*---------楼层  结束-----------*/
        
        /*---------楼层标签  开始-----------*/
        // 新增楼层标签出现异常
        public static final String CMS_FLOORLABEL_500101 = "CMS.FLOORLABEL.500101";
        // 修改楼层标签出现异常
        public static final String CMS_FLOORLABEL_500102 = "CMS.FLOORLABEL.500102";
        // 查询楼层标签出现异常
        public static final String CMS_FLOORLABEL_500103 = "CMS.FLOORLABEL.500103";
        // 删除楼层标签出现异常
        public static final String CMS_FLOORLABEL_500104 = "CMS.FLOORLABEL.500104";
        // 批量删除楼层标签出现异常
        public static final String CMS_FLOORLABEL_500105 = "CMS.FLOORLABEL.500105";
        // 更新楼层标签状态出现异常
        public static final String CMS_FLOORLABEL_500106 = "CMS.FLOORLABEL.500106";
        // 批量更新楼层标签状态出现异常！
        public static final String CMS_FLOORLABEL_500107 = "CMS.FLOORLABEL.500107";
        /*---------楼层标签  结束-----------*/
        
        /*---------楼层页签  开始-----------*/
        // 新增楼层页签出现异常
        public static final String CMS_FLOORTAB_500101 = "CMS.FLOORTAB.500101";
        // 修改楼层页签出现异常
        public static final String CMS_FLOORTAB_500102 = "CMS.FLOORTAB.500102";
        // 查询楼层页签出现异常
        public static final String CMS_FLOORTAB_500103 = "CMS.FLOORTAB.500103";
        // 删除楼层页签出现异常
        public static final String CMS_FLOORTAB_500104 = "CMS.FLOORTAB.500104";
        // 批量删除楼层页签出现异常
        public static final String CMS_FLOORTAB_500105 = "CMS.FLOORTAB.500105";
        // 更新楼层页签状态出现异常
        public static final String CMS_FLOORTAB_500106 = "CMS.FLOORTAB.500106";
        // 批量更新楼层页签状态出现异常！
        public static final String CMS_FLOORTAB_500107 = "CMS.FLOORTAB.500107";
        /*---------楼层页签  结束-----------*/
        
        /*---------楼层商品  开始-----------*/
        // 新增楼层商品出现异常
        public static final String CMS_FLOORGDS_500101 = "CMS.FLOORGDS.500101";
        // 修改楼层商品出现异常
        public static final String CMS_FLOORGDS_500102 = "CMS.FLOORGDS.500102";
        // 查询楼层商品出现异常
        public static final String CMS_FLOORGDS_500103 = "CMS.FLOORGDS.500103";
        // 删除楼层商品出现异常
        public static final String CMS_FLOORGDS_500104 = "CMS.FLOORGDS.500104";
        // 批量删除楼层商品出现异常
        public static final String CMS_FLOORGDS_500105 = "CMS.FLOORGDS.500105";
        // 更新楼层商品状态出现异常
        public static final String CMS_FLOORGDS_500106 = "CMS.FLOORGDS.500106";
        // 批量更新楼层商品状态出现异常！
        public static final String CMS_FLOORGDS_500107 = "CMS.FLOORGDS.500107";
        /*---------楼层商品  结束-----------*/
        
        /*---------楼层促销  开始-----------*/
        // 新增楼层促销出现异常
        public static final String CMS_FLOORCOUPON_500101 = "CMS.FLOORCOUPON.500101";
        // 修改楼层促销出现异常
        public static final String CMS_FLOORCOUPON_500102 = "CMS.FLOORCOUPON.500102";
        // 查询楼层促销出现异常
        public static final String CMS_FLOORCOUPON_500103 = "CMS.FLOORCOUPON.500103";
        // 删除楼层促销出现异常
        public static final String CMS_FLOORCOUPON_500104 = "CMS.FLOORCOUPON.500104";
        // 批量删除楼层促销出现异常
        public static final String CMS_FLOORCOUPON_500105 = "CMS.FLOORCOUPON.500105";
        // 更新楼层促销状态出现异常
        public static final String CMS_FLOORCOUPON_500106 = "CMS.FLOORCOUPON.500106";
        // 批量更新楼层促销状态出现异常！
        public static final String CMS_FLOORCOUPON_500107 = "CMS.FLOORCOUPON.500107";
        /*---------楼层促销  结束-----------*/
        
        /*---------楼层广告  开始-----------*/
        // 新增楼层广告出现异常
        public static final String CMS_FLOORADVERTISE_500101 = "CMS.FLOORADVERTISE.500101";
        // 修改楼层广告出现异常
        public static final String CMS_FLOORADVERTISE_500102 = "CMS.FLOORADVERTISE.500102";
        // 查询楼层广告出现异常
        public static final String CMS_FLOORADVERTISE_500103 = "CMS.FLOORADVERTISE.500103";
        // 删除楼层广告出现异常
        public static final String CMS_FLOORADVERTISE_500104 = "CMS.FLOORADVERTISE.500104";
        // 批量删除楼层广告出现异常
        public static final String CMS_FLOORADVERTISE_500105 = "CMS.FLOORADVERTISE.500105";
        // 更新楼层广告状态出现异常
        public static final String CMS_FLOORADVERTISE_500106 = "CMS.FLOORADVERTISE.500106";
        // 批量更新楼层广告状态出现异常！
        public static final String CMS_FLOORADVERTISE_500107 = "CMS.FLOORADVERTISE.500107";
        /*---------楼层广告  结束-----------*/
        
        /*---------楼层属性  开始-----------*/
        // 新增楼层属性出现异常
        public static final String CMS_FLOORATTRCOUNT_500101 = "CMS.FLOORATTRCOUNT.500101";
        // 修改楼层属性出现异常
        public static final String CMS_FLOORATTRCOUNT_500102 = "CMS.FLOORATTRCOUNT.500102";
        // 查询楼层属性出现异常
        public static final String CMS_FLOORATTRCOUNT_500103 = "CMS.FLOORATTRCOUNT.500103";
        // 删除楼层属性出现异常
        public static final String CMS_FLOORATTRCOUNT_500104 = "CMS.FLOORATTRCOUNT.500104";
        // 批量删除楼层属性出现异常
        public static final String CMS_FLOORATTRCOUNT_500105 = "CMS.FLOORATTRCOUNT.500105";
        // 更新楼层属性状态出现异常
        public static final String CMS_FLOORATTRCOUNT_500106 = "CMS.FLOORATTRCOUNT.500106";
        // 批量更新楼层属性状态出现异常！
        public static final String CMS_FLOORATTRCOUNT_500107 = "CMS.FLOORATTRCOUNT.500107";
        /*---------楼层属性  结束-----------*/
        
        
        /*---------站点  开始-----------*/
        // 新增站点出现异常
        public static final String CMS_SITE_500101 = "CMS.SITE.500101";
        // 修改站点出现异常
        public static final String CMS_SITE_500102 = "CMS.SITE.500102";
        // 查询站点出现异常
        public static final String CMS_SITE_500103 = "CMS.SITE.500103";
        // 删除站点出现异常
        public static final String CMS_SITE_500104 = "CMS.SITE.500104";
        // 批量删除站点出现异常
        public static final String CMS_SITE_500105 = "CMS.SITE.500105";
        // 更新站点状态出现异常
        public static final String CMS_SITE_500106 = "CMS.SITE.500106";
        // 批量更新站点状态出现异常！
        public static final String CMS_SITE_500107 = "CMS.SITE.500107";
        // 设置默认站点出现异常
        public static final String CMS_SITE_500108 = "CMS.SITE.500108";
        // 批量设置默认站点出现异常！
        public static final String CMS_SITE_500109 = "CMS.SITE.500109";
        /*---------站点  结束-----------*/
        
        /*---------组件  开始-----------*/
        // 新增模板出现异常
        public static final String CMS_COMPONENT_500101 = "CMS.COMPONENT.500101";
        // 修改模板出现异常
        public static final String CMS_COMPONENT_500102 = "CMS.COMPONENT.500102";
        // 查询模板出现异常
        public static final String CMS_COMPONENT_500103 = "CMS.COMPONENT.500103";
        // 删除模板出现异常
        public static final String CMS_COMPONENT_500104 = "CMS.COMPONENT.500104";
        // 批量删除模板出现异常
        public static final String CMS_COMPONENT_500105 = "CMS.COMPONENT.500105";
        // 更新模板状态出现异常
        public static final String CMS_COMPONENT_500106 = "CMS.COMPONENT.500106";
        // 批量更新模板状态出现异常！
        public static final String CMS_COMPONENT_500107 = "CMS.COMPONENT.500107";
        /*---------模板  结束-----------*/
        
        /*---------排行榜  开始-----------*/
        // 新增排行榜出现异常
        public static final String CMS_LIST_500101 = "CMS.LIST.500101";
        // 修改排行榜出现异常
        public static final String CMS_LIST_500102 = "CMS.LIST.500102";
        // 查询排行榜出现异常
        public static final String CMS_LIST_500103 = "CMS.LIST.500103";
        // 删除排行榜出现异常
        public static final String CMS_LIST_500104 = "CMS.LIST.500104";
        // 批量删排行榜出现异常
        public static final String CMS_LIST_500105 = "CMS.LIST.500105";
        // 更新排行榜状态出现异常
        public static final String CMS_LIST_500106 = "CMS.LIST.500106";
        // 批量更新排行榜状态出现异常！
        public static final String CMS_LIST_500107 = "CMS.LIST.500107";
        /*---------排行榜  结束-----------*/
        
        /*---------  开始-----------*/
        // 新增模板出现异常
        public static final String CMS_TEMPLATE_500101 = "CMS.TEMPLATE.500101";
        // 修改模板出现异常
        public static final String CMS_TEMPLATE_500102 = "CMS.TEMPLATE.500102";
        // 查询模板出现异常
        public static final String CMS_TEMPLATE_500103 = "CMS.TEMPLATE.500103";
        // 删除模板出现异常
        public static final String CMS_TEMPLATE_500104 = "CMS.TEMPLATE.500104";
        // 批量删除模板出现异常
        public static final String CMS_TEMPLATE_500105 = "CMS.TEMPLATE.500105";
        // 更新模板状态出现异常
        public static final String CMS_TEMPLATE_500106 = "CMS.TEMPLATE.500106";
        // 批量更新模板状态出现异常！
        public static final String CMS_TEMPLATE_500107 = "CMS.TEMPLATE.500107";
        /*---------模板  结束-----------*/
        
        /*---------推荐  开始-----------*/
        // 新增推荐出现异常
        public static final String CMS_RECOMMEND_500101 = "CMS.RECOMMEND.500101";
        // 修改推荐出现异常
        public static final String CMS_RECOMMEND_500102 = "CMS.RECOMMEND.500102";
        // 查询推荐出现异常
        public static final String CMS_RECOMMEND_500103 = "CMS.RECOMMEND.500103";
        // 删除推荐出现异常
        public static final String CMS_RECOMMEND_500104 = "CMS.RECOMMEND.500104";
        // 批量删除推荐出现异常
        public static final String CMS_RECOMMEND_500105 = "CMS.RECOMMEND.500105";
        // 更新推荐状态出现异常
        public static final String CMS_RECOMMEND_500106 = "CMS.RECOMMEND.500106";
        // 批量更新推荐状态出现异常！
        public static final String CMS_RECOMMEND_500107 = "CMS.RECOMMEND.500107";
        /*---------推荐   结束-----------*/
        
        /*---------热门搜索  开始-----------*/
        // 新增热门搜索出现异常
        public static final String CMS_HOTSEARCH_500101 = "CMS.HOTSEARCH.500101";
        // 修改热门搜索出现异常
        public static final String CMS_HOTSEARCH_500102 = "CMS.HOTSEARCH.500102";
        // 查询热门搜索出现异常
        public static final String CMS_HOTSEARCH_500103 = "CMS.HOTSEARCH.500103";
        // 删除热门搜索出现异常
        public static final String CMS_HOTSEARCH_500104 = "CMS.HOTSEARCH.500104";
        // 批量删除热门搜索出现异常
        public static final String CMS_HOTSEARCH_500105 = "CMS.HOTSEARCH.500105";
        // 更新热门搜索状态出现异常
        public static final String CMS_HOTSEARCH_500106 = "CMS.HOTSEARCH.500106";
        // 批量更新热门搜索状态出现异常！
        public static final String CMS_HOTSEARCH_500107 = "CMS.HOTSEARCH.500107";
        /*---------热门搜索  结束-----------*/
        
        /*-------商品分类              ----------------*/
        //删除商品分类失败
        public static final String CMS_GDSCATEGORY_500101 = "CMS.GDSCATEGORY.500101";
        //更新商品分类失败
        public static final String CMS_GDSCATEGORY_500102 = "CMS.GDSCATEGORY.500102";
        //查询商品分类
        public static final String CMS_GDSCATEGORY_500103 = "CMS.GDSCATEGORY.500103";
        //批量删除商品分类失败
        public static final String CMS_GDSCATEGORY_500104 = "CMS.GDSCATEGORY.500104";
        //删除商品分类失败
        public static final String CMS_GDSCATEGORY_500105 = "CMS.GDSCATEGORY.500105"; 
        //更新商品分类状态失败
        public static final String CMS_GDSCATEGORY_500106 = "CMS.GDSCATEGORY.500106";
        //批量更新商品分类状态失败
        public static final String CMS_GDSCATEGORY_500107 = "CMS.GDSCATEGORY.500107";
        /*------商品分类      结束 ---------------*/
        
        /*-------内容位置与商品分类关系              ----------------*/
        //添加内容位置与商品分类关系 失败
        public static final String CMS_PLACECATEGORY_500101 = "CMS.PLACECATEGORY.500101";
        //更新内容位置与商品分类关系 失败
        public static final String CMS_PLACECATEGORY_500102 = "CMS.PLACECATEGORY.500102";
        //查询内容位置与商品分类关系 分类
        public static final String CMS_PLACECATEGORY_500103 = "CMS.PLACECATEGORY.500103";
      //批量删除内容位置与商品分类关系 失败
        public static final String CMS_PLACECATEGORY_500104 = "CMS.PLACECATEGORY.500104";
      //更新内容位置与商品分类关系 状态失败
        public static final String CMS_PLACECATEGORY_500106 = "CMS.PLACECATEGORY.500106";
      //批量更新内容位置与商品分类关系 状态失败
        public static final String CMS_PLACECATEGORY_500107 = "CMS.PLACECATEGORY.500107";
        /*------商品分类      结束 ---------------*/
        
        /*------------栏目管理  开始 ---------------*/
        /**
         * 添加栏目 失败
         */
        public static final String CMS_CHANNEL_500101 = "CMS.CHANNEL.500101";
        /**
         * 更新栏目 失败
         */
        public static final String CMS_CHANNEL_500102 = "CMS.CHANNEL.500102";
        /**
         * 查询栏目 失败
         */
        public static final String CMS_CHANNEL_500103 = "CMS.CHANNEL.500103";
        /**
         * 删除栏目 失败
         */
        public static final String CMS_CHANNEL_500104 = "CMS.CHANNEL.500104";
        
        public static final String CMS_CHANNEL_500105 = "CMS.CHANNEL.500105";
        /*------------栏目结束  开始 ---------------*/
        
        /*---------文章  开始-----------*/
        // 新增文章出现异常
        public static final String CMS_ARTICLE_500101 = "CMS.ARTICLE.500101";
        // 修改文章出现异常
        public static final String CMS_ARTICLE_500102 = "CMS.ARTICLE.500102";
        // 查询文章出现异常
        public static final String CMS_ARTICLE_500103 = "CMS.ARTICLE.500103";
        // 删除文章出现异常
        public static final String CMS_ARTICLE_500104 = "CMS.ARTICLE.500104";
        // 批量删除文章出现异常
        public static final String CMS_ARTICLE_500105 = "CMS.ARTICLE.500105";
        // 更新文章状态出现异常
        public static final String CMS_ARTICLE_500106 = "CMS.ARTICLE.500106";
        // 批量更新文章状态出现异常！
        public static final String CMS_ARTICLE_500107 = "CMS.ARTICLE.500107";
        // 导入旧官网数据出现异常！
        public static final String CMS_ARTICLE_500108 = "CMS.ARTICLE.500108";
        /*---------文章  结束-----------*/
        
        /*---------网站信息  开始-----------*/
        // 新增网站信息出现异常
        public static final String CMS_SITEINFO_500101 = "CMS.SITEINFO.500101";
        // 修改网站信息出现异常
        public static final String CMS_SITEINFO_500102 = "CMS.SITEINFO.500102";
        // 查询网站信息出现异常
        public static final String CMS_SITEINFO_500103 = "CMS.SITEINFO.500103";
        // 删除网站信息出现异常
        public static final String CMS_SITEINFO_500104 = "CMS.SITEINFO.500104";
        // 批量删除网站信息出现异常
        public static final String CMS_SITEINFO_500105 = "CMS.SITEINFO.500105";
        // 更新网站信息状态出现异常
        public static final String CMS_SITEINFO_500106 = "CMS.SITEINFO.500106";
        // 批量更新网站信息状态出现异常！
        public static final String CMS_SITEINFO_500107 = "CMS.SITEINFO.500107";
        /*---------网站信息  结束-----------*/
        
        /*---------内容位置与栏目关系  开始-----------*/
        // 新增网站信息出现异常
        public static final String CMS_PLACECHANNEL_500101 = "CMS.PLACECHANNEL.500101";
        // 修改网站信息出现异常
        public static final String CMS_PLACECHANNEL_500102 = "CMS.PLACECHANNEL.500102";
        // 查询网站信息出现异常
        public static final String CMS_PLACECHANNEL_500103 = "CMS.PLACECHANNEL.500103";
        // 删除网站信息出现异常
        public static final String CMS_PLACECHANNEL_500104 = "CMS.PLACECHANNEL.500104";
        // 批量删除网站信息出现异常
        public static final String CMS_PLACECHANNEL_500105 = "CMS.PLACECHANNEL.500105";
        // 更新网站信息状态出现异常
        public static final String CMS_PLACECHANNEL_500106 = "CMS.PLACECHANNEL.500106";
        // 批量更新网站信息状态出现异常！
        public static final String CMS_PLACECHANNEL_500107 = "CMS.PLACECHANNEL.500107";
        /*---------内容位置与栏目关系  结束-----------*/

        /*---------楼层模板  开始-----------*/
        // 新增楼层模板出现异常
        public static final String CMS_FLOORTEMPLATE_500101 = "CMS.FLOORTEMPLATE.500101";
        // 修改楼层模板出现异常
        public static final String CMS_FLOORTEMPLATE_500102 = "CMS.FLOORTEMPLATE.500102";
        // 查询楼层模板出现异常
        public static final String CMS_FLOORTEMPLATE_500103 = "CMS.FLOORTEMPLATE.500103";
        // 删除楼层模板出现异常
        public static final String CMS_FLOORTEMPLATE_500104 = "CMS.FLOORTEMPLATE.500104";
        // 批量删除楼层模板出现异常
        public static final String CMS_FLOORTEMPLATE_500105 = "CMS.FLOORTEMPLATE.500105";
        // 更新楼层模板状态出现异常
        public static final String CMS_FLOORTEMPLATE_500106 = "CMS.FLOORTEMPLATE.500106";
        // 批量更新楼层模板状态出现异常！
        public static final String CMS_FLOORTEMPLATE_500107 = "CMS.FLOORTEMPLATE.500107";
        /*---------楼层模板  结束-----------*/
        
        /*---------楼层模板内容位置  开始-----------*/
        // 新增楼层模板内容位置出现异常
        public static final String CMS_FLOORPLACE_500101 = "CMS.FLOORPLACE.500101";
        // 修改楼层模板内容位置出现异常
        public static final String CMS_FLOORPLACE_500102 = "CMS.FLOORPLACE.500102";
        // 查询楼层模板内容位置出现异常
        public static final String CMS_FLOORPLACE_500103 = "CMS.FLOORPLACE.500103";
        // 删除楼层模板内容位置出现异常
        public static final String CMS_FLOORPLACE_500104 = "CMS.FLOORPLACE.500104";
        // 批量删除楼层模板内容位置出现异常
        public static final String CMS_FLOORPLACE_500105 = "CMS.FLOORPLACE.500105";
        // 更新楼层模板内容位置状态出现异常
        public static final String CMS_FLOORPLACE_500106 = "CMS.FLOORPLACE.500106";
        // 批量更新楼层模板内容位置状态出现异常！
        public static final String CMS_FLOORPLACE_500107 = "CMS.FLOORPLACE.500107";
        /*---------楼层模板内容位置  结束-----------*/
        
        /*---------app楼层 开始-----------*/
        // 新增app楼层出现异常
        public static final String CMS_APPFLOOR_500101 = "CMS.APPFLOOR.500101";
        // 修改app楼层出现异常
        public static final String CMS_APPFLOOR_500102 = "CMS.APPFLOOR.500102";
        // 查询app楼层出现异常
        public static final String CMS_APPFLOOR_500103 = "CMS.APPFLOOR.500103";
        // 删除app楼层出现异常
        public static final String CMS_APPFLOOR_500104 = "CMS.APPFLOOR.500104";
        // 批量删除app楼层出现异常
        public static final String CMS_APPFLOOR_500105 = "CMS.APPFLOOR.500105";
        // 更新app楼层状态出现异常
        public static final String CMS_APPFLOOR_500106 = "CMS.APPFLOOR.500106";
        // 批量更新app楼层状态出现异常！
        public static final String CMS_APPFLOOR_500107 = "CMS.APPFLOOR.500107";
        /*---------app楼层  结束-----------*/
        
        /*---------app楼层数据 开始-----------*/
        // 新增app楼层数据出现异常
        public static final String CMS_APPFLOORDATA_500101 = "CMS.APPFLOORDATA.500101";
        // 修改app楼层数据出现异常
        public static final String CMS_APPFLOORDATA_500102 = "CMS.APPFLOORDATA.500102";
        // 查询app楼层数据出现异常
        public static final String CMS_APPFLOORDATA_500103 = "CMS.APPFLOORDATA.500103";
        // 删除app楼层数据出现异常
        public static final String CMS_APPFLOORDATA_500104 = "CMS.APPFLOORDATA.500104";
        // 批量删除app楼层数据出现异常
        public static final String CMS_APPFLOORDATA_500105 = "CMS.APPFLOORDATA.500105";
        // 更新app楼层数据状态出现异常
        public static final String CMS_APPFLOORDATA_500106 = "CMS.APPFLOORDATA.500106";
        // 批量更新app楼层数据状态出现异常！
        public static final String CMS_APPFLOORDATA_500107 = "CMS.APPFLOORDATA.500107";
        /*---------app楼层数据  结束-----------*/
        
        /*---------链接  开始-----------*/
        // 新增链接出现异常
        public static final String CMS_LINK_500101 = "CMS.LINK.500101";
        // 修改链接出现异常
        public static final String CMS_LINK_500102 = "CMS.LINK.500102";
        // 查询链接出现异常
        public static final String CMS_LINK_500103 = "CMS.LINK.500103";
        // 删除链接出现异常
        public static final String CMS_LINK_500104 = "CMS.LINK.500104";
        // 批量删除链接出现异常
        public static final String CMS_LINK_500105 = "CMS.LINK.500105";
        // 更新楼层链接出现异常
        public static final String CMS_LINK_500106 = "CMS.LINK.500106";
        // 批量更新链接状态出现异常！
        public static final String CMS_LINK_500107 = "CMS.LINK.500107";
        // 删除链接前，请清空子节点！
        public static final String CMS_LINK_500108 = "CMS.LINK.500108";
        
        /*---------链接  结束-----------*/
    }
    
    // 信息来源
    public static class InfoSource {
        // WEB
        public static final String CMS_INFOSOURCE_WEB = "01";
        // 手机
        public static final String CMS_INFOSOURCE_MOBILE = "02";
        // IPAD
        public static final String CMS_INFOSOURCE_IPAD = "03";
    }
    
    // 页面类型
    public static class PageType {
        // 店铺首页
        public static final String CMS_PAGETYPE_SHOP_INDEX = "1";
        // 商城活动主页
        public static final String CMS_PAGETYPE_INDEX_PROM = "2";
        // 商城首页
        public static final String CMS_PAGETYPE_INDEX = "3";
        // 店铺活动主页
        public static final String CMS_PAGETYPE_SHOP_PROM = "4";
        // 商城二级页
        public static final String CMS_PAGETYPE_INDEX_TWO = "5";
    }
    
    // 信息类型
    public static class InfoType {
        // 促销
        public static final String CMS_INFOTYPE_PROM = "01";
        // 公告
        public static final String CMS_INFOTYPE_NOTICE = "02";
        // 新闻
        public static final String CMS_INFOTYPE_NEWS = "03";
        // 其它
        public static final String CMS_INFOTYPE_OTHER = "09";
    }
    
    // 信息类型
    public static class LinkType {
        // 商品
        public static final String CMS_LINKTYPE_01 = "01";
        // 公告
        public static final String CMS_LINKTYPE_02 = "02";
        // 促销
        public static final String CMS_LINKTYPE_03 = "03";
        // 其它
        public static final String CMS_LINKTYPE_04 = "09";
    }
    
    // 审核状态
    public static class CheckStatus {
        // 未审核
        public static final String CMS_CHECKSTATUS_0 = "0";
        // 审核通过
        public static final String CMS_CHECKSTATUS_1 = "1";
        // 审核不通过
        public static final String CMS_CHECKSTATUS_2 = "2";
    }
    
    // 平台类型
    public static class PlatType {
        // 平台
        public static final String CMS_PLATTYPE_PLAT = "01";
        // 店铺
        public static final String CMS_PLATTYPE_SHOP = "02";
    }
    
    // 平台类型
    public static class PlatformType {
        // WEB
        public static final String CMS_PLATFORMTYPE_01 = "01";
        // APP
        public static final String CMS_PLATFORMTYPE_02 = "02";
        // WAP
        public static final String CMS_PLATFORMTYPE_03 = "03";
    }

    // 是否
    public static class IsNot {
        // 是
        public static final String CMS_ISNOT_1 = "1";
        // 否
        public static final String CMS_ISNOT_0 = "0";
    }
    
    // 背景展现方式
    public static class BackgroupShowType {
        // 平铺
        public static final String CMS_BACKGROUPSHOWTYPE_01 = "01";
        // 不平铺
        public static final String CMS_BACKGROUPSHOWTYPE_02 = "02";
        // 纵向平铺
        public static final String CMS_BACKGROUPSHOWTYPE_03 = "03";
    }
    
    //布局展示类型
    public static class LayoutShowType {
        /**
         *  左右居中
         */
        public static final String CMS_LAYOUTSHOWTYPE_01 = "01";
        /**
         *  左右全宽
         */
        public static final String CMS_LAYOUTSHOWTYPE_02 = "02";
        /**
         *  左侧悬浮
         */
        public static final String CMS_LAYOUTSHOWTYPE_03 = "03";
        /**
         * 右侧悬浮
         */
        public static final String CMS_LAYOUTSHOWTYPE_04 = "04";
    }
    
    //属性值类型
    public static class PropValueType {
        /**
         *  单选
         */
        public static final String CMS_LAYOUTSHOWTYPE_01 = "01";
        /**
         *  多选 
         */
        public static final String CMS_LAYOUTSHOWTYPE_02 = "02";
        /**
         *  手动输入 
         */
        public static final String CMS_LAYOUTSHOWTYPE_03 = "03";
        /**
         * 下拉框 
         */
        public static final String CMS_LAYOUTSHOWTYPE_04 = "04";
        /**
         * 图片
         */
        public static final String CMS_LAYOUTSHOWTYPE_05 = "05";
        /**
         * 自定义
         */
        public static final String CMS_LAYOUTSHOWTYPE_06 = "06";
    }
    
    // 通用状态
    public static class ParamStatus {
        // 无效
        public static final String CMS_PARAMSTATUS_0 = "0";
        // 有效
        public static final String CMS_PARAMSTATUS_1 = "1";
        // 逻辑删除
        public static final String CMS_PARAMSTATUS_2 = "2";
    }
    
    // 位置类型
    public static class PlaceType {
        // 广告
        public static final String CMS_PlACE_TYPE_01 = "01";
        // 顶部
        public static final String CMS_PlACE_TYPE_02 = "02";
        // 底部
        public static final String CMS_PlACE_TYPE_03 = "03";
        // 楼层
        public static final String CMS_PlACE_TYPE_04 = "04";
    }
    
    // 网站信息类型
    public static class SiteInfoType {
        // 文本
        public static final String CMS_SITE_INFO_TYPE_01 = "01";
        // 文章列表
        public static final String CMS_SITE_INFO_TYPE_02 = "02";
    }
    //网站信息根节点 (即没有父节点时 parent字段指向的虚拟节点)
    public static class SiteInfoRoot{
        //根节点ID
        public static final Long CMS_SITE_INFO_ROOT_ID = 0L;
        //根节点名称
        public static final String CMS_SITE_INFO_ROOT_NAME = "无";
    }
    // 栏目类型
    public static class ChannelType {
        // 导航
        public static final String CMS_CHANNEL_TYPE_01 = "01";
        // 信息栏目
        public static final String CMS_CHANNEL_TYPE_02 = "02";
        // 投票栏目
        public static final String CMS_CHANNEL_TYPE_03 = "03";
        // 帮助中心
        public static final String CMS_CHANNEL_TYPE_04 = "04";
        // 客服中心
        public static final String CMS_CHANNEL_TYPE_05 = "05";
    }
    
    // 楼层数据来源
    public static class DataSource {
        // 手工配置
        public static final String CMS_DATA_SOURCE_01 = "01";
        // 行为分析
        public static final String CMS_DATA_SOURCE_02 = "02";
    }
    
    // 统计类型
    public static class CountType {
        //01：销售量
        public static final String CMS_COUNT_TYPE_01 = "01";
        //02：收藏量
        public static final String CMS_COUNT_TYPE_02 = "02";
        //03：销售额
        public static final String CMS_COUNT_TYPE_03 = "03";
        //04：访客量
        public static final String CMS_COUNT_TYPE_04 = "04";
        //05：新书排行
        public static final String CMS_COUNT_TYPE_05 = "05";
        
    }
    
    //业务参数定义
    public static class ParamConfig {
        /*---------公共  开始-----------*/
        //是否参数
        public static final String PUBLIC_PARAM_ISNOT = "PUBLIC_PARAM_ISNOT";
        
        //楼层属性
        public static final String CMS_FLOOR_ATTR = "CMS_FLOOR_ATTR";
        
        //模板分类
        public static final String CMS_TEMPLATE_CLASS = "CMS_TEMPLATE_CLASS";
        
        //组件分类
        public static final String CMS_COMPONENT_CLASS = "CMS_COMPONENT_CLASS";
        
        //系统状态
        public static final String PUBLIC_PARAM_STATUS = "PUBLIC_PARAM_STATUS";
        
        //内容位置类型
        public static final String CMS_PLACE_TYPE = "CMS_PLACE_TYPE";
        
        //系统编码
        public static final String SYS_SUB_SYSTEM = "SYS_SUB_SYSTEM";
        
        //CMS状态业务参数
        public static final String CMS_STATUS="CMS_STATUS";
        /*---------公共  结束-----------*/
        
        //模板化配色样式
        public static final String CMS_COLOR_STYLE="CMS_COLOR_STYLE";
        //广告链接类型业务参数
        public static final String CMS_ADVERTISE_LINK_TYPE="CMS_ADVERTISE_LINK_TYPE";
        //链接类型
        public static final String CMS_LINK_TYPE="CMS_LINK_TYPE";
        //平台类型
        public static final String CMS_PLATFORM_TYPE="CMS_PLATFORM_TYPE";
        //网站信息类型
        public static final String CMS_SITE_INFO_TYPE="CMS_SITE_INFO_TYPE";
        //页面信息状态业务参数
        public static final String CMS_INFO_TYPE="CMS_INFO_TYPE";
        //排行榜分类
		public static final String CMS_LIST_CLASS = "CMS_LIST_CLASS";
		//专家/编辑推荐
		public static final String CMS_RECOMMEND_TYPE = "CMS_RECOMMEND_TYPE";
		//站点缓存  KEY
        public static final String CMS_SITE_CACHE = "CMS_SITE_CACHE";
        //信息来源
        public static final String CMS_SOURCE="CMS_SOURCE"; 
        //数据来源
        public static final String CMS_DATA_SOURCE="CMS_DATA_SOURCE"; 
        //统计类型
        public static final String CMS_COUNT_TYPE="CMS_COUNT_TYPE"; 
        //页面配置-属性值类型
        public static final String CMS_PROP_VALUE_TYPE="CMS_PROP_VALUE_TYPE";
        //页面配置-模块类型
        public static final String CMS_MODULAR_TYPE="CMS_MODULAR_TYPE";
        //页面配置-布局展示类型
        public static final String CMS_LAYOUT_SHOW_TYPE="CMS_LAYOUT_SHOW_TYPE";
        //页面配置-模板类型
        public static final String CMS_TEMPLATE_TYPE="CMS_TEMPLATE_TYPE";
        //智网
        public static final String TOPIC_GDSDEL = "gdsDelTopic";

        //站点url配置
    	public static final String CMS_SITE_MAPPING = "CMS_SITE_MAPPING";
    	
    	//微信扫码配置
    	public static final String WECHAT_QRCODE_CONFIG = "WECHAT_QRCODE_CONFIG";
    	
    	public static final String WECHAT_QRCODE_CONFIG_APP_ID = "APP_ID";
    	public static final String WECHAT_QRCODE_CONFIG_CONNECT_REDIRECT = "CONNECT_REDIRECT";
    	public static final String WECHAT_QRCODE_CONFIG_RESPONSE_TYPE = "RESPONSE_TYPE";
    	public static final String WECHAT_QRCODE_CONFIG_SCOPE = "SCOPE";
    	public static final String WECHAT_QRCODE_CONFIG_STATE = "STATE";
    }

    
    // 模板类型
    public static class TemplateType {
        // 系统
        public static final String CMS_TEMPLATE_TYPE_SYS = "01";
        // 店铺
        public static final String CMS_TEMPLATE_TYPE_SHOP = "02";
    }
    
}


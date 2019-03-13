/**
 * 页面编辑
 * @author gxq
 * 
 * 模块id
 * 01:专题页头
 * 02:招牌标题
 * 03:自定义区
 * 04:图片轮播
 * 05:添加宝贝
 * 06:楼层导航
 * 07:店铺招牌
 * 08:店铺首页广告
 * 09:客服中心
 * 10:店铺导航
 * 11:商品推荐
 * 12:商品排行
 * 13:商品搜索
 * 14:商品展示
 * 15:页面广告
 * 16:店铺公告
 * 17:店铺基本信息
 */
$(function(){
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
			
	        
        };
        
        return {
            "init" : init
        };
    };
    pageConfig.config({    	
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['ePageTop'],
        //指定页面
        init : function(){
            var p = pageInit();
            p.init();
        }
    });
});
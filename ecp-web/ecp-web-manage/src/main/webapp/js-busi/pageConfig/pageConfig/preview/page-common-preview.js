/**
 * 页面预览。
 * gxq
 */
$(function(){
	//页面业务逻辑处理内容
    var pageInit = function(){
        var init = function(){
        	/**
        	 * 背景图片的渲染
        	 */
        	itemRender.bgImgRender();
        	/**
        	 * 标题颜色的渲染
        	 */
        	itemRender.titleBgCRender();
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
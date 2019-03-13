/**
 * 
 * CMS模板预览
 * 
 * @author Terry
 * 
 */
$(function(){
	 var pageInit = function(){
	        var init = function(){
				var main = $('div.previewMainBox');
				var requestPath = 'page-pre';
				var isPublish = false;
				if($(main).hasClass('publishMainBox')){
					requestPath = 'page-pub';
					isPublish = true;
				}
				
				//处理所有布局项内容
				$('div.tItem',$(main)).each(function(i, ele) {
					var _this = $(this);
					var itemId = $('#itemId',$(this)).val();
					if(!itemId) return;
					$.eAjax({
						url : $webroot + requestPath + '/loadLayoutItemProp',			
						data : {'itemId' : itemId},
						success : function(returnInfo){
							if(returnInfo && $.type(returnInfo.itemProp)!='undefined' && $.isPlainObject(returnInfo.itemProp)){
								itemRender.doRender($(_this),returnInfo,isPublish);
							}
						}
					});
				});
				
				//处理所有侧边导航
				$('div.skipfloor',$(main)).each(function(i, ele) {
					itemRender.floorNav($(ele),isPublish);
				});
				
				/*
				var url = $('#imgUrlPrefix').val();
				if(url){
					var lastIndex = url.lastIndexOf('/');
					var str = url.substr(0,lastIndex);
					itemRender.imgUrl = str;
				}
				*/
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
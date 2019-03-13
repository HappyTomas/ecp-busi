/**
 * common-init.js
 * 业务公共组件
 * 
 * @author Terry
 * created : 2016.08.12
 * 
 */
;(function($){
	"use strict";
	window.commonInit = {
		//初始化页面组件
		//$p页面document
		init : function($p){
			
			//下拉分页组件控件
			if($.fn.bComboSelect){
				var inputs = $("input.cShopNameList", $p);
				if($(inputs).size() > 0){
			        $.eAjax({
			            url : GLOBAL.WEBROOT + '/shop/cache/list',
			            success : function(data) {
			            	if(data && $.isArray(data) && data.length > 0){
								$(inputs).each(function(i,input) {
									$(input).bComboSelect({
									    showField : 'shopFullName',
									    keyField : 'id',
									    data : data,
									    callback : function(key,value){
									        //alert('key:'+key+'  value:'+value);
									    }
									});
								});
			            	}
			            }
			        });
				}
			}
		}
	};
})(window.jQuery);
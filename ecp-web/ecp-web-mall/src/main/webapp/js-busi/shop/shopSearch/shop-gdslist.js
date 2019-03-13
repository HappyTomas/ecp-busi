
// 页面初始化模块
$(function() {
			var pInit = function() {

				var init = function() {

				
				};
				return {
					init : init
				};
			};
			pageConfig.config({
						// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
						plugin : ['bPage'],
						// 指定页面
						init : function() {
							var shopGrid = new pInit();
							shopGrid.init();
						}
					});
		});

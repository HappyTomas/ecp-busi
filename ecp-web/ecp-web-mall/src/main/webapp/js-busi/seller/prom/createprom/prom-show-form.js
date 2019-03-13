$(function() {
	var pageInit = function() {

		var init = function() {

			//发布促销
			$('.publish').click(
					function() {
						$(this).attr('disabled', "true");
						$("#promTypeCode").attr('value',$(this).attr("id") );
						$("#shopId").attr('value',$(this).attr("name") );
						/*var shopId= $(this).attr("id");
						var shopName=$(this).attr("name");*/
						window.status ='createprom';
						var path = GLOBAL.WEBROOT+"/seller/createprom/newprom";
						$("#createProm").attr("action",path);
						$("#createProm").submit();
						//window.location.href = GLOBAL.WEBROOT+'/seller/createprom/'+shopName+'/'+shopId;
					});
			//返回
			$('.btnReturn').click(function() {
				window.location.href = GLOBAL.WEBROOT + '/seller/createprom';
			});
			
			//错误信息
			$('#btnShowError').click(function() {
				$('div.formValidateMessages').slideToggle('fast');
			});

		};

		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});
$(function() {
	var pInit = function() {
		
		var init = function() {
			var shopId = $('#shopId').val();
			//分页
			$('#pageControlbar').bPage({
						url : GLOBAL.WEBROOT+ '/seller/createprom/shoplist',
						asyncLoad : true,
						asyncTarget :'#shopListDiv',
						params:function(){
							return {
								shopId : $('#shopId').val()
								};
							}
					});
			$("tr").click(function() {
				if ($(this).find(":radio").attr("checked")) {
					$(this).find(":radio").attr("checked", false);
				}else{
					$(this).find(":radio").attr("checked", true);
				}
			});
			$("input:radio").click(function() {
				if ($(this).attr("checked")){
					$(this).attr("checked", false);
				}else{
					$(this).attr("checked", true);
				}
			});
		};
		
		return {
			init : init
		};
		
	};
	pageConfig.config({
		plugin : [ 'bPage' ],
		init : function() {
			var groupList = new pInit();
			groupList.init();
		}
	});
});
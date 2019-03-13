//页面初始化模块
$(function(){
	// 页面业务逻辑处理内容
	var pInit = function(){
		var init = function(){
		    $('#saveaddr').click(function(){
				if(!$("#addrdetailfrom").valid()) return false;
				var val = ebcForm.formParams($("#addrdetailfrom"));				
				var dlg = bDialog.getDialog();
				var params = bDialog.getDialogParams(dlg);
				$('#saveaddr').attr("disabled",false);
				$(this).button('提交...');//设置加载
				$.eAjax({
					url : GLOBAL.WEBROOT + "/order/build/saveaddr",
					data : val,
					datatype:'json',
					success : function(result) {
						$(this).button('reset');//按钮还原
						if(result&&result.resultInfo&&result.resultInfo.id){
							result.resultInfo.provinceStr = $('#p-code option:selected').text();
							result.resultInfo.cityCodeStr = $('#city-code option:selected').text();
							result.resultInfo.countyCodeStr = $('#county-code option:selected').text();
							result.resultInfo.chnlAddress = $("input[name='chnlAddress']").val();
							result.resultInfo.contactName = $("input[name='contactName']").val();
							result.resultInfo.contactNumber = $("input[name='contactNumber']").val();
						}
						
						bDialog.closeCurrent(result);
					}
				});
			});
		    
		    /*$('input[name="chnlAddress"]').on('focus',function(){
		    	
		    	var provinceStr = $('#p-code option:selected').text();
				var cityCodeStr = $('#city-code option:selected').text();
				var countyCodeStr = $('#county-code option:selected').text();
				
				$(this).val(provinceStr+cityCodeStr+countyCodeStr+'-');
		    });*/
		    
		    $('#cancle').click(function(){
				bDialog.closeCurrent();
			});
		    
		};
		return {
			init : init
		};
		
	}
	
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [],
		// 指定页面
		init : function(){
			var ppp = new pInit();
			ppp.init();
		}
	});
});
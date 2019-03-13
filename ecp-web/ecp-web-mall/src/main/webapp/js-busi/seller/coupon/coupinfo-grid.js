//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
	
			//查询
			 $('#coupSearchBtn').unbind("click").click(function(){
				 
				 
				 	var siteId = $("#siteId").val();
					var shopId = $("#shopId").val();
					var coupTypeId = $("#coupTypeId").val();
					var coupName = $("#coupName").val();
					var status = $("#status").val();
					var effType = $("#effType").val();
					if(status=="0"||status=="2"||status=="3"){
						$("#btn_code_batch_invalid").addClass("hidden");
					}else{
						$("#btn_code_batch_invalid").removeClass("hidden");
					}
					
					$('#coupListDiv').load(GLOBAL.WEBROOT + '/seller/coupinfo/grid', 
							{"siteId":siteId,
						     "shopId":shopId,
						     "coupTypeId":coupTypeId,
						     "coupName":coupName,
						     "status":status,
						     "effType":effType		
							});
				 
			 });
			//初始化加载
			$('#coupSearchBtn').click();
			// 重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#searchForm');
			});

			$('#btn_code_add').click(function(){
				$(this).attr("disabled","true");
				window.location.href = GLOBAL.WEBROOT+'/seller/coupinfo/type';
			});
			 //固定时间选择
			$('#effType').change(function(){
				var p1=$(this).children('option:selected').val();
				$('.queryDateCls').hide();
				//清空
				$('#activeTime').val('');
				$('#inactiveTime').val('');
				if(p1==1){
					$('.queryDateCls').show();
				}
			});
			//批量删除
			$('#btn_code_batch_invalid').click(function(){
				coupinfoGrid.batchInvalid();
			});
			
			

		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bForm'],
		// 指定页面
		init : function() {
			var coupList = new pInit();
			coupList.init();
		}
	});
});


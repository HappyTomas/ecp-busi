$(function() {
	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {
			
			//选择店铺 并且跳转
			var modifyBiz = function(){
				var _ids = $('input[type=radio]:checked').val();
				if(_ids ){
					window.location.href = GLOBAL.WEBROOT+'/seller/createprom/ct/'+_ids;
				}else{
					 eDialog.alert('请选择至少选择一个店铺进行操作！');
				}
			};
			
			//重置
			$('#btnFormReset').click(function(){
				ebcForm.resetForm('#searchForm');
				$("#status").val('1');//特别处理 置为有效
			});
			//新增促销
			$('#btn_code_add').click(function(){
				modifyBiz();
			});
			
			$('#shopQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#shopQueryBtn').click(
					function() {
						var shopId = $('#shopId').val();
						$('#shopListDiv').load(GLOBAL.WEBROOT + '/seller/createprom/shoplist', 
								{
								"shopId" : shopId
								});
							});
			//初始化调用
			$('#shopQueryBtn').click();
			
		};

		return {
			init : init
		};
		
	};
	pageConfig.config({
		plugin : [ 'bForm' ],
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});
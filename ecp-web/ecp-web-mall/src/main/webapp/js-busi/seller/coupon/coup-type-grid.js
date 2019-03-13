$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			
			var modifyBiz = function(){
				var _ids = $('input[type=radio]:checked').attr('id');
			 if(!_ids || _ids.length==0){
					 eDialog.alert('请选择至少选择一个优惠券类型进行操作！');
					 $('#btn_code_modify').removeAttr("disabled");
				}
			 else{
					window.location.href = GLOBAL.WEBROOT+'/seller/coup/add/'+_ids;
				}
			};
			
			$('#btnFormReset').click(function(){
				ebcForm.resetForm('#searchForm');
			});
			
			$('#btn_code_publish').click(function(){
				  modifyBiz();
			});

			$('#btnFormSearch').unbind("click");
			//绑定提交按钮事件
			$('#btnFormSearch').click(
					function() {
						var coupTypeName = $('#coupTypeName').val();
						var typeLimit = $('#typeLimit').val();
						var status = $('#status').val();
						$('#couponListDiv').load(
								GLOBAL.WEBROOT + '/seller/coupontype/typelist', {"coupTypeName" : coupTypeName,"typeLimit" : typeLimit,"status":status});
					});
			//初始化加载
			$('#btnFormSearch').click();
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
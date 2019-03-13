//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			//是否展示批量失效按钮
			$("tr").click(function() {
				if($(this).find(":checkbox").attr("id")=="dt_row_all_check"){
					if($(this).find(":checkbox").attr("checked")){
						$(this).find(":checkbox").prop("checked", false);
						$(" tbody tr td input[ name='checkboxcoup']").prop(
								'checked', false);
					}else{
						$(this).find(":checkbox").prop("checked", true);
						$(" tbody tr td input[ name='checkboxcoup']").prop(
								'checked', true);
					}
				}else{

					if ($(this).find(":checkbox").attr("checked")) {
						$(this).find(":checkbox").prop("checked", false);
					}else{
						$(this).find(":checkbox").prop("checked", true);
						
					}
				}
				
			});
			$("input:checkbox").click(function() {
				if ($(this).attr("checked")){
					$(this).attr("checked", false);
				}else{
					$(this).attr("checked", true);
				}
			});
			
			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/coupinfo/grid',
				asyncLoad : true,
				asyncTarget : '#coupListDiv',
				params :function(){
					var data = new Object();
					data.siteId = $("#siteId").val();
					data.shopId = $("#shopId").val();
					data.coupTypeId = $("#coupTypeId").val();
					data.coupName = $("#coupName").val();
					data.status = $("#status").val();
					data.effType = $("#effType").val();
					return data;
				}
			});
			
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
			var coupList = new pInit();
			coupList.init();
		}
	});
});

var coupinfoGrid = {
		//复制
		copy:function(id){
				window.location.href = GLOBAL.WEBROOT+'/seller/coup/copy/'+id;
		},
		//失效
		invalid:function(id){
				eDialog.confirm("您确认要失效吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/seller/coupinfo/invalid",
								data : {'id':id},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('已失效！',{
										buttons:[{
											caption:"确定",
											callback:function(){
												
												var siteId = $("#siteId").val();
												var shopId = $("#shopId").val();
												var coupTypeId = $("#coupTypeId").val();
												var coupName = $("#coupName").val();
												var status = $("#status").val();
												var effType = $("#effType").val();
												
												$('#coupListDiv').load(GLOBAL.WEBROOT + '/seller/coupinfo/grid', 
														{"siteId":siteId,
													     "shopId":shopId,
													     "coupTypeId":coupTypeId,
													     "coupName":coupName,
													     "status":status,
													     "effType":effType		
														});
									        }
										}]
									}); 
								}
							});
							
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
		},
		//详情
		detail:function(id){
			window.location.href = GLOBAL.WEBROOT+'/seller/coup/view/'+id;
		},
		//生效
		valid:function(id){
			eDialog.confirm("您确认要生效吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/seller/coupinfo/valid",
							data : {'id':id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('发布成功！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											var siteId = $("#siteId").val();
											var shopId = $("#shopId").val();
											var coupTypeId = $("#coupTypeId").val();
											var coupName = $("#coupName").val();
											var status = $("#status").val();
											var effType = $("#effType").val();
											
											$('#coupListDiv').load(GLOBAL.WEBROOT + '/seller/coupinfo/grid', 
													{"siteId":siteId,
												     "shopId":shopId,
												     "coupTypeId":coupTypeId,
												     "coupName":coupName,
												     "status":status,
												     "effType":effType		
													});
								        }
									}]
								}); 
							}
						});
						
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		//编辑
		edit:function(id){
			window.location.href = GLOBAL.WEBROOT+'/seller/coup/edit/'+id;
		},
		//删除
		del:function(id){
			eDialog.confirm("您确认删除吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/seller/coupinfo/del",
							data : {'id':id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('已删除！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											var siteId = $("#siteId").val();
											var shopId = $("#shopId").val();
											var coupTypeId = $("#coupTypeId").val();
											var coupName = $("#coupName").val();
											var status = $("#status").val();
											var effType = $("#effType").val();
											
											$('#coupListDiv').load(GLOBAL.WEBROOT + '/seller/coupinfo/grid', 
													{"siteId":siteId,
												     "shopId":shopId,
												     "coupTypeId":coupTypeId,
												     "coupName":coupName,
												     "status":status,
												     "effType":effType		
													});
								        }
									}]
								}); 
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		//批量失效
		batchInvalid:function(){
			
			var _idsArr=new Array();
			$("input[name='checkboxcoup']:checkbox").each(function(){ 
    			if($(this).attr("checked")){
    				if($(this).attr("status")=="有效"){
    				_idsArr.push($(this).val());
    				}
    			}
    		});
    		if(_idsArr && _idsArr.length>=1){
    			
    			var _data=_idsArr.join("#");
				eDialog.confirm("您确认要批量失效吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT + "/seller/coupinfo/batchinvalid",
								data : {'ids':_data},
								datatype:'json',
								success : function(returnInfo) {
									eDialog.success('已批量失效！',{
										buttons:[{
											caption:"确定",
											callback:function(){
												var siteId = $("#siteId").val();
												var shopId = $("#shopId").val();
												var coupTypeId = $("#coupTypeId").val();
												var coupName = $("#coupName").val();
												var status = $("#status").val();
												var effType = $("#effType").val();
												
												$('#coupListDiv').load(GLOBAL.WEBROOT + '/seller/coupinfo/grid', 
														{"siteId":siteId,
													     "shopId":shopId,
													     "coupTypeId":coupTypeId,
													     "coupName":coupName,
													     "status":status,
													     "effType":effType		
														});
									        }
										}]
									}); 
								}
							});
							
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
    			
    		}else if(!_idsArr || _idsArr.length==0){
    			eDialog.alert('请至少选择一个有效优惠券进行失效操作！');
    		}
			
		}
	};

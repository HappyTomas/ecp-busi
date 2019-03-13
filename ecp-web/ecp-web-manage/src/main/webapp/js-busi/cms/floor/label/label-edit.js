$(function(){
	label_edit.init();
});

var label_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			label_edit.saveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var floorId = $("#floorId").val();
			var searchParams = $("#searchParams").val();
			var floorSearchParams = $("#floorSearchParams").val();
			var params = {
				"floorId":floorId,
				"searchParams":searchParams,
				"floorSearchParams":floorSearchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floorlabel/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			label_edit.pubSaveFrom();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floorlabel/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层标签列表页签
							var searchParams = $("#searchParams").val();
							var floorSearchParams = $("#floorSearchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams,
								"floorSearchParams":floorSearchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'floorlabel/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			}
		});
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floorlabel/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('楼层标签保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层标签列表页签
							var searchParams = $("#searchParams").val();
							var floorSearchParams = $("#floorSearchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams,
								"floorSearchParams":floorSearchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'floorlabel/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			}
		});
	}
};


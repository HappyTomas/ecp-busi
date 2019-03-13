$(function(){
	attrcount_edit.init();
});

var attrcount_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			attrcount_edit.saveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
//			$.gridLoading({"message":"正在加载中...."});
			var floorId = $("#floorId").val();
			var searchParams = $("#searchParams").val();
			var params = {
				"floorId":floorId,
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floorattrcount/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			attrcount_edit.pubSaveFrom();
		});
		
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "floorattrcount/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层属性列表页签
							var searchParams = $("#searchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'floorattrcount/grid',
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
			url : $webroot + "floorattrcount/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
//				alert(returnInfo.id);
				eDialog.success('楼层属性保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层属性列表页签
							var searchParams = $("#searchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'floorattrcount/grid',
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


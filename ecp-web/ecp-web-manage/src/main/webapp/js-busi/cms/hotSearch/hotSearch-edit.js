$(function(){
	hotSearch_edit.init();
});

var hotSearch_edit = {
	init : function(){//初始化
		//绑定选择内容位置
		$('#placeId').bind('change', function(){
			hotSearch_edit.showUploadWarn();
		});
		
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			hotSearch_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			hotSearch_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'hotSearch/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		
		/*//站点来匹配模板
		$('#siteId').change(function(){
			hotSearch_edit.changeSite();
		});
		//模板来匹配模板
		$('#templateId').change(function(){
			hotSearch_edit.changeTemplate();
		});
		$('#templateId').finish(function(){
			hotSearch_edit.changeTemplate();
		});
		//初始化是否加载联动效果
		var id = $("#id").val();
		if(id == null || id == ""){
			hotSearch_edit.changeSite();
		}*/
	},
	
	/*changeSite : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'hotSearch/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#templateId").html("");
				$("#templateId").append("<option value = ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].templateName+"</option>";
					$("#templateId").append(option);
				}
				hotSearch_edit.changeTemplate();
			}	
		});
	},*/
	/*changeTemplate : function(){//模板来匹配模板
		var templateId = $('#templateId').val();
		$.eAjax({
			url : $webroot + 'hotSearch/changeTemplate',
			data : {
				"templateId":templateId,
				"placeType":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#placeId").html("");
				$("#placeId").append("<option value = ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option  value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].placeName+"</option>";
					$("#placeId").append(option);
				}
			}
		});
	},*/
	
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "hotSearch/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('热门搜索保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'hotSearch/grid',
								"params" :{"searchParams":(searchParams?searchParams:"")},
								"method" :"post"
							});
				        }
					}]
				}); 
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "hotSearch/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('热门搜索发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'hotSearch/grid',
								"params" :{"searchParams":(searchParams?searchParams:"")},
								"method" :"post"
							});
				        }
					}]
				}); 
			},
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	}
	
};


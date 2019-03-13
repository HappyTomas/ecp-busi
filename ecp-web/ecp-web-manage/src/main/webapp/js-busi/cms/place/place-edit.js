$(function(){
	place_edit.init();
});

var place_edit = {
	init : function(){//初始化
		//绑定选择内容位置
		$('#placeId').bind('change', function(){
			place_edit.showUploadWarn();
		});
		//绑定链接类型
		//$('input[type=radio][name=linkType]').bind('click', function(){
		$('#linkType').bind('change', function(){
			place_edit.changLinkName();
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			place_edit.saveFrom();
		});
	
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'place/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});

		//绑定选择内容
		$('#select_link_detail').click(function(){
			place_edit.openLinkDetail();
		});
		//站点来匹配模板
		$('#siteId').change(function(){
			place_edit.changeSite();
		});
		//初始化显示“选择”按钮
			place_edit.initLinkName();
		//根据是新增还是编辑来决定是否初始化
		if(($('#id').val())==""){
			place_edit.changeSite();
		}
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			place_edit.pubSaveFrom();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "place/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'place/grid',
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
	changeSite : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'place/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#templateId").html("");
				$("#templateId").append("<option value= ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].templateName+"</option>";
					$("#templateId").append(option);
				}
			}
		});
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "place/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('内容位置保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'place/grid',
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
	initLinkName : function(){//初始化是否显示“选择”按钮
		var linkType = $("#linkType").val();
		if(linkType == "03"){//促销
			$('#linkName').attr("readonly","readonly");
			$("#select_link_detail").hide();
		}else if(linkType == "09"){//其它
			$('#linkName').removeAttr("readonly");
			$("#select_link_detail").hide();
		}else{//商品 、公告 
			$('#linkName').attr("readonly","readonly");
			$("#select_link_detail").show();
		}
	}

};


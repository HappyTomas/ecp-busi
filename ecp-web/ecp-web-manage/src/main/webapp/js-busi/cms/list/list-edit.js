$(function(){
	list_edit.init();
});

var list_edit = {
	init : function(){//初始化
	
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			list_edit.saveFrom();
		});
	
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'list/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		
		//绑定选择内容
		$('#select_link_detail').click(function(){
			list_edit.openLinkDetail();
		});
		
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			list_edit.pubSaveFrom();
		});
		
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "list/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'list/grid',
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
	
	
	openLinkDetail : function(){
		var title = "选择链接内容";
//		var linkType = $('#linkType').val();		
//		//var linkType = $("input[name=linkType]:checked").val();
//		if(!linkType || linkType.length==0){
//			eDialog.alert('请先选择链接类型！');
//			return ;
//		}
//		var url = "";
//		if(linkType == "01"){//商品
//			url = "list/opengds";
//		}else if(linkType == "02"){//公告
//			url = "list/openinfo";
//		}else{
//			return;
//		}
		url = "list/opengds"
		bDialog.open({
			title : title,
			width : 860,
			height : 500,
			url : $webroot + url,
			params : {
			},
			callback:function(data){
//				alert(data.results[0].gdsId);
				$("#gdsId").val(data.results[0].gdsId);
				$("#gdsName").val(data.results[0].infoTitle);
			}
		});
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "list/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('排行榜保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'list/grid',
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


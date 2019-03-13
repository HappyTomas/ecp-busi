$(function(){
	recommend_edit.init();
});

var recommend_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			recommend_edit.saveFrom();
		});
		
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'recommend/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		//点击图片上传触发事件
		var $uploadObj = $("#uploadFileObj");
		$uploadObj.eUploadBaseInit({
			imageMaxWidth:$uploadObj.data("placeWidth")||0,
			imageMaxHeight:$uploadObj.data("placeHeight")||0,
			fileSizeLimit:$uploadObj.data("placeSize")?$uploadObj.data("placeSize")+'KB':'0KB',
			callback:function(fileInfo){
				if(fileInfo && fileInfo.success){
					$("#authorImage").val(fileInfo.fileId);
					$("#imagePreview").attr("src",fileInfo.url);
				}
			}
		});
		//绑定推荐商品
		$('#select_link_detail').click(function(){
			recommend_edit.openLinkDetail('1');
		});
		
		//绑定该作者其他的作品
		$('#select_link_otherProduction').click(function(){
			recommend_edit.openLinkDetail('2');
		});
		
		//绑定该作者推荐的作品
		$('#select_link_recommendProduction').click(function(){
			recommend_edit.openLinkDetail('3');
		});
		
		//绑定喜欢该作者还喜欢
		$('#select_link_otherLike').click(function(){
			recommend_edit.openLinkDetail('4');
		});
		//绑定推荐商品
		$('#clean_recommendGds').click(function(){
			recommend_edit.cleanData('1');
		});
		
		//绑定该作者其他的作品
		$('#clean_otherProduction').click(function(){
			recommend_edit.cleanData('2');
		});
		
		//绑定该作者推荐的作品
		$('#clean_recommendProduction').click(function(){
			recommend_edit.cleanData('3');
		});
		
		//绑定喜欢该作者还喜欢
		$('#clean_otherLike').click(function(){
			recommend_edit.cleanData('4');
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			recommend_edit.pubSaveFrom();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#authorIntroduction").get(0),'count','250');
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "recommend/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'recommend/grid',
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
	openLinkDetail : function(type){
		var title = "选择链接内容";
		var gdsName ="";
		var gdsId = "";
		switch(type){
		case '2':
			gdsName = $("#otherProductionName").val();
			gdsId = $("#otherProduction").val()+'、';
			break;
		case '3':
			gdsName = $("#recommendProductionName").val();
			gdsId = $("#recommendProduction").val()+'、';
			break;
		case '4':
			gdsName = $("#otherLikeName").val();
			gdsId = $("#otherLike").val()+'、';
			break;
			
		default :
			break;
		}
//		alert(escape(gdsName));
//		gdsName = escape(gdsName);
		url = "recommend/opengds?type="+type+"&gdsName="+gdsName;
		bDialog.open({
//			contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			title : title,
			width : 860,
			height : 500,
			url : $webroot + url,
			params : {
//				"gdsName" : gdsName 
			},
			callback:function(data){
				if(gdsId != null && gdsId != ""){
					gdsId=gdsId.replace(/、$/,'');//替换最后一个‘、’
				}
				if(gdsName != null && gdsName != ""){
					gdsName=gdsName.replace(/、$/,'');
				}
				
				if(gdsName!="" && gdsId!=""){
					gdsName = gdsName +"、"+ data.results[0].gdsNames;
					gdsId = gdsId + "、"+data.results[0].gdsIds;
				}else{
				gdsName = gdsName + data.results[0].gdsNames;
				gdsId = gdsId + data.results[0].gdsIds;
				}
//				alert("name="+gdsName+"   id="+gdsId);
				if(type == '1'){//推荐商品
					$("#recommendGdsId").val(gdsId);
					$("#recommendGdsName").val(gdsName);
				}
				else if(type == '2'){//该作者推荐的作品
					$("#otherProduction").val(gdsId);
					$("#otherProductionName").val(gdsName);
				}
				else if(type == '3'){//该作者推荐的作品
					$("#recommendProduction").val(gdsId);
					$("#recommendProductionName").val(gdsName);
				}
				else if(type == '4'){//喜欢该作者还喜欢
					$("#otherLike").val(gdsId);
					$("#otherLikeName").val(gdsName);
				}
			}
		});
	},
	
	cleanData : function(type){//清楚框中的数据
		if(type == '1'){//推荐商品
			$("#recommendGdsId").val("");
			$("#recommendGdsName").val("");
		}
		else if(type == '2'){//该作者推荐的作品
			$("#otherProduction").val("");
			$("#otherProductionName").val("");
		}
		else if(type == '3'){//该作者推荐的作品
			$("#recommendProduction").val("");
			$("#recommendProductionName").val("");
		}
		else if(type == '4'){//喜欢该作者还喜欢
			$("#otherLike").val("");
			$("#otherLikeName").val("");
		}
	},
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "recommend/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('推荐保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'recommend/grid',
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


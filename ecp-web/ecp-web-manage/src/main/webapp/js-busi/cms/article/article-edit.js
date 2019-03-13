$(function(){
	article_edit.init();
	$(".chooseChannel").live('click',function(){
		var $this = $(this);
		bDialog.open({
			title : '选择栏目',
			width : 340,
			height : 565,
			url : GLOBAL.WEBROOT + '/cms/channel/openchannel?siteId='+$("#siteId").val()+"&isOutLink=0",
			params : {
				'param' : $this.parent().find('input[id="channelId"]').val(),
				'checkType':"radio",
				'siteId' : $("#siteId").val()
			},
			callback:function(data){
				if(data && data.results && data.results.length > 0 ){
					$this.parent().find('input[id="channelZH"]').val(data.results[0].stringShow);
					$this.parent().find('input[id="channelId"]').val(data.results[0].param);
				} 
			}
		});
	});
});

var article_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			article_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			article_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			$.gridLoading({"message":"正在加载中...."});
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'article/grid',
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
					$("#thumbnailName").val(fileInfo.fileName);
					$("#thumbnail").val(fileInfo.fileId);
					$("#imagePreview").attr("src",fileInfo.url);
				}
			}
		});
		//点击二维码上传触发事件
		var $uploadQrCodeObj = $("#uploadQrCodeObj");
		$uploadQrCodeObj.eUploadBaseInit({
			imageMaxWidth:$uploadQrCodeObj.data("placeWidth")||0,
			imageMaxHeight:$uploadQrCodeObj.data("placeHeight")||0,
			fileSizeLimit:$uploadQrCodeObj.data("placeSize")?$uploadQrCodeObj.data("placeSize")+'KB':'0KB',
			callback:function(fileInfo){
				if(fileInfo && fileInfo.success){
					$("#qrCode").val(fileInfo.fileId);
					$("#qrCodePreview").attr("src",fileInfo.url);
				}
			}
		});
		//绑定选择内容
		$('#select_link_detail').click(function(){
			article_edit.openLinkDetail();
		});
		//清除图片
		$('#clean_image').click(function(){
			article_edit.cleanImage();
		});
		//清除图片
		$('#clean_qrCode').click(function(){
			article_edit.cleanQrCode();
		});
		//根据静态文件路径，填充富文本内容。
		var staticUrl = $("#staticUrl").val();
		if(staticUrl !=""){
			var editorText = $("#editorText"); 
			var url = staticUrl;
			$.ajax({
				url : url,
				async : true,
				type : "get",
				dataType : 'jsonp',
				jsonp :'jsonpCallback',//注意此处写死jsonCallback
				success: function (data) {
					editorText.empty();
	            	editorText.html(data.result);
	            	KindEditor.html(editorText,data.result);
					KindEditor.sync(editorText);
			    }
			});
		}
		
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#articleRemark").get(0),'count','250');
	},
	
	cleanImage : function(){//清除图片
		$("#thumbnail").val("");
		$("#thumbnailName").val("");
		$("#imagePreview").attr("src",$("#emptyImage").val());
	},
	cleanQrCode : function(){//清除图片
		$("#qrCode").val("");
		$("#qrCodePreview").attr("src",$("#emptyImage").val());
	},
	
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		var editorText = $("#editorText").val();
		if(editorText == null || editorText == ''){
			eDialog.alert('信息内容不允许为空，请重新填写！'); 
			$.gridUnLoading();
			return false;
		}
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "article/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('文章保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'article/grid',
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
		var editorText = $("#editorText").val();
		if(editorText == null || editorText == ''){
			eDialog.alert('信息内容不允许为空，请重新填写！'); 
			$.gridUnLoading();
			return false;
		}
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "article/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('文章发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'article/grid',
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
};


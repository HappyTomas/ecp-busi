$(function(){
	advertise_edit.init();
});

var advertise_edit = {
	init : function(){//初始化
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			advertise_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			advertise_edit.pubSaveFrom();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
		//新增返回
		$('#btnReturn').click(function(){
			var floorId = $("#floorId").val();
			var searchParams = $("#searchParams").val();
			var params = {
				"floorId":floorId,
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'flooradvertise/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定链接类型
		$('#linkType').bind('change', function(){
			advertise_edit.changLinkName();
		});
		//绑定选择内容
		$('#select_link_detail').click(function(){
			advertise_edit.openLinkDetail();
		});
		//初始化显示“选择”按钮
		advertise_edit.initLinkName();
		//点击图片上传触发事件
		var $uploadObj = $("#uploadFileObj");
		$uploadObj.eUploadBaseInit({
			imageMaxWidth:$uploadObj.data("placeWidth")||0,
			imageMaxHeight:$uploadObj.data("placeHeight")||0,
			fileSizeLimit:$uploadObj.data("placeSize")?$uploadObj.data("placeSize")+'KB':'0KB',
			callback:function(fileInfo){
				if(fileInfo && fileInfo.success){
					$("#vfsId").val(fileInfo.fileId);
					$("#image-preview").attr("src",fileInfo.url);
				}
			}
		});
	},
	
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "flooradvertise/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层广告列表页签
							var searchParams = $("#searchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'flooradvertise/grid',
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
			url : $webroot + "flooradvertise/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('楼层广告保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							//保存完，跳转至楼层广告列表页签
							var searchParams = $("#searchParams").val();
							var params = {
								"floorId":returnInfo.floorId,
								"searchParams":searchParams
							}
							SearchObj.openPage({
								"url" : $webroot+'flooradvertise/grid',
								"params" : params,
								"method" :"post"
							});
				        }
					}]
				}); 
			}
		});
	},
	openLinkDetail : function(){
		var title = "选择链接内容";
		var linkType = $('#linkType').val();		
		//var linkType = $("input[name=linkType]:checked").val();
		if(!linkType || linkType.length==0){
			eDialog.alert('请先选择链接类型！');
			return ;
		}
		var url = "";
		if(linkType == "01"){//商品
			var shopId = $("#shopId").val();
			var siteId = $("#siteId").val();
			if(siteId==null || siteId==""){
				eDialog.alert('请先选择站点！');
				return;
			}
			url = "advertise/opengds?shopId="+(shopId||"")+"&siteId="+siteId;
		}else if(linkType == "02"){//公告
			url = "advertise/openinfo";
		}else if(linkType == "03"){//促销
			var siteId = $("#siteId").val();
			if(siteId==null || siteId==""){
				eDialog.alert('请先选择站点！');
				return;
			}
			url = "pageInfo/openpageinfo?siteId="+siteId;
		}else{
			return;
		}
		bDialog.open({
			title : title,
			width : 860,
			height : 500,
			url : $webroot + url,
			params : {
			},
			callback:function(data){
				$("#linkUrl").val(data.results[0].linkUrl);
				$("#linkName").val(data.results[0].infoTitle);
			}
		});
	},
	changLinkName : function(){//根据链接类型调整链接内容显示
		//连接类型的radio点击以后，清空连接地址
		$('#linkName').attr("readonly","readonly");
		$('#linkName').val('');
		$('#linkUrl').val('');
		var linkType = $("#linkType").val();
		if(linkType == "09"){//其它
			$("#select_link_detail").hide();
			$('#linkName').removeAttr("readonly");
			$('#linkName').attr("minlength","2");
			$('#linkName').attr("maxlength","256");
			$('#linkName').attr("placeholder","请输入链接内容(2~256个字符之间)");
		}else{//商品 、公告 
			$("#select_link_detail").show();
			$('#linkName').attr("placeholder","请选择链接内容");
		}
	},
	initLinkName : function(){//初始化是否显示“选择”按钮
		var linkType = $("#linkType").val();
		if(linkType == "09"){//其它
			$('#linkName').removeAttr("readonly");
			$('#linkName').attr("minlength","2");
			$('#linkName').attr("maxlength","256");
			$('#linkName').attr("placeholder","请输入链接内容(2~256个字符之间)");
			$("#select_link_detail").hide();
		}else{//商品 、公告 
			$('#linkName').attr("readonly","readonly");
			$('#linkName').attr("placeholder","请选择链接内容");
			$("#select_link_detail").show();
		}
	}
};


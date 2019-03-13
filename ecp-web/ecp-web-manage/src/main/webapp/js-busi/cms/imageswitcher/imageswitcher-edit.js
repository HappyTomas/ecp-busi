$(function(){
	advertise_edit.init();
});

var advertise_edit = {
	init : function(){//初始化
		//绑定选择内容位置
		$('#placeId').bind('change', function(){
			advertise_edit.showUploadWarn();
		});
		//绑定链接类型
		$('#linkType').bind('change', function(){
			advertise_edit.changLinkName();
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			advertise_edit.saveFrom();
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			advertise_edit.pubSaveFrom();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'cms/weixh/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		//点击图片上传触发事件
		$("#uploadFileObj").live("change", function(o) {
			var path = $(this).val();
		    if(path==""){
		    	return false;
		    }else{
		    	advertise_edit.uploadImage(this, path);
		    }
		});
		//点击图片上传触发事件
		$("#uploadFileObj2").live("change", function(o) {
			var path = $(this).val();
		    if(path==""){
		    	return false;
		    }else{
		    	advertise_edit.uploadImage2(this, path);
		    }
		});
		//绑定选择内容
		$('#select_link_detail').click(function(){
			advertise_edit.openLinkDetail();
		});
		
		//店铺改变
		$('#shopId').change(function(){
			var linkType = $('#linkType').val();	
			if(linkType == "01")
			advertise_edit.changeShop();
		});
		//初始化显示“选择”按钮
		advertise_edit.initLinkName();
		//初始化根据内容位置的限制控制上传图片提醒
		advertise_edit.showUploadWarn();
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
		//内容位置改变
		/*$('#placeId').change(function(){
			advertise_edit.changePlace();
		});*/
	},
	changeShop : function(){//店铺改变
		$("#linkName").val("");
		$("#linkName").attr("placeholder","请选择链接内容");
		$("#linkUrl").val("");
		return;
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
			url = "advertise/opengds?shopId="+shopId+"&siteId="+siteId;
		}else if(linkType == "02"){//公告
			url = "advertise/openinfo";
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
	saveFrom : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "cms/weixh/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'cms/weixh/grid',
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
			url : $webroot + "cms/weixh/edit",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('配置发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'cms/weixh/grid',
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
	changLinkName : function(){//根据链接类型调整链接内容显示
		//连接类型的radio点击以后，清空连接地址
		$('#linkName').attr("readonly","readonly");
		$('#linkName').val('');
		$('#linkUrl').val('');
		var linkType = $("#linkType").val();
		if(linkType == "03"){//促销
			$("#select_link_detail").hide();
			$("#linkName").val("促销页面");
			$("#linkUrl").val("");//放到controll中去获取数字字典
			return;
		}else if(linkType == "09"){//其它
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
		if(linkType == "03"){//促销
			$('#linkName').attr("readonly","readonly");
			$("#select_link_detail").hide();
		}else if(linkType == "09"){//其它
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
	},
	showUploadWarn : function(){//根据内容位置的限制控制上传图片提醒
		//var _placeIdObj = $("#placeId");
		//$("#uploadFileObj").val("");
		//$("#vfsId").val("");
		//$("#vfsName").val("");
		var _placeIdObj =$("#placeId").find("option:selected");
		var placeValue = _placeIdObj.val();
		if(placeValue == null || placeValue == ''){
			$("#uploadWarn_div").hide();
		}else{
			$("#uploadWarn_div").show();
			var place_count =  _placeIdObj.attr("place_count");
			var place_width =  _placeIdObj.attr("place_width");
			var place_height =  _placeIdObj.attr("place_height");
			var place_size =  _placeIdObj.attr("place_size");
			//alert(place_count+","+place_width+","+place_height);
			//var warnText = "温馨提醒：请上传小于100k的图片（jpg格式），规格为600*300px！";
			if(place_width != '' && place_height != ''){
				var warnText = "温馨提醒：请上传小于"+place_size+"k的图片（jpg格式），规格为"+place_width+"*"+place_height+"px！";
				$("#uploadWarn").text(warnText);
			}
		}
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	uploadImage : function (object, path) {
		var _placeIdObj =$("#placeId").find("option:selected");
		var placeValue = _placeIdObj.val();
		var data = "";
		if(placeValue == null || placeValue == ''){
			eDialog.alert('请先选择内容位置');
			$("#uploadFileObj").val("");
			$("#onePic").val("");
			$("#onePicName").val("");
    		return;
		}else{
			var place_width =  _placeIdObj.attr("place_width");
			var place_height =  _placeIdObj.attr("place_height");
			var place_size =  _placeIdObj.attr("place_size");
			data = {
				'place_width' : place_width,
				'place_height' : place_height,
				'place_size' : place_size
			};
		}
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
    		$("#uploadFileObj").val("");
			$("#onePic").val("");
			$("#onePicName").val("");
    		return;
    	}
    	var url = $webroot + 'common/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview1").attr("src",returnInfo.resultMap.vfsUrl);
				$("#onePic").val(returnInfo.resultMap.vfsId);
				$("#onePicName").val(returnInfo.resultMap.vfsName);
				eDialog.alert(returnInfo.message);
    		} else {
    			/*$("#imagePreview").attr("src","");
				$("#vfsId").val("");
				$("#vfsName").val("");*/
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	advertise_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
    },
    ajaxFileUpload : function (url, secureuri, fileElementId, type, dataType, callback,data) {
		$.ajaxFileUpload({
			url : url, // 用于文件上传的服务器端请求地址
			secureuri : secureuri, // 一般设置为false
			data : data,
			fileElementId : fileElementId, // 文件上传空间的id属性 <input type="file" id="imageFile" name="imageFile" />
			type : type, // get 或 post
			dataType : dataType, // 返回值类型
			success : callback, // 服务器成功响应处理函数
			error : function(data, status, e) // 服务器响应失败处理函数
			{
				$("#uploadFileObj").val("");
				$("#onePic").val("");
				$("#onePicName").val("");
				eDialog.alert(e);
			}
		});
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	uploadImage2 : function (object, path) {
		var _placeIdObj =$("#placeId").find("option:selected");
		var placeValue = _placeIdObj.val();
		var data = "";
		if(placeValue == null || placeValue == ''){
			eDialog.alert('请先选择内容位置');
			$("#uploadFileObj2").val("");
			$("#twicePic").val("");
			$("#twicePicName").val("");
    		return;
		}else{
			var place_width =  _placeIdObj.attr("place_width");
			var place_height =  _placeIdObj.attr("place_height");
			var place_size =  _placeIdObj.attr("place_size");
			data = {
				'place_width' : place_width,
				'place_height' : place_height,
				'place_size' : place_size
			};
		}
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
    		$("#uploadFileObj2").val("");
			$("#twicePic").val("");
			$("#twicePicName").val("");
    		return;
    	}
    	var url = $webroot + 'cms/weixh/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview2").attr("src",returnInfo.resultMap.vfsUrl);
				$("#twicePic").val(returnInfo.resultMap.vfsId);
				$("#twicePicName").val(returnInfo.resultMap.vfsName);
				eDialog.alert(returnInfo.message);
    		} else {
    			/*$("#imagePreview").attr("src","");
				$("#vfsId").val("");
				$("#vfsName").val("");*/
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	advertise_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
    }
};


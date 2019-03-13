$(function(){
	leaflet_edit.init();
	//初始化根据内容位置的限制控制上传图片提醒
	leaflet_edit.showUploadWarn();
});

var leaflet_edit = {
	init : function(){//初始化
		//绑定选择内容位置
		$('#placeId').bind('change', function(){
			leaflet_edit.showUploadWarn();
		});
		//绑定保存按钮
		$('#btn-form-save').click(function(){ 
			leaflet_edit.saveFrom(0);
		});
		//绑定发布按钮
		$('#btn-form-pub').click(function(){ 
			leaflet_edit.saveFrom(1);
		});
		//新增返回
		$('#btn-return').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'/seller/leaflet/grid',
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
		    	$("#vfs-id-selector-error").hide();
		    	leaflet_edit.uploadImage(this, path);
		    }
		});
		//绑定链接类型
		$('#linkType').bind('change', function(){
			leaflet_edit.changLinkName();
		});
		//绑定选择内容
		$('#select_link_detail').click(function(){
			leaflet_edit.openLinkDetail();
		});
		
		//店铺改变
		$('#shopId').change(function(){
			var linkType = $('#linkType').val();	
			if(linkType == "01")
			leaflet_edit.changeShop();
		});
		//初始化显示“选择”按钮
		leaflet_edit.initLinkName();
	},
	changeShop : function(){//店铺改变
		$("#linkName").val("");
		$("#linkName").attr("placeholder","请选择链接内容");
		$("#linkUrl").val("");
		return;
	},
	openLinkDetail : function(){
		var title = "";
		var linkType = $('#linkType').val();		
		//var linkType = $("input[name=linkType]:checked").val();
		if(!linkType || linkType.length==0){
			eDialog.alert('请先选择链接类型！');
			return ;
		}
		var url = "";
		if(linkType == "01"){//商品
			title = "选择商品";
			var shopId = $("#shopId").val();
			var siteId = $("#siteId").val();
			if(!siteId && siteId != 0){
				eDialog.alert('请先选择站点！');
				return;
			}
			if(!shopId && shopId != 0){
				eDialog.alert('请先选择店铺！');
				return;
			}
			 
			url = "seller/leaflet/opengds?shopId="+shopId+"&siteId="+siteId;
		}else if(linkType == "02"){//公告
			title = "选择公告";
			url = "seller/leaflet/openinfo";
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
				if(data&&data.results&&data.results[0]){
					$("#linkUrl").val(data.results[0].linkUrl);
					$("#linkName").val(data.results[0].infoTitle);
				}
			}
		});
	},
	formValid:function(){
		var valid = true;
		if(!$("#detailInfoForm").valid()){
			valid = false;
		}
		
		var vfsId = $("#vfsId").val();
		if(!vfsId){
			$("#vfs-id-selector-error").show();
			valid = false;
		}
		return valid;
	},
	saveFrom : function(status){//保存
		if(!(leaflet_edit.formValid())){return false;}
		var param = ebcForm.formParams($("#detailInfoForm"));
		var pubOrSaveStr = "保存";
		if(status != 1){
			status = 0;
		}
		if(status == 1){
			pubOrSaveStr="发布";
		}
		if(param.length > 0){
			var stautusP = {
					"name":"status",
					"value":status
			};
			param.push(stautusP);
			$.eAjax({
				url : $webroot + "seller/leaflet/save",
				data : param,
				success : function(returnInfo) {
					//window.location.reload();
					eDialog.success('广告'+pubOrSaveStr+'成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'seller/leaflet/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
					        }
						}]
					}); 
				},
				error : function(e,xhr,opt){
					eDialog.error(pubOrSaveStr+"遇到错误!");
				},
				exception : function(msg){
					eDialog.error(pubOrSaveStr+"遇到异常!");
				}
			});
		}
	},
	changLinkName : function(){//根据链接类型调整链接内容显示
		//连接类型的radio点击以后，清空连接地址
		$('#linkName').attr("readonly","readonly");
		$('#linkName').val('');
		$('#linkUrl').val('');
		var linkType = $("#linkType").val();
		if(linkType == "03"){//促销
			$("#select_link_detail").hide();
			$("#linkName").val("/prom/init");
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
			if(place_width != '' && place_height != ''){
				var warnText = "仅支持JPG、PNG、JPEG、GIF、BMP格式，并且小于"+place_size+"k，规格小于"+place_width+"x"+place_height+"px。";
				$("#vfs-id-warn").text(warnText);
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
			$("#vfsId").val("");
			$("#vfsName").val("");
    		return;
		}else{
			var place_width =  _placeIdObj.attr("place_width");
			var place_height =  _placeIdObj.attr("place_height");
			var place_size =  _placeIdObj.attr("place_size");
			data = {
				'place_width' : place_width,
				'place_height' : place_height,
				'place_size' : place_size,
				'standard' : "220x220!"
			};
		}
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
    		$("#uploadFileObj").val("");
    		return;
    	}
    	var url = $webroot + 'seller/common/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview").attr("src",returnInfo.resultMap.vfsUrl);
				$("#vfsId").val(returnInfo.resultMap.vfsId);
				eDialog.alert(returnInfo.message);
    		} else {
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	leaflet_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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
				eDialog.alert(e);
			}
		});
	}
};


$(function(){
	advertise_edit.init();
});

var advertise_edit = {
	"appWapLimit":{//app类型及wap类型的特殊处理
		width:640,
		height:320,
		size:1024
	},
	init : function(){//初始化
		//初始化图片上传限制数据
		advertise_edit.initImgLimit();
		//绑定选择内容位置,平台类型
		$('#placeId,#platformType').unbind('change.ad').bind('change.ad', function(){
			//清空旧数据
			$("#vfsName").val("");
			$("#vfsId").val("");
			$("#imagePreview").attr("src",$("#emptyImageUrl").val());
			//$("#nailVfsId").val("");
			//$("#nailImagePreview").attr("src",$("#emptyImageUrl").val());
			//同步图片上传限制数据
			advertise_edit.setImgLimit();
		});
		//绑定站点及模板改变事件
		$('#siteId,#templateId').unbind('change.ad').bind('change.ad', function(){
			//旧数据清空，防止初始化模板与内容位置时自动定位到旧数据
			$("#linkage_templateId").val("");
			$("#linkage_placeId").val("");
			
			if($("#placeId").val()){
				$("#placeId").val("");
				$("#placeId").trigger("change.ad");
			}
		})
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
				"url": $webroot+'advertise/grid',
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
		//点击缩略图片上传触发事件
		$("#nailUpload").eUploadBaseInit({
			fileSizeLimit:'1MB',
			callback:function(fileInfo){
				if(fileInfo){
					$("#nailVfsId").val(fileInfo.fileId);
					$("#nailImagePreview").attr("src",fileInfo.url);
				}
			}
		});
		//清空缩略图
		$("#nail_clean_image").unbind("click.cn").bind("click.cn",function(){
			$("#nailVfsId").val("");
			$("#nailImagePreview").attr("src",$("#emptyImageUrl").val());
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
		
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
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
			url = "advertise/opengds?shopId="+(shopId||"")+"&siteId="+siteId;
		}else if(linkType == "02"){//公告
			url = "advertise/openinfo";
		}else if(linkType == "03"){//促销
			var siteId = $("#siteId").val();
			var platformType = $("#platformType").val() || '';
			//app 与 wap 都只返回wap类型的促销也  特殊处理  start
			if(platformType && platformType == "02"){
				platformType = "03";
			}
			//app 与 wap 都只返回wap类型的促销也  特殊处理  end	
			if(siteId==null || siteId==""){
				eDialog.alert('请先选择站点！');
				return;
			}
			url = "pageInfo/openpageinfo?siteId="+siteId+"&platformType="+platformType;
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
			url : $webroot + "advertise/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('广告保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'advertise/grid',
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
			url : $webroot + "advertise/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('广告发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'advertise/grid',
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
	//初始化步图片上传限制数据
	"initImgLimit":function(){
		var platformType=$("#platformType").val();
		if(platformType && (platformType == '02' || platformType == '03')){
			$("#place_width").val(advertise_edit.appWapLimit.width);
			$("#place_height").val(advertise_edit.appWapLimit.height);
			$("#place_size").val(advertise_edit.appWapLimit.size);
		}
		advertise_edit.showUploadWarn();
	},
	//同步图片上传限制数据
	"setImgLimit":function(){
		var platformType=$("#platformType").val();
		var placeId = $("#placeId").val();
		
		var place_width =  '';
		var place_height =  '';
		var place_size =  '';
		if(+placeId){
			if(platformType && (platformType == '02' || platformType == '03')){//app及wap图片规格都定死
				 place_width =  advertise_edit.appWapLimit.width;
				 place_height =  advertise_edit.appWapLimit.height;
				 place_size =  advertise_edit.appWapLimit.size;
			}else{
				 var $placeIdEl = $("#placeId").find("option:selected");
				 place_width =  $placeIdEl.attr("place_width")||'';
				 place_height =  $placeIdEl.attr("place_height")||'';
				 place_size =  $placeIdEl.attr("place_size")||'';
			}
		}
		$("#place_width").val(place_width);
		$("#place_height").val(place_height);
		$("#place_size").val(place_size);
		advertise_edit.showUploadWarn();
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
		}else{//商品 、公告 、促销 
			$("#select_link_detail").show();
			$('#linkName').attr("placeholder","请选择链接内容");
		}
	},
	initLinkName : function(){//初始化是否显示“选择”按钮
		var linkType = $("#linkType").val();
		/*if(linkType == "03"){//促销
			$('#linkName').attr("readonly","readonly");
			$("#select_link_detail").hide();
		}else*/ 
		if(linkType == "09"){//其它
			$('#linkName').removeAttr("readonly");
			$('#linkName').attr("minlength","2");
			$('#linkName').attr("maxlength","256");
			$('#linkName').attr("placeholder","请输入链接内容(2~256个字符之间)");
			$("#select_link_detail").hide();
		}else{//商品 、公告  、促销 
			$('#linkName').attr("readonly","readonly");
			$('#linkName').attr("placeholder","请选择链接内容");
			$("#select_link_detail").show();
		}
	},
	showUploadWarn : function(){//根据内容位置的限制控制上传图片提醒
		var place_width =  $("#place_width").val();
		var place_height =  $("#place_height").val();
		var place_size =  $("#place_size").val();
		//var warnText = "温馨提醒：请上传小于100k的图片（jpg格式），规格为600*300px！";
		if(place_width && place_height && place_size){
			$("#uploadWarn").text("温馨提醒：请上传小于"+place_size+"k的图片（jpg,png,jpeg,gif,bmp），规格为"+place_width+"*"+place_height+"px！");
			$("#uploadWarn_div").show();
		}else{
			$("#uploadWarn_div").hide();
			$("#uploadWarn").text("");
		}
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	uploadImage : function (object, path) {
		var placeValue = $("#placeId").val();
		var data = "";
		if(placeValue == null || placeValue == ''){
			$("#uploadFileObj").val("");
			eDialog.alert('请先选择内容位置');
    		return;
		}else{
			data = {
					'place_width' : $("#place_width").val(),
					'place_height' : $("#place_height").val(),
					'place_size' : $("#place_size").val()
				};
		}
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
    		$("#uploadFileObj").val("");
			$("#vfsId").val("");
			$("#vfsName").val("");
    		return;
    	}
    	var url = $webroot + 'common/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview").attr("src",returnInfo.resultMap.vfsUrl);
				$("#vfsId").val(returnInfo.resultMap.vfsId);
				$("#vfsName").val(returnInfo.resultMap.vfsName);
				eDialog.alert(returnInfo.message);
    		} else {
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
				$("#vfsId").val("");
				$("#vfsName").val("");
				eDialog.alert(e);
			}
		});
	}
};


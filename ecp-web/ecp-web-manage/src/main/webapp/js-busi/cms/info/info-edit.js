//新增修改页面信息JS处理
$(function(){
	
	//根据静态文件路径，填充富文本内容。
	var staticUrl = $("#staticUrl").val();
	if(staticUrl !=""){
		var editorText = $("#editorText"); 
		var url = staticUrl;
		//var url = "http://192.168.1.102:8080/imageServer/static/html/55f68185cc964eefd42d1429";
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
	
	//清除图片
	$('#clean').click(function(){
		$('#vfsName').val('');
		$('#vfsId').val('');
		$('#attachmentFileInput').val('');
	});
	//绑定发布按钮
	$('#btnFormPubSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		var editorText = $("#editorText").val();
		if(editorText == null || editorText == ''){
			eDialog.alert('信息内容不允许为空，请重新填写！'); 
			$.gridUnLoading();
			return false;
		}
		$.eAjax({
			url : $webroot + "info/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//跳转到页面信息列表查询页面
				eDialog.success('信息发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'info/infogrid',
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
	});
	
	//保存按钮点击触发事件
	$("#btnFormSave").on('click',function(){
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		var editorText = $("#editorText").val();
		if(editorText == null || editorText == ''){
			eDialog.alert('信息内容不允许为空，请重新填写！'); 
			$.gridUnLoading();
			return false;
		}
		$.eAjax({
			url : $webroot + "info/infosave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//跳转到页面信息列表查询页面
				eDialog.success('信息保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'info/infogrid',
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
	});
	
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到页面信息列表查询页面
		//window.location.href = $webroot+'info/infogrid';
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'info/infogrid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});

	//点击附件上传触发事件
	$("#uploadFileObj").live("change", function(o) {
		var path = $(this).val();
	    if(path==""){
	    	return false;
	    }else{
	    	info_edit.uploadImage(this, path);
	    }
	});
	//初始化字数
	$.isFunction(checkLen) && checkLen($("#remark").get(0),'count','250');
});

var info_edit = {
	uploadImage : function (object, path) {
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(doc)$|\\.(docx)$|\\.(pdf)$|\\.(zip)$|\\.(rar)$');
    	/** 上传附件文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择附件格式为(.doc,.docx,.pdf,.zip,.rar)');
    		uploadfile.value = "";
    		return;
    	}
    	
//    	var img = new Image();
//    	img.src = path;
//    	if(img.fileSize > 5*1024*1024 ){
//    		alert("大小不能超过5M");
//    		return;
//    	}
    	
    	var url = GLOBAL.WEBROOT + '/common/uploadFile';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#vfsId").val(returnInfo.resultMap.vfsId);
				$("#vfsName").val(returnInfo.resultMap.vfsName);
				eDialog.alert(returnInfo.message);
    		} else {
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	info_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback);
    },
    ajaxFileUpload : function (url, secureuri, fileElementId, type, dataType, callback) {
		$.ajaxFileUpload({
			url : url, // 用于文件上传的服务器端请求地址
			secureuri : secureuri, // 一般设置为false
			fileElementId : fileElementId, // 文件上传空间的id属性 <input
			// type="file" id="imageFile"
			// name="imageFile" />
			type : type, // get 或 post
			dataType : dataType, // 返回值类型
			success : callback, // 服务器成功响应处理函数
			error : function(data, status, e) // 服务器响应失败处理函数
			{
				eDialog.alert(e);
			}
		});
	}
};
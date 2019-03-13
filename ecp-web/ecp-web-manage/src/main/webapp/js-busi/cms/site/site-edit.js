$(function(){
	site_edit.init();
});

var site_edit = {
	init : function(){//初始化
		
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			site_edit.saveForm();
		});
		
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){
			site_edit.pubSaveFrom();
		});
		
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'site/grid',
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
					$("#siteLogo").val(fileInfo.fileId);
					$("#imagePreview").attr("src",fileInfo.url);
				}
			}
		});
		//清除图片
		$('#clean_image').click(function(){
			site_edit.cleanImage();
		});
		//初始化字数
		$.isFunction(checkLen) && checkLen($("#siteAddress").get(0),'count','200');
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "site/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'site/grid',
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
	
	saveForm : function(){//保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "site/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('站点保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'site/grid',
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
	cleanImage : function(){//清除图片
		$("#uploadFileObj").val("");
		$("#siteLogo").val("");
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	uploadImage : function (object, path) {
		var data = "";
    	var filepath = path;
    	filepath=(filepath+'').toLowerCase();
    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
    	/** 上传图片文件格式验证 */
    	if (!filepath || !filepath.match(regex)) {
    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
    		$("#uploadFileObj").val("");
			$("#siteLogo").val("");
    		return;
    	}
    	data = {
				'place_width' : 400,
				'place_height' : 80,
				'place_size' : 100
			};
    	var url = $webroot + 'common/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview").attr("src",returnInfo.resultMap.vfsUrl);
				$("#siteLogo").val(returnInfo.resultMap.vfsId);
				eDialog.alert(returnInfo.message);
    		} else {
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	site_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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
				$("#siteLogo").val("");
				eDialog.alert(e);
			}
		});
	}
};


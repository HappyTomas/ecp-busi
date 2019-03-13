$(function(){
	component_edit.init();
});

var component_edit = {
	init : function(){//初始化
		
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			component_edit.saveForm();
		});
		
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'component/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
		//绑定发布按钮
		$('#btnFormPubSave').click(function(){ 
			component_edit.pubSaveFrom();
		});
//		//点击图片上传触发事件
//		$("#uploadFileObj").live("change", function(o) {
//			var path = $(this).val();
//		    if(path==""){
//		    	return false;
//		    }else{
//		    	component_edit.uploadImage(this, path);
//		    }
//		});
//		//清除图片
//		$('#clean_image').click(function(){
//			component_edit.cleanImage();
//		});
		//点击缩略图片上传触发事件
		$("#showPicUpload").eUploadBaseInit({
			imageMaxWidth:260,
			imageMaxHeight:370,
			fileSizeLimit:'100KB',
			callback:function(fileInfo){
				if(fileInfo){
					$("#showPic").val(fileInfo.fileId);
					$("#imagePreview").attr("src",fileInfo.url);
				}
			}
		});
		//清空缩略图
		$("#showPic_clean_image").unbind("click.cn").bind("click.cn",function(){
			$("#showPic").val("");
			$("#imagePreview").attr("src",$("#emptyImageUrl").val());
		});
	},
	pubSaveFrom : function(){//发布保存
		if(!$("#detailInfoForm").valid())return false;
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "component/pubsave",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'component/grid',
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
			url : $webroot + "component/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				//window.location.reload();
				eDialog.success('组件管理保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							var searchParams = $("#searchParams").val();
							SearchObj.openPage({
								"url": $webroot+'component/grid',
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
		$("#showPic").val("");
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
			$("#showPic").val("");
    		return;
    	}
    	data = {
				'place_width' : 260,
				'place_height' : 370,
				'place_size' : 200
			};
    	var url = $webroot + 'common/uploadImage';
    	var callback = function(returnInfo) {
    		/** 上传成功，隐藏上传组件，并显示该图片 */
    		if (returnInfo.success == "ok") {
				$("#imagePreview").attr("src",returnInfo.resultMap.vfsUrl);
				$("#showPic").val(returnInfo.resultMap.vfsId);
				eDialog.alert(returnInfo.message);
    		} else {
    			eDialog.alert(returnInfo.message);
    		}
    	};
    	component_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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
				$("#showPic").val("");
				eDialog.alert(e);
			}
		});
	}
};


$(function(){
	modular_edit.init();
});

var modular_edit = {
	init : function(){//初始化
		if($('#isRead').val()=='1'||$('#isPubish').val()=='1'){
		   ebcForm.setFormReadonly('#detailInfoForm');
		}
		//验证模块名称是否重复
		$('#modularName').live('blur',function(){
			modular_edit.sameModular($(this));
		});
		//发布按钮
//		$('#btnPublish').click(function(){ 
//			modular_edit.saveFrom("1");
//		});
		//保存按钮
		$('#btnSave').click(function(){ 
			modular_edit.saveFrom();
		});
		//下一步按钮
		$('#btnFormNext').click(function(){ 
			modular_edit.goNext();
		});
		//退回
		$('#btnReturn').click(function(){ 
			window.location.href = GLOBAL.WEBROOT+'/modular/init';
		});
		//选择页面类型
		$('#select_PageType').click(function(){ 
			modular_edit.openPageType();
		});
		//选择布局项尺寸 
		$('#select_ItemSize').click(function(){ 
			modular_edit.openItemSize();
		});
		//选择关联组件 
		$('#select_componentId').click(function(){ 
			modular_edit.openComponent();
		});
//		//点击图片上传触发事件
//		$("#uploadFileObj").live("change", function(o) {
//			var path = $(this).val();
//		    if(path==""){
//		    	return false;
//		    }else{
//		    	modular_edit.uploadImage(this, path);
//		    }
//		});
//		//清除图片
//		$('#clean_image').click(function(){
//			modular_edit.cleanImage();
//		});
		//点击缩略图片上传触发事件
		$("#showPicUpload").eUploadBaseInit({
			imageMaxWidth:40,
			imageMaxHeight:40,
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
	openPageType : function(){
		bDialog.open({
			title : "选择页面类型",
			width : 460,
			height : 300,
			url : $webroot + "modular/pageTypeView?applyPageTypeId="+$('#applyPageTypeIds').val(),
			params : {
			},
			callback:function(data){
				var result=data?data.results:[];
                if(result&&result.length>0){
                	$("#applyPageTypeIds").val(result[0].applyPageTypeIds);
    				$("#applyPageTypeNames").val(result[0].applyPageTypeNames);
                }
			}
		});
	},
	openItemSize : function(){
		var pageType = $('#applyPageTypeNames').val();		
		//var linkType = $("input[name=linkType]:checked").val();
		if(!pageType || pageType.length==0){
			eDialog.alert('请选择适用页面类型！');
			return ;
		}
		bDialog.open({
			title : "选择适用布局项尺寸",
			width : 460,
			height : 300,
			url : $webroot + "modular/itemSizeView?applyItemSize="+$("#applyItemSize").val()+"&applyPageTypeId="+$('#applyPageTypeIds').val(),
			params : {
			},
			callback:function(data){
				var result=data?data.results:[];
                if(result&&result.length>0){
					$("#applyItemSize").val(result[0].applyPageTypeIds);
                }
			}
		});
	},
	openComponent : function(){
		bDialog.open({
			title : "选择关联组件",
			width : 860,
			height : 500,
			url : $webroot + "modular/opencomponent",
			params : {
			},
			callback:function(data){
				var result=data?data.results:[];
                if(result&&result.length>0){
					$("#componentId").val(result[0].id);
					$("#componentName").val(result[0].componentName);
                }
			}
		});
	},
	sameModular:function($this){
		if($this.val()!=''){
			var params=ebcForm.formParams($("#detailInfoForm"));
		//	$('#btnSave').attr("disabled",true);
			$.eAjax({
				url : $webroot + "modular/sameModularName",
				data : params,
				async:false,
				success : function(returnInfo){
					if(returnInfo.resultFlag != 'ok'){
						eDialog.error($('#modularName').val()+'已存在，请重新填写！',{
							onClose:function(){
								$('#modularName').val('');
								$('#modularName').focus('');
							}
						});
						
					}else{
					//	$('#btnSave').attr('disabled',false);
					}
				},
				error : function(e,xhr,opt){
					eDialog.error("查询遇到异常!");
				}
			});
		}
	},
	saveFrom : function(doSuccess){//保存
		if(!$("#detailInfoForm").valid())return false;
		//初始化成功函数  returnInfo为后台返回信息
		var params=ebcForm.formParams($("#detailInfoForm"));

		if((typeof doSuccess) != "function" ){
			doSuccess = function(returnInfo){
				if(returnInfo.resultFlag == 'ok'){//保存成功
					eDialog.success('保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = GLOBAL.WEBROOT+'/modular/init';
							}
						}]
					});
				}else{
					eDialog.error('保存出现异常，请联系管理员！');
					$.gridUnLoading();
				}
	        }
		}
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "modular/save",
			data : params,
			success : doSuccess,
			error : function(e,xhr,opt){
				eDialog.error("保存遇到异常!");
				$.gridUnLoading();
			},
			exception : function(msg){
				$.gridUnLoading();
			}
		});
	},
	"goNext" : function(){
		//初始化成功函数  returnInfo为后台返回信息
		var doSuccess = function(returnInfo){
			if(returnInfo && returnInfo.resultFlag == 'ok'){//保存成功
				if(returnInfo.resultMsg){//成功的话resultMsg存页面id
					SearchObj.openPage({
						"url": $webroot+'modularPara/init',
						"params" :{"modularId":returnInfo.resultMsg,"isValid":0},
						"method" :"post"
					});
				}else{
					eDialog.error('返回页面id为空，请联系管理员！');
					$.gridUnLoading();
				}
				
			}else{
				eDialog.error('保存出现异常，请联系管理员！');
				$.gridUnLoading();
			}
		}
		modular_edit.saveFrom(doSuccess);
	},
	"publishPage" : function(){
		//初始化成功函数  returnInfo为后台返回信息
		var doSuccess = function(returnInfo){
			if(returnInfo.resultFlag == 'ok'){//保存成功
				eDialog.success('发布成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = GLOBAL.WEBROOT+'/modular/init';
						}
					}]
				});
			}else{
				eDialog.error('发布出现异常，请联系管理员！');
				$.gridUnLoading();
			}
        }
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "modular/publish",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : doSuccess,
			error : function(e,xhr,opt){
				eDialog.error("发布遇到异常!");
			},
			exception : function(msg){
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
				'place_width' : 40,
				'place_height' : 40,
				'place_size' : 100
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
    	modular_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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


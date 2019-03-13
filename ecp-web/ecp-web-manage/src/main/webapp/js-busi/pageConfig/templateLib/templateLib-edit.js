$(function(){
	templateLib_edit.init();
});

var templateLib_edit = {
	init : function(){//初始化
		var id = $('#id').val();
		if(id){
			$('#siteId').attr('disabled','disabled');
			$('#platformType').attr('disabled','disabled');
			$('#pageTypeId').attr('disabled','disabled');
		}else{
			$('#siteId').removeAttr('disabled');
			$('#platformType').removeAttr('disabled');
			$('#pageTypeId').removeAttr('disabled');
			templateLib_edit.showPageTypeList();
		}
		//绑定选择平台类型
		$('#platformType').bind('change', function(){
			templateLib_edit.showPageTypeList();
		});
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			templateLib_edit.saveFrom();
		});
		//验证模块名称是否重复
		$('#templateName').live('blur',function(){
			templateLib_edit.sameTemplate($(this));
		});
		//绑定下一步按钮
		$('#btnFormNext').click(function(){ 
			templateLib_edit.goNext();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'templateLib/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
//		//点击图片上传触发事件
//		$("#uploadFileObj").live("change", function(o) {
//			var path = $(this).val();
//		    if(path==""){
//		    	return false;
//		    }else{
//		    	templateLib_edit.uploadImage(this, path);
//		    }
//		});
//		//清除图片
//		$('#clean_image').click(function(){
//			templateLib_edit.cleanImage();
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
	showPageTypeList : function(){//平台类型来检索页面类型
		var platformType = $('#platformType').val();
		$.eAjax({
			url : $webroot + 'pageInfo/changePlatformType',
			data : {
				"platformType":platformType,
				"status":"1"
			},
			success : function(returnInfo){
				$("#pageTypeId").html("");
				$("#pageTypeId").append("<option value= ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].pageTypeName+"</option>";
					$("#pageTypeId").append(option);
				}
			}
		});
	},
	saveFrom : function(doSuccess){//保存
		if(!$("#detailInfoForm").valid())return false;
		
		//初始化成功函数  returnInfo为后台返回信息
		if((typeof doSuccess) != "function" ){
			doSuccess = function(returnInfo){
				if(returnInfo.resultFlag == 'ok'){//保存成功
					eDialog.success('保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'templateLib/grid',
									"params" :{"searchParams":(searchParams?searchParams:"")},
									"method" :"post"
								});
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
			url : $webroot + "templateLib/save",
			data : ebcForm.formParams($("#detailInfoForm")),
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
		var reqType = $('#reqType').val();
		if(reqType == 'view'){//查看
			SearchObj.openPage({
				"url": $webroot+'templateConfig/init',
				"params" :{"templateLibId":$('#id').val(),"reqType":reqType},
				"method" :"post"
			});
		}else{
			//初始化成功函数  returnInfo为后台返回信息
			var doSuccess = function(returnInfo){
				if(returnInfo && returnInfo.resultFlag == 'ok'){//保存成功
					if(returnInfo.resultMsg){//成功的话resultMsg存页面id
						SearchObj.openPage({
							"url": $webroot+'templateConfig/init',
							"params" :{"templateLibId":returnInfo.resultMsg,"reqType":reqType},
							"method" :"post"
						});
					}else{
						eDialog.error('返回模板id为空，请联系管理员！');
						$.gridUnLoading();
					}
				}else{
					eDialog.error('保存出现异常，请联系管理员！');
					$.gridUnLoading();
				}
			}
			templateLib_edit.saveFrom(doSuccess);
		}
	},
	sameTemplate:function($this){
		if($this.val()!=''){
			var params=ebcForm.formParams($("#detailInfoForm"));
		//	$('#btnSave').attr("disabled",true);
			$.eAjax({
				url : $webroot + "templateLib/sameTemplateName",
				data : params,
				async:false,
				success : function(returnInfo){
					if(returnInfo.resultFlag != 'ok'){
						eDialog.error($('#templateName').val()+'已存在，请重新填写！',{
							onClose:function(){
								$('#templateName').val('');
								$('#templateName').focus('');
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
    	templateLib_edit.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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


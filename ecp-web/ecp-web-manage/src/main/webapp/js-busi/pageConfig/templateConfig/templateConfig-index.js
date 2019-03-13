$(function(){
	templateConfig.init();
	$('#goBack').click(function(){ 
		var reqType=$('#reqType').val();
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":$("#templateId").val(),
				"reqType":reqType
		}
		SearchObj.openPage({
			"url" : $webroot+'templateLib/edit',
			"params" : params,
			"method" :"post"
		});
	});
});
var templateConfig = {
	"init" : function(){//初始化配置页面
		templateConfig.showLayoutDesign();
		
		//发布功能
		$('#lnkTemplatePub').click(function(e) {
			var pId = $('#templateId').val();
			eDialog.confirm('确认要发布该模板吗？',{
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + "templateLib/changestatus",
							data : {'id' : pId,status:1},
							success : function(returnInfo){
								if(returnInfo && returnInfo.resultFlag == 'ok'){//发布成功
									eDialog.alert('模板发布成功！',function(){
										window.location.replace($webroot + 'templateLib/grid');
										//templateConfig.goList();
									},'confirmation');
								}
						    }
						});
					}
				},{
					caption : '取消',callback : $.noop
				}]
			});
		});
	},
	"goList": function(){
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'templateLib/grid',
			"params" : params,
			"method" :"post"
		});
	},
	"showLayoutDesign":function(){
		var reqType = $('#reqType').val();
		var templateId = $("#templateId").val();
		var pageTypeId = $("#pageTypeId").val();
		var $content = $("#tpl-content");
		if(!templateId){
			$content.html("<span>模板不存在，请返回模板列表确认信息！</span>");
		}
		
		//请求参数
		var param = {
					reqType:reqType?reqType:"",
					templateId:templateId?templateId:"",
					pageTypeId:pageTypeId?pageTypeId:""
				};
		
		$.eAjax({
			url : $webroot + "templateLayout/init",
			data : param,
			async : true,
			type : "post",
			dataType : "html",
			success : function(returnInfo){
				if(returnInfo && returnInfo !='error'){//查询成功
					$content.html(returnInfo);
					
					//刷新拖拽功能作用对象
					if(doJsmartdrag.jsmartdragObj){
						doJsmartdrag.jsmartdragObj.freshTarget();
					}
				}else{
					$content.html("<span>查询模板布局异常！</span>");
				}
		    },
			error : function(e,xhr,opt){
				$content.html("<span>查询模板布局错误！</span>");
			},
			exception : function(msg){
				$content.html("<span>获取模板布局异常！</span>");
			}
		});
	},
	/**
	 * 图片上传
	 * @param {} object  file对象
	 * @param {} path 本地文件路径
	 */
	"uploadImage" : function (object, path ,callback) {
		if(callback && (typeof callback) == "function"){
			var data = {
					'place_width' : 130,
					'place_height' : 130,
					'place_size' : 100
				};
		    	var filepath = path;
		    	filepath=(filepath+'').toLowerCase();
		    	var regex = new RegExp('\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$');
		    	/** 上传图片文件格式验证 */
		    	if (!filepath || !filepath.match(regex)) {
		    		eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif,.bmp).');
		    		return;
		    	}
		    	var url = $webroot + 'common/uploadImage';
		    	
		    	templateConfigSideBar.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
		}else{
			eDialog.alert("无效的回调函数，请联系管理员！");
		}
    },
    "ajaxFileUpload" : function (url, secureuri, fileElementId, type, dataType, callback,data) {
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
				eDialog.alert(e);
			}
		});
	}
};
var doJsmartdrag = {
	"jsmartdragObj" : null,//存放拖拽功能作用的对象
	"bandJsmartdrag" :function($obj,jsmartdragParam){
		if(!($obj instanceof jQuery) || !jsmartdragParam || !jsmartdragParam.target){
			return false;
		}
		var param = 
		doJsmartdrag.jsmartdragObj = $obj.jsmartdrag(jsmartdragParam);
	}
};

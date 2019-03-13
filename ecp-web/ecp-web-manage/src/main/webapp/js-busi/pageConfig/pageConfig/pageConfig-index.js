
var PageConfigtion = {
	"init" : function(){//初始化配置页面
		//初始化选中页签
		var pageType = $.trim($('#pageType').val());
		if(pageType == 'edit') PageConfigtion.showPageEdit();
		else PageConfigtion.showLayoutDesign();
		
		//初始化页签切换事件
		PageConfigtion.designModeSelect();
		
		//发布功能
		$('#lnkPagePub').click(function(e) {
			var pId = $('#pageId').val();
			var shopId = $('#shopId').val();
			var pageTypeId = $('#pageTypeId').val();
			var siteId = $('#siteId').val();
			var hasOtherRelease=false;
			var msg='确认要发布该页面吗？';
			if(1==pageTypeId){//店铺首页
				$.eAjax({
					url : $webroot + "pageInfo/hasOtherRelease",
					type : "POST",
					async: false,
					data : {"id":pId,"shopId":shopId,"pageTypeId":pageTypeId,"siteId":siteId},
					success : function(returnInfo) {
						if(returnInfo.resultFlag=='ok'){
							hasOtherRelease=true;
						}else if(returnInfo.resultMsg){
							hasOtherRelease=false;
						}
					},error : function(e,xhr,opt){
						eDialog.error("出现异常!");
						$.gridUnLoading();
					},complete:function(){
						$.gridUnLoading();
					}
				});
			}
			if(hasOtherRelease){
				msg='存在其他已发布的页面，是否替换？';
			}else{ 
				var status=$('#status').val();
				if(1==status){
					msg='该页面已发布，是否覆盖？';
				}
			}
			eDialog.confirm(msg,{
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + "pageConfig/publishTemplate",
							data : {'pageId' : pId},
							success : function(returnInfo){
								if(returnInfo && returnInfo.resultFlag == 'ok'){//发布成功
									eDialog.alert('页面发布成功！',function(){
										var url = $webroot+'page-pub/init?pageId=' + pId;
										windowOpenUrl(url,"open","_blank");
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
	"designModeSelect" :function(){//布局管理与页面编辑切换
		$(".design-mode-select li").click(function(){
	        var index=$(this).index();
	        var pageType;
	        if(index == 0){//布局管理
	        	pageType = "layout";
	        }else if(index == 1){//页面编辑
	        	pageType = "edit";
	        }
	        SearchObj.openPage({
				"url": $webroot+'pageConfig/init?pageId='+$('#pageId').val()+'&pageType='+pageType,
				"params" :{"pageId":$('#pageId').val(),"mallskintomanage":$("#mallskintomanage").val()},
				"method" :"post"
			});
	    });
	},
	"showLayoutDesign":function(){
		var pageId = $("#pageId").val();
		var pageTypeId = $("#pageTypeId").val();
		var $content = $("#tpl-content");
		if(!pageId){
			$content.html("<span>页面不存在，请返回页面列表确认信息！</span>");
		}
		//请求参数
		var param = {
			pageId:pageId?pageId:"",
			pageTypeId:pageTypeId?pageTypeId:""
		};
		$.eAjax({
			url : $webroot + "layoutDesign/init",
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
					$content.html("<span>查询页面布局异常！</span>");
				}
		    },
			error : function(e,xhr,opt){
				$content.html("<span>查询页面布局错误！</span>");
			},
			exception : function(msg){
				$content.html("<span>获取页面布局异常！</span>");
			}
		});
	},
	includeCss: function(file) 
	{ 
		var name = file.replace(/^\s|\s$/g, "");
		var att = name.split('.');
		var ext = att[att.length - 1].toLowerCase();
		var isCSS = ext == "css";
		var tag = isCSS ? "link" : "script";
		var attr = isCSS ? " type='text/css' rel='stylesheet' "
				: " type='text/javascript' ";
		var link = (isCSS ? "href" : "src") + "='" +  name
				+ "'";
		if ($(tag + "[" + link + "]").length == 0)
			$("head").prepend("<" + tag + attr + link + "></" + tag + ">");
		  
	},	
	loadjscssfile: function(filename, filetype){
		if(!filename || !filetype) return;
		if (filetype=="js"){ //if filename is a external JavaScript file 
			var fileref=document.createElement('script') ;
			fileref.setAttribute("type","text/javascript") ;
			fileref.setAttribute("src", filename); 
		}else if (filetype=="css"){ //if filename is an external CSS file 
			var fileref=document.createElement("link"); 
			fileref.setAttribute("rel", "stylesheet");
			fileref.setAttribute("type", "text/css");
			fileref.setAttribute("href", filename);
		} 
		if (typeof fileref!="undefined") {
			try {
				document.getElementsByTagName("head")[0].appendChild(fileref);
			} catch (e) {}
		} 
	},
	removejscssfile: function(filename, filetype){
		//判断文件类型 
		var targetelement=(filetype=="js")? "script" : (filetype=="css")? "link" : "none"; 
		//判断文件名 
		var targetattr=(filetype=="js")? "src" : (filetype=="css")? "href" : "none"; 
		var allsuspects=document.getElementsByTagName(targetelement); 
		//遍历元素， 并删除匹配的元素 
		for (var i=allsuspects.length; i>=0; i--){ 
			if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1){
				allsuspects[i].parentNode.removeChild(allsuspects[i]); 
			} 
		}
	},
	/**
	 * 页面切换到页面逻辑功能
	 */
	showPageEdit : function(){
		var css_name = $("#attr-matchingColour-css").val();
		css_name +=".css";
		PageConfigtion.loadjscssfile($webroot+'css/pageConfig/skin/'+css_name,"css");
		/*if("tpl-skin00"!=css_name&&css_name!=null&&css_name.length!=0){//引入css
			css_name +=".css";
			PageConfigtion.loadjscssfile($webroot+'css/pageConfig/skin/'+css_name,"css");
		}else if("tpl-skin00"==css_name){//删除前一个css
			css_name ="tpl-skin";
			PageConfigtion.removejscssfile($webroot+'css/pageConfig/skin/'+css_name,"css");
		}*/
		$('ul.design-mode-select a').removeClass('active');
		$('#lnkPageEdit').addClass('active');
		var pageId = $("#pageId").val();
		var param = {
			pageId : (pageId?pageId:'')
		};
		$.eAjax({
			url : $webroot + "page-edit/pageEdit",
			data : param,
			dataType : "text",
			success : function(returnInfo){
				if(returnInfo) $('#tpl-content').html(returnInfo);
				
				pageConfigSideBar.showBgColor();
				pageConfigSideBar.showBgPic();
			}
		});
	},
	/**
	 * 保存为模板
	 */
	"saveToTemplate":function(){
		var $templateDialog = $("#save-template-dialog");
		var checkName = function(name,successCallback){
			if(!($.isFunction(successCallback))){
				successCallback = $.noop;
			}
			var param = {
					templateName : (name?name:'')
				};
			$.eAjax({
				url : $webroot + "pageConfig/isExistTemplateName",
				data : param,
				async : true,
				type : "post",
				dataType : "json",
				success : function(returnInfo){
					if(returnInfo && returnInfo.resultFlag == "02"){//不重名
						successCallback();
					}else if(returnInfo && returnInfo.resultMsg){
						$(".error-msg",".dialog-content",$templateDialog).text(returnInfo.resultMsg).show();
					}else{
						$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
					}
			    },
				error : function(e,xhr,opt){
					$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
				},
				exception : function(msg){
					$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
				}
			});
		}
		var doSave = function(){
			var pageId = $("#pageId").val();
			var siteId = $("#siteId").val();
			var pageTypeId = $("#pageTypeId").val();
			var templateName = $("#template-name").val();
			var platformType = $("#platform_type").val();
			var showPic = $("#template-show-pic").val();
			var param = {
				pageInfoId : (pageId?pageId:''),
				siteId : (siteId?siteId:''),
				pageTypeId : (pageTypeId?pageTypeId:''),
				showPic : (showPic?showPic:''),
				templateName:templateName?templateName:"",
				platformType:platformType?platformType:""
			};
			$.eAjax({
				url : $webroot + "pageConfig/saveToTemplate",
				data : param,
				async : true,
				type : "post",
				dataType : "json",
				success : function(returnInfo){
					if(returnInfo && returnInfo.resultFlag == "ok"){
						bDialog.closeCurrent();
						if(returnInfo.resultMsg!=null && returnInfo.resultMsg!=""&&returnInfo.resultMsg.length>0 ){
							eDialog.confirm("保存成功", {
								buttons : [{
									caption : '确定',
									callback : $.noop
								}]
							});
						}else{
							eDialog.confirm("保存成功", {
								buttons : [{
									caption : '模板管理',
									callback : function(){
										window.location.href=$webroot + "templateLib/grid";
									}
								},{
									caption : '留在本页',
									callback : $.noop
								}]
							});
						}
					}else{
						$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();;
					}
			    },
				error : function(e,xhr,opt){
					$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
				},
				exception : function(msg){
					$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
				}
			});
		}
		//上传图回调函数
		var updateImgCallback = function(returnInfo) {
    		/** 上传成功，显示该图片 */
			var $templateDialog = $("#save-template-dialog");
    		if (returnInfo && returnInfo.success == "ok") {
    			if(returnInfo.resultMap && returnInfo.resultMap.vfsUrl && returnInfo.resultMap.vfsId){
    				$("img.image-preview",$templateDialog).attr("src",returnInfo.resultMap.vfsUrl);
    				$("#template-show-pic").val(returnInfo.resultMap.vfsId);
    			}else{
    				eDialog.alert("上传失败，请重试！");
    			}
    		} else {
    			eDialog.alert(returnInfo.message);
    		}
    	};
		$(".save","#save-template-dialog").live("click",function(){
			if(!$(".save-template-form").valid())return false;
			checkName($("#template-name").val(),doSave);
		});
		$(".cancel","#save-template-dialog").live("click",function(){
			bDialog.closeCurrent();
		});
		
		$(".error-msg",".dialog-content",$templateDialog).hide();
		//绑定输入框
		$("#template-name").live("change", function(o) {
			$(".error-msg",".dialog-content",$templateDialog).hide();
			$("#template-name").val($(this).val());
		});
		//绑定图交互事件
		$("#save-template-pic",$templateDialog).live("change", function(o) {
			var path = $(this).val();
		    if(path==""){
		    	return false;
		    }else{
		    	PageConfigtion.uploadImage(this, path,updateImgCallback);
		    }
		});
		$(".page-operation-btns .page-save-template-btn").live("click",function(){
			//初始化表单
			var templateName=$("#template-name").val();
			if(templateName==null||templateName.length==0){
				$("#template-name").val($("#pageName").val());
			}
			$("#platform_type").val($("#platformType").val());
			checkName($("#template-name").val());
			$("img.image-preview",$templateDialog).attr("src",$("input[name=no-image-preview]",$templateDialog).val());
			$("input.show-pic",$templateDialog).val("");
			bDialog.open({
                title : "保存为模板",
                width : 520,
                height : 450,
                callback:$.noop,
                onShow:function(){
                	$templateDialog.remove();
                },
                onHidden:function(){
                	$templateDialog.attr("style","");
                	$("#page").after($templateDialog);
                }
            },
            $templateDialog
            );
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
					'place_width' : 280,
					'place_height' : 200,
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
		    	
		    	pageConfigSideBar.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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
$(function(){
	PageConfigtion.init();
	PageConfigtion.saveToTemplate();
	$('#goBack').click(function(){ 
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":$("#pageId").val(),
				"mallskintomanage" : $("#mallskintomanage").val()
		};
		var _url = "";
		if($("#mallskintomanage").val()=="true"){
			_url = $webroot+'cmssellerpageinfo/edit';
		}else{
			_url = $webroot+'pageInfo/edit';
		}
		SearchObj.openPage({
			"url" : _url,
			"params" : params,
			"method" :"post"
		});
	});
	$('#backtogrid').click(function(){
		var params = {
				"mallskintomanage" : $("#mallskintomanage").val()
		};
		var url = "";
		if($("#mallskintomanage").val()=="true"){
			url = $webroot+'cmssellerpageinfo/init';
		}else{
			url = $webroot+'pageInfo/grid';
		}
		SearchObj.openPage({
			"url" : url,
			"params" : params,
			"method" :"post"
		});
	});
});
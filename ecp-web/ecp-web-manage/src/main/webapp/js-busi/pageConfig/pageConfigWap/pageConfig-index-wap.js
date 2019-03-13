
var pageConfigtionWap = {
	"init" : function(){//初始化配置页面
		calPageDecorateHeight();
    	calPageEditHeight();
		pageConfigtionWap.showPageEdit();
//		var $layoutItemDiv=$('.active',$('#tpl-content'));
//		pageConfigtionWap.showPageEditItem($layoutItemDiv);
		$(".page-phone").css("padding-right", "0px");
        $(".page-phone-edit").hide();
		//发布功能
		$('#lnkPagePub').click(function(e) {
			var pId = $('#pageId').val();
			var shopId = $('#shopId').val();
			var pageTypeId = $('#pageTypeId').val();
			var platformType = $('#platformType').val();
			var siteId = $('#siteId').val();
			var hasOtherRelease=false;
			var msg='确认要发布该页面吗？';
			if(50==pageTypeId){//页面类型为移动端首页
				$.eAjax({
					url : $webroot + "pageInfo/hasOtherRelease",
					type : "POST",
					async: false,
					data : {"id":pId,"platformType":platformType,"pageTypeId":pageTypeId,"siteId":siteId},
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
							data : {'pageId' : pId,'platformType' : platformType},
							success : function(returnInfo){
								if(returnInfo && returnInfo.resultFlag == 'ok'){//发布成功
									if('03'==platformType){ //wap 
										var arrayObj=eval(returnInfo.resultMsg);
										$('#page_pub_dialog_a').html(arrayObj[0]);
										$('#page_pub_dialog_a').attr("href",arrayObj[0]);
										$('#page_pub_dialog_img').attr("src",arrayObj[1]);
										var $pagePubDialog = $("#page_pub_dialog");
										$pagePubDialog.show();
										$pagePubDialog.removeClass("hide");
										$('.save',$pagePubDialog).live("click",function(){
											if($pagePubDialog.hasClass("hide")){
												return;
											}else{
												bDialog.closeCurrent();
												$pagePubDialog.hide();
												$pagePubDialog.addClass("hide");
												var url = $webroot+'page-pub/init?pc=true&pageId=' + pId;
												windowOpenUrl(url,"open","_blank");
											}
					            		});
					                	$('.cancel',$pagePubDialog).live("click",function(){
					                		$pagePubDialog.hide();
											$pagePubDialog.addClass("hide");
					                		bDialog.closeCurrent();
					                	});
										bDialog.open({
							                title : "发布成功",
							                width : 600,
							                height : 360,
							                callback:function () {
							                	$pagePubDialog.hide();
							                },
							                onShow:function(){
							                	$pagePubDialog.hide();
							                },
							                onHidden:function(){
							                	$pagePubDialog.attr("style","");
							                	$("#page").after($pagePubDialog);
							                }
							            },
							            $pagePubDialog
							            );
									}else{ //web
										eDialog.alert('页面发布成功！',function(){
											var url = $webroot+'page-pub/init?pageId=' + pId;
											windowOpenUrl(url,"open","_blank");
										},'confirmation');
									}
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
		//$.includePath +
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
		var pageId = $("#pageId").val();
		var param = {
			pageId : (pageId?pageId:'')
		};
		$.eAjax({
			url : $webroot + "page-edit/pageEdit",
			data : param,
			dataType : "text",
			async: false,
			success : function(returnInfo){
				if(returnInfo) {
					$('#tpl-content').html(returnInfo);
					$('#tpl-content').imagesLoaded(function(){
						window.setTimeout(function (){
							if($("#pageDecorate").hasClass('mCustomScrollbar')){
								$('#pageDecorate').mCustomScrollbar('update');
							}else{
								var pageDecorate = $("#pageDecorate").mCustomScrollbar({
									scrollInertia: 150,
									advanced: {
										autoScrollOnFocus: false
									}
								});
							}
						},500);
				    });
					//显示页面背景
					if(pageConfigSideBarWap && $.isFunction(pageConfigSideBarWap.showBgColor)){
						pageConfigSideBarWap.showBgColor();
						pageConfigSideBarWap.showBgPic();
					}
				}
			}
		});
	},
	/**
	 * 显示属性编辑页
	 */
	showPageEditItem : function($phone_layout){
		var hasAjaxVm=false;
		var getUrl = function(modId){
			var param = {};
			switch (modId) {
			case "100"://单列图模块
				param.url = '';
				param.requestVmName = "wap/wap-oneImg";
				param.title = "单列图模块";
				break;
			case "101"://双列图模块
				param.url = '';
				param.requestVmName = "wap/wap-doubleImg";
				param.title = "双列图模块";
				break;
			case "102"://多图模块
				param.url = '';//modular-load/floorNavInit
				param.requestVmName = "wap/wap-moreImg";
				param.title = "多图模块";
				break;
			case "103"://焦点图模块
				param.url = '';
				param.requestVmName = "wap/wap-focus";
				param.title = "焦点图模块";
				break;
			case "104"://楼层图片模块
				param.hasAjaxVm=true;
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-floor";
				param.title = "楼层模块";
				break;
			case "105"://栏目模块
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-column";
				param.title = "栏目模块";
				break;	
			case "106"://搜索模块
				param.url = '';
				param.requestVmName = "wap/wap-search";
				param.title = "搜索模块";
				break;
			case "107"://楼层宝贝模块
				param.hasAjaxVm=true;
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-floorGds";
				param.title = "楼层模块";
				break;
			case "108"://优惠券模块
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-coupon";
				param.title = "优惠券模块";
				break;
			case "109"://信息模块
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-noticeInfo";
				param.title = "信息模块";
				break;
			case "110"://四列图模块
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-fourImg";
				param.title = "四列图模块";
				break;
			case "29"://猜你喜欢模块
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-guess";
				param.title = "猜你喜欢模块";
				break;
            case "112"://竖排优惠券模块 
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-couponVert";
				param.title = "优惠券模块";
				break;
			case "113"://我的积分 
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-myPoint";
				param.title = "我的积分模块";
				break;
            case "115"://限时秒杀模块 
				param.url = '';
				//弹出框请求展示的vm名称
				param.requestVmName = "wap/wap-seckill";
				param.title = "优惠券模块";
				break;
            case "31"://秒杀列表
            	param.url = '';
            	//弹出框请求展示的vm名称
            	param.requestVmName = "wap/wap-seckillList";
            	param.title = "秒杀列表模块";
            	break;
            case "117"://多楼层模块
            	param.url = '';
            	//弹出框请求展示的vm名称
            	param.requestVmName = "wap/wap-multiFloors";
            	param.title = "多楼层模块";
            	break;
			default://公共属性处理页面
				param.url = 'modular-dynamic/publicSet';
				break;
			}
			return param;
		};
		var itemId = $('#itemId',$phone_layout).val();
		var pageId = $('#pageId').val();
		var modularId = $('#modularId',$phone_layout).val();
		var modularType = $('#modularType',$phone_layout).val();
		var requestVmName = $('#componentEditUrl',$phone_layout).val();
		
		if (typeof(itemId) == "undefined") { return ;}
		/**
		 * 在原有的基础上进行改造。通过用户传的自身要渲染的vm名称，来请求数据。vm一定要放在modular目录下、
		 */
		var param = {url:''}//getUrl(modularType);
		if("104" == modularType || "107" == modularType){
			hasAjaxVm=true ;//param.hasAjaxVm;
		}
		var url = "";
		if(param.url != ""){
			url = param.url;
			url = $webroot + url + '?pageId='+pageId+'&itemId=' + itemId + '&modularId=' + modularId+ '&modularType=' + modularType;
		}else{
			//platFormType=wap&
			url = $webroot +'commonmodular/modularcommonload?platFormType=wap&pageId='+pageId+'&itemId=' + itemId + '&modularId=' + modularId+'&requestVmName=' +requestVmName+'&pageTypeId='+$("#pageTypeId").val();
		}
		if(hasAjaxVm){
			$('#btnSave').hide();
		}
		$.eAjax({
			url : url,
//			data : param,
			dataType : "text",
//			async: false,
			success : function(returnInfo){
				if(returnInfo) {
					$('#pageEdit .edit-cont').html(returnInfo);
				}
				$('#pageEdit').imagesLoaded(function(){
					if($("#pageEdit").hasClass('mCustomScrollbar')){
						$('#pageEdit').mCustomScrollbar('update');
					}else{
						var pageEdit = $("#pageEdit").mCustomScrollbar({
							scrollInertia: 150,
							advanced: {
								autoScrollOnFocus: false
							}
						});
					}
				}); 
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
						$(".error-msg",".dialog-content",$templateDialog).removeClass("hide");
					}else{
						$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
						$(".error-msg",".dialog-content",$templateDialog).removeClass("hide");
					}
			    },
				error : function(e,xhr,opt){
					$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
					$(".error-msg",".dialog-content",$templateDialog).removeClass("hide");

				},
				exception : function(msg){
					$(".error-msg",".dialog-content",$templateDialog).text("存在异常，请刷新页面！").show();
					$(".error-msg",".dialog-content",$templateDialog).removeClass("hide");

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
									callback : function(){
										if($templateDialog.hasClass("hide")){
											return;
										}else{
											bDialog.closeCurrent();
											$templateDialog.hide();
											$templateDialog.addClass("hide");
										}
									}
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
			$templateDialog.hide();
			$templateDialog.addClass("hide");
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
		    	pageConfigtionWap.uploadImage(this, path,updateImgCallback);
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
			$templateDialog.removeClass("hide");
			bDialog.open({
                title : "保存为模板",
                width : 520,
                height : 450,
                callback:function () {
                	$templateDialog.hide();
                },
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
		    	
		    	pageConfigSideBarWap.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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
function calPageDecorateHeight() {
    $('#pageDecorate').height($(window).height()
            - $('.tpl-navbar').height()
            - $('.design-navigation-wrap').height()
            - 10);
}

function calPageEditHeight() {
    $('#pageEdit').height($(window).height()
            - $('.tpl-navbar').height()
            - $('.design-navigation-wrap').height()
            - 78);
}
$(function(){
	pageConfigtionWap.init();
	pageConfigtionWap.saveToTemplate();
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
    $(".phone-layout").live('click',function () {
    	$(this).toggleClass('active')
    	.siblings('.phone-layout')
    	.removeClass('active');
    });
    //编辑
    $(".medit").live('click',function(){
    	var $thisLayout=$(this).closest('.phone-layout');
    	$thisLayout.toggleClass('active')
    	.siblings('.phone-layout')
    	.removeClass('active');
    	pageConfigtionWap.showPageEditItem($thisLayout);
        if ($(".page-phone-edit").css("display") == 'none') {
            $(".page-phone").css("padding-right", "290px");
            $(".page-phone-edit").show();
        }
    });
    //上移
    $(".mup").live('click',function(){
    	var $div_layout=$(this).parents(".phone-layout");
    	var $prevOne= $div_layout.prev(".phone-layout");
    	if($prevOne.length==0){
    		$div_layout.addClass("active");
    		return ;
    	}
    	var this_modularId=$('#modularId',$div_layout).val();
    	if(this_modularId==111 || this_modularId==116 || this_modularId==117){
    		eDialog.alert("该模块只能放在最下方，上移失败");
			return;
    	}
    	//异步保存入库 start
    	var param = {
				"layoutItemList":[]
			};
    	var thisLayoutItem = {};
    	thisLayoutItem.id = $('#itemId',$div_layout).val();
    	thisLayoutItem.rowNo = $('#rowNo',$prevOne).val();
        param.layoutItemList.push(thisLayoutItem);
        
        var downLayoutItem = {};
        downLayoutItem.id = $('#itemId',$prevOne).val();
        downLayoutItem.rowNo = $('#rowNo',$div_layout).val();
        param.layoutItemList.push(downLayoutItem);
        
        pageConfigSideBarWap.updateLayoutItemBatch(param,null,null); 
        //异步保存入库  end
    	var rowNo=$('#rowNo',$div_layout).val();
    	$('#rowNo',$div_layout).val($('#rowNo',$prevOne).val());
    	$('#rowNo',$prevOne).val(rowNo);
    	
    	$prevOne.addClass("active");
    	$div_layout.removeClass("active");
    	
    	var inHtml=$div_layout.clone(true);
    	var inHtml2=$prevOne.clone(true);
    	$prevOne.replaceWith(inHtml);
    	$div_layout.replaceWith(inHtml2);
    	
    	doJsmartdrag.jsmartdragObj.freshTarget();
        $('#pageDecorate').mCustomScrollbar('update');
    });
    //下移
    $(".mdown").live('click',function(){
    	var $div_layout=$(this).parents(".phone-layout");
    	var $next_layout= $div_layout.next(".phone-layout");
    	if($next_layout.length==0){
    		$div_layout.addClass("active");
    		return ;
    	}
    	var next_modularId=$('#modularId',$next_layout).val();
    	if(next_modularId==111 || next_modularId==116 || next_modularId==117){
    		eDialog.alert("下方模块只能放在最下方，下移失败");
			return;
    	}
    	//异步保存入库 start
    	var param = {
				"layoutItemList":[]
			};
    	var thisLayoutItem = {};
    	thisLayoutItem.id = $('#itemId',$div_layout).val();
    	thisLayoutItem.rowNo = $('#rowNo',$next_layout).val();
        param.layoutItemList.push(thisLayoutItem);
        
        var downLayoutItem = {};
        downLayoutItem.id = $('#itemId',$next_layout).val();
        downLayoutItem.rowNo = $('#rowNo',$div_layout).val();
        param.layoutItemList.push(downLayoutItem);
        
        pageConfigSideBarWap.updateLayoutItemBatch(param,null,null); 
        //异步保存入库  end
        var rowNo=$('#rowNo',$div_layout).val();
    	$('#rowNo',$div_layout).val($('#rowNo',$next_layout).val());
    	$('#rowNo',$next_layout).val(rowNo);
        
    	$next_layout.addClass("active");
    	$div_layout.removeClass("active");
    	
    	var inHtml=$div_layout.html();
    	$div_layout.html($next_layout.html());
    	$next_layout.html(inHtml);
    	
    	doJsmartdrag.jsmartdragObj.freshTarget();
    	$('#pageDecorate').mCustomScrollbar('update');
    });
    //删除
    $(".mdel",".phone-content").live('click',function(){
    	var $div_layout=$(this).parents(".phone-layout");
    	eDialog.confirm('确定移除该布局项吗？',{
			buttons : [{
				caption : '确定',
				callback : function(){
					// $(this).parents(".phone-layout").hide();
			        var param = {
							"layoutItemList":[]
						};
			        var delLayoutItem = {};
			        delLayoutItem.id = $('#itemId',$div_layout).val();
			        delLayoutItem.status = 2;
			        param.layoutItemList.push(delLayoutItem);
			        var $prevOne= $div_layout.prev(".phone-layout");
			        var $nextOne= $div_layout.next(".phone-layout");
			        var allDel=false;
			        if($prevOne.length==0&&$nextOne.length==0){
			        	allDel=true; 
			        }
			        var i=0;
			        var thisRowNo=$('#rowNo',$div_layout).val();
			        while(true){
			        	var divId=$('#id',$nextOne).val()
						if($nextOne.length>0 && 'default_layout_item'!=divId){
							var nextRowNo=$('#rowNo',$nextOne).val();
							var nextItemId=$('#itemId',$nextOne).val();
							var nextModularId=$('#modularId',$nextOne).val();
							var nextLayoutItem = {};
							var rowNoTemp=parseFloat(thisRowNo)+i;
							nextLayoutItem.rowNo=rowNoTemp;
							$('#rowNo',$nextOne).val(rowNoTemp);
							
							
							nextLayoutItem.id = nextItemId;
							nextLayoutItem.modularId = nextModularId;
							nextLayoutItem.itemSize = 0;
							nextLayoutItem.itemNo = 1;
							param.layoutItemList.push(nextLayoutItem);
						}else{
							break;
						}
						$nextOne=$nextOne.next('.phone-layout');
						i=i+1;
					}
			        $div_layout.remove();
			        if(allDel){
			        	var $defaultLayoutItem=$('<div class="phone-layout" id="default_layout_item"><div class="module-placeholder">亲，您还未添加任何模块，请将左侧模块拖动到这里开始装修哦~</div></div>');
			        	$('#tpl-content').append($defaultLayoutItem);
			        }
			        doJsmartdrag.jsmartdragObj.freshTarget();
			        $('#pageDecorate').mCustomScrollbar('update');
			        var successCallback2 = function(itemData){
					}
					var errorCallback2 = function(errorMsg){
						if(errorMsg){
							eDialog.alert(errorMsg);
						}else{
							eDialog.alert("更新布局项错误，请刷新页面！");
						}
					}
					pageConfigSideBarWap.updateLayoutItemBatch(param,successCallback2,errorCallback2); 
					$('#pageEdit').parents(".page-phone-edit").hide();
			        $(".page-phone").css("padding-right", "0");
				}
			},{caption : '取消',callback : $.noop}]
		});
    });
    function debounce(func, wait, immediate) {
        var timeout;
        return function () {
            var context = this, args = arguments;
            var later = function () {
                timeout = null;
                if (!immediate) func.apply(context, args);
            };
            var callNow = immediate && !timeout;
            clearTimeout(timeout);
            timeout = setTimeout(later, wait);
            if (callNow) func.apply(context, args);
        };
    };
	if($("#pageEdit").hasClass('mCustomScrollbar')){
		$('#pageEdit').mCustomScrollbar('update');
	}else{
		var pageEdit = $("#pageEdit").mCustomScrollbar({
			scrollInertia: 150,
			advanced: {
				autoScrollOnFocus: false
			}
		});
	}
    $(window).on('resize.scroll', debounce(function () {
        calPageEditHeight();
        $("#pageEdit").mCustomScrollbar('update');

        calPageDecorateHeight();
        $("#pageDecorate").mCustomScrollbar('update');
    }, 300));
    var w = 0;
    $('#proList li').each(function () {
        w = w + $(this).width() + 10;
    });
    $('#proList .tScroll').width(w);
    var $scrollObj = $('#proList')[0];
});

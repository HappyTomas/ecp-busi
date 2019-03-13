$(function(){
	templateConfigSideBarWap.init();
});
var templateConfigSideBarWap = {
		"init" : function(){//初始化左侧栏
			templateConfigSideBarWap.bandSelectSidebarItem();
			templateConfigSideBarWap.bandCloseSidebarItem();
			templateConfigSideBarWap.getModule($(".layout-item span.size-selected").text());
			templateConfigSideBarWap.bandSelectItemSize();
			//templateConfigSideBarWap.initPageAttrbute();//初始化页面属性交互事件
		},
		"initPageAttrbute":function(){//初始化页面属性交互事件
			//绑定背景色   （背景色，是否显示）
			templateConfigSideBarWap.bandBgColor();
			//绑定背景图    （ 更改    删除     展示方式）
			templateConfigSideBarWap.bandBgPic();
			
		},
		"bandSelectSidebarItem" :function(){//左侧栏选择事件
			var tab_li=$(".tab-bar li");
		    tab_li.click(function(){
		        if(!$(this).hasClass("disabled")){
		            var index=tab_li.index($(this));

		            $(".tab-bar li").removeClass("selected");
		            $(this).addClass("selected");

		            $(".toolbar").show();
		            $(".toolbar dd").removeClass("selected");
		            $(".toolbar dd").eq(index).addClass("selected");
		            $(".tpl-main").css({
		                "padding-left":"235px",
		                //"width":"963px",
		                //"width":"100%",
		                //"margin-left":"0px",
		            });
		        }
		    });
		},
		"bandCloseSidebarItem":function(){//绑定关闭边栏交互事件
			// 关闭左侧浮动
		    $(".close-icon").click(function(){
		        $(".toolbar").hide();
		        $(".tab-bar li").removeClass("selected");
		        $(".tpl-main").css({
		            "padding-left":"0px",
		            //"width":"100%",
		            //"margin-left":"-230px",
		        });
		    });
		},
		"bandSelectItemSize" : function(){//绑定选择布局规格交互事件
			//选择尺寸规格
			$(".layout-item span").live("click",function(){
				if(!$(this).hasClass("size-selected")){
					$(".layout-item span").removeClass("size-selected");
					$(this).addClass("size-selected");
					
					templateConfigSideBarWap.getModule($(this).text());
				}
			});
		},
		"bandBgColor" :function(){
			//绑定背景色交互事件
			var $bgColor = $("#attr-backgroundColor");
			templateConfigSideBarWap.bandJpicker($bgColor);//绑定取色器
			//绑定是否显示背景色交互事件
			$("#attr-showBackFlag").click(function(){
				if($(this).attr("checked")){
					$(this).val("1");
				}else{
					$(this).val("0");
				}
				//保存
				templateConfigSideBarWap.saveBgColor();
			});
			
		},
		"bandBgPic" : function(){
			//上传背景图回调函数
			var updateImgCallback = function(returnInfo) {
	    		/** 上传成功，隐藏上传组件，并显示该图片 */
	    		if (returnInfo && returnInfo.success == "ok") {
	    			if(returnInfo.resultMap && returnInfo.resultMap.vfsUrl && returnInfo.resultMap.vfsId){
	    				$("#background-pic-url").attr("src",returnInfo.resultMap.vfsUrl);
	    				$("#background-pic-url").attr("data-url",returnInfo.resultMap.vfsUrlPri);
		    			$("#attr-backgroundPic").val(returnInfo.resultMap.vfsId);
		    			//eDialog.alert(returnInfo.message);
		    			//保存背景图
		    			templateConfigSideBarWap.saveBgPic();
	    			}else{
	    				eDialog.alert("上传失败，请重试！");
	    			}
	    		} else {
	    			eDialog.alert(returnInfo.message);
	    		}
	    	};
	    	//绑定更换背景图交互事件
			$("#change-bg-img").live("change", function(o) {
				var path = $(this).val();
			    if(path==""){
			    	return false;
			    }else{
			    	templateConfigSideBarWap.uploadImage(this, path,updateImgCallback);
			    }
			});
			
			//绑定删除背景图交互事件
			$("#delete-bg-img").live("click", function(o){
				if($("#attr-backgroundPic").val()){
					eDialog.confirm("您确认删除背景图吗？", {
						buttons : [{
							caption : '确认',
							callback : function(){
								$("#background-pic-url").attr("src",$webroot+"images/pageConfig/no-pic.gif");
								$("#background-pic-url").attr("data-url","");
				    			$("#attr-backgroundPic").val("");
				    			//保存背景图
				    			templateConfigSideBarWap.saveBgPic();
							}
						},{
							caption : '取消',
							callback : $.noop
						}]
					});
				}
			});
			
			//绑定背景色显示方式交互事件
			$(".type-switch .backgroup-show-type").live("click", function(o){
				if(!$(this).hasClass("selected")){
					$(this).siblings().removeClass("selected");
					$(this).addClass("selected");
					
					$("#attr-backgroupShowType").val($(this).attr("data-show-type"));
					//保存背景图
	    			templateConfigSideBarWap.saveBgPic();
				}
			});
		},
		"bandJpicker":function($obj){//绑定颜色拾取器
			//设置图片地址
			$.fn.jPicker.defaults.images.clientPath=$webroot+'js-busi/pageConfig/public/jpicker-1.1.6/images/';
			$.fn.jPicker.defaults.localization.text={
				      title: '拖动鼠标选取颜色',
				      newColor: '新颜色',
				      currentColor: '当前颜色',
				      ok: '确认',
				      cancel: '取消'
				    };
			if($obj && $obj.jPicker){
				$obj.jPicker(
						{
							window:{
								expandable:true,
								title:'拖动鼠标选取颜色',
								position:  
								{  
									x: '540px', /* acceptable values "left", "center", "right", "screenCenter", or relative px value */  
									y: '0px' /* acceptable values "top", "bottom", "center", or relative px value */  
								}
							}
						},
						function(){//确定的回调函数
								if(!$obj.val()){
									$obj.val("ffffff");//设为白色
								}
								//保存
								templateConfigSideBarWap.saveBgColor();
						}
				);
			}
		},
		
		/**
		 * 模块事件
		 */
		"getModule":function(layoutItemType){//获取模块
			if(!templateConfigSideBarWap.isPositiveNum(layoutItemType)){
				layoutItemType = "";
			}
			var $moduleList = $(".module-list");
			var pageTypeId = $("#pageTypeId").val();
			var param = {
				"applyPageType"	: pageTypeId?pageTypeId:'',
				"applyItemSize" : layoutItemType?layoutItemType:''
			}
			$.eAjax({
				url : $webroot + "pageConfig/qryModular",
				data : param,
				success : function(returnInfo){
					if(returnInfo && returnInfo.msg == '1'){//查询成功
						if(returnInfo.modulars && returnInfo.modulars.length && returnInfo.modulars.length > 0){
							var html = template('arttemplate-modular-list', returnInfo);//使用arttemplate插件
							
							$moduleList.html(html);
							
							//绑定拖拽功能
							//对象doJsmartdrag 定义在 /js-busi/pageConfig/pageConfig/pageConfig-index.js
							if(doJsmartdrag && $("#reqType").val() != "view"){
								doJsmartdrag.bandJsmartdrag($(".module-list li"),{//拖拽参数
									target: '.phone-layout',
							        self: false,
							        dragHoverClass: 'dragBox',
									afterDrag : templateConfigSideBarWap.modularAfterDrag
								});
							}
						}else{
							$moduleList.html("<span>暂无模块，敬请期待！</span>");
						}
					}else{
						$moduleList.html("<span>查询模块异常！</span>");
					}
			    },
				error : function(e,xhr,opt){
					$moduleList.html("<span>查询模块错误！</span>");
				},
				exception : function(msg){
					$moduleList.html("<span>获取模块异常！</span>");
				}
			});
		},
		"modularAfterDrag":function (selected, $current, $target) {
			if($target==null || selected == false)return ;
			var default_layout_item=$('#default_layout_item');
			if(default_layout_item)
			default_layout_item.remove();
			var hiddenItemId ;
			var hiddenModularId ;
			var hiddenModularType ;
			var hiddenRowNo ;
			var moduleData = templateConfigSideBarWap.getData($current);//获取模块的属性数据
			var $lastOne=$target.prev('.phone-layout');
			var $nextOne=$target.next('.phone-layout');
			if($nextOne.length!=0 && moduleData.id==111){
				eDialog.alert("该模块只能放在最下方");
				return;
			}
			var last_modularId=$('#modularId',$lastOne).val();
			if(111==last_modularId ){
				eDialog.alert("上方模块只能放在最下方，请重新配置");
				return;
			}
			var menu = $('<div class="menu"><span class="mup"></span><span class="mdown"></span><span class="mdel"></span></div>');
			var phone_modular= $('<div class="phone-modular tpl-slider"><div class="modular-body">'+
					'<div class="nodata" style="height:90px;text-align:center;padding-top:30px;">'+
					'<span style="color: #aaa;font-size: 14px;">'+moduleData.modularName+'</span><br>'+
					'<span style="color:red;">该模块未配置数据，请点击配置数据！</span></div></div>');
			var layoutItem = {};
			var thisRowNo=1;
			if($lastOne.length>0){
				var lastRowNo=$('#rowNo',$lastOne).val();
				if(lastRowNo){
					thisRowNo=parseFloat(lastRowNo)+1;
				}
			}else{
			}
			layoutItem.rowNo=thisRowNo;
			hiddenRowNo=$('<input type="hidden" value="'+thisRowNo+'" id="rowNo"/>');
			layoutItem.templateId = $('#templateId').val();
			layoutItem.layoutId = $('#layoutId').val();
			layoutItem.modularId = moduleData.id;
			layoutItem.modularType = moduleData.modularType;
			layoutItem.itemSize = 0;
			layoutItem.itemNo = 1;
			hiddenModularId=$('<input type="hidden" value="'+layoutItem.modularId+'" id="modularId"/>');
			hiddenModularType=$('<input type="hidden" value="'+layoutItem.modularType+'" id="modularType"/>');
			//回调函数
			var successCallback = function(itemData){
				hiddenItemId=$('<input type="hidden" value="'+itemData.id+'" id="itemId"/>');
				$target.removeClass('dragBox').addClass('phone-layout');
				$target.append(hiddenItemId)
				.append(hiddenModularId)
				.append(hiddenModularType)
				.append(hiddenRowNo)
				.append(menu)
				.append(phone_modular);
				$('#pageDecorate').mCustomScrollbar('update');
//				//刷新拖拽功能作用对象
				if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
					doJsmartdrag.jsmartdragObj.freshTarget();
				}
				$('#pageDecorate').mCustomScrollbar('update');
			}
			var errorCallback = function(errorMsg){
				if(errorMsg){
					eDialog.alert(errorMsg);
				}else{
					eDialog.alert("更新布局项错误，请刷新页面！");
				}
			}
			//保存数据
			templateConfigSideBarWap.saveLayoutItem(layoutItem,successCallback,errorCallback);
			var param = {
				"templateLayoutItemList":[]
			};
			while(true){
				if($nextOne.length>0 && 'default_layout_item'!=$nextOne.attr("id")){
					var nextRowNo=$('#rowNo',$nextOne).val();
					var nextItemId=$('#itemId',$nextOne).val();
					var nextModularId=$('#modularId',$nextOne).val();
					var nextLayoutItem = {};
					nextRowNo=parseFloat(nextRowNo)+1;
					nextLayoutItem.rowNo=nextRowNo;
					$('#rowNo',$nextOne).val(nextRowNo);
					nextLayoutItem.id = nextItemId;
					nextLayoutItem.modularId = nextModularId;
					nextLayoutItem.itemSize = 0;
					nextLayoutItem.itemNo = 1;
					param.templateLayoutItemList.push(nextLayoutItem);
				}else{
					break;
				}
				$nextOne=$nextOne.next('.phone-layout');
			}
			var successCallback2 = function(itemData){
			}
			var errorCallback2 = function(errorMsg){
				if(errorMsg){
					eDialog.alert(errorMsg);
				}else{
					eDialog.alert("更新布局项错误，请刷新页面！");
				}
			}
			templateConfigSideBarWap.updateLayoutItemBatch(param,successCallback2,errorCallback2); 
			$('#pageDecorate').mCustomScrollbar('update');
		},
		
		
		
		"savePageAttrbute": function(options,callback){
			var param = {
					"pageId" : $("#pageId").val(),
					"id":$("#pageAttrPreId").val()
			};
			if(! param.id){//页面属性是新增
				options = ["backgroundColor","backgroundPic","showBackFlag","backgroupShowType","matchingColour"];
			}
			if(templateConfigSideBarWap.isEmptyList(options)){
				options = ["backgroundColor","backgroundPic","showBackFlag","backgroupShowType","matchingColour"];
			}
			
			$.each(options, function(i,option){    
				var attrVal = $("#attr-"+option).val();
				param[option] = attrVal?attrVal:"";
			});
			$.eAjax({
				url : $webroot + "pageConfig/savePageAttrbutePre",
				//url : $webroot + "templateLayout/saveLayout",
				data : param,
				success : function(returnInfo){
					if(returnInfo && returnInfo.resultFlag=="ok"){
						if(returnInfo.resultMsg) $("#pageAttrPreId").val(returnInfo.resultMsg);
						//运行回调函数
						if(callback && (typeof callback)=="function"){
							callback();
						}
					}else{
						eDialog.alert("保存信息异常！");
					}
					
				},
				error : function(e,xhr,opt){
					eDialog.alert("保存信息异常！");
				},
				exception : function(msg){
					eDialog.alert("保存信息异常！");
				}
			});
		},
		"saveBgColor" : function(){//保存背景颜色   包括背景色与是否显示背景色
			var options = ["backgroundColor","showBackFlag"];//保存页面哪个属性
			templateConfigSideBarWap.savePageAttrbute(options, templateConfigSideBarWap.showBgColor);
		},
		showBgColor : function(){//保存页面属性的回调函数
			if($("#attr-showBackFlag").val() == 1){
				$(".phone-content").css("background-color","#"+$("#attr-backgroundColor").val());
			}else{
				$(".phone-content").css("background-color","transparent");
			}
		},
		"saveBgPic" : function(){
			var options = ["backgroundPic","backgroupShowType"];//保存页面哪个属性
			templateConfigSideBarWap.savePageAttrbute(options, templateConfigSideBarWap.showBgPic);
		},
		showBgPic : function(){//保存页面属性的回调函数
			var repeatType = $("#attr-backgroupShowType").val();
			if(repeatType == "01"){
				repeatType = "repeat";
			}else if(repeatType == "02"){
				repeatType = "no-repeat";
			}else if(repeatType == "03"){
				repeatType = "repeat-y";
			}
			
			if($("#attr-backgroundPic").val()){
				$(".phone-content").css({
					                               "background-image":"url("+$("#background-pic-url").attr("data-url")+")",
					                               "background-repeat":repeatType
					                               });
			}else{
				$(".phone-content").css({
                    "background-image":"none",
                    "background-repeat":repeatType
                    });
			}
		},
		"saveLayoutItem":function(itemParam,successCallback,errorCallback){
			if(! $.isFunction(errorCallback)){
				errorCallback = $.noop;
			}
			if(! $.isFunction(successCallback)){
				successCallback = $.noop;
			}
			if(itemParam && itemParam.layoutId  && itemParam.itemNo && itemParam.rowNo){
				//异步更新
				$.eAjax({
					url : $webroot + "templateLayout/saveLayoutItem",
					data : itemParam,
					async: false,
					success : function(returnInfo){
						if(returnInfo && returnInfo.id){//成功
							successCallback(returnInfo);
						}else{
							errorCallback("新增布局项异常，请刷新页面！");
						}
				    },
					error : function(e,xhr,opt){
						errorCallback("新增布局项请求错误，请刷新页面！");
					},
					exception : function(msg){
						errorCallback("新增布局项请求异常，请刷新页面！");
					}
				});
			}else{
				errorCallback("参数错误，请刷新页面！");
			}
		},
		"updateLayoutItem":function($item,options,successCallback,errorCallback){
			if(! $.isFunction(errorCallback)){
				errorCallback = $.noop;
			}
			if(! $.isFunction(successCallback)){
				successCallback = $.noop;
			}
			
			if($item instanceof jQuery){
				//获取参数
				var param = null;
				if(layoutDesign.isEmptyList(options)){//没有指定选项则全部获取
					param = pageConfigSideBar.getData($item);
				}else{//获取指定选项的参数
					param={};
					$.each(options, function(i,option){    
						var val = pageConfigSideBar.getData($item,option);
						param[option] = val?val:"";
					});
					param.id = pageConfigSideBar.getData($item,"id");
				}
				if(param && param.id){
					//异步更新
					$.eAjax({
						url : $webroot + "templateLayout/updateLayoutItem",
						data : param,
						success : function(returnInfo){
							if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
								successCallback();
							}else{
								errorCallback("更新布局项异常，请刷新页面！");
							}
					    },
						error : function(e,xhr,opt){
							errorCallback("更新布局项请求错误，请刷新页面！");
						},
						exception : function(msg){
							errorCallback("更新布局项请求异常，请刷新页面！");
						}
					});
				}else{
					errorCallback("获取的布局项参数有误，请刷新页面！");
				}
			}else{
				errorCallback("请传递正确的参数！");
			}
		},
		"updateLayoutItemBatch":function(param,successCallback,errorCallback){
			if(! $.isFunction(errorCallback)){
				errorCallback = $.noop;
			}
			if(! $.isFunction(successCallback)){
				successCallback = $.noop;
			}
			
			if(param.templateLayoutItemList.length > 0){
				//异步更新
				$.eAjax({
					url : $webroot + "templateLayout/updateLayoutItemBatch",
					data : ebcUtils.serializeObject(param),
					success : function(returnInfo){
						if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
							successCallback();
						}else{
							errorCallback("布局排序失败,请刷新页面！");
						}
				    },
					error : function(e,xhr,opt){
						errorCallback("布局排序错误,请刷新页面！");
					},
					exception : function(msg){
						errorCallback("布局排序异常,请刷新页面！");
					}
				});
			}else{
				successCallback();
			}
		},
		
//--------------------------------工具方法-----------------------------------//
		/**
		 * 图片上传
		 * @param {} object  file对象
		 * @param {} path 本地文件路径
		 */
		"uploadImage" : function (object, path ,callback) {
			if(callback && (typeof callback) == "function"){
				var data = {
						'place_width' : 1200,
						'place_height' : 1200,
						'place_size' : 1024
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
			    	
			    	templateConfigSideBarWap.ajaxFileUpload(url, false, $(object).attr('id'), "POST", "json", callback,data);
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
		},
		"isPositiveNum" : function(s){//是否为正整数 
			if(s){
				var re = /^[0-9]*[1-9][0-9]*$/ ; 
				return re.test(s) ;
			}
			return false;
		},
		"isEmptyList" : function(obj){
			var isEmpty = true;
			if(Object.prototype.toString.call(obj) === '[object Array]' && obj.length > 0){
				isEmpty = false;
			}
			return isEmpty;
		},
		"unFormatStr":function(str){//去掉|
			if(!str){
				return "";
			}
			str = str.replace(/\|/gm,' ');
			return str;
		},
		"formatStr":function(str){//前后加|
			if(!str){
				return "";
			}
			var reg = /^\|/;
			if (!(reg.test(str))) {
				str = "|" + str;
			}
			reg = /\|$/;
			if (!(reg.test(str))) {
				str = str + "|";
			}
			return str;
		},
		"getData":function($obj,str){
			if($($obj).length > 0){
				if(str){
					str = String(str);
					str = templateConfigSideBarWap.jsAttrToHtmlAttr(str);
					return $obj.attr(str);
				}else{
					var result = {};
					var attributes = $obj[0].attributes;
					var reg = /^(data-)/i;
					if(attributes){
						$.each(attributes,function(i,attribute){
							var attrName = attribute["name"];
							var attrVal = attribute["value"];
							if(attrVal){
								if(reg.test(attrName)){
									attrName =  templateConfigSideBarWap.htmlAttrToJsAttr(attrName);
									result[attrName] = attrVal;
								}
							}
						});
					}
					return result;
				}
			}
		},
		"setData":function($obj,name,value){
			if(value == null){
				value = ""
			}
			if($($obj).length > 0){
				name = templateConfigSideBarWap.jsAttrToHtmlAttr(name);
				$obj.attr(name,value);
			}
		},
		"jsAttrToHtmlAttr":function(str){//jsAttr  转成  data-js-attr
			if(!str){
				return "";
			}else{
				str = String(str);
				var strUps = str.match(/[A-Z]/g);
				if(strUps){
					$.each(strUps,function(i,strUp){
						var strLower = strUp.toLowerCase();
						str = str.replace(strUp,"-"+strLower);
					});
				}
				return "data-" + str;
			}
		},
		"htmlAttrToJsAttr":function(str){//data-js-attr  转成  jsAttr
			if(!str){
				return "";
			}else{
				str = String(str);
				var reg = /^(data-)/i;
				if(reg.test(str)){
					str = str.substring(5,str.length);
				}
				var matStrs = str.match(/\-[a-z]/g);
				if(matStrs){
					$.each(matStrs,function(i,matStr){
						var strUp = matStr.toUpperCase();
						str=str.replace(matStr,strUp[1])
					});
				}
				return str;
			}
		}
		
};

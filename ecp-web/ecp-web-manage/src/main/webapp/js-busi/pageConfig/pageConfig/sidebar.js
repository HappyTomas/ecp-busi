$(function(){
	pageConfigSideBar.init();
});
var pageConfigSideBar = {
		"init" : function(){//初始化左侧栏
			pageConfigSideBar.bandSelectSidebarItem();
			pageConfigSideBar.bandCloseSidebarItem();
			pageConfigSideBar.getModule($(".layout-item span.size-selected").text());
			pageConfigSideBar.bandSelectItemSize();
			pageConfigSideBar.initPageAttrbute();
		},
		"initPageAttrbute":function(){//初始化页面属性交互事件
			//绑定背景色   （背景色，是否显示）
			pageConfigSideBar.bandBgColor();
			//绑定背景图    （ 更改    删除     展示方式）
			pageConfigSideBar.bandBgPic();
			//配色
			pageConfigSideBar.bandMatchingColour();
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
					
					pageConfigSideBar.getModule($(this).text());
				}
			});
		},
		"bandBgColor" :function(){
			//绑定背景色交互事件
			var $bgColor = $("#attr-backgroundColor");
			pageConfigSideBar.bandJpicker($bgColor);//绑定取色器
			//绑定是否显示背景色交互事件
			$("#attr-showBackFlag").click(function(){
				if($(this).attr("checked")){
					$(this).val("1");
				}else{
					$(this).val("0");
				}
				//保存
				pageConfigSideBar.saveBgColor();
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
		    			pageConfigSideBar.saveBgPic();
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
			    	pageConfigSideBar.uploadImage(this, path,updateImgCallback);
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
				    			pageConfigSideBar.saveBgPic();
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
	    			pageConfigSideBar.saveBgPic();
				}
			});
		},
		"bandMatchingColour":function(){
			//初始化样式
			var colorTheme = $("#attr-matchingColour").val();
			if(colorTheme){
				$("#"+colorTheme).find(".color-snip").addClass("selected");
			}
			//交互事件
			$("li",".color-style").live("click", function(o){
				
				if(!$(this).find(".color-snip").hasClass("selected")){
					$(".color-snip",".color-style").removeClass("selected");
					$(this).find(".color-snip").addClass("selected");
					var preCssName = $("#attr-matchingColour-css").val();
					$("#attr-matchingColour").val($(this).attr("id"));
					$("#attr-matchingColour-css").val($(this).attr("colorStyle"));
					//保存
					pageConfigSideBar.saveMatchingColour(preCssName);
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
									x: '150px', /* acceptable values "left", "center", "right", "screenCenter", or relative px value */  
									y: '0px' /* acceptable values "top", "bottom", "center", or relative px value */  
								}
							}
						},
						function(){//确定的回调函数
								if(!$obj.val()){
									$obj.val("ffffff");//设为白色
								}
								//保存
								pageConfigSideBar.saveBgColor();
						}
				);
			}
		},
		/**
		 * 模块事件
		 */
		"getModule":function(layoutItemType){//获取模块
			if(!pageConfigSideBar.isPositiveNum(layoutItemType)){
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
							if(doJsmartdrag){
								doJsmartdrag.bandJsmartdrag($(".module-list li"),{//拖拽参数
									target : '.target',
									targetHoverClass : 'check-emptyp target',
									afterDrag : pageConfigSideBar.modularAfterDrag
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
			if (selected && layoutDesign) {//layoutDesign定义在layoutDesign.js  存在即页面已经异步加载完毕
				var $layoutItem = $target;
				var moduleData =  pageConfigSideBar.getData($current);//获取模块的属性数据
				var layoutItemData = pageConfigSideBar.getData($layoutItem);//获取布局项的属性数据
				
				if(moduleData && layoutItemData && moduleData.applyType && layoutItemData.itemSize){
					moduleData.applyType = pageConfigSideBar.formatStr(moduleData.applyType);
					layoutItemData.itemSize = pageConfigSideBar.formatStr(layoutItemData.itemSize);
					
					if(moduleData.applyType.indexOf(layoutItemData.itemSize) >= 0 ){//模块拖入成功
						//新增布局项
						if($layoutItem.hasClass("blankp")){
							var lock = $layoutItem.parents(".item-list").attr("lock");//上锁标识
							var sorting = $layoutItem.parents(".item-list").attr("sorting");
							if(lock || sorting){
								layoutDesign.showWarn("操作过于频繁，请稍后操作！");
							}else{
								$layoutItem.parents(".item-list").attr("lock","1");
								$layoutItem.parents(".item-list").attr("sorting","1");
								//拼接布局项数据
								var itemParam = pageConfigSideBar.getData($layoutItem);
								itemParam.modularId = moduleData.id;
								itemParam.rowNo = +($layoutItem.prev(".item-admin").attr("data-row-no"));	
								if(!itemParam.rowNo){
									itemParam.rowNo = 1;
								}else{
									itemParam.rowNo = itemParam.rowNo + 1;;
								}
								itemParam.pageId = $("#pageId").val();
								
								
								//更新布局项样式
								$layoutItem.before($layoutItem.clone());
								$layoutItem.after($layoutItem.clone());
								
								pageConfigSideBar.setData($layoutItem,"modularId",moduleData.id);
								pageConfigSideBar.setData($layoutItem,"rowNo",itemParam.rowNo);
								$layoutItem.addClass('tb-module').addClass('item-admin').removeClass('emptyp').removeClass('blankp');
								$layoutItem.html("<span class='txt'>"+$current.find('.content').text()+"</span><span class='close'></span>");
								
								
								//回调函数
								var successCallback = function(itemData){
									//刷新拖拽功能作用对象
									if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
										doJsmartdrag.jsmartdragObj.freshTarget();
									}
									$layoutItem.attr("data-id",itemData.id);
									$layoutItem.parents(".item-list").attr("lock","");
								}
								var errorCallback = function(errorMsg){
									$layoutItem.parents(".item-list").attr("lock","");//解锁
									if(errorMsg){
										eDialog.alert(errorMsg);
									}else{
										eDialog.alert("更新布局项错误，请刷新页面！");
									}
								}
								
								//保存数据
								layoutDesign.saveLayoutItem(itemParam,successCallback,errorCallback);
								
								
								//回调函数
								var sortSuccessCallback = function(){
									$layoutItem.parents(".item-list").attr("sorting","");
								}
								var sortErrorCallback = function(errorMsg){
									$layoutItem.parents(".item-list").attr("sorting","");//解锁
									if(errorMsg){
										eDialog.alert(errorMsg);
									}else{
										eDialog.alert("更新布局项错误，请刷新页面！");
									}
								}
								//重排布局项
								layoutDesign.resortLayoutItem($layoutItem.parents(".item-list"),null,sortSuccessCallback,sortErrorCallback);
							}
							
							
						//更新布局项	
						}else if($layoutItem.hasClass("item-admin")){
							if(moduleData.id && moduleData.id != layoutItemData.modularId){//拖入新的模块
									
								var doUpdate = function(){//执行模块绑定函数
									var lock = $layoutItem.attr("drag-lock");//上锁标识
									if(lock){//锁定的处理
										layoutDesign.showWarn("操作过于频繁，请稍后操作！");
									}else{
										$layoutItem.attr("drag-lock","1");//上锁
										
										pageConfigSideBar.setData($layoutItem,"modularId",moduleData.id);
										
										//更新布局项样式
										var isOnlyOne = $layoutItem.parents(".item-list").attr("data-only-one");//只支持单维布局项
										if($layoutItem.hasClass("emptyp")){//空布局项则对上下两个布局项种子做激活处理
											$layoutItem.addClass('tb-module').removeClass('emptyp');
											if(!isOnlyOne){// 悬浮特殊处理  
												$layoutItem.next().addClass("target");
												$layoutItem.prev().addClass("target");
												//刷新拖拽功能作用对象
												if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
													doJsmartdrag.jsmartdragObj.freshTarget();
												}
											}
										}
										$layoutItem.html("<span class='txt'>" + $current.find('.content').text() + "</span><span class='close'></span>");
										
										
										//回调函数
										var successCallback = function(){
											$layoutItem.attr("drag-lock","");//解锁
										}
										var errorCallback = function(errorMsg){
											$layoutItem.attr("drag-lock","");//解锁
											if(errorMsg){
												eDialog.alert(errorMsg);
											}else{
												eDialog.alert("更新布局项错误，请刷新页面！");
											}
										}
										
										//更新修改了的布局项
										layoutDesign.updateLayoutItem($layoutItem,["id","modularId"],successCallback,errorCallback);
									}
								}
									
								//如果已经存在模块 则给出提醒	
								if(layoutItemData.modularId){
									eDialog.confirm("替换模块将使原来的模块参数丢失，确定继续？", {
										buttons : [{
											caption : '确认',
											callback : doUpdate
										},{
											caption : '取消',
											callback : function(){
											}
										}]
									});
								}else{
									doUpdate();
								}
									
									
							}
						}
						
					}else{
						if('|LXF|RXF|'.indexOf(layoutItemData.itemSize) >= 0 ){
							eDialog.alert("请选择适合悬浮的模块！");
						}else{
							eDialog.alert("请选择适合"+pageConfigSideBar.unFormatStr(layoutItemData.itemSize)+"(px) 尺寸的模块！");
						}
					}
				}	
			}
		},
		"savePageAttrbute": function(options,callback){
			var param = {
					"pageId" : $("#pageId").val(),
					"id":$("#pageAttrPreId").val()
			};
			if(! param.id){//页面属性是新增
				options = ["backgroundColor","backgroundPic","showBackFlag","backgroupShowType","matchingColour"];
			}
			if(pageConfigSideBar.isEmptyList(options)){
				options = ["backgroundColor","backgroundPic","showBackFlag","backgroupShowType","matchingColour"];
			}
			
			$.each(options, function(i,option){    
				var attrVal = $("#attr-"+option).val();
				param[option] = attrVal?attrVal:"";
			});
			$.eAjax({
				url : $webroot + "pageConfig/savePageAttrbutePre",
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
			pageConfigSideBar.savePageAttrbute(options, pageConfigSideBar.showBgColor);
		},
		showBgColor : function(){//保存页面属性的回调函数
			//eDialog.alert("执行改变（背景色，是否显示）回调函数");
			if($("#attr-showBackFlag").val() == 1){
				$(".page-edit","#tpl-content").css("background-color","#"+$("#attr-backgroundColor").val());
			}else{
				$(".page-edit","#tpl-content").css("background-color","transparent");
			}
		},
		"saveBgPic" : function(){
			var options = ["backgroundPic","backgroupShowType"];//保存页面哪个属性
			pageConfigSideBar.savePageAttrbute(options, pageConfigSideBar.showBgPic);
		},
		showBgPic : function(){//保存页面属性的回调函数
			//eDialog.alert("执行改变（背景图，展示方式）回调函数");
			var repeatType = $("#attr-backgroupShowType").val();
			if(repeatType == "01"){
				repeatType = "repeat";
			}else if(repeatType == "02"){
				repeatType = "no-repeat";
			}else if(repeatType == "03"){
				repeatType = "repeat-y";
			}
			
			if($("#attr-backgroundPic").val()){
				$(".page-edit","#tpl-content").css({
					                               "background-image":"url("+$("#background-pic-url").attr("data-url")+")",
					                               "background-repeat":repeatType
					                               });
			}else{
				$(".page-edit","#tpl-content").css({
                    "background-image":"none",
                    "background-repeat":repeatType
                    });
			}
		},
		"saveMatchingColour" : function(preCssName){//保存配色
			var saveAttrCallback = function(){//保存页面属性的回调函数
				//eDialog.alert("执行改变配色回调函数");
				//PageConfigtion.showPageEdit();
				if(preCssName){
					preCssName +=".css";
					PageConfigtion.removejscssfile($webroot+'css/pageConfig/skin/'+preCssName,"css");
				}
				var css_name = $("#attr-matchingColour-css").val();
				var css_id = +$("#attr-matchingColour").val();
				if(css_name && css_id){
					css_name +=".css";
					PageConfigtion.loadjscssfile($webroot+'css/pageConfig/skin/'+css_name,"css");
				}
			}
			var options = ["matchingColour"];//保存页面哪个属性
			pageConfigSideBar.savePageAttrbute(options, saveAttrCallback);
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
			if($obj instanceof jQuery && $obj[0]){
				if(str){
					str = String(str);
					str = pageConfigSideBar.jsAttrToHtmlAttr(str);
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
									attrName =  pageConfigSideBar.htmlAttrToJsAttr(attrName);
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
			if($obj instanceof jQuery){
				name = pageConfigSideBar.jsAttrToHtmlAttr(name);
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

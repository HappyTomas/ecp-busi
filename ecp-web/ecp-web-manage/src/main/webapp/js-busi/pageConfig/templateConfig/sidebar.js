$(function(){
	templateConfigSideBar.init();
});
var templateConfigSideBar = {
		"init" : function(){//初始化左侧栏
			templateConfigSideBar.bandCloseSidebarItem();
			templateConfigSideBar.getModule($(".layout-item span.size-selected").text());
			templateConfigSideBar.bandSelectItemSize();
		},
		"bandCloseSidebarItem":function(){//绑定关闭边栏交互事件
			// 关闭左侧浮动
		    $(".close-icon").click(function(){
		        $(".toolbar").hide();
		        $(".tab-bar li").removeClass("selected");
		        $(".tpl-main").css({
		            "padding-left":"0px",
		        });
		    });
		},
		"bandSelectItemSize" : function(){//绑定选择布局规格交互事件
			//选择尺寸规格
			$(".layout-item span").live("click",function(){
				if(!$(this).hasClass("size-selected")){
					$(".layout-item span").removeClass("size-selected");
					$(this).addClass("size-selected");
					
					templateConfigSideBar.getModule($(this).text());
				}
			});
		},
		/**
		 * 模块事件
		 */
		"getModule":function(layoutItemType){//获取模块
			if(!templateConfigSideBar.isPositiveNum(layoutItemType)){
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
							if(doJsmartdrag && $("#reqType").val() != "view"){//如果是查看状态 则不支持拖拽
								doJsmartdrag.bandJsmartdrag($(".module-list li"),{//拖拽参数
									target : '.target',
									targetHoverClass : 'check-emptyp target',
									afterDrag : templateConfigSideBar.modularAfterDrag
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
			if (selected && templateLayout) {//templateLayout定义在templateLayout.js  存在即页面已经异步加载完毕
				var $layoutItem = $target;
				var moduleData =  templateConfigSideBar.getData($current);//获取模块的属性数据
				var layoutItemData = templateConfigSideBar.getData($layoutItem);//获取布局项的属性数据
				
				if(moduleData && layoutItemData && moduleData.applyType && layoutItemData.itemSize){
					moduleData.applyType = templateConfigSideBar.formatStr(moduleData.applyType);
					layoutItemData.itemSize = templateConfigSideBar.formatStr(layoutItemData.itemSize);
					
					if(moduleData.applyType.indexOf(layoutItemData.itemSize) >= 0 ){//模块拖入成功
						//新增布局项
						if($layoutItem.hasClass("blankp")){
							var lock = $layoutItem.parents(".item-list").attr("lock");//上锁标识
							var sorting = $layoutItem.parents(".item-list").attr("sorting");
							if(lock || sorting){
								templateLayout.showWarn("操作过于频繁，请稍后操作！");
							}else{
								$layoutItem.parents(".item-list").attr("lock","1");
								$layoutItem.parents(".item-list").attr("sorting","1");
								//拼接布局项数据
								var itemParam = templateConfigSideBar.getData($layoutItem);
								itemParam.modularId = moduleData.id;
								itemParam.rowNo = +($layoutItem.prev(".item-admin").attr("data-row-no"));	
								if(!itemParam.rowNo){
									itemParam.rowNo = 1;
								}else{
									itemParam.rowNo = itemParam.rowNo + 1;;
								}
								itemParam.templateId = $("#templateId").val();
								
								
								//更新布局项样式
								$layoutItem.before($layoutItem.clone());
								$layoutItem.after($layoutItem.clone());
								
								templateConfigSideBar.setData($layoutItem,"modularId",moduleData.id);
								templateConfigSideBar.setData($layoutItem,"rowNo",itemParam.rowNo);
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
								templateLayout.saveLayoutItem(itemParam,successCallback,errorCallback);
								
								
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
								templateLayout.resortLayoutItem($layoutItem.parents(".item-list"),null,sortSuccessCallback,sortErrorCallback);
							}
							
							
						//更新布局项	
						}else if($layoutItem.hasClass("item-admin")){
							if(moduleData.id && moduleData.id != layoutItemData.modularId){//拖入新的模块
									
								var doUpdate = function(){//执行模块绑定函数
									var lock = $layoutItem.attr("drag-lock");//上锁标识
									if(lock){//锁定的处理
										templateLayout.showWarn("操作过于频繁，请稍后操作！");
									}else{
										$layoutItem.attr("drag-lock","1");//上锁
										
										templateConfigSideBar.setData($layoutItem,"modularId",moduleData.id);
										
										//更新布局项样式
										var isOnlyOne = $layoutItem.parents(".item-list").attr("data-only-one");//只支持单维布局项
										if($layoutItem.hasClass("emptyp")){//空布局项则对上下两个布局项种子做激活处理
											$layoutItem.addClass('tb-module').removeClass('emptyp');
											if(!isOnlyOne && $("#pageTypeId").val() != 2){//促销页特殊处理  悬浮特殊处理  
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
										templateLayout.updateLayoutItem($layoutItem,["id","modularId"],successCallback,errorCallback);
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
							eDialog.alert("请选择适合"+templateConfigSideBar.unFormatStr(layoutItemData.itemSize)+"(px) 尺寸的模块！");
						}
					}
				}	
			}
		},
		
//--------------------------------工具方法-----------------------------------//
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
					str = templateConfigSideBar.jsAttrToHtmlAttr(str);
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
									attrName =  templateConfigSideBar.htmlAttrToJsAttr(attrName);
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
				name = templateConfigSideBar.jsAttrToHtmlAttr(name);
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

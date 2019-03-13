/**
 * 2016.4.11  zhanbh   布局装修相关的js  
 */
var templateLayout = {
	"layoutAdminCount":0,//布局的个数
	"addDelLock":0,//删除与添加 不能同时进行
    "pubLock":0,
	"init":function(){
		templateLayout.layoutAdminCount = $(".layoutAdmin",".layoutAdmin-bd").length;
		templateLayout.resortLayout();//布局重新排序
		templateLayout.initLayoutAdminCss();//初始化布局宽度样式
		templateLayout.initLayoutTypeCss();//初始化布局类型宽度样式
		templateLayout.initLayoutDialog();//初始化布局类型弹出框事件
		templateLayout.bandLayoutAdmin();//初始化布局事件
		templateLayout.bandLayoutType();//初始化布局类型事件
	},
	"bandLayoutAdmin":function(){//绑定布局实体的交互事件
		templateLayout.bandAddLayoutAdmin();//初始化添加布局事件
		templateLayout.bandDelLayoutAdmin();//初始化删除布局事件
		templateLayout.bandEditLayoutAdmin();//初始化替换布局事件
		templateLayout.bandMoveLayoutAdmin();//初始化移动布局事件
		
		templateLayout.bandDelLayoutItem();//初始化删除布局项事件
		templateLayout.initLayoutItemSort();//初始化布局项排序    将错误的排序更正过来
	},
	/**
	 * 布局拖拽排序  start
	 */
	"bandMoveLayoutAdmin":function(){
		$("div.layoutAdmin-bd").dragsort({ 
			dragSelector: "a.move", 
			dragBetween: false, 
			dragEnd: templateLayout.resortLayout, 
			placeHolderTemplate: "<div class='layoutAdmin-wrap '><div class='placeholder'></div></div>"}
		);
	},
	//重新排序
	"resortLayout":function(){
		var $layoutAdminList = $(".layoutAdmin-bd .layoutAdmin");
		var param = {
				"templateLayoutList":[]
			};
			
		$layoutAdminList.each(function(i,el){
			var showOrder = $(el).attr("data-show-order");
			if(showOrder != (i+1)){
				$(el).attr("data-show-order",i+1);
				var delLayout ={
						"id":$(el).attr("data-id"),
						"showOrder":i+1
					}
				param.templateLayoutList.push(delLayout);
			}
		});
		
		if(param.templateLayoutList.length > 0){
			//异步更新
			$.eAjax({
				url : $webroot + "templateLayout/updateLayoutBatch",
				data : ebcUtils.serializeObject(param),
				success : function(returnInfo){
					if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
						
					}else{
						eDialog.alert("布局排序失败,请刷新页面！");
					}
			    },
				error : function(e,xhr,opt){
					eDialog.alert("布局排序错误,请刷新页面！");
				},
				exception : function(msg){
					eDialog.alert("布局排序异常,请刷新页面！");
				}
			});
		}
	},
	/**
	 * 布局拖拽排序  end
	 */
	
	/**
	 * 布局重置布局类型  start
	 */
	"bandEditLayoutAdmin":function(){
		$('.layoutAdmin .edit').die().live("click", function() {
			//隐藏悬浮布局
			$(".layout-type-float",".layout-type-wrap").hide();
			var $layoutAdmin = $(this).parents('.layoutAdmin');
			$layoutAdmin.attr("editing","yes");
			var $layoutTypes = $(".layout-type",".layout-type-tb");
			var layoutTypeId = $layoutAdmin.attr("data-layout-type-id");
			
			var callback = function(data){
				templateLayout.editLayoutDialogCallback($layoutAdmin,data);
			}
			if(layoutTypeId){
				$layoutTypes.removeClass("selected");
				$(".layout-type[data-layout-type-id="+layoutTypeId+"]",".layout-type-tb").addClass("selected");
				bDialog.open({
	                title : "布局管理",
	                width : 520,
	                height : 400,
	                callback:callback,
	                onHidden:function(){
	                	//将弹窗内容移出模态框
		        		$("#page").after($("#add-layout-dialog").attr("style",""));
	                }
	            },
	            $("#add-layout-dialog")
	            );
			}
		});
	},
	//重置布局弹出框回调函数
	"editLayoutDialogCallback":function($layoutAdmin,returnData){
		if(($layoutAdmin instanceof jQuery) && returnData &&returnData.results && returnData.results[0]){
			if(returnData.results[0].resultFlag === true && returnData.results[0].param.layoutTypeId != $layoutAdmin.attr("data-layout-type-id")){
				
				var successCallback = function(data){
					var html = template("layoutAdmin-template", data);//使用arttemplate插件
					$layoutAdmin.replaceWith(html);
					templateLayout.initLayoutAdminCss();
					//刷新拖拽功能作用对象
					if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
						doJsmartdrag.jsmartdragObj.freshTarget();
					}
				}
				
				var doEdit= function(){
					returnData.results[0].param.id = $layoutAdmin.attr("data-id");
					returnData.results[0].param.showOrder = $layoutAdmin.attr("data-show-order");
					
					templateLayout.updateLayoutAdmin(returnData.results[0].param,successCallback);	
				}
				
				var $hasModuleItem =$layoutAdmin.find(".tb-module");
				if($hasModuleItem.length > 0){
					eDialog.confirm("替换新的布局会使原来的模块丢失，确定继续？", {
						buttons : [{
							caption : '确认',
							callback : doEdit
						},{
							caption : '取消',
							callback : $.noop
						}]
					});
				}else{
					doEdit();
				}
				
			}else if(returnData.results[0].resultFlag === false){
				eDialog.alert("获取布局类型数据失败，请刷新页面！");
			}
		}
	},
	"updateLayoutAdmin":function(param,callback){//保存布局与布局项
		if(param && param.layoutTypeId){
			$.eAjax({
				url : $webroot + "templateLayout/updateLayout",
				data : param,
				success : function(returnInfo){
					if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
						if($.isFunction(callback)){
							callback(returnInfo);
						}
					}else{
						eDialog.alert("替换生成布局失败，请刷新页面！");
					}
			    },
				error : function(e,xhr,opt){
					eDialog.alert("替换布局错误，请刷新页面！");
				},
				exception : function(msg){
					eDialog.alert("生成布局异常，请刷新页面！");
				}
			});
		}
	},
	/**
	 * 布局重置布局类型  end
	 */
	
	
	
	//点击添加布局
	"bandAddLayoutAdmin":function(){
		//var $layoutTypes = $(".layout-type",".layout-type-tb");
		$('#addLayout').die().live("click",function(){
			//显示悬浮   如果布局有相应悬浮  则布局类型不显示对应类型
			var $layoutAdminBd = $(".layoutAdmin-bd","#tpl-content");
			var $layoutTypeFloatLeft = $(".layout-type-float.layout-left",".layout-type-wrap");
			if($layoutAdminBd.nextAll(".layoutAdmin-left").length > 0){
				$layoutTypeFloatLeft.hide();
			}else{
				$layoutTypeFloatLeft.show();
			}
			
			var $layoutTypeFloatRight = $(".layout-type-float.layout-right",".layout-type-wrap");
			if($layoutAdminBd.nextAll(".layoutAdmin-right").length > 0){
				$layoutTypeFloatRight.hide();
			}else{
				$layoutTypeFloatRight.show();
			}
			
			$(".layout-type,.layout-type-float",".layout-type-wrap").removeClass("selected");
			$(".layout-type",".layout-type-wrap").eq(0).addClass('selected');
			
			var $addLayoutDialog = $("#add-layout-dialog");
            bDialog.open({
                title : "布局管理",
                width : 520,
                height : 400,
                callback:templateLayout.addLayoutDialogCallback,
                onShow:function(){
                	$addLayoutDialog.remove();
                },
                onHidden:function(){
                	//将弹窗内容移出模态框
	        		$("#page").after($("#add-layout-dialog").attr("style",""));
                }
            },
            $addLayoutDialog
            );
		 });
	},
	"addLayoutDialogCallback":function(returnData){//添加布局弹出框回调函数
		if(returnData &&returnData.results && returnData.results[0]){
			if(returnData.results[0].resultFlag === true){
				if(templateLayout.addDelLock > 0 ){//锁定处理
					templateLayout.showWarn("操作过于频繁，请稍后操作！");
				}else{
					if(returnData.results[0].param.layoutShowType == "03" || returnData.results[0].param.layoutShowType == "04"){
						if($(".layoutAdmin-float[data-layout-type-id="+returnData.results[0].param.layoutTypeId+"]").length <= 0){//悬浮只能有一个
							templateLayout.addDelLock += 1;//加锁
							
							//回调函数
							var successCallback = function(data){
								templateLayout.addDelLock -= 1;//解锁
								data.layout.layoutShowType = returnData.results[0].param.layoutShowType;
								var html = template("layoutAdmin-template-float", data);//使用arttemplate插件
								$(".page-layoutAdmin-edit").append(html);
								//刷新拖拽功能作用对象
								if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
									doJsmartdrag.jsmartdragObj.freshTarget();
								}
							}
							var errorCallback = function(){
								templateLayout.addDelLock -= 1;//解锁
								eDialog.alert("生成布局错误,请刷新页面！");
							}
							
							templateLayout.saveLayoutAdmin(returnData.results[0].param,successCallback,errorCallback);
						}
					}else{//普通布局
						templateLayout.addDelLock += 1;//加锁
						returnData.results[0].param.showOrder = templateLayout.layoutAdminCount + 1;
						
						//回调函数
						var successCallback = function(data){
							templateLayout.addDelLock -= 1;//解锁
							templateLayout.layoutAdminCount += 1;
							
							var html = template("layoutAdmin-template", data);//使用arttemplate插件
							$(".layoutAdmin-bd").append(html);
							
							//如果页面只有一个布局则刷新排序事件
							if(templateLayout.layoutAdminCount == 1){
								templateLayout.bandMoveLayoutAdmin();
							}
							templateLayout.initLayoutAdminCss();
							//刷新拖拽功能作用对象
							if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
								doJsmartdrag.jsmartdragObj.freshTarget();
							}
						}
						var errorCallback = function(){
							templateLayout.addDelLock -= 1;//解锁
							eDialog.alert("生成布局错误,请刷新页面！");
						}
						
						templateLayout.saveLayoutAdmin(returnData.results[0].param,successCallback,errorCallback);
					}
				}
				
			}else if(returnData.results[0].resultFlag === false){
				eDialog.alert("获取布局类型数据失败，请刷新页面！");
			}
		}
	},
	"saveLayoutAdmin":function(param,successCallback,errorCallback){//保存布局与布局项
		if(! $.isFunction(errorCallback)){
			errorCallback = $.noop;
		}
		if(! $.isFunction(successCallback)){
			successCallback = $.noop;
		}
		
		if(param && param.layoutTypeId){
			$.eAjax({
				url : $webroot + "templateLayout/saveLayout",
				data : param,
				success : function(returnInfo){
					if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
						successCallback(returnInfo);
					}else{
						errorCallback();
					}
			    },
				error : function(e,xhr,opt){
					errorCallback();
				},
				exception : function(msg){
					errorCallback();
				}
			});
		}else{
			errorCallback();
		}
	},
	
	
	
	//布局删除操作
	"bandDelLayoutAdmin":function(){//布局实体的工具栏交互事件
		$('.page-layoutAdmin-edit .del').die().live("click", function() {
			if(templateLayout.addDelLock > 0 ){//锁定处理
				templateLayout.showWarn("操作过于频繁，请稍后操作！");
			}else{
				templateLayout.addDelLock += 1;//加锁
				var $layoutAdmin = $(this).parents('.layoutAdmin,.layoutAdmin-float');
				
				//回调函数
				var successCallback = function(){
					if(!$layoutAdmin.hasClass("layoutAdmin-float")){//非悬浮布局
						templateLayout.layoutAdminCount -= 1;//将布局个数减1，新增才不会出错
					}
					templateLayout.addDelLock -= 1;//解锁
					//刷新拖拽功能作用对象
					if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
						doJsmartdrag.jsmartdragObj.freshTarget();
					}
				}
				var errorCallback = function(){
					templateLayout.addDelLock -= 1;//解锁
					eDialog.alert("更新布局错误,请刷新页面！");
				}
				
				var doDel = function(){
					$layoutAdmin.hide(200);
					window.setTimeout(function() {
						$layoutAdmin.parent(".layoutAdmin-wrap").remove();
						$layoutAdmin.remove();
					}, 200);
					
					templateLayout.delLayoutAdmin($layoutAdmin,successCallback,errorCallback);
				}
				
				var $hasModuleItem =$layoutAdmin.find(".tb-module");
				if($hasModuleItem.length > 0){
					eDialog.confirm("删除布局会将布局内的模块一并删除，确定继续？", {
						buttons : [{
							caption : '确认',
							callback : doDel
						},{
							caption : '取消',
							callback : function(){
								templateLayout.addDelLock -= 1;//解锁
							}
						}]
					});
				}else{
					doDel();
				}
				
			}
		});
	},
	"delLayoutAdmin":function($layoutAdmin,successCallback,errorCallback){//删除布局并重排
		if(! $.isFunction(errorCallback)){
			errorCallback = $.noop;
		}
		if(! $.isFunction(successCallback)){
			successCallback = $.noop;
		}
		
		if($layoutAdmin instanceof jQuery ){
			var param = {
				"templateLayoutList":[]
			};
			var delLayout ={
				"id":$layoutAdmin.attr("data-id"),
				"status":2
			}
			if(delLayout.id){
				param.templateLayoutList.push(delLayout);
				//非悬浮布局   重新排序
				if(!$layoutAdmin.hasClass("layoutAdmin-float")){
					var beginCount = $layoutAdmin.attr("data-show-order");
					if(beginCount > 0){
						var $nextAll = $layoutAdmin.parent(".layoutAdmin-wrap").nextAll(".layoutAdmin-wrap");
						$nextAll.each(function(i,el){
							var $layout = $(el).find(".layoutAdmin");
							var layout = {
								"id":$layout.attr("data-id"),
								"showOrder":Number(beginCount) + i,
							}
							param.templateLayoutList.push(layout);
							$layout.attr("data-show-order",layout.showOrder);
						});
					}else{
						errorCallback();
						return false;
					}
				}
				
				//异步更新
				if(param && param.templateLayoutList.length > 0){
					$.eAjax({
						url : $webroot + "templateLayout/updateLayoutBatch",
						data : ebcUtils.serializeObject(param),
						success : function(returnInfo){
							if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
								successCallback();
							}else{
								errorCallback();
							}
					    },
						error : function(e,xhr,opt){
							errorCallback();
						},
						exception : function(msg){
							errorCallback();
						}
					});
				}
			}else{
				errorCallback();
			}
		}else{
			errorCallback();
		}
	},
	
	
	
//---------------------布局项相关--------------------------//
	"initLayoutItemSort":function(){
		var $layoutLists = $(".item-list",".layoutAdmin-bd");
		$layoutLists.each(function(i,el){
			var $this = $(this);
			$this.attr("sorting","1");
			//回调函数
			var successCallback = function(){
				$this.attr("sorting","");
			}
			var errorCallback = function(errorMsg){
				$this.attr("sorting","");//解锁
			}
			
			templateLayout.resortLayoutItem($this,null,successCallback,errorCallback);
		});
	},
	"saveLayoutItem":function(itemParam,successCallback,errorCallback){
		if(! $.isFunction(errorCallback)){
			errorCallback = $.noop;
		}
		if(! $.isFunction(successCallback)){
			successCallback = $.noop;
		}
		
		if(itemParam && itemParam.layoutId && itemParam.itemSize && itemParam.itemNo && itemParam.rowNo){
			//异步更新
			$.eAjax({
				url : $webroot + "templateLayout/saveLayoutItem",
				data : itemParam,
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
			if(templateLayout.isEmptyList(options)){//没有指定选项则全部获取
				param = templateConfigSideBar.getData($item);
			}else{//获取指定选项的参数
				param={};
				$.each(options, function(i,option){    
					var val = templateConfigSideBar.getData($item,option);
					param[option] = val?val:"";
				});
				param.id = templateConfigSideBar.getData($item,"id");
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
	"resortLayoutItem":function($itemList,param,successCallback,errorCallback){
		if(! $.isFunction(errorCallback)){
			errorCallback = $.noop;
		}
		if(! $.isFunction(successCallback)){
			successCallback = $.noop;
		}
		
		var $layoutItemList = $($itemList).find(".item-admin");
		
		if(!param || !param.templateLayoutItemList){
			 param = {
					"templateLayoutItemList":[]
				};
		}
		
			
		$layoutItemList.each(function(i,el){
			var rowNo = $(el).attr("data-row-no");
			if(rowNo != (i+1)){
				$(el).attr("data-row-no",i+1);
				var layoutItem ={
						"id":$(el).attr("data-id"),
						"rowNo":i+1
					}
				param.templateLayoutItemList.push(layoutItem);
			}
		});
		
		//保存数据
		if(param.templateLayoutItemList.length > 0){
			templateLayout.updateLayoutItemBatch(param,successCallback,errorCallback);
		}else{
			successCallback();
		}
		
	},
	"bandDelLayoutItem":function(){
		$(".close",".item-admin").die().live("click",function(){
			var lock = $(this).parents(".item-list").attr("lock");//上锁标识
			if(lock){
				templateLayout.showWarn("操作过于频繁，请稍后操作！");
			}else{
				$(this).parents(".item-list").attr("lock","1");
				
				var $thisItem = $(this).parents(".item-admin").eq(0);
				var $itemList = $thisItem.siblings(".item-admin");
				var $itemParent = $(this).parents(".item-list");
				
				//回调函数
				var successCallback = function(){
					$itemParent.attr("lock","");
				}
				var errorCallback = function(errorMsg){
					$itemParent.attr("lock","");//解锁
					if(errorMsg){
						eDialog.alert(errorMsg);
					}else{
						eDialog.alert("更新布局项错误，请刷新页面！");
					}
				}
				
				if($itemList.length > 0){//多个布局项则删除改布局
					var param = {
							"templateLayoutItemList":[]
						}
					var layoutItem ={
							"id":$thisItem.attr("data-id"),
							"status":2
						}
					param.templateLayoutItemList.push(layoutItem);
					
					$thisItem.removeClass("item-admin").hide(200);
					window.setTimeout(function() {
						$thisItem.next().remove();
						$thisItem.remove();
					}, 200);
					
					templateLayout.resortLayoutItem($(this).parents(".item-list"),param,successCallback,errorCallback);
				}else if($itemList.length == 0){//一个布局项 则更新布局项
					$thisItem.prev().removeClass("target").removeClass("jsmartdrag-target");
					$thisItem.next().removeClass("target").removeClass("jsmartdrag-target");
					//刷新拖拽功能作用对象
					if(doJsmartdrag && doJsmartdrag.jsmartdragObj){
						doJsmartdrag.jsmartdragObj.freshTarget();
					}
					$thisItem.removeClass("tb-module").addClass("emptyp").attr("data-modular-id","");
					$thisItem.html("<span>请拖入模块 </span>");
					//异步更新
					$.eAjax({
						url : $webroot + "templateLayout/removeItemModular",
						data : {
							itemId:$thisItem.attr("data-id")
						},
						success : function(returnInfo){
							if(returnInfo && returnInfo.resultFlag == 'ok'){//成功
								successCallback()
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
				}else{//出错
					errorCallback("布局项信息出错！请刷新页面！");
				}
			}
		});
	},
	
	
	//--------------------布局弹窗-------------------------//
	//初始化布局弹出框按钮事件
	"initLayoutDialog":function(){
		var $dialogDiv=$("#add-layout-dialog");
		
		//点击确定
		$dialogDiv.find(".save-layout-btn").live("click",function(){
			var $layoutType = $(".layout-type.selected,.layout-type-float.selected");
			var data = {};
			if($layoutType.length > 0){
				var param = $layoutType.data();
				if(param){
					param.templateId=$("#templateId").val();
					data={
						"param":param,
						"resultFlag": true
					}
				}else{
					data={
						"resultFlag": false
					}
				}
			}
			bDialog.closeCurrent(data);
		});
		
		//点击取消
		$dialogDiv.find(".cancel-btn").live("click",function(){
			bDialog.closeCurrent();
		});
		
	},
	//绑定布局类型的交互事件
	"bandLayoutType":function(){
		var $layoutTypes = $(".layout-type,.layout-type-float",".layout-type-wrap");
		$layoutTypes.live("click",function(){
			if(!$(this).hasClass("selected")){
				$(".layout-type,.layout-type-float",".layout-type-wrap").removeClass("selected");
				$(this).addClass("selected");
			}
		});
	},
	
	
	//--------------------布局动态初始化宽度----------------------//
	//初始化布局实体宽度样式
	"initLayoutAdminCss":function(){
		var $layoutAdmins = $(".layoutAdmin.hide",".layoutAdmin-bd");
		$layoutAdmins.each(function(){
			 var $this=$(this);
             var items=$('.item-list',$this);
             var numList=[];
             items.each(function(){
            	 numList.push(Number($.trim($(this).find(".item-admin").attr("data-item-size"))));
             });
             numList = templateLayout.numToTrueWidth(numList,5,$this.width());
             items.each(function(i,el){
                 $(this).outerWidth(numList[i] +"px");
             });
             $this.removeClass("hide");
		});
	},
	//初始化布局类型宽度样式
	"initLayoutTypeCss":function(){
		var $layoutTypes = $(".layout-type",".layout-type-tb");
		$layoutTypes.each(function(){
			 var $this=$(this);
             var items=$('.item',$this);
             var numList=[];
             items.each(function(){
            	 numList.push(Number($.trim($(this).text())));
             });
             numList = templateLayout.numToTrueWidth(numList,5,$this.width());
             items.each(function(i,el){
                 $(this).outerWidth(numList[i] +"px");
            });
		});
	},
	//将数字数组转化为实际宽度
	"numToTrueWidth":function(numList,blank,totalWidth){
		if(!blank){
			blank=0;
		}
		if(templateLayout.isEmptyList(numList) || numList.length == 1 || !totalWidth){
			return [totalWidth];
		}
		var blankPersent = blank*100*(numList.length - 1 )/totalWidth;
		var total = 0;
		var error = false;
		$.each(numList,function(i,val){
			if(!isNaN(val)){
				numList[i] =Number(val);
				total += numList[i];
			}else{
				eDialog.alert("未得到期望的数字：i="+i+",val="+val+"!");
				error = true;
				return;
			}
		});
		if(error){
			return false;
		}
		var persentList =[];
		var persentSum = 0;
		$.each(numList,function(i,val){
			if(total == 0){
				persentList[i]=0;
			}else{
				if(i != numList.length - 1 ){
					persentList[i] =  Math.round((((val * 100) / total)*(100 - blankPersent)/10000)*totalWidth); 
					persentSum += persentList[i];
				}else{
					persentList[i] = totalWidth - persentSum - (blank*(numList.length - 1 ));
				}
			}
		});
		return persentList;
	},
	
	
	
	
	//--------------------工具方法-------------------------//
	"showWarn":function(txt){
        var alertObj=$('.drag-alert'), outTime, inTime;
        if(alertObj.size()>0){
            alertObj.find('span').text(txt);
        }else{
             alertObj=$('<div class="drag-alert">'+
                    '<span>'+txt+'</span>'+
                    '</div>');
            $('.page-container').append(alertObj);
        }
        if(!inTime){
            inTime=window.setTimeout(function(){
                alertObj.css({
                    'top':20
                });
                window.clearTimeout(inTime);
            },20);
        }
        if(!outTime){
            outTime=window.setTimeout(function(){
                alertObj.css({
                    'top':-20
                });
                window.clearTimeout(outTime);
            },3000);
        }
    },
	"isEmptyList" : function(obj){
		var isEmpty = true;
		if(Object.prototype.toString.call(obj) === '[object Array]' && obj.length > 0){
			isEmpty = false;
		}
		return isEmpty;
	},
	"isPositiveNum" : function(s){//是否为正整数 
		if(s){
			var re = /^[0-9]*[1-9][0-9]*$/ ; 
			return re.test(s) ;
		}
		return false;
	}

}
$(function(){
	templateLayout.init();
});


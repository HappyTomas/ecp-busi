$(function(){
	var pageInfo_edit = {
			"id":$('#id').val()||"",
			"exParam" :{
				"mallskintomanage" :$("#mallskintomanage").val()||"",
				"searchParams" : $("#searchParams").val()||''
			},
			"disableEl":['siteId','platformType','shopId','pageTypeId'],//编辑状态下不能修改的字段
			init : function(){//初始化事件绑定
				if(pageInfo_edit.id){
					var disable = pageInfo_edit.disableEl;
					for(var i in disable){
						$("#"+disable[i]).attr("disabled","disabled");
					}
				}else{
					pageInfo_edit.showPageTypeList();
				}
				
				//初始化是否显示所属店铺
				pageInfo_edit.showShopDiv();
				
				//绑定选择平台类型
				$('#platformType').bind('change', function(){
					pageInfo_edit.showPageTypeList();
				});
				
				//页面类型改变
				$("#pageTypeId").live("change",function(){
					//改变店铺字段
					pageInfo_edit.showShopDiv();
				});
				//绑定保存按钮
				$('#btnFormSave').click(function(){
					if(!$("#detailInfoForm").valid()){
						return false;
					}
					pageInfo_edit.saveBtnEvent();
				});
				//绑定下一步按钮
				$('#btnFormNext').click(function(){ 
					if(!$("#detailInfoForm").valid()){
						return false;
					}
					//新增则进行数据判断
					if(!pageInfo_edit.id){
						pageInfo_edit.infoComfirm();
					}else{
						pageInfo_edit.goNext();
					}
				});
				//新增返回
				$('#btnReturn').click(function(){
					SearchObj.openPage({
						"url": $webroot+'pageInfo/grid',
						"params" :pageInfo_edit.exParam,
						"method" :"get"
					});
				});
				//初始化信息确认弹出框
				pageInfo_edit.initDialogEvent();
			},
			"saveBtnEvent":function(){
				//成功回调函数
				var doSuccess = function(returnInfo){
					if(returnInfo.resultFlag == 'ok'){//保存成功
						eDialog.success('保存成功！',{
							buttons:[{
								caption:"确定",
								callback:function(){
									SearchObj.openPage({
										"url": $webroot+'pageInfo/grid',
										"params" :pageInfo_edit.exParam,
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
				pageInfo_edit.saveFrom(doSuccess);
			},
			"goNext" : function(){
				//初始化成功函数  returnInfo为后台返回信息
				var doSuccess = function(returnInfo){
					if(returnInfo && returnInfo.resultFlag == 'ok'){//保存成功
						if(returnInfo.resultMsg){//成功的话resultMsg存页面id
							var params = pageInfo_edit.exParam;
							params.pageId = returnInfo.resultMsg;
							eDialog.confirm("数据保存成功，如需要应用模板覆盖页面，请进入模板应用界面！", {
								buttons : [{
									caption : '去应用模板',
									callback : function(){
										SearchObj.openPage({
											"url": $webroot+'pageInfo/toUseTemp',
											"params" :params,
											"method" :"post"
										});
									}
								},{
									caption : '下一步',
									callback : function(){
										SearchObj.openPage({
											"url": $webroot+'pageConfig/init?tabType=layout',
											"params" :params,
											"method" :"get"
										});
									}
								}]
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
				pageInfo_edit.saveFrom(doSuccess);
			},
			"initDialogEvent":function(){//初始化信息确认弹出框的按钮事件
				var $dialog = $("#info-confirm-dialog");
				//确认按钮
				$dialog.find(".btn-info").die("click").live("click",function(){
					bDialog && bDialog.closeCurrent &&  bDialog.closeCurrent("yes");
				});
				//取消按钮
				$dialog.find(".cancel-btn").die("click").live("click",function(){
					bDialog && bDialog.closeCurrent && bDialog.closeCurrent();
				});
			},
			"renewInfoDialog":function($dialog){//更新信息确认弹出框
				var $contain = $(".dialog-content",$dialog);
				$contain.empty();
				//数据展示模板
				var $itemTemp = $($("#info-confirm-temp").text());
				
				var disable = pageInfo_edit.disableEl;
				for(var i in disable){
					var $el = $("#"+disable[i]+":visible");
					var tabName = $el.attr("data-name");
					if(tabName){
						var $item = $itemTemp.clone();//获取模板副本
	            		$item.find(".name").text(tabName);
	            		$contain.append($item);
						if($el[0] && $el[0].tagName && $el[0].tagName.toLocaleLowerCase() == 'select'){
							$item.find(".value").text($el.find("option:selected").text());
						}else{
							$item.find(".value").text(data[$el.attr("data-name")] = $el.val());
						}
					}
				}
				
			},
			"infoComfirm":function(){//保存后就不能修改的信息的确定
				var $dialog = $("#info-confirm-dialog");
				pageInfo_edit.renewInfoDialog($dialog);
				
				bDialog.open({
	                title : "信息确认",
	                width : 480,
	                height : 280,
	                callback:function(data){
	                	if(data && data.results && data.results[0] == 'yes'){
	                		pageInfo_edit.goNext();
	                	}
	                }
	            },
	            $dialog
	            );
			},
			showPageTypeList : function(){//获取页面类型
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
						/*if(!returnInfo || returnInfo.length <=0){
							returnInfo =[{"id":'',pageTypeName:"--暂无数据--"}]; 
						}*/
						for(var info in returnInfo){
							var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].pageTypeName+"</option>";
							$("#pageTypeId").append(option);
						}
						pageInfo_edit.showShopDiv();
					}
				});
			},
			showShopDiv : function(){//是否显示所属店铺
				var pageTypeId = $('#pageTypeId').val();
				if(pageTypeId=="1" || pageTypeId=="4"){//店铺首页，店铺活动页
					$("#shopId").attr("name","shopId");//给店铺字段加name 才能取到值
					$("#shop_div").show();
				}else{
					$("#shopId").attr("name","");//去掉店铺字段的那么  使之不能取到值
					$("#shop_div").hide();
				}
			},
			saveFrom : function(doSuccess){//保存
				if(!$("#detailInfoForm").valid()){
					return false;
				}
				//初始化成功函数  returnInfo为后台返回信息
				if((typeof doSuccess) != "function" ){
					doSuccess = $.noop;
				}
				$.gridLoading({"message":"正在加载中...."});
				$.eAjax({
					url : $webroot + "pageInfo/save",
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
			}
			
		};
	pageInfo_edit.init();
});


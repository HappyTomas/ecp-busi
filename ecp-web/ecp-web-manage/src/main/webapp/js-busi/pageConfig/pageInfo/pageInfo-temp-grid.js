$(function(){
	var useTempGrid = {
			"exParam" :{
				"mallskintomanage" :$("#mallskintomanage").val(),
				"searchParams" : $("#searchParams").val()
			},
			"data":{
				"pageId":$("#pageId").val()||"",
				"siteId":$("#siteId").val()||"",
				"platformType":$("#platformType").val()||"",
				"shopId":$("#shopId").val()||"",
				"pageTypeId":$("#pageTypeId").val()||"",
			},
			"init":function(){
				$("input[type=hidden]").remove();
				//模板类型页签切换
				$("#template-type-tab li").live("click",function(){
					if(!$(this).hasClass("active")){
						$(this).siblings().removeClass("active");
						$(this).addClass("active");
						useTempGrid.getTempList($(this).attr("data-temp-type"));
					}
				});
				//初始化模板页签
				useTempGrid.initTempType();
				//返回按钮
				$(".go-back",".grid-menu").bind("click",function(){
					useTempGrid.goBack();
				});
				//下一步按钮
				$(".go-next",".grid-menu").bind("click",function(){
					useTempGrid.goTOPageEdit();
				});
				//模板使用按钮
				$(".to-use-temp").die("click").live("click",function(){
					var $this = $(this);
					var callback =function(){
						useTempGrid.changeTempOfPage($this);
					}
					var tempName = $this.closest("li").find(".temp-name").attr("title")||'';
					eDialog.confirm("确定使用"+(tempName?"模板「"+tempName+"」":"该模板")+"进行覆盖？", {
						buttons : [{
							caption : '立即覆盖',
							callback : callback
						},{
							caption : '取消',
							callback : $.noop
						}]
					});
				});
			},
			"changeTempOfPage":function($temp){
				//遮罩
				$.gridLoading({"message":"正在覆盖中...."});
				var param = {
					id:useTempGrid.data.pageId,
					templateId:+($($temp).attr("data-temp-i"))
				}
				//数据错误处理
				if(!param.id){
					eDialog.alert('页面ID丢失，请刷新页面！');
					$.gridUnLoading();//取消遮罩
					return false;
				}else if(!param.templateId){
					eDialog.alert('模板ID丢失，请刷新页面！');
					$.gridUnLoading();//取消遮罩
					return false;
				}
				
				var doSuccess=function(returnInfo){
					if(returnInfo){
						if(returnInfo.resultFlag == 'ok'){//成功
							eDialog.confirm("覆盖成功，已经生效！", {
								buttons : [{
									caption : '留在本页',
									callback : $.noop
								},{
									caption : '下一步',
									callback : useTempGrid.goTOPageEdit
								}]
							});
						}else{
							eDialog.error(returnInfo.resultMsg);
						}
					}else{
						eDialog.error('覆盖不成功，无返回错误信息！');
					}
					
					$.gridUnLoading();//取消遮罩
				}
				
				$.eAjax({
					url : $webroot + "pageInfo/changePageTemp",
					data : param,
					success : doSuccess,
					error : function(e,xhr,opt){
						eDialog.error("覆盖遇到错误!");
						$.gridUnLoading();//取消遮罩
					},
					exception : function(msg){
						eDialog.error("覆盖遇到异常!");
						$.gridUnLoading();//取消遮罩
					}
				});
			},
			"goBack":function(){
				var params = useTempGrid.exParam;
				params.id =useTempGrid.data.pageId;
				SearchObj.openPage({
					"url" : $webroot+'pageInfo/edit',
					"params" : params,
					"method" :"get"
				});
			},
			"goTOPageEdit":function(){
				var params = useTempGrid.exParam;
				params.pageId =useTempGrid.data.pageId;
				SearchObj.openPage({
					"url": $webroot+'pageConfig/init?tabType=layout',
					"params" :params,
					"method" :"get"
				});
			},
			"initTempType":function(){
				$.eAjax({
					url : $webroot + "pageInfo/getTempType",
					data : {},
					success : function(data){
						if(data && data.resultFlag == "ok"){
							var tempTypes = data.tempTypes;
							if(tempTypes && tempTypes.length > 0 ){
								var str = "";
								for(var i = 0;i<tempTypes.length;i++){
									str+="<li ";
									str+=" data-temp-type="+tempTypes[i].spCode+" ";
									str += "><a href='javascript:void(0);'>";
									str +=tempTypes[i].spValue;
									str += "</a></li>";
								}
								$("#template-type-tab").html(str);
								
								$("li","#template-type-tab").eq(0).trigger("click");
							}
						}
					},
					error : function(e,xhr,opt){
						useTempGrid.showTempError("发生错误，请刷新页面重试！");
					},
					exception : function(msg){
						useTempGrid.showTempError("发生异常，请刷新页面重试！");
					}
				});
				
			},
			"showTempError":function(msg){
				$('#template-lib-list').replaceWith($("<div class='data-empty'>"+msg+"</div>"));
			},
			"getTempList":function(templateType){
				if(!templateType){
					useTempGrid.showTempError("模板类型为空，请刷新页面重试！");
					return false;
				}
				var param = useTempGrid.data;
				param.templateType =templateType;
				if(!param.pageTypeId){
					useTempGrid.showTempError("页面类型为空，请刷新页面重试！");
					return false;
				}
				if(templateType == "02" && param.shopId){//非系统模板读取shopId
					useTempGrid.showTempError("店铺id为空，请刷新页面重试！");
					return false;
				}
				$.eAjax({
					url : $webroot + "templateLib/getTempList",
					data : param,
					async : true,
					type : "get",
					dataType : "html",
					success : function(data){
						$('#template-lib-list').html(data);
					},
					error : function(e,xhr,opt){
						useTempGrid.showTempError("发生错误，请刷新页面重试！");
					},
					exception : function(msg){
						useTempGrid.showTempError("发生异常，请刷新页面重试！");
					}
				});
			}
	}
	
	useTempGrid.init();
});
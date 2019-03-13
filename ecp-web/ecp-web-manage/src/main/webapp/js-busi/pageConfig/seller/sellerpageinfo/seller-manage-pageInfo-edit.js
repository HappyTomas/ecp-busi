$(function(){
	pageInfo_edit.init();
});

var pageInfo_edit = {
	init : function(){//初始化
		var id = $('#id').val();
		if(id){
			$('#pageTypeId').attr('disabled','disabled');
		}else{
			$('#pageTypeId').removeAttr('disabled');
		}
		
		//初始化是否显示所属店铺
		pageInfo_edit.showShopDiv();
		
		//绑定选择页面类型
		$('#pageTypeId').bind('change', function(){
			pageInfo_edit.showShopDiv();
			
			var $tempType=$("#template-type-tab li[class=active]");
			$tempType.removeClass("active");
			$tempType.trigger("click");
		});
		
		$("#shopId").live("change",function(){
			//改变模板
			var $tempType=$("#template-type-tab li[class=active]");
			$tempType.removeClass("active");
			$tempType.trigger("click");
		});
		
		//绑定保存按钮
		$('#btnFormSave').click(function(){ 
			//初始化成功函数  returnInfo为后台返回信息
			doSuccess = function(returnInfo){
				if(returnInfo.resultFlag == 'ok'){//保存成功
					eDialog.success('保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								var searchParams = $("#searchParams").val();
								SearchObj.openPage({
									"url": $webroot+'cmssellerpageinfo/grid',
									"params" :{"searchParams":(searchParams?searchParams:""),"mallskintomanage":$("#mallskintomanage").val()},
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
		});
		//绑定保存按钮
		$('#btnFormNext').click(function(){ 
			pageInfo_edit.goNext();
		});
		//新增返回
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'cmssellerpageinfo/grid',
				"params" :{"searchParams":(searchParams?searchParams:""),"mallskintomanage":$("#mallskintomanage").val()},
				"method" :"post"
			});
		});
		
		$("#template-type-tab li").live("click",function(){
			if(!$(this).hasClass("active")){
				$(this).siblings().removeClass("active");
				$(this).addClass("active");
				pageInfo_edit.getTempList($("#pageTypeId").val(),$(this).attr("data-temp-type"));
			}
		});
		
		//选择使用模板覆盖展示模板提供选择
		$("#isUseTemplate").live("change",function(){
			if($(this).attr("checked")){
				$("#template-lib-div").show();
			}else{
				$("#template-lib-div").hide();
			}
		});
		
		//初始化模板列表是否显示
		$("#isUseTemplate").trigger("change");
		
		//初始化模板页签
		pageInfo_edit.initTempType();
		
		//模板马上使用按钮
		$(".to-use-temp").live("click",function(){
			var $this = $(this);
			var pageId = $("#id").val();
			if(!pageId){//新增页面
				pageInfo_edit.toUseTemp($this);
			}else{//修改页面
				var changeFn = function(){
					pageInfo_edit.changeTempOfPage($this);
				}
				eDialog.confirm("选用模板会将原来的布局重置，且立即生效！", {
					buttons : [{
						caption : '立即覆盖',
						callback : changeFn
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
			}
		});
	},
	"changeTempOfPage":function($temp){
		//遮罩
		$.gridLoading({"message":"正在覆盖中...."});
		var param = {
			id:+($("#id").val()),
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
					eDialog.alert('覆盖成功，已经生效！');
					$("#template-lib-list").find(".using-temp").removeClass("using-temp").addClass("to-use-temp").text("使用覆盖");
					$temp.removeClass("to-use-temp").addClass("using-temp").text("当前使用");
				}else{
					eDialog.error(returnInfo.resultMsg);
				}
			}else{
				eDialog.error('覆盖不成功，无返回错误信息！');
			}
			
			$.gridUnLoading();//取消遮罩
		}
		
		$.eAjax({
			url : $webroot + "cmssellerpageinfo/changePageTemp",
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
	"toUseTemp":function($temp){
		$("#template-lib-list").find(".using-temp").removeClass("using-temp").addClass("to-use-temp").text("使用覆盖");
		$temp.removeClass("to-use-temp").addClass("using-temp").text("即将使用");
		$("#page-using-temp").html($temp.parents("li").html());
		$("#templateId").val($temp.attr("data-temp-i"));
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
			doSuccess = $noop;
		}
		$.gridLoading({"message":"正在加载中...."});
		$.eAjax({
			url : $webroot + "cmssellerpageinfo/save",
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
		//初始化成功函数  returnInfo为后台返回信息
		var doSuccess = function(returnInfo){
			if(returnInfo && returnInfo.resultFlag == 'ok'){//保存成功
				if(returnInfo.resultMsg){//成功的话resultMsg存页面id
					SearchObj.openPage({
						"url": $webroot+'pageConfig/init?tabType=layout',
						"params" :{"pageId":returnInfo.resultMsg,"mallskintomanage":$("#mallskintomanage").val()},
						"method" :"post"
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
	changeSite : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'cmssellerpageinfo/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#templateId").html("");
				$("#templateId").append("<option value= ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].templateName+"</option>";
					$("#templateId").append(option);
				}
			}
		});
	},
	"initTempType":function(){
		$.eAjax({
			url : $webroot + "cmssellerpageinfo/getTempType",
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
			},
			exception : function(msg){
			}
		});
		
	},
	"getTempList":function(pageTypeId,templateType){
		if(pageTypeId && templateType){
			$.eAjax({
				url : $webroot + "templateLib/getTempList",
				data : {pageTypeId:pageTypeId,templateType:templateType,templateId:$("#templateId").val()},
				async : true,
				type : "post",
				dataType : "html",
				success : function(data){
					$('#template-lib-list').html(data);
				},
				error : function(e,xhr,opt){
				},
				exception : function(msg){
				}
			});
		}
	}
};


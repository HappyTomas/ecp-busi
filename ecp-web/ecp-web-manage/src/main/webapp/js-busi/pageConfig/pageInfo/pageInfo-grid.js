$(function(){
	pageInfo.init();
});

var pageInfo = {
	"init" : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		var params = ebcForm.formParams($("#searchForm"));
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'params' : params,
	        "sAjaxSource": $webroot + 'pageInfo/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"30px","bSortable":true},
	        	{ "mData": "pageName", "sTitle":"页面名称","sWidth":"120px","bSortable":false},	
				{ "mData": "siteName", "sTitle":"所属站点","bVisible":true,"sWidth":"120px","bSortable":false},
	        	{ "mData": "platformTypeZH", "sTitle":"平台类型","sWidth":"80px","bSortable":false},	
				{ "mData": "pageTypeZH", "sTitle":"页面类型","sWidth":"80px","bVisible":true,"bSortable":false},
				{ "mData": "siteUrl", "sTitle":"页面地址","bVisible":true,"sWidth":"160px","bSortable":false,"mRender": function(data,type,row){
					if(row.platformType == "03" && row.status == "1"){//WAP
						return "<a href='javascript:void(0)'  onclick='pageInfo.showQrcode(\""+row.qrcodePic+"\",\""+row.qrcodePicUrl+"\")'>查看</a>";
//						return "<div class='lookEwm'><span>查看</span>" +
//								"<div class='cont'>" +
//								"<div class='tit'>链接地址：<a href='' target='_blank'>http://192.168.1.102:19080/imageServer/image/57bbb95M</a></div>" +
//								"<div class='ewm-wrap'>" +
//								"<span>请用手机扫描二维码 </span>" +
//								"<img class='ewm' src='http://192.168.1.102:19080/imageServer/image/57bbb952e4b0d68f50cd7bb7.jpg'/>" +
//								"</div>" +
//                                "</div>" +
//							    "</div>";
					}else if(row.platformType == "01" && row.status == "1"){
						return  row.siteUrl;
					}else{
						return "--";
					}
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				//{ "mData": "shopName", "sTitle":"所属店铺","sWidth":"100px","bSortable":false},
				{ "mData": "statusZH", "sTitle":"状态","sWidth":"60px","bVisible":true,"bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				/*{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},*/
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"140px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="";
						if(row.platformType != "03"){
							preViewOption+="<a href='javascript:void(0)' onclick='pageInfo.preview("+data+","+row.platformType+")'>预览</a> |";
						}else{
							preViewOption+="<a href='javascript:void(0)' onclick='pageInfo.preview("+data+","+row.platformType+",\""+row.viewQrcodePic+"\",\""+row.viewQrcodePicUrl+"\")'>预览</a> |";
						}
						if(row.status == "1"){//已发布
							return "<a href='javascript:void(0)' onclick='pageInfo.decorate("+data+")'>编辑</a> | "+preViewOption+" <a href='javascript:void(0)' onclick='pageInfo.changeStatus("+data+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return "<a href='javascript:void(0)' onclick='pageInfo.decorate("+data+")'>编辑</a> | "+preViewOption+"" +
									" <a href='javascript:void(0)' onclick='pageInfo.doRelease("+data+","+row.pageTypeId+","+row.shopId+","+row.siteId+","+row.platformType+")'>发布</a>" +
									" | <a href='javascript:void(0)' onclick='pageInfo.changeStatus("+data+",2)'>使失效</a>";
						}else{//已失效
							return "";
						}
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//modifyBiz();
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			if(!$("#searchForm").valid()) return false;
			$.gridLoading({"message":"正在加载中...."});
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
			$.gridUnLoading();
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			eNav.setSubPageText('新增页面');
 			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams,
				"mallskintomanage" : $("#mallskintomanage").val()
			};
			SearchObj.openPage({
				"url" : $webroot+'pageInfo/add',
				"params" : params,
				"method" :"post"
			});
		});
		//站点来匹配模板
		$('#siteId').change(function(){
			pageInfo.changeSite();
		});
		//隐藏错误
		$('#btnShowError').click(function(){
			$('div.formValidateMessages').slideToggle('fast');
		});
	},
	"showQrcode" : function(qrcodePic,qrcodePicUrl){//预览
		$('#page_pre_dialog_a').html(qrcodePicUrl);
		$('#page_pre_dialog_a').attr("href",qrcodePicUrl);
		$('#page_pre_dialog_img').attr("src",qrcodePic);
		var $pagePreDialog = $("#page_pre_dialog");
		$pagePreDialog.show();
		$pagePreDialog.removeClass("hide");
		$('.cancel',$pagePreDialog).live("click",function(){
			$pagePreDialog.hide();
			$pagePreDialog.addClass("hide");
			bDialog.closeCurrent();
		});
		bDialog.open({
			title : "发布成功",
			width : 600,
			height : 380,
			callback:function () {
				$pagePreDialog.hide();
			},
			onShow:function(){
				$pagePreDialog.hide();
			},
			onHidden:function(){
				$pagePreDialog.attr("style","");
				$("#page").after($pagePreDialog);
			}
		},
		$pagePreDialog
		);
	},
	"preview" : function(id,platformType,qrcodePic,qrcodePicUrl){//预览
		if(platformType == '03'){//WAP
			//wap
			$('#dataGridTable').gridReload();
			$('#page_preview_dialog_a').html(qrcodePicUrl);
			$('#page_preview_dialog_a').attr("href",qrcodePicUrl);
			$('#page_preview_dialog_img').attr("src",qrcodePic);
			var $pagePreviewDialog = $("#page_preview_dialog");
			$pagePreviewDialog.show();
			$pagePreviewDialog.removeClass("hide");
			$('.save',$pagePreviewDialog).live("click",function(){
				if($pagePreviewDialog.hasClass("hide")){
					return;
				}else{
					bDialog.closeCurrent();
					$pagePreviewDialog.hide();
					$pagePreviewDialog.addClass("hide");
					var url = $webroot+'page-pre/init?pageId=' + id;
					windowOpenUrl(url,"open","_blank");
				}
			});
			$('.cancel',$pagePreviewDialog).live("click",function(){
				$pagePreviewDialog.hide();
				$pagePreviewDialog.addClass("hide");
				bDialog.closeCurrent();
			});
			bDialog.open({
				title : "页面预览",
				width : 600,
				height : 380,
				callback:function () {
					$pagePreviewDialog.hide();
				},
				onShow:function(){
					$pagePreviewDialog.hide();
				},
				onHidden:function(){
					$pagePreviewDialog.attr("style","");
					$("#page").after($pagePreviewDialog);
				}
			},
			$pagePreviewDialog
			);
		}else{
			var url = $webroot+'page-pre/init?pageId='+id;
			windowOpenUrl(url,"open","_blank");
		}
	},
	"decorate" : function(id){ //编辑
		eNav.setSubPageText('编辑页面');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams,
				"mallskintomanage" : $("#mallskintomanage").val()
		}
		SearchObj.openPage({
			"url" : $webroot+'pageInfo/edit',
			"params" : params,
			"method" :"post"
		});
	},
	"changeSite" : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'pageInfo/changeSite',
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
	doRelease : function(id,pageTypeId,shopId,siteId,platformType){ //发布
		if(1==pageTypeId){//店铺首页
			if(shopId){
				var hasOtherRelease=false;
				$.eAjax({
					url : $webroot + "pageInfo/hasOtherRelease",
					type : "POST",
					async: false,
					data : {
						"id":id,
						"shopId":shopId,
						"pageTypeId":pageTypeId,
						"siteId":siteId
					},
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
				if(hasOtherRelease){//存在已发布的其他页面
					eDialog.confirm("存在其他已发布的页面，是否替换？", {
						buttons : [{
							caption : '确认',
							callback : function(){
								pageInfo.changeStatus(id,1);
							}
						},{
							caption : '取消',
							callback : $.noop
						}]
					});
				}else{
					pageInfo.changeStatus(id,1);
				}
			}else{//店铺id不存在
				pageInfo.changeStatus(id,1);
			}
		}else if(50==pageTypeId){//移动端首页
			var hasOtherRelease=false;
			$.eAjax({
				url : $webroot + "pageInfo/hasOtherRelease",
				type : "POST",
				async: false,
				data : {
					"id":id,
					"platformType":platformType,
					"pageTypeId":pageTypeId,
					"siteId":siteId
				},
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
			if(hasOtherRelease){//存在已发布的其他页面
				eDialog.confirm("存在其他已发布的页面，是否替换？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							pageInfo.changeStatus(id,1,platformType);
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
			}else{
				pageInfo.changeStatus(id,1,platformType);
			}
		}else{
			pageInfo.changeStatus(id,1);
		}
	},
	changeStatus : function(id,status,platformType){ //生效、失效
		var param = {
				'id':id,
				'status':status
		};
		var dochange = function(){
			$.eAjax({
				url : $webroot + "pageInfo/changestatus",
				type : "POST",
				data : param,
				datatype:'json',
				beforeSend:function(){
					$.gridLoading({"message":"正在加载中...."});
				},
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						if(platformType== 3){//wap
							$('#dataGridTable').gridReload();
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
									var url = $webroot+'page-pub/init?pc=true&pageId=' + id;
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
								height : 380,
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
						}else{
							var text = "";
							if(status == "0"){
								text = "撤销成功！";
							}else if(status == "1"){
								text = "发布成功！";
							}else if(status == "2"){
								text = "失效成功！";
							}else{
								text = "未知操作！";
							}
							eDialog.success(text); 
							$('#dataGridTable').gridReload();
						}
					}else if(returnInfo.resultMsg){
						eDialog.error(returnInfo.resultMsg);
					}
				},error : function(e,xhr,opt){
					eDialog.error("出现异常!");
					$.gridUnLoading();
				},complete:function(){
					$.gridUnLoading();
				}
			});
		}
		if(status == "2"){
			eDialog.confirm("您确认将该记录失效吗？", {
				buttons : [{
					caption : '确认',
					callback : dochange
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else{
			dochange();
		}
	},
	changeStatusBatch : function(status){ //生效、失效 
		var ids = $('#dataGridTable').getCheckIds();
		if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一条记录进行操作！');
			return ;
		}
		var param = {
				'ids':ids.join(","),
				'status':status
		};
		$.eAjax({
			url : $webroot + "pageInfo/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "生效成功！";
					if(status == "1"){
						text = "生效成功！";
					}else{
						text = "失效成功！";
					}
					eDialog.success(text); 
					$('#dataGridTable').gridReload();
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
				$.gridUnLoading();
			},complete:function(){
				$.gridUnLoading();
			}
		});
	}
};

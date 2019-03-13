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
	        "sAjaxSource": $webroot + 'cmssellerpageinfo/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
	        	{ "mData": "pageName", "sTitle":"页面名称","sWidth":"160px","bSortable":false},	
				{ "mData": "siteName", "sTitle":"所属站点","bVisible":true,"sWidth":"120px","bSortable":false},
	        	{ "mData": "platformTypeZH", "sTitle":"平台类型","sWidth":"80px","bSortable":false},	
				{ "mData": "pageTypeZH", "sTitle":"页面类型","sWidth":"140px","bVisible":true,"bSortable":false},
				//{ "mData": "siteUrl", "sTitle":"路径","bVisible":true,"bSortable":false},
				{ "mData": "shopName", "sTitle":"所属店铺","sWidth":"100px","bSortable":false},
				{ "mData": "statusZH", "sTitle":"状态","sWidth":"100px","bVisible":true,"bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				/*{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},*/
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="<a href='javascript:void(0)' onclick='pageInfo.preview("+data+")'>预览</a>";
						if(row.status == "1"){//已发布
							return "<a href='javascript:void(0)' onclick='pageInfo.decorate("+data+")'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='pageInfo.changeStatus("+data+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return "<a href='javascript:void(0)' onclick='pageInfo.decorate("+data+")'>编辑</a> | "+preViewOption+" | <a href='javascript:void(0)' onclick='pageInfo.doRelease("+data+","+row.pageTypeId+","+row.shopId+")'>发布</a> | <a href='javascript:void(0)' onclick='pageInfo.changeStatus("+data+",2)'>使失效</a>";
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
			var shopId = $('#shopId').val();
			ebcForm.resetForm('#searchForm');
			$('#shopId').val(shopId);
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
				"url" : $webroot+'cmssellerpageinfo/add',
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
	"preview" : function(id){//预览
		var url = $webroot+'page-pre/init?pageId='+id;
		windowOpenUrl(url,"open","_blank");
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
			"url" : $webroot+'cmssellerpageinfo/edit',
			"params" : params,
			"method" :"post"
		});
	},
	"changeSite" : function(){//站点来匹配模板
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
	doRelease : function(id,pageTypeId,shopId){ //发布
		if(1!=pageTypeId){//非店铺首页
			pageInfo.changeStatus(id,1);
		}else{
			if(shopId){
				var hasOtherRelease=false;
				$.eAjax({
					url : $webroot + "cmssellerpageinfo/hasOtherRelease",
					type : "POST",
					async: false,
					data : {"id":id,"shopId":shopId},
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
				if(hasOtherRelease){//存在已发布的其他 首页
					//存在其他已发布的店铺首页，是否替换
					eDialog.confirm("存在其他已发布的店铺首页，是否替换？", {
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
		}
	},
	changeStatus : function(id,status){ //生效、失效
		var param = {
				'id':id,
				'status':status
		};
		var dochange = function(){
			$.eAjax({
				url : $webroot + "cmssellerpageinfo/changestatus",
				type : "POST",
				data : param,
				datatype:'json',
				beforeSend:function(){
					$.gridLoading({"message":"正在加载中...."});
				},
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
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
			url : $webroot + "cmssellerpageinfo/changestatus",
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

$(function(){
	templateLib.init();
});

var templateLib = {
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
	        "sAjaxSource": $webroot + 'templateLib/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
	        	{ "mData": "templateName", "sTitle":"模板名称","bSortable":false},	
				{ "mData": "showPic", "sTitle":"模板缩略图","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
					if(row.showPic!=null && row.showPic.length>0){
	        			return "<img src='"+data+"' width='120' height='50'>";
	        		}else{
	        			return "暂无图片";
	        		}
				}},
				{ "mData": "siteName", "sTitle":"所属站点","bVisible":true,"sWidth":"120px","bSortable":false},
				{ "mData": "platformTypeZH", "sTitle":"平台类型","sWidth":"100px","bSortable":false},	
				{ "mData": "pageTypeZH", "sTitle":"页面类型","sWidth":"120px","bSortable":false},
				{ "mData": "templateTypeZH", "sTitle":"模板类型","sWidth":"100px","bSortable":false},	
				//{ "mData": "isDefTemplateZH", "sTitle":"是否默认模板","sWidth":"120px","bVisible":true,"bSortable":false},
				{ "mData": "statusZH", "sTitle":"状态","sWidth":"80px","bVisible":true,"bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				/*{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},*/
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="<a href='javascript:void(0)' onclick='templateLib.decorate("+data+",\"view\")'>查看</a>";
						if(row.status == "1"){//已发布
							return preViewOption+" | <a href='javascript:void(0)' onclick='templateLib.changeStatus("+data+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return preViewOption+" | <a href='javascript:void(0)' onclick='templateLib.decorate("+data+",\"edit\")'>编辑</a> | <a href='javascript:void(0)' onclick='templateLib.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='templateLib.changeStatus("+data+",2)'>使失效</a>";
						}else{//已失效
							return preViewOption;
						}
						/*if(row.status == "1"){//已发布
							return "<a href='javascript:void(0)' onclick='templateLib.changeStatus("+data+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return "<a href='javascript:void(0)' onclick='templateLib.decorate("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='templateLib.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='templateLib.changeStatus("+data+",2)'>使失效</a>";
						}else{//已失效
							return "";
						}*/
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
			eNav.setSubPageText('新增模板');
 			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'templateLib/add',
				"params" : params,
				"method" :"post"
			});
		});
		//站点来匹配模板
		$('#siteId').change(function(){
			templateLib.changeSite();
		});
		//隐藏错误
		$('#btnShowError').click(function(){
			$('div.formValidateMessages').slideToggle('fast');
		});
	},
	"preview" : function(id){//查看
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'templateLib/view',
			"params" : params,
			"method" :"post"
		});
	},
	"decorate" : function(id,reqType){ //编辑
		if('view'==reqType){
			eNav.setSubPageText('查看模板');
		}else{
			eNav.setSubPageText('编辑模板');
		}
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams,
				"reqType":reqType
		}
		SearchObj.openPage({
			"url" : $webroot+'templateLib/edit',
			"params" : params,
			"method" :"post"
		});
	},
	"changeSite" : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'templateLib/changeSite',
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
	changeStatus : function(id,status){ //生效、失效
		var param = {
				'id':id,
				'status':status
		};
		var dochange = function(){
			$.eAjax({
				url : $webroot + "templateLib/changestatus",
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
			url : $webroot + "templateLib/changestatus",
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

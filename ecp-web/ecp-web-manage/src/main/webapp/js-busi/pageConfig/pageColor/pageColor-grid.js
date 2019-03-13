$(function(){
	pageColor.init();
});

var pageColor = {
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
	        "sAjaxSource": $webroot + 'pageColor/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "colorName", "sTitle":"配色名称","sWidth":"150px","bSortable":false},	
				{ "mData": "showPic", "sTitle":"配色缩略图","sWidth":"150px","bSortable":false,"mRender": function(data,type,row){
	        		if(row.showPic!=null && row.showPic.length>0){
	        			return "<img src='"+data+"' width='50' height='50'>";
	        		}else{
	        			return "暂无图片";
	        		}
				}},
				{ "mData": "colorStyleZH", "sTitle":"配色样式","bVisible":true,"bSortable":false},
				{ "mData": "statusZH", "sTitle":"状态","sWidth":"100px","bVisible":true,"bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{ "mData": "updateTime", "sTitle":"修改时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						var preViewOption="<a href='javascript:void(0)' onclick='pageColor.preview("+data+")'>查看</a>";
						if(row.status == "1"){//已发布
							return preViewOption+" | <a href='javascript:void(0)' onclick='pageColor.changeStatus("+data+",0)'>撤销</a>";
						}else if(row.status == "0"){//有效
							return preViewOption+" | <a href='javascript:void(0)' onclick='pageColor.decorate("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='pageColor.changeStatus("+data+",1)'>发布</a> | <a href='javascript:void(0)' onclick='pageColor.changeStatus("+data+",2)'>使失效</a>";
						}else{//已失效
							return preViewOption;
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
			eNav.setSubPageText('新增配色');
 			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'pageColor/add',
				"params" : params,
				"method" :"post"
			});
		});
		//站点来匹配模板
		$('#siteId').change(function(){
			pageColor.changeSite();
		});
		//隐藏错误
		$('#btnShowError').click(function(){
			$('div.formValidateMessages').slideToggle('fast');
		});
	},
	"preview" : function(id){//预览
		eNav.setSubPageText('查看配色');
//		var url = $webroot+'pageColor/init?pageId='+id;
//		windowOpenUrl(url,"open","_blank");
		//var url = $webroot+'site/view?id='+id;
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'pageColor/view',
			"params" : params,
			"method" :"post"
		});
	},
	"decorate" : function(id){ //编辑
		eNav.setSubPageText('编辑配色');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'pageColor/edit',
			"params" : params,
			"method" :"post"
		});
	},
	"changeSite" : function(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'pageColor/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnColor){
				$("#templateId").html("");
				$("#templateId").append("<option value= ''>--请选择--</option>");
				for(var info in returnColor){
					var option = "<option value ="+"\""+returnColor[info].id+"\""+">"+returnColor[info].templateName+"</option>";
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
				url : $webroot + "pageColor/changestatus",
				type : "POST",
				data : param,
				datatype:'json',
				beforeSend:function(){
					$.gridLoading({"message":"正在加载中...."});
				},
				success : function(returnColor) {
					if(returnColor.resultFlag=='ok'){
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
					}else if(returnColor.resultMsg){
						eDialog.error(returnColor.resultMsg);
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
			url : $webroot + "pageColor/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			success : function(returnColor) {
				if(returnColor.resultFlag=='ok'){
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

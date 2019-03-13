$(function(){
	component.init();
});

var component = {
	init : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		
		//初始化列表
		var params = ebcForm.formParams($("#searchForm"));
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'component/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "componentName", "sTitle":"组件名称","sWidth":"200px","bSortable":false},	
				{ "mData": "componentClassZH", "sTitle":"组件分类","bVisible":true,"sWidth":"120px","bSortable":false},	
				{ "mData": "showPic", "sTitle":"组件缩略图","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
					if(row.showPic!=null && row.showPic.length>0){
	        			return "<img src='"+data+"' width='120' height='50'>";
	        		}else{
	        			return "暂无图片";
	        		}
				}},
				{ "mData": "componentMethod", "sTitle":"调用方法","sWidth":"200px","bSortable":false},
				//{ "mData": "componentUrl", "sTitle":"组件JS路径","bSortable":false},	
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{
					"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						if(row.status == "0"){//无效
							return "<a href='javascript:void(0)' onclick='component.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='component.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='component.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='component.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return "<a href='javascript:void(0)' onclick='component.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='component.changeStatus("+data+",\"0\")'>撤销</a>";
						}else{//使失效
							return "<a href='javascript:void(0)' onclick='component.showByID("+data+")'>查看</a>";
						}
					}
				}
	        ],
	        "eDbClick" : function(){
//	        	modifyBiz();
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
			eNav.setSubPageText('新增组件');
			var componentClass = $("#componentClass").val();
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"componentClass":componentClass,
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'component/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			component.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			component.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			component.changeStatusBatch("0");
		});
		//隐藏错误
		$('#btnShowError').click(function(){
			$('div.formValidateMessages').slideToggle('fast');
		});
	},
	
	edit : function(id){ //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑组件');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'component/edit',
			"params" : params,
			"method" :"post"
		});
	},
	
	deleteByID : function(id){   //使失效
		var param = {
				'ids':id
		};
		eDialog.confirm("您确认使失效该记录吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : $webroot + 'component/delete',
						type : "POST",
						data : param,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('使失效成功！'); 
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
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	},
	deleteBatch : function(){   //使失效、批量使失效
		var ids = $('#dataGridTable').getCheckIds();
		if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return ;
		}
		var param = {
				'ids':ids.join(",")
		};
		eDialog.confirm("您确认使失效该记录吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : $webroot + 'component/delete',
						type : "POST",
						data : param,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('使失效成功！'); 
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
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	},
	changeStatus : function(id,status){ //生效、失效
		var param = {
				'ids':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "component/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "1"){
						text = "发布成功！";
					}else{
						text = "撤销成功！";
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
			url : $webroot + "component/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "1"){
						text = "发布成功！";
					}else{
						text = "撤销成功！";
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
	},
	//查看详情
	showByID : function (id) {
		eNav.setSubPageText('查看组件');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'component/view',
			"params" : params,
			"method" :"post"
		});
	}
};

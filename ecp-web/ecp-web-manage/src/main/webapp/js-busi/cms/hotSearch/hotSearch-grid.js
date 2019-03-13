$(function(){
	hotSearch.init();
});

var hotSearch = {
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
	        "sAjaxSource": $webroot + 'hotSearch/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"id","bVisible":false,"sWidth":"50px","bSortable":true},
				{ "mData": "hotSearchName", "sTitle":"热门搜索词","sWidth":"160px","bSortable":false},
					
				/*{ "mData": "siteId", "sTitle":"所属站点","bVisible":false,"sWidth":"100px","bSortable":false},*/
				{ "mData": "siteName", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
//				{ "mData": "templateId", "sTitle":"所属模板","bVisible":false,"sWidth":"100px","bSortable":false},
//				{ "mData": "templateName", "sTitle":"所属模板","bVisible":true,"sWidth":"100px","bSortable":false},
//				{ "mData": "placeId","bVisible":false, "sTitle":"内容位置","sWidth":"100px","bSortable":false},
//				{ "mData": "placeName","bVisible":true, "sTitle":"内容位置","sWidth":"100px","bSortable":false},
//				
				/*{ "mData": "status", "bVisible":false,"sTitle":"状态","sWidth":"100px","bSortable":false},*/
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"100px","bSortable":false},
				
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"140px","bSortable":false,"sClass": "left",
					"mRender": function(data,type,row){
						//alert(data+"---"+type+"---"+row);
						if(row.status == "0"){//无效
							return "<a href='javascript:void(0)' onclick='hotSearch.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='hotSearch.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='hotSearch.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='hotSearch.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return "<a href='javascript:void(0)' onclick='hotSearch.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='hotSearch.changeStatus("+data+",\"0\")'>撤消</a>";
						}else{//使失效
							return "<a href='javascript:void(0)' onclick='hotSearch.showByID("+data+")'>查看</a>";
						}
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//edit();
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
			eNav.setSubPageText('新增搜索词');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'hotSearch/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定编辑按钮
		$('#btn_code_edit').click(function(){
			hotSearch.edit();
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			hotSearch.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			hotSearch.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			hotSearch.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			hotSearch.deleteBatch();
		});
	},

	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑搜索词');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'hotSearch/edit',
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
						url : $webroot + 'hotSearch/delete',
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
								$.gridUnLoading();
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
	deleteBatch : function(){   //批量使失效
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
						url : $webroot + 'hotSearch/delete',
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
			url : $webroot + "hotSearch/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "0"){
						text = "撤销成功！";
					}else{
						text = "发布成功！";
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
	changeStatusBatch : function(status){ //批量生效、失效 
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
			url : $webroot + "hotSearch/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "发布成功！";
					if(status == "0"){
						text = "撤销成功！";
					}else{
						text = "发布成功！";
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
		eNav.setSubPageText('查看搜索词');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'hotSearch/view',
			"params" : params,
			"method" :"post"
		});
	}
};

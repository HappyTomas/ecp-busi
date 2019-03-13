$(function(){
	recommend.init();
});

var recommend = {
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
	        "sAjaxSource": $webroot + 'recommend/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"id","bVisible":false,"sWidth":"80px","bSortable":true},
				/*{ "mData": "recommendType", "bVisible":false,"sTitle":"推荐类型","sWidth":"100px","bSortable":false},*/
				{ "mData": "recommendTypeZH", "bVisible":true,"sTitle":"推荐类型","sWidth":"100px","bSortable":false},
				{ "mData": "authorName","sTitle":"作者名称","sWidth":"100px","bSortable":false},
				{ "mData": "authorImageUrl", "sTitle":"作者图片","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					return "<img src='"+data+"' width='120' height='50'>";
				}},
				/*{ "mData": "recommendGdsId", "bVisible":false,"sTitle":"推荐商品名称","sWidth":"220px","bSortable":false},*/
				{ "mData": "recommendGdsName", "bVisible":true,"sTitle":"推荐商品名称","sWidth":"220px","bSortable":false},
				/*{ "mData": "status", "bVisible":false,"sTitle":"状态","sWidth":"80px","bSortable":false},*/
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{
					"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
					"mRender": function(data,type,row){
						//alert(data+"---"+type+"---"+row+" ");					
						//return "<a href='javascript:void(0)'  onclick='site.edit("+data+")'>编辑</a>";
						if(row.status == "0"){//无效
							return "<a href='javascript:void(0)' onclick='recommend.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='recommend.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='recommend.edit("+data+")'>编辑</a>" +
							"| <a href='javascript:void(0)' onclick='recommend.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return "<a href='javascript:void(0)' onclick='recommend.showByID("+data+")'>查看</a> | <a href='javascript:void(0)' onclick='recommend.changeStatus("+data+",\"0\")'>撤销</a>";
						}else{
							return "<a href='javascript:void(0)' onclick='recommend.showByID("+data+")'>查看</a>";
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
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		//绑定添加按钮
		$('#btn_code_add').click(function(){
			eNav.setSubPageText('新增推荐');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'recommend/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定编辑按钮
		$('#btn_code_edit').click(function(){
			recommend.edit();
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			recommend.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			recommend.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			recommend.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			recommend.deleteBatch();
		});
	},
	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑推荐');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'recommend/edit',
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
						url : $webroot + 'recommend/delete',
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
						url : $webroot + 'recommend/delete',
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
		$.gridLoading({"message":"正在加载中...."});
		var param = {
				'ids':id,
				'status':status
		};
		$.eAjax({
			url : $webroot + "recommend/changestatus",
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
			url : $webroot + "recommend/changestatus",
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在加载中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					var text = "生效成功！";
					if(status == "0"){
						text = "失效成功！";
					}else{
						text = "生效成功！";
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
		eNav.setSubPageText('查看推荐');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
				"id":id,
				"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'recommend/view',
			"params" : params,
			"method" :"post"
		});
	}
};

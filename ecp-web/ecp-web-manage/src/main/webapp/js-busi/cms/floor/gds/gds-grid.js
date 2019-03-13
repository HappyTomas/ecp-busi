$(function(){
	gds.init();
});

var gds = {
	init : function(){//初始化
		//初始化查询条件，回填值
		SearchObj.initSearchParams({paramsId:"searchParams",formId:"searchForm"});
		
		//初始化列表，由于站点，模板，内容位置特殊，进行重置数据处理
		var params = ebcForm.formParams($("#searchForm"));
		for(var index in params){
			if(params.hasOwnProperty(index) && params[index]){
				var name = params[index].name;
				if("siteId"==name || "templateId"==name || "placeId"==name){
					params[index] = ({'name':name,'value':$("#"+name).attr('attrId')});
				}
			}
		}
		
		var floorId = $("#floorId").val();
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'pCheck' : "multi",
	        'params' : params,
	        "sAjaxSource": $webroot + 'floorgds/gridlist?floorId='+floorId,
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"id","bVisible":false,"sWidth":"50px","bSortable":true},
				{ "mData": "gdsName", "sTitle":"商品名称","bSortable":false,"mRender": function(data,type,row){
					return SearchObj.HTMLEncode(data);
				}},
				{ "mData": "isProm", "sTitle":"选择促销商品","bSortable":false,"mRender": function(data,type,row){
					if(!data){
						return "否";
					}else if(data == "1"){
						return "是";
					}else if(data == "0"){
						return "否";
					}
				}},
				{ "mData": "gdsId", "sTitle":"商品ID","bVisible":false,"bSortable":false},
				{ "mData": "gdsImgUrl", "sTitle":"图片","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					return "<img src='"+data+"' width='120' height='50'>";
				}},
				{ "mData": "floorName", "sTitle":"所属楼层","sWidth":"120px","bSortable":false},
				{ "mData": "tabName", "sTitle":"所属页签","sWidth":"120px","bSortable":false},
				{ "mData": "sortNo", "sTitle":"排序","sWidth":"80px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","bSortable":false,"sClass": "left",
					"mRender": function(data,type,row){
						//alert(data+"---"+type+"---"+row);
						var optionview=" <a href='javascript:void(0)' onclick='gds.viewFloorGds("+row.id+")'>查看</a>";
						if(row.status == "0"){//无效
							return optionview+" | <a href='javascript:void(0)' onclick='gds.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='gds.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='gds.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return optionview+" | <a href='javascript:void(0)' onclick='gds.changeStatus("+data+",\"0\")'>撤消</a>";
						}else{//使失效
							return optionview+"";
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
			eNav.setSubPageText('新增楼层商品');
			var tabId = $("#tabId").val();
			var floorId = $("#floorId").val();
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var floorSearchParams = $("#floorSearchParams").val();
			var params = {
				"floorId":floorId,
				"tabId":tabId,
				"searchParams":searchParams,
				"floorSearchParams":floorSearchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floorgds/add',
				"params" : params,
				"method" :"post"
			});
		});
		
		//绑定批量添加按钮
		$('#btn_batch_add').click(function(){
			eNav.setSubPageText('批量新增楼层商品');
			var tabId = $("#tabId").val();
			var floorId = $("#floorId").val();
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var floorSearchParams = $("#floorSearchParams").val();
			var params = {
				"floorId":floorId,
				"tabId":tabId,
				"searchParams":searchParams,
				"floorSearchParams":floorSearchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floorgds/batchadd',
				"params" : params,
				"method" :"post"
			});
		});
		//返回楼层
		$('#btn_code_return').click(function(){
			var searchParams = $("#floorSearchParams").val();
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floor/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定编辑按钮
		$('#btn_code_edit').click(function(){
			gds.edit();
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			gds.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			gds.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			gds.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			gds.deleteBatch();
		});
	},
	
	viewFloorGds : function(id){
		eNav.setSubPageText('查看楼层商品');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"id":id,
			"searchParams":searchParams,
			"floorSearchParams":floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorgds/view',
			"params" : params,
			"method" :"post"
		});
	},
	
	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑楼层商品');
		var floorId = $("#floorId").val();
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"id":id,
			"floorId":floorId,
			"searchParams":searchParams,
			"floorSearchParams":floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorgds/edit',
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
						url : $webroot + 'floorgds/delete',
						type : "POST",
						data : param,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('失效成功！'); 
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
						url : $webroot + 'floorgds/delete',
						type : "POST",
						data : param,
						datatype:'json',
						beforeSend:function(){
							$.gridLoading({"message":"正在加载中...."});
						},
						success : function(returnInfo) {
							if(returnInfo.resultFlag=='ok'){
								eDialog.success('失效成功！'); 
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
			url : $webroot + "floorgds/changestatus",
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
					$.gridUnLoading();
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
			url : $webroot + "floorgds/changestatus",
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
	}
};

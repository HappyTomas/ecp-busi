$(function(){
	floorTab.init();
});

var floorTab = {
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
	        "sAjaxSource": $webroot + 'floortab/gridlist?floorId='+floorId,
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "tabName", "sTitle":"页签名称","bSortable":false},
				{ "mData": "floorName", "sTitle":"所属楼层","sWidth":"100px","bSortable":false},
				{ "mData": "isLinkZH","bVisible":true, "sTitle":"是否链接","sWidth":"100px","bSortable":false},
				{ "mData": "linkUrl", "sTitle":"链接地址","sWidth":"100px","bSortable":false},
				{ "mData": "catgCodeZH", "sTitle":"关联商品分类","sWidth":"100px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{ "mData": "sortNo", "sTitle":"排序","sWidth":"80px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","bSortable":false,"sClass": "left",
					"mRender": function(data,type,row){
						var optionview=" <a href='javascript:void(0)' onclick='floorTab.viewFloorTab("+row.id+")'>查看</a>";
						if(row.status == "0"){//无效
							return optionview+" | <a href='javascript:void(0)' onclick='floorTab.changeStatus("+data+",\"1\")'>发布</a> | <a href='javascript:void(0)' onclick='floorTab.edit("+data+")'>编辑</a> | <a href='javascript:void(0)' onclick='floorTab.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return optionview+" | <a href='javascript:void(0)' onclick='floorTab.changeStatus("+data+",\"0\")'>撤销</a>";
						}else{//使失效
							return "";
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
			eNav.setSubPageText('新增楼层页签');
			var floorId = $("#floorId").val();
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var floorSearchParams = $("#floorSearchParams").val();
			var params = {
				"floorId":floorId,
				"searchParams":searchParams,
				"floorSearchParams":floorSearchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floortab/add',
				"params" : params,
				"method" :"post"
			});
		});
		//返回楼层
		$('#btn_code_return').click(function(){
			var floorSearchParams = $("#floorSearchParams").val();
			var params = {
				"searchParams":floorSearchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floor/grid',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			floorTab.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			floorTab.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			floorTab.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			floorTab.deleteBatch();
		});
	},
	viewFloorTab : function(id){
		eNav.setSubPageText('查看楼层页签');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"id":id,
			"searchParams":searchParams,
			"floorSearchParams":floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floortab/view',
			"params" : params,
			"method" :"post"
		});
	},
	
	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑楼层页签');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"id":id,
			"searchParams":searchParams,
			"floorSearchParams":floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floortab/edit',
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
						url : $webroot + 'floortab/delete',
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
						url : $webroot + 'floortab/delete',
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
			url : $webroot + "floortab/changestatus",
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
			url : $webroot + "floortab/changestatus",
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

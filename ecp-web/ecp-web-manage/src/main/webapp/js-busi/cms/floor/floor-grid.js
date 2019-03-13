$(function(){
	floor.init();
});

var floor = {
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
		
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
//	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        'pCheck' : "single",
	        'params' : params,
	        "sAjaxSource": $webroot + 'floor/gridlist',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
				{ "mData": "floorName", "sTitle":"楼层名称","bSortable":false},
				{ "mData": "siteName", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "templateName", "sTitle":"所属模板","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "placeName", "sTitle":"内容位置","sWidth":"100px","bSortable":false},
				{ "mData": "dataSourceZH", "sTitle":"数据来源","sWidth":"100px","bSortable":false},
				{ "mData": "countTypeZH", "sTitle":"统计类型","sWidth":"100px","bSortable":false},
				{ "mData": "catgCodeZH", "sTitle":"关联商品分类","sWidth":"100px","bSortable":false},
				{ "mData": "sortNo", "sTitle":"排序","sWidth":"60px","bSortable":false},
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"70px","bSortable":false},
				{ "mData": "createTime", "sTitle":"创建时间","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"220px","bSortable":false,"sClass": "left",
					"mRender": function(data,type,row){
						//alert(data+"---"+type+"---"+row);
						var optionview=" <a href='javascript:void(0)' onclick='floor.viewFloor("+row.id+")'>查看</a>";
						if(row.status == "0"){//无效
							return optionview+"| <a href='javascript:void(0)' onclick='floor.changeStatus("+data+",\"1\")'>发布</a> " +
									" | <a href='javascript:void(0)' onclick='floor.edit("+data+")'>编辑</a>" +
									" | <a href='javascript:void(0)' onclick='floor.deleteByID("+data+")'>使失效</a>";
						}else if(row.status == "1"){//有效
							return optionview+" | <a href='javascript:void(0)' onclick='floor.changeStatus("+data+",\"0\")'>撤销</a>";
						}else{//使失效
							return ""+optionview;
						}
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//floor.edit();
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
			eNav.setSubPageText('新增楼层');
			var searchParams = SearchObj.getFormParam($("#searchForm"));
			var params = {
				"searchParams":searchParams
			}
			SearchObj.openPage({
				"url" : $webroot+'floor/add',
				"params" : params,
				"method" :"post"
			});
		});
		//绑定页签配置按钮
		$('#btn_tab_mgr').click(function(){
			floor.tabMgr();
		});
		//绑定广告配置按钮
		$('#btn_ad_mgr').click(function(){
			floor.adMgr();
		});
		//绑定标签配置按钮
		$('#btn_label_mgr').click(function(){
			floor.labelMgr();
		});
		//绑定商品配置按钮
		$('#btn_gds_mgr').click(function(){
			floor.gdsMgr();
		});
		//绑定优惠券配置按钮
		$('#btn_coupon_mgr').click(function(){
			floor.couponMgr();
		});
		//绑定属性配置按钮
		$('#btn_attr_mgr').click(function(){
			floor.attrMgr();
		});
		//绑定编辑按钮
		$('#btn_code_edit').click(function(){
			floor.edit();
		});
		//绑定使失效按钮
		$('#btn_code_del').click(function(){
			floor.deleteBatch();
		});
		//绑定生效按钮
		$('#btn_code_up').click(function(){
			floor.changeStatusBatch("1");
		});
		//绑定失效按钮
		$('#btn_code_down').click(function(){
			floor.changeStatusBatch("0");
		});
		//绑定批量使失效按钮
		$('#btn_code_batch_del').click(function(){
			floor.deleteBatch();
		});
	},
	
	viewFloor : function(id){
		eNav.setSubPageText('查看楼层');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"id":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floor/view',
			"params" : params,
			"method" :"post"
		});
	},
	
	//跳转页面，修改楼层子元素
	tabMgr : function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var floorId = data[0].id;
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('页签配置');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":floorId,
			"floorSearchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floortab/grid',
			"params" : params,
			"method" :"post"
		});
	},
	labelMgr : function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var floorId = data[0].id;
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('标签配置');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":floorId,
			"floorSearchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorlabel/grid',
			"params" : params,
			"method" :"post"
		});
	},
	gdsMgr : function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var floorId = data[0].id;
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('商品配置');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":floorId,
			"floorSearchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorgds/grid',
			"params" : params,
			"method" :"post"
		});
	},
	couponMgr : function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var floorId = data[0].id;
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('优惠券配置');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":floorId,
			"floorSearchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorcoupon/grid',
			"params" : params,
			"method" :"post"
		});
	},
	attrMgr : function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var floorId = data[0].id;
		$.gridLoading({"message":"正在加载中...."});
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":floorId,
			"floorSearchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorattrcount/grid',
			"params" : params,
			"method" :"post"
		});
	},
	adMgr : function(){
		var data = $("#dataGridTable").getSelectedData();
		if(!data || data.length==0){
			eDialog.alert('请先选择一条记录！');
			return;
		}
		if(data[0].status == "2"){
			eDialog.alert('请选择一条有效的记录进行操作');
			return;
		}
		var floorId = data[0].id;
		$.gridLoading({"message":"正在加载中...."});
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":floorId,
			"floorSearchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'flooradvertise/grid',
			"params" : params,
			"method" :"post"
		});
	},
	
	edit : function(id){   //编辑
		$.gridLoading({"message":"正在加载中...."});
		eNav.setSubPageText('编辑楼层');
		var searchParams = SearchObj.getFormParam($("#searchForm"));
		var params = {
			"floorId":id,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floor/edit',
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
						url : $webroot + 'floor/delete',
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
						url : $webroot + 'floor/delete',
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
			url : $webroot + "floor/changestatus",
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
				}else{
					var text = "该内容位置下已有楼层，请先撤销后再发布新的！";
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
			url : $webroot + "floor/changestatus",
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

$(function() {

	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'multi',
						"sAjaxSource" : GLOBAL.WEBROOT
								+ '/gdscatalog/cataloglist',

						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "目录ID",
									"sWidth" : "50px",
									"bSortable" : true,
									"sClass" : "center",
									"bVisible" : false
								},
								{
									"mData" : "catlogCode",
									"sTitle" : "目录编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : true

								},
								{
									"mData" : "catlogName",
									"sTitle" : "目录名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "statusName",
									"sTitle" : "状态",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
							
								{
									"mData" : "ifDefault",
									"sTitle" : "是否默认",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if(row.ifDefault == "1"){
											return "<span>是</span>"
										}else{
											return "<span>否</span>"
											
										}
									}
								},

								{
									"mData" : "id",
									"sTitle" : "操作",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (row.status == "1"){
											if(row.ifDefault == "1"){
											var optStr = "<span><a href='#' onclick='gdsEdit(this,"
													+ data
													+ ")'>编辑</a>|<a href='#' onclick=\"gdsRemove(this,"
													+ data
													+ ")\">失效</a></span>";
											}else{
												
												var optStr = "<span><a href='#' onclick='gdsEdit(this,"
													+ data
													+ ")'>编辑</a>|<a href='#' onclick=\"gdsRemove(this,"
													+ data
													+ ")\">失效</a>|<a href='#' onclick=\"gdsDefault(this,"
													+ data
													+ ")\">设为默认目录</a></span>";
											}
													
										}else{
											var optStr = "<span><a href='#' onclick='gdsEnable(this,"
													+ data + ")'>启用</a></span>";
										}
										return optStr;
									}
								}

						]

					});

	// 查询
	$('#btnFormSearch').click(function() {
		if (!$("#searchForm").valid())
			return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 重置
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
	});

	$('#btn_code_add').click(function() {
		bDialog.open({
			title : '新增目录',
			width : 500,
			height : 400,
			url : GLOBAL.WEBROOT + '/gdscatalog/catalogadd',
			callback : function() {
				$('#dataGridTable').gridReload();
			}
		});
	});

	// 批量失效
	$('#btn_code_remove').click(function() {
		GdsCatalog.gdsbatchRemove();
	});

});

function gdsRemove(obj, id) {
	// alert(id);
	GdsCatalog.gdsRemove(obj, id);
}

function gdsDefault(obj,id){
	
	GdsCatalog.gdsDefault(obj, id);
}
function gdsEdit(obj, id) {
	GdsCatalog.gdsEdit(obj, id);
}
function gdsEnable(obj, id) {
	GdsCatalog.gdsEnable(obj, id);
}

var GdsCatalog = {

	gdsEnable : function(obj, id) { // 启用

		var param = {
			operateId : id
		};
		eDialog.confirm("您确认启用该记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdscatalog/gdsenable",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('启用成功！');
								$('#dataGridTable').gridReload();
							} else {
								eDialog.alert(returnInfo.resultMsg);
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	},

	gdsRemove : function(obj, id) { // 删除

		var allowDisable = true;
		$.eAjax({
			url : GLOBAL.WEBROOT + '/gdscatalog/removeCheck',
			data : {
				'id' : id
			},
			success : function(returnInfo) {
				
				if(null != returnInfo 
						&& null != returnInfo.ecpBaseResponseVO
						&& 'ok' == returnInfo.ecpBaseResponseVO.resultFlag){
					if('true' == returnInfo.ecpBaseResponseVO.resultMsg){
						allowDisable = false;
						return ;
					}
				}else{
					eDialog.error('目录删除前检测遇到异常!');
				}
			},
			async : false,
			error : function(e,xhr,opt){
				eDialog.error("目录删除前检测遇到异常!");
			},
			exception : function(msg){
				eDialog.error('目录删除前检测遇到异常!');
			}
		});
		
		if(allowDisable == false){
			eDialog.alert('该目录已经被站点关联不允许禁用!');
			return;
		}
		
		
		
		var param = {
			operateId : id
		};
		eDialog.confirm("您确认使该目录失效吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdscatalog/gdsbatchremove",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('失效成功！');
								$('#dataGridTable').gridReload();
							} else {
								eDialog.alert(returnInfo.resultMsg);
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	},
	gdsDefault : function(obj,id){
		
		 // 启用

		var param = {
			operateId : id
		};
		eDialog.confirm("您确认设置该记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdscatalog/gdsDefault",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('设置成功！');
								$('#dataGridTable').gridReload();
							} else {
								eDialog.alert(returnInfo.resultMsg);
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	
		
	},
	
	gdsbatchRemove : function() { // 批量删除
		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var param = {
			operateId : ids.join(",")
		};
		eDialog.confirm("您确认使该目录失效吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdscatalog/gdsbatchremove",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('失效成功！');
								$('#dataGridTable').gridReload();
							} else {
								eDialog.alert(returnInfo.resultMsg);
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	},

	gdsEdit : function(obj, id) {

		bDialog.open({
			title : '修改目录',
			width : 500,
			height : 400,
			url : GLOBAL.WEBROOT + '/gdscatalog/catalogedit?id=' + id,
			callback : function() {
				$('#dataGridTable').gridReload();
			}
		});

	},

};
/**
 * 标签管理初始化页面 js create by zhanbh 2015.9.16
 */
$(function() {
	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'multi',
						'pCheckColumn' : true, // 是否显示单选/复选框的列
						"sAjaxSource" : GLOBAL.WEBROOT + '/gdslabel/gridlist',
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "标签编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "labelTitle",
									"sTitle" : "标签名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "labelDesc",
									"sTitle" : "标签描述",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "labelType",
									"sTitle" : "标签类型",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "status",
									"sTitle" : "状态",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (row.status == '0') {
											return "禁用";
										} else {
											return "启用";
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
										var optStr = "<a href='#' onclick='switchStat("
												+ row.status
												+ ","
												+ data
												+ ")'>";
										if (row.status == '0') {
											optStr = optStr + "启用</a>"
										} else {
											optStr = optStr + "禁用</a>"
										}
										return optStr
												+ " | <a href='#' onclick='labelEdit(this,"
												+ data + ")'>编辑</a>";
									}
								} ]
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
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 添加 标签
	$('#btn_code_add').click(function() {
		bDialog.open({
			title : '添加标签',
			width : 600,
			height : 400,
			url : GLOBAL.WEBROOT + '/gdslabel/labeladd',
			callback : function() {
				// 刷新列表
				$('#dataGridTable').gridReload();
			}
		});
	});
	// 批量启用
	$("#btn_code_use").click(function() {
		labelGrid.batchOper(1);
	});
	// 批零禁用
	$("#btn_code_unuse").click(function() {
		labelGrid.batchOper(0);
	});
});// end of $(function(){})

// 切换状态
function switchStat(obj, id) {
	labelGrid.switchStat(obj, id);
}
// 编辑
function labelEdit(obj, id) {
	bDialog.open({
		title : '编辑标签',
		width : 600,
		height : 500,
		url : GLOBAL.WEBROOT + '/gdslabel/labeledit?id=' + id,
		callback : function() {
			// 刷新列表
			$('#dataGridTable').gridReload();
		}
	});
}

var labelGrid = {
	// 获取列表
	gridGdsList : function() {
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	},
	switchStat : function(status, id) {
		var param = {
			id : id,
			status : status
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdslabel/switchstat",
			data : param,
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					// eDialog.success('状态切换成功！');
					$('#dataGridTable').gridReload();
				} else {
					eDialog.alert("状态切换失败，请联系管理员");
				}
			}
		});
	},// end of switchStat
	batchOper : function(flag) { // 批量操作 flag : 0 禁用 1 启用
		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0 || !ids[0]) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var param = {
			'operateId' : ids.join(","),
			'flag' : flag
		};

		var msg;
		if (flag === 0)
			msg = "您确认禁用这些记录吗？";
		else if (flag === 1)
			msg = "您确认启用这些记录吗？";

		eDialog.confirm(msg, {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdslabel/batchoper",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('操作成功！');
								$('#dataGridTable').gridReload();
							} else if (returnInfo.resultFlag == 'expt') {
								// 异常
								eDialog.success('部分操作成功！');
								$('#dataGridTable').gridReload();
							} else {
								eDialog.alert(" 操作失败，请联系管理员");
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	}// end of gdsbatchRemove
};// end of labelGrid


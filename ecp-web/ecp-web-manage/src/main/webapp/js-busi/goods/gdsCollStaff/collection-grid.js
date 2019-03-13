/**
 * 买家我的收藏页面初始化 js
 */
$(function() {
	$("#dataGridTable").initDT(
			{
				'pTableTools' : false,
				'pSingleCheckClean' : true,
				'pCheck' : 'multi',
				"sAjaxSource" : GLOBAL.WEBROOT + '/gdscollstaff/gridlist',
				// 指定列数据位置
				"aoColumns" : [
						{
							"mData" : "id",
							"sTitle" : "收藏ID",
							"sWidth" : "80px",
							"sClass" : "center",
							"bSortable" : false,
							"bVisible" : false
						},
						{
							"mData" : "gdsId",
							"sTitle" : "商品编码",
							"sWidth" : "80px",
							"sClass" : "center",
							"bSortable" : false
						},
						{
							"mData" : "gdsName",
							"sTitle" : "商品名称",
							"sWidth" : "80px",
							"sClass" : "center",
							"bSortable" : false,
							"mRender" : function(data, type, row) {
								return " <a href='#' onclick='gdsShow(this,"
										+ row.gdsId + ")'>" + row.gdsName
										+ "</a>";
							}
						},
						{
							"mData" : "gdsPrice",
							"sTitle" : "收藏时价格",
							"sWidth" : "80px",
							"sClass" : "center",
							"bSortable" : false
						},
						{
							"mData" : "nowPrice",
							"sTitle" : "现价",
							"sWidth" : "80px",
							"sClass" : "center",
							"bSortable" : false
						},
						{
							"mData" : "id",
							"sTitle" : "操作",
							"sWidth" : "80px",
							"sClass" : "center",
							"bSortable" : false,
							"mRender" : function(data, type, row) {
								return " <a href='#' onclick='collRemove(this,"
										+ data + ")'>删除</a>";
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
	// 批量删除
	$('#btn_code_remove').click(function() {
		CollGrid.gdsbatchRemove(this, 'multi');
	});
});// end of $(function(){})

// 删除
function collRemove(obj, id) {
	if (id == null) {
		eDialog.alert("找不到此记录，请联系管理员");
		return false;
	}
	eDialog.confirm('您确定要删除这条数据吗？', {
		buttons : [ {
			caption : '确定',
			callback : function() {
				CollGrid.collRemove(obj, id);
			}
		}, {
			caption : '取消',
			callback : function() {
				return true;
			}
		} ]
	});

}
var CollGrid = {
	// 获取列表
	gridGdsList : function() {
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	},
	// 单条数据删除
	collRemove : function(obj, id) {
		var param = {
			id : id
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdscollstaff/collremove",
			data : param,
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('删除成功！');
					CollGrid.gridGdsList();
				} else {
					eDialog.alert(" 删除失败，请联系管理员");
				}
			}
		});
	},// end of collRemove
	// 批量删除
	gdsbatchRemove : function() { // 批量删除
		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0 || !ids[0]) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var param = {
			operateId : ids.join(",")
		};
		eDialog.confirm("您确认删除这些记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdscollstaff/collbatchremove",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('删除成功！');
								$('#dataGridTable').gridReload();
							} else if (returnInfo.resultFlag == 'expt') {
								// 异常
								eDialog.success('部分删除成功！');
								$('#dataGridTable').gridReload();
							} else {
								eDialog.alert(" 删除失败，请联系管理员");
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	}

};//end of CollGrid


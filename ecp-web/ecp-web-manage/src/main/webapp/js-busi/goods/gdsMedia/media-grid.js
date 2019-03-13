$(function() {
	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'single',
						'pCheckColumn' : false, // 是否显示单选/复选框的列
						"sAjaxSource" : GLOBAL.WEBROOT + '/gdsmedia/gridlist',
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "媒体编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"sTitle" : "媒体名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {// 增加图片显示
										return "<img style = 'float:left;' src = \'"
												+ row.URL
												+ "\' />"
												+ " "
												+ row.mediaName;
									}
								},
								{
									"mData" : "mediaType",
									"sTitle" : "媒体类型",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "sortNo",
									"sTitle" : "媒体排序值",
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
										return "<a href='#' onclick='mediaShow(this,"
												+ data
												+ ")'>预览</a> | <a href='#' onclick='mediaRemove(this,"
												+ data
												+ ")'>删除</a> | <a href='#' onclick='mediaEdit(this,"
												+ data + ")'>编辑</a>";
									}
								} ],
						"params" : [ {
							name : 'shopId',
							value : $("#shopId").val()
						} ]
					});

	// 查询
	$('#btnFormSearch').click(function() {
		if (!$("#searchForm").valid())
			return false;
		// 获取列表
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 重置
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
		// 获取列表
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 添加媒体
	$('#btn_code_add').click(function() {
		bDialog.open({
			title : '添加媒体',
			width : 650,
			height : 550,
			url : GLOBAL.WEBROOT + '/gdsmedia/mediaadd',
			callback : function() {
				// 刷新列表
				MediaGrid.gridGdsList();
			}
		});
	});

});// end of $(function(){})

// 删除
function mediaRemove(obj, id) {
	if (id == null) {
		eDialog.alert("媒体编码为空，请联系管理员");
		return false;
	}
	eDialog.confirm('您确定要删除这条数据吗？', {
		buttons : [ {
			caption : '确定',
			callback : function() {
				MediaGrid.mediaRemove(obj, id);
			}
		}, {
			caption : '取消',
			callback : function() {
				return true;
			}
		} ]
	});

}
// 编辑
function mediaEdit(obj, id) {
	bDialog.open({
		title : '编辑媒体',
		width : 650,
		height : 550,
		url : GLOBAL.WEBROOT + '/gdsmedia/mediaedit?id=' + id,
		callback : function() {
			// 刷新列表
			MediaGrid.gridGdsList();
		}
	});
}
// 预览
function mediaShow(obj, id) {
	bDialog.open({
		title : '媒体预览',
		width : 400,
		height : 400,
		url : GLOBAL.WEBROOT + '/gdsmedia/meidashow?id=' + id,
	});
}
var MediaGrid = {
	// 获取列表
	gridGdsList : function() {
		$('#dataGridTable').gridReload();
	},
	mediaRemove : function(obj, id) {
		var param = {
			id : id
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsmedia/mediaremove",
			data : param,
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
//					eDialog.success('删除成功！');
					MediaGrid.gridGdsList();
				} else {
					eDialog.alert(" 删除失败，请联系管理员");
				}
			}
		});
	}//end of mediaRemove
};//end of MediaGrid


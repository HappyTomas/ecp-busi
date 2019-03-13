$(function() {
    //删除积分商城店铺的选项
	//$('#shopId').find('option[value=101]').remove();
	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'multi',
						"sAjaxSource" : GLOBAL.WEBROOT
								+ '/gdsdiscount/discountlist',

						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "",
									"sWidth" : "80px",
									"bSortable" : true,
									"bVisible" : false
								},
								{
									"mData" : "catgName",
									"sTitle" : "分类名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : true

								},
								{
									"mData" : "catgCode",
									"sTitle" : "分类名称",
									"sWidth" : "80px",
									"bSortable" : true,
									"bVisible" : false
								},
								{
									"mData" : "custLevelName",
									"sTitle" : "会员等级",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "custLevelCode",
									"sTitle" : "会员等级",
									"sWidth" : "80px",
									"bSortable" : true,
									"bVisible" : false

								},
								{
									"mData" : "discount",
									"sTitle" : "折扣率",
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
									"bVisible" : false

								},

								{
									"mData" : "id",
									"sTitle" : "操作",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {

										var optStr = "<span><a href='#' onclick='gdsEdit(this,"
												+ row.id
												+ ","
												+ row.catgCode
												+ ","
												+ row.shopId
												+ ")'>编辑</a>|<a href='#' onclick=\"gdsRemove(this,"
												+ row.id
												+ ","

												+ row.catgCode
												+ ","
												+ row.shopId
												+ ")\">删除</a></span>";

										return optStr;
									}
								}

						],
						"params" : [ {
							name : 'shopId',
							value : $("#shopId").val()
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
	});

	
	$('#btn_code_add').click(function() {
		bDialog.open({
			title : '新增分类折扣',
			width : 500,
			height : 440,
			url : GLOBAL.WEBROOT + '/gdsdiscount/discountadd',
			callback : function() {
				$('#dataGridTable').gridReload();
			}
		});
	});

	// 批量禁用
	$('#btn_code_remove').click(function() {
		GdsDiscount.gdsbatchRemove();
	});

});

function gdsRemove(obj, id, catgCode, shopId) {
	// alert(custLevelCode);
	GdsDiscount.gdsRemove(obj, id,catgCode, shopId);
}
function gdsEdit(obj, id,  catgCode, shopId) {
	GdsDiscount.gdsEdit(obj, id, catgCode, shopId);
}

var GdsDiscount = {

	gdsRemove : function(obj, id,  catgCode, shopId) { // 删除

		var _discParam = "[";
		_discParam = _discParam + "{'id':'"+id+ "','catgCode':'" + catgCode + "','shopId':'" + shopId + "'}]";

		var param = {
			operateId : _discParam
		};
		eDialog.confirm("您确认删除该记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdsdiscount/gdsbatchremove",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('删除成功！');
								$('#dataGridTable').gridReload();
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
		var ids = $('#dataGridTable').getSelectedData();
		if (!ids || ids.length == 0) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var _discParam = "[";
		$.each(ids, function(n, value) {

			_discParam = _discParam + "{'id':'"+value.id+"','catgCode':'" + value.catgCode
					+ "','shopId':'" + value.shopId + "'},";

		});
		_discParam = _discParam + "]";
		var param = {

			operateId : _discParam
		};
		eDialog.confirm("您确认删除该记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdsdiscount/gdsbatchremove",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('删除成功！');
								$('#dataGridTable').gridReload();
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

	gdsEdit : function(obj, id, catgCode, shopId) {

		bDialog.open({
			title : '编辑分类折扣',
			width : 500,
			height : 440,
			url : GLOBAL.WEBROOT + '/gdsdiscount/discountedit?catgCode=' + catgCode + '&shopId='
					+ shopId+'&id='+id,
			callback : function() {
				$('#dataGridTable').gridReload();
			}
		});

	},

	chooseCatg : function() {
		bDialog
				.open({
					title : '分类选择',
					width : 350,
					height : 550,
					url : GLOBAL.WEBROOT
							+ "/goods/category/open/catgselect?catgType=1&catlogId=1",
					callback : function(data) {
						if (data && data.results && data.results.length > 0) {
							var _catgs = data.results[0].catgs;
							for ( var i in _catgs) {

								$("#catgName").val(_catgs[i].catgName);
								$("#catgCode").val(_catgs[i].catgCode);
							}
						}
					}
				});
	}

};
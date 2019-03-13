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
								+ '/custcommission/commissionlist',

						// 指定列数据位置
						"aoColumns" : [
//								{
//									"mData" : "id",
//									"sTitle" : "",
//									"sWidth" : "80px",
//									"bSortable" : true,
//									"bVisible" : false
//								},
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
									"mData" : "commission",
									"sTitle" : "提成比例",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "shopId",
									"sTitle" : "状态",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"bVisible" : false

								},

								{
									"mData" : "catgCode",
									"sTitle" : "操作",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {

										var optStr = "<span><a href='#' onclick='gdsEdit(this,"
												//+ row.id
												//+ ","
												+ row.catgCode
												+ ","
												+ row.shopId
												+ ")'>编辑</a>|<a href='#' onclick=\"gdsRemove(this,"
												+"'"+row.catgName+"'"
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
			title : '新增客户提成比例',
			width : 500,
			height : 340,
			url : GLOBAL.WEBROOT + '/custcommission/commissionadd',
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

function gdsRemove(obj, catgName, catgCode, shopId) {
	// alert(custLevelCode);
	GdsDiscount.gdsRemove(obj, catgName,catgCode, shopId);
}
function gdsEdit(obj,  catgCode, shopId) {
	GdsDiscount.gdsEdit(obj,  catgCode, shopId);
}

var GdsDiscount = {

	gdsRemove : function(obj, catgName, catgCode, shopId) { // 删除

		var _discParam = "[";
		_discParam = _discParam + "{'catgName':'"+catgName+ "','catgCode':'" + catgCode + "','shopId':'" + shopId + "'}]";
//		_discParam = _discParam + "{'catgCode':'" + catgCode + "'}]";
		var param = {
			operateId : _discParam
		};
		eDialog.confirm("您确认删除该记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/custcommission/gdsbatchremove",
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
		var catgCodes = $('#dataGridTable').getSelectedData();
		if (!catgCodes || catgCodes.length == 0) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var _discParam = "[";
		$.each(catgCodes, function(n, value) {

			_discParam = _discParam + "{'catgName':'"+value.catgName+"','catgCode':'" + value.catgCode
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
						url : GLOBAL.WEBROOT + "/custcommission/gdsbatchremove",
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

	gdsEdit : function(obj,  catgCode, shopId) {

		bDialog.open({
			title : '编辑提成比例',
			width : 500,
			height : 340,
			url : GLOBAL.WEBROOT + '/custcommission/commissionedit?catgCode=' + catgCode ,//+ '&shopId='+ shopId,//+'&id='+id,
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
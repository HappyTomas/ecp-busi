$(function() {
	var _initShop = $("select[name=shopId]").val();
	$("#dataGridTable").initDT({

		'pSingleCheckClean' : true,
		'pTableTools' : false,
		"sAjaxSource" : GLOBAL.WEBROOT + '/goods/stockrep/listRep',
		"params" : [ {
			name : 'shopId',
			value : _initShop
		} ],
		// 指定列数据位置
		"aoColumns" : [ {
			"mData" : "id",
			"sTitle" : "仓库编码",
			"sWidth" : "80px",
			"bSortable" : true
		}, {
			"mData" : "repName",
			"sTitle" : "仓库名称",
			"sWidth" : "80px",
			"bSortable" : false
		}, {
			"mData" : "repType",
			"sTitle" : "仓库类型",
			"sWidth" : "80px",
			"bSortable" : false
		},

		{
			"mData" : "count",
			"sTitle" : "商品总数",
			"sWidth" : "80px",
			"bSortable" : false
		}, {
			"mData" : "status",
			"sTitle" : "状态",
			"sWidth" : "80px",
			"bSortable" : false
		}

		]

	});

	$('#btnFormSearch').click(function() {
		 if (!$("#searchForm").valid())
		 return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	$('#btnFormSearch').trigger("click");
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
	});

	$('#btn_code_add').click(
			function() {
				eNav.setSubPageText('新增仓库');
				window.location.href = GLOBAL.WEBROOT
						+ '/goods/stockrep/add';
			});

	$('#btn_code_modify').click(
			function() {
				eNav.setSubPageText('编辑仓库');
				var _shopId = $("select[name=shopId]").val();
				var ids = $('#dataGridTable').getCheckIds();
				if (ids && ids.length == 1) {
					var datas = $('#dataGridTable').getSelectedData();
					var data = datas[0];
					if (data.repType == "卖家共仓") {
						eDialog.alert('共仓类型仓库不能编辑！');
						return;
					}
					
					if (data.status == "失效") {

						eDialog.alert('仓库已经失效，不能编辑！');
						return;
					}
					window.location.href = GLOBAL.WEBROOT
							+ '/goods/stockrep/editRep?id=' + ids[0]
							+ "&shopId=" + _shopId;
				} else if (ids && ids.length > 1) {
					eDialog.alert('只能选择一个项目进行操作！');
				} else if (!ids || ids.length == 0) {
					eDialog.alert('请选择至少选择一个项目进行操作！');
				}
			});

	$('#btn_code_check').click(
			function() {
				eNav.setSubPageText('查看仓库');
				var ids = $('#dataGridTable').getCheckIds();
				var _shopId = $("select[name=shopId]").val();
				if (ids && ids.length == 1) {
					var datas = $('#dataGridTable').getSelectedData();
					var data = datas[0];
					if (data.repType == "01") {
						eDialog.alert('共仓类型仓库不能查看！');
						return;
					}

					if (data.status == "失效") {

						eDialog.alert('仓库已经失效，不能查看！');
						return;
					}
					window.location.href = GLOBAL.WEBROOT
							+ '/goods/stockrep/check?id=' + ids[0] + "&shopId="
							+ _shopId;
				} else if (ids && ids.length > 1) {
					eDialog.alert('只能选择一个项目进行操作！');
				} else if (!ids || ids.length == 0) {
					eDialog.alert('请选择至少选择一个项目进行操作！');
				}
			});

	$('#btn_code_del')
			.click(
					function() {
						var _shopId = $("select[name=shopId]").val();
						var ids = $('#dataGridTable').getCheckIds();
						if (ids && ids.length == 1) {
							var datas = $('#dataGridTable').getSelectedData();
							var data = datas[0];
							if (data.repType == "卖家共仓") {
								eDialog.alert('共仓类型仓库不能失效！');
								return;
							}
							if (data.status == "失效") {

								eDialog.alert('仓库已经失效！');
								return;
							}
							eDialog
									.confirm(
											"您确认失效该仓库吗？",
											{
												buttons : [
														{
															caption : '确认',
															callback : function() {
																$
																		.eAjax({
																			url : GLOBAL.WEBROOT
																					+ "/goods/stockrep/delRep",
																			data : {
																				"id" : ids[0],
																				"shopId" : _shopId
																			},
																			success : function(
																					returnInfo) {
																				eDialog
																						.success(
																								'仓库失效成功！',
																								{
																									buttons : [ {
																										caption : "确定",
																										callback : function() {
																											window.location.href = $webroot
																													+ '/goods/stockrep/pageInit';
																										}
																									} ]
																								});

																			}
																		});

																eDialog
																		.alert(
																				'success',
																				'失效成功！');
																/*
																 * $.eAjax({ url :
																 * $webroot +
																 * 'action/dml?op=code:2&code.id=' +
																 * ids[0],
																 * success :
																 * function(returnInfo) {
																 * if(returnInfo.code){
																 * 
																 * window.location.reload(); } }
																 * });
																 */
															}
														}, {
															caption : '取消',
															callback : $.noop
														} ]
											});
						} else if (ids && ids.length > 1) {
							eDialog.alert('只能选择一个仓库进行操作！');
						} else if (!ids || ids.length == 0) {
							eDialog.alert('请选择至少选择一个仓库进行操作！');
						}
					});

});
$(function() {
	var _dlg = bDialog.getDialog();
	// 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
		var importId = _param.importId;
			// 表格数据初始化
			$("#dataGridTableSuccess").initDT({
				'pTableTools' : false,
				'pSingleCheckClean' : false,
				'pCheckColumn' : false,
				"sAjaxSource" : GLOBAL.WEBROOT
						+ '/goods/excelImport/listResult',
				'params' : [{
							name : 'importId',
							value : importId
						}, {
							name : 'status',
							value : "1"

						}

				],
				// 指定列数据位置
				"aoColumns" : [{
							"mData" : "gdsId",
							"sTitle" : "商品编码",
							"sWidth" : "50px",
							"bSortable" : false,
							"bVisible" : false
						}, {
							"mData" : "catgCode",
							"sTitle" : "平台分类编码",
							"sWidth" : "80px",
							"bSortable" : false
						}, {
							"mData" : "gdsName",
							"sTitle" : "商品名称",
							"sWidth" : "70px",
							"bSortable" : false
						}, {
							"mData" : "shopId",
							"sTitle" : "店铺编码",
							"sWidth" : "80px",
							"bSortable" : false
						}, {
							"mData" : "gdsType",
							"sTitle" : "产品类型编码",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsTitle",
							"sTitle" : "商品副标题",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsPrice",
							"sTitle" : "商品价格(单位:分)",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsPropStr",
							"sTitle" : "商品属性串",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsStock",
							"sTitle" : "商品库存",
							"sWidth" : "50px",
							"bSortable" : false

						}

				],
				"rowCallback" : function(row, data) {
					// if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
					// $(row).addClass('selected');
					// }
				},
				"eDbClick" : function() {
					// modifyBiz();
				}
			});

			
			
				// 表格数据初始化
			$("#dataGridTableFail").initDT({
				'pTableTools' : false,
				'pSingleCheckClean' : false,
				'pCheckColumn' : false,
				"sAjaxSource" : GLOBAL.WEBROOT
						+ '/goods/excelImport/listResult',
				'params' : [{
							name : 'importId',
							value : importId
						}, {
							name : 'status',
							value : "0"

						}

				],
				// 指定列数据位置
				"aoColumns" : [{
							"mData" : "gdsId",
							"sTitle" : "商品编码",
							"sWidth" : "50px",
							"bSortable" : false,
							"bVisible" : false
						}, {
							"mData" : "catgCode",
							"sTitle" : "平台分类编码",
							"sWidth" : "80px",
							"bSortable" : false
						}, {
							"mData" : "gdsName",
							"sTitle" : "商品名称",
							"sWidth" : "70px",
							"bSortable" : false
						}, {
							"mData" : "shopId",
							"sTitle" : "店铺编码",
							"sWidth" : "80px",
							"bSortable" : false
						}, {
							"mData" : "gdsType",
							"sTitle" : "产品类型编码",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsTitle",
							"sTitle" : "商品副标题",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsPrice",
							"sTitle" : "商品价格(单位:分)",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsPropStr",
							"sTitle" : "商品属性串",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "gdsStock",
							"sTitle" : "商品库存",
							"sWidth" : "50px",
							"bSortable" : false

						}, {
							"mData" : "failReason",
							"sTitle" : "失败原因",
							"sWidth" : "50px",
							"bSortable" : false

						}


				],
				"rowCallback" : function(row, data) {
					// if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
					// $(row).addClass('selected');
					// }
				},
				"eDbClick" : function() {
					// modifyBiz();
				}
			});
		});
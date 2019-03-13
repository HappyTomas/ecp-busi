$(function() {

	var _initSrcSys = $("select[name=catgSrcSystem]").val();
	$("#dataGridTable").initDT({
		'pCheckColumn' : true,
		'pTableTools' : false,
		'pSingleCheckClean' : false,
		'pCheck' : 'multi',
		"sAjaxSource" : GLOBAL.WEBROOT + '/goods/gdsCatgSync/listCatgSync',
		"params" : [{
					name : 'srcSystem',
					value : _initSrcSys
				}],
		// 指定列数据位置
		"aoColumns" : [{
					"mData" : "id",
					"sTitle" : "映射记录id",
					"sWidth" : "60px",
					"bSortable" : false,
					"bVisible" : false
				}, {
					"mData" : "catgCode",
					"sTitle" : "原分类编码",
					"sWidth" : "80px",
					"bSortable" : false,
					"bVisible" : false
				}, {
					"mData" : "catgName",
					"sTitle" : "原分类名称",
					"sWidth" : "80px",
					"bSortable" : false

				}, {
					"mData" : "srcSystem",
					"sTitle" : "来源",
					"sWidth" : "80px",
					"bSortable" : false

				}, {
					"mData" : "mapCatgCode",
					"sTitle" : "现分类编码",
					"sWidth" : "80px",
					"bSortable" : false
				},

				{
					"mData" : "mapCatgName",
					"sTitle" : "现分类名称",
					"sWidth" : "90px",
					"bSortable" : false
				}, {
					"mData" : "opt",
					"sTitle" : "操作",
					"sWidth" : "80px",
					"bSortable" : false,
					"mRender" : function(data, type, row) {
						var optOption = "";
						if (row.mapCatgCode && row.mapCatgCode != "") {
							return "<span><a href='#' name='catgSyncOpt' delId='"
									+ row.id + "'>取消映射</a> </span>";
						} else {
							return ""
						}
					}
				}]

	});

	$("a[name='catgSyncOpt']").live('click', function(e) {
		var data = new Object();
		data.delId = $(this).attr("delId");
		eDialog.confirm("是否确认取消?", {
			buttons : [{
				caption : '是',
				callback : function() {

					$.eAjax({
						url : GLOBAL.WEBROOT + "/goods/gdsCatgSync/delCatgSync",
						data : data,
						success : function(returnInfo) {
							$('#btnFormSearch').trigger("click");
						}

					});
				}
			}, {
				caption : '否',
				callback : $.noop
			}]
		});

	});

	$('#btn_code_remove').live('click', function(e) {
		var data = new Object();

		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0) {
			eDialog.alert('请选择至少选择一条映射记录进行操作！');
			return;
		} else if (ids[0] == undefined) {
			eDialog.alert('请选择至少选择一条映射记录进行操作！');
			return;
		}
		
		data.delIds =  ids.join(",");
		eDialog.confirm("是否确认取消?", {
			buttons : [{
				caption : '是',
				callback : function() {

					$.eAjax({
						url : GLOBAL.WEBROOT + "/goods/gdsCatgSync/delBatchCatgSync",
						data : data,
						success : function(returnInfo) {
							$('#btnFormSearch').trigger("click");
						}

					});
				}
			}, {
				caption : '否',
				callback : $.noop
			}]
		});

	});
	$('#btnFormSearch').click(function() {
				if (!$("#searchForm").valid())
					return false;

				var p = ebcForm.formParams($("#searchForm"));
				p.push({
							'name' : 'mapCatgCode',
							'value' : $("#mapCatgCode").attr('mapCatgCode')
						});
				$.gridLoading({
							"el" : "#gridLoading",
							"messsage" : "正在加载中...."
						});
				$('#dataGridTable').gridSearch(p);
				$.gridUnLoading({
							"el" : "#gridLoading"
						});
			});

	$('#btnFormReset').click(function() {
				ebcForm.resetForm('#searchForm');
				$("#mapCatgCode").removeAttr('mapCatgCode');
			});

	$("#mapCatgCode").click(function() {
		bDialog.open({
			title : '分类选择',
			width : 350,
			height : 550,
			params : {
				multi : false
			},
			url : GLOBAL.WEBROOT + "/goods/category/open/catgselect?catgType=1&catlogId=1",
			callback : function(data) {
				if (data && data.results && data.results.length > 0) {
					var _catgs = data.results[0].catgs;
					var size = _catgs.length;
					for (var i = 0; i < size; i++) {
						var obj = _catgs[i];
						$("#mapCatgCode").val(obj.catgName);
						$("#mapCatgCode").attr('mapCatgCode', obj.catgCode);
					}
				}
			}
		});

	});

});
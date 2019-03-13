$(function() {

	$("#dataGridTable").initDT(
			{
				'overflow-y' : scroll,
				'pTableTools' : false,
				'pSingleCheckClean' : true,
				'pCheck' : 'multi',
				"sAjaxSource" : GLOBAL.WEBROOT + '/gdsevalaudit/auditlist',

				// 指定列数据位置
				"aoColumns" : [
						{
							"mData" : "id",
							"sTitle" : "",
							"sWidth" : "5%",
							"bSortable" : false,
							"bVisible" : false
						},
						{
							"mData" : "gdsName",
							"sTitle" : "商品名称",
							"sWidth" : "10%",
							"sClass" : "center",
							"bSortable" : true,
							"mRender" : function(data, type, row) {
								return '<a  target="_blank" href="'
										+ row.gdsDetailUrl + '">' + data
										+ '</a>';
							}
						},
						{
							"mData" : "orderId",
							"sTitle" : "订单",
							"sWidth" : "10%",
							"sClass" : "center",
							"bSortable" : false,
							"mRender" : function(data, type, row) {
								return '<a  target="_blank" href="'
										+ GLOBAL.WEBROOT
										+ "/order/orderdetails?orderId=" + data
										+ '">' + data + '</a>';
							}
						},
						{
							"mData" : "score",
							"sTitle" : "评价评分",
							"sWidth" : "10%",
							"sClass" : "center",
							"bSortable" : false
						},
						{
							"mData" : "detail",
							"sTitle" : "评价内容",
							"sWidth" : "45%",
							"sClass" : "left",
							"bSortable" : false

						},
						{
							"mData" : "labelName",
							"sTitle" : "评价标签",
							"sWidth" : "10%",
							"sClass" : "center",
							"bSortable" : false,

						},

						{
							"mData" : "evaluationTime",
							"sTitle" : "评价时间",
							"sWidth" : "10%",
							"sClass" : "center",
							"bSortable" : false,
							"mRender" : function(data, type, row) {
								return ebcDate.dateFormat(data,
										"yyyy-MM-dd hh:mm:ss");
							}

						}

				],
				"params" : [ {
					name : 'shopId',
					value : $("#shopId").val()
				} ],
				"createdRow" : function(row, data, index) {
					$('td', row).eq(4).css('word-break', "break-all");
					$('td', row).eq(4).css('word-wrap', 'break-word');

				},

			});

	// 查询
	$('#btnFormSearch').click(function() {
		if (!$("#searchForm").valid())
			return false;
		var auditStatus = $("#myTab").find("li.active").attr("auditStatus");
		var p = ebcForm.formParams($("#searchForm"));
		p.push({
			'name' : 'auditStatus',
			'value' : auditStatus
		});
		$('#dataGridTable').gridSearch(p);

	});

	// 重置
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
	});

	// 批量审批通过
	$('#btn_code_pass').click(function() {
		GdsAudit.gdsbatchPass();
	});
	// 批量审批不通过
	$('#btn_code_nopass').click(function() {
		GdsAudit.gdsbatchNoPass();
	});

	GdsAudit.gdsEvalTabEventInit();

});

var GdsAudit = {

	gdsEvalTabEventInit : function() {
		$("#myTab").find("li").die("click").live("click", function() {
			$("#myTab").find("li.active").removeClass("active");
			$(this).addClass("active");
			var auditStatus = $(this).attr("auditStatus")
			if(auditStatus == "2"){
				$("#btn_code_pass").show();
				$("#btn_code_nopass").show();
			}else{
				$("#btn_code_pass").hide();
				$("#btn_code_nopass").hide();	
			}
		
			if (!$("#searchForm").valid())
				return false;
			var p = ebcForm.formParams($("#searchForm"));
			p.push({
				'name' : 'auditStatus',
				'value' : auditStatus
			});
			$('#dataGridTable').gridSearch(p);

		});

	},

	gdsbatchPass : function() { // 批量审批通过
		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var param = {
			operateId : ids.join(",")
		};
		eDialog.confirm("您确认审批通过吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdsevalaudit/gdsbatchpass",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('审批成功！');
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

	gdsbatchNoPass : function() { // 批量审批不通过
		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0) {
			eDialog.alert('请选择至少选择一个项目进行操作！');
			return;
		}
		var param = {
			operateId : ids.join(",")
		};
		eDialog.confirm("您确认审批不通过吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdsevalaudit/gdsbatchnopass",
						type : "POST",
						data : param,
						datatype : 'json',
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('审批成功！');
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

};
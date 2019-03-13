$(function() {
	$.validator.addMethod("digits",function(value, element){
		var decimal = /^\d?$/;
		return decimal.test(value);
	},"<font style='color:red'>只能输入数字</font>");
	// 获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	// 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	// 获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var _gdsId = _param.gdsId;
	var _shopId = _param.shopId;
	var _catgCode = _param.catgCode;
	$("#gdsId").val(_gdsId);
	$("#shopId").val(_shopId);
	debugger;
	$("#catgCode").val(_catgCode);
	$("#dataGridTable").initDT({
		'pTableTools' : false,
		'pCheckColumn' : true,
		'pSingleCheckClean' : true,
		'pCheck' : 'single',
		"sAjaxSource" : GLOBAL.WEBROOT + '/gdsinfoentry/gridshiptemplist',
		// 指定列数据位置
		"aoColumns" : [ {
			"mData" : "id",
			"sTitle" : "模板编码",
			"sWidth" : "80px",
			"sClass" : "center",
			"bSortable" : false
		}, {
			"mData" : "shipTemplateName",
			"sTitle" : "模板名称",
			"sWidth" : "80px",
			"sClass" : "center",
			"bSortable" : false,
			"mRender" : function(data, type, row) {
				return "<span class='escapeTxt'>" + unescape(data) + "</span>";
			}
		}, {
			"mData" : "ifDefaultTemplate",
			"sTitle" : "是否默认运费模板",
			"sWidth" : "90px",
			"sClass" : "center",
			"bSortable" : false,
			"mRender" : function(data, type, row) {
				if (data == '1') {
					return "是";
				} else {
					return "否";
				}
			}
		}
		// {"mData": null,"sTitle":"操作","sWidth":"80px","sClass":
		// "center",
		// "mRender": function(data,type,row){
		// return "<a href='javascript:void(0)'>选择</a>";
		// }
		// }
		],
		"params" : [ {
			name : 'shopId',
			value : $("#shopId").val()
		},{
			name : 'catgCode',
			value : $("#catgCode").val()
		} ],
		"onSuccess" : function() {

			$(".escapeTxt").each(function() {
				$(this).text($(this).html())
			});
		}
	});
	$('#btnReturn').click(function() {
		bDialog.closeCurrent();
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
	$("#saveGdsInfo").click(function() {
		SetInfo.saveSetInfo();
	});
});
var SetInfo = {
	saveSetInfo : function() {
		var _flag = "";
		var ids = $('#dataGridTable').getCheckIds();
		if (!ids || ids.length == 0) {
			eDialog.alert('请选择一条商品记录进行操作！');
			return;
		}
		var param = {};
		param.shipTemplateId = ids[0];
		param.gdsId = $("#gdsId").val();
		param.shopId = $("#shopId").val();
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsmanage/setshiptemp",
			data : param,
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					_flag = "ok";
				} else {
					_flag = "fail";
				}
				bDialog.closeCurrent({
					'flag' : _flag
				});
			}
		});
	}
};

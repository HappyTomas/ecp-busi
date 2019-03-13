$(function() {
	// 获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	// 获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	// 获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	SkuGrid.querySkuList(_param.gdsId,_param.status);
	$('#btnReturn').click(function() {
		bDialog.closeCurrent();
	});
});
function skuUpDown(obj, skuId, gdsId, shopId, skuStatus) {
	SkuGrid.skuUpDown(obj, skuId, gdsId, shopId, skuStatus);
}

function addArrMsg(skuId) {
	SkuGrid.addArrMsg(skuId);
}
function addCollect(skuId) {
	SkuGrid.addCollect(skuId);
}
var SkuGrid = {
	querySkuList : function(gdsId,status) {
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsmanage/gridskulist",
			data : {
				"gdsId" : gdsId,
				"status" : status
			},
			dataType : "html",
			success : function(returnInfo) {
				$("#datatablesList").empty();
				$("#datatablesList").html(returnInfo);
			}
		});
	},
	skuUpDown : function(obj, skuId, gdsId, shopId, skuStatus) {
		var operateFlag = "";
		if (skuStatus == '11') {
			operateFlag = "0";
		} else {
			operateFlag = "1";
		}
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsmanage/skuupdown",
			data : {
				"skuId" : skuId,
				"gdsId" : gdsId,
				"shopId" : shopId,
				"skuStatus" : skuStatus,
				"operateFlag" : operateFlag
			},
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('操作成功！');
					SkuGrid.querySkuList(gdsId,skuStatus);
				} else {
					eDialog.error('操作失败！原因是：'+returnInfo.resultMsg);
				}

			}
		});
	},
	addArrMsg : function(skuId) {
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsarrmsg/add",
			data : {
				"skuId" : skuId
			},
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('操作成功！');
				} else {
					eDialog.error(returnInfo.resultMsg);
				}

			}
		});
	},
	addCollect : function(skuId) {
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdscollstaff/add",
			data : {
				"skuId" : skuId
			},
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('操作成功！');
				} else {
					eDialog.error(returnInfo.resultMsg);
				}
			}
		});

	}
};

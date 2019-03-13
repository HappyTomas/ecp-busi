$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//客户查询按钮
			$('#btnCustSelect').unbind('click').click(function() {
				batchSendRule.queryCust();
			});
			//优惠券查询按钮
			$('#btnSendCoupSelect').unbind('click').click(function() {
				batchSendRule.queryCoup("11");
			});
			var coupTr = null;
			//批量删除功能 
			$("#btn_coup_del_batch").live('click', function(e) {
				batchSendRule.delAllCoup(e);
			});

			//分类删除按钮功能
			$("#coupTable tr td a[ name='delCoupRow']").live('click', function(e) {
				batchSendRule.delCoup(e);
			});
			//checkbox选择事件
			$("#coupTable thead tr input[ id='dt_row_all_check']").live('click',
					function(e) {
						batchSendRule.selectCheckBox(e);
					});
			//checkbox选择事件
			$("#custTable thead tr input[ id='dt_row_all_check']").live('click',
					function(e) {
						batchSendRule.selectCheckBoxCust(e);
					});

			var custTr = null;
			//批量删除功能 
			$("#btn_cust_del_batch").live('click', function(e) {
				batchSendRule.delAllCust(e);
			});

			//删除按钮功能
			$("#custTable tr td a[ name='delCustRow']").live('click', function(e) {
				batchSendRule.delCust(e);
			});
			//提交按钮
			$('#btnFormSave').unbind('click').click(function() {
				batchSendRule.save(this);
			});


		};

		return {
			init : init
		};
	};

	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});


var batchSendRule = {
	//查询客户
	queryCust : function() {
		bDialog.open({
			title : '客户选择',
			width : 820,
			height : 540,
			url : GLOBAL.WEBROOT + "/seller/sendrule/custgrid",
			callback : function(data) {
				//确定 按钮_if_query==1
				if (data && data.results && data.results[0]._if_query == '1') {
					batchSendRule.initCustList(data.results[0].rows);
				}
			}
		});
	},
	//查询优惠券
	queryCoup : function(_shopId) {
		bDialog.open({
			title : '优惠券选择',
			width : 800,
			height : 540,
			url : GLOBAL.WEBROOT + "/seller/sendrule/select",
			callback : function(data) {
				//确定 按钮_if_query==1
				if (data && data.results && data.results[0]._if_query == '1') {
					batchSendRule.initCoupList(data.results[0].rows);
				}
			}
		});
	},
	//客户选择
	initCustList : function(_ids) {
		//_ids 初始化页面为空  ，open页面回掉可非空
		if (!_ids) {
			_ids = new Array();
		}
		//当前页面的列表数据 需要加入到ids中
		var _l = $("#custTable tbody tr td input[ name='checkboxcust']");
		if (_l && _l.length > 0) {
			for (var i = 0; i < _l.length; i++) {
				_ids.push(_l[i].parentNode.parentNode.id);
			}
		} else {
			//没有数据 清空
			if (_ids && _ids.length == 0) {
				_ids = '';
			}
		}
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/sendrule/custList",
			data : [ {
				name : 'ids',
				value : _ids
			} ],
			"dataType" : "text",
			success : function(returnInfo) {
				$('#sendCustTableId').empty();
				$('#sendCustTableId').append(returnInfo);
			}
		});
	},
	//优惠券列表 ifmerge是否合并
	initCoupList : function(_ids) {
		//_ids 初始化页面为空  ，open页面回掉可非空
		if (!_ids) {
			_ids = new Array();
		}
		//当前页面的列表数据 需要加入到ids中
		var _l = $("#coupTable tbody tr td input[ name='checkboxcoup']");
		if (_l && _l.length > 0) {
			for (var i = 0; i < _l.length; i++) {
				_ids.push(_l[i].parentNode.parentNode.id);
			}
		} else {
			//没有数据 清空
			if (_ids && _ids.length == 0) {
				_ids = '';
			}
		}
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/sendrule/coupInfoList",
			data : [ {
				name : 'ids',
				value : _ids
			} ],
			"dataType" : "text",
			success : function(returnInfo) {
				$('#sendCoupTableId').empty();
				$('#sendCoupTableId').append(returnInfo);
			}
		});
	},
	//优惠券批量删除
	delAllCoup : function(e) {
		var _doAction = false;
		var _l = $("#coupTable tbody tr td input[ name='checkboxcoup']");
		for (var i = 0; i < _l.length; i++) {
			if (_l[i].checked) {
				_doAction = true;
				break;
			}
		}
		if (!_doAction) {
			eDialog.alert('请选择需要删除的数据。');
			return;
		}

		eDialog
				.confirm(
						"确定批量删除吗？",
						{
							buttons : [
									{
										caption : '确认',
										callback : function() {
											var _l = $("#coupTable tbody tr td input[ name='checkboxcoup']");
											var _tr = null;
											for (var i = 0; i < _l.length; i++) {
												if (_l[i].checked) {
													//选中 删除
													_tr = $(_l[i]).parent()
															.parent();
													_tr.remove();
												}
											}
										}
									}, {
										caption : '取消',
										callback : $.noop
									} ]
						});
	},
	//删除
	delCoup : function(e) {
		//gdsTr=e.currentTarget.parentNode.parentNode;
		coupTr = $(e.currentTarget).parent().parent();
		eDialog.confirm("确定删除吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					coupTr.remove();
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	},
	//客户批量删除
	delAllCust : function(e) {
		var _doAction = false;
		var _l = $("#custTable tbody tr td input[ name='checkboxcust']");
		for (var i = 0; i < _l.length; i++) {
			if (_l[i].checked) {
				_doAction = true;
				break;
			}
		}
		if (!_doAction) {
			eDialog.alert('请选择需要删除的数据。');
			return;
		}

		eDialog
				.confirm(
						"确定批量删除吗？",
						{
							buttons : [
									{
										caption : '确认',
										callback : function() {
											var _l = $("#custTable tbody tr td input[ name='checkboxcust']");
											var _tr = null;
											for (var i = 0; i < _l.length; i++) {
												if (_l[i].checked) {
													//选中 删除
													_tr = $(_l[i]).parent()
															.parent();
													_tr.remove();
												}
											}
										}
									}, {
										caption : '取消',
										callback : $.noop
									} ]
						});
	},
	//删除
	delCust : function(e) {
		custTr = $(e.currentTarget).parent().parent();
		eDialog.confirm("确定删除吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					custTr.remove();
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	},
	//优惠券选择
	selectCheckBox : function(e) {
		//选中 表示全选
		if (e.currentTarget.checked) {
			$("#coupTable tbody tr td input[ name='checkboxcoup']").prop(
					'checked', true);
		} else {
			//全部取消
			$("#coupTable tbody tr td input[ name='checkboxcoup']").prop(
					'checked', false);
		}
	},
	//客户选择
	selectCheckBoxCust : function(e) {
		//选中 表示全选
		if (e.currentTarget.checked) {
			$("#custTable tbody tr td input[ name='checkboxcust']").prop(
					'checked', true);
		} else {
			//全部取消
			$("#custTable tbody tr td input[ name='checkboxcust']").prop(
					'checked', false);
		}
	},
	//提交
	save : function(objectBtn) {
		$(objectBtn).attr('disabled', 'true');
		var _data1 = new Array();
		var _l = $("#custTable tbody tr td input[ name='checkboxcust']");
		if (_l.length <= 0) {
			eDialog.alert('请选择客户', null);
			return;
		} else {
			for (var i = 0; i < _l.length; i++) {
				_data1.push(_l[i].id);
			}
		}
		var _data2 = new Array();
		var _l = $("#coupTable tbody tr td input[ name='checkboxcoup']");
		if (_l.length <= 0) {
			eDialog.alert('请选择需要赠送的优惠券', null);
			return;
		} else {
			for (var i = 0; i < _l.length; i++) {
				_data2.push(_l[i].id);
			}
		}

		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/sendrule/save",
			data : [ {
				name : 'custIds',
				value : _data1
			}, {
				name : 'coupIds',
				value : _data2
			} ],
			success : function(returnInfo) {
				//成功返回
				if (returnInfo.resultFlag == 'ok') {
					eDialog.alert("赠送成功！", function() {
						window.location.href = GLOBAL.WEBROOT + '/seller/sendrule';
					});
				} else if (returnInfo.resultFlag == 'fail') {
					eDialog.alert(returnInfo.resultMsg, null);
				} else {
					eDialog.alert(returnInfo.resultMsg, null);
				}
			},
			complete:function(){
				$(objectBtn).removeAttr('disabled'); 
			},
			exception : function(returnInfo) {
				//enable  保存 和提交
			}
		});
	}
};
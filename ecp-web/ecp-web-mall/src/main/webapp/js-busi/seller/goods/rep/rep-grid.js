$(function() {
	//绑定搜索按钮事件
	$('#btnFormSearch').click(function(){
		var shopId = $("select[name='shopId']").val();
		var repName = $("input[name='repName']").val();
		var status = $("select[name='status']").val();
		$('#listDiv').html('');
		$('#listDiv').load(GLOBAL.WEBROOT + '/seller/goods/stockrep/listRep',
				{shopId : shopId,repName : repName,status : status});
	});

	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
	});

	// 新增库存按钮事件
	$('#btn_code_add').live('click',function() {
		window.location.href = GLOBAL.WEBROOT + '/seller/goods/stockrep/add';
	});

	// 绑定编辑按钮事件。
	$('#btn_code_modify').live('click',
			function() {
				var _shopId = $("select[name=shopId]").val();
				var chk = $('input[type=radio][name=repId]:checked');
				// var ids = $('#dataGridTable').getCheckIds();
				if (chk && chk.length == 1) {
					if (chk.attr('repType') == "卖家共仓") {
						eDialog.alert('共仓类型仓库不能编辑！');
						return;
					}

					if (chk.attr('status') == "失效") {
						eDialog.alert('仓库已经失效，不能编辑！');
						return;
					}
					window.location.href = GLOBAL.WEBROOT
							+ '/seller/goods/stockrep/editRep?id=' + chk.val()
							+ "&shopId=" + _shopId;
				} else if (chk && chk.length > 1) {
					eDialog.alert('只能选择一个项目进行操作！');
				} else if (!chk || chk.length == 0) {
					eDialog.alert('请至少选择一个项目进行操作！');
				}
			});

	// 点击查看按钮事件。
	$('#btn_code_check').live('click',
			function() {
				var chk = $('input[type=radio][name=repId]:checked');
				if (chk && chk.length == 1) {
					if (chk.attr('repType') == "01") {
						eDialog.alert('共仓类型仓库不能查看！');
						return;
					}

					if (chk.attr('status') == "失效") {

						eDialog.alert('仓库已经失效，不能查看！');
						return;
					}
					var _shopId = $("select[name=shopId]").val();
					window.location.href = GLOBAL.WEBROOT
							+ '/seller/goods/stockrep/check?id=' + chk.val()
							+ "&shopId=" + _shopId;
				} else if (chk && chk.length > 1) {
					eDialog.alert('只能选择一个项目进行操作！');
				} else if (!chk || chk.length == 0) {
					eDialog.alert('请至少选择一个项目进行操作！');
				}
			});

	$('#btn_code_del')
			.live('click',
					function() {
						var chk = $('input[type=radio][name=repId]:checked');
						if (chk && chk.length == 1) {
							if (chk.attr('repType') == "卖家共仓") {
								eDialog.alert('共仓类型仓库不能失效！');
								return;
							}
							if (chk.attr('status') == "失效") {
								eDialog.alert('仓库已经失效！');
								return;
							}
							eDialog.confirm(
							"您确认失效该仓库吗？",
							{
								buttons : [
										{
											caption : '确认',
											callback : function() {
												$.eAjax({
															url : GLOBAL.WEBROOT
																	+ "/seller/goods/stockrep/delRep",
															data : {
																"id" : chk.val(),
																"shopId" : chk.attr('shopId')
															},
															success : function(returnInfo) {
																if(null != returnInfo && 'ok' == returnInfo.ecpBaseResponseVO.resultFlag){
																	eDialog.success('仓库失效成功！',
																			{
																		buttons : [ {
																			caption : "确定",
																			callback : function() {
																				$('#btnFormSearch').trigger('click');
																			}
																		} ]
																	});
																}else{
																	eDialog.error('仓库失效失败!');
																}
															},
															exception : function(e,xhr,opt){
																eDialog.error("仓库失效遇到异常!"); 
															}
														});
											}
										}, {
											caption : '取消',
											callback : $.noop
										}]
							});
						} else if (chk && chk.length > 1) {
							eDialog.alert('只能选择一个项目进行操作！');
						} else if (!chk || chk.length == 0) {
							eDialog.alert('请至少选择一个项目进行操作！');
						}
					});
	
	$('#btnFormSearch').trigger('click');

});

$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/scoremgr/gridlist',
        "params":[{name:"staffCode",value:$('#staffCode').val()},{name:"status",value:$('#status').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "custName", "sTitle":"真实姓名","sWidth":"80px","bSortable":false},
			{ "mData": "nickname", "sTitle":"会员昵称","sWidth":"80px","bSortable":false},
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"80px","bSortable":false},
			{ "mData": "scoreTotal", "sTitle":"总积分","sWidth":"80px","bSortable":false},
			{ "mData": "scoreBalance", "sTitle":"可用积分","sWidth":"80px","bSortable":false},
			{ "mData": "scoreFrozen", "sTitle":"冻结积分","sWidth":"80px","bSortable":false},
			{ "mData": "scoreUsed", "sTitle":"已使用积分","sWidth":"80px","bSortable":false},
			{ "mData": "status", "sTitle":"账户状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if(data == '1'){
                	return "正常";
                } else if(data == '2'){
               	 	return "冻结";
                } else {
                	return "失效";
                }
	         }}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	//积分调整
	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1) {
		    bDialog.open({
				title : '积分调整',
				width : 500,
				height : 380,
				scroll : false,
				url : GLOBAL.WEBROOT+'/scoremgr/adjust?staffId=' + val[0].staffId,
				callback:function(data){
						$('#dataGridTable').gridReload();
					}
			});
		} else if(val && val.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		} else if(!val || val.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
		}
	};
	
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_add').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/cust/more';
	});
	
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	
	
	//冻结
	$('#btn_code_invalid').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].status == '2') {
				eDialog.alert("您好，该积分账户状态已为冻结，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该积分账户冻结吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'scoremgr/gridlist/scoreinvalid?id=' + ids[0],
							success : function(returnInfo) {
								if(returnInfo.resultMsg){
									eDialog.alert(returnInfo.resultMsg);
									//window.location.reload();
									$('#btnFormSearch').click();//刷新列表
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
		}
	});
	//使正常
	$('#btn_code_valid').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].status == '1') {
				eDialog.alert("您好，该积分账户状态已为正常，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该积分账户解除冻结吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'scoremgr/gridlist/scorevalid?id=' + ids[0],
							success : function(returnInfo) {
								if(returnInfo.resultMsg){
									eDialog.alert(returnInfo.resultMsg);
									$('#btnFormSearch').click();//刷新列表
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
		}
	});
});
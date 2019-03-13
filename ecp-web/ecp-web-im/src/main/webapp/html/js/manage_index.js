$(function(){
	$("#dataGridTable").initDT({
        "pColDrag":true,
        'pTableTools' : true,
        'pSingleCheckClean' : false,
        'pRequestType' : 'GET',
        "sAjaxSource": $webroot + '/html/js/data.json',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "table_name", "sTitle":"数据表","bSortable":false},
			{ "mData": "field_name", "sTitle":"表字段","bSortable":false},
			{ "mData": "code_desc", "sTitle":"代码描述","sWidth":"100px","bSortable":false},
			{ "mData": "code_value", "sTitle":"代码值","bSortable":false},
			{ "mData": "prop1", "sTitle":"其他属性1","bSortable":false},
			{ "mData": "prop2", "sTitle":"其他属性2","bSortable":false},
			{ "mData": "prop3", "sTitle":"其他属性3","bSortable":false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"30px","bSortable":false},
			{ "mData": "sortno", "sTitle":"排序号","sWidth":"40px","bSortable":false}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			window.location.href = './manage_modify.html';
		}else if(ids && ids.length>1){
			eDialog.info('只能选择一个项目进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.info('请选择至少选择一个项目进行操作！');
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
		window.location.href = './manage_modify.html';
	});
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	$('#btn_code_del').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			eDialog.confirm("您确认删除该字典项目吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						eDialog.success('删除成功！');
						/*
						$.eAjax({
							url : $webroot + 'action/dml?op=code:2&code.id=' + ids[0],
							success : function(returnInfo) {
								if(returnInfo.code){
									
									window.location.reload();
								}
							}
						});
						*/
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(ids && ids.length>1){
			eDialog.info('只能选择一个项目进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.info('请选择至少选择一个项目进行操作！');
		}
	});
});
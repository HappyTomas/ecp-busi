$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/createprom/shoplist',
        'params' : [{name:'status',value:'1'}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"店铺编码","sWidth":"80px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"80px","bSortable":false}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/createprom/ct/'+_ids;
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个店铺进行操作！');
		}else if(!_ids || _ids.length==0){
			 eDialog.alert('请选择至少选择一个店铺进行操作！');
		}
	};
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#status").val('1');//特别处理 置为有效
	});
	
	$('#btn_code_add').click(function(){
		eNav.setSubPageText('创建促销');
		modifyBiz();
	});
});
$(function(){
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/prom/edit';
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!_ids || _ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
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
	
	$('#btnFormSave').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/promauth/add';
	});
 
	
	$("#btnReturn").on("click",function(){
		window.location.href = GLOBAL.WEBROOT+'/promauth/add';
	});
	 
});
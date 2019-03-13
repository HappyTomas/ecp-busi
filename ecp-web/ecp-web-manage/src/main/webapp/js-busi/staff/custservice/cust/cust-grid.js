$(function(){ 
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false, 
        "sAjaxSource": GLOBAL.WEBROOT + '/staffadmin/gridlist',
        //"params":[{name:"staffCode",value:$('#code').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"50px","bSortable":true},
			{ "mData": "aliasName", "sTitle":"昵称","sWidth":"50px","bSortable":false},
			{ "mData": "staffName", "sTitle":"姓名","sWidth":"80px","bSortable":false},
			{ "mData": "staffEmail", "sTitle":"邮箱","sWidth":"80px","bSortable":false},
			{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false}, 
			{ "mData": "staffFlag", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == '1') {
               	 	return "正常";
                } else if (data == '2') {
               	 	return "锁定";
                } else if (data  == '3') {
                	return "失效";
                } else {
                	return "失效";
                }
	        }}
			
        ],
        "eClick" : function(){
//        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();  
		if(val && val.length==1){ 
			bDialog.closeCurrent(val[0]);
		}else if(!val || val.length==0){
			eDialog.alert('请至少选择一个客户进行操作！');
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
	
	$('#btnFormCheck').click(function(){
		modifyBiz();
	});
	
 
});
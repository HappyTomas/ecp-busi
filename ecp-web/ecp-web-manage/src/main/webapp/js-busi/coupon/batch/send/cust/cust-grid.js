$(function(){
	
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	
	$('#btn_code_modify').unbind('click').click(function(){
		modifyBiz();
	});
	
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/cust/gridlist',
        "params":[{name:"companyId",value:$('#ids').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"50px","bSortable":true},
			{ "mData": "nickname", "sTitle":"昵称","sWidth":"50px","bSortable":false},
			{ "mData": "custLevelName", "sTitle":"会员等级","sWidth":"80px","bSortable":false},
			{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false},
			{ "mData": "pccString", "sTitle":"所在区域","sWidth":"80px","bSortable":false},
	        { "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == '1') {
               	 	return "正常";
                } else if (data == '2') {
               	 	return "锁定";
                } else if (data  == '3') {
                	return "失效";
                } else if (data  == '4') {
                	return "临时冻结";
                } else {
                	return "失效";
                }
	        }},
	        { "mData": "createTime", "sTitle":"注册时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
				data = ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
			}}
			
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length>=1){
			var parm=new Object();
			parm._if_query="1";
			parm.rows=ids;
			//bDialog.getDialogCallback(_dlg);
			bDialog.closeCurrent(parm);
			
		}else if(!ids || ids.length==0){
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
	
 
});
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/scoremgr/exchange/gridlist',
        "params":[{name:"staffCode",value:$('#staffCode').val()},{name:"dateFrom",value:$('#dateFrom').val()},{name:"dateEnd",value:$('#dateEnd').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"80px","bSortable":false},
			{ "mData": "orderId", "sTitle":"订单编码","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == null) {
                	return "-";
                } else {
                	return data;
                }
	        }}, 
			{ "mData": "subOrderId", "sTitle":"子订单编码","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == null) {
                	return "-";
                } else {
                	return data;
                }
	        }}, 
			{ "mData": "scoreUsing", "sTitle":"使用积分","sWidth":"80px","bSortable":false},
			{ "mData": "remark", "sTitle":"备注","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == null) {
                	return "-";
                } else {
                	return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
                }
	        }}, 
			{ "mData": "createTime", "sTitle":"兑换时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}}
        ],
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
});
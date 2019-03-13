$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/scoremgr/detail/gridlist',
        "params":[{name:"staffCode",value:$('#staffCode').val()},{name:"dateFrom",value:$('#dateFrom').val()},{name:"dateEnd",value:$('#dateEnd').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"50px","bSortable":false},
			{ "mData": "orderId", "sTitle":"订单编码","sWidth":"60px","bSortable":false,"mRender": function(data,type,row){
                if (data == null) {
                	return "-";
                } else {
                	if (data.indexOf("RW") > -1) {
                		return '<a href="'+ GLOBAL.WEBROOT +'/order/orderdetails?orderId='+data+'" target="_blank">'+data+'</a>';
                	} else {
                		return data;
                	}
                	
                }
	        }}, 
	        { "mData": "createTime", "sTitle":"创建时间","sWidth":"60px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}}, 
			{ "mData": "scoreTypeName", "sTitle":"积分类型","sWidth":"40px","bSortable":false},
			{ "mData": "consumeScore", "sTitle":"积分","sWidth":"40px","bSortable":false},
			{ "mData": "remark", "sTitle":"备注","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
                if (data == null) {
                	return "-";
                } else {
                	return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
                }
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
		//异步查询汇总数据
		$.eAjax({
			url : GLOBAL.WEBROOT + "/scoremgr/detail/total",
			data : {'staffCode':$('#staffCode').val(),
					'selDateFrom':$('#selDateFrom').val(),
					'selDateEnd':$('#selDateEnd').val(),
					'scoreType':$('#scoreType').val(),
					'orderId':$('#orderId').val()
				},
			datatype:'json',
			success : function(info) {
				//赋值
				$("#scoreBalance").val(info.scoreBalance);
				$("#consumeScore").val(info.consumeScore);
				$("#modifyAddScore").val(info.modifyAddScore);
				$("#exchangeScore").val(info.exchangeScore);
				$("#modifyReduceScore").val(info.modifyReduceScore);
			}
		});
	});
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
});
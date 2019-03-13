var bill_grid = { 
		getOrdDetail: function(orderid){
			var url = GLOBAL.WEBROOT+"/order/orderdetails?orderId="+orderid;
			window.open(url);
		},
		reviewOrd:function(orderId){  
    		var isStatus = true;
        	var url = GLOBAL.WEBROOT + "/order/bill/checkReturn";
        	$.eAjax({
    			url:url,
    			data:[{name:"orderId",value:orderId},{name:"oper",value:"00"}],
    			async:false,
    			method:'post',
    			success:function(returnInfo){
    				if(returnInfo&&returnInfo.flag==false){
    					isStatus = false; 
    					eDialog.alert(returnInfo.msg);
    				}
    				
    			}
    		}); 
        	if(isStatus == true){
        		bDialog.open({
    			    title : '开票状态',
    			    width : 400,
    			    height : 270,
    			    scroll : true,
    			    params : {
    			    	orderId:orderId
    			    },
    			    url : GLOBAL.WEBROOT+'/order/bill/review?orderId='+orderId,
    			    callback:function(data){ 
    			    	if(data && data.results && data.results.length > 0 ){
    			    		$('#dataGridTable').gridReload();
    			    	}
    			    }
    			});  
    		} 
			
		},
		getPrintDetail: function(orderId){
			window.open(GLOBAL.WEBROOT+'/order/bill/printDetail?orderId='+orderId);
		} 
		
};
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pCheckColumn' : false,
        'params' : [{name:"begDate",value:$("input[name='begDate']").val()},
                    {name:"endDate",value:$("input[name='endDate']").val()}],
        'pCheckRow' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/order/bill/billlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "orderId", "sTitle":"订单编号","bSortable":true, "sClass":"center", "mRender":function(data,type,row){
				return '<a href="javascript:void(0)" onclick="bill_grid.getOrdDetail(\''+data+'\')">'+data+'</a>';
			}},
			{ "mData": "realMoney", "sTitle":"订单金额","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				return ebcUtils.numFormat(data/100, 2);
			}},
			{ "mData": "orderAmount", "sTitle":"图书册数","bSortable":false, "sClass":"center"}, 
			{ "mData": "invoiceTitle", "sTitle":"发票抬头","bSortable":false, "sClass":"center"},
			{ "mData": "invoiceContent", "sTitle":"发票内容","bSortable":false, "sClass":"center"}, 
			{ "mData": "detailFlag", "sTitle":"是否附加明细","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				if(data == 0){
					return '<span>否</span>';
				}else{
					return '<span>是</span>';
				} 
			}},
			{ "mData": "taxpayerNo", "sTitle":"纳税人识别号","bSortable":false, "sClass":"center"}, 
			{ "mData": "taxpayerNo","sTitle":"订单归属","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				if(data == ''||data==null){
					return '<span>个人</span>';
				}else{
					return '<span>公司</span>';
				} 
			}},
			{ "mData": "billingFlag", "sTitle":"开票状态","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				if(data == 0){
					return '<span>未开票</span>';
				}else{
					return '<span>已开票</span>';
				} 
			}}, 
			{ "mData": "orderId", "sTitle":"操作","bSortable":false, "sClass":"center", "mRender": function(data,type,row){
				var str='';
				if(row.billingFlag == 0){
					str = '<a href="javascript:void(0)" onclick="bill_grid.reviewOrd(\''+data+'\')">开票</a>';
				}
				if(row.detailFlag == 1) {
					if(row.billingFlag == 0) str += ' | ';
					str += '<a href="javascript:void(0)" onclick="bill_grid.getPrintDetail(\''+data+'\')">打印</a>';
				} 
				return str;
			}}
		],
        "eDbClick" : function(){
        	 
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
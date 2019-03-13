$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        "sAjaxSource": GLOBAL.WEBROOT + '/coupuse/grid',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "siteName", "sTitle":"站点","sWidth":"80px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"120px","bSortable":false},
			{ "mData": "orderId", "sTitle":"订单编号","sWidth":"60px","bSortable":false},
			{ "mData": "orderSubId", "sTitle":"子订单编号","sWidth":"60px","bSortable":false},
			{ "mData": "coupNo", "sTitle":"优惠券编码","sWidth":"80px","bSortable":false},
			{ "mData": "coupMoney", "sTitle":"优惠券平摊金额","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){ 
				  if(data){
					  var str = (data/100).toFixed(2) + '';
						var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
						var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
						var ret = intSum + dot;
						return ret;
  				  }else{
  					  return "";
  				  }
  			}},
			{ "mData": "useTime", "sTitle":"使用时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}}
        ],
        "eDbClick" : function(){
        },
        "eClick" : function(data){
         
        }
	});
	
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
});

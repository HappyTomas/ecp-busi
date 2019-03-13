$(function(){

	$("#dataGridTable").initDT({ 
		'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        'pCheckRow' : false,
        'pIdColumn' : 'orderId',
        'pLengthMenu' : [25,10,50,100],
        "sAjaxSource": GLOBAL.WEBROOT + '/order/customer/gridlist',
        'params':[
                  {name:"begDate",value:$("input[name='begDate']").val()},
                  {name:"endDate",value:$("input[name='endDate']").val()},
                  {name:"categoryCode",value:$("input[name='categoryCode']").val()}
        ],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "orderId", "sTitle":"订单编号", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
				return '<a href="javascript:void(0)" onclick="oUtil.getOrdUpdateDetail(\''+data+'\')">'+data+'</a>';
			}},
			{ "mData": "orderDate", "sTitle":"下单日期", "sClass":"center", "bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "payTime", "sTitle":"支付时间", "sClass":"center", "bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			
			{ "mData": "realMoney", "sTitle":"实付金额", "sClass":"center", "bSortable":false, "mRender": function(data,type,row){
				return ebcUtils.numFormat(data/100, 2);
			}},
			{ "mData": "status", "sTitle":"订单状态",  "sClass":"center","bSortable":false, "mRender": function(data,type,row){
				var val = oUtil.constant[data];
				if(val == null){
					val = '';
				}
				return val;
			}},
			{ "mData": "orderTwoStatus", "sTitle":"退款退货标识",  "sClass":"center","bSortable":false, "mRender": function(data,type,row){
				if(data == "0701" ){
					return "退货流程中";
				} else if(data == "0801"){
					return "退款流程中";
				} else {
					return "";
				}
				
			}},
			{ "mData": "contactPhone", "sTitle":"手机号码", "sClass":"center", "bSortable":false},
			{ "mData": "contactName", "sTitle":"收货人姓名", "sClass":"center", "bSortable":false},
			{ "mData": "payType", "sTitle":"支付方式", "sClass":"center", "bSortable":false,  "mRender": function(data,type,row){
				var val = oUtil.constant.pay[data];
				if(val == null){
					val = '';
				}
				return val;
			}},
			{ "mData": "payWay", "sTitle":"支付通道", "sClass":"center", "bSortable":false,  "mRender": function(data,type,row){
				var val = oUtil.constant.payWay[data];
				if(val == null){
					val = '';
					return val;
				}
				return val;
			}},
			{ "mData": "staffName", "sTitle":"会员名", "sClass":"center", "bSortable":false},
			{ "mData": "shopName", "sTitle":"商铺名称",  "sClass":"center","bSortable":false}
			// { "mData": "joinOrderid", "sTitle":"商户订单号",  "sClass":"center","bSortable":false}
			/*{ "mData": "remainAmounts", "sTitle":"剩余发货数量",  "sClass":"center","bSortable":false},
			{ "mData": "orderAmounts", "sTitle":"订购数量", "sClass":"center", "bSortable":false},
			{ "mData": "deliverAmounts", "sTitle":"已发货数量",  "sClass":"center","bSortable":false},
			{ "mData": "contactName", "sTitle":"联系人",  "sClass":"center","bSortable":false},
			{ "mData": "contactPhone", "sTitle":"手机号码", "sClass":"center", "bSortable":false},
			{ "mData": "chnlAddress", "sTitle":"地址", "sClass":"center", "bSortable":false}*/

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
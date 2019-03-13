$(function(){
	//汇总信息查询
	paySum();
	var yh_pay_url = GLOBAL.WEBROOT + '/order/pay/yhPayList';
	var yh_back_url = GLOBAL.WEBROOT + '/order/pay/yhBackList'; 
	var pt_pay_url = GLOBAL.WEBROOT + '/order/pay/ptPayList';
	var pt_back_url = GLOBAL.WEBROOT + '/order/pay/ptBackList'; 
	//银行支付账单
	var yhPayColColuns = [
		 				{ "mData": "payWay", "sTitle":"支付通道名称", "sClass":"center", "bSortable":false,  "mRender": function(data,type,row){
							var val = oUtil.constant.payWay[data];
							if(val == null){
								val = '';
								return val;
							}
							return val;
						}},
		 				{ "mData": "orderId", "sTitle":"订单编号", "sClass":"center","bSortable":false},
		 				{ "mData": "transNo", "sTitle":"支付平台流水号", "sClass":"center","bSortable":false},
		 				{ "mData": "transAmount", "sTitle":"清算金额", "sClass":"center","bSortable":false, "mRender": function(data,type,row){
							return ebcUtils.numFormat(data/100, 2);
						}},
						{ "mData": "orderAmount", "sTitle":"订单金额", "sClass":"center","bSortable":false, "mRender": function(data,type,row){
							return ebcUtils.numFormat(data/100, 2);
						}},
						{ "mData": "payTime", "sTitle":"支付时间", "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						}},
						{ "mData": "auditStatus", "sTitle":"对账状态", "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							if(data=='00'){
								return "正常";
							}else if(data=='01'){
								return "长款";
							}else if(data=='10'){
								return "短款";
							} 
						}},
						{ "mData": "checkDate", "sTitle":"清算日期", "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd");
						}},
						{ "mData": "shopName", "sTitle":"店铺名称", "sClass":"center","bSortable":false},
						{ "mData": "staffName", "sTitle":"会员名", "sClass":"center","bSortable":false},
						{ "mData": "cardNo", "sTitle":"银行卡号", "sClass":"center","bSortable":false},
						{ "mData": "cardName", "sTitle":"银行卡姓名", "sClass":"center","bSortable":false}
						
						
	];  
	//银行退款账单
	var yhBackColColuns = [
		 				{ "mData": "payWay", "sTitle":"支付通道名称", "sClass":"center", "bSortable":false,  "mRender": function(data,type,row){
							var val = oUtil.constant.payWay[data];
							if(val == null){
								val = '';
							}
							return val;
						}},
		 				{ "mData": "orderId", "sTitle":"订单编码", "sClass":"center","bSortable":false},
		 				{ "mData": "transNo", "sTitle":"支付平台流水号", "sClass":"center","bSortable":false},
		 				{ "mData": "refundTransAmount", "sTitle":"实际退款金额", "sClass":"center","bSortable":false, "mRender": function(data,type,row){
							return ebcUtils.numFormat(data/100, 2);
						}},
						{ "mData": "refundOrderAmount", "sTitle":"应退金额", "sClass":"center","bSortable":false, "mRender": function(data,type,row){
							return ebcUtils.numFormat(data/100, 2);
						}},
						{ "mData": "payTime", "sTitle":"退款时间", "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						}},
						{ "mData": "auditStatus", "sTitle":"对账状态", "sClass":"center","bSortable":false,"mRender": function(data,type,row){
							if(data=='00'){
								return "正常";
							}else if(data=='01'){
								return "长款";
							}else if(data=='10'){
								return "短款";
							} 
						}},
						{ "mData": "backId", "sTitle":"退款编号", "sClass":"center","bSortable":false},
						{ "mData": "shopName", "sTitle":"店铺名称", "sClass":"center","bSortable":false},
						{ "mData": "staffName", "sTitle":"会员名", "sClass":"center","bSortable":false},
						{ "mData": "cardNo", "sTitle":"银行卡号", "sClass":"center","bSortable":false},
						{ "mData": "cardName", "sTitle":"银行卡姓名", "sClass":"center","bSortable":false}
	]; 
	
	//支付差异对账单
	var ptPayColColuns = [
						{ "mData": "payWay", "sTitle":"支付通道名称", "sClass":"center", "bSortable":false,  "mRender": function(data,type,row){
							var val = oUtil.constant.payWay[data];
							if(val == null){
								val = '';
							}
							return val;
						}},
						{ "mData": "id", "sTitle":"订单编号", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
							return '<a href="javascript:void(0)" onclick="oUtil.getOrdDetail(\''+data+'\')">'+data+'</a>';
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
						{ "mData": "shopName", "sTitle":"店铺名称",  "sClass":"center","bSortable":false},
						{ "mData": "staffName", "sTitle":"会员名", "sClass":"center", "bSortable":false},
						{ "mData": "payTime", "sTitle":"支付时间", "sClass":"center", "bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						}}
						
	]; 
	
	//退款差异对账单
	var ptyhBackColColuns = [
                        { "mData": "orderId", "sTitle":"订单编号", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
 							return '<a href="javascript:void(0)" onclick="oUtil.getOrdDetail(\''+data+'\')">'+data+'</a>';
 						}},
	                    { "mData": "backId", "sTitle":"退款编号",  "sClass":"center","bSortable":false},
						
						{ "mData": "backMoney", "sTitle":"退款金额", "sClass":"center", "bSortable":false, "mRender": function(data,type,row){
							return ebcUtils.numFormat(data/100, 2);
						}},
						{ "mData": "applyTime", "sTitle":"退款时间", "sClass":"center", "bSortable":false,"mRender": function(data,type,row){
							return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						}},
						{ "mData": "num", "sTitle":"退款数量",  "sClass":"center","bSortable":false},
						{ "mData": "backTypeName", "sTitle":"退款类型名称", "sClass":"center", "bSortable":false},
						{ "mData": "isEntire", "sTitle":"是否整个主订单退货", "sClass":"center", "bSortable":false, "mRender": function(data,type,row){
							var val = "";
							if(data == "0"){
								val = "否";
							}else if(data == "1"){
								val = "是";
							}
							return val;
						}},
						{ "mData": "siteId", "sTitle":"商城", "sClass":"center", "bSortable":false, "mRender": function(data,type,row){
							var val = "";
							if(data == "1"){
								val = "人卫商城";
							}else if(data == "2"){
								val = "积分商城";
							}
							return val;
						}},
						{ "mData": "status", "sTitle":"退货原因", "sClass":"center", "bSortable":false, "mRender": function(data,type,row){
							var val = oUtil.constant.backGdsType[data];
							if(val == null){
								val = '';
							}
							return val;
						}},
						{ "mData": "backDesc", "sTitle":"退货原因备注", "sClass":"center", "bSortable":false},
						{ "mData": "checkDesc", "sTitle":"供货商处理意见描述", "sClass":"center", "bSortable":false}
	]; 
	gridInitDT({url:yh_pay_url,id:"yhPayGridTable",aoColumns:yhPayColColuns,checkColumn:false,tabType:1});
	gridInitDT({url:yh_back_url,id:"yhBackGridTable",aoColumns:yhBackColColuns,checkColumn:false,tabType:2});
	gridInitDT({url:pt_pay_url,id:"ptPayGridTable",aoColumns:ptPayColColuns,checkColumn:false,tabType:3});
	gridInitDT({url:pt_back_url,id:"ptBackGridTable",aoColumns:ptyhBackColColuns,checkColumn:false,tabType:4});
	
	function gridInitDT(json){
		var url = json.url,id=json.id,aoColumns = json.aoColumns,pCheckColumn = json.checkColumn,tabType = json.tabType; 
		var params = [{name:"startTime",value:$("input[name='startTime']").val()},{name:"endTime",value:$("input[name='endTime']").val()},
	                  {name:"begDate",value:$("input[name='startTime']").val()},{name:"endDate",value:$("input[name='endTime']").val()}];
		if(tabType == 1){
			params.push({name:"auditType",value:"01"});
		}else if(tabType == 2){
			params.push({name:"auditType",value:"02"});
		}
		initDT(id,pCheckColumn,url,params,aoColumns);
	} 
	
	function initDT(id,pCheckColumn,url,params,aoColumns){
		$("#"+id).initDT({
	        'pTableTools' : false,
	        'pCheckRow' : false,
	        'pCheckColumn' : pCheckColumn,
	        'pSingleCheckClean' : false,
	        'params':params,
	        "scrollX":true,
	        "sAjaxSource": url,
	        //指定列数据位置
	        "aoColumns": aoColumns,
	        "eDbClick" : function(){
	        	//modifyBiz(json);
	        }
		});
	}
	 
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show'); 
		if(!$("#searchForm").valid()) return false;
		var tab = $(this).attr("href");
		if(tab == "#yhPayTab"){
			$("#auditType").val("01");
			$(".yhTypeName").html("支付");
			paySum();
			$(".yhDiv").show();
			url = GLOBAL.WEBROOT +'/order/pay/exportYhPayExcel';
		}else if(tab == "#yhBackTab"){
			$("#auditType").val("02");
			$(".yhTypeName").html("退款");
			paySum();
			$(".yhDiv").show();
			url = GLOBAL.WEBROOT +'/order/pay/exportYhBackExcel';
		}else if(tab == "#ptPayTab"){
			$(".yhDiv").hide();
			url = GLOBAL.WEBROOT +'/order/pay/exportPtPayExcel';
		}else if(tab == "#ptBackTab"){
			$(".yhDiv").hide();
			url = GLOBAL.WEBROOT +'/order/pay/exportPtBackExcel';
		}
		$("#searchForm").attr("action",url);
  
		/*这个方法是ajax的，后台跳转的，自然和它无关了，难道这就叫ajax跨域吗*/
		/*带了tab之后就必须要获取当前是那个tab的数据*/
		var p = ebcForm.formParams($("#searchForm"));
		var type = getTabId();
		p.push({name:"begDate",value:$("input[name='startTime']").val()},{name:"endDate",value:$("input[name='endTime']").val()});
		$(type+'GridTable').gridSearch(p);
    }); 
	
	$('#btnFormSearch').click(function(){
		
		//console.info(ebcForm.formParams($("#searchForm")));
		if(!$("#searchForm").valid()) return false;
		
		/*这个方法是ajax的，后台跳转的，自然和它无关了，难道这就叫ajax跨域吗*/
		/*带了tab之后就必须要获取当前是那个tab的数据*/
		var p = ebcForm.formParams($("#searchForm"));
		var type = getTabId();
		if(type == "#yhPay"){ 
			$(".yhTypeName").html("支付"); 
			$("#auditType").val("01");
			paySum();
		}else if (type == "#yhBack"){
			$(".yhTypeName").html("退款"); 
			$("#auditType").val("02");
			paySum();
		} 
		p.push({name:"begDate",value:$("input[name='startTime']").val()},{name:"endDate",value:$("input[name='endTime']").val()});
		$(type+'GridTable').gridSearch(p);
		
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btnFormExport').click(function(){
		$("#begDate").val($("#startTime").val());
		$("#endDate").val($("#endTime").val());
		if(!$("#searchForm").valid()) return false;
		$('#searchForm').submit();
		
	});
	
	/**
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#myTab>li.active").find("a").attr("href");
		var _id= type.replace('Tab','');
		return _id;
	} 
	
	function paySum(){
		$.eAjax({
			url : GLOBAL.WEBROOT + '/order/pay/paySumData',
			data :ebcForm.formParams($("#searchForm")),
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
				$("#transOrderCounts").text(datas.transOrderCounts);
				$("#successOrderCounts").text(datas.successOrderCounts);
				$("#transAmounts").text(ebcUtils.numFormat(datas.transAmounts/100, 2));
				$("#successOrderAmounts").text(ebcUtils.numFormat(datas.successOrderAmounts/100, 2));
			}
		});
	}
});
$(function(){
	$("#dataGridTable").initDT({
		'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        'pCheckRow' : false,
        'pIdColumn' : 'orderId',
        'pLengthMenu' : [25,10,50,100],
        "sAjaxSource": GLOBAL.WEBROOT + '/unpforder/list',
        'params':ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
			{ "mData": "outerId", "sTitle":"订单编号", "sClass":"center","bSortable":false},
			{ "mData": "status", "sTitle":"订单状态", "sClass":"center","bSortable":false,"mRender": function(data,type,row){
				var val = oUtil.constant[data];
				if(val == null){
					val = '';
				}
				return val;
			}},
			{ "mData": "orderTime", "sTitle":"下单日期", "sClass":"center", "bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "shopName", "sTitle":"店铺","sWidth":"300px", "sClass":"center", "bSortable":false},
			{ "mData": "platType", "sTitle":"平台类型",  "sClass":"center","bSortable":false, "mRender": function(data,type,row){
				var val = "";
				if(data == "taobao") {
					val = "天猫&淘宝";
				} else if(data == "youzan") {
					val = "有赞";
				} else if(data == "jd") {
					val = "京东";
				}
				return val;
			}},
			{ "mData": "id", "sTitle":"订单详情", "sClass":"center", "bSortable":false,  "mRender": function(data,type,row){
				return '<a href="javascript:void(0)" onclick="window.open(\''+GLOBAL.WEBROOT+"/unpforder/detail?orderId="+data+'\')">'+ "详情" +'</a>';
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
		$("#begDate").val($("#resetBegDate").val());
		$("#endDate").val($("#resetEndDate").val());
	});
	//导出单头
	$("#btnFormExport").click(function(){
		$('#btnFormSearch').trigger('click');

		if(!$("#searchForm").valid()) return false;

		eDialog.confirm("导出订单" , {
			buttons: [{
				'caption': '导出',
				'callback': function () {
					var p = ebcForm.formParams($('#searchForm'));
					//导出限制
					p.push({name:'pageNo',value:1});
					p.push({name:'pageSize',value:10000});
					p.push({name:'exportType',value:'getFileId'});
					$('#exportInfo').val(JSON.stringify(p));
					$("#exportForm").submit();

				}
			}, {
				'caption': '取消',
				'callback': function () {

				}
			}]
		});
	});


	//导出明细
	$("#barCodeExport").click(function(){
		$('#btnFormSearch').trigger('click');

		if(!$("#searchForm").valid()) return false;

		eDialog.confirm("导出订单" , {
			buttons: [{
				'caption': '导出',
				'callback': function () {
					var p = ebcForm.formParams($('#searchForm'));
					//导出限制
					p.push({name:'pageNo',value:1});
					p.push({name:'pageSize',value:10000});
					p.push({name:'exportType',value:'getBarCodeFileId'});
					$('#exportInfo').val(JSON.stringify(p));
					$("#exportForm").submit();

				}
			}, {
				'caption': '取消',
				'callback': function () {

				}
			}]
		});
	});

});
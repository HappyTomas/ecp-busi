$(function(){
	//汇总信息查询
	orderSum();
	
	$("#dataGridTable").initDT({ 
		'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, 
        'pCheckRow' : false,
        'pIdColumn' : 'orderId',
        'pLengthMenu' : [25,10,50,100],
        "sAjaxSource": GLOBAL.WEBROOT + '/order/gridlist',
        'params':[
                  {name:"begDate",value:$("input[name='begDate']").val()},
                  {name:"endDate",value:$("input[name='endDate']").val()},
                  {name:"categoryCode",value:$("input[name='categoryCode']").val()}
        ],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "orderId", "sTitle":"订单编号", "sClass":"center","bSortable":false, "mRender":function(data,type,row){
				return '<a href="javascript:void(0)" onclick="oUtil.getOrdDetail(\''+data+'\')">'+data+'</a>';
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
			{ "mData": "shopName", "sTitle":"商铺名称",  "sClass":"center","bSortable":false},
			{ "mData": "joinOrderid", "sTitle":"商户订单号",  "sClass":"center","bSortable":false}
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
	
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/order/edit';
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
		orderSum();
		
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_add').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/order/edit';
	});
	
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	
	$("#btn_code_more").on("click",function(){
		window.location.href = GLOBAL.WEBROOT+'/order/more';
	});
	
	$("#btn_code_publish").on("click",function(){
		var id = oUtil.getSelectId();
		if(id!=''){
			window.location.href = GLOBAL.WEBROOT+'/ordersub/grid/'+id;
		}
	})
	
	
	$('#categorySearch').click(function(){
		bDialog.open({
	        title : '分类选择',
	        width : 350,
	        height : 550,
	        url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
	        callback:function(data){
	            if(data && data.results && data.results.length > 0 ){
	                var _catgs = data.results[0].catgs;
	                for(var i in _catgs){
	                	$('input[name="category"]').val(_catgs[i].catgName);
	                	$('input[name="categoryCode"]').val(_catgs[i].catgCode);
	                }
	            }
	        }
	    });
	});
	
	$('input[name="category"]').on('focus',function(){
		bDialog.open({
	        title : '分类选择',
	        width : 350,
	        height : 550,
	        url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1",
	        callback:function(data){
	            if(data && data.results && data.results.length > 0 ){
	                var _catgs = data.results[0].catgs;
	                for(var i in _catgs){
	                	$('input[name="category"]').val(_catgs[i].catgName);
	                	$('input[name="categoryCode"]').val(_catgs[i].catgCode);
	                }
	            }
	        }
	    });
	});
	
	$("#btn_code_detail").click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val&&val.length==1){
			window.location.href = GLOBAL.WEBROOT + "/order/orderdetails?orderId="+val[0].orderId;
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个订单进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个订单进行操作！');
		}
	});
	
	/**
	 * 批量打印
	 */
    $('#btnPrint').click(function(){
    	$('#btnFormSearch').trigger('click');
    	if(!$("#searchForm").valid()) return false;
    	$("#searchForm").append("<input type=\"hidden\" id=\"pageSize\" name=\"pageSize\" value=\"1000\">");
    	$("#searchForm").attr("target","_blank");
    	$("#searchForm").attr("method","post");
    	$("#orderId").val($.trim($("#orderId").val()));
    	$("#contactName").val($.trim($("#contactName").val()));
    	$("#staffCode").val($.trim($("#staffCode").val()));
    	$("#searchForm").attr("action",GLOBAL.WEBROOT + '/order/printList');
    	$("#searchForm").submit();
	});

	//导出明细
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
					//导出限制

					$('#exportType').val('getFileId');
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


	//导出条码
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
					//导出限制

					$('#exportType').val('getBarCodeFileId');
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
	function orderSum(){
		$.eAjax({
			url : GLOBAL.WEBROOT + '/order/ordersum',
			data :ebcForm.formParams($("#searchForm")),
			async : true,
			type : "post",
			dataType : "json",
			success : function(datas) {
//				console.info(datas);
				$("#sumOrderMoney").text(ebcUtils.numFormat(accDiv(datas.sumOrderMoney,100),2));
				$("#sumRealMoney").text(ebcUtils.numFormat(accDiv(datas.sumRealMoney,100),2));
				$("#orderCount").text(datas.orderCount);
				$("#payedCount").text(datas.payedCount);
				if(datas.orderCount == 0){
					$("#payedRate").text("0.00");
				} else {
					$("#payedRate").text(ebcUtils.numFormat(accMul(accDiv(datas.payedCount,datas.orderCount),100),2));
				}
			}
		});
	}
});
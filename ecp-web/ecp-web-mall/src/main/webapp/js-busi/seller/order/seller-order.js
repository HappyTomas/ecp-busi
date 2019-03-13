function addLoadParamsToUrl(_load_url_){
	var begDate = $('input[name="begDate"]').val();
    if(begDate != "" && begDate != undefined)
    {
    	_load_url_ += '&begDate=' + begDate;	
    }
    var endDate = $('input[name="endDate"]').val();
    if(endDate != "" && endDate != undefined)
    {
    	_load_url_ += '&endDate=' + endDate;	
    }
    var siteId  = $('select[name="siteId"]').val();
    if(siteId != "" && siteId != undefined)
    {
    	_load_url_ += '&siteId=' + siteId;	
    }
    var status  = $('select[name="status"]').val();
    if(status != "" && status != undefined)
    {
    	_load_url_ += '&status=' + status;	
    }   
    var orderId = $('input[name="orderId"]').val();
    if(orderId != "" && orderId != undefined)
    {
    	_load_url_ += '&orderId=' + orderId;
    }
    var contactName = $('input[name="contactName"]').val();
    if(contactName != "" && contactName != undefined)
    {
    	_load_url_ += '&contactName=' + contactName;
    }
    var invoiceType = $('select[name="invoiceType"]').val();
    if(invoiceType != "" && invoiceType != undefined)
    {
    	_load_url_ += '&invoiceType=' + invoiceType;
    }
    var payType = $('select[name="payType"]').val();
   
    if(payType != "" && payType != undefined)
    {
    	_load_url_ += '&payType=' + payType;
    }
    var staffCode = $('input[name="staffCode"]').val();
    if(staffCode != "" && staffCode != undefined)
    {
    	_load_url_ += '&staffCode=' + staffCode;
    }
    var payFlag = $('select[name="payFlag"]').val();
    if(payFlag != ""  && payFlag != undefined)
    {
    	_load_url_ += '&payFlag=' + payFlag;
    }
    var shopId = $('select[name="shopId"]').val();
    if(shopId != "" && shopId != undefined)
    {
    	_load_url_ += '&shopId=' + shopId;
    }
    var date = new Date();
    _load_url_ += '&t='+date.getTime();
    return _load_url_;
}
//页面初始化模块
$(function(){
	
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		
    		function orderSum(){
    			$.eAjax({
    				url : GLOBAL.WEBROOT + '/seller/order/manage/ordersum',
    				data :ebcForm.formParams($("#searchForm")),
    				async : true,
    				type : "post",
    				dataType : "json",
    				success : function(datas) {
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
    		
    		function queryOrders(){
    			var _load_url_ = '/seller/order/manage/orderlist?_t='+new Date().getTime();
            	//_load_url_ = addLoadParamsToUrl(_load_url_);
//            	alert(_load_url_);
            	$(this).attr("class","active");
                $('#order-contentDiv').load(GLOBAL.WEBROOT + _load_url_, ebcForm.formParams($("#searchForm")));
    		}
    		
    		orderSum();
    		queryOrders();
    		//查询
    		$('#btnFormSearch').click(function(){
    			if(!$('#searchForm').valid())
    				return;
    			orderSum();
        		queryOrders();
    		});	
    		//重置
    		$('#btnFormReset').click(function(){
    			ebcForm.resetForm('#searchForm');
    			$("#begDate").val($("#resetBegDate").val());
    			$("#endDate").val($("#resetEndDate").val());
    		});
    		
    		//批量打印
    	    $('#btnPrint').click(function(){
    	    	$('#btnFormSearch').trigger('click');
    	    	if(!$("#searchForm").valid()) return false;
    	    	$("#searchForm").append("<input type=\"hidden\" id=\"pageSize\" name=\"pageSize\" value=\"1000\">");
    	    	$("#searchForm").attr("target","_blank");
    	    	$("#searchForm").attr("method","post");
    	    	$("#orderId").val($.trim($("#orderId").val()));
    	    	$("#contactName").val($.trim($("#contactName").val()));
    	    	$("#staffCode").val($.trim($("#staffCode").val()));
    	    	$("#searchForm").attr("action",GLOBAL.WEBROOT + '/seller/order/manage/printList');
    	    	$("#searchForm").submit();
    		});

    		//导出明细
    		$("#btnFormExport").click(function(){
//    			$('#btnFormSearch').trigger('click');

    			if(!$("#searchForm").valid()) return false;

    			eDialog.confirm("导出订单" , {
    				buttons: [{
    					'caption': '导出',
    					'callback': function () {
    						var p = ebcForm.formParams($('#searchForm'));
    						//导出限制
    						p.push({name:'pageNo',value:1});
    						p.push({name:'pageSize',value:10000});
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
//    			$('#btnFormSearch').trigger('click');

    			if(!$("#searchForm").valid()) return false;

    			eDialog.confirm("导出订单" , {
    				buttons: [{
    					'caption': '导出',
    					'callback': function () {
    						var p = ebcForm.formParams($('#searchForm'));
    						//导出限制
    						p.push({name:'pageNo',value:1});
    						p.push({name:'pageSize',value:10000});
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
    	};
    	return {
    		init : init
    	};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm','bPage'],
		//指定页面
		init : function(){
			var p = new pInit();
			p.init();
			
		}
	});
	
});



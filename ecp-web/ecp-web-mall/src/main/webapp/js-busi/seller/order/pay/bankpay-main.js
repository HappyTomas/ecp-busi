	/*
	 * 获取当前tab页面的id
	 */
	function getTabId(){
		var type = $("#myBackTab>li.active").attr("id");
		var _id= type;
		return _id;
	}
	function addLoadParamsToPayUrl2(_load_url_){
		var begDate = $('input[name="startTime"]').val();
	    if(begDate != "")
	    {
	    	_load_url_ += '&begDate=' + begDate;
	    	_load_url_ += '&startTime=' + begDate;
	    }
	    var endDate = $('input[name="endTime"]').val();
	    if(endDate != "")
	    {
	    	_load_url_ += '&endDate=' + endDate;
	    	_load_url_ += '&endTime=' + endDate;
	    }
	    var shopId  = $('select[name="shopId"]').val();
	    if(shopId != "")
	    {
	    	_load_url_ += '&shopId=' + shopId;	
	    }
	    return _load_url_;

	}
	function addLoadParamsToPayUrl(_load_url_){
		var date = new Date();
		_load_url_ += 't='+date.getTime();
	    var orderId = $('input[name="orderId"]').val();
	    if(orderId != "")
	    {
	    	_load_url_ += '&orderId=' + orderId;
	    }
		var begDate = $('input[name="begDate"]').val();
	    if(begDate != "")
	    {
	    	_load_url_ += '&begDate=' + begDate;
	    	_load_url_ += '&startTime=' + begDate;
	    }
	    var endDate = $('input[name="endDate"]').val();
	    if(endDate != "")
	    {
	    	_load_url_ += '&endDate=' + endDate;
	    	_load_url_ += '&endTime=' + endDate;
	    }
	    var staffCode = $('input[name="staffCode"]').val();
	    if(staffCode != "")
	    {
	    	_load_url_ += '&staffCode=' + staffCode;
	    }
	    var shopId  = $('select[name="shopId"]').val();
	    if(shopId != "")
	    {
	    	_load_url_ += '&shopId=' + shopId;	
	    }
	    var payWay  = $('select[name="payWay"]').val();
	    if(payWay != "")
	    {
	    	_load_url_ += '&payWay=' + payWay;	
	    }   
	    var auditStatus  = $('select[name="auditStatus"]').val();
	    if(auditStatus != "")
	    {
	    	_load_url_ += '&auditStatus=' + auditStatus;	
	    }   
	    var auditType  = $('input[name="auditType"]').val();
	    if(auditType != "")
	    {
	    	_load_url_ += '&auditType=' + auditType;	
	    }

	    return _load_url_;
	};
//页面初始化模块
$(function(){

	$('#btnFormSearch').click(function(){
		if(!$('#searchForm').valid())
			return;
		var cur_active_tab = getTabId();
		$('#'+cur_active_tab).click();
	});	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		$("#begDate").val($("#resetBegDate").val());
		$("#endDate").val($("#resetEndDate").val());
		$("#startTime").val($("#resetBegDate").val());
		$("#endTime").val($("#resetEndDate").val());
	});
	$('#btnFormExport').click(function(){
		$("#begDate").val($("#startTime").val());
		$("#endDate").val($("#endTime").val());
		if(!$("#searchForm").valid()) return false;
		$('#searchForm').submit();
		
	});
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		
    		/*选项卡事件注册*/
    		//银行支付账单
            $('#yhPayTab').click(function(){
            	var yh_pay_url = GLOBAL.WEBROOT + '/seller/order/bankpay/yhPayList?';
            	var yh_pay_export_url = GLOBAL.WEBROOT +'/seller/order/bankpay/exportYhPayExcel';

            	$(".yhDiv").show();
            	$(".yhTypeName").html("支付");
            	$("#auditType").attr("value", "01");
            	yh_pay_url = addLoadParamsToPayUrl(yh_pay_url);
            	paySum();
            	$(this).attr("class","active");
            	$('#ptPayTab').attr("class","");
            	$('#yhBackTab').attr("class","");
            	$('#ptBackTab').attr("class","");
            	$('#tabContentDiv').load(yh_pay_url);
            	$("#searchForm").attr("action",yh_pay_export_url);
            });
	    	
	    	//支付差异对账单
            $('#ptPayTab').click(function(){
            	var pt_pay_url = GLOBAL.WEBROOT + '/seller/order/bankpay/ptPayList?';
            	var pt_pay_export_url = GLOBAL.WEBROOT +'/seller/order/bankpay/exportPtPayExcel';

            	$(".yhDiv").hide();
            	$(".yhTypeName").html("支付");
            	pt_pay_url = addLoadParamsToPayUrl2(pt_pay_url);
        	    
            	$(this).attr("class","active");
            	$('#yhPayTab').attr("class","");
            	$('#yhBackTab').attr("class","");
            	$('#ptBackTab').attr("class","");
            	$('#tabContentDiv').load(pt_pay_url);
            	$("#searchForm").attr("action",pt_pay_export_url);

            });
	    	//银行退款账单
            $('#yhBackTab').click(function(){
            	var yh_back_url = GLOBAL.WEBROOT + '/seller/order/bankpay/yhBackList?'; 
            	var yh_back_export_url = GLOBAL.WEBROOT +'/seller/order/bankpay/exportYhBackExcel';

            	$(".yhDiv").show();
            	$(".yhTypeName").html("退款");
            	$("#auditType").attr("value", "02");
            	yh_back_url = addLoadParamsToPayUrl(yh_back_url);
            	paySum();
            	$(this).attr("class","active");
            	$('#yhPayTab').attr("class","");
            	$('#ptPayTab').attr("class","");
            	$('#ptBackTab').attr("class","");
            	$('#tabContentDiv').load(yh_back_url);
            	$("#searchForm").attr("action",yh_back_export_url);

            });          
            
	    	//退款差异对账单
            $('#ptBackTab').click(function(){
            	var pt_back_url = GLOBAL.WEBROOT + '/seller/order/bankpay/ptBackList?'; 
            	var pt_back_export_url = GLOBAL.WEBROOT +'/seller/order/bankpay/exportPtBackExcel';

            	$(".yhDiv").hide();
            	$(".yhTypeName").html("退款");
            	pt_back_url = addLoadParamsToPayUrl2(pt_back_url);
        	  
            	$(this).attr("class","active");
            	$('#yhPayTab').attr("class","");
            	$('#ptPayTab').attr("class","");
            	$('#yhBackTab').attr("class","");
            	$('#tabContentDiv').load(pt_back_url);
            	$("#searchForm").attr("action",pt_back_export_url);

            });          
            //初始化完成后，加载数据
    		var cur_active_tab = getTabId();
    		$('#'+cur_active_tab).click();
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
	function paySum(){
		var load_pay_sum_url = GLOBAL.WEBROOT + '/seller/order/bankpay/paySumData?'; 

		$.eAjax({
			url : addLoadParamsToPayUrl(load_pay_sum_url),
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
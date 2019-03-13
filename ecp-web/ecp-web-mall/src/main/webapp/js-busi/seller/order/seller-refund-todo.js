
//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
    		var _load_url_ = '/seller/order/refund/querytodo?';
    		_load_url_= addLoadParamsToUrl(_load_url_);
	    	//分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + _load_url_,
	    	    asyncLoad : true,
	    	    asyncTarget : '#order-contentDiv',
	    	    params : {

	    	    }
	    	});
		
	    	//退款审核
			$('.refundReview').click(function(){
				var $form = $("#backreviewForm");
				$form.attr("action",GLOBAL.WEBROOT + '/seller/order/refund/review');
				$form.attr("method","post");
				var tr = $(this).closest('tr');
				var orderId = $(".orderId",$(tr)).text();
				var backId = $(".backId",$(tr)).text();
	//			alert(orderId+backId)
				var contentHTML="";
				contentHTML = "<input type=\"hidden\" id=\"orderId\" name=\"orderId\" value=\""+ orderId +"\">";
				contentHTML = contentHTML + "<input type=\"hidden\" id=\"backId\" name=\"backId\" value=\""+ backId +"\">";
				$form.html(contentHTML);
				$form.submit();
	        });
			//确认退款
			$('.refundConfirm').click(function(){
				var tr = $(this).closest('tr');
				var orderId = $(".orderId",$(tr)).text();
				var backId = $(".backId",$(tr)).text();
				var payType = $("input[name='payType']",$(tr)).val();
				if(payType=="0"){ //线下支付
					bDialog.open({
						title : '确认退款',
						width : 650,
						height : 370,
						params : {
					    },
						url:GLOBAL.WEBROOT + '/seller/order/refund/queryRefund?backId='+backId+'&orderId='+orderId,
						callback:function(data){
							if(data && data.results && data.results.length > 0 ){
								if(data.results[0].result == "ok"){
									var cur_active_tab = getTabId();
						    		$('#'+cur_active_tab).click();
								}
							}
						}
					});
				} else if(payType == "1"){ //线上支付
					var data = [];
					data.push({name:'orderId',value:orderId},
							{name:'backId',value:backId}
							);
					$.eAjax({
						url : GLOBAL.WEBROOT + '/seller/order/refund/onlineRefund',
						data :data,
						async : false,
						type : "post",
						dataType : "json",
						success : function(result) {
							if(result.refundMethod == "01"){  //不跳页面
								eDialog.alert(result.message,function(){
								},'confirmation');
							}else{  //跳页面
								var actionUrl = result.payRequestData.actionUrl;
								var method = result.payRequestData.method;
								var charset = result.payRequestData.charset;
								var formData = result.payRequestData.formData;
								requestPayment.submitPayData(actionUrl,method,charset,formData);
							}
						},
						failure:function(){
						}
					});
					
				} else {
					return;
				}
	        });
			//退款详情
			$('.backId').click(function(){
				var url =  "";
	        	var siteUrl  = $.trim($("#site1").val());
	        	var tr = $(this).closest('tr');
	        	var orderId = $(".orderId",$(tr)).text();
				var backId = $(".backId",$(tr)).text();
				var siteId =  $("input[name='siteId']",$(tr)).val();
	        	if(siteId == 1){
	        		url = siteUrl+"/order/return/returnMoney/"+ backId+"/"+orderId;
	        	} else if(siteId == 2){
	        		url = siteUrl+"/order/point/return/returnMoney/"+ backId+"/"+orderId;
	        	}
	        	window.open(url);
	        	
	        });
			//退款详情
			$('.seebackId').click(function(){
				var url =  "";
	        	var siteUrl  = $.trim($("#site1").val());
	        	var tr = $(this).closest('tr');
	        	var orderId = $(".orderId",$(tr)).text();
				var backId = $(".backId",$(tr)).text();
				var siteId =  $("input[name='siteId']",$(tr)).val();
	        	if(siteId == 1){
	        		url = siteUrl+"/order/return/returnMoney/"+ backId+"/"+orderId;
	        	} else if(siteId == 2){
	        		url = siteUrl+"/order/point/return/returnMoney/"+ backId+"/"+orderId;
	        	}
	        	window.open(url);
	        	
	        });
			//订单详情
			$('.orderId').click(function(){
				var url =  "";
	        	var tr = $(this).closest('tr');
	        	var orderId = $(".orderId",$(tr)).text();
	        	url = GLOBAL.WEBROOT+"/seller/order/orderdetail/detail/"+orderId;
	        	window.open(url);
	        });
    	};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage'],
		//指定页面
		init : function(){
			var scoreList = new pInit();
			scoreList.init();
		}
	});
});

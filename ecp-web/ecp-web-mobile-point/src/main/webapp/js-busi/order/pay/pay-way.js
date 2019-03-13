var formData;
$(function(){
	/**
	 * 发起支付
	 * @param payWay 支付通道编码
	 * @param orderId 订单ID
	 * @returns {Boolean}
	 */
	$('#btn_payment').click(function(){
		var obj = $("input[name='strRadio']:checked");
		if(!obj || obj.length == 0){
			eDialog.alert('请选择支付通道再提交！');
			return false;
		}
		$('body').append('<div class="loading-lo"></div>');
		var payWay = obj.val();
		var orderIds = $("#orderIds").val();
		if (payWay != "" && orderIds != "") {
			var data = {
				"orderIds" : orderIds,
				"payWay" : payWay,
				"oper" : "01"
			};
			var url = GLOBAL.WEBROOT + '/pay/requestPayment';
			$.eAjax({
				url:url,
				data:data,
				success:function(data){
					$('.loading-lo').remove();
					if(data.flag == true){
						if(data.actionUrl==""){
							eDialog.alert("系统异常，请求表单URL为空！");
							return ;
						}
						if (data.charset == "") {
							eDialog.alert("系统异常，请求表单字符集为空！");
							return;
						}
						if (data.actionUrl == '' || data.actionUrl == undefined) {
							formData = data.formData;

							/**
							 * 微信内置
							 *
							 */
							function onBridgeReady() {
								var getBrandWCPayRequest = {};
								for (var key in formData) {
									var value = "";
									if (formData[key] != null) {
										value = formData[key];
									}
									getBrandWCPayRequest[key] = value;
								}
								WeixinJSBridge.invoke(
									'getBrandWCPayRequest',
									getBrandWCPayRequest,
									function (res) {
										if (res.err_msg == "get_brand_wcpay_request:ok") {//即时处理，跳转首页
											window.location.href = GLOBAL.WEBROOT+'/payresult/9006?orderId='+orderIds;
										}else{
											//window.location.reload();
											window.location.href = GLOBAL.WEBROOT+'/pay/queryWay?orderIds='+orderIds;
										}
									}
								);
							}

							if (typeof WeixinJSBridge == "undefined") {
								if (document.addEventListener) {
									document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
								} else if (document.attachEvent) {
									document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
									document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
								}
							} else {
								onBridgeReady();
							}

						} else {
							submitPayData(data.actionUrl, data.method, data.charset, data.formData);
						}

					}else{
						eDialog.alert(data.msg);
					}

				},
				failure : function(error) {
					eDialog.alert(error);
				}
			});
		}

	});


	/**
	 * 生成支付页面，并自动提交支付报文
	 * @param actionUrl 网银接口地址
	 * @param charset 接口规定的编码格式
	 * @param url 当前页面URL
	 * @param formData 订单支付数据
	 * @return
	 */
	function submitPayData(actionUrl,method,charset,formData) {
		var setMethod = "post";
		if(method!= null &&method!= "" && charset!='method'){
			setMethod = method;
		}

		var $form = $("#netPayForm");
		$form.attr("action",actionUrl);
		$form.attr("method",setMethod);
		if(charset!="" && charset!='undefined'){
			$form.attr("accept-charset",charset);
			$form.attr("onsubmit","document.charset='"+charset+"';return true;");
		}
		var contentHTML="";
		for(var key in formData) {
			var value = "";
			if (formData[key] != null) {
				value = formData[key];
			}
			contentHTML = contentHTML + "<input type=\"hidden\" id=\""+key+"\" name=\""+key+"\" value=\""+ value +"\">";
		}
		$form.html(contentHTML);
		$form.submit();
	}

	$("#btn_point").click(function () {
		window.location.replace(GLOBAL.WEBROOT + '/point/order/my');
	});

});



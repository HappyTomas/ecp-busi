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

								var getBrandWCPayRequest = {};
								for (var key in formData) {
									var value = "";
									if (formData[key] != null) {
										value = formData[key];
									}
									getBrandWCPayRequest[key] = value;
								}
							   wx.config({
								    debug: false,
								    appId: getBrandWCPayRequest['appId'], // 必填，公众号的唯一标识
								    timestamp: getBrandWCPayRequest['timeStamp'], // 必填，生成签名的时间戳
								    nonceStr: getBrandWCPayRequest['nonceStr'], // 必填，生成签名的随机串
								    signature: getBrandWCPayRequest['paySign'], // 必填，微信签名
								    jsApiList: [
								      'chooseWXPay'
								    ] // 必填，需要使用的JS接口列表
								});
								wx.chooseWXPay({
									appId: getBrandWCPayRequest['appId'],
									timestamp: getBrandWCPayRequest['timeStamp'], // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
									nonceStr: getBrandWCPayRequest['nonceStr'], // 支付签名随机串，不长于 32 位
									package: getBrandWCPayRequest['package'], // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
									signType: getBrandWCPayRequest['signType'], // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
									paySign: getBrandWCPayRequest['paySign'], // 支付签名
									success: function (res) {
									    if(res.errMsg == "chooseWXPay:ok" ) {
									        //支付成功
									    	window.location.href = GLOBAL.WEBROOT+'/payresult/9006?orderId='+orderIds;
									    }else{
									    	window.location.href = GLOBAL.WEBROOT+'/pay/queryWay?orderIds='+orderIds;
									    }
									},
									cancel:function(res){
									    //支付取消
									}
								});

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


});



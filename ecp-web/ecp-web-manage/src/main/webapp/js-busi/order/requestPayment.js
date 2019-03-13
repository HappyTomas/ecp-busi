	var requestPayment ={	
		/**
		 * 生成支付页面，并自动提交支付报文
		 * @param actionUrl 网银接口地址
		 * @param charset 接口规定的编码格式
		 * @param url 当前页面URL
		 * @param formData 订单支付数据
		 * @return
		 */
		submitPayData: function (actionUrl,method,charset,formData) {
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
	}; 
	

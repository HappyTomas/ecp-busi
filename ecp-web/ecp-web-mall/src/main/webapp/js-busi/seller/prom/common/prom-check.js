var promCheck = {
		//价格格式
		priceNumber : function(value) {
			var decimal = /^\d+(\.\d{1,2})?$/;
			return decimal.test(value);
		}
	}	
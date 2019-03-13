$(function(){ 
	
});

var discountRuleFun={
	      //保存 提交验证
		 valid:function(){
			    var _reData=new Object();
			        _reData.value=false;//返回默认 失败
			    var fixedPrice=$('#fixedPrice').val();    
		        if (!promCheck.priceNumber(fixedPrice)){
		        	eDialog.alert('促销规则-固定单价格式不正确，请最多保留两位小数。',null);
			  		 _reData.value=false;//返回默认 失败
				  		return _reData;
		        }
		        _reData.value=true;
		  		return _reData;
		 }
};
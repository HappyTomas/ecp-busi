$(function(){ 


});

var discountRuleFun={
	      //保存 提交验证
		 valid:function(){
				var buyX = parseInt($("#buyX").val());
				var sendY = parseInt($("#sendY").val());
			    var _reData=new Object();
		        _reData.value=false;//返回默认 失败
		        if (buyX <= sendY ){
		        	eDialog.alert('配置的免费数量大于等于购买数量，不合法！',null);
			  		 _reData.value=false;//返回默认 失败
				  		return _reData;
		        }
		        _reData.value=true;
		  		return _reData;
		 }
};
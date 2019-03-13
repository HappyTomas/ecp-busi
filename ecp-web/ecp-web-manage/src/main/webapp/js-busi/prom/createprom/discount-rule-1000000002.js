$(function(){ 
	   //初始化优惠券基本信息
	    discountRuleFun.queryCoupInfo();
	    //优惠券查询按钮
		$('#btnCoupQuery').unbind('click').click(function(){ 
			var _shopId=$('#shopId').val();
			discountRuleFun.queryCoup(_shopId);
		});
});

var discountRuleFun={
	      //保存 提交验证
		 valid:function(){
			    var _reData=new Object();
			        _reData.value=false;//返回默认 失败
			    var orderMoney=$('#orderMoney').val();    
		        if (!promCheck.priceNumber(orderMoney)){
		        	eDialog.alert('促销规则-订单金额格式不正确，请最多保留两位小数。',null);
			  		 _reData.value=false;//返回默认 失败
				  		return _reData;
		        }
		        _reData.value=true;
		  		return _reData;
		 },
	  //查询优惠券
	  queryCoup:function(_shopId){
				bDialog.open({
					title : '优惠券选择',
					width : 800,
					height : 560,
					url : GLOBAL.WEBROOT + "/promcoup/select?shopId="+_shopId,
					callback:function(data){
						   //确定 按钮_if_query==1
						   if(data && data.results && data.results[0]._if_query=='1'){
							   if($('#coupCodeFlag').val()=='false' || data.results[0].row[0].coupCode==null){
								   $('#coupId').val(data.results[0].row[0].id);
								   $('#coupName').val(data.results[0].row[0].coupName);
								   var value="";
								   if(data.results[0].row[0].coupValue<=0){
									   value= "抵用券";
								   }else{
									   value= data.results[0].row[0].coupValue/100;
								   }
								   $('#coupValue').val(value);
								   }else{
									   var _reData=new Object();
								        _reData.value=false;//返回默认 失败
									   eDialog.alert('优惠码不能赠送，请选择优惠券（不包含优惠码的）。',null);
								  		 _reData.value=false;//返回默认 失败
									  		return _reData;
								   }
						   }
					}
				});
			},
		  //查询优惠券明细
		  queryCoupInfo:function(){ 
			  var _coupId=$('#coupId').val();  
				//ajax请求
				$.eAjax({
					url : GLOBAL.WEBROOT + "/promcoup/coupinfo?coupId="+_coupId,
					success : function(returnInfo) {
						  $('#coupName').val(returnInfo.coupName);
						       var value="";
						       if(returnInfo.coupValue!=null){
						    	   if(returnInfo.coupValue<=0){
			          					  value= "抵用券";
			          				  }else{
			          					  value= returnInfo.coupValue/100;
			          				  }
						       }
							   $('#coupValue').val(value);
					}
				});
		  }
};
//页面初始化模块
$(function(){
	$('#btn_pay').click(function(){
		var obj = $(".o-pay-box input[radioName='strRadio']:checked");
		var onlineKey = $('#onlineKey').val();
		if(obj && obj.length >= 1){
			var orderStr = "";
			for(var i = 0; i < obj.length; i++){
				orderStr += obj[i].value;
				if(i < obj.length -1 ){
					orderStr += ",";
				}
			}
			orderIds = orderStr;
			window.location.href = GLOBAL.WEBROOT+'/pay/way?orderIds='+orderStr+'&onlineKey='+onlineKey;
		}else if(!obj || obj.length==0){
			eDialog.alert('请选择一个订单进行操作！');
		}
	});
	// 全选复选框
	$("#allChecked").change(function(){ 
		$(".addCheck").prop("checked", this.checked); 
	}); 
});
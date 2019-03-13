//页面初始化模块
$(function(){ 
	$('#btn_pay').click(function(){
		var obj = $(".order-sb-list input[radioName='strRadio']:checked");
		if(obj && obj.length>=1){
			var orderStr = "";
			for(var i = 0;i<obj.length;i++){
				var objI = obj[i];
				orderStr += objI.value.split(",")[1];
				if(i < obj.length -1 ){
					orderStr += ":";
				}
			}
			orderIds = orderStr;
			window.location.href = GLOBAL.WEBROOT+'/pay/way?&orderIds='+orderStr;
		}else if(!obj || obj.length==0){
			eDialog.alert('请选择一个订单进行操作！');
		}
	});
	$("#allChecked").live('click',function(){
		if($(this).attr("checked")=="checked"){
			$(".order-sb-list .addCheck").each(function(){
				$(this).attr("checked","checked");
			});
		}else{
			$(".order-sb-list .addCheck").each(function(){
				$(this).removeAttr("checked");
			});
		}
	});	 
});
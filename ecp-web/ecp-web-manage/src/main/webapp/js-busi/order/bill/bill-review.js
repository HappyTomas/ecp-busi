$(function(){
	$("input[name='sendInvoiceType']").click(function(){ 
		if($('input[name="sendInvoiceType"]:checked').val() == "0"){
			$("#expressNoDiv").hide();
		}else{
			$("#expressNoDiv").show();
		}
	});
	$('#btnSave').click(function(){ 
		if(!$('#reviewForm').valid()) return false;
		var data = [];
		data.push({name:'orderId',value:$('#orderId').val()},
				{name:'sendInvoiceType',value:$('input[name="sendInvoiceType"]:checked').val()},
				{name:'invoiceExpressNo',value:$('#invoiceExpressNo').val()});
		$.eAjax({
			url:GLOBAL.WEBROOT+'/order/bill/billsave',
			data:data,
			success:function(result){
				if(result&&result.resultFlag=='ok'){
					bDialog.closeCurrent({result:'ok'}); 
				}else{
					eDialog.alert(result.resultMsg);
				}
			},
			failure:function(){
				bDialog.closeCurrent();
			}
		});
		       
	});  
	$("#btnReturn").click(function(){ 
    	bDialog.closeCurrent();
    }); 
});
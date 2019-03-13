$(function(){
	var constant = {
			allow:1,
			unallow:2
	};

	var url = GLOBAL.WEBROOT+'/seller/order/pay/check';
	
	$('#allow').click(function(){

		if(!$('#reviewForm').valid()) return false;
		var data = [];
		
		data.push({name:'staffId', value:$('#reivewForm_staffId').val()});
		data.push({name:'orderId', value:$('#reivewForm_orderId').val()});
		data.push({name:'shopId', value:$('#reivewForm_shopId').val()});
		data.push({name:'offlineNo', value:$('#reivewForm_offlineNo').val()});
		data.push({name:'status',value:constant.allow});
		data.push({name:'checkCont',value:$('#checkCont').val()});
		$.eAjax({
			url:GLOBAL.WEBROOT+'/seller/order/pay/offlinesave',
			data:data,
			success:function(result){
				bDialog.closeCurrent({result:'ok'});
				if(result&&result.resultFlag=='ok'){

					eDialog.success('审核成功',{
						buttons:[{
							caption:"确定",
							callback:function(){
								bDialog.closeCurrent();
					        }
						}]
					}); 
				}else{
					eDialog.success(result.resultMsg,{
						buttons:[{
							caption:"确定",
							callback:function(){
								bDialog.closeCurrent();
					        }
						}]
					}); 
				}

			},
			failure:function(){
				bDialog.closeCurrent();
			}
		});
		       
	});
		
	
	$('#unallow').click(function(){

		if(!$('#reviewForm').valid()) return false;
		var data = [];
		data.push({name:'staffId', value:$('#reivewForm_staffId').val()});
		data.push({name:'orderId', value:$('#reivewForm_orderId').val()});
		data.push({name:'offlineNo', value:$('#reivewForm_offlineNo').val()});
		data.push({name:'status',value:constant.unallow});
		data.push({name:'checkCont',value:$('#checkCont').val()});
		$.eAjax({
			url:GLOBAL.WEBROOT+'/seller/order/pay/offlinesave',
			data:data,
			success:function(result){
				bDialog.closeCurrent({result:'ok'});
				if(result&&result.resultFlag=='ok'){

					eDialog.success('审核不通过',{
						buttons:[{
							caption:"确定",
							callback:function(){
								bDialog.closeCurrent();
					        }
						}]
					}); 
				}else{
					eDialog.success(result.resultMsg,{
						buttons:[{
							caption:"确定",
							callback:function(){
								bDialog.closeCurrent();
					        }
						}]
					}); 
				}

			},
			failure:function(){
				bDialog.closeCurrent();
			}
		});
	});
});
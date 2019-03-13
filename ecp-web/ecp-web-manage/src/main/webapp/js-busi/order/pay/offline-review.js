$(function(){
	var constant = {
			allow:1,
			unallow:2
	};

	var url = GLOBAL.WEBROOT+'/order/pay/offlinegrid2?shopId='+$('#offline_grid_shopId').val();

	$('#allow').click(function(){
		var dlg = bDialog.getDialog();
		var params = bDialog.getDialogParams(dlg);
		
		if(!$("#reviewForm").valid())return false;
		$.gridLoading({"message":"提交信息...."});//遮罩
		$('input[name="status"]').val(constant['allow']);
		
		eDialog.confirm("审核订单" , {
		    buttons : [{
		        'caption' : '通过',
		        'callback' : function(){
		        	$.eAjax({
		    			url:GLOBAL.WEBROOT+'/order/pay/offlinesave',
		    			data:ebcForm.formParams('#reviewForm'),
		    			success:function(result){
		    				$.gridUnLoading();
		    				if(result&&result.resultFlag=='ok'){
		    					eDialog.alert("审核通过",function(){
		    						window.location.href = url;
		    					},'success');
		    				}else{
								eDialog.alert("审核通过",function(){
									window.location.href = url;
								},'success');
		    				}
		    			},
		    			failure:function(){
		    				$.gridUnLoading();
		    			}
		    		});
		        }
		    },{
		        'caption' : '取消',
		        'callback' : function(){
		            
		        }
		    }]
		});
		
		
	});
	
	$('#unallow').click(function(){
		if(!$("#reviewForm").valid())return false;
		$.gridLoading({"message":"提交信息...."});//遮罩
		$('input[name="status"]').val(constant['unallow']);
		$.eAjax({
			url:GLOBAL.WEBROOT+'/order/pay/offlinesave',
			data:ebcForm.formParams('#reviewForm'),
			success:function(result){
				$.gridUnLoading();
				if(result&&result.resultFlag=='ok'){
					eDialog.alert("操作成功",function(){
						window.location.href = url;
					},'success');
				}else{
					eDialog.alert(result.resultMsg);
				}
			},
			failure:function(){
				$.gridUnLoading();
			}
		});
	});
});

$(function(){
	
	$('#allow').click(function(){
		var orderId = $('#orderId').val();
		var backId = $('#backId').val();
		var payWay = $("input[name='payway']:checked").val();
		var checkDesc = $('#checkDesc').val();
		var applyType = $('#applyType').val();
		var shareInfo = $('#shareInfo').val();
		
		var data = [];
		data.push({name:'payType',value:payWay});
		data.push({name:'checkDesc',value:checkDesc});
		data.push({name:'orderId',value:orderId});
		data.push({name:'backId',value:backId});
		data.push({name:'status',value:"1"});
		data.push({name:'shareInfo',value:shareInfo});
		var reviewUrl = null;
		if(applyType == "0"){
			reviewUrl = GLOBAL.WEBROOT+'/ordrefund/confirmReview';
		} else if(applyType == "1"){
			reviewUrl = GLOBAL.WEBROOT+'/ordback/confirmReview';
		} else {
			return ;
		}
//		console.log(data);
		$.eAjax({
			url:reviewUrl,
			data:data,
			success:function(result){
				if(result&&result.resultFlag=='ok'){
					
					eDialog.alert('审核成功',function(){
						if(applyType == "0"){
							window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
						} else if(applyType == "1"){
							window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
						}
						bDialog.closeCurrent({result:'ok'});
					},'confirmation');
				}else{
					eDialog.alert(result.resultMsg,function(){
//						window.location.href = GLOBAL.WEBROOT+'/ordback/review';
						if(applyType == "0"){
							window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
						} else if(applyType == "1"){
							window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
						}
						bDialog.closeCurrent({result:'ok'});
					},'error');
					
				}
			},
			failure:function(){
				if(applyType == "0"){
					window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
				} else if(applyType == "1"){
					window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
				}
				bDialog.closeCurrent();
			}
		});
		       
	});
	
	$('#update_money_btn').click(function(){
		var ordId=$('#orderId').val();
		var scale = $('#scale').val();
		var backId = $('#backId').val();
		var backMoney = $('#backMoney').val();
		var reduCulateScore = $('#backScore').val();
		var staffId = $('#staffId').val();
		var isLastFlag = $('#isLastFlag').val();
	
		bDialog.open({
		    title : '调整退款金额',
		    width : 550,
		    height : 300,
		    scroll : false,
		    url : GLOBAL.WEBROOT+'/ordback/goModifyMoney?orderId=' + ordId+ '&scale=' + scale+ '&backId=' + backId+ '&backMoney=' + backMoney+ '&reduCulateScore=' + reduCulateScore+ '&staffId=' + staffId+ '&lastFlag=' + isLastFlag,
			callback:function(data){
				
			},		   
		});
	});
	
	$('#unallow').click(function(){
		var orderId = $('#orderId').val();
		var backId = $('#backId').val();
		var payWay = $("input[name='payway']:checked").val();
		var checkDesc = $('#checkDesc').val();
		var applyType = $('#applyType').val();
		
		var data = [];
//		alert(checkDesc);
		if(!checkDesc || checkDesc == ''){
			eDialog.alert("审核意见不能为空",function(){
			},'error');
			return;
		}
		
		data.push({name:'payType',value:payWay});
		data.push({name:'checkDesc',value:checkDesc});
		data.push({name:'orderId',value:orderId});
		data.push({name:'backId',value:backId});
		data.push({name:'status',value:"0"});
		var reviewUrl = null;
		if(applyType == "0"){
			reviewUrl = GLOBAL.WEBROOT+'/ordrefund/confirmReview';
		} else if(applyType == "1"){
			reviewUrl = GLOBAL.WEBROOT+'/ordback/confirmReview';
		} else {
			return ;
		}
//		console.log(data);
		$.eAjax({
			url:reviewUrl,
			data:data,
			success:function(result){
				if(result&&result.resultFlag=='ok'){
					eDialog.alert('【审核不通过】操作成功',function(){
						if(applyType == "0"){
							window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
							
						} else if(applyType == "1"){
							window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
						}
						bDialog.closeCurrent({result:'ok'});
					},'confirmation');
				}else{
					eDialog.alert(result.resultMsg,function(){
//						window.location.href = GLOBAL.WEBROOT+'/ordback/review';
						if(applyType == "0"){
							window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
						} else if(applyType == "1"){
							window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
						}
						bDialog.closeCurrent();
					},'error');
					
				}
			},
			failure:function(){
				if(applyType == "0"){
					window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
				} else if(applyType == "1"){
					window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
				}
				bDialog.closeCurrent();
			}
		});
	});
	$('#return').click(function(){
		var applyType = $('#applyType').val();
		if(applyType == "0"){
			window.location.href = GLOBAL.WEBROOT+'/ordrefund/refund';
			
		} else if(applyType == "1"){
			window.location.href = GLOBAL.WEBROOT+'/ordback/backgds';
		}
	});
	
	//返回
	$('#btnReturn').click(function(){
		 history.go(-1);
	});
});
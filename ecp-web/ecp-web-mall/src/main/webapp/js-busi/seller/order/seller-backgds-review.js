
//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
    		$('#btn_pass').click(function(){
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
    			var reviewUrl = GLOBAL.WEBROOT+'/seller/order/backgds/confirmReview';
//    			console.log(data);
    			$.eAjax({
    				url:reviewUrl,
    				data:data,
    				success:function(result){
    					if(result&&result.resultFlag=='ok'){
    						
    						eDialog.alert('审核成功',function(){
    							window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    							bDialog.closeCurrent({result:'ok'});
    						},'confirmation');
    					}else{
    						eDialog.alert(result.resultMsg,function(){
    							window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    							bDialog.closeCurrent({result:'ok'});
    						},'error');
    						
    					}
    				},
    				failure:function(){
    					window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    					bDialog.closeCurrent();
    				}
    			});
    			       
    		});
    		$('#btn_unallow').click(function(){
    			var orderId = $('#orderId').val();
    			var backId = $('#backId').val();
    			var payWay = $("input[name='payway']:checked").val();
    			var checkDesc = $('#checkDesc').val();
    			var applyType = $('#applyType').val();
    			
    			var data = [];
//    			alert(checkDesc);
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
    			var reviewUrl = GLOBAL.WEBROOT+'/seller/order/backgds/confirmReview';
//    			console.log(data);
    			$.eAjax({
    				url:reviewUrl,
    				data:data,
    				success:function(result){
    					if(result&&result.resultFlag=='ok'){
    						eDialog.alert('【审核不通过】操作成功',function(){
    							window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    							bDialog.closeCurrent({'result':'ok'});
    						},'confirmation');
    					}else{
    						eDialog.alert(result.resultMsg,function(){
    							window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    							bDialog.closeCurrent();
    						},'error');
    						
    					}
    				},
    				failure:function(){
    					window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    					bDialog.closeCurrent();
    				}
    			});
    		});
    		$('#btn_return').click(function(){
    			window.location.href = GLOBAL.WEBROOT+'/seller/order/backgds/index';
    		});
    	};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage'],
		//指定页面
		init : function(){
			var scoreList = new pInit();
			scoreList.init();
		}
	});
});

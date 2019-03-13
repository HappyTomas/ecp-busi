
$fun  = (function(){
	var init = function(){
    	//禁用按钮
		if($("#tcode").text()!==''){
		$("#bind").attr({"disabled":"disabled"});
		$("input:text").attr("disabled",true); 
		$("#bind").css("background-color","#999");
		$("#bind").css("border","#999");
		}
        $('#bind').on('click',function(){
        	if(!$("#detailInfoForm").valid())return false;
        	var _tmallCode = $('#thirdCode').val();
			var _orderCode = $('#orderCode').val();
        	$.eAjax({
    			url : $webroot+'taobao/savethirdcode',
    			data : {'thirdCode':_tmallCode,'orderCode':_orderCode},
    			success : function(returnInfo) {
    				if(returnInfo.resultFlag=='ok'){
    				eDialog.success('淘宝会员号绑定成功！',{
    					buttons:[{
    						caption:"确定",
    						callback:function(){
    							location.reload(false);
    				        }
    					}]
    				}); 
    				    }else{
    				    	eDialog.alert('请确认您的会员名和订单号，订单需为一天前、交易成功的订单。如会员名和订单号确认无误，请您次工作日再进行尝试，或拨打010-59787584联系我们');
    				}
    			}
    		});
        	
        });
	};

	
	return {
		'init':init
	};

	
})();


$(function(){
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [''],
		//指定页面
		init : function(){
			var ppp = $fun;
			ppp.init();
		}
	});

})




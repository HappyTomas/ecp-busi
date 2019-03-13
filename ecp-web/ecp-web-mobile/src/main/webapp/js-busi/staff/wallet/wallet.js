$(function(){
	 //加载我的資金賬戶
	$('#wrapper1').height($(window).height()
            -$('.am-header').height()
            -20
    );
	var parseMoney = function(data){
		var str = (data/100).toFixed(2) + '';
		var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
		var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
		return ret = intSum + dot;
	};
    var loadScroll = new LoadScroll("wrapper1", {
        url: GLOBAL.WEBROOT+'/wallet/querywallet',
        isAjax:true,
        params:{
            
        },
        bindevent : function(){
        	$('.wallet-list>li').click(function(){
        		var $this = $(this);
        		$("#hiddenBalance").text(parseMoney($("#balance",$this).val()));
        		$("#hiddenAcctTypeName").text($("#acctTypeName",$this).val());
        		$("#hiddenCreateTime").text(ebcDate.dateFormat($("#createTime",$this).val(),'yyyy-MM-dd hh:mm'));
                $('.slidePageContanier').offCanvas('open');
            });
            $('#pageReturn').click(function(event){
                $('.slidePageContanier').offCanvas('close');
                event.preventDefault();
                event.stopPropagation();
            });
        }
	});
});
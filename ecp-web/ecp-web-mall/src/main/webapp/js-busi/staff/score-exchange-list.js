//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
    		var beginDate = $('input[name="beginDate"]').val();
    	    var endDate = $('input[name="endDate"]').val();
    	    var scoreType = $('#scoreType').val();
	    	//分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + '/buyerscore/exchangelist?beginDate='+beginDate+'&endDate='+endDate,
	    	    asyncLoad : true,
	    	    asyncTarget : '#exchangeListDiv',
	    	    params : {
	    	    	beginDate : $('input[name="beginDate"]').val(),
	    	    	endDate : $('input[name="endDate"]').val(),
	    	    	scoreType : '1'
	    	    }
	    	});
	    	$('#exchangeQueryBtn').unbind("click");
	    	//绑定提交按钮事件
	    	$('#exchangeQueryBtn').click(function(){
	    		$.eAjax({
					url : GLOBAL.WEBROOT + "/buyerscore/checklogin?v=" + Math.random(),
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							var beginDate = $('input[name="beginDate"]').val();
				    	    var endDate = $('input[name="endDate"]').val();
				    	    if (beginDate == '') {
				    	    	eDialog.alert('开始时间不能为空！');
				    	    	return;
				    	    }
				    	    if (endDate == '') {
				    	    	eDialog.alert('结束时间不能为空！');
				    	    	return;
				    	    }
				    	    var scoreType = $('#scoreType').val();
				    	    $('#selDateExchange').html('查询日期：'+beginDate+' 至 '+endDate);
				    		$('#exchangeListDiv').load(GLOBAL.WEBROOT + '/buyerscore/exchangelist?beginDate='+beginDate+'&endDate='+endDate+'&scoreType='+scoreType+'&v='+Math.random());
						} else {
							window.location.href = GLOBAL.WEBROOT + '/buyerscore/index';
						}
					}
				});
	    		
	    	});
	    	
	    	//积分合计赋值
	    	$('#scoreExchangeSum').html($('#exchangeSumTemp').val());
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
			var exchangeList = new pInit();
			exchangeList.init();
		}
	});
});
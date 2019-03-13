
//页面初始化模块
$(function(){
	//商城列表
	 $.eAjax({
	        url: GLOBAL.WEBROOT + '/score/mallpoint',
	        dataType: 'html',
	        success: function(returnInfo) {
	          if(returnInfo){
	        	  $('#pointList').html(returnInfo);
	          }   
	        },
	        exception: function() {
	            eDialog.alert("获取积分商城列表失败。");
	        }
	    });
	 $('#listTab').find('a').on('opened.tabs.amui', function(e) {
		 if($(this).hasClass('dhNav') && !$(this).data('loadData')){
			//我的兑换记录列表
			 $.eAjax({
			        url: GLOBAL.WEBROOT + '/score/myChangeList',
			        dataType: 'html',
			        success: function(returnInfo) {
			          if(returnInfo){
			        	  $('#myChangeList').html(returnInfo);
			          }   
			        },
			        exception: function() {
			            eDialog.alert("获取积分商城列表失败。");
			        }
			    });
			 $(this).data('loadData',true)
		 }
	
	 });
	$('#sign').click(function(){
		 $.eAjax({
		        url: GLOBAL.WEBROOT + '/score/sign',
		        data:{signFlag:1} ,
		        datatype: 'json',
		        success: function(returnInfo) {
		            if (returnInfo.resultFlag == 'ok') {
		            	$('.sign','.give-panel').text('已签到');
		            	$('#giveDay span').text(returnInfo.giveDay);
		            	$('#scoreNum').show().find('span').text(returnInfo.scoreNum);
		            } else {
		            	eDialog.alert(returnInfo.resultMsg);
		            }
		        },
		        exception: function() {
		            eDialog.alert(returnInfo.resultMsg);
		        }
		    });
		    
	});
});
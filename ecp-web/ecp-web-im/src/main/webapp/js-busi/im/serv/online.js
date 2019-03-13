/**
*客服状态
**/

var online =  {
		
		onlienStatus : "1",
		
		updateOnline :  function(loginFlag){
			  var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
			  var onlineUrl = GLOBAL.WEBROOT + '/online/updateOnlineStatus';
			  if(loginFlag){
				  onlineUrl = GLOBAL.WEBROOT + '/online/loginAndupdateOnlineStatus';
			  }
			  $.eAjax({
			        url: onlineUrl,
			        data: {csaCode:csaCode,onlineStatus:this.onlienStatus,resource:"WEB",shopId:ChatBoxUtil.shopId},
			        datatype: 'json',
			        async: true,
			        success: function(returnInfo) {
			        	if(returnInfo.resultFlag=='ok'){
			        		var state = $('.hState').find('.cur-state');
			        		switch (returnInfo.resultMsg) {
			        		case '0':
			        			state.removeAttr('class');
			        			state.addClass('cur-state leave');
			        			 var presence = $pres({type: "unavailable"}).c("status").t("Unavailable");//$pres().c("show").t("xa").up().c("status").t("down the rabbit hole!");
				        		 ChatBoxUtil.connection.send(presence.tree()); 
			        		/*	$.eAjax({
			        			        url: GLOBAL.WEBROOT + '/serv/logout',
			        			        data: {csaCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),shopId:ChatBoxUtil.shopId},
			        			        datatype: 'json',
			        			        async: true,
			        			        success: function(returnInfo) {
			        			                 if(returnInfo.resultFlag=='ok'){
			        			                	 var presence = $pres({type: "unavailable"}).c("status").t("Unavailable");//$pres().c("show").t("xa").up().c("status").t("down the rabbit hole!");
			        				        		 ChatBoxUtil.connection.send(presence.tree()); 
			        			                 }
			        			        }
			        	    	  });*/
			        			break;
			        		case '1':
			        			state.removeAttr('class');
			        			state.addClass('cur-state online');
			        			var presence = $pres().c("show").t("chat").up().c("status").t("I am online again!");
			        			ChatBoxUtil.connection.send(presence.tree());
			        	/*	    $.eAjax({
			        		        url: GLOBAL.WEBROOT + '/serv/loginQueue',
			        		        data: {csaCode:Strophe.getNodeFromJid(ChatBoxUtil.connection.jid),shopId:ChatBoxUtil.shopId},
			        		        datatype: 'json',
			        		        async: false,
			        		        success: function(returnInfo) {
			        		        	if(returnInfo.resultFlag=='ok'){
						        		
			        		        	}
			        		        }
			        		    });*/
			        		
			        			break;
			        		case '2':
			        			state.removeAttr('class');
			        			state.addClass('cur-state business');
			        			var presence = $pres().c("show").t("away").up().c("status").t("I leave for!");
			        			ChatBoxUtil.connection.send(presence.tree()); 
			        		/*	$.eAjax({
		        			        url: GLOBAL.WEBROOT + '/serv/logout',
		        			        data: {csaCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),shopId:ChatBoxUtil.shopId},
		        			        datatype: 'json',
		        			        async: true,
		        			        success: function(returnInfo) {
		        			                 if(returnInfo.resultFlag=='ok'){
		        			                	
		        			                 }
		        			        }
		        	    	  });*/
			        			break;	
			        		}
			        	}else{
			        		eDialog.alert("网络异常");
			        	}
			        }
			  });
			
		}
		
}

$(function(){
	//在线状态切换
    //初始状态为在线
//	$('.state-qh .ui-btn').on('click', function(){
//		var self = $(this);
//		if(self.hasClass('active')) return;
//		var val = self.attr('value');
//		online.onlienStatus = val;
//		online.updateOnline();
//		$(this).addClass('active').siblings().removeClass('active');
//	});
	
	$('.ui-states li').on('click',function(){
		var status = $(this).find('i').attr('class');
		switch (status) {
		case 'online':
			online.onlienStatus = '1';
			break;
		case 'business':
			online.onlienStatus = '2';
			break;
		case 'leave':
			online.onlienStatus = '0';
			break;	
		}
		online.updateOnline();
		$('.ui-states').hide();
	});
})



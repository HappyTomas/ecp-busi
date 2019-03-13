
$(function () {
	
	// xmpp params
	// BOSH地址 
	var BOSH_SERVICE = $("#boshService").val();
	ChatBoxUtil.BOSH_SERVICE = BOSH_SERVICE;//set value
	// XMPP连接 
    ChatBoxUtil.connection = null;
    // 当前状态是否连接
    ChatBoxUtil.connected = false;
    
    var baseH = $('.chat-bottom').outerHeight()+$('.chat-bar').outerHeight();
    //set value
    ChatBoxUtil.constants.baseH = baseH;
    ChatBoxUtil.model = ChatBoxUtil.constants.SERV;
    

    /* 聊天人数面板 beign */
    var chatListBox=$(".chat-uContainer");
    ChatBoxUtil.setBoxHeight(chatListBox);
    
    chatListBox.mCustomScrollbar({
        scrollInertia: 150,
        autoHideScrollbar:true
    });

    /* 聊天人数面板 end */
    ChatBoxUtil.ServName = $('#staffCode').val();
    ChatBoxUtil.hotlinePerson = $('#hotlinePerson').val();
    //登录
    var userName = $('#ofStaffCode').val();
    var PASS = ChatBoxUtil.defPass;
    ChatBoxUtil.shopId = $('#shopId').val();
    ChatBoxUtil.servPic= $('#servPic').val();
    initConnection({uName:userName, uPass:PASS});
  
     //初始化等待界面
    var chatWin=$('.chat-win');
    var winItem=$('<div class="winItem"></div>');
    winItem.show();
    chatWin.append(winItem);
    var html = template('winItemTpl',{'chatId':'','issueType':'0'});
    winItem.append(html);
    winItem.attr('id','initialize');
	var html = template('proQueTpl', {});
	var winItem=this.box;
	var chatMain = $('#chatMain'+this.chatId,winItem);
	var quePanel=$('.que-panel',winItem).append(html);
	var proPanel=$('.pro-panel',winItem);
	var winPercentHi=0.85; //页面高度百分比
	ChatBoxUtil.setBoxHeight(quePanel,chatMain);
	quePanel.mCustomScrollbar({
        scrollInertia: 150,
        autoHideScrollbar:true,
    });
	$('.chat-side .nav-tabs a',winItem).click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');
		  if(proPanel.hasClass('mCustomScrollbar')){
			  ChatBoxUtil.setBoxHeight(proPanel,winItem);
			  proPanel.mCustomScrollbar('update');
		  }
		  if(quePanel.hasClass('mCustomScrollbar')){
			  ChatBoxUtil.setBoxHeight(quePanel,winItem);
			  quePanel.mCustomScrollbar('update');
		  }
	})
    var chatData = {uName:'',custPic : ''}
    var html = template('chatPanelTpl', chatData);
	var chatMain = $('#chatMain',chatWin);
	chatMain.append(html);
	
	var chatRecord = $(".chat-record", chatMain);
    var chartList = $('.list', chatRecord);

    //页面高度适配
    calchatRecordHeight(chatRecord,winItem);
    calSerInfoHeight();
    
    function calchatRecordHeight(chatRecord,winItem){
    	chatRecord.height(
    	    $(window).height()*winPercentHi
    	    - baseH
    	    - $('.chat-header',winItem).outerHeight()
    	    - $('.chat-in',winItem).outerHeight()
    	    - 55
    	);
    }

    /* 系统信息高度 */
    function calSerInfoHeight() {
        $('.chat-serInfo').height(
            $(window).height()*winPercentHi
            - baseH
        );
    }
    
    var notice = chatMain.find('.notice');
	$(notice).find('p').html('客服'+ChatBoxUtil.ServName+'登录成功，开始接待用户');
	//$('.notice').html(msg);
	$(notice).show(300).delay(5000).hide(300); 
	$(notice).find('#noticeClose').click(function(){
		$(notice).find('p').html();
		$(notice).hide();
	});
    
    //登出
    $('.exit').click(function(){
		eDialog.confirm("确定退出客服系统", {
			buttons : [{
				caption : '确认',
				callback : function(){
					  //确认则立即退出
					  var presence = $pres({type: "unavailable"}).c("status").t("Unavailable");
	        		  ChatBoxUtil.connection.send(presence.tree());
	        		  ChatBoxUtil.connection.flush();
				   	  $.eAjax({
					        url: GLOBAL.WEBROOT + '/serv/logout',
					        data: {csaCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),shopId:ChatBoxUtil.shopId,onlineStatus:'0',resource:"WEB"},
					        datatype: 'json',
					        async: true,
					        success: function(returnInfo) {
					        	
					                 if(returnInfo.resultFlag=='ok'){
					                	 var presence = $pres({type: "unavailable"}).c("status").t("Unavailable");
						        		 ChatBoxUtil.connection.send(presence.tree()); 
						        		 window.location.href=GLOBAL.WEBROOT+'/j_spring_security_logout';
					                 }
					        }
			    	  });
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
 
    });
    
    
    $('.hState .cur-state').click(function () {
        $(this).siblings('.ui-states').show();
    });

    $(window).on('click.hstate', function (e) {
        if ($(e.target).parents('.hState').size() <= 0 && !$(e.target).hasClass('hState')) {
            $('.ui-states').hide();
        }
    })
    
    ChatBoxUtil.setInterval();
    ChatBoxUtil.enableBrowserMsgTip();
    ChatBoxUtil.wndUnload();
    //日志
	Strophe.log = function(level, msg){
		if(window.console){
			console.log(msg);
		}
	}
});

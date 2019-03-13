//-------jq navPageScroll  -------

//导航分页
$.fn.navPageScroll = function (opt) {
    var option = $.extend({
            navPrev: $('.comLan-prev'),
            navNext: $('.comLan-next'),
            disabledClaz: 'disabled'
        }, opt),
        nav = $(this),
        navs = $('li', nav),
        navW = nav.outerWidth(),
        moveIndex = 0,
        preIndex = 0,
        navPrev = option.navPrev,
        navNext = option.navNext;

     nav.resetPage = function () {
         nav.scrollToIndex(0);
    };
     nav.scrollToIndex = function (objIndex) { //指定到某个位置
         var movel = 0;
         var nextW = 0;
         navs = $('li', nav);
         navs.each(function (index) {
             if (index >= objIndex) {
                 nextW += $(this).outerWidth();
             }else{
                 movel += $(this).outerWidth();
             }
         });
         preIndex = objIndex;
         nav.animate({left:  -movel },
             "normal" ,
             "linear",
             function () {
             if (nextW > navW) {
                 navNext.removeClass(option.disabledClaz);
             }else{
                 navNext.addClass(option.disabledClaz);
             }
             if(parseFloat(nav.css('left')) >= 0){
                 navPrev.addClass(option.disabledClaz);
             }else{
                 navPrev.removeClass(option.disabledClaz);
             }
         })

     };

    function prevPage() {
        navs = $('li', nav);
        var moveW = 0;
        for (var index = navs.size() - 1; index >= 0; index--) {
            if (index <= preIndex) {
                if (moveW < navW) {
                    moveW += navs.eq(index).outerWidth() ;
                }
                if (moveW > navW) {
                    moveW -= navs.eq(index).outerWidth();
                    moveIndex = index;
                    break;
                }
                if (index <= 0) {
                    moveIndex = 0;
                }
            }
        }
        nav.scrollToIndex(moveIndex);
    }
    function pageNext() {
        if (navNext.hasClass(option.disabledClaz)) {
            return false
        }
        navs = $('li', nav);
        var moveW = 0;   //向左滑动的距离
        navs.each(function (index) {
            if (index >= moveIndex) {
                if (moveW < navW) {
                    moveW += $(this).outerWidth();
                }
                if (moveW > navW) { //已经刚好一页
                    moveW -= $(this).outerWidth();
                    moveIndex = index;
                    return false;
                }
                if (index >= navs.size()) {
                    moveIndex = index;
                    return false;
                }
            }
        });
        /* 下一页是否可用 */
        nav.scrollToIndex(moveIndex);
    }

    navPrev.click(function () {
        prevPage();
        return false;
    });
    navNext.click(function () {
        pageNext();
        return false;
    });

    nav.resetPage();
    return nav;
};

//-------jq navPageScroll

Strophe.addNamespace('TRANSFER_IQ', "com:ai:of:transfer");

/* TODO ========= ChatBox start ========== */
/**
 * 会话对象
 * 
 * **下划线开头为私有属性，不对外暴露
 * 
 * @param args
 */
function ChatBox(chatBean){
	if(!chatBean) throw new error("chatBean should be initialized!"); 
	this.chatId = chatBean.chatId || "";
	this.box = chatBean.box || null; //box dom实例
	this.tab = chatBean.tab || null; //会话列表tab项
	this.host = chatBean.host || ""; //登录者 JID
	this.from = chatBean.from || ""; //接收消息时 JID
	this.to = chatBean.to || ""; //发送消息时 JID
	this.sessionId = chatBean.sessionId ||"";//当前的会话Id
	this.msgId = chatBean.msgId ||"";
	this.custPic = chatBean.custPic;
	this.custStaffCode = "";  //对方用户的staffcode;
	this.current = chatBean.current || false; //是否当前窗口
	this.unread = chatBean.unread || 0; //未读消息
	this.model = chatBean.model || ChatBoxUtil.constants.SERV;  // "serv" "cust" 
	this.reciveTmplId = chatBean.reciveTmplId || "record"; //接收消息展示模板
	this.sendTmplId = chatBean.sendTmplId || "selfRecord"; //发送消息展示模板
	this.msgInput = null; //消息输入区
	this.phrasebookUE = null;//常用语
	
	this.userInfo = chatBean.userInfo || {};
	
	this.lastSendMoment = new Date(); //最后一次发消息的时刻
	this.sendable = true; //消息可否发送
	this._creationTime = new Date();
	//初始化
	this._initDom();
}

ChatBox.prototype = {
	constructor: ChatBox,
	getTo: function(){
		if(!this.to) throw new error("to is not found!");
		return this.to;
	},
	getFrom: function(){
		if(!this.from) throw new error("from is not found!");
		return this.from; 
	},
	setFrom: function(from){
		if(!from) throw new error("from is missing!");
		this.from = from;
	},
	setTo: function(to){
		if(!to) throw new error("to is missing!");
		this.to = to;
	},
	setCustStaffCode: function(custStaffCode){
		this.custStaffCode = custStaffCode;
	},
	isCurrent: function(){
		return this.current;
	},
	setCurrent: function(status){
		status == !!status;
		this.current = status;
	},
	setSessionId : function(sessionId){
		if(!sessionId) throw new error("session is missing!");
		this.sessionId = sessionId;
	},
	/**
	 * 关闭会话窗口
	 */
	close: function(locale){
		if(!locale){
			//通知
			this.notifyCustOnApp();
		}
		//1.窗口移除
		this.box.remove();
		if(this.tab){
			this.tab.remove();
			var useListScroll=$('.chat-uContainer');
			ChatBoxUtil.setBoxHeight(useListScroll);
		    useListScroll.mCustomScrollbar('update');
		} 
		
		//2.ChatBoxUtil.list 移除
		var index = $.inArray(this, ChatBoxUtil.list);
		if(index>=0){
			if(ChatBoxUtil.list.length==1){
				$('#initialize').show();
				ChatBoxUtil.list.splice(index,1);
			}else{
				ChatBoxUtil.list.splice(index,1);
				ChatBoxUtil.list[0].show();
			}
			
		}
	},
	/**
	 * 展示会话窗口
	 */
	show: function(){
		//1.当前窗口显示
		//2.兄弟窗口隐藏
		var list = ChatBoxUtil.list;
		for(var chat in list){
			var chatBox = list[chat];
			if(chatBox.chatId == this.chatId){
				chatBox._display();
			}else{
				chatBox._hide();
			}
		}
		
		//tab 
    	this.unread = 0;
    	if(this.tab) this.tab.find(".mNum").html(this.unread).hide();
	},
	/**
	 * 显示当前
	 */
	_display: function(){
		this.current = true;
		//显示窗口
		this.box.show();
		//展示添加滚动条
		 var chatRecord=$('.chat-record',this.box);
			var proPanel=$('.pro-panel',this.box);
			var quePanel=$('.que-Panel',this.box);
			var ordPanel=$('.ord-detail-panel',this.box);
			var allordPanel=$('.allord-panel',this.box);
			var msgPanelCont=$('.msgPanelCont',this.box);
			   var scrollClz='mCustomScrollbar';
			if(chatRecord.hasClass(scrollClz)){
				chatRecord.mCustomScrollbar('update');
				chatRecord.mCustomScrollbar('scrollTo','bottom');
			}
			
			if(allordPanel.hasClass(scrollClz)&&allordPanel.css('display')=='block'){
				allordPanel.mCustomScrollbar('update');
			}
			if(proPanel.hasClass(scrollClz)&&proPanel.css('display')=='block'){
				proPanel.mCustomScrollbar('update');
			}
			if(ordPanel.hasClass(scrollClz)&&!ordPanel.is(":hidden")){
               	ordPanel.mCustomScrollbar('update');
             }
			if(quePanel.hasClass(scrollClz)&&quePanel.css('display')=='block'){
				quePanel.mCustomScrollbar('update');
			}
			if(msgPanelCont.hasClass(scrollClz)&&msgPanelCont.css('display')=='block'){
				msgPanelCont.mCustomScrollbar('update');
			}
			
		//选中tab
		if(this.tab) this.tab.addClass("current");
		
		//从cookie中读取ueditor快捷键
	    var ueditorShortKeyIndex=$.cookie('ueditorShortKeyIndex_'+$('#staffCode').val());
    	if(ueditorShortKeyIndex){
    		$('.btn-keysel li',this.box).eq(ueditorShortKeyIndex).addClass('checked').siblings().removeClass('checked');
    	}
	},
	/**
	 * 隐藏当前
	 */
	_hide: function(){
		this.current = false;
		this.box.hide();
		if(this.tab) this.tab.removeClass("current");
	},
	/**
	 * 展示通知消息
	 * @param msginfo
	 */
	showInform : function(msginfo){
		var msg =  msginfo.msg;
		var winItem = this.box;
		var chatMain = $('#chatMain'+this.chatId,winItem);
		var notice = chatMain.find('.notice');
		$(notice).find('p').html(msg);
		//$('.notice').html(msg);
		$(notice).show();
		$(notice).find('#noticeClose').click(function(){
			$(notice).find('p').html();
			$(notice).hide();
		});
		//发送欢迎语
        var csa = ChatBoxUtil.connection.jid.split('/');
        var issueType = $('#issueType').val();
        var data = {to:this.to||this.from,
        		    toResource:csa[1],
        		    from:ChatBoxUtil.connection.jid,
        		    body:ChatBoxUtil.welcomes,
        		    sessionId:this.sessionId,
        		    messageType:'welcome',
        		    contentType:'0',
        		    shopId : ChatBoxUtil.shopId,
        		    csaCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),
        		    userCode:ChatBoxUtil.getUserFromJid(this.to||this.from)}
        $.eAjax({
	        url: GLOBAL.WEBROOT + '/history/saveMsgHistory',
	        data: data,
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	ChatBoxUtil.message.messagetype = returnInfo.messageType;
	        	ChatBoxUtil.message.body = ChatBoxUtil.welcomes;
	        	ChatBoxUtil.message.sessionId = returnInfo.sessionId;
	        	ChatBoxUtil.message.contentType = returnInfo.contentType;
	        	ChatBoxUtil.message.sendTime = returnInfo.beginDate;
	        	
	        	 var str = JSON.stringify(ChatBoxUtil.message); 
	        	  var msg = $msg({  
		                to: returnInfo.to,//发送或回复消息   
		                from: returnInfo.from, //登录者ID
		                id : returnInfo.id,
		               // messagetype : returnInfo.messageType,
		                type: 'chat'  
		            }).c("body", null, str)//.c("session", null,returnInfo.sessionId);//带入本次会话的sessionId
		            ChatBoxUtil.connection.send(msg.tree());  
	        },
	        exception: function() {
	            eDialog.alert("网络异常");
	            return;
	        }
        });
        
     /*   var msg = $msg({  
            to: this.to||this.from,//发送或回复消息   
            from: ChatBoxUtil.connection.jid, //登录者ID
            id : 'welcome',
            messagetype : 'msg',
            type: 'chat'  
        }).c("body", null, ChatBoxUtil.welcomes).c("session", {id: this.sessionId});//带入本次会话的sessionId
        ChatBoxUtil.connection.send(msg.tree());  */
        // 展示消息
        /* 
         * bdx-issues 20170218_客服_客服端欢迎语不显示
         * 对以下代码保留并注释
         */
        /*var winItem = this.box;
		var chatMain = $('#chatMain'+this.chatId,winItem);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
        var tplData = {
    		msg: ChatBoxUtil.welcomes,
    		uName: ChatBoxUtil.ServName,
    		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
    		custPic: ChatBoxUtil.servPic
        }
        var html = template('selfRecord', tplData);
        chartList.append(html);
        chatRecord.mCustomScrollbar('update');
        chatRecord.mCustomScrollbar('scrollTo', 'bottom');*/
	      //tab
        var tabItem = this.tab;
        if(tabItem){
	        tabItem.find(".tip").html(msg.substring(0,30)+"...");
	        if(!this.current){
	        	this.unread++;
	        	tabItem.find(".mNum").html(this.unread).show();
	        }
        }
		
	},
	/**
	 * 商品消息
	 * @param msginfo
	 */
	showgdsmsg: function(msginfo){
		var msg =  msginfo.msg;
		msg = JSON.parse(msg);
		var id = msginfo.id;
		//winItem
		var winItem = this.box;
		var chatMain = $('#chatMain'+this.chatId,winItem);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
        var cid = ChatBoxUtil.getUserFromJid(this.from);
        var staffCode = ChatBoxUtil.getStaffCodeByUserCode(cid);
        var tplData = {
    		uName: staffCode,
    		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
    		custPic : this.custPic,
    		// TODO 补充返回的参数skuId
        	url : $("#gdsDetailUrl").val() + msg.gdsId +"-",
    		gdsId : msg.gdsId,
    		gdsImage : msg.gdsImage,
    		gdsName : msg.gdsName,
    		gdsPrice : msg.price
        }
        var html = template('recordGds', tplData);
   	    $.eAjax({
	        url: GLOBAL.WEBROOT + '/history/updateMsgStatus',
	        data: {id:id},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	  chartList.append(html);
	        	  chartList.imagesLoaded(function () {
	        		  chatRecord.mCustomScrollbar('update');
		   	          chatRecord.mCustomScrollbar('scrollTo', 'bottom');
	 	             });
	        	  
	        	  //消息提示
	        	  var $msg=$(tplData.gdsName);
	        	  $msg=$msg.wrap("<div class='wrap'></div>");
	              var imgs=$('img',$msg);
	              imgs.each(function () {
	                if($(this).hasClass('emotionImg')){  //表情图片
	                    $(this).after('【'+$(this).attr("alt")+'】');
	                }else{
	                    $(this).after('【图片】');
	                }
	              })
	        	  ChatBoxUtil.popNotice(tplData.uName,tplData.custPic,$msg.text());
	        	
	        },
	        exception: function() {
	            eDialog.alert("网络异常");
	            return;
	        }
        });
        
        //tab
        var tabItem = this.tab;
        if(tabItem){
        	if(!msg.gdsName){
        		msg.gdsName = "[商品消息]";
        	}
	        tabItem.find(".tip").html(msg.gdsName.length>30?msg.gdsName.substring(0,30)+"...":msg.gdsName);
	        if(!this.current){
	        	this.unread++;
	        	tabItem.find(".mNum").html(this.unread).show();
	        }
        }
	},
	/**
	 * 订单消息 
	 * @param msginfo
	 */
	showordmsg: function(msginfo){
		var msg =  msginfo.msg;
		msg = JSON.parse(msg);
		var id = msginfo.id;
		//winItem
		var winItem = this.box;
		var chatMain = $('#chatMain'+this.chatId,winItem);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
        var cid = ChatBoxUtil.getUserFromJid(this.from);
        var staffCode = ChatBoxUtil.getStaffCodeByUserCode(cid);
        var tplData = {
    		uName: staffCode,
    		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
    		custPic : this.custPic,
    		ordId : msg.ordId,
    		price : msg.price,
    		ordImage : msg.ordImage,
    		createTime : ebcDate.dateFormat(new Date(Number(msg.createTime)), "yyyy-MM-dd hh:mm:ss")
//    		url : "javascript:orderDetail('"+ msg.ordId +"')"
        }
        var html = template('recordOrder1', tplData);
   	    $.eAjax({
	        url: GLOBAL.WEBROOT + '/history/updateMsgStatus',
	        data: {id:id},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	  chartList.append(html);
	        	  chartList.imagesLoaded(function () {
	        		  chatRecord.mCustomScrollbar('update');
		   	          chatRecord.mCustomScrollbar('scrollTo', 'bottom');
	 	             });
	        	  
	        	  //消息提示
	        	 /* var $msg=$(tplData.ordId);
	        	  $msg=$msg.wrap("<div class='wrap'></div>");
	              var imgs=$('img',$msg);
	              imgs.each(function () {
	                if($(this).hasClass('emotionImg')){  //表情图片
	                    $(this).after('【'+$(this).attr("alt")+'】');
	                }else{
	                    $(this).after('【图片】');
	                }
	              })*/
	        	  ChatBoxUtil.popNotice(tplData.uName,tplData.custPic,"【订单】");
	        	
	        },
	        exception: function() {
	            eDialog.alert("网络异常");
	            return;
	        }
        });
        
        //tab
        var tabItem = this.tab;
        if(tabItem){
        	if(!msg.ordId){
        		msg.ordId = "[订单消息]";
        	}
	        tabItem.find(".tip").html(msg.ordId);
	        if(!this.current){
	        	this.unread++;
	        	tabItem.find(".mNum").html(this.unread).show();
	        }
        }
	},
	/**
	 * 展示消息
	 */
	showReceived: function(msginfo){
		if(msginfo.messageType=='msg'){
			var msg =  msginfo.msg;
			var id = msginfo.id;
			//winItem
			var winItem = this.box;
			var chatMain = $('#chatMain'+this.chatId,winItem);
			var chatRecord = $(".chat-record", chatMain);
	        var chartList = $('.list', chatRecord);
	        var cid = ChatBoxUtil.getUserFromJid(this.from);
	        var staffCode = ChatBoxUtil.getStaffCodeByUserCode(cid);
	        var tplData = {
	    		msg: msg,
	    		uName: staffCode,
	    		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
	    		custPic : this.custPic
	        }
	        var html = template('record', tplData);
	   	    $.eAjax({
		        url: GLOBAL.WEBROOT + '/history/updateMsgStatus',
		        data: {id:id},
		        datatype: 'json',
		        async: false,
		        success: function(returnInfo) {
		        	  chartList.append(html);
		        	  chartList.imagesLoaded(function () {
		        		  chatRecord.mCustomScrollbar('update');
			   	          chatRecord.mCustomScrollbar('scrollTo', 'bottom');
		 	             });
		        	  //消息提示
		        	  tplData.msg="<div class='wrap'>"+tplData.msg+"</div>"; //兼容app只发不带html的文字或者图片
		        	  var $msg=$(tplData.msg);
		              var imgs=$('img',$msg);
		              imgs.each(function () {
		                if($(this).hasClass('emotionImg')){  //表情图片
		                    $(this).after('【'+$(this).attr("alt")+'】');
		                }else{
		                    $(this).after('【图片】');
		                }
		               });
		        	  ChatBoxUtil.popNotice(tplData.uName,tplData.custPic,$msg.text());
		        	
		        },
		        exception: function() {
		            eDialog.alert("网络异常");
		            return;
		        }
	        });
	        
	        //tab
	        var tabItem = this.tab;
	        if(tabItem){
		        tabItem.find(".tip").html(msg.substring(0,30)+"...");
		        if(!this.current){
		        	this.unread++;
		        	tabItem.find(".mNum").html(this.unread).show();
		        }
	        }
		}else if(msginfo.messageType=='inform'){
			this.showInform(msginfo);
		}else if(msginfo.messageType=='gds'){
			this.showgdsmsg(msginfo);
		}else if(msginfo.messageType=='order'){
			this.showordmsg(msginfo);
		}
	},
	/**
	 * 通知app下线
	 */
	notifyCustOnApp : function(){
		var toUser = this.to||this.from;
		var toDestJID = Strophe.getBareJidFromJid(toUser)+"/APP";
		var note = $msg({  
            to: toDestJID,//发送或回复消息   
            from: ChatBoxUtil.connection.jid, //登录者ID
            type: 'headline'  
        }).c("body", null, "1");
		note.c("notify", null, "1");
		ChatBoxUtil.connection.send(note.tree()); 
	},
	/**
	 * 发送消息
	 */
	sendMessage: function(){
		if(ChatBoxUtil.connected) {  
            if(!this.to && !this.from) {  
            	eDialog.alert("没有联系人！");
                return;  
            }  
            
        	var flag = true;
        	var winItem = this.box;
        	var chatMain = $('#chatMain'+this.chatId,winItem);
        	
        	//控制发送频率
            var smDiff = new Date().getTime() - this.lastSendMoment.getTime();
            if(smDiff < 500){
            	ChatBoxUtil.showNotice('消息发送过于频繁，请稍事休息！',true,chatMain);
            	return;
            }
            
            //前序消息发送中
            if(!this.sendable){
            	ChatBoxUtil.showNotice('消息发送过于频繁，请稍事休息！',true,chatMain);
            }
            
        	
            $.eAjax({
    	        url:GLOBAL.WEBROOT + '/history/getSessionByone',
    	        data: {id:this.sessionId},
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    	        
                       if(returnInfo.status!='1'){
                    	   ChatBoxUtil.showNotice('用户结束了与您的会话，请关闭当前会话窗口',false,chatMain);
                    	   flag =  false;
                       }        	        	
    	        
    	            }
                 });
            
            // 发送消息 
            // 创建一个<message>元素并发送  
            var ue = this.msgInput;
            var messageValue = ue.getContent();
            

            var filterMsg = $(messageValue); 
            var reg = new RegExp("data:image/png");
            var isIEparseImg = false;
            filterMsg.find('img').each(function(){
            	if(reg.test($(this).attr('src'))){
            		isIEparseImg = true;
            		return;
            	}
            });
            
            if(isIEparseImg){
            	return; //如果是ie黏贴图片不允许提交
            }
            /*
             * 消息处理；1.内容  2.频次
             */
            if(messageValue == "") {
            	// 清空
                ue.execCommand("cleardoc");
            	return;
            }
            this.sendable = false; //消息发送中
            /**
             * 消息uuid生成
             */
            var csa = this.from.split('/');
            var issueType = $('#issueType').val();
            var data = {to:this.to||this.from,
            		    toResource:csa[1],
            		    from:ChatBoxUtil.connection.jid,
            		    body:messageValue,
            		    sessionId:this.sessionId,
            		    messageType:'msg',
            		    contentType: "0",
            		    shopId : ChatBoxUtil.shopId,
            		    csaCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),
            		    userCode:ChatBoxUtil.getUserFromJid(this.to||this.from)}
            $.eAjax({
    	        url: GLOBAL.WEBROOT + '/history/saveMsgHistory',
    	        data: data,
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    	        	ChatBoxUtil.message.messagetype = returnInfo.messageType;
    	        	ChatBoxUtil.message.body = returnInfo.body;
    	        	ChatBoxUtil.message.sessionId = returnInfo.sessionId;
    	        	ChatBoxUtil.message.contentType = returnInfo.contentType;
    	        	ChatBoxUtil.message.sendTime = returnInfo.beginDate;
    	        	messageValue = returnInfo.body;
    	        	var str = JSON.stringify(ChatBoxUtil.message); 
    	            var msg = $msg({  
    	                to: returnInfo.to,//发送或回复消息   
    	                from: returnInfo.from, //登录者ID
    	                id : returnInfo.id,
    	                type: 'chat'  
    	            }).c("body", null, str);//.c("session", null,returnInfo.sessionId);//带入本次会话的sessionId
    	            //离线站内消息
    	            if(!flag){
    	            	msg.c("note", null, ChatBoxUtil.ServName);
    	            }

    	            ChatBoxUtil.connection.send(msg.tree());  
    	        },
    	        exception: function() {
    	            eDialog.alert("网络异常");
    	            this.sendable = true;
    	            return;
    	        }
            });
  
            // 展示消息
            var winItem = this.box;
    		var chatMain = $('#chatMain'+this.chatId,winItem);
    		var chatRecord = $(".chat-record", chatMain);
            var chartList = $('.list', chatRecord);
            var tplData = {
        		msg: flag==false?messageValue+"[用户离线]":messageValue,
        		uName: ChatBoxUtil.ServName,
        		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
        		custPic:ChatBoxUtil.servPic
            }
            var html = template('selfRecord', tplData);
            chartList.append(html);
            chatRecord.mCustomScrollbar('update');
            chatRecord.mCustomScrollbar('scrollTo', 'bottom');
            
            // 清空
            setTimeout(function(){ // 延迟按回车键时换行
            	 ue.execCommand("cleardoc");
            });
            this.sendable = true;
        } else {  
        	eDialog.alert("请先登录！");  
        }  
	},
	_loadPro: function(userCode,place,issueType, gdsId,ordId){
		var winItem = this.box;
		var proPanel=$('.pro-panel',winItem);
		var data={},dataUrl='';
		userCode = ChatBoxUtil.getStaffCodeByUserCode(userCode);
		if("00"==place){//加载历史订单
		     data = {staffCode:userCode,issueType:issueType,joinModel:ChatBoxUtil.constants.SERV};
		     dataUrl= GLOBAL.WEBROOT + '/mallInfo/ordAll';
		     proPanel.empty();
		     var orderPage=$('<div class="order-page" style="display:none">'+
		    		    '<span class="prev"></span>'+
		    		    '<span class="next"></span>'+
		    		    '</div>');
		       proPanel.append(orderPage);
		      proPanel=$('<div class="ord-detail-panel"></div>').prependTo(proPanel);
		      
		      var prevPage=$('.order-page .prev',winItem);
		      var nextPage=$('.order-page .next',winItem);
		      loadAjax(dataUrl,data,function(){
		    	    prevPage.addClass('disabled');
			        var pageCount=parseInt($('.pageCount',winItem).val());
			        if(pageCount>0){
			            orderPage.show();
			        }
	           		if(pageCount==1){
	           			nextPage.addClass('disabled');
				      }
		          });
		      prevPage.click(function(){
		    	  var $this=$(this);
		    	  var pageNumber=parseInt($('.pageNumber',winItem).val())-1;
	           	  data.pageNumber=pageNumber;
	           	  if(!$this.hasClass('disabled')){
		           		nextPage.removeClass('disabled');  
		           		if(pageNumber>=1){
		           		   if(pageNumber==1){
			           			prevPage.addClass('disabled');
					    	  }
		           			loadAjax(dataUrl,data);
		           		}
		          }
            });
		      nextPage.click(function(){
         	  var $this=$(this);
		    	  var pageNumber=parseInt($('.pageNumber',winItem).val())+1;
		    	  var pageCount=parseInt($('.pageCount',winItem).val());
	           	  data.pageNumber=pageNumber;
	           	  if(!$this.hasClass('disabled')){
		           		prevPage.removeClass('disabled');  
		           		if(pageNumber<=pageCount){
		           		   if(pageNumber>=pageCount){
			           			nextPage.addClass('disabled');
					    	  }
		           		  loadAjax(dataUrl,data);
		           		}
		           }
         	  });
		}else if("01"==place){ //加载订单信息
			data = {ordId:ordId,joinModel:ChatBoxUtil.constants.SERV};
			dataUrl= GLOBAL.WEBROOT + '/mallInfo/ordDetail';
			loadAjax(dataUrl,data);
		}else if("02"==place){  //加载商品信息
			data = {gdsId:gdsId,joinModel:ChatBoxUtil.constants.SERV,custStaffCode:userCode};
		    dataUrl= GLOBAL.WEBROOT + '/mallInfo/gdsDetail';
			loadAjax(dataUrl,data);
		}
		function loadAjax(url,param,callback){
			proPanel.empty().append("<div class='block-empty'>加载中..</div>");
			$.eAjax({
	 	        url:url,
	 	        data: param,
	 	        dataType: 'html',
	 	        async: true,
	 	        success: function(returnInfo) {
	 	         	 proPanel.empty().append(returnInfo);
	 	         	 //订单、商品发送按钮移除
	 	         	 $(".pro-panel", winItem).find(".gdssend").remove();
	 	         	 $(".pro-panel", winItem).find(".ordersend").remove();
	 	         	 ChatBoxUtil.setBoxHeight(proPanel,winItem);
	 	             proPanel.imagesLoaded(function () {
	 	                 proPanel.mCustomScrollbar({
	 	                     scrollInertia: 150,
	 	                     autoHideScrollbar:true,
	 	                 });
	 	             })
	 	            callback&&callback();
	 	 
	 	        },
	 	        exception: function() {
	 	            eDialog.alert("网络异常");
	 	            return;
	 	        }
	         });
		}
	},
	_loadProQue:function(){
		var html = template('proQueTpl', {});
		var winItem=this.box;
		var chatMain = $('#chatMain'+this.chatId,winItem);
		var quePanel=$('.que-panel',winItem).append(html);
		var proPanel=$('.pro-panel',winItem);
		var allordPanel=$('.ord-detail-panel',winItem);
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
			  if(allordPanel.hasClass('mCustomScrollbar')){
				  ChatBoxUtil.setBoxHeight(allordPanel,winItem);
				  allordPanel.mCustomScrollbar('update');
			  }
		})
	},
	_loadAllord: function(userCode,place,issueType, gdsId,ordId){
		var winItem = this.box;
		if(place=="00") {
			$(".allord-panel",winItem).remove();
			return;
		}
		var allordPanel=$('.allord-panel',winItem);
		var data={},dataUrl='';
		userCode = ChatBoxUtil.getStaffCodeByUserCode(userCode);
		data = {staffCode:userCode,issueType:issueType,joinModel:ChatBoxUtil.constants.SERV};
	     dataUrl= GLOBAL.WEBROOT + '/mallInfo/ordAll';
	     allordPanel.empty();
	     var orderPage=$('<div class="order-page" style="display:none">'+
	    		    '<span class="prev"></span>'+
	    		    '<span class="next"></span>'+
	    		    '</div>');
	      allordPanel.append(orderPage);
	      allordPanel=$('<div class="ord-detail-panel"></div>').prependTo(allordPanel);
	      
	      var prevPage=$('.order-page .prev',winItem);
	      var nextPage=$('.order-page .next',winItem);
	      loadAjax(dataUrl,data,function(){
	    	    prevPage.addClass('disabled');
		        var pageCount=parseInt($('.pageCount',winItem).val());
		        if(pageCount>0){
		            orderPage.show();
		        }
          		if(pageCount==1){
          			nextPage.addClass('disabled');
			      }
	          });
	      prevPage.click(function(){
	    	  var $this=$(this);
	    	  var pageNumber=parseInt($('.pageNumber',winItem).val())-1;
          	  data.pageNumber=pageNumber;
          	  if(!$this.hasClass('disabled')){
	           		nextPage.removeClass('disabled');  
	           		if(pageNumber>=1){
	           		   if(pageNumber==1){
		           			prevPage.addClass('disabled');
				    	  }
	           			loadAjax(dataUrl,data);
	           		}
	          }
       });
	      nextPage.click(function(){
    	  var $this=$(this);
	    	  var pageNumber=parseInt($('.pageNumber',winItem).val())+1;
	    	  var pageCount=parseInt($('.pageCount',winItem).val());
          	  data.pageNumber=pageNumber;
          	  if(!$this.hasClass('disabled')){
	           		prevPage.removeClass('disabled');  
	           		if(pageNumber<=pageCount){
	           		   if(pageNumber>=pageCount){
		           			nextPage.addClass('disabled');
				    	  }
	           		  loadAjax(dataUrl,data);
	           		}
	           }
    	  });
		
		function loadAjax(url,param,callback){
			allordPanel.empty();
			allordPanel.append("<div class='block-empty'>加载中..</div>");
			$.eAjax({
	 	        url:url,
	 	        data: param,
	 	        dataType: 'html',
	 	        async: true,
	 	        success: function(returnInfo) {
	 	        	allordPanel.show().empty().append(returnInfo);
	 	        	 //订单、商品发送按钮移除
	 	         	 $(".allord-panel", winItem).find(".gdssend").remove();
	 	         	 $(".allord-panel", winItem).find(".ordersend").remove();
	 	         	 ChatBoxUtil.setBoxHeight(allordPanel,winItem);
	 	         	 allordPanel.imagesLoaded(function () {
	 	         		allordPanel.mCustomScrollbar({
	 	                     scrollInertia: 150,
	 	                     autoHideScrollbar:true,
	 	                 });
	 	             })
	 	            callback&&callback();
	 	 
	 	        },
	 	        exception: function() {
	 	            eDialog.alert("网络异常");
	 	            return;
	 	        }
	         });
		}
		
		if (allordPanel.hasClass('mCustomScrollbar')) {
			allordPanel.mCustomScrollbar();
        } else {
        	ChatBoxUtil.setBoxHeight(allordPanel,winItem);
        	allordPanel.mCustomScrollbar({autoHideScrollbar:true});
        }
	},
	_loadChat: function(){
		var chatBox = this;
		var winItem = this.box;
		var staffCode = ChatBoxUtil.getStaffCodeByUserCode(this.chatId);
		var chatData = {
			uName:staffCode,
			custPic : this.custPic,
			transfer : 1
		}
		var html = template('chatPanelTpl', chatData);
		var chatMain = $('#chatMain'+this.chatId,this.box);
		chatMain.append(html);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
		ChatBoxUtil.setBoxHeight(chatRecord,winItem);
		var scrollTop = 0;
		var userCode = this.chatId;
 		var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
 		var pageNum = 1;
        chatRecord.mCustomScrollbar({
            scrollInertia: 150,
            scrollButtons: {enable: true},
            autoHideScrollbar:true,
            callbacks: {
                onScroll: function (arg) {

                    if (arg.draggerTop <= 30
                            && scrollTop != 0
                            && scrollTop > arg.draggerTop
                    ) {
                   
                    /*	var serMore = $('.ser-more', chartList);
                    	$(serMore).show();
                        $(serMore).addClass('loading');*/
                    	$('.ser-more',chartList).addClass('loading');
                        $('.ser-more',chartList).show();
                        if(pageNum==1){
                        	$('.hst-tip',chartList).show();
                        }
                        window.setTimeout(function () {
                        //TODO 获取消息
                        pageNum  =pageNum+1;
                        $.eAjax({
                	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
                	        data: {
                	        	userCode:userCode,
                	        	csaCode:csaCode,
//                	        	messageType:'msg',
                	        	pageNumber:pageNum,
                	        	pageSize:ChatBoxUtil.pageSize,
                	        	status:'20',
                	        	shopId : ChatBoxUtil.shopId
                	        },
                	        datatype: 'json',
                	        async: false,
                	        success: function(returnInfo) {
                	        	$.each(returnInfo, function(p1, p2) {
                	        	   if(p2.from ==  ChatBoxUtil.connection.jid){
                	        		   		
                	        	            var tplData = {
                	        	            		msg: p2.body,
                	        	            		uName: ChatBoxUtil.ServName,
                	        	            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
                	        	            		custPic:ChatBoxUtil.servPic
                	        	            }
                	        	            var html = template('selfRecord', tplData);
                	        	            if(p2.messageType != "msg"){
                	        	            	var domInfo = {
		        	        	            			messageType: p2.messageType,
		        	        	            			uName: ChatBoxUtil.ServName,	
		        	        	            			custPic: ChatBoxUtil.servPic
                	        	            	};
                	        	            	html = chatBox._createMessageDom(domInfo, p2, "chat-right");
                	        	            }
                	        	            $('.ser-more',chartList).after(html);
                	        	            //$(html).appendTo(chartList);
                	        	           
                	        		}else{
                	        			var staffCode = ChatBoxUtil.getStaffCodeByUserCode(p2.userCode);
                		                var tplData = {
                		            		msg: p2.body,
                		            		uName:staffCode,
                		            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
                		            		custPic:chatBox.custPic
                		                }
                		                var html = template('record', tplData);
                		                if(p2.messageType != "msg"){
            	        	            	var domInfo = {
	        	        	            			messageType: p2.messageType,
	        	        	            			uName: staffCode,	
	        	        	            			custPic: chatBox.custPic
            	        	            	};
            	        	            	html = chatBox._createMessageDom(domInfo, p2, "");
            	        	            }
                		                $('.ser-more',chartList).after(html);
                		               // $(html).appendTo(chartList);
                	        		}
                	        	   chatRecord.mCustomScrollbar('update');
                	        	});
                	        	$('.ser-more',chartList).hide();
                	        	
                	        },
                	        exception: function() {
                	            eDialog.alert("网络异常");
                	            return;
                	        }
                        });
                        }, 300);
                        
                       
                    }
                    scrollTop = arg.draggerTop;
                }
            }
        });
        
        //至顶
        $('.scTop', chatMain).click(function () {
            chatRecord.mCustomScrollbar('scrollTo', 0);
        });
        //至底
        $('.scBottom', chatMain).click(function () {
            chatRecord.mCustomScrollbar('scrollTo', $('.list', chatRecord).height());
        });
        
         /* 标记为敏感客户 begin */
        var sensBlock = $('.sens',chatMain).show();
       
        $.eAjax({
	        url: GLOBAL.WEBROOT + '/chat/loadSensInfo',
	        data: {
	        	staffCode:staffCode,
	        },
	        datatype: 'json',
	        async: false,
	        success: function(data){
	        	   if(data.sensitiveType && data.sensitiveType != '0'){
	        		    sensBlock.addClass('hadSign');
	        		    $('.sensBtn',sensBlock).text('敏感');
	        		    $('.btn-signed .tit',chatMain).html(data.sensitiveTypeTxt);
	        			$('.btn-signed .cont',chatMain).html(data.sensitiveDesc);
	        	   }else{
	        		   //标记为敏感客户
	        		   var sensTip = "";
	        		   var monthLimit = data.monthLimit;
	        		   var ordCancelNumber = data.ordCancelNumber;
	        		   var ordRefundNumber = data.ordRefundNumber;
	        		   if(data.cancleOrdCount >= ordCancelNumber){
	        			   sensTip =monthLimit+"个月内取消订单数量"+data.cancleOrdCount+"笔";
	        		   }
	        		   if(data.backOrdCount >= ordRefundNumber){
	        			   sensTip =monthLimit+"个月内退货退款订单量"+data.backOrdCount+"笔";
	        		   }
	        		   if(data.backOrdCount >= ordRefundNumber && data.cancleOrdCount >= ordCancelNumber){
	        			   sensTip =monthLimit+"个月内取消订单数量"+data.cancleOrdCount+"笔,退货退款订单量"+data.backOrdCount+"笔";
	        		   }
	        		   sensTip?$('.sens-tip',sensBlock).html(sensTip):$('.sens-tip',sensBlock).hide();
	        		
	        		  var sensTpl=""; 
	 	        	  $('.sensBtn',chatMain).click(function(){
	 	        		 $('.sensitiveType').attr("style","");
	 	        		 $('.sensitiveDesc').attr("style","")
	 	        		 sensTpl=template('sensitiveSetTpl',{});
	 	        		   bDialog.open({
	 	        			    'title' : "请填写备注",
	 	                        'width' : 550,
	 	                        'height' : 250,
	 	                        'customClass': 'imDialog'
	 	                    },sensTpl);
	 	        	  });
	 	        	  
	 	        	  $('.saveSens',sensTpl).die("click").live("click",function(){
	 	        		 var sensitiveType = $('.sensitiveType');
	 	       		    var sensitiveDesc = $('.sensitiveDesc');
	 	        		 if(!sensitiveType.val()){
	 	        			sensitiveType.css("border-color","red")
	 	        			 return false;
	 	        		 }
	 	        		if(!sensitiveDesc.val()){
	 	        			sensitiveDesc.css("border-color","red")
	 	        			 return false;
	 	        		 }
	 	        	        $.eAjax({
	 	        		        url: GLOBAL.WEBROOT + '/chat/setSensUser',
	 	        		        data: {
	 	        		        	staffCode:userCode.split('_')[1],
	 	        		        	sensitiveType:sensitiveType.val(),
	 	        		        	sensitiveDesc:sensitiveDesc.val(),
	 	        		        },
	 	        		        datatype: 'json',
	 	        		        async: false,
	 	        		        success: function(data){
	 	        		        	if(data.resultFlag == 'ok'){
	 	        		        		sensBlock.addClass('hadSign');
	 	        		        		$('.sensBtn',chatMain).hide();
	 	        		        		$('.btn-signed .cont',chatMain).html(data.resultMsg);
	 	        		        	}
	 	        		        	else{
	 	        		        		eDialog.error("设置敏感用户失败！")
	 	        		        	}
	 	        		        	
	 	        		        
	 	        		          },
	 	        		         error:function(){
	 	        		        	eDialog.error("设置敏感用户失败！")
	 	        		         }
	 	        		        });
	 	        	     	bDialog.closeCurrent();
	 	        		        
	 	        	  });
	 	        	  
                       $('.btn-canter',sensTpl).die("click").live("click",function(){
                    		bDialog.closeCurrent();
	 	        	  });
	        	   }
	        	 
	        	}
	        });
        /* 标记为敏感客户 end */
        
        //加载用户信息
        $('.head-inner img',winItem).qtip({
    	    content: {
    	        text: 'Loading...', // The text to use whilst the AJAX request is loading
    	        ajax: {
    	        	url: GLOBAL.WEBROOT + '/chat/getcustinfo',
    	            type: 'post',
    	            data: {
    	            	staffCode:$('.head-inner .name',winItem).text(),
    	            },
    	            success: function(data, status) {
    	            	data=JSON.parse(data);
    	            	var usrInfo=template('staffInfoTpl',data.values);
    	                this.set('content.text', usrInfo);
    	            }
    	        }
    	    },
	         hide: {
	             fixed: true,
	             delay: 300
	         },
	         style: {
	                classes: 'qtip-bootstrap'
	            },
	         position:{
	        	 my: 'top center',
                  at: 'bottom center'
	         }
    	});
        
        /* 发送快捷键切换   */
        $('.btn-keysel .arrow',winItem).click(function () {
            $(this).siblings('.sel-list').slideToggle();
        });
        $('.btn-keysel li',winItem).click(function () {
        	var keyLi= $(this);
        	keyLi.addClass('checked').siblings().removeClass('checked');
        	keyLi.parent().slideToggle();
        	$.cookie('ueditorShortKeyIndex_'+$('#staffCode').val(),keyLi.index(),{expires:100});
        });
    
	},
	/**
	 * 
	 * @param p2 MessageHistoryRespVO
	 *
	 * @returns {String}  历史面板中的消息
	 */
	_createMessageHtml : function(p2){
		var content = p2.body;
		if(p2.messageType == "msg"){
			//todo
		}else if(p2.messageType == "gds"){
			var bd = $.parseJSON(p2.body);
			content = '<div class="cont">商品名称：'+bd.gdsName+'</div>'
			+'<div class="cont">商品价格：'+(bd.price!=undefined?bd.price:"")+'</div>';
		}else if(p2.messageType == "order"){
			var bd = $.parseJSON(p2.body);
			content = '<div class="cont">订单编号：'+bd.ordId+'</div>'
			+'<div class="cont">订单价格：'+(bd.price!=undefined?bd.price:"")+'</div>'
			+'<div class="cont">下单时间：'+ebcDate.dateFormat(new Date(Number(bd.createTime)), "yyyy-MM-dd hh:mm:ss")+'</div>';
		}
		var li = $('<li><p class="mh"><span class="uName">'
				+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))
				+'</span><span class="cTime">'
				+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")
				+'</span></p><div class="cont">'
				+content+'</div></li>');
		return li;
	},
	/**
	 * domInfo:{messageType,uName,custPic}
	 * record: MessageHistoryRespVO
	 * side: left or right .. chat-right
	 * 
	 * @returns {String}  对话框中的消息
	 */
	_createMessageDom : function(domInfo, record, side){
		var html,tplData,msg;
		switch(domInfo.messageType){
			case 'gds':
				msg = $.parseJSON(record.body);
				tplData = {
		    		uName: domInfo.uName,
		    		time: ebcDate.dateFormat(record.beginDate, "yyyy-MM-dd hh:mm:ss"),
		    		custPic : domInfo.custPic,
		    		// TODO 补充返回的参数skuId
		        	url : $("#gdsDetailUrl").val() + msg.gdsId +"-",
		    		gdsId : msg.gdsId,
		    		gdsImage : msg.gdsImage,
		    		gdsName : msg.gdsName,
		    		gdsPrice : msg.price,
		    		chatSide : side
		        };
				html = template('recordGds', tplData);
				break;
			case 'order':
				msg = $.parseJSON(record.body);
				tplData = {
		    		uName: domInfo.uName,
		    		time: ebcDate.dateFormat(record.beginDate, "yyyy-MM-dd hh:mm:ss"),
		    		custPic : domInfo.custPic,
		    		ordId : msg.ordId,
		    		price : msg.price,
		    		ordImage : msg.ordImage,
		    		createTime : ebcDate.dateFormat(isNaN(Number(msg.createTime))?msg.createTime:new Date(Number(msg.createTime)), "yyyy-MM-dd hh:mm:ss"),
//		    		url : "javascript:orderDetail('"+ msg.ordId +"')"
		    		chatSide : side
		        }
				html = template('recordOrder1', tplData);
				break;
			default:
				html = "";
		}
		return html;
	},
	/**
	 * 初始化常用语
	 */
	_loadPhrasebookDom: function(){
		var chatbox = this;
		var phrasebookGroup = [];
		var phrasebookGroupPub = [];
		//个人
		$.eAjax({
	        url: GLOBAL.WEBROOT + '/phrasebook/list',
	        data: {
	        	shopId: ChatBoxUtil.shopId,
	        	groupClass: "20"
	        },
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	phrasebookGroup = returnInfo;
	        },
	        exception: function() {
	            eDialog.alert("个人常用语加载失败！");
	            return;
	        }
        });
		//公共
		$.eAjax({
	        url: GLOBAL.WEBROOT + '/phrasebook/list',
	        data: {
	        	shopId: ChatBoxUtil.shopId,
	        	groupClass: "10"
	        },
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	phrasebookGroupPub = returnInfo;
	        },
	        exception: function() {
	            eDialog.alert("公共常用语加载失败！");
	            return;
	        }
        });
		var proData = {
			chatId: chatbox.chatId,
			phrasebookGroup: phrasebookGroup, //个人常用语
			phrasebookGroupPub: phrasebookGroupPub //公共常用语
        };
        var html = template('lanListTpl', proData);
        var comLanPanel = $('#phrasebookPanel'+chatbox.chatId, chatbox.box);
        comLanPanel.empty().append(html);
        var scrollPanel=$('.chat-lan-group-wrap', chatbox.box);
        ChatBoxUtil.setBoxHeight(scrollPanel);
        scrollPanel.mCustomScrollbar({
            scrollInertia: 150,
            autoHideScrollbar:true
        });
     
        return phrasebookGroup;
	},
	_phrasebookRequest: function(){
		var ajaxreq = function(url,data,callback){
			var result = {};
			$.eAjax({
    	        url: url,
    	        data: data,
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    	        	if(callback&&$.isFunction(callback)) result = callback(returnInfo) 
    	        	else result = returnInfo;
    	        },
    	        exception: function() {
    	            eDialog.alert("常用语业务调用失败！");
    	            return;
    	        }
            });
			return result;
		};
		return{
			addGroup: function(name){
				var url = GLOBAL.WEBROOT + '/phrasebook/group/save';
				var data = {
						groupName:name,
						groupClass:"20",
						shopId:ChatBoxUtil.shopId
					};
				return ajaxreq(url,data);
			},
			updateGroup: function(id,name){
				var url = GLOBAL.WEBROOT + '/phrasebook/group/save';
				var data = {
						id:id,
						groupName:name
					};
				return ajaxreq(url,data);
			},
			deleteGroup: function(id){
				var url = GLOBAL.WEBROOT + '/phrasebook/group/delete';
				var data = {id:id};
				return ajaxreq(url,data);
			},
			switchGroup: function(id,id2){
				var url = GLOBAL.WEBROOT + '/phrasebook/group/switch/'+id+'/'+id2;
				var data = {};
				return ajaxreq(url,data);
			},
			addItem: function(text,groupId){
				var url = GLOBAL.WEBROOT + '/phrasebook/item/save';
				var data = {
						itemText:text,
						groupId:groupId
					};
				return ajaxreq(url,data);
			},
			updateItem: function(id,text){
				var url = GLOBAL.WEBROOT + '/phrasebook/item/save';
				var data = {
						id:id,
						itemText:text
					};
				return ajaxreq(url,data);
			},
			deleteItem: function(id){
				var url = GLOBAL.WEBROOT + '/phrasebook/item/delete';
				var data = {
						id:id
					};
				return ajaxreq(url,data);
			},
			switchItem: function(id,id2){
				var url = GLOBAL.WEBROOT + '/phrasebook/item/switch/'+id+'/'+id2;
				var data = {};
				return ajaxreq(url,data);
			}
		}
	},
	/**
	 * 加载常用语
	 */
	_loadPhrasebook: function(){
		var chatbox = this;
		chatbox._loadPhrasebookDom();//初始化
		var winItem = chatbox.box;
		//常用语
		$('.pro-lan .nav-tabs a',winItem).click(function (e) {
			  e.preventDefault();
			  $(this).tab('show');
		})
		// 常用语
        $('.chat-lan-group',winItem).delegate(".hd","click",function () {
        	var self = $(this);
        	var scrollPanel= $('.chat-lan-group-wrap', chatbox.box);
            $(this).siblings('.bd').slideToggle("fast",function(){
            	rerender(self);
            });
            $('i',$(this)).toggleClass('micon-up').toggleClass('micon-down');
        });
		// 快捷回复
		var navWrap = $('.nav-wrap',chatbox.box).navPageScroll();
		$('.clkReply', winItem).click(function () {
			var index = $('#phrasebookPanel'+chatbox.chatId).index();
			navWrap.scrollToIndex(index);
			$('.chat-side .nav-tabs a',winItem).eq(index).trigger("click");
        });
		//刷新高度
		function rerender(pbj){
			var scrollPanel= $('.chat-lan-group-wrap', chatbox.box);
			if(pbj&&pbj[0]){
				scrollPanel = pbj.parents('.chat-lan-group-wrap')
			}
	        ChatBoxUtil.setBoxHeight(scrollPanel);
	        if(scrollPanel.hasClass('mCustomScrollbar')){
	            scrollPanel.mCustomScrollbar("update");
	        }else{
	            scrollPanel.mCustomScrollbar({
		            autoHideScrollbar:true
		        });
	        }
	   
		}
		var contextAttach = function(){
			//分组右键菜单
	        context.attach('.chat-lan-group .groupA', [
//	            {text: '编辑', href: '#',clz:'context-editor',action:function (event) {
//	                var clkTarget=$(event.data.target);
//	
//	            }},
	            {text: '删除', href: '#',clz:'context-del',action:function(event){
	            	var event = event || window.event;
	                var curItem=$(event.data.target).parents(".item");
	            	var id = curItem.attr("groupId");
	            	//移除元素
	            	curItem.remove();
	            	//删除
	            	ChatBoxUtil.getCurrent()._phrasebookRequest().deleteGroup(id);
	            }},
	            {text: '上移', href: '#',clz:'context-up',action:function (event) {
	                var event = event || window.event;
	                var curItem=$(event.data.target).parents(".item");
	                var prevItem=curItem.prev(".item");
	                if(prevItem.size()>0){
	                    prevItem.before(curItem);
	                    //请求
		                var id = curItem.attr("groupId");
		                var id2 = prevItem.attr("groupId");
		                ChatBoxUtil.getCurrent()._phrasebookRequest().switchGroup(id,id2);
	                }
	            }},
	            {text: '下移', href: '#',clz:'context-down',action:function (event) {
	                var event = event || window.event;
	                var curItem=$(event.data.target).parents(".item");
	                var nextItem=curItem.next(".item");
	                if(nextItem.size()>0){
	                    nextItem.after(curItem);
	                    //请求
		                var id = curItem.attr("groupId");
		                var id2 = nextItem.attr("groupId");
		                ChatBoxUtil.getCurrent()._phrasebookRequest().switchGroup(id,id2);
	                }
	            }}
	        ]);
	        
	        //常用语右键菜单
	        context.attach('.chat-lan-group .lanItemA', [
//	            {text: '编辑', href: '#',clz:'context-editor',action:function (event) {
//	                var clkTarget=$(event.data.target);
//	
//	            }},
	            {text: '删除', href: '#',clz:'context-del', action:function (event){
	            	var event = event || window.event;
	            	var eTaget = $(event.data.target);
	            	var id = eTaget.closest("a").attr("itemId");
	            	//移除元素
	            	eTaget.closest("li").remove();
	            	//删除
	            	ChatBoxUtil.getCurrent()._phrasebookRequest().deleteItem(id);
	            }},
	            {text: '上移', href: '#',clz:'context-up',action:function (event) {
	            	var event = event || window.event;
	                var curItem=$(event.data.target).parents("li");
	                var prevItem=curItem.prev(".lanItemA");
	                if(prevItem.size()>0){
	                    prevItem.before(curItem);
	                    //请求
		                var id = curItem.find("a").attr("itemId");
		                var id2 = prevItem.find("a").attr("itemId");
		                ChatBoxUtil.getCurrent()._phrasebookRequest().switchItem(id,id2);
	                }
	            }},
	            {text: '下移', href: '#',clz:'context-down',action:function (event) {
	            	var event = event || window.event;
	                var curItem=$(event.data.target).parents("li");
	                var nextItem=curItem.next(".lanItemA");
	                if(nextItem.size()>0){
	                    nextItem.after(curItem);
	                    //请求
		                var id = curItem.find("a").attr("itemId");
		                var id2 = nextItem.find("a").attr("itemId");
		                ChatBoxUtil.getCurrent()._phrasebookRequest().switchItem(id,id2);
	                }
	            }}
	        ]);
	        //单击事件
	        $('.chat-lan-group .lanItemA').on('click', function(e){
	        	var text = $(this).find("a").html();
	        	var ue = ChatBoxUtil.getCurrent().msgInput;
	        	ue.setContent(text||"");
	        	ue.focus(true);
	        });
	        
	        //单击事件
	        $('.chat-lan-group .lanItemGroup').on('click', function(e){
	        	var text = $(this).find("a").html();
	        	var ue = ChatBoxUtil.getCurrent().msgInput;
	        	ue.setContent(text||"");
	        	ue.focus(true);
	        });
	        
	        //重新刷新
	        rerender();
		}
		contextAttach();
        $('.lan-new',winItem).click(function () {
        	var chatbox1 = chatbox;
        	//只能新增“个人”
        	//数据准备
        	//个人
        	var phrasebookGroup = [];
    		$.eAjax({
    	        url: GLOBAL.WEBROOT + '/phrasebook/list',
    	        data: {
    	        	shopId: ChatBoxUtil.shopId,
    	        	groupClass: "20"
    	        },
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    	        	phrasebookGroup = returnInfo;
    	        },
    	        exception: function() {
    	            eDialog.alert("个人常用语加载失败！");
    	            return;
    	        }
            });
    		var tplData = {
    				chatId : chatbox1.chatId,
    				phraseGroup : phrasebookGroup,
    				selectedId : phrasebookGroup.length>0?phrasebookGroup[0].id:"",
    				selectedName : phrasebookGroup.length>0?phrasebookGroup[0].groupName:""
    		};
            var tpl=template('addLanTpl',tplData);
            bDialog.open({
                'width' : 550,
                'height' : 250,
                'title' : "新增常用语",
                'customClass' : 'lanDialog'
            },tpl);
            var editorId = 'lanEditor'+chatbox1.chatId;
            UE.delEditor(editorId);
            var uemaximumWords = 500;
            var ue = UE.getEditor(editorId, {
                initialFrameHeight: '100',
                initialFrameWidth: '100%',
                elementPathEnabled: false,
                wordCount: true,
                maximumWords: uemaximumWords,
                autoHeightEnabled: false,
                wordOverFlowMsg: '<span style="color:red;">你输入的字符个数已经超出最大允许值！</span>',
                toolbars: [
                    ['emotion']
                ]
            });
            //分组菜单下拉表隐藏
            ue.on('mousedown',function(e){
                //收起下拉
                $('.select-body').slideUp();
            });
            chatbox1.phrasebookUE = ue;

            $('.lan-select .micon-arrow').click(function () {
                $(this).parents('.lan-select').find('.select-body').slideToggle();
            });
            $('.lan-select').delegate("li","click",function () {//option选中
                $(this).addClass('current').siblings().removeClass('current');
                $(this).parents('.lan-select').find('.selected').text($(this).text()).attr("value",$(this).attr("value"));
            });
            $('.lan-select .new-gp-clk').click(function () {//新增 分组
                $(this).hide();
                $(this).siblings('.new-gp-txt').show();
            });
            $('.lan-select .new-gp-txt .group-save').click(function () {//添加 分组 
                $(this).parent().hide();
                $(this).parent().siblings('.new-gp-clk').show();
                
                var name = $(this).siblings("input.in").val();
                if(!name) return;
                //request
                var result = chatbox1._phrasebookRequest().addGroup(name);
                //清除
                $(this).siblings("input.in").val("");
                var value = result.id;
                //添加到下拉框
                var li = $("<li>").attr("value", value).text(name);
                $(this).parents('.select-body').find('ul.selects').append(li);
                $(this).parents('.lan-select').find('.selected').text(name).attr("value",value);
                //添加到右侧
                var tplData = {
                		id: value,
                		groupName: name
                };
                var tpl=template('lanGroupTpl',tplData);
                $("#comLan01"+chatbox1.chatId, chatbox1.box).find(".chat-lan-group").append(tpl);
                //刷新右键事件
                contextAttach();
            });
            $('.group-item-cancel').click(function () {//取消保存  常用用语
            	//关闭弹出框
                bDialog.closeCurrent();
            });
            $('.group-item-save').click(function () {//保存  常用用语
            	var groupId = $(this).closest('div.finner').find('.selected').attr('value');
            	var ue = chatbox1.phrasebookUE;
            	var text = ue.getContent();
            	if(!groupId){
            		eDialog.alert("先选择分组！");
            		return;
            	}
            	if(!text){
            		eDialog.alert("常用语不能为空！");
            		return;
            	}
            	var textLength = ue.getContentLength();
            	if(textLength>uemaximumWords){
            		eDialog.alert("请限制内容长度！注意，表情比普通字符占用更多空间！");
            		return;
            	}
            	
            	var result = chatbox1._phrasebookRequest().addItem(text, groupId);
            	var id = result.id;
            	//保存到右侧
            	var tplData = {
                		id: id,
                		itemText: text
                };
                var tpl=template('lanItemTpl',tplData);
                $("#comLan01"+chatbox1.chatId, chatbox1.box).find("div[groupId='"+groupId+"']").find("ul.chat-lans").append($(tpl));
                //刷新右键事件
                contextAttach();
                //关闭弹出框
                bDialog.closeCurrent();
            });
            
            $(window).on('click.lanSelect',function (event) {
                var event = event || window.event;
                var target = event.target || event.srcElement;

                if($(target).parents('.new-gp').size()<=0 && !$(target).hasClass('new-gp')){
                    $('.new-gp-clk').show();
                    $('.new-gp-txt').hide();
                }
            });
            
        })
	},
	_createHistoryMsg:function(){
		var userCode = this.chatId;
		var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
		var chatBox = this;
        $.eAjax({
	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
	        data: {
	        	userCode:userCode,
	        	csaCode:csaCode,
//	        	messageType:'msg',
	        	pageNumber:ChatBoxUtil.pageNum,
	        	pageSize:ChatBoxUtil.pageSize,
	        	shopId : ChatBoxUtil.shopId,
	        	status:'20'
	        },
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	returnInfo = returnInfo.reverse();
	        	var winItem = this.box;
  	    		var chatMain = $('#chatMain'+userCode,winItem);
  	    		var chatRecord = $(".chat-record", chatMain);
  	            var chartList = $('.list', chatRecord);
	        	$.each(returnInfo, function(p1, p2) {
	        	
	        	   if(p2.from ==  ChatBoxUtil.connection.jid){
	        		   
	        	            var tplData = {
	        	            		msg: p2.body,
	        	            		uName: ChatBoxUtil.ServName,
	        	            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
	        	            		custPic : ChatBoxUtil.servPic
	        	            }
	        	            var html = template('selfRecord', tplData);
	        	            if(p2.messageType != "msg"){
	        	            	var domInfo = {
	        	            			messageType: p2.messageType,
	        	            			uName: ChatBoxUtil.ServName,	
	        	            			custPic: ChatBoxUtil.servPic
	        	            	};
	        	            	html = chatBox._createMessageDom(domInfo, p2, "chat-right");
	        	            }
	        	            $(html).appendTo(chartList);
	        	           
	        		}else{
	        			var staffCode = ChatBoxUtil.getStaffCodeByUserCode(p2.userCode);
		                var tplData = {
		            		msg: p2.body,
		            		uName:staffCode,
		            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
		            		custPic : chatBox.custPic
		                }
		                var html = template('record', tplData);
		                if(p2.messageType != "msg"){
		                	var domInfo = {
		                			messageType: p2.messageType,
		                			uName: staffCode,	
		                			custPic: chatBox.custPic
		                	};
		                	html = chatBox._createMessageDom(domInfo, p2, "");
		                }
		                $(html).appendTo(chartList);
	        		}
	        	  
	        	});
	        	 chatRecord.mCustomScrollbar('update');
	        },
	        exception: function() {
	            eDialog.alert("网络异常");
	            return;
	        }
        });
		
		
		
	},
	/**
	 * 聊天记录
	 */
	_loadChatHis: function(){
		var chatBox = this;
		var winItem = this.box;
		var proData = {
				chatId:this.chatId,
				isWho : ChatBoxUtil.constants.SERV
		};
		var chatSideHis = $(".chat-hisMgs", winItem);
        var html = template('hisMgsTpl', proData);
        chatSideHis.empty().append(html);
        var chatId = this.chatId;
      
        $('.clkRecord', winItem).click(function () {
        	winItem.toggleClass('hasMsgPanel');
        	$('#message').val('');
        	$('#gx').val('');
        	//chatBox._loadChatHisAjax();
            //共享聊天记录
        	
            chatBox._hisMsgShare(ChatBoxUtil.pageNum,ChatBoxUtil.pageSize);
        });
        
        //图片放大
        eImage.gallery($('.chat-record', winItem),{
            bigImg:".msg img",
            ignoreImg:'uImg'
        });
        
        //历史消息模糊查询
        //this._hisMsgLike();
    
	},
	_loadChatHisAjax:function(){
		   var chatBox = this;
		   var winItem = this.box;
		   var chatId = this.chatId;
           var hMsgList = $("#msgPanel"+chatId, winItem).find('.msgPanelCont');
           hMsgList.empty();
           var userCode = chatId;
   		   var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
   		   var date = new Array();
   		  var body = $('#message').val();
           $.eAjax({
   	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
   	        data: {userCode:userCode,csaCode:csaCode,body:body,pageNumber:ChatBoxUtil.pageNum,pageSize:ChatBoxUtil.pageSize,shopId:ChatBoxUtil.shopId},
   	        datatype: 'json',
   	        async: false,
   	        success: function(returnInfo) {
   	          	returnInfo = returnInfo.reverse();
   	         	$.each(returnInfo, function(p1, p2) {
   	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
   	        		date[p1] = day;
   	        	});
   	         	date = $.unique(date);
   	        	for (var key in date){
   	        		var time = $('<div class="time"> <div class="txt">'+ebcDate.dateFormat(date[key], "yyyy-MM-dd")+'</div> </div>');
   	        		hMsgList.append(time);
   	        		var body = $('<ul class="msg-list"></ul>');
   	         	  	$.each(returnInfo, function(p1, p2) {
       	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
       	        		if(day==date[key]){
       	        			var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
       	        			li = chatBox._createMessageHtml(p2);
       	        			body.append(li);
   	        			}
       	        	});
   	         	  	$(hMsgList).append(body);
	        		}
   	        }
           });
        	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
           if (hMsgList.hasClass('mCustomScrollbar')) {
               hMsgList.mCustomScrollbar("update");
           } else {
               hMsgList.mCustomScrollbar({autoHideScrollbar:true});
           }
	},
	/**
	 * 历史消息模糊查询
	 */
//	_hisMsgLike:function(){
//		var chatBox = this;
//		var winItem = this.box;
//		//历史记录模糊查询
//		var hMsgList = $("#msgPanel"+this.chatId, winItem);
//		var msgsearch = $(".msg-search", hMsgList);
//		var sbtn = $(msgsearch).find('.sbtn');
//		var chatId = this.chatId;
//		sbtn.click(function(){
//			 var hMsgList = $("#msgPanel"+chatId, winItem).find('.msgPanelCont');
//			 hMsgList.empty();
//	        	var userCode = chatId;
//	    		var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
//	    		var date = new Array();
//	    		var body = $(msgsearch).find('input').val();
//	    		ChatBoxUtil.pageNum = 1;
//	    		ChatBoxUtil.pageSize =10;
//	            $.eAjax({
//	    	        url: GLOBAL.WEBROOT + '/history/getMessageHistoryDate',
//	    	        data: {userCode:userCode,csaCode:csaCode,body:body,pageNumber:ChatBoxUtil.pageNum,pageSize:ChatBoxUtil.pageSize},
//	    	        datatype: 'json',
//	    	        async: false,
//	    	        success: function(returnInfo) {
//	    	        	returnInfo = returnInfo.reverse();
//	    	        	if(returnInfo.length==0){
//	    	        		var _p = $('<p style="margin-left:5%">无搜索结果</p>');
//	    	        		$(hMsgList).html(_p);
//	    	        	}
//	    	         	$.each(returnInfo, function(p1, p2) {
//	    	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
//	    	        		date[p1] = day;
//	    	        	});
//	    	         	date = $.unique(date);
//	    	        	for (var key in date){
//	    	        		var time = $('<div class="time"> <div class="txt">'+ebcDate.dateFormat(date[key], "yyyy-MM-dd")+'</div> </div>');
//	    	        		hMsgList.append(time);
//	    	        		var body = $('<ul class="msg-list"></ul>')
//	    	         	  	$.each(returnInfo, function(p1, p2) {
//	        	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
//	        	        		if(day==date[key]){
//	        	        			var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
//	        	        			li = chatBox._createMessageHtml(p2);
//	        	        			body.append(li);
//	    	        			}
//	        	        	});
//	    	        		$(hMsgList).append(body);
//		        		}
//	    	        }
//	            });
//	            hMsgList.mCustomScrollbar();
//	           /* if (hMsgList.hasClass('mCustomScrollbar')) {
//	                hMsgList.mCustomScrollbar('update');
//	            } else {
//	            	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
//	                hMsgList.mCustomScrollbar({autoHideScrollbar:true});
//	            }*/
//			
//		});
//	},
	/**
	 * 消息共享
	 */
	_hisMsgShare:function(pageNum,pageSize){
		var chatBox = this;
		var winItem =this.box;
		var hMsgList = $("#msgPanel"+this.chatId, winItem).find('.msgPanelCont');
      	var userCode = this.chatId;
  		var date = new Array();
  		var msgsearch = $(".msg-search", hMsgList);
  		var body = $(winItem).find('#gx');
  		body  = body.val();
          $.eAjax({
  	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
  	        data: {userCode:userCode,shopId:ChatBoxUtil.shopId,body:body,pageNumber:pageNum,pageSize:pageSize},
  	        datatype: 'json',
  	        async: false,
  	        success: function(returnInfo) {
  	        	//有数据才清除
  	        	if(returnInfo.length>0){
  	        		hMsgList.empty();
  	        	}
  	        	returnInfo = returnInfo.reverse();
  	         	$.each(returnInfo, function(p1, p2) {
  	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
  	        		date[p1] = day;
  	        	});
  	         	date = $.unique(date);
  	        	for (var key in date){
  	        		var time = $('<div class="time"> <div class="txt">'+ebcDate.dateFormat(date[key], "yyyy-MM-dd")+'</div> </div>');
  	        		hMsgList.append(time);
  	        		var body = $('<ul class="msg-list"></ul>');
  	         	  	$.each(returnInfo, function(p1, p2) {
      	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
      	        		if(day==date[key]){
      	        			var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
      	        			li = chatBox._createMessageHtml(p2);
      	        			body.append(li);
  	        			}
      	        	});
  	         	        $(hMsgList).append(body);
	        		}
  	           if (hMsgList.hasClass('mCustomScrollbar')) {
  	        	 	hMsgList.mCustomScrollbar("destroy");
	                hMsgList.mCustomScrollbar();
	            } else {
	            	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
	                hMsgList.mCustomScrollbar({autoHideScrollbar:true});
	            }
  	         
  	        }
          });
          //共享消息模糊查询
          this._shareMsgLike();
	},
	/**
	 * 共享消息模糊查询
	 */
	_shareMsgLike:function(){
		var chatBox = this;
		var winItem = this.box;
		//历史记录模糊查询
		var hMsgList = $("#msgPanel"+this.chatId, winItem);
		var msgsearch = $(".msg-search", hMsgList);
		var sbtn = $(msgsearch).find('.sbtn');
		var chatId = this.chatId;
		sbtn.unbind('click').click(function(){
			ChatBoxUtil.pageNums = 1;
			ChatBoxUtil.pageSizes =10;
			 var hMsgList = $("#msgPanel"+chatId, winItem).find('.msgPanelCont');
			 hMsgList.empty();
	        	var userCode = chatId;
	    		var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
	    		var date = new Array();
	    		var body = $(msgsearch).find('input').val();
	            $.eAjax({
	    	        url: GLOBAL.WEBROOT + '/history/getMessageHistoryDate',
	    	        data: {userCode:userCode,body:body,pageNumber:ChatBoxUtil.pageNum,pageSize:ChatBoxUtil.pageSize,shopId:ChatBoxUtil.shopId},
	    	        datatype: 'json',
	    	        async: false,
	    	        success: function(returnInfo) {
	    	        	returnInfo = returnInfo.reverse();
	    	        	if(returnInfo.length==0){
	    	        		var _p = $('<p style="margin-left:5%">无搜索结果</p>');
	    	        		$(hMsgList).html(_p);
	    	        	}
	    	         	$.each(returnInfo, function(p1, p2) {
	    	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
	    	        		date[p1] = day;
	    	        	});
	    	         	date = $.unique(date);
	    	        	for (var key in date){
	    	        		var time = $('<div class="time"> <div class="txt">'+ebcDate.dateFormat(date[key], "yyyy-MM-dd")+'</div> </div>');
	    	        		hMsgList.append(time);
	    	        		var body = $('<ul class="msg-list"></ul>');
	    	         	  	$.each(returnInfo, function(p1, p2) {
	        	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
	        	        		if(day==date[key]){
	        	        			var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
	        	        			li = chatBox._createMessageHtml(p2);
	        	        			body.append(li);
	    	        			}
	        	        	});
	    	         		$(hMsgList).append(body);
		        		}
	    	        }
	            });
	        //    hMsgList.mCustomScrollbar();
	            if (hMsgList.hasClass('mCustomScrollbar')) {
	            	hMsgList.mCustomScrollbar("destroy");
	                hMsgList.mCustomScrollbar();
	            } else {
	            	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
	                hMsgList.mCustomScrollbar({autoHideScrollbar:true});
	            }
			
		});
	},
	_createBox: function(){
		var chatWin=$('.chat-win');
	/*	if(this.model == ChatBoxUtil.constants.CUST){
			chatWin=$('#chatPanel');
		}*/
		var winItem=$('<div class="winItem"></div>');
		this.box = winItem; //设置box
        chatWin.append(winItem);
     
        /* 查询用户信息 begin*/
        var sessionid = this.sessionId;
        var issueType, gdsId,ordId,userCode,place='00';
		 $.eAjax({
		 	        url: GLOBAL.WEBROOT + '/history/getSessionByone',
		 	        data: {id:sessionid},
		 	        dataType: 'json',
		 	        async: false,
		 	        success: function(returnInfo) {
		 	        	issueType=returnInfo.issueType;
		 	        	gdsId=returnInfo.gdsId;
		 	        	ordId=returnInfo.ordId;
		 	        	userCode=returnInfo.userCode;
		 	        },
		 	        exception: function() {
		 	            eDialog.alert("网络异常");
		 	            return;
		 	        }
		});
        if(gdsId){
        	place='02';//商品详情
        }else if(ordId){
        	place='01';//订单
        }
		/* 查询用户信息 end*/
        var html = template('winItemTpl',{'chatId':this.chatId,'place':place,'model':ChatBoxUtil.constants.SERV});
        winItem.append(html);
        winItem.attr('id','winItem'+this.chatId);
        
        this._loadChat();//聊天工作区
        this._loadPro(userCode,place,issueType, gdsId,ordId);//右侧信息
        this._loadAllord(userCode,place,issueType, gdsId,ordId);//所有订单
        this._loadProQue();//常见问题
        this._loadChatHis();//聊天记录
        this._createHistoryMsg();//最近会话记录 
        this._loadPhrasebook();//常用语

    //    $('.nav-wrap',winItem).navPageScroll();
        
        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var editorId = 'editor'+this.chatId;
        UE.delEditor(editorId);
        var ue = UE.getEditor(editorId, {
            initialFrameHeight: '100',
            initialFrameWidth: '100%',
            elementPathEnabled: false,
            wordCount: false,
            autoHeightEnabled: false,
            autoFloatEnabled: false,
            enableAutoSave: false,
            serverUrl:GLOBAL.WEBROOT+"/uedit/upload", 
            toolbars: [
                ['emotion','simpleupload']
            ]
        });
        this.msgInput = ue; //设置
        var chatBox = this;
        ue.addListener("keydown", function(i,e){ 
        	var enterIndex=$('.btn-keysel li.checked',winItem).index();
        	if(enterIndex==0){
            	if(e.ctrlKey && e.which == 13 || e.which == 10) { 
        			chatBox.sendMessage();
        		}
        	}else{
        		if(e.which == 13 || e.which == 10) { 
        			chatBox.sendMessage();
        		}
        	}
    	});
        //在线状态下拉表隐藏
        ue.on('mousedown',function(e){
            if($(e.target).parents('.hState').size()<=0 && !$(e.target).hasClass('hState')){
                $('.ui-states').hide();
            }
        });
        
       
        $('.parse-help',winItem).qtip({
            content: function () {
                return "将您已截好的图片直接粘贴至输入框中即可（说明：目前暂不支持IE浏览器）";
            },
            style: {
                classes: 'qtip-bootstrap qtip-eval'
            },
            position: {
                my: 'bottom center',
                at: 'top center'
            }
        });
        
	},
	_createTab: function(){
		var chatList=$('.chat-uList');
		var staffCode = ChatBoxUtil.getStaffCodeByUserCode(this.chatId);
		var staffInfo = ChatBoxUtil.getStaffInfo(staffCode);
		this.custPic = staffInfo.custPic;
		var html = template('chatItemTpl',{'uName':staffCode, 'chatId':staffCode,'sessionId':this.sessionId,'custPic':staffInfo.custPic});
		this.tab = $(html);
		chatList.append(this.tab);
		
	    var useListScroll=$('.chat-uContainer');
        useListScroll.mCustomScrollbar('update');
               
	    /* 获取等待人数  begin*/
		function getWaitCount(){
			 $.eAjax({
		 	        url: GLOBAL.WEBROOT + '/chat/getWaitCount',
		 	        data: {shopId:$('#shopId').val()},
		 	        dataType: 'text',
		 	        async: true,
		 	        success: function(returnInfo) {
		 	        	var waitNum=$('.wait-num');
		 	            waitNum.show();
		 	            $('.num',waitNum).text(returnInfo);
		 	        },
		 	        exception: function() {
		 	            eDialog.alert("网络异常");
		 	            return;
		 	        }
		   });
		}
		getWaitCount();
	    window.setInterval(function(){
	    	getWaitCount();
	    },5000);
		
		/*获取等待人数  end*/
	},
	_initDom: function(){
		$('#initialize').hide();
		if(ChatBoxUtil.hasChatBox(this.chatId)) return;
		if(this.model == ChatBoxUtil.constants.SERV) this._createTab();
		this._createBox();
		if(ChatBoxUtil.isEmpty()){
			this._display();
		}
		ChatBoxUtil.list.push(this);
		
		$('.nav-wrap',this.box).navPageScroll();//初始化 navPage
		   
		this._bindEvent();
	},

	/**
	 *  绑定事件
	 */
	_bindEvent: function(){
		//1.消息发送
		var chatBox = this;
		$("#sendMsg", this.box).click(function(e){
			$("#sendMsg").attr("disabled","true");
			if(ChatBoxUtil.connected) chatBox.sendMessage();
			$("#sendMsg").removeAttr("disabled");
		});
		
		//会话列表tab选中//删除
		var csaCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
		if(this.tab){
			var chatId = this.chatId;
			this.tab.click(function(e){
				$('#initialize').hide();
				chatBox.show();
			});
			this.tab.find('.close').on('click',function(){
				eDialog.confirm("将从队列中清除该用户，如果买家未离线还可继续聊天", {
					buttons : [{
						caption : '确认',
						callback : function(){
						
							      $.eAjax({
						    	        url: GLOBAL.WEBROOT + '/history/updateSession',
						    	        data: {userCode:chatId,id:chatBox.sessionId,csaCode:csaCode},
						    	        datatype: 'json',
						    	        async: false,
						    	        success: function(returnInfo) {
						    	        	if(returnInfo.resultFlag=='ok'){
						    	        		chatBox.close();
						    	        	}
						    	        }
							      });
							
							return false;
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
			});
		};
		
		
		//客服列表
		$('.chat-header .trans', chatBox.box).hover(
				function(){
					var me = $(this);
					var iq = $iq({  
		                type: 'get'
		            }).c("transfer", {"xmlns":Strophe.NS.TRANSFER_IQ,
		            	"bizcode":"fetchCsa",
		            	"shopId":ChatBoxUtil.shopId
		            	}, "");
					ChatBoxUtil.connection.sendIQ(iq.tree(),
							function(iq){//成功
								var transfer = $(iq).find("transfer");
								//正常
								var hotlines = $.parseJSON(transfer.find("hotlines").text());
								var transList = me.next();
								transList.empty();
								for(var item in hotlines){
									item = hotlines[item];
									var li = $("<li>");
									li.text(item.hotlinePerson);
									li.attr("code", "csa_"+item.staffCode);
									transList.append(li);
								}
								
								//错误
								
				            },
				            function(iq){//失败
				            	
				            }    
		            ); 
				},
				function(){
					//do nothing
				}
		);
		//客服转出
		$('.trans-list',chatBox.box).delegate("li", "click", function () {
            $('.modal-header h3').hide();
            var me = $(this);
            var staffCode = ChatBoxUtil.getStaffCodeByUserCode(chatBox.chatId);
            var data = {csaName:me.text(),
            		userName:staffCode};
            var tpl = template('transTpl2', data);
            bDialog.open({
                'width': 550,
                'height': 150,
                'customClass': 'lanDialog imDialog2'
            }, tpl);
            
            $('.trans-item-cancel.out').click(function () {//取消
            	//关闭弹出框
                bDialog.closeCurrent();
            });
	        $('.trans-item-save.out').click(function () {//确定
	        	var csaCode = me.attr("code");//受理客服
	        	var csaName = me.text();//受理客服名称
	        	var iq = $iq({  
	                type: 'get'
	            }).c("transfer", {"xmlns":Strophe.NS.TRANSFER_IQ,
	            	"bizcode":"doTransfer",
	            	"sessionId":chatBox.sessionId,
	            	"userCode":ChatBoxUtil.getUserFromJid(chatBox.from), 
	            	"csaCode":csaCode,
	            	"csaName":csaName,
	            	"srcCsaName":ChatBoxUtil.hotlinePerson
	            	}, "");
				ChatBoxUtil.connection.sendIQ(iq.tree(),
						function(iq){//成功
							var transfer = $(iq).find("transfer");
							var status = transfer.attr("status");
							var iqmessage = transfer.text();
							//正常	
							if(status=="success"){
								ChatBoxUtil.showNotice(iqmessage,true,chatBox.box);
							}else{//错误
								ChatBoxUtil.showNotice(iqmessage,true,chatBox.box);
							}
							
							//关闭窗口
							bDialog.closeCurrent();
			            },
			            function(iq){//失败
			            	console.log(iq);
			            }    
	            ); 
	        });
        });
		
		
		//(共享)聊天记录分页绑定
		var msgPanel = $("#msgPanel"+this.chatId,chatBox.box);
		var msgPage = $(".msg-page", msgPanel);
		var sbtn = $('button',msgPage);
		var userCode = this.chatId;
		$(sbtn).click(function(){
			var body = $(chatBox.box).find('#gx');
			//获取历史消息总数
			var count=0;
			body = body.val();
		    $.eAjax({
    	        url: GLOBAL.WEBROOT + '/history/getMessageCount',
    	        data: {userCode:userCode,messageType:'msg',body:body,shopId:ChatBoxUtil.shopId},
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    				count = returnInfo;
    	        }
		    });
			if($(this).hasClass('page-next')){
				if(ChatBoxUtil.pageNums!=1){
					ChatBoxUtil.pageNums = ChatBoxUtil.pageNums - 1;
					chatBox._hisMsgShare(ChatBoxUtil.pageNums,ChatBoxUtil.pageSizes);
				}
			}
			var s = count / ChatBoxUtil.pageSizes;
			s = Math.ceil(s);
            if($(this).hasClass('page-prev')){
            	if(s>ChatBoxUtil.pageNums){
            		ChatBoxUtil.pageNums = ChatBoxUtil.pageNums + 1;
            		chatBox._hisMsgShare(ChatBoxUtil.pageNums,ChatBoxUtil.pageSizes);
            	}
			}
            
            if($(this).hasClass('page-last')){
            	//查询最后一页
            	if(s!=0){
            	    ChatBoxUtil.pageNums = 1;
            		chatBox._hisMsgShare(ChatBoxUtil.pageNums,ChatBoxUtil.pageSizes);
            	}
            	
            }
            
            if($(this).hasClass('page-first')){
            	//查询第一页
            	if(s!=0){
            		ChatBoxUtil.pageNums = s;
            		chatBox._hisMsgShare(ChatBoxUtil.pageNums,ChatBoxUtil.pageSizes);
            	}
            	
            }
		});
		
		
        var scrollClz='mCustomScrollbar';
		  /* 判断 浏览器resize beign*/
        $(window).on('resize.chatScroll',ChatBoxUtil.debounce(function(){
            var useListScroll=$('.chat-uContainer');
            if(useListScroll.hasClass(scrollClz)){
            	ChatBoxUtil.setBoxHeight(useListScroll);
                useListScroll.mCustomScrollbar('update');
            }
        	ChatBoxUtil.setBoxHeight($('.chat-serInfo'));
            $('.winItem','.chat-win').each(function(){
            	 if(!$(this).is(":hidden")){
            		  var $win=$(this);
            		  var chatRecord=$('.chat-record',$win);
                      var proPanel=$('.pro-panel',$win);
                      var quePanel=$('.que-panel',$win)
                      var allordPanel=$('.allord-panel',$win);
                      var msgPanelCont=$('.msgPanelCont',$win);
                      var ordPanel=$('.ord-detail-panel',$win);
                      if(chatRecord.hasClass(scrollClz)&&!chatRecord.is(":hidden")){
                         ChatBoxUtil.setBoxHeight(chatRecord,$win);
                         chatRecord.mCustomScrollbar('update');
                      }
                      if(proPanel.hasClass(scrollClz)&&!proPanel.is(":hidden")){
                      	ChatBoxUtil.setBoxHeight(proPanel,$win);
                          proPanel.mCustomScrollbar('update');
                      }
                      if(allordPanel.hasClass(scrollClz)&&!allordPanel.is(":hidden")){
                      	ChatBoxUtil.setBoxHeight(allordPanel,$win);
                      	allordPanel.mCustomScrollbar('update');
                      }
                      if(ordPanel.hasClass(scrollClz)&&!ordPanel.is(":hidden")){
                      	ChatBoxUtil.setBoxHeight(ordPanel,$win);
                      	ordPanel.mCustomScrollbar('update');
                      }
                      if(quePanel.hasClass(scrollClz)&&!quePanel.is(":hidden")){
                      	ChatBoxUtil.setBoxHeight(quePanel,$win);
                      	quePanel.mCustomScrollbar('update');
                      }
                      msgPanelCont.each(function(){
                    	  if($(this).hasClass(scrollClz)&&!$(this).is(":hidden")){
                          	ChatBoxUtil.setBoxHeight($(this),$win);
                          	$(this).mCustomScrollbar('update');
                          }
                      })
                  
                 }
              
            });
       },200));
      /* 判断 浏览器resize end*/
	
	
	}
		
}
/* ========= ChatBox end ========== */

/* TODO ========= ChatBoxUtil start ========== */
/**
 *  会话窗口工具
 */
var ChatBoxUtil = {
	constants: {
		SERV: "serv",
		CUST: "cust",
		baseH: 0
	},
	message:{
		body:"",
		messagetype:"",
		contentType:"",
		sessionId:"",
		sendTime:""
	},
	ServName:"",
	hotlinePerson:"",
	shopId:"",
	defPass:"123456",
	connection: "",
	welcomes : "您好，请问有什么可以帮助您的？",
	custPic : "",
	servPic : "",
	connected: false,
	BOSH_SERVICE: "",
	pageNum : 1,
	pageSize : 10,
	pageNums : 1,
	pageSizes : 10,
	model: "",//界面模型
	list: [],
	first: null,
	last: null,
	getSize: function(){
		return this.list.length;
	},
	hasChatBox: function(chatId){
		if(!chatId) throw new error("chatId is missing!");
		for(var chat in this.list){
			if(chat.chatId ==  chatId){
				return true;
			}
		}
		return false;
	},
	getChatIdFromJID: function(jid){
		if(!jid) return null;
		return Strophe.getNodeFromJid(jid) + Strophe.getResourceFromJid(jid);
	},
	getUserFromJid : function(jid){
		if(!jid) return null;
		return Strophe.getNodeFromJid(jid);
	},
	getChatBoxFromJID: function(jid){
		var chatId = this.getUserFromJid(jid);
		var list = this.list;
		if(list.length>0){
			for(var chat in list){
				if(list[chat].chatId == chatId){
					return list[chat];
				}
			}
		}
		
		return null;
	},
	getChatBoxByChatId: function(chatId){
		var list = this.list;
		if(list.length>0){
			for(var chat in list){
				if(list[chat].chatId == chatId){
					return list[chat];
				}
			}
		}
		
		return null;
	},
	getCurrent: function(){
		var list = this.list;
		if(list.length>0){
			for(var chat in list){
				if(list[chat].current){
					return list[chat];
				}
			}
		}
		return null;
	},
	isEmpty: function(){
		return !(this.list.length > 0);
	},
	setBoxHeight:function(domObj,winItem){
		 var calHeight = $(window).height()*.85- ChatBoxUtil.constants.baseH;
		 var navTabHeight=35;
		 if(domObj.hasClass('chat-serInfo')){
			 calHeight = calHeight;
		 }
		 else if(domObj.hasClass('chat-record')){
			 calHeight = calHeight - $('.chat-header',winItem).outerHeight()
                         - $('.chat-in',winItem).outerHeight()
                         - 55;
		 }
		 else if(domObj.hasClass('msgPanelCont')){
			 calHeight = calHeight  - $('.msg-head',winItem).outerHeight()
                         - $('#msgTab',winItem).outerHeight()
                          - $('.msg-search',winItem).outerHeight()
                         - 60;
		 }
		 else if(domObj.hasClass('pro-panel')){
			 calHeight = calHeight - navTabHeight - 52;
		 }
		 else if(domObj.hasClass('ord-detail-panel')){
			 calHeight = calHeight - navTabHeight - 65;
		 }
		 else if(domObj.hasClass('que-panel')){
			 calHeight = calHeight - 70;
		 }
		 else if(domObj.hasClass('allord-panel')){
			 calHeight = calHeight - $('.chat-side .nav-tabs',winItem).outerHeight() - 30;
		 }
		 else if(domObj.hasClass('chat-uContainer')){
			 calHeight = calHeight - $('.chat-nav .tit').outerHeight() - 20;
		 }
		 else if(domObj.hasClass('chat-lan-group-wrap')){
			 calHeight = calHeight -105;
		 }
		 
		 domObj.height(calHeight);
		 
	},
	debounce:function(func, wait, immediate){
		 var timeout;
         return function () {
             var context = this, args = arguments;
             var later = function () {
                 timeout = null;
                 if (!immediate) func.apply(context, args);
             };
             var callNow = immediate && !timeout;
             clearTimeout(timeout);
             timeout = setTimeout(later, wait);
             if (callNow) func.apply(context, args);
         };
	},
	browserMsgTip: 0,//缺省为0次
	browserMsgTimer: null,
	setBrowserMsgTip: function(num){
		if(!num) num = 20;
		this.browserMsgTip = num;
	},
	playAudio:function(){
		var chatAudio=$('#chatAudio');
        if(chatAudio.size()<1){
        	chatAudio=$('<audio id="chatAudio">'+
        			'<source src="'+$webroot+'/js/audio/msgWarn.ogg" type="audio/ogg">'+
        			'<source src="'+$webroot+'/js/audio/msgWarn.mp3" type="audio/mpeg">');
        	chatAudio.appendTo('body');
        }
        chatAudio[0].play();
	},
	popNotice:function(custName,custIcon,msg){
		ChatBoxUtil.playAudio();	 
		if (window.Notification) {
			 var noticeObj;
			 if (Notification.permission == "granted") {
				 noticeObj=notification();
		        } else if (Notification.permission != "denied") {
		            Notification.requestPermission(function (permission) {
		            	noticeObj=notification();
		            });
		        }
			   function notification(){
					return new Notification(custName, {
			                body: msg,
			                icon: custIcon,
			                tag: "." ,
		                    renotify:true
			            });
				  };
				 /* noticeObj.ondisplay = function() { setTimeout(function(){
					  noticeObj.cancel();
				  }, 1000); }*/
			 return noticeObj;
		}else{
			console.error("浏览器不支持Notification");
		}
		 
	},
	/**
	 * 浏览器标签页消息提示
	 */
	enableBrowserMsgTip: function(){
		// 各种浏览器兼容
	    var hidden, state, visibilityChange;
	    if (typeof document.hidden !== "undefined") {
	        hidden = "hidden";
	        visibilityChange = "visibilitychange";
	        state = "visibilityState";
	    } else if (typeof document.mozHidden !== "undefined") {
	        hidden = "mozHidden";
	        visibilityChange = "mozvisibilitychange";
	        state = "mozVisibilityState";
	    } else if (typeof document.msHidden !== "undefined") {
	        hidden = "msHidden";
	        visibilityChange = "msvisibilitychange";
	        state = "msVisibilityState";
	    } else if (typeof document.webkitHidden !== "undefined") {
	        hidden = "webkitHidden";
	        visibilityChange = "webkitvisibilitychange";
	        state = "webkitVisibilityState";
	    } 
	    if(typeof hidden === "undefined") return;//不支持

	    var title = document.title;
	    var self = this;
	    var onVisibilityChange = function(){
	    	if(document[state] == 'hidden'){
	    		self.browserMsgTimer = setInterval(function() {  
		    		if(self.browserMsgTip!=0){
		    			if (self.browserMsgTip % 2 == 0) {  
			                document.title = "【新消息】" + title;
			            } else {  
			                document.title = "【　　　】" + title;
			            };
			            self.browserMsgTip--;
		            }else{
		            	if(document.title != title){
		            		document.title = title;
		            	}
		            }
		        }, 500);
	        }else{
	        	clearInterval(self.browserMsgTimer);
	        	document.title = title;
	        	self.browserMsgTip = 0;
	        }
	    }
	    //监听
	    document.addEventListener(visibilityChange, onVisibilityChange, false); 
	},
	getStaffCodeByUserCode :function(chatId){
		var user = chatId.substring(0,5);
		var csa = chatId.substring(0,4);
		if(user=='user_'){
			var chatBox = this.getChatBoxByChatId(chatId);
			if(chatBox){
				if(!chatBox.custStaffCode){
					var staff = this.getCustInfoByOFUser(chatId);
					if(staff) chatBox.custStaffCode = staff.staffCode; //set custStaffCode
				}
			}else{
				var staff = this.getCustInfoByOFUser(chatId);
				return staff.staffCode;//immediatly
			}
			var staffCode = chatBox!=null?chatBox.custStaffCode:"";
			return staffCode || chatId.substring(5,chatId.length);
		}else if(csa == 'csa_'){
			return chatId.substring(4,chatId.length);
		}
		return chatId
	},
	getStaffCodeByCsaCode: function(chatId){
		chatId = chatId.substring(3,chatId.length);
		return chatId
	},
	getStaffInfo :function(staffCode){
		var staffInfo;
	    $.eAjax({
	        url: GLOBAL.WEBROOT + '/chat/getcustinfo',
	        data: {staffCode:staffCode},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	staffInfo = returnInfo;
	        }
	    });
	    
	    return staffInfo;
	},
	/**
	 * 通过xmpp账号查询用户信息
	 * @param ofAcount
	 * @returns
	 */
	getCustInfoByOFUser: function(ofStaffCode){
		var staffInfo;
		$.eAjax({
	        url: GLOBAL.WEBROOT + '/chat/getcustinfo/of',
	        data: {ofStaffCode:ofStaffCode},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	staffInfo = returnInfo;
	        }
	    });
		
		return staffInfo;
	},
	/**
	 * 轮询判断用户是否下线
	 */
	setInterval : function(){
		//offOnline();
		setInterval("offOnline();", 10000);  
	},
	/**
	 * 当前用户退出
	 */
	signout : function(){
    	ChatBoxUtil.connection.disconnect(); 
	},
	wndUnload : function(){
		var self = this;
		window.onbeforeunload = function(){
			self.signout();
		}
	},
	showNotice :function(e,f,o){
		var notice = o.find('.notice');
		$(notice).find('p').html(e);
		if(f){
			$(notice).show(300).delay(5000).hide(300); 
		}else{
			$(notice).show();
		}
		$(notice).find('#noticeClose').click(function(){
			$(notice).hide();
		});
		$(notice).find('#resrt').click(function(){
			ChatBoxUtil.queueUp();
		});
	}
	
}
/* ========= ChatBoxUtil end ========== */

/* TODO ========= strophe start ========== */

/**
 * 开始连接
 */
var initConnection = function(userInfo){
	ChatBoxUtil.connection = new Strophe.Connection(ChatBoxUtil.BOSH_SERVICE);
		//用户登录
    ChatBoxUtil.connection.connect(userInfo.uName, userInfo.uPass, onConnect);//登录
    ChatBoxUtil.connection.rawInput = rawInput;
    ChatBoxUtil.connection.rawOutput = rawOutput;
}

/**
 * 连接
 */
var onConnect = function(status) {
	switch (status) {
	case Strophe.Status.CONNECTING:
		break;
	case Strophe.Status.CONNFAIL:
	case Strophe.Status.AUTHFAIL:
	case Strophe.Status.ERROR:
		console.log(status+" ERROR");
		break;
	case Strophe.Status.CONNECTED:
		connectSuccess();
		console.log(status+" CONNECTED");
		ChatBoxUtil.connected = true;
		break;
	case Strophe.Status.DISCONNECTED:
		//TODO
		console.log(status+" DISCONNECTED");
		//eDialog.alert(ChatBoxUtil.connection.jid + " 断开连接");
	     /* $.eAjax({
  	        url:GLOBAL.WEBROOT + '/online/getOnlineInfo',
  	        data: {JID:ChatBoxUtil.connection.jid},
  	        datatype: 'json',
  	        async: true,
  	        success: function(returnInfo) {
  	        	for(var key in returnInfo){
  	        		if(returnInfo[key]=='Unavailable'){
  	        			 var userName = $('#ofStaffCode').val();
  	        			 var PASS = ChatBoxUtil.defPass;
  	        			 initConnection({uName:userName, uPass:PASS});
  	        			
  	        		}else{
  	        			eDialog.alert('您已经在其他页面登录， 请点击确定重新登录客服系统!',function(){
  	        				location.reload();
  	        			});
  	        		}
  	        	  }
  	           }
               });*/
		var userName = $('#ofStaffCode').val();
	    var PASS = ChatBoxUtil.defPass;
		connectFail({uName:userName, uPass:PASS});
		break;
	case Strophe.Status.DISCONNECTING:
		break;
	}
}

/**
 * 连接成功
 */
var connectSuccess = function(){
	ChatBoxUtil.connection.addHandler(onMessage, null, 'message', null, null, null); // 消息
	ChatBoxUtil.connection.addHandler(onPresenceOff,null,"presence","unavailable", null,null);
	
	if(online.onlienStatus != 1){//恢复当前状态
		online.updateOnline(true);
	}else{
		var presence = $pres().c("show").t("chat").up().c("status").t("I am online!");
		ChatBoxUtil.connection.send(presence.tree()); // 发送在线请求
	}
	
	//createFriend();//获取好友列表
}

/**
 * 连接失败,客服尝试重新连接
 */
var connectFail = function(userInfo){
	//TODO do something
	ChatBoxUtil.connection = new Strophe.Connection(ChatBoxUtil.BOSH_SERVICE);
	//用户登录
    ChatBoxUtil.connection.connect(userInfo.uName, userInfo.uPass, onReconnect);//登录
    ChatBoxUtil.connection.rawInput = rawInput;
    ChatBoxUtil.connection.rawOutput = rawOutput;
}

var onReconnect = function(status) {
	switch (status) {
	case Strophe.Status.CONNECTING:
		break;
	case Strophe.Status.CONNFAIL:
	case Strophe.Status.AUTHFAIL:
	case Strophe.Status.ERROR:
		console.log(status+" ERROR");
		break;
	case Strophe.Status.CONNECTED:
		reconnectSuccess();
		console.log(status+" CONNECTED");
		ChatBoxUtil.connected = true;
		break;
	case Strophe.Status.DISCONNECTED:
		console.log(status+" DISCONNECTED");
		var userName = $('#ofStaffCode').val();
	    var PASS = ChatBoxUtil.defPass;
		connectFail({uName:userName, uPass:PASS});
		break;
	case Strophe.Status.DISCONNECTING:
		break;
	}
}

/**
 * 重新连接成功
 */
var reconnectSuccess = function(){
	ChatBoxUtil.connection.addHandler(onMessage, null, 'message', null, null, null); // 消息
	ChatBoxUtil.connection.addHandler(onPresenceOff,null,"presence","unavailable", null,null);
	
	if(online.onlienStatus != 1){//恢复当前状态
		online.updateOnline(true);
	}else{
		var sessionids="";
		$('li','.chat-uList').each(function(obj,event){
			var sessionid = $(this).attr('sessionid');
			sessionids = sessionid+","+sessionids;
		});
		var presence = $pres().c("show").t("chat").up().c("status").t("I am online!").up().c("sessionId").t(sessionids);
		ChatBoxUtil.connection.send(presence.tree()); // 发送在线请求
	}
	
	//createFriend();//获取好友列表
}

//最近联系人列表
var createFriend = function(){
    $.eAjax({
        url: GLOBAL.WEBROOT + '/history/getSessionList',
        data: {csaCode:Strophe.getNodeFromJid(ChatBoxUtil.connection.jid)},
        datatype: 'json',
        async: false,
        success: function(returnInfo) {
        	$('.chat-uList').empty();
        	$.each(returnInfo, function(p1, p2) {
        			var chatBean = {
        				chatId: p2.userCode,
        				model: ChatBoxUtil.constants.SERV,
                        sessionId: p2.id,
                        from : p2.userServer
        			}
        			try {
        				chatBox = new ChatBox(chatBean);
        			} catch(e) {
        				if(window.console && window.console.log){
        					console.error(e);
        				}
        			}
        		
        	});
        }
    });
}

/**
 * 消息
 */
var onMessage = function(msg){
	// 解析出<message>的from、type属性，以及body子元素  
    var from = msg.getAttribute('from');  
    var to = msg.getAttribute('to')
    var type = msg.getAttribute('type');  
  //  var messageType = msg.getAttribute('messagetype');
    var id = msg.getAttribute('id');  
    var elems = msg.getElementsByTagName('body'); 
   // var session = msg.getElementsByTagName('session');  
   // session = session[0];
   // var sessionId = Strophe.getText(session);	
    var body = "";
    if (type == "chat" && elems.length > 0) {
    	body = elems[0];
    	body = Strophe.getText(body);
    	body = HtmlUtil.htmlDecode(body);
    	var json = $.parseJSON(body);
    	var msgInfo = {
    		from: from,
    		to: to,
    		id: id,
    		type: type,
    		msg: json.body,
    		sessionId: json.sessionId,
    		messageType:json.messagetype
    	};
    	handleMessage(msgInfo);
    } else if(type="headline" && elems.length > 0){
    	body = elems[0];
    	body = Strophe.getText(body);
    	body = HtmlUtil.htmlDecode(body);
    	var json = $.parseJSON(body);
    	var msgInfo = {
    		from: from,
    		to: to,
    		id: id,
    		type: type,
    		bizcode: json.bizcode,
    		body:json
    	};
    	handleHeadline(msgInfo);
    }
    
    return true;
}

/**
 * 离线
 */
var onPresenceOff = function(msg){
	var message = "用户已离开";
	var from = msg.getAttribute('from'); 
	var chatBox = ChatBoxUtil.getChatBoxFromJID(from);
	var chatMain = $('#chatMain'+chatBox.chatId,chatBox.box);
	var notice = chatMain.find('.notice');
	$(notice).find('p').html(message);
	//$('.notice').html(msg);
	$(notice).show();
	$(notice).find('#noticeClose').click(function(){
		$(notice).find('p').html();
		$(notice).hide();
	});
	
    var tabItem = chatBox.tab;
    if(tabItem){
        tabItem.find(".tip").html(message.substring(0,30)+"...");
    }
}

/**
 * 用户离线
 */
var offOnline = function(){
	var list = ChatBoxUtil.list;
	var JID = "";
	for(var chat in list){
		var chatBox = list[chat];
		JID = chatBox.chatId+","+JID;
	}
	 $.eAjax({
	        url: GLOBAL.WEBROOT + '/online/getOnlineInfo',
	        data: {JID:JID},
	        datatype: 'json',
	        async: true,
	        success: function(returnInfo) {
	        	var userOff = "用户已离开";
	        	for(var key in returnInfo){
	        		for(var chat in list){
	        			var chatBox = list[chat];
	        			if(chatBox.chatId == key){
	        				if(returnInfo[key]=='Unavailable'){
	        				    var tabItem = chatBox.tab;
		        		        if(tabItem){
		        			        tabItem.find(".tip").html(userOff);
		        			        //tabItem.find('.head').style.filter = "alpha(opacity=50)";
		        		        }
	        				}else{
	        					var tabItem = chatBox.tab;
	        			        if(tabItem && tabItem.find(".tip").html() == userOff){
	        			        	tabItem.find(".tip").html(" ");
	        			        }
	        			        //tabItem.find('.head').style.filter = "alpha(opacity=50)";
	        				}
	        		
	        			}
	        		}
	        	};
	        }
	 });
}

function rawInput(data)
{
	//console.log('RECV: ' + data);
}

function rawOutput(data)
{
	//console.log('SENT: ' + data);
}

/**
 * handleHeadline 处理业务通知 
 * body中的对象包含 "bizcode"
 */
function handleHeadline(msgInfo){
	switch(msgInfo.bizcode){
		case "transfercsa"://用户待接收
			var userName = msgInfo.body.userCode.substring(msgInfo.body.userCode.indexOf("_")+1);
			var data = {
				csaName: msgInfo.body.csaName,
				userName: userName,
				sessionId: msgInfo.body.sessionId,
				srcCsaName: msgInfo.body.srcCsaName,
				userCode: msgInfo.body.userCode
			};
			var tpl = template('transTpl1', data);
	        bDialog.open({
	            'width': 550,
	            'height': 150,
	            'customClass': 'lanDialog imDialog2'
	        }, tpl);
	        $('.trans-item-cancel.in').click(function () {//取消
	        	var userCode = msgInfo.body.userCode;
	        	var iq = $iq({  
	                type: 'get'
	            }).c("transfer", {"xmlns":Strophe.NS.TRANSFER_IQ,
	            	"bizcode":"refuseTransfer",
	            	"srcCsaCode":ChatBoxUtil.getUserFromJid(msgInfo.from),
	            	"userCode":msgInfo.body.userCode
	            	}, "");
				ChatBoxUtil.connection.sendIQ(iq.tree(),
						function(iq){//成功
							//正常
							var transfer = $(iq).find("transfer");
							var iqmessage = transfer.text();
							//关闭窗口
							bDialog.closeCurrent();
			            },
			            function(iq){//失败
			            	console.log(iq);
			            }    
	            ); 
            });
	        $('.trans-item-save.in').click(function () {//接受
	        	var userCode = msgInfo.body.userCode;
	        	var iq = $iq({  
	                type: 'get'
	            }).c("transfer", {"xmlns":Strophe.NS.TRANSFER_IQ,
	            	"bizcode":"acceptTransfer",
	            	"sessionId":msgInfo.body.sessionId,
	            	"srcCsaCode":ChatBoxUtil.getUserFromJid(msgInfo.from),
	            	"userCode":msgInfo.body.userCode
	            	}, "");
				ChatBoxUtil.connection.sendIQ(iq.tree(),
						function(iq){//成功
							//正常
							var transfer = $(iq).find("transfer");
							var sessionId = transfer.attr("sessionId");//新的会话id
							console.log("新会话的Id:"+sessionId);
							var userJID = transfer.attr("userJID");//对方用户的jid
							var iqmessage = transfer.text();
							//创建与已接收会员的会话
							var chat = ChatBoxUtil.getChatBoxByChatId(userCode);
							if(!chat){
								var chatBean = {
									chatId: userCode,
									model: ChatBoxUtil.constants.SERV,
					                sessionId: sessionId,
					                from: userJID
								}
								try {
									chat = new ChatBox(chatBean);
								} catch(e) {
									if(window.console && window.console.log){
										console.error(e);
									}
								}
							}else{
								chat.setSessionId(sessionId);
							}
							console.log("chat.sessionId:"+chat.sessionId);
							ChatBoxUtil.showNotice(iqmessage,true,chat.box);
							//错误
							//关闭窗口
							bDialog.closeCurrent();
			            },
			            function(iq){//失败
			            	console.log(iq);
			            }    
	            ); 
            });
			break;
		case "transfercsaResult"://用户已接收
			var success = msgInfo.body.success;
			//用户成功转移，移除本地
			if(success=="true"){
				var chat = ChatBoxUtil.getChatBoxByChatId(msgInfo.body.userCode);
				chat.close(true);
				eDialog.alert(msgInfo.body.reason);
			}else{
				eDialog.alert("对方"+msgInfo.body.reason);
			}
			break;
		default:
			//do nothing
			break;
	}
	
}


/**
 * handleMessage 处理消息 
 * 可根据消息来源对应样式
 */
var handleMessage = function(msgInfo){
	//消息同步
	//客服侧不处理
	if(msgInfo.from == ChatBoxUtil.connection.jid){
		return;
	}
	
	var chatBox = null;
	if(ChatBoxUtil.model == ChatBoxUtil.constants.SERV){
		chatBox = ChatBoxUtil.getChatBoxFromJID(msgInfo.from);
		if(!chatBox){
			var chatBean = {
				chatId: ChatBoxUtil.getUserFromJid(msgInfo.from),
				model: ChatBoxUtil.constants.SERV,
                sessionId: msgInfo.sessionId,
                msgId : msgInfo.id
			}
			try {
				chatBox = new ChatBox(chatBean);
			} catch(e) {
				if(window.console && window.console.log){
					console.error(e);
				}
			}
		}
	}else if(ChatBoxUtil.model == ChatBoxUtil.constants.CUST){
		chatBox = ChatBoxUtil.getChatBoxFromJID(ChatBoxUtil.connection.jid);
	}
	if(chatBox && !chatBox.from){
		chatBox.setFrom(msgInfo.from);
	}
	if(chatBox){ 
		//刷新from jid
		chatBox.setFrom(msgInfo.from);
		//展示消息
		chatBox.showReceived(msgInfo);
		ChatBoxUtil.setBrowserMsgTip();//收到消息  展示有效消息时提示
		//判断sessionId是否一致，不一致就重新展示右侧信息
		if(chatBox.sessionId!=msgInfo.sessionId){
			chatBox.setSessionId(msgInfo.sessionId);
		    var issueType, gdsId,ordId,userCode,place='00';
			 $.eAjax({
			 	        url: GLOBAL.WEBROOT + '/history/getSessionByone',
			 	        data: {id:msgInfo.sessionId},
			 	        dataType: 'json',
			 	        async: false,
			 	        success: function(returnInfo) {
			 	        	issueType=returnInfo.issueType;
			 	        	gdsId=returnInfo.gdsId;
			 	        	ordId=returnInfo.ordId;
			 	        	userCode=returnInfo.userCode;
			 	        },
			 	        exception: function() {
			 	            eDialog.alert("网络异常");
			 	            return;
			 	        }
			});
	        if(gdsId){
	        	place='02';//商品详情
	        	$("a[href='#proPanel"+chatBox.chatId+"']",chatBox.box).text("商品详情");
	        	var allordPanelLi = $("a[href='#allordPanel"+chatBox.chatId+"']",chatBox.box).closest("li");
	        	if(!allordPanelLi[0]){//会话刷新时，如果没有“历史订单”
	        		var tabLi = $("<li>");
	        		var tabLiA = $("<a>");
	        		tabLiA.attr("href","#allordPanel"+chatBox.chatId);
	        		tabLiA.text("历史订单");
	        		tabLiA.click(function (e) {
	        			e.preventDefault();
	        			$(this).tab('show');
        			});
	        		tabLi.append(tabLiA);
	        		$("a[href='#proPanel"+chatBox.chatId+"']",chatBox.box).closest("li").after(tabLi);
	        		var contDiv = $('<div class="tab-pane allord-panel" id="allordPanel'+chatBox.chatId+'"> </div>');
	        		$("#proPanel"+chatBox.chatId,chatBox.box).after(contDiv);
	        		chatBox._loadAllord(userCode,place,issueType, gdsId,ordId);//所有订单
	        		$('.nav-wrap',chatBox.box).navPageScroll();//初始化 navPage
	        	}
	        }else if(ordId){
	        	place='01';//订单
	        	$("a[href='#proPanel"+chatBox.chatId+"']",chatBox.box).text("订单详情");
	        	var allordPanelLi = $("a[href='#allordPanel"+chatBox.chatId+"']",chatBox.box).closest("li");
	        	if(!allordPanelLi[0]){//会话刷新时，如果没有“历史订单”
	        		var tabLi = $("<li>");
	        		var tabLiA = $("<a>");
	        		tabLiA.attr("href","#allordPanel"+chatBox.chatId);
	        		tabLiA.text("历史订单");
	        		tabLiA.click(function (e) {
	        			e.preventDefault();
	        			$(this).tab('show');
        			});
	        		tabLi.append(tabLiA);
	        		$("a[href='#proPanel"+chatBox.chatId+"']",chatBox.box).closest("li").after(tabLi);
	        		var contDiv = $('<div class="tab-pane allord-panel" id="allordPanel'+chatBox.chatId+'"> </div>');
	        		$("#proPanel"+chatBox.chatId,chatBox.box).after(contDiv);
	        		chatBox._loadAllord(userCode,place,issueType, gdsId,ordId);//所有订单
	        		$('.nav-wrap',chatBox.box).navPageScroll();//初始化 navPage
	        	}
	        }else{
	        	$("a[href='#proPanel"+chatBox.chatId+"']",chatBox.box).text("历史订单");
	        	$("a[href='#allordPanel"+chatBox.chatId+"']",chatBox.box).closest("li").remove();
	        	$(".allord-panel",chatBox.box).remove();
	        }
			chatBox._loadPro(userCode, place, issueType, gdsId, ordId);
		}
	}
	
}

$(function () {

	//订单绑定打开新窗口
	$(".chat-win").delegate(".jsOrderOpen", "click", function(e){
		var url = $(this).attr('paraval');
		orderDetail(url);
	});
});
/**
 * 订单详情页点击
 */
var orderDetail = function(orderId){
	var json = "{flag:'"+$('#orderEdit').val()+"',orderId:'"+orderId+"',usercode:'"+$('#staffCode').val()+"',time:"+new Date().getTime()+"}";
	$.eAjax({
        url:GLOBAL.WEBROOT + '/mallInfo/encryptCode',
        data: {jsonStr:json},
        datatype: 'json',
        async: false,
        success: function(data) {
		    var href = $("#ordDetailUrl").val() + data.jsonStr;
		    window.open(href); 
        },
		exception: function() {
            eDialog.alert("网络异常");
            return;
        }
    });
}

/* ========= strophe end ========== */
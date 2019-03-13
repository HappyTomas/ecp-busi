

Strophe.addNamespace('OFFLINEMSG_IQ', "com:ai:of:offlinemsg");

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
	this.sessionId = chatBean.sessionId ||"";//当前的会员Id
	this.msgId = chatBean.msgId ||"";  //消息ID
	this.shopId = chatBean.shopId || "";
	this.current = chatBean.current || false; //是否当前窗口
	this.unread = chatBean.unread || 0; //未读消息
    this.isinit = chatBean.isinit || false;
	this.model = chatBean.model || ChatBoxUtil.constants.SERV;  // "serv" "cust" 
	this.reciveTmplId = chatBean.reciveTmplId || "record"; //接收消息展示模板
	this.sendTmplId = chatBean.sendTmplId || "selfRecord"; //发送消息展示模板
	this.msgInput = null; //消息输入区
	
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
	close: function(){
		//1.窗口移除
		this.box.remove();
		if(this.tab) this.tab.remove();
		//2.ChatBoxUtil.list 移除
		var index = $.inArray(this, ChatBoxUtil.list);
		if(index>=0){
			ChatBoxUtil.list.splice(index,1);
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
			var quePanel=$('.que-panel',this.box);
			var allordPanel=$('.allord-panel',this.box);
			var msgPanelCont=$('.msgPanelCont',this.box);
			var ordPanel=$('.ord-detail-panel',this.box);
			   var scrollClz='mCustomScrollbar';
			if(chatRecord.hasClass(scrollClz)){
				chatRecord.mCustomScrollbar('update');
				chatRecord.mCustomScrollbar('scrollTo', 'bottom');
			}
			if(proPanel.hasClass(scrollClz)&&proPanel.css('display')=='block'){
				proPanel.mCustomScrollbar('update');
			}
			if(allordPanel.hasClass(scrollClz)&&allordPanel.css('display')=='block'){
				allordPanel.mCustomScrollbar('update');
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
	 * 展示消息
	 */
	showReceived: function(msgInfo){
		var msg = msgInfo.msg;
		//winItem
		//var winItem = this.box;
		var chatMain = $('#chatMain'+this.chatId);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
        var tplData = {
    		msg: msg,
    		uName: ChatBoxUtil.ServName,
    		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
    		custPic: ChatBoxUtil.servPic
        }
        var html = template('record', tplData);
        if(msgInfo.messageType=='welcome'){
        	 chartList.append(html);
        	 chartList.imagesLoaded(function () {
       		  chatRecord.mCustomScrollbar('update');
	   	          chatRecord.mCustomScrollbar('scrollTo', 'bottom');
	             });
        }else{
            $.eAjax({
    	        url: GLOBAL.WEBROOT + '/history/updateMsgStatus',
    	        data: {id:this.msgId},
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    	        	  chartList.append(html);
    	        	  chartList.imagesLoaded(function () {
		        		  chatRecord.mCustomScrollbar('update');
			   	          chatRecord.mCustomScrollbar('scrollTo', 'bottom');
		 	             });
    	        },
    	        exception: function() {
    	            eDialog.alert("消息展示异常，请检查您的网络");
    	            return;
    	        }
            });
        }

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
	 * 发送消息
	 */
	sendMessage: function(){
		if(ChatBoxUtil.connected) {
			
			//控制发送频率
            var smDiff = new Date().getTime() - this.lastSendMoment.getTime();
            if(smDiff < 500){
            	ChatBoxUtil.showNotice('消息发送过于频繁，请稍事休息！',true);
            	return;
            }
            
            //前序消息发送中
            if(!this.sendable){
            	ChatBoxUtil.showNotice('消息发送过于频繁，请稍事休息！',true);
            }
			
			var waitCount = $('#waitCount').val();
			if(waitCount>0){
				ChatBoxUtil.showNotice("您的前面还有"+waitCount+"位正在等待，请稍等",false);
				return false;
			}
            if(!this.to && !this.from) {  
            	//eDialog.alert("没有联系人！");
            	ChatBoxUtil.showNotice('您好，客服还没有上线，请在正常工作时间内联系客服人员.',true);
    			return false;
            }else{
            	var sessionid = $('#sessionId').val();
            	this.setSessionId(sessionid);
            	var flag = true;
                $.eAjax({
        	        url:GLOBAL.WEBROOT + '/history/getSessionByone',
        	        data: {id:sessionid},
        	        datatype: 'json',
        	        async: false,
        	        success: function(returnInfo) {
        	        
                           if(returnInfo.status!='1'){
                        	   ChatBoxUtil.showNotice('与当前客服的会话已经结束，重新发起咨询请点击'+'<span style="color:blue" id="resrt">确认</span>'+'按钮',false);
                        	   flag =  false;
                           }        	        	
        	        
        	            }
                     });
                if(!flag){
                	return false;
                }
            }
            
            // 发送消息 
            // 创建一个<message>元素并发送  
            var ue = ChatBoxUtil.ue;
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
            var csa = this.to.split('/');
            var issueType = $('#issueType').val();
            var data = {to:this.to||this.from,
        		    toResource:csa[1],
        		    from:ChatBoxUtil.connection.jid,
        		    body:messageValue,
        		    sessionId:this.sessionId,
        		    messageType:'msg',
        		    contentType:'0',
        		    shopId : this.shopId,
        		    csaCode:ChatBoxUtil.getUserFromJid(this.to||this.from),
        		    userCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),
        		    issueType:$('#issueType').val(),
        		    gdsId : $('#gdsId').val(),
        		    ordId : $('#ordId').val()}
            $.eAjax({
    	        url: GLOBAL.WEBROOT + '/history/saveMsgHistory',
    	        data: data,
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    	        	ChatBoxUtil.message.messagetype = returnInfo.messageType;
    	        	ChatBoxUtil.message.body = returnInfo.body;
    	        	messageValue = returnInfo.body;
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
    	            //用户发消息后，先清除原来的定时任务 
    	            window.clearTimeout(ChatBoxUtil.timeOutId);
    	            window.clearTimeout(ChatBoxUtil.timeOutRId);
    	            
    	            //重新开启定时任务：5分钟后，如果用户没有主动发消息，则调用服务，请求断开链接
    	            ChatBoxUtil.timeOutId = window.setTimeout(ChatBoxUtil.disconnFromServ, ChatBoxUtil.outTime);
    	            ChatBoxUtil.timeOutRId = window.setTimeout(ChatBoxUtil.timeOutRemind, ChatBoxUtil.outTime-120000);
    	        },
    	        exception: function() {
    	            eDialog.alert("消息发送请求异常，请检查网络");
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
        		msg: messageValue,
        		uName: ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid)),
        		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
        		custPic: ChatBoxUtil.custPic
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
	
	/**
	 * 同步其他平台的消息
	 * @param msg
	 */
	syncSendMessage: function(msgInfo){
		// 展示消息
        var winItem = this.box;
		var chatMain = $('#chatMain'+this.chatId,winItem);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
        var staffCode = ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid));
        var tplData,html,msg;
        // 消息类型处理
        if(msgInfo.messageType == "msg"){
	        tplData = {
	    		msg: msgInfo.msg,
	    		uName: staffCode,
	    		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
	    		custPic: ChatBoxUtil.custPic
	        }
	        html = template('selfRecord', tplData);
        }else if(msgInfo.messageType == "gds"){
        	msg = JSON.parse(msgInfo.msg);
        	tplData = {
        		uName: staffCode,
        		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
        		custPic : this.custPic,
        		// TODO 补充返回的参数skuId
        		url : $("#gdsDetailUrl").val() + msg.gdsId + "-",
        		gdsId : msg.gdsId,
        		gdsImage : msg.gdsImage,
        		gdsName : msg.gdsName,
        		gdsPrice : msg.price,
        		chatSide : "chat-right"
            }
            html = template('recordGds', tplData);
        }else if(msgInfo.messageType == "order"){
        	msg = JSON.parse(msgInfo.msg);
        	tplData = {
        		uName: staffCode,
        		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
        		custPic : this.custPic,
        		ordId : msg.ordId,
        		price : msg.price,
        		ordImage : msg.ordImage,
        		createTime : ebcDate.dateFormat(new Date(Number(msg.createTime)), "yyyy-MM-dd hh:mm:ss"),
        		url : $("#ordDetailUrl").val() + msg.ordId,
        		chatSide : "chat-right"
            }
            html = template('recordOrder', tplData);
        }
        
        chartList.append(html);
        
        chatRecord.mCustomScrollbar('update');
        chatRecord.mCustomScrollbar('scrollTo', 'bottom');
	},
	
	sendEvaluate: function(){
		if(ChatBoxUtil.connected) {  
			var notSatisfyType = $('#notSatisfyType').val();
			var notSatisfyReason = $.trim($('#notSatisfyReason').val());
			if(notSatisfyType == "1" && notSatisfyReason == ""){
				$("#notSatisfyReason").attr("placeholder","请填写业务流程不满意原因！");
				$("#notSatisfyReason").css({"border":"1px solid #FC5D60"});
				return false;
			}
			var winItem = this.box;
            var satisfyType = $('#satisfyType').val();
			if(satisfyType == ""){
				satisfyType="4";
			}
			var data = {csaCode:ChatBoxUtil.csaCode,
					shopId:this.shopId,
					ofStaffCode:$("#uNameForLogout").val(),
					sessionId:this.sessionId,
					satisfyType:satisfyType,
					notSatisfyType:notSatisfyType,
					notSatisfyReason:notSatisfyReason}
			$.eAjax({
				url:GLOBAL.WEBROOT + '/cust/saveEvaluate',
				data: data,
				datatype: 'json',
		        async: false,
				success:function(returnInfo){
					if(returnInfo&&returnInfo.resultFlag == 'ok'){
						ChatBoxUtil.showNotice('您对'+ChatBoxUtil.ServName+'评价成功！',false);
						$(".eval-box").hide();
						$('.clkEval', winItem).qtip('hide');
					}else{  
						ChatBoxUtil.showNotice(returnInfo.resultMsg,false);
						$('.clkEval', winItem).qtip('hide');
					}
				}
			});   
            
        } else {  
        	eDialog.alert("请先登录！");  
        }  
	}, 
	evaluateCheck: function(){
		if(ChatBoxUtil.connected) {   
			if(this.isinit){
				var isStatus = false;
				var evalHtml = "";
				var data = {csaCode:ChatBoxUtil.csaCode,
						shopId:this.shopId,
						ofStaffCode:$("#uNameForLogout").val(),
						sessionId:this.sessionId,
						satisfyType:$('#satisfyType').val(),
						notSatisfyType:$('#notSatisfyType').val(),
						notSatisfyReason:$('#notSatisfyReason').val()}
				$.eAjax({
					url:GLOBAL.WEBROOT + '/cust/evaluateCheck',
					data: data,
					datatype: 'json',
			        async: false,
					success:function(returnInfo){
						if(returnInfo&&returnInfo.resultFlag == 'ok'){
							isStatus = true;
							
						}else{  
							ChatBoxUtil.showNotice(returnInfo.resultMsg,false);
						}
					}
				});  
				if(isStatus == true){
					$(window).trigger('resize');
					evalHtml=template('evalTpl',{});
		            return evalHtml;
				}else{
					$('.clkEval', winItem).qtip('hide');
				}
			} 
        } else {  
        	eDialog.alert("请先登录！");  
        } 
	},
	_loadPro: function(place){
		var winItem = this.box;
		var proPanel=$('.pro-panel',winItem);
		var issueType=$('#issueType').val();
		var data={},dataUrl='';
		if("00"==place){//加载历史订单
		     data = {staffCode:$('#staffCode').val()};
		     dataUrl= GLOBAL.WEBROOT + '/mallInfo/ordAll';
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
			data = {ordId:$('#ordId').val()};
			dataUrl= GLOBAL.WEBROOT + '/mallInfo/ordDetail';
			loadAjax(dataUrl,data);
		}else if("02"==place){ //加载商品信息
			data = {gdsId:$('#gdsId').val(),custStaffCode:$('#staffCode').val()};
		    dataUrl= GLOBAL.WEBROOT + '/mallInfo/gdsDetail';
		    loadAjax(dataUrl,data);
		}
		
		function loadAjax(url,param,callback){
       		proPanel.empty();
       		proPanel.append("<div class='block-empty'>加载中..</div>");
			$.eAjax({
	 	        url:url,
	 	        data: param,
	 	        dataType: 'html',
	 	        async: true,
	 	        success: function(returnInfo) {
	 	         	 proPanel.show().empty().append(returnInfo);
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
		//var chatMain = $('#chatMain'+this.chatId,this.box);
		var quePanel=$('.que-panel',winItem).append(html);
		var proPanel=$('.pro-panel',winItem);
		var allordPanel=$('.ord-detail-panel',winItem);
		ChatBoxUtil.setBoxHeight(quePanel,winItem);
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
	_loadAllord: function(place){
		var winItem = this.box;
		var allordPanel=$('.allord-panel',winItem);
		var issueType=$('#issueType').val();
		var data={},dataUrl='';
		if("01"==place || "02"==place){//加载历史订单
		     data = {staffCode:$('#staffCode').val()};
		     dataUrl= GLOBAL.WEBROOT + '/mallInfo/ordAll';
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
		}
		
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
		var staffCode = $('#staffCode').val();
		var chatData = {
			uName: '人卫商城在线客服',
			serName:'-'+ChatBoxUtil.ServName,
			custPic:ChatBoxUtil.servPic
		}
		var html = template('chatPanelTpl', chatData);
		var chatMain = $('#chatMain'+this.chatId,this.box);
		chatMain.append(html);
		var chatRecord = $(".chat-record", chatMain);
        var chartList = $('.list', chatRecord);
		
		ChatBoxUtil.setBoxHeight(chatRecord,winItem);
		
		var scrollTop = 0;
		var pageNum = 1;
		var userCode = $("#uNameForLogout").val();
		var csaCode = $("#csaCodeForLogout").val();
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
//                	        	messageType:'msg',
                	        	pageNumber:pageNum,
                	        	pageSize:ChatBoxUtil.pageSize,
                	        	shopId:ChatBoxUtil.shopId,
                	        	status:'20'
                	        },
                	        datatype: 'json',
                	        async: false,
                	        success: function(returnInfo) {
                	        	var winItem = this.box;
                  	    		var chatMain = $('#chatMain'+userCode+'WEB',winItem);
                  	    		var chatRecord = $(".chat-record", chatMain);
                  	            var chartList = $('.list', chatRecord);
                	        	$.each(returnInfo, function(p1, p2) {
                	        	   if(ChatBoxUtil.getUserFromJid(p2.from) ==  ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid)){
                	        	            var tplData = {
                	        	            		msg: p2.body,
                	        	            		uName: staffCode,
                	        	            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
                	        	            		custPic: ChatBoxUtil.custPic
                	        	            }
                	        	            var html = template('selfRecord', tplData);
                	        	            if(p2.messageType != "msg"){
                	        	            	var domInfo = {
                	        	            			messageType: p2.messageType,
                	        	            			uName: staffCode,	
                	        	            			custPic: ChatBoxUtil.custPic
                	        	            	};
                	        	            	html = chatBox._createMessageDom(domInfo, p2, "chat-right");
                	        	            }
                	        	            $('.ser-more',chartList).after(html);
                	        	          //  $(html).prependTo(chartList);
                	        	           
                	        		}else{
                	        			
                		                var tplData = {
                		            		msg: p2.body,
                		            		uName:ChatBoxUtil.ServName,
                		            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
                		            		custPic: ChatBoxUtil.servPic
                		                }
                		                var html = template('record', tplData);
                		                if(p2.messageType != "msg"){
                		                	var domInfo = {
                		                			messageType: p2.messageType,
                		                			uName: ChatBoxUtil.ServName,	
                		                			custPic: ChatBoxUtil.servPic
                		                	};
                		                	html = chatBox._createMessageDom(domInfo, p2, "");
                		                }
                		                $('.ser-more',chartList).after(html);
                		               // $(html).prependTo(chartList);
                	        		}
                	        	   chatRecord.mCustomScrollbar('update');
                	        	});
                	        	$('.ser-more',chartList).hide();
                	        },
                	        exception: function() {
                	            eDialog.alert("滚动加载历史消息异常，请检查网络");
                	            return;
                	        }
                        });
                        },300);
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
	_loadChatHissdf: function(){
		var winItem = this.box;
		var proData = {
				
		}
		var chatSideHis = $(".chat-hisMgs", winItem);
        var html = template('hisMgsTpl', proData);
        chatSideHis.empty().append(html);
        $('.clkRecord', winItem).click(function () {
            winItem.toggleClass('hasMsgPanel');
            var hMsgList = $(".msgPanelCont", winItem);
            if (hMsgList.hasClass('mCustomScrollbar')) {
                hMsgList.mCustomScrollbar('update');
            } else {
            	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
           /*     hMsgList.height(
                        $(window).height()
                        - ChatBoxUtil.constants.baseH
                        - $('.msg-head').outerHeight()
                        - $('#msgTab').outerHeight()
                        - 50
                );*/
                hMsgList.mCustomScrollbar({autoHideScrollbar:true});
            }

        });
		
	},
	_createBox: function(){
		var chatWin=$('.chat-win');
	/*	if(this.model == ChatBoxUtil.constants.CUST){
			chatWin=$('#chatPanel');
		}*/
		chatWin.empty();
		var winItem=$('<div class="winItem"></div>');
		this.box = winItem; //设置box
        chatWin.append(winItem);
        var place='00'; //首页
        if($('#gdsId').val()!=''){
        	place='02';//商品详情
        }else if($('#ordId').val()!=''){
        	place='01';//订单
        }
        var html = template('winItemTpl',{'chatId':this.chatId,'place':place,'model':ChatBoxUtil.constants.CUST}); 
        winItem.append(html);
        winItem.attr('id','winItem'+this.chatId);
        
        this._loadChat();//聊天工作区
        this._loadPro(place);//右侧信息
        this._loadAllord(place);//所有订单
        this._loadProQue();//常见问题
        if(this.isinit){
        	   this._loadChatHis();//聊天记录
               this._createHistoryMsg();//最近会话记录 
        }
     

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
        ChatBoxUtil.ue = ue; //设置
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
        
        $('.parse-help').qtip({
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
		var html = template('chatItemTpl',{'uName':this.chatId, 'chatId':this.chatId});
		this.tab = $(html);
		chatList.append(this.tab);
	},
	_initDom: function(){
		if(ChatBoxUtil.hasChatBox(this.chatId)) return;
		if(this.model == ChatBoxUtil.constants.SERV) this._createTab();
		this._createBox();
	
	    this._display();
		
		ChatBoxUtil.list.push(this);
		this._bindEvent();
	},
	/**
	 *  绑定事件
	 */
	_bindEvent: function(){
	
		//1.消息发送
		var chatBox = this;
		if(this.isinit){
			$("#sendMsg").click(function(e){
				$("#sendMsg").attr("disabled","true");
				if(ChatBoxUtil.connected) chatBox.sendMessage();
				$("#sendMsg").removeAttr("disabled");
				
			}); 
		}

		
		//会话列表tab选中
		if(this.tab){
			this.tab.click(function(e){
				chatBox.show();
			});
		}
		
		//发送商品
		$(".pro-panel", chatBox.box).delegate(".gdssend","click",function(e){
			var staffCode = ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid));
			var me = $(this);
			var gdsId = me.attr("gdsId");
			var gdsName = me.attr("gdsName");
			var realPrice = me.closest("table").find("#realPrice").text();
			var proDetailPic = me.closest("table").siblings(".pic");
			var gdsImage = proDetailPic.find("img").attr("src");
			var url = proDetailPic.find("a").attr("href");
			var data = {
					gdsId:gdsId,
					gdsName:gdsName,
					gdsImage:gdsImage,
					url:url,
					price:realPrice	
			};
			tplData = {
        		uName: staffCode,
        		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
        		custPic : chatBox.custPic,
        		// TODO 补充返回的参数skuId
        		url : $("#gdsDetailUrl").val() + gdsId + "-",
        		gdsId : gdsId,
        		gdsImage : gdsImage,
        		gdsName : gdsName,
        		gdsPrice : realPrice,
        		chatSide : "chat-right"
            }
            var html = template('recordGds', tplData);
			sendmsgOnButton(chatBox,"gds",2,JSON.stringify(data),html);
		});
		
		//发送订单
		$(".pro-panel", chatBox.box).delegate(".ordersend","click",function(e){
			var staffCode = ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid));
			var me = $(this);
			var orderId = me.attr("orderId");
			var orderPrice = me.attr("orderPrice");
			var orderTime = me.attr("orderTime");
			orderTime = new Date(Date.parse(orderTime.replace(/-/g,"/"))).getTime();
			var ordDetailP = me.closest("table").find("tbody tr:first");
			var ordImage = ordDetailP.find("img").attr("src");
			var url = ordDetailP.find("a").attr("href");
			var data = {
					ordId:orderId,
					price:orderPrice,
					ordImage:ordImage,
					url:url,
					createTime:orderTime
			};
			tplData = {
        		uName: staffCode,
        		time: ebcDate.dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss"),
        		custPic : chatBox.custPic,
        		ordId : orderId,
        		price : orderPrice,
        		ordImage : ordImage,
        		createTime : ebcDate.dateFormat(new Date(Number(orderTime)), "yyyy-MM-dd hh:mm:ss"),
        		url : $("#ordDetailUrl").val() + orderId,
        		chatSide : "chat-right"
            }
            var html = template('recordOrder', tplData);
			sendmsgOnButton(chatBox,"order",2,JSON.stringify(data),html);
		});
		
		function sendmsgOnButton(chatBox, messageType, contentType, msgbody, htmlShow){
			if(ChatBoxUtil.connected) {
				
				//控制发送频率
	            var smDiff = new Date().getTime() - chatBox.lastSendMoment.getTime();
	            if(smDiff < 500){
	            	ChatBoxUtil.showNotice('消息发送过于频繁，请稍事休息！',true);
	            	return;
	            }
	            
	            //前序消息发送中
	            if(!chatBox.sendable){
	            	ChatBoxUtil.showNotice('消息发送过于频繁，请稍事休息！',true);
	            }
				
				var waitCount = $('#waitCount').val();
				if(waitCount>0){
					ChatBoxUtil.showNotice("您的前面还有"+waitCount+"位正在等待，请稍等",false);
					return false;
				}
	            if(!chatBox.to && !chatBox.from) {  
	            	//eDialog.alert("没有联系人！");
	            	ChatBoxUtil.showNotice('您好，客服还没有上线，请在正常工作时间内联系客服人员.',true);
	    			return false;
	            }else{
	            	var sessionid = $('#sessionId').val();
	            	chatBox.setSessionId(sessionid);
	            	var flag = true;
	                $.eAjax({
	        	        url:GLOBAL.WEBROOT + '/history/getSessionByone',
	        	        data: {id:sessionid},
	        	        datatype: 'json',
	        	        async: false,
	        	        success: function(returnInfo) {
	        	        
	                           if(returnInfo.status!='1'){
	                        	   ChatBoxUtil.showNotice('与当前客服的会话已经结束，重新发起咨询请点击'+'<span style="color:blue" id="resrt">确认</span>'+'按钮',false);
	                        	   flag =  false;
	                           }        	        	
	        	        
	        	            }
	                     });
	                if(!flag){
	                	return false;
	                }
	            }
	            
	            // 发送消息 
	            // 创建一个<message>元素并发送  
	            
	            chatBox.sendable = false; //消息发送中
	            /**
	             * 消息uuid生成
	             */
	            var csa = chatBox.to.split('/');
	            var issueType = $('#issueType').val();
	            var data = {to:chatBox.to||chatBox.from,
	        		    toResource:csa[1],
	        		    from:ChatBoxUtil.connection.jid,
	        		    body:msgbody,
	        		    sessionId:chatBox.sessionId,
	        		    messageType:messageType,
	        		    contentType:contentType,
	        		    shopId : chatBox.shopId,
	        		    csaCode:ChatBoxUtil.getUserFromJid(chatBox.to||chatBox.from),
	        		    userCode:ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid),
	        		    issueType:$('#issueType').val(),
	        		    gdsId : $('#gdsId').val(),
	        		    ordId : $('#ordId').val()}
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
	    	        	var str = JSON.stringify(ChatBoxUtil.message); 
	    	            var msg = $msg({  
	    	                to: returnInfo.to,//发送或回复消息   
	    	                from: returnInfo.from, //登录者ID
	    	                id : returnInfo.id,
	    	               // messagetype : returnInfo.messageType,
	    	                type: 'chat'  
	    	            }).c("body", null, str)//.c("session", null,returnInfo.sessionId);//带入本次会话的sessionId
	    	            ChatBoxUtil.connection.send(msg.tree());  
	    	            //用户发消息后，先清除原来的定时任务 
	    	            window.clearTimeout(ChatBoxUtil.timeOutId);
	    	            window.clearTimeout(ChatBoxUtil.timeOutRId);
	    	            
	    	            //重新开启定时任务：5分钟后，如果用户没有主动发消息，则调用服务，请求断开链接
	    	            ChatBoxUtil.timeOutId = window.setTimeout(ChatBoxUtil.disconnFromServ, ChatBoxUtil.outTime);
	    	            ChatBoxUtil.timeOutRId = window.setTimeout(ChatBoxUtil.timeOutRemind, ChatBoxUtil.outTime-120000);
	    	        },
	    	        exception: function() {
	    	            eDialog.alert("消息发送请求异常，请检查网络");
	    	            chatBox.sendable = true;
	    	            return;
	    	        }
	            });
	            
	            // 展示消息
	            var winItem = chatBox.box;
	    		var chatMain = $('#chatMain'+chatBox.chatId,winItem);
	    		var chatRecord = $(".chat-record", chatMain);
	            var chartList = $('.list', chatRecord);
	            
	            chartList.append(htmlShow);
	            chatRecord.mCustomScrollbar('update');
	            chatRecord.mCustomScrollbar('scrollTo', 'bottom');
	            
	            chatBox.sendable = true;
	        } else {  
	        	eDialog.alert("请先登录！");  
	        }  
		}
		
	
        var scrollClz='mCustomScrollbar';
		  /* 判断 浏览器resize beign*/
        $(window).on('resize.chatScroll',ChatBoxUtil.debounce(function(){
            var useListScroll=$('.chat-uContainer');
            if(useListScroll.hasClass(scrollClz)){
            	ChatBoxUtil.setBoxHeight(useListScroll);
                useListScroll.mCustomScrollbar('update');
            }
            $('.winItem','.chat-win').each(function(){
            	var $win=$(this);
                var chatRecord=$('.chat-record',$win);
                var proPanel=$('.pro-panel',$win);
                var proPanel=$('.ord-panel',$win);
                var allordPanel=$('.allord-panel',$win);
                var quePanel=$('.que-panel',$win);
                var ordPanel=$('.ord-detail-panel',$win);
                var msgPanelCont=$('.msgPanelCont',$win);
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
                if(quePanel.hasClass(scrollClz)&&!quePanel.is(":hidden")){
                	ChatBoxUtil.setBoxHeight(quePanel,$win);
                	quePanel.mCustomScrollbar('update');
                }
                if(ordPanel.hasClass(scrollClz)&&!ordPanel.is(":hidden")){
                	ChatBoxUtil.setBoxHeight(ordPanel,$win);
                	ordPanel.mCustomScrollbar('update');
                }
               
                msgPanelCont.each(function(){
                	  if($(this).hasClass(scrollClz)&&!$(this).is(":hidden")){
                      	ChatBoxUtil.setBoxHeight($(this),$win);
                      	$(this).mCustomScrollbar('update');
                      }
                })
              
            });
       },200));
      /* 判断 浏览器resize end*/
      //聊天记录分页绑定
		var msgPanel = $("#msgPanel"+this.chatId,chatBox.box);
		var msgPage = $(".msg-page", msgPanel);
		var sbtn = $('button',msgPage);
		var userCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
		var csaCode = ChatBoxUtil.getUserFromJid(chatBox.to||chatBox.from);
		$(sbtn).click(function(){
			//获取历史消息总数
			var count=0;
			var body = $('#message').val();
		    $.eAjax({
    	        url: GLOBAL.WEBROOT + '/history/getMessageCount',
    	        data: {userCode:userCode,csaCode:csaCode,messageType:'msg',body:body,shopId:ChatBoxUtil.shopId},
    	        datatype: 'json',
    	        async: false,
    	        success: function(returnInfo) {
    				count = returnInfo;
    	        }
		    });
			if($(this).hasClass('page-next')){
				if(ChatBoxUtil.pageNum!=1){
					ChatBoxUtil.pageNum = ChatBoxUtil.pageNum - 1;
					chatBox._loadChatHisAjax();
				}
			}
			var s = count / ChatBoxUtil.pageSize;
			s = Math.ceil(s);
            if($(this).hasClass('page-prev')){
            	if(s>ChatBoxUtil.pageNum){
            		ChatBoxUtil.pageNum = ChatBoxUtil.pageNum + 1;
            		chatBox._loadChatHisAjax();
            	}
			}
            
            if($(this).hasClass('page-last')){
            	//查询第一页
            	if(s!=0){
            		ChatBoxUtil.pageNum = 1;
            		chatBox._loadChatHisAjax();
            	}
            	
            }
            
            if($(this).hasClass('page-first')){
            	//查询最后一页
            	if(s!=0){
            		ChatBoxUtil.pageNum = s;
            		chatBox._loadChatHisAjax();
            	}
            }
		});
        /* 分数评分 begin*/
        $('.eval-score span').live('click',function () {
            var score=$(this).parent();
            var index = $(this).index();
            var scoreClz='star' + index;
            score.attr('class', 'eval-score '+scoreClz);
            score.data('score',scoreClz);
            $(".starResult").show();
            $("#btnEvaluate").removeClass("disbaled"); 
            if(index == 0 || index == 1) {
            	$(".eval-result").show();
    		}else{
    			$(".eval-result").hide()
    		}
            var scoreIndex = {'1':'非常不满意','2':'不满意','3':'一般','4':'满意','5':'非常满意'};
            $(".starResult").html(scoreIndex[index+1]);
            $("#satisfyType").val(index+1);
        }); 
        
        /**
    	 * 不满意类型选择
    	 */
		$('.reason li').live('click',function(){
    		var self = $(this);
    		var notSatisfyType = self.attr("notSatisfyType"); 
    		if(notSatisfyType == 0 || notSatisfyType == 1) {
    			$(".user-say").show();
    		}else{
    			$(".user-say").hide();
    		}
    		$("#notSatisfyType").val(notSatisfyType);
    		self.closest("ul").find("li").each(function(i,el){
    			if($(el).is(self)){ 
					if(self.hasClass("on")){
						$(el).removeClass("on");
						$(".user-say").hide();
						$("#notSatisfyType").val("");
					}else{
						$(el).addClass("on");
					} 
				}else{
					$(el).removeClass("on");
				} 
    		});
    	});
		
		/**
    	 * 评价提交保存
    	 */
		$('#btnEvaluate').live('click',function(){
			chatBox.sendEvaluate();
    	});
	
        /* 分数评分 end*/  
	
	},
	/**
	 * domInfo:{messageType,uName,custPic}
	 * record: MessageHistoryRespVO
	 * side: left or right .. chat-right
	 * 
	 * @returns {String}
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
	_createHistoryMsg:function(){
		var chatBox = this;
		var userCode = $("#uNameForLogout").val();
		var to = this.to;
		var csaCode = ChatBoxUtil.getUserFromJid(to);
		var staffCode = $("#staffCode").val();
		var shopId = ChatBoxUtil.shopId;
        $.eAjax({
	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
	        data: {
	        	userCode:userCode,
	        //	csaCode:csaCode,
//	        	messageType:'msg',
	        	pageNumber:ChatBoxUtil.pageNum,
	        	pageSize:ChatBoxUtil.pageSize,
	        	shopId:shopId,
	        	status:'20'
	        },
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	returnInfo = returnInfo.reverse();
	        	var winItem = this.box;
  	    		var chatMain = $('#chatMain'+userCode+'WEB',winItem);
  	    		var chatRecord = $(".chat-record", chatMain);
  	            var chartList = $('.list', chatRecord);
	        	$.each(returnInfo, function(p1, p2) {
	        		if(ChatBoxUtil.getUserFromJid(p2.from) == ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid)){
	        	            var tplData = {
	        	            		msg: p2.body,
	        	            		uName: staffCode,
	        	            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
	        	            		custPic: ChatBoxUtil.custPic
	        	            }
	        	            var html = template('selfRecord', tplData);
	        	            if(p2.messageType != "msg"){
	        	            	var domInfo = {
	        	            			messageType: p2.messageType,
	        	            			uName: staffCode,	
	        	            			custPic: ChatBoxUtil.custPic
	        	            	};
	        	            	html = chatBox._createMessageDom(domInfo, p2, "chat-right");
	        	            }
	        	            $(html).appendTo(chartList);
	        		}else{
	        			
		                var tplData = {
		            		msg: p2.body,
		            		uName:ChatBoxUtil.ServName,
		            		time: ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd hh:mm:ss"),
		            		custPic: ChatBoxUtil.servPic
		                }
		                var html = template('record', tplData);
		                if(p2.messageType != "msg"){
		                	var domInfo = {
		                			messageType: p2.messageType,
		                			uName: ChatBoxUtil.ServName,	
		                			custPic: ChatBoxUtil.servPic
		                	};
		                	html = chatBox._createMessageDom(domInfo, p2, "");
		                }
		                $(html).appendTo(chartList);
	        		}
	        	});
	        	
	        },
	        exception: function() {
	            eDialog.alert("面板历史消息加载异常");
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
				chatId:this.chatId
		};
		var chatSideHis = $(".chat-hisMgs", winItem);
        var html = template('hisMgsTpl', proData);
        chatSideHis.empty().append(html);
        var chatId = this.chatId;
        var shopId = this.shopId;
        $('.clkRecord', winItem).click(function () {
        	$('#message').val('');
            winItem.toggleClass('hasMsgPanel');
            var hMsgList = $("#msgPanel"+chatId, winItem).find('.msgPanelCont');
            hMsgList.empty();
        	var userCode = $("#uNameForLogout").val();
        	var to = chatBox.to;
    		var csaCode = ChatBoxUtil.getUserFromJid(to);
    		var date = new Array();
            $.eAjax({
    	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
    	        data: {
    	        	userCode:userCode,
    	        //	csaCode:csaCode,
//    	        	messageType:'msg',
    	        	pageNumber:ChatBoxUtil.pageNum,
    	        	pageSize:ChatBoxUtil.pageSize,
    	        	shopId:shopId,
    	        	status:'20'
    	        },
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
    	        		var ul = $('<ul class="msg-list"></ul>')
    	         	  	$.each(returnInfo, function(p1, p2) {
        	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
        	        		if(day==date[key]){
        	        			var csa = p2.from.substring(0,4);
        	        			var li = "";
        	        			if(csa == 'csa_'){
        	        				li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.ServName+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
        	        			}else{
        	        				li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
        	        			}
        	        			li = chatBox._createMessageHtml(p2);
        	        			ul.append(li);
    	        			}
        	        	});
    	        		$(hMsgList).append(ul);
	        		}
    	        }
            });
            
            if (hMsgList.hasClass('mCustomScrollbar')) {
                hMsgList.mCustomScrollbar();
            } else {
            	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
                hMsgList.mCustomScrollbar({autoHideScrollbar:true});
            }
        });
        
        //评价
        $('.clkEval', winItem).qtip({
            content:function(){ 
            	return chatBox.evaluateCheck();
            },
            show: {
                event: 'click', // Show it on click...
            },
            hide: 'unfocus',
            style: {
                classes: 'qtip-bootstrap qtip-eval'
            },
            position:{
                my: 'bottom left',
                at: 'top center'
            }
        }); 
        
        //图片放大
        eImage.gallery($('.chat-record', winItem),{
            bigImg:".msg img",
            ignoreImg:'uImg'
        });
        
        //历史消息模糊查询
        this._hisMsgLike();
    
	},
	_loadChatHisAjax:function(){
		var chatBox = this;
		var winItem = this.box;
		var chatId = this.chatId;
        var hMsgList = $("#msgPanel"+chatId, winItem).find('.msgPanelCont');
    	var userCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
		var csaCode = ChatBoxUtil.getUserFromJid(this.to||this.from);
		var date = new Array();
		var body = $('#message').val();
		var shopId = this.shopId;
        $.eAjax({
	        url: GLOBAL.WEBROOT + '/history/getMessageHistory',
	        data: {userCode:userCode,body:body,pageNumber:ChatBoxUtil.pageNum,pageSize:ChatBoxUtil.pageSize,shopId:shopId},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	returnInfo = returnInfo.reverse();
	        	if(returnInfo.length>0){
	        		 hMsgList.empty();
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
    	        			var csa = p2.from.substring(0,4);
    	        			
     	        		   if(csa == 'csa_'){
     	        		    var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.ServName+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
     	        			}else{
     	        			var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
     	        			}
     	        		   	li = chatBox._createMessageHtml(p2);
    	        			//var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
    	        			body.append(li);
	        			}
    	        	});
	         	  	$(hMsgList).append(body);
	        		}
	        }
        });
        
        if (hMsgList.hasClass('mCustomScrollbar')) {
            hMsgList.mCustomScrollbar();
        } else {
        	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
            hMsgList.mCustomScrollbar({autoHideScrollbar:true});
        }
	},
	/**
	 * 历史消息模糊查询
	 */
	_hisMsgLike:function(){
		var chatBox = this;
		var winItem = this.box;
		//历史记录模糊查询
		var hMsgList = $("#msgPanel"+this.chatId, winItem);
		var msgsearch = $(".msg-search", hMsgList);
		var sbtn = $(msgsearch).find('.sbtn');
		var chatId = this.chatId;
		var shopId =this.shopId;
		sbtn.click(function(){
			 var hMsgList = $("#msgPanel"+chatId, winItem).find('.msgPanelCont');
			 hMsgList.empty();
			 	var userCode = $("#uNameForLogout").val();
				var to = chatBox.to;
	    		var csaCode = ChatBoxUtil.getUserFromJid(to);
	    		var date = new Array();
	    		var body = $(msgsearch).find('input').val();
	    		ChatBoxUtil.pageNum = 1;
	            $.eAjax({
	    	        url: GLOBAL.WEBROOT + '/history/getMessageHistoryDate',
	    	        data: {userCode:userCode,body:body,pageNumber:ChatBoxUtil.pageNum,pageSize:ChatBoxUtil.pageSize,shopId:shopId},
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
	    	        		var ul = $('<ul class="msg-list"></ul>')
	    	         	  	$.each(returnInfo, function(p1, p2) {
	        	        		var day = ebcDate.dateFormat(p2.beginDate, "yyyy-MM-dd");
	        	        		if(day==date[key]){
	        	        			var csa = p2.from.substring(0,4);
	        	        			
	         	        		   if(csa == 'csa_'){
	         	        		    var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.ServName+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
	         	        			}else{
	         	        			var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.getStaffCodeByUserCode(ChatBoxUtil.getUserFromJid(p2.from))+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
	         	        			}
	         	        		   	li = chatBox._createMessageHtml(p2);
	        	        			//var li = $('<li><p class="mh"><span class="uName">'+ChatBoxUtil.ServName+'</span><span class="cTime">'+ebcDate.dateFormat(p2.beginDate, "hh:mm:ss")+'</span></p><div class="cont">'+p2.body+'</div></li>');
	        	        			ul.append(li);
	    	        			}
	        	        	});
	    	        		$(hMsgList).append(ul);
		        		}
	    	        }
	            });
	            hMsgList.mCustomScrollbar();
	           /* if (hMsgList.hasClass('mCustomScrollbar')) {
	                hMsgList.mCustomScrollbar('update');
	            } else {
	            	ChatBoxUtil.setBoxHeight(hMsgList,winItem);
	                hMsgList.mCustomScrollbar({autoHideScrollbar:true});
	            }*/
			
		});
	},
		
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
		sessionId:"",
		contentType:"",
		sendTime:""
	},
	ServName:"",
	csaCode : "",
	defPass:"123456",
	ue:null,
	connection: "",
	custPic : "",
	servPic : "",
	clearInterval : "",
	connected: false,
	BOSH_SERVICE: "",
	pageNum : 1,
	pageSize : 10,
	model: "",//界面模型
	list: [],
	first: null,
	last: null,
	timeOutId:"",//定时任务id
	outTime:10*60*1000,//会话超时时间5分钟
	timeOutRId:"",//会话超时提示ID
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
		var chatId = this.getChatIdFromJID(jid);
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
		 var calHeight = $(window).height()*.85- ChatBoxUtil.constants.baseH-$('.chat-bar').outerHeight();
		 if(domObj.hasClass('chat-record')){
			 calHeight = calHeight - $('.chat-header',winItem).outerHeight()
                         - $('.chat-in',winItem).outerHeight()
                         - 10;
		 }
		 else if(domObj.hasClass('msgPanelCont')){
			 calHeight = calHeight  - $('.msg-head',winItem).outerHeight()
                         - $('#msgTab',winItem).outerHeight()
                         - 60;
		 }
		 else if(domObj.hasClass('pro-panel')){
			 calHeight = calHeight - $('.chat-side .nav-tabs',winItem).outerHeight() - 30;
		 }
		 else if(domObj.hasClass('allord-panel')){
			 calHeight = calHeight - $('.chat-side .nav-tabs',winItem).outerHeight() - 30;
		 }
		 else if(domObj.hasClass('ord-detail-panel')){
			 calHeight = calHeight - $('.chat-side .nav-tabs',winItem).outerHeight() - 30;
		 }
		 else if(domObj.hasClass('que-panel')){
			 calHeight = calHeight - $('.chat-side .nav-tabs',winItem).outerHeight() - 30;
		 }
		 else if(domObj.hasClass('chat-uContainer')){
			 calHeight = calHeight - $('.chat-nav .tit').outerHeight() +10;
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
	/**
	 * 主动断开与服务器的连接
	 */
	disconnFromServ: function(){
		$.eAjax({
	        url: GLOBAL.WEBROOT + '/cust/logout/disconn',
	        data: {csaCode:$("#csaCodeForLogout").val(),shopId:$("#shopIdForLogout").val(),ofStaffCode:$("#uNameForLogout").val(),sessionId:$('#sessionId').val()},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	
	        	if (returnInfo.resultFlag == "ok") {
	        		/*//通知客服下线
	        		 var messageValue ="该会员已经离开";
	        		 var msg = $msg({  
	        	         to: ChatBoxUtil.csaCode,//发送或回复消息   
	        	         from: ChatBoxUtil.connection.jid, //登录者ID
	        	         id : "inform",
	        	         messagetype : "inform",//通知类型
	        	         type: 'chat'  
	        	     }).c("body", null, messageValue).c("session", {id: "inform"});//带入本次会话的sessionId
	        	     ChatBoxUtil.connection.send(msg.tree());*/
    	        	 var presence = $pres({type: "unavailable"}).c("status").t("Unavailable");//$pres().c("show").t("xa").up().c("status").t("down the rabbit hole!");
    	        	 ChatBoxUtil.connection.send(presence.tree());
    	        	 ChatBoxUtil.connection.flush();
	        		 eDialog.alert('对不起！由于长时间没有发送消息，您已断开与客服的连接，请重新接入。',function(){
	        		    var sessionId = $('#sessionId').val();
	        			var userAgent = navigator.userAgent;
	        			if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") !=-1) {
	        			   window.location.href = "about:blank";
	        			} else {
	        			   window.opener = null;
	        			   window.open("", "_self");
	        			   window.close();
	        			}
	        			
	        		});
	        	}
	        }
        });
	},
	timeOutRemind:function(){
		
		ChatBoxUtil.showNotice('您已经8分钟未发言，如果继续不发言2分钟后会自动断线，请您注意.',false);
	},
	/**
	 * 排队
	 */
	queueUp : function(){
		$.eAjax({
	        url: GLOBAL.WEBROOT + '/cust/getHotlineQueue',
	        data: {businessType:$('#issueType').val(),shopId:$("#shopIdForLogout").val(),ofStaffCode:$("#uNameForLogout").val(),custLevelCode:$('#custLevel').val(),orderId:$('#ordId').val(),goodsId:$('#gdsId').val()},
	        datatype: 'json',
	        async: false,
	        success: function(returnInfo) {
	        	var waitCount = returnInfo.waitCount;
	        	var notice = $('.notice');
	        	$(notice).find('#noticeClose').click(function(){
	 				$(notice).hide();
	 			});
	        	
	       	 if(waitCount=='-99'){
	       		 /*判断是否第一次加载*/
	       	   if($('#waitCount').val()=='-99'){
      		    	ChatBoxUtil.showNotice('对不起，客服还没有上线，请在正常工作时间内联系客服人员.',false);
      		    	setTimeout("ChatBoxUtil.queueUp()",5000);
      		    	return false;
      		    }
	       		    ChatBoxUtil.ServName = "人卫商城在线客服";
	       		    $('#waitCount').val(waitCount);
	       		 
	            	//初始化界面
	            	var chatBean = {
	            		chatId: ChatBoxUtil.getChatIdFromJID(ChatBoxUtil.connection.jid),
	            		model: ChatBoxUtil.constants.CUST,
	            		sessionId : '',
	            		to: '',
	            		isinit : false,
	            		shopId:returnInfo.shopId
	            	}
	            	try {
	            		new ChatBox(chatBean);
	            		ChatBoxUtil.showNotice('对不起，客服还没有上线，请在正常工作时间内联系客服人员.',false);
	            		$('#sendMsg').attr("disabled",true);
	            	} catch(e) {
	            		if(window.console && window.console.log){
	            			console.error(e);
	            		}
	            	}
	            	setTimeout("ChatBoxUtil.queueUp()",5000);
	 			return false;
	 	     }else if(waitCount!=0){
	 	    	 if($('#waitCount').val()>0){
	 	    		    ChatBoxUtil.showNotice("您的前面还有"+waitCount+"位正在等待，请稍等",false);
	      		    	setTimeout("ChatBoxUtil.queueUp()",5000);
	      		    	return false;
	      		    }
	 	    	 $('#waitCount').val(waitCount);
	 	    	   //初始化界面
	            	var chatBean = {
	            		chatId: ChatBoxUtil.getChatIdFromJID(ChatBoxUtil.connection.jid),
	            		model: ChatBoxUtil.constants.CUST,
	            		sessionId : '',
	            		to: '',
	            		isinit : false,
	            		shopId:returnInfo.shopId
	            	}
	            	try {
	            		new ChatBox(chatBean);
	            		ChatBoxUtil.showNotice("您的前面还有"+waitCount+"位正在等待，请稍等",false);
	            		$('#sendMsg').attr("disabled",true);
	            	} catch(e) {
	            		if(window.console && window.console.log){
	            			console.error(e);
	            		}
	            	}
	 	    	   
	    			setTimeout("ChatBoxUtil.queueUp()",5000);
	        	}else{
	        		 $('#waitCount').val('');
	        		 $('#sendMsg').attr("disabled",false);
	        		ChatBoxUtil.csaCode = returnInfo.csaCode;  
	        		$('#sessionId').val(returnInfo.sessionId);
	        		  //初始化界面
	            	var chatBean = {
	            		chatId: ChatBoxUtil.getChatIdFromJID(ChatBoxUtil.connection.jid),
	            		model: ChatBoxUtil.constants.CUST,
	            		sessionId : returnInfo.sessionId,
	            		to: returnInfo.csaCode+returnInfo.ofserver,
	            		isinit : true,
	            		shopId:returnInfo.shopId
	            	}
	            	try {
	            		ChatBoxUtil.ServName = returnInfo.serName;
	            		//ChatBoxUtil.ServName = "人卫商城在线客服";
	            		new ChatBox(chatBean);
	            		/*设置超时时间*/
	            		if($('#sessionTime').val()!=''&&$('#sessionTime').val()!=null){
	            			ChatBoxUtil.outTime = $('#sessionTime').val();
	            		}
	            		//获取离线消息
	            		var chat = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
	                    ChatBoxUtil.fetchHistoryMsg(returnInfo.shopId,returnInfo.csaCode,chat);
	            		inform(returnInfo.sessionId,chatBean.to);//通知客服对接
		        		ChatBoxUtil.showNotice("人民卫生出版社"+ChatBoxUtil.ServName+"将为您服务,为保证您的服务质量，请您在服务结束后，点击小红心为本次服务做出评价",true);
	            	} catch(e) {
	            		if(window.console && window.console.log){
	            			console.error(e);
	            		}
	            	}
	            	return waitCount;
	        	}
	        }
		});
	},
	getStaffCodeByUserCode :function(chatId){
		var user = chatId.substring(0,5);
		var csa = chatId.substring(0,4);
		if(user=='user_'){
			return chatId.substring(5,chatId.length);
		}else if(csa == 'csa_'){
			return chatId.substring(4,chatId.length);
		}
		return chatId
	},
	showNotice :function(e,f){
		var notice = $('.notice');
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
	},
	/**
	 * 获取与相关客服的离线消息
	 * @param csaCode
	 */
	fetchHistoryMsg : function(shopId,csaCode,chatBox){
		if(!shopId) return;
		if(!csaCode) return;
		var chatBox = this.getChatBoxFromJID(ChatBoxUtil.connection.jid) || chatBox;
		if(chatBox){
			var shopId = chatBox.shopId;
			var userCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
			var iq = $iq({  
                type: 'get'
            }).c("offlinemsg", {"xmlns":Strophe.NS.OFFLINEMSG_IQ,"shopId":shopId,"userCode":userCode, "csaCode":csaCode}, "");
			ChatBoxUtil.connection.sendIQ(iq.tree(),
					function(e){
		            },
		            function(e){
		            }    
            ); 
		}
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
		//TODO
		console.log(status+" ERROR");
		eDialog.alert('您已在其他页面发起咨询，当前咨询已失效， 请点击确定重新咨询!',function(){
			location.reload();
		});
		break;
	case Strophe.Status.CONNECTED:
		connectSuccess();
		console.log(status+" CONNECTED");
		ChatBoxUtil.connected = true;
		break;
	case Strophe.Status.DISCONNECTED:
		//TODO
		console.log(status+" DISCONNECTED");
		eDialog.alert('您已在其他页面发起咨询，当前咨询已失效， 请点击确定重新咨询!',function(){
			location.reload();
		});
		ChatBoxUtil.connected = false;
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
	var presence = $pres().c("show").t("chat").up().c("status").t("I am online!");
	ChatBoxUtil.connection.send(presence.tree()); // 发送在线请求
	//等待人数
	waitCountError();
	
}

/**
 * 连接失败
 */
var connectFail = function(){
	//TODO do something
}


var waitCountError = function(){
	 
   waitCount = ChatBoxUtil.queueUp();
}

/**
 * 通知客服对接
 */
var inform = function(sessionId,to){
	
	     var userCode = ChatBoxUtil.getUserFromJid(ChatBoxUtil.connection.jid);
	     $.eAjax({
		        url: GLOBAL.WEBROOT + '/history/getSessionCount',
		        data: {userCode:userCode},
		        datatype: 'json',
		        async: true,
		        success: function(returnInfo) {
		        	var count = returnInfo.count;
		        	var csaCode = returnInfo.csaCode;
		        	var sessonTime = returnInfo.sessionTime;
		        	var messageValue = "";
		        	count = count -1;
		        	if(count=='0'){
		        		 messageValue = "用户"+ChatBoxUtil.getStaffCodeByUserCode(userCode)+"第"+(count+1)+"次接入";
		        	}else{
		        		 var s = ChatBoxUtil.getUserFromJid(csaCode);
		        		 messageValue = "用户"+ChatBoxUtil.getStaffCodeByUserCode(userCode)+"第"+(count+1)+"次接入,上次由客服"+ ChatBoxUtil.getStaffCodeByUserCode(s)+"接入于"+ebcDate.dateFormat(sessonTime, "yyyy-MM-dd hh:mm:ss");
		        	}
		   	     //通知客服人员买家接入
		         var sessionId = $('#sessionId').val();
		         ChatBoxUtil.message.messagetype = "inform";
		         ChatBoxUtil.message.body = messageValue;
		         ChatBoxUtil.message.sessionId = sessionId;
		         var str = JSON.stringify(ChatBoxUtil.message); 
				 var msg = $msg({  
			         to: to,//发送或回复消息   
			         from: ChatBoxUtil.connection.jid, //登录者ID
			         id : "inform",
			       //  messagetype : "inform",//通知类型
			         type: 'chat'  
			     }).c("body", null, str)//.c("session", null,sessionId);//带入本次会话的sessionId
			     ChatBoxUtil.connection.send(msg.tree());
				  //用户发消息后，先清除原来的定时任务 
 	            window.clearTimeout(ChatBoxUtil.timeOutId);
 	            window.clearTimeout(ChatBoxUtil.timeOutRId);
			     //启动定时任务，监听用户5分钟之内是否有发送消息，未发送消息，则断开与服务器的连接
			     ChatBoxUtil.timeOutId = window.setTimeout(ChatBoxUtil.disconnFromServ,ChatBoxUtil.outTime);
			     ChatBoxUtil.timeOutRId = window.setTimeout(ChatBoxUtil.timeOutRemind, ChatBoxUtil.outTime-120000);
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
    var id = msg.getAttribute('id');  
    var elems = msg.getElementsByTagName('body'); 
  //  var session = msg.getElementsByTagName('session');  
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
    		messageType:json.messagetype,
    		contentType:json.contentType,
    		sessionId: json.sessionId
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
 * handleHeadline 处理业务通知 
 * body中的对象包含 "bizcode"
 */
function handleHeadline(msgInfo){
	switch(msgInfo.bizcode){
		case "transferuser"://用户待接收
			var sessionId = msgInfo.body.sessionId;
			var csaCode = msgInfo.body.csaCode;
			var hotlinePerson = msgInfo.body.hotlinePerson;
			var greeting = msgInfo.body.greeting;
			
			var chatBox = ChatBoxUtil.getChatBoxFromJID(ChatBoxUtil.connection.jid);
			var refCsaCode = chatBox.getFrom();
			csa = csaCode+refCsaCode.substring(refCsaCode.indexOf("@"));
			//1.更新chatBox
			chatBox.setSessionId(sessionId);
			$('#sessionId').val(sessionId);
			chatBox.setFrom(csa);
			chatBox.setTo(csa);
			ChatBoxUtil.csaCode = csaCode;
			//2.更新boxutil
			ChatBoxUtil.ServName = hotlinePerson;
			//3.更新html
			$("#hotlineSerName", chatBox.box).html("-"+hotlinePerson);
			//4.问候语
			// 展示消息
			ChatBoxUtil.showNotice(greeting,true,chatBox.box);
			
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
	var chatBox = null;
	//消息同步
	if(msgInfo.from == ChatBoxUtil.connection.jid){
		chatBox = ChatBoxUtil.getChatBoxFromJID(ChatBoxUtil.connection.jid);
		if(chatBox){
			chatBox.syncSendMessage(msgInfo);
		}
		
		return;
	}
	
	if(ChatBoxUtil.model == ChatBoxUtil.constants.SERV){
		chatBox = ChatBoxUtil.getChatBoxFromJID(msgInfo.from);
		if(!chatBox){
			var chatBean = {
				chatId: ChatBoxUtil.getChatIdFromJID(msgInfo.from),
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
		chatBox.msgId = msgInfo.id;
	}
	if(chatBox && !chatBox.from){
		chatBox.setFrom(msgInfo.from);
	}
	if(chatBox){
		chatBox.showReceived(msgInfo);
		ChatBoxUtil.setBrowserMsgTip();//收到消息 有效消息进行提示
	}
	
}


/* ========= strophe end ========== */
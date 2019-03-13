//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			var shopId = $("#shopId").val();
			var beginTime    = $("#beginTime").val();
			var endTime = $("#endTime").val();
			var data = new Object();
			data.shopId = shopId;
			data.beginTime = beginTime;
			data.endTime = endTime;
		
 			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/gdsevalshop/gridlist',
				asyncLoad : true,
				asyncTarget : '#evalListDiv',
				params :function(){
					return data;
				}
			});


		            $('a[name=del]').live("click",function () {});


		            $('a[name=edit]').live("click",function () {
		            	var giftId = $(this).attr("giftId");
		            	window.location.href = GLOBAL.WEBROOT+'/seller/gift/giftedit?giftId='+giftId;
		            	
		            });
		            
		            
		        	//回复级提交回复
		        	var submitRReply = function(){
		        		// 回复按钮
		        		$(":input.forreply").live('click',function(e){
		        			
		        			// var btn = $(e);
		        			var btn = $(this);
		        			btn.button('loading');// 设置按钮为处理状态，并为中读，不允许点击
		        			
		        			// 获取回复内容
		        			var detail = $(this).parent().find("textarea")
		        					.val().trim();
		        			if (detail.length == 0) {
		        				eDialog.alert("请输入回复内容！");
		        				return false;
		        			}
		        			// 获取回复id
		        			var replyId = $(this).parents(".reply-box").attr(
		        					"id");
		        			// 评价id
		        			var evalId = $(this).parents(".replytr").prev()
		        					.attr("id");
		        			$.eAjax({
		        				url : $webroot
		        						+ '/seller/gdsevalshop/savereply',
		        				data : {
		        					"evalId" : evalId,
		        					"detail" : detail,
		        					"replyId" : replyId
		        				},
		        				success : function(returnInfo) {
		        					if (returnInfo.bRespVo.resultFlag == 'ok') {
		        								$.eAjax({
		        									url : $webroot
		        											+ '/seller/gdsevalshop/replylist',
		        									data : {
		        										"evalId" : evalId
		        									},
		        									dataType : "text",
		        									success : function(
		        											returnInfo) {
		        										$("#" + evalId)
		        												.next(
		        														".replytr")
		        												.remove();
		        										$("#" + evalId)
		        												.after(
		        														$(returnInfo));
		        										$("#" + evalId)
		        												.find(
		        														".closereply")
		        												.show();
		        										$("#" + evalId)
		        												.find(
		        														".showreply")
		        												.hide();
		        									},
		        									error : function() {
		        										$("#" + evalId)
		        												.next(
		        														".replytr")
		        												.remove();
		        										$("#" + evalId)
		        												.after(
		        														"<tr class='replytr'><td colspan='10'>获取回复内容失败，请检查网络与浏览器</td></tr>");
		        										$("#" + evalId)
		        												.find(
		        														".closereply")
		        												.show();
		        										$("#" + evalId)
		        												.find(
		        														".showreply")
		        												.hide();
		        									}
		        								});// end of eAjax
		        					} else {
		        						eDialog.alert(returnInfo.msg);
		        					}
		        				},
		        				error : function() {
		        					btn.button('reset');
		        					eDialog.alert("请求发起失败，请检查网络与浏览器");
		        					
		        				}
		        			});// end of eAjax
		        			e.preventDefault();
		        			e.stopPropagation();
		        		});

		        	};
		        	
		        

		        	//显示回复文本输入框
		        	var showReplyInput = function(){
		        		//评价级回复输入框
		        		$('.rfeval').die().live('click',function(e){
		        			var text = $(this).parents(".evaltr").next('.replyeval');
		        			if (text.css('display') == 'none') {
		        				text.show();
		        			} else {
		        				text.hide();
		        			}
		        			e.preventDefault();
		        			e.stopPropagation();
		        		});
		        		
		        		// 回复级回复输入框
		        		$('.rhf').die().live('click',function(e){
		        			var text = $(this).parents('.reply-box').find('.textwrap');
		        			if (text.css('display') == 'none') {
		        				text.slideDown();
		        			} else {
		        				text.slideUp();
		        			}
		        			e.preventDefault();
		        			e.stopPropagation();
		        		});
		        	};
		        	
		        	
		        	//显示回复数据
		        	var showReplyData = function (){
		        		// 点击回复事件
		        		$('.replynum').die().live('click',function(e){
		        				// 如果回复内容已显示 收起回复内容
		        				if ($(this).find(".showreply").css('display') == 'none') {
		        					// 获取回复行
		        					var evaltr = $(this).parents("tr");
		        					var replytr = evaltr.next();
		        					
		        					//修改回复数
		        					var replyCount = replytr.find(".replyCount").val();
		        					if(replyCount)
		        					$(this).find(".showreply").children("a").text("显示回复("+ replyCount + ")");
		        					
		        					replytr.remove();
		        					$(this).find(".closereply").hide();
		        					$(this).find(".showreply").show();
		        				} else {// 否则显示回复内容
		        					var evalId = $(this).parents("tr").attr("id");
		        					$.eAjax({
		        						url : $webroot
		        								+ '/seller/gdsevalshop/replylist',
		        						data : {
		        							"evalId" : evalId
		        						},
		        						dataType : "text",
		        						success : function(returnInfo) {
		        							$("#" + evalId)
		        									.next(".replytr")
		        									.remove();
		        							$("#" + evalId).after(
		        									$(returnInfo));
		        							$("#" + evalId).find(
		        									".closereply").show();
		        							$("#" + evalId).find(
		        									".showreply").hide();
		        						},
		        						error : function() {
		        							$("#" + evalId)
		        									.next(".replytr")
		        									.remove();
		        							$("#" + evalId)
		        									.after(
		        											"<tr class='replytr'><td colspan='10'>获取回复内容失败，请检查网络与浏览器</td></tr>");
		        							$("#" + evalId).find(
		        									".closereply").show();
		        							$("#" + evalId).find(
		        									".showreply").hide();
		        						}
		        					});// end of eAjax
		        				}
		        				e.preventDefault();
		        				e.stopPropagation();
		        		});
		        				
		        	};
		        	
		        	// 评价级的回复提交
		        var	submiEReply = function() {
		        		$(":input.foreval").unbind('click').on('click',function(e){
		        			$(this).attr("disabled","disabled");  
//		        			var btn = $(e);
//		        			btn.button('loading');// 设置按钮为处理状态，并为中读，不允许点击
		        					// 获取回复内容
		        					var detail = $(this).parent(".etextwrap").find("textarea")
		        							.val().trim();
		        					if (detail.length == 0) {
		        						eDialog.alert("请输入回复内容！");
		        						return false;
		        					}
		        					var ss = $(this);
		        					// 获取评价id
		        					var evalId = $(this).parents(".replyeval").prev(".evaltr")
		        							.attr("id");
		        					$.eAjax({
		        						url : $webroot + '/seller/gdsevalshop/savereply',
		        						data : {
		        							"evalId" : evalId,
		        							"detail" : detail
		        						},
		        						success : function(returnInfo) {
		        							if (returnInfo.bRespVo.resultFlag == 'ok') {
		        								// 删除回复行
		        								$("#" + evalId).next('.replyeval').remove();
		        								// 改变操作列
		        								$("#" + evalId).find(".rfeval").remove();
		        								$("#" + evalId).find(".replynum").css(
		        										"display", "");
		        							} else {
		        								eDialog.alert(returnInfo.msg);
		        							}
		        							
		        						},
		        						error : function() {
		        							btn.button('reset');
		        							eDialog.alert("请求发起失败，请检查网络与浏览器");
		        						}
		        					});// end of eAjax
		        				
		        					e.preventDefault();
		        					e.stopPropagation();
		        				});
		        	};// end of evalReply
		        	showReplyInput();
		        	submitRReply();	       
		        	textLimit();
		        	//显示回复数据
		        	showReplyData();
		        	submiEReply();
		        
		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bPage' ],
		// 指定页面
		init : function() {
			var evalList = new pInit();
			evalList.init();
		}
	});
});
//限制输入字数
var	textLimit = function() {
	
	$.each($('textarea').filter('.rtext'),function(i,n){
		$(this).live('keyup',function(e){
			var str = $(this).val().trim();
			var l = str.length;
			if (l > 120) {
//				$(this).next(".help-block").children("b").text('0');
				$(this).val(str.substring(0, 120));
			} else {
				$(this).next(".help-block").children("b").text(
						120 -l);
			}
		});
	});
	
//	$.each($('textarea').filter('.rtext'),function(i,n){
//		$(this).live('keydown',function(e){
//			var str = $(this).val().trim();
//			var l = str.length;
//			if (l > 120) {
//				$(this).next(".help-block").children("b").text('0');
//				$(this).val(str.substring(0, 119));
//			} else {
//				$(this).next(".help-block").children("b").text(
//						120-l);
//			}
//		});
//	});
	
	
	/*$("textarea .rtext").keyup(
		function(e) {
			var str = $(this).val().trim();
			var l = str.length;
			var blen = 0; //字节长度 
			for (i = 0; i < l; i++) {
				if ((str.charCodeAt(i) & 0xff00) != 0) {
					blen++;
				}
				blen++;
				if (blen > 240) {
					$(this).val(str.substring(0, i));
					break;
				}
			}//end of for
			if (blen > 240) {
				$(this).next(".help-block").children("b").text('0');
			} else {
				$(this).next(".help-block").children("b").text(
						Math.floor((240 - blen) / 2));
			}
			e.preventDefault();
		});
	$("textarea .rtext").keydown(
		function(e) {
			var str = $(this).val().trim();
			var l = str.length;
			var blen = 0; //字节长度 
			for (i = 0; i < l; i++) {
				if ((str.charCodeAt(i) & 0xff00) != 0) {
					blen++;
				}
				blen++;
				if (blen > 240) {
					$(this).val(str.substring(0, i));
					break;
				}
			}//end of for
			if (blen > 240) {
				$(this).next(".help-block").children("b").text('0');
			} else {
				$(this).next(".help-block").children("b").text(
						Math.floor((240 - blen) / 2));
			}
			e.preventDefault();
		});*/
};//end of textLimit
	
	
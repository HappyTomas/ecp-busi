/**
 * 卖家评价管理初始化页面 js create by zhanbh 2015.9.16
 */
$(function() {
	// 初始化页面
	EvalGrid.pInit();
	// 绑定搜索栏事件
	EvalGrid.searchBar();
	EvalGrid.submitRReply();
	EvalGrid.showReplyInput();
	//显示回复数据
	EvalGrid.showReplyData();
	EvalGrid.submiEReply();
});// end of $(function(){})

var EvalGrid = {
	// 页面初始化
	pInit : function() {
		// 获取列表
		$.eAjax({
			url : $webroot + '/gdsevalshop/gridinit',
			data : EvalGrid.form2json($("#searchForm")),
			dataType : "text",
			success : function(returnInfo) {
				if (returnInfo) {
					$("#dataBody").html(returnInfo);
				}
			}// end of success
		});// end of eAjax
	},// end of pInit
	
	//评价列表事件绑定
	evalListFn : function (){
		//更新搜索栏与分页条状态
		EvalGrid.pageBar();
		//字数限制
		EvalGrid.textLimit();
		
	},

	// 与回复div有关的事件绑定
	repliesFn : function() {
		// 限制字数
		EvalGrid.textLimit();
	},// end of replies
	
	
	// 更新搜索栏于分页条状态
	pageBar : function() {
		// 更新分页条
		$('#pageControlbar').bPage({
			url : $webroot + '/gdsevalshop/gridlist',
			pageSizeMenu : [ 5, 10, 20 ],
			asyncLoad : true,
			asyncTarget : '#dataBody',
			params : function(){
				var data = EvalGrid.form2json($("#searchForm"));
				return data;
			}
		});
	},// end of updateBar
	
	// 与搜索栏有关的的事件绑定
	searchBar : function() {
		// 查询
		$('#btnFormSearch').click(function() {
			// 验证表单搜索框
			if (!$("#searchForm").valid())
				return false;
			// 获取表单参数
			var p = EvalGrid.form2json($("#searchForm"));
			
			//判断时间
			/*
			 * if(Boolean(p.beginTime) !== Boolean(p.endTime)){
				eDialog.alert("时间段有错误，请填完整，或去掉时间段！");
				return false;
			}
			*/
			if(p.beginTime && p.endTime && p.beginTime > p.endTime){
				eDialog.alert("结束时间不能早于开始时间！");
				return false;
			}
			// 执行查询
			EvalGrid.gridSearch(p);
		});
		// 重置
		$('#btnFormReset').click(function() {
			ebcForm.resetForm('#searchForm');
			var p = ebcForm.formParams($("#searchForm"));
			EvalGrid.gridSearch(p);
		});
	},// end of searchBar
	
	// 获取查询列表
	gridSearch : function(params) {
		$.eAjax({
			url : $webroot + '/gdsevalshop/gridinit',
			data : params,
			dataType : "text",
			success : function(returnInfo) {
				if (returnInfo) {
					$("#dataBody").html(returnInfo);
				}
			}// end of success
		});// end of eAjax
	},// end of gridSerach
	
	
	//显示回复文本输入框
	showReplyInput : function(){
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
	},
	//显示回复数据
	showReplyData : function (){
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
								+ '/gdsevalshop/replylist',
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
				
	},
	
	// 评价级的回复提交
	submiEReply : function() {
		$(":input.foreval").live('click',function(e){
			         $.gridLoading({"message":"正在提交回复,请稍候..."});
					// 获取回复内容
					var detail = $(this).parent(".etextwrap").find("textarea")
							.val().trim();
					if (detail.length == 0) {
						eDialog.alert("请输入回复内容！");
						$.gridUnLoading();
						return false;
					}
					// 获取评价id
					var evalId = $(this).parents(".replyeval").prev(".evaltr")
							.attr("id");
					$.eAjax({
						url : $webroot + '/gdsevalshop/savereply',
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
							$.gridUnLoading();
						},
						error : function() {
							eDialog.alert("请求发起失败，请检查网络与浏览器");
							$.gridUnLoading();
						},exception : function(msg){
							eDialog.alert("回复异常，请检查网络与浏览器");
							$.gridUnLoading();
						}
					});// end of eAjax
					e.preventDefault();
					e.stopPropagation();
				});
	},// end of evalReply
	
	
	//回复级提交回复
	submitRReply : function(){
		// 回复按钮
		$(":input.forreply").live('click',function(e){
			$.gridLoading({"message":"正在提交回复,请稍候..."});
			// 获取回复内容
			var detail = $(this).parent().find("textarea")
					.val().trim();
			if (detail.length == 0) {
				eDialog.alert("请输入回复内容！");
				$.gridUnLoading();
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
						+ '/gdsevalshop/savereply',
				data : {
					"evalId" : evalId,
					"detail" : detail,
					"replyId" : replyId
				},
				async : false,
				success : function(returnInfo) {
					if (returnInfo.bRespVo.resultFlag == 'ok') {
								$.eAjax({
									url : $webroot
											+ '/gdsevalshop/replylist',
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
					$.gridUnLoading();
				},
				error : function() {
					eDialog.alert("请求发起失败，请检查网络与浏览器");
					$.gridUnLoading();
				},
				exception : function(msg){
					eDialog.alert("回复异常，请检查网络与浏览器");
					$.gridUnLoading();
				}
			});// end of eAjax
			e.preventDefault();
			e.stopPropagation();
		});

	},
	
	//限制输入字数
	textLimit : function() {
		
		$.each($('textarea').filter('.rtext'),function(i,n){
			$(this).die('keyup').live('keyup',function(e){
				var str = $(this).val().trim();
				var l = str.length;
				if (l > 120) {
					$(this).next(".help-block").children("b").text('0');
					$(this).val(str.substring(0, 120));
				} else {
					$(this).next(".help-block").children("b").text(
							120 -l);
				}
			});
		});
		
//		$.each($('textarea').filter('.rtext'),function(i,n){
//			$(this).die('keydown').live('keydown',function(e){
//				var str = $(this).val().trim();
//				var l = str.length;
//				if (l > 120) {
//					$(this).next(".help-block").children("b").text('0');
//					$(this).val(str.substring(0, 119));
//				} else {
//					$(this).next(".help-block").children("b").text(
//							120-l);
//				}
//			});
//		});
		
		
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
	},//end of textLimit
	
	//将表单转化为json
	form2json : function( fromitem ){
		var data = new Object();
		fromitem.find("input").each(function(){
			data[$(this).attr("id")] = $(this).val();
		});
		fromitem.find("select").each(function(){
			data[$(this).attr("id")] = $(this).val();
		});
		fromitem.find("textarea").each(function(){
			data[$(this).attr("id")] = $(this).val();
		});
		
		return data;
	}
};// end of EvalGrid


/**
 * 解决IE不支持trim问题
 */
if (!String.prototype.trim) {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}

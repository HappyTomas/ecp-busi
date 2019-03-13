// 页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {

			$("#addShipBtn").live("click", function() {
				var shopId = $("#shopId").val();
				window.location.href = GLOBAL.WEBROOT
						+ "/seller/gdsshiptemp/toshiptempadd?shopId=" + shopId;

			});
			// //绑定提交按钮事件
			$('#shipSearchBtn').unbind("click").click(function() {
				
				
				if($("input[name='shipTemplateId']")){
					var gdsId=$("input[name='shipTemplateId']").val();
					if(gdsId && gdsId!=""){
						var r = /^[0-9]*$/;
						if(!r.test(gdsId)){
							eDialog.alert('模板编码只能是整数！');
							return false;
						}
					}
				}
				
				
				var shopId = $("#shopId").val();
			
				var  p = ebcForm.formParams($('#shipForm'));
				
				var url = GLOBAL.WEBROOT
						+ '/seller/gdsshiptemp/gridlist?shopId=' + shopId;
//				if (shipTemplateId) {
//					url = url + "&shipTemplateId=" + shipTemplateId;
//				}
//
//				if (shipTemplateName) {
//					url = url + "&shipTemplateName=" + shipTemplateName;
//				}
//				if (shipTemplateType) {
//					url = url + "&shipTemplateType=" + shipTemplateType;
//				}
//
//				if (ifFree) {
//					url = url + "&ifFree=" + ifFree;
//				}
				$('#shiptListDiv').load(url,p);
			});

			$(function() {
				// U.tab(".seller-tab .tab-nav",".seller-tab .tab-content");
				$('a[name=del]').click(function() {

					var tempId = $(this).attr("tempId");
					var shopId = $("#shopId").val();
					var param = {
						tempId : tempId,
						shopId : shopId
					};
					eDialog.confirm("确定要删除信息吗？", {
						'title' : "提示",
						'buttons' : [{
							caption : '确定',
							callback : function() {

								$.eAjax({
									url : GLOBAL.WEBROOT
											+ "/seller/gdsshiptemp/deleteshiptemp",
									data : param,
									success : function(returnInfo) {

										if (returnInfo.resultFlag == 'ok') {
											eDialog.success('删除成功！');
											$('#shipSearchBtn')
													.trigger('click');
										} else {
											eDialog.error(returnInfo.resultMsg);
										}
									}
								});

							}
						}, {
							caption : '取消',
							callback : $.noop
						},]
					});
				});

				$("#shipResetBtn").unbind("click").click(function() {
							$("#shipForm")[0].reset();
						});

			})

		};
		return {
			init : init
		};
	};
	pageConfig.config({
				// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
				plugin : ['bPage'],
				// 指定页面
				init : function() {
					var shipList = new pInit();
					shipList.init();
				}
			});
});

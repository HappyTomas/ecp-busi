// 页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			var shopId = $("#shopId").val();
			var id = $("#id").val();
			var mediaName = $("#mediaName").val();
			var data = new Object();
			data.shopId = shopId;
			data.id = id;
			data.mediaName = mediaName;
			// 分页
			$('#pageControlbar').bPage({
						url : GLOBAL.WEBROOT + '/seller/gdsmedia/gridlist',
						asyncLoad : true,
						asyncTarget : '#mediaListDiv',
						params : function() {
							return data;
						}
					});

			// 删除
			function mediaRemove(object) {
				var id = $(this).attr("mediaId");
				if (id == null) {
					eDialog.alert("图片编码为空，请联系管理员");
					return false;
				}
				eDialog.confirm('您确定要删除这条数据吗？', {
							buttons : [{
										caption : '确定',
										callback : function() {
											MediaGrid.mediaRemove(obj, id);
										}
									}, {
										caption : '取消',
										callback : function() {
											return true;
										}
									}]
						});

			}
			// 编辑
			$('a[name=edit]').click(function(){
				var id = $(this).attr("mediaId");
				bDialog.open({
							title : '编辑图片',
						    width : 700,
							height : 410,
							url : GLOBAL.WEBROOT + '/seller/gdsmedia/mediaedit?id='+ id,
							callback : function(data) {
							     // 刷新列表
								if(data && data.results && data.results[0].success){
									$('#mediaSearchBtn').trigger('click');
								}
							}
						});
			});
			// 预览
			$('a[name=preView]').click(function() {
				var id = $(this).attr("mediaId");
				bDialog.open({
							title : '图片预览',
							width : 400,
							height : 400,
							url : GLOBAL.WEBROOT + '/seller/gdsmedia/meidashow?id='
									+ id

						});
			});

			$('a[name=del]').click(function() {
				var mediaId = $(this).attr("mediaId");
				var param = {
					id : mediaId
				};
				eDialog.confirm("确定要删除信息吗？", {
					'title' : "提示",
					'buttons' : [{
						caption : '确定',
						callback : function() {

							$.eAjax({
								url : GLOBAL.WEBROOT + "/seller/gdsmedia/mediaremove",
								data : param,
								success : function(returnInfo) {
									if (returnInfo.resultFlag == 'ok') {
										eDialog.success('删除成功！');
										$('#mediaSearchBtn').trigger('click');
									}
								}
							});

						}
					}, {
						caption : '取消',
						callback : $.noop
					}]
				});
			});

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
					var giftList = new pInit();
					giftList.init();
				}
			});
});

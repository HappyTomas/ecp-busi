// 页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {

			// //绑定提交按钮事件
			$('#mediaSearchBtn').unbind("click").click(function() {
				if($("input[name='id']")){
					var gdsId=$("input[name='id']").val();
					if(gdsId && gdsId!=""){
						var r = /^[0-9]*$/;
						if(!r.test(gdsId)){
							eDialog.alert('图片编码只能是整数！');
							return false;
						}
					}
				}
				var  p = ebcForm.formParams($('#mediaForm'));
				
				var shopId = $("#shopId").val();
			
				var url = GLOBAL.WEBROOT + '/seller/gdsmedia/gridlist?shopId='
						+ shopId;

				$('#mediaListDiv').load(url,p);
			});

			$("#mediaAddBtn").live("click", function() {
						bDialog.open({
									title : '添加图片',
									width : 730,
									height : 410,
									url : GLOBAL.WEBROOT + '/seller/gdsmedia/mediaadd',
									callback : function(data) {
										// 刷新列表
										if(data && data.results && data.results[0].success){
											$('#mediaSearchBtn').trigger('click');
										}
									}

								});
					});
					
					$("#mediaResetBtn").unbind("click").click(function() {
								$("#mediaForm")[0].reset();
							});
			 		

		};
		return {
			init : init
		};
	};
	pageConfig.config({
				// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
				plugin : [],
				// 指定页面
				init : function() {
					var mediaList = new pInit();
					mediaList.init();
				}
			});
});

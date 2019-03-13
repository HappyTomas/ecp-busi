// 页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {

			// //绑定提交按钮事件
			$('#stockSearchBtn').unbind("click").click(function() {

				
				if($("input[name='gdsId']")){
					var gdsId=$("input[name='gdsId']").val();
					if(gdsId && gdsId!=""){
						var r = /^[0-9]*$/;
						if(!r.test(gdsId)){
							eDialog.alert('商品编码只能是整数！');
							return false;
						}
					}
				}
				
				
				if($("input[name='skuId']")){
					var gdsId=$("input[name='skuId']").val();
					if(gdsId && gdsId!=""){
						var r = /^[0-9]*$/;
						if(!r.test(gdsId)){
							eDialog.alert('单品编码只能是整数！');
							return false;
						}
					}
				}
				
				var p = ebcForm.formParams($("#searchForm"));
				p.push({
							'name' : 'catgCode',
							'value' : $("#catgCode").attr('catgCode')
						});
				var url = GLOBAL.WEBROOT
						+ '/seller/goods/stockinfo/listStock';
			
				$('#stockListDiv').load(url,p);
			});

			$("#catgCode").click(function() {
				bDialog.open({
					title : '分类选择',
					width : 350,
					height : 550,
					params : {
						multi : false
					},
					url : GLOBAL.WEBROOT
							+ "/seller/goods/category/open/catgselect?catgType=1",
					callback : function(data) {
						if (data && data.results && data.results.length > 0) {
							var _catgs = data.results[0].catgs;
							var size = _catgs.length;
							for (var i = 0; i < size; i++) {
								var obj = _catgs[i];
								$("#catgCode").val(obj.catgName);
								$("#catgCode").attr('catgCode', obj.catgCode);
							}
						}
					}
				});

			});
			
				$("#stockResetBtn").unbind("click").click(function() {
							$("#searchForm")[0].reset();
							$("#catgCode").attr('catgCode','');
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
					var stockList = new pInit();
					stockList.init();
				}
			});
});

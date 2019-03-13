// 页面初始化模块
$(function() {
			var pInit = function() {
				var init = function() {

					// //绑定提交按钮事件
					$('#collectSearchBtn').unbind("click").click(function() {
						
						
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
						
						var shopId = $("#shopId").val();
				
						var  p = ebcForm.formParams($('#collectForm'));
						
						
						var url = GLOBAL.WEBROOT
								+ '/seller/gdscollshop/gridlist?shopId='
								+ shopId;
				

						$('#collectListDiv').load(url,p);
					});
					$("#collectResetBtn").unbind("click").click(function() {
								$("#collectForm")[0].reset();
							});
					// $("#giftAddBtn").live("click",function(){
					// var shopId = $("#shopId").val();
					// window.location.href = GLOBAL.WEBROOT +
					// "/gift/giftadd?shopId=" + shopId;
					//		        	
					// });
					//			 

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
							var giftList = new pInit();
							giftList.init();
						}
					});
		});

// 点开查看用户
function showCollStaff(gdsid, skuid) {
	if (gdsid == null || skuid == null) {
		eDialog.alert("找不到此商品，请联系管理员");
		return false;
	}
	bDialog.open({
				title : '查看收藏用户',
				width : 850,
				height : 470,
				url : GLOBAL.WEBROOT
						+ '/seller/gdscollshop/showcollstaff?gdsId=' + gdsid
						+ "&skuId=" + skuid,

				callback : function() {

				}
			});
}
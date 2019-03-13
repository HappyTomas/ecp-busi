$(function(){
	//$.cookie('SUPPORT-WEB-COOKIE-MOUDEL', "homepage", {path : '/'});
	var _linkkey = $.cookie('SUPPORT-WEB-COOKIE-MOUDEL');
	var mainButtomNavbar = {
			"init":function(){
				mainButtomNavbar.showBuyNum();
				mainButtomNavbar.setSiteSession();
			},
			"showBuyNum":function(){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/order/getcartcount",
					data : "",
					success : function(returnInfo) {
						if(0<returnInfo){
							$('.num-tip').html(returnInfo);
							$('.num-tip').show();
						}else{
							$('.num-tip').html("");
							$('.num-tip').hide();
						}
					},
					error: function(){
						$('.num-tip').html("");
						$('.num-tip').hide();
					},
					exception : function(){
						$('.num-tip').html("");
						$('.num-tip').hide();
					}
				});
			},
			"setSiteSession":function(){
				var siteId = $(".bottom-site-id","#main_navbar").val();
				siteId = siteId || 1;
				$.eAjax({
					url : GLOBAL.WEBROOT + "/homepage/setSiteSession",
					data : {"siteId":siteId},
					success : function(returnInfo) {
						mainButtomNavbar.bindNavTab();
					},
					error: function(){
						mainButtomNavbar.bindNavTab();
					},
					exception : function(){
						mainButtomNavbar.bindNavTab();
					}
				});
			},
			"bindNavTab":function(){
				//绑定tab页切换
				$("#main_navbar a").each(function(){
					var lazyHref = $(this).attr("lazy-href");
					var linkkey = $(this).attr('linkkey');
					if(linkkey!=''&&linkkey==_linkkey){
						$(this).parent("li").addClass("active");
					}else{
						$(this).parent("li").removeClass("active");
					}
					if(lazyHref){
						$(this).attr("href",lazyHref);
						$(this).removeAttr("lazy-href");
					}
					$(this).click(function(){
						$("#main_navbar li").removeClass("active");
						$(this).parent("li").addClass("active");
						$.cookie('SUPPORT-WEB-COOKIE-MOUDEL', $(this).attr("linkkey"), {path : '/'});
					});
				});
			}
		};
	
	mainButtomNavbar.init();
});

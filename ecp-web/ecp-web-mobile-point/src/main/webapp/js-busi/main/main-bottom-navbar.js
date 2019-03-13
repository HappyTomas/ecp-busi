$(function(){
	//$.cookie('SUPPORT-WEB-COOKIE-MOUDEL', "homepage", {path : '/'});
	var _linkkey = $.cookie('SUPPORT-WEB-COOKIE-MOUDEL');
	$('#main_navbar a').each(function(){
		$(this).attr("href",$(this).attr("lazy-href"));
		var linkkey = $(this).attr('linkkey');
		if(linkkey!=''&&linkkey==_linkkey){
			$(this).parent("li").addClass("active");
		}else{
			$(this).parent("li").removeClass("active");
		}
	});
	
	//绑定tab页切换
	$("#main_navbar a").click(function(e){
		$("#main_navbar li").removeClass("active");
		$(this).parent("li").addClass("active");
		$.cookie('SUPPORT-WEB-COOKIE-MOUDEL', $(this).attr("linkkey"), {path : '/'});
	});
	mainButtomNavbar.showBuyNum();
});
var mainButtomNavbar = {
	"showBuyNum":function(){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/point/order/getcartcount",
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
	}	
};


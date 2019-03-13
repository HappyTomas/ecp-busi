
$(function(){
	var nav_type = $("#nav_type").val();
	if(nav_type != undefined && nav_type != null && nav_type != ''){
		$("#pt-nav .item").removeClass("current");		
		$("#pt-nav .item").eq(nav_type).addClass("current");
	}
});

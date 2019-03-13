$(function(){
	$(".top-left","header").unbind("click").bind("click",function(){
		history.go(-1);
	});
});
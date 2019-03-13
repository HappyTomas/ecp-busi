$(function(){
	$("#sellerManage li").live('click',function(e){
		var _this = $(this);
		var name = _this.attr('name');
		if(name=="page"){
			window.location.href= $webroot+"cmssellerpageinfo/init";
		}else if(name=="advertise"){
			window.location.href= $webroot+"cmsselleradvertise/init";
		}
		e.preventDefault();
	});
});
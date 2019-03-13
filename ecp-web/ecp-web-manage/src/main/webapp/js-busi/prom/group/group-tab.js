$(function () { 
        $('#myTab a').click(function (e) {
              e.preventDefault();
              $(this).tab('show');
        });
	//页面返回(detail 返回 和列表返回)
	$('.btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/promgroup';
	});
});
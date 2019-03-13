$(function(){
	remove();
	//发布促销
	$('.publish').click(function(){
		$(this).attr('disabled',"true"); 
		window.location.href = GLOBAL.WEBROOT+'/createprom/'+this.name+'/'+this.id;
	});
	//返回
	$('.btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/createprom';
	});
	//错误信息
	$('#btnShowError').click(function(){
		$('div.formValidateMessages').slideToggle('fast');
	});
});

function remove(){
	$('.publish').removeAttr('disabled');
}

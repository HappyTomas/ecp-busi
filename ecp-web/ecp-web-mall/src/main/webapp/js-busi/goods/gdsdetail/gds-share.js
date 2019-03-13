$(function(){
	$("#share-trigger a").powerSwitch({
        container: $("#share-container"),
        number: 1,
    });
});
function shareAddress(){
	var url = "http://"+window.location.host+""+GLOBAL.WEBROOT+"/gdsdetail/share?gdsId="+$("#gdsId").val()+"&skuId="+$("#skuId").val()
				+"&staffId="+$("#staffId").val();
	window.open(url);
}
$('#bdText').bind('propertychange input', function () {
	var content = $('#bdText').val().trim();
    var counter = content.length;
    var num = 100 - counter;
    if(num<0){
    	//eDialog.alert("输入的字数不能超过100个！");
    	$('#bdText').val(cutStr(content,100));
    	num = 0;
    }
    $('#allNum').text("您还可以输入"+num+"个字");
});
$('#bdText').trigger('propertychange');
$('#bdText').trigger('input');

function cutStr(str,num){//根据字数截取字符串  超出进行截取  
	if(!str && str !== 0){str = ""}
	str = (str + "").trim();
	num = +num;
	if(num !== num || 0 > num){
		num = 0;
	}
	var len = str.length;
	if(len > num){
		str = str.substring(0,num);
	}
	return str;
}
function defaultShare(){
	eDialog.alert("请至少选中一个分享地址！");
}
function initImgurl(){
	var obj_lis = document.getElementById("share-container").getElementsByTagName("li");
	for(i=0;i<obj_lis.length;i++){
		obj_lis[i].onclick = function(){
			$(this).siblings('li').removeClass('active');
			$(this).addClass('active');
			shareOne();
		}
	}
	shareOne();
}
$('#p-share').click(function () {
        $(this).parent().find('.share-box').toggle();
        initImgurl()
    });
$('#off').click(function(){
	$('#p-share').trigger('click');
});
$('#inlineCheckbox1').change(function (e) {
	var isChecked = document.getElementById("inlineCheckbox1");
	if (isChecked.checked == true) {
		$('#inlineCheckbox2').removeAttr("checked");
		$('#inlineCheckbox3').removeAttr("checked");
		$("#newshare").remove();
		$("#oldshare").remove();
		$("#share-pro").append("<div id='newshare' ><div class='bdsharebuttonbox'>" +
				"<a href='javascript:void(0);' class='bds_tqq' data-cmd='tqq' title='分享到腾讯微博'>分 享</a>" +
		"</div></div>");
//		$("#oldshare").replaceWith("<div id='newshare' ><div class='bdsharebuttonbox'>" +
//				"<a href='#' class='bds_tqq' data-cmd='tqq' title='分享到腾讯微博'>分享</a>" +
//		"</div></div>");
	}else{
		$("#newshare").replaceWith("<div class='share-btn' id='oldshare'><a href='javascript:void(0);' onclick='defaultShare()'>分 享</a></div> ");
	}
	shareOne();
	
})
$('#inlineCheckbox2').change(function (e) {
	var isChecked = document.getElementById("inlineCheckbox2");
	if (isChecked.checked == true) {
		$('#inlineCheckbox1').removeAttr("checked");
		$('#inlineCheckbox3').removeAttr("checked");
		$("#newshare").remove();
		$("#oldshare").remove();
		$("#share-pro").append("<div id='newshare' ><div class='bdsharebuttonbox'>" +
				"<a href='javascript:void(0);' class='bds_tsina' data-cmd='tsina' title='分享到新浪微博'>分 享</a>" +
		"</div></div>");
//		$("#oldshare").replaceWith("<div id='newshare' ><div class='bdsharebuttonbox'>" +
//				"<a href='#' class='bds_tsina' data-cmd='tsina' title='分享到新浪微博'>分享</a>" +
//		"</div></div>");
	}else{
		$("#newshare").replaceWith("<div class='share-btn' id='oldshare' ><a href='javascript:void(0);' onclick='defaultShare()' >分 享</a></div> ");
	}
	shareOne();
	
})
$('#inlineCheckbox3').change(function (e) {
	var isChecked = document.getElementById("inlineCheckbox3");
	if (isChecked.checked == true) {
		$('#inlineCheckbox2').removeAttr("checked");
		$('#inlineCheckbox1').removeAttr("checked");
		$("#newshare").remove();
		$("#oldshare").remove();
		$("#share-pro").append("<div id='newshare' ><div class='bdsharebuttonbox'>" +
				"<a href='javascript:void(0);' class='bds_douban' data-cmd='douban' title='分享到豆瓣网'>分&nbsp;享</a>" +
		"</div></div>");
//		$("#oldshare").replaceWith("<div id='newshare' ><div class='bdsharebuttonbox'>" +
//				"<a href='#' class='bds_douban' data-cmd='douban' title='分享到豆瓣网'>分享</a>" +
//		"</div></div>");
	}else{
		$("#newshare").replaceWith("<div class='share-btn' id='oldshare'><a href='javascript:void(0);' onclick='defaultShare()' >分 享</a></div> ");
	}
	shareOne();
})
function shareOne(){
	var ul=document.getElementById("share-container").getElementsByTagName("li");
	var imgUrl;
    for(var i=0;i<ul.length;i++){
       var imgUrl_temp=ul[i].getAttribute("value");
       var liClass=ul[i].getAttribute("class");
       if(liClass.indexOf("active")>=0){
    	   imgUrl=imgUrl_temp;
       }
    
    }
	window._bd_share_config = { 
			common : { 
				onBeforeClick: function SetShareUrl(cmd, config) {            
		                config.bdText = $("#bdText").val(); 
		                config.bdPic = imgUrl;
		                config.bdUrl = "http://"+window.location.host+""+GLOBAL.WEBROOT+"/gdsdetail/share?gdsId="+$("#gdsId").val()+"&skuId="+$("#skuId").val()
										+"&staffId="+$("#staffId").val();
		            return config;
		        }
/*				bdText : bdText, 
				bdDesc : '自定义分享摘要', 
				bdUrl : "", 
				bdPic : bdPic*/ 
			}, 
			share : [{ 
				"bdSize" : 36
				/*"bdCustomStyle" : "../images/css/product-detail.css"*/
				}], 

	}
	//判断是否已经被销毁，重新加载。
	if(window._bd_share_main){
		window._bd_share_main.init();
	}
}
	

with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)]; 

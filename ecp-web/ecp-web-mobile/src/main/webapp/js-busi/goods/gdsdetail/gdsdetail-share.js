/**
 * lcx
 */
$(function(){
	//分享地址与商城一致  在商城做跳转处理
	var url = $("#mallSiteUrl").val()+"/gdsdetail/share?gdsId="+$("#gdsId").val()+"&skuId="+$("#skuId").val()+"&staffId="+$("#staffId").val();
	$.eAjax({
		url : GLOBAL.WEBROOT + "/gdsdetail/getSignature",
		data : {
			"url" :  window.location.href//生成签名的url必须是浏览器地址栏的地址
		},
		success : function(returnInfo) {
			var timestamp = returnInfo.timestamp;
			var nonceStr = returnInfo.echostr;
			var signature = returnInfo.signature;
			var appid = returnInfo.appid;
			weixininit(appid,nonceStr,timestamp,signature);
//			wx.error(function(res){
//				alert(res.errMsg);
//			});
			wx.ready(function(){
				//调试验证客户端是否支持分享接口
//				wx.checkJsApi({
//					jsApiList : ['onMenuShareAppMessage','onMenuShareQQ','onMenuShareTimeline','onMenuShareQZone'],
//					success : function(res) {
//						alert(JSON.stringify(res));
//					}
//				});
				sendQQ(url)
				shareTimeline(url);
				shareAppMessage(url);
				shareQZone(url);
			})

		},
		exception : function(){
		}
	});
	
	
});
function weixininit(appid,nonceStr,timestamp,signature){
	wx.config({
//		debug: true,
//		appId:"wx8b358f3aead72646",
//		timestamp:'1472539651',// 必填，生成签名的时间戳
//		nonceStr: '7915334779189325355',// 必填，生成签名的随机串
//		signature: '5be9d75c6969a66f4193445444f70436b6ae9057',// 必填，签名，见附录
		appId:appid,
		timestamp:timestamp,// 必填，生成签名的时间戳
		nonceStr: nonceStr,// 必填，生成签名的随机串
		signature: signature,// 必填，签名，见附录
		jsApiList: ['onMenuShareAppMessage','onMenuShareQQ','onMenuShareTimeline','onMenuShareQZone']
	})
}

//分享到QQ
function sendQQ(url){
	var title = $("#gdsName").val();
	var firstImgUrl = $("#firstImgUrl").val();
	var desc = $("#shareDesc").val() || title || url;
	wx.onMenuShareQQ({
		title: title, // 分享标题
		desc: desc, // 分享描述
		link: url, // 分享链接
		imgUrl: firstImgUrl, // 分享图标
		success: function () {
//			alert("sucessfull");
		},
		cancel: function () {
//			alert("failed");
		}
	})
}

//分享到朋友圈
function shareTimeline(url){
	var title = $("#gdsName").val();
	var firstImgUrl = $("#firstImgUrl").val();
	var desc = $("#shareDesc").val() || title || url;
	wx.onMenuShareTimeline({
		title: title, // 分享标题
		desc: desc, // 分享描述
		link: url, // 分享链接
		imgUrl: firstImgUrl, // 分享图标
		success: function () {
//			alert("sucessfull");
		},
		cancel: function () {
//			alert("failed");
		}
	})
}

//分享给朋友
function shareAppMessage(url){
	var gdsName = $("#gdsName").val();
	var firstImgUrl = $("#firstImgUrl").val();
	var desc = $("#shareDesc").val() || title || url;
	wx.onMenuShareAppMessage({
		title: gdsName, // 分享标题
		desc: desc, // 分享描述
		link: url, // 分享链接
		imgUrl: firstImgUrl, // 分享图标
		success: function () {
//			alert("分享成功");
		},
		cancel: function () {
//			alert("failed");
		}
	})
}

//分享到QQ空间
function shareQZone(url){
	var title = $("#gdsName").val();
	var firstImgUrl = $("#firstImgUrl").val();
	var desc = $("#shareDesc").val() || title || url;
	wx.onMenuShareQZone({
		title: title, // 分享标题
		desc: desc, // 分享描述
		link: url, // 分享链接
		imgUrl: firstImgUrl, // 分享图标
		success: function () {
//			alert("分享成功");
		},
		cancel: function () {
//			alert("failed");
		}
	})
}

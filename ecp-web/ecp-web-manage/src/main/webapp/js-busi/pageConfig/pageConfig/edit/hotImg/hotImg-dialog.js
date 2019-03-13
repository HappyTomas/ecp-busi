/**
 * 
 * 热点编辑界面
 * 
 * @author Terry
 * 
 */
$(function(){
	//是否初始化完成开关
	var init = false;
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	
	$('#btnCancel').click(function(){
		bDialog.closeCurrent();
	});
	var getFormData = function(){
		var form = $('#saveHotImgForm');
		var id = $('#id',$(form)).val();
		var pageId = $('#pageId',$(form)).val();
		var picId = $('#picId',$(form)).val();
		var itemPropId = $('#itemPropId',$(form)).val();
		var urlInfo = $('#urlInfo',$(form)).val();
		var showTitle = $('#showTitle',$(form)).val();
		 
		return result;
	};
	var IsURL = function(str_url){
		var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
			+ "(([0-9]{1,3}.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-z_!~*'()-]+.)*" // 域名- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]." // 二级域名
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'/().;?:@&=+$,%#-]+)+/?/)$";
			var re=new RegExp(strRegex);
			//re.test()
			if (re.test(str_url)){
				return (true);
			}else{
				return (false);
			}
	};
	
	//保存表单
	$('#save').click(function(){
		var urlInfo=$.trim($('#urlInfo').val());
		var showTitle=$.trim($('#showTitle').val());
		var url = $('form.templateItemForms').data('url');
		if(urlInfo==null||urlInfo.length==0){
			$("#error-msg-hot-img").text("请填写链接地址").show();
			return;
		}else if(showTitle==null||showTitle.length==0){
			$("#error-msg-hot-img").text("请填写显示标题").show();
			return;
		}else if(IsURL(urlInfo)==false){
			$("#error-msg-hot-img").text("链接地址格式错误").show();
			return;
		}else{
			$("#error-msg-hot-img").hide();
		}
		var form = $('#saveHotImgForm');
		var id = $('#id',$(form)).val();
		var pageId = $('#pageId',$(form)).val();
		var picId = $('#picId',$(form)).val();
		var itemPropId = $('#itemPropId',$(form)).val();
		var relativeCoord = $('#relativeCoord',$(form)).val();
		$.eAjax({
			url : url,
			data : {"id":id,"pageId":pageId,"picId":picId,"itemPropId":itemPropId,
				"urlInfo":urlInfo,"showTitle":showTitle,"relativeCoord":relativeCoord},
			success : function(returnInfo){
				if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
					eDialog.alert('热点设置成功！',function(){
						if($.isFunction(_callback)){
							_callback({
								'id' : returnInfo.resultMsg
							});
						}
						bDialog.closeCurrent();
						;
					},'confirmation');
				}
			}
		});
	});
});
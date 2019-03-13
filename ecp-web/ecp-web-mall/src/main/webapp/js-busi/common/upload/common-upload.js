$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//返回的数组
	var fileResults = new Array();
	//统计上传成功文件个数
	var successCount = 0;
	//上传处理URL
	var url = _param.uploadUrl ? _param.uploadUrl : $webroot + 'ecpupload/publicFileUpload';
    //单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
    //最终结果可在onQueueComplete中统一显示
    var uploadSuccess = function(file,data,response){
		if(response){
			if(data){//data为后台返回的JSON内容
				var tmp = JSON.parse(data);
				var fileInfo = {};
				if($.fn.eUpload.onUploadSuccessCheck(tmp)){
					if(tmp && $.isPlainObject(tmp) && typeof(tmp.fileId) != 'undefined' && typeof(tmp.url) != 'undefined' ){
						fileInfo.fileId = tmp.fileId;
						fileInfo.url = tmp.url;
						fileResults.push(fileInfo);
					}
				}
			}
		}
	};
	//上传插件初始化参数
	var uploadInitParams = {
		'uploader' : url + ';jsessionid=' + $('#pageSessionId').val(),//后台接收文件处理的controller
		'fileObjName' : _param.uploadFileObjName ? _param.uploadFileObjName : 'uploadFileObj',//后台接收文件的对象名称
		'fileTypeDesc' : _param.fileTypeExts,  //文件选择类型描述
		'fileTypeExts' : _param.fileTypeExts,  //文件选择类型限制
		'fileSizeLimit' : _param.fileSizeLimit, //文件大小限制
		'params' : {
			'token' : $('#pageSessionId').val(),
			'fileSizeLimit' : _param.fileSizeLimit,
			'fileTypeExts'  : _param.fileTypeExts
		},
		'multi' : _param.checktype=='multi'?true:false,  //是否批量上传
		'queueID' : "attachmentFileQueue",//队列内容显示元素ID指定默认ID为attachmentFileQueue
		'onUploadSuccess' : uploadSuccess,
		//回调
		'callback' : function(){
			$('#btnFileUploaderUpload').button('reset');//设置状态按钮的状态为恢复
		}
	};
	

    $('#btnFileUploaderUpload').click(function(){
    	var itemList = $('#attachmentFileQueue .uploadify-queue-item');
    	if($(itemList).size() > 0){
        	$(this).button('loading');//设置状态按钮的状态为处理中
        	successCount = 0;
    		$("#attachmentFileInput").eUpload(uploadInitParams);
    	}else{
    		eDialog.alert('请选择需要上传的文件！');
    	}
    });
    
	
	//更多的参数请参考e.upload.js中的详细参数
	$("#attachmentFileInput").eUploadInit(uploadInitParams);
	
	//完成上传，关闭窗口，返回数据
	$('#btnUploadDone').click(function(){
		bDialog.closeCurrent(fileResults);
	});
	
	//Flash安装检查
	var result = ebcUtils.flashCheck();
	if(!result.hasFlash){
		$('#checkMessage').text('文件上传功能依赖Flash插件，当前浏览器未安装，请在安装后刷新页面');
	}
});
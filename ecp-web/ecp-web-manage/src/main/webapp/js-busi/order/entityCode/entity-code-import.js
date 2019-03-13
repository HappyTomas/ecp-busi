$(function(){
	//统计上传成功文件个数
	var successCount = 0;
    $('#fileUploaderUpload').click(function(){
    	$(this).button('loading');//设置状态按钮的状态为处理中
    	successCount = 0;
		$("#attachmentFileInput").eUpload();
    });
    
    //单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
    //最终结果可在onQueueComplete中统一显示
    var uploadSuccess = function(file,data,response){
		if(response){
			if(data){//data为后台返回的JSON内容
				var tmp = JSON.parse(data);
				if(tmp && typeof(tmp.successCount) != 'undefined'){
					successCount += tmp.successCount;
				}
			}
		}
	};
    
	//更多的参数请参考e.upload.js中的详细参数
	$("#attachmentFileInput").eUploadInit({
		'uploader' : GLOBAL.WEBROOT + '/ordersub/import',//后台接收文件处理的controller
		'fileTypeDesc' : "*.xls;*.xlsx",  //文件选择类型描述
		'fileTypeExts' : "*.xls;*.xlsx",  //文件选择类型限制
		'queueID' : "attachmentFileQueue",//队列内容显示元素ID指定默认ID为attachmentFileQueue
		'onUploadSuccess' : uploadSuccess,
		//回调
		'callback' : function(){
			$('#fileUploaderUpload').button('reset');//设置状态按钮的状态为恢复
		}
	});
});
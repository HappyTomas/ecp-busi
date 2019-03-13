$(function(){
	$('#btnFileUpload').click(function(evt){
		busSelector.uploader({
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#imgPreview').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	
//	$('#textAutocomplete').autocomplete({
//		source : [['boat', 'dog', 'drink', 'elephant', 'fruit', 'London']],
//		minLength : 1,
//		limit : 10,
//		showHint : false
//	});
	
	//***********************************************文件上传静默模式（非弹出窗口）*****************************************
	//返回的数组
	//var fileResults = new Array();
	//返回的文件信息
//	var fileInfo = {};
/*//*var uploadSuccess = function(file,data,response){
		if(response){
			if(data){//data为后台返回的JSON内容
				var tmp = JSON.parse(data);
				//var fileInfo = {};
				//检查文件是否上传成功
				if($.fn.eUpload.onUploadSuccessCheck(tmp)){
					if(tmp && $.isPlainObject(tmp) && typeof(tmp.fileId) != 'undefined' && typeof(tmp.url) != 'undefined' ){
						fileInfo.fileId = tmp.fileId;
						$('#vfsId').val(fileInfo.fileId);
						$('#vfsName').val(file.name);
						fileInfo.url = tmp.url;
						eDialog.alert("上传成功。");
						//fileResults.push(fileInfo);
					}
				}
			}
		}
	};*/
	/*var uploadError = function(event, queueId, fileObj, errorObj){
		eDialog.error("导入格式需正确，且大小小于5M");
	};*/
	
	//更多的参数请参考e.upload.js中的详细参数
	/*$("#attachmentFileInput").eUploadSilentInit({
		'uploader' : $webroot + 'ecpupload/publicFileUpload',//后台接收文件处理的controller
		'fileTypeDesc' : "*.doc;*.docx;.pdf;.zip;.rar",  //文件选择类型描述
		'fileTypeExts' : "*.doc;*.docx;*.pdf;*.zip;*.rar",  //文件选择类型限制(.doc,.docx,.pdf,.zip,.rar)
		//'queueID' : "attachmentFileQueue",//队列内容显示元素ID指定默认ID为attachmentFileQueue
		'queueSizeLimit':1,
		'multi' : false,
		'onUploadSuccess' : uploadSuccess,
		'onUploadError': uploadError,	
		//回调
		'callback' : function(){
			if(fileInfo) $('#imgPreview1').attr('src',fileInfo.url);
		}
	});*/
	$("#attachmentFileInput").eUploadBaseInit({
		 "fileSizeLimit":'5MB',
		"fileTypeExts":['doc','docx','pdf','zip','rar'],
	    "callback" : function(fileInfo){
			$('#vfsId').val(fileInfo.fileId);
			$('#vfsName').val(fileInfo.fileName);
		    if(fileInfo) $('#imgPreview1').attr('src',fileInfo.url);
			eDialog.alert("上传成功。");
	    }
	});

	//***********************************************文件上传静默模式（非弹出窗口）*****************************************
});
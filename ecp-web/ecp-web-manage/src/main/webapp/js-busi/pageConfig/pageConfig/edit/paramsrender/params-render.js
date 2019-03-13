/**
 * 页面编辑
 * @author Terry
 * 
 * 
 * 属性类型常量
 * 01--单选
 * 02--多选
 * 03--手动输入
 * 04--下拉框
 * 05--图片
 * 06--自定义
 */
$(function(){
	var preBox = $('div.imagePreviewBox');
	//***********************************************文件/图片上传处理*****************************************
	//返回的文件信息
	var fileInfo = {};
    //单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
    //最终结果可在onQueueComplete中统一显示
/*    var uploadSuccess = function(file,data,response){
		if(response){
			if(data){//data为后台返回的JSON内容
				var tmp = JSON.parse(data);
				//检查文件是否上传成功
				if($.fn.eUpload.onUploadSuccessCheck(tmp)){
					if(tmp && $.isPlainObject(tmp) && typeof(tmp.fileId) != 'undefined' && typeof(tmp.url) != 'undefined' ){
						fileInfo.fileId = tmp.fileId;
						fileInfo.url = tmp.url;
					}else fileInfo = null;
				}
			}
		}
	};*/
    
	//更多的参数请参考e.upload.js中的详细参数
	if($("input.fileUploadPlugin").size() > 0){
		$("input.fileUploadPlugin").each(function(i,n){
			var _this = this;
			var main = $(this).closest('div.controls');
			var key = $('.rowPropKey',$(main)).attr('id');
			$(this).eUploadBaseInit({
				//'uploader' : $webroot + 'ecpupload/publicFileUpload',//后台接收文件处理的controller
				//'onUploadSuccess' : uploadSuccess,
				//回调
				'callback' : function(fileInfo){
					if(fileInfo){
						$('#propValue',$(main)).val(fileInfo.fileId);
						$('.uploadImgUrl',$(main)).val(fileInfo.url);
						$('span.imgUploadStatus',$(main)).removeClass('imgUnUpload').addClass('imgUploaded').text('已上传');
						//TODO: 处理预览区唯一问题，同一位置，若多次上传图片，应覆盖原图
						$('.'+key,$(preBox)).remove();//移除原预览图
						$(preBox).append('<img src="' + fileInfo.url + '" class="' + key + '">');
					}else{
						$('#propValue',$(main)).val('');
					}
				}
			});
		});
	}
	//***********************************************文件/图片上传处理*****************************************
	//无图片上传内容，不显示图片预览区
	if($('.imgUploadStatus').size() > 0) $('div.imgPreviewArea').show();
});
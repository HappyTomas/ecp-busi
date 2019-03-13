$(function() {
	//绑定文件属性的上传事件===========start
	 $('.upFileTyle').each(function(){
		 
	    	$(this).live('change',function(e){
	    		var fileSize =  this.files[0].size;
	    		if(fileSize/1048576 >10){
	    			eDialog.alert("文件最大不能超过10M");
	    			return;
	    		}
	    		var path = $(this).val();
	    		fileInfo.uploadFile(this, path);
				e.preventDefault();
	    	});
	    }); 
	
}); 
function cancelFile(obj){
	$(obj).parent().find('input[type="text"]').removeAttr('disabled');
	$(obj).parent().find('input[type="text"]').val('');
	$(obj).parent().find('.file-wrap').show();
	$(obj).hide();
} 
var fileInfo = {
		uploadFile : function(object1, path){
			var filepath = path;
	    	filepath=(filepath+'').toLowerCase(); 
	    	var url = GLOBAL.WEBROOT + '/order/file/uploadfile';
	    	var propId = $(object1).attr('propId');
	    	var callback = function(data, status) {
	    		/** 上传成功，隐藏上传组件，并显示该图片 */
	    		if (data.success == "ok") {
	    			var _this = $("#file"+propId);
	    			_this.val(data.resultMap.vfsId);
	    			_this.attr('disabled',true);
	    			_this.parent().find('.file-wrap').hide();
	    			_this.parent().find('button').show();
	    		} else {
	    			eDialog.error(data.message);
	    		}
	    		fileInfo.bindFileUpload();
	    		$("#attributeForm").valid();
	    	};
	    	fileInfo.ajaxFileUpload(url, false, $(object1).attr('id'), "POST", "json", callback);
		},
		bindFileUpload : function(){
			 $('.upFileTyle').each(function(){
			    	$(this).live('change',function(e){
			    		var fileSize =  this.files[0].size;
			    		if(fileSize/1048576 >10){
			    			eDialog.alert("文件最大不能超过10M");
			    			return;
			    		}
			    		var path = $(this).val();
			    		fileInfo.uploadFile(this, path);
						e.preventDefault();
			    	});
			    });
		},
		ajaxFileUpload : function(url, secureuri, fileElementId, type, dataType,
				callback) {
			$.ajaxFileUpload({
						url : url, // 用于文件上传的服务器端请求地址
						secureuri : secureuri, // 一般设置为false
						fileElementId : fileElementId, // 文件上传空间的id属性 <input
						// type="file" id="imageFile"
						// name="imageFile" />
						type : type, // get 或 post
						dataType : dataType, // 返回值类型
						success : callback, // 服务器成功响应处理函数
						error : function(data, status, e) // 服务器响应失败处理函数
						{
							alert(e);
						}
					});
		}
}
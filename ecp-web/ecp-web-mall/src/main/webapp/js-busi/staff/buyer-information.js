
$photoUpload  = (function(){
	var init = function(){
		var staffId = $('#staffId').val();
		$("#uploadFileButton").eUploadBaseInit({
			callback : function(fileInfo) {
				$('#iconimage').attr('src', fileInfo.url);
				$.eAjax({
					url : $webroot + 'information/icon',
					data : {"custPic":fileInfo.fileId},
					success : function(returnInfo) {
						eDialog.success('用户头像保存成功！', {
							buttons : [ {
								caption : "确定",
								callback : function() {
									location.reload(false);
								}
							} ]
						});
						//刷新缓存
					}
				});
			}
		});
	};

	var submit = function(){
		if(!$("#custInformation").valid())return false;
		var formData = $('#custInformation').serializeArray();
	//	formData = eval("(" + formData + ")");
		var object_id = "";
		$("input[name='disturbFlag']").each(function(){
			  if ($(this).attr("checked")) {
				    	object_id +=$(this).val();
				    }else{
				    	object_id = '0';
				    }
			  });	
		formData.disturbFlag = object_id;
		$.eAjax({
			url : $webroot+'information/savecustinfo',
			data : formData,
			success : function(returnInfo) {
				eDialog.success('用户保存成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							location.reload(false);
				        }
					}]
				}); 
				//刷新缓存
			}
		});
	};
	
	return {
		'init':init,
		'submit':submit
	};

	
})();


$(function(){
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['eUpload'],
		//指定页面
		init : function(){
			var ppp = $photoUpload;
			ppp.init();
	/*		$('#saveIcon').on('click',function(){
				 $('#uploadify').uploadify('upload');
				 
			 });*/
			 
			 $('#sub').on('click',function(){
				 $photoUpload.submit();
			 });
			
		}
	});

})




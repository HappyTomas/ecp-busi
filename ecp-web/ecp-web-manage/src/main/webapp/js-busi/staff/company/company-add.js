$(function() {
	var url = GLOBAL.WEBROOT + '/company/addcompany';
	// 统计上传成功文件个数
	$('#btnFormSave').click(function() {
		if (!$("#detailInfoForm").valid())
			return false;
		$(this).attr('disabled', 'true');
		$.eAjax({
			url : url,
			data : ebcForm.formParams($("#detailInfoForm")),
			complete:function(){
				$('#btnFormSave').removeAttr('disabled'); 
			},
			success : function(returnInfo) {
				eDialog.success('企业新增成功！', {
					buttons : [ {
						caption : "确定",
						callback : function() {
							window.location.href = 'grid';
						}
					} ]
				});
				// 刷新缓存
			}
		});
	});

	$('#btnReturn').click(function() {
		history.back();
	});

	/*$("#image1").eUploadInit({
		'uploader' : GLOBAL.WEBROOT + '/company/licence',//后台接收文件处理的controller
		'fileObjName' : 'licence',
		'queueID' : "image1attr",//队列内容显示元素ID指定默认ID为attachmentFileQueue
		'onUploadSuccess' : function(data){
			$('#image1Upload').button('reset');//设置状态按钮的状态为恢复
			$('#licence').val(data);
		},
		//回调
		'callback' : function(){
			$('#image1Upload').button('reset');//设置状态按钮的状态为恢复
		}
	});
	$('#image1Upload').click(function() {
		$(this).button('loading');// 设置状态按钮的状态为处理中
		$('#image1').eUpload();

	});*/
	
	$('#image1Upload').click(function(evt){
		busSelector.uploader({
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#licencePath').val(data.results[0].fileId);
					$('#imgPreview1').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	
	$('#image2Upload').click(function(evt){
		busSelector.uploader({
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#legalPersonImage').val(data.results[0].fileId);
					$('#imgPreview2').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	
	$('#fileUpload').click(function(evt){
		busSelector.uploader({
			fileTypeExts :['doc','docx','pdf'],
			checktype : 'single',
			'fileSizeLimit': '5MB',
			callback : function(data){
				var ddo = data.results.length;
				if(ddo>0){
					$.ajax({
						url : GLOBAL.WEBROOT + '/company/taxpath',
						data : {'fileId':data.results[0].fileId},
						success : function(fileInfo) {
							$('#fileurl').text('税务附件');
							$('#fileurl').attr('href',fileInfo);
							$('#taxPath').val(data.results[0].fileId);
						}
					});
				}
			}
		}, evt);
	});
	
	
	
	
	$('#isEnter').change(function(){
		var _val = $(this).val();
		if(_val=='0'){
			$('#ruzhu').hide();
		}else{
			$('#ruzhu').show();
		}
	});
	
	
	
	

});
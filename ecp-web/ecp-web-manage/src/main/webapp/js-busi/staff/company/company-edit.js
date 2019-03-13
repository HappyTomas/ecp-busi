$(function(){
	var url =  GLOBAL.WEBROOT + '/company/editcompany';

	$('#btnFormSave').click(function(){ 
		if(!$("#detailInfoForm").valid())return false;
		$(this).attr('disabled',false); 
		$.eAjax({
			url : url,
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('企业编辑成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = 'grid';
				        }
					}]
				}); 
				//刷新缓存
			}
		});
		
	});
	
	var _isEnter=  $('#isEnter').val();
	if(_isEnter=='1'){
		$('#ruzhu').show();
	}
	
	$('#isEnter').change(function(){
		var _val = $(this).val();
		if(_val=='0'){
			$('#ruzhu').hide();
		}else{
			$('#ruzhu').show();
		}
	});
	$('#btnReturn').click(function(){
		history.back();
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
			fileTypeExts :'*.jpg;*.gif;*.bmp;*.png;*.rar;*.zip;*.doc;*.docx;*.xls;*.xlsx;*.pdf',
			fileTypeDesc : '*.jpg;*.gif;*.bmp;*.png;*.rar;*.zip;*.doc;*.docx;*.xls;*.xlsx;*.pdf',
			checktype : 'single',
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
});
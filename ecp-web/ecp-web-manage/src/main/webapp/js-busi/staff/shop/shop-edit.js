$(function(){
    //设置全选复选框 
    $("#list :checkbox").click(function(){ 
        if(this.checked){    
        	 $(this).attr("checked", true);   
        }else{    
        	 $(this).attr("checked", false); 
        }  
    }); 
	$('#btnFormSave').click(function(){
		if(!$("#detailInfoForm").valid()) return false;
        var valArr = new Array; 
        $("#list :checkbox[checked]").each(function(i){ 
            valArr[i] = $(this).val(); 
        }); 
        var vals = valArr.join(','); 
               
		var param = ebcForm.formParams($("#detailInfoForm"));
		param.push(
		{
			name : 'distribution',
			value : vals
		});
		
					
		$.eAjax({
			url : GLOBAL.WEBROOT + "/shop/editshop",
			data : param,
			datatype:'json',
			success : function(returnInfo) {
				eDialog.success('编辑成功！',{
					buttons:[{
						caption:"确定",
						callback:function(){
							window.location.href = GLOBAL.WEBROOT+'/shop/grid';
				        }
					}]
				}); 
			}
		});
					
	});
	
	$('#btnReturn').click(function(){
		//$('#returnFrom').submit();
//		history.back();
		window.location.href = GLOBAL.WEBROOT+'/shop/grid';
	});
	$('#shoplogoUpload').click(function(evt){
		busSelector.uploader({
			uploadUrl : GLOBAL.WEBROOT + '/company/shoplogoUpload',
			title : '店铺logo上传',
			fileSizeLimit : '2MB',
			imageMaxHeight : 200,
			imageMaxWidth : 300,
			imageMinHeight : 11,
			imageMinWidth : 11,
			checktype : 'single',
			callback : function(data){
				if(data && data.results && data.results.length > 0){
					$('#logoMongodbKey').val(data.results[0].fileId);
					$('#imgPreview1').attr('src',data.results[0].url);
				}
			}
		}, evt);
	});
	
});
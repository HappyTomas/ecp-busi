$(function(){
	//修改
	$('#btnFormUpdateSubmit').click(function(){
		if(!$("#detailInfoForm").valid())return false;
		var _val = ebcForm.formParams($("#detailInfoForm"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/authrole/updaterole",
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('修改成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = 'grid';
					        }
						}]
					});
		    	}else{
		    		eDialog.error('修改失败！'+remsg); 
		    	}
			}
		});
	});
	
	//返回
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/authrole/grid';
	});
});
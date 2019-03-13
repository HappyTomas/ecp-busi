$(function(){
	var url =  GLOBAL.WEBROOT + '/companycheck/checkok';

	$('#btnCheckOk').click(function(){ 
		$.eAjax({
			url : url,
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				eDialog.success('企业审核成功！请到企业管理模块查询该数据',{
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
	
	$('#btnCheckNo').click(function(){
		
		bDialog.open({
			title : '不通过理由',
			width : 400,
			height : 350,
			params : {
			'val':$('#companyId').val(),
			'Id' :$('#Id').val()
			},
			url : GLOBAL.WEBROOT+'/companycheck/checkremark',
			callback:function(data){
				if(data.results[0].ifsubmit){window.location.href = 'grid';}
				
			}
		});
		
	});
	
	$('#btnReturn').click(function(){
		window.location.href = 'grid';
	});
});
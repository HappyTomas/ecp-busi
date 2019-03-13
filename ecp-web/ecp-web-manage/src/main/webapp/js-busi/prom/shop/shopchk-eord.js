$(function(){
	
	//更多详情
	$("#queryDetail").click(function(){
		eNav.setSubPageText('更多详情');
		  var _promId=$('#promId').val();
			window.location.href = GLOBAL.WEBROOT+'/createprom/view/'+_promId;
	});
	//返回
	$("#btnReturn").click(function(){
		window.history.back();
	});
	
	if(type=='detail'){
		ebcForm.disableAllInput("#shopchkSave");
		$("#btnFormSave").remove();
	}else{
		$("#btnFormSave").click(function(){
			//审核结果
			var _status=$('input:radio[name=status]:checked').val();
			//审核内容
			var _chkDesc=$("#chkDesc").val();
			//没有选择 审核 
			if(_status=='0' || _status=='1'){
			}else{
				eDialog.alert("亲！请选择审核结果。通过或拒绝！");
				return;
			}
			if(_status=='0' ){
				 if(_chkDesc=="" || _chkDesc==null || _chkDesc==undefined){
					 eDialog.alert("亲！拒绝，请输入审核内容！");
						return;
				 }
			}
			 //保存disable
			 $('#btnFormSave').attr('disabled',"true"); 
			var p = ebcForm.formParams($("#shopchkSave"));
			$.eAjax({
			url : GLOBAL.WEBROOT + "/shopchk/verify",
			data : p,
			success : function(returnInfo) {
				
				if(returnInfo.resultFlag!='ok'){
					   $('#btnFormSave').removeAttr("disabled");
					   eDialog.alert("亲！保存失败啦！");
						return;
				}
				eDialog.success('保存成功！',{
					buttons : [{
						caption : '确定',
						callback : function(){
							 var _groupId=$("#groupId").val();
							 if(_groupId=="" || _groupId==null || _groupId==undefined ){
								 //店铺促销审核
								 window.location.href = $webroot + 'shopchk/grid';
							 }else{
								 //主题促销审核
								 window.location.href = $webroot + 'promgroupchk';
							 }
						}
					}],
					show_close_button : false
				});
			}
			});
		});
	}
});
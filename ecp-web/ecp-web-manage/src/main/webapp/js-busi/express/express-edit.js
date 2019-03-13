$(function(){
	$("#btnReturn").click(function(){
		///关闭；
		bDialog.closeCurrent();
	});
	
	$("#btnFormSave").on('click',function(){
		
		if(!$("#detailInfoForm").valid()){
			return false;
		}
		
		saveForm();
	});
	
	var saveForm = function(){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/express/save",
			data : ebcForm.formParams($("#detailInfoForm")),
			success : function(returnInfo) {
				if(returnInfo.resultFlag == "ok"){
					eDialog.alert("物流信息保存成功" , function(){
						bDialog.closeCurrent({"flag":"1"});
					} , "success");
				} else {
					eDialog.alert("物流信息保存失败，失败信息："+returnInfo.resultMsg , $noop , "error");
				}
				 
			}
		});
	}
	
})
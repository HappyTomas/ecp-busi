$(function(){
	$('#btnSave').unbind("click");
	//保存子帐号信息
	$('#btnSave').click(function(){
		if(!$("#subAcctForm").valid()) return false;
		
		//获取角色
		var roleIds = "";
		
        $("input[name='role']").each(function(){ 
            if($(this).attr("checked")){
            	roleIds += $(this).val()+",";
            }
        });
        if (roleIds == '') {
        	eDialog.alert("您好，请给帐号赋予相应的角色。");
        	return;
        }
        $('#roleIds').val(roleIds);
        var val = ebcForm.formParams($("#subAcctForm"));
        $.eAjax({
			url : GLOBAL.WEBROOT + "/seller/shopmgr/subacct/save",
			data : val,
			datatype:'json',
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					window.location.href = GLOBAL.WEBROOT+'/seller/shopmgr/subacctlist';
				} else {
					eDialog.alert(returnInfo.resultMsg);
				}
			}
		});
	});
	
	$('#btnEditSave').unbind("click");
	//编辑子帐号信息-保存
	$('#btnEditSave').click(function(){
		if(!$("#subAcctForm").valid()) return false;
		
		//获取角色
		var roleIds = "";
		
        $("input[name='role']").each(function(){ 
            if($(this).attr("checked")){
            	roleIds += $(this).val()+",";
            }
        });
        if (roleIds == '') {
        	eDialog.alert("您好，请给帐号赋予相应的角色。");
        	return;
        }
        $('#roleIds').val(roleIds);
        var val = ebcForm.formParams($("#subAcctForm"));
        $.eAjax({
			url : GLOBAL.WEBROOT + "/seller/shopmgr/subacct/edit/save",
			data : val,
			datatype:'json',
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					window.location.href = GLOBAL.WEBROOT+'/seller/shopmgr/subacctlist';
				} else {
					eDialog.alert(returnInfo.resultMsg);
				}
			}
		});
	});
	$('#backSaveBtn').unbind("click");
	//返回
	$('#backSaveBtn').click(function(){
		window.history.back();
	});
	$('#backEditBtn').unbind("click");
	//返回
	$('#backEditBtn').click(function(){
		window.history.back();
	});
});
$(function(){
	
	$("#authTypeShow").bootstrapToggle({
		on: '包含',//0
		off: '排他'//1
    });
	$("#authTypeShow").change(function() {
		var checked = $(this).prop('checked');
		$("#authType").val(checked?0:1);
    });
	
	$("#enabledShow").bootstrapToggle({
		on: '是',//1
		off: '否'//0
    });
	$("#enabledShow").change(function() {
		var checked = $(this).prop('checked');
		$("#enabled").val(checked?1:0);
    });
	
	//获得弹出容器对象
	var dlg = bDialog.getDialog();
	var params = bDialog.getDialogParams(dlg);
	//初始化页面状态
	$("#enabledShow").prop('checked', !(params.status=='0')).change();
	$("#authTypeShow").prop('checked', params.authType=='0').change();
	$("#funcId").val(params.funcId);
	$("#sysCode").val(params.sysCode);
	$("#id").val(params.id);
	$("#name").val(params.name);
	$("#authCode").val(params.authCode);
	$("#authDesc").val(params.authDesc);
	$("#authSrc").val(params.authSrc);
	$("#authSrc").prop("disabled", true);//规则来源不可修改
	
	if(params.status=='0'){//如果数据功能为禁用状态，则不可改变规则启用状态即默认为不启用
		$("#enabledShow").prop("disabled", true);
	}
	
	//保存
	$("#confirm").click(function(){
		var _url = GLOBAL.WEBROOT + "/dataauth/dataauth/update";
		//表单数据
		if(!$("#detailInfoForm").valid())return false;
		var _val = ebcForm.formParams($("#detailInfoForm"));
		
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('修改成功！');
		    	}else{
		    		eDialog.error('修改失败！'+remsg); 
		    	}
		    	bDialog.closeCurrent({authSrc:$("#authSrc").val()});
			}
		});
	});
	
	//关闭
	$("#close").click(function(){
		bDialog.closeCurrent();
	});
	
	
});
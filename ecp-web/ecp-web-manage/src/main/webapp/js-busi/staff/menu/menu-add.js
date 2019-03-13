
$(function () { 
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	document.getElementById("parentMenuName").innerHTML = _param.parentName;
	document.getElementById("parentMenuId").value = _param.parentId;
	document.getElementById("sysCode").value = _param.sysCode;
	var menuType = _param.menuType;
//	document.getElementById("parentName").value = _param.praentName;
	
	var _ADD_URL_ = "";
	if(menuType == "0")
	{
		_ADD_URL_ = GLOBAL.WEBROOT + "/menu/addmenudir";
	}
	else
	{
		_ADD_URL_ = GLOBAL.WEBROOT + "/menu/addsubmenu";
	}
    //获取选中选项的值 
    $("#btn_menu_add_save").click(function(){

		if(!$("#menuadddetail").valid()) return false;
		var val = ebcForm.formParams($("#menuadddetail"));
		eDialog.confirm("您确认要保存吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
				
					$.eAjax({
						url : _ADD_URL_,
						data : val,
						datatype:'json',
						success : function(json) {
							if(null != json){
								bDialog.closeCurrent({
									"vo":json
								});
								
								eDialog.alert('success','保存成功！');
									
							}else{

									eDialog.error('创建失败');	
									bDialog.closeCurrent();
							}
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	
	});
    
    $("#btn_menu_add_cancle").click(function(){ 
    	//history.back();
    	bDialog.closeCurrent();
    }); 
    

    
});  
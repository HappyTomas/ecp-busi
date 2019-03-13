
$(function () { 
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	
    //获取选中选项的值 
    $("#btnSave").click(function(){ 
    	//调整积分必须为正整数
		if ($('#score').val() != '') {
			var reg = /^[0-9]*[1-9][0-9]*$/;
		    if (!reg.test($('#score').val())) {
		    	eDialog.alert('调整积分必须为正整数，请修改！');
		        return;
		    } 
		} else {
			eDialog.alert('调整积分不能为空，请修改！');
	        return;
		}
		eDialog.confirm("您确认要进行积分调整操作吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/scoremgr/adjust/save",
						data : {'staffId':$('#staffId').val(),
								'score':$('#score').val(),
								'adjustType':$('input[name="adjust"]:checked').val(),
								'scoreAdjustType':$('#scoreAdjustType').val(),
								'remark':$('#remark').val()
							},
						datatype:'json',
						success : function(returnInfo) {
							//eDialog.alert(returnInfo.resultMsg);
							bDialog.closeCurrent();
						}
					});
					
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	
    });
    
    $("#btnReturn").click(function(){ 
    	bDialog.closeCurrent();
    }); 
    
});  
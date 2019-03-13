
$(function () { 
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	
    //获取选中选项的值 
    $("#btnSave").click(function(){ 
    	if(!$("#msgfrom").valid())return false;
		eDialog.confirm("您确认要进行保存操作吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/msg/mgr/template/save",
						data : {'msgCode':$('#msgCode').val(),
								'msgSiteTemplate':$('#msgSiteTemplate').val(),
								'smsTemplate':$('#smsTemplate').val(),
								'mailTitleTemplate':$('#mailTitleTemplate').val(),
								'mailBodyTemplate':$('#mailBodyTemplate').val()
							},
						datatype:'json',
						success : function(returnInfo) {
							eDialog.alert(returnInfo.resultMsg);
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
    
    //提交后台，改变状态
    var changeStatus = function(flag,msgCode,sendType){
    	//保存数据
    	$.eAjax({
    		url : GLOBAL.WEBROOT + "/msg/mgr/saveMsgSend",
    		data : {'flag':flag,'msgCode':msgCode,'sendType':sendType},
    		datatype:'json',
    		success : function(returnInfo) {
    			
    		}
    	});
    };
    //站内消息，开关：关闭
    $("#site_flag_1").click(function(){
    	$("#site_flag_1").css("display","none");
    	$("#site_flag_0").css("display","block");
    	changeStatus("0",$("#msgCode").val(),"10");
    });
    //站内消息，开关：开启
    $("#site_flag_0").click(function(){
    	$("#site_flag_0").css("display","none");
    	$("#site_flag_1").css("display","block");
    	changeStatus("1",$("#msgCode").val(),"10");
    });
    //手机短信，开关：关闭
    $("#sms_flag_1").click(function(){
    	$("#sms_flag_1").css("display","none");
    	$("#sms_flag_0").css("display","block");
    	changeStatus("0",$("#msgCode").val(),"20");
    });
    //手机短信，开关：开启
    $("#sms_flag_0").click(function(){
    	$("#sms_flag_0").css("display","none");
    	$("#sms_flag_1").css("display","block");
    	changeStatus("1",$("#msgCode").val(),"20");
    });
    //邮件，开关：关闭
    $("#email_flag_1").click(function(){
    	$("#email_flag_1").css("display","none");
    	$("#email_flag_0").css("display","block");
    	changeStatus("0",$("#msgCode").val(),"30");
    });
    //邮件，开关：开启
    $("#email_flag_0").click(function(){
    	$("#email_flag_0").css("display","none");
    	$("#email_flag_1").css("display","block");
    	changeStatus("1",$("#msgCode").val(),"30");
    });
});  
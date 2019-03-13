
$(function(){
    var pInit = function(){
    	var init = function(){
    		//查询按钮
	    	$('#queryBtn').unbind("click");
	    	$('#queryBtn').click(function(){
	    		var staffCode = $('#staffCode').val();
	    	    var status = $('#status').val();
	    	    var shopId = $('#shopId').val();
	    	    window.location.href = GLOBAL.WEBROOT + '/seller/shopmgr/subacctlist?staffCode='+staffCode+'&status='+status+'&shopId='+shopId;
	    	});
	    	
	    	//权限管理
	    	$('#acctRoleMgr').unbind("click");
	    	$('#acctRoleMgr').click(function(){
	    		var staffId = this.name;
	    	});
	    	
	    	//新增
	    	$('#addBtn').unbind("click");
	    	$('#addBtn').click(function(){
	    		window.location.href = GLOBAL.WEBROOT + '/seller/shopmgr/subacct/add?shopId='+$('#shopId').val();
	    	});
	    	$('#resetBtn').unbind("click");
	    	$('#resetBtn').click(function(){
	    		$('#staffCode').val('');
	    		$('#status').val('');
	    		$('#shopId').val($('#defaultShopId').val());
	    	});
	    	
		};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		init : function(){
			var subAcctList = new pInit();
			subAcctList.init();
		}
	});
});

//启用、失效、删除
function statusOpt(type,staffId) {
	var url = "";
	var content = "";
	//启用
	if (type == 'enable') {
		url = "/seller/shopmgr/subacct/enable";
		content = "您确认要启用该帐号吗？";
		//失效
	} else if (type == 'disable') {
		url = "/seller/shopmgr/subacct/disable";
		content = "您确认要失效该帐号吗？";
		//删除
	} else if (type == 'del') {
		url = "/seller/shopmgr/subacct/del";
		content = "您确认要删除该帐号吗？";
	}
	eDialog.confirm(content, {
		buttons : [{
			caption : '确认',
			callback : function(){					
				$.eAjax({
					url : GLOBAL.WEBROOT + url,
					data :  {'staffId':staffId},
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							var staffCode = $('#staffCode').val();
				    	    var status = $('#status').val();
				    	    window.location.href = GLOBAL.WEBROOT + '/seller/shopmgr/subacctlist?staffCode='+staffCode+'&status='+status;
						} else {
							eDialog.alert(returnInfo.resultMsg);
						}
					}
				});
				
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
};
//权限管理
function acctSubEdit(staffId) {
	window.location.href = GLOBAL.WEBROOT + '/seller/shopmgr/subacct/edit?staffId=' + staffId;
}
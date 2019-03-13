$(function(){
	//新增提交
	$('#btnFormAddSubmit').click(function(){
		if(!$("#detailInfoForm").valid())return false;
		var _val = ebcForm.formParams($("#detailInfoForm"));
		$.eAjax({
			url : GLOBAL.WEBROOT + "/authrole/addrole",
			data : _val,
			datatype:'json',
			success : function(data) {
				var reflag = data.resultFlag;//ok  fail
		    	var remsg = data.resultMsg;
		    	if(reflag=="ok"){
		    		eDialog.success('保存成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = 'grid';
					        }
						}]
					});
		    	}else{
		    		eDialog.error('保存失败！'+remsg); 
		    	}
				
			}
		});
	});
	
	//返回
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/authrole/grid';
	});
	
	//子系统select事件
	$('select[name="sysCode"]').on("change", function(){
		var self = $(this);
		//菜单树
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");
		treeObj.setting.async.otherParam = {"sysCode":self.val()};
		treeObj.reAsyncChildNodes(null, "refresh", true);
		
		var treeObj1 = $.fn.zTree.getZTreeObj("treeFunc");
		treeObj1.setting.async.otherParam = {"sysCode":self.val()};
		treeObj1.reAsyncChildNodes(null, "refresh", true);
	});
});
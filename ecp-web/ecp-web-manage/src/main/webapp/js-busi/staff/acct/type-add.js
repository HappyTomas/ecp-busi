$(function(){
	
	//比例输入约束,格式化
	$("input[name='deductOrderRatio']").bind("input propertychange", function(e){
		var self = $(this);
		var val = self.val();
		var ma = val.match(/\d{0,2}\.{1}\d{0,4}|\d{0,2}/g);
		//重置
		if(ma){
			self.val(ma[0]);
		}else{//ma == null
			self.val("");
		}
	});
	
	//新增提交
	$('#btnFormAddSubmit').click(function(){
		if(!$("#acctTypeForm").valid())return false;
		var acctType = $("select[name='acctType']").val();
		var adaptType = $("select[name='adaptType']").val();
		var shopId = $("select[name='shopId']").val();
		var deductOrderRatio = $("input[name='deductOrderRatio']").val();
		eDialog.confirm("是否确定保存？", {
			buttons : [{
				caption : '保存',
				callback : function(){
					$.eAjax({
						url : GLOBAL.WEBROOT + "/acct/type/add",
						data : {'acctType':acctType,'adaptType':adaptType,'shopId':shopId,'deductOrderRatio':deductOrderRatio},
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
				}
			},{
				caption : '返回',
				callback : function(){
					bDialog.closeCurrent();
				}
			}]
		});
		
	});
	
	//返回
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/acct/type/grid';
	});
});
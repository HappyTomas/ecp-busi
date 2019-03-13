$(function(){
	//绑定选择内容
	$('.select_link_gds').unbind("click").bind("click",function(){
		var $this=$(this).parent();
		var siteId = $("#siteId").val();
		var title = "选择商品";
		var	url = "floorgds/openpromgds?siteId="+siteId;
		bDialog.open({
			title : title,
			width : 860,
			height : 500,
			url : $webroot + url,
			callback:function(data){
				if(data && data.results && data.results[0]){
					$("#propValue",$this).val(data.results[0].gdsIds);
					$("#propValueId",$this).val(data.results[0].promIds);
					$("#gdsName",$this).val(data.results[0].gdsNames);
					$("#remark",$this).val(data.results[0].gdsNames);
				}
			}
		});
	});
});
var CommonModularFloor = {
	//对于手动输入表单。输入值的时候对其进行propValue的赋值
		inputAssignMent : function(obj){
			$(obj).parents('.input-append').children("#propValue").val($(obj).val());
		}
};
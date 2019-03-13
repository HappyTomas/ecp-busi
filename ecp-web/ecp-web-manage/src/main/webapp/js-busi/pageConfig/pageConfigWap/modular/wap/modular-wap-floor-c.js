$(function(){
	
});
var CommonModularFloor = {
	//对于手动输入表单。输入值的时候对其进行propValue的赋值
		inputAssignMent : function(obj){
			$(obj).parents('.input-append').children("#propValue").val($(obj).val());
		}
};
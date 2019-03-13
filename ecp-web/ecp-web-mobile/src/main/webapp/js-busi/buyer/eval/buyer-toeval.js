$(function(){
	$("#saveBtn").click(function(){
		var data = ebcForm.formParams($("#evalForm"));
		if($("#cheboxpj").attr('checked')=='checked'){
			data.push({ 'name': 'ifAnonymous','value' :'1' });
		}else{
			data.push({ 'name': 'ifAnonymous','value' :'0' });
		}
		var eval = $('#detail').val();
		if(eval.trim() == ''){
			eDialog.alert("请输入评价内容!");
			return;
		}
		eDialog.confirm("确定提交评价吗？",function(){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/buyereval/save",
				data : data,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=='ok'){
						window.location.href = GLOBAL.WEBROOT + "/buyereval/index";
					}else{
						new AmLoad({content:returnInfo.resultMsg,type:'2'});
					}
				}
			});
      	});
		
	});
	$('.pfen a').click(function(){
        $(this).parent().attr('class','pfen star'+($(this).index()+1));
        $("#score").val($(this).index()+1);

    });
	$("#nmpj").click(function(){
		var $obj = $(this).siblings("#cheboxpj");
		if($obj.attr('checked')=='checked'){
			$obj.removeAttr('checked');
		}else{
			$obj.attr('checked',"checked");
		}
		
	});
});
function textCounter(id,limitNum,showArea) {			
	if   ($("#"+id).val().length  > limitNum)        
		//如果元素区字符数大于最大字符数，按照最大字符数截断；        
		$("#"+id).val($("#"+id).val().substring(0, limitNum));      
	else      
		//在记数区文本框内显示剩余的字符数；        
		$("#"+showArea).text($("#"+id).val().length);     
}
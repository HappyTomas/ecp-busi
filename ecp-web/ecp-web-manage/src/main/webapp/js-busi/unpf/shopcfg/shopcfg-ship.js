$(function(){
$(".cost").click(function(){
		 c.b(this);
	 });
$('.template').click(function(){
	c.d(this);
})
	c.b($("input[id=shipType]:checked"));
	c.d($("input[class=template]:checked"));
});

var c={
		b:function(obj){
			if($(obj).val()=='1'){
				$('#mould').show();
				$('#costs').show();
			}else if($(obj).val()=='2'){
					$('#mould').hide();
					$('#costs').hide();
			}
		},
		d:function(_obj){
			if($(_obj).val()=='postage'){
				$('#post').attr('disabled',"true");
				$('#express').attr('disabled',"true");
				$('#ems').attr('disabled',"true");
				$("#post").val("");
				$("#express").val("");
				$("#ems").val("");
				$('#templateId').removeAttr("disabled");
			}else if($(_obj).val()=='freight_details'){
					$('#templateId').attr('disabled',"true");
					$("#templateId").val("");
					$('#post').removeAttr("disabled");
					$('#express').removeAttr("disabled");
					$('#ems').removeAttr("disabled");
			}
		}
			
		
};
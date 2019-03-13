$(function(){
	param();
	$(".cost").click(function(){
		c.b(this);
	});
	$('.template').click(function(){
		c.d(this);
	})
	c.b($("input[id=shipType]:checked"));
	c.d($("input[class=template]:checked"));
	$('#btnSubmit').click(function() {
		$('#btnSubmit').attr('disabled',"true");
		if(!$("#shopsubmitform").valid()){
			$('#btnSubmit').removeAttr("disabled");
			return false;
		}
		if($("input[id=shipType]:checked").val() == "2"){
			$('#mould').parent().find('#mould').remove();
			$('#costs').parent().find('#costs').remove();
		}
		$.eAjax({
			url : GLOBAL.WEBROOT + "/shopcfg/submit/"+$("#shopCfgId").val(),
			data : ebcForm.formParams($("#shopsubmitform")),
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('提交成功！', {
						buttons : [ {
							caption : "确定",
							callback : function() {
								window.location.href = GLOBAL.WEBROOT+'/platauth/sync/'+$("#shopAuthId").val();
							}
						} ]
					});
				} else {
					eDialog.alert(returnInfo.resultMsg);
					$('#btnSubmit').removeAttr("disabled");
				}
			}
		});
		$('#btnSubmit').removeAttr("disabled");
	});
});
function param(){
	$("#div1").load( GLOBAL.WEBROOT+"/shopcfg/girdlist",{"shopAuthId":$("#shopAuthId").val(),"platType":$("#platType").val(),"shopId":$("#shopId").val()});
}
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
			$('#templateId').removeAttr("disabled");
		}else if($(_obj).val()=='freight_details'){
			$('#templateId').attr('disabled',"true");
			$('#post').removeAttr("disabled");
			$('#express').removeAttr("disabled");
			$('#ems').removeAttr("disabled");
		}
	},

};
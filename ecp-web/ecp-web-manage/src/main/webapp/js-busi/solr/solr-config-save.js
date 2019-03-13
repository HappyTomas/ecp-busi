$(function(){
	if($('#isRead').size()>0&&$('#isRead').val()=='1'){
		ebcForm.setFormReadonly('#promInfoForm');
	}
	if($('#ifMultilan').is(":checked")){
		$('.ifMultilanBlock').show();
	}else{
		$('.ifMultilanBlock').hide();
	}
	$('#ifMultilan').click(function(){
		$('.ifMultilanBlock').toggle();
	});
	//保存按钮
	$("#btnFormSave").click(function(){
		if(!$("#promInfoForm").valid())return false;
		SolrConfigAdd.addSolrConfig(this);
	});
	$("#btnReturn").click(function(){
		window.location.href = GLOBAL.WEBROOT + "/solrconfig";
	});
	if($('#queryIfHl').is(":checked")){
		$('.hiblock').show();
	}else{
		$('.hiblock').hide();
	}
	if($('#spellcheck').is(":checked")){
		$('.spellcountblock').show();
	}else{
		$('.spellcountblock').hide();
	}
	$('#queryIfHl').click(function(){
		$('.hiblock').toggle();
	});
	$('#spellcheck').click(function(){
		$('.spellcountblock').toggle();
	})
});
var SolrConfigAdd = {
		addSolrConfig : function(obj){
			var btn = $(obj);
			btn.button('loading');//设置按钮为处理状态，并为中读，不允许点击
			var ck=$('#spellcheck');
			if(ck.is(":checked")){
				ck.val('1');
			}
			var ck1=$('#queryIfHl');
			if(ck1.is(":checked")){
				ck1.val('1');
			}
			var ck2=$('#ifMultilan');
			if(ck2.is(":checked")){
				ck2.val('1');
			}
			var url=GLOBAL.WEBROOT + "/solrconfig/";
			if($('#formId').size()>0&&$('#formId').val().trim()){
				//编辑
				url+='eidtsolrconfig';
			}else{
				//添加
				url+='addsolrconfig';
			}
			var params = ebcForm.formParams($("#promInfoForm"));
			$.eAjax({
				url : url,
				data : params,
				datatype:'json',
				success : function(returnInfo) {
					$.gridUnLoading();
					if(returnInfo.resultFlag=='ok'){
						//window.location.href = GLOBAL.WEBROOT + "/solrconfig";
						eDialog.success(returnInfo.resultMsg, {
							buttons : [{
								caption : "确定",
								callback : function() {
									window.location.href = GLOBAL.WEBROOT + "/solrconfig";
								}
							}]
						});
					}else{
						eDialog.error(returnInfo.resultMsg);
					}
				
				},
				exception : function() {
					btn.button('reset');
				}
			});
		}
}; 
$(function(){
	$('#allSelect').click(function(){
		if($(this).is(":checked")){
			$('.label-list label').each(function(){
				$(this).find('.ck').prop("checked",true);
			});
		}else{
			$('.label-list label').each(function(){
				$(this).find('.ck').prop("checked",false);
			});
		}
	})
	var applyItemSize=$('#applyItemSize').val();
	var ids=applyItemSize.split('|');
	for(var i=0;i<ids.length;i++){
		$('.label-list label').each(function(){
			if($(this).find('.txt').text()==ids[i]){
				$(this).click();
			}
		});
	}
	//关闭弹框
	$('#closeDialog').live("click",function(){ 
		 var checkstr="",checkId="";
		$('.label-list label').each(function(){
			var $this=$(this),inputDom=$this.find('input');
			if(inputDom.is(":checked")){
				checkstr+="|"+$this.find('.txt').text();
			}
		});
		if(checkstr!=""){
			checkstr+="|";
		}
		bDialog.closeCurrent({
			"applyPageTypeIds":checkstr,
		});
	});
	
});


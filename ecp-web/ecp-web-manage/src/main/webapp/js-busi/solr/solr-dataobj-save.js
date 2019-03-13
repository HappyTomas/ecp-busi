$(function(){
	if($('#isRead').size()>0&&$('#isRead').val()=='1'){
		ebcForm.setFormReadonly('#promInfoForm');
		ebcForm.setFormReadonly('#dataTbFom');
	}
    $('#btnAddSave').click(function(){
    	if(!$("#promInfoForm").valid())return false;
    	if(!$("#dataTbFom").valid())return false;
    	SolrObjecMrg.addObject(this);
    });
	$('#btnReturn').click(function(){
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj?id="+$('#configId').val();
		e.preventDefault();
	});
	if($('#insidepager').is(":checked")){
		$('.insidepagerBlock').show();
	}else{
		$('.insidepagerBlock').hide();
	}
	$('#insidepager').click(function(){
		$('.insidepagerBlock').toggle();
	})
	
     $('#dataDel').click(function () {
        var checks = $('.solr-table .sel-row:checked');
        if (checks.size() > 0) {
			eDialog.confirm("确认删除选中索引字段吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						 checks.each(function () {
				                $(this).parents('tr').remove();
				        });
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
        } else {
        	eDialog.alert('请选择删除行');
        }
    });
     $('.edit-sel select').live('change',function(){
        $(this).parents('.edit-sel').find('.edit-input').val($(this).val());
    })
	
	var SolrObjecMrg={
		addObject:function(obj){
			var btn = $(obj);
			btn.button('loading');//设置按钮为处理状态，并为中读，不允许点击
			$.gridLoading({
				"messsage" : "正在操作中...."
			});
			var params={};
			$('#promInfoForm input[type="checkbox"]').each(function(){
				if($(this).is(":checked")){
					$(this).val('1');
				}
			})
			var tbParams= ebcForm.formParams($("#promInfoForm"));
			params=tbParams;
			var _valStr='[';
			$('.solr-table .tbcheck').each(function(){
				$(this).val('0');
				if($(this).is(":checked")){
					$(this).val('1');
				}
			})
			$('.solr-table tbody tr').each(function(item){
				var param=ebcForm.formParams(this);
				_valStr += "{";
			    $.each(param,function(item,map){
			    	_valStr += "'"+map.name.split('_')[0]+"':'"+map.value+"',";
			    });
				_valStr += "},";
			});
			_valStr += "]";
			params.push({name:'fieldStr',value:_valStr});
			$.eAjax({
				url : GLOBAL.WEBROOT + "/solrdataobj/saveobj",
				data : params,
				datatype:'json',
				success : function(returnInfo) {
					$.gridUnLoading();
					if(returnInfo.resultFlag=='ok'){
						eDialog.success(returnInfo.resultMsg, {
							buttons : [{
								caption : "确定",
								callback : function() {
									window.location.href = GLOBAL.WEBROOT + "/solrdataobj?id="+$('#configId').val();
								}
							}]
						});
					}else{
						eDialog.error(returnInfo.resultMsg);
					}
				
				},
				exception : function() {
					$.gridUnLoading();
					btn.button('reset');
				}
			});
		}
	};
});

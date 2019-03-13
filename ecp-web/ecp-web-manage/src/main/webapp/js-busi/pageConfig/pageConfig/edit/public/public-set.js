/**
 * 
 * 动态表单处理
 * 
 * @author Terry
 * 
 */
$(function(){
	$('#btnCancel').click(function(){
		bDialog.closeCurrent();
	});
	var params = {
		modularId : $('#publicModularId').val(),
		itemId : $('#publicItemId').val(),
		pageId : $('#publicPageId').val()
	};
	//读取动态表单项目放入页面
	$.eAjax({
		url : $webroot + "paramsrender/init",
		data : params,
		dataType : 'text',
		success : function(returnInfo){
			if(returnInfo){
				$('#paramsSetForm').html(returnInfo);
				//获得布局项属性设置数据后的回调处理（将数据回填到表单中）
				var callback = function(data){
					if(data && $.type(data.props) && data.props.length > 0){
						var form = $('form.templateItemForms');
						var manualList = cmsTemplate.dynamicPropValueSet(data);
					}
				};
				//读取布局项属性设置数据
				cmsTemplate.loadFormSet(callback);
			}
		}
	});
	var modularType=$('#modularType').val();
	//保存表单
	$('#btnSave').click(function(){
		cmsTemplate.formDataFill();//处理表单数据，将输入控件的内容填入隐藏域
		var itemsData = cmsTemplate.getFormData();
		var url = $('form.templateItemForms').data('url');
		if(itemsData && $.isArray(itemsData) && itemsData.length > 0 && url){
			var submitData = {'forms' : itemsData};
			$.eAjax({
				url : url,
				data : ebcUtils.serializeObject(submitData),
				success : function(returnInfo){
					if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
						eDialog.alert('模块设置成功！',function(){
							if('01'==modularType){
								parent.location.reload(); 
							}else{
								bDialog.closeCurrent();
							}
						},'confirmation');
					}
				}
			});
		}
	});
});
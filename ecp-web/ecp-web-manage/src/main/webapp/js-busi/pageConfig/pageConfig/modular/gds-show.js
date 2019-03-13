/**
 * 
 * 宝贝图片编辑界面
 * 
 * @author gongxq
 * 
 */
$(function(){
//	//是否初始化完成开关
//	var init = false;
//	//获得当前弹出窗口对象
//	var _dlg = bDialog.getDialog();
//	//获得父窗口传递给弹出窗口的参数集
//	var _param = bDialog.getDialogParams(_dlg);
//	//获得父窗口设置的回调函数
//	var _callback = bDialog.getDialogCallback(_dlg);
//	
	//保存表单
//	$('#btnSave').click(function(){
//		if(!$("#paramsSetForm").valid())return false;
//		//获取公用参数
//		var itemsData = CommonModular.getFormDataForCommon();
//		var url = $('form.templateItemForms').data('url');
//		if(itemsData && $.isArray(itemsData) && itemsData.length > 0 && url){
//			var submitData = {'forms' : itemsData};
//			$.eAjax({
//				url : url,
//				data : ebcUtils.serializeObject(submitData),
//				success : function(returnInfo){
//					if(returnInfo && $.type(returnInfo.resultFlag)!='undefined' && returnInfo.resultFlag == 'ok'){
//						eDialog.alert('宝贝图片内容设置成功！',function(){
//							bDialog.closeCurrent();
//						},'confirmation');
//					}
//				}
//			});
//		}
//	});
});
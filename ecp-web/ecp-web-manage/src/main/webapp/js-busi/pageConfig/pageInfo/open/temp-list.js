//页面初始化模块
$(function(){
	var params = {
		"pageId":$("#list-pageId").val()||"",
		"siteId":$("#list-siteId").val()||"",
		"platformType":$("#list-platformType").val()||"",
		"shopId":$("#list-shopId").val()||"",
		"pageTypeId":$("#list-pageTypeId").val()||"",
		"templateType":$("#list-templateType").val()||"",
	}
	$("input[type=hidden]").remove();
	if(params.pageTypeId && params.templateType){
		$('#temp-page-controlbar').bPage({
    	    url : GLOBAL.WEBROOT + '/templateLib/getTempList',
    	    asyncLoad : true,
    	    asyncTarget : '#template-lib-list',
    	    params : function(){
    	    	return params;
    	    }
    	});
	}
});    	

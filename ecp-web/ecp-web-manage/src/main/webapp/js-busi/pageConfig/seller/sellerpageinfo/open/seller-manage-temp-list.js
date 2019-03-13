//页面初始化模块
$(function(){
	//debugger;
	var pageTypeId = $("#pageTypeId").val();
	var templateType = $("#template-type-tab li[class=active]").attr("data-temp-type");
	if(pageTypeId && templateType){
		$('#temp-page-controlbar').bPage({
    	    url : GLOBAL.WEBROOT + '/templateLib/getTempList',
    	    asyncLoad : true,
    	    asyncTarget : '#template-lib-list',
    	    params : function(){
    	    	return {
    	    		pageTypeId:pageTypeId,
    	    		templateType:templateType
    	    	}
    	    }
    	});
	}
});    	

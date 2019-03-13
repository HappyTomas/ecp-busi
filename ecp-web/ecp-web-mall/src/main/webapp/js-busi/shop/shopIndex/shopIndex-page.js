$(function() {
	$('#pageControlbar').bPage({
		url : $webroot+"/shopIndex/page",
		asyncLoad : true,
		asyncTarget : '#pageMainBox',
		//pageSize:16,
		pageSizeMenu: [16,24,32,40],
		params : function(){
	    	return {
	    		  "shopId":$('#shopId').val(),
			      "field":shopIndex.getField(),
			      "sort":shopIndex.getSort()
		    };
	    }
	});
	
	//$("#searchCategory").val($("#changeOnceCategory").val());
});
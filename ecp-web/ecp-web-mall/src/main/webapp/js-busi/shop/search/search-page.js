$(function() {
	$('#shopGdsPageControlbar').bPage({
		url : $webroot+"/shopgdssearch/page",
		asyncLoad : true,
		asyncTarget : '#shopGdsPageMainBox',
		//pageSize:16,
		pageSizeMenu: [16,24,32,40],
		params : function(){
	    	return {
		        "showType":$('#showType').val(),
				"keyword":$('#searchingTxt').val(),
				"shopId":$('#shopId').val(),
				'category':$filterCategory.getCategoryId(),
				'propertyGroup':$propertyGroup.getPropertyGroup(),
				'searchmore':$('#searchingInResultTxt').val(),
				'field':$sortField.getSortField(),
				'sort':$fieldSort.getFieldSort(),
				'priceStart':$('#priceStart').attr('realPriceStart'),
				'priceEnd':$('#priceEnd').attr('realPriceEnd')
		    };
	    }
	});
	$("#searchCategory").val($("#changeOnceCategory").val());
});
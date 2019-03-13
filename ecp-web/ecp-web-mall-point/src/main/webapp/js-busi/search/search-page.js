$(function() {
	$('#pageControlbar').bPage({
		url : $webroot+"/search/page",
		asyncLoad : true,
		asyncTarget : '#pageMainBox',
		//pageSize:16,
		pageSizeMenu: [16,24,32,40],
		params : function(){
	    	return {
				"keyword":$('#searchingTxt').val(),
				'category':$('#searchCategory').val(),
				'field':'score',
				'sort':$('.score-sort').attr('sort'),
				'rangeType':$('.score-range').attr('_type'),
				'adid':$('#_adid').val()
		    };
	    }
	});
	
});
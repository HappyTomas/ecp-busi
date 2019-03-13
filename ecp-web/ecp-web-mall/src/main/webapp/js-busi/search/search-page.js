$(function() {
	var senior=$("#senior_sign").val();
	var propertyGroup;
	var p_keyword;
	var category;
	var price_start;
	var price_end;
	if(senior=="true"){
		var ifSenior=true;
		var keyword = $("#keyword").val();
		if(keyword!=null){
			p_keyword=keyword;
		}
		var binding="";//装帧 1016
		$("input[name='binding']:checked").each(function(){
			binding+="装帧_"+$(this).val()+",";
			});
		if(binding!=""){
			binding=binding.substr(0,binding.length-1);
			
		}
		if($('#priceStart_senior').val()!=null){
			price_start_temp=$('#priceStart_senior').val();
		}
		if($('#priceEnd_senior').val()!=null){
			price_end_temp=$('#priceEnd_senior').val();
		}
		if(price_start_temp-price_end_temp>0){
			price_start=price_end_temp;
			price_end=price_start_temp;
			
		}else{
			price_start=price_start_temp;
			price_end=price_end_temp;
		}
		var storage="";//库存状态
		$("input[name='saveStatue']:checked").each(function(){
			if($(this).val()==1) storage="1";
			});
		category = $("#platCatgs").val();//$filterCategory.getCategoryId();
		if($("#publicationDateStart").val()!=null){
			var time_begin = (new Date($("#publicationDateStart").val())).getTime();
		}
		if($("#publicationDateEnd").val()!=null){
			var time_end = (new Date($("#publicationDateEnd").val())).getTime();
		}
		if(time_begin>time_end){
			var publicationDateStart = $("#publicationDateEnd").val();
			var publicationDateEnd = $("#publicationDateStart").val();
		}else{
			var publicationDateStart = $("#publicationDateStart").val();
			var publicationDateEnd = $("#publicationDateEnd").val();
		}
	}else{
		var ifSenior=false;
		propertyGroup=$propertyGroup.getPropertyGroup();
		p_keyword=$('#searchingTxt').val();
		category = $filterCategory.getCategoryId();
		price_start = $('#priceStart').attr('realPriceStart');
		price_end = $('#priceEnd').attr('realPriceEnd');
//		propertyGroup="[,{\"propertyId\":\"priceCode\",\"propertyValueIds\":\"1\"},]";
	}
	var category_temp=$("#pageUpdate").attr("value");
	if(category_temp!=""){
		category=category_temp
	}
	$('#pageControlbar').bPage({
		url : $webroot+"/search/page",
		asyncLoad : true,
		asyncTarget : '#pageMainBox',
		//pageSize:16,
		pageSizeMenu: [16,24,32,40],
		params : function(){
	    	return {
//		        "showType":$('#showType').val(),
//				"keyword":$('#searchingTxt').val(),
//				'category':$filterCategory.getCategoryId(),
//				'propertyGroup':$propertyGroup.getPropertyGroup(),
//				'searchmore':$('#searchingInResultTxt').val(),
//				'field':$sortField.getSortField(),
//				'sort':$fieldSort.getFieldSort(),
//				'shopId':$('#shopId').val(),
//				'priceStart':$('#priceStart').attr('realPriceStart'),
//				'priceEnd':$('#priceEnd').attr('realPriceEnd'),
//				'adid':$('#_adid').val()
	    		
	    		"showType":$('#showType').val(),
				"keyword":p_keyword,//$('#searchingTxt').val(),
				'category':category,//$filterCategory.getCategoryId(),
				'propertyGroup':$propertyGroup.getPropertyGroup(),//$propertyGroup.getPropertyGroup()
				'searchmore':$('#searchingInResultTxt').val(),
				'field':$sortField.getSortField(),
				'sort':$fieldSort.getFieldSort(),
				'shopId':$('#shopId').val(),
				'priceStart':price_start,//$('#priceStart').attr('realPriceStart'),
				'priceEnd':price_end,//$('#priceEnd').attr('realPriceEnd'),
				'adid':$('#_adid').val(),
				//高级搜索条件
				'senior':ifSenior,
				'bookName':$("#name_value").val(),
				'author':$("#author_value").val(),
				'isbn'  :$("#isbn_value").val(),
				'binding':binding,
				'ifStorage':storage,
				'publicationDateStart':publicationDateStart,//$("#publicationDateStart").val(),
				'publicationDateEnd':publicationDateEnd,//$("#publicationDateEnd").val()
		    };
	    },
	    callback: function(){
	    	location.href = "#carGus";//刷新界面定为到页面最顶端 
	    }
	});
	$("#searchCategory").val($("#changeOnceCategory").val());
});
//搜索结果列表渲染
$gdsListRefresh=function(){
	var refresh = function(opts,code){
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
			if($("#publicationDateStart").val()!=null && $("#publicationDateStart").val()!=""){
				var time_begin = (new Date($("#publicationDateStart").val())).getTime();
			}else{
				var time_begin="";
			}
			if($("#publicationDateEnd").val()!=null && $("#publicationDateEnd").val()!=""){
				var time_end = (new Date($("#publicationDateEnd").val())).getTime();
			}else{
				var time_end="";
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
//			propertyGroup="[,{\"propertyId\":\"priceCode\",\"propertyValueIds\":\"1\"},]";
		}
		if(code=="undefined" || isNaN(code) || code==null){
		}else{
			category=code;
		}
//		category=code;

		//刷新分页栏和列表
		$('#pageControlbar').bPageRefresh({
			asyncLoad : true,
			asyncTarget : '#pageMainBox',
			async : false ,
			pageNumber:opts.pageNumber,//pageNumber必须指定
			//pageSize:16,
			//pageSizeMenu: [16,20,24,28],
			params : function(){
		    	return {
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
					'publicationDateEnd':publicationDateEnd//$("#publicationDateEnd").val()
			    };
		    }
		});
		
		try {
			var total=$('#searchTotal').val();
			$('#numFound').html(total);
		} catch (e) {
			
		}
	
	}
	
	return {
		"refresh" : refresh
	};

}();

//下钻属性刷新页面
$gdsListRefresh_down=function(){
	var refresh_down = function(opts,code){
		var	propertyGroup=$propertyGroup.getPropertyGroup();
		var	p_keyword=$('#searchingTxt').val();
		var	category = code;
		var	price_start = $('#priceStart').attr('realPriceStart');
		var	price_end = $('#priceEnd').attr('realPriceEnd');
//			propertyGroup="[,{\"propertyId\":\"priceCode\",\"propertyValueIds\":\"1\"},]";

		//刷新分页栏和列表
		$('#pageControlbar').bPageRefresh({
			asyncLoad : true,
			asyncTarget : '#pageMainBox',
			async : false ,
			pageNumber:opts.pageNumber,//pageNumber必须指定
			//pageSize:16,
			//pageSizeMenu: [16,20,24,28],
			params : function(){
		    	return {
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
					'adid':$('#_adid').val()
			    };
		    }
		});
		
		try {
			var total=$('#searchTotal').val();
			$('#numFound').html(total);
		} catch (e) {
			
		}
	
	}
	
	return {
		"refresh_down" : refresh_down
	};

}();


//高级搜索结果列表渲染
$gdsListRefresh_senior=function(){
	var refresh_senior = function(opts,CatgCode){
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
			if($("#publicationDateStart").val()!=null && $("#publicationDateStart").val()!=""){
				var time_begin = (new Date($("#publicationDateStart").val())).getTime();
			}else{
				var time_begin="";
			}
			if($("#publicationDateEnd").val()!=null && $("#publicationDateEnd").val()!=""){
				var time_end = (new Date($("#publicationDateEnd").val())).getTime();
			}else{
				var time_end="";
			}
			if(time_begin>time_end){
				var publicationDateStart = $("#publicationDateEnd").val();
				var publicationDateEnd = $("#publicationDateStart").val();
			}else{
				var publicationDateStart = $("#publicationDateStart").val();
				var publicationDateEnd = $("#publicationDateEnd").val();
			}
		}

		//刷新分页栏和列表
		$('#pageControlbar').bPageRefresh({
			asyncLoad : true,
			asyncTarget : '#pageMainBox',
			async : false ,
			pageNumber:opts.pageNumber,//pageNumber必须指定
			//pageSize:16,
			//pageSizeMenu: [16,20,24,28],
			params : function(){
		    	return {
			        "showType":$('#showType').val(),
					"keyword":p_keyword,//$('#searchingTxt').val(),
					'category':CatgCode,//$filterCategory.getCategoryId(),
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
					'publicationDateEnd':publicationDateEnd//$("#publicationDateEnd").val()
			    };
		    }
		});
		
		try {
			var total=$('#searchTotal').val();
			$('#numFound').html(total);
		} catch (e) {
			
		}
	
	}
	
	return {
		"refresh_senior" : refresh_senior
	};

}();

//获取过滤分类
$filterCategory=function(){
	
	var getCategoryId = function(){
		//开始过滤
		if($('.cateList .fackCateSelected').length<=0){
			if($('.cateList .cateSelected').length>0){
				return $('.cateList .cateSelected').attr("code");
			}
		}
		//先判断是否是分类搜索
		if($('#searchCategory').val()){
			return $('#searchCategory').val();
		}
		
		return "";
	};
	
	return {
		"getCategoryId" : getCategoryId
	};
	
}();

//分类属性渲染
$propRender = function(){
	var render = function(opts){
		
		//清空属性列表
		var $propContainer = $("#search-xz");
		$propContainer.empty();
		//分类属性写死
		var catepor=
		"<div class=\"sl-line\" id=\"slArrow\">"+
	        "<div class=\"sl-wrap\">"+
	            "<label class=\"sl-key\" code=\"priceCode\">"+
	                "分类属性："+
	            "</label>"+
	            "<div class=\"sl-val sl-arrow sl-litem\" id=\"proCategory\">"+
					"<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"1\"><i class=\"s-check\"></i></a>"+
	                "</span>"+
	            "</div>"+
	            "<span class=\"arrow-bottom\">更多</span>"+
	        "</div>"+
	    "</div>";
		$propContainer.append(catepor);
		if(opts.propList){
			
			var propList=opts.propList;

			//加载属性列表
			if(propList!=null&&propList.length>0){
				for(var index in propList){
					
		            var v=propList[index];
		            if(v.values!=null&&v.values.length>0){
		            	
		            	var height=(Math.floor(v.values.length/6)+1)*40;
		            	
		            	var element="<div class=\"sl-line\">"+
				                    "<div class=\"sl-wrap\">"+
				                        "<label class=\"sl-key\" code=\""+v.id+"\" style=\"height:"+height+"px;\">"+
				                            v.propName+"："+
				                        "</label>"+
				                        "<div class=\"sl-val\">";
		            	
		            	for(var k in v.values){
	                        element=element+
	                        		"<span>"+
				                        "<a href=\"#\" code=\""+v.values[k].id+"\" class=\"proptype\"><i class=\"s-check\"></i>"+v.values[k].propValue+"</a>"+
				                    "</span>";
			            }
			            
					            element=element+
					            	   "</div>"+
				                    "</div>"+
				                "</div>";
				                
				        $propContainer.append(element);
		            	
		            }
				}
			}
			
		}
		
		//价格属性写死
		var priceEle=
		"<div class=\"sl-line\" id=\"priceSign\">"+
	        "<div class=\"sl-wrap\">"+
	            "<label class=\"sl-key\" code=\"priceCode\">"+
	                "价格："+
	            "</label>"+
	            "<div class=\"sl-val\">"+
					"<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"1\"><i class=\"s-check\"></i>0-89 </a>"+
	                "</span>"+
	                "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"2\"><i class=\"s-check\"></i>90-199</a>"+
	                "</span>"+
	                 "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"3\"><i class=\"s-check\"></i>200-299</a>"+
	                "</span>"+
	                 "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"4\"><i class=\"s-check\"></i>300-399</a>"+
	                "</span>"+
	                "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"5\"><i class=\"s-check\"></i>400以上</a>"+
	                "</span>"+
	            "</div>"+
	            "<div class=\"sl-price\">"+
	                "<input class=\"itxt\" title=\"最低价\" maxlength=\"6\" id=\"priceStart\" realPriceStart=\"\""+
	                       "onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');\">"+
	                "<em> - </em>"+
	                "<input class=\"itxt\" title=\"最高价\" maxlength=\"8\" id=\"priceEnd\" realPriceEnd=\"\""+
	                       "onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');\">"+
	                "<a class=\"mbtn\" id=\"priceBtn\">确定</a>"+
	            "</div>"+
	        "</div>"+
	    "</div>";
		$propContainer.append(priceEle);
	}
		
	return {
		"render" : render
	};
}();

//获取排序字段
$sortField=function(){
	
	var getSortField = function(){
		var selection=$('#sl-order').children('.selected')[0];	
		return $(selection).attr("id");
	}
	
	return {
		"getSortField" : getSortField
	};
	
}();

//获取排序规则
$fieldSort=function(){
	
	var getFieldSort = function(){
		var selection=$('#sl-order').children('.selected')[0];	
		if($($(selection).children()[0]).hasClass('micon-down')){
			return "desc";
		}else{
			return "asc";
		}
	}
	
	return {
		"getFieldSort" : getFieldSort
	};
	
}();

//获取过滤属性值
$propertyGroup=function(){
	var getPropertyGroup = function(){
		var propertyGroup="[";
		var propArr=$("#search-xz > div.sl-line");
		if(propArr!=null&&propArr.length>0){
			var i=0;
			for(;i<propArr.length;i++){
				var prop=propArr[i];
				var liArr=$(prop).find('a.selected');
				if(liArr!=null&&liArr.length>0){
					if(i==0){
						propertyGroup+="{";
					}else{
						propertyGroup+=",{";
					}
					propertyGroup+="\"propertyId\":\""+$(prop).find('label.sl-key').attr("code");
					propertyGroup+="\",\"propertyValueIds\":\""+$(liArr[0]).attr("code");
					propertyGroup+="\"}";
				}
			}
		}
		
		propertyGroup+="]";
		return propertyGroup;
	}
	
	return {
		"getPropertyGroup" : getPropertyGroup
	};
}();


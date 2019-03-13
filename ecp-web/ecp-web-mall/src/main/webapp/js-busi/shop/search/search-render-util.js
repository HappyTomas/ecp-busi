//搜索结果列表渲染
$shopGdsListRefresh=function(){
	
	var refresh = function(opts){
		
		//刷新分页栏和列表
		$('#shopGdsPageControlbar').bPageRefresh({
			asyncLoad : true,
			asyncTarget : '#shopGdsPageMainBox',
			async : false ,
			pageNumber:opts.pageNumber,//pageNumber必须指定
			//pageSize:16,
			//pageSizeMenu: [16,20,24,28],
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
				                        "<a href=\"#\" code=\""+v.values[k].id+"\" class=\"proptype\">"+v.values[k].propValue+"</a>"+
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
		"<div class=\"sl-line\">"+
	        "<div class=\"sl-wrap\">"+
	            "<label class=\"sl-key\" code=\"priceCode\">"+
	                "价格："+
	            "</label>"+
	            "<div class=\"sl-val\">"+
					"<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"1\">0-89 </a>"+
	                "</span>"+
	                "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"2\">90-199</a>"+
	                "</span>"+
	                 "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"3\">200-299</a>"+
	                "</span>"+
	                 "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"4\">300-399</a>"+
	                "</span>"+
	                "<span>"+
	                    "<a href=\"#\" class=\"proptype\" code=\"5\">400以上</a>"+
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
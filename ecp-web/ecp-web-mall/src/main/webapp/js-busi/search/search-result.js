$(function(){
	$('#colShop').click(function () {
		if($('#isLogin').val() != 'true'){
			window.location.href = GLOBAL.WEBROOT+'/login';
			return;
		}
		var isCollect=$('#isCollect').val();
		var url=GLOBAL.WEBROOT + "/shopIndex/collectShop/"+$('#shopId').val()
		if(isCollect== 'true'){
			url=GLOBAL.WEBROOT + "/shopIndex/deleteShop/"+$('#shopId').val()
		}
		$.eAjax({
			url :url ,
			data : {},
			async : false,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=="ok"){
					eDialog.success(returnInfo.resultMsg);
				}
				if(isCollect== 'true'){
					$('#isCollect').val('false');
					$('#colShop').html("收藏店铺");
				}else{
					$('#isCollect').val('true');
					$('#colShop').html("取消店铺收藏");
				}
			},
			exception : function() {
				eDialog.error(returnInfo.resultMsg);
			}
		});
  	});
	
	//搜索结果分类顶级分类单击事件绑定
//	$('#ct-nav .it').live("click",function(){
//        $(this).parent().toggleClass('drawdown');
//    });
	 $('#ct-nav .it .micon').click(function(){
		 $(this).parents('.item').toggleClass('drawdown');
	 })
	$('#slArrow .arrow-bottom').click(function () {
	   var slArrowHeight = $(this).parents('.sl-wrap').find('.sl-val');
	   slArrowHeight.toggleClass('sl-arrow')
	
	})
    
    //搜索结果叶子分类点击事件
	$('.leafCate').children('li').each(function () {
		var li=$(this);

		$(li).live("click",function(){
//			$("#leadCat").children("li").each(function(index){
//				$(this).children('span').show();
//				$(this).show();
//
//			});
//			//去除之前选中标识
//			$('.leafCate').children('li').removeClass('cateSelected');
//			$('.leafCate').children('li').removeClass('fackCateSelected');
//			//添加分类选中标识
//			$(this).addClass('cateSelected');
////
//			//更新分类面包屑
//			var parent=$(this).parents('.item');
//			var $this = $(this);
//			$("a[id='topCateA']").each(function(index,domEle){
//				if(0 == index){
//					$(domEle).html($(parent).children().children('span').html());
//				}else if(1 == index){
//					$(domEle).html($this.attr("_value"));
//				}
//			});
//			
//			//$("#topCateA").html($(parent).children().children('span').html());
//
//			$("#mainCateA").attr("code",$(this).attr("code"));
//			$("#mainCateA").attr("_value",$(this).attr("_value"));
//			$("#mainCateA").html($(this).attr("_value")+"<i class=\"ci-right\"><s>◇</s></i>");
//
//			$('#downc').empty();
//			var children=$(this).parent().children();
//			var i=0;
//			for(;i<children.length;i++){
//				var child=children[i];
//				if($(child).attr("code")!=$(this).attr("code")){
//					$('#downc').append("<a href=\"#\" code=\""+$(child).attr("code")+"\" class=\"downc-cate\">"+$(child).attr("_value")+"</a>");
//				}
//			}
//
//			//清空在结果中搜
//			$('#searchingInResultTxt').val("");
//			console.log('===='+$(this));
//			//刷新属性
//			$.eAjax({
//				url : GLOBAL.WEBROOT + "/search/category",
//
//				data : {'category':$(this).attr("code")},
//				success : function(json) {
//
//					//属性和属性值列表处理
//					$propRender.render({"propList":json.propList});
//
//					//刷新分页，分页刷新需放在属性刷新后
//					$gdsListRefresh.refresh({"pageNumber":1});
//
//				},
//				error : function(e,xhr,opt){
//					//eDialog.alert("属性值列表数据遇到异常!");
//				}
//			});
			
			//--------------------------------
			var ifSenior = $("#rootCate").attr("senior");
			 //if(ifSenior=="true"){
				 var code = $(this).attr("code");
				 downCateData(code);
				 //downData(code);
				 var storage="";//库存状态
				 $("input[name='saveStatue']:checked").each(function(){
					if($(this).val()==1) storage="1";
				 });
				 var binding="";//装帧 1016
				$("input[name='binding']:checked").each(function(){
					binding+="装帧_"+$(this).val()+",";
					});
				if(binding!=""){
					binding=binding.substr(0,binding.length-1);
					
				}
				//获取选中分类的分类属性
					$.eAjax({
						url : GLOBAL.WEBROOT + "/search/getCurrentcategory",
						data :$getSeniorParamter(code,binding,storage),
						dataType : 'text',
						success : function(data) {
							var pro="";
							if(data!=null && data!=""){
								$.each(data,function(j,m){
									var catgName=m.split("_")[0];
									var catgNum=m.split("_")[1];
									var catgCode=m.split("_")[2];
									pro  +="<span>"+
									"<a href=\"#\" class=\"proptype_catg\" code=\""+catgCode+"\"><i class=\"s-check\"></i>"+catgName+"（"+catgNum+"）"+"</a>"+
									"</span>";
								})
							}
							if(pro!=""){
								$("#proCategory").parent().parent().css('display','block');
								$("#proCategory").empty();
								$("#proCategory").append(pro);
							}
							else{
								$("#proCategory").parent().parent().css('display','none');
							}
							
						},
						error : function(e,xhr,opt){
							//eDialog.alert("属性值列表数据遇到异常!");
						}
					});
				//--------------------------------------------	

		});

	})

	//面包屑叶子分类点击事件
	$('.searchdownc a').live("click",function(){
		//更新选中叶子分类和分类列表
		var newCode=$(this).attr("code");
		var newValue=$(this).html();
		
		$(this).attr("code",$("#mainCateA").attr("code"));
		$(this).html($("#mainCateA").attr("_value"))
		
		$("#mainCateA").attr("code",newCode);
		$("#mainCateA").attr("_value",newValue);
		$("#mainCateA").html(newValue+"<i class=\"ci-right\"><s>◇</s></i>");
		
		//更新分类Facet的选中class
		//去除之前选中标识
		$('.leafCate').children('li').removeClass('cateSelected');
		$('.leafCate').children('li').removeClass('fackCateSelected');
		//添加分类选中标识
		$(".leafCate").children("li[code="+newCode+"]").addClass('cateSelected');
		
		//清空在结果中搜
		$('#searchingInResultTxt').val("");
		
		//刷新属性
		$.eAjax({
			url : GLOBAL.WEBROOT + "/search/category",
			
			data : {'category':newCode},
			success : function(json) {
				
				//属性和属性值列表处理
				$propRender.render({"propList":json.propList});
				
				//刷新分页，分页刷新需放在属性刷新后
				$gdsListRefresh.refresh({"pageNumber":1});
				
			},
			error : function(e,xhr,opt){
				//eDialog.alert("属性值列表数据遇到异常!"); 
			}
    	});
		
	});
	
	//自定义范围查询点击事件
	$('#priceBtn').live('click',function(){
		
		if(!$('#priceStart').val()){
			eDialog.alert("请输入最小值!"); 
			return;
		}
		
		if(!$('#priceEnd').val()){
			eDialog.alert("请输入最大值!"); 
			return;
		}
		
		//去除已有选中样式
		//$('#search-xz a.selected').removeClass('selected');
		
		//去除假过滤标识
		//$('#cateList li').removeClass('fackCateSelected');
		
		//清空在结果中搜
		$('#searchingInResultTxt').val("");
		
		$('#priceStart').attr('realPriceStart',$('#priceStart').val());
		$('#priceEnd').attr('realPriceEnd',$('#priceEnd').val());
		var code = $("#pageUpdate").attr("value");
		$gdsListRefresh.refresh({"pageNumber":1},code);
		//$gdsListRefresh.refresh({"pageNumber":1});
	});
	
	//属性值点击事件
	$('.proptype').live('click',function(){
		
		//去除已有选中样式
		//$('#search-xz a.selected').removeClass('selected');

		//添加选中样式
		if(!$(this).hasClass("selected")){
			$(this).parent().addClass("selected");
			$(this).addClass("selected");
			$(this).parent().siblings().removeClass("selected");
			$(this).parent().siblings().children().removeClass("selected");
		}else{
			$(this).removeClass("selected");
			$(this).parent().removeClass("selected");
		}
		
		//第一次过滤价格不传分类参数
		if(!$(this).hasClass("priceProp")){
			
			//去除假过滤标识
			$('.leafCate').children('li').removeClass('fackCateSelected');
		}
		
		//清空在结果中搜
		$('#searchingInResultTxt').val("");
		var code = $("#pageUpdate").attr("value");
		$gdsListRefresh.refresh({"pageNumber":1},code);
		
	});
	
	//在结果中搜事件
	$('#searchInResultBtn').live('click',function(){
		
		var searchmore=$('#searchInResultTxt').val().trim();
		
		/*if(!searchmore){
			//eDialog.alert('请输入检索关键字');
			return ;
		}*/
		
		$('#searchingInResultTxt').val(searchmore);
		
		$gdsListRefresh.refresh({"pageNumber":1});
		
	});
	
	//销量排序事件
	$('#sales').live('click',function(){
		
		//设置选中样式
		if($(this).hasClass('selected')){
			
			//设置选中排序图标样式
			if($('#sales i:first').hasClass('micon-down')){
				$('#sales i:first').removeClass('micon-down');			
				$('#sales i:first').addClass('micon-downup')
			}else if($('#sales i:first').hasClass('micon-downup')){
				$('#sales i:first').removeClass('micon-downup');			
				$('#sales i:first').addClass('micon-down')
			}
			
		}else{
			
			$('#sl-order li').removeClass('selected');			
			$(this).addClass('selected')

		}
		var code = $("#pageUpdate").attr("value");
		$gdsListRefresh.refresh({"pageNumber":1},code);
		
	});
	
	//价格排序事件
	$('#discountPrice').live('click',function(){
		
		//设置选中样式
		if($(this).hasClass('selected')){
			
			//设置选中排序图标样式
			if($('#discountPrice i:first').hasClass('micon-down')){
				$('#discountPrice i:first').removeClass('micon-down');			
				$('#discountPrice i:first').addClass('micon-downup')
			}else if($('#discountPrice i:first').hasClass('micon-downup')){
				$('#discountPrice i:first').removeClass('micon-downup');			
				$('#discountPrice i:first').addClass('micon-down')
			}
			
		}else{
			
			$('#sl-order li').removeClass('selected');			
			$(this).addClass('selected')
		}
		var code = $("#pageUpdate").attr("value");
		$gdsListRefresh.refresh({"pageNumber":1},code);
		
	});
	
	//出版时间排序事件
	$('#newProductTime').live('click',function(){
		
		var field="newProductTime";
		var sort="desc";
		
		//设置选中样式
		if($(this).hasClass('selected')){
			
			//设置选中排序图标样式
			if($('#newProductTime i:first').hasClass('micon-down')){
				$('#newProductTime i:first').removeClass('micon-down');			
				$('#newProductTime i:first').addClass('micon-downup')
				sort="asc";
			}else if($('#newProductTime i:first').hasClass('micon-downup')){
				$('#newProductTime i:first').removeClass('micon-downup');			
				$('#newProductTime i:first').addClass('micon-down')
				sort="desc";
			}
			
		}else{
			
			$('#sl-order li').removeClass('selected');			
			$(this).addClass('selected')

			//第一次更改排序字段，使用默认排序规则
			if($('#newProductTime i:first').hasClass('micon-down')){
				sort="desc";
			}else if($('#newProductTime i:first').hasClass('micon-downup')){
				sort="asc";
			}
		}
		var code = $("#pageUpdate").attr("value");
		$gdsListRefresh.refresh({"pageNumber":1},code);
		
	});
	
	//简易分页栏前翻事件绑定
    $('#prepagebtn').live('click',function(){
    	var code=$("#pageUpdate").attr("value");
    	if(!$(this).hasClass("disable")){
    		var pageNo=parseInt($('#curpage').text())-1;
    		$gdsListRefresh.refresh({"pageNumber":pageNo},code);
    	}
    	
    });
    
    //简易分页栏后翻事件绑定
    $('#nextpagebtn').live('click',function(){
    	var code=$("#pageUpdate").attr("value");
    	if(!$(this).hasClass("disable")){
    		var pageNo=parseInt($('#curpage').text())+1;
    		$gdsListRefresh.refresh({"pageNumber":pageNo},code);
    	}
    });
	
    //搜索结果列表展示方式单击事件绑定
    $('.showbtn').live('click',function(){
    	
    	//已经是目前的展示方式，无需切换
		if($(this).hasClass('selected')){
			return;
		}
		$('.showbtn').removeClass('selected');
		$(this).addClass('selected');
		
		var showType=-1;
		
		//搜索结果列表重新渲染
		if($(this).children('.micon').hasClass('micon-showtb')){//网格方式
			showType=1;
		}else if($(this).children('.micon').hasClass('micon-showlt')){//列表方式
			showType=2;
		}
		
		$('#showType').val(showType);
		
		$gdsListRefresh.refresh({"pageNumber":$('#pageControlbar').attr('data-page-number')});
		
    });
    
    var getAddToCartCommon = function(_obj){
    	//购物车类型
		var cartType = "01";
		//购买数量
		var purchaseAmount = 1;
		//店铺id
		var shopId = _obj.children("input[name='shopId']").val();
		//店铺名称
		var shopName = _obj.children("input[name='shopName']").val();
		//商品id
		var gdsId = _obj.children("input[name='gdsId']").val();
		//商品类型id
		var gdsTypeId = _obj.children("input[name='gdsTypeId']").val();
		//商品名称
		var gdsName = _obj.children("input[name='gdsName']").val();
		//单品id
		var skuId = _obj.children("input[name='skuId']").val();
		//单品属性串
		var skuProps = _obj.children("input[name='skuProps']").val();
		//商品主分类
		var mainCatgs = _obj.children("input[name='mainCatgs']").val();
		//组合类型
		var groupType = "0";
		//组合明细 	默认为skuId，组合搭配的话，为主skuId:次skuId:次skuId	1	
		var groupDetail	= _obj.children("input[name='skuId']").val();
		
		var ordCartItemList = new Array();
		var ordCartItemListObj = {
				cartType : cartType,
				orderAmount : purchaseAmount,
				shopId : shopId,
				shopName : shopName,
				gdsId : gdsId,
				gdsType : gdsTypeId,
				gdsName : gdsName,
				skuId : skuId,
				mainCatgs : mainCatgs,
				skuInfo : skuProps,
				groupType : groupType,
				groupDetail : groupDetail,
				promId : "",
				prnFlag : "0",
    			scoreTypeId : ''
		};
		ordCartItemList.push(ordCartItemListObj);
		var param = {
				cartType : cartType,
				shopId : shopId,
				shopName : shopName,
				ordCartItemList	: JSON.stringify(ordCartItemList)
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/order/cart/add",
			data : param,
			plugin : ['ePageTop'],
			success : function(returnInfo) {
				if(returnInfo.ecpBaseResponseVO.resultFlag == "ok"){
					var startOffset = _obj.offset();
	                var endOffset = $("#carGus").offset();
	                var img = _obj.attr('img');
	                var flyer = $('<img class="u-flyer"  src="'+img+'">');
	                flyer.fly({
						speed : 1.4,
	                    start: {
	                        left: startOffset.left,
	                        top: startOffset.top
	                    },
	                    end: {
	                        left: endOffset.left+50,
	                        top: endOffset.top+10,
	                        width: 0,
	                        height: 0
	                    },
	                    onEnd: function(){
	                        this.destory();
	                    }
	                });
					$.pageTop.setOrderCount({url:GLOBAL.WEBROOT + '/order/getcartcount'}); 
//					window.location.href = GLOBAL.WEBROOT+"/order/cart/list";
	            }else{
	            	eDialog.alert(returnInfo.ecpBaseResponseVO.resultMsg);
	            }
			}
		});
    };
    $(".addToCart").live("click",(function(){
    	var buyFlag = 0;
    	var _obj = $(this);
    	if(_obj.children("input[name='ifGdsTypeBuyonce']").val()=='false'){
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsdetail/wetherbuyed",
				data : {skuId: _obj.children("input[name='skuId']").val()},
				async : false,
				success : function(returnInfo) {
					if(returnInfo.resultFlag=="ok"){
						if(returnInfo.buyedFlag==true){
							//已购买过咯
							buyFlag = 1;
						}
					}
				}
			});
		}
		if(buyFlag == 1){
			eDialog.alert("抱歉，该商品只允许购买一次！");
			return;
		}
    	//判断是是否是数字教材
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdetail/ifebookordigitsbook",
			data : {mainCatgs: _obj.children("input[name='mainCatgs']").val()},
			async : false,
			success : function(returnInfo) {
				if (returnInfo.digitsBook == true || returnInfo.ebook == true) {
					eDialog.confirm("您加入购物车的商品是【数字产品-"+returnInfo.catgCodeName+"】", {
	        			buttons : [ {
	        				caption : '确定加入购物车',
	        				callback : function() {
	        					getAddToCartCommon(_obj);
	        				}
	        			}, {
	        				caption : '返回',
	        				callback : $.noop
	        			} ]
	        		});
				}else{
					getAddToCartCommon(_obj);
				}
			}
		});
	}));
	
	$(".gdsCollect").live("click",(function(e){
		$(this).unbind('click');
		if(!$(this).find("input[name=isLogin]").val() == 'true'){
			window.location.href = GLOBAL.WEBROOT+'/login';
			return;
		}
		  var collect = $(this);
		var addToCart=$(this).siblings(".addToCart");
		
		//单品id
		var skuId = $(this).children("input[name='skuId']").val();
//		var gdsId = $(this).children("input[name='gdsId']").val();
//		var shopId = $(this).children("input[name='shopId']").val();
//        var gdsName = $(this).children("input[name='gdsName']").val();
//        var gdsPrice = $(this).children("input[name='gdsPrice']").val();

		var param = {
				skuId : skuId
//				gdsId : gdsId,
//				gdsPrice : gdsPrice,
//				shopId : shopId,
//				gdsName : gdsName,
				
		};
		
		$.eAjax({
			url : GLOBAL.WEBROOT + "/search/collect",
			data : param,
			success : function(returnInfo) {
				if (returnInfo.flag == '1') {
					eDialog.success("您已成功收藏该商品！");
				    collect.find("i").removeClass().addClass('micon micon-havfav');
				} else {
					eDialog.error(returnInfo.message);
				}
				$(this).bind('click');
			}
		});
		e.preventDefault();
	}));
	 pageConfig.config({
        //指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
        plugin : ['ePageTop']
        //指定页面
    });
	 
	//高级搜索
//	 $("#senior").click(function(){
//		 window.open(GLOBAL.WEBROOT + "/search?senior="+true);
//
//	 });
	 //高级搜索纸质书
	 $("#tab_1").click(function(){
		 cleanSearch();
		 $.fn.mcDropDown.change("1115");
		 $("#platCatgs").val("1115");
		 $("#save_statues").css('display','block');
		 $("#layout").css('display','block');
		 $("#isbn").css('display','block');
		 $("#author").css('display','block');
		 $("#tab_1").addClass("active");
		 $("#tab_2").removeClass("active");
		 $("#tab_3").removeClass("active");
	 });
	//高级搜索电子书
	 $("#tab_2").click(function(){
		 cleanSearch();
		 $.fn.mcDropDown.change("1199");
		 $("#platCatgs").val("1199");
		 $("#save_statues").css('display','none');
		 $("#layout").css('display','none');
		 $("#isbn").css('display','block');
		 $("#author").css('display','block');
		 $("#tab_1").removeClass("active");
		 $("#tab_2").addClass("active");
		 $("#tab_3").removeClass("active");
	 });
	 //高级搜索考试培训
	 $("#tab_3").click(function(){
		 cleanSearch();
		 var exaBook=$("#tab_3").attr("value");
		 $.fn.mcDropDown.change(exaBook);
		 $("#platCatgs").val(exaBook);
		 $("#save_statues").css('display','none');
		 $("#layout").css('display','none');
		 $("#isbn").css('display','none');
		 $("#author").css('display','none');
		 $("#tab_1").removeClass("active");
		 $("#tab_2").removeClass("active");
		 $("#tab_3").addClass("active");
//		 $gdsListRefresh.refresh({"pageNumber":1});
	 });
	//高级搜索
	 $("#search_senior").live('click',function(){
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
		$.eAjax({
			url : $webroot + "/search/refreshCategory",
			data : {
				"keyword":$('#keyword').val(),
				'category':$("#platCatgs").val(),//'3042',//$filterCategory.getCategoryId(),
				'field':$sortField.getSortField(),
				'sort':$fieldSort.getFieldSort(),
				'priceStart':$('#priceStart').attr('realPriceStart'),
				'priceEnd':$('#priceEnd').attr('realPriceEnd'),
				//高级搜索条件
				'senior':"true",
				'bookName':$("#name_value").val(),
				'author':$("#author_value").val(),
				'isbn'  :$("#isbn_value").val(),
				'binding':binding,
				'ifStorage':storage,
				'publicationDateStart':publicationDateStart,
				'publicationDateEnd':publicationDateEnd
				
				},
			dataType : 'text',
			success : function(datas) {
				var firAndSecCategorymap = datas.firAndSecCategory;
				for(var k in firAndSecCategorymap){
					var firstNameAndNum = k;
					var secondNameAndNumArray = firAndSecCategorymap[k]
				}
				var firstName=firstNameAndNum.split("_")[0];
				var firstNum=firstNameAndNum.split("_")[1];
				var firstCode=firstNameAndNum.split("_")[2];
				var children='';
				for ( var i=0;i<secondNameAndNumArray.length;i++){
					var secondName=secondNameAndNumArray[i].split("_")[0];
					var secondNum=secondNameAndNumArray[i].split("_")[1];
					var secondCode=secondNameAndNumArray[i].split("_")[2];
					if(firstCode=="1199"){
						if(secondCode=="1200" || secondCode=="1201"){
							if(secondNum!=0){
								children+='<ul class="child leafCate"  code="'+secondCode+'">'+
								'<li class="c-tit" title="" code="'+secondCode+'" _value="">'+
								'<a href="#">'+secondName+
								'<i class="num">（<span>'+secondNum+'</span>）</i>'+
								'</a>'+
								'</li>'+
								'</ul>'
							}
						}
					}else{
						//if(secondNum!=0){
							children+='<li class="c-tit" title="" code="'+secondCode+'" _value="">'+
										'<a href="#">'+secondName+
											'<i class="num">（<span>'+secondNum+'</span>）</i>'+
										'</a>'+
									  '</li>';
							
						//}
					}
				}
				if(firstNum!="0"){
					$("#sl-rtype").html(
							'<h3 class="head" id="rootCate" senior="true">平台分类</h3>'+
							'<ul class="list cateList" >'+
							'<li class="item">'+
							'<div class="it">'+
							'<i class="micon"></i>'+
							'<span class="tit" code="'+firstCode+'">'+firstName+
							'<i class="num">（'+firstNum+'）</i>'+
							'<a href="#"></a>'+
							'</span>'+
							'</div>'+
							'<ul class="child leafCate"  code="'+secondCode+'">'+
								children+
							'</ul>'+
							'</li>'+
							'</ul>'
					);
				}else{
					$("#sl-rtype").html('');
				}
				//刷新树就要在执行展开按钮事件
				$('#ct-nav .it .micon').click(function(){
					 $(this).parents('.item').toggleClass('drawdown');
				 })
				$gdsListRefresh.refresh({"pageNumber":1});
				 $("#root-nav").css('display','block');
				 $("#search-xz").css('display','block');
				 $("#pageMainBox").css('display','block');
				 $("#res_search").css('display','block');
				 $("#sl-rtype").css('display','block');
				 $("#ct-nav").css('display','block');
				 $("#tab_menu").css('display','none');
				 $("#tab_base").css('display','none');
				 downCateData($("#platCatgs").val());
			},
			error : function(e,xhr,opt){
				//eDialog.alert("属性值列表数据遇到异常!");
				//alert(111);
			}
    	});
		 return false;
		 
	 });
	 //清空高级搜索条件
	 $("#clearCont").live('click',function(){
		 cleanSearch();
	 });
	 
	 cleanSearch = function(){
		 $("#base_condition input").each(function(){  
		      this.value = "";  
		});
		$("#other_condition input").each(function(){  
		      this.value = "";  
		});
		$("[name='binding']").removeAttr("checked");//取消全选
		$("[name='saveStatue']").removeAttr("checked");
		$("#category_condition option:first").prop("selected", 'selected');	
	}
	
	 $("#name_value").keypress(function(e) { 
		 // 回车键事件 
       if(e.which == 13) { 
    	   $("#search_senior").trigger("click");
       } 
	 });
	 $("#author_value").keypress(function(e) { 
		 // 回车键事件 
       if(e.which == 13) { 
    	   $("#search_senior").trigger("click");
       } 
	 });
	 $("#isbn_value").keypress(function(e) { 
		 // 回车键事件 
       if(e.which == 13) { 
    	   $("#search_senior").trigger("click");
       } 
	 });
	 $("#keyword").keypress(function(e) { 
		 // 回车键事件 
       if(e.which == 13) { 
    	   $("#search_senior").trigger("click");
       } 
	 });

	 $(".c-tit").live('click',function(){
		 var ifSenior = $("#rootCate").attr("senior");
			 var code = $(this).attr("code");
			 downCateData(code);
			 $("#pageUpdate").attr("value",code);
			 $("#attrCode").attr("value",code);

	 })
	 
	 //分类路径显示获取数据和下钻功能获取数据和左侧树形更新
	 downCateData=function(code){
			 //var code = $(this).attr("code");
			//去除已有选中价格，版次等属性的样式
			$('#search-xz span').removeClass('selected');
			$('#search-xz a').removeClass('selected');
		 	var ifSenior = $("#rootCate").attr("senior");
		 	var tab_1_code = $("#tab_1").attr("value");
		 	var tab_2_code = $("#tab_2").attr("value");
		 	var tab_3_code = $("#tab_3").attr("value");
		 	var standArry = new Array();
		 	standArry.push(tab_1_code);
		 	standArry.push(tab_2_code);
		 	standArry.push(tab_3_code);
			 var storage="";//库存状态
			 $("input[name='saveStatue']:checked").each(function(){
				if($(this).val()==1) storage="1";
			 });
			 var binding="";//装帧 1016
			$("input[name='binding']:checked").each(function(){
				binding+="装帧_"+$(this).val()+",";
				});
			if(binding!=""){
				binding=binding.substr(0,binding.length-1);
				
			}
			//$gdsListRefresh.refresh({"pageNumber":1},code);
			$("#pageUpdate").attr("value",code);
			$("#attrCode").attr("value",code);
			 $.eAjax({
				 url : $webroot + "/search/getSameLevelCategory",
				 data : $getSeniorParamter(code,binding,storage),
				 dataType : 'text',
				 success : function(datas) {
					 	var parentCateCode=datas.parentCateCode;
					 	var parentCateName=datas.parentCateName;
						var item='';
						var secondLevelName='';
						var other_firstItem="";
						var downCatelist = datas.downCatelist;
						for ( var i=0;i<downCatelist.length;i++){
							var secondName=downCatelist[i].split("_")[0];
							var secondNum=downCatelist[i].split("_")[1];
							var secondCode=downCatelist[i].split("_")[2];
							if(secondCode==datas.searchCategory){
								secondLevelName=secondName;
								secondLevelNum=secondNum;
								secondLevelCode=secondCode;
							}
							if(secondNum!=0 && secondCode!=datas.searchCategory){
								item+='<a href="#" class="secondItem"  code="'+secondCode+'">'+secondName+'（'+secondNum+'）'+'</a>';//href=""
								other_firstItem+='<li class="item drawdown">'+
														'<div class="it">'+
														'<i class="micon"></i>'+
														'<span class="tit" code="'+secondCode+'">'+secondName+
														'<i class="num">（'+secondNum+'）</i>'+
														'<a href="#"></a>'+
														'</span>'+
														'</div>'+
													'</li>'
							}else if(secondCode==datas.searchCategory){
								var firstName=secondName;
								var firstCode=secondCode;
								var firstNum=secondNum;
							}
						}
						var parentItem = "";
						$.each(datas.pathCategorylist,function(i,m){
							parentItem  +='<li>'+
					                     '<a href="#" class="firstItem_temp" code="'+m.catgCode+'">'+m.catgName+'</a>'+
					                     '<span class="divider">&gt;</span>'+
				                    '</li>';
						})
						
						var seniorPath='<a href="#" class="secondItem_temp" code="'+secondLevelCode+'" title="'+secondLevelName+
										'（'+secondLevelNum+'）'+'">'+secondLevelName+'（'+secondLevelNum+'）';
						var NotSeniorPath='<div class="dorpdown">'+
											 '<a href="#" class="secondItem_temp" code="'+secondLevelCode+'" title="'+secondLevelName+'（'+secondLevelNum+'）'+'">'+secondLevelName+'（'+secondLevelNum+'）'+
											 '<i class="ci-right"><s>◇</s></i>'+
											 '</a>'+
											 '<span class="spacer"></span>'+
											 '<div class="downc ">'+item+'</div>'+
										 '</div>';
						if(item==""){
							NotSeniorPath='<div class="dorpdown">'+
											 '<a href="#" class="secondItem_temp" code="'+secondLevelCode+'" title="'+secondLevelName+'（'+secondLevelNum+'）'+'">'+secondLevelName+'（'+secondLevelNum+'）'+
											 '<i class="ci-right"><s>◇</s></i>'+
											 '</a>'+
										 '</div>';
						}
						if($("#senior_sign").attr("value")=="true" && $.inArray(code, standArry)!=-1){
							var secondItem=seniorPath;
						}else{
							var secondItem=NotSeniorPath;
						}
						 $("#leadCat").html('<li class="fir"><a href="#">全部商品</a> <span class="divider">&gt;</span></li>'+
								 parentItem+
								 '<li>'+secondItem+
									 //'<span class="divider">&gt;</span>'+
								 '</li>'
						 );
						//获取选中分类的分类属性
							$.eAjax({
								url : GLOBAL.WEBROOT + "/search/getCurrentcategory",
								data :$getSeniorParamter(code,binding,storage),
								dataType : 'text',
								success : function(data) {
									var pro="";
									var children="";
									if(data!=null && data!=""){
										$.each(data,function(j,m){
											var catgName=m.split("_")[0];
											var catgNum=m.split("_")[1];
											var catgCode=m.split("_")[2];
											pro  +="<span>"+
											"<a href=\"#\" class=\"proptype_catg\" code=\""+catgCode+"\"><i class=\"s-check\"></i>"+catgName+"（"+catgNum+"）"+"</a>"+
											"</span>";
											children+='<li class="c-tit" title="" code="'+catgCode+'" _value="">'+
															'<a href="#">'+catgName+
															'<i class="num">（<span>'+catgNum+'</span>）</i>'+
															'</a>'+
													  '</li>';
										})
									}
									if(pro!=""){
										$("#proCategory").parent().parent().css('display','block');
										$("#proCategory").empty();
										$("#proCategory").append(pro);
									}
									else{
										$("#proCategory").parent().parent().css('display','none');
									}
									
									//更新左侧分类树
									if($("#senior_sign").attr("value")=="true" && $.inArray(code, standArry)!=-1){
										other_firstItem="";
									}parentCateCode
									//var mallSiteUrl="http://127.0.0.1:8280/ecp-web-mall";//$("#mallSiteUrl").attr("contextpath");
									if(children!=""){
										children_last='<ul class="child leafCate">'+
														children+
													  '</ul>';
									}else{
										children_last="";
									}
									$("#sl-rtype").html(
											'<h3 class="head" id="rootCate" code="'+parentCateCode+'"><a href="#">'+parentCateName+'</a></h3>'+
											'<ul class="list cateList" >'+
												'<li class="item drawdown">'+
													'<div class="it">'+
														'<i class="micon"></i>'+
														'<span class="tit" code="'+firstCode+'">'+firstName+
														'<i class="num">（'+firstNum+'）</i>'+
														'<a href="#"></a>'+
//														'<a href="'+mallSiteUrl+'/search?category='+firstCode+'">'+firstName+
//														'<i class="num">（'+firstNum+'）</i>'+
//														'</a>'+
														'</span>'+
													'</div>'+
													//'<ul class="child leafCate">'+
														children_last+
													//'</ul>'+
												'</li>'+other_firstItem+
											'</ul>'
									);
									 $('#ct-nav .it .micon').click(function(){
										 $(this).parents('.item').toggleClass('drawdown');
									 })
									//更新对应属性(价格、是否试读等)
									 $.eAjax({
											url : GLOBAL.WEBROOT + "/search/getGdsProp",
											data :{'category' : code},
											dataType : 'text',
											success : function(propData) {
												var prop='';
												if(propData!=""){
												$.each(propData,function(j,m){
													var values=m.values;
													var proValue="";
													$.each(values,function(i,n){
														proValue+='<span>'+
																	'<a href="#" class="proptype" code="'+n.id+'"><i class="s-check"></i>'+n.propValue+'</a>'+
																 '</span>';
													});
													prop+='<div class="sl-line">'+
																'<div class="sl-wrap">'+
																	'<label class="sl-key" code="'+m.id+'" style="'+((m.values.length/6)+1)*40+'px;">'+
																		m.propName+
																	'</label>'+
																	'<div class="sl-val">'+
																		proValue+
																	'</div>'+
																'</div>'+
															'</div>';
												});
												}
												$("#slArrow").nextUntil("#priceSign").remove();
												$("#slArrow").after(prop);
												
											},
											error : function(e,xhr,opt){
												//eDialog.alert("属性值列表数据遇到异常!");
											}
										});
									
								},
								error : function(e,xhr,opt){
									//eDialog.alert("属性值列表数据遇到异常!");
								}
							});
				 },
				 error : function(e,xhr,opt){
					 //eDialog.alert("属性值列表数据遇到异常!");
				 }
			 });
			//去除已有选中价格，版次等属性的样式
			 $('#search-xz a.selected').removeClass('selected');
			 $gdsListRefresh.refresh({"pageNumber":1},code);
	 }
	 $(".secondItem").live("click",function(){
		 	$("#proCategory").parent().parent().css('display','none');
		 	//$(this).nextAll('li').remove();
			$('#searchingInResultTxt').val("");
			var $this = $(this);
			var $firstItem = $(".secondItem_temp",$this.closest(".dorpdown"));
			//删除后面的li节点
			$firstItem.parent().parent().nextAll('li').remove();
			
			var currentcode = $this.attr("code");
			var currenta = $this.text();
			var firstcode = $firstItem.attr("code");
			var firstatitle = $firstItem.attr("title");
			
			$firstItem.attr("code",currentcode);
			$firstItem.attr("title",currenta);
			$firstItem.html(currenta+'<i class=\"ci-right\"><s>◇</s></i>');
			
			$this.text(firstatitle);
			$this.attr("code",firstcode);
			downCateData(currentcode);
			$("#pageUpdate").attr("value",currentcode);
			$("#attrCode").attr("value",currentcode);
			
	 })
	 $(".secondItem_temp").live("click",function(){
		 	$("#proCategory").parent().parent().css('display','none');
			$('#searchingInResultTxt').val("");
			var $this = $(this);
			var code = $this.attr("code");
			var senior=$("#senior_sign").val();
			if($this.parent().hasClass("dorpdown")){
				$this.parent().parent().nextAll('li').remove();
			}else{
				$this.parent().nextAll('li').remove();
			}
			$("#pageUpdate").attr("value",code);
			$("#attrCode").attr("value",code);
			downCateData(code);
	 })
	 $(".firstItem_temp").live("click",function(){
		$("#proCategory").parent().parent().css('display','none');
		$(this).parent().nextAll('li').remove();
		$('#searchingInResultTxt').val("");
		var code = $(this).attr("code");
		//$("#leadCat li:last").remove();
		downCateData(code);
		$("#pageUpdate").attr("value",code);
		$("#attrCode").attr("value",code);
	 })
	 
	 $(".tit").live("click",function(){
			$('#searchingInResultTxt').val("");
			var code = $(this).attr("code");
			//downData(code);
			downCateData(code);
			$("#pageUpdate").attr("value",code);
			$("#attrCode").attr("value",code);
	 })
	 $("#rootCate").live("click",function(){
			$('#searchingInResultTxt').val("");
			var code = $(this).attr("code");
			if(code!=""){
				//downData(code);
				downCateData(code);
				$("#pageUpdate").attr("value",code);
				$("#attrCode").attr("value",code);
			}
	 })
	 //获取分类属性
	 downData = function(code){
		 //var code = $(this).attr("code");
		 var storage="";//库存状态
		 $("input[name='saveStatue']:checked").each(function(){
			if($(this).val()==1) storage="1";
		 });
		 var binding="";//装帧 1016
		$("input[name='binding']:checked").each(function(){
			binding+="装帧_"+$(this).val()+",";
			});
		if(binding!=""){
			binding=binding.substr(0,binding.length-1);
			
		}
		$("#pageUpdate").attr("value",code);
		$("#attrCode").attr("value",code);
		 $.eAjax({
				url : GLOBAL.WEBROOT + "/search/category",
				data : $getSeniorParamter(code,binding,storage),
				success : function(json) {
					//属性和属性值列表处理
					$propRender.render({"propList":json.propList});
					//刷新分页，分页刷新需放在属性刷新后
					$gdsListRefresh_down.refresh_down({"pageNumber":1},code);
					//获取选中分类的分类属性
					$.eAjax({
						url : GLOBAL.WEBROOT + "/search/getCurrentcategory",
						data :$getSeniorParamter(code,binding,storage),
						dataType : 'text',
						success : function(data) {
							var pro="";
							if(data!=null){
								$.each(data,function(j,m){
									var catgName=m.split("_")[0];
									var catgNum=m.split("_")[1];
									var catgCode=m.split("_")[2];
									pro  +="<span>"+
									"<a href=\"#\" class=\"proptype_catg\" code=\""+catgCode+"\"><i class=\"s-check\"></i>"+catgName+"（"+catgNum+"）"+"</a>"+
									"</span>";
								})
								
							}
							if(pro!=""){
								$("#proCategory").parent().parent().css('display','block');
								$("#proCategory").empty();
								$("#proCategory").append(pro);
							}else{
								$("#proCategory").parent().parent().css('display','none');
							}
							
						},
						error : function(e,xhr,opt){
							//eDialog.alert("属性值列表数据遇到异常!");
						}
					});
				},
				error : function(e,xhr,opt){
					//eDialog.alert("属性值列表数据遇到异常!");
				}
			});
	}
	 //点击分类属性事件
	 $(".proptype_catg").live("click",function(){
		//去除已有选中价格，版次等属性的样式
		 $('#search-xz span').removeClass('selected');
		 $('#search-xz a').removeClass('selected');
		 $("#proCategory").parent().parent().css('display','none');
		 var senior=$("#senior_sign").val();
		 var code = $(this).attr("code");
		 var parentName;
		 var parentCode;
		 //var senior=$("#senior_sign").val();
		 //$("#pageUpdate").attr("pageUpdate",code);//为分页传分类参数
		 //$("#attrCode").attr("value",code);//为点击属性传参
		 $.eAjax({
			 url : GLOBAL.WEBROOT + "/search/getParentcategory",
				data : $getSeniorParamter(code,binding,storage),
				dataType : 'text',
				success : function(data) {
					parentCode=data.catgCode;
					parentName=data.catgName;
				}
		 });
		 var storage="";//库存状态
		 $("input[name='saveStatue']:checked").each(function(){
			if($(this).val()==1) storage="1";
		 });
		 var binding="";//装帧 1016
		$("input[name='binding']:checked").each(function(){
			binding+="装帧_"+$(this).val()+",";
			});
		if(binding!=""){
			binding=binding.substr(0,binding.length-1);
			
		}
		//$gdsListRefresh.refresh({"pageNumber":1},code)
			$.eAjax({
				url : GLOBAL.WEBROOT + "/search/getsubcategory",
				data : $getSeniorParamter(code,binding,storage),
				dataType : 'text',
				success : function(datamap) {
					for(var k in datamap){
						var firstNameAndNum = k;
						var secondNameAndNumArray = datamap[k]
					}
					var item='';
					var firstName=firstNameAndNum.split("_")[0];
					var firstNum=firstNameAndNum.split("_")[1];
					var firstCode=firstNameAndNum.split("_")[2];
					var li_item="";
					var mallSiteUrl="http://127.0.0.1:8280/ecp-web-mall";//$("#mallSiteUrl").attr("contextpath");
					for ( var i=0;i<secondNameAndNumArray.length;i++){
						var secondName=secondNameAndNumArray[i].split("_")[0];
						var secondNum=secondNameAndNumArray[i].split("_")[1];
						var secondCode=secondNameAndNumArray[i].split("_")[2];
//						if(secondCode==datas.searchCategory){
//							secondLevelName=secondName;
//							secondLevelNum=secondNum;
//							secondLevelCode=secondCode;
//						}
						if(secondCode!=code && secondNum!="0"){//secondCode!=code
							item+='<a href="#" class="secondItem"  code="'+secondCode+'">'+secondName+'（'+secondNum+'）'+'</a>';//href=""
							li_item+='<li class="item">'+
										'<div class="it">'+
											'<i class="micon"></i>'+
											'<span class="tit" code="'+secondCode+'">'+
											'<i class="num">（'+secondNum+'）</i>'+
											//'<a href="'+mallSiteUrl+'/search?category='+secondCode+'">'+secondName+'</a>'+
												secondName+
											'</span>'+
										'</div>'+
			//							'<ul class="child leafCate">'+
			//								children+
			//							'</ul>'+
									'</li>';
						}
						
					}
					if(item!=""){
						var	parentItem ='<li>'+
						'<span class="divider">&gt;</span>'+
						'<div class="dorpdown">'+
						'<a href="#" class="secondItem_temp" code="'+firstCode+'" title="'+firstName+'（'+firstNum+'）'+'">'+firstName+'（'+firstNum+'）'+
						'<i class="ci-right"><s>◇</s></i>'+
						'</a>'+
						'<span class="spacer"></span>'+
						'<div class="downc ">'+item+'</div>'+
						'</div>'+
						//'<span class="divider">&gt;</span>'+
						'</li>'
					}else{
						var	parentItem ='<li>'+
						'<span class="divider">&gt;</span>'+
						'<div class="dorpdown">'+
						'<a href="#" class="secondItem_temp" code="'+firstCode+'" title="'+firstName+'（'+firstNum+'）'+'">'+firstName+'（'+firstNum+'）'+
						'<i class="ci-right"><s>◇</s></i>'+
						'</a>'+
						//'<span class="divider">&gt;</span>'+
						'</li>'
					}
					$("#leadCat").append(parentItem);
					//获取分类属性
					$.eAjax({
						url : GLOBAL.WEBROOT + "/search/getCurrentcategory",
						data : $getSeniorParamter(code,binding,storage),
						dataType : 'text',
						success : function(data) {
							var pro="";
							var children="";
							if(data!=null && data!=""){
								$.each(data,function(j,m){
									var catgName=m.split("_")[0];
									var catgNum=m.split("_")[1];
									var catgCode=m.split("_")[2];
									pro  +="<span>"+
									"<a href=\"#\" class=\"proptype_catg\" code=\""+catgCode+"\"><i class=\"s-check\"></i>"+catgName+"（"+catgNum+"）"+"</a>"+
									"</span>";
									children+='<li class="c-tit" title="" code="'+catgCode+'" _value="">'+
													'<a href="#">'+catgName+
													'<i class="num">（<span>'+catgNum+'</span>）</i>'+
													'</a>'+
											  '</li>';
								})
							}
							if(pro!=""){
								$("#proCategory").parent().parent().css('display','block');
								$("#proCategory").empty();
								$("#proCategory").append(pro);
							}
							else{
								$("#proCategory").parent().parent().css('display','none');
							}
							$("#pageUpdate").attr("value",code);//为分页传分类参数
							$("#attrCode").attr("value",code);//为点击属性传分类参
							$gdsListRefresh.refresh({"pageNumber":1},code);
							//downData(code);
							if(children!=""){
								children_last='<ul class="child leafCate">'+
												children+
											  '</ul>';
							}else{
								children_last="";
							}
							//更新左侧分类树
							$("#sl-rtype").html(
									'<h3 class="head" id="rootCate" code="'+parentCode+'"><a href="#">'+parentName+'</a></h3>'+
									'<ul class="list cateList" >'+
										'<li class="item drawdown">'+
											'<div class="it">'+
												'<i class="micon"></i>'+
												'<span class="tit" code="'+firstCode+'">'+firstName+
												'<i class="num">（'+firstNum+'）</i>'+
												'<a href="#"></a>'+
												'</span>'+
											'</div>'+
											//'<ul class="child leafCate">'+
												children_last+
											//'</ul>'+
										'</li>'+li_item+
									'</ul>'
							);
							 $('#ct-nav .it .micon').click(function(){
								 $(this).parents('.item').toggleClass('drawdown');
							 })
							//更新对应属性(价格、是否试读等)
							 $.eAjax({
									url : GLOBAL.WEBROOT + "/search/getGdsProp",
									data :{'category' : code},
									dataType : 'text',
									success : function(propData) {
										var prop='';
										if(propData!=""){
										$.each(propData,function(j,m){
											var values=m.values;
											var proValue="";
											$.each(values,function(i,n){
												proValue+='<span>'+
															'<a href="#" class="proptype" code="'+n.id+'"><i class="s-check"></i>'+n.propValue+'</a>'+
														 '</span>';
											});
											prop+='<div class="sl-line">'+
														'<div class="sl-wrap">'+
															'<label class="sl-key" code="'+m.id+'" style="'+((m.values.length/6)+1)*40+'px;">'+
																m.propName+
															'</label>'+
															'<div class="sl-val">'+
																proValue+
															'</div>'+
														'</div>'+
													'</div>';
										});
										}
										$("#slArrow").nextUntil("#priceSign").remove();
										$("#slArrow").after(prop);
										
									},
									error : function(e,xhr,opt){
										//eDialog.alert("属性值列表数据遇到异常!");
									}
								});
							
						},
						error : function(e,xhr,opt){
							//eDialog.alert("属性值列表数据遇到异常!");
						}
					});
					
				},
				error : function(e,xhr,opt){
					//eDialog.alert("属性值列表数据遇到异常!");
				}
			});
	 })

	 
	 //高级搜索参数
	 $getSeniorParamter=function(code,binding,storage){
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
				category = code;
			}else{
				var ifSenior=false;
				propertyGroup=$propertyGroup.getPropertyGroup();
				p_keyword=$('#searchingTxt').val();
				category = code;//$filterCategory.getCategoryId();
				price_start = $('#priceStart').attr('realPriceStart');
				price_end = $('#priceEnd').attr('realPriceEnd');
//				propertyGroup="[,{\"propertyId\":\"priceCode\",\"propertyValueIds\":\"1\"},]";
			}
		 var paramter_senior = {
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
				 //'adid':$('#_adid').val(),
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
		 return paramter_senior;
	 }
	 
 
});

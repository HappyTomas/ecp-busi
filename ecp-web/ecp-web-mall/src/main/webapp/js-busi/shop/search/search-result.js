$(function(){
	
	//搜索关键字回填到搜索框
	$("#searchTxt").val($("#searchingTxt").val());
	
	if($('#searchTxt').val()){
		$('#searchConten').hide();
	}else{
		$('#searchConten').show();
	}
	
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
	$('#ct-nav .it').live("click",function(){
        $(this).parent().toggleClass('drawdown');
    });
    
    //搜索结果叶子分类点击事件
	$('.leafCate').children('li').each(function () {
		var li=$(this);

		$(li).live("click",function(){
			$("#leadCat").children("li").each(function(index){
				$(this).children('span').show();
				$(this).show();

			});
			//去除之前选中标识
			$('.leafCate').children('li').removeClass('cateSelected');
			$('.leafCate').children('li').removeClass('fackCateSelected');
			//添加分类选中标识
			$(this).addClass('cateSelected');

			//更新分类面包屑
			var parent=$(this).parents('.item');
			$("#topCateA").html($(parent).children().children('span').html());

			$("#mainCateA").attr("code",$(this).attr("code"));
			$("#mainCateA").attr("_value",$(this).attr("_value"));
			$("#mainCateA").html($(this).attr("_value")+"<i class=\"ci-right\"><s>◇</s></i>");

			$('#downc').empty();
			var children=$(this).parent().children();
			var i=0;
			for(;i<children.length;i++){
				var child=children[i];
				if($(child).attr("code")!=$(this).attr("code")){
					$('#downc').append("<a href=\"#\" code=\""+$(child).attr("code")+"\" class=\"downc-cate\">"+$(child).attr("_value")+"</a>");
				}
			}

			//清空在结果中搜
			$('#searchingInResultTxt').val("");
			console.log('===='+$(this));
			//刷新属性
			$.eAjax({
				url : GLOBAL.WEBROOT + "/shopgdssearch/category",

				data : {'category':$(this).attr("code")},
				success : function(json) {

					//属性和属性值列表处理
					$propRender.render({"propList":json.propList});

					//刷新分页，分页刷新需放在属性刷新后
					$shopGdsListRefresh.refresh({"pageNumber":1});

				},
				error : function(e,xhr,opt){
					//eDialog.alert("属性值列表数据遇到异常!");
				}
			});

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
			url : GLOBAL.WEBROOT + "/shopgdssearch/category",
			
			data : {'category':newCode},
			success : function(json) {
				
				//属性和属性值列表处理
				$propRender.render({"propList":json.propList});
				
				//刷新分页，分页刷新需放在属性刷新后
				$shopGdsListRefresh.refresh({"pageNumber":1});
				
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
		$('#search-xz a.selected').removeClass('selected');
		
		//去除假过滤标识
		//$('#cateList li').removeClass('fackCateSelected');
		
		//清空在结果中搜
		$('#searchingInResultTxt').val("");
		
		$('#priceStart').attr('realPriceStart',$('#priceStart').val());
		$('#priceEnd').attr('realPriceEnd',$('#priceEnd').val());
		$shopGdsListRefresh.refresh({"pageNumber":1});
	});
	
	//属性值点击事件
	$('.proptype').live('click',function(){
		
		//去除已有选中样式
		$('#search-xz a.selected').removeClass('selected');
		
		//添加选中样式
		$(this).addClass("selected");
		
		//第一次过滤价格不传分类参数
		if(!$(this).hasClass("priceProp")){
			
			//去除假过滤标识
			$('.leafCate').children('li').removeClass('fackCateSelected');
		}
		
		//清空在结果中搜
		$('#searchingInResultTxt').val("");
		
		$shopGdsListRefresh.refresh({"pageNumber":1});
		
	});
	
	//在结果中搜事件
	$('#searchInResultBtn').live('click',function(){
		
		var searchmore=$('#searchInResultTxt').val().trim();
		
		if(!searchmore){
			//eDialog.alert('请输入检索关键字');
			return ;
		}
		
		$('#searchingInResultTxt').val(searchmore);
		
		$shopGdsListRefresh.refresh({"pageNumber":1});
		
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
		
		$shopGdsListRefresh.refresh({"pageNumber":1});
		
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
		
		$shopGdsListRefresh.refresh({"pageNumber":1});
		
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
		
		$shopGdsListRefresh.refresh({"pageNumber":1});
		
	});
	
	//简易分页栏前翻事件绑定
    $('#prepagebtn').live('click',function(){
    	
    	if(!$(this).hasClass("disable")){
    		var pageNo=parseInt($('#curpage').text())-1;
    		$shopGdsListRefresh.refresh({"pageNumber":pageNo});
    	}
    	
    });
    
    //简易分页栏后翻事件绑定
    $('#nextpagebtn').live('click',function(){
    	if(!$(this).hasClass("disable")){
    		var pageNo=parseInt($('#curpage').text())+1;
    		$shopGdsListRefresh.refresh({"pageNumber":pageNo});
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
		
		$shopGdsListRefresh.refresh({"pageNumber":$('#pageControlbar').attr('data-page-number')});
		
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
			url : GLOBAL.WEBROOT + "/shopgdssearch/collect",
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
});

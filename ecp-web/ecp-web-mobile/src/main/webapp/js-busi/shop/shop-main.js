$(function(){
	//分类
	$("#gotocategory").click(function(){
		window.location.href = GLOBAL.WEBROOT + "/shopmain/queryshopcategoryinfo?shopId="+$("#shopId").val();
	});
	//促销列表 选项滑动
    var w=0;
    $('.cx-pro li').each(function () {
        w = w + $(this).outerWidth();
    });
    $('.cx-pro > ul').width(w + 10);
    var $scrollObj = $('.cx-pro')[0];
    new $.AMUI.iScroll($scrollObj, {
        scrollX: true,
        scrollY: false,
        preventDefault:false
    });
	//店铺关注
	 $('.sbtn').on('click', function () {
        var em = $(this).find('em');
        $(this).toggleClass('sbtn-def');
 		var isCollect=$('#isCollect').val();
 		var url=GLOBAL.WEBROOT + "/shopmain/collectShop/"+$('#shopId').val();
 		if(isCollect== 'true'){
 			url=GLOBAL.WEBROOT + "/shopmain/deleteShop/"+$('#shopId').val();
 		}
 		$.eAjax({
 				url :url ,
 				data : {},
 				async : false,
 				success : function(returnInfo) {
 					if(returnInfo.resultFlag=="ok"){
 						if(isCollect== 'true'){
 	 						$('#isCollect').val('false');
 	 						 em.text('关注');
 	 					}else{
 	 						$('#isCollect').val('true');
 	 						em.text('已关注');
 	 					}
 					}
 				},
 				exception : function() {
 					eDialog.error(returnInfo.resultMsg);
 				}
		});
     });
	$(".salecheck").click(function(e){
		$(".salecheck").removeClass("active");
		$(this).addClass("active");
		$.eAjax({
			url : GLOBAL.WEBROOT + '/shopmain/gridsalelist',
			data : {"promTypeCode":$(this).attr('value')},
			dataType : "html",
			success : function(returnInfo) {
				$("#saleGdsList",$("#saleGdsForm")).empty();
				$("#saleGdsList",$("#saleGdsForm")).append(returnInfo);
			}
		});
		e.preventDefault();
	});
		
	//获取店铺的广告
	$.eAjax({
		url : GLOBAL.WEBROOT + '/shopmain/qryLeafletList',
		data : {
			"placeId" : "1501",
			"shopId" : $("#shopId").val(),
			"placeSize" : 10,
			"placeWidth":"375",
			"placeHeight":"144",
			"status" : "1"
		},
		async : true,
		dataType : "html",
		success : function(returnInfo) {
			$("#shopMainAdvertise").empty();
			$("#shopMainAdvertise").append(returnInfo);
		}
	});
	var parseMoney = function(data){
		var str = (data/100).toFixed(2) + '';
		var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
		var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
		return ret = intSum + dot;
	};
	 var $allGds = $("#allGdsForm");
	 var $newGds = $("#newGdsForm");
	 var $hotSale = $("#hotsaleForm");
	 var getPrice = function($obj){
		$(".resultList",$obj).each(function() {
			var $this = $(this);
			var gdsId = $this.find("input[name=pGdsId]").val();
			var skuId = $this.find("input[name=pSkuId]").val();
			var shopId = $("#shopId").val();
			var realPrice = $this.find("input[name=pRealPrice]").val();
			var discountPrice = $this.find("input[name=pDiscountPrice]").val();
			var ifFree=$this.find("input[name=pIfFree]").val();
			var data = new Object();
			data.gdsId = gdsId;
			data.skuId = skuId;
			data.shopId = shopId;
			data.realPrice = realPrice;
			data.discountPrice = discountPrice;
			data.ifFree=ifFree;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/shopmain/queryPromInfo",
				data : data,
				success : function(json) {
					$this.removeClass('resultList');
					var beforePrice=$this.find("b[name=discountPrice]").html().replace(/[^0-9]/ig, ""); 
					var flag=true;
					if(beforePrice+"" == json.price+""){
						flag=false;
					}
					if(json.price!=null  && json.price+"" !="" && flag){
						$this.find("b[name=discountPrice]").html(parseMoney(json.price));
					}	
					$.each(json.promTypes, function(i, value) {
						if(i==0){
							$(".saleInfo",$this).html(value).addClass("p-color p-color-radius");
							
						}
					});
				},
				error : function(e, xhr, opt) {
				}
			});
		});
	 };
	 //重磅推荐
//   //重磅推荐滚动
//   $("#hotSaleGds").height( $(window).height()-$('.shop-header').outerHeight()
//   		-$('.shop-tab').outerHeight()- $('.am-slider-pictrues').outerHeight()
//   		-$('.am-header').outerHeight()-30);
//   new $.AMUI.iScroll(("#hotSaleGds"), {
//       scrollX: false,
//       scrollY: true,
//       preventDefault:false
//   });
	 $('#wrapper3',$hotSale).height( $(window).height()-$('.shop-header').outerHeight()
		   		-$('.shop-tab').outerHeight()- $('.am-slider-pictrues').outerHeight()
		   		-$('.am-header').outerHeight()-30);
	 var loadScrollAll = new LoadScroll("wrapper3", {
	        url:GLOBAL.WEBROOT + '/shopmain/scroll',
	        params : {"shopId":$("#shopId").val(),'field':"sales",'sort':'desc'},
	        isAjax:true,
	        bindevent : function(){
	        	if($(".ui-empty",$("#wrapper3"))!=undefined && $(".ui-empty",$("#wrapper3")).length > 0){
	        		$(".panel-header",$("#wrapper3")).attr('style','display:none');
	        	}
	        	getPrice($hotSale);
	        }
	    });
	 $('#wrapper2',$allGds).height($(window).height()
		-20
		-$('.am-header').height()
		-$('.shop-header').height()
		-$('.shop-tab').height()
		-$('.filter-head',$allGds).outerHeight()
		-parseInt($('.filter-head',$allGds).css('margin-bottom'))
	);
    var loadScrollAll = new LoadScroll("wrapper2", {
        url:GLOBAL.WEBROOT + '/shopmain/scroll',
        params : ShopMain.getReqParam($allGds),
        isAjax:true,
        bindevent : function(){
        	getPrice($allGds);
        }
    });
    $('#wrapper1',$newGds).height($(window).height()
    	-20
		-$('.am-header').height()
		-$('.shop-header').height()
		-$('.shop-tab').height()
		-$('.filter-head',$newGds).outerHeight()
		-parseInt($('.filter-head',$newGds).css('margin-bottom'))
	);
    var loadScrollNew = new LoadScroll("wrapper1", {
        url:GLOBAL.WEBROOT + '/shopmain/scroll',
        params : ShopMain.getReqParam($newGds),
        isAjax:true,
        bindevent : function(){
        	getPrice($newGds);
        }
    });
    //展示方式
    $('#showType',$allGds).click(function(){
        if($('.pro-list',$allGds).hasClass('pro-list-grid')){
            $(this,$allGds).html('<i class="iconfont icon-cls"></i>');
        }else{
            $(this,$allGds).html('<i class="iconfont icon-all"></i>');
        }
        $('.pro-list',$allGds).toggleClass('pro-list-grid');
        loadScrollAll.refresh();
    });
    $('#showType',$newGds).click(function(){
        if($('.pro-list',$newGds).hasClass('pro-list-grid')){
            $(this,$newGds).html('<i class="iconfont icon-cls"></i>');
        }else{
            $(this,$newGds).html('<i class="iconfont icon-all"></i>');
        }
        $('.pro-list',$newGds).toggleClass('pro-list-grid');
        loadScrollNew.refresh();
    });

    $('.sortComprehensive').click(function(){
        if(!$(this).hasClass('selected')){
            $(this).addClass('selected');

            $('.sortSales').removeClass('selected');
            $('.sortSales').children().remove('i');
            $('.sortPrice').removeClass('selected');
            $('.sortPrice').children().remove('i');
            var objStr = $(this).parents(".GdsForm").attr('id');
			if(objStr=="allGdsForm"){
	            loadScrollAll.refreshPage({
	                params:{
	                    keyword:$('#needSearch').val(),
	                    field:'',
	                    sort:'',
	                    shopId:$("#shopId").val(),
	                    propertyGroup:ShopMain.getSelectedProp($allGds),
	                    page:'1'
	                }
	            });
			}else{
				loadScrollNew.refreshPage({
	                params:{
	                    keyword:$('#needSearch').val(),
	                    field:'',
	                    sort:'',
	                    shopId:$("#shopId").val(),
	                    propertyGroup:ShopMain.getSelectedProp($newGds),
	                    page:'1'
	                }
	            });
			}
        }
    });

    $('.sortSales').click(function(){
        if(!$(this).hasClass('selected')){
            $(this).addClass('selected');
            $(this).append("<i class=\"drop drop-up\"></i>");

            $('.sortComprehensive').removeClass('selected');
            $('.sortPrice').removeClass('selected');
            $('.sortPrice').children().remove('i');
            console.log("进入sortSales");
            var objStr = $(this).parents(".GdsForm").attr('id');
			if(objStr=="allGdsForm"){
	            loadScrollAll.refreshPage({
	                params:{
	                    keyword:$('#needSearch').val(),
	                    field:'sales',
	                    sort:'asc',
	                    shopId:$("#shopId").val(),
	                    propertyGroup:ShopMain.getSelectedProp($allGds),
	                    page:'1'
	                }
	            });
            }else{
            	 loadScrollNew.refreshPage({
                     params:{
                         keyword:$('#needSearch').val(),
                         field:'sales',
                         sort:'asc',
                         shopId:$("#shopId").val(),
                         propertyGroup:ShopMain.getSelectedProp($newGds),
                         page:'1'
                     }
                 });
            }
        }else{
            //逆序
            if($(this).children('i').hasClass('drop-up')){
                $(this).children('i').removeClass('drop-up');
                $(this).children('i').addClass('drop-down');
                var objStr = $(this).parents(".GdsForm").attr('id');
    			if(objStr=="allGdsForm"){
	                loadScrollAll.refreshPage({
	                    params:{
	                        keyword:$('#needSearch').val(),
	                        field:'sales',
	                        sort:'desc',
	                        shopId:$("#shopId").val(),
	                        propertyGroup:ShopMain.getSelectedProp($allGds),
	                        page:'1'
	                    }
	                });
    			}else{
    				loadScrollNew.refreshPage({
	                    params:{
	                        keyword:$('#needSearch').val(),
	                        field:'sales',
	                        sort:'desc',
	                        shopId:$("#shopId").val(),
	                        propertyGroup:ShopMain.getSelectedProp($newGds),
	                        page:'1'
	                    }
	                });
    			}
            }else if($(this).children('i').hasClass('drop-down')){
                $(this).children('i').removeClass('drop-down');
                $(this).children('i').addClass('drop-up');
                var objStr = $(this).parents(".GdsForm").attr('id');
    			if(objStr=="allGdsForm"){
	                loadScrollAll.refreshPage({
	                    params:{
	                        keyword:$('#needSearch').val(),
	                        field:'sales',
	                        sort:'asc',
	                        shopId:$("#shopId").val(),
	                        propertyGroup:ShopMain.getSelectedProp($allGds),
	                        page:'1'
	                    }
	                });
    			}else{
    				loadScrollNew.refreshPage({
                        params:{
                            keyword:$('#needSearch').val(),
                            field:'sales',
                            sort:'asc',
                            shopId:$("#shopId").val(),
                            propertyGroup:ShopMain.getSelectedProp($newGds),
                            page:'1'
                        }
                    });
    			}
            }
        }

    });

    $('.sortPrice').click(function(){
        if(!$(this).hasClass('selected')){
            $(this).addClass('selected');
            $(this).append("<i class=\"drop drop-up\"></i>");

            $('.sortSales').removeClass('selected');
            $('.sortSales').children().remove('i');
            $('.sortComprehensive').removeClass('selected');
            var objStr = $(this).parents(".GdsForm").attr('id');
			if(objStr=="allGdsForm"){
	            loadScrollAll.refreshPage({
	                params:{
	                    keyword:$('#needSearch').val(),
	                    field:'discountPrice',
	                    sort:'asc',
	                    shopId:$("#shopId").val(),
	                    propertyGroup:ShopMain.getSelectedProp($allGds),
	                    page:'1'
	                }
	            });
			}else{
				loadScrollNew.refreshPage({
	                params:{
	                    keyword:$('#needSearch').val(),
	                    field:'discountPrice',
	                    sort:'asc',
	                    shopId:$("#shopId").val(),
	                    propertyGroup:ShopMain.getSelectedProp($newGds),
	                    page:'1'
	                }
	            });
			}
        }else{
            //逆序
            if($(this).children('i').hasClass('drop-up')){
                $(this).children('i').removeClass('drop-up');
                $(this).children('i').addClass('drop-down');
                var objStr = $(this).parents(".GdsForm").attr('id');
    			if(objStr=="allGdsForm"){
	                loadScrollAll.refreshPage({
	                    params:{
	                        keyword:$('#needSearch').val(),
	                        field:'discountPrice',
	                        sort:'desc',
	                        shopId:$("#shopId").val(),
	                        propertyGroup:ShopMain.getSelectedProp($allGds),
	                        page:'1'
	                    }
	                });
    			}else{
    				 loadScrollNew.refreshPage({
 	                    params:{
 	                        keyword:$('#needSearch').val(),
 	                        field:'discountPrice',
 	                        sort:'desc',
 	                        shopId:$("#shopId").val(),
 	                        propertyGroup:ShopMain.getSelectedProp($newGds),
 	                        page:'1'
 	                    }
 	                });
    			}
            }else if($(this).children('i').hasClass('drop-down')){
                $(this).children('i').removeClass('drop-down');
                $(this).children('i').addClass('drop-up');
                var objStr = $(this).parents(".GdsForm").attr('id');
    			if(objStr=="allGdsForm"){
	                loadScrollAll.refreshPage({
	                    params:{
	                        keyword:$('#needSearch').val(),
	                        field:'discountPrice',
	                        sort:'asc',
	                        shopId:$("#shopId").val(),
	                        propertyGroup:ShopMain.getSelectedProp($allGds),
	                        page:'1'
	                    }
	                });
    			}else{
    				 loadScrollNew.refreshPage({
 	                    params:{
 	                        keyword:$('#needSearch').val(),
 	                        field:'discountPrice',
 	                        sort:'asc',
 	                        shopId:$("#shopId").val(),
 	                        propertyGroup:ShopMain.getSelectedProp($newGds),
 	                        page:'1'
 	                    }
 	                });
    			}
    				
            }
        }
    });

    $('.prop-item').click(function () {
        $(this).parent().children().removeClass('selected');
        $(this).addClass('selected');
    });
    //筛选侧边栏
    $('#filterSel',$allGds).click(function(){
        $('#filterContAll').offCanvas('open');
    });
    $('#filterSel',$newGds).click(function(){
        $('#filterContNew').offCanvas('open');
    });
    //重置
    $("#cancelNewSet").click(function(){
    	$(this).parents("#filterContNew").find(".prop-item").removeClass('selected');
	});
    $("#cancelAllSet").click(function(){
    	$(this).parents("#filterContAll").find(".prop-item").removeClass('selected');
	});
    $('#propBtnAll').click(function () {

        $('#filterContAll').offCanvas('close');
        loadScrollAll.refreshPage({
            params:ShopMain.getReqParam($allGds)
        });
    });
    $('#propBtnNew').click(function () {

        $('#filterContNew').offCanvas('close');

        loadScrollNew.refreshPage({
            params:ShopMain.getReqParam($newGds)
        });
    });
});
var ShopMain = {
		getSelectedProp : function (obj) {
			var objStr = $(obj).attr('id');
			var divs = "";
			if(objStr=="allGdsForm"){
				divs=$('#filter-items',$("#filterContAll")).children('div');
			}else if(objStr=="newGdsForm"){
				divs=$('#filter-items',$("#filterContNew")).children('div');
			}
		    var propertyGroup="[";
		    for (var i=0;i<divs.length;i++){
		        var div=divs[i];
		        var liArr=$(div).find('span.selected');
		        if(liArr!=null&&liArr.length>0){
		            if(i==0){
		                propertyGroup+="{";
		            }else{
		                propertyGroup+=",{";
		            }
		            propertyGroup+="\"propertyId\":\""+$(div).attr("code");
		            propertyGroup+="\",\"propertyValueIds\":\""+$(liArr[0]).attr("code");
		            propertyGroup+="\"}";
		        }
		    }

		    propertyGroup+="]";

		    return propertyGroup;
		},
		getReqParam : function (obj) {
			console.log("obj =   "+obj);
		    var div=$('.filter-head',obj).children('div .selected');
		    var span=$(div).children('span');
		    var i = $(div).children('i');
		    var field="";
		    var id=$(span).attr('id');
		    if(id=="sortComprehensive"){

		    }else if(id=="sortSales"){
		        field="sales";
		    }else if(id=="sortPrice"){
		        field="discountPrice";
		    }

		    var sort="";
		    if($(i).hasClass('drop-up')){
		        sort="asc";
		    }else if($(i).hasClass('drop-down')){
		        sort="desc";
		    }
		    console.log("paixxuuxu "+sort);
		    if($(obj).attr('id') == $("#newGdsForm").attr('id')){
		    	console.log("进入了 newGdsForm 中field="+field);
			    if(id=="sortComprehensiveNew"){

			    }else if(id=="sortSalesNew"){
			        field="sales";
			    }else if(id=="sortPriceNew"){
			        field="discountPrice";
			    }
		    	/*if(field!=""){
		    		field +=",updateTime";
		    	}else{
		    		field +="updateTime";
		    	}
		    	if(sort!=""){
		    		sort +=",desc";
		    	}else{
		    		sort +="desc";
		    	}*/
		    }
		    var keyword=$('#needSearch').val();
		    return {'field':field,'sort':sort,'keyword':keyword,'propertyGroup':ShopMain.getSelectedProp(obj),
		    	'shopId':$("#shopId").val()};
		},
		clickMeForPromInfo : function(){
			alert(3);
		}
};
$(function(){
	$("#cancelAllSet").click(function(){
    	$(this).parents("#filterCont").find(".prop-item").removeClass('selected');
	});
	var parseMoney = function(data){
		var str = (data/100).toFixed(2) + '';
		var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
		var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
		return ret = intSum + dot;
	};
	//是否收藏
	var getGdsCollect =  function($obj){
		$(".resultList",$obj).each(function() {
			var $this = $(this);
			var gdsId = $this.find("input[name=pGdsId]").val();
			var data = new Object();
			data.gdsId = gdsId;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsdetail/querygdscollect",
				data : data,
				success : function(json) {
					if (json.ifHavFav == '1') {
						$(".iconfont",$this).attr('collectId',json.collectId);
						$(".iconfont",$this).addClass("faved");
					} else {
						$(".iconfont",$this).removeClass("faved");
					}
				},
				error : function(e, xhr, opt) {
				}
			});
		});
	};
	//是否有，纸质书 1，电子书 2，数字教材 3
	var getBookOtherType =  function($obj){
		$(".resultList",$obj).each(function() {
			var $this = $(this);
			var gdsId = $this.find("input[name=pGdsId]").val();
			var skuId = $this.find("input[name=pSkuId]").val();
			var data = new Object();
			data.gdsId = gdsId;
			data.skuId = skuId;
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsdetail/queryBookOtherType",
				data : data,
				success : function(json) {
					if (json.bookOtherType == '1') {
						$(".p-type",$this).text("纸质书");
					}else if (json.bookOtherType == '2') {
						$(".p-type",$this).text("有电子书");
					}else if (json.bookOtherType == '3') {
						$(".p-type",$this).text("有数字教材");
					} else {
						$(".p-type",$this).text("");
					}
				},
				error : function(e, xhr, opt) {
				}
			});
		});
	};
	 var getPrice = function($obj){
		$(".resultList",$obj).each(function() {
			var $this = $(this);
			var gdsId = $this.find("input[name=pGdsId]").val();
			var skuId = $this.find("input[name=pSkuId]").val();
			var shopId = $this.find("input[name=pShopId]").val();
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
    $('#wrapper2').height($(window).height()
        -$('.am-header').height()
        -20
        -$('.filter-head').outerHeight()
        -parseInt($('.filter-head').css('margin-bottom'))
    );
    var loadScroll = new LoadScroll("wrapper2", {
        url:GLOBAL.WEBROOT + '/search/scroll',
        params :getReqParam(),
        preventDefault:true,
        isAjax:true,
        bindevent : function(){
        	getPrice($('#wrapper2'));
        	getGdsCollect($('#wrapper2'));	//是否收藏
        	getBookOtherType($('#wrapper2'));  //是否有其他类型
        }
    });

    $('#showType').click(function(){
    	 if($('.pro-list').hasClass('pro-list-grid')){
             $(this).html('<i class="iconfont icon-cls"></i>');
         }else{
             $(this).html('<i class="iconfont icon-all"></i>');
         }
         $('.pro-list').toggleClass('pro-list-grid');
    });

    $('#filterSel').click(function () {
        $('#filterCont').offCanvas('open');
        $("#filter-items").height($(window).height() - $('.btn-fix').outerHeight()-5);
        $(window).off('touchmove.iscroll');
    });
    $('#sortComprehensive').click(function(){
        if(!$(this).parent().hasClass('selected')){
            $(this).parent().addClass('selected');

            $('#sortSales').parent().removeClass('selected');
            $('#sortSales').parent().children().remove('i');
            $('#sortPrice').parent().removeClass('selected');
            $('#sortPrice').parent().children().remove('i');
            $('#sortDate').parent().removeClass('selected');
            $('#sortDate').parent().children().remove('i');
            loadScroll.refreshPage({
                params:{
                    keyword:$('#needSearch').val(),
                    field:'',
                    sort:'',
                    shopId:$("#shopId").val(),
                    propertyGroup:getSelectedProp(),
                    page:'1',
                    adid:$('#adid').val(),
                    category:$('#category').val()
                }
            });
        }
    });

    $('#sortSales').click(function(){
        if(!$(this).parent().hasClass('selected')){
            $(this).parent().addClass('selected');
            $(this).parent().append("<i class=\"drop drop-up\"></i>");

            $('#sortComprehensive').parent().removeClass('selected');
            $('#sortPrice').parent().removeClass('selected');
            $('#sortPrice').parent().children().remove('i');
            $('#sortDate').parent().removeClass('selected');
            $('#sortDate').parent().children().remove('i');
            
            loadScroll.refreshPage({
                params:{
                    keyword:$('#needSearch').val(),
                    field:'sales',
                    sort:'asc',
                    shopId:$("#shopId").val(),
                    propertyGroup:getSelectedProp(),
                    page:'1',
                    adid:$('#adid').val(),
                    category:$('#category').val()
                }
            });
        }else{
            //逆序
            if($(this).parent().children('i').hasClass('drop-up')){
                $(this).parent().children('i').removeClass('drop-up');
                $(this).parent().children('i').addClass('drop-down');

                loadScroll.refreshPage({
                    params:{
                        keyword:$('#needSearch').val(),
                        field:'sales',
                        sort:'desc',
                        shopId:$("#shopId").val(),
                        propertyGroup:getSelectedProp(),
                        page:'1',
                        adid:$('#adid').val(),
                        category:$('#category').val()
                    }
                });
            }else if($(this).parent().children('i').hasClass('drop-down')){
                $(this).parent().children('i').removeClass('drop-down');
                $(this).parent().children('i').addClass('drop-up');

                loadScroll.refreshPage({
                    params:{
                        keyword:$('#needSearch').val(),
                        field:'sales',
                        sort:'asc',
                        shopId:$("#shopId").val(),
                        propertyGroup:getSelectedProp(),
                        page:'1',
                        adid:$('#adid').val(),
                        category:$('#category').val()
                    }
                });
            }
        }

    });

    $('#sortPrice').click(function(){
        if(!$(this).parent().hasClass('selected')){
            $(this).parent().addClass('selected');
            $(this).parent().append("<i class=\"drop drop-up\"></i>");

            $('#sortSales').parent().removeClass('selected');
            $('#sortSales').parent().children().remove('i');
            $('#sortDate').parent().removeClass('selected');
            $('#sortDate').parent().children().remove('i');
            $('#sortComprehensive').parent().removeClass('selected');

            loadScroll.refreshPage({
                params:{
                    keyword:$('#needSearch').val(),
                    field:'discountPrice',
                    sort:'asc',
                    shopId:$("#shopId").val(),
                    propertyGroup:getSelectedProp(),
                    page:'1',
                    adid:$('#adid').val(),
                    category:$('#category').val()
                }
            });
        }else{
            //逆序
            if($(this).parent().children('i').hasClass('drop-up')){
                $(this).parent().children('i').removeClass('drop-up');
                $(this).parent().children('i').addClass('drop-down');

                loadScroll.refreshPage({
                    params:{
                        keyword:$('#needSearch').val(),
                        field:'discountPrice',
                        sort:'desc',
                        shopId:$("#shopId").val(),
                        propertyGroup:getSelectedProp(),
                        page:'1',
                        adid:$('#adid').val(),
                        category:$('#category').val()
                    }
                });
            }else if($(this).parent().children('i').hasClass('drop-down')){
                $(this).parent().children('i').removeClass('drop-down');
                $(this).parent().children('i').addClass('drop-up');

                loadScroll.refreshPage({
                    params:{
                        keyword:$('#needSearch').val(),
                        field:'discountPrice',
                        sort:'asc',
                        shopId:$("#shopId").val(),
                        propertyGroup:getSelectedProp(),
                        page:'1',
                        adid:$('#adid').val(),
                        category:$('#category').val()
                    }
                });
            }
        }
    });

    $('#sortDate').click(function(){
        if(!$(this).parent().hasClass('selected')){
            $(this).parent().addClass('selected');
            $(this).parent().append("<i class=\"drop drop-up\"></i>");

            $('#sortSales').parent().removeClass('selected');
            $('#sortSales').parent().children().remove('i');
            $('#sortPrice').parent().removeClass('selected');
            $('#sortPrice').parent().children().remove('i');
            $('#sortComprehensive').parent().removeClass('selected');

            loadScroll.refreshPage({
                params:{
                    keyword:$('#needSearch').val(),
                    field:'publishDate',
                    sort:'asc',
                    shopId:$("#shopId").val(),
                    propertyGroup:getSelectedProp(),
                    page:'1',
                    adid:$('#adid').val(),
                    category:$('#category').val()
                }
            });
        }else{
            //逆序
            if($(this).parent().children('i').hasClass('drop-up')){
                $(this).parent().children('i').removeClass('drop-up');
                $(this).parent().children('i').addClass('drop-down');

                loadScroll.refreshPage({
                    params:{
                        keyword:$('#needSearch').val(),
                        field:'publishDate',
                        sort:'desc',
                        shopId:$("#shopId").val(),
                        propertyGroup:getSelectedProp(),
                        page:'1',
                        adid:$('#adid').val(),
                        category:$('#category').val()
                    }
                });
            }else if($(this).parent().children('i').hasClass('drop-down')){
                $(this).parent().children('i').removeClass('drop-down');
                $(this).parent().children('i').addClass('drop-up');

                loadScroll.refreshPage({
                    params:{
                        keyword:$('#needSearch').val(),
                        field:'publishDate',
                        sort:'asc',
                        shopId:$("#shopId").val(),
                        propertyGroup:getSelectedProp(),
                        page:'1',
                        adid:$('#adid').val(),
                        category:$('#category').val()
                    }
                });
            }
        }
    });
    //如果从分类进来操作，加载动态搜索条件
    var category = $('#category').val();
    if(category!=null && category!=""){
//    	$("#allType").hide();
    	var html = PMPH_SEARCH2.getSearchCon(category);
    	$("#allType").after(html);
    }
    $('#catgChoose').click(function(){
    	var name = $(".ser-aType").find(".selected").text();
    	var id = $(".ser-aType").find(".selected").attr("id");
    	
    	var catlogName_html=name+'<em class="ui-arrow ui-arrow-right"></em>';
    	
    	$("#catlogName").html(catlogName_html);
    	$("#catlogId").val(id);
    	//加载动态搜索条件
    	$('div[name="searchConDiv"]').remove();
    	$('div[name="searchConDiv2"]').remove();
    	var html = PMPH_SEARCH2.getSearchCon(id,name);
    	$("#allType").after(html);
    	$('#allTypeCont').offCanvas('close');
    });
    $("#returnCanvas").click(function(){
    	$('#allTypeCont').offCanvas('close');
    });
    $("#saveType").click(function(){
    	$('#catgChoose').trigger('click');
    });
    $("#returnCanvas").click(function(){
    	$('#allTypeCont').offCanvas('close');
    });
    $('.prop-item').live("click",function () {
        $(this).parent().children().removeClass('selected');
        $(this).addClass('selected');
    });
    $('.filter-item-box').live("click",function () {
    	var code=$(this).attr('code');
        $('div[name="searchConDiv2"]').each(function () {
          	var thisCode=$(this).attr('code');
          	if(thisCode==code){
          		$(this).toggle(); 
          	}
        });
    });
    $('.service-item').click(function () {
    	var $this = $(this);
    	if($this.hasClass('selected')){
    		$this.removeClass('selected');
    	}else{
    		$this.addClass('selected');
    	}
    });
    
    $('#propClearBtn').click(function () {
    	$('#serviceGroup').children().removeClass('selected');
        $('#priceGroup').children().removeClass('selected');
        var catlogName_html='全部<em class="ui-arrow ui-arrow-right"></em>';
        $("#catlogName").html(catlogName_html);
    	$("#catlogId").val("");
    	$("#allType").show();
    	$("#allTypeCont").find('.selected').removeClass('selected');
    	$("#defaultType").addClass('selected');
    	$('div[name="searchConDiv"]').remove();
    	$('div[name="searchConDiv2"]').remove();
    });
    
    $('#moreSearch').live("click",function () {
//    	$('div[name="searchConDiv2"]').toggle(); 
    });
    
    $('#propBtn').click(function () {

        $('#filterCont').offCanvas('close');

        loadScroll.refreshPage({
            params:getReqParam()
        });

    });
   
});

var getSelectedProp=function () {
    var propertyGroup="%5B";//[ 左中括号的转义

    var divs=$('#filter-items').children('div');
    for (var i=0;i<divs.length;i++){
        var div=divs[i];
        if(div.id=="pmphService"){
        	var liArr=$(div).find('span.selected');
        	if(liArr.length > 0){
        		var serviceVal = "";
        		for(var j=0; j<liArr.length;j++){
        			var li = liArr[j];
        			serviceVal += $(li).attr("code")+",";
        		}
        		if(serviceVal != ""){
        			serviceVal = serviceVal.substring(0,serviceVal.length-1);
        		}
        		propertyGroup+="%7B";//{  左大括号的转义
        		propertyGroup+="\"propertyId\":\""+"pmphService";
        		propertyGroup+="\",\"propertyValueIds\":\""+serviceVal;
        		propertyGroup+="\"%7D";//}  右大括号的转义
        	}
        }else if(div.id=="priceCode"){
        	var liArr=$(div).find('span.selected');
        	if(liArr!=null&&liArr.length>0){
        		if(i==0){
        			propertyGroup+="%7B";
        		}else{
        			propertyGroup+=",%7B";
        		}
        		propertyGroup+="\"propertyId\":\""+$(div).attr("code");
        		propertyGroup+="\",\"propertyValueIds\":\""+$(liArr[0]).attr("code");
        		propertyGroup+="\"%7D";
        	}
        }else if(div.id == "allType"){
        	var catlogId = $("#catlogId").val();
        	if(catlogId!=null && catlogId!=""){
        		propertyGroup+=",%7B";
        		propertyGroup+="\"propertyId\":\""+"categories";
        		propertyGroup+="\",\"propertyValueIds\":\""+catlogId;
        		propertyGroup+="\"%7D";
        	}
        }else{
        	var liArr=$(div).find('span.selected');
        	if(liArr!=null&&liArr.length>0){
        		if(i==0){
        			propertyGroup+="%7B";
        		}else{
        			propertyGroup+=",%7B";
        		}
        		propertyGroup+="\"propertyId\":\""+$(div).attr("code");
        		propertyGroup+="\",\"propertyValueIds\":\""+$(liArr[0]).attr("code");
        		propertyGroup+="\"%7D";
        	}
        }
        
    }
    //如果查询作者相关书籍
    var authorName = $("#authorName").val();
    if(authorName!=null && authorName!=""){
    	propertyGroup+=",%7B";
		propertyGroup+="\"propertyId\":\""+"authorNameService";
		propertyGroup+="\",\"propertyValueIds\":\""+authorName;
		propertyGroup+="\"%7D";
    }
    
    propertyGroup+="%5D";//］ 右中括号的转义

    return propertyGroup;
}

var getReqParam=function () {
    var div=$('.filter-head').children('div .selected');
    var span=$(div).children('span');
    var field="";
    var id=$(span).attr('id');
    if(id=="sortComprehensive"){

    }else if(id=="sortSales"){
        field="sales";
    }else if(id=="sortPrice"){
        field="discountPrice";
    }else if(id=="sortDate"){
    	field="pubDate"
    }

    var sort="";
    if($(div).children('i').hasClass('drop-up')){
        sort="asc";
    }else if($(div).children('i').hasClass('drop-down')){
        sort="desc";
    }

    var keyword=$('#needSearch').val();
    var adid = $('#adid').val();
    if(adid==null || adid==0){
    	return {'field':field,'sort':sort,'keyword':keyword,'propertyGroup':getSelectedProp(),"shopId":$("#shopId").val(),"category":$('#category').val()};
    }else{
    	return {'field':field,'sort':sort,'keyword':keyword,'propertyGroup':getSelectedProp(),"shopId":$("#shopId").val(),"category":$('#category').val(),"adid":adid};
    }
}

/*==================================================人卫二期搜索改造start======================================================*/

function addCollect(skuId){
	PMPH_SEARCH2.addCollect(skuId);
}

$(".item-cont i.iconfont").live("click",function(event){
		var skuId = $(this).data("skuId");
		PMPH_SEARCH2.addCollect(skuId,$(this));
});
var PMPH_SEARCH2 = {
		/**
		 * 添加收藏
		 */
		addCollect : function(skuId,$this){
			var _collect = 0;
			if(_collect == 1){
				return;
			}
			_collect = 1;
			var collectId = "";
			if($this.attr("collectId") != undefined && $this.attr("collectId")!= ""){
				collectId = $this.attr("collectId");
			}
			$.eAjax({
				url : GLOBAL.WEBROOT + "/addCollection/add",
				data : {
					"skuId" : skuId,
					"collectId" : collectId
				},
				success : function(returnInfo) {
					if (returnInfo.resultFlag == 'ok') {
						$this.attr("collectId",returnInfo.resultMsg);
						if(returnInfo.resultMsg==""){
							$this.removeClass("faved");
							new AmLoad({content:'取消收藏商品成功！'});
						}else{
							$this.addClass("faved");
							new AmLoad({content:'收藏商品成功！'});
						}
					} else {
						new AmLoad({content:returnInfo.resultMsg,type:'2'});
					}
					_collect = 0;
				}
			});
		},
		getSearchCon : function(categoryCode,categoryName){
			var url = GLOBAL.WEBROOT + "/search/getSearchCon";
			var param = {};
			param.catgCode = categoryCode;
			var html = "";
			var nodes = "";
			$.eAjax({
				url:url,
				dataType : "text",
				async : false,
				data : param,
				success : function(data) {
					if(data != "" && data != null){
						nodes = eval(data);
					}
				},
				error: function(){
					alert("error");
				}
			});
			var name="全部";
			if(categoryName!=null&&categoryName.length>0){
				name=categoryName;
			}else{
				var catgName=$('#catgName').val();
				if(catgName!=null&&catgName.length>0){
					name=catgName;
				}
			}
			$('#catlogId').val(categoryCode);
			var catlogName_html=name+'<em class="ui-arrow ui-arrow-right"></em>';
			$("#catlogName").html(catlogName_html);
			if(nodes != ""){
				for(var i in nodes){
					var node = nodes[i];
					var vals = node.values;
					if(vals != null && vals != ""){
						html += '<div class="filter-item filter-item-box" name="searchConDiv" code='+node.id+'>'+
						'<p id="moreSearch"><span class="" id="catlogName">'+node.propName+'</span>'+
                        '<span class="pull-right"><em class="ui-arrow ui-arrow-bottom"></em>'+
                        '</span></p></div>';
						var items = eval(vals);
						html += '<div class="filter-item " name="searchConDiv2" code='+node.id+'>'+
						"<p></p>"+
						'<div class="ui-selBox" name="searchConGroup">';
						var items = eval(vals);
						for(var n in items){
							html += '<span class="prop-item" code="'+items[n].id+'">'+items[n].propValue+'</span>';
						}
						html += "</div></div>";
					}
				}
			}
			return html;
		}
}

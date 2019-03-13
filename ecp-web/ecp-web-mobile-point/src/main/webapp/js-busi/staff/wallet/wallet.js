$(function(){
	 
    var commonLoadingScroll = function(wrapper,scrollCont){
    	 var index=1;
    	$('#'+wrapper).height($(window).height()
                -$('.am-header').height()
                -20
                -$('.am-tabs-nav').outerHeight()
                -parseInt($('.am-tabs-nav').css('margin-bottom'))
        );

        var loadScroll = new LoadScroll(wrapper, {
            url:  GLOBAL.WEBROOT + "/wallet/querywallet",
            isAjax:true,
            params: {
                page: 1,
                count:1
            },
    		callback:function(obj,data){
    			//最多给加载100条数据就可以了
                obj.total=100;
                if(index==1){
                    index++;
                    $('.'+scrollCont).empty();
                }
    			var html = "";
    			$.each(data.values.resultList, function(i, n) {
    				 html += "<li>"+
		                     "<div class='img-wrap'>"+
		                         "<img src='"+LogoPath+"' alt='"+n.shopName+"'/>"+
		                    " </div>"+
		                     "<div class='cont'>"+
		                        " <p class='name'>"+n.shopName+"</p>"+
		                        " <p class='discount'>"+n.acctTypeName+"</p>"+
		                    " </div>"+
		                    " <div class='price'><em>¥</em>"+n.balance+"</div>"+
		                 "</li>";
    			});
                $('.'+scrollCont).append(html);
            }
        });
    };
    commonLoadingScroll('wrapper1','scrollCont1');
});
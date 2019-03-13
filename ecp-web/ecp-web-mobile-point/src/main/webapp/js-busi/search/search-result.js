var sort_param = "";
var range_type = "";
$(function(){
    $('#wrapper2').height($(window).height()
        -$('.am-header').height()
        -20
        -$('.filter-head').outerHeight()
        -parseInt($('.filter-head').css('margin-bottom'))
    );
    var loadScroll = new LoadScroll("wrapper2", {
        url:GLOBAL.WEBROOT + '/search/scroll',
        params :getReqParam(),
        isAjax:true,
    });
    //采用一行两列展示
    //$('.pro-list').toggleClass('pro-list-grid');
    
    //排序
    $(".pxlist li").click(function () {
		if($(this).parent().hasClass('score-sort')){
			sort_param = $(this).attr("sort");    //获取每个li的value值
		}else if($(this).parent().hasClass('score-range')){//范围查询
			range_type = $(this).attr("_type");    //获取每个li的value值
		}
		searchConditon();
	});
    //根据条件过滤查询
    var searchConditon = function(){
    	loadScroll.refreshPage({
            params:{
                keyword:$('#needSearch').val(),
                field:'score',
                sort:sort_param,
                rangeType:range_type,
                propertyGroup:getSelectedProp(),
                page:'1',
                category:$('#category').val()
            }
        });
    }
   $('.prop-item').click(function () {
        $(this).parent().children().removeClass('selected');
       $(this).addClass('selected');
    })

    $('#propBtn').click(function () {

        $('#filterCont').offCanvas('close');

        loadScroll.refreshPage({
            params:getReqParam()
        });

    })
    
});

var getSelectedProp=function () {
    var propertyGroup="[";

    var divs=$('#filter-items').children('div');
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
}

var getReqParam=function () {
    var keyword=$('#needSearch').val();
    return {'field':'score','sort':sort_param,'rangeType':range_type,'keyword':keyword,'propertyGroup':getSelectedProp(),"category":$('#category').val(),"adid":$('#adid').val()};
}

$(function () {

    $('.filter-head .column').click(function (e) {
//        $(this).addClass('sealist').siblings().removeClass('sealist');
//        $('.filter-head .column .pxlist').css('display','none');
//        if ($(this).parent().find('.sealist').size() > 0) {
//            $(".sealist .pxlist").toggle();
//        }
        if($(this).children("ul").css('display')=="block"){
        	$(this).addClass('sealist').siblings().removeClass('sealist');
        	$('.filter-head .column .pxlist').css('display','none');
        	$(this).children("ul").css('display','none');
        	$(".model-zz").css('display','none');
        }else{
			$(this).addClass('sealist').siblings().removeClass('sealist');
        	$('.filter-head .column .pxlist').css('display','none');
        	$(this).children("ul").css('display','block');
        	$(".model-zz").css('display','none');
        }
        //$(this).parents(".ui-content").find(".model-zz").toggle();
        e.stopPropagation();
    });
    $(".pxlist li").click(function (e) {
        $(this).addClass("sel-x").siblings().removeClass("sel-x");
        $(".sealist .selected >span").text($(this).text());
        $(this).parent(".pxlist").hide();
        $(this).parents(".ui-content").find(".model-zz").hide();
        e.stopPropagation();
    });
    $(document).click(function () {
        $(".pxlist").hide();
        $(".model-zz").hide();
    });
    
})
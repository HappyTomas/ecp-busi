$(function(){
	
	
	$('#slideButtomBtn').click(function () {
        $('#slideButtom .am-offcanvas-bar').height($('.am-offcanvas-content').outerHeight());
    });
    $('.icon-close').click(function () {
        $('#slideButtom').offCanvas('close');
    });
    //异步加载好评、中评、差评
    $.eAjax({
		url : GLOBAL.WEBROOT + "/gdsdetail/querygdsevallevel",
		data : {
			"gdsId" : $("#gdsId").val(),
			"skuId" : $("#skuId").val()
		},
		success : function(returnInfo) {
			$("#highPraise").text(returnInfo.highPraise);
			$("#middlePraise").text(returnInfo.middlePraise);
			$("#badPraise").text(returnInfo.badPraise);
		}
	});
    
    
    
    var commonLoadingScroll = function(wrapper,scrollCont,level){
    	 var index=1;
    	$('#'+wrapper).height($(window).height()
                -$('.am-header').height()
                -20
                -$('.am-tabs-nav').outerHeight()
                -parseInt($('.am-tabs-nav').css('margin-bottom'))
        );

        var loadScroll = new LoadScroll(wrapper, {
            url:  GLOBAL.WEBROOT + "/gdsdetail/querygdsevalscroll",
            isAjax:true,
            params: {
    			"gdsId" : $("#gdsId").val(),
    			"skuId" : $("#skuId").val(),
    			"evalLeval" : level,
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
    			$.each(data.values.datas, function(i, n) {
    				 html += "<div class='pro-ctBox'>"+
                        "<div class='head'>"+
                            "<div class='hmg'>"+
                                "<img src='"+n.custPic+"' alt=''/>"+
                            "</div>"+
                            "<div class='name'>"+n.staffName+"</div>"+
                            "<span class='time'>"+ebcDate.dateFormat(n.evaluationTime,'yyyy-MM-dd hh:mm')+"</span>"+
                        "</div>"+
                        "<div class='body'>"+
                            "<div class='ctPoint'>"+
                                "<i class='eve-star"+n.score+"'></i>"+
                            "</div>"+
                            "<div class='ctTxt'>"+
                    			""+n.detail+""+
                            "</div>"+(function(){
                            	var temp = "";
                            	$.each(n.evalReplyRespDTOs,function(j,m){
                            		if(m.replyType==2){
                            			temp  +=
                            				"<div class='add'>"+
                            				"<span class='add-color'>[掌柜评论]</span>"+
                            				""+m.detail+""+
                            				"</div>";
                            		}else{
                            			temp  +=
                            				"<div class='add'>"+
                            				"<span class='add-color'>["+m.staffName+"]</span>"+
                            				""+m.detail+""+
                            				"</div>";
                            		}
                            	})
                            	return temp;
                            })()
                            /*"<div class='info'>"+
                                "<div><label>购买日期：</label><span>"+ebcDate.dateFormat(n.buyTime,'yyyy-MM-dd hh:mm')+"</span></div>"+
                    		"</div>"+*/
                        +"</div>"+
                    "</div>";
    			
    			});
                $('.'+scrollCont).append(html);
            }
        });
    };
    commonLoadingScroll('wrapper0','scrollCont0',0);
    commonLoadingScroll('wrapper1','scrollCont1',1);
    commonLoadingScroll('wrapper2','scrollCont2',2);
    commonLoadingScroll('wrapper3','scrollCont3',3);
    $(".am-tabs-nav a").click(function(){
    	var obj = $(this).attr('scrollCont');
    	if($.trim($("."+obj).html())!=""){
    		return;
    	}
    	 commonLoadingScroll($(this).attr('wrapper'),$(this).attr('scrollCont'),$(this).attr('level'));
    });
});
$(function(){
    // 关闭左侧浮动
    $(".close-icon").click(function(){
        $(".toolbar").hide();
        $(".tab-bar li").removeClass("selected");
        $(".tpl-main").css({
            "padding-left":"0px",
            //"width":"100%",
            //"margin-left":"-230px",
        });

    });

    var tab_li=$(".tab-bar li");
    tab_li.click(function(){
        if(!$(this).hasClass("disabled")){

            var index=tab_li.index($(this));

            $(".tab-bar li").removeClass("selected");
            $(this).addClass("selected");

            $(".toolbar").show();
            $(".toolbar dd").removeClass("selected");
            $(".toolbar dd").eq(index).addClass("selected");
            $(".tpl-main").css({
                "padding-left":"235px",
                //"width":"963px",
                //"width":"100%",
                //"margin-left":"0px",

            });
        }
    });

    var nav_li=$(".div3 li a");
    nav_li.click(function(){
        var index=nav_li.index($(this));
        $(".div3 li a").removeClass("active");
        $(this).addClass("active");
        $(".div4 dl dd").hide();
        $(".div4 dl dd").eq(index).show();
    });
});
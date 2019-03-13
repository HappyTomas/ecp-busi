$(function(){
	$(".top-left","header").unbind("click").bind("click",function(){
		history.go(-1);
	});
	//分类展示区的高度
	var showHeight = $(window).height() - (+$(".am-header").outerHeight()||0);
	
	//设置左侧栏高度方法
	var setNavHeight = function(){
		var bdHeight = +$(".am-tabs-bd").outerHeight()||0;
		$(".am-tabs-nav").css("min-height",(showHeight >= bdHeight ? showHeight:bdHeight)+"px");
	}
	
	//初始化左侧高度
	setNavHeight();
	//设置左侧栏点击事件
	$("li",".am-tabs-nav").bind("click",function(){
		var $this = $(this);
		//如果当前tab不是激活状态并且分类为获取则获取数据
		if(!$this.hasClass("am-active") && !$this.hasClass("loaded")){
			$this.addClass("loaded");
			var catgId = $this.attr("catg-id");
			$.eAjax({
				url : $webroot + "category/getcatgsons",
				data : {id:catgId},
				dataType : "html",
				success : function(returnInfo){
					$("#tabs-bd"+$this.attr("tab-no"),".am-tabs-bd").html(returnInfo);
					setNavHeight();
				},
				error : function(e,xhr,opt){
					$(".am-tabs-bd").children("am-active").html('<div class="pro-empty">请求出错！</div>');
				},
				exception : function(msg){
					$(".am-tabs-bd").children("am-active").html('<div class="pro-empty">请求异常！</div>');
				}
			});
		}else{
			setNavHeight();
		}
	});
});

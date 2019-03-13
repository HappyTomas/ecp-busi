$(function(){
	//搜索关键字回填到搜索框
	$("#searchTxt").val($("#searchingTxt").val());
	
	//搜索过滤条件事件绑定
	$('.input-down').hover(function(){
        $(this).find('.i-line').addClass('i-show');
        $(this).find('.select-icon').addClass('s-icon-down');
    },function(){
        $(this).find('.i-line').removeClass('i-show');
        $(this).find('.select-icon').removeClass('s-icon-down');
    });
    
    //排序事件和范围查询事件
	$('.input-down .i-line p').live('click',function(){
		
		//积分排序
		if($(this).parent().hasClass('score-sort')){
			$(this).parent().parent().find('.input-line-text').val($(this).html());
			$(this).parent().attr('sort',$(this).attr('sort'));
			$gdsListRefresh.refresh({"pageNumber":1});
		}else if($(this).parent().hasClass('score-range')){//范围查询
			$(this).parent().parent().find('.input-line-text').val($(this).html());
			$(this).parent().attr('_type',$(this).attr('_type'));
			$gdsListRefresh.refresh({"pageNumber":1});
		}
		
	});
	
    //分类树叶子分类点击事件
	$('#leafCate ul a').live("click",function(){
		
		if($(this).parent().hasClass('current')){
			return ;
		}
		
		//去除之前选中标识
		$('#leafCate ul li').removeClass('current');
		//添加分类选中标识
		$(this).parent().addClass('current');
		
		//刷新分页
		$gdsListRefresh.refresh({"pageNumber":1});
		
	});
       
});

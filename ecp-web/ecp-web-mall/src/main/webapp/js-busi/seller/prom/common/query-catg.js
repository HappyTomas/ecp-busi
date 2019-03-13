$(function() {
	

	// 分类回填
	$('#mainCatgsName').mcDropDown({
		backfillCatgName : 'mainCatgsName',
		backfillCatgCode : 'platCatgs'
	});
	//初始化分类下拉
	$('#mainCatgsName').mcDropDown.change();
	
	//让分类不能被选中
	$("#mainCatgsName").focus(function(){
		$(this).blur();
		});
});
//新增修改flooradvertise JS处理
$(function(){
	
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到floor列表查询页面
		var floorId = $("#floorId").val();
		//var url = $webroot+'floorgds/grid?floorId='+floorId;
		//window.location.href = url;
		var searchParams = $("#searchParams").val();
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"floorId":floorId,
			"searchParams":searchParams,
			"floorSearchParams":floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorgds/grid',
			"params" : params,
			"method" :"post"
		});
	});
	
});

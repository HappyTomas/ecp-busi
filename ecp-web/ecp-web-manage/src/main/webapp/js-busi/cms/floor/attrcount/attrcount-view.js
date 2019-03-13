//新增修改flooradvertise JS处理
$(function(){

	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到floor列表查询页面
		var floorId = $("#floorId").val();
		//var url = $webroot+'floorattrcount/grid?floorId='+floorId;
		//window.location.href = url;
		var searchParams = $("#searchParams").val();
		var params = {
			"floorId":floorId,
			"searchParams":searchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorattrcount/grid',
			"params" : params,
			"method" :"post"
		});
	});
	
});

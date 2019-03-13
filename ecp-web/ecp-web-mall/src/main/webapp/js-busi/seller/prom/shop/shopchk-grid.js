$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#myPromForm');
			});
			
			$('#shopCheckQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#shopCheckQueryBtn').click(function() {
						var siteId = $('#siteId').val();
						var shopId = $('#shopId').val();
						var promTypeCode = $('#promTypeCode').val();
						var status = $('#status').val();
						var startTime = $('#startTime').val();
						var endTime = $('#endTime').val();
						$('#shopCheckListDiv').load(GLOBAL.WEBROOT + '/seller/shopchk/gridlist', {"siteId":siteId,"shopId":shopId,"promTypeCode":promTypeCode
							,"status":status,"startTime":startTime,"endTime":endTime});
					});
			
			//初始化加载load数据
			$('#shopCheckQueryBtn').click();
			
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		// 指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});
function verify(id){
	if(id){
		window.location.href = GLOBAL.WEBROOT +"/seller/shopchk/edit/"+id;
	}
}
	function detail(id){
		if(id){
		window.location.href = GLOBAL.WEBROOT + "/seller/shopchk/detail/"+id;
	}
		}
	
	
	


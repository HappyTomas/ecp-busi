$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

		};
		return {
			init : init
		};

	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});
var discountRuleFun = {
	//保存 提交验证
	valid : function() {
		var buyX = parseInt($("#buyX").val());
		var sendY = parseInt($("#sendY").val());
		var _reData = new Object();
		_reData.value = false;//返回默认 失败
		if (buyX <= sendY) {
			eDialog.alert('配置的免费数量大于等于购买数量，不合法！', null);
			_reData.value = false;//返回默认 失败
			return _reData;
		}
		_reData.value = true;
		return _reData;
	}
};
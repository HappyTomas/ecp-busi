$(function() {
	var url = '/goods/stockrep/saveAddRep';

	// 保存仓库
	$('#btnFormSave').click(function() {
		// 获取仓库名称
		var _repName = $("input[name=repname]").val();
		// 新增适用范围数组
		var _adaptArray = new Array();
		// 获取选中的省份
		$("a.selCityArea").parent()
				.find("input[type=checkbox]:checked:enabled").each(function() {
							var _adaptArea = new Object();
							_adaptArea.adaptProvince = $(this).val();
							_adaptArray.push(_adaptArea);

						});

		$("a.selCityArea").parent().find("input[type=hidden]").each(function() {

			var _citys = $(this).attr("citys");
			if ("" == _citys) {
				return;
			}
			var _provinceCode = $(this).parent().find("input[type=checkbox]")
					.val();
			$.each(_citys.split(','), function(n, value) {
						var _adaptArea = new Object();
						_adaptArea.adaptProvince = _provinceCode;
						_adaptArea.adaptCity = value;
						_adaptArray.push(_adaptArea);
					});

		});
		var _param = new Object();
		_param.repName = _repName;
		var _adaptParam = "[";
		$.each(_adaptArray, function(n, value) {
					if (value.adaptCity) {
						_adaptParam = _adaptParam + "{'adaptCity':'"
								+ value.adaptCity + "','adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'ZH'},";

					} else {
						_adaptParam = _adaptParam + "{'adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'ZH'},";
					}
				});
		_adaptParam = _adaptParam + "]";
		_param.newStockRepAdaptVOsStr = _adaptParam;
		$.eAjax({
					url : GLOBAL.WEBROOT + url,
					data : _param,
					success : function(returnInfo) {
						eDialog.success('仓库新增成功！', {
									buttons : [{
										caption : "确定",
										callback : function() {
											window.location.href = $webroot
													+ '/goods/stockrep/pageInit';
										}
									}]
								});

					}
				});

	});
	// 省份选框选择时，清空已选地市列表

	$("a.selCityArea").parent().find("input[type=checkbox]").click(function() {
				var _cityE = $(this).parent().find("input[type=hidden]");
				_cityE.attr("citys", "");

			});

	$("a.selCityArea").click(function(o) {
		// 获取所选的省份编码
		var _provinceCode = $(this).parent().find("input[type=checkbox]").val();
		// 获取查看的仓库id
		var _repCode = $("input[name=id]").val();
		var _cityE = $(this).parent().find("input[type=hidden]");
		// 判断当前省份是否被选中
		var _ifCheck = $(this).parent().find("input[type=checkbox]")
				.prop("checked");
		var _shopId = $("input[name=shopId]").val();

		bDialog.open({
					title : '选择地市窗口',
					width : 500,
					height : 400,
					url : GLOBAL.WEBROOT + "/goods/stockrep/selCheckCityAdapt?"
							+ "provinceCode=" + _provinceCode + "&repCode="
							+ _repCode + "&shopId=" + _shopId,
					params : {
						"ifCheck" : _ifCheck,
						"provinceCode" : _provinceCode,
						"cityList" : _cityE.attr("citys"),
						"shopId" : _shopId
					},
					callback : function(data) {
						// var _cityElement =
						// $("#"+data.results[0].provinceCode);
						//
						// if (data && data.results && data.results.length > 0)
						// {
						// _cityElement.attr("citys",
						// data.results[0].citySelParam.cityArray);
						//							
						// if (data.results[0].citySelParam.selSize == 0) {
						// // var s =
						// // _cityE.parent().find("img");
						// _cityElement.parent().find("img")
						// .hide();
						// _cityElement
						// .parent()
						// .find(
						// "input[type=checkbox]")
						// .show();
						// } else {
						// _cityElement.parent().find("img")
						// .show();
						// _cityElement
						// .parent()
						// .find(
						// "input[type=checkbox]")
						// .hide();
						//
						// }

						// }
					}
				});

	});
	$('#btnReturn').click(function() {
				window.location.href = GLOBAL.WEBROOT
						+ '/goods/stockrep/pageInit';
			});
});
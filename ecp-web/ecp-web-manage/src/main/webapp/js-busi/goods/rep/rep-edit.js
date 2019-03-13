$(function() {
	var url = '/goods/stockrep/saveEditRep';

	// 保存仓库
	$('#btnFormSave').click(function() {
		if (!$("#repEditForm").valid())
			return false;
		// 获取仓库名称
		var _repName = $("input[name=repname]").val();
		// 获取仓库id
		var _id = $("input[name=id]").val();

		// 新增适用范围数组
		var _adaptNewArray = new Array();
		// 删除的适用范围数组
		var _adaptDelArray = new Array();

		$("a.selCityArea").parent().find("input[type=checkbox]:enabled").each(
				function() {
					// 获取此次编辑变更为选中的省份
					if ($(this).parent().find("input[type=hidden]")
							.attr(	"ifHasOver") != "true"
							&& $(this).attr("checked") == "checked") {
						var _adaptArea = new Object();
						_adaptArea.adaptProvince = $(this).val();
						_adaptNewArray.push(_adaptArea);
					}
					// 获取此次编辑取消的省份
					if ($(this).parent().find("input[type=hidden]")
							.attr(	"ifHasOver") == "true"
							&& $(this).attr("checked") != "checked") {
						var _adaptArea = new Object();
						_adaptArea.adaptProvince = $(this).val();
						_adaptDelArray.push(_adaptArea);
					}
				});

		$("a.selCityArea").parent().find("input[type=hidden]").each(function() {
			// 获取新增的适用范围
			var _newCitys = $(this).attr("newCitys");
			if ("" != _newCitys) {

				var _provinceCode = $(this).parent()
						.find(	"input[type=checkbox]").val();
				$.each(_newCitys.split(','), function(n, value) {
							var _adaptArea = new Object();
							_adaptArea.adaptProvince = _provinceCode;
							_adaptArea.adaptCity = value;
							_adaptNewArray.push(_adaptArea);
						});
			}
			// 获取删除的适用范围
			var _delCitys = $(this).attr("delCitys");
			if ("" != _delCitys) {

				var _provinceCode = $(this).parent()
						.find(	"input[type=checkbox]").val();
				$.each(_delCitys.split(','), function(n, value) {
							var _adaptArea = new Object();
							_adaptArea.adaptProvince = _provinceCode;
							_adaptArea.adaptCity = value;
							_adaptDelArray.push(_adaptArea);
						});
			}

		});
		var _param = new Object();
		_param.repName = _repName;
		_param.id = _id;
		// 组装新增适用范围参数
		var _adaptNewParam = "[";
		$.each(_adaptNewArray, function(n, value) {
					if (value.adaptCity) {
						_adaptNewParam = _adaptNewParam + "{'adaptCity':'"
								+ value.adaptCity + "','adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'156'},";
					} else {
						_adaptNewParam = _adaptNewParam + "{'adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'156'},";
					}
				});
		_adaptNewParam = _adaptNewParam + "]";
		_param.newStockRepAdaptVOsStr = _adaptNewParam;
		// 组装删除的适用范围参数
		// 组装新增适用范围参数
		var _adaptDelParam = "[";
		$.each(_adaptDelArray, function(n, value) {
					if (value.adaptCity) {
						_adaptDelParam = _adaptDelParam + "{'adaptCity':'"
								+ value.adaptCity + "','adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'156'},";
					} else {
						_adaptDelParam = _adaptDelParam + "{'adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'156'},";
					}
				});
		_adaptDelParam = _adaptDelParam + "]";
		_param.delStockRepAdaptVOsStr = _adaptDelParam;
		var _shopId = $("select[name=shopId]").val();
		_param.shopId = _shopId;
		$.gridLoading({
					"message" : "正在保存数据中...."
				});
		$.eAjax({
					url : GLOBAL.WEBROOT + url,
					data : _param,
					success : function(returnInfo) {
						eDialog.success('仓库编辑成功！', {
									buttons : [{
										caption : "确定",
										callback : function() {
											window.location.href = $webroot
													+ '/goods/stockrep/pageInit';
										}
									}]
								});

					},
					exception : function() {
						$.gridUnLoading();
					}
				});

	});
	// 省份选框选择时，清空已选地市列表

	$("a.selCityArea").parent().find("input[type=checkbox]").click(function() {
				var _cityE = $(this).parent().find("input[type=hidden]");
				_cityE.attr("newCitys", "");

			});

	$("a.selCityArea").each(function(i, e) {
		$(this).click(function(o) {
			// 获取所选的省份编码
			var _provinceCode = $(this).parent().find("input[type=checkbox]")
					.val();
			// 获取编辑的仓库id
			var _repCode = $("input[name=id]").val();

			// 判断当前省份是否被选中
			var _ifCheck = $(this).parent().find("input[type=checkbox][name='"
							+ _provinceCode + "']").prop("checked");
			// 获取此次已经选的地市编码
			var _cityE = $("#" + _provinceCode);
			var _shopId = $("select[name=shopId]").val();

			bDialog.open({
				title : '选择地市窗口',
				width : 500,
				height : 400,
				url : GLOBAL.WEBROOT
						+ "/goods/stockrep/selEditCityAdapt?provinceCode="
						+ _provinceCode + "&repCode=" + _repCode + "&shopId="
						+ _shopId,
				params : {
					"ifCheck" : _ifCheck,
					"provinceCode" : _provinceCode,
					"newCityList" : _cityE.attr("newCitys"),
					"delCityList" : _cityE.attr("delCitys"),
					"shopId" : _shopId
				},
				callback : function(data) {

					if (data && data.results && data.results.length > 0) {
						var _cityElement = $("#" + data.results[0].provinceCode);

						var _ifCheck = _cityElement.parent()
								.find(	"input[type=checkbox]").prop("checked");

						if (true != _ifCheck) {
							_cityElement.attr("newCitys",
									data.results[0].citySelParam.newCityArray);
							_cityElement.attr("delCitys",
									data.results[0].citySelParam.delCityArray);
						}
						if (data.results[0].citySelParam.selSize == 0) {
							// var s =
							// _cityE.parent().find("img");
							_cityElement.parent().find("img").hide();
							_cityElement.parent().find("input[type=checkbox]")
									.show();
						} else {
							_cityElement.parent().find("img").show();
							_cityElement.parent().find("input[type=checkbox]")
									.hide();

						}
					}

				}
			});
		});
	});

	$('#btnReturn').click(function() {
				window.location.href = GLOBAL.WEBROOT
						+ '/goods/stockrep/pageInit';
			});

});
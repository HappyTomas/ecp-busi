$(function() {

	// 保存仓库
	$('#btnFormSave').click(function(e) {
				RepAdd.saveFunction(e);

			});

	RepAdd.initAdaptPage();
	$("select[name=shopId]").live("change", function(e) {
    RepAdd.initAdaptPage();
			});

	// 省份选框选择时，清空已选地市列表

	$("a.selCityArea").parent().find("input[type=checkbox]").live("click",function() {
				var _cityE = $(this).parent().find("input[type=hidden]");
				_cityE.attr("citys", "");

			});

	$("a.selCityArea").live("click",function(o) {
		// 获取所选的省份编码
		var _provinceCode = $(this).parent().find("input[type=checkbox]").val();
		var _cityE = $(this).parent().find("input[type=hidden]");
		// 判断当前省份是否被选中
		var _ifCheck = $(this).parent().find("input[type=checkbox]")
				.prop("checked");
		var _shopId = $("select[name=shopId]").val();

		bDialog.open({
					title : '选择地市窗口',
					width : 500,
					height : 400,
					url : GLOBAL.WEBROOT + "/goods/stockrep/selAddCityAdapt?"
							+ "provinceCode=" + _provinceCode + "&shopId="
							+ _shopId,
					params : {
						"ifCheck" : _ifCheck,
						"provinceCode" : _provinceCode,
						"cityList" : _cityE.attr("citys"),
						"shopId" : _shopId
					},
					callback : function(data) {
						var _cityElement = $("#" + data.results[0].provinceCode);

						if (data && data.results && data.results.length > 0) {
							_cityElement.attr("citys",
									data.results[0].citySelParam.cityArray);

							if (data.results[0].citySelParam.selSize == 0) {
								// var s =
								// _cityE.parent().find("img");
								_cityElement.parent().find("img").hide();
								_cityElement.parent()
										.find("input[type=checkbox]").show();
							} else {
								_cityElement.parent().find("img").show();
								_cityElement.parent()
										.find("input[type=checkbox]").hide();

							}

						}
					}
				});

	});
	$('#btnReturn').live("click",function() {
				window.location.href = GLOBAL.WEBROOT
						+ '/goods/stockrep/pageInit';
			});

});

var RepAdd = {

	initAdaptPage : function() {
		var _shopId = $("select[name=shopId]").val();
		var _param = new Object();
		_param.shopId = _shopId;
		$.eAjax({
					url : GLOBAL.WEBROOT + "/goods/stockrep/addAdaptListInit",
					data : _param,
					dataType:"html",
					success : function(returnInfo) {
						$("#selAdapt").empty();
						$("#selAdapt").html(returnInfo);

					}				
				});

	},

	saveFunction : function(e) {
		if (!$("#repAddForm").valid())
			return false;
		var url = '/goods/stockrep/saveAddRep';
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
								+ "','adaptCountry':'156'},";

					} else {
						_adaptParam = _adaptParam + "{'adaptProvince':'"
								+ value.adaptProvince
								+ "','adaptCountry':'156'},";
					}
				});
		_adaptParam = _adaptParam + "]";
		_param.newStockRepAdaptVOsStr = _adaptParam;
		var _shopId = $("select[name=shopId]").val();
		_param.shopId = _shopId;
		$.gridLoading({
					"message" : "正在保存数据中...."
				});
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

					},
					exception : function() {

						$.gridUnLoading();
					}
				});

	}

};

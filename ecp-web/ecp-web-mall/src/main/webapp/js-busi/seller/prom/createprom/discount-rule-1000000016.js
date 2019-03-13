$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			var openParam = $('#postAreaCode').val();
			if (openParam == "") {
				openParam = "[]";
			}
			var json = eval(openParam);
			for (var i = 0; i < json.length; i++) {
				var tempObj = json[i];
				var allCitySize = $(
						"input[areaCode='" + tempObj.provinceCode + "']")
						.parent().parent().parent().find("input[name='city']").length;
				var cityList = tempObj.cityList;
				var citySize = cityList.length;
				for (var j = 0; j < citySize; j++) {
					var obj = cityList[j];
					$("input[areaCode='" + tempObj.provinceCode + "']")
							.parent().parent().find('font[name="checkCount"]')
							.html(citySize);
					$("input[cityCode='" + obj.cityCode + "']").attr("checked",
							"checked");
					$("input[cityCode='" + obj.cityCode + "']").parent()
							.addClass("allsel");
					if (obj.ifAll == "ALL" || citySize == allCitySize) {
						$("input[areaCode='" + tempObj.provinceCode + "']")
								.attr("checked", "checked");
						$("input[areaCode='" + tempObj.provinceCode + "']")
								.parent().addClass("allsel");
					} else {
						$("input[areaCode='" + tempObj.provinceCode + "']")
								.attr("checked", "checked");
						$("input[areaCode='" + tempObj.provinceCode + "']")
								.parent().addClass("partsel");
					}

				}
				if (citySize == 0) {
					$("input[areaCode='" + tempObj.provinceCode + "']").attr(
							"checked", "checked");
					$("input[areaCode='" + tempObj.provinceCode + "']")
							.parent().addClass("allsel");
				}
			}
			$(".fitem")
					.each(
							function() {

								var $this = $(this);
								$this
										.find('input[name="province"]')
										.click(
												function() {
													if ($(this).attr('checked') == "checked") {
														$(this)
																.parent()
																.removeClass(
																		"partsel");
														$(this)
																.parent()
																.addClass(
																		"allsel");
														var length = $this
																.find('input[name="city"]').length;
														$this
																.find(
																		'font[name="checkCount"]')
																.text(length);
														$this
																.find(
																		'input[name="city"]')
																.each(
																		function() {
																			$(
																					this)
																					.attr(
																							'checked',
																							'checked');
																			$(
																					this)
																					.parent()
																					.addClass(
																							"allsel");
																		});
													} else {
														$(this)
																.parent()
																.removeClass(
																		"allsel");
														$this
																.find(
																		'font[name="checkCount"]')
																.html("0");
														$this
																.find(
																		'input[name="city"]')
																.each(
																		function() {
																			$(
																					this)
																					.removeAttr(
																							'checked');
																			$(
																					this)
																					.parent()
																					.removeClass(
																							'allsel');
																		});
													}
												});
								$this
										.find('input[name="city"]')
										.each(
												function() {
													var $obj = $(this);
													$obj
															.click(function() {
																if ($obj
																		.attr('checked') == "checked") {
																	$obj
																			.parent()
																			.addClass(
																					"allsel");
																} else {
																	$obj
																			.parent()
																			.removeClass(
																					"allsel");
																}
																var count = 0;
																var length = $this
																		.find('input[name="city"]').length;
																$this
																		.find(
																				'input[name="city"]')
																		.each(
																				function() {
																					if ($(
																							this)
																							.attr(
																									'checked') == "checked") {
																						count++;
																					}
																				});
																if (count == length) {
																	$this
																			.find(
																					'input[name="province"]')
																			.attr(
																					'checked',
																					'checked');
																	$this
																			.find(
																					'input[name="province"]')
																			.parent()
																			.removeClass(
																					'partsel');
																	$this
																			.find(
																					'input[name="province"]')
																			.parent()
																			.addClass(
																					'allsel');
																} else {
																	$this
																			.find(
																					'input[name="province"]')
																			.removeAttr(
																					'checked');
																	$this
																			.find(
																					'input[name="province"]')
																			.parent()
																			.addClass(
																					'partsel');
																	$this
																			.find(
																					'input[name="province"]')
																			.parent()
																			.removeClass(
																					'allsel');
																}
																$this
																		.find(
																				'font[name="checkCount"]')
																		.text(
																				count);
															});
												});

							});

			// 邮递区域 事件
			$('#postAreaAll').click(function() {
				$(".fitem").each(function() {
					var $this = $(this);
					$this.find('input[name="province"]').click();
				});
			});
			// 是否全部选择
			$('#ifSelectAll').click(function() {
				if ($('#ifSelectAll').prop('checked') == true) {
					$('.postHide').hide();
				} else {
					$('.postHide').show();
				}
			});
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

function openChild(obj) {
	$this = $(obj);
	if ($this.hasClass('icon-caret-down')) {
		$this.removeClass('icon-caret-down');
		$this.addClass('icon-caret-up');
		$this.parent().parent().parent().addClass('active');
		$this.parent().parent().parent().siblings().each(function() {
			$(this).removeClass('active');
			if ($(this).find("i").hasClass("icon-caret-up")) {
				$(this).find("i").removeClass("icon-caret-up");
				$(this).find("i").addClass("icon-caret-down");
			}
		});
	} else {
		$this.removeClass('icon-caret-up');
		$this.addClass('icon-caret-down');
		$this.parent().parent().parent().removeClass('active');
		$this.parent().parent().parent().siblings().each(function() {
			$(this).removeClass('active');
		});
	}

}

var discountRuleFun = {

	//判断是否为数字
	isNum : function(s) {
		if (s != null && s != "") {
			return !isNaN(s);
		}
		return false;
	},
	valid : function() {
		var _reData = new Object();
		_reData.value = false;//返回默认 失败
		var data = new Array();

		var orderMoney = $('#orderMoney').val();
		if (!promCheck.priceNumber(orderMoney)) {
			eDialog.alert('促销规则-订单金额格式不正确，请最多保留两位小数。', null);
			_reData.value = false;//返回默认 失败
			return _reData;
		}

		if ($('#ifSelectAll').prop('checked') != true) {
			var param = "[";
			$("li[name='head']")
					.each(
							function() {
								var $this = $(this);
								var provinceObj = $this
										.find('input[name="province"]');
								if (provinceObj.attr('checked') == "checked"
										|| provinceObj.parent().hasClass(
												"partsel")) {
									var cityObj = $this
											.find('input[name="city"]');
									var count = 0;
									var length = cityObj.length;
									var all = "";
									var cityParam = "[";
									cityObj
											.each(function() {
												if ($(this).attr('checked') == "checked") {
													count++;
													if (provinceObj
															.attr('checked') == "checked"
															&& provinceObj
																	.parent()
																	.hasClass(
																			"allsel")) {
														all = "ALL";
													}
													cityParam += "{cityCode:'"
															+ $(this).attr(
																	'cityCode')
															+ "',cityName:'"
															+ $(this).attr(
																	'cityName')
															+ "',areaLevel:'"
															+ $(this)
																	.attr(
																			'areaLevel')
															+ "',ifAll:'" + all
															+ "'},";
												}
											});
									cityParam += "]";
									param += "{provinceCode:'"
											+ provinceObj.attr('areaCode')
											+ "',areaLevel:'"
											+ provinceObj.attr('areaLevel')
											+ "',provinceName:'"
											+ provinceObj.attr('areaName')
											+ "',cityList:" + cityParam + "},";
								}

							});
			param += "]";
			if (param == '[]') {
				param = "";
			}
			data.push({
				'name' : 'discountMap["postAreaCode"]',
				'value' : param
			});
		}
		_reData.value = true;
		_reData.result = data;
		return _reData;
	}
};
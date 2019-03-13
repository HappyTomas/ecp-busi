// 页面初始化模块
$(function() {
	$("#cancelSelect").hide();
	var pInit = function() {
		var init = function() {
			var jian = new Array('首件（件）', '续件（件）', '续件运费（元 ）', '免邮数量（件）', '件');
			var zhongl = new Array('首重（克/g）', '续重（克/g）', '续重运费（元 ）',
					'免邮数量（克/g）', '重量');
			var tiji = new Array('首体积（立方厘米/cm³）', '续体积（立方厘米/cm³）', '续体积运费（元 ）',
					'免邮数量（立方厘米/cm³）', '体积');
			var jine = new Array('首金额（元）', '续金额（元）', '续金额运费（元 ）', '免邮金额（元）',
					'金额');
			var _decimal = /^\d+(\.\d{1,2})?$/;

			ShipAdd.initValid();
			$("#backShiptemp").click(function() {
				window.location.href = GLOBAL.WEBROOT
						+ '/seller/gdsshiptemp?shopId=' + $("#shopId").val();
			});
			$("#selectCat").click(function() {
				bDialog.open({
					title : '分类选择',
					width : 350,
					height : 550,
					params : {multi : false},
					url : GLOBAL.WEBROOT
							+ "/seller/goods/category/open/catgselect?catgType=1&maxShowNode=1&catlogId=1",
					callback : function(data) {
						if (data && data.results && data.results.length > 0) {
							var _catgs = data.results[0].catgs;
							var size = _catgs.length;
							for (var i = 0; i < size; i++) {
								var obj = _catgs[i];
								$("#catgCode").attr('catgCode', obj.catgCode);
								$("#catgCode").val(obj.catgName);
							}
							$("#cancelSelect").show();
						}
					}
				});
			});
			$("#cancelSelect").click(function() {
				$("#catgCode").attr('catgCode',"");
				$("#catgCode").val("分类名称");
				$("#cancelSelect").hide();
			});
			// -------------------------事件绑定---------------------------------------

			$("#ifFree").click(function() {
						$this = $(this);
						if ($this.attr('checked') == "checked") {
							$("#defaultFree").hide();
						} else {
							$("#defaultFree").show();
						}
					});

			$("#saveShipMent").click(function() {
				if (!($("#addShiptempForm").valid()
						&& ShipAdd.validDefaultFree() && ShipAdd
						.validSeniorFree()))
					return;
				ShipAdd.saveShiptemp(this);
			});

			$("input[type='radio']").each(function() {
						var $this = $(this);
						$this.click(function() {
									var id = $this.attr('id');
									if ($this.attr('checked') == 'checked') {
										if (id == '1') {
											$("#first").text(jian[0]);
											$("#second").text(jian[1]);
											$("#secondFree").text(jian[2]);
											$("#noFree").text(jian[3]);
											$("#unit").text(jian[4]);
											$("#senior_first").text(jian[0]);
											$("#senior_second").text(jian[1]);
											$("#senior_secondFree")
													.text(jian[2]);
											$("#senior_noFree").text(jian[3]);
											$("#platCatCode").show();
										} else if (id == "2") {
											$("#first").text(zhongl[0]);
											$("#second").text(zhongl[1]);
											$("#secondFree").text(zhongl[2]);
											$("#noFree").text(zhongl[3]);
											$("#unit").text(zhongl[4]);
											$("#senior_first").text(zhongl[0]);
											$("#senior_second").text(zhongl[1]);
											$("#senior_secondFree")
													.text(zhongl[2]);
											$("#senior_noFree").text(zhongl[3]);
											$("#platCatCode").show();
										} else if (id == "3") {
											$("#first").text(tiji[0]);
											$("#second").text(tiji[1]);
											$("#secondFree").text(tiji[2]);
											$("#noFree").text(tiji[3]);
											$("#unit").text(tiji[4]);
											$("#senior_first").text(tiji[0]);
											$("#senior_second").text(tiji[1]);
											$("#senior_secondFree")
													.text(tiji[2]);
											$("#senior_noFree").text(tiji[3]);
											$("#platCatCode").show();
										} else if (id == "4") {
											$("#first").text(jine[0]);
											$("#second").text(jine[1]);
											$("#secondFree").text(jine[2]);
											$("#noFree").text(jine[3]);
											$("#unit").text(jine[4]);
											$("#senior_first").text(jine[0]);
											$("#senior_second").text(jine[1]);
											$("#senior_secondFree")
													.text(jine[2]);
											$("#senior_noFree").text(jine[3]);
											$("#platCatCode").hide();
										}
									}
								});
					});

		};
		return {
			init : init
		};
	};
	pageConfig.config({
				// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
				plugin : ['bPage'],
				// 指定页面
				init : function() {
					var shipList = new pInit();
					shipList.init();

				}
			});

});
function validatorSenior(obj, str) {
	ShipAdd.validatorSenior(obj, str);
}

var ShipAdd = {
	newTemplate : function() {
		var _c = $("#seniorTempList").find('tr').length;
		var html = "";
		html += "<tr>"
				+ "<td class='pmgr-city' style='max-width:100px;'>"
				+ "<span class='pmropt'>"
				+ "<input type='hidden' value='[]' id='area'/>"
				+ "<span></span><a href='javascript:void(0)' class='chooseArea'>选择地区</a>"
				+ "</span>"
				+ "</td>"
				+ "<td><select style='min-width:90px;width:90px;' name='pricingMode'>"
				+
				// "<option value='0'>平邮</option>"+
				"<option value='1'>快递</option>"
				+
				// "<option value='2'>EMS</option></select></td>"+
				"<td><input type='text' onblur=\"validatorSenior(this,'price')\" id='free"
				+ _c
				+ "' name='free' class='input-mini '></td>"
				+ "<td><input type='text' onblur=\"validatorSenior(this,'number')\" id='first"
				+ _c
				+ "' name='first' class='input-mini '></td>"
				+ "<td><input type='text' onblur=\"validatorSenior(this,'number')\" id='second"
				+ _c
				+ "' name='second' class='input-mini '></td>"
				+ "<td><input type='text' onblur=\"validatorSenior(this,'price')\" id='secondFree"
				+ _c
				+ "' name='secondFree' class='input-mini '></td>"
				+ "<td><input type='text' onblur=\"validatorSenior(this,'number')\" id='noFree"
				+ _c
				+ "' name='noFree' class='input-mini '></td>"
				+ "<td class='text-erro'><a href='javascript:void(0)' onclick='delTemp(this)'>删除</a></td>"
				+ "</tr>";
		$("#seniorTempList").append(html);

	},
	saveShiptemp : function(obj) {
		var param = {};
		var shipTemplateType = "";
		$("input[type='radio']").each(function() {
					var $this = $(this);
					if ($this.attr("checked") == "checked") {
						shipTemplateType = $this.attr('id');
					}
				});
		var ifFree = "0";
		if ($("#ifFree").attr("checked") == "checked") {
			ifFree = "1";
		} else {
			param.defaultFreeParam = ShipAdd.getDefaultParam();
		}
		if ($("#senior_button_open").hasClass("active")) {
			param.seniorFreeParam = ShipAdd.getSeniorParam();
		}
		param.ifFree = ifFree;
		param.shipTemplateType = shipTemplateType;
		param.shipTemplateName = $("#shipTemplateName").val();
		param.catgCode = $("#catgCode").attr('catgCode');
		param.shopId = $("#shopId").val();
		var btn = $(obj);
		btn.button('loading');// 设置按钮为处理状态，并为中读，不允许点击
		$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/gdsshiptemp/addshiptemp",
					data : param,
					success : function(returnInfo) {
						btn.button('reset');
						if (returnInfo.resultFlag == 'ok') {
							eDialog.success('模板保存成功！', {
										buttons : [{
											caption : "确定",
											callback : function() {
												window.location.href = GLOBAL.WEBROOT
														+ '/seller/gdsshiptemp?shopId='
														+ $("#shopId").val();
											}
										}]
									});
						} else {
							eDialog.error(returnInfo.resultMsg);
						}
					},
					exception : function() {
						btn.button('reset');
					}
				});
	},
	delTemp : function(obj) {
		$(obj).parent().parent().remove();
	},
	getSeniorParam : function() {
		var param = "[";
		$("#seniorTempList").find('tr').each(function() {
			$this = $(this);
			var _firstObj = $this.find('input[name="first"]');
			var first = _firstObj.val();
			var _pricingModeObj = $this.find('select[name="pricingMode"]');
			var pricingMode = _pricingModeObj.val();
			var _secondObj = $this.find('input[name="second"]');
			var second = _secondObj.val();
			var _freeObj = $this.find('input[name="free"]');
			var free = _freeObj.val();
			var _secondFreeObj = $this.find('input[name="secondFree"]');
			var secondFree = _secondFreeObj.val();
			var _noFreeObj = $this.find('input[name="noFree"]');
			var noFree = _noFreeObj.val();
			var _areaCodeListObj = $this.find("input[id='area']");
			var areaCodeList = _areaCodeListObj.val();
			if (!_decimal.test(first)) {

			}
			param += "{pricingMode:'" + pricingMode + "',firstAmount:'" + first
					+ "',firstPrice:'" + free + "',continueAmount:'" + second
					+ "'," + "continuePrice:'" + secondFree + "',freeAmount:'"
					+ noFree + "',areaCodeList:" + areaCodeList + "},";
		});
		param += "]";
		return param;
	},
	getDefaultParam : function() {
		var param = "[";
		var $this = $("#defaultPei").find("tr");
		var pricingMode = $this.find('select[id="pricingMode"]').val();
		var first = $this.find('input[name="dfirst"]').val();
		var second = $this.find('input[name="dsecond"]').val();
		var free = $this.find('input[name="dfree"]').val();
		var secondFree = $this.find('input[name="dsecondFree"]').val();
		var noFree = $this.find('input[name="dnoFree"]').val();
		param += "{pricingMode:'" + pricingMode + "',firstAmount:'" + first
				+ "',firstPrice:'" + free + "',continueAmount:'" + second
				+ "'," + "continuePrice:'" + secondFree + "',freeAmount:'"
				+ noFree + "',areaCodeList:[]},";
		param += "]";
		return param;
	},
	validDefaultFree : function() {
		if ($("#ifFree").attr("checked") == "checked") {
			ifFree = "1";
			return true;
		}
		var number = /^([0-9]+)$/;
		var price = /^\d+(\.\d{1,2})?$/;
		var erroC = 0;
		var shipTemplateType = "";
		$("input[type='radio']").each(function() {
					var $this = $(this);
					if ($this.attr("checked") == "checked") {
						shipTemplateType = $this.attr('id');
					}
				});
		$("#defaultPei").find('tr').each(function() {
			$this = $(this);
			var _firstObj = $this.find('input[name="dfirst"]');
			var first = _firstObj.val();
			var _secondObj = $this.find('input[name="dsecond"]');
			var second = _secondObj.val();
			var _freeObj = $this.find('input[name="dfree"]');
			var free = _freeObj.val();
			var _secondFreeObj = $this.find('input[name="dsecondFree"]');
			var secondFree = _secondFreeObj.val();
			var _noFreeObj = $this.find('input[name="dnoFree"]');
			var noFree = _noFreeObj.val();
			if ($.trim(first) == "") {
				_firstObj.nextAll().remove();
				_firstObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(first)) {
						_firstObj.nextAll().remove();
						_firstObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!number.test(first)) {
						_firstObj.nextAll().remove();
						_firstObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
						erroC++;
					}
				}
			}
			if ($.trim(second) == "") {
				_secondObj.nextAll().remove();
				_secondObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(second)) {
						_secondObj.nextAll().remove();
						_secondObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!number.test(second)) {
						_secondObj.nextAll().remove();
						_secondObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
						erroC++;
					}
				}
			}
			if ($.trim(free) == "") {
				_freeObj.nextAll().remove();
				_freeObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(free)) {
						_freeObj.nextAll().remove();
						_freeObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!price.test(free)) {
						_freeObj.nextAll().remove();
						_freeObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				}

			}
			if ($.trim(secondFree) == "") {
				_secondFreeObj.nextAll().remove();
				_secondFreeObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (!price.test(secondFree)) {
					_secondFreeObj.nextAll().remove();
					_secondFreeObj
							.parent()
							.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
					erroC++;
				}
			}
			if ($.trim(noFree) == "") {
				_noFreeObj.nextAll().remove();
				_noFreeObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(noFree)) {
						_noFreeObj.nextAll().remove();
						_noFreeObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!number.test(noFree)) {
						_noFreeObj.nextAll().remove();
						_noFreeObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
						erroC++;
					}
				}
			}
		});
		if (erroC > 0) {
			return false;
		}
		return true;
	},
	validSeniorFree : function() {
		var number = /^([0-9]+)$/;
		var price = /^\d+(\.\d{1,2})?$/;
		var erroC = 0;
		var shipTemplateType = "";
		$("input[type='radio']").each(function() {
					var $this = $(this);
					if ($this.attr("checked") == "checked") {
						shipTemplateType = $this.attr('id');
					}
				});
		$("#seniorTempList").find('tr').each(function() {
			$this = $(this);
			var _firstObj = $this.find('input[name="first"]');
			var first = _firstObj.val();
			var _secondObj = $this.find('input[name="second"]');
			var second = _secondObj.val();
			var _freeObj = $this.find('input[name="free"]');
			var free = _freeObj.val();
			var _secondFreeObj = $this.find('input[name="secondFree"]');
			var secondFree = _secondFreeObj.val();
			var _noFreeObj = $this.find('input[name="noFree"]');
			var noFree = _noFreeObj.val();
			if ($.trim(first) == "") {
				_firstObj.nextAll().remove();
				_firstObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(first)) {
						_firstObj.nextAll().remove();
						_firstObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!number.test(first)) {
						_firstObj.nextAll().remove();
						_firstObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
						erroC++;
					}
				}
			}
			if ($.trim(second) == "") {
				_secondObj.nextAll().remove();
				_secondObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(second)) {
						_secondObj.nextAll().remove();
						_secondObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!number.test(second)) {
						_secondObj.nextAll().remove();
						_secondObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
						erroC++;
					}
				}
			}
			if ($.trim(free) == "") {
				_freeObj.nextAll().remove();
				_freeObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (!price.test(free)) {
					_freeObj.nextAll().remove();
					_freeObj
							.parent()
							.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
					erroC++;
				}
			}
			if ($.trim(secondFree) == "") {
				_secondFreeObj.nextAll().remove();
				_secondFreeObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (!price.test(secondFree)) {
					_secondFreeObj.nextAll().remove();
					_secondFreeObj
							.parent()
							.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
					erroC++;
				}
			}
			if ($.trim(noFree) == "") {
				_noFreeObj.nextAll().remove();
				_noFreeObj
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				erroC++;
			} else {
				if (shipTemplateType == "4") {
					if (!price.test(noFree)) {
						_noFreeObj.nextAll().remove();
						_noFreeObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
						erroC++;
					}
				} else {
					if (!number.test(noFree)) {
						_noFreeObj.nextAll().remove();
						_noFreeObj
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
						erroC++;
					}
				}
			}
		});
		if (erroC > 0) {
			return false;
		}
		return true;
	},
	initValid : function() {
		jQuery.validator.addMethod("compareTime", function(value, element) {
					var endTimeId = element.id;
					var startTime = $("#startTime").val();
					var endTime = $("#" + endTimeId).val();
					if (startTime == "" || endTime == "") {
						return true;
					}

					if (endTime == "" || startTime == "") {
						return false;
					}
					/**
					 * 解决IE不支持trim问题
					 */
					if (!String.prototype.trim) {
						String.prototype.trim = function() {
							return this.replace(/^\s+|\s+$/g, '');
						};
					}
					if (startTime.trim().length == 10) {
						startTime = startTime + " 00:00:00";
					}
					if (endTime.trim().length == 10) {
						endTime = endTime + " 00:00:00";
					}
					var reg = new RegExp('-', 'g');
					startTime = startTime.replace(reg, '/');// 正则替换
					endTime = endTime.replace(reg, '/');
					startTime = new Date(parseInt(Date.parse(startTime), 10));
					endTime = new Date(parseInt(Date.parse(endTime), 10));
					if (startTime > endTime) {
						return false;
					} else {
						return true;
					}
				}, "<font color='#E47068'>截至时间不能早于起始时间</font>");
		$.validator.addMethod("price", function(value) {
					var decimal = /^\d+(\.\d{1,2})?$/;
					return decimal.test(value);
				});
		$("#addShiptempForm").validate({
					rules : {
						shipTemplateName : {
							required : true
						}
					},
					messages : {
						shipTemplateName : {
							required : "</br><span style='color:red'>不能为空</span>"
						}
					},
					// debug: false, //如果修改为true则表单不会提交
					submitHandler : function() {
					},
					errorPlacement : function(error, element) {
						error.appendTo(element.parent());
					}
				});
	},
	validatorSenior : function(obj, str) {
		var shipTemplateType = "";
		$("input[type='radio']").each(function() {
					var $this = $(this);
					if ($this.attr("checked") == "checked") {
						shipTemplateType = $this.attr('id');
					}
				});
		var number = /^([0-9]+)$/;
		var price = /^\d+(\.\d{1,2})?$/;
		var value = $.trim($(obj).val());
		var objects = $(obj);
		if (shipTemplateType == "4") {
			if (value == "") {
				objects.nextAll().remove();
				objects
						.parent()
						.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
			} else {
				if (!price.test(value)) {
					objects.nextAll().remove();
					objects
							.parent()
							.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
				} else {
					objects.nextAll().remove();
				}
			}
		} else {
			if (str == 'price') {
				if (value == "") {
					objects.nextAll().remove();
					objects
							.parent()
							.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				} else {
					if (!price.test(value)) {
						objects.nextAll().remove();
						objects
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>价格格式不合法</span></span>");
					} else {
						objects.nextAll().remove();
					}
				}
			} else if (str == 'number') {
				if (value == "") {
					objects.nextAll().remove();
					objects
							.parent()
							.append("<span generated='true' class='error'><br><span style='color:red'>不能为空</span></span>");
				} else {
					if (!number.test(value)) {
						objects.nextAll().remove();
						objects
								.parent()
								.append("<span generated='true' class='error'><br><span style='color:red'>请输入整数</span></span>");
					} else {
						objects.nextAll().remove();
					}
				}
			}
		}
	}

};

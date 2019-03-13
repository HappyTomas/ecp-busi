$(function() {
	var url = '/goods/gdsprop/saveEditProp';

	$('select[name=propType]').attr('disabled', 'true');

	$('select[name=propValueType]').attr('disabled', 'true');

	$('select[name=propInputType]').attr('disabled', 'true');

	// 保存属性
	$('#btnFormSave').unbind("click").click(function() {
		//
		if (!$("#propInfoForm").valid())
			return false;
		var _gdsProp = new Object();
		_gdsProp.id = $("#propId").val();
		_gdsProp.propName = $("input[name=propName]").val();
		_gdsProp.propSname = $("input[name=propSname]").val();
		_gdsProp.propValueType = $("select[name=propValueType]").val();
		_gdsProp.propType = $("select[name=propType]").val();
		_gdsProp.sortNo = $("input[name=sortNo]").val();
		_gdsProp.propDesc = $("textarea[name=propDesc]").val();
		_gdsProp.delPropValIds = $("#propValT").attr("delProps");

		// 如果是手动输入，则保存输入类型
		if (_gdsProp.propValueType == "1") {
			_gdsProp.propInputType = $("select[name=propInputType]").val();
			// 如果是文本手动输入，则保存校验正则
			if (_gdsProp.propInputType == "1")
				_gdsProp.propInputRule = $("input[name=propInputRule]").val();

		}
		_gdsProp.propValuesStr = "";
		var _valArray = new Array();
		$("#propValT").find("tr").each(function() {

			var _valName = $(this).find("input[mark=propValName]").val();
			var _valSort = $(this).find("input[mark=valSortNo]").val();
			var _valPicId = $(this).find("input[type=hidden]").val();
			var _valId = $(this).find("input[mark=propValName]").parent()
					.parent().parent().attr("valueId");
			var _val = new Object();
			_val.valName = _valName;
			if (_valId && "" != _valId) {
				_val.valId = _valId;
			}
			if (_valPicId == null || _valPicId == "") {
				_val.valPicId = "";
			} else {
				_val.valPicId = _valPicId;
			}

			if (_valSort == null || _valSort == "") {
				_val.sortNo = "";
			} else {
				_val.sortNo = _valSort;
			}
			_valArray.push(_val);

		});

		var _valStr = "[";
		$.each(_valArray, function(n, value) {

					_valStr = _valStr + "{'propValue':'" + value.valName + "',";
					if (value.valId && "" != value.valId) {
						_valStr = _valStr + "'id':'" + value.valId + "',";
					}
					_valStr = _valStr + "'sortNo':'" + value.sortNo + "',";
					_valStr = _valStr + "'mediaId':'" + value.valPicId + "'},";

				});
		_valStr = _valStr + "]";

		_gdsProp.propValuesStr = _valStr;
		$.gridLoading({
					"message" : "正在保存数据中...."
				});
		$.eAjax({
					url : GLOBAL.WEBROOT + url,
					data : _gdsProp,
					success : function(returnInfo) {
						eDialog.success('属性编辑成功！', {
									buttons : [{
										caption : "确定",
										callback : function() {
											window.location.href = $webroot
													+ '/goods/gdsprop';
										}
									}]
								});

					},
					exception : function() {

						$.gridUnLoading();
					}
				});
	});

	$('#btnReturn').unbind("click").click(function() {
				window.location.href = GLOBAL.WEBROOT + '/goods/gdsprop';
			});

	$('#btn_prop_add').unbind("click").click(function() {
				PropEdit.addPropValue();
			});
	$('select[name=propValueType]').change(function() {
				var type = $('select[name=propValueType]').val();

				if (type == "1") {
					$("#propValTable").hide();

				} else {
					$("#propValTable").show();
					$("#inputValidate").show();
				}

			});

	$('select[name=propType]').change(function() {
				var type = $('select[name=propType]').val();

				if (type == "1") {
					$("#propValueType").val("3");
					$("#propValueType").prop('disabled', 'true');
				} else {
					$("#propValueType").removeAttr('disabled');

				}
			});

	var type = $('select[name=propType]').val();

//	if (type == "1") {
//		$("#propValueType").val("3");
//		$("#propValueType").prop('disabled', 'true');
//	} else {
//		$("#propValueType").removeAttr('disabled');
//
//	}

	// 初始化页面时，判断是否展示属性值列表
	var type = $('select[name=propValueType]').val();

	if (type == "1") {
		$("#propValTable").hide();

	} else {
		$("#propValTable").show();
	}

	// 先隐藏
	var type = $('select[name=propValueType]').val();

	if (type == "1") {
		$("#inputValTable").show();
	} else {
		$("#inputValTable").hide();
	}
	$('select[name=propInputType]').live("change", function() {
				var propInputType = $('select[name=propInputType]').val();

				if (propInputType == "1") {
					$("#inputValidate").show();

				} else {
					$("#inputValidate").hide();
				}

			});

	$('select[name=propValueType]').change(function() {
				var type = $('select[name=propValueType]').val();

				if (type == "1") {
					$("#inputValTable").show();

				} else {
					$("#inputValTable").hide();
				}

			});

	PropEdit.uploadBind();

});

var PropEdit = {
	addPropValue : function() {
		var count = $('#propValT').find("tr").length;

		$('#propValT')
				.append("<tr><td><div><input type='text' mark='propValName' name='propValName"
						+ count
						+ "' class='input-block-level required htmlcheck' style='width:150px'/></div></td>"
						+ "<td>"
						+ "<span style='float:right;margin-right:20px;margin-bottom:-10px;width:120px;'>"
						+ "<img src='"
						+ GLOBAL.WEBROOT
						+ "/images/goods/default.png' style='width:20px;height:20px;display:inline;margin-right:7px;margin-bottom:20px' name='pic"
						+ count
						+ "'/>"
						+ "<input type='hidden' id = 'pic"
						+ count
						+ "VfsId'>"
						+ '<a href="javascript:;" class="a-upload">'
					    + '<input type="file" name="pic'+ count +'" id="pic'+count +'">上传图片</a>'
						+ "</span></td>"
						+ "<td style='text-align: center;'><input type='text' mark='valSortNo' name='valSortNo"
						+ count
						+ "' class='input-block-level digits' max='99999'  style='width:50px'/></td>"
						+ "<td style='text-align: center;'><a href='#' style='text-align: center;margin-left:22px' name='del'>删除</a></td></tr>");

	},
	uploadBind : function() {

	

		$('#propValT').find("input[type=file]").live('change',
				function() {
					var path = $(this).val();
					PropEdit.updateImage(this, path);
					// $(this).parent().find("input[name=path]").val(path);

				});

		$('#propValT').find("a[name='del']").live('click',function() {
					var propId = $(this).parent().parent().attr("valueId");
					if (propId && propId != "") {
						var delProps = $("#propValT").attr("delProps");
						delProps = delProps + propId + ",";
						$("tbody").attr("delProps", delProps);
					}
					$(this).parent().parent().remove();

				});

	},
	updateImage : function(object, path) {

		var filepath = path;
		filepath = (filepath + '').toLowerCase();
		var regex = new RegExp(
				'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
		/** 上传图片文件格式验证 */
		if (!filepath || !filepath.match(regex)) {
			eDialog.info('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
			return;
		}
		var url = GLOBAL.WEBROOT + '/goods/gdsprop/uploadImage';
		var callback = function(data, status) {
			if (status == "success") {
				/** 上传成功，隐藏上传组件，并显示该图片 */
				if (data == null) {
					eDialog.alert("图片上传失败!");
				} else {
					if (data.flag == true) {
						var img = "img[name='" + data.id + "']";

						var imagePath = data.imagePath;

						$(img).attr("src", imagePath);
						$("#" + data.id + "VfsId").val(data.imageId);

					} else {
						eDialog.alert(data.msg);
					}
				}
			} else {
				eDialog.alert("图片上传失败");
			}
		};
		PropEdit.ajaxFileUpload(url, false, $(object).attr('id'), "POST",
				"json", callback);

	},
	ajaxFileUpload : function(url, secureuri, fileElementId, type, dataType,
			callback) {
		$.ajaxFileUpload({
					url : url, // 用于文件上传的服务器端请求地址
					secureuri : secureuri, // 一般设置为false
					fileElementId : fileElementId, // 文件上传空间的id属性 <input
					// type="file" id="imageFile"
					// name="imageFile" />
					type : type, // get 或 post
					dataType : dataType, // 返回值类型
					success : callback, // 服务器成功响应处理函数
					error : function(data, status, e) // 服务器响应失败处理函数
					{
						PropEdit.uploadBind();
						eDialog.info(e);
					}
				});
	}

};
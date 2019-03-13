$(function() {
			var url = '/goods/gdsprop/saveAddProp';

			// 保存仓库
			$('#btnFormSave').unbind("click").click(function() {
				if (!$("#propInfoForm").valid())
					return false;
				var _gdsProp = new Object();
				_gdsProp.propName = $("input[name=propName]").val();
				_gdsProp.propSname = $("input[name=propSname]").val();
				_gdsProp.propValueType = $("select[name=propValueType]").val();
				_gdsProp.propType = $("select[name=propType]").val();
				_gdsProp.sortNo = $("input[name=sortNo]").val();
				_gdsProp.propDesc = $("textarea[name=propDesc]").val();
				// 如果是手动输入，则保存输入类型
				if (_gdsProp.propValueType == "1") {
					_gdsProp.propInputType = $("select[name=propInputType]")
							.val();
					// 如果是文本手动输入，则保存校验正则
					if (_gdsProp.propInputType == "1")
						_gdsProp.propInputRule = $("input[name=propInputRule]")
								.val();

				}

				_gdsProp.propValuesStr = "";
				var _valArray = new Array();
				$("#propValT").find("tr").each(function() {
					var _valName = $(this).find("input[mark=propValName]")
							.val();
					var _valSort = $(this).find("input[mark=valSortNo]").val();
					var _valPicId = $(this).find("input[type=hidden]").val();
					var _val = new Object();
					_val.valName = _valName;

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

							_valStr = _valStr + "{'propValue':'"
									+ value.valName + "','sortNo':'"
									+ value.sortNo + "','mediaId':'"
									+ value.valPicId + "'},";

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
								eDialog.success('属性新增成功！', {
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
						window.location.href = GLOBAL.WEBROOT
								+ '/goods/gdsprop';
					});

			$('#btn_prop_add').unbind("click").click(function() {
						PropAdd.addPropValue();
					});

			$('select[name=propType]').change(function() {
						var type = $('select[name=propType]').val();

						if (type == "1") {
							$("#propValueType").val("3");
							$("#propValueType").prop('disabled', 'true');
//							$("#inputValTable").empty();
							$("#inputValTable").hide();
							$("#propValTable").show();
							
						} else {
							$("#propValueType").removeAttr('disabled');

						}
					});

			var propType = $('select[name=propType]').val();

			if (propType == "1") {
				$("#propValueType").val("3");
				$("#propValueType").prop('disabled', 'true');
			} else {
				$("#propValueType").removeAttr('disabled');

			}
			$('select[name=propValueType]').change(function() {
						var propValueType = $('select[name=propValueType]')
								.val();

						if (propValueType == "1") {
//							$("#propValTable").empty();
							$("#propValTable").hide();

						} else {
							$("#propValTable").show();
						}

					});

			// 先隐藏
			var propValueType = $('select[name=propValueType]').val();

			if (propValueType == "1") {
				$("#inputValTable").show();
				var propInputType = $('select[name=propInputType]').val();
				if (propInputType == "1") {
					$("#inputValidate").show();

				} else {
					$("#inputValidate").hide();
				}

			} else {
//				$("#inputValTable").empty();
				$("#inputValTable").hide();
			}
			$('select[name=propValueType]').change(function() {
						var propValueType = $('select[name=propValueType]')
								.val();

						if (propValueType == "1") {
							$("#inputValTable").show();

						} else {
//							$("#inputValTable").empty();
							$("#inputValTable").hide();
						}

					});

			$('select[name=propInputType]').change(function() {
						var propInputType = $('select[name=propInputType]')
								.val();

						if (propInputType == "1") {
							$("#inputValidate").show();

						} else {
							$("#inputValidate").hide();
						}

					});

			// 初始化页面时，判断是否展示属性值列表
			var propValueType = $('select[name=propValueType]').val();

			if (propValueType == "1") {
				$("#propValTable").empty();
				$("#propValTable").hide();

			} else {
				$("#propValTable").show();
			}
			PropAdd.uploadBind();

		})

var PropAdd = {
	addPropValue : function() {
		var count = $('#propValT').find("tr").length;

		$('#propValT')
				.append("<tr><td><div><input type='text' mark='propValName' name='propValName" 
						+ count
						+ "' class='required htmlcheck' style='width:150px'/></div></td>"
						+ "<td>"
						+ "<span style='float:right;margin-right:20px;margin-bottom:-10px;width:100px;'>"
						+ "<img src='"
						+ GLOBAL.WEBROOT
						+ "/images/goods/default.png' style='width:20px;height:20px;display:inline;margin-right:4px;margin-bottom:20px' name='pic"
						+ +count
						+ "'/>"
						+ "<input type='hidden' id = 'pic"
						+ count
						+ "VfsId'>"				
					    + '<a href="javascript:;" class="a-upload">'
					    + '<input type="file" name="pic'+ count +'" id="pic'+count +'">上传图片</a>'
						+ "</span></td>"
						+ "<td style='text-align: center;'><div><input type='text' mark='valSortNo' name='valSortNo"
						+ count
						+ "' class='digits' max='99999' style='width:50px'/></div></td>"
						+ "<td style='text-align: center;'><a href='#' style='text-align: center;text-decoration:none;' name='del'>删除</a></td></tr>");
//		PropAdd.uploadBind();

	},
	uploadBind : function() {

//		$('#propValT').find("input[type=button]").unbind('click').click(
//				function() {
//
//					$(this).parent().find("input[type=file]").trigger("click");
//
//				});

		$('#propValT').find("input[type=file]").live('change',
				function() {
					var path = $(this).val();
					PropAdd.updateImage(this, path);
					// $(this).parent().find("input[name=path]").val(path);

				});

		$('#propValT').find("a[name=del]").live('click',function() {
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
			/** 上传成功，隐藏上传组件，并显示该图片 */
			if (status == "success") {
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
		PropAdd.ajaxFileUpload(url, false, $(object).attr('id'), "POST",
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
//						PropAdd.uploadBind();
						eDialog.info(e);
					}
				});
	}

}
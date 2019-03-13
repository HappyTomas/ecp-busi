/**
 * 媒体添加 对应的js zhanbh 2015.9.7
 */
$(function() {

	// 图片分类回填
	$('#mediaCatgSelector').mcDropDown({
		backfillCatgName : 'mediaCatgSelector',
		backfillCatgCode : 'picCatgCode'
		
	});
	//初始化图片分类下拉
	$('#mediaCatgSelector').mcDropDown.change($("#shopId").val());
	
	//让图片分类不能被选中
	$("#mediaCatgSelector").focus(function(){
		$(this).blur();
		});
	
	//店铺改变联动图片分类
	$("#shopId").change(function(){
		$("#mediaCatgSelector").val("");
		$("#picCatgCode").val("");
		$('#mediaCatgSelector').mcDropDown.change($("#shopId").val());
	});
	
	// 上传按钮
	$('#btnUpload').click(function() {
		var path = $("#mediaFile").val();
		path = (path + '').toLowerCase();
		// 验证是否可以上传
		if ($("#uploadAble").val() == "0") {
			eDialog.alert('文件读取错误！');
			return false;
		}
		if ($("#uploaded").val() == "1") {
			eDialog.alert('请勿重复上传！');
			return false;
		}
		// 上传文件
		GdsMedia.uploadMedia("#mediaFile", path);
	});

	// 先上传文件 成功后保存数据
	$('#btnFormSave').click(function() {
		// 验证表单是否为空
		if ($("#detailInfoForm").valid()) {
			// 验证媒体名称长度
			var str = $("#mediaName").val();
			var l = str.length;
			var blen = 0; // 字节长度
			for (i = 0; i < l; i++) {
				if ((str.charCodeAt(i) & 0xff00) != 0) {
					blen++;
				}
				blen++;
			}
			if (blen > 128) {
				eDialog.alert('媒体名称必须少于64个汉字');
				return false;
			}

			// 验证文件是否长上传成功
			if ($("#uploaded").val() == 0) {
				eDialog.alert('请先上传文件');
				return false;
			}
			// 保存数据
			GdsMedia.saveGdsMedia($(this));
		}
	});// end of btnFormSave

	// 返回按钮
	$('#btnReturn').click(function() {
		// 关闭弹窗
		bDialog.closeCurrent();
	});

	// 选择文件 联动图片分类
	$("#mediaFile")
			.live(
					"change",
					function(o) {
						var path = $(this).val();
						path = (path + '').toLowerCase();
						// 图片格式
						var imgRegex = new RegExp(
								'\\.(jpg)$|\\.(png)$|\\.(jpeg)$',
								'gi');
						// 视频格式
						var vdoRegex = new RegExp(
								'\\.(avi)$|\\.(wma)$|\\.(rmvb)$|\\.(rm)$|\\.(flash)$|\\.(mp4)$|\\.(mid)$|\\.(3gp)$',
								'gi');
						// 音频格式
						var adoRegex = new RegExp(
								'\\.(mp3)$|\\.(wav)$|\\.(ogg)$|\\.(ape)$|\\.(acc)$',
								'gi');
						/** 上传文件格式验证 */
						if (path) {
							if (path.match(imgRegex)) {
								// 令上传按钮有效
								$("#uploadAble").val("1");
								$("#mediaType").val("1");
							} else if (path.match(vdoRegex)) {
								$("#uploadAble").val("1");
								$("#mediaType").val("2");
							} else if (path.match(adoRegex)) {
								$("#uploadAble").val("1");
								$("#mediaType").val("3");
							} else {
								$("#uploadAble").val("0");
								$("#mediaType").val("1");
								eDialog.alert('媒体文件格式错误');
								$("#mediaFile").val("");
							}
						} else {
							$("#uploadAble").val("0");
							$("#mediaType").val("1");
							eDialog.alert('媒体文件格式错误');
							$("#mediaFile").val("");
						}
						// 设置为未上传
						$("#uploaded").val("0");
						$("#imgShow").attr("src", "");
					});

	var GdsMedia = {
		// 保存媒体数据
		saveGdsMedia : function(btn) {
			// 让按钮不能被点击
			btn.button('loading');
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdsmedia/savemedia",
				data : ebcForm.formParams($("#detailInfoForm")),// 获取表单参数
				success : function(returnInfo) {
					if (returnInfo.resultFlag == 'ok') {
						eDialog.success('媒体保存成功！', {
							onClose : function() {

								bDialog.closeCurrent();
							}
						});
					} else {
						// 清空状态
						$("#uploadAble").val("0");
						$("#uploaded").val("0");
						$("#mediaUuid").val("");
						eDialog.error('媒体保存失败！');
					}
					// 恢复按钮被点击能力
					btn.button('reset');
				},
				error : function() {
					eDialog.error('浏览器错误！');
					btn.button('reset');
				}
			});
		},// end of saveGdsMedia

		uploadMedia : function(object, path) {
			// if($("#mediaType").val() == '1'){//图片
			// var url = GLOBAL.WEBROOT + '/gdsmedia/uploadimage';
			// }else{//非图片
			var url = GLOBAL.WEBROOT + '/gdsmedia/uploadmedia';
			// }

			var callback = function(returnInfo) {
				/** 上传成功，隐藏上传组件，并得到uuid 设置状态为文件已上传 */
				if (returnInfo.success == "ok") {

					$("#imgShow").attr("src", returnInfo.resultMap.bmpUrl);
					$("#mediaUuid").val(returnInfo.resultMap.vfsId);
					$("#uploaded").val("1");
					$("#mediaFile").removeClass("required");
				} else {
					$("#uploadAble").val("0");
					$("#uploaded").val("0");
					$("#mediaFile").val("");
					eDialog.alert(returnInfo.message);

				}
			};
			GdsMedia.ajaxFileUpload(url, false, $(object).attr('id'), "POST",
					"json", callback);
		},// end of uploadImage

		ajaxFileUpload : function(url, secureuri, fileElementId, type,
				dataType, callback) {
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
					eDialog.alert("上传发起错误");
					$("#uploadAble").val("0");
					$("#uploaded").val("0");
				}
			});// end of $.ajaxFileUpload({

		}// end of ajaxFileUpload

	};// end of var GdsMedia

});// end of $(function(){}

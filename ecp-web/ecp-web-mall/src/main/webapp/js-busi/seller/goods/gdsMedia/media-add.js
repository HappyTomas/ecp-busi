// 页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {

			var ajaxFileUpload = function(url, secureuri, fileElementId, type,
					dataType, callback) {
				$.ajaxFileUpload({
							url : url, // 用于文件上传的服务器端请求地址
							secureuri : secureuri, // 一般设置为false
							fileElementId : fileElementId, // 文件上传空间的id属性
															// <input
							// type="file" id="imageFile"
							// name="imageFile" />
							type : type, // get 或 post
							dataType : dataType, // 返回值类型
							success : callback, // 服务器成功响应处理函数
							error : function(data, status, e) // 服务器响应失败处理函数
							{
								alert(e);
							}
						});
			};
			
			
			
			var uploadImage = function(object, path) {
				// var Img = new Image();
				// Img.src = object.value;
				// alert(object.height+":"+object.width);
				// return false;
				var filepath = path;
				filepath = (filepath + '').toLowerCase();
				var regex = new RegExp(
						'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$', 'gi');
				/** 上传图片文件格式验证 */
				if (!filepath || !filepath.match(regex)) {
					eDialog.alert('请选择图片格式为(.jpg,.png,.jpeg,.gif).');
					uploadfile.value = "";
					return;
				}
				var url = GLOBAL.WEBROOT + '/seller/gdsmedia/uploadmedia';
				var callback = function(returnInfo) {
					/** 上传成功，隐藏上传组件，并显示该图片 */
					if (returnInfo.success == "ok") {
						$("#image").attr("src", returnInfo.resultMap.bmpUrl);
						$("#mediaPic").val(returnInfo.resultMap.vfsId);
						$("#pictrueName").val(returnInfo.resultMap.bmpName);
					} else {
						eDialog.error(returnInfo.message);
					}
				};
				ajaxFileUpload(url, false, $(object).attr('id'), "POST",
						"json", callback);
			};

			var saveGdsMedia = function(obj) {
				if (!$("#detailInfoForm").valid())
					return false;
				if(!$("#mediaPic").val()){
					eDialog.error('请上传图片！');
				return false;
				}
				var btn = $(obj);
				btn.button('loading');// 设置按钮为处理状态，并为中读，不允许点击
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/gdsmedia/savemedia",
					data : ebcForm.formParams($("#detailInfoForm")),
					success : function(returnInfo) {
						btn.button('reset');
						if (returnInfo.resultFlag == 'ok') {
							eDialog.success('图片新增成功！', {
								onClose : function() {
									$('#mediaSearchBtn').trigger('click');
									bDialog.closeCurrent({"success":true});
								}
							});
						} else {
							eDialog.error('图片保存失败！');
						}
					},
					exception : function() {
						btn.button('reset');
					}
				});
			};

			

			$("#picture").live("change", function(o) {
						var path = $(this).val();
						uploadImage(this, path);
					});

			$("#btnFormSave").live("click", function() {
						saveGdsMedia(this);
					});
			$("#btnReturn").live("click", function() {
					
						bDialog.closeCurrent({});
					});
					
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
		};
		return {
			init : init
		};
	};
	pageConfig.config({
				// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
				plugin : [],
				// 指定页面
				init : function() {
					var mediaAdd = new pInit();
					mediaAdd.init();
				}
			});
});

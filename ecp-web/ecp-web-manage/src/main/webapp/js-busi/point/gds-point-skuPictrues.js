$(function() {
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var pictrueList = _param.pictrueList;
	$("#shopId").val(_param.shopId);
	var json = eval(pictrueList);
	if (json.length > 0) {
		for (var i = 1; i < json.length + 1; i++) {
			var objParam = json[i - 1];
			var url = objParam.url;
			var picVfsIdOne = url.substring(url.lastIndexOf('/')+1, url.lastIndexOf('.'));
			var picVfsId = "";
			if(picVfsIdOne.indexOf("_") > 0 ){
				picVfsId = picVfsIdOne.substring(0, picVfsIdOne.lastIndexOf('_'));
			}else{
				picVfsId = picVfsIdOne;
			}
			$("#picVfsId" + i).val(picVfsId);
			$("#picVfsId" + i).attr('picName', objParam.picName);
			$("#picVfsId" + i).parent().parent().find('img').attr('src',
					objParam.url);
			$("#picVfsId" + i).attr('meidaRtype', objParam.meidaRtype);
			$("#picVfsId" + i).attr('mediaType', objParam.mediaType);
			$("#picVfsId" + i).attr('mediaId', objParam.mediaId);
		}
	}

	$('#btnFormCancle').click(function() {
		bDialog.closeCurrent();
	});
	$('#btnFormSave').click(
			function() {
				var pictrueParam = "[";
				var length = $("input[name='mainPicVfsId']").length;
				var picCount = 0;
				for (var i = 1; i < length +1; i++) {
					var vfsId = $("#picVfsId" + i).val();
					if(vfsId == undefined){
						continue;
					}
					var picName = $("#picVfsId" + i).attr('picName');
					var url = $("#picVfsId" + i).parent().parent().find('img')
							.attr('src');
					var mediaRtype = $("#picVfsId" + i).attr('mediaRtype');
					var mediaType = $("#picVfsId" + i).attr('mediaType');
					if ($("#picVfsId" + i).val() != "") {
						//meidaRtype 1 为媒体库引用，2 直接上传 。 mediaType 1位图片，2位视频 3为音频
						pictrueParam += "{picVfsId" + i + ":'" + vfsId
								+ "',picName:'" + picName + "',mediaRtype:'"
								+ mediaRtype + "',mediaType:'" + mediaType
								+ "',sortNo:'" + picCount + "',url:'" + url
								+ "'},";
						picCount++;
					}

				}
				pictrueParam += "]";
				bDialog.closeCurrent({
					'param' : pictrueParam
				});
			});

	jQuery.validator.addMethod("regex", //addMethod第1个参数:方法名称  
	function(value, element, params) { //addMethod第2个参数:验证方法，参数（被验证元素的值，被验证元素，参数）  
		var decimal = /^([0-9]+)$/;
		return (decimal.test(value) || $.trim(value) == "");
	}, "格式错误");
	$(function() {
		$("#seniorPriceForm").validate({
			rules : {
				picVfsId1 : {

				},
				skuStock : {
					regex : true
				},
			},
			messages : {
				skuStock : {
					regex : "<b style='color:red'>请输入大于等于0的整数</b>"
				},
			},
			//          debug: false,  //如果修改为true则表单不会提交  
			submitHandler : function() {
			}
		});
	});
	/**
	 * 图片上传
	 */
	//		$('.com_input').each(function() {
	//			$(this).live("change", function(o) {
	//				var path = $(this).val();
	//				SkuPictrue.uploadImage(this, path);
	//			});
	//		});
	$('.com_input').parent().find("input[type=button]").unbind('click').click(
			function() {
				$(this).parent().find("input[type=file]").trigger("click");
			});

	$('.com_input').unbind('change').change(function() {
		var path = $(this).val();
		SkuPictrue.uploadImage(this, path);
	});
	var more = 1;
	$("#more-pictrue").click(function() {
		$this = $(this);
		if (more == 1) {
			$this.find('i').removeClass('icon-caret-down');
			$this.find('i').addClass('icon-caret-up');
			$this.find('span').text('收起');
			more = -1;
			$("#picture-block").show();
			SkuPictrue.queryMediaList();
		} else {
			$this.find('i').removeClass('icon-caret-up');
			$this.find('i').addClass('icon-caret-down');
			$this.find('span').text('更多（从图片库选择）');
			more = 1;
			$("#picture-block").hide();

		}
	});
	//选择图片的
    $(".imgcont").click(function(){
	  	$(".imgcont").each(function(){
	  		 $(this).removeAttr("style");
		});
	    $(this).attr('style',"border-color: red");
	});
});
function clickThis(obj) {
	$(obj).parent().find("input[type=file]").trigger('click');
}
function searchMedia(){
	SkuPictrue.queryMediaList();
}
var SkuPictrue = {
	bindPicUpload : function() {
		$('.com_input').parent().find("input[type=button]").unbind('click')
				.click(function() {
					$(this).parent().find("input[type=file]").trigger("click");
				});

		$('.com_input').unbind('change').change(function() {
			var path = $(this).val();
			SkuPictrue.uploadImage(this, path);
		});
	},
	uploadImage : function(object, path) {
		var filepath = path;
		filepath = (filepath + '').toLowerCase();
		var regex = new RegExp(
				'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
		/** 上传图片文件格式验证 */
		if (!filepath || !filepath.match(regex)) {
			eDialog.alert('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
			$(object).value = "";
			return;
		}
		var url = GLOBAL.WEBROOT + '/gdsinfoentry/uploadimage';
		var callback = function(data, status) {
			/** 上传成功，隐藏上传组件，并显示该图片 */
			if (data.success == "ok") {
				var index = $(object).attr('index');
				$("#image" + index).attr("src", data.map.imagePath);
				$("#picVfsId" + index).val(data.map.vfsId);
				$("#picVfsId" + index).attr('picName', data.map.imageName);
				$("#picVfsId" + index).attr('mediaRtype', '2');
				$('#picVfsId' + index).attr('mediaType', '1');
			} else {
				eDialog.error(data.message);
			}
			$('.com_input').each(function() {
				$(this).live("change", function(o) {
					var path = $(this).val();
					SkuPictrue.uploadImage(this, path);
				});
			});
			SkuPictrue.bindPicUpload();
		};
		SkuPictrue.ajaxFileUpload(url, false, $(object).attr('id'), "POST",
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
				alert(e);
			}
		});
	},
	queryMediaList : function() {
		var param = {
				shopId : $("#shopId").val(),
				mediaName : $("#mediaName").val(),
				picCatgCode : $("#picCatgCode").val()
				
		};
		$.gridLoading({"el":"#mediaList","messsage":"正在加载中...."});
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsinfoentry/gridmedialist",
			data : param,
			dataType : "html",
			success : function(returnInfo) {
				$.gridUnLoading({"el":"#mediaList"});
				$("#mediaList").empty();
				$("#mediaList").html(returnInfo);
			}
		});
	}
};
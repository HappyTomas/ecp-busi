/**
 * 标签管理添加标签页面对应的js
 * create by  zhanbh 
 * 2015.9.17
 */
$(function() {
	//保存按钮
	$("#btnFormSave").click(function() {
		//验证表单是否为空
		if (!$("#detailInfoForm").valid())
			return false;
		//验证标签名称长度
		var str = $("#labelTitle").val();
		var l = str.length;
		var blen = 0; //字节长度 
		for (i = 0; i < l; i++) {
			if ((str.charCodeAt(i) & 0xff00) != 0) {
				blen++;
			}
			blen++;
		}
		if (blen > 64) {
			eDialog.alert('标签名称必须少于32个汉字');
			return false;
		}
		//验证标签描述长度
		str = $("#labelDesc").val();
		l = str.length;
		blen = 0; //字节长度 
		for (i = 0; i < l; i++) {
			if ((str.charCodeAt(i) & 0xff00) != 0) {
				blen++;
			}
			blen++;
		}
		if (blen > 128) {
			eDialog.alert('标签描述必须少于64个汉字');
			return false;
		}
		GdsLabel.saveLabel($(this));
	});

	//返回按钮
	$("#btnReturn").click(function() {

		//关闭弹窗
		bDialog.closeCurrent();

	});

	//标签对象
	var GdsLabel = {

		saveLabel : function(btn) {//保存标签
			//让按钮不能被点击
			btn.button('loading');
			$.eAjax({
				url : GLOBAL.WEBROOT + "/gdslabel/savelabel",
				data : ebcForm.formParams($("#detailInfoForm")),//获取表单参数
				success : function(returnInfo) {
					if (returnInfo.resultFlag == "ok") {
						eDialog.success('标签保存成功', {
							onClose : function() {

								bDialog.closeCurrent();
							}
						});
					} else {
						eDialog.error("标签保存失败！");
					}
					//恢复按钮被点击能力
					btn.button('reset');
				},
				error : function() {
					eDialog.error('浏览器错误！');
					btn.button('reset');
				}
			});
		}//end of saveLabel

	}
});//end of $(function(){}


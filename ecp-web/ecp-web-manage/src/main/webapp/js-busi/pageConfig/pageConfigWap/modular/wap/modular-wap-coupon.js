/**
 * 优惠券编辑块对应js 函数
 * 16.9.6 zhanbh
 */
;$(function () {
 //编辑框方法定义---------------------------------------------//
	var focusEdit = {
		"init":function(){
			//focusEdit.bindAdd();
			focusEdit.bindMenu();
			focusEdit.bindSelectCoup();
		},
		"bindSelectCoup":function(){
			$(".select-link-coup",".form-block").unbind("click.sc").bind("click.sc",function(){
				var $formBlock = $(this).closest(".form-block");
				var siteId = $("#siteId").val();
				var title = "选择优惠券";
				var	url = "floorcoupon/opencoupon?siteId="+siteId;
				bDialog.open({
					title : title,
					width : 900,
					height : 550,
					url : $webroot + url,
					callback:function(data){
						if(data && data.results && data.results[0]){
							$("#propValue",$formBlock).val(data.results[0].couponIds);
							$("#remark",$formBlock).val(data.results[0].couponNames);
							$("#coupName",$formBlock).val(data.results[0].couponNames);
						}
					}
				});
			});
		},
		"bindMenu":function(){
			_eCmsModMenuTool.mUp();
			_eCmsModMenuTool.mDown();
		},
	}
//初始化--------------------
	focusEdit.init();
});
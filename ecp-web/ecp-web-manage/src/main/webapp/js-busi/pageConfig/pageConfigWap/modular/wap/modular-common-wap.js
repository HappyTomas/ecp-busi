/**
 * 页面编辑
 * @author gongxq
 * 
 * propValueType（属性类型常量）
 * 01--单选
 * 02--多选
 * 03--手动输入
 * 04--下拉框
 * 05--图片
 * 06--自定义
 */
$(function(){
	//wap 自定义上传组件
	var eCmsImageUploadCallB = function (fileInfo) {
		if(fileInfo && fileInfo.success == true && fileInfo.fileId){
			$(fileInfo.el).parents(".formItem").children("#propValue").val(fileInfo.fileId);
		}
	};
	var params={
			 "fileSizeLimit":'100KB',//KB,MB
			 "fileTypeExts":['png','jpg','jepg'],
			 "elWidth" : 250,//html元素宽度
			 "elHeight" : 128,//html元素高度
			 "showWidth" : 250,//图片展示规格宽度
			 "showHeight" :128,//图片展示规格高度
			 "showText":"建议选择{imageMaxWidth}*{imageMaxHeight}尺寸的图片，类型:{fileTypeExts}",//上传插件提示语
			 "callback":eCmsImageUploadCallB
		}
	$(".wap_img_upload").eCmsImageUploadInit(params);
	
	//链接工具初始化
	var siteId = $("#siteId").val();
	$(".link-input-tool").cmsLinkInputInit({
		"types":["good","catg","secondpage","prom","sitenav"],
		"siteId":siteId,
		"urlMap":(function(){return siteId==2?{"good":"gdspointdetail/{key}-"}:{}})(),
		"busiParams":{"prom":{"pageTypeId":51},"secondpage":{"pageTypeId":52}},//51为微信端活动页类型id  硬编码
		"callback":function(data){
			if(data && data.success == true && data.url){
				$(data.el).val(data.url);
	    		$(data.el).parents(".form-block.formItem").find("#propValue").val(data.url);
	    	}
		}
	});
	
	$(".wap_close_i").live('click',function () {
	 	var $parentObj=$(this).parents(".edit-tip");
	 	if($parentObj.css("display") != 'none'){
	 		$parentObj.hide();
	 		$('#pageEdit').mCustomScrollbar('update');
	 	}
	 });
});


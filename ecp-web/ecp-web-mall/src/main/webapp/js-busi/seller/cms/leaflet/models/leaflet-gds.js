$(function(){
	/**
	 * 解决IE不支持trim问题
	 */
	if (!String.prototype.trim) {
		String.prototype.trim = function() {
			return this.replace(/^\s+|\s+$/g, '');
		};
	}
	
	leaflet_gds.init();
});
var leaflet_gds = {
	init : function(){//初始化
		// 绑定搜索按钮事件.
		$('#btnFormSearch').click(function(){
			var p = ebcForm.formParams($('#searchForm'));
			var siteId = $("#siteId").val();
			(siteId || siteId==0)?siteId:"";
			$('#leaflet-gds-div').load(GLOBAL.WEBROOT + '/seller/common/querygds?siteId='+siteId,p);
		});
		
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		
		//选择商品
		$(".select-leaflet-gds").live("click",function(){
			leaflet_gds.selectInfo($(this).attr("data-i"),$(this).attr("data-n"));
		});
		
		// 初始化事件.
		$('#btnFormSearch').trigger('click');
	},
	//绑定选定
	selectInfo : function (id,title){
		bDialog.closeCurrent({
			"linkUrl": id,
			"infoTitle":title
		});
	}
};


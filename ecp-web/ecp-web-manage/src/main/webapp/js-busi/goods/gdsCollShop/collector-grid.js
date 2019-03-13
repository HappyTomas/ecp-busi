/*
 * 卖家收藏用户列表   js
 */
$(function() {
	$("#collectorList").initDT({
		'pTableTools' : false,
		'pSingleCheckClean' : true,
		'pCheck' : 'single',
		'pCheckColumn' : false, // 是否显示单选/复选框的列
		"sAjaxSource" : GLOBAL.WEBROOT + '/gdscollshop/collectorlist',
		// 指定列数据位置
		"aoColumns" : [ {
			"mData" : "staffName",
			"sTitle" : "收藏人",
			"sWidth" : "180px",
			"sClass" : "center",
			"bSortable" : false,
			"mRender" : function(data, type, row) {
				var name;
				if (row.staffName == null)
					name = "用户名不存在";
				else
					name = row.staffName;
				return name;
			}
		}, {
			"mData" : "collectionTime",
			"sTitle" : "收藏时间",
			"sWidth" : "80px",
			"sClass" : "center",
			"bSortable" : false,
			"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}
		} ],
		"params" : [ {
			name : 'gdsId',
			value : $("#gdsId").val()
		}, {
			name : 'skuId',
			value : $("#skuId").val()
		} ]
	});
});// end of $(function(){})

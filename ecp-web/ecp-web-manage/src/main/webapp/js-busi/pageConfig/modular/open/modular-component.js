$(function(){
	modular_component.init();
});

var modular_component = {
	
	init : function(){//初始化
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheck' : "multi",
	        'pAutoload' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        "sAjaxSource": $webroot + 'common/querycomponent',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"id","bVisible":false,"sWidth":"80px","bSortable":true},
				{ "mData": "componentName", "sTitle":"组件名称","sWidth":"260px","bSortable":false},	
				{ "mData": "componentClassZH", "sTitle":"所属组件分类","bVisible":true,"sWidth":"120px","bSortable":false},	
				{ "mData": "componentMethod", "sTitle":"调用方法","sWidth":"120px","bSortable":false},
				{ "mData": "componentUrl", "sTitle":"请求路径","sWidth":"120px","bSortable":false},	
				{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"80px","sClass": "left",
					"mRender": function(data,type,row){
						return "<a href='javascript:void(0)' onclick='modular_component.selectInfo(\""+row.id+"\",\""+row.componentName+"\")'>选定</a> ";
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//update();
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			modular_component.search();
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		
		modular_component.search();
	},
	//查询方法
	search : function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	},
	//绑定选定
	selectInfo : function (id,componentName){
		bDialog.closeCurrent({
			"id": id,
			"componentName":componentName
		});
	}
	
};


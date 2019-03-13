$(function(){
	list_gds.init();
});

var list_gds = {
	init : function(){//初始化
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheck' : "multi",
	        'pAutoload' : false,
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        "sAjaxSource": $webroot + 'list/querygds',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"商品编码","bVisible":false,"sWidth":"80px","bSortable":false},
				{ "mData": "gdsName", "sTitle":"商品名称","sWidth":"80px","bSortable":false},
				{ "mData": "gdsTypeCode", "sTitle":"商品类型","bVisible":false,"sWidth":"80px","bSortable":false},
				{ "mData": "gdsTypeName", "sTitle":"商品类型名称","sWidth":"80px","bSortable":false},
				{ "mData": "shopId", "sTitle":"所属店铺","sWidth":"80px","bSortable":false},
//				{ "mData": "putonTime", "sTitle":"上架时间","bSortable":false,"mRender": function(data,type,row){
//					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
//				}},
//				{ "mData": "putoffTime", "sTitle":"下架时间","bSortable":false,"mRender": function(data,type,row){
//					return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
//				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"80px","sClass": "left",
					"mRender": function(data,type,row){
						return "<a href='javascript:void(0)' onclick='list_gds.selectInfo(\""+data+"\",\""+row.gdsName+"\")'>选定</a> ";
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//update();
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			list_gds.search();
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
		list_gds.search();
	},
	//查询
	search : function (){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	},
	//绑定选定
	selectInfo : function (id,infoTitle){
		bDialog.closeCurrent({
			"gdsId":id,
			"infoTitle":infoTitle
		});
	},
	/**
	 * 根据url打开窗口
	 * @param {} url : 链接路径
	 * @param {} linkType : href,open
	 * @param {} openType : _blank..
	 */
	windowOpenUrl : function (url,linkType,openType){
		if(linkType=="href"){
			window.location.href = url;
		}else if(linkType=="open"){
			window.open(url,openType);
		}
	},
	/**
	 * 判断字数
	 * @param {} thisObj  当前对象
	 * @param {} showObj  要显示提示的对象
	 */
	checkLen : function (thisObj,showObj,maxChars){
		//var maxChars = 200;
		if (thisObj.value.length > maxChars) thisObj.value = thisObj.value.substring(0,maxChars);  
		var curr = maxChars - thisObj.value.length;  
		$("#"+showObj).html(curr.toString());
	}
};


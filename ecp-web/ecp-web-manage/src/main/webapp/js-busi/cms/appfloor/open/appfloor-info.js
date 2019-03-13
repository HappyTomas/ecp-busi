$(function(){
	advertise_info.init();
});

var advertise_info = {
	init : function(){//初始化
		//初始化列表
		$("#dataGridTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        'pCheck' : "multi",
	        'pCheckColumn' : false, //是否显示单选/复选框的列
	        "sAjaxSource": $webroot + 'advertise/queryinfo?status=1',
	        //指定列数据位置
	        "aoColumns": [
	        	{ "mData": "id", "sTitle":"id","bVisible":false,"sWidth":"50px","bSortable":true},
				{ "mData": "infoTitle", "sTitle":"主题","bSortable":false},
				/*{ "mData": "infoType", "sTitle":"类型","bVisible":false,"sWidth":"100px","bSortable":false},*/
				{ "mData": "typeName", "sTitle":"类型","bVisible":true,"sWidth":"100px","bSortable":false},
				{ "mData": "pubTime", "sTitle":"发布时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				{ "mData": "lostTime", "sTitle":"失效时间","sWidth":"140px","bSortable":false,"mRender": function(data,type,row){
					return ebcDate.dateFormat(data,"yyyy-MM-dd");
				}},
				{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"80px","sClass": "left",
					"mRender": function(data,type,row){
						//alert(data+"---"+type+"---"+row);
						return "<a href='javascript:void(0)' onclick='advertise_info.selectInfo(\""+row.id+"\",\""+row.infoTitle+"\")'>选定</a> ";
					}
				}
	        ],
	        "eDbClick" : function(){
	        	//update();
	        }
		});
		//绑定查询按钮
		$('#btnFormSearch').click(function(){
			if(!$("#searchForm").valid()) return false;
			var p = ebcForm.formParams($("#searchForm"));
			$('#dataGridTable').gridSearch(p);
		});
		//绑定重置按钮
		$('#btnFormReset').click(function(){
			ebcForm.resetForm('#searchForm');
		});
	},
	//绑定选定
	selectInfo : function (infoId,infoTitle){
		//var linkUrl =  "/info/infodetail?id="+infoId;
		bDialog.closeCurrent({
			"linkUrl":infoId,
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


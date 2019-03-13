$(function(){
	//初始化列表
	$("#dataGridTable","#info").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        'pCheckColumn' : false, //是否显示单选/复选框的列
        "sAjaxSource": $webroot + 'cmslinkinpututil/infogrid?status=1',
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
					return "<a href='javascript:void(0)' onclick='_selectTypeItem(\""+row.id+"\",\""+row.infoTitle+"\")'>选定</a> ";
				}
			}
        ]
	});
	//绑定查询按钮
	$('#btnFormSearch',"#info").click(function(){
		if(!$("#searchForm","#info").valid()) return false;
		var p = ebcForm.formParams($("#info-searchForm"));
		$('#dataGridTable',"#info").gridSearch(p);
	});
	//绑定重置按钮
	$('#btnFormReset',"#info").click(function(){
		ebcForm.resetForm('#searchForm',"#info");
	});
});


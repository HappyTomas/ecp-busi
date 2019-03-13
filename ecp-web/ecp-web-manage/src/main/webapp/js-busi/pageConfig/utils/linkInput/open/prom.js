$(function(){
	var siteId = $('#siteId',"#prom").val();
	var pageTypeId = $('#pageTypeId',"#prom").val();
	var url = 'cmslinkinpututil/pagegrid?siteId='+(siteId||"")+"&pageTypeId="+(pageTypeId||51);
	//初始化列表
	$("#dataGridTable","#prom").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        'pCheckColumn' : false, //是否显示单选/复选框的列
        "sAjaxSource": $webroot + url,
        "params" : [],
        //指定列数据位置
        "aoColumns": [
        	{ "mData": "id", "sTitle":"ID","bVisible":false,"sWidth":"50px","bSortable":true},
			{ "mData": "pageName","sWidth":"180px", "sTitle":"页面名称","bSortable":false},
			{ "mData": "pageTypeZH", "sTitle":"页面类型","sWidth":"120px","bVisible":true,"bSortable":false},
			{ "mData": "siteUrl", "sTitle":"页面地址","bVisible":true,"bSortable":false},
			{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"80px","sClass": "left",
				"mRender": function(data,type,row){
					return "<a href='javascript:void(0)' onclick='_selectTypeItem(\""+row.id+"\",\""+row.infoTitle+"\")'>选定</a> ";
				}
			}
        ]
	});
	//绑定查询按钮
	$('#btnFormSearch',"#prom").click(function(){
		if(!$("#searchForm","#prom").valid()) return false;
		var p = ebcForm.formParams($("#searchForm","#prom"));
		$('#dataGridTable',"#prom").gridSearch(p);
	});
	//绑定重置按钮
	$('#btnFormReset',"#prom").click(function(){
		ebcForm.resetForm('#searchForm',"#prom");
	});
});


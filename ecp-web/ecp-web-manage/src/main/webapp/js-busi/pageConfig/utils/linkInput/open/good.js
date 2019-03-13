$(function(){
	//初始化列表
	var params = ebcForm.formParams($("#searchForm","#good"));
	//初始化列表
	$("#dataGridTable","#good").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : "multi",
        'params' : params,
        'pCheckColumn' : false, //是否显示单选/复选框的列
        "sAjaxSource": $webroot + 'cmslinkinpututil/goodgrid',
        //指定列数据位置
        "aoColumns": [
        	{ "mData": "id", "sTitle":"商品编码","bVisible":false,"sWidth":"40px","bSortable":false},
			{ "mData": "gdsName", "sTitle":"商品名称","sWidth":"240px","bSortable":false},
			{ "mData": "imageUrl", "sTitle":"图片","bSortable":false,"mRender": function(data,type,row){
				return "<img src='"+data+"' width='120' height='50'>";
			}},
			{ "mData": "prop1002", "sTitle":"ISBN号","sWidth":"180px","bSortable":false},
			{ "mData": "gdsTypeName", "sTitle":"商品类型","sWidth":"120px","bSortable":false},
			//{ "mData": "prop1001", "sTitle":"作者","sWidth":"100px","bSortable":false},
			//{ "mData": "prop1002", "sTitle":"ISBN","sWidth":"100px","bSortable":false},
			//{ "mData": "prop1005", "sTitle":"出版日期","sWidth":"100px","bSortable":false},
			{ "mData": "updateTime", "sTitle":"上架日期","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}},
			//{ "mData": "prop1010", "sTitle":"版次","sWidth":"80px","bSortable":false},
			{ "mData": "shopName", "sTitle":"所属店铺","sWidth":"120px","bSortable":false},
			{"targets": -1,"mData": "id","sTitle":"操作","sWidth":"80px","sClass": "left",
				"mRender": function(data,type,row){
					return "<a href='javascript:void(0)' onclick='_selectTypeItem(\""+row.id+"\",\""+row.gdsName+"\")'>选定</a> ";
				}
			}
        ]
	});
	var shopId = $('#shopId',"#good").val();
	var siteId = $('#siteId',"#good").val();
	//绑定查询按钮
	$('#btnFormSearch',"#good").click(function(){
		if(!$("#searchForm","#good").valid()) return false;
		var p = ebcForm.formParams($("#searchForm","#good"));
		$('#dataGridTable',"#good").gridSearch(p);
	});
	//绑定重置按钮
	$('#btnFormReset',"#good").click(function(){
		$("#catgCode","#good").attr('catgCode',""); 
		ebcForm.resetForm('#good-searchForm');
		$('#shopId',"#good").val(shopId);
		$('#siteId',"#good").val(siteId);
	});
	
	$("#siteId","#good").live("change",function(){
		// 分类回填
		$('#catgName',"#good").mcDropDown({
			backfillCatgName : 'catgName',
			backfillCatgCode : 'catgCode'
		});
		//初始化分类下拉
		$('#catgName',"#good").mcDropDown.change();
	});
	
	$("#siteId","#good").trigger("change");
	
	//让分类不能被选中
	$("#catgName","#good").focus(function(){
		$(this).blur();
	});
});


$(function(){
	//初始化列表
	var params = ebcForm.formParams($("#searchForm"));
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false, //是否显示单选/复选框的列
        'pCheck' : "multi",
        'params' : params,
        "sAjaxSource": $webroot + 'place/gridlist',
        //指定列数据位置
        "aoColumns": [
        	{ "mData": "id", "sTitle":"ID","bVisible":true,"sWidth":"50px","bSortable":true},
			{ "mData": "placeName", "sTitle":"位置名称","bSortable":false},	
			{ "mData": "siteZH", "sTitle":"所属站点","bVisible":true,"sWidth":"100px","bSortable":false},
			{ "mData": "templateZH", "sTitle":"所属模板","bVisible":true,"sWidth":"100px","bSortable":false},
			{ "mData": "placeTypeZH", "sTitle":"位置类型","bVisible":true,"sWidth":"100px","bSortable":false},
			{ "mData": "placeWidth", "sTitle":"宽度","sWidth":"80px","bSortable":false},
			{ "mData": "placeHeight", "sTitle":"高度","sWidth":"80px","bSortable":false},
			//{ "mData": "placeSize", "sTitle":"大小","sWidth":"80px","bSortable":false},
			//{ "mData": "sortNo", "sTitle":"排序","sWidth":"70px","bSortable":false},
			{ "mData": "statusZH", "bVisible":true,"sTitle":"状态","sWidth":"80px","bSortable":false},
			{
				"targets": -1,"mData": "id","sTitle":"操作","sWidth":"180px","sClass": "left","bSortable":false,
				"mRender": function(data,type,row){
					return "<a href='javascript:void(0)' onclick=\"checkme('"+row.placeName+"',"+row.id+")\">选定</a>";
				}
			}
        ],
        "eDbClick" : function(){
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
	//绑定添加按钮
	$('#btn_code_add').click(function(){
		window.open($webroot+'place/add');
	});
	//站点来匹配模板
	$('#siteId').change(function(){
		changeSite();
	});
	function changeSite(){//站点来匹配模板
		var siteId = $('#siteId').val();
		$.eAjax({
			url : $webroot + 'place/changeSite',
			data : {
				"siteId":siteId,
				"templateClass":"",
				"status":"1"
			},
			success : function(returnInfo){
				$("#templateId").html("");
				$("#templateId").append("<option value= ''>--请选择--</option>");
				for(var info in returnInfo){
					var option = "<option value ="+"\""+returnInfo[info].id+"\""+">"+returnInfo[info].templateName+"</option>";
					$("#templateId").append(option);
				}
			}
		});
	}
});
//选定
function checkme(templateName,id){
	bDialog.closeCurrent({
		'templateName' : templateName,
		'templateId' : id
	});
}

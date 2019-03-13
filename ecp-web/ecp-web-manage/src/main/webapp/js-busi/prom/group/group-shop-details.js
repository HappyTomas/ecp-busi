$(function(){
	//初始化grid
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/promgroup/detailsShop',
        'params' : [{name:'groupId',value:$('#groupId').val()}],//默认当前促销主题id查询
        //指定列数据位置
        "aoColumns": [
            { "mData": "siteName", "sTitle":"站点","sWidth":"80px","bSortable":false},
			//{ "mData": "shopId", "sTitle":"店铺编码","sWidth":"100px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"100px","bSortable":false},
			//{ "mData": "id", "sTitle":"促销编码","sWidth":"100px","bSortable":false},
			{ "mData": "promTheme", "sTitle":"促销名称","sWidth":"140px","bSortable":false},
			{ "mData": "promTypeName", "sTitle":"促销类型","sWidth":"100px","bSortable":false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"80px","bSortable":false},
			{ "mData": "priority", "sTitle":"优先级","sWidth":"90px","bSortable":false},
			{ "mData": "startTime", "sTitle":"生效开始时间","bSortable":false,"sWidth":"90px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "endTime", "sTitle":"生效截至时间","bSortable":false,"sWidth":"90px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "showStartTime", "sTitle":"开始展示时间","bSortable":false,"sWidth":"90px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "showEndTime", "sTitle":"结束展示时间","bSortable":false,"sWidth":"90px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}}
        ],
        "eDbClick" : function(){
        	detailClick();
        }
	});
	
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	var detailClick=function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/createprom/view/'+ids[0];
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	}
	$('#btn_code_detail').click(function(){
		detailClick();
	});
 
});
$(function(){
	
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/querygroup/grid',
        'params': [{name:'siteId',value:$("#siteId").val()}],
        //指定列数据位置
        "aoColumns": [
            { "mData": "siteName", "sTitle":"站点","sWidth":"100px","bSortable":false},
			{ "mData": "id", "sTitle":"促销编码","sWidth":"60px","bSortable":false},
			{ "mData": "promTheme", "sTitle":"主题名称","sWidth":"180px","bSortable":false},
			{ "mData": "showStartTime", "sTitle":"开始展示时间","bSortable":false,"sWidth":"90px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "showEndTime", "sTitle":"截止展示时间","bSortable":false,"sWidth":"90px","mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "shopCnt", "sTitle":"参与店铺数量","sWidth":"70px","bSortable":false}
        ],
        "eDbClick" : function(){
        }
	});
	 
	//查询
	$('#btnFormSearch').click(function(){
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	//确定添加
	$('#btn_code_add').click(function(){
		
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length>=1){
			var _val = $('#dataGridTable').getSelectedData();
			var parm=new Object();
			parm.id=_val[0].id;
			parm.promTheme=_val[0].promTheme;
			bDialog.closeCurrent(parm);
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个主题进行操作！');
		}
	
	});
});
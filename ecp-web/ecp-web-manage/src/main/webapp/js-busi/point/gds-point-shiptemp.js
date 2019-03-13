$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	$("#shopId").val(_param.shopId);
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pCheckColumn':false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single',
        "sAjaxSource": GLOBAL.WEBROOT + '/gdsinfoentry/gridshiptemplist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"模板编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "shipTemplateName", "sTitle":"模板名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": null,"sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return "<a href='javascript:void(0)' onclick=\"chooseMe(this,'"+row.id+"','"+row.shipTemplateName+"')\">选择</a>";
				}	
			}
        ],
        "params" : [{
			name : 'shopId',
			value : $("#shopId").val()
		}],
	});	
	$('#btnReturn').click(function(){
		bDialog.closeCurrent();
	});
	
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		p.push({ 'name': 'shopId','value' : $("#shopId").val() });
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
});
function chooseMe(obj,id,shipTemplateName){
	if(shipTemplateName=="null"){
		shipTemplateName = "";
	}
	bDialog.closeCurrent({
		'tempId' : id,
		'tempName' : shipTemplateName
	});
}
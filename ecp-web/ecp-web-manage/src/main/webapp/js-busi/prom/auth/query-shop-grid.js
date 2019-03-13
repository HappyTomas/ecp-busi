$(function(){
	
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	
	$("#dataGridTableShop").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/promshop/shoplist',
        'params' : [{name:'status',value:$('#status').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"店铺编码","sWidth":"80px","bSortable":false},
			{ "mData": "shopName", "sTitle":"店铺名称","sWidth":"80px","bSortable":false},
			{ "mData": "shopStatus", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '有效';
				}else{
					return '无效';
				}
			}}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){

		var _ids = $('#dataGridTableShop').getCheckIds();
	    if(!_ids || _ids.length==0){
			eDialog.alert('请选择至少选择一个店铺进行操作！');
			return ;
		}
		var parm=new Object();
		parm.shopIds=_ids;
		bDialog.closeCurrent(parm);
	};
	
	$('#btnFormSearchShop').click(function(){
		if(!$("#searchFormShop").valid()) return false;
		var _p = ebcForm.formParams($("#searchFormShop"));
		$('#dataGridTableShop').gridSearch(_p);
	});
	
	$('#btnFormResetShop').click(function(){
		ebcForm.resetForm('#searchFormShop');
		$("#status").val('1');//特别处理 置为有效
	});
	
	$('#btnFormSaveShop').click(function(){
		var _ids = $('#dataGridTableShop').getCheckIds();
	    if(!_ids || _ids.length==0){
			eDialog.alert('请选择至少选择一个店铺进行操作！');
			return ;
		}
		var parm=new Object();
		parm.shopIds=_ids;
		bDialog.closeCurrent(parm);
	});
     //关闭	
	$("#btnReturnShop").on("click",function(){
		bDialog.closeCurrent();
	});
});
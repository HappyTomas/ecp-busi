$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	
	//添加
	$('#btn_code_add').click(function(){
		//优惠券id
       var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length>=1){
			var _val = $('#dataGridTable').getSelectedData();
			var _idsArr=new Array();
		    for(var i=0;i<_val.length;i++){
		    	_idsArr.push(_val[i].id);
		    }
			var parm=new Object();
			parm.ids=_idsArr;
			bDialog.closeCurrent(parm);
			
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个优惠券进行操作！');
		}
	});
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/coupinfo/grid',
        'params' : ebcForm.formParams($("#searchForm")),
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "coupName", "sTitle":"优惠券名称","sWidth":"120px","bSortable":false},
			{ "mData": "coupTypeName", "sTitle":"优惠券类型","sWidth":"120px","bSortable":false},
			{ "mData": "coupValue", "sTitle":"面额","sWidth":"90px","bSortable":false,
				"mRender": function(data,type,row){
					var str = (data/100).toFixed(2) + '';
					var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
					var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
					var ret = intSum + dot;
					return ret;
				}	
			},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,bVisible:false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"100px","bSortable":false}
		 
        ],
        "eDbClick" : function(){ 
        	
        },
        "eClick" : function(data){ 
        	
        }
	});
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var _p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(_p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	//关闭
	$('#btn_code_closed').click(function(){
		bDialog.closeCurrent();
	});
});
$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	
	$('#btn_code_add_giftList').unbind('click').click(function(){
		add();
	});
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/giftprom/grid',
        'params' : [{name:'shopId',value:$('#shopId').val()}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"编码","sWidth":"60px","bSortable":false},
			{ "mData": "giftName", "sTitle":"赠品名称","sWidth":"120px","bSortable":false},
			{ "mData": "giftValue", "sTitle":"赠品价值","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
			    var str = (data/100).toFixed(2) + '';
				var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
				var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
				var ret = intSum + dot;
				return ret;
			}},
			{ "mData": "giftTypeName", "sTitle":"赠品类型","sWidth":"80px","bSortable":false},
			{ "mData": "giftStatusName", "sTitle":"赠品状态","sWidth":"80px","bSortable":false},
			{ "mData": "giftAmount", "sTitle":"赠品总量","sWidth":"80px","bSortable":false},
			{ "mData": "giftSend", "sTitle":"已赠量","sWidth":"80px","bSortable":false},
			{ "mData": "giftValid", "sTitle":"可赠量","sWidth":"80px","bSortable":false}
			/*{ "mData": "startTime", "sTitle":"生效时间","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "endTime", "sTitle":"失效时间","sWidth":"120px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}}*/
        ],
        "eDbClick" : function(){
        	add();
        }
	});
	//添加
	var add=function(){
		
		var ids = $('#dataGridTable').getCheckIds();
		
		if(ids && ids.length>=1){
			var _val = $('#dataGridTable').getSelectedData();
			var _idsArr=new Array();
		    for(var i=0;i<_val.length;i++){
		    	_idsArr.push(_val[i].id);
		    }
			var parm=new Object();
			parm.skuIds=_idsArr;
			bDialog.closeCurrent(parm);
			
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个赠品进行操作！');
		}
	};
	//查询
	$('#btnFormSearch').click(function(){
		var p = ebcForm.formParams($("#searchGiftForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchGiftForm');
		//店铺处理
		$('#shopId').val($('#shopIdInit').val());
		
	});
	//关闭
	$('#btnReturn').click(function(){
		bDialog.closeCurrent();
	});
	

});
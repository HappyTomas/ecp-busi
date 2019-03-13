$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	//var _callback = bDialog.getDialogCallback(_dlg);
	
	$('#btn_code_add_coup').unbind('click').click(function(){
		add();
	});
	
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
       // 'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/coupinfo/grid',
        'params' : ebcForm.formParams($("#searchCoupForm")),
        //指定列数据位置
        "aoColumns": [
                    { "mData": "id", "sTitle":"id","sWidth":"80px","bSortable":false,bVisible:false},
          			{ "mData": "siteName", "sTitle":"站点","sWidth":"120px","bSortable":false},
          			{ "mData": "shopName", "sTitle":"店铺","sWidth":"120px","bSortable":false},
          			{ "mData": "coupName", "sTitle":"优惠券名称","sWidth":"120px","bSortable":false},
          			{ "mData": "coupTypeName", "sTitle":"优惠券类型","sWidth":"120px","bSortable":false},
          			{ "mData": "coupValue", "sTitle":"额度","sWidth":"90px","bSortable":false,"mRender":function(data,type,row){ 
          				  if(data<=0){
          					  return "抵用券";
          				  }else{
          					var str = (data/100).toFixed(2) + '';
          					var intSum = str.substring(0,str.indexOf("."));//取到整数部分.replace( /\B(?=(?:\d{3})+$)/g, ',' )
          					var dot = str.substring(str.length,str.indexOf("."));//取到小数部分
          					var ret = intSum + dot;
          					return ret;
          				  }
          			}},
          			{ "mData": "coupNum", "sTitle":"发行总量","sWidth":"90px","bSortable":false},
          			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,bVisible:false},
          			{ "mData": "coupCode", "sTitle":"优惠码","sWidth":"80px","bSortable":false,bVisible:false},
          			{ "mData": "effTypeName", "sTitle":"日期类型","sWidth":"80px","bSortable":false},
          			{ "mData": "activeTime", "sTitle":"生效时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
          				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
          			}},
          			{ "mData": "inactiveTime", "sTitle":"失效时间","bSortable":false,"sWidth":"120px","mRender": function(data,type,row){
          				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
          			}}
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
			var parm=new Object();
			parm._if_query="1";
			parm.row=_val;
			//bDialog.getDialogCallback(_dlg);
			bDialog.closeCurrent(parm);
			
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个优惠券进行操作！');
		}
	};
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchCoupForm").valid()) return false;
		var p = ebcForm.formParams($("#searchCoupForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		 //ebcForm.resetForm('#searchCoupForm');
		$('#coupTypeId').val('');
		$('#coupName').val('');
	});
	//关闭
	$('#btnReturn').click(function(){
		bDialog.closeCurrent();
	});
	

});
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/custservice/gridlist',
        "params":[{}],
        //指定列数据位置
        "aoColumns": [
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"50px","bSortable":true},
			{ "mData": "shopName", "sTitle":"所属店铺","sWidth":"50px","bSortable":false},
			{ "mData": "moduleType", "sTitle":"客户类型","sWidth":"80px","bSortable":false},
			{ "mData": "hotlinePerson", "sTitle":"客服姓名","sWidth":"80px","bSortable":false},
			{ "mData": "hotlinePhone", "sTitle":"客服电话","sWidth":"80px","bSortable":false},
			{ "mData": "orderEdit", "sTitle":"订单修改","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '开';
				}else{
					return '关';
				}
	        }},
			{ "mData": "receptionCount", "sTitle":"上限接待人数","sWidth":"80px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if(data=='0'){
					return '失效';
				}else{
					return '正常';
				}
	        }},
	        { "mData": "createTime", "sTitle":"生成时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
				data = ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
				return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
			}} 
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			$('#id').val(val[0].id);
			$('#editForm').submit();
		}else if(val && val.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
		}
	};
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#btn_code_add').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/custservice/add';
	});
	
	$('#btn_code_edit').click(function(){
		modifyBiz();
	});
	 
	
	//使失效
	$('#btn_code_invalid').click(function(){ 
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].status == '0') {
				eDialog.alert("您好，该会员状态已为失效，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该条记录置为失效吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT+'/custservice/custinvalid?id=' + rowData[0].id,
							success : function(returnInfo) {
								if(returnInfo.resultMsg){
									eDialog.alert(returnInfo.resultMsg);
									$('#btnFormSearch').click();//刷新列表
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
		}
	});
	//使生效
	$('#btn_code_valid').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].status == '1') {
				eDialog.alert("您好，该会员状态已为正常，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该条记录状态恢复为正常吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT+'/custservice/custvalid?id=' + rowData[0].id,
							success : function(returnInfo) {
								if(returnInfo.resultMsg){
									eDialog.alert(returnInfo.resultMsg);
									$('#btnFormSearch').click();//刷新列表
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
		}
	});
	
});
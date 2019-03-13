$(function(){
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/authstaf/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"用户id","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "staffCode", "sTitle":"登录名","sWidth":"80px","bSortable":false},      
            { "mData": "staffClass", "sTitle":"用户分类","sWidth":"80px","bSortable":false},      
            { "mData": "staffFlag", "sTitle":"帐号状态","sWidth":"80px","bSortable":false},         
            { "mData": "lastLoginTime", "sTitle":"上次登录时间","sWidth":"80px","bSortable":false},         
			{ "mData": "loginFailureCntToday", "sTitle":"当天失败次数","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "lastLoginFailureTime", "sTitle":"最后登录失败时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}},
			{ "mData": "createFrom", "sTitle":"创建来源","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "updateTime", "sTitle":"最后更新时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}}
        ],
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			
			
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
});
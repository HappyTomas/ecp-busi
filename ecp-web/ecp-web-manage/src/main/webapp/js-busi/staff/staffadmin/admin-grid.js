$(function(){
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/staffadmin/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"用户id","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "staffCode", "sTitle":"会员名","sWidth":"80px","bSortable":false},      
            { "mData": "aliasName", "sTitle":"昵称","sWidth":"80px","bSortable":false},      
            { "mData": "staffName", "sTitle":"姓名","sWidth":"80px","bSortable":false},         
            { "mData": "staffEmail", "sTitle":"邮箱","sWidth":"80px","bSortable":false},         
			{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "staffFlag", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
                if (data == '1') {
               	 	return "正常";
                } else if (data == '2') {
               	 	return "锁定";
                } else if (data  == '3') {
                	return "失效";
                } else {
                	return "失效";
                }
	        }},
	        { "mData": "staffRoleName", "sTitle":"所属角色","sWidth":"80px","bSortable":false},   
	        { "mData": "staffGroupName", "sTitle":"所属用户组","sWidth":"80px","bSortable":false},     
			{ "mData": "updateTime", "sTitle":"更新时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
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
			eNav.setSubPageText("修改用户");//第三级面包屑
			window.location.href = GLOBAL.WEBROOT+'/staffadmin/update?adminId=' + val[0].id + '&staffRole=' + val[0].staffRole + '&staffGroup=' + val[0].staffGroup;
		}else if(val && val.length>1){
			eDialog.alert('只能选择一条记录进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少一条记录进行操作！');
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
	$('#btn_code_add').click(function(){
		eNav.setSubPageText("新增用户");//第三级面包屑
		window.location.href = GLOBAL.WEBROOT+'/staffadmin/add';
	});
	
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	
	
	//使失效
	$('#btn_code_invalid').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].staffFlag == '3') {
				eDialog.alert("您好，该用户状态已为失效，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该条记录置为失效吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'staffadmin/updateflag?id=' + ids[0] + '&staffFlag=3',
							success : function(returnInfo) {
								if(returnInfo.resultMsg){
									eDialog.alert(returnInfo.resultMsg);
									//window.location.reload();
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
			if (rowData[0].staffFlag == '1') {
				eDialog.alert("您好，该用户状态已为正常，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该条记录状态恢复为正常吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'staffadmin/updateflag?id=' + ids[0] + '&staffFlag=1',
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
	
	//账户加锁
	$('#btn_code_lock').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].staffFlag == '2') {
				eDialog.alert("您好，该用户状态已为锁定，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该条记录置为锁定吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'staffadmin/updateflag?id=' + ids[0] + '&staffFlag=2',
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
	
	//密码重置
	$('#btn_code_pwd_reset').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			eDialog.confirm("您确认要把该用户的密码重置吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'staffadmin/pwdreset?staffId=' + ids[0],
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
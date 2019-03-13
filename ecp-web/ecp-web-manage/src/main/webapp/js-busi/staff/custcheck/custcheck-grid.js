$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/custcheck/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"用户编码","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "staffId", "sTitle":"会员编码","sWidth":"80px","bSortable":false,"bVisible":false},      
            { "mData": "companyName", "sTitle":"企业名称","sWidth":"80px","bSortable":false},         
			{ "mData": "staffCode", "sTitle":"会员名","sWidth":"80px","bSortable":false},
			{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false},
		/*	{ "mData": "trade", "sTitle":"企业行业","sWidth":"80px","bSortable":false},
			{"mData": "employeeNum", "sTitle":"企业人数","sWidth":"80px","bSortable":false},*/
			{ "mData": "areaCode", "sTitle":"所在区域","sWidth":"80px","bSortable":false},
			{ "mData": "checkStatus", "sTitle":"审核状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if(data=='3'){
					return '审核不通过';
				}else{
					return '待审核';
				}
			}},
			{ "mData": "checkDate", "sTitle":"审核时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}},
			{ "mData": "checkStaffName", "sTitle":"审核人","sWidth":"80px","bSortable":false},
			{ "mData": "checkRemark", "sTitle":"审核备注","sWidth":"80px","bSortable":false}
			/**
			 * ,
			{ "mData": "prop1", "sTitle":"其他属性1","sWidth":"90px","bSortable":false},
			{ "mData": "prop2", "sTitle":"其他属性2","sWidth":"90px","bSortable":false},
			{ "mData": "prop3", "sTitle":"其他属性3","sWidth":"90px","bSortable":false},
			{ "mData": "statusName", "sTitle":"状态","sWidth":"30px","bSortable":false},
			{ "mData": "sortno", "sTitle":"排序","sWidth":"40px","bSortable":false}
			 */
        ],
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			
			$.eAjax({
				url : GLOBAL.WEBROOT + "/custcheck/check",
				data : {'id':val[0].id,'staffId':val[0].staffId},
				datatype:'json',
				success : function(returnInfo) {
					eDialog.success('审核成功！请到会员管理查看成功数据',{
						buttons:[{
							caption:"确定",
							callback:function(){
								$('#dataGridTable').gridReload();
					        }
						}]
					}); 
				}
			});
			
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
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
		window.location.href = GLOBAL.WEBROOT+'/demo/edit';
	});
	
	$('#btn_code_check').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			eDialog.confirm("您确定要进行审核通过操作吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						modifyBiz();
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
	
	$('#btn_code_nocheck').on('click',function(){
		
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			bDialog.open({
				title : '不通过理由',
				width : 500,
				height : 260,
				params : {
				'val':val	
				},
				url : GLOBAL.WEBROOT+'/custcheck/checkremark',
				callback:function(data){
					$('#dataGridTable').gridReload();
				}
			});
			
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
		
	});
	
	$('#btn_code_del').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			eDialog.confirm("您确认删除该待审核数据吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						eDialog.alert('success','删除成功！');
						
						$.eAjax({
							url : GLOBAL.WEBROOT + "/custcheck/remove",
							data : {'id':val[0].id},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('删除成功！',{
									buttons:[{
										caption:"确定",
										callback:function(){
											$('#dataGridTable').gridReload();
								        }
									}]
								}); 
							}
						});
						
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
});
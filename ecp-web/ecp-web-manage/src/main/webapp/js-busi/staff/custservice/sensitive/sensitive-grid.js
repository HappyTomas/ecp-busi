$(function(){
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
		if($('#sTab').hasClass('active')){
			$('#btn_not_sensitive').show();
			$('#btn_add_sensitive').hide();
			$('#sensitive').show();
			var a = $('#sensitiveType1').val();
			if(a==''){
				$('#sensitiveType').val('5');
			}else{
				$('#sensitiveType').val(a);
			}
			
		}
		if($('#snotTab').hasClass('active')){
			$('#btn_not_sensitive').hide();
			$('#btn_add_sensitive').show();
			$('#sensitive').hide();
			$('#sensitiveType').val('');
		}
    });
	
	$('#sensitiveType1').change(function(){
		if($(this).val()!=''){
		$('#sensitiveType').val($(this).val());
		}else{
			if($('#sTab').hasClass('active')){
				$('#sensitiveType').val('5');
			}
		}
	});
	var showSTab = function(){
		$("#sTable").initDT({
			'pCheck' : 'multi',
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        "sAjaxSource": GLOBAL.WEBROOT + '/sensitive/gridlist',
	        "params":[{name:"sensitiveType",value:'5'}],
	        //指定列数据位置
	        "aoColumns": [
				{ "mData": "staffCode", "sTitle":"会员名","sWidth":"50px","bSortable":true},
				{ "mData": "nickname", "sTitle":"昵称","sWidth":"50px","bSortable":false},
				{ "mData": "custType", "sTitle":"客户类型","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
	                         if(data=='10'){
	                        	 return "普通会员";
	                         }else if(data=='20'){
	                        	 return "企业会员";
	                         }else if(data=='30'){
	                        	 return "企业管理员";
	                         }else{
	                        	 return "";
	                         }
					
				}},
				{ "mData": "custLevelName", "sTitle":"会员等级","sWidth":"80px","bSortable":false},
				{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false},
				{ "mData": "sensitiveType", "sTitle":"敏感类型","sWidth":"80px","bSortable":false},
				{ "mData": "sensitiveDesc", "sTitle":"敏感备注","sWidth":"80px","bSortable":false},
				{ "mData": "createStaffCode", "sTitle":"操作账号","sWidth":"80px","bSortable":false},
				 { "mData": "sensitiveTime", "sTitle":"操作时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
						data = ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
						return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
					}},
		        { "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
	                if (data == '1') {
	               	 	return "正常";
	                } else if (data == '2') {
	               	 	return "锁定";
	                } else if (data  == '3') {
	                	return "失效";
	                } else if (data  == '4') {
	                	return "临时冻结";
	                } else {
	                	return "失效";
	                }
		        }},
		        { "mData": "createTime", "sTitle":"注册时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					data = ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
					return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
				}}/*,
				{ "mData": "thirdCode", "sTitle":"淘宝会员号","sWidth":"80px","bSortable":false}*/
				
	        ],
	        "eDbClick" : function(){
	        	modifyBiz();
	        }
		});
	};
	
	var snotTab = function(){
		$("#snotTable").initDT({
	        'pTableTools' : false,
	        'pSingleCheckClean' : false,
	        "sAjaxSource": GLOBAL.WEBROOT + '/sensitive/gridlist',
	        "params":[{name:"sensitiveType",value:'0'}],
	        //指定列数据位置
	        "aoColumns": [
				{ "mData": "staffCode", "sTitle":"会员名","sWidth":"50px","bSortable":true},
				{ "mData": "nickname", "sTitle":"昵称","sWidth":"50px","bSortable":false},
				{ "mData": "custType", "sTitle":"客户类型","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
	                         if(data=='10'){
	                        	 return "普通会员";
	                         }else if(data=='20'){
	                        	 return "企业会员";
	                         }else if(data=='30'){
	                        	 return "企业管理员";
	                         }else{
	                        	 return "";
	                         }
					
				}},
				{ "mData": "custLevelName", "sTitle":"会员等级","sWidth":"80px","bSortable":false},
				{ "mData": "serialNumber", "sTitle":"手机号码","sWidth":"80px","bSortable":false},
		        { "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
	                if (data == '1') {
	               	 	return "正常";
	                } else if (data == '2') {
	               	 	return "锁定";
	                } else if (data  == '3') {
	                	return "失效";
	                } else if (data  == '4') {
	                	return "临时冻结";
	                } else {
	                	return "失效";
	                }
		        }},
		        { "mData": "createTime", "sTitle":"注册时间","sWidth":"100px","bSortable":false,"mRender": function(data,type,row){
					data = ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
					return "<span title='"+data+"' style='cursor:default'>"+data+"</span>";
				}}/*,
				{ "mData": "thirdCode", "sTitle":"淘宝会员号","sWidth":"80px","bSortable":false}*/
				
	        ],
	        "eDbClick" : function(){
	        	modifyBiz();
	        }
		});
	}
	//默认展示
	showSTab();
	snotTab();
	var modifyBiz = function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			$('#staffId').val(val[0].id);
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
		if($('#sTab').hasClass('active')){
			$('#sTable').gridSearch(p);
		}
		if($('#snotTab').hasClass('active')){
			$('#snotTable').gridSearch(p);
		}
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		if($('#sTab').hasClass('active')){
		   $('#sensitiveType').val('5');
		}
	});
	
	$('#btn_add_sensitive').click(function(){
		var val = $('#snotTable').getSelectedData();
	
		if(val && val.length>=1){
			bDialog.open({
				title : '转换为敏感客户',
				width : 400,
				height : 250,
				url : GLOBAL.WEBROOT+'/sensitive/change?staffId='+val[0].id,
				callback:function(data){
					$('#sTable').gridReload();
					$('#snotTable').gridReload();
				}
			});
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
		
	});
	
	$('#btn_not_sensitive').click(function(){
		var val = $('#sTable').getSelectedData();
		if(val && val.length>=1){
				var staffId = '';
		    	for(var i=0;i<val.length;i++){
		    		if(i==val.length-1){
		    			staffId += val[i].id
		    		}else{
		    			staffId += val[i].id+",";
		    		}
		    	}
		    	eDialog.confirm("确认删除该用户的敏感标识吗？", {
					buttons : [{
						caption : '确认',
						callback : function(){
							$.eAjax({
								url : GLOBAL.WEBROOT+'/sensitive/delSensitive?listStaff='+staffId,
								success : function(returnInfo) {
									if(returnInfo.resultFlag=='ok'){
										eDialog.success('转换成功！',{
											buttons:[{
												caption:"确定",
												callback:function(){
													$('#sTable').gridReload();
													$('#snotTable').gridReload();
										        }
											}]
										}); 
									}else{
										eDialog.alert('转换失败');
									}
								
								}
							});
						}
					},{
						caption : '取消',
						callback : $.noop
					}]
				});
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	});
	
	
});
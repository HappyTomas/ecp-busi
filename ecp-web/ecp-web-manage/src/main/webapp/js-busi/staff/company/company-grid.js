$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/company/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"企业编码","sWidth":"80px","bSortable":false,"bVisible":false},      
            { "mData": "companyName", "sTitle":"企业名称","sWidth":"80px","bSortable":false,"mRender":function(data){
				if(data.length>12){
					var datas = data.substring(0,12)+"...";
					return "<span title="+data+">"+datas+"</span>";
				}else{
					return data;
				}
			}},         
			/*{ "mData": "companyType", "sTitle":"企业类型","sWidth":"80px","bSortable":false},*/
			{ "mData": "isEnter", "sTitle":"入驻企业","sWidth":"80px","bSortable":false,"mRender":function(data){
				if(data=='0'){
					return '否';
				}else{
					return '是';
				}
			}},
			/*{ "mData": "trade", "sTitle":"企业行业","sWidth":"80px","bSortable":false},
			{ "mData": "employeeNum", "sTitle":"企业人数","sWidth":"80px","bSortable":false},*/
			{ "mData": "areaCode", "sTitle":"所属地区","sWidth":"80px","bSortable":false},
			{ "mData": "linkPersonMsg", "sTitle":"企业联系人","sWidth":"80px","bSortable":false},
			{"mData": "linkPhoneMsg", "sTitle":"联系人手机","sWidth":"80px","bSortable":false},
			{ "mData": "status", "sTitle":"状态","sWidth":"80px","bSortable":false,"mRender":function(data){
				if(data=='0'){
					return '失效';
				}else{
					return '正常';
				}
			}},
			{"mData": "shopNum", "sTitle":"下属店铺数","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){
				var shopurl = GLOBAL.WEBROOT+'/shop/grid?companyId='+row.id;
			
				return '<a href="javascript:void(0);" id="shopurl" data-url='+shopurl+'>'+data+'</a>';
				
			}},
			{"mData": "custNum", "sTitle":"下属会员数","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){
				var custurl = GLOBAL.WEBROOT+'/cust/grid?companyId='+row.id;
			
				return '<a href="javascript:void(0);" id="custurl" data-url='+custurl+'>'+data+'</a>';
			}},
			{ "mData": "id", "sTitle":"操作","sWidth":"80px","bSortable":false,"mRender":function(data,type,row){
			
				if(row.isEnter=='0'){
					return "";
				}
				
				return '<a href="javascript:void(0)" id="addshop" companyId='+data+'>新增店铺</a>';
			}}
			/**
			 * ,
			{ "mData": "prop1", "sTitle":"其他属性1","sWidth":"90px","bSortable":false},
			{ "mData": "prop2", "sTitle":"其他属性2","sWidth":"90px","bSortable":false},
			{ "mData": "prop3", "sTitle":"其他属性3","sWidth":"90px","bSortable":false},
			
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
					eDialog.success('审核成功！',{
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
	
	$('#addshop').live('click',function(){
		eNav.setSubPageText("新增店铺");
		var companyId = $(this).attr('companyId');
		$('#companyId').val(companyId);
		$('#addShopForm').submit();
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	$('#shopurl').live('click',function(){
		var url = $(this).data('url');
		window.location.href = url;
	});
	
    $('#custurl').live('click',function(){
    	var url = $(this).data('url');
		window.location.href = url
	});
	
	$('#btn_code_add').click(function(){
		eNav.setSubPageText("新增企业");
		window.location.href = GLOBAL.WEBROOT+'/company/add';
	});
	
	$('#btn_code_edit').click(function(){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			eNav.setSubPageText("编辑企业信息");
			$('#ids').val(val[0].id);
			$('#editCompanyForm').submit();
			
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
	
	
	//使失效
	var changeStatus = function(status,tip) {
		var ids = $('#dataGridTable').getCheckIds();
		var rowData = $('#dataGridTable').getSelectedData();
		if(ids && ids.length==1){
			if (rowData[0].status == '0' && status == 'invalid') {
				eDialog.alert("您好，该企业状态已为" + tip + "，无须操作！");
				return;
			}
			if (rowData[0].status == '1' && status == 'valid') {
				eDialog.alert("您好，该企业状态已为" + tip + "，无须操作！");
				return;
			}
			eDialog.confirm("您确认要把该条记录置为" + tip + "吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : $webroot + 'company/updatestatus?status=' + status + '&companyId=' + ids,
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
	}
	//使失效
	$('#btn_code_invalid').click(function(){
		changeStatus('invalid','失效');
	});
	//使生效
	$('#btn_code_valid').click(function(){
		changeStatus('valid','生效');
	});
});


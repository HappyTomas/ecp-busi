$(function(){
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/staffgroup/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"用户组id","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "groupName", "sTitle":"用户组名称","sWidth":"80px","bSortable":false},      
            { "mData": "staffClass", "sTitle":"用户分类","sWidth":"80px","bSortable":false},      
            { "mData": "roleName", "sTitle":"所属角色","sWidth":"80px","bSortable":false},     
			{ "mData": "status", "sTitle":"用户组状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				if(data=='1'){
					return '有效';
				}else{
					return '无效';
				}
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
	//新增
	$('#btn_add').click(function(){
		eNav.setSubPageText("新增分组");//第三级面包屑
		window.location.href = GLOBAL.WEBROOT+'/staffgroup/add';
	});
	//编辑
	$('#btn_edit').click(function(){
		var val = $('#dataGridTable').getSelectedData();
        if(val && val.length==1){
        	eNav.setSubPageText("修改分组");//第三级面包屑
        	$('#groupId').val(val[0].id);
        	$('#editForm').submit();
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	
	});
	
	//失效
	$('#btn_valid').click(function(){
		var val = $('#dataGridTable').getSelectedData();
	     if(val && val.length==1){
	 		$.eAjax({
				url : GLOBAL.WEBROOT + '/staffgroup/deletegroup',
				data : {'groupId':val[0].id,'status':'0','groupName':val[0].groupName},
				success : function(returnInfo) {
					eDialog.success('失效成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = $webroot + '/staffgroup/grid';
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
	})
		//生效
	$('#btn_active').click(function(){
		var val = $('#dataGridTable').getSelectedData();
	     if(val && val.length==1){
	 		$.eAjax({
				url : GLOBAL.WEBROOT + '/staffgroup/deletegroup',
				data : {'groupId':val[0].id,'status':'1','groupName':val[0].groupName},
				success : function(returnInfo) {
					eDialog.success('生效成功！',{
						buttons:[{
							caption:"确定",
							callback:function(){
								window.location.href = $webroot + '/staffgroup/grid';
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
	})
	
});
$(function(){
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/acct/type/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "shopName", "sTitle":"资金适用店铺","sWidth":"80px","bSortable":false},      
            { "mData": "shopId", "sTitle":"资金适用店铺id","sWidth":"80px","bSortable":false,"bVisible":false}, 
            { "mData": "acctType", "sTitle":"资金类型","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "acctTypeValue", "sTitle":"资金类型","sWidth":"80px","bSortable":false},
            { "mData": "adaptType", "sTitle":"资金适用类型","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "adaptTypeValue", "sTitle":"资金适用类型","sWidth":"80px","bSortable":false},
            { "mData": "deductOrderRatio", "sTitle":"抵扣比例","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
            	if(data){
            		return data+"%";	
            	}else{
            		return "";
            	}
			}},
			{ "mData": "updateTime", "sTitle":"更新时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}}
        ],
        "eDbClick" : function(){
        	handleBiz(function(val){
        	});
        }
	});
	
	//handleBiz
	var handleBiz = function(callback){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			if(callback){
				callback(val);
			}
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
	
	//跳转到新增页面
	$('#btnFormAdd').click(function(){
		eNav.setSubPageText("新增类型");
		window.location.href = GLOBAL.WEBROOT + "/acct/type/addpage";
	});
	
	//跳转到修改页面
	$('#btnFormUpdate').click(function(){
		handleBiz(function(val){
			eNav.setSubPageText("修改类型");
    		$("#shopId").val(val[0].shopId);
    		$("#acctType").val(val[0].acctType);
    		$("#adaptType").val(val[0].adaptType);
    		$('#updateForm').submit();
    	});
	});
	
	//删除
	$('#btnFormDeleteSubmit').click(function(){
		handleBiz(function(val){
			eDialog.confirm("是否确定删除？", {
				buttons : [{
					caption : '删除',
					callback : function(){
						var rowData = $('#dataGridTable').getSelectedData()[0];
						$.eAjax({
							url : GLOBAL.WEBROOT + "/acct/type/delete",
							data : {'acctType':rowData.acctType,'adaptType':rowData.adaptType,'shopId':rowData.shopId},
							datatype:'json',
							success : function(returnInfo) {
								eDialog.success('删除成功！'); 
								$('#btnFormSearch').click();//刷新列表
							}
						});
					}
				},{
					caption : '返回',
					callback : function(){
						bDialog.closeCurrent();
					}
				}]
			});
		});
	});
	
});
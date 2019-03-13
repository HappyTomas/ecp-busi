$(function(){
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheckColumn' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/acct/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "acctTypeName", "sTitle":"资金类型","sWidth":"80px","bSortable":false},
            { "mData": "shopId", "sTitle":"适用店铺编码","sWidth":"80px","bSortable":false},    
            { "mData": "shopName", "sTitle":"适用店铺名称","sWidth":"80px","bSortable":false},    
            { "mData": "staffName", "sTitle":"资金所属用户","sWidth":"80px","bSortable":false},         
            { "mData": "staffId", "sTitle":"资金所属用户id","sWidth":"80px","bSortable":false,"bVisible":false},         
			{ "mData": "totalMoney", "sTitle":"总金额(元)","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return (data/100).toFixed(2);
			}},
			{ "mData": "balance", "sTitle":"可用金额(元)","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return (data/100).toFixed(2);
			}},
			{ "mData": "freezeMoney", "sTitle":"冻结金额(元)","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return (data/100).toFixed(2);
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
	
	//模板下载
//	$('#btnDownload').click(function(){
//	});
	
	//导入资金账户
	$('#importAcctExcel').click(function(){
		bDialog.open({
			title : '资金账户导入',
			width : 850,
			height : 550,
			url : 'importpage',
			params : {
			},
			onHidden:function(){
				$("#dataGridTable").gridReload();
			},
			callback:function(data){
				$("#dataGridTable").gridReload();
			}
		});
	});
});
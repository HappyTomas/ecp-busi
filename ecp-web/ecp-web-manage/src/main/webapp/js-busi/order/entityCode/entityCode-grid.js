$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/entitycode/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "orderId", "sTitle":"订单号","sClass":"center", "bSortable":true,"mRender": function(data,type,row){
				return '<a href="javascript:void(0)" onclick="oUtil.getOrdDetail(\''+data+'\')">'+data+'</a>';
			}},
			{ "mData": "skuInfo", "sTitle":"品牌","sClass":"center", "bSortable":true},
			{ "mData": "gdsName", "sTitle":"商品", "sClass":"center","bSortable":true},
			{ "mData": "sendDate", "sTitle":"发货时间","sClass":"center", "bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "skuInfo", "sTitle":"物流单号", "sClass":"center","bSortable":false},
			{ "mData": "entityCode", "sTitle":"实体编号","sClass":"center", "bSortable":false},
			{ "mData": "skuInfo", "sTitle":"型号","sClass":"center", "bSortable":false},
			{ "mData": "skuInfo", "sTitle":"买家名称","sClass":"center", "bSortable":false}
        ],
        "eDbClick" : function(){
        	//modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/demo/edit';
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	$('#btnFormSearch').click(function(){
		//alert(1);
		//console.info(ebcForm.formParams($("#searchForm")));
		if(!$("#searchForm").valid()) return false;
		
		/*这个方法是ajax的，后台跳转的，自然和它无关了，难道这就叫ajax跨域吗*/
		
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btn_code_add').click(function(){
		window.location.href = GLOBAL.WEBROOT+'/demo/edit';
	});
	
	$('#btn_code_modify').click(function(){
		modifyBiz();
	});
	
	$("#btn_code_more").on("click",function(){
		window.location.href = GLOBAL.WEBROOT+'/demo/more';
	});
	
});
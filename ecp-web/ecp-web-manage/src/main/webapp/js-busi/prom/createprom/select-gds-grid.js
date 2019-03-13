$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        'pCheck' : 'multi',
        "sAjaxSource": GLOBAL.WEBROOT + '/promauth/gridlist',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "gdsName", "sTitle":"商品名称【编码】","sWidth":"100px","bSortable":false},
			{ "mData": "gdsType", "sTitle":"商品分类","sWidth":"60px","bSortable":false},
			{ "mData": "skuId", "sTitle":"单品编码","sWidth":"60px","bSortable":false},
			{ "mData": "skuName", "sTitle":"单品名称","sWidth":"100px","bSortable":false},
			{ "mData": "status", "sTitle":"单品状态","sWidth":"50px","bSortable":false},
			{ "mData": "price", "sTitle":"价格","sWidth":"60px","bSortable":false},
			{ "mData": "appPrice", "sTitle":"APP价格","sWidth":"60px","bSortable":false},
			{ "mData": "promCnt", "sTitle":"<span style='color:red'>*</span>促销数量设置","sWidth":"80px","bSortable":false},
			{ "mData": "promTypeName", "sTitle":"操作","sWidth":"80px","bSortable":false}
        ],
        "eDbClick" : function(){
        	modifyBiz();
        }
	});
	
	var modifyBiz = function(){
		var _ids = $('#dataGridTable').getCheckIds();
		if(_ids && _ids.length==1){
			window.location.href = GLOBAL.WEBROOT+'/prom/edit';
		}else if(_ids && _ids.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!_ids || _ids.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
 
	 
});
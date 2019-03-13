$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pCheck' : false,
        'pCheckColumn' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/modular-load/floorLayerLoad',
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"ID"},
			{ "mData": "floorName", "sTitle":"楼层名称"},
			{ "mData": "placeName", "sTitle":"内容位置"},
			{ "mData": "statusZH", "sTitle":"状态"},
			{ "mData": "createTime", "sTitle":"创建时间","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd hh:mm:ss");
			}},
			{ "mData": "", "sTitle":"操作","bSortable":false,"mRender": function(data,type,row){
				return '<a href="javascript:void(0);" class="returnSelected" data-id="'+row.id+'" data-name="'+row.placeName+'">选择</a>';
			}}
        ],
        "onSuccess" : function(){
        	$('a.returnSelected',$("#dataGridTable")).click(function(e) {
            	bDialog.closeCurrent({
    				'floorId' : $(this).data('id'),
    				'floorName' : $(this).data('name')
    			});
        	});
        }
	});
	
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
});
$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/express/grid',
        "params" : ebcForm.formParams($("#searchForm")),
        // 指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"编码","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "status", "sTitle":"status","sWidth":"80px","bSortable":false,"bVisible":false},
			{ "mData": "expressFullName", "sTitle":"物流公司全称","sWidth":"380px","bSortable":false},
			{ "mData": "expressName", "sTitle":"物流公司","sWidth":"280px","bSortable":false},
			{ "mData": "expressWebsite", "sTitle":"公司网站","sWidth":"380px","bSortable":false},
			{ "mData": "statusCn", "sTitle":"状态","sWidth":"60px","bSortable":false}
        ]
	});
	
	$('#btnFormSearch').click(function(){
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	/**
	 * 新增按钮的操作，弹出编辑层；
	 */
	$('#btn_code_add').click(function(){
		bDialog.open({
			title : '新增物流信息',
			width : 460,
			height : 450,
			url : GLOBAL.WEBROOT + '/express/new',
			callback:function(data){
				$('#btnFormSearch').click();
			}
		});
	});
	
	/**
	 * 修改物流信息
	 */
	$('#btn_code_modify').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			expressFunction.modify(ids[0]);
			
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个物流信息进行编辑！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个物流信息进行编辑！');
		}
		
		
	});
	
	/**
	 * 注销物流信息；
	 */
	$('#btn_code_del').click(function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			eDialog.confirm("您确认注销该物流信息吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						expressFunction.invalid(ids[0]);
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个物流信息进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个物流信息进行操作！');
		}
	});
	
	$("#btn_code_valid").on('click',function(){
		var ids = $('#dataGridTable').getCheckIds();
		if(ids && ids.length==1){
			
			expressFunction.valid(ids[0]);
			
		}else if(ids && ids.length>1){
			eDialog.alert('只能选择一个物流信息进行操作！');
		}else if(!ids || ids.length==0){
			eDialog.alert('请选择至少选择一个物流信息进行操作！');
		}
	});
	
	var expressFunction = {
			modify: function(id){
					bDialog.open({
						title : '编辑物流信息',
						width : 460,
						height : 450,
						url : GLOBAL.WEBROOT + '/express/edit/'+id,
						callback:function(data){
							$('#btnFormSearch').click();
						}
					});
			},
			
			invalid : function(id){
				$.eAjax({
					url : GLOBAL.WEBROOT + '/express/invalid/' + id,
					success : function(returnInfo) {
						alert("处理成功");
						$('#btnFormSearch').click();
					}
				});
			},
			
			valid : function(id){
				$.eAjax({
					url : GLOBAL.WEBROOT + '/express/valid/' + id,
					success : function(returnInfo) {
						alert("处理成功");
						$('#btnFormSearch').click();
					}
				});
			}
	};
});
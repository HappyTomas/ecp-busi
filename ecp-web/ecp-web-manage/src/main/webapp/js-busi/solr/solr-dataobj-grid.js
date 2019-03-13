$(function(){
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single', //multi  single
        'pCheckColumn' : false, //是否显示单选/复选框的列  
        "sAjaxSource": GLOBAL.WEBROOT + '/solrdataobj/datagridlist?objectType=1&configId='+$('#configId').val(),
        //指定列数据位置
        "aoColumns": [
			{ "mData": "id", "sTitle":"编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "objectNamecn", "sTitle":"数据对象中文名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "objectNameen", "sTitle":"数据对象主表名","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "objectDesc", "sTitle":"数据对象描述信息","sWidth":"80px","sClass": "center","bSortable":false},
			{"targets": -1,"data": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return  "<a href='javascript:void(0);' class='readIndex' rowid='"+row.id+"'>查看</a>" +
					        " | <a href='javascript:void(0);' class='editIndex' rowid='"+row.id+"'>编辑</a>" +
							" | <a href='javascript:void(0);' class='clearIndex' rowid='"+row.id+"'>删除</a>";
				}
			}
        ]
	});
	
	var dataBaseObj=$('#dataGridTable');
	$('.readIndex',dataBaseObj).live('click',function(){
		eNav.setSubPageText('查看数据库搜索对象');
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj/toaddview?id="+$(this).attr('rowId')+"&isRead=1&objectType=1&configId="+$('#configId').val();
	})
	$('.editIndex',dataBaseObj).live('click',function(){
		eNav.setSubPageText('编辑数据库搜索对象');
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj/toaddview?id="+$(this).attr('rowId')+"&objectType=1&configId="+$('#configId').val();
	})
	$('.clearIndex',dataBaseObj).live('click',function(){
		var params = {id:$(this).attr('rowId'),objectType:1,configId:$('#configId').val()};
		SolrManage.clearIndex(params,'#dataGridTable','#dataGridLoading');
		e.preventDefault();
	})
	
	$('#btn_dataadd').click(function(e){
		eNav.setSubPageText('创建数据库搜索对象');
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj/toaddview?objectType=1&configId="+$('#configId').val();
		e.preventDefault();
	});
	
	$('#btn_selfadd').click(function(e){
		eNav.setSubPageText('创建数据库搜索自定义对象');
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj/toaddview?objectType=2&configId="+$('#configId').val();
		e.preventDefault();
	});
});
var SolrManage={
	clearIndex:function(params,listTb,loadBlock){
		eDialog.confirm("确认删除选中搜索对象吗？", {
			buttons : [{
				caption : '确认',
				callback : function(){
					$.gridLoading({
						"messsage" : "正在操作中...."
					});
					$.eAjax({
						url : GLOBAL.WEBROOT + "/solrdataobj/removesolrobj",
						data : params,
						success : function(returnInfo) {
							$.gridUnLoading();
							if(returnInfo.resultFlag=='ok'){
								eDialog.success(returnInfo.resultMsg, {
									buttons : [{
										caption : "确定",
										callback : function() {
											SolrManage.gridList(listTb,loadBlock);
										}
									}]
								});
							}else{
								eDialog.error(returnInfo.resultMsg);
							}
						},
						exception : function() {
							$.gridUnLoading();
						}
					});
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});
	},
	gridList : function(listTb,loadBlock){
		$.gridLoading({"el":loadBlock,"messsage":"正在加载中...."});
		$(listTb).gridSearch();
		$.gridUnLoading({"el":loadBlock});
	}
};
//自定义搜索数据对象
$(function(){
	$("#selfGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheck' : 'single', //multi  single
        'pCheckColumn' : false, //是否显示单选/复选框的列  
        "sAjaxSource": GLOBAL.WEBROOT + '/solrdataobj/datagridlist?objectType=2&configId='+$('#configId').val(),
        //指定列数据位置
        "aoColumns": [
  			{ "mData": "id", "sTitle":"编码","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "objectNamecn", "sTitle":"数据对象中文名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "objectNameen", "sTitle":"数据对象英文名称","sWidth":"80px","sClass": "center","bSortable":false},
			{ "mData": "objectDesc", "sTitle":"数据对象描述信息","sWidth":"80px","sClass": "center","bSortable":false},
			{"targets": -1,"data": "shopId","sTitle":"操作","sWidth":"80px","sClass": "center",
				"mRender": function(data,type,row){
					return  "<a href='javascript:void(0);' class='readIndex' rowid='"+row.id+"'>查看</a>" +
					        " | <a href='javascript:void(0);' class='editIndex' rowid='"+row.id+"'>编辑</a>" +
							" | <a href='javascript:void(0);' class='clearIndex' rowid='"+row.id+"'>删除</a>";
				}
			}
        ]
	});
	
	var dataBaseObj=$('#selfGridTable');
	
	$('.readIndex',dataBaseObj).live('click',function(){
		eNav.setSubPageText('查看数据库搜索自定义对象');
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj/toaddview?id="+$(this).attr('rowId')+"&isRead=1&objectType=2&configId="+$('#configId').val();
	})
	$('.editIndex',dataBaseObj).live('click',function(){
		eNav.setSubPageText('编辑数据库搜索自定义对象');
		window.location.href = GLOBAL.WEBROOT + "/solrdataobj/toaddview?id="+$(this).attr('rowId')+"&objectType=2&configId="+$('#configId').val();
	})
	$('.clearIndex',dataBaseObj).live('click',function(){
		var params = {id:$(this).attr('rowId'),objectType:2,configId:$('#configId').val()};
		SolrManage.clearIndex(params,'#selfGridTable','#selfGridLoading');
		e.preventDefault();
	})

});


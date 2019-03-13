$(function() {
	$("#dataGridTable").initDT(
					{
						'pTableTools' : false,
				        'pSingleCheckClean' : false,
				        'pCheck' : 'multi',
						"sAjaxSource" : GLOBAL.WEBROOT + '/gdsdisctblacklist/gridlist',
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"bVisible" : false,
									"sTitle" : "黑名单编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "gdsId",
									"sTitle" : "商品编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "gdsName",
									"sTitle" : "商品名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "shopId",
									"sTitle" : "店铺编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "shopName",
									"sTitle" : "店铺名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "id",
									"sTitle" : "操作",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										return "<a href='javascript:void(0);' onclick='blacklistRemove(this,"
												+ data
												+ ")'>删除</a>";
									}
								} ],
						"params" : [ {
							name : 'shopId',
							value : $("#shopId").val()
						} ]
					});

	// 查询
	$('#btnFormSearch').click(function() {
		if (!$("#searchForm").valid())
			return false;
		// 获取列表
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 重置
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
		// 获取列表
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 添加媒体
	$('#btn_code_add').click(function() {
		var shopId = $("#shopId").val();
		bDialog.open({
			title : '添加黑名单',
			width : 860,
			height : 550,
			url : GLOBAL.WEBROOT + '/gdsdisctblacklist/gotoAdd?shopId='+shopId,
			callback : function(returnInfo) {
				var params = returnInfo && returnInfo.results && returnInfo.results[0];
				blacklistAdd(params);
			}
		});
	});
	//批量删除
	$('#btn_code_batch_del').click(function() {
		batchRemove();
	});
	
});
//添加
function blacklistAdd(param){
	if(!param || !param.shopId || !param.gdsIds){
		return this;
	}
	param.gdsIdsStr = param.gdsIds.join(",");
	BlacklistGrid.add(param);
}
// 删除
function blacklistRemove(obj, id) {
	if (id == null) {
		eDialog.alert("编码为空，请联系管理员");
		return false;
	}
	eDialog.confirm('您确定要删除这条数据吗？', {
		buttons : [ {
			caption : '确定',
			callback : function() {
				BlacklistGrid.remove(obj, id);
			}
		}, {
			caption : '取消',
			callback : function() {
				return true;
			}
		} ]
	});

}
function checkIds(ids){
	var result = new Array();
	if(ids && ids.length>=1)	{
		$.each(ids,function(i,item){
			if(null != item){
				item = item + "";
			}
			if(item){
				result.push($.trim(item));
			}
		});
	}
	return result;
}
function batchRemove() {
	var ids = $('#dataGridTable').getCheckIds();
	ids = checkIds(ids);
	if(!ids || ids.length==0){
		eDialog.alert('请选择至少选择一个数据进行操作！');
		return ;
	}
	eDialog.confirm("您确认要删除这些数据吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				BlacklistGrid.batchRemove(ids);
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
}
var BlacklistGrid = {
	// 获取列表
	gridGdsList : function() {
		$('#dataGridTable').gridReload();
	},
	add:function(param){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdisctblacklist/add",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在处理中...."});
			},
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('添加成功！');
					BlacklistGrid.gridGdsList();
				} else {
					eDialog.alert(" 添加失败，请联系管理员");
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	remove : function(obj, id) {
		var param = {
			id : id,
			"shopId":$("#shopId").val()
		};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/gdsdisctblacklist/remove",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在处理中...."});
			},
			success : function(returnInfo) {
				if (returnInfo.resultFlag == 'ok') {
					eDialog.success('删除成功！');
					BlacklistGrid.gridGdsList();
				} else {
					eDialog.alert(" 删除失败，请联系管理员");
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	},
	batchRemove:function(ids){
		var param = {
				'idsStr':ids.join(","),
				'shopId' : $("#shopId").val()
		};
		$.eAjax({
			url : $webroot + '/gdsdisctblacklist/remove',
			type : "POST",
			data : param,
			datatype:'json',
			beforeSend:function(){
				$.gridLoading({"message":"正在处理中...."});
			},
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					eDialog.success('删除成功！'); 
					BlacklistGrid.gridGdsList();
				}else {
					eDialog.alert(" 删除失败，请联系管理员");
				}
			},error : function(e,xhr,opt){
				eDialog.error("出现异常!");
			},complete:function(){
				$.gridUnLoading();
			}
		});
	}
};


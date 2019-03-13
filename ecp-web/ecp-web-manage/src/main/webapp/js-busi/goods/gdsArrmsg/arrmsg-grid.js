$(function() {
		
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : true,
        'pCheckColumn' : false, 
        'pCheck' : 'multi',
		"sAjaxSource" : GLOBAL.WEBROOT + '/gdsarrmsg/arrmsglist',
		
		// 指定列数据位置
		"aoColumns" : [ {
					"mData" : "id",
					"sTitle" : "",
					"sWidth" : "80px",
					"bSortable" : true,
					"bVisible" : false
				},{
					"mData" : "gdsId",
					"sTitle" : "商品编码",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : true
					
				},{
					"mData" : "gdsName",
					"sTitle" : "商品名称",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : false
				}, {
					"mData" : "stockStatus",
					"sTitle" : "库存数量",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : false
				}, {
					"mData" : "status",
					"sTitle" : "通知状态",
					"sWidth" : "80px",
					"bSortable" : false,
					"bVisible" : false
				}, {
					"mData" : "statusName",
					"sTitle" : "通知状态",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : false
				}, 

				{
					"mData": "id",
					"sTitle" : "操作",
					"sWidth" : "80px",
					"sClass" : "center",
					"bSortable" : false,
					"mRender" : function(data, type, row) {
//						if(row.status == "1"){
//							var optStr = "<span><a href='#' onclick=\"gdsRemove(this,"+data+")\">删除</a>|<a href='#' onclick='gdsNotice(this,"+data+")'>通知</a></span>";
//						}else if(row.status == "2"){
							var optStr = "<span><a href='#' onclick=\"gdsRemove(this,"+data+")\">删除</a></span>";
//						}
						return optStr;
					}
				}

		],
        "params" : [{
			name : 'shopId',
			value : $("#shopId").val()
		}]

	});

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
	
	

	//批量通知
	$('#btn_code_notice').click(function(){
		GdsArrmsg.gdsbatchRemove();
	});



});

function gdsRemove(obj,id){
	GdsArrmsg.gdsRemove(obj,id);
}
function gdsNotice(obj,id){
	GdsArrmsg.gdsNotice(obj,id);
}

var GdsArrmsg = {
		
		
		
		gdsRemove : function(obj,id){   //删除
			
			var param = {
					id : id
			};
			eDialog.confirm("您确认删除该记录吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/gdsarrmsg/gdsremove",
							type : "POST",
							data : param,
							datatype:'json',
							success : function(returnInfo) {
								if(returnInfo.resultFlag=='ok'){
									eDialog.success('删除成功！'); 
									$('#dataGridTable').gridReload();
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		
		gdsNotice : function(obj,id){   //通知
			
			var param = {
					operateId : id
			};
			eDialog.confirm("您确认通知吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/gdsarrmsg/gdssendnotice",
							type : "POST",
							data : param,
							datatype:'json',
							success : function(returnInfo) {
								if(returnInfo.resultFlag=='ok'){
									eDialog.success('通知成功！'); 
									$('#dataGridTable').gridReload();
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		
		gdsbatchNotice : function(){   //批量通知
			var ids = $('#dataGridTable').getCheckIds();
			if(!ids || ids.length==0){
				eDialog.alert('请选择至少选择一个项目进行操作！');
				return ;
			}
			var param = {
					operateId:ids.join(",")
			};
			eDialog.confirm("您确认通知吗？", {
				buttons : [{
					caption : '确认',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/gdsarrmsg/gdssendnotice",
							type : "POST",
							data : param,
							datatype:'json',
							success : function(returnInfo) {
								if(returnInfo.resultFlag=='ok'){
									eDialog.success('通知成功！'); 
									$('#dataGridTable').gridReload();
								}
							}
						});
					}
				},{
					caption : '取消',
					callback : $.noop
				}]
			});
		},
		

		

		
};
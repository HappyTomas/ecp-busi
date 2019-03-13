/**
 * 卖家我的收藏页面初始化 js
 */
$(function() {
	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'single',
						'pCheckColumn' : false, // 是否显示单选/复选框的列
						"sAjaxSource" : GLOBAL.WEBROOT
								+ '/gdscollshop/gridlist',
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "收藏ID",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"bVisible" : false
								},
								{
									"mData" : "skuId",
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
									"bSortable" : false,
									"mRender": function(data,type,row){
										return '<a  target="_blank" href="'+row.gdsDetailUrl+'">'+data+'</a>';
									}
								},
								{
									"sTitle" : "现价",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (row.nowPrice != null)
											return "&yen;"
													+ accDiv(row.nowPrice, 100)
															.toFixed(2);
										else
											return "&yen;0.00";
									}
								},
								{
									"mData" : "collectStaffCount",
									"sTitle" : "收藏用户数",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										return " <a href='#' onclick='showCollStaff(this,"
												+ row.gdsId
												+ ","
												+ row.skuId
												+ ")'>" + data + "(点开查看用户)</a>";
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
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	// 重置
	$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
});//end of $(function(){})

//点开查看用户
function showCollStaff(obj, gdsid, skuid) {
	if (gdsid == null || skuid == null) {
		eDialog.alert("找不到此商品，请联系管理员");
		return false;
	}
	bDialog.open({
		title : '查看收藏用户',
		width : 850,
		height : 500,
		url : GLOBAL.WEBROOT + '/gdscollshop/showcollstaff?gdsId=' + gdsid
				+ "&skuId=" + skuid,
		//				params : {
		//					'gdsId' : gdsid,
		//					'skuId' : skuid
		//			    },
		callback : function() {

		}
	});
}

$(function() {
	$("#dataGridTable")
			.initDT(
					{
						'pTableTools' : false,
						'pSingleCheckClean' : true,
						'pCheck' : 'multi',
						'pCheckColumn' : false, // 是否显示单选/复选框的列
						"sAjaxSource" : GLOBAL.WEBROOT
								+ '/gdsshiptemp/gridlist',
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "id",
									"sTitle" : "运费模板编码",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "shipTemplateName",
									"sTitle" : "运费模板名称",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false

									,
									"mRender" : function(data, type, row) {
										return "<script type='text/html' style='display:block;'>"
												+ data + "</script>";
									}
								},
								{
									"mData" : "shipTemplateType",
									"sTitle" : "计价方式",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (data == '1') {
											return "按件";
										} else if (data == '2') {
											return "按重量";
										} else if (data == '3') {
											return "按体积";
										} else if (data == '4') {
											return "按金额";
										} else {
											return "";
										}
									}
								},
								{
									"mData" : "ifFree",
									"sTitle" : "是否免邮",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (data == 1) {
											return "是";
										} else {
											return "否";
										}
									}
								},
								{
									"mData" : "ifDefaultTemplate",
									"sTitle" : "是否默认运费模板",
									"sWidth" : "90px",
									"sClass" : "center",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (data == '1') {
											return "是";
										} else {
											return "否";
										}
									}
								},
								{
									"mData" : "shopName",
									"sTitle" : "店铺",
									"sWidth" : "80px",
									"sClass" : "center",
									"bSortable" : false
								},
								{
									"mData" : "shopId",
									"sTitle" : "操作",
									"sWidth" : "80px",
									"sClass" : "center",
									"mRender" : function(data, type, row) {
										return "<a href='#' onclick=\"delteTemp(this,'"
												+ row.shopId
												+ "','"
												+ row.id
												+ "')\">删除</a> | <a href='#' onclick=\"tempEdit(this,'"
												+ row.id + "')\">编辑</a>";
									}
								} ],
						"params" : [ {
							name : 'shopId',
							value : $("#shopId").val()
						} ],
						"fnDrawCallback" :function(){
						
				   
				        	/*$(".escapeTxt").each(function() {
				    			$(this).text($(this).html())
				    		});*/
						}
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
	});
	$("#btn_code_add").click(
			function() {
				eNav.setSubPageText('新增运费模板');
				window.location.href = GLOBAL.WEBROOT
						+ '/gdsshiptemp/toshiptempadd?shopId='
						+ $("#shopId").val();
			});
});
function tempEdit(obj, tempId) {
	GdsShiptemp.tempEdit(obj, tempId);
}
function delteTemp(obj, shopId, id) {
	GdsShiptemp.delteTemp(obj, shopId, id);
}
var GdsShiptemp = {
	toSkuManage : function(obj) {
		var gdsId = $(obj).parent().siblings().eq(1).text();
		bDialog.open({
			title : '单品列表',
			width : 860,
			height : 400,
			url : GLOBAL.WEBROOT + '/gdsmanage/sku-open',
			params : {
				'gdsId' : gdsId
			},
			callback : function(data) {

			}
		});
	},
	delteTemp : function(obj, shopId, id) {
		var param = {
			tempId : id,
			shopId : shopId,
		};
		eDialog.confirm("您确认删除该记录吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/gdsshiptemp/deleteshiptemp",
						data : param,
						success : function(returnInfo) {
							if (returnInfo.resultFlag == 'ok') {
								eDialog.success('删除成功！');
								if (!$("#searchForm").valid())
									return false;
								var p = ebcForm.formParams($("#searchForm"));
								$('#dataGridTable').gridSearch(p);
							} else {
								eDialog.error(returnInfo.resultMsg);
							}
						}
					});
				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});

	},
	tempEdit : function(obj, tempId) {
		eNav.setSubPageText('编辑运费模板');
		window.location.href = GLOBAL.WEBROOT
				+ "/gdsshiptemp/totempedit?templateId=" + tempId;
	}
};
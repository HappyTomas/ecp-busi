$(function() {

	// 统计上传成功文件个数
	var successCount = 0;
	$('#fileUploaderUpload').click(function() {
		if($('div.uploadify-queue-item',$('#attachmentFileQueue')).size()>0){
			$(this).button('loading');// 设置状态按钮的状态为处理中
			$.gridLoading({
						"el" : "#importdata_id",
						"messsage" : "正在加载中...."
					});// 增加遮罩
			successCount = 0;
		
			$("#excelFileInput").eUpload();
		} else {
			eDialog.warning("请选择导入文件!");
		}
	});

	// 单个文件上传成功后的回调处理，实现上传结果处理等信息均在此处理
	// 最终结果可在onQueueComplete中统一显示

//	var importTempData = function(importUuid, importFile) {
//		var importId = $("#importId").val();
//		$.eAjax({
//					url : GLOBAL.WEBROOT + "/goods/excelImport/importTempData",
//					data : {
//						"importId" : importId,
//						"importUuid" : importUuid,
//						"importFile" : importFile
//					},
//					datatype : 'json',
//					success : function(data) {
//						var reflag = data.resultFlag;// ok fail
//						var remsg = data.resultMsg;
//						if (reflag == "ok") {
//							eDialog.success("导入数据成功！一共导入" +  data.totalCount + "条,其中成功" + data.successCount +"条,失败" + data.failCount + "条");
//						} else {
//							eDialog.error("导入数据失败！" + remsg);
//						}
//						$.gridUnLoading({
//									"el" : "#importdata_id"
//								});// 取消遮罩
//						$("#dataGridTableTemp").gridReload();// 刷新表格
//					}
//				});
//
//	};

	var uploadError = function(event, queueId, fileObj, errorObj) {
		// eDialog.error("服务异常,请联系站点运维人员。");
		$.gridUnLoading({
					"el" : "#importdata_id"
				});// 取消遮罩
	};
	var sessionId = $('#pageSessionId').val();
	// 更多的参数请参考e.upload.js中的详细参数
//	$("#excelFileInput").eUploadInit({
//		'uploader' : $webroot + 'goods/excelImport/uploadfile;'+ $('#pageSessionId').val(),// 后台接收文件处理的controller
//		'fileObjName' : 'excelFile',
//		'fileTypeDesc' : "*.xls;*.xlsx", // 文件选择类型描述
//		'fileTypeExts' : "*.xls;*.xlsx", // 文件选择类型限制
//		// 'queueID' :
//		// "attachmentFileQueue",//队列内容显示元素ID指定默认ID为attachmentFileQueue
//		'queueSizeLimit' : 1,
//		'successTimeout' : 3600,
//		'multi' : false,
//		'onUploadSuccess' : uploadSuccess,
//		'onUploadError' : uploadError,
//		'onQueueComplete' : function(queueData) {
//			// console.log(queueData);
//			// console.log(queueData.uploadsSuccessful);
//		},
//		// 回调
//		'callback' : function() {
//			$('#fileUploaderUpload').button('reset');// 设置状态按钮的状态为恢复
////			$("#promGdsDataCommit").button('reset');
//		}
//	});
	$("#uploadFileButtton").eUploadBaseInit({
		uploadUrl : $webroot + 'goods/excelImport/uploadfile;'+ $('#pageSessionId').val(),// 后台接收文件处理的controller
		uploadFileObjName : 'excelFile',
		fileTypeExts : ['.xls','xlsx'], // 文件选择类型限制
		callback : function(data) {
			$("#importId").val(data.fileId);
			var param = new Array();
			param.push({
						'name' : 'importId',
						'value' : $("#importId").val()
					});
			$("#dataGridTableTemp").gridSearch(param);
			eDialog.success("上传成功！");
			$.gridUnLoading({"el" : "#importdata_id"});// 取消遮罩
		}
	});
	
	// 表格数据初始化
	$("#dataGridTableTemp").initDT({
		'pTableTools' : false,
		'pSingleCheckClean' : false,
		'pCheckColumn' : false,
		"sAjaxSource" : GLOBAL.WEBROOT + '/goods/excelImport/importLogList',
		'params' : [{
					name : 'importId',
					value : $('#importId').val()
				}],
		// 指定列数据位置
		"aoColumns" : [{
					"mData" : "importUuid",
					"sTitle" : "导入文件uuid",
					"sWidth" : "50px",
					"bSortable" : false,
					"bVisible" : false
				}, {
					"mData" : "importId",
					"sTitle" : "导入批次号",
					"sWidth" : "80px",
					"bSortable" : false
				}, {
					"mData" : "importFile",
					"sTitle" : "导入文件名称",
					"sWidth" : "70px",
					"bSortable" : false
				}, {
					"mData" : "importStatus",
					"sTitle" : "导入状态",
					"sWidth" : "80px",
					"bSortable" : false
				}, {
					"mData" : "info",
					"sTitle" : "操作",
					"sWidth" : "100px",
					"bSortable" : false,
					"mRender" : function(data, type, row) {
						var _html  =  "";
						if(row.importStatus == "未导入"){
						 _html = "<a href='javascript:void(0)' onclick='gdsExcelImportData.del("
								+ "\""
								+ row.importId
								+ "\")'>&nbsp;取消导入 &nbsp;</a>"
								+ "<a href='javascript:void(0)' onclick='gdsExcelImportData.tempImp("
								+ "\""
								+ row.importId
								+ "\","
								+ "\""
								+ row.importFile
								+ "\""
								+ ","
								+ "\""
								+ row.importUuid
								+ "\""
								+ ")'>&nbsp;导入 &nbsp;</a>";

					
							
						}else if(row.importStatus == "已导入"){
						
							 _html = "<a href='javascript:void(0)' onclick='gdsExcelImportData.listResult("
								+ "\""
								+ row.importId
								+ "\")'>&nbsp;查看导入结果 &nbsp;</a>";
						}
						
		
						return _html;
					}
				}],
		"rowCallback" : function(row, data) {
			// if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
			// $(row).addClass('selected');
			// }
		},
		"eDbClick" : function() {
			// modifyBiz();
		}
	});


});

var gdsExcelImportData = {
	// 删除
	del : function(importId) {
		eDialog.confirm("您确认删除该数据吗？", {
			buttons : [{
				caption : '确认',
				callback : function() {
					$.eAjax({
								url : GLOBAL.WEBROOT
										+ "/goods/excelImport/delImportLog",
								data : {

									'importId' : importId
								},
								datatype : 'json',
								success : function(returnInfo) {
									if (returnInfo.resultFlag == "ok") {
										eDialog.success('已删除！', {
													buttons : [{
														caption : "确定",
														callback : function() {

															$('#dataGridTableTemp')
																	.gridReload();

														}
													}]
												});
									} else {
										eDialog.alert("取消导入失败！");
									}
								}
							});
				}
			}, {
				caption : '取消',
				callback : $.noop
			}]
		});
	},

	tempImp : function(importId, importFile, importUuid) {
		var importId = $("#importId").val();
					$.gridLoading({
						"el" : "#importdata_id",
						"messsage" : "正在导入中...."
					});// 增加遮罩
			
		$.eAjax({
					url : GLOBAL.WEBROOT + "/goods/excelImport/importTempData",
					data : {
						"importId" : importId,
						"importFileUuid" : importUuid,
						"importFile" : importFile
					},
					datatype : 'json',
					success : function(data) {
						var reflag = data.resultFlag;// ok fail
						var remsg = data.resultMsg;
						if (reflag == "ok") {
							eDialog.success("导入数据成功！一共导入" +  data.totalCount + "条,其中成功" + data.successCount +"条,失败" + data.errorCount + "条");
						} else {
							eDialog.error("导入中间表失败！" + remsg);
						}
						$.gridUnLoading({
						"el" : "#importdata_id"
					});// 取消遮罩
						$("#dataGridTableTemp").gridReload();// 刷新表格
					}
				});

	},
	listResult : function(importId) {
		bDialog.open({
					title : '导入结果展示',
					width : 800,
					height : 500,
					url : GLOBAL.WEBROOT + "/goods/excelImport/watchResultList",

					params : {
						"importId" : importId
					},
					callback : function(data) {

					}
				});

	}
};
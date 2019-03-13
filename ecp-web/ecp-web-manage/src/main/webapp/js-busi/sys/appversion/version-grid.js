/**
 * 
 */
$(function() {
     
	$("#btn_code_add").live('click',function(){
		eNav.setSubPageText("新增APP版本");
		window.location.href = GLOBAL.WEBROOT
		+ '/appversion/addVersion';
		
	});
	
	
	$("#dataGridTable")
			.initDT(
					{

						'pCheckColumn' : false,
						'pTableTools' : false,
						'pSingleCheckClean' : false,
						"sAjaxSource" : GLOBAL.WEBROOT
								+ '/appversion/listVersion',
						"params" : [ ],
						// 指定列数据位置
						"aoColumns" : [
								{
									"mData" : "verNo",
									"sTitle" : "内部版本号",
									"sWidth" : "40px",
									"bSortable" : false
								
								},
								{
									"mData" : "verPublishNo",
									"sTitle" : "发布版本号",
									"sWidth" : "50px",
									"bSortable" : false
								
								},
								{
									"mData" : "verProgram",
									"sTitle" : "归属项目",
									"sWidth" : "80px",
									"bSortable" : false

								},
								{
									"mData" : "verOs",
									"sTitle" : "系统",
									"sWidth" : "50px",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										if (row.skuStatus == '99') {
											// <a href='javascript:void(0)'
											// onclick='gdsRestart(this)'>重新启用</a>
											return data;
										}
										return '<a  target="_blank" href="'
												+ row.gdsDetailUrl + '">'
												+ data + '</a>';
									}
								},
								{
									"mData" : "verUrl",
									"sTitle" : "下载地址",
									"sWidth" : "80px",
									"bSortable" : false
								},

								{
									"mData" : "status",
									"sTitle" : "状态",
									"sWidth" : "50px",
									"bSortable" : false
								},
								{
									"mData" : "opt",
									"sTitle" : "操作",
									"sWidth" : "80px",
									"bSortable" : false,
									"mRender" : function(data, type, row) {
										var renderStr = '';
										if (row.status == '未发布') {
											renderStr = "<span>" +
													"<a href='#' name='edit' id = '"
													+ row.id
													+ "'  >编辑</a> "
													+ "<a href='#' name='watchContent' verId = '"
													+ row.id
													+ "'  >查看更新内容</a> "											
													+ "<a href='#' name='publish' id = '"
													+ row.id
													+ "'  >发布</a> "
													+ "</span>";
										} else if (row.status == '发布中') {
											renderStr = "<span>" 
											+ "<a href='#' name='watchContent' verId = '"
											+ row.id
											+ "'>查看更新内容</a> "	
											+ "</span>";
										} else if (row.status == '已过时') {
											renderStr = "<span>" 
												+ "<a href='#' name='watchContent' verId = '"
												+ row.id
												+ "'>查看更新内容</a> "	
												+ "</span>";
										}
										return renderStr;

									}
								} ],
						"createdRow" : function(row, data, index) {
							 $('td', row).eq(4).css('word-break',"break-all");
						}

					});
	
	
	$('#btnFormSearch').click(function() {
		if (!$("#searchForm").valid())
			return false;

		var p = ebcForm.formParams($("#searchForm"));

		$.gridLoading({
					"el" : "#gridLoading",
					"messsage" : "正在加载中...."
				});
		$('#dataGridTable').gridSearch(p);
		$.gridUnLoading({
					"el" : "#gridLoading"
				});
	});

$('#btnFormReset').click(function() {
		ebcForm.resetForm('#searchForm');
		var catgCode = $('#catgCode');
		if (catgCode.attr("catgcode")) {
			catgCode.removeAttr("catgcode");
		}
	});

	$('a[name=edit]').live('click',function() {
		var id = $(this).attr('id');
		window.location.href = GLOBAL.WEBROOT + '/appversion/editVersion?id=' +
		id;

	});
	

	$('a[name=watchContent]').live('click',function() {
		var verId = $(this).attr('verId');
		bDialog.open({
			title : '版本详情',
			width : 350,
			height : 550,
			params : {'verId' : verId},
			url : GLOBAL.WEBROOT + '/appversion/verDetail?id='+verId ,
			callback : function(data) {
				
			
			}
		});
	});
	$('a[name=publish]').live('click',function() {
		var id = $(this).attr('id');
		var _verPublish = new Object();
		_verPublish.id=id;
		eDialog.confirm("是否发布?",{
			buttons : [{
				caption : '确认',
				callback : function(){
				var url = "/appversion/publishVer";
					$.eAjax({
						url : GLOBAL.WEBROOT + url,
						data : _verPublish,
						success : function(returnInfo) {
							eDialog.success('版本发布成功！', {
										buttons : [{
											caption : "确定",
											callback : function() {
												$('#btnFormSearch').trigger('click');
											}
										}]
									});

						},
						exception : function() {
							$.gridUnLoading();

						}
					});
					
					
					eDialog.success('发布成功！');
				
				}
			},{
				caption : '取消',
				callback : $.noop
			}]
		});


	});
	
});
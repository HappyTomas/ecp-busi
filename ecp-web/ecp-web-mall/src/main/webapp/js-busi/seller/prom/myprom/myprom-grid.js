$(function() {

	// 页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {

			//重置
			$('#btnFormReset').click(function() {
				ebcForm.resetForm('#myPromForm');
				$("#status").val($('#oldSpCode').val())
			});

			$('#myPromQueryBtn').unbind("click");
			//绑定提交按钮事件
			$('#myPromQueryBtn').click(function() {
				
						var promTheme = $('input[name="promTheme"]').val();
						var endTime = $('#endTime').val();
						var startTime = $('#startTime').val();
						var showStartTime = $('input[name="showStartTime"]').val();
						var showEndTime = $('input[name="showEndTime"]').val();
						var promTypeCode = $('#promTypeCode').val();
						var status = $('#status').val();
						var siteId = $('#siteId').val();
						var shopId = $('#shopId').val();
						
						$('#myPromListDiv').load(GLOBAL.WEBROOT + '/seller/myprom/promlist', 
								{"promTheme":promTheme,
							     "promTypeCode":promTypeCode,
							     "endTime":endTime,
							     "startTime":startTime,
							     "status":status,
							     "siteId":siteId,
							     "shopId":shopId,
							     "ifFreePost"  : $('#ifFreePost').val(),
							     "showStartTime":showStartTime,
							     "showEndTime":showEndTime		
								});
					});
		
			
			//初始化加载load数据
			$('#myPromQueryBtn').click();
		};

		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm'],
		// 指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});
});
function copy(id){
	window.location.href = GLOBAL.WEBROOT + '/seller/createprom/copy/' + id;
}
	function invalid(id){

		eDialog.confirm("您确认要失效该促销吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/seller/myprom/valid",
						data : {
							'id' : id
						},
						datatype : 'json',
						success : function(returnInfo) {
							eDialog.success('已失效！', {
								buttons : [ {
									caption : "确定",
									callback : function() {
										//$('#dataGridTable').gridReload();//改
										//window.location.href = GLOBAL.WEBROOT +'/seller/myprom';
										$('#myPromQueryBtn').click();
									}
								} ]
							});
						}
					});

				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	
}
	
	function fresh(id){
		eDialog.confirm("您确认要更新搜索吗？", {
			buttons : [ {
				caption : '确认',
				callback : function() {
					$.eAjax({
						url : GLOBAL.WEBROOT + "/seller/myprom/fresh",
						data : {
							'id' : id
						},
						datatype : 'json',
						success : function(returnInfo) {
							eDialog.success('更新搜索成功！', {
								buttons : [ {
									caption : "确定",
									callback : function() {
									}
								} ]
							});
						}
					});

				}
			}, {
				caption : '取消',
				callback : $.noop
			} ]
		});
	}
	
	function importData(id,type){
		bDialog.open({
			title : '促销商品批量导入',
			width : 1000,
			height : 600,
			url : GLOBAL.WEBROOT + "/seller/gdsprom/importGds?promId="+id+"&promTypeCode="+type,
			params : {},
			onHidden : function() {
				// $("#dataGridTable").gridReload();
			},
			callback : function(data) {
				// $("#dataGridTable").gridReload();
			}
		});

	}
	
function detail(id){
	window.location.href = GLOBAL.WEBROOT + '/seller/createprom/view/' + id;
}

function publish(id){
	eDialog.confirm("您确认要发布该促销吗？", {
		buttons : [ {
			caption : '确认',
			callback : function() {
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/myprom/publish",
					data : {
						'id' : id
					},
					datatype : 'json',
					success : function(returnInfo) {
						eDialog.success('发布成功！', {
							buttons : [ {
								caption : "确定",
								callback : function() {
									//$('#dataGridTable').gridReload();//改
									//window.location.href = GLOBAL.WEBROOT +'/seller/myprom';
									$('#myPromQueryBtn').click();
								}
							} ]
						});
					}
				});

			}
		}, {
			caption : '取消',
			callback : $.noop
		} ]
	});
}


function edit(id){
	window.location.href = GLOBAL.WEBROOT + '/seller/createprom/edit/' + id;
}


function del(id){
	eDialog.confirm("您确认删除该促销吗？", {
		buttons : [ {
			caption : '确认',
			callback : function() {
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/myprom/del",
					data : {
						'id' : id
					},
					datatype : 'json',
					success : function(returnInfo) {
						eDialog.success('已删除！', {
							buttons : [ {
								caption : "确定",
								callback : function() {
									//$('#dataGridTable').gridReload();
									$('#myPromQueryBtn').click();
								}
							} ]
						});
					}
				});
			}
		}, {
			caption : '取消',
			callback : $.noop
		} ]
	});
}


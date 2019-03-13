$(function() {

	$("#dataGridTable").initDT({
		'pTableTools' : false,
		'pSingleCheckClean' : true,
		'pCheckColumn':false,
		"sAjaxSource" : GLOBAL.WEBROOT + '/goods/gdsprop/listProp',

		// 指定列数据位置
		"aoColumns" : [{
					"mData" : "id",
					"sTitle" : "属性编码",
					"sWidth" : "80px",
					"bSortable" : true
				}, {
					"mData" : "propName",
					"sTitle" : "属性名称",
					"sWidth" : "80px",
					"bSortable" : false
				}, {
					"mData" : "propType",
					"sTitle" : "属性类型",
					"sWidth" : "80px",
					"bSortable" : false
				},

				{
					"mData" : "propValueType",
					"sTitle" : "属性值类型",
					"sWidth" : "80px",
					"bSortable" : false
				}, {
					"mData" : "status",
					"sTitle" : "状态",
					"sWidth" : "80px",
					"bSortable" : false
				}, {

					"sTitle" : "操作",
					"sWidth" : "80px",
					"bSortable" : false,
					"mRender" : function(data, type, row) {
						var optStr = null;
                        if(null == row.ifAbleEdit || '1' != row.ifAbleEdit)	{					
						   optStr = "<span><a href='#' name='editProp' data='" + row.id+"'>编辑</a>|<a href='#' ";
                        }else{
                        	optStr = "<a href='#'";
                        }
						if (row.status == '无效') {
							optStr = optStr + "name='enableProp' data='" + row.id+"'>启用</a>"
						} else {
							optStr = optStr + "name='disableProp' data='" + row.id+"'>禁用</a>"
						}
						optStr = optStr + "</span>";
						return optStr;
					}
				}

		]

	});
	PropPageInit.optBind();
	$('#btnFormSearch').click(function() {
				 if (!$("#searchForm").valid())
				 return false;
				var p = ebcForm.formParams($("#searchForm"));
				$('#dataGridTable').gridSearch(p);
				PropPageInit.optBind();
			});

	$('#btnFormReset').click(function() {
				ebcForm.resetForm('#searchForm');
			});

	$('#btn_prop_add').click(function() {
		eNav.setSubPageText('新增属性');
				var _shopId = $("select[name=shopId]").val();
				window.location.href = GLOBAL.WEBROOT
						+ '/goods/gdsprop/addProp';
			});

	

});

var PropPageInit = {

	optBind : function() {

		$('a[name=editProp]').live('click', function() {
			eNav.setSubPageText('编辑属性');
		var id = $(this).attr("data");

				window.location.href = GLOBAL.WEBROOT
						+ '/goods/gdsprop/editProp?id=' + id
			
		});

		$('a[name=disableProp]').die('click').live('click', function() {
			var id = $(this).attr("data");
			var allowDisable = false;
			// 新增属性禁用前检测.
			$.eAjax({
				url : GLOBAL.WEBROOT + '/goods/gdsprop/disableCheck',
				data : {
					'id' : id
				},
				success : function(returnInfo) {
					
					if(null != returnInfo 
							&& null != returnInfo.ecpBaseResponseVO
							&& 'ok' == returnInfo.ecpBaseResponseVO.resultFlag){
						if('false' == returnInfo.ecpBaseResponseVO.resultMsg){
							allowDisable = true;
						}
					}else{
						eDialog.error('属性删除前检测遇到异常!');
					}
				},
				async : false,
				error : function(e,xhr,opt){
					eDialog.error("属性删除前检测遇到异常!");
				},
				exception : function(msg){
					eDialog.error('属性删除前检测遇到异常!');
				}
			});
			
			if(allowDisable == false){
				eDialog.alert('该属性已经被有效分类关联不允许禁用!');
				return;
			}
			
			
			
			
				$.eAjax({
							url : GLOBAL.WEBROOT + '/goods/gdsprop/disableProp',
							data : {
								'id' : id
							},
							success : function(returnInfo) {
								eDialog.success('属性失效成功！', {
											buttons : [{
												caption : "确定",
												callback : function() {
													window.location.href = $webroot
															+ '/goods/gdsprop';
												}
											}]
										});

							}
						});

			
		});
		$('a[name=enableProp]').die('click').live('click', function() {
			var id = $(this).attr("data");
	
				$.eAjax({
							url : GLOBAL.WEBROOT + '/goods/gdsprop/enableProp',
							data : {
								'id' : id
							},
							success : function(returnInfo) {
								eDialog.success('属性启用成功！', {
											buttons : [{
												caption : "确定",
												callback : function() {
													window.location.href = $webroot
															+ '/goods/gdsprop';
												}
											}]
										});

							}
						});

			
		});
	}

}
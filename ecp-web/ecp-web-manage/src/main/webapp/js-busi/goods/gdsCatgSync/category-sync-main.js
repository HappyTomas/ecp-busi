$(function() {

	/**
	 * 常量
	 */
	var GdsCatgConstants = {

		detailUrl : GLOBAL.WEBROOT + "/goods/category/categoryview",
		treeId : "categoryTree",
		catlogIdPrefix : "CATLOG-",
		catgType : $('#catg-type').val(),
		initCatlogId : $('#param-catlogId').val(),

		TREE_NODE_URL_NORMAL : GLOBAL.WEBROOT
				+ "/goods/gdsCatgSync/asyncData/getNodes", // 分类树正常获取节点URL地址.
		TREE_NODE_URL_SEARCH : GLOBAL.WEBROOT
				+ "/goods/gdsCatgSync/asyncData/catgSyncsearch" // 分类树搜索功能获取节点URL地址.

	};

	var url = GdsCatgConstants.TREE_NODE_URL_NORMAL;

	/**
	 * zTree数据过滤.
	 */
	function ajaxDataFilter(treeId, parentNode, responseData) {
		if (responseData) {
			if (responseData.errorMessage) {
				eDialog.error('树初始异常!');
				return;
			}

		}
		return responseData;
	};

	/**
	 * zTree异步错误.
	 */
	function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest,
			textStatus, errorThrown) {
		eDialog.alert('树异步加载出错!状态码[' + textStatus + ']-' + errorThrown);
	};

	/**
	 * zTree异步成功回调.
	 */
	function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
		var catgCode = $('#v-catg-code').val();
		if (null != catgCode && '' != catgCode) {
			var treeObj = categoryTree.getTree();
			var selectNode = treeObj.getNodeByParam('id', catgCode, null);
			treeObj.selectNode(selectNode);
		}

		var treeObject = categoryTree.getTree();
		var rootNode = treeObject.getNodes();
		if (rootNode.length == 1 &&  rootNode[0].isRoot == true) {
			treeObject.expandNode(rootNode[0],true);
		}
	};

	/**
	 * html dom可初始化元素.
	 */
	var domEles = {
		// Images
		_viewImgPreviewHold : $('#view-imgPreviewHold'),
		_viewImgPreview : $('#view-imgPreview'),
		_formImgPreviewHold : $('#form-imgPreviewHold'),
		_formImgPreview : $('#form-imgPreview')
	};

	// 待切换显示DIV对象数组。
	var _divAry = new Array();
	_divAry.push(domEles._divFormContent);
	_divAry.push(domEles._divViewContent);

	/**
	 * 树节点点击事件响应。
	 */
	function onTreeClick(event, treeId, treeNode, clickFlag) {

		// 树点击详情切换时清空View表单。
		$("#viewForm")[0].reset();
		$("#defCatlog").attr("selected", "selected");

		$.eAjax({
					url : GLOBAL.WEBROOT
							+ "/goods/gdsCatgSync/categorySyncView",
					type : "POST",
					cache : false,
					timeout : 5000,
					data : {
						"catgCode" : treeNode.id,
						"srcSystem" : treeNode.srcSystem
					},
					success : function(json) {
						if (null != json && 'ok' == json.resultFlag) {
							// 如果原始分类有映射分类
							if (json.categoryVO) {
								$("#catgCode").val(json.categoryVO.catgName);
								$("#catgCode").attr('catgCode',
										json.categoryVO.catgCode);
								$("#v-catg-code").val(json.categoryVO.catgCode);
								$("#sort-no").val(json.categoryVO.sortNo);
								// alert(json.categoryVO.catlogId);
								$("#v-catlog-id option[value='"
										+ json.categoryVO.catlogId + "']")
										.attr("selected", true);
								$("input[name=catgUrl]")
										.val(json.categoryVO.catgUrl);
								$("input[name=ifShow]")
										.val(json.categoryVO.ifShow);
								var _imgUrl = json.categoryVO.mediaURL;
								if (null != _imgUrl && '' != _imgUrl) {
									domEles._viewImgPreviewHold.hide();
									domEles._viewImgPreview
											.attr('src', _imgUrl);
									domEles._viewImgPreview.show();
								} else {
									domEles._viewImgPreview.hide();
									domEles._viewImgPreviewHold.show();
									domEles._viewImgPreview.attr('src', '');
								}
								$("#btnCancel").show();
							}

						} else if (null != json && 'fail' == json.resultFlag) {
							eDialog.alert(json.resultMsg);
						} else {
							eDialog.alert('获取'
									+ GdsCatgConstants.getCatgTypeName()
									+ '信息失败!');
						}
					}
				});
	}
	/**
	 * 树节点点击前事件。
	 */
	function beforeClick(treeId, treeNode, clickFlag) {
		return (treeNode.click != false);
	}

	/**
	 * 树设置.
	 */
	var categoryTree = {
		// 商品分类树初始化功能代码段。
		settingNormal : {
			view : {
				selectedMulti : false,
				nameIsHTML : true
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			async : {
				enable : true,
				url : function() {
					return url;
				},
				type : "post",
				autoParam : ["id", "srcSystem"],
				otherParam : {

					"catgSrcSystem" : function() {
						return $('#srcSystem').val();
					},
					"catgName" : function() {
						return $('#catgName').val();
					}
					// ,
					// "ifMap" : function() {
					// return $('#ifMap').val();
					// }
				},
				dataFilter : ajaxDataFilter
			},
			callback : {
				beforeClick : beforeClick,
				onClick : onTreeClick,
				onAsyncError : zTreeOnAsyncError,
				onAsyncSuccess : zTreeOnAsyncSuccess
			}
		},
		// 搜索设置
		settingSearch : {
			view : {
				selectedMulti : false,
				nameIsHTML : true
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			async : {
				enable : true,
				url : function() {
					return url;
				},
				type : "post",
				autoParam : ["id", "srcSystem"],
				otherParam : {
					"catgSrcSystem" : function() {
						return $('#srcSystem').val();

					},
					"catgName" : function() {
						return $('#catgName').val();
					}
					// ,
					// "ifMap" : function() {
					// return $('#ifMap').val();
					// }
				},
				dataFilter : ajaxDataFilter
			},
			callback : {
				beforeClick : beforeClick,
				onClick : onTreeClick,
				onAsyncError : zTreeOnAsyncError,
				onAsyncSuccess : zTreeOnAsyncSuccess
			}
		},
		/**
		 * 获取分类树选定节点。
		 * 
		 * @returns
		 */
		getSelectedNode : function() {
			// 获取zTree
			var _catgTree = categoryTree.getTree();
			var nodes = _catgTree.getSelectedNodes();
			if (0 < nodes.length) {
				return nodes[0];
			}
		},
		getParentNode : function() {
			var nodeObj = categoryTree.getSelectedNode();
			if (nodeObj) {
				return nodeObj.getParentNode()
			}
		},
		/**
		 * 获取分类树对象。
		 * 
		 * @returns
		 */
		getTree : function() {
			return $.fn.zTree.getZTreeObj(GdsCatgConstants.treeId);
		}
	};

	/**
	 * 事件监听
	 */
	var actionListener = {};

	/***************************************************************************
	 * 属性配置JS代码段结束.*
	 **************************************************************************/
	var pageInit = {

		bindClickEvent : function() {
			var clickEventType = 'click';

			// domEles._btnImgUpload.bind(clickEventType,actionListener.imgUpload);

			/**
			 * 搜索功能.
			 */
			$('#btnFormSearch').bind('click', function() {

				// $("#btnSave").hide();
				$("#btnCancel").hide();
				// 清空表单信息
				$("#viewForm")[0].reset();
				$("#defCatlog").attr("selected", "selected");
				var _keyword = $('#catgName').val();
				var _srcSystem = $("#srcSystem").val();
				if ('' != $.trim(_keyword)) {
					url = GdsCatgConstants.TREE_NODE_URL_SEARCH;
					$.fn.zTree.init($("#" + GdsCatgConstants.treeId),
							categoryTree.settingSearch);
					url = GdsCatgConstants.TREE_NODE_URL_NORMAL;
				} else {
					url = GdsCatgConstants.TREE_NODE_URL_NORMAL;
					$.fn.zTree.init($("#" + GdsCatgConstants.treeId),
							categoryTree.settingNormal);
				}

			});

		},
		showTree : function() {
			// 树展现
			$.fn.zTree.init($("#" + GdsCatgConstants.treeId),
					categoryTree.settingNormal);
		},

		initCatlog : function() {
			if ('' != GdsCatgConstants.initCatlogId) {
				domEles._catlog.find('option').each(function() {
							if ($(this).val() != GdsCatgConstants.initCatlogId) {
								$(this).remove();
							}
						});
			}
		},
		init : function() {
			domEles._viewImgPreview.hide();
			domEles._viewImgPreviewHold.show();
			domEles._viewImgPreview.attr('src', '');

			pageInit.bindClickEvent();
			pageInit.showTree();
			pageInit.initCatlog();

			$("#catgCode").click(function() {
				bDialog.open({
					title : '分类选择',
					width : 350,
					height : 550,
					params : {
						multi : false
					},
					url : GLOBAL.WEBROOT
							+ "/goods/category/open/catgselect?catgType=1&catlogId=1",
					callback : function(data) {
						if (data && data.results && data.results.length > 0) {
							var _catgs = data.results[0].catgs;
							var size = _catgs.length;
							for (var i = 0; i < size; i++) {
								var obj = _catgs[i];
								$("#catgCode").val(obj.catgName);
								$("#catgCode").attr('catgCode', obj.catgCode);
								$("#v-catg-code").val(obj.catgCode);
								$.eAjax({
											url : GdsCatgConstants.detailUrl,
											type : "POST",
											cache : false,
											timeout : 5000,
											data : {
												"catgCode" : obj.catgCode
											},
											success : function(json) {
												$("#sort-no")
														.val(json.categoryVO.sortNo);
												// alert(json.categoryVO.catlogId);
												$("#v-catlog-id option[value='"
														+ json.categoryVO.catlogId
														+ "']").attr(
														"selected", true);
												$("input[name=catgUrl]")
														.val(json.categoryVO.catgUrl);
												$("input[name=ifShow]")
														.val(json.categoryVO.ifShow);

												$("#btnSave").show();
												$("#btnCancel").hide();
												var _imgUrl = json.categoryVO.mediaURL;
												if (null != _imgUrl
														&& '' != _imgUrl) {
													domEles._viewImgPreviewHold
															.hide();
													domEles._viewImgPreview
															.attr('src',
																	_imgUrl);
													domEles._viewImgPreview
															.show();
												} else {
													domEles._viewImgPreview
															.hide();
													domEles._viewImgPreviewHold
															.show();
													domEles._viewImgPreview
															.attr('src', '');
												}

											}
										});
							}
						}
					}
				});

			});
			// 如果分类编码不为空则展示保存按钮
			if ('' != $.trim($("#v-catg-code").val())) {
				$("#btnSave").show();
				// $("#btnCancel").hide();
			}
			// 保存分类映射
			$("#btnSave").live("click", function() {

				var nodeObj = categoryTree.getSelectedNode();

				if (!nodeObj || nodeObj.isRoot) {
					eDialog.alert("请先选择一个原始分类!");
					return;
				}
				var _mapCatgcode = $("#v-catg-code").val();
				if (!_mapCatgcode) {
					eDialog.alert("请先选择一个当前系统分类!");
					return;
				}

				$.eAjax({
							url : GLOBAL.WEBROOT
									+ "/goods/gdsCatgSync/saveCategorySync",
							type : "POST",
							cache : false,
							timeout : 5000,
							data : {
								"catgCode" : nodeObj.id,
								"mapCatgCode" : _mapCatgcode,
								"srcSystem" : nodeObj.srcSystem

							},
							success : function(json) {
								if (json.resultFlag == "success") {

									eDialog.alert("更改分类映射成功！");
									$("#btnCancel").show();
									var nodeId = "#" + nodeObj.tId + "_span";
									$(nodeId).css("color", "red");
								} else {
									eDialog.alert(json.resultMsg);
								}
							}
						});

			});

			$("#btnCancel").live("click", function() {
				var nodeObj = categoryTree.getSelectedNode();

				if (!nodeObj || nodeObj.isRoot) {
					eDialog.alert("请先选择一个原始分类!");
					return;
				}
				var _mapCatgcode = $("#v-catg-code").val();
				if (!_mapCatgcode) {
					eDialog.alert("请先选择一个当前系统分类!");
					return;
				}

				$.eAjax({
							url : GLOBAL.WEBROOT
									+ "/goods/gdsCatgSync/cancelCategorySync",
							type : "POST",
							cache : false,
							timeout : 5000,
							data : {
								"catgCode" : nodeObj.id,
								"mapCatgCode" : _mapCatgcode,
								"srcSystem" : nodeObj.srcSystem

							},
							success : function(json) {
								if (json.resultFlag == "success") {

									eDialog.alert("取消分类映射成功！");
									// $("#btnSave").hide();
									$("#btnCancel").hide();
									// 清空表单信息
									$("#viewForm")[0].reset();
									$("#defCatlog")
											.attr("selected", "selected");
									var nodeId = "#" + nodeObj.tId + "_span";
									$(nodeId).find("font").each(function() {
												$(this).attr("color", "");

											});

									$(nodeId).css("color", "");

								} else {
									eDialog.alert(json.resultMsg);
								}
							}
						});

			});

		}
	};

	pageInit.init();

});

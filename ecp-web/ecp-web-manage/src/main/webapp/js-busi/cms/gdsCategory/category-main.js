$(function(){
	/**
	 * 常量
	 */
	var GdsCatgConstants = {
			updateUrl : GLOBAL.WEBROOT + "/cms/category/categoryupdate",
		    saveUrl : GLOBAL.WEBROOT + "/cms/category/categorysave",
		    detailUrl : GLOBAL.WEBROOT + "/cms/category/categoryview",
		    treeId : "categoryTree",
//		    catlogIdPrefix : "CATLOG-",
		    shopIdPrefix : "SHOP-",
		    platType : "1",
		    shopType : "2",
		    catgType : $('#catg-type').val(),
		    initCatlogId : $('#param-catlogId').val(),
	        _addPropsUrl : GLOBAL.WEBROOT+"/cms/category/addprops",
	        _delPropsUrl : GLOBAL.WEBROOT+"/cms/category/delprops",
	        _propchangeUrl : GLOBAL.WEBROOT+"/cms/category/propchange",
			getCatgTypeName : function(){
				if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
					return '平台分类';
				}else{
					return '店铺分类';
				}
			}
	};
	
	/**
	 * zTree数据过滤.
	 */
  function ajaxDataFilter(treeId, parentNode, responseData) {
        if (responseData) {
          if(responseData.errorMessage){
        	  eDialog.error('树初始异常!');
        	  return;
          }
        }
        return responseData;
    };
    
    /**
     * zTree异步错误.
     */
    function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    	eDialog.alert('树异步加载出错!状态码['+textStatus+']-'+errorThrown);
    };
    
    /**
     * zTree异步成功回调.
     */
    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
        var catgId = $('#v-catg-id').val();
        if(null != catgId && ''!=catgId){
        	var treeObj = categoryTree.getTree();
        	var selectNode = treeObj.getNodeByParam('id',catgId,null);
			treeObj.selectNode(selectNode);
        }
    };
	
	/**
	 * html dom可初始化元素.
	 */
	var domEles = {
			// Buttons
			// 添加分类按钮.
			_btnAddCategory : $("#btnAddCategory"),
			// 添加子分类按钮.
			_btnAddSubCategory : $("#btnAddSubCategory"),
			// 删除分类按钮.
			_btnDelCategory : $("#btnDelCategory"),
			// 编辑按钮.
			_btnEditCategory : $('#btnEditCategory'),
			// 更新分类按钮.
			_btnUpdate : $('#btnUpdate'),
			// 更新重置分类按钮.
			_btnReset : $('#btnReset'),
			// 新增分类保存按钮.
			_btnSave : $('#btnSave'),
			// 新增分类,编辑分类页面取消按钮.
			_btnCancel : $('#btnCancel'),
			// 属性组配置返回按钮.
			_btnGroupBack : $('#btnGroupBack'),
			// 属性配置返回按钮.
			_btnPropBack : $('#btnPropBack'),
			// 可选属性组搜索按钮.
			_searchBtn2 : $('#search-btn-2'),
			// 已配置属性搜索按钮.
			_searchBtn3 : $('#search-btn-3'),
			// 可选属性搜索按钮.
			_searchBtn4 : $('#search-btn-4'),
			// 批量添加属性组按钮.
			_btnBatchAddGroups : $('#batch-add-groups'),
			// 批量添加属性.
			_btnBatchAddProps : $('#batch-add-props'),
			// 批量删除属性.
			_btnBatchDelProps : $('#batch-del-props'),
			// 图片上传.
			_btnImgUpload : $('#imgUpload'),
			
			// 分类详情层
		    _divViewContent : $('#viewContent'),
		    // 表单视图层
		    _divFormContent : $('#formContent'),
		    // 分类编码显示层.
		    _divCatgId : $('#catgId'),
		    
		    // Images
		    _viewImgPreviewHold : $('#view-imgPreviewHold'),
		    _viewImgPreview : $('#view-imgPreview'), 
		    _formImgPreviewHold : $('#form-imgPreviewHold'),
		    _formImgPreview : $('#form-imgPreview'),
		    // forms
		    _actionForm : $('#actionForm'),
		    _viewForm : $('#viewForm'),
		    _optionalGroupsForm : $('#optionalGroupsForm'),
		    _groupBaseInfoForm : $('#groupBaseInfoForm'),
		    _configedPropForm : $('#configedPropsForm'),
		    _optionalPropsForm : $('#optionalPropsForm'),
		    _propBaseInfoForm : $('#propBaseInfoForm'),
		    // inputs
		    _parentName : $('#parent-name'),
		    _catgParent : $('#catg-parent'),
		    _catlog : $('#catlog-id'),
		    _shopId : $('#shop-id'),
		    _catgName : $("#catg-name"),
		    _vParentName : $('#v-catg-parent'),
		    _vCatgLevel : $('#v-catg-level'),
		    _catgLevel : $("#catg-level"),
		    
		    // inputs hide
		    _catgCodeHide : $("#catg-code"),
		    _showCatgIdHide : $("#show-catg-id"),
			_mediaUuid : $('#mediaUuid')			
			
	};
	
	
	// 待切换显示DIV对象数组。
    var _divAry = new Array();
    _divAry.push(domEles._divFormContent);
    _divAry.push(domEles._divViewContent);
    
	
	 /**
     * 树节点点击事件响应。
     */
	 function onTreeClick(event, treeId, treeNode, clickFlag) {
		var treeSide=treeNode.siteId;
		if(treeSide=="null"||treeSide==null) return;
		actionListener.switchView(domEles._divFormContent);
		domEles._actionForm[0].reset();
		domEles._catgParent.val('');
		if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
			domEles._catlog.attr("disabled", true);
		}else if(GdsCatgConstants.shopType == GdsCatgConstants.catgType){
			domEles._shopId.attr("disabled", true);
		}
    	actionListener.switchActionBtn();
		$.eAjax({
			url : GdsCatgConstants.detailUrl,
			type : "POST",
			cache : false,
			timeout : 5000,
			data : {"catgId":treeNode.id},
			success : function(json) {
			    if(null != json && 'ok' == json.resultFlag){
			    	// 自动填充分类信息并展示。
			    	ebcForm.formDataFill(domEles._actionForm.attr('id'),json.cmsCategoryVO);
			    	// 填充预览图。
			    	var _imgUrl = json.cmsCategoryVO.mediaURL;
			    	if(null != _imgUrl && ''!=_imgUrl){
			    		domEles._formImgPreviewHold.hide();
			    		domEles._formImgPreview.attr('src',_imgUrl);
			    		domEles._formImgPreview.show();
			    	}else{
			    		domEles._formImgPreview.hide();
			    		domEles._formImgPreviewHold.show();
			    		domEles._formImgPreview.attr('src','');
			    	}
			    	$("#catg-id").val(json.cmsCategoryVO.id);
			    	$('#siteId').val(treeSide);
			    	
			    	var $siteId = $("#siteId");
			    	$siteId.attr("disabled","disabled");
			    	
			    	// 执行父节点名称填充。
			    	var pNode = treeNode.getParentNode();
			    	if(pNode ){
			    		$('#parent-name').val(pNode.name);
			    		$('#catg-parent').val(pNode.name);
			    	}else{
			    		$('#parent-name').val('');
			    		$('#catg-parent').val('');
			    	}
			    }else{
			    	eDialog.alert('获取信息失败!');
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
					view:{
						selectedMulti: false
					},
					data: {
						simpleData: {
							enable: true,
							idKey:"id",
							pIdKey:"pId"
						}
					},
					async: {
						enable: true,
						url:GLOBAL.WEBROOT+"/cms/category/loadnodes",
						type:"post",
						autoParam:["id"],
						otherParam:{
							"siteId":function(){
								return $('#sysSiteIdSearch').val();
							}
						},
					    dataFilter: null
					},
					callback: {
						beforeClick: beforeClick,
						onClick : onTreeClick,
						onAsyncError : zTreeOnAsyncError,
						onAsyncSuccess: zTreeOnAsyncSuccess
					}
			},
			// 搜索设置
			settingSearch : {
					view:{
						selectedMulti: false
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					async: {
						enable: true,
						url:GLOBAL.WEBROOT+"/cms/category/asyncData/catgsearch",
						type:"post",
						otherParam:{"catgName":function(){return $('#keyword').val();},"catlogId":function(){return GdsCatgConstants.initCatlogId;}},
						dataFilter: ajaxDataFilter
					},
					callback: {
						beforeClick: beforeClick,
						onClick : onTreeClick,
						onAsyncError : zTreeOnAsyncError,
						onAsyncSuccess: zTreeOnAsyncSuccess
					}
			},
			/**
			 * 获取分类树选定节点。
			 * @returns
			 */
			getSelectedNode : function(){
				// 获取zTree
		    	var _catgTree = categoryTree.getTree();
				var nodes = _catgTree.getSelectedNodes();
				if(0 < nodes.length){
					return nodes[0];
				}
			},
			getParentNode : function(){
				var nodeObj = categoryTree.getSelectedNode();
				if(nodeObj){
					return nodeObj.getParentNode();
				}
			},
			/**
			 * 获取分类树对象。
			 * @returns
			 */
			getTree : function(){
			   return $.fn.zTree.getZTreeObj(GdsCatgConstants.treeId);
			}
	};
	
	
	/**
	 * 事件监听
	 */
	var actionListener = {
		/**
		 * 添加分类事件监听.
		 */
		addCategory : function(){
			actionListener.switchView(domEles._divFormContent);
			actionListener.switchActionBtn('add');
			domEles._actionForm[0].reset();
			domEles._catgParent.val('');
			domEles._mediaUuid.val('');
			domEles._catgCodeHide.val('');
			domEles._showCatgIdHide.val('');
			domEles._catgCodeHide.attr('catg-code',"");
			domEles._showCatgIdHide.attr('show-catg-id',"");
			domEles._catgName.focus();
			var $siteId = $("#siteId");
			$siteId.val($('#sysSiteIdSearch').val());
			$siteId.attr("disabled","disabled");
			if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
				domEles._catlog.attr('disabled',false);
			}else if(GdsCatgConstants.shopType == GdsCatgConstants.catgType){
				domEles._shopId.attr('disabled',false);
			}
			
			domEles._formImgPreview.hide();
    		domEles._formImgPreviewHold.show();
    		domEles._formImgPreview.attr('src','');
		},
		/**
		 * 添加子分类事件监听.
		 */
		addSubCategory: function(){
			
			var nodeObj = categoryTree.getSelectedNode();
			
			if(!nodeObj || nodeObj.isRoot){
				eDialog.alert("请先选择一个!");
				return;
			}
			
			domEles._actionForm[0].reset();
			domEles._catgParent.val('');
			domEles._vCatgLevel.val('');
			domEles._mediaUuid.val('');
			domEles._catgCodeHide.val('');
			domEles._showCatgIdHide.val('');
			domEles._catgCodeHide.attr('catg-code',"");
			domEles._showCatgIdHide.attr('show-catg-id',"");
			var $siteId = $("#siteId");
			$siteId.val($('#sysSiteIdSearch').val());
			$siteId.attr("disabled","disabled");
			// 设置父节点信息.(需要针对catgType作区分处理)
			// 平台分类.
			domEles._catgParent.val(nodeObj.id);
			domEles._parentName.val(nodeObj.name);
			domEles._catgLevel.val(nodeObj.level+2);
			
			domEles._catgName.focus();
			domEles._formImgPreview.hide();
    		domEles._formImgPreviewHold.show();
    		domEles._formImgPreview.attr('src','');
    		
			actionListener.switchView(domEles._divFormContent);
			actionListener.switchActionBtn('add');
		},
		/**
		 * 删除分类事件监听.
		 */
		delCategory : function(){
			
			var treeObj = categoryTree.getTree();
			var nodeObj = categoryTree.getSelectedNode();
			
			if(!nodeObj || nodeObj.isRoot){
				eDialog.alert("请先选择一个进行删除!");
				return;
			}
	    	
	    	eDialog.confirm('您确认要删除选定吗?',{
				buttons:[{
					caption:"确定",
					callback:function(){
				    	$.eAjax({
							url : GLOBAL.WEBROOT + "/cms/category/categorydel",
							type : "POST",
							data : {'catgId':nodeObj.id},
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
									eDialog.success('删除成功！');
									// 删除zTree对应节点.
									treeObj.removeNode(nodeObj);
									domEles._viewForm[0].reset();
									domEles._actionForm[0].reset();
									// 隐藏所有视图.
									actionListener.hideAllView();
								}else{
									eDialog.error('信息删除失败！错误信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
								eDialog.error("删除遇到异常!"); 
							}
						});
						
					}
			},{
					caption:'取消'
			}]
			});
			
		},
		/**
		 * 编辑分类事件监听.
		 */
		editCategory : function(){
			//var parentName = $('#v-catg-parent').val();
			var parentName = $('#v-catg-parent-name').val();
			var catgCodeName = $('#v-catg-code-name').val();
			actionListener.switchView(domEles._divFormContent);
			domEles._actionForm[0].reset();
			domEles._catgParent.val('');
			if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
				domEles._catlog.attr("disabled", true);
			}else if(GdsCatgConstants.shopType == GdsCatgConstants.catgType){
				domEles._shopId.attr("disabled", true);
			}
	    	actionListener.switchActionBtn();
			$.eAjax({
				url : GdsCatgConstants.detailUrl,
				type : "POST",
				cache : false,
				timeout : 5000,
				data : {"catgId":function(){return $('#v-catg-id').val();}},
				success : function(json) {
				    if(null != json && 'ok' == json.resultFlag){
				    	// 自动填充分类信息并展示。
				    	ebcForm.formDataFill(domEles._actionForm.attr('id'),json.cmsCategoryVO);
				    	// 填充预览图。
				    	var _imgUrl = json.cmsCategoryVO.mediaURL;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		domEles._formImgPreviewHold.hide();
				    		domEles._formImgPreview.attr('src',_imgUrl);
				    		domEles._formImgPreview.show();
				    	}else{
				    		domEles._formImgPreview.hide();
				    		domEles._formImgPreviewHold.show();
				    		domEles._formImgPreview.attr('src','');
				    	}
				    	$("#catg-id").val(json.cmsCategoryVO.id);
				    	// 执行父节点名称填充。
				    	$('#parent-name').val(parentName);
				    	$('#catg-code-name').val(catgCodeName);
				    }else{
				    	eDialog.alert('获取信息失败!');
				    }
				}
			});
		},
		/**
		 * 保存分类。
		 */
		saveCategory : function(){
			if(!domEles._actionForm.valid()){
				return;
			}
			var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	        var nodes = treeObj.getSelectedNodes();
	        
			$.gridLoading({"message":"正在保存,请稍候..."});
			$.eAjax({
				url : GdsCatgConstants.saveUrl,
				type : "POST",
				data : ebcForm.formParams(domEles._actionForm),
				success : function(json) {
					$.gridUnLoading();
					if(null != json && 'ok' == json.resultFlag){
						eDialog.success('创建成功!');
						var vo = json.cmsCategoryVO;
						var newNode = null;
						clearFrom();
						newNode = {id:vo.catgId, 
								   pId:vo.catgParent,
								   name:vo.catgName,
								   catgLevel:vo.catgLevel,
								   //catlogId:vo.catlogId
								   catgId:vo.catgId,
								   siteId:vo.siteId
								   };
						if(vo.catgLevel == 1){
						 	treeObj.addNodes(null, newNode);
				        }else{
								// 有父分类，则新节点需添加在父分类下。
								// 查询出父分类。
							treeObj.addNodes(nodes[0], newNode);
						}
						treeObj.refresh();	
						//设置选中当前节点
						var allNodes = treeObj.getNodes();
						if (allNodes.length>0) {
							if(vo.catgLevel == 1){
								treeObj.selectNode(newNode);
							}else{
								treeObj.selectNode(nodes[0]);
							}
						}
				        var siteId = $("#sysSiteIdSearch").val();
						$('#siteId').attr('value', siteId);
						actionListener.switchView(domEles._divFormContent);
						domEles._actionForm[0].reset();
						domEles._catgParent.val('');
						if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
							domEles._catlog.attr("disabled", true);
						}else if(GdsCatgConstants.shopType == GdsCatgConstants.catgType){
							domEles._shopId.attr("disabled", true);
						}
				    	actionListener.switchActionBtn();
				    	ebcForm.formDataFill(domEles._actionForm.attr('id'),json.cmsCategoryVO);
				    	// 填充预览图。
				    	var _imgUrl = json.cmsCategoryVO.mediaURL;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		domEles._formImgPreviewHold.hide();
				    		domEles._formImgPreview.attr('src',_imgUrl);
				    		domEles._formImgPreview.show();
				    	}else{
				    		domEles._formImgPreview.hide();
				    		domEles._formImgPreviewHold.show();
				    		domEles._formImgPreview.attr('src','');
				    	}
				    	$("#catg-id").val(vo.id);
				    	// 执行父节点名称填充。
				    	$('#parent-name').val(vo.parentName);
				    	$('#catg-code-name').val(vo.catgCodeName);
					}else{
						eDialog.error('创建失败!错误信息：'+json.resultMsg);
					}
					
				},
				error : function(e,xhr,opt){
					eDialog.error("创建遇到异常!");
					$.gridUnLoading();
				},
				exception : function(msg){
					eDialog.error("创建遇到异常!");
					$.gridUnLoading();
				}
			});
		},
		resetCategory : function (){
			var parentName = $('#parent-name').val();
			var catgCodeName = $('#catg-code-name').val();
			var cateId=$("#catg-id").val();
			actionListener.switchView(domEles._divFormContent);
			domEles._actionForm[0].reset();
			domEles._catgParent.val('');
			if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
				domEles._catlog.attr("disabled", true);
			}else if(GdsCatgConstants.shopType == GdsCatgConstants.catgType){
				domEles._shopId.attr("disabled", true);
			}
	    	actionListener.switchActionBtn();
			$.eAjax({
				url : GdsCatgConstants.detailUrl,
				type : "POST",
				cache : false,
				timeout : 5000,
				data : {"catgId":cateId},
				success : function(json) {
				    if(null != json && 'ok' == json.resultFlag){
				    	// 自动填充分类信息并展示。
				    	ebcForm.formDataFill(domEles._actionForm.attr('id'),json.cmsCategoryVO);
				    	// 填充预览图。
				    	var _imgUrl = json.cmsCategoryVO.mediaURL;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		domEles._formImgPreviewHold.hide();
				    		domEles._formImgPreview.attr('src',_imgUrl);
				    		domEles._formImgPreview.show();
				    	}else{
				    		domEles._formImgPreview.hide();
				    		domEles._formImgPreviewHold.show();
				    		domEles._formImgPreview.attr('src','');
				    	}
				    	$("#catg-id").val(json.cmsCategoryVO.id);
				    	// 执行父节点名称填充。
				    	$('#parent-name').val(parentName);
				    	$('#catg-code-name').val(catgCodeName);
				    }else{
				    	eDialog.alert('获取信息失败!');
				    }
				}
			});
		},
		/**
		 * 更新分类。
		 */
		updateCategory : function(){
			
			if(!domEles._actionForm.valid()){
				return;
			}
			var parentName = $('#parent-name').val();
			var catgName = $('#catg-name').val();
			var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	        var nodes = treeObj.getSelectedNodes();
			$.gridLoading({"message":"正在更新,请稍候..."});
			$.eAjax({
				url : GdsCatgConstants.updateUrl,
				type : "POST",
				data : ebcForm.formParams(domEles._actionForm),
				success : function(json) {
					$.gridUnLoading();
					if(null != json && 'ok' == json.resultFlag){
						eDialog.success('更新成功!');
						nodes[0].name = catgName;
						treeObj.updateNode(nodes[0]);
						treeObj.refresh();	
						//设置选中当前节点
						var allNodes = treeObj.getNodes();
						if (allNodes.length>0) {
							treeObj.selectNode(nodes[0]);
						}
					}else{
						eDialog.error('更新失败!错误信息：'+json.resultMsg);
					}
				},
				error : function(e,xhr,opt){
					eDialog.error("更新遇到异常!");
					$.gridUnLoading();
				},
				exception : function(){
					eDialog.error("更新遇到异常!");
					$.gridUnLoading();
				}
			});
		},
		/**
		 * 分类编辑与分类添加表单取消按钮事件回调.
		 */
		actionFormCancel : function(){
			if(!categoryTree.getSelectedNode()){
				actionListener.hideAllView();
				return;
			}
			actionListener.switchView(domEles._divViewContent);
			domEles._actionForm[0].reset();
		},
		cleanErrorMsg : function(){
			$('.formValidateMessages').html('');
			$('.formValidateMessages').hide();
		},
		/**
		 * 视图切换.
		 * @param divId 需要显示的DIV ID.
		 */
		switchView : function(div){
			$.gridUnLoading();
			actionListener.cleanErrorMsg();
			var validator = domEles._actionForm.validate();
			validator.resetForm();
			$('.f_error').removeClass('f_error');
			$.each(_divAry, function(i,n){
				if(div == n){
					n.show();
				}else{
					n.hide();
				}
			});
		},
		hideAllView : function(){
			$.each(_divAry, function(i,n){
				n.hide();
			});
		},
		/**
		 * 保存更新按钮切换显示.
		 * @param type
		 */
		switchActionBtn : function(type){
			if('add' == type){
				domEles._btnSave.show();
				domEles._btnUpdate.hide();
				domEles._btnReset.hide();
				domEles._divCatgId.hide();
				domEles._btnCancel.hide();
			}else{
				domEles._btnSave.hide();
				domEles._btnUpdate.show();
				domEles._btnReset.show();
				domEles._divCatgId.show();
				domEles._btnCancel.hide();
			}
		},
		searchBtn1Action : function(){
			//var p = ebcForm.formParams(domEles._configedGroupsForm);
			//domEles._configedGroupsGridTable.gridSearch(p);
		},
		searchBtn2Action : function(){
			var p = ebcForm.formParams(domEles._optionalGroupsForm);
			domEles._optionalGroupsGridTable.gridSearch(p);
		},
		searchBtn3Action : function(){
			var p = ebcForm.formParams(domEles._configedPropForm);
			domEles._configedPropGridTable.gridSearch(p);
		},
		searchBtn4Action : function(){
			var p = ebcForm.formParams(domEles._optionalPropsForm);
			domEles._optionalPropGridTable.gridSearch(p);
		},
		backBtnAction : function(){
			actionListener.switchView(domEles._divViewContent);
		}
	};
	
	var pageInit = {
		
	    bindClickEvent : function(){
	    	var clickEventType = 'click';
	        domEles._btnAddCategory.bind(clickEventType,actionListener.addCategory);
	        domEles._btnAddSubCategory.bind(clickEventType,actionListener.addSubCategory);
	        domEles._btnDelCategory.bind(clickEventType,actionListener.delCategory);
	        domEles._btnSave.bind(clickEventType,actionListener.saveCategory);
	        domEles._btnEditCategory.bind(clickEventType,actionListener.editCategory);
	        domEles._btnUpdate.bind(clickEventType,actionListener.updateCategory);
	        domEles._btnReset.bind(clickEventType,actionListener.resetCategory);
	        domEles._btnCancel.bind(clickEventType,actionListener.actionFormCancel);
	        domEles._searchBtn2.bind(clickEventType,actionListener.searchBtn2Action);
	        domEles._searchBtn3.bind(clickEventType,actionListener.searchBtn3Action);
	        domEles._searchBtn4.bind(clickEventType,actionListener.searchBtn4Action);
	        domEles._btnGroupBack.bind(clickEventType,actionListener.backBtnAction);
	        domEles._btnPropBack.bind(clickEventType,actionListener.backBtnAction);
	        //domEles._btnImgUpload.bind(clickEventType,actionListener.imgUpload);
	        
	        //点击商品关联框弹出
	        $("#select_catgCode_btn").click(function(){
	        	var catlogId = $('#sysSiteIdSearch').val();
	        	//获取已选中数据
	        	var selectedStr = domEles._catgCodeHide.val()||"";
//	    		if($("#ifGdsScore").val()=='1'){
//	    			catlogId = '2';
//	    		}
	        	bDialog.open({
	        		title : '分类选择',
	        		width : 350,
	        		height : 550,
	        		params:{multi : true,
	        				limit:10,//不能选择多于10个分类
	        				checked:selectedStr.split("_"),
	        				fillBack:true
	        				},
	        		url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=1&catlogId="+catlogId,
	        		callback:function(data){
	        			if(data && data.results && data.results.length > 0 ){
	        				var _catgs = data.results[0].catgs;
	        				var size = (_catgs && _catgs.length) || 0;
	        				var codeNames = '';
	        				var catgCodes = "";
	        				var first = true;
        					for(var i =0;i<size;i++){
	        					var obj = _catgs[i] || {};
	        					if(obj.catgCode){
	        						if(!first){
		        						codeNames += " , ";
		        						catgCodes += "_";
		        					}else{
		        						first = false;	
		        					}
		        					codeNames += obj.catgName;
		        					catgCodes += obj.catgCode;
	        					}
	        				}
	        				$("#catg-code-name").val(codeNames);
        					$("#catg-code").val(catgCodes);
        					$("#catg-code").attr('catg-code',catgCodes);
	        			}
	        		}
	        	});
	        });
	        //点击关联本身的树
	        $(".select_cms_tree").click(function(){
	        	var siteId = $('#sysSiteIdSearch').val();
	    		bDialog.open({
	                title : '分类选择',
	                width : 350,
	                height : 550,
	                params:{'multi':false },
	                url : GLOBAL.WEBROOT+"/cms/category/open/catgselect?siteId="+siteId,
	                callback:function(data){
	                	if(data && data.results && data.results.length > 0 ){
	                        var _catgs = data.results[0].catgs;
	    					var size = _catgs.length;
	    					for(var i =0;i<size;i++){
	    						var obj = _catgs[i];
	    						$("#show-catg-id-name").val(obj.catgName);
	    						$("#show-catg-id").val(obj.catgCode);
	    						$("#show-catg-id").attr('show-catg-id',obj.catgCode);
	    					}
	    				}
	                }
	            });
	    	});
	        /**
			 * 图片上传按钮监听.
			 */
	        domEles._btnImgUpload.eUploadBaseInit({
				fileSizeLimit:domEles._btnImgUpload.data("placeSize")?domEles._btnImgUpload.data("placeSize")+'KB':'0KB',
				callback:function(fileInfo){
					if(fileInfo && fileInfo.success){
						$('#mediaUuid').val(fileInfo.fileId);
						domEles._formImgPreviewHold.hide();
						domEles._formImgPreview.show();
						domEles._formImgPreview.attr('src',fileInfo.url);
					}
				}
			});
			$("#clean_image").click(function(){
				$('#mediaUuid').val("");
				domEles._formImgPreviewHold.show();
				domEles._formImgPreview.hide();
				//domEles._formImgPreview.attr('src',fileInfo.url);
			});
	        $("#clean_catgCode").click(function(){//清除
	        	$("#catg-code-name").val("");
				$("#catg-code").val("");
				$("#catg-code").attr('catg-code',"");
	        });
	        $("#clean_showCatgId").click(function(){
	        	$("#show-catg-id-name").val("");
				$("#show-catg-id").val("");
				$("#show-catg-id").attr('show-catg-id',"");
	        });
	        /**
	    	 * 搜索功能.
	    	 */
	    	$('#search').click(function(evt){
	    		var keyword =  $("#keyword").val(); 
	            var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
	            var nodes = treeObj.getNodesByParamFuzzy("name", keyword, null);
	            //隐藏树
	            var allnodes = treeObj.getNodes(); 
	            treeObj.hideNodes(allnodes);
	            
	            for(var i=0;i<nodes.length;i++){
	            	//显示父节点
	            	showParentNodes(nodes[i]);
	            	treeObj.showNode(nodes[i]);
	            	var node = nodes[i].getParentNode();
	            	treeObj.expandNode(node, true, false, false);
	        	}
	            treeObj.refresh();
	    	});
	    	
	    	
	    	$('form[id=configedPropsForm] input[name=propName]').bind('keydown',function(e){
	    		if(e.keyCode == 13){
	    			domEles._searchBtn3.click();
	    		}
	    	});
	    	
	    	$('form[id=optionalPropsForm] input[name=propName]').bind('keydown',function(e){
	    		if(e.keyCode == 13){
	    			domEles._searchBtn4.click();
	    		}
	    	});
	        
	    	
	    	$('form[id=optionalGroupsForm] input[name=groupName]').bind('keydown',function(e){
	    		if(e.keyCode == 13){
	    			domEles._searchBtn2.click();
	    		}
	    	});
	    },
	    showTree : function(){
	    	// 树展现
	    	$.fn.zTree.init($("#"+GdsCatgConstants.treeId), categoryTree.settingNormal);
	    },
		initCatlog : function(){
			if('' != GdsCatgConstants.initCatlogId){
				domEles._catlog.find('option').each(function(){
					if($(this).val() != GdsCatgConstants.initCatlogId){
						$(this).remove();
					}
				});
			}
		},
		init : function(){
			pageInit.bindClickEvent();
			pageInit.showTree();
			$("form[id=viewForm] select[id=ifEntityCode]").attr("disabled", true);
		}
	};
	//绑定站点选择查询
	$('#sysSiteIdSearch').change(function(){
		clearFrom();
        var siteId = $("#sysSiteIdSearch").val();
		$('#siteId').attr('value', siteId);
		$.fn.zTree.init($("#categoryTree"), categoryTree.settingNormal);
	});
	function showParentNodes(nodes){
	    var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
		var node = nodes.getParentNode();
		if(node != null){
	    	treeObj.showNode(node);
			showParentNodes(node);
		}
	}
	//清空右侧表单
	function clearFrom(){
		var treeObj = $.fn.zTree.getZTreeObj("categoryTree");
		var nodes = treeObj.getSelectedNodes();
		if (nodes.length>0) { 
			treeObj.cancelSelectedNode(nodes[0]);
		}
		actionListener.hideAllView();
	};
pageInit.init();

});
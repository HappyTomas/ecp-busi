$(function(){

	
	/**
	 * 常量
	 */
	var GdsCatgConstants = {
			updateUrl : GLOBAL.WEBROOT + "/seller/goods/category/categoryupdate",
		    saveUrl : GLOBAL.WEBROOT + "/seller/goods/category/categorysave",
		    detailUrl : GLOBAL.WEBROOT + "/seller/goods/category/categoryview",
		    treeId : "categoryTree",
		    catlogIdPrefix : "CATLOG-",
		    shopIdPrefix : "SHOP-",
		    platType : "1",
		    shopType : "2",
		    catgType : $('#catg-type').val(),
		    initCatlogId : $('#param-catlogId').val(),
		    _addGroupsUrl : GLOBAL.WEBROOT+"/seller/goods/category/addgroups",
	        _delGroupsUrl : GLOBAL.WEBROOT+"/seller/goods/category/delgroups",
	        _addPropsUrl : GLOBAL.WEBROOT+"/seller/goods/category/addprops",
	        _delPropsUrl : GLOBAL.WEBROOT+"/seller/goods/category/delprops",
	        _propchangeUrl : GLOBAL.WEBROOT+"/seller/goods/category/propchange",
			getCatgTypeName : function(){
				if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
					return '平台分类';
				}else{
					return '商品分类';
				}
			},
			TREE_NODE_URL_NORMAL : GLOBAL.WEBROOT+"/seller/goods/category/asyncData/getNodes",     // 分类树正常获取节点URL地址.
	    	TREE_NODE_URL_SEARCH : GLOBAL.WEBROOT+"/seller/goods/category/asyncData/catgsearch"	 // 分类树搜索功能获取节点URL地址.
			
				
	};
	
	var url = GdsCatgConstants.TREE_NODE_URL_NORMAL;
	
	/**
	 * zTree数据过滤.
	 */
  function ajaxDataFilter(treeId, parentNode, responseData) {
        if (responseData) {
          if(responseData.errorMessage){
        	  eDialog.error(GdsCatgConstants.getCatgTypeName()+'树初始异常!');
        	  return;
          }
        }
        return responseData;
    };
    
    /**
     * zTree异步错误.
     */
    function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    	eDialog.alert(GdsCatgConstants.getCatgTypeName()+'树异步加载出错!状态码['+textStatus+']-'+errorThrown);
    };
    
    /**
     * zTree异步成功回调.
     */
    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
        var catgCode = $('#v-catg-code').val();
        if(null != catgCode && ''!=catgCode){
        	var treeObj = categoryTree.getTree();
        	var selectNode = treeObj.getNodeByParam('id',catgCode,null);
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
			// 配置属性组按钮.
			_btnCfgGroup : $("#btnConfigGroup"),
			// 配置属性按钮.
			_btnCfgProp  : $('#btnConfigProp'),
			// 编辑按钮.
			_btnEditCategory : $('#btnEditCategory'),
			// 更新分类按钮.
			_btnUpdate : $('#btnUpdate'),
			// 新增分类保存按钮.
			_btnSave : $('#btnSave'),
			// 新增分类,编辑分类页面取消按钮.
			_btnCancel : $('#btnCancel'),
			// 属性组配置返回按钮.
			_btnGroupBack : $('#btnGroupBack'),
			// 属性配置返回按钮.
			_btnPropBack : $('#btnPropBack'),
			// 已配置属性组搜索按钮.
			//_searchBtn1 : $('#search-btn-1'),
			// 可选属性组搜索按钮.
			_searchBtn2 : $('#search-btn-2'),
			// 已配置属性搜索按钮.
			_searchBtn3 : $('#search-btn-3'),
			// 可选属性搜索按钮.
			_searchBtn4 : $('#search-btn-4'),
			// 批量删除属性组按钮.
			//_btnBatchDelGroups : $('#batch-del-groups'),
			// 批量添加属性组按钮.
			_btnBatchAddGroups : $('#batch-add-groups'),
			// 批量添加属性.
			_btnBatchAddProps : $('#batch-add-props'),
			// 批量删除属性.
			_btnBatchDelProps : $('#batch-del-props'),
			// 图片上传.
			_btnImgUpload : $('#imgUpload'),
			
			
			
		    // Divs
			// 分类详情层
		    _divViewContent : $('#viewContent'),
		    // 表单视图层
		    _divFormContent : $('#formContent'),
		    // 属性配置层
		    _divPropConfig : $('#propConfig'),
		    // 属性组配置层
		    _divPropgroupConfig : $('#propgroupConfig'),
		    // 分类编码显示层.
		    _divCatgCode : $('#catgCode'),
		    
		    // Images
		    _viewImgPreviewHold : $('#view-imgPreviewHold'),
		    _viewImgPreview : $('#view-imgPreview'), 
		    _formImgPreviewHold : $('#form-imgPreviewHold'),
		    _formImgPreview : $('#form-imgPreview'),
		    // forms
		    _actionForm : $('#actionForm'),
		    _viewForm : $('#viewForm'),
		   // _configedGroupsForm : $('#configedGroupsForm'),
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
		    _vParentName : $('#v-parent-name'),
		    // grids
		    // 可选属性表格
			_optionalGroupsGridTable : $('#optionalGroupsGridTable'),
			// 已配置属性表格.
			//_configedGroupsGridTable : $('#configedGroupsGridTable'),
			// 可选属性表格.
			_optionalPropGridTable : $('#optionalPropGridTable'),
			// 已配置属性表格.
			_configedPropGridTable : $('#configedPropGridTable'),
			
			// hidden
			_mediaUuid : $('#mediaUuid')			
			
	};
	
	
	// 待切换显示DIV对象数组。
    var _divAry = new Array();
    _divAry.push(domEles._divFormContent);
    //_divAry.push(domEles._divPropConfig);
    //_divAry.push(domEles._divPropgroupConfig);
    _divAry.push(domEles._divViewContent);
    
	
	 /**
     * 树节点点击事件响应。
     */
	 function onTreeClick(event, treeId, treeNode, clickFlag) {
		 
		// 显示分类详情内容层.
	    actionListener.switchView(domEles._divViewContent);
	    // 树点击详情切换时清空View表单。
	    domEles._viewForm[0].reset();
	    
	    if(treeNode.isRoot){
	    	 // 隐藏所有视图.
			actionListener.hideAllView();
			return;
	    }
		 
    	$.eAjax({
			url : GdsCatgConstants.detailUrl,
			type : "POST",
			cache : false,
			timeout : 5000,
			data : {"catgCode":treeNode.id},
			success : function(json) {
			    if(null != json && 'ok' == json.resultFlag){
			    	// 自动填充分类信息并展示。
			    	ebcForm.formDataFill(domEles._viewForm.attr('id'),json.categoryVO);
			    	
			    	// 实体配置.
			    	if( null == json.categoryVO.ifEntityCode || '' == json.categoryVO.ifEntityCode){
			    		$("form[id=viewForm] select[id=ifEntityCode] option:first").attr("selected", 'selected');
			    	}
			    	var _imgUrl = json.categoryVO.mediaURL;
			    	if(null != _imgUrl && ''!=_imgUrl){
			    		domEles._viewImgPreviewHold.hide();
			    		domEles._viewImgPreview.attr('src',_imgUrl);
			    		domEles._viewImgPreview.show();
			    	}else{
			    		domEles._viewImgPreview.hide();
			    		domEles._viewImgPreviewHold.show();
			    		domEles._viewImgPreview.attr('src','');
			    	}
			    	// 执行父节点名称填充。
			    	/*var pNode = treeNode.getParentNode();
			    	if(pNode && pNode.level != 0){
			    		$('#v-parent-name').val(pNode.name);
			    	}else{
			    		$('#v-parent-name').val('');
			    	}*/
			    	if(null != json.parentVO){
			    		$('#v-parent-name').val(json.parentVO.catgName);
			    	}else{
			    		$('#v-parent-name').val('');
			    	}
			    	
			    }else if(null != json && 'fail' == json.resultFlag){
			    	eDialog.alert(json.resultMsg);
			    }else{
			    	eDialog.alert('获取'+GdsCatgConstants.getCatgTypeName()+'信息失败!');
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
							enable: true
						}
					},
					async: {
						enable: true,
						url:function(){return url;},
						type:"post",
						autoParam:["id"],
						otherParam:{
							"catgType":GdsCatgConstants.catgType,
							"catlogId":GdsCatgConstants.initCatlogId,
							"catgName":function(){
								return $('#keyword').val();
							  }
							},
					    dataFilter: ajaxDataFilter
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
						url:function(){return url;},
						type:"post",
						autoParam:["id"],
						otherParam:{
							"catgType":$('#catg-type').val(),
							"catgName":function(){
								   return $('#keyword').val();
								},
							"catlogId":function(){
								   return GdsCatgConstants.initCatlogId;
								}
							},
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
					return nodeObj.getParentNode()
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
			domEles._catgName.focus();
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
				eDialog.alert("请先选择一个"+GdsCatgConstants.getCatgTypeName()+"!");
				return;
			}
			
			domEles._actionForm[0].reset();
			domEles._catgParent.val('');
			domEles._mediaUuid.val('');
			// 设置父节点信息.(需要针对catgType作区分处理)
			// 平台分类.
			domEles._catgParent.val(nodeObj.id);
			domEles._parentName.val(nodeObj.name);
			domEles._catgName.focus();
			// 平台分类设置归属目录.
			if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
				domEles._catlog.val(nodeObj.catlogId);
				domEles._catlog.attr('disabled',true);
			}else if(GdsCatgConstants.shopType == GdsCatgConstants.catgType){
				debugger;
				domEles._shopId.val(nodeObj.shopId);
				domEles._shopId.attr('disabled',true);
			}
			
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
				eDialog.alert("请先选择一个"+GdsCatgConstants.getCatgTypeName()+"进行删除!");
				return;
			}
		    ///////分类删除检测开始。
            var allowEdit = actionListener.editCheck(nodeObj.id);
            if(!allowEdit){
            	eDialog.alert("该"+GdsCatgConstants.getCatgTypeName()+"不允许编辑或者删除!");
            	return;
            }
			///////分类删除检测结束。
	    	eDialog.confirm('您确认要删除选定'+GdsCatgConstants.getCatgTypeName()+'吗?',{
				buttons:[{
					caption:"确定",
					callback:function(){
				    	$.eAjax({
							url : GLOBAL.WEBROOT + "/seller/goods/category/categorydel",
							type : "POST",
							data : {'catgCode':nodeObj.id},
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
									eDialog.success(GdsCatgConstants.getCatgTypeName()+'删除成功！');
									// 删除zTree对应节点.
									treeObj.removeNode(nodeObj);
									domEles._viewForm[0].reset();
									domEles._actionForm[0].reset();
									// 隐藏所有视图.
									actionListener.hideAllView();
								}else{
									eDialog.error(GdsCatgConstants.getCatgTypeName()+'信息删除失败！错误信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
								eDialog.error("删除"+GdsCatgConstants.getCatgTypeName()+"遇到异常!"); 
							}
						});
						
					}
			},{
					caption:'取消'
			}]
			});
			
		},
		/**
		 * 分类属性组配置事件回调.
		 */
		cfgGroup : function(){
			var nodeObj = categoryTree.getSelectedNode();
			if(!nodeObj){
				eDialog.alert('请先选择一个'+GdsCatgConstants.getCatgTypeName()+'!');
				return;
			}
			// 属性组配置页面表单重置.
			domEles._groupBaseInfoForm[0].reset();
			//domEles._configedGroupsForm[0].reset();
			domEles._optionalGroupsForm[0].reset();
			
			// 切换显示属性组配置div.
			actionListener.switchView(domEles._divPropgroupConfig);
			var catgCode = $('#v-catg-code').val();
			var catgName = $('#v-catg-name').val();
			var data = {"catgCode" : catgCode, "catgName" : catgName};
			// 属性组配置页面的三个表单数据填充.
			ebcForm.formDataFill(domEles._groupBaseInfoForm.attr('id'),data);
			//ebcForm.formDataFill(domEles._configedGroupsForm.attr('id'),data);
			ebcForm.formDataFill(domEles._optionalGroupsForm.attr('id'),data);
			
			// 触发搜索按钮点击事件展示属性组配置数据.
			refreshGroupGrid();
			
		},
		/**
		 * 分类属性配置事件回调.
		 */
		cfgProp : function(){
			var nodeObj = categoryTree.getSelectedNode();
			if(!nodeObj){
				eDialog.alert('请先选择一个'+GdsCatgConstants.getCatgTypeName()+'!');
				return;
			}
			
			domEles._propBaseInfoForm[0].reset();
			domEles._configedPropForm[0].reset();
			domEles._optionalPropsForm[0].reset();
			
			actionListener.switchView(domEles._divPropConfig);
			var catgCode = $('#v-catg-code').val();
			var catgName = $('#v-catg-name').val();
			var data = {"catgCode" : catgCode, "catgName" : catgName};
			
			ebcForm.formDataFill(domEles._propBaseInfoForm.attr('id'),data);
			ebcForm.formDataFill(domEles._configedPropForm.attr('id'),data);
			ebcForm.formDataFill(domEles._optionalPropsForm.attr('id'),data);
			refreshPropGrid();
			
		},
		/**
		 * 编辑检测。
		 * @param catgCode
		 */
		editCheck : function(catgCode){
			var allowEdit = false;
			///////分类删除检测开始。
			$.eAjax({
				url : GLOBAL.WEBROOT + '/seller/goods/category/editCheck',
				data : {
					'catgCode' : catgCode
				},
				success : function(returnInfo) {
					if(null != returnInfo 
							&& null != returnInfo.ecpBaseResponseVO
							&& 'ok' == returnInfo.ecpBaseResponseVO.resultFlag){
						if('true' == returnInfo.ecpBaseResponseVO.resultMsg){
							allowEdit = true;
						}
					}else{
						//eDialog.error('分类可编辑检测遇到异常!');
						console.error("分类可编辑检测遇到异常!");
						return;
					}
				},
				async : false,
				error : function(e,xhr,opt){
					//eDialog.error("分类可编辑检测遇到异常!");
					console.error("分类可编辑检测遇到异常!");
					return;
				},
				exception : function(msg){
					//eDialog.error('分类可编辑检测遇到异常!');
					console.error("分类可编辑检测遇到异常!");
					return;
				}
			});
			
			return allowEdit;
		},
		/**
		 * 属性编辑检测。
		 * @param catgCode
		 */
		propDelCheck : function(id){
			//=======================
			var count = null;
			var datas = "catgCode="+$('#prop-catg-code').val();
			if(null != id){
				datas=datas+"&propIds="+id;
			}else{
				var _val = domEles._configedPropGridTable.getSelectedData();
				$.each( _val, function(i, n){
					  datas=datas+"&propIds="+n.propId;
				}); 
			}
			//=======================
			///////分类属性关联关系删除检测开始。
			$.eAjax({
				url : GLOBAL.WEBROOT + '/seller/goods/category/propDelCheck',
				data :datas,
				success : function(returnInfo) {
					if(null != returnInfo 
							&& null != returnInfo.ecpBaseResponseVO
							&& 'ok' == returnInfo.ecpBaseResponseVO.resultFlag){
						count = returnInfo.ecpBaseResponseVO.resultMsg;
					}else{
						//eDialog.error('分类可编辑检测遇到异常!');
						console.error("分类可编辑检测遇到异常!");
					}
				},
				async : false,
				error : function(e,xhr,opt){
					//eDialog.error("分类可编辑检测遇到异常!");
					console.error("分类可编辑检测遇到异常!");
				},
				exception : function(msg){
					//eDialog.error('分类可编辑检测遇到异常!');
					console.error("分类可编辑检测遇到异常!");
				}
			});
			
			return count;
		},
		/**
		 * 编辑分类事件监听.
		 */
		editCategory : function(){
			var categoryCode = $('#v-catg-code').val();
		    var allowEdit = actionListener.editCheck(categoryCode);
		    
		    if(!allowEdit){
		    	eDialog.alert('该'+GdsCatgConstants.getCatgTypeName()+'不允许编辑!');
		    	return;
		    }
			
			var parentName = $('#v-parent-name').val();
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
				data : {"catgCode":function(){return $('#v-catg-code').val();}},
				success : function(json) {
				    if(null != json && 'ok' == json.resultFlag){
				    	// 自动填充分类信息并展示。
				    	ebcForm.formDataFill(domEles._actionForm.attr('id'),json.categoryVO);
				    	// 实体配置.
				    	if( null == json.categoryVO.ifEntityCode){
				    		$("form[id=viewForm] select[id=ifEntityCode] option:first").attr("selected", 'selected');
				    	}
				    	var _imgUrl = json.categoryVO.mediaURL;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		domEles._formImgPreviewHold.hide();
				    		domEles._formImgPreview.attr('src',_imgUrl);
				    		domEles._formImgPreview.show();
				    	}else{
				    		domEles._formImgPreview.hide();
				    		domEles._formImgPreviewHold.show();
				    		domEles._formImgPreview.attr('src','');
				    	}
				    	// 执行父节点名称填充。
				    	$('#parent-name').val(parentName);
				    }else{
				    	eDialog.alert('获取'+GdsCatgConstants.getCatgTypeName()+'信息失败!');
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
			if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
					domEles._catlog.attr('disabled',false);
			}else if(GdsCatgConstants.platType == GdsCatgConstants.catgType){
					domEles._shopId.attr('disabled',false);
			}
			//$.gridLoading({"message":"正在保存"+GdsCatgConstants.getCatgTypeName()+",请稍候..."});
			$.eAjax({
				url : GdsCatgConstants.saveUrl,
				type : "POST",
				data : ebcForm.formParams(domEles._actionForm),
				success : function(json) {
					//$.gridUnLoading();
					if(null != json && 'ok' == json.resultFlag){
						eDialog.success(GdsCatgConstants.getCatgTypeName()+'创建成功!');
						var vo = json.categoryVO;
						var newNode = null;
						// 获取分类树对象。
						var treeObj = categoryTree.getTree();
						var parentNode = null;
						// 商品分类创建成功两种回调情况：
						// case 1 : 平台分类（有目录归属）
						if(GdsCatgConstants.platType == vo.catgType){
							// 无父分类，则新节点需新增在根目录下。
							if(vo.catgLevel == 1){
								newNode = {id:vo.catgCode, 
									   pId:GdsCatgConstants.catlogIdPrefix+vo.catlogId,
									   name:vo.catgName,
									   catgLevel:vo.catgLevel,
									   catlogId:vo.catlogId};
								// 根据分类目录ID获取目录节点。
								parentNode = treeObj.getNodeByParam("id", GdsCatgConstants.catlogIdPrefix+vo.catlogId, null);
							}else{
								// 有父分类，则新节点需添加在父分类下。
								newNode = {id:vo.catgCode, 
										   pId:vo.catgParent,
										   name:vo.catgName,
										   catgLevel:vo.catgLevel,
										   catlogId:vo.catlogId};
								// 查询出父分类。
								parentNode = treeObj.getNodeByParam("id", vo.catgParent, null);
							}
							
						}else{
							// case 2 : 店铺分类（无父分类,则为根分类,节点挂在店铺下.）
							if(1 == vo.catgLevel){
								newNode = {id:vo.catgCode, 
										   pId:GdsCatgConstants.shopIdPrefix+vo.shopId,
										   name:vo.catgName,
										   catgLevel:vo.catgLevel,
										   shopId:vo.shopId
										   };
									// 根据分类目录ID获取目录节点。
									parentNode = treeObj.getNodeByParam("id", GdsCatgConstants.shopIdPrefix+vo.shopId, null);
							}else{
								// 有父分类，则新节点需添加在父分类下。
								newNode = {id:vo.catgCode, 
										   pId:vo.catgParent,
										   name:vo.catgName,
										   catgLevel:vo.catgLevel,
										   shopId:vo.shopId};
								// 查询出父分类。
								parentNode = treeObj.getNodeByParam("id", vo.catgParent, null);
							}
						}
						
						if(null != parentNode){
						    treeObj.expandNode(parentNode, true, false, true);
						}
						
						var selectNode = treeObj.getNodeByParam('id',newNode.id,null);
						if(selectNode){
							treeObj.selectNode(selectNode);
						}else{
							treeObj.addNodes(parentNode, newNode);
							selectNode = treeObj.getNodeByParam('id',newNode.id,null);
							treeObj.selectNode(selectNode);
						}
						
						
						// UI切换,图片预览切换.
						actionListener.switchView(domEles._divViewContent);
						// 重置视图表单并，自动填充分类信息展示。
						domEles._viewForm[0].reset();
				    	ebcForm.formDataFill(domEles._viewForm.attr('id'),vo);
				    	if(null != vo.catgParent){
							if(parentNode && 0 != parentNode.level){
								domEles._vParentName.val(parentNode.name);
							}else{
								domEles._vParentName.val('');
							}
						}else{
							domEles._vParentName.val('');
						}
						
						var _imgUrl = vo.mediaURL;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		domEles._viewImgPreviewHold.hide();
				    		domEles._viewImgPreview.attr('src',_imgUrl);
				    		domEles._viewImgPreview.show();
				    	}else{
				    		domEles._viewImgPreview.hide();
				    		domEles._viewImgPreviewHold.show();
				    		domEles._viewImgPreview.attr('src','');
				    	}
				    	domEles._actionForm[0].reset();
				    	
				    	
					}else{
						eDialog.error(GdsCatgConstants.getCatgTypeName()+'创建失败!错误信息：'+json.resultMsg);
					}
					
				},
				error : function(e,xhr,opt){
					eDialog.error(GdsCatgConstants.getCatgTypeName()+"创建遇到异常!");
					$.gridUnLoading();
				},
				exception : function(msg){
					$.gridUnLoading();
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
			//domEles._catlog.addAttr('disabled','disabled');
			//domEles._catlogAll.addAttr('disabled','disabled');
			//$.gridLoading({"message":"正在更新"+GdsCatgConstants.getCatgTypeName()+",请稍候..."});
			$.eAjax({
				url : GdsCatgConstants.updateUrl,
				type : "POST",
				data : ebcForm.formParams(domEles._actionForm),
				success : function(json) {
					//$.gridUnLoading();
					if(null != json && 'ok' == json.resultFlag){
						eDialog.success(GdsCatgConstants.getCatgTypeName()+'更新成功!');
						var vo = json.categoryVO;
						// 获取分类树对象。
						var treeObj = categoryTree.getTree();
                        // $('#v-parent-name').val(parentName);
                        
                        var nodeObj = categoryTree.getSelectedNode();
                        
                        nodeObj.name = vo.catgName;
                        treeObj.updateNode(nodeObj);
                        treeObj.selectNode(nodeObj);
						actionListener.switchView(domEles._divViewContent);
						// 重置视图form并自动填充分类信息展示。
						domEles._viewForm[0].reset();
				    	ebcForm.formDataFill(domEles._viewForm.attr('id'),vo);
				    	$('#v-parent-name').val(parentName);
				    	if( null == json.categoryVO.ifEntityCode || '' == json.categoryVO.ifEntityCode){
				    		$("form[id=viewForm] select[id=ifEntityCode] option:first").attr("selected", 'selected');
				    	}
				    	var _imgUrl = vo.mediaURL;
				    	if(null != _imgUrl && ''!=_imgUrl){
				    		domEles._viewImgPreviewHold.hide();
				    		domEles._viewImgPreview.attr('src',_imgUrl);
				    		domEles._viewImgPreview.show();
				    	}else{
				    		domEles._viewImgPreview.hide();
				    		domEles._viewImgPreviewHold.show();
				    		domEles._viewImgPreview.attr('src','');
				    	}
				    	domEles._actionForm[0].reset();
							
					}else{
						eDialog.error(GdsCatgConstants.getCatgTypeName()+'更新失败!错误信息：'+json.resultMsg);
					}
				},
				error : function(e,xhr,opt){
					eDialog.error(GdsCatgConstants.getCatgTypeName()+"更新遇到异常!");
					$.gridUnLoading();
				},
				exception : function(){
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
			//$.gridUnLoading();
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
				domEles._divCatgCode.hide();
			}else{
				domEles._btnSave.hide();
				domEles._btnUpdate.show();
				domEles._divCatgCode.show();
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
		},
		/**
		 * 图片上传按钮监听.
		 */
		imgUpload : function(){
			var filepath = $(this).val();
			filepath = (filepath + '').toLowerCase();
			var regex = new RegExp(
					'\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$', 'gi');
			/** 上传图片文件格式验证 */
			if (!filepath || !filepath.match(regex)) {
				eDialog.info('请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp).');
				return;
			}
			var url = GLOBAL.WEBROOT + '/seller/goods/category/uploadImage';
			var callback = function(data, status) {
				/** 上传成功，隐藏上传组件，并显示该图片 */
				if (status == "success") {
					if (data == null) {
						eDialog.alert("图片上传失败!");
					} else {
						if (data.flag == true) {
							$('#mediaUuid').val(data.imageId);
							domEles._formImgPreviewHold.hide();
							domEles._formImgPreview.show();
							domEles._formImgPreview.attr('src',data.imagePath);
	
						} else {
							eDialog.alert(data.error);
						}
					}
				} else {
	
					eDialog.alert("图片上传失败");
				}
			};
			actionListener.ajaxFileUpload(url, false,"POST",
					"json", callback);
		},
		ajaxFileUpload : function(url, secureuri, type, dataType,
			callback) {
			$.ajaxFileUpload({
						url : url, // 用于文件上传的服务器端请求地址
						secureuri : secureuri, // 一般设置为false
						fileElementId : "imgUpload", // 文件上传空间的id属性
						type : type, // get 或 post
						dataType : dataType, // 返回值类型
						success : callback, // 服务器成功响应处理函数
						error : function(data, status, e) // 服务器响应失败处理函数
						{
							eDialog.info(e);
						}
					});
		}
	};
	
	/***********************
	 *  属性组配置JS代码段开始.*
	 ***********************/
	function refreshGroupGrid(){
        // domEles._searchBtn1.click();
         domEles._searchBtn2.click();
	}
	// 添加单个属性组.
	var _fnAddGroup = function(id){
		if( null == id ){
			eDialog.alert('请选择一个属性组进行添加');
			return;
		}
		$.eAjax({
			url : GdsCatgConstants._addGroupsUrl,
			type : "POST",
			data : {
				"groupIds" : id,
				"catgCode" :$('#group-catg-code').val()
				},
			success : function(json) {
				if(null != json && 'ok' == json.resultFlag){
						eDialog.success('与属性组关联属性已经成功关联至当前分类,请点击属性配置按钮进行查看!');
						refreshGroupGrid();
				}else{
						eDialog.error('添加属性组遇到异常！异常信息:'+json.resultMsg);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("添加属性组遇到异常!");
			}
		});
	};
	
	// 删除单个关联属性组.
	/*var _fnDelGroup = function(id){
		if( null == id ){
			eDialog.alert('请选择一个属性组进行删除');
			return;
		}
		$.eAjax({
			url : GdsCatgConstants._delGroupsUrl,
			type : "POST",
			data : {
				"groupIds" : id,
				"catgCode" :$('#group-catg-code').val()
				},
			success : function(json) {
				if(null != json && 'ok' == json.resultFlag){
						eDialog.success('属性组删除成功！');
						refreshGroupGrid();
				}else{
						eDialog.error('删除属性组遇到异常！异常信息:'+json.resultMsg);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("删除属性组遇到异常!");
			}
		});
	};*/
	
	
	/**
	 * 批量删除属性组.
	 */
	/*var _fnBatchDelGroups = function(){
		var ids = domEles._configedGroupsGridTable.getCheckIds();
		var datas = "catgCode="+$('#group-catg-code').val();
		if(ids && ids.length>0){
			
			eDialog.confirm("您是否要批量删除选定属性组?",{
				buttons:[{
					caption:"确定",
					callback:function(){
						var _val = domEles._configedGroupsGridTable.getSelectedData();
						$.each( _val, function(i, n){
							  datas=datas+"&groupIds="+n.propGroupId;
						}); 
						$.eAjax({
							url : GdsCatgConstants._delGroupsUrl,
							type : "POST",
							data : datas,
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
										eDialog.success('属性组批量删除成功！');
										refreshGroupGrid();
								}else{
										eDialog.error('属性组批量删除遇到异常！异常信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
									eDialog.error("属性组批量删除遇到异常!");
							}
						});
					}
			},{
					caption:'取消'
			}]
			});
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个已配置属性组进行删除！');
			return;
		}
	};*/
	/**
	 * 批量添加属性组.
	 */
	var _fnBatchAddGroups = function(){
		var ids = domEles._optionalGroupsGridTable.getCheckIds();
		var datas = "catgCode="+$('#group-catg-code').val();
		if(ids && ids.length>0){
			eDialog.confirm("您是否要批量添加选定属性组?",{
				buttons:[{
					caption:"确定",
					callback:function(){
						var _val = domEles._optionalGroupsGridTable.getSelectedData();
						$.each( _val, function(i, n){
							  datas=datas+"&groupIds="+n.id;
						}); 
						$.eAjax({
							url : GdsCatgConstants._addGroupsUrl,
							type : "POST",
							data : datas,
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
										eDialog.success('与属性组关联属性已经成功关联至当前分类,请点击属性配置按钮进行查看!');
										refreshGroupGrid();
								}else{
										eDialog.error('属性批量添加遇到异常！异常信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
									eDialog.error("属性组批量添加遇到异常!");
							}
						});
					}
			},{
					caption:'取消'
			}]
			});
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个属性组添加！');
			return;
		}
	};
	
	/**
	 * 已配置属性组数据栅格.
	 */
	/*var _initConfigedGroupsDT = {

			'pTableTools' : false,
			'pSingleCheckClean' : true,
			"sAjaxSource" : GLOBAL.WEBROOT + '/goods/category/listconfigedgroups',
			'pCheck' : 'multi',
			// 指定列数据位置
			"aoColumns" : [{
						"mData" : "propGroupId",
						"sTitle" : "属性组编码",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "groupName",
						"sTitle" : "属性组名称",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						
						"sTitle" : "操作",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "<span><a href='#' name='delgroup' data="+row.propGroupId+">删除</a>";
							return optStr;
						}
					}
			]
	};*/
	/**
	 * 可选属性组数据栅格配置.
	 */
	var _initOptGroupsDT = {

			'pTableTools' : false,
			'pCheck' : 'multi',
			'pSingleCheckClean' : true,
			"sAjaxSource" : GLOBAL.WEBROOT + '/seller/goods/category/listoptgroups',
			// 指定列数据位置
			"aoColumns" : [{
						"mData" : "id",
						"sTitle" : "属性组编码",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "groupName",
						"sTitle" : "属性组名称",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						
						"sTitle" : "操作",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "<span><a href='#' name='addgroup' data="+row.id+" >添加</a>";
							return optStr;
						}
					}
			]
	};
	
	
	/***********************
	 *  属性组配置JS代码段结束.*
	 ***********************/
	
	/***********************
	 *  属性配置JS代码段开始.*
	 ***********************/
	/**
	 * 判断checkbox是否是选中状态.
	 * @param obj 请传入jQuery对象.
	 */
	function isChecked(obj){
		if(null == obj){
			return false;
		}else{
           if('checked' == obj.attr('checked')){
        	   return true;
           }else{
        	   return false;
           } 			
		}
	}
	/**
	 * 还原复选框.
	 * @param obj 请传入jquery复选框对象.
	 */
	function restoreCheckBox(obj){
		if(null == obj)
			return ;
		if('checked' == obj.attr('checked')){
			obj.removeAttr('checked');
		}else{
			obj.attr('checked','checked');
		}
	}
	
	function refreshPropGrid(){
        domEles._searchBtn3.click();
        domEles._searchBtn4.click();
	}
	
	// 添加单个属性.
	var _fnSingleAddProp = function(id){
		if( null == id ){
			eDialog.alert('请选择一个属性进行添加');
			return;
		}
		$.eAjax({
			url : GdsCatgConstants._addPropsUrl,
			type : "POST",
			data : {
				"propIds" : id,
				"catgCode" :$('#prop-catg-code').val()
				},
			success : function(json) {
				if(null != json && 'ok' == json.resultFlag){
						eDialog.success('添加属性成功！');
						refreshPropGrid();
				}else{
						eDialog.error('添加属性遇到异常！异常信息:'+json.resultMsg);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("添加属性遇到异常!");
			}
		});
	};
	
	
	/**
	 * 已配置属性Grid.
	 */
	var _initConfigedPropGridDT = {
			'pTableTools' : false,
			'pSingleCheckClean' : true,
			"sAjaxSource" : GLOBAL.WEBROOT + '/seller/goods/category/listconfigedprop',
			'pCheck' : 'multi',
			// 指定列数据位置
			"aoColumns" : [{
						"mData" : "propId",
						"sTitle" : "属性编码",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propName",
						"sTitle" : "属性名称",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propTypeName",
						"sTitle" : "属性类型",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propValueTypeName",
						"sTitle" : "属性值类型",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "ifBasic",
						"sTitle" : "基础属性",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "";
							// 规格属性不能作为基础属性. 规格属性为1.
							if('1' != row.propType){
								if('1' == data){
									optStr = '<input type="checkbox" name="ifBasic" value="1" data="'+row.propId+'" checked/>';
								}else{
									optStr = '<input type="checkbox" name="ifBasic" value="1" data="'+row.propId+'"/>';
								}
							}
							return optStr;
						}
					},{
						"mData" : "ifHaveto",
						"sTitle" : "是否必填",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "";
							if('1' == data){
								optStr = '<input type="checkbox" name="ifHaveTo" value="1" data="'+row.propId+'" checked/>';
							}else{
								optStr = '<input type="checkbox" name="ifHaveTo" value="1" data="'+row.propId+'"/>';
							}
							return optStr;
						}
					},{
						"mData" : "ifSearch",
						"sTitle" : "是否搜索",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "";
							// 手动输入的属性，不能作为搜索属性 属性值类型:手工输入 1
							if('1' != row.propValueType){
								if('1' == data){
									optStr = '<input type="checkbox" name="ifSearch" value="1" data="'+row.propId+'" checked/>';
								}else{
									optStr = '<input type="checkbox" name="ifSearch" value="1" data="'+row.propId+'"/>';
								}
							}
							return optStr;
						}
					}
//					,{
//						"mData" : "ifGdsInput",
//						"sTitle" : "是否输入",
//						"sWidth" : "70px",
//						"sClass" : "center",
//						"bSortable" : false,
//						"mRender" : function(data, type, row) {
//							var optStr = "";
//							if('1' == data){
//								optStr = '<input type="checkbox" name="ifGdsInput" value="1" data="'+row.propId+'" checked/>';
//							}else{
//								optStr = '<input type="checkbox" name="ifGdsInput" value="1" data="'+row.propId+'"/>';
//							}
//							return optStr;
//						}
//					}
					,{
						
						"sTitle" : "操作",
						"sWidth" : "70px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = null;
							if('1' != row.ifAbleEdit){
								optStr = "<span><a href='#' name='delProp' data="+row.propId+">删除</a></span>";
							}else{
								optStr = "";
							}
							return optStr;
						}
					}
			]

		};
	/**
	 * 可选属性Grid初始.
	 */
	var _initOptPropGridDT = {
			'pTableTools' : false,
			'pCheck' : 'multi',
			'pSingleCheckClean' : true,
			"sAjaxSource" : GLOBAL.WEBROOT + '/seller/goods/category/listoptionalprop',
			// 指定列数据位置
			"aoColumns" : [{
						"mData" : "id",
						"sTitle" : "属性编码",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propName",
						"sTitle" : "属性名称",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propType",
						"sTitle" : "属性类型",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						"mData" : "propValueType",
						"sTitle" : "属性值类型",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false
					},{
						
						"sTitle" : "操作",
						"sWidth" : "80px",
						"sClass" : "center",
						"bSortable" : false,
						"mRender" : function(data, type, row) {
							var optStr = "<span><a href='#' name='addProp' data="+row.id+" >添加</a>";
							return optStr;
						}
					}
			]

		};
	
	// 删除单个关联属性.
	var _fnSingleDelProp = function(id){
		if( null == id ){
			eDialog.alert('请选择一个属性进行删除');
			return;
		}
		
		var count = actionListener.propDelCheck(id);
        
		if(null == count){
			alert("删除属性前检测遇到异常!");
			return;
		}else{
			if(0 < count){
				alert("属性不允许删除!");
				return;
			}
		}
		
		
		$.eAjax({
			url : GdsCatgConstants._delPropsUrl,
			type : "POST",
			data : {
				"propIds" : id,
				"catgCode" :$('#prop-catg-code').val()
				},
			success : function(json) {
				if(null != json && 'ok' == json.resultFlag){
						eDialog.success('属性关联删除成功！');
						refreshPropGrid();
				}else{
						eDialog.error('删除属性关联遇到异常！异常信息:'+json.resultMsg);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("删除属性遇到异常!");
			}
		});
	};
	var _fnBatchDelProps = function(){
		var ids = domEles._configedPropGridTable.getCheckIds();
		var datas = "catgCode="+$('#prop-catg-code').val();
		if(ids && ids.length>0){
			eDialog.confirm("您是否要批量删除选定属性?",{
				buttons:[{
					caption:"确定",
					callback:function(){
						var count = actionListener.propDelCheck();
                        
						if(null == count){
							alert("删除属性前检测遇到异常!");
							return;
						}else{
							if(0 < count){
								alert("部份属性不允许删除!");
								return;
							}
						}
						
						var _val = domEles._configedPropGridTable.getSelectedData();
						$.each( _val, function(i, n){
							  datas=datas+"&propIds="+n.propId;
						}); 
						$.eAjax({
							url : GdsCatgConstants._delPropsUrl,
							type : "POST",
							data : datas,
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
										eDialog.success('属性关联批量删除成功！');
										refreshPropGrid();
								}else{
										eDialog.error('批量删除属性遇到异常！异常信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
									eDialog.error("批量删除属性遇到异常!");
							}
						});
					}
			},{
					caption:'取消'
			}]
			});
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个已配置属性进行删除！');
			return;
		}
	};
	var _fnBatchAddProps = function(){
		var ids = domEles._optionalPropGridTable.getCheckIds();
		var datas = "catgCode="+$('#prop-catg-code').val();
		if(ids && ids.length>0){
			eDialog.confirm("您是否要批量添加选定属性?",{
				buttons:[{
					caption:"确定",
					callback:function(){
						var _val = domEles._optionalPropGridTable.getSelectedData();
						$.each( _val, function(i, n){
							  datas=datas+"&propIds="+n.id;
						}); 
						
						$.eAjax({
							url : GdsCatgConstants._addPropsUrl,
							type : "POST",
							data : datas,
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
										eDialog.success('属性关联批量添加成功！');
										refreshPropGrid();
								}else{
										eDialog.error('批量添加属性遇到异常！异常信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
									eDialog.error("批量添加属性遇到异常!");
							}
						});
						
					}
			},{
					caption:'取消'
			}]
			});
		}else if(!ids || ids.length==0){
			eDialog.alert('请至少选择一个属性进行添加！');
			return;
		}
	};
	
	/**
	 * 属性配置复选框点击事件.
	 */
	var _fnChkBoxChange = function(chkBox,catgCode,type,msg){
		var _val = "0";
		if(isChecked(chkBox)){
			_val = "1";
		}
		$.eAjax({
			url : GdsCatgConstants._propchangeUrl,
			type : "POST",
			data : {
				"catgCode" : catgCode,
				"propId"   : chkBox.attr('data'),
				"type" : type,
				"value" :_val
			},
			success : function(data) {
				if(null == data || 'ok' != data.ecpBaseResponseVO.resultFlag){
					eDialog.alert("属性ID=["+chkBox.attr('data')+"]"+msg+"配置遇到异常!异常信息:"+data.ecpBaseResponseVO.resultMsg);
					restoreCheckBox(chkBox);
				}
			},
			error : function(e,xhr,opt){
					eDialog.error("属性ID=["+chkBox.attr('data')+"]"+msg+"配置遇到异常!");
					restoreCheckBox(chkBox);
			}
		});
	};
	
	
	
	/***********************
	 *  属性配置JS代码段结束.*
	 ***********************/
	var pageInit = {
		
	    bindClickEvent : function(){
	    	var clickEventType = 'click';
	        domEles._btnAddCategory.bind(clickEventType,actionListener.addCategory);
	        domEles._btnAddSubCategory.bind(clickEventType,actionListener.addSubCategory);
	        domEles._btnDelCategory.bind(clickEventType,actionListener.delCategory);
	        domEles._btnSave.bind(clickEventType,actionListener.saveCategory);
	        domEles._btnEditCategory.bind(clickEventType,actionListener.editCategory);
	        domEles._btnUpdate.bind(clickEventType,actionListener.updateCategory);
	        domEles._btnCancel.bind(clickEventType,actionListener.actionFormCancel);
	        domEles._btnCfgGroup.bind(clickEventType,actionListener.cfgGroup);
	        domEles._btnCfgProp.bind(clickEventType,actionListener.cfgProp);
	        // domEles._searchBtn1.bind(clickEventType,actionListener.searchBtn1Action);
	        domEles._searchBtn2.bind(clickEventType,actionListener.searchBtn2Action);
	        domEles._searchBtn3.bind(clickEventType,actionListener.searchBtn3Action);
	        domEles._searchBtn4.bind(clickEventType,actionListener.searchBtn4Action);
	        //domEles._btnBatchDelGroups.bind(clickEventType,_fnBatchDelGroups);
	        domEles._btnBatchAddGroups.bind(clickEventType,_fnBatchAddGroups);
	        domEles._btnBatchAddProps.bind(clickEventType,_fnBatchAddProps);
	        domEles._btnBatchDelProps.bind(clickEventType,_fnBatchDelProps);
	        domEles._btnGroupBack.bind(clickEventType,actionListener.backBtnAction);
	        domEles._btnPropBack.bind(clickEventType,actionListener.backBtnAction);
	        domEles._btnImgUpload.live("change",actionListener.imgUpload);
	        
	        
	       /* domEles._btnImgUpload.click(function(evt){
	     			busSelector.uploader({
	     				callback : function(data){
	     					if(data && data.results && data.results.length > 0){
	     						$('#mediaUuid').val(data.results[0].fileId);
	     						domEles._formImgPreviewHold.hide();
	     						domEles._formImgPreview.attr('src',data.results[0].url);
	     						domEles._formImgPreview.show();
	     					}
	     				}
	     			}, evt);
	        });*/
	        
	        
	        /**
	    	 * 搜索功能.
	    	 */
	    	$('#search').bind('click',function(){
	    		domEles._viewForm[0].reset();
				domEles._actionForm[0].reset();
				// 隐藏所有视图.
				actionListener.hideAllView();
	    		var _keyword = $('#keyword').val();
	    		if(''!=$.trim(_keyword)){
	    			url = GdsCatgConstants.TREE_NODE_URL_SEARCH;
	    			$.fn.zTree.init($("#"+GdsCatgConstants.treeId), categoryTree.settingSearch);
	    			url = GdsCatgConstants.TREE_NODE_URL_NORMAL;
	    		}else{
	    			url = GdsCatgConstants.TREE_NODE_URL_NORMAL;
	    			$.fn.zTree.init($("#"+GdsCatgConstants.treeId), categoryTree.settingNormal);
	    		}
	    		
	    	});
	    	
	    	
	    	/**
	    	 * 分类树绑定搜索关键字回车响应。
	    	 */
	    	$('#keyword').bind('keydown',function(e){
	    	    // 回车键响应。
	    	    if(e.keyCode==13) {
	    	    	$('#search').click();
	    	    }
	    	});
	    	
	    	
	    	$('#keyword').bind('input propertychange',function(){
	    		if('' == $('#keyword').val()){
	    			$('#search').click();
	    		}
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
	        
	    	/*$('form[id=optionalGroupsForm] input[name=groupName]').bind('keydown',function(e){
	    		if(e.keyCode == 13){
	    			domEles._searchBtn1.click();
	    		}
	    	});*/
	    	
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
	    optBind : function() {
			// 添加属性组按钮绑定事件.
			$('a[name=addgroup]').live('click', function() {
				var _ids=$(this).attr("data");
				eDialog.confirm("您确认要添加选定属性组吗?",{
					buttons:[{
						caption:"确定",
						callback:function(){
							_fnAddGroup(_ids);				
						}
				},{
						caption:'取消'
				}]
				});
			});
			// 删除属性组按钮添加事件.
			$('a[name=delgroup]').live('click', function() {
				var _ids=$(this).attr("data");
				eDialog.confirm("您确认要删除选定属性组吗?",{
					buttons:[{
						caption:"确定",
						callback:function(){
							_fnDelGroup(_ids);				
						}
				},{
						caption:'取消'
				}]
				});
				
			});
			
			// 添加属性按钮绑定事件.
			$('a[name=addProp]').live('click', function() {
				var _ids=$(this).attr("data");
				
				eDialog.confirm("您确认要添加选定属性吗?",{
					buttons:[{
						caption:"确定",
						callback:function(){
							_fnSingleAddProp(_ids);						
						}
				},{
						caption:'取消'
				}]
				});
			});
			// 删除属性按钮添加事件.
			$('a[name=delProp]').live('click', function() {
				var _ids=$(this).attr("data");
				eDialog.confirm("您确认要删除选定属性吗?",{
					buttons:[{
						caption:"确定",
						callback:function(){
							_fnSingleDelProp(_ids);					
						}
				},{
						caption:'取消'
				}]
				});
			});
			// 是否基础属性勾选框监听.
			$('input[type=checkbox][name=ifBasic]').live('change',function(){
				_fnChkBoxChange($(this),$('#prop-catg-code').val(),1,"是否基础属性");
			});
			// 是否必填属性勾选框监听.
			$('input[type=checkbox][name=ifHaveTo]').live('change',function(){
				_fnChkBoxChange($(this),$('#prop-catg-code').val(),2,"是否必填属性");
			});
			// 是否搜索属性勾选框监听.
			$('input[type=checkbox][name=ifSearch]').live('change',function(){
				_fnChkBoxChange($(this),$('#prop-catg-code').val(),3,"是否搜索属性");
			});	
			// 是否输入属性勾选框监听.
			$('input[type=checkbox][name=ifGdsInput]').live('change',function(){
				_fnChkBoxChange($(this),$('#prop-catg-code').val(),4,"是否输入属性");
			});	
			
			
		},
		initGrid : function(){
			// domEles._configedGroupsGridTable.initDT(_initConfigedGroupsDT);
			domEles._optionalGroupsGridTable.initDT(_initOptGroupsDT);
			domEles._optionalPropGridTable.initDT(_initOptPropGridDT);
			domEles._configedPropGridTable.initDT(_initConfigedPropGridDT);
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
//			pageInit.optBind();
//			pageInit.initGrid();
			pageInit.initCatlog();
			$("form[id=viewForm] select[id=ifEntityCode]").attr("disabled", true);
		}
	};

	pageInit.init();
	/*$('#test').bind('click',function(){
		bDialog.open({
            title : '分类选择',
            width : 350,
            height : 550,
            params:{multi : true,showRoot : false},
            url : GLOBAL.WEBROOT+"/goods/category/open/catgselect?catgType=2",
            callback:function(data){
            	if(data && data.results && data.results.length > 0 ){
                    var _catgs = data.results[0].catgs;
                    for(var i in _catgs){
                    	alert(_catgs[i].catgName);
                    }
				}
            }
        });
		
	});*/
	
   /* $('#dropDownTree').catgDropDown({backfillCatgName : 'dropDownTree',width:'300px',multi:true,showRoot:1});
	
	$('#test1,#test2').bind('click',function(){
		$('#dropDownTree').catgDropDown.change($(this).attr('data'));
		$('#dropDownTree').click();
	});*/
	
	
	
});

$(function(){

	/**
	 * zTree数据过滤.
	 */
  function ajaxDataFilter(treeId, parentNode, responseData) {
        if (responseData) {
          if(responseData.errorMessage){
        	  eDialog.error('媒体分类树初始异常!');
        	  return;
          }
        }
        return responseData;
    };
    
    /**
     * zTree异步错误.
     */
    function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    	eDialog.alert('分类树异步加载出错!状态码['+textStatus+']-'+errorThrown);
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
	 * 常量
	 */
	var GdsMediaCategory = {
			updateUrl : GLOBAL.WEBROOT+"/goods/mediacatg/mediacatgsave",
		    saveUrl : GLOBAL.WEBROOT+"/goods/mediacatg/mediacatgsave",
		    detailUrl : GLOBAL.WEBROOT+"/goods/mediacatg/mediacatgview",
		    treeId : "categoryTree",
		    shopIdPrefix : "SHOP-"
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
			// 新增分类保存按钮.
			_btnSave : $('#btnSave'),
			// 新增分类,编辑分类页面取消按钮.
			_btnCancel : $('#btnCancel'),
			// 图片上传.
			_btnImgUpload : $('#imgUpload'),
			
		    // Divs
			// 分类详情层
		    _divViewContent : $('#viewContent'),
		    // 表单视图层
		    _divFormContent : $('#formContent'),
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
		    // inputs
		    _parentName : $('#parent-name'),
		    _shopId : $('#shop-id'),
		    
		    // hiddens
		    _catgParent : $('#catg-parent'),
		    _mediaId : $('#mediaId'),
		    _vParentName : $('#v-parent-name')
		    
	};
	
	
	// 待切换显示DIV对象数组。
    var _divAry = new Array();
    _divAry.push(domEles._divFormContent);
    _divAry.push(domEles._divViewContent);
    
	
	 /**
     * 树节点点击事件响应。
     */
	 function onTreeClick(event, treeId, treeNode, clickFlag) {
		 
		// 显示分类详情内容层.
	    actionListener.switchView(domEles._divViewContent);
		domEles._viewForm[0].reset();
    	$.eAjax({
			url : GdsMediaCategory.detailUrl,
			type : "POST",
			cache : false,
			timeout : 5000,
			data : {"catgCode":treeNode.id},
			success : function(json) {
			    if(null != json && 'ok' == json.resultFlag){
			    	// 自动填充分类信息并展示。
			    	ebcForm.formDataFill(domEles._viewForm.attr('id'),json.respDTO);
			    	
			    	var _imgUrl = json.respDTO.mediaUrl;
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
			    	if(null != json.parentRespDTO){
			    		$('#v-parent-name').val(json.parentRespDTO.catgName);
			    	}else{
			    		$('#v-parent-name').val('');
			    	}
			    	
			    }else{
			    	eDialog.alert('获取分类信息失败!');
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
			 // 媒体分类树初始化功能代码段。
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
						url:GLOBAL.WEBROOT+"/goods/mediacatg/asyncData/getNodes",
						type:"post",
						autoParam:["id"],
						otherParam:{"catgType":GdsMediaCategory.catgType},
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
						url:GLOBAL.WEBROOT+"/goods/mediacatg/asyncData/mediacatgsearch",
						type:"post",
						otherParam:{"catgName":function(){return $('#keyword').val();}},
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
			   return $.fn.zTree.getZTreeObj(GdsMediaCategory.treeId);
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
			domEles._mediaId.val('');
			
			
			domEles._shopId.attr('disabled',false);
			
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
				eDialog.alert("请先选择一个图片分类!");
				return;
			}
			
			domEles._actionForm[0].reset();
			// 设置父节点信息.(需要针对catgType作区分处理)
			// 平台分类.
			domEles._parentName.val(nodeObj.name);
			domEles._catgParent.val(nodeObj.id);
			domEles._mediaId.val('');
			
			domEles._shopId.val(nodeObj.shopId);
			domEles._shopId.attr('disabled',true);
			
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
				eDialog.alert("请先选择一个图片分类进行删除!");
				return;
			}
	    	
	    	eDialog.confirm("您确认要删除选定分类吗?",{
				buttons:[{
					caption:"确定",
					callback:function(){
				    	$.eAjax({
							url : GLOBAL.WEBROOT + "/goods/mediacatg/mediacatgdel",
							type : "POST",
							data : {'catgCode':nodeObj.id},
							success : function(json) {
								if(null != json && 'ok' == json.resultFlag){
									eDialog.success('分类删除成功！');
									// 删除zTree对应节点.
									treeObj.removeNode(nodeObj);
									domEles._viewForm[0].reset();
									domEles._actionForm[0].reset();
									// 隐藏所有视图.
									actionListener.hideAllView();
								}else{
									eDialog.error('分类信息删除失败！错误信息:'+json.resultMsg);
								}
							},
							error : function(e,xhr,opt){
								eDialog.error("删除分类信息遇到异常!"); 
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
			var parentName = $('#v-parent-name').val();
			actionListener.switchView(domEles._divFormContent);
			domEles._actionForm[0].reset();
			domEles._shopId.attr("disabled", true);
	    	actionListener.switchActionBtn();
			$.eAjax({
				url : GdsMediaCategory.detailUrl,
				type : "POST",
				cache : false,
				timeout : 5000,
				data : {"catgCode":function(){return $('#v-catg-code').val();}},
				success : function(json) {
				    if(null != json && 'ok' == json.resultFlag){
				    	// 自动填充分类信息并展示。
				    	ebcForm.formDataFill(domEles._actionForm.attr('id'),json.respDTO);
				    	if(null == json.respDTO.catgParent){
				    		$('#catg-parent').val('');
				    	}
				    	var _imgUrl = json.respDTO.mediaUrl;
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
				    	eDialog.alert('获取分类信息失败!');
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
			domEles._shopId.attr('disabled',false);
			$.gridLoading({"message":"正在保存分类,请稍候..."});
			$.eAjax({
				url : GdsMediaCategory.saveUrl,
				type : "POST",
				data : ebcForm.formParams(domEles._actionForm),
				success : function(json) {
					$.gridUnLoading();
					if(null != json && 'ok' == json.resultFlag){
						eDialog.success('媒体分类创建成功!');
						var vo = json.respDTO;
						var newNode = null;
						// 获取分类树对象。
						var treeObj = categoryTree.getTree();
						var parentNode = null;
						
						// （无父分类,则为根分类,节点挂在店铺下.）
						if(1 == vo.catgLevel){
							newNode = {id:vo.catgCode, 
									   pId:GdsMediaCategory.shopIdPrefix+vo.shopId,
									   name:vo.catgName,
									   catgLevel:vo.catgLevel,
									   shopId:vo.shopId
									   };
								// 根据分类目录ID获取目录节点。
								parentNode = treeObj.getNodeByParam("id", GdsMediaCategory.shopIdPrefix+vo.shopId, null);
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
						domEles._viewForm[0].reset();
						// 自动填充分类信息并展示。
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
				    	
				    	
						
						var _imgUrl = vo.mediaUrl;
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
						eDialog.error('媒体分类创建失败!错误信息：'+json.resultMsg);
					}
					
				},
				error : function(e,xhr,opt){
					eDialog.error("媒体分类创建遇到异常!");
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
			$.gridLoading({"message":"正在更新图片分类,请稍候..."});
			$.eAjax({
				url : GdsMediaCategory.updateUrl,
				type : "POST",
				data : ebcForm.formParams(domEles._actionForm),
				success : function(json) {
					$.gridUnLoading();
					if(null != json && 'ok' == json.resultFlag){
						eDialog.success('图片分类更新成功!');
						var vo = json.respDTO;
						// 获取分类树对象。
						var treeObj = categoryTree.getTree();
                        $('#v-parent-name').val(parentName);
                        
                        var nodeObj = categoryTree.getSelectedNode();
                        
                        nodeObj.name = vo.catgName;
                        treeObj.updateNode(nodeObj);
                        treeObj.selectNode(nodeObj);
						actionListener.switchView(domEles._divViewContent);
						// 自动填充分类信息并展示。
				    	ebcForm.formDataFill(domEles._viewForm.attr('id'),vo);
				    	var _imgUrl = vo.mediaUrl;
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
						eDialog.error('图片分类更新失败!错误信息：'+json.resultMsg);
					}
				},
				error : function(e,xhr,opt){
					eDialog.error("图片分类更新遇到异常!");
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
				domEles._divCatgCode.hide();
			}else{
				domEles._btnSave.hide();
				domEles._btnUpdate.show();
				domEles._divCatgCode.show();
			}
		},
		/**
		 * 图片上传按钮监听.
		 */
		imgUpload : function(e){
			busSelector.uploader({
				fileTypeExts : '*.jpg;*.png;', //定义文件上传的文件类型限制【可选项，默认为*.jpg;*.gif;*.bmp;*.png图片格式】
				fileSizeLimit : "100KB",
				checktype : "single",
				callback : function(data){
					if(data && data.results && data.results.length > 0){
						var result = data.results[0];
						$('#mediaId').val(result.fileId);
						//增加图片规格    默认为240x240! s add by zhanbh 2017.1.6
						var imgHeight = domEles._formImgPreview.closest(".imgup-show").innerHeight();
						var finalUrl = result.url;
						if(0 >= imgHeight){
							imgHeight = 240;
						}
						finalUrl = finalUrl.replace(result.fileId,result.fileId+"_"+imgHeight+"x"+imgHeight+"!");
						//增加图片规格    240x240! e
						domEles._formImgPreviewHold.hide();
						domEles._formImgPreview.show();
						domEles._formImgPreview.attr('src',finalUrl);
					}
				}
			}, e);
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
	        domEles._btnCancel.bind(clickEventType,actionListener.actionFormCancel);
	        domEles._btnImgUpload.bind(clickEventType,actionListener.imgUpload);
	        
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
	    			$.fn.zTree.init($("#"+GdsMediaCategory.treeId), categoryTree.settingSearch);
	    		}else{
	    			$.fn.zTree.init($("#"+GdsMediaCategory.treeId), categoryTree.settingNormal);
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
	    	
	        
	    },
	    showTree : function(){
	    	// 树展现
	    	$.fn.zTree.init($("#"+GdsMediaCategory.treeId), categoryTree.settingNormal);
	    },
		init : function(){
			pageInit.bindClickEvent();
			pageInit.showTree();
		}
	};

	pageInit.init();
	
	/*$('#dropDownTree').mcDropDown({backfillCatgName : 'dropDownTree',width:'500px',multi:false});
	
	$('#test1,#test2').bind('click',function(){
		$('#dropDownTree').mcDropDown.change($(this).attr('data'));
		$('#dropDownTree').click();
	});*/
	
	
	
});

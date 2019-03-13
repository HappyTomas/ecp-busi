/**
 * 图片分类下拉选择器。
 */
(function($) {
	
	var Constants = {
			treeContentDIV : "gdsMediaCategoryDropDownDIV",    			// zTree DIV
			treeId : "gdsMeidaCategoryTree",				   			// zTree Id.
			$this : null,									   			// 当前对象.
			multi : false,									   			// 是否多选.
			_shopId : null,									   			// 店铺ID.
			url : GLOBAL.WEBROOT+"/goods/mediacatg/asyncData/selector/getNodes"  // 分类节点异步加载地址.
	};
	
	
	/**
	 * zTree DIV。
	 */
	
	$.fn.mcDropDown = function(opt){
		var p = $.extend($.fn.mcDropDown.p,$.fn.mcDropDown.defaults,opt);
		Constants.$this = this;
		Constants.multi = p.multi;
		createTreeContentDIV(p);
		// $.fn.mcDropDown.initDropDown(p);
		this.bind('click',{obj : this},showTree);
	};
	
	
	$.fn.mcDropDown.change = function(shopId){
		if(null == shopId){
			categoryTree.getTree().destory();
			return;
		}
		if(Constants._shopId != shopId){
			Constants._shopId = shopId;
		    $.fn.mcDropDown.initDropDown($.fn.mcDropDown.p);
		}
	};
	
	
	/**
	 * 默认设置。
	 */
	$.fn.mcDropDown.defaults = {
			multi : false,	// 是否多选 true-多选 false-单选。
			backfillCatgName : '',  // 分类名回填组件ID。
			backfillCatgCode : '',     // 分类编码回填组件ID。
			width : '200px', // 默认宽度。
			height : '300px', // 默认高度。
			backgroundColor : '#f0f6e4' // 背景色。
	};

	 /**
     * 树节点点击事件响应。
     */
	function onTreeClick(event, treeId, treeNode, clickFlag) {
		// 复选情况节点点击直接返回。
		if($.fn.mcDropDown.p.multi){
			return;
		}
		
		var zTree = categoryTree.getTree(),
		nodes = zTree.getSelectedNodes();
		if(null != nodes && nodes.length > 0){
			var catgName = nodes[0].name;
			var catgCode = nodes[0].id;
			
			if('' != $.fn.mcDropDown.p.backfillCatgName){
				
				$('#'+$.fn.mcDropDown.p.backfillCatgName).val(catgName);
			}
			if('' != $.fn.mcDropDown.p.backfillCatgCode){
				$('#'+$.fn.mcDropDown.p.backfillCatgCode).val(catgCode);
			}
			
		}
	}
	 /**
	  * 多选。
	  */
	 function onCheck(e, treeId, treeNode) {
		    if(!multi){
		    	return;
		    }
			var zTree = categoryTree.getTree();
			var nodes = zTree.getCheckedNodes(true);
			var catgName = "";
			var catgId = "";
			
			
			for (var i=0, l=nodes.length; i<l; i++) {
				catgName += nodes[i].name + ",";
				catgId += nodes[i].id + ",";
			}
			if (catgName.length > 0 ) catgName = catgName.substring(0, catgName.length-1);
			if (catgId.length > 0 ) catgId = catgId.substring(0, catgId.length-1);
			
           if('' != $.fn.mcDropDown.p.backfillCatgName){
				$('#'+$.fn.mcDropDown.p.backfillCatgName).val(catgName);
			}
			if('' != $.fn.mcDropDown.p.backfillCatgCode){
				$('#'+$.fn.mcDropDown.p.backfillCatgCode).val(catgCode);
			}
			
			
		}
	 
	 
	
	 /**
	  * ajax数据过滤.
	  * @param treeId
	  * @param parentNode
	  * @param responseData
	  * @returns
	  */
	 function ajaxDataFilter(treeId, parentNode, responseData) {
	        if (responseData) {
	            if(responseData.errorMessage){
	          	  eDialog.error('图片分类树初始异常!');
	          	  return;
	            }
	            for(var i =0; i < responseData.length; i++) {
	                var nodeData = responseData[i];
	                if(true == nodeData.isRoot){
	                	if(nodeData.shopId != "SHOP-"+Constants._shopId){
	                		nodeData = null;
	                	}
	                    responseData[i].nocheck = true;
	                }
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
						selectedMulti:false
					},
					check : {
						enable : false,
						chkboxType : {"Y":"","N":""}
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					async: {
						enable: true,
						url:GLOBAL.WEBROOT+"/goods/mediacatg/asyncData/selector/getNodes",
						type:"post",
						autoParam:["id"],
					    dataFilter: ajaxDataFilter
					},
					callback: {
						beforeClick: beforeClick,
						onClick : onTreeClick,
						onAsyncError : zTreeOnAsyncError,
						onAsyncSuccess: zTreeOnAsyncSuccess,
						onCheck : onCheck
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
						onAsyncSuccess: zTreeOnAsyncSuccess,
						onCheck : onCheck
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
			   return $.fn.zTree.getZTreeObj(Constants.treeId);
			}
	};
	
	function showTree(e) {
		var treeOffset = e.data.obj.offset();
		$("#"+Constants.treeId).css({left:treeOffset.left + "px", top:treeOffset.top + e.data.obj.outerHeight() + "px"}).slideDown("fast");
		$("#"+Constants.treeContentDIV).css({left:treeOffset.left + "px", top:treeOffset.top + e.data.obj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == Constants.$this.attr('id') 
				|| event.target.id == Constants.treeContentDIV 
				|| $(event.target).parents("#"+Constants.treeContentDIV).length>0)) {
			hideTree();
		}
	}
	function hideTree() {
		$("#"+Constants.treeContentDIV).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function createTreeContentDIV(opt){
		
		var div = document.createElement("div");
		div.id = Constants.treeContentDIV;
		div.style.display = 'none';
		div.style.position = 'absolute';
		div.style.backgroundColor = opt.backgroundColor;
		div.style.overflow = 'auto';
		div.style.width = opt.width;
		div.style.height = opt.height;
		div.style.borderColor = 'black';
		div.style.borderWidth = '1px';
		div.style.borderStyle = 'solid';
		
		var ul = document.createElement('ul');
		ul.id = Constants.treeId;
		ul.className = 'ztree';
		
		div.appendChild(ul);
		
		Constants.$this.parent().append($(div));
		
	}
	
	function initTree(){
		if(null == Constants._shopId){
			categoryTree.getTree().destory();
		}
		categoryTree.settingNormal.async.url = Constants.url+"?shopId="+Constants._shopId;
		// 树展现
    	$.fn.zTree.init($("#"+Constants.treeId), categoryTree.settingNormal);
	}
	
	$.fn.mcDropDown.p = {};
	
	
	$.fn.mcDropDown.initDropDown = function(opt){
		categoryTree.settingNormal.view.selectedMulti = opt.multi;
		categoryTree.settingNormal.check.enable = opt.multi;
		initTree();
	};

})(jQuery);
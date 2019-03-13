$(function(){
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _params = bDialog.getDialogParams(_dlg);
	
	var _multiFlag = false;
	
    var domEles = {
    	_treeName : "category-tree",
    	_btnConfirm : $('#confirm',"#catg"),
    	_btnClose : $('#close',"#catg"),
    	_search : $('#search',"#catg"),
    	_keyword : $('#keyword',"#catg")
    };
    
    var constants = {
    	TREE_NODE_URL_NORMAL : GLOBAL.WEBROOT+"/cmslinkinpututil/asyncData/selector/getNodes?siteId="+$('#siteId',"#catg").val(),     // 分类树正常获取节点URL地址.
    	TREE_NODE_URL_SEARCH : GLOBAL.WEBROOT+"/cmslinkinpututil/asyncData/selector/catgsearch?siteId="+$('#siteId',"#catg").val()	 // 分类树搜索功能获取节点URL地址.
    };
    
  
    /**
	 * 点击前事件.
	 * @param treeId
	 * @param treeNode
	 * @param clickFlag
	 * @returns {Boolean}
	 */
	function beforeClick(treeId, treeNode, clickFlag) {
		return (treeNode.click != false);
	 };
	 /**
	  * 树异步加载错误回调.
	  * @param event
	  * @param treeId
	  * @param treeNode
	  * @param XMLHttpRequest
	  * @param textStatus
	  * @param errorThrown
	  */
	 function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	    	eDialog.alert('分类树异步加载出错!状态码['+textStatus+']-'+errorThrown);
	 };
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
	          	  eDialog.error('商品分类树初始异常!');
	          	  return;
	            }
	            for(var i =0; i < responseData.length; i++) {
	                var nodeData = responseData[i];
	                if(true == nodeData.isRoot){
	                    responseData[i].nocheck = true;
	                }
	              }
	          }
	          return responseData;
	 };
    
    
    var treeSettings = {
    		normalSetting : {
	    			view:{
						selectedMulti: _multiFlag
					},
					check : {
						enable : _multiFlag,
						chkboxType : {"Y":"","N":""}
					},
    				data: {
    					simpleData: {
    						enable: true
    					}
    				},
    				async: {
    					enable: true,
    					url:constants.TREE_NODE_URL_NORMAL,
    					type:"post",
    					autoParam:["id"],
    					otherParam:{"showRoot":function(){return treeSettings.showRootParam();}},
    					dataFilter: ajaxDataFilter
    				},
    				callback: {
    					beforeClick: beforeClick,
    					onAsyncError : zTreeOnAsyncError
    				}
    		},
    		
    		searchSetting : {
	    			view:{
						selectedMulti: _multiFlag
					},
					check : {
						enable : _multiFlag,
						chkboxType : {"Y":"","N":""}
					},
    				data: {
    					simpleData: {
    						enable: true
    					}
    				},
    				async: {
    					enable: true,
    					url:constants.TREE_NODE_URL_SEARCH,
    					type:"post",
    					otherParam:{"catgName":function(){return $('#keyword',"#catg").val();},"showRoot":function(){return treeSettings.showRootParam()}},
    					dataFilter: ajaxDataFilter
    				},
    				callback: {
    					beforeClick: beforeClick,
    					onAsyncError : zTreeOnAsyncError
    				}
    		},
    		
    		showRootParam : function(){
    			var showRoot = 0;
    			
    			if(null != _params || null != _params.showRoot){
    				if(true != _params.showRoot && false != _params.showRoot){
    					return showRoot;
    				}else if(true == _params.showRoot){
    					showRoot = 1;
    				}else{
    					showRoot = 0;
    				}
    			}
    			return showRoot;
    		}
    };
    
    var actionListener = {
    		/**
    		 * 执行搜索.
    		 */
    		_doSearch : function(){
    			var _keyword = domEles._keyword.val();
    			if(''!=$.trim(_keyword)){
    				$.fn.zTree.init($("#"+domEles._treeName,"#catg"), treeSettings.searchSetting);
    			}else{
    				$.fn.zTree.init($("#"+domEles._treeName,"#catg"), treeSettings.normalSetting);
    			}
    		},
    		/**
    		 * 分类确认选择.
    		 */
    		_doConfirm : function(){
    			// 获取zTree
    	    	var _catgTree = $.fn.zTree.getZTreeObj(domEles._treeName);
    	    	var nodes = null;
    	    	if(_multiFlag){
    	    		nodes = _catgTree.getCheckedNodes(true);
    	    	}else{
    	    		nodes = _catgTree.getSelectedNodes();
    	    	}
    	    	
    	    	var node = null;
    	    	if (!nodes || nodes.length == 0) {
    				eDialog.alert("请先选择一个分类!");
    				return;
    			}else{
    				node = nodes[0];
    			}
    	    	// 获取选中节点
    	    	_selectTypeItem(node.catgCode,node.name);
    		},
    		_doClose : function(){
    			bDialog.closeCurrent();
    		},
    		_doKeydownEnter : function(e){
    			 // 回车键响应。
    		    if(e.keyCode==13) {
    		    	domEles._search.click();
    		    }
    		}
    };
    /**
     * 页面初始化.
     */
    var pageInit = {
    	init : function(){
    		var clickEventType = 'click';
    		var keydownEventType = "keydown";
    		domEles._btnConfirm.bind(clickEventType, actionListener._doConfirm);
    		domEles._search.bind(clickEventType, actionListener._doSearch);
    		domEles._btnClose.bind(clickEventType, actionListener._doClose);
    		domEles._keyword.bind(keydownEventType, actionListener._doKeydownEnter);
    		// 树展现
    		$.fn.zTree.init($("#"+domEles._treeName,"#catg"), treeSettings.normalSetting);
    	}	
    };
	
    pageInit.init();

});

$(function(){
	/**
	 * 插件参数 siteId shopIds ignoreDataAuth showRoot multi：是否多选 checked：已选分类数组 fillBack：对多选是否回填已选 limit：限制多选时最多选几个
	 */
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _params = bDialog.getDialogParams(_dlg) || {};
	
	var _multiFlag = false;
	
	var _siteId = _params.siteId;
	
	var _maxShowNode = $("#maxShowNode").val();
	
	var _shopIds = _params.shopIds;
	
	var _fillBackFlag = false;
	
	var _checkedList = _params.checked || [];//多选时已选择的数据 add by zhanbh
	
	function isMulti(){
		if(null == _params || (null != _params && (null == _params.multi || true == _params.multi))){
			_multiFlag = true;
			_fillBackFlag = true === _params.fillBack ? true : false;//只在多选状态下有效果    是否对已选数据进行回填
		}
	}
	
	// 显示调用是否多选设置.
	isMulti();
	var ignoreDataAuth = _params.ignoreDataAuth;
	
	
    var domEles = {
    	_treeName : "category-tree",
    	_btnConfirm : $('#confirm'),
    	_btnClose : $('#close'),
    	_search : $('#search'),
    	_keyword : $('#keyword')
    };
    
    var constants = {
    	TREE_NODE_URL_NORMAL : GLOBAL.WEBROOT+"/goods/category/asyncData/selector/getNodes?" + "maxShowNode=" + _maxShowNode,     // 分类树正常获取节点URL地址.
    	TREE_NODE_URL_SEARCH : GLOBAL.WEBROOT+"/goods/category/asyncData/selector/catgsearch",	 // 分类树搜索功能获取节点URL地址.
    	TREE_NODE_URL_NORMAL_WITHOUT_PARAM : GLOBAL.WEBROOT+"/goods/category/asyncData/selector/getNodes"
    };
    
    
    var url = constants.TREE_NODE_URL_NORMAL;
    
  
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
	 * 勾选前事件.
	 * @param treeId
	 * @param treeNode
	 * @param clickFlag
	 * @returns {Boolean}
	 * author zhanbh 16.12.27 多选状态下才有此回调函数
	  */
	var beforeCheck = null;
	if(_multiFlag){
		beforeCheck = function(treeId, treeNode){
			if(treeNode && treeNode.checked){
				_checkFillTool.unCheckNode(treeNode);//取消选择
				return true;
			}
			if(null != _params.limit && 0 < _params.limit){
				var limitNum = _params.limit;
				var nodes = null;
				nodes = _checkFillTool.getCheckNodes();
				if (limitNum <= nodes.length) {
					eDialog.alert("最多选择 "+limitNum+" 个分类！");
					return false;
				}
			}
			_checkFillTool.checkNode(treeNode);//选择分类
			return true;
		}
	}
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
		 if(_checkFillTool.status == "async"){
			 _checkFillTool.status = "error";
			 _checkFillTool.fillBack();//异步加载下个值
		 }	
	 };
	 /**
	  * 树异步加载成功回调 主要用于回填
	  * add by zhanbh
	  */
	 function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
		 if(_checkFillTool.status == "init"){//更新状态时加载需异步分类   
			 _checkFillTool.refleshAsyncIds();//更新需加载分类  是否进入回填在refleshAsyncIds这个方法内统一控制
		 }else if(_checkFillTool.status == "async"){
			 _checkFillTool.fillBack();//异步加载下个值
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
	          	  eDialog.error('商品分类树加载异常!');
	          	if(_checkFillTool.status == "async"){
		   			 _checkFillTool.status = "error";
		   			 _checkFillTool.fillBack();//异步加载下个值
		   		}
	          	  return;
	            }
	            for(var i =0; i < responseData.length; i++) {
	                var nodeData = responseData[i];
	                if(true == nodeData.isRoot){
	                	nodeData.nocheck = true;
	                }
	                //多选时且要求回填时 勾选已选的 add by zhanbh
	                if(_fillBackFlag && _checkFillTool.isChecked(nodeData.id)){
	                	_checkFillTool.checkNode(nodeData);//选择分类
	                	nodeData.checked = true;
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
    					url:function(){return url;},
    					type:"post",
    					autoParam:["id"],
    					otherParam:{"catgType":$('#catg-type').val(),
    						"catgName":function(){return $('#keyword').val();},
    						"showRoot":function(){return treeSettings.showRootParam();},
    						"catlogId":$("#catlog-id").val(),
    						"ignoreDataAuth":ignoreDataAuth,
    						"siteId":function(){
    							if(null != _siteId && undefined != _siteId){
    								return _siteId;
    							}else{
    								return "";
    							}
    						},
    						"shopIds":function(){
    							if(undefined != _shopIds && 0 != _shopIds.length){
    								return _shopIds;
    							}else{
    								return "";
    							}
    						}
    						},
    					dataFilter: ajaxDataFilter
    				},
    				callback: {
    					beforeClick: beforeClick,
    					beforeCheck:beforeCheck,
    					onAsyncError : zTreeOnAsyncError,
    					onAsyncSuccess : zTreeOnAsyncSuccess
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
    					url:function(){return url;},
    					type:"post",
    					autoParam:["id"],
    					otherParam:{"catgType":$('#catg-type').val(),
    						"catgName":function(){return $('#keyword').val();},
    						"showRoot":function(){return treeSettings.showRootParam();},
    						"catlogId":$('#catlog-id').val(),
    						"ignoreDataAuth":ignoreDataAuth,
    						"siteId":function(){
    							if(null != _siteId && undefined != _siteId){
    								return _siteId;
    							}else{
    								return "";
    							}
    						},
    						"shopIds":function(){
    							if(undefined != _shopIds && 0 != _shopIds.length){
    								return _shopIds;
    							}else{
    								return "";
    							}
    						},
    						maxShowNode : _maxShowNode
    						},
    					dataFilter: ajaxDataFilter
    				},
    				callback: {
    					beforeClick: beforeClick,
    					beforeCheck:beforeCheck,
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
    				url = constants.TREE_NODE_URL_SEARCH;
    				$.fn.zTree.init($("#"+domEles._treeName), treeSettings.searchSetting);
    				url = constants.TREE_NODE_URL_NORMAL_WITHOUT_PARAM;
    			}else{
    				url = constants.TREE_NODE_URL_NORMAL;
    				_checkFillTool.status = "init";//标记回填状态为初始化 add by zhanbh 只有在多选且要求回填才会起作用
    				$.fn.zTree.init($("#"+domEles._treeName), treeSettings.normalSetting);
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
    	    		nodes = _checkFillTool.getCheckNodes();//_catgTree.getCheckedNodes(true);  change by zhanbh
    	    	}else{
    	    		nodes = _catgTree.getSelectedNodes();
    	    	}
    	    	
    	    	
    	    	if (nodes.length == 0) {
    				eDialog.alert("请先选择一个分类!");
    				return;
    			}
    	    	// 获取选中节点
    	    	var _ary = new Array();
    	    	
    	    	for(var i in nodes){
    	    		var _obj = new Object();
    	    		_obj.catgCode = nodes[i].id;
    	        	_obj.catgName = nodes[i].name;
    	        	_obj.catgLevel = nodes[i].catgLevel;
    	        	if(null != nodes[i].catlogId){
    	        		_obj.catlogId = nodes[i].catlogId;
    	        	}
    	        	_ary.push(_obj);
    	    	}
    	    	bDialog.closeCurrent({
    				"catgs":_ary
    			});
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
    
    function checkParam(){
    	if(undefined != _siteId && isNaN(_siteId)){
    		alert("站点ID="+_siteId+"为无效参数");
    		return false;
    	}
    	return true;
    }
    
    /**
     * 多选状态下  已选分类回填工具类 add by zhanbh 2017.1.12
     */
    var _checkFillTool = (function(){
    	var idList = _checkedList;
    	var nodesMap = {};//已选的节点，键值对
    	var asyncIds = [];
    	var baseUrl = GLOBAL.WEBROOT+"/goods/category/asyncData/selector/getcatgpath?" + "idsStr=";// 获取分类树路径地址
    	/**
		 * 将选中的id 过滤并转化为字符串  方便统一处理  add by zhanbh
		 */
    	var filterList = function(list){
    		list = list || [];
			var result = [];
			var len = list.length;
			for(var i = 0 ; i < len ; i++){
				var val = list[i];
				if(val || val === 0){
					result.push(val+"");
				}
			}
			return result;
		}
    	idList = filterList(idList);
    	_checkedList = idList;
    	/**
    	 * 获取已选分类的树路径
    	 */
    	var getCheckIdPath = function(){
    		_checkFillTool.status = "start";
    		asyncIds = [];
    		if(0 >= idList.length){
    			nodesMap = {};
    	    	return 0;
    		}
    		$.gridLoading({"message":"加载中"});//加遮罩 防止未回填完点确定 出现数据异常
    		$.eAjax({
    			url : baseUrl+(idList.join(",")),
    			data : {},
    			success : function(returnInfo) {
    				if(returnInfo && returnInfo.errMsg){
    					eDialog.error("回填已选分类异常!");
    				}else{
    					var result = returnInfo["result"]||[];
    					var len = result.length;
    					for(var i = 0 ; i < len ; i++){
    						asyncIds = asyncIds.concat(pathStrToList(result[i]));
    					}
    					asyncIds = filterList(asyncIds);
    				}
    				_checkFillTool.status = "async";//标记进入回填状态
					_checkFillTool.fillBack();
    			},
    			error : function(e,xhr,opt){
    				eDialog.error("回填已选分类异常!");
    				_checkFillTool.status = "async";//标记进入回填状态
					_checkFillTool.fillBack();
    			},
    			exception : function(msg){
    				eDialog.error("回填已选分类异常!");
    				_checkFillTool.status = "async";//标记进入回填状态
					_checkFillTool.fillBack();
    			}
    		});
    	}
    	var pathStrToList = function(str){
    		if(!str){
    			return [];
    		}
    		str = str+"";
    		str = str.replace(/^\s*</ig,"").replace(/>\s*$/ig,"");
    		var strList = str.split("><");
    		strList.pop();//最后一个不需要展开
    		return strList;
    	}
    	return {
    		status : "static",
    		refleshAsyncIds : function(){//回填入口
    			if(_fillBackFlag){//是否回填总开关
    				getCheckIdPath();
    			}
    		},
    		isChecked : function(catgCode){
    			return 0 <= idList.indexOf(catgCode +"");
    		},
    		getCheckedNum : function(){
    			return idList.length;
    		},
    		checkNode : function(node){
    			if(!_fillBackFlag || !node || !node.id){
    				return this;
    			}
    			var  catgCode = node.id + "";
    			if(!_checkFillTool.isChecked(catgCode)){
    				idList.push(catgCode);
    			}
    			nodesMap[catgCode] = node;
    		},
    		unCheckNode:function(node){
    			if(!_fillBackFlag || !node || !node.id){
    				return this;
    			}
    			var  catgCode = node.id + "";
    			var index = idList.indexOf(catgCode);
    			if(0 <= index){//存在
    				delete idList[index];
    				delete nodesMap[catgCode];
    				idList = filterList(idList);
    				_checkedList = idList;
    			}
    		},
    		getCheckNodes:function(){
    			var nodes = null;
    			if(_fillBackFlag){
    				nodes = [];
    				for(var catg in nodesMap){
    					if(nodesMap.hasOwnProperty(catg)){
    						nodes.push(nodesMap[catg]);
    					}
    				}
    			}else{//非回填模式
    				var _catgTree = $.fn.zTree.getZTreeObj(domEles._treeName);
    				nodes = _catgTree.getCheckedNodes(true) || [];
    			}
    			return nodes;
    		},
    		fillBack:function(){
    			if(0 >= asyncIds.length || _checkFillTool.status != "async"){
    				_checkFillTool.status = "end";
    				asyncIds= [];
    				$.gridUnLoading();//回填完  去掉遮罩
    				return "end";
    			}
    			var treeObj = $.fn.zTree.getZTreeObj(domEles._treeName);
    			var catgCode = asyncIds.shift();//队列方式
    			var node = treeObj.getNodeByParam('id',catgCode,null);
    			if(!node || node.zAsync){
    				_checkFillTool.fillBack();
    			}else{
    				treeObj.reAsyncChildNodes(node, "refresh", true);//异步加载成功会回调fillBack
    			}
    		}
    	}
    }())
    
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
    		$('#keyword').bind('input propertychange',function(){
	    		if('' == $('#keyword').val()){
	    			$('#search').click();
	    		}
	    	});
    		if(!checkParam()){
    			return;
    		}
    		// 树展现
    		_checkFillTool.status = "init";//标记回填状态为初始化 add by zhanbh 只有在多选且要求回填才会起作用
    		$.fn.zTree.init($("#"+domEles._treeName), treeSettings.normalSetting);
    	}	
    };
    
    pageInit.init();

});

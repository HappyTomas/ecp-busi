$(function(){

	$('#searchleft').click(function(evt){
		var catgName = $('#keywordleft').val();
		var treeId=$("#category-tree-left");
		if(catgName == ""){
	    	$.fn.zTree.init(treeId, categoryTree.settingNormalLeft);
		}else{
	    	$.fn.zTree.init(treeId, categoryTree.settingSearchLeft);
		}

	});
	$('#searchright').click(function(evt){
		var catgName = $('#keywordright').val();
		var treeId=$("#category-tree-right");
		
		if(catgName == ""){
	    	$.fn.zTree.init(treeId, categoryTree.settingNormalRight);
		}else{
	    	$.fn.zTree.init(treeId, categoryTree.settingSearchRight);
		}

	});
	var url = GdsCatgConstants.CATEGORY_TREE_LEFT_DATA;

	 function onTreeClick(event, treeId, treeNode, clickFlag) {
		 var clickNode = ACTION_TREE.getSelectedNode(treeId);
		 //alert("点击树"+treeId+"的节点:"+clickNode.name);
		 var TreeNodes = [];
		 var childrenNodes = [];
		 childrenNodes = LoadData.ChildrenNodes(treeId, treeNode);
		 childrenNodes = eval("("+childrenNodes+")");
		 TreeNodes.push({'id':treeNode.id,'name':treeNode.name,'open':true,'isParent':true,click:false,children:childrenNodes});
		 pageInit.showCategorySubTree(treeId,TreeNodes);
		 pageInit.showCategoryRelation(treeId,treeNode);
		 
	 }
	 function beforeClick(treeId, treeNode, clickFlag) {
			return (treeNode.click != false);
	 }
	 function ajaxDataFilter(treeId, parentNode, responseData) {
	        if (responseData) {
	          if(responseData.errorMessage){
	        	  eDialog.error('平台分类树初始异常!');
	        	  return;
	          }
	        }
	        return responseData;
	    };
	function zTreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
		eDialog.alert('平台分类树异步加载出错!状态码['+textStatus+']-'+errorThrown);
	};
    function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
        var catgCode = $('#v-catg-code').val();
        if(null != catgCode && ''!=catgCode){
        	var treeObj = ACTION_TREE.getTree(treeId);
        	var selectNode = treeObj.getNodeByParam('id',catgCode,null);
			treeObj.selectNode(selectNode);
        }
    };
    
	var categoryTree = {
			settingSimple : {
				view:{
					selectedMulti: false,
					nameIsHTML : true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeClick: beforeClick,
					onClick : onTreeClick,
				}
			},
			settingNormalLeft : {
				view:{
					selectedMulti: false,
					nameIsHTML : true
				},
				data: {
					simpleData: {
						enable: true
					},
				callback: {
					beforeClick: beforeClick,
					onClick : onTreeClick,
					onAsyncError : zTreeOnAsyncError,
					onAsyncSuccess: zTreeOnAsyncSuccess
					}
				},
				async: {
					enable: true,
					url:function(){return GdsCatgConstants.CATEGORY_TREE_LEFT_DATA;},
					type:"post",
					autoParam:["id"],
					otherParam:{
						"catgType":"1",//平台分类
						"siteId" : $('#siteId').val(),
						"showRoot" : "1",
						"srcSystem" : function(){return $('#platType').val();},
						"shopId" : function(){return $('#shopId').val();},
						"shopAuthId":function(){return $('#shopAuthId').val();},						//"catlogId":GdsCatgConstants.initCatlogId,
						//"catgName":function(){return $('#keyword').val(); }
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
			settingNormalRight : {
				view:{
					selectedMulti: false,
					nameIsHTML : true
				},
				data: {
					simpleData: {
						enable: true
					},
				callback: {
					beforeClick: beforeClick,
					onClick : onTreeClick,
					onAsyncError : zTreeOnAsyncError,
					onAsyncSuccess: zTreeOnAsyncSuccess
					}
				},
				async: {
					enable: true,
					url:function(){return GdsCatgConstants.CATEGORY_TREE_RIGHT_DATA;},
					type:"post",
					autoParam:["id"],
					otherParam:{
						"srcSystem" : function(){return $('#platType').val();},
						"shopId" : function(){return $('#shopId').val();},
						"shopAuthId":function(){return $('#shopAuthId').val();},
						//"catlogId":GdsCatgConstants.initCatlogId,
						//"catgName":function(){return $('#keyword').val();}
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
			settingSearchLeft : {
					view:{
						selectedMulti: false,
						nameIsHTML : true
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					async: {
						enable: true,
						url:function(){return GdsCatgConstants.CATEGORY_TREE_LEFT_SEARCH_DATA;},
						type:"post",
						autoParam:["id"],
						otherParam:{
							"catgType":"1",//平台分类
							"siteId" : $('#siteId').val(),
							"showRoot" : "1",
							"srcSystem" : function(){return $('#platType').val();},
							"shopId" : function(){return $('#shopId').val();},
							"shopAuthId":function(){return $('#shopAuthId').val();},						//"catlogId":GdsCatgConstants.initCatlogId,
							"catgName":function(){return $('#keywordleft').val(); }
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
			settingSearchRight : {
				view:{
					selectedMulti: false,
					nameIsHTML : true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				async: {
					enable: true,
					url:function(){return GdsCatgConstants.CATEGORY_TREE_RIGHT_SEARCH_DATA;},
					type:"post",
					autoParam:["id"],
					otherParam:{
						//"siteId" : $('#siteId').val(),
						"srcSystem" : function(){return $('#platType').val();},
						"shopId" : function(){return $('#shopId').val();},
						"shopAuthId":function(){return $('#shopAuthId').val();},						//"catlogId":GdsCatgConstants.initCatlogId,
						"name":function(){return $('#keywordright').val(); }
						},
					dataFilter: ajaxDataFilter
				},
				callback: {
					beforeClick: beforeClick,
					onClick : onTreeClick,
					onAsyncError : zTreeOnAsyncError,
					onAsyncSuccess: zTreeOnAsyncSuccess
				}
		}
	};

	
	var pageInit = {
	    showCategoryTree : function(treeId){
	    	if(treeId==GdsCatgConstants.CATEGORY_TREE_LEFT)
	    	{
	    		treeId=$("#category-tree-left");
	    		url = GdsCatgConstants.CATEGORY_TREE_LEFT_DATA;
		    	$.fn.zTree.init(treeId, categoryTree.settingNormalLeft);
	    	}else{
	    		treeId=$("#category-tree-right");
	    		url = GdsCatgConstants.CATEGORY_TREE_RIGHT_DATA;
		    	$.fn.zTree.init(treeId, categoryTree.settingNormalRight);
	    	}
	    	// 树展现
	    },
	    showCategorySubTree:function(treeId,nodes){
	    	if(treeId==GdsCatgConstants.CATEGORY_TREE_LEFT){
	    		treeId=$("#category-attribute-tree-left");
	    		var _catgTree = ACTION_TREE.getTree(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT);
	    		if(_catgTree != null){
	    			_catgTree.destroy();
	    		}
	    	}else if(treeId==GdsCatgConstants.CATEGORY_TREE_RIGHT){
	    		treeId=$("#category-attribute-tree-right");
	    		var _catgTree = ACTION_TREE.getTree(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT);
	    		if(_catgTree != null){
	    			_catgTree.destroy();
	    		}
	    	}
	    	else if(treeId==GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_LEFT){
	    		treeId=$("#category-attribute-value-tree-left");
	    	}else if(treeId==GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_RIGHT){
	    		treeId=$("#category-attribute-value-tree-right");
	    	}else{
	    		return;
	    	}
	    	$.fn.zTree.init(treeId, categoryTree.settingSimple,nodes);
	    },
	    showCategoryRelation:function(treeId,clickNode){
	    	switch (treeId){
	    	 case GdsCatgConstants.CATEGORY_TREE_LEFT: 
	    	 case GdsCatgConstants.CATEGORY_TREE_RIGHT: 
	 	    	LoadData.CategoryRelation();
	 	    	LoadData.AttributeRelation();
	 	    	LoadData.ValueRelation();
	    		 break;
	    	 case GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_LEFT: 
	    	 case GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_RIGHT: 
	    		 LoadData.AttributeRelation();
	    		 LoadData.ValueRelation();
	    		 break;
	    	 case GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT: 
	    	 case GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT:
	    		 LoadData.ValueRelation();
	    		 break;
	    	 default : break;
	    	}
	    }
	};
	pageInit.showCategoryTree(GdsCatgConstants.CATEGORY_TREE_LEFT);
	pageInit.showCategoryTree(GdsCatgConstants.CATEGORY_TREE_RIGHT);
});
var RelationURL = {
		CATEGORY_ADD : GLOBAL.WEBROOT+"/unpf/catemap/add/category/relation",
		CATEGORY_DELETE : GLOBAL.WEBROOT+"/unpf/catemap/delete/category/relation",
		
		CATEGORY_RELATION : GLOBAL.WEBROOT+"/unpf/catemap/select/category/relation",
		CATEGORY_ATTRIBUTE_RELATION : GLOBAL.WEBROOT+"/unpf/catemap/select/category/attribute/relation",
		CATEGORY_ATTRIBUTE_VALUE_RELATION : GLOBAL.WEBROOT+"/unpf/catemap/select/category/attribute/value/relation"
};
var GdsCatgConstants = {
		TREE_NODE_URL_NORMAL : GLOBAL.WEBROOT+"/goods/category/asyncData/getNodes",     // 分类树正常获取节点URL地址.
    	TREE_NODE_URL_SEARCH : GLOBAL.WEBROOT+"/goods/category/asyncData/catgsearch",	 // 分类树搜索功能获取节点URL地址.
    	
    	CATEGORY_TREE_LEFT : "category-tree-left",
    	//CATEGORY_TREE_LEFT_DATA : GLOBAL.WEBROOT+"/goods/category/asyncData/selector/getNodes",
    	CATEGORY_TREE_LEFT_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/category/left",
    	CATEGORY_TREE_LEFT_SEARCH_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/category/left/search",

    	CATEGORY_TREE_RIGHT : "category-tree-right",
    	CATEGORY_TREE_RIGHT_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/category/right",
    	CATEGORY_TREE_RIGHT_SEARCH_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/category/right/search",

    	CATEGORY_TREE_ATTRIBUTE_LEFT : "category-attribute-tree-left",
    	CATEGORY_TREE_ATTRIBUTE_LEFT_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/attribute/left",
    	CATEGORY_TREE_ATTRIBUTE_RIGHT : "category-attribute-tree-right",
    	CATEGORY_TREE_ATTRIBUTE_RIGHT_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/attribute/right",
    	
    	CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT : "category-attribute-value-tree-left",
    	CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/attribute/value/left",
    	CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT : "category-attribute-value-tree-right",
    	CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT_DATA : GLOBAL.WEBROOT+"/unpf/catemap/asyncData/attribute/value/right",
};
var ACTION_TREE = {
		getSelectedNode : function(treeId){
			// 获取zTree
	    	var _catgTree = ACTION_TREE.getTree(treeId);
	    	if(_catgTree != null)
	    	{
				var nodes = _catgTree.getSelectedNodes();
				if(0 < nodes.length){
					return nodes[0];
				}	
	    	}
		},
		getParentNode : function(treeId){
			var nodeObj = ACTION_TREE.getSelectedNode(treeId);
			if(nodeObj){
				return nodeObj.getParentNode()
			}
		},
		getCurrentRootNode : function(treeNode){  
			   if(treeNode.getParentNode()!=null){    
			      var parentNode = treeNode.getParentNode();    
			      return ACTION_TREE.getCurrentRootNode(parentNode);    
			   }else{    
			      return treeNode;   
			   }  
			},
		getTree : function(treeID){
		   return $.fn.zTree.getZTreeObj(treeID);
		}	
};
var ACTION_RELATION = {
		
		ADD : function(type){
			var clickNodeLeft = null;
			var clickNodeRight = null;
			
			switch(type){
				case "category": 
					clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_LEFT);
					clickNodeRight = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_RIGHT);
					break;
				case "attribute": 
					clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_LEFT);
					clickNodeRight = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_RIGHT);
					break;
				case "value": 
					clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT);
					clickNodeRight = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT);
					break;
				default:
					break;
			}
			
			if(clickNodeLeft==null)
			{
				eDialog.alert("请选择左树节点！")
				return;
			}
			if(clickNodeRight==null)
			{
				eDialog.alert("请选择右树节点！")
				return;
			}
			//alert("关联节点【"+clickNodeLeft.name+"->"+clickNodeRight.name+"】");
			
			var leftJson = JSON.stringify(clickNodeLeft);
			var rightJson = JSON.stringify(clickNodeRight);
			var message = "你确定要关联【"+clickNodeLeft.name+"】与【"+clickNodeRight.name+"】吗？";
			eDialog.confirm(message, {
				buttons : [ {
					caption : '确认',
					callback : function() {
						$.eAjax({
							url : RelationURL.CATEGORY_ADD,
							data : {"leftNodeJson":leftJson,"rightNodeJson":rightJson,"type":type},
							async : false,
							dataType :  'json',
							type  : 'post',
							//traditional: true,  
							complete:function(returnInfo){

							},
							success : function(returnInfo) {
								if(returnInfo.resultFlag == 'ok'){
									eDialog.success('关联成功！');
									if(type == "category"){
								    	var _catgTree = ACTION_TREE.getTree(GdsCatgConstants.CATEGORY_TREE_LEFT);
										clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_LEFT);
								    	clickNodeLeft.name = "<font color=\"red\">"+clickNodeLeft.name+ "</font>";
								    	_catgTree.updateNode(clickNodeLeft);
									}
									ACTION_RELATION.REFRESH(type);
								}else{
									eDialog.alert(returnInfo.resultMsg);
								}
							}
						});
					}
				}, {
					caption : '取消',
					callback : $.noop
				} ]
			});
		},
		DELETE : function(relationId, type){
			eDialog.confirm("你确定要删除该关联关系吗？", {
				buttons : [ {
					caption : '确认',
					callback : function() {
						$.eAjax({
							url : RelationURL.CATEGORY_DELETE,
							data : {"relationId":relationId,"type":type},
							async : false,
							dataType :  'json',
							type  : 'post',
							//traditional: true,  
							complete:function(returnInfo){
							},
							success : function(returnInfo) {
								if(type == "category"){
							    	var _catgTree = ACTION_TREE.getTree(GdsCatgConstants.CATEGORY_TREE_LEFT);
									var clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_LEFT);
							    	var nodename = clickNodeLeft.name;
							    	var start = "<font color=\"red\">";
							    	var end   = "</font>";
							    	nodename = nodename.substring(start.length,nodename.length-end.length)
							    	clickNodeLeft.name = nodename;
							    	_catgTree.updateNode(clickNodeLeft);
								}
								ACTION_RELATION.REFRESH(type);
							}
						});
					}
				}, {
					caption : '取消',
					callback : $.noop
				} ]
			});
		},
		REFRESH : function(type){
			switch(type){
			case "category":
				LoadData.CategoryRelation();
				break;
			case "attribute": 
				LoadData.AttributeRelation();
				break;
			case "value": 
				LoadData.ValueRelation();
				break;
			default:
				break;
			};
		}
	};
var LoadData = {
		ChildrenNodes:function(treeId,treenode){
		 var load_url = "";
		 if(treeId==GdsCatgConstants.CATEGORY_TREE_LEFT){
			 load_url = GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_LEFT_DATA;
			 treenode.srcSystem = $('#platType').val();
			 treenode.shopId = $('#shopId').val();
			 treenode.shopAuthId = $('#shopAuthId').val();
		 }else if(treeId==GdsCatgConstants.CATEGORY_TREE_RIGHT){
			 load_url = GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_RIGHT_DATA;
		 }else if(treeId==GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_LEFT){
			 load_url = GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT_DATA;
		 }else if(treeId==GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_RIGHT){
			 load_url = GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT_DATA;
		 }
		 //"srcSystem":$('#platType').val(),"shopId":$('#shopId').val(), "shopAuthId":$('#shopAuthId').val()
		var param = {
				"srcSystem" : treenode.srcSystem,
				"shopId" : treenode.shopId,
				"id" : treenode.id,
				"pId": treenode.pId,
				"shopAuthId" : treenode.shopAuthId
		};
		var data = [];
		$.eAjax({
			url : load_url,
			data : param,
			async : false,
			complete:function(returnInfo){
				data = returnInfo.responseText;
			},
			success : function(returnInfo) {
			}
		});
		return data;
	},
	CategoryRelation:function(){
		
		var clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_LEFT);
		var clickNodeRight = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_RIGHT);
		
		var leftNodeJson = JSON.stringify(clickNodeLeft);
		var rightNodeJson = JSON.stringify(clickNodeRight);
		
		var _load_url_ = RelationURL.CATEGORY_RELATION;
		
		$('#category-relation-table').load(_load_url_,{"leftNodeJson":leftNodeJson,"rightNodeJson":rightNodeJson});
		
	},
	AttributeRelation:function(){
		var clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_LEFT);
		var clickNodeRight = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_RIGHT);
		
		var leftNodeJson = JSON.stringify(clickNodeLeft);
		var rightNodeJson = JSON.stringify(clickNodeRight);
		
		var _load_url_ = RelationURL.CATEGORY_ATTRIBUTE_RELATION;
		
		$('#category-attribute-relation-table').load(_load_url_,{"leftNodeJson":leftNodeJson,"rightNodeJson":rightNodeJson});
		
	},
	ValueRelation:function(){
		var clickNodeLeft = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_LEFT);
		var clickNodeRight = ACTION_TREE.getSelectedNode(GdsCatgConstants.CATEGORY_TREE_ATTRIBUTE_VALUE_RIGHT);
		
		var leftNodeJson = JSON.stringify(clickNodeLeft);
		var rightNodeJson = JSON.stringify(clickNodeRight);
		
		var _load_url_ = RelationURL.CATEGORY_ATTRIBUTE_VALUE_RELATION;
		$('#category-attribute-value-relation-table').load(_load_url_,{"leftNodeJson":leftNodeJson,"rightNodeJson":rightNodeJson});
		
	}
};

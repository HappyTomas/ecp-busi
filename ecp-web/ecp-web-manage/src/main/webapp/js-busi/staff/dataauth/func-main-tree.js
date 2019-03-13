$(function(){

	var setting = {
		async: {
			enable: true,
			url: GLOBAL.WEBROOT + "/dataauth/datafunc/list",
			autoParam:["id=parentId"],
			otherParam: {"sysCode":$("#sysCode").val()}
		},
		data:{
			key:{
				name: "name",
				title: "code"
			},
			simpleData:{
				enable: true,
				idKey: "id",
				pIdKey: "parentId",
			}
		},
		callback:{
			onClick: funcOnClick,
			onAsyncSuccess: onAsyncSuccess
		},
		view: {
			selectedMulti: false,
			fontCss: getFont,
			nameIsHTML: true
		}
	};
	
	/**
	 * 节点样式
	 */
	function getFont(treeId, node) {
		var normalCss = {color:"#333", "font-weight":"normal"};
		if(!!node.highlight){
//			return {color:"#A60000", "font-weight":"bold"};
			return {color:"#e66828", "font-weight":"bold"};
		}else{
			var res = node.font ? _isNullObj(node.font) ? normalCss : node.font : normalCss;
			return res;
		}
	}
	
	/**
	 * 是否是空对象
	 */
	function _isNullObj(obj){
	    for(var i in obj){
	        if(obj.hasOwnProperty(i)){
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * 节点单击事件
	 */
	function funcOnClick(event, treeId, treeNode, clickFlag) {
		if(clickFlag==1){
			var parentNode = treeNode.getParentNode();
			//父节点
			if(parentNode){
				$("#parentId").attr("value", parentNode.id);
				$("#parentIdLabel").html(parentNode.name);
			}else{
				$("#parentId").attr("value", "0");
				$("#parentIdLabel").html("");
			}
			_fillForm(treeNode);
		}else{
			$("#parentId").attr("value", "0");//“父节点”设置为0
			$("#parentIdLabel").html("");//清空
			_fillForm({});
		}
	}
	
	
	/**
	 * 加载树成功后初始化
	 */
	function onAsyncSuccess(event, treeId, treeNode, msg){
		//默认选中第一个
		/*
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var clickFirstNode = treeObj.getNodes()[0];
		if(clickFirstNode){
			treeObj.selectNode(clickFirstNode);//选中默认节点
			var parentNode = clickFirstNode.getParentNode();
			if(parentNode){
				$("#parentId").value(parentNode.id);
				$("#parentIdLabel").html(parentNode.name);
			}
			_fillForm(clickFirstNode);
		}*/
	}
	
	/**
	 * 填充表单数据
	 */
	function _fillForm(node){
		if(node){
			if(node.id){
				$("#id").val(node.id);
			}else{
				$("#id").val("");
			}
			if(node.code){
				$("#code").val(node.code);
			}else{
				$("#code").val("");
			}
			if(node.name){
				$("#name").val(node.name);
			}else{
				$("#name").val("");
			}
			//status
			$("#status").val(node.status);
			if(node.sortOrder){
				$("#sortOrder").val(node.sortOrder);
			}else{
				$("#sortOrder").val("");
			}
			if(node.funcDesc){
				$("#funcDesc").val(node.funcDesc);
			}else{
				$("#funcDesc").val("");
			}
			//修改状态
			if(node.code&&node.name){
				$("#objectState").attr("value","0");//1表示新增，0表示修改
			}else{
				$("#objectState").attr("value","1");
			}
		}
	}
	
	/**
	 * 清空表单
	 */
	function _clearForm(){
		//清空表单
		$("#detailInfoForm input,#detailInfoForm textarea").each(function(i){
			var self = $(this);
			if(self&&self.attr("id")=="parentId"){
				var treeObj = $.fn.zTree.getZTreeObj("treeFunc");
				var nodes = treeObj.getSelectedNodes();
				var selected = {};
				if(nodes.length>0){
					selected = nodes[0];
				}
				$("#parentIdLabel").html(selected.name);
				self.attr("value", selected.id);
				return true;
			}
			self.val("");
		});
	}
	
	/**
	 * 节点搜索
	 */
	var SearchNode = {
			key :  $("#searchValue"),
			searchButton: $("#treeSerach"),
			lastValue : "",
			nodeList : [],
			searchNode : function(e){
				var zTree = _getZTreeObj();
				var value = $.trim(SearchNode.key.get(0).value);
				var keyType = "name";
				if (SearchNode.key.hasClass("empty")) {
					value = "";
				}
//				if (SearchNode.lastValue === value) return;
				SearchNode.lastValue = value;
				if (value === "") {
					SearchNode.updateNodes(false);
					return;
				}
				SearchNode.updateNodes(false);
				SearchNode.nodeList = zTree.getNodesByParamFuzzy(keyType, value);
				SearchNode.updateNodes(true);
			},
			updateNodes : function(highlight) {
				var zTree = _getZTreeObj();
				if(SearchNode.nodeList.length==0){
					return;
				}
				for( var i=0, l=SearchNode.nodeList.length; i<l; i++) {
					SearchNode.nodeList[i].highlight = highlight;
					zTree.updateNode(SearchNode.nodeList[i]);
					if(SearchNode.nodeList[i].getParentNode()!=null&&highlight){
						zTree.expandNode(SearchNode.nodeList[i].getParentNode(),true,false);
					}
				}
			},
			focusKey : function(e) {
				if (SearchNode.key.hasClass("empty")) {
					SearchNode.key.removeClass("empty");
				}
			},
			blurKey : function(e) {
				if (SearchNode.key.get(0).value === "") {
					SearchNode.key.addClass("empty");
				}
			}
	};
	
	/**
	 * 节点查询事件绑定
	 */
	SearchNode.key.bind("focus", SearchNode.focusKey)
	.bind("blur", SearchNode.blurKey);
//	.bind("propertychange", SearchNode.searchNode)
//	.bind("input", SearchNode.searchNode);
	SearchNode.searchButton.bind("click", SearchNode.searchNode);
	
	//得到当前树对象
	function _getZTreeObj(){
		return $.fn.zTree.getZTreeObj("treeFunc");
	}
	
	/**初始化树**/
	$.fn.zTree.init($("#treeFunc"), setting, []);
	
});
$(function(){
	var setting = {
		async: {
			enable: true,
			url: GLOBAL.WEBROOT + "/authrole/generatefunc/entiretree",
			otherParam: {"sysCode":$("#sysCode").val(), "zTreeIsLoad":false}
		},
		data:{
			key:{
				name: "name",
				title: "name"
			},
			simpleData:{
				enable: true,
				idKey: "zId",
				pIdKey: "zPId",
			}
		},
		check:{
			enable: true,
			nocheckInherit: true
		},
		callback:{
			onClick: funcOnClick,
			onCheck: funcOnCheck,
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
		return node.font ? node.font : {};
	}

	/**
	 * 节点单击事件
	 */
	function funcOnClick(event, treeId, treeNode, clickFlag) {

	}
	
	/**
	 * 节点复选框选中事件
	 */
	function funcOnCheck(event, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		/*
		 * 过滤器
		 * 节点为权限规则 并且是选中状态
		 */
		var filter = function(node) {
		    return (node.zType == "rule" && node.checked == true);
		}
		var nodes = treeObj.getNodesByFilter(filter)
		if(nodes.length>0){//待定权限关系
			var privlgRule = "";
			for(var i=0; i<nodes.length; i++){
				privlgRule += ","+nodes[i].privilegeId;
			}
			//赋值
			$("#privlgRule").val(privlgRule.substr(1));
		}else{
			//赋值,置空
			$("#privlgRule").val("");
		}
	};
	
	/**
	 * 加载树成功后初始化
	 */
	var flagOnAsyncSuccessInit = false;  //初次初始化
	function onAsyncSuccess(event, treeId, treeNode, msg){
		var treeObj = $.fn.zTree.getZTreeObj(treeId);//树
		//初始化后不再异步取数据
		treeObj.setting.async.otherParam = {"sysCode":$("#sysCode").val(), "zTreeIsLoad":true};
		
		if($("#rule")[0]&&!flagOnAsyncSuccessInit){
			var arrNodes = treeObj.getNodes();
			//装饰树
			var inputh = $("#rule").val();//得到权限规则
			if(inputh&&inputh.length>0){
				var arrRule = inputh.split(",");
				for(var i=0; i<arrRule.length; i++){
					/*
					 * 过滤器
					 * 节点为权限规则 并且是选中状态
					 */
					var filter = function(node) {
					    return (node.zType == "rule" && node.id == arrRule[i]);
					}
//					var treeNode = treeObj.getNodeByParam("id", arrRule[i]);
					var treeNode = treeObj.getNodesByFilter(filter, true);
					treeObj.checkNode(treeNode, true, true, false);
				}
			}
			
			flagOnAsyncSuccessInit = true;
		}
	}
	
	
	/**初始化树**/
	$.fn.zTree.init($("#treeFunc"), setting, []);
	
});
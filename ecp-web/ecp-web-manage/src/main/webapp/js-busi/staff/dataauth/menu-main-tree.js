$(function(){

	var setting = {
		async: {
			enable: true,
			url: GLOBAL.WEBROOT + "/authrole/generatemenu/entiretree",
			otherParam: {"sysCode":$("#sysCode").val()}
		},
		data:{
			key:{
				name: "menuTitle",
				title: "menuDesc"
			},
			simpleData:{
				enable: true,
				idKey: "id",
				pIdKey: "parentMenuId",
			}
		},
		callback:{
			onClick: menuOnClick,
			onAsyncSuccess: onAsyncSuccess
		}
	};
	/**
	 * 节点单击事件
	 */
	function menuOnClick(event, treeId, treeNode, clickFlag) {
		if(clickFlag==1){
			$("#funcCode").val(treeNode.menuCode);
			$("#funcId").val(treeNode.id);
		}else{
			$("#funcCode").val("");
			$("#funcId").val("");
		}
		if($('#dataGridTable')) $('#dataGridTable').gridSearch([{"name":"funcCode","value":$("#funcCode").val()}]);
		if($('#btnFormReSet')) $('#btnFormReSet').click();
	}
	
	
	/**
	 * 加载树成功后初始化
	 */
	function onAsyncSuccess(event, treeId, treeNode, msg){
		//默认选中第一个
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var clickFirstNode = treeObj.getNodes()[0];
		if(clickFirstNode){
			treeObj.selectNode(clickFirstNode);//选中默认节点
			$("#funcCode").val(clickFirstNode.menuCode);
			$("#funcId").val(clickFirstNode.id);
			if($('#dataGridTable')) $('#dataGridTable').gridSearch([{"name":"funcCode","value":clickFirstNode.menuCode}]);//初始化表格
			if($('#btnFormReSet')) $('#btnFormReSet').click();
		}
	}
	
	/**初始化树**/
	$.fn.zTree.init($("#treeMenu"), setting, []);
	
});
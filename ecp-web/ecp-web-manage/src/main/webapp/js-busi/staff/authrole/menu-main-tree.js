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
		check:{
			enable: true,
			nocheckInherit: true
		},
		callback:{
			onClick: menuOnClick,
			onCheck: menuOnCheck,
			onAsyncSuccess: onAsyncSuccess
		}
	};
	/**
	 * 节点单击事件
	 */
	function menuOnClick(event, treeId, treeNode, clickFlag) {
		
	}
	/**
	 * 节点复选框选中事件
	 */
	function menuOnCheck(event, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length>0){//待定权限关系
			var privlgMenu = "";
			for(var i=0; i<nodes.length; i++){
				privlgMenu += ","+nodes[i].privilegeId;
			}
			//赋值
			$("#privlgMenu").val(privlgMenu.substr(1));
		}else{
			//赋值,置空
			$("#privlgMenu").val("");
		}
	};
	
	/**
	 * 加载树成功后初始化
	 */
	var flagOnAsyncSuccessInit = false;  //初次初始化
	function onAsyncSuccess(event, treeId, treeNode, msg){
		if($("#menu")[0]&&!flagOnAsyncSuccessInit){
			var treeObj = $.fn.zTree.getZTreeObj(treeId);//树
			var arrNodes = treeObj.getNodes();
			//装饰树
			var inputh = $("#menu").val();//得到权限菜单
			var patt = new RegExp("^[0-9,]*$");//校验为纯数字
			if(inputh&&inputh.length>0&&patt.test(inputh)){
				var arrMenu = inputh.split(",");
				for(var i=0; i<arrMenu.length; i++){
					var treeNode = treeObj.getNodeByParam("id", arrMenu[i]);
					treeObj.checkNode(treeNode, true, false, false);
				}
			}
			
			flagOnAsyncSuccessInit = true;
		}
	}
	
	/**初始化树**/
	$.fn.zTree.init($("#treeMenu"), setting, []);
	
});
$(function(){
	
	//全局变量
	var _currentSysCode = "";//从列表得到的“sysCode”
	
	var _currentMenu = "";//从列表得到的对应角色的权限菜单
	var _currentMenuSelectObj = "";//当前预备权限菜单展现对象
	
	var _currentRule = "";//从列表得到的对应角色的权限规则
	var _currentRuleSelectObj = "";//当前预备权限规则展现对象
	
	
	//*************ztree begin menu*****************//
	var setting = {
		async: {
			enable: true,
			url: GLOBAL.WEBROOT + "/authrole/generatemenu/entiretree",
			otherParam: {"sysCode":""}
		},
		view: {
			dblClickExpand: false
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
			onClick: onClick,
			beforeCheck: function(){return false},
			onAsyncSuccess: onAsyncSuccessMenutree
		}
	};
	/**
	 * 节点单击事件
	 */
	function onClick(event, treeId, treeNode, clickFlag) {
	}
	/**
	 * 树加载成功
	 */
	function onAsyncSuccessMenutree(event, treeId, treeNode, msg){
		var treeObj = $.fn.zTree.getZTreeObj(treeId);//树
		if(_currentMenuSelectObj[0]){
			var objOffset = _currentMenuSelectObj.offset();
			//装饰树
			treeObj.checkAllNodes(false);//清空勾选
			var inputh = _currentMenu;//得到权限菜单
			if(inputh&&inputh.length>0){
				var arrMenu = inputh.split(",");
				for(var i=0; i<arrMenu.length; i++){
					var treeNode = treeObj.getNodeByParam("id", arrMenu[i]);
					treeObj.checkNode(treeNode, true, false, false);
				}
			}
			//展示树
			$("#menuContent").css({left:objOffset.left + "px", top:objOffset.top + _currentMenuSelectObj.outerHeight() + "px"}).slideDown("fast");
			treeObj.expandAll(true);
		}
	}
	/**初始化树**/
	$.fn.zTree.init($("#treeMenu"), setting, []);
	/**event**/
	function showMenu(event) {
		var self = this;
		var treeObj = $.fn.zTree.getZTreeObj("treeMenu");//树
		var selfObj = $(self);
		_currentMenuSelectObj = selfObj;
		var selfOffset = $(self).offset();
		if(_currentSysCode==selfObj.next().attr("sysCode")){
			//装饰树
			treeObj.checkAllNodes(false);//清空勾选
			var inputh = selfObj.next().val();//得到权限菜单
			if(inputh&&inputh.length>0){
				var arrMenu = inputh.split(",");
				for(var i=0; i<arrMenu.length; i++){
					var treeNode = treeObj.getNodeByParam("id", arrMenu[i]);
					treeObj.checkNode(treeNode, true, false, false);
				}
			}
			//展示树
			$("#menuContent").css({left:selfOffset.left + "px", top:selfOffset.top + selfObj.outerHeight() + "px"}).slideDown("fast");
			treeObj.expandAll(true);
		}else{
			//初始化全局变量
			_currentSysCode = selfObj.next().attr("sysCode");
			_currentMenu = selfObj.next().attr("value");
			
			treeObj.setting.async.otherParam = {"sysCode":_currentSysCode};
			treeObj.reAsyncChildNodes(null, "refresh", true);//刷新树
		}
		//下拉框绑定
		$("body").bind("mousedown", onBodyDownMenu);
	}
	//隐藏菜单
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownMenu);
	}
	function onBodyDownMenu(event) {
		var id = event.target.id;
		if (!((event.target.id!=""&&event.target.id.indexOf("menuBtn") != -1) || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	//*************ztree end menu*****************//
	//*************ztree begin func*****************//
	var settingFunc = {
		async: {
			enable: true,
			url: GLOBAL.WEBROOT + "/authrole/generatefunc/entiretree",
			otherParam: {"sysCode":"", "zTreeIsLoad":false}
		},
		view: {
			dblClickExpand: false
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
			beforeCheck: function(){return false},
			onAsyncSuccess: onAsyncSuccessFunctree
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
	 * 树加载成功
	 */
	function onAsyncSuccessFunctree(event, treeId, treeNode, msg){
		var treeObj = $.fn.zTree.getZTreeObj(treeId);//树
		//初始化后不再异步取数据
		treeObj.setting.async.otherParam = {"sysCode":$("#sysCode").val(), "zTreeIsLoad":true};
		
		if(_currentRuleSelectObj[0]){
			var objOffset = _currentRuleSelectObj.offset();
			//装饰树
			treeObj.checkAllNodes(false);//清空勾选
			var inputh = _currentRule;//得到权限菜单
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
					var treeNode = treeObj.getNodesByFilter(filter, true);
					treeObj.checkNode(treeNode, true, false, false);
				}
			}
			//展示树
			$("#ruleContent").css({left:objOffset.left + "px", top:objOffset.top + _currentRuleSelectObj.outerHeight() + "px"}).slideDown("fast");
			treeObj.expandAll(true);
		}
	}
	/**初始化树**/
	$.fn.zTree.init($("#treeFunc"), settingFunc, []);
	/**event**/
	function showFunc(event) {
		var self = this;
		var treeObj = $.fn.zTree.getZTreeObj("treeFunc");//树
		var selfObj = $(self);
		_currentMenuSelectObj = selfObj;
		var selfOffset = $(self).offset();
		if(_currentSysCode==selfObj.next().attr("sysCode")){
			//装饰树
			treeObj.checkAllNodes(false);//清空勾选
			var inputh = selfObj.next().val();//得到权限菜单
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
					var treeNode = treeObj.getNodesByFilter(filter, true);
					treeObj.checkNode(treeNode, true, false, false);
				}
			}
			//展示树
			$("#funcContent").css({left:selfOffset.left + "px", top:selfOffset.top + selfObj.outerHeight() + "px"}).slideDown("fast");
			treeObj.expandAll(true);
		}else{
			//初始化全局变量
			_currentSysCode = selfObj.next().attr("sysCode");
			_currentRule = selfObj.next().attr("value");
			
			treeObj.setting.async.otherParam = {"sysCode":_currentSysCode};
			treeObj.reAsyncChildNodes(null, "refresh", true);//刷新树
		}
		//下拉框绑定
		$("body").bind("mousedown", onBodyDownFunc);
	}
	//隐藏菜单
	function hideFunc() {
		$("#funcContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownFunc);
	}
	function onBodyDownFunc(event) {
		var id = event.target.id;
		if (!((event.target.id!=""&&event.target.id.indexOf("ruleBtn") != -1) || event.target.id == "funcContent" || $(event.target).parents("#funcContent").length>0)) {
			hideFunc();
		}
	}
	//*************ztree end func*****************//
	//表格数据初始化
	$("#dataGridTable").initDT({
        'pTableTools' : false,
        'pSingleCheckClean' : false,
        "sAjaxSource": GLOBAL.WEBROOT + '/authrole/gridlist',
        //指定列数据位置
        "aoColumns": [
            { "mData": "id", "sTitle":"角色id","sWidth":"80px","bSortable":false,"bVisible":false},
            { "mData": "roleName", "sTitle":"角色名称","sWidth":"80px","bSortable":false},      
            { "mData": "sysCodeValue", "sTitle":"归属子系统","sWidth":"80px","bSortable":false},      
            { "mData": "roleDesc", "sTitle":"角色描述","sWidth":"80px","bSortable":false},         
            { "mData": "statusValue", "sTitle":"角色状态","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
            	if(data=="无效") data="失效";
				return data;
            }},         
			{ "mData": "menuValue", "sTitle":"关联菜单","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				var amenuId = 'menuBtn'+row.DT_RowId;
				if(data!==null&&data.length>10){
					data = data.substring(0,10) + "...";
				}
				var castStr = '<a id="'+amenuId+'" href="javascript:void(0)" >'+data+'</a><input type="hidden" value="'+row.menu+'" sysCode="'+row.sysCode+'" />';
				
				if(data==null) castStr = "无";
				return castStr;
			}},
			{ "mData": "ruleValue", "sTitle":"关联规则","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				var aruleId = 'ruleBtn'+row.DT_RowId;
				if(data!==null&&data.length>10){
					data = data.substring(0,10) + "...";
				}
				var castStr = '<a id="'+aruleId+'" href="javascript:void(0)" >'+data+'</a><input type="hidden" value="'+row.rule+'" sysCode="'+row.sysCode+'" />';
				
				if(data==null) castStr = "无";
				return castStr;
			}},
			{ "mData": "roleOrder", "sTitle":"排序","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return data;
			}},
			{ "mData": "updateTime", "sTitle":"最后更新时间","sWidth":"80px","bSortable":false,"mRender": function(data,type,row){
				return ebcDate.dateFormat(data,"yyyy-MM-dd");
			}}
        ],
        "eDbClick" : function(){
        	//handleBiz();
        },
        "onSuccess" : function(){
        	$("a[id^='menuBtn']").on('click', showMenu);
        	$("a[id^='ruleBtn']").on('click', showFunc);
        }
	
	});
	
	//handleBiz
	var handleBiz = function(callback){
		var val = $('#dataGridTable').getSelectedData();
		if(val && val.length==1){
			if(callback){
				callback(val);
			}
		}else if(val && val.length>1){
			eDialog.alert('只能选择一个项目进行操作！');
		}else if(!val || val.length==0){
			eDialog.alert('请选择至少选择一个项目进行操作！');
		}
	};
	
	//查询
	$('#btnFormSearch').click(function(){
		if(!$("#searchForm").valid()) return false;
		var p = ebcForm.formParams($("#searchForm"));
		$('#dataGridTable').gridSearch(p);
	});
	//重置
	$('#btnFormReset').click(function(){
		ebcForm.resetForm('#searchForm');
	});
	
	//跳转到新增页面
	$('#btnFormAdd').click(function(){
		eNav.setSubPageText("新增角色");
		window.location.href = GLOBAL.WEBROOT + "/authrole/add";
	});
	
	//跳转到修改页面
	$('#btnFormUpdate').click(function(){
		handleBiz(function(val){
			eNav.setSubPageText("修改角色");
    		$("#roleId").val(val[0].id);
    		$('#updateForm').submit();
    	});
	});
	
	//删除//失效
	$('#btnFormDeleteSubmit').click(function(){
		handleBiz(function(){
			var rowData = $('#dataGridTable').getSelectedData()[0];
			if(rowData.status==0){
				eDialog.warning('该角色已经失效！'); 
				return;
			}
			eDialog.confirm("是否确定设置为失效？", {
				buttons : [{
					caption : '确定',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/authrole/deleterole",
							data : {'id':rowData.id,'sysCode':rowData.sysCode},
							datatype:'json',
							success : function(data) {
								var reflag = data.resultFlag;//ok  fail
						    	var remsg = data.resultMsg;
						    	if(reflag=="ok"){
						    		eDialog.success('设置成功！'); 
									$('#btnFormSearch').click();//刷新列表
						    	}else{
						    		eDialog.error('设置失败！'+remsg); 
						    	}
							}
						});
					}
				},{
					caption : '返回',
					callback : function(){
						bDialog.closeCurrent();
					}
				}]
			});
		});
	});
	
	//生效
	$('#btnFormEnableSubmit').click(function(){
		handleBiz(function(){
			var rowData = $('#dataGridTable').getSelectedData()[0];
			if(rowData.status==1){
				eDialog.warning('该角色已经生效！'); 
				return;
			}
			eDialog.confirm("是否确定设置为生效？", {
				buttons : [{
					caption : '确定',
					callback : function(){
						$.eAjax({
							url : GLOBAL.WEBROOT + "/authrole/enablerole",
							data : {'id':rowData.id,'sysCode':rowData.sysCode},
							datatype:'json',
							success : function(data) {
								var reflag = data.resultFlag;//ok  fail
						    	var remsg = data.resultMsg;
						    	if(reflag=="ok"){
						    		eDialog.success('设置成功！'); 
									$('#btnFormSearch').click();//刷新列表
						    	}else{
						    		eDialog.error('设置失败！'+remsg); 
						    	}
							}
						});
					}
				},{
					caption : '返回',
					callback : function(){
						bDialog.closeCurrent();
					}
				}]
			});
		});
	});
	
	
	
});


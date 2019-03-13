$(function(){

	var setting = {
		async: {
			enable: true,
			url: GLOBAL.WEBROOT + "/dataauth/datafunc/list",
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
		return node.font ? node.font : {};
	}

	/**
	 * 节点单击事件
	 */
	function funcOnClick(event, treeId, treeNode, clickFlag) {
		if(clickFlag==1){
			_refreshDataGridTable(treeNode);
			_loadDataItemList(treeNode);// data-rule
		}else{
			_refreshDataGridTable({});
			_loadDataItemList({});// data-rule
		}
		_refreshDataRuleView()//data-rule
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
			_refreshDataGridTable(clickFirstNode);//“数据功能”
			_loadDataItemList(clickFirstNode);// data-rule
			_refreshDataRuleView();//data-rule
		}
	}
	
	/**
	 * 刷新规则配置页面内容 rule-data
	 */
	function _refreshDataRuleView(){
		var btnFormAuthConfigSubmit = $("#btnFormAuthConfigSubmit");
		if(btnFormAuthConfigSubmit[0]&&btnFormAuthConfigSubmit.attr("value")==2){
			//btnFormAuthConfigSubmit.removeClass("btn-success").addClass("btn-primary");
			btnFormAuthConfigSubmit.html('<i class="icon-edit"></i>配置明细');
			if($("#dataRuleDiv").css("display")=="block"){
				$("#dataRuleDiv").fadeOut("fast",function(){
					$("#dataAuthDiv").fadeIn("fast");
				});
				$("#ruleOjbectTableTips").hide();//tips
			}
			if($("#fieldRuleDiv").css("display")=="block"){
				$("#fieldRuleDiv").fadeOut("fast",function(){
					$("#dataAuthDiv").fadeIn("fast");
				});
			}
			btnFormAuthConfigSubmit.attr("value", "1");//状态变更
		}
		
	}
	
	/**
	 * 刷新表格
	 */
	function _refreshDataGridTable(node){
		if($('#dataGridTable')[0]) $('#dataGridTable').gridSearch([{"name":"funcId","value":node.id||-1}]);
		var searchParams = [{"name":"funcId","value":node.id||-1}];
		searchParams.push({"name":"name", "value":""});
		searchParams.push({"name":"authCode", "value":""});
		var authSrc = $("select[name='authSrc']");
		searchParams.push({"name":"authSrc", "value":!!authSrc[0]?authSrc.val():"1"});
		if($('#dataAuthGridTable')[0]) $('#dataAuthGridTable').gridSearch(searchParams);
	}
	
	/**
	 * 初始化dataItemList,以便准备select
	 */
	function _loadDataItemList(node){
		//场景 data-rule
		if($('#dataItemList')[0]==undefined){
			return;
		}
		//确认功能点
		if(!node.id){//不为空校验
			$('#dataItemList').val("");//置空
			return false;
		}
		//表单数据
		var _val = [{'name':'funcId', 'value': node.id},{"name":"listAll","value":1}];
		var _url = GLOBAL.WEBROOT + "/dataauth/dataitem/gridlist";
		$.eAjax({
			url : _url,
			data : _val,
			datatype:'json',
			success : function(data) {
				if(data&&data.gridResult&&data.gridResult.list.length>0){
					$('#dataItemList').val(JSON.stringify(data.gridResult.list));
				}
			}
		});
	}
	
	/**初始化树**/
	$.fn.zTree.init($("#treeFunc"), setting, []);
	
});
$(function(){
	
	//获得当前弹出窗口对象
	var _dlg = bDialog.getDialog();
	//获得父窗口传递给弹出窗口的参数集
	var _param = bDialog.getDialogParams(_dlg);
	//获得父窗口设置的回调函数
	var _callback = bDialog.getDialogCallback(_dlg);
	var openParam = _param.param;
	var checkType = _param.checkType;
	var _siteId = _param.siteId;
	var _channelType= _param.channelType;
	/////--------------确定按钮----------------////
	$('#btn_save_channel').click(function(evt){
		//解析选中的节点
		var param = "";
		var stringShow = "";
		var siteUrl="";
		if($('#siteUrl').val().length>0){
			siteUrl=$('#siteUrl').val();
		}
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length>0){
			var channelArrayId = "";
			var channelArrayName = "";
			for(var i=0; i<nodes.length; i++){
				channelArrayId += ","+nodes[i].id;
				channelArrayName +=","+nodes[i].name;
			}
			//赋值
			param = channelArrayId.substr(1);//将第一个,去掉
			stringShow = channelArrayName.substr(1);
		}else{
			//赋值,置空
		}
	
		//返回选中的参数给父页面
		bDialog.closeCurrent({
			'param' : param,
			'stringShow' : stringShow,
			'siteUrl' : siteUrl
		});
	});
	
	
	$("#btn_save_channel").click(function(evt){
		bDialog.closeCurrent();
	});
	//////-----------站点下的栏目树查询--------------/////
	$('#btnserachTree').click(function(evt){
		var siteId;
		if(_siteId!=null && _siteId!=""){
			siteId = _siteId;
		}
		else
			siteId = $("#sysSiteIdSearch").val();
		$('#siteId').attr('value', siteId);
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	//绑定平台选择查询
	$('#siteId').change(function(){
        var siteId = $("#siteId").val();
		$('#siteId').attr('value', siteId);
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	//绑定平台选择查询
	$('#platformType').change(function(){
        var PlatformType = $("#platformType").val();
		$('#platformType').attr('value', PlatformType);
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	//绑定栏目类型选择查询
	$('#channelType').change(function(){
		var channelType = $("#channelType").val();
		$('#channelType').attr('value', channelType);
		$.fn.zTree.init($("#tree"), classify.setting);
	});
	/////////////-------------------------名字匹配-------------------------///////////////////
	$('#treeSerach').click(function(evt){
		var menuName =  $("#searchValue").val(); 
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var nodes = treeObj.getNodesByParamFuzzy("name", menuName, null);
        //隐藏树
        var allnodes = treeObj.getNodes(); 
        treeObj.hideNodes(allnodes);
        
        for(var i=0;i<nodes.length;i++)
    	{
        	//显示父节点
        	showParentNodes(nodes[i]);
        	treeObj.showNode(nodes[i]);
        	var node = nodes[i].getParentNode();
        	treeObj.expandNode(node, true, true, true);
    	}
	});
	
	function showParentNodes(nodes)
	{
        var treeObj = $.fn.zTree.getZTreeObj("tree");
    	var node = nodes.getParentNode();
    	if(node != null)
    	{
        	treeObj.showNode(node);
    		showParentNodes(node);
    	}
	}

	function onAsyncSuccess(event, treeId, treeNode, msg){
			var treeObj = $.fn.zTree.getZTreeObj("tree");//树
			//装饰树
			var patt = new RegExp("^[0-9,]*$");//校验为纯数字
			if(openParam&&openParam.length>0&&patt.test(openParam)){
				var channelArray = openParam.split(",");
				for(var i=0; i<channelArray.length; i++){
					var treeNode = treeObj.getNodeByParam("id", channelArray[i]);
					if(treeNode != null)
					{
						treeObj.checkNode(treeNode, true, false, false);
						treeObj.expandNode(treeNode.getParentNode(), true, false, true);
					}
				}
			}
	}
/////////////------------------------------- 树设置 -----------------------------------------///////	
var classify = {

	setting:{
		view:{
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:"id",
				pIdKey:"pId"
			}
		},
		check:{
			chkStyle: checkType,
			chkboxType: { "Y": "", "N": "" },
			radioType: "all",
			enable: true
		},
		async: {
			enable: true,
			url:GLOBAL.WEBROOT+"/cms/channel/openloadnodes",
			type:"post",
			dataType:"json",
			autoParam:["id=channelParent"],
			otherParam:{
						"siteId" : function() {
							if (_siteId) {
								$('#siteId').attr('disabled', 'disabled');
								$('#btnserachTree').hide();
								return _siteId;
							} else {
								var siteId = $('#siteId').val();
								return siteId;
							}
						},
						"isOutLink" : function() {
							var isOutLink = $('#isOutLink').val();
							return isOutLink?isOutLink:"";
						},
						"isresiteinfo" : function() {
							var isresiteinfo = $('#isresiteinfo').val();
							return isresiteinfo?isresiteinfo:"";
						},
						"siteInfoType" : function() {
							var siteInfoType = $('#siteInfoType').val();
							return siteInfoType?siteInfoType:"";
						},
						"platformType" : function() {
							var platformType = $('#platformType').val();
							return platformType?platformType:"";
						},
						"channelType" : function() {
							if(_channelType){
								$('#channelType').attr('disabled', 'disabled');
								$('#btnserachTree').hide();
								return _channelType;
							}else{
								var channelType = $('#channelType').val();
								return channelType?channelType:"";
							}
						}
			},
			dataFilter: null
		},
		callback: {
	//		onClick :onTreeClick
			onAsyncSuccess: onAsyncSuccess
			}
	   }
};

$.fn.zTree.init($("#tree"), classify.setting);
/////////////----------------------------zTree js代码 结束-----------------------------///////////////////	
});